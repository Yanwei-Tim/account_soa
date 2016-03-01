package com.tianque.domain;

import java.util.List;

/** 我的组织机构*/
public class MyArea extends MultipleContacter{
	/** 所属用户 */
	private User owner;
	/** 备注*/
	private String remark;
	/** 拥有的联系人*/
	List<SingleContacter> singleContacters;
	
	/**当前最高层组织机构**/
	private Organization organization;
	/**包含层级**/
	private List<Integer> orgLevels;
	/**包含组织机构**/
	private List<Organization> organizations;
	/**去除组织机构**/
	private List<Organization> exceptOrganization;
	
	@Override
	public String getMobile() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public MyArea(){
		this.setBelongClass(MYAREA);
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<SingleContacter> getSingleContacters() {
		return singleContacters;
	}

	public void setSingleContacters(List<SingleContacter> singleContacters) {
		this.singleContacters = singleContacters;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<Integer> getOrgLevels() {
		return orgLevels;
	}

	public void setOrgLevels(List<Integer> orgLevels) {
		this.orgLevels = orgLevels;
	}

	public List<Organization> getExceptOrganization() {
		return exceptOrganization;
	}

	public void setExceptOrganization(List<Organization> exceptOrganization) {
		this.exceptOrganization = exceptOrganization;
	}

	public List<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}
	
	
	
}
