package com.tianque.domain;

import com.tianque.core.base.BaseDomain;


@SuppressWarnings("serial")
public class Permission extends BaseDomain {

	private String cname;
	private String ename;
	private int permissionType;
	private Permission parent;
	private boolean enable;
	private String moduleName;
	private String description;
	private Long indexId;
	private String normalUrl;
	private String leaderUrl;
	private Long parentId;

	public Long getIndexId() {
		return indexId;
	}

	public void setIndexId(Long indexId) {
		this.indexId = indexId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Permission getParent() {
		return parent;
	}

	public void setParent(Permission parent) {
		this.parent = parent;
	}

	public int getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(int permissionType) {
		this.permissionType = permissionType;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getNormalUrl() {
		return normalUrl;
	}

	public void setNormalUrl(String normalUrl) {
		this.normalUrl = normalUrl;
	}

	public String getLeaderUrl() {
		return leaderUrl;
	}

	public void setLeaderUrl(String leaderUrl) {
		this.leaderUrl = leaderUrl;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}
