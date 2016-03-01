package com.tianque.sysadmin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.vo.OrgDataWhenAdd;
import com.tianque.controller.vo.OrganizationTreeData;
import com.tianque.controller.vo.TreeData;
import com.tianque.core.base.BaseAction;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.ExtTreeData;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.sysadmin.service.impl.ReferType;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.userAuth.api.PropertyDictDubboService;

@Controller("organizationController")
@Namespace("/sysadmin/orgManage")
@Scope("prototype")
@Transactional
public class OrganizationController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(OrganizationController.class);
	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;
	@Autowired
	private PropertyDictDubboService propertyDictDubboService;
	@Autowired
	private PermissionDubboService permissionDubboService;
	private Organization organization;
	private Long parentId;
	private Long rootId;
	private Long id;
	private Long orgLevel;
	private Long orgType;
	private List<Organization> organizations;
	private List<Organization> childs;
	private PageInfo<Organization> pageInfo;
	private GridPage gridPage;
	private List<TreeData> treeData = new ArrayList<TreeData>();
	private List<ExtTreeData> extTreeDatas = new ArrayList<ExtTreeData>();
	private ExtTreeData extTreeData;
	private String position;
	private String operation;
	private String searchContext;
	private Long referOrgId;
	private Integer countChildren;
	private Map dispalyNames;
	private List<String> nodeIds = new ArrayList<String>();
	private String parentNodeIdsForSearch = "";
	private List<PropertyDict> orgTypes;
	private List<PropertyDict> orgLevels;
	private OrgDataWhenAdd orgDataWhenAdd;
	private boolean shouldJugeMultizones;
	private boolean excludeRoot;
	private boolean allOrg;
	private String simpleCode;
	@Autowired
	private ValidateHelper validateHelper;
	private String fieldCode;
	private boolean initCountry = true;

	public boolean isInitCountry() {
		return initCountry;
	}

	public void setInitCountry(boolean initCountry) {
		this.initCountry = initCountry;
	}

	public String getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	@Action(value = "orgSelectComponent", results = { @Result(name = "success", location = "/includes/orgSelect.jsp") })
	public String orgSelectComponent() {
		organization = organizationDubboService.getSimpleOrgById(id);
		if (ThreadVariable.getUser().isAdmin()) {
			childs = organizationDubboService.findChildOrgs(
					organizationDubboService.getRootOrganization().getId(), id);
		} else {
			childs = organizationDubboService.findChildOrgs(ThreadVariable
					.getOrganization().getId(), id);
		}
		if (organization.getSubCount() > 0) {
			organizations = organizationDubboService
					.findOrgsByParentIdAndOrgTypeInternalIds(id, new Long[] {
							new Long(OrganizationType.ADMINISTRATIVE_REGION),
							new Long(OrganizationType.OTHER) });
		} else {
			organizations = new ArrayList<Organization>();
			organizations.add(organization);
		}
		for (Organization org : organizations) {
			org.setOrgLevel(propertyDictDubboService.getPropertyDictById(org
					.getOrgLevel().getId()));
		}
		return SUCCESS;
	}

	@Action(value = "findChildOrgs", results = { @Result(name = "success", type = "json", params = {
			"root", "childs", "ignoreHierarchy", "false" }) })
	public String findChildOrgs() {
		if (ThreadVariable.getUser().isAdmin()) {
			childs = organizationDubboService.findChildOrgs(
					organizationDubboService.getRootOrganization().getId(), id);
		} else {
			childs = organizationDubboService.findChildOrgs(ThreadVariable
					.getOrganization().getId(), id);
		}
		return SUCCESS;
	}

	/**
	 * 查找父节点
	 * 
	 * @return
	 */
	@Action(value = "ajaxSearchParentNodeIds", results = { @Result(name = "success", type = "json", params = {
			"root", "parentNodeIdsForSearch", "ignoreHierarchy", "false" }) })
	public String searchParentNodeIds() {
		List<Long> ids = organizationDubboService.searchParentOrgIds(
				organization.getId(), rootId);
		fillSearchParentNodeIds(ids);
		return SUCCESS;
	}

	@Action(value = "ajaxSearchParentNodeIdsWhenRootIsTown", results = { @Result(name = "success", type = "json", params = {
			"root", "parentNodeIdsForSearch", "ignoreHierarchy", "false" }) })
	public String searchParentNodeIdsWhenRootIsTown() {
		List<Long> ids = organizationDubboService
				.searchParentOrgIdsWhenRootIsTown(organization.getId());
		fillSearchParentNodeIds(ids);
		return SUCCESS;
	}

	private void fillSearchParentNodeIds(List<Long> ids) {

		for (int i = ids.size() - 1; i >= 0; i--) {
			if (i == ids.size() - 1) {
				parentNodeIdsForSearch = ids.get(i).toString();
			} else {
				parentNodeIdsForSearch = parentNodeIdsForSearch + "/"
						+ ids.get(i);
			}
		}
		parentNodeIdsForSearch = parentNodeIdsForSearch + "/"
				+ organization.getId();
	}

	@PermissionFilter(ename = "addOrganization")
	@Action(value = "ajaxAddOrganization", results = { @Result(name = "success", type = "json", params = {
			"root", "orgDataWhenAdd", "ignoreHierarchy", "false" }) })
	public String addOrganization() {
		if (organization.getParentOrg() == null
				|| organization.getParentOrg().getId() == null) {
			errorMessage = "请选择所属网格！";
			return ERROR;
		}
		String str = validateDepartmentNo();
		if (str.equals(ERROR)) {
			errorMessage = "部门编号已存在！";
			return ERROR;
		}
		try {
			organization = organizationDubboService
					.addOrganization(organization);
			organization.setOrgType(propertyDictDubboService
					.getPropertyDictById(organization.getOrgType().getId()));
			organization.setOrgLevel(propertyDictDubboService
					.getPropertyDictById(organization.getOrgLevel().getId()));
			orgDataWhenAdd = new OrgDataWhenAdd(new OrganizationTreeData(
					organization), organization);
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "findMultizonesWithParentOrgNameByUserId", results = { @Result(type = "json", params = {
			"root", "organizations", "ignoreHierarchy", "false" }) })
	public String findMultizonesWithParentOrgNameByUserId() {
		try {
			organizations = organizationDubboService
					.findMultizonesWithParentOrgNameByUserId(ThreadVariable
							.getSession().getUserId());
		} catch (Exception e) {
			logger.error("责任区选择：", e);
			return SUCCESS;
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "deleteOrganization")
	@Action(value = "ajaxDeleteById", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String deleteById() {
		try {
			if (ERROR.equals(checkDeleteOrgById())) {
				return ERROR;
			}
			String info = organizationDubboService.deleteOrgById(organization
					.getId());
			if (!("success".equals(info))) {
				errorMessage = info;
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "ajaxDeletegridById", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String ajaxDeletegridById() {
		try {
			if (ERROR.equals(checkDeleteOrgById())) {
				return ERROR;
			}
			String info = organizationDubboService.deleteOrgById(organization
					.getId());
			if (!("success".equals(info))) {
				errorMessage = info;
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "getOrgByDepartmentNo", results = { @Result(name = "success", type = "json", params = {
			"root", "organization", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String getOrgByDepartmentNo() {
		organization = organizationDubboService
				.getOrgByDepartmentNo(organization.getDepartmentNo());
		return SUCCESS;
	}

	@Actions(value = {
			@Action(value = "ajaxOrganization", results = { @Result(type = "json", params = {
					"root", "organization", "ignoreHierarchy", "false" }) }),
			@Action(value = "toUpdateOrgJsp", results = { @Result(name = "success", location = "/sysadmin/orgManage/orgUpdateDLG.jsp") }),
			@Action(value = "toMoveOrgJsp", results = { @Result(name = "success", location = "/sysadmin/orgManage/orgMoveDLG.jsp") }),
			@Action(value = "toOrgDetailJsp", results = { @Result(name = "success", location = "/sysadmin/orgManage/orgDetailDLG.jsp") }) })
	public String findById() {
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());

		if (organization.getParentOrg() != null
				&& organization.getParentOrg().getId() != null) {
			organization.setParentOrg(organizationDubboService
					.getSimpleOrgById(organization.getParentOrg().getId()));
		}
		dispalyNames = organizationDubboService
				.getOrganizationDisplayName(new Long[] { organization.getId() });
		processOrgLevelAndOrgType();

		return SUCCESS;
	}

	private void processOrgLevelAndOrgType() {
		organization.setOrgType(propertyDictDubboService
				.getPropertyDictById(organization.getOrgType().getId()));
		organization.setOrgLevel(propertyDictDubboService
				.getPropertyDictById(organization.getOrgLevel().getId()));
		if (DialogMode.EDIT_MODE.toString().equals(mode)) {
			if (organization.getSubCount().intValue() > 0) {
				orgTypes = propertyDictDubboService
						.findPropertyDictByDomainNameAndInternalId(
								OrganizationType.ORG_TYPE_KEY,
								OrganizationType.ADMINISTRATIVE_REGION);
			} else {
				if (OrganizationLevel.GRID == organization.getOrgLevel()
						.getInternalId()) {
					orgTypes = propertyDictDubboService
							.findPropertyDictByDomainNameAndInternalIds(
									OrganizationType.ORG_TYPE_KEY,
									new int[] {
											OrganizationType.ADMINISTRATIVE_REGION,
											OrganizationType.PARTYWORK });
				} else if (OrganizationLevel.VILLAGE == organization
						.getOrgLevel().getInternalId()) {
					orgTypes = propertyDictDubboService
							.findPropertyDictByDomainNameAndInternalIds(
									OrganizationType.ORG_TYPE_KEY,
									new int[] {
											OrganizationType.ADMINISTRATIVE_REGION,
											OrganizationType.PARTYWORK });
				} else {
					orgTypes = propertyDictDubboService
							.findPropertyDictByDomainNameAndInternalIds(
									OrganizationType.ORG_TYPE_KEY,
									new int[] {
											OrganizationType.ADMINISTRATIVE_REGION,
											OrganizationType.FUNCTIONAL_ORG,
											OrganizationType.PARTYWORK });
				}
			}
		}
	}

	private Organization getOrgByUserId(User user) {
		organization = organizationDubboService.getSimpleOrgById(user
				.getOrganization().getId());
		organization.setOrgType(propertyDictDubboService
				.getPropertyDictById(organization.getOrgType().getId()));
		organization.setOrgLevel(propertyDictDubboService
				.getPropertyDictById(organization.getOrgLevel().getId()));
		return organization;
	}

	private User getUserFromSession() {
		Session session = ThreadVariable.getSession();
		User user = permissionDubboService.getSimpleUserById(session
				.getUserId());
		return user;
	}

	@Action(value = "getFullOrgById", results = { @Result(type = "json", params = {
			"root", "organization", "ignoreHierarchy", "false" }) })
	public String getFullOrgById() {
		organization = organizationDubboService.getFullOrgById(organization
				.getId());
		return SUCCESS;
	}

	/**
	 * 获取当前部门的上级部门
	 * 
	 * @return
	 */
	@Action(value = "getFullParentOrgById", results = { @Result(type = "json", params = {
			"root", "organization", "ignoreHierarchy", "false" }) })
	public String getFullParentOrgById() {
		organization = organizationDubboService.getFullOrgById(organization
				.getId());
		if (organization != null && organization.getParentOrg() != null
				&& organization.getParentOrg().getId() != null) {
			organization = organizationDubboService.getFullOrgById(organization
					.getParentOrg().getId());
		}
		return SUCCESS;
	}

	@Action(value = "firstLoadExtTreeData", results = { @Result(type = "json", params = {
			"root", "extTreeDatas", "ignoreHierarchy", "false" }) })
	public String firstLoadExtTreeData() {
		try {
			if (shouldJugeMultizones) {
				organizations = organizationDubboService
						.findMultizonesByUserId(ThreadVariable.getUser()
								.getId());
				if (organizations.size() > 0) {
					for (int i = 0; i < organizations.size(); i++) {
						extTreeDatas.add(new OrganizationTreeData(
								organizationDubboService
										.getFullOrgById(organizations.get(i)
												.getId())));
					}
					return SUCCESS;
				}
			}
			ajaxOrgsForExtTree();
			ExtTreeData extTreeData = extTreeDatas.get(0);
			if (!excludeRoot) {
				extTreeData.setChildren(getChildOrgs(extTreeData.getId(),
						orgType));
			} else {
				extTreeDatas = getChildOrgs(extTreeData.getId(), orgType);
			}
		} catch (Exception e) {
			logger.error("异常信息", e);
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "firstLoadExtTreeDataWithCheckedBox", results = { @Result(type = "json", params = {
			"root", "extTreeDatas", "ignoreHierarchy", "false" }) })
	public String firstLoadExtTreeDataWithCheckedBox() {
		firstLoadExtTreeData();
		if (extTreeDatas != null && extTreeDatas.size() > 0) {
			for (int i = 0; i < extTreeDatas.size(); i++) {
				ExtTreeData extTreeData = extTreeDatas.get(i);
				extTreeData.setChecked(false);
				checkChildTreeData(extTreeData);
			}
		}
		return SUCCESS;
	}

	private void checkChildTreeData(ExtTreeData extTreeData) {
		if (extTreeData.getChildren() != null
				&& extTreeData.getChildren().size() > 0) {
			for (int j = 0; j < extTreeData.getChildren().size(); j++) {
				ExtTreeData childData = extTreeData.getChildren().get(j);
				childData.setChecked(false);
				checkChildTreeData(childData);
			}
		}
	}

	@Action(value = "ajaxOrgsForExtTreeWithCheckedBox", results = { @Result(type = "json", params = {
			"root", "extTreeDatas", "ignoreHierarchy", "false" }) })
	public String ajaxOrgsForExtTreeWithCheckedBox() {
		ajaxOrgsForExtTree();
		if (extTreeDatas != null && extTreeDatas.size() > 0) {
			for (int i = 0; i < extTreeDatas.size(); i++) {
				ExtTreeData extTreeData = extTreeDatas.get(i);
				extTreeData.setChecked(false);
			}
		}
		return SUCCESS;
	}

	/**
	 * 异步查找组织机构树
	 * 
	 * @return
	 */
	@Action(value = "ajaxOrgsForExtTree", results = { @Result(type = "json", params = {
			"root", "extTreeDatas", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String ajaxOrgsForExtTree() {
		User user = getUserFromSession();
		if (allOrg) {
			extTreeDatas
					.add(new OrganizationTreeData(organizationDubboService
							.getFullOrgById(organizationDubboService
									.findOrganizationsByParentId(null).get(0)
									.getId())));
		} else if (rootId != null) {
			extTreeDatas.add(new OrganizationTreeData(organizationDubboService
					.getFullOrgById(rootId)));
		} else if ((parentId == null || parentId == 0) && !user.isAdmin()) {
			organization = getOrgByUserId(user);
			if (orgType != null
					&& organization.getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG
					&& orgType.intValue() == OrganizationType.ADMINISTRATIVE_REGION) {
				organization = organizationDubboService
						.getFullOrgById(organization.getParentOrg().getId());
			}
			extTreeDatas.add(new OrganizationTreeData(organization, orgType));
		} else {
			extTreeDatas = getChildOrgs(parentId, orgType);
		}

		return SUCCESS;
	}

	private List<ExtTreeData> getChildOrgs(Long parentId, Long orgType) {
		List<ExtTreeData> extTreeDatas = new ArrayList<ExtTreeData>();
		List<Organization> organizations = null;
		if (orgType != null && orgType.longValue() == 0L) {
			organizations = organizationDubboService
					.findOrgsByParentIdAndOrgTypeInternalIds(
							parentId,
							new Long[] {
									Long.valueOf(OrganizationType.ADMINISTRATIVE_REGION),
									Long.valueOf(OrganizationType.OTHER) });
		} else if (orgType != null && orgType.longValue() == 1L) {
			organizations = organizationDubboService
					.findFunOrgsByParentId(parentId);
		} else if (orgType != null && orgType.longValue() == 2L) {
			organizations = organizationDubboService
					.findOrgsByParentIdAndOrgTypeInternalIds(
							parentId,
							new Long[] {
									Long.valueOf(OrganizationType.ADMINISTRATIVE_REGION),
									Long.valueOf(OrganizationType.OTHER),
									Long.valueOf(OrganizationType.PARTYWORK) });
		} else {
			organizations = organizationDubboService
					.findOrgsByParentIdAndOrgTypeInternalIds(
							parentId,
							new Long[] {
									Long.valueOf(OrganizationType.ADMINISTRATIVE_REGION),
									Long.valueOf(OrganizationType.OTHER),
									Long.valueOf(OrganizationType.FUNCTIONAL_ORG),
									Long.valueOf(OrganizationType.PARTYWORK) });
		}
		for (int i = 0; i < organizations.size(); i++) {
			Organization organization = organizations.get(i);
			organization.setOrgType(propertyDictDubboService
					.getPropertyDictById(organization.getOrgType().getId()));
			organization.setOrgLevel(propertyDictDubboService
					.getPropertyDictById(organization.getOrgLevel().getId()));
			extTreeDatas.add(new OrganizationTreeData(organization));
		}
		return extTreeDatas;
	}

	@Action(value = "findOrganizationsByParentIdAndFunctionalOrgType", results = { @Result(type = "json", params = {
			"root", "organizations", "ignoreHierarchy", "false" }) })
	public String findOrganizationsByParentIdAndFunctionalOrgType() {
		organizations = organizationDubboService
				.findOrgsByParentIdAndFunTypes(parentId);
		return SUCCESS;
	}

	@Action(value = "findOrganizationsByParent", results = { @Result(type = "json", params = {
			"root", "organizations", "ignoreHierarchy", "false" }) })
	public String findOrganizationsByParent() {
		if (parentId == null) {
			organizations = new ArrayList<Organization>();
		} else {
			organizations = organizationDubboService
					.findOrganizationsByParentId(parentId);
		}
		return SUCCESS;
	}

	@Action(value = "ajaxFunOrgTree", results = { @Result(type = "json", params = {
			"root", "treeData", "ignoreHierarchy", "false" }) })
	public String ajaxFunOrgTree() {
		boolean isRoot = false;
		if (id == null) {
			isRoot = true;
			parentId = permissionDubboService
					.getSimpleUserById(ThreadVariable.getSession().getUserId())
					.getOrganization().getId();
		}
		if (isRoot) {
			treeData.add(new TreeData(organizationDubboService
					.getSimpleOrgById(id), isRoot));
		} else {
			organizations = organizationDubboService.findFunOrgsByParentId(id);
			for (int i = 0; i < organizations.size(); i++) {
				Organization organization = organizations.get(i);
				organization
						.setOrgType(propertyDictDubboService
								.getPropertyDictById(organization.getOrgType()
										.getId()));
				organization.setSubCount(Long.valueOf(organizationDubboService
						.findFunOrgsByParentId(id).size()));
				treeData.add(new TreeData(organization, isRoot));
			}
		}
		return SUCCESS;
	}

	@Action(value = "ajaxOrgPage", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false",
			"excludeNullProperties", "true", "excludeProperties",
			"page,total,records" }) })
	public String ajaxOrgPage() {
		gridPage = new GridPage(
				organizationDubboService.findOrganizationsByParentId(parentId));
		return SUCCESS;
	}

	@PermissionFilter(ename = "updateOrganization")
	@Action(value = "ajaxUpdateOrganization", results = { @Result(name = "success", type = "json", params = {
			"root", "organization", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String updateOrganization() {
		if (validateDepartmentNo().equals("ERROR")) {
			errorMessage = "部门编号已存在！";
			return ERROR;
		}
		try {
			organization = organizationDubboService
					.updateOrgNameAndOrgTypeAndContactWay(organization);
			organization.setOrgType(propertyDictDubboService
					.getPropertyDictById(organization.getOrgType().getId()));
		} catch (Exception e) {
			errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "ajaxFindOrganizationsByOrgNameAndOrgType", results = { @Result(name = "success", type = "json", params = {
			"root", "organizations", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String findOrganizationsByOrgNameAndOrgType() {
		organizations = organizationDubboService
				.findOrganizationsByorgNameAndOrgType(
						organization.getOrgName(), 1,
						GridProperties.ORG_TREE_AUTOCOMPLETE_SEARCH_NUM);
		return SUCCESS;
	}

	@Action(value = "ajaxFindOrganizationsByOrgName", results = { @Result(name = "success", type = "json", params = {
			"root", "organizations", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String findOrganizationsByOrgName() {
		try {
			organizations = organizationDubboService
					.findOrganizationsByOrgNameForPage(
							organization.getOrgName(), 1,
							GridProperties.ORG_TREE_AUTOCOMPLETE_SEARCH_NUM);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Action(value = "autoFindOrganizationsByOrgNameOrOrgInternalCode", results = { @Result(name = "success", type = "json", params = {
			"root", "standardOrgs", "excludeNullProperties", "true" }) })
	public String autoFindOrganizationsByOrgNameOrOrgInternalCode() {
		return SUCCESS;
	}

	@PermissionFilter(ename = "organizationManagement")
	@Action(value = "validateOrgName", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String validateOrgName() {
		organizations = organizationDubboService
				.findOrganizationsByOrgNameAndParentId(organization.getId(),
						organization.getOrgName(), parentId);
		if (organizations.size() >= 1) {
			return ERROR;
		} else {
			return SUCCESS;
		}
	}

	@PermissionFilter(ename = "organizationManagement")
	@Action(value = "validateDepartmentNo", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String validateDepartmentNo() {
		boolean bol = organizationDubboService
				.isDistrictOfAdministrativeRegion(organization);

		if (!bol) {
			return SUCCESS;
		} else if (bol
				&& !validateHelper.emptyString(organization.getDepartmentNo())) {
			organizations = organizationDubboService
					.findOrganizationsByDepartmentNoAndTypeAndLevel(organization);
			if (organizations.size() > 0) {
				return ERROR;
			}
		} else if (bol
				&& validateHelper.emptyString(organization.getDepartmentNo())) {
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "validateRepeatDepartmentNo", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String validateRepeatDepartmentNo() {
		if (organization.getDepartmentNo() == null) {
			return ERROR;
		}
		if (organizationDubboService.validateRepeatDepartmentNo(organization)) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	@PermissionFilter(ename = "moveOrganization")
	@Action(value = "ajaxMoveOrganization", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String moveOrganization() {
		organizationDubboService
				.moveOrganization(organization.getId(), organization
						.getParentOrg().getId(), getReferType(), referOrgId);
		return SUCCESS;
	}

	private ReferType getReferType() {
		ReferType referType = null;
		ReferType[] values = ReferType.values();
		for (ReferType r : values) {
			if (r.toString().equals(position)) {
				referType = r;
			}
		}
		return referType;
	}

	@Action(value = "getOrgRelativePath", results = { @Result(name = "success", type = "json", params = {
			"root", "organization.orgName", "excludeNullProperties", "true" }) })
	public String getOrgRelativePath() {
		Long orgId = organization.getId();
		organization.setOrgName(ControllerHelper.getRelativeOrgNameByOrgId(
				orgId, organizationDubboService));
		return SUCCESS;
	}

	@Action(value = "getOrgTypeListWhenAdd", results = { @Result(name = "success", type = "json", params = {
			"root", "orgTypes", "excludeNullProperties", "true" }) })
	public String getOrgTypeListWhenAdd() {
		int parentLevelInternalId = organization.getOrgLevel().getInternalId();
		int parentTypeInternalId = organization.getOrgType().getInternalId();
		if (parentLevelInternalId > OrganizationLevel.TOWN
				&& parentTypeInternalId == OrganizationType.ADMINISTRATIVE_REGION) {
			orgTypes = propertyDictDubboService
					.findPropertyDictByDomainName(OrganizationType.ORG_TYPE_KEY);
		} else if (parentLevelInternalId == OrganizationLevel.TOWN
				&& parentTypeInternalId == OrganizationType.ADMINISTRATIVE_REGION) {
			orgTypes = propertyDictDubboService
					.findPropertyDictByDomainNameAndInternalIds(
							OrganizationType.ORG_TYPE_KEY, new int[] {
									OrganizationType.OTHER,
									OrganizationType.ADMINISTRATIVE_REGION,
									OrganizationType.PARTYWORK,
									OrganizationType.FUNCTIONAL_ORG });
		} else if (parentLevelInternalId >= OrganizationLevel.TOWN
				&& parentTypeInternalId == OrganizationType.FUNCTIONAL_ORG) {
			orgTypes = propertyDictDubboService
					.findPropertyDictByDomainNameAndInternalId(
							OrganizationType.ORG_TYPE_KEY,
							OrganizationType.FUNCTIONAL_ORG);
		} else if (parentLevelInternalId == OrganizationLevel.VILLAGE
				&& parentTypeInternalId == OrganizationType.ADMINISTRATIVE_REGION) {
			orgTypes = propertyDictDubboService
					.findPropertyDictByDomainNameAndInternalIds(
							OrganizationType.ORG_TYPE_KEY, new int[] {
									OrganizationType.ADMINISTRATIVE_REGION,
									OrganizationType.PARTYWORK });
		} else {
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "checkDeleteOrgById", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String checkDeleteOrgById() {
		try {
			organization = organizationDubboService
					.getSimpleOrgById(organization.getId());

			if (organizationDubboService
					.countOrgsByOrgInternalCode(organization
							.getOrgInternalCode()) > 1) {
				this.errorMessage = "此部门下有子部门，不能删除！";
				return ERROR;
			}
			organization.setOrgType(propertyDictDubboService
					.getPropertyDictById(organization.getOrgType().getId()));
			if (organization.getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG
					&& organizationDubboService.findFunOrgsByParentId(
							organization.getId()).size() > 0) {
				this.errorMessage = "此部门下有职能部门，不能删除！";
				return ERROR;
			}

			if (permissionDubboService.countUsersByOrgInternalCode(organization
					.getOrgInternalCode()) > 0) {
				this.errorMessage = "此部门下有用户，不能删除！";
				return ERROR;
			}

		} catch (Exception e) {
			logger.error("异常信息", e);
		}

		return SUCCESS;
	}

	@Action(value = "loadTownForExtTree", results = { @Result(name = "success", type = "json", params = {
			"root", "extTreeDatas", "excludeNullProperties", "true",
			"ignoreHierarchy", "false" }) })
	public String loadTownForExtTree() {
		organization = organizationDubboService.getFullOrgById(ThreadVariable
				.getUser().getOrganization().getId());
		int orgLevelInternald = propertyDictDubboService.getPropertyDictById(
				organization.getOrgLevel().getId()).getInternalId();
		if (orgLevelInternald > OrganizationLevel.TOWN) {
			this.firstLoadExtTreeData();
			return SUCCESS;
		} else if (orgLevelInternald == OrganizationLevel.TOWN) {
			parentId = organization.getId();
		} else if (orgLevelInternald == OrganizationLevel.GRID) {
			parentId = organization.getParentOrg().getParentOrg().getId();
		} else if (orgLevelInternald == OrganizationLevel.VILLAGE) {
			parentId = organization.getParentOrg().getId();
		}
		extTreeDatas.add(new OrganizationTreeData(organizationDubboService
				.getFullOrgById(parentId)));
		return SUCCESS;
	}

	@Action(value = "getOrganizationBySimpleCode", results = { @Result(name = "success", type = "json", params = {
			"root", "organization", "excludeNullProperties", "true",
			"ignoreHierarchy", "false" }) })
	public String getOrganizationBySimpleCode() {
		organization = organizationDubboService
				.getSimplePinyinBySimpleCode(simpleCode);
		return SUCCESS;
	}

	@Action(value = "getTitleProvinceName")
	public String getTitleProvinceName() {
		List<Organization> organizations = organizationDubboService
				.findOrganizationsByParentId(null);
		organization = organizations.get(0);
		if (null == organization.getDepartmentNo()) {
			Organization provinceOrganization = organizationDubboService
					.findOrganizationsByParentId(organization.getId()).get(0);
			organization = provinceOrganization;
			initCountry = true;
		} else {
			initCountry = false;
		}
		return SUCCESS;
	}

	@Action(value = "isGridOrganization", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String isGridOrg() {
		if (organization == null || organization.getId() == null) {
			return ERROR;
		}
		return organizationDubboService
				.isGridOrganization(organization.getId()) ? SUCCESS : ERROR;
	}

	@Action(value = "isDistrictOfAdministrativeRegion", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String isDistrictOfAdministrativeRegion() {
		String dis = (organizationDubboService
				.isDistrictOfAdministrativeRegion(organization) && validateHelper
				.emptyString(organization.getDepartmentNo())) ? SUCCESS : ERROR;
		return dis;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	public GridPage getGridPage() {
		return gridPage;
	}

	public void setGridPage(GridPage gridPage) {
		this.gridPage = gridPage;
	}

	public PageInfo<Organization> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<Organization> pageInfo) {
		this.pageInfo = pageInfo;
	}

	public List<TreeData> getTreeData() {
		return treeData;
	}

	public void setTreeData(List<TreeData> treeData) {
		this.treeData = treeData;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public List<String> getNodeIds() {
		return nodeIds;
	}

	public void setNodeIds(List<String> nodeIds) {
		this.nodeIds = nodeIds;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Long getReferOrgId() {
		return referOrgId;
	}

	public void setReferOrgId(Long referOrgId) {
		this.referOrgId = referOrgId;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Map getDispalyNames() {
		return dispalyNames;
	}

	public void setDispalyNames(Map dispalyNames) {
		this.dispalyNames = dispalyNames;
	}

	public String getSearchContext() {
		return searchContext;
	}

	public void setSearchContext(String searchContext) {
		this.searchContext = searchContext;
	}

	public Integer getCountChildren() {
		return countChildren;
	}

	public void setCountChildren(Integer countChildren) {
		this.countChildren = countChildren;
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

	public List<ExtTreeData> getExtTreeDatas() {
		return extTreeDatas;
	}

	public void setExtTreeDatas(List<ExtTreeData> extTreeDatas) {
		this.extTreeDatas = extTreeDatas;
	}

	public String getParentNodeIdsForSearch() {
		return parentNodeIdsForSearch;
	}

	public void setParentNodeIdsForSearch(String parentNodeIdsForSearch) {
		this.parentNodeIdsForSearch = parentNodeIdsForSearch;
	}

	public ExtTreeData getExtTreeData() {
		return extTreeData;
	}

	public void setExtTreeData(ExtTreeData extTreeData) {
		this.extTreeData = extTreeData;
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

	public Long getRootId() {
		return rootId;
	}

	public void setRootId(Long rootId) {
		this.rootId = rootId;
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

	public String getSimpleCode() {
		return simpleCode;
	}

	public void setSimpleCode(String simpleCode) {
		this.simpleCode = simpleCode;
	}

	public List<Organization> getChilds() {
		return childs;
	}

	public void setChilds(List<Organization> childs) {
		this.childs = childs;
	}

}
