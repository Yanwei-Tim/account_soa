package com.tianque.controller.vo;

import java.io.Serializable;

import com.tianque.core.vo.ExtTreeData;
import com.tianque.domain.Organization;

public class OrgDataWhenAdd implements Serializable{
	private ExtTreeData extTreeData ;
	private Organization organization ;
	
	public OrgDataWhenAdd(ExtTreeData extTreeData,Organization organization){
		this.extTreeData = extTreeData;
		this.organization = organization;
	}
	
	public ExtTreeData getExtTreeData() {
		return extTreeData;
	}
	public void setExtTreeData(ExtTreeData extTreeData) {
		this.extTreeData = extTreeData;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
}