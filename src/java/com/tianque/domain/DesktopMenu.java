package com.tianque.domain;

import java.util.List;

import com.tianque.core.base.BaseDomain;

public class DesktopMenu extends BaseDomain {

	private Long userId;
	private Long permissionId;
	private String name;
	private Long indexId;
	private Long page;
	private Long menuType;
	private String url;
	private List<DesktopMenu> children;
	private String imgUrl;
	private Long width;
	private Long height;
	private String ename;
	private boolean isUserDefined;
	private DesktopMenu parentMenu;

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

	public Long getIndexId() {
		return indexId;
	}

	public void setIndexId(Long indexId) {
		this.indexId = indexId;
	}

	public Long getPage() {
		return page;
	}

	public void setPage(Long page) {
		this.page = page;
	}

	public Long getMenuType() {
		return menuType;
	}

	public void setMenuType(Long menuType) {
		this.menuType = menuType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<DesktopMenu> getChildren() {
		return children;
	}

	public void setChildren(List<DesktopMenu> children) {
		this.children = children;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Long getWidth() {
		return width;
	}

	public void setWidth(Long width) {
		this.width = width;
	}

	public Long getHeight() {
		return height;
	}

	public void setHeight(Long height) {
		this.height = height;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public DesktopMenu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(DesktopMenu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public boolean isUserDefined() {
		return isUserDefined;
	}

	public void setUserDefined(boolean isUserDefined) {
		this.isUserDefined = isUserDefined;
	}

}
