package com.tianque.sysadmin.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.mode.MoveMode;
import com.tianque.controller.vo.OrgDataWhenAdd;
import com.tianque.controller.vo.PermissionTreeData;
import com.tianque.controller.vo.TreeData;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.ExtTreeData;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.Permission;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.sysadmin.vo.PermissionType;
import com.tianque.userAuth.api.PermissionDubboService;

/**
 * @author Administrator
 * 
 */
@Namespace("/sysadmin/permissionManage")
@Controller("permissionController")
@Scope("prototype")
@Transactional
public class PermissionController extends BaseAction {

	@Autowired
	PermissionDubboService permissionDubboService;
	private List<ExtTreeData> extTreeDatas = new ArrayList<ExtTreeData>();
	private ExtTreeData extTreeData;
	private List<Permission> permissions;
	private String parentId;
	private Long rootId;
	private Long id;
	private Long orgLevel;
	private Long orgType;
	private List<Organization> organizations;
	private PageInfo<Organization> pageInfo;
	private GridPage gridPage;
	private List<TreeData> treeData = new ArrayList<TreeData>();
	private String position;
	private String operation;
	private String searchContext;
	private Long referOrgId;
	private Integer countChildren;
	private List<String> nodeIds = new ArrayList<String>();
	private String parentNodeIdsForSearch = "";
	private List<PropertyDict> orgTypes;
	private List<PropertyDict> orgLevels;
	private OrgDataWhenAdd orgDataWhenAdd;
	private boolean shouldJugeMultizones;
	private boolean excludeRoot;
	private boolean allOrg;
	private Permission permission;
	private String cname;
	private String name;
	private Session session;
	private User user;
	private List<String> userPermissionEname;

	@Action(value = "firstLoadPermission", results = { @Result(type = "json", params = {
			"root", "extTreeDatas", "excludeNullProperties", "true",
			"ignoreHierarchy", "false" }) })
	public String firstLoadPermission() {
		// 判断indexId中的排序数据是不是已经初始化过了
		if (null != parentId)
			permissions = permissionDubboService.getPermissionByParentId(Long
					.valueOf(parentId));
		else {
			permissions = permissionDubboService.getRootPermissions();
		}
		extTreeDatas = parsePermissionForExtTree(permissions);

		return SUCCESS;
	}

	private List<ExtTreeData> parsePermissionForExtTree(
			List<Permission> permissions) {
		List<ExtTreeData> extTreeDatas = new ArrayList<ExtTreeData>();
		for (Permission permission : permissions) {
			PermissionTreeData data = new PermissionTreeData(permission,
					isFolder(permission));
			extTreeDatas.add(data);
		}
		return extTreeDatas;
	}

	private boolean isFolder(Permission permission) {
		List<Permission> list = permissionDubboService
				.getPermissionByParentId(permission.getId());
		for (Permission p : list) {
			if (p.isEnable()
					&& p.getPermissionType() == PermissionType.MENU_TYPE) {
				return true;
			}
		}
		return false;
	}

	@Action(value = "getChildPermissions", results = { @Result(type = "json", params = {
			"root", "gridPage", "excludeNullProperties", "true",
			"ignoreHierarchy", "false" }) })
	public String getChildPermissions() {
		session = ThreadVariable.getSession();
		if (this.session == null || !session.isLogin()) {
			return SUCCESS;
		}
		user = this.permissionDubboService.getSimpleUserById(session
				.getUserId());
		if (!user.isAdmin()) {
			return SUCCESS;
		}
		if (parentId.equals("orgTree-root")) {
			permissions = permissionDubboService.getRootPermissions();
			PageInfo<Permission> pageInfo = new PageInfo<Permission>();
			pageInfo.setResult(permissions);
			pageInfo.setTotalRowSize(1);
			pageInfo.setCurrentPage(1);
			pageInfo.setPerPageSize(20);
			gridPage = new GridPage(pageInfo);
			return SUCCESS;
		}
		if (parentId != null) {
			PageInfo<Permission> pageInfo = permissionDubboService
					.getChildPermissions(Long.parseLong(parentId));
			gridPage = new GridPage(pageInfo);
		}
		return SUCCESS;
	}

