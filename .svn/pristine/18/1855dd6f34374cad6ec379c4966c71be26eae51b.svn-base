package com.tianque.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;


@SuppressWarnings("serial")
public class Session extends BaseDomain {
	private String sessionId;
	private Long userId;
	private String userName;
	private String userRealName;
	private Organization organization;
	private String accessIp;
	private Date accessTime;
	private boolean isLogin = false;
	private Date loginDate;
	private String lastUrl;
	private String loginIp;
	private String validateCode;
	private String orgInternalCode;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getLastUrl() {
		return lastUrl;
	}

	public void setLastUrl(String lastUrl) {
		this.lastUrl = lastUrl;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

	public String getAccessIp() {
		return accessIp;
	}

	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	
	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
