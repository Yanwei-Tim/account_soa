package com.tianque.init.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.core.util.Chinese2pinyin;
import com.tianque.domain.Organization;
import com.tianque.domain.Permission;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.Role;
import com.tianque.domain.User;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.WorkBenchType;
import com.tianque.init.Initialization;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.userAuth.api.PropertyDictDubboService;

public class UserRoleInitialization implements Initialization {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private PermissionDubboService permissionDubboService;
	private PropertyDictDubboService propertyDictDubboService;

	public UserRoleInitialization(
			PermissionDubboService permissionDubboService,
			OrganizationDubboRemoteService organizationDubboService,
			PropertyDictDubboService propertyDictDubboService) {
		this.permissionDubboService = permissionDubboService;
		this.propertyDictDubboService = propertyDictDubboService;
	}

	@Override
	public void init() {
		addRole(getAllPermissionEname());
		addUser();
		logger.info("默认用户创建完成!");
	}

	private String[] getAllPermissionEname() {
		List<Permission> permissions = permissionDubboService
				.findAllPermissionsForPage(1, Integer.MAX_VALUE).getResult();
		String[] result = new String[permissions.size()];
		for (int i = 0; i < permissions.size(); i++) {
			Permission permission = permissions.get(i);
			result[i] = permission.getEname();
		}
		return result;
	}

	//
	private Role addRole(String[] permissions) {
		Role role = new Role();
		role.setRoleName("admin");
		role.setDescription("系统管理员");
		role.setCreateDate(Calendar.getInstance().getTime());
		List<PropertyDict> roles = propertyDictDubboService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.WORKBENCH_TYPE, WorkBenchType.SUPER_LEVEL);
		if (roles != null && roles.size() > 0) {
			role.setWorkBenchType(roles.get(0));
		}
		List<PropertyDict> orgLevels = propertyDictDubboService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationLevel.ORG_LEVEL_KEY, OrganizationLevel.CITY);
		if (orgLevels != null && orgLevels.size() > 0) {
			role.setUseInLevel(orgLevels.get(0));
		}
		role.setCreateUser("admin");
		permissionDubboService.addRole(role, permissions);
		return role;
	}

	private Organization getRootOrganization() {
		// yl.lu
		// return
		// organizationDubboService.findOrganizationsByParentId(null).get(0);
		return null;
	}

	private User addUser() {

		User user = new User();
		user.setAdmin(true);
		user.setLock(false);
		user.setCreateDate(Calendar.getInstance().getTime());
		user.setCreateUser("admin");
		user.setUserName("admin");
		user.setName("超级管理员");
		user.setHasNewMessage(false);
		user.setMobile("13988888888");
		user.setWorkPhone("0571-88102930");

		user.setCredits1(0L);
		user.setCredits2(0L);
		user.setCreateUser("admin");
		user.setCreateDate(Calendar.getInstance().getTime());
		Organization rootOrg = getRootOrganization();
		user.setOrganization(rootOrg);
		user.setOrgInternalCode(rootOrg.getOrgInternalCode());
		Map<String, String> map = Chinese2pinyin.changeChinese2Pinyin(user
				.getName());
		user.setFullPinyin(map.get("fullPinyin"));
		user.setSimplePinyin(map.get("simplePinyin"));

		user.setChangePassword(false);
		user.setPassword("admin");

		User exitsUser = permissionDubboService.findUserByUserName(user
				.getUserName());

		if (exitsUser == null) {
			exitsUser = permissionDubboService.addUser(user);
			// permissionDubboService.addUserRoleRela(exitsUser.getId(),
			// role.getId());

			user.setUserName("lcf");
			user.setName("李晨妃");
			user.setPassword("11111111");
			permissionDubboService.addUser(user);

			user.setUserName("yushijuan");
			user.setName("喻士娟");
			user.setPassword("11111111");
			permissionDubboService.addUser(user);

			user.setUserName("yangshuai");
			user.setName("杨帅");
			user.setPassword("11111111");
			permissionDubboService.addUser(user);

			user.setUserName("zhangdongyu");
			user.setName("张东玉");
			user.setPassword("11111111");
			permissionDubboService.addUser(user);

			user.setUserName("chengwenjin");
			user.setName("程文金");
			user.setPassword("11111111");
			permissionDubboService.addUser(user);

			user.setUserName("yangpengdian");
			user.setName("杨-鹏-滇");
			user.setPassword("11111111");
			permissionDubboService.addUser(user);

			user.setUserName("songfei");
			user.setName("宋飞");
			user.setPassword("11111111");
			permissionDubboService.addUser(user);

			user.setUserName("weiyinglei");
			user.setName("魏迎雷");
			user.setPassword("11111111");
			permissionDubboService.addUser(user);

			user.setUserName("wangxiaoyan");
			user.setName("王小艳");
			user.setPassword("11111111");
			permissionDubboService.addUser(user);

			user.setUserName("hujiwei");
			user.setName("胡继伟");
			user.setPassword("11111111");
			permissionDubboService.addUser(user);

			user.setUserName("siyuyan");
			user.setName("司于艳");
			user.setPassword("11111111");
			permissionDubboService.addUser(user);

			user.setUserName("hemiaojun");
			user.setName("何淼军");
			user.setPassword("11111111");
			permissionDubboService.addUser(user);

			user.setUserName("yumeng");
			user.setName("余梦");
			user.setPassword("11111111");
			permissionDubboService.addUser(user);

			user.setUserName("wangzhen");
			user.setName("王振");
			user.setPassword("11111111");
			permissionDubboService.addUser(user);

			user.setUserName("huangxiaoping");
			user.setName("黄小平");
			user.setPassword("11111111");
			permissionDubboService.addUser(user);

			user.setUserName("liman");
			user.setName("李满");
			user.setPassword("11111111");
			permissionDubboService.addUser(user);

			user.setUserName("lvliujie");
			user.setName("吕刘杰");
			user.setPassword("11111111");
			permissionDubboService.addUser(user);

			user.setUserName("lijiecong");
			user.setName("李洁聪");
			user.setPassword("11111111");
			permissionDubboService.addUser(user);

			user.setUserName("jiajunnan");
			user.setName("贾君楠");
			user.setPassword("11111111");
			permissionDubboService.addUser(user);

		}
		return exitsUser;
	}

}
