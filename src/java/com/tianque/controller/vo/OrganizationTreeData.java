package com.tianque.controller.vo;

import org.apache.struts2.ServletActionContext;

import com.tianque.core.vo.ExtTreeData;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;

public class OrganizationTreeData extends ExtTreeData{
	
	private int orgTypeInternalId = 0;
	private int orgLevelInternalId = 0;
	private Long seq;
	private String departmentNo;
	private String orgInternalCode;
	
	public OrganizationTreeData(Organization organization) {
		setText(organization.getOrgName());
		setId(organization.getId());
		seq = organization.getSeq();
		orgTypeInternalId = organization.getOrgType().getInternalId();
		orgLevelInternalId = organization.getOrgLevel().getInternalId();
		departmentNo = organization.getDepartmentNo();
		orgInternalCode=organization.getOrgInternalCode();
		convertNodeAdministrative(organization);
	}
	
	public OrganizationTreeData(Organization organization, Boolean checked) {
		setText(organization.getOrgName());
		setId(organization.getId());
		seq = organization.getSeq();
		orgTypeInternalId = organization.getOrgType().getInternalId();
		orgLevelInternalId = organization.getOrgLevel().getInternalId();
		departmentNo = organization.getDepartmentNo();
		orgInternalCode=organization.getOrgInternalCode();
		this.checked = checked;
		convertNodeAdministrative(organization);
	}
	
	public OrganizationTreeData(Organization organization, Long orgType) {
		text = organization.getOrgName();
		id = organization.getId();
		seq = organization.getSeq();
		orgTypeInternalId = organization.getOrgType().getInternalId();
		orgLevelInternalId = organization.getOrgLevel().getInternalId();
		departmentNo = organization.getDepartmentNo();
		orgInternalCode=organization.getOrgInternalCode();
		if(orgType != null && orgType.intValue() == OrganizationType.FUNCTIONAL_ORG){
			convertNodeFun(organization);
		}else{
			convertNodeAdministrative(organization);
		}
	}

	private void convertNodeFun(Organization organization){
		if(organization.getSubCountFun() > 0L){
			leaf = false;
			cls = "folder";
		}else{
			cls = "file";
			leaf = true;
			expanded = false;
			icon = ServletActionContext.getRequest().getContextPath()
			+ "/resource/js/ext/treeImages/funLeaf.gif";
		}
	}
	
	private void convertNodeAdministrative(Organization organization){
		if (organization.getSubCount() > 0L
				|| (OrganizationType.ADMINISTRATIVE_REGION == organization
						.getOrgType().getInternalId() && OrganizationLevel.GRID != organization
						.getOrgLevel().getInternalId())) {
			leaf = false;
			cls = "folder";
		} else {
			cls = "file";
			leaf = true;
			if (OrganizationType.FUNCTIONAL_ORG == organization.getOrgType()
					.getInternalId()) {
				icon = ServletActionContext.getRequest().getContextPath()
						+ "/resource/js/ext/treeImages/funLeaf.gif";
			}
			if (OrganizationLevel.GRID == organization.getOrgLevel()
					.getInternalId()) {
				icon = ServletActionContext.getRequest().getContextPath()
						+ "/resource/js/ext/treeImages/Leaf.gif";
			}
		}
		if (organization.getSubCount() == 0L) {
			cls = "file";
			leaf = true;
			expanded = false;
		}
	}

	public int getOrgTypeInternalId() {
		return orgTypeInternalId;
	}

	public void setOrgTypeInternalId(int orgTypeInternalId) {
		this.orgTypeInternalId = orgTypeInternalId;
	}

	public int getOrgLevelInternalId() {
		return orgLevelInternalId;
	}

	public void setOrgLevelInternalId(int orgLevelInternalId) {
		this.orgLevelInternalId = orgLevelInternalId;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}
}
