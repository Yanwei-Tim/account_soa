package com.tianque.domain;

import java.util.Date;

import com.tianque.core.base.BaseDomain;

@SuppressWarnings("serial")
public class Organization extends BaseDomain {

	private String orgName;
	private String contactWay;
	private String departmentNo;

	private String buildingId;

	private GisInfo gisInfo;
	/**
	 * 组织结构内置代码，对于外部不暴露
	 */
	private String orgInternalCode;
	/**
	 * 父组织结构
	 */
	private Organization parentOrg;
	/**
	 * 所属职能部门
	 */
	private Organization parentFunOrg;
	/**
	 * 子组织结构分配到最大的编码，依然是内部，不对外暴露
	 */

	private String fullPinyin;
	private String simplePinyin;
	// @NotSaved
	private int maxCode = 0;
	private String remark;
	private Long subCount;
	private Long seq;
	private PropertyDict orgLevel;
	private PropertyDict orgType;
	private Long subCountFun;
	/**
	 * 组织机构的全名，如中国-->浙江->杭州 。不存取
	 */
	private String fullOrgName;

	private PropertyDict functionalOrgType;

	private Long changeType;
	private Date changeDate;

	public Long getChangeType() {
		return changeType;
	}

	public void setChangeType(Long changeType) {
		this.changeType = changeType;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public String getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Long getSubCount() {
		return subCount;
	}

	public void setSubCount(Long subCount) {
		this.subCount = subCount;
	}

	public Organization getParentOrg() {
		return parentOrg;
	}

	public void setParentOrg(Organization parentOrg) {
		this.parentOrg = parentOrg;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public int getMaxCode() {
		return maxCode;
	}

	public void setMaxCode(int maxCode) {
		this.maxCode = maxCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public String getFullPinyin() {
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		if (fullPinyin != null && fullPinyin.length() > 30) {
			fullPinyin = fullPinyin.substring(0, 30);
		}
		this.fullPinyin = fullPinyin;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		if (simplePinyin != null && simplePinyin.length() > 10) {
			simplePinyin = simplePinyin.substring(0, 10);
		}
		this.simplePinyin = simplePinyin;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public PropertyDict getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(PropertyDict orgLevel) {
		this.orgLevel = orgLevel;
	}

	public PropertyDict getOrgType() {
		return orgType;
	}

	public void setOrgType(PropertyDict orgType) {
		this.orgType = orgType;
	}

	public Organization getParentFunOrg() {
		return parentFunOrg;
	}

	public void setParentFunOrg(Organization parentFunOrg) {
		this.parentFunOrg = parentFunOrg;
	}

	public Long getSubCountFun() {
		return subCountFun;
	}

	public void setSubCountFun(Long subCountFun) {
		this.subCountFun = subCountFun;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public GisInfo getGisInfo() {
		return gisInfo;
	}

	public void setGisInfo(GisInfo gisInfo) {
		this.gisInfo = gisInfo;
	}

	/**
	 * 组织机构的全名，如中国-->浙江->杭州 。不存取
	 */
	public String getFullOrgName() {
		return fullOrgName;
	}

	/**
	 * 组织机构的全名，如中国-->浙江->杭州 。不存取
	 */
	public void setFullOrgName(String fullOrgName) {
		this.fullOrgName = fullOrgName;
	}

	public PropertyDict getFunctionalOrgType() {
		return functionalOrgType;
	}

	public void setFunctionalOrgType(PropertyDict functionalOrgType) {
		this.functionalOrgType = functionalOrgType;
	}

}
