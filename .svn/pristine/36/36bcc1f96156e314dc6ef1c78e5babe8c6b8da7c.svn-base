package com.tianque.mobile.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

public class MobileInfo extends BaseDomain {
	/**
	 * 
	 */
	private static final long serialVersionUID = 241806637044721795L;
	private String imsiNo;
	private String name;
	private String mobileNumber;
	private Date acceptTime;
	private Date effectivelyTime;
	private Organization organization;
	private String orgInternalCode;

	public String getImsiNo() {
		return imsiNo;
	}

	public void setImsiNo(String imsiNo) {
		this.imsiNo = imsiNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getEffectivelyTime() {
		return effectivelyTime;
	}

	public void setEffectivelyTime(Date effectivelyTime) {
		this.effectivelyTime = effectivelyTime;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
