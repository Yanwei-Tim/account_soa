package com.tianque.sysadmin.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.annotation.PermissionFilters;
import com.tianque.core.base.BaseAction;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.GlobalValue;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;
import com.tianque.userAuth.api.PermissionDubboService;

@Scope("prototype")
@Transactional
@Controller("userManageController")
public class UserManageController extends BaseAction {
	public final static Logger logger = LoggerFactory
			.getLogger(UserManageController.class);
	private static final String RESET_PWD = "resetPwd";
	@Autowired
	private PermissionDubboService permissionDubboService;
	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;

	private List<Organization> organizations;

	private String searchLockStatus; // 用于userAutocomplete查询
	private boolean searchChildOrg = false;// 用于userAutocomplete查询
	private String[][] userInfors;// 用于userAutocomplete查询返回

	private User user = new User();
	private User operateUser;
	private Long organizationId;

	private Long[] roleIds;
	private Long[] zoneIds;

	private String roleIdsStr;

	private String oldPassword;
	private String currentPassword;
	private String validatePassword;
	private String email;
	private String userIds;
	private String logInUserName;
	private String modifyRoleUserName;

	private Long roleId;
	private Long[] ids;
	private Long userId;

	/** 用户订阅的消息类型 */
	private List<Integer> messageTypes;

	/** 用户未订阅的消息类型 */
	private List<Integer> canSelectMessageTypes;

	private final static String ACCESSKEY = "f1b09eea8d7e69f7fae3d14f37ac82f1";

