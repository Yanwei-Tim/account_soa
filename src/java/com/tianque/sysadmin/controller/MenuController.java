package com.tianque.sysadmin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.domain.Permission;
import com.tianque.service.MenuService;
import com.tianque.userAuth.api.PermissionDubboService;

@Namespaces({ @Namespace("/sysadmin/menuManage"),
		@Namespace("/hotModuel/sysadmin/menuManage") })
@Controller("menuController")
@Transactional
@Scope("prototype")
public class MenuController extends BaseAction {

	@Autowired
	private MenuService menuService;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private PermissionDubboService permissionDubboService;
	private String gisUrl;
	private String unifiedSearchUrl;
	private String ename;
	private String cname;
	private List<Permission> permissions;
	private Map<String, List<Permission>> childMap = new HashMap<String, List<Permission>>();
	private Map<String, List<Permission>> grandsonMap = new HashMap<String, List<Permission>>();

	@Actions(value = {
			@Action(value = "getHighLevelBaseInfoMenuList", results = { @Result(location = "/baseinfo/middleLevelSideBar.jsp", name = "success") }),
			@Action(value = "getLowLevelBaseInfoMenuList", results = { @Result(location = "/baseinfo/lowLevelSideBar.jsp", name = "success") }),
			@Action(value = "getLowLevelBaseInfoMenuListByPageList", results = { @Result(location = "/includes/pageList.jsp", name = "success") }),
			@Action(value = "getNavigationList", results = { @Result(location = "/includes/navigation.jsp", name = "success") }),
			@Action(value = "getNavigationMap", results = { @Result(location = "/workBench/common/list.jsp", name = "success") }),
			@Action(value = "getLeftMenuList", results = { @Result(location = "/includes/leftMenuList.jsp", name = "success") }) })
	public String getBaseInfoMenuList() {
		if (ename != null) {
			cname = permissionDubboService.findPermissionByEname(ename)
					.getCname();
		}
		permissions = menuService.getChildMenuByEnameAndExcludeButtons(ename);
		if ("socialManagement".equals(ename)) {
			permissions = menuService
					.getChildMenuByEnameAndExcludeButtons(permissions.get(0)
							.getEname());
		}
		getChildMapByEname();
		gisUrl = globalSettingService.getGlobalValue(GlobalSetting.GIS_URL);
		unifiedSearchUrl = globalSettingService
				.getGlobalValue(GlobalSetting.UNIFIEDSEARCH_URL);
		return SUCCESS;
	}

	@Actions(value = { @Action(value = "getTabByParentEname", results = { @Result(location = "/includes/tabList.jsp", name = "success") }) })
	public String getMenuListByParentId() {
		permissions = menuService.getChildMenuByEname(ename);
		return SUCCESS;
	}

	private void getChildMapByEname() {
		for (Permission permission : permissions) {
			List<Permission> parents = menuService
					.getChildMenuByEnameAndExcludeButtons(permission.getEname());
			for (Permission parent : parents) {
				List<Permission> grandsons = menuService
						.getChildMenuByEnameAndExcludeButtons(parent.getEname());
				grandsonMap.put(parent.getEname(), grandsons);
			}
			childMap.put(permission.getEname(), parents);
		}
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public Map<String, List<Permission>> getChildMap() {
		return childMap;
	}

	public void setChildMap(Map<String, List<Permission>> childMap) {
		this.childMap = childMap;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Map<String, List<Permission>> getGrandsonMap() {
		return grandsonMap;
	}

	public void setGrandsonMap(Map<String, List<Permission>> grandsonMap) {
		this.grandsonMap = grandsonMap;
	}

	public String getGisUrl() {
		return gisUrl;
	}

	public void setGisUrl(String gisUrl) {
		this.gisUrl = gisUrl;
	}

	public String getUnifiedSearchUrl() {
		return unifiedSearchUrl;
	}

	public void setUnifiedSearchUrl(String unifiedSearchUrl) {
		this.unifiedSearchUrl = unifiedSearchUrl;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

}
