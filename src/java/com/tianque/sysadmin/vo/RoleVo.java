package com.tianque.sysadmin.vo;

import java.io.Serializable;

public class RoleVo implements Serializable {
	private Long roleId;
	private Long permissionId;
	private Boolean ststus;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	public Boolean getStstus() {
		return ststus;
	}

	public void setStstus(Boolean ststus) {
		this.ststus = ststus;
	}

	public RoleVo() {
	}
}