	@PermissionFilter(ename = "deleteUser")
	public String deleteUserById() {
		try {
			if (!permissionDubboService.deleteUserById(analyzePopulationIds())) {
				errorMessage = "admin用户不能删除！";
				return ERROR;
			}
		} catch (Exception e) {
			errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	private Long[] analyzePopulationIds() {
		String[] deleteId = userIds.split(",");
		List<Long> idList;
		if (deleteId[0].equals("")) {
			idList = initTargetId(deleteId, 1);
		} else {
			idList = initTargetId(deleteId, 0);
		}
		Long[] ids = new Long[idList.size()];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = idList.get(i);
		}
		return ids;
	}

	@PermissionFilter(ename = "addUser")
	public String addUser() {
		try {
			if (organizationId == null) {
				errorMessage = "请选择网格!";
				return ERROR;
			}
			if (roleIds == null && !user.isAdmin()) {
				errorMessage = "岗位不能为空";
				return ERROR;
			}
			if (user.getOrganization() == null) {
				user.setOrganization(new Organization());
			}
			user.getOrganization().setId(organizationId);
			if ("error".equals(validateUserName())) {
				errorMessage = "该用户已经存在!";
			}
			user = permissionDubboService.addUser(user);
			if (roleIds != null) {
				for (Long roleId : roleIds) {
					permissionDubboService
							.addUserRoleRela(user.getId(), roleId);
				}
			}
			if (zoneIds != null) {
				for (Long zoneId : zoneIds) {
					permissionDubboService.addUserMultiZone(user.getId(),
							zoneId);
				}
			}
			user = permissionDubboService.getFullUserById(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@PermissionFilters(value = {
			@PermissionFilter(ename = "updateUser", actionName = "updateUser"),
			@PermissionFilter(ename = "userListUpdate", actionName = "updateUser") })
	public String updateUser() {
		if (roleIds == null && user.isAdmin() == false) {
			errorMessage = "岗位不能为空";
			return ERROR;
		}
		if (!checkUserAssigned())
			return ERROR;
		if (user.getOrganization() == null) {
			user.setOrganization(new Organization());
		}
		user.getOrganization().setId(organizationId);
		if (!SUCCESS.equals(validateUserName())) {
			errorMessage = "用户名已经存在";
			return ERROR;
		}
		user = permissionDubboService.updateUser(user);
		permissionDubboService.updateUserRoleRela(user.getId(), roleIds);
		permissionDubboService.updateUserMultiZone(user.getId(), zoneIds);
		user = permissionDubboService.getFullUserById(user.getId());
		return SUCCESS;
	}

	public String updateDetails() {
		if (!permissionDubboService.updateUserDetails(user)) {
			this.errorMessage = "修改个人信息失败";
			return ERROR;
		}
		return SUCCESS;
	}

	// modify by FCY at 2011.12.26 start
	@PermissionFilters(value = {
			@PermissionFilter(ename = "resetPassword", actionName = "resetUserPassword2"),
			@PermissionFilter(ename = "usersListResetPassword", actionName = "resetUserPassword2") })
	public String resetUserPasswordByUserName() {
		try {
			// permissionService.resetUserPasswordByUserName(user.getUserName(),
			// user.getPassword());
			return SUCCESS;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	// modify by FCY at 2011.12.26 end

	@PermissionFilters(value = {
			@PermissionFilter(ename = "resetPassword", actionName = "resetUserPassword"),
			@PermissionFilter(ename = "usersListResetPassword", actionName = "resetUserPassword") })
	public String resetUserPassword() {
		logger.info("=====UserManageController====UserManageController==resetUserPassword===="
				+ organizationId + ":" + user);
		if (!checkUserAssigned())
			return ERROR;
		try {
			// permissionService.resetUserPasswordByUserId(user.getId(),
			// user.getPassword());
			return SUCCESS;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	//@PermissionFilter(ename = "userManagement")
	public String findUsers() {
		if (organizationId == null) {
			gridPage = new GridPage(new PageInfo<User>());
			return SUCCESS;
		}
		Boolean lockStatus = null;
		if ("locked".equalsIgnoreCase(searchLockStatus)) {
			lockStatus = Boolean.TRUE;
		} else if ("unlocked".equals(searchLockStatus)) {
			lockStatus = Boolean.FALSE;
		}
		PageInfo<User> pageInfo = permissionDubboService
				.findUsersForPageByUserIdAndOrgIdAndLockState(user.getId(),
						organizationId, searchChildOrg, lockStatus, user,
						roleIdsStr, page, rows, sidx, sord);
		gridPage = new GridPage(pageInfo);

		return SUCCESS;
	}

	@PermissionFilter(ename = "usersListManagement")
	public String usersList() {
		if (organizationId == null) {
			gridPage = new GridPage(new PageInfo<User>());
			return SUCCESS;
		}
		Boolean lockStatus = null;
		if ("locked".equalsIgnoreCase(searchLockStatus)) {
			lockStatus = Boolean.TRUE;
		} else if ("unlocked".equals(searchLockStatus)) {
			lockStatus = Boolean.FALSE;
		}
		PageInfo<User> pageInfo = permissionDubboService
				.findUsersForPageByUserIdAndOrgIdAndLockState(user.getId(),
						organizationId, searchChildOrg, lockStatus, user,
						roleIdsStr, page, rows, sidx, sord);
		gridPage = new GridPage(pageInfo);

		return SUCCESS;
	}

	@PermissionFilter(ename = "searchUsers")
	public String searchUsers() {
		PageInfo<User> pageInfo = permissionDubboService.findUsersBylockStatus(
				searchLockStatus, user, page, rows, sidx, sord);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@PermissionFilters(value = {
			@PermissionFilter(ename = "lockUser", actionName = "lockOperate"),
			@PermissionFilter(ename = "usersListLockUsers", actionName = "lockOperate") })
	public String lockOperate() {
		if (!checkUserAssigned())
			return ERROR;
		boolean lockStatus = false;
		if ("true".equalsIgnoreCase(searchLockStatus)) {
			lockStatus = Boolean.FALSE;
		} else if ("false".equals(searchLockStatus)) {
			lockStatus = Boolean.TRUE;
		} else {
			this.errorMessage = "更改用户锁状态失败!";
			return ERROR;
		}
		try {
			permissionDubboService.lockOperate(user.getId(), lockStatus);
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	@PermissionFilters(value = {
			@PermissionFilter(ename = "reUsed", actionName = "reUsed"),
			@PermissionFilter(ename = "usersListReUsed", actionName = "reUsed") })
	public String reUsed() {
		String[] usersId = userIds.split(",");
		List<Long> idList;
		if (usersId[0].equals("")) {
			idList = initTargetId(usersId, 1);
		} else {
			idList = initTargetId(usersId, 0);
		}
		for (Long id : idList) {
			User user = permissionDubboService.getSimpleUserById(id);
			user.setShutDown(true);
			user = permissionDubboService.updateUserByShutdown(user);
			if (null == user) {
				return ERROR;
			}
		}
		return SUCCESS;
	}

	@PermissionFilters(value = {
			@PermissionFilter(ename = "stopUsed", actionName = "stopUsed"),
			@PermissionFilter(ename = "usersListStopUsed", actionName = "stopUsed") })
	public String stopUsed() {
		String[] usersId = userIds.split(",");
		List<Long> idList;
		if (usersId[0].equals("")) {
			idList = initTargetId(usersId, 1);
		} else {
			idList = initTargetId(usersId, 0);
		}
		for (Long id : idList) {
			User user = permissionDubboService.getSimpleUserById(id);
			user.setShutDown(false);
			user = permissionDubboService.updateUserByShutdown(user);
			if (null == user) {
				return ERROR;
			}
		}
		return SUCCESS;
	}

	private List<Long> initTargetId(String[] targetIds, int size) {
		List<Long> idLongs = new ArrayList<Long>();
		for (int i = size; i < targetIds.length; i++) {
			String tempId = targetIds[i];
			if (size == 0) {
				idLongs.add(Long.parseLong(targetIds[i]));
			} else {
				idLongs.add(Long.parseLong(tempId));
			}
		}
		return idLongs;
	}

	public String dispatchUserOperate() {
		operateUser = permissionDubboService.getFullUserById(ThreadVariable
				.getSession().getUserId());
		if (DialogMode.ADD_MODE.equalsIgnoreCase(getMode())) {
			organizations = organizationDubboService
					.findOrganizationsByParentId(organizationId);
			user = new User();
			user.setChangePassword(true);
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(getMode())) {
			organizations = organizationDubboService
					.findOrganizationsByParentId(organizationId);
			user = permissionDubboService.getFullUserById(user.getId());
			organizationId = user.getOrganization() == null ? null : user
					.getOrganization().getId();
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(getMode())) {
			user = permissionDubboService.getFullUserById(user.getId());
			organizationId = user.getOrganization() == null ? null : user
					.getOrganization().getId();
		} else if (RESET_PWD.equalsIgnoreCase(getMode())) {
			if (user != null && user.getId() != null)
				user = permissionDubboService.getFullUserById(user.getId());
			return RESET_PWD;
		} else if (DialogMode.SEARCH_MODE.equals("search")) {
			organizations = organizationDubboService
					.findOrganizationsByParentId(organizationId);
			return "search";
		}
		return SUCCESS;
	}

	public String viewUserOperateByUserName() {
		operateUser = permissionDubboService.getFullUserById(ThreadVariable
				.getSession().getUserId());
		user = permissionDubboService.getFullUserByUerName(user.getUserName());
		organizationId = user.getOrganization() == null ? null : user
				.getOrganization().getId();
		return SUCCESS;
	}

	public String prepareZoneSelection() {
		return SUCCESS;
	}

	public String prepareRoleTable() {
		try {
			Organization organization = organizationDubboService
					.getSimpleOrgById(organizationId);
			Long currentOrgLevel = organization.getOrgLevel().getId();
			if (organization == null || organization.getOrgLevel() == null)
				return ERROR;

			gridPage = new GridPage();

			if (mode != null && "view".equals(mode)) {
				gridPage.setRows(permissionDubboService.findRolesByUserId(user
						.getId()));
				return SUCCESS;
			}

			if (loginIsSuperAdmin()) {
				gridPage.setRows(permissionDubboService
						.findRolesByUserInLevel(currentOrgLevel));
			} else {
				user = permissionDubboService
						.getFullUserById(((Session) ThreadVariable.getSession())
								.getUserId());

				Organization userOwerOrg = organizationDubboService
						.getSimpleOrgById(user.getOrganization().getId());
				Long currentUserOrgLevel = userOwerOrg.getOrgLevel().getId();
				if (user.isAdmin() || currentUserOrgLevel > currentOrgLevel) {
					gridPage.setRows(permissionDubboService
							.findRolesByUserInLevel(currentOrgLevel));
				} else {
					gridPage.setRows(permissionDubboService
							.findRolesByUserIdAndUseInLevel(user.getId(),
									currentOrgLevel));
				}
			}
		} catch (Exception e) {
			logger.error("异常信息", e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String currentUserIsSuperAdmin() {
		user.setAdmin(loginIsSuperAdmin());
		return SUCCESS;

	}

	public String validateUserName() {
		User existedUser = permissionDubboService.findUserByUserName(user
				.getUserName());
		if (existedUser == null) {
			return SUCCESS;
		}
		if (DialogMode.EDIT_MODE.equalsIgnoreCase(getMode())) {
			if (existedUser.getId().equals(user.getId())) {
				return SUCCESS;
			}
		}
		return ERROR;
	}

	public String findUserForAutocomplete() {
		Boolean lockStatus = null;
		if ("locked".equalsIgnoreCase(searchLockStatus)) {
			lockStatus = Boolean.TRUE;
		} else if ("unlocked".equals(searchLockStatus)) {
			lockStatus = Boolean.FALSE;
		}
		userInfors = permissionDubboService.findUserForAutocomplete(
				organizationId, user.getName(), lockStatus, searchChildOrg,
				rows);
		return SUCCESS;
	}

	public String updatePasswordEmail() {
		if (permissionDubboService.updatePasswordSuccess(ThreadVariable
				.getSession().getUserId(), oldPassword, currentPassword,
				validatePassword, email)) {
			return SUCCESS;
		}
		errorMessage = "密码更新失败，请重试！";
		return ERROR;
	}

	public String asyncoUser() {
		user = permissionDubboService.getSimpleUserById(user.getId());
		asyncBBSUser(user.getUserName(), user.getPassword());
		return SUCCESS;
	}

	private boolean asyncBBSUser(String username, String password) {
		if (!"production".equals(GlobalValue.environment))
			return false;
		String url = "?accessKey=%s&userName=%s&password=%s";
		try {
			URL bbsURL = new URL(GridProperties.BBS_BASEURL
					+ String.format(url, ACCESSKEY, username, password));
			Object result = bbsURL.getContent();
			logger.error(result.toString());
			return true;
		} catch (MalformedURLException mu) {
			logger.error("错误信息", mu);
		} catch (IOException e) {
			logger.error("异常信息", e);
		}
		return false;
	}

	private boolean checkUserAssigned() {
		if (user == null || user.getId() == null) {
			this.errorMessage = "系统错误,请联系管理员!";
			return false;
		}
		return true;
	}

	public String toPersonalDetailsUpdate() {
		user = permissionDubboService.getSimpleUserById(ThreadVariable
				.getSession().getUserId());
		return SUCCESS;
	}

	public String oldPasswordIsRight() {
		if (permissionDubboService
				.oldPasswordIsRight(user.getId(), oldPassword)) {
			return SUCCESS;
		}
		errorMessage = "旧密码输入不正确，请重试！";
		return ERROR;
	}

	// 用户列表 角色
	public String prepareRoleTableForUserList() {
		try {
			Organization organization = organizationDubboService
					.getSimpleOrgById(organizationId);
			Long currentOrgLevel = organization.getOrgLevel().getId();
			if (organization == null || organization.getOrgLevel() == null)
				return ERROR;

			gridPage = new GridPage();
			gridPage.setRows(permissionDubboService
					.findAllRolesDownCurrentOrgLevel(currentOrgLevel));
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	// 根据roleId重置工作台
	public String reSetPatelConfig() {
		if (roleId == null && user.isAdmin() == false) {
			errorMessage = "岗位不能为空";
			return ERROR;
		}
		try {
			ids = permissionDubboService.reSetPatelConfigByRoleId(roleId);
		} catch (ServiceException e) {
			errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			logger.error("根据roleId重置工作台", e);
			errorMessage = "根据roleId重置工作台错误";
			return ERROR;
		}
		return SUCCESS;
	}

	// 根据roleId重置工作台
	public String reSetPatelConfigByUserId() {
		if (roleId == null && user.isAdmin() == false && userId == null) {
			errorMessage = "岗位不能为空";
			return ERROR;
		}
		try {
			permissionDubboService.reSetPatelConfigByUserId(userId);
		} catch (ServiceException e) {
			errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			logger.error("根据roleId和userId重置工作台", e);
			errorMessage = "根据roleId和userId重置工作台错误";
			return ERROR;
		}
		return SUCCESS;
	}

	/***
	 * 获取用户订阅的消息类型
	 * 
	 * @return
	 */
	public String findUserHasPlatformMessageTypeByUserId() {

		return SUCCESS;
	}

	/***
	 * 保存用户订阅的消息类型
	 * 
	 * @return
	 */
	public String updateUserHasPlatformMessageType() {

		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private boolean loginIsSuperAdmin() {
		String currentUserName = ((Session) ThreadVariable.getSession())
				.getUserName();
		return "admin".equals(currentUserName);
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getValidatePassword() {
		return validatePassword;
	}

	public void setValidatePassword(String validatePassword) {
		this.validatePassword = validatePassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String[][] getUserInfors() {
		return userInfors;
	}

	public void setUserInfors(String[][] userInfors) {
		this.userInfors = userInfors;
	}

	public boolean isSearchChildOrg() {
		return searchChildOrg;
	}

	public void setSearchChildOrg(boolean searchChildOrg) {
		this.searchChildOrg = searchChildOrg;
	}

	public String getSearchLockStatus() {
		return searchLockStatus;
	}

	public void setSearchLockStatus(String searchLockStatus) {
		this.searchLockStatus = searchLockStatus;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public Long[] getZoneIds() {
		return zoneIds;
	}

	public void setZoneIds(Long[] zoneIds) {
		this.zoneIds = zoneIds;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public List<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	public User getOperateUser() {
		return operateUser;
	}

	public void setOperateUser(User operateUser) {
		this.operateUser = operateUser;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getLogInUserName() {
		return logInUserName;
	}

	public void setLogInUserName(String logInUserName) {
		this.logInUserName = logInUserName;
	}

	public String getModifyRoleUserName() {
		return modifyRoleUserName;
	}

	public void setModifyRoleUserName(String modifyRoleUserName) {
		this.modifyRoleUserName = modifyRoleUserName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Integer> getMessageTypes() {
		return messageTypes;
	}

	public void setMessageTypes(List<Integer> messageTypes) {
		this.messageTypes = messageTypes;
	}

	public List<Integer> getCanSelectMessageTypes() {
		return canSelectMessageTypes;
	}

	public void setCanSelectMessageTypes(List<Integer> canSelectMessageTypes) {
		this.canSelectMessageTypes = canSelectMessageTypes;
	}

	public String getRoleIdsStr() {
		return roleIdsStr;
	}

	public void setRoleIdsStr(String roleIdsStr) {
		this.roleIdsStr = roleIdsStr;
	}

}