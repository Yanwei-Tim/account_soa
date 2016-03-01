package com.tianque.workBench.moduelClickCount.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Permission;

public class ModuelClick extends BaseDomain {
	private Long userId;
	private Long permissionId;
	private Long clickTimes;
	private Permission permission;
	private String rel;
	private String url;
	private String imgUrl;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	public Long getClickTimes() {
		return clickTimes;
	}

	public void setClickTimes(Long clickTimes) {
		this.clickTimes = clickTimes;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}


}
