package com.tianque.init.vo;

import java.util.ArrayList;

public class PermissionObject{
	public String cname;
	public String ename;
	public int permissionType;
	public String moduleName;
	public String description;
	public String normalUrl;
	public String leaderUrl;
	public ArrayList<PermissionObject> permissionObject;
	public PermissionObject(String cname,String ename,String moduleName,String description ,ArrayList<PermissionObject> permissionObject){
		this.cname = cname;
		this.ename = ename;
		this.moduleName = moduleName;
		this.description = description;
		this.permissionObject = permissionObject;
	}
	public PermissionObject(){
		
	}
	public ArrayList<PermissionObject> getPermissionObject() {
		return permissionObject;
	}
	public void setPermissionObject(ArrayList<PermissionObject> permissionObject) {
		this.permissionObject = permissionObject;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	
}
