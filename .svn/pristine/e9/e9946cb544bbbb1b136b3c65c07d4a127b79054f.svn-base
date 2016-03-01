/**
 * tianque-com.tianque.core.user.controllor-PermissionControllor.java Created on Mar 19, 2010
 * Copyright (c) 2010 by 杭州天阙科技有限公司
 */
package com.tianque.sysadmin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.Permission;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.Role;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.init.Node;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.userAuth.api.PropertyDictDubboService;

/**
 * Title: ***<br>
 * 
 * @author <a href=mailto:nifeng@hztianque.com>倪峰</a><br>
 * 
 * @description ***<br/>
 * 
 * @version 1.0
 */
@Controller("roleManageController")
@Scope("prototype")
@Transactional
public class RoleManageController extends BaseAction {

	private static Logger logger = LoggerFactory
			.getLogger(RoleManageController.class);
	@Autowired
	private PermissionDubboService permissionDubboService;

	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;

	@Autowired
	private PropertyDictDubboService propertyDictDubboService;

	/**
	 * 选中的Role列表
	 */
	private Map<String, Boolean> permissionsMap = new HashMap<String, Boolean>();

	/**
	 * 编辑、修改、查看所需要的role对象
	 */
	private Role role = new Role();

	private List<PropertyDict> orgLevelDict = new ArrayList<PropertyDict>();
	private PropertyDict propertyDict;

	public PropertyDict getPropertyDict() {
		return propertyDict;
	}

	public void setPropertyDict(PropertyDict propertyDict) {
		this.propertyDict = propertyDict;
	}

	@PermissionFilter(ename = "addRole")
	public String addRole() {
		try {
			if (ERROR.equals(validateRoleName())) {
				this.errorMessage = "岗位名已经存在";
				return ERROR;
			}
			role = permissionDubboService.addRole(role,
					getSelectedPermissionEnamesArray());
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	private boolean checkNoRoleAssigned() {
		if (role == null || ((BaseDomain) role).getId() == null) {
			this.errorMessage = "系统错误,请联系管理员!";
			return false;
		}
		return true;
	}

	@PermissionFilter(ename = "deleteRole")
	public String deleteRole() {
		if (!checkNoRoleAssigned())
			return ERROR;
		if (this.permissionDubboService.isRoleUsed(role.getId())) {
			this.errorMessage = "岗位已经被分配给用户，无法删除！";
			return ERROR;
		}
		try {
			permissionDubboService.deleteRoleById(role.getId());
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@PermissionFilter(ename = "roleManagement")
	public String findRolesByUseInLevel() {
		Session session = ThreadVariable.getSession();
		User user = permissionDubboService.getSimpleUserById(session
				.getUserId());
		Organization org = organizationDubboService.getFullOrgById(user
				.getOrganization().getId());
		PageInfo pageInfo = permissionDubboService
				.findAllRolesByUseInLevelForPage(this.getPage(),
						this.getRows(), org.getOrgLevel().getId(), sidx, sord);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	private List getCheckedPareTreeByCurrentId() throws Exception {
		List<Node> permissionNodes = permissionDubboService
				.getMenuPermissionTree();
		if (DialogMode.ADD_MODE.equals(this.mode)) {// 此处也要进行修改，添加时候给的权限
			return permissionNodes;
		}
		List<Permission> permissions = this.permissionDubboService
				.findAllPermissionsByRoleId(role.getId());
		permissionDubboService
				.checkPermissionTree(permissions, permissionNodes);
		return permissionNodes;
	}

	public Map<String, Boolean> getPermissionsMap() {
		return permissionsMap;
	}

	public Role getRole() {
		return role;
	}

	private String[] getSelectedPermissionEnamesArray() {
		String[] permissionEnames = new String[permissionsMap.size()];
		permissionsMap.keySet().toArray(permissionEnames);
		return permissionEnames;
	}

	@PermissionFilter(ename = "roleManagement")
	public String preparePermissionTree() {
		try {
			gridPage = new GridPage();
			gridPage.setRows(getCheckedPareTreeByCurrentId());
		} catch (Exception e) {
			logger.error("异常信息", e);
			return ERROR;
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "roleManagement")
	public String prepareRole() {
		if (DialogMode.ADD_MODE.equals(this.mode)) {
			role = new Role();

		} else if (DialogMode.COPY_MODE.equals(this.mode)) {
			role = this.permissionDubboService.findRoleById(role.getId());

			// role.setId(null);
		} else {
			role = this.permissionDubboService.findRoleById(role.getId());
			propertyDict = propertyDictDubboService.getPropertyDictById(role
					.getUseInLevel().getId());
			role.setUseInLevel(propertyDict);
		}

		int[] internalIds = getOrgLevelDownInternalidsByCurrentUerOrgLevel();
		orgLevelDict = propertyDictDubboService
				.findPropertyDictByDomainNameAndInternalIds(
						PropertyTypes.ORGANIZATION_LEVEL, internalIds);

		return SUCCESS;
	}

	private int[] getOrgLevelDownInternalidsByCurrentUerOrgLevel() {
		Session session = ThreadVariable.getSession();
		User user = permissionDubboService.getSimpleUserById(session
				.getUserId());
		Organization org = organizationDubboService.getFullOrgById(user
				.getOrganization().getId());
		int userInLevle = org.getOrgLevel().getInternalId();

		int[] internalIds = new int[userInLevle + 1];
		for (int i = 0; i < userInLevle + 1; i++) {
			internalIds[i] = i;
		}
		return internalIds;
	}

	public void setPermissionsMap(Map<String, Boolean> permissionsMap) {
		this.permissionsMap = permissionsMap;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@PermissionFilter(ename = "updateRole")
	public String updateRole() {
		if (!checkNoRoleAssigned())
			return ERROR;
		try {
			if (ERROR.equals(validateRoleName())) {
				this.errorMessage = "岗位名已经存在!";
				return ERROR;
			}
			role = permissionDubboService.updateRole(role,
					getSelectedPermissionEnamesArray());
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "roleManagement")
	public String validateRoleName() {
		if (role == null) {
			return ERROR;
		} else {
			if (role.getRoleName() == null) {
				return ERROR;
			}
			if (role.getUseInLevel().getId() == null
					|| role.getUseInLevel().getId().longValue() == 0L) {
				return SUCCESS;
			}
			Role checkRole = this.permissionDubboService
					.findRoleByRoleNameAndUserInLevel(role.getRoleName(), role
							.getUseInLevel().getId());
			if (checkRole == null) {
				return SUCCESS;
			}
			if (DialogMode.EDIT_MODE.equals(this.mode) && checkRole != null
					&& checkRole.getId().equals(role.getId())) {
				return SUCCESS;
			}
		}
		return ERROR;
	}

	@PermissionFilter(ename = "copyRole")
	public String copyRole() {
		try {
			if (ERROR.equals(validateRoleName())) {
				this.errorMessage = "岗位名已经存在";
				return ERROR;
			}
			role = permissionDubboService.addRole(role,
					getSelectedPermissionEnamesArray());
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	public List<PropertyDict> getOrgLevelDict() {
		return orgLevelDict;
	}

	public void setOrgLevelDict(List<PropertyDict> orgLevelDict) {
		this.orgLevelDict = orgLevelDict;
	}
}