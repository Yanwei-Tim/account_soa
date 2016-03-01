package com.tianque.domain.vo;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.vo.BaseVo;
import com.tianque.domain.Organization;
import com.tianque.domain.Role;

@SuppressWarnings("serial")
public class UserVo extends BaseVo {
	private String userName;
	private String name;
	private String password;
	private String mobile;
	private Boolean isLock;

	private Boolean isAdmin;
	private Boolean changePassword;
	private Boolean hasNewMessage;
	private String fullPinyin;
	private String simplePinyin;

	private Long credits1 = 0L;
	private Long credits2 = 0L;
	private String email;
	private Date lastLoginTime;
	private String lastLoginIp;
	private Date previousLoginTime;
	private String previousLoginIp;

	private Organization organization;

	private String workCompany;
	private String workPhone;
	private String homePhone;
	private String orgInternalCode;
	private Integer failureTimes = 0;
	private List<Role> roles;
	private List<Organization> multiZone;
	private String vpdn;
	// 用户是否启用
	private Boolean isShutDown;
	// 登录时是否连接vpdn
	private Boolean connectVpdn;
	// PC可用
	private Boolean pcusable;
	// 手机可用
	private Boolean mobileusable;
	private int ignoreIsShutDownOrNot;
	private int ignoreIsAdminOrNot;
	private int ignoreIsValidatorImsiOrNot;
	private Date timeforQuery;
	// 头像路径
	private String headerUrl;

	// 用户所在市名称
	private String cityOrgName;

	/** IMSI号 */
	private String imsiMax;
	/** IMSI号 */
	private String imsiMin;
	/** 是否验证IMSI号 */
	private Boolean isValidatorImsi;
	/** 是否第一次登录工作台 */
	private Boolean isFristWorkBench;
	/** 是否GPS定位 */
	private int gpsOrNot;
	/** 是否四支队伍 */
	private int fourTeamsOrNot;
	/** 是否匹配网格 */
	private int matchupOrgOrNot;
	/** 在线状态 */
	private int onLineState;
	/** 账号状态 */
	private int state;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getTimeforQuery() {
		return timeforQuery;
	}

	public Boolean isPcusable() {
		return pcusable;
	}

	public void setPcusable(Boolean pcusable) {
		this.pcusable = pcusable;
	}

	public Boolean isMobileusable() {
		return mobileusable;
	}

	public void setMobileusable(Boolean mobileusable) {
		this.mobileusable = mobileusable;
	}

	public void setTimeforQuery(Date timeforQuery) {
		this.timeforQuery = timeforQuery;
	}

	public int getIgnoreIsAdminOrNot() {
		return ignoreIsAdminOrNot;
	}

	public void setIgnoreIsAdminOrNot(int ignoreIsAdminOrNot) {
		this.ignoreIsAdminOrNot = ignoreIsAdminOrNot;
	}

	public int getIgnoreIsShutDownOrNot() {
		return ignoreIsShutDownOrNot;
	}

	public void setIgnoreIsShutDownOrNot(int ignoreIsShutDownOrNot) {
		this.ignoreIsShutDownOrNot = ignoreIsShutDownOrNot;
	}

	public Boolean isConnectVpdn() {
		return connectVpdn;
	}

	public void setConnectVpdn(Boolean connectVpdn) {
		this.connectVpdn = connectVpdn;
	}

	public Boolean getHasNewMessage() {
		return hasNewMessage;
	}

	public void setHasNewMessage(Boolean hasNewMessage) {
		this.hasNewMessage = hasNewMessage;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean isChangePassword() {
		return changePassword;
	}

	public void setChangePassword(Boolean changePassword) {
		this.changePassword = changePassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JSON(serialize = false, deserialize = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullPinyin() {
		if (fullPinyin != null && fullPinyin.length() > 30) {
			return fullPinyin.substring(0, 30);
		}
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		if (fullPinyin != null && fullPinyin.length() > 30) {
			fullPinyin = fullPinyin.substring(0, 30);
		}
		this.fullPinyin = fullPinyin;
	}

	public String getSimplePinyin() {
		if (simplePinyin != null && simplePinyin.length() > 10) {
			return simplePinyin.substring(0, 10);
		}
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		if (simplePinyin != null && simplePinyin.length() > 10) {
			simplePinyin = simplePinyin.substring(0, 10);
		}
		this.simplePinyin = simplePinyin;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWorkCompany() {
		return workCompany;
	}

	public void setWorkCompany(String workCompany) {
		this.workCompany = workCompany;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Boolean isLock() {
		return isLock;
	}

	public void setLock(Boolean isLock) {
		this.isLock = isLock;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public List<Organization> getMultiZone() {
		return multiZone;
	}

	public void setMultiZone(List<Organization> multiZone) {
		this.multiZone = multiZone;
	}

	public Long getCredits1() {
		return credits1;
	}

	public void setCredits1(Long credits1) {
		this.credits1 = credits1;
	}

	public Long getCredits2() {
		return credits2;
	}

	public void setCredits2(Long credits2) {
		this.credits2 = credits2;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getPreviousLoginTime() {
		return previousLoginTime;
	}

	public void setPreviousLoginTime(Date previousLoginTime) {
		this.previousLoginTime = previousLoginTime;
	}

	public String getPreviousLoginIp() {
		return previousLoginIp;
	}

	public void setPreviousLoginIp(String previousLoginIp) {
		this.previousLoginIp = previousLoginIp;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public Integer getFailureTimes() {
		return failureTimes;
	}

	public void setFailureTimes(Integer failureTimes) {
		this.failureTimes = failureTimes;
	}

	public String getVpdn() {
		return vpdn;
	}

	public void setVpdn(String vpdn) {
		this.vpdn = vpdn;
	}

	public Boolean isShutDown() {
		return isShutDown;
	}

	public void setShutDown(Boolean isShutDown) {
		this.isShutDown = isShutDown;
	}

	public Boolean getIsFristWorkBench() {
		return isFristWorkBench;
	}

	public void setIsFristWorkBench(Boolean isFristWorkBench) {
		this.isFristWorkBench = isFristWorkBench;
	}

	public String getHeaderUrl() {
		return headerUrl;
	}

	public String getImsiMax() {
		return imsiMax;
	}

	public void setImsiMax(String imsiMax) {
		this.imsiMax = imsiMax;
	}

	public String getImsiMin() {
		return imsiMin;
	}

	public void setImsiMin(String imsiMin) {
		this.imsiMin = imsiMin;
	}

	public void setHeaderUrl(String headerUrl) {
		this.headerUrl = headerUrl;
	}

	public String getCityOrgName() {
		return cityOrgName;
	}

	public void setCityOrgName(String cityOrgName) {
		this.cityOrgName = cityOrgName;
	}

	public Boolean isValidatorImsi() {
		return isValidatorImsi;
	}

	public void setValidatorImsi(Boolean isValidatorImsi) {
		this.isValidatorImsi = isValidatorImsi;
	}

	public int getIgnoreIsValidatorImsiOrNot() {
		return ignoreIsValidatorImsiOrNot;
	}

	public void setIgnoreIsValidatorImsiOrNot(int ignoreIsValidatorImsiOrNot) {
		this.ignoreIsValidatorImsiOrNot = ignoreIsValidatorImsiOrNot;
	}

	public int getGpsOrNot() {
		return gpsOrNot;
	}

	public void setGpsOrNot(int gpsOrNot) {
		this.gpsOrNot = gpsOrNot;
	}

	public int getFourTeamsOrNot() {
		return fourTeamsOrNot;
	}

	public void setFourTeamsOrNot(int fourTeamsOrNot) {
		this.fourTeamsOrNot = fourTeamsOrNot;
	}

	public int getMatchupOrgOrNot() {
		return matchupOrgOrNot;
	}

	public void setMatchupOrgOrNot(int matchupOrgOrNot) {
		this.matchupOrgOrNot = matchupOrgOrNot;
	}

	public int getOnLineState() {
		return onLineState;
	}

	public void setOnLineState(int onLineState) {
		this.onLineState = onLineState;
	}

}
