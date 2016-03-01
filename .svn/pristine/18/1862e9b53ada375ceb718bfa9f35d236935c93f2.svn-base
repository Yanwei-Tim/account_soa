package com.tianque.sysadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Role;
import com.tianque.userAuth.api.SearchRoleDubboService;

@Controller("searchRoleManageController")
@Scope("prototype")
@Transactional(readOnly = true)
public class SearchRoleManageController extends BaseAction {
	private Role role;
	@Autowired
	private SearchRoleDubboService searchRoleDubboService;
	
	@PermissionFilter(ename = "searchRoles")
	public String searchRoles(){
		PageInfo<Role> pageInfo = searchRoleDubboService.searchRoles(role, page, rows, sidx, sord);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}
	

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