	/**
	 * @return 修改页面
	 */
	@Action(value = "updatePermission", results = { @Result(name = "success", location = "/sysadmin/permissionManage/refactorPermission.jsp") })
	public String updatePermission() {
		session = ThreadVariable.getSession();
		if (this.session == null || !session.isLogin()) {
			return SUCCESS;
		}
		user = this.permissionDubboService.getSimpleUserById(session
				.getUserId());
		if (!user.isAdmin()) {
			return SUCCESS;
		}
		updatePermissionName();
		return SUCCESS;
	}

	@Action(value = "updatePermissionName", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String updatePermissionName() {
		session = ThreadVariable.getSession();
		if (this.session == null || !session.isLogin()) {
			return SUCCESS;
		}
		user = this.permissionDubboService.getSimpleUserById(session
				.getUserId());
		if (!user.isAdmin()) {
			return SUCCESS;
		}

		permission = permissionDubboService.updatePermissionName(permission);
		this.id = permission.getId();
		this.cname = permission.getCname();
		if (id == null || cname == null || cname == "") {
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "movePermission", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String movePermission() {
		session = ThreadVariable.getSession();
		if (this.session == null || !session.isLogin()) {
			return SUCCESS;
		}
		user = this.permissionDubboService.getSimpleUserById(session
				.getUserId());
		if (!user.isAdmin()) {
			return SUCCESS;
		}
		try {
			Permission p = permissionDubboService
					.getPermissionByPermissionId(this.permission.getId());
			if (MoveMode.TO_FIRST.equals(mode) || MoveMode.TO_END.equals(mode)) {
				permissionDubboService.moveFirstOrLast(p, mode);
			} else if (MoveMode.TO_NEXT.equals(mode)
					|| MoveMode.TO_PREVIOUS.equals(mode)) {
				permissionDubboService.movePreviousOrNext(p, mode);
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@Action(value = "findPermissionsByPermissionName", results = { @Result(name = "success", type = "json", params = {
			"root", "permissions", "excludeNullProperties", "true" }) })
	public String findPermissionsByPermissionName() {
		session = ThreadVariable.getSession();
		if (this.session == null || !session.isLogin()) {
			return SUCCESS;
		}
		user = this.permissionDubboService.getSimpleUserById(session
				.getUserId());
		if (!user.isAdmin()) {
			return SUCCESS;
		}
		this.permissions = permissionDubboService
				.findPermissionsByPermissionName(name, 1,
						GridProperties.ORG_TREE_AUTOCOMPLETE_SEARCH_NUM);
		return SUCCESS;
	}

	@Action(value = "findPermissionsNodeId", results = { @Result(name = "success", type = "json", params = {
			"root", "parentNodeIdsForSearch", "excludeNullProperties", "true" }) })
	public String findPermissionsNodeId() {
		session = ThreadVariable.getSession();
		if (this.session == null || !session.isLogin()) {
			return SUCCESS;
		}
		user = this.permissionDubboService.getSimpleUserById(session
				.getUserId());
		if (!user.isAdmin()) {
			return SUCCESS;
		}
		List<Long> ids = permissionDubboService.searchParentPermissionIds(Long
				.parseLong(id.toString()));
		for (int i = ids.size() - 1; i >= 0; i--) {
			if (i == ids.size() - 1) {
				parentNodeIdsForSearch = ids.get(i).toString();
			} else {
				parentNodeIdsForSearch = parentNodeIdsForSearch + "/"
						+ ids.get(i);
			}
		}
		parentNodeIdsForSearch = parentNodeIdsForSearch + "/" + id;
		return SUCCESS;
	}

	@Action(value = "findUserAllPermissionByUserId", results = { @Result(name = "success", type = "json", params = {
			"root", "userPermissionEname", "excludeNullProperties", "true" }) })
	public String findUserAllPermissionByUserId() {
		if (user == null || user.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		userPermissionEname = permissionDubboService
				.findUserAllPermissionEnameByUserId(user.getId());
		return SUCCESS;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<ExtTreeData> getExtTreeDatas() {
		return extTreeDatas;
	}

	public void setExtTreeDatas(List<ExtTreeData> extTreeDatas) {
		this.extTreeDatas = extTreeDatas;
	}

	public ExtTreeData getExtTreeData() {
		return extTreeData;
	}

	public void setExtTreeData(ExtTreeData extTreeData) {
		this.extTreeData = extTreeData;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Long getRootId() {
		return rootId;
	}

	public void setRootId(Long rootId) {
		this.rootId = rootId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(Long orgLevel) {
		this.orgLevel = orgLevel;
	}

	public Long getOrgType() {
		return orgType;
	}

	public void setOrgType(Long orgType) {
		this.orgType = orgType;
	}

	public List<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	public PageInfo<Organization> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<Organization> pageInfo) {
		this.pageInfo = pageInfo;
	}

	public GridPage getGridPage() {
		return gridPage;
	}

	public void setGridPage(GridPage gridPage) {
		this.gridPage = gridPage;
	}

	public List<TreeData> getTreeData() {
		return treeData;
	}

	public void setTreeData(List<TreeData> treeData) {
		this.treeData = treeData;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getSearchContext() {
		return searchContext;
	}

	public void setSearchContext(String searchContext) {
		this.searchContext = searchContext;
	}

	public Long getReferOrgId() {
		return referOrgId;
	}

	public void setReferOrgId(Long referOrgId) {
		this.referOrgId = referOrgId;
	}

	public Integer getCountChildren() {
		return countChildren;
	}

	public void setCountChildren(Integer countChildren) {
		this.countChildren = countChildren;
	}

	public List<String> getNodeIds() {
		return nodeIds;
	}

	public void setNodeIds(List<String> nodeIds) {
		this.nodeIds = nodeIds;
	}

	public String getParentNodeIdsForSearch() {
		return parentNodeIdsForSearch;
	}

	public void setParentNodeIdsForSearch(String parentNodeIdsForSearch) {
		this.parentNodeIdsForSearch = parentNodeIdsForSearch;
	}

	public List<PropertyDict> getOrgTypes() {
		return orgTypes;
	}

	public void setOrgTypes(List<PropertyDict> orgTypes) {
		this.orgTypes = orgTypes;
	}

	public List<PropertyDict> getOrgLevels() {
		return orgLevels;
	}

	public void setOrgLevels(List<PropertyDict> orgLevels) {
		this.orgLevels = orgLevels;
	}

	public OrgDataWhenAdd getOrgDataWhenAdd() {
		return orgDataWhenAdd;
	}

	public void setOrgDataWhenAdd(OrgDataWhenAdd orgDataWhenAdd) {
		this.orgDataWhenAdd = orgDataWhenAdd;
	}

	public boolean isShouldJugeMultizones() {
		return shouldJugeMultizones;
	}

	public void setShouldJugeMultizones(boolean shouldJugeMultizones) {
		this.shouldJugeMultizones = shouldJugeMultizones;
	}

	public boolean isExcludeRoot() {
		return excludeRoot;
	}

	public void setExcludeRoot(boolean excludeRoot) {
		this.excludeRoot = excludeRoot;
	}

	public boolean isAllOrg() {
		return allOrg;
	}

	public void setAllOrg(boolean allOrg) {
		this.allOrg = allOrg;
	}

	public List<String> getUserPermissionEname() {
		return userPermissionEname;
	}

	public void setUserPermissionEname(List<String> userPermissionEname) {
		this.userPermissionEname = userPermissionEname;
	}

}
