package com.tianque.plugin.account.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class BaseWorking extends BaseDomain {
	private static final long serialVersionUID = 1L;

	/** 所属网格 */
	private Organization organization;
	/** 所属网格编号(ORGINTERNALCODE) */
	private String orgInternalCode;
	/** 姓名(NAME) */
	private String name;
	/** 身份证(IDCARDNO) */
	private String idCardNo;
	/** 性别(GENDER) */
	private PropertyDict gender;
	/** 联系电话(MOBILENUMBER) */
	private String mobileNumber;
	/** 出生年月(BIRTHDAY) */
	private java.util.Date birthDay;
	/** 是否党员，0否 1是(ISPARTYMEMBER) */
	private Boolean isPartyMember;
	/** 常住地址(PERMANENTADDRESS) */
	private String permanentAddress;
	/** 职业或身份(POSITION) */
	private PropertyDict position;
	/** 服务联系人(SERVERCONTRACTOR) */
	private String serverContractor;
	/** 服务联系电话(SERVERTELEPHONE) */
	private String serverTelephone;
	/** 服务职务(SERVERJOB) */
	private String serverJob;
	/** 服务联系人单位(SERVERUNIT) */
	private String serverUnit;
	/** 编号(SERIALNUMBER) */
	private String serialNumber;
	/** 建表类型(CREATETABLETYPE) */
	private PropertyDict createTableType;
	/** 发生网格(OCCURORGID) */
	private Organization occurOrg;
	/** 发生网格(OCCURORGINTERNALCODE) */
	private String occurOrgInternalCode;
	/** 网格号(GRIDNO) */
	private String gridNo;
	/** 登记单位(BOOKINGUNIT) */
	private String bookingUnit;
	/** 登记人(REGISTRANT) */
	private String registrant;
	/** 登记时间(REGISTRATIONTIME) */
	private Date registrationTime;

	/** 是否有记录 */
	private Integer hasAccountLog;
	/** 仅显示本级、所有下辖、直属下辖 */
	private String displayLevel;

	private Integer earingWarn;

	private int ledgerType;

	private Long ledgerId;

	private Organization createOrg;

	private Integer status;

	private ThreeRecordsIssueStep currentStep;

	private Organization currentOrg;

	/** 时间发生的小时 */
	private String hours;
	/** 时间发生的分钟 */
	private String minute;

	/** 发生时间 */
	private Date occurDate;

	private String subject;
	/*
	 * 事件编号
	 */
	private String oldIssueId;

	/** 来源方式 */
	private PropertyDict sourceKind;

	/* 历史数据台帐号 */
	private String historyId;

	/*
	 * 事件转台账编号
	 */
	private Long convertId;

	/* 历史数据转入台帐号 */
	private String oldHistoryId;
	/* 台账转入编号* */
	private Long oldLedgerId;
	/* 台账转入类型* */
	private Integer oldLedgerType;

	/* 是否可以反馈* */
	private Integer isCanFeedBack;

	public BaseWorking() {
		super();
	}

	public BaseWorking(Organization organization, String orgInternalCode,
			String name, String idCardNo, PropertyDict gender,
			String mobileNumber, Date birthDay, Boolean isPartyMember,
			String permanentAddress, PropertyDict position,
			String serverContractor, String serverTelephone, String serverJob,
			String serverUnit, String serialNumber,
			PropertyDict createTableType, Organization occurOrg,
			String occurOrgInternalCode, String gridNo, String bookingUnit,
			String registrant, Date registrationTime, Integer hasAccountLog) {
		super();
		this.organization = organization;
		this.orgInternalCode = orgInternalCode;
		this.name = name;
		this.idCardNo = idCardNo;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.birthDay = birthDay;
		this.isPartyMember = isPartyMember;
		this.permanentAddress = permanentAddress;
		this.position = position;
		this.serverContractor = serverContractor;
		this.serverTelephone = serverTelephone;
		this.serverJob = serverJob;
		this.serverUnit = serverUnit;
		this.serialNumber = serialNumber;
		this.createTableType = createTableType;
		this.occurOrg = occurOrg;
		this.occurOrgInternalCode = occurOrgInternalCode;
		this.gridNo = gridNo;
		this.bookingUnit = bookingUnit;
		this.registrant = registrant;
		this.registrationTime = registrationTime;
		this.hasAccountLog = hasAccountLog;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public PropertyDict getGender() {
		return gender;
	}

	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@JSON(format = "yyyy-MM-dd")
	public java.util.Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(java.util.Date birthDay) {
		this.birthDay = birthDay;
	}

	public Boolean getIsPartyMember() {
		return isPartyMember;
	}

	public void setIsPartyMember(Boolean isPartyMember) {
		this.isPartyMember = isPartyMember;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public PropertyDict getPosition() {
		return position;
	}

	public void setPosition(PropertyDict position) {
		this.position = position;
	}

	public String getServerContractor() {
		return serverContractor;
	}

	public void setServerContractor(String serverContractor) {
		this.serverContractor = serverContractor;
	}

	public String getServerTelephone() {
		return serverTelephone;
	}

	public void setServerTelephone(String serverTelephone) {
		this.serverTelephone = serverTelephone;
	}

	public String getServerJob() {
		return serverJob;
	}

	public void setServerJob(String serverJob) {
		this.serverJob = serverJob;
	}

	public String getServerUnit() {
		return serverUnit;
	}

	public void setServerUnit(String serverUnit) {
		this.serverUnit = serverUnit;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public PropertyDict getCreateTableType() {
		return createTableType;
	}

	public void setCreateTableType(PropertyDict createTableType) {
		this.createTableType = createTableType;
	}

	public Organization getOccurOrg() {
		return occurOrg;
	}

	public void setOccurOrg(Organization occurOrg) {
		this.occurOrg = occurOrg;
	}

	public String getOccurOrgInternalCode() {
		return occurOrgInternalCode;
	}

	public void setOccurOrgInternalCode(String occurOrgInternalCode) {
		this.occurOrgInternalCode = occurOrgInternalCode;
	}

	public String getGridNo() {
		return gridNo;
	}

	public void setGridNo(String gridNo) {
		this.gridNo = gridNo;
	}

	public String getBookingUnit() {
		return bookingUnit;
	}

	public void setBookingUnit(String bookingUnit) {
		this.bookingUnit = bookingUnit;
	}

	public String getRegistrant() {
		return registrant;
	}

	public void setRegistrant(String registrant) {
		this.registrant = registrant;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}

	public Integer getHasAccountLog() {
		return hasAccountLog;
	}

	public void setHasAccountLog(Integer hasAccountLog) {
		this.hasAccountLog = hasAccountLog;
	}

	public String getDisplayLevel() {
		return displayLevel;
	}

	public void setDisplayLevel(String displayLevel) {
		this.displayLevel = displayLevel;
	}

	public void setEaringWarn(Integer earingWarn) {
		this.earingWarn = earingWarn;
	}

	public Integer getEaringWarn() {
		return earingWarn;
	}

	public int getLedgerType() {
		return ledgerType;
	}

	public void setLedgerType(int ledgerType) {
		this.ledgerType = ledgerType;
	}

	public Organization getCreateOrg() {
		return createOrg;
	}

	public void setCreateOrg(Organization createOrg) {
		this.createOrg = createOrg;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public ThreeRecordsIssueStep getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(ThreeRecordsIssueStep currentStep) {
		this.currentStep = currentStep;
	}

	public Organization getCurrentOrg() {
		return currentOrg;
	}

	public void setCurrentOrg(Organization currentOrg) {
		this.currentOrg = currentOrg;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getOccurDate() {
		return occurDate;
	}

	public void setOccurDate(Date occurDate) {
		this.occurDate = occurDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public PropertyDict getSourceKind() {
		return sourceKind;
	}

	public void setSourceKind(PropertyDict sourceKind) {
		this.sourceKind = sourceKind;
	}

	public String getOldIssueId() {
		return oldIssueId;
	}

	public void setOldIssueId(String oldIssueId) {
		this.oldIssueId = oldIssueId;
	}

	public String getHistoryId() {
		return historyId;
	}

	public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}

	public Long getLedgerId() {
		return ledgerId;
	}

	public void setLedgerId(Long ledgerId) {
		this.ledgerId = ledgerId;
	}

	public Long getConvertId() {
		return convertId;
	}

	public void setConvertId(Long convertId) {
		this.convertId = convertId;
	}

	public String getOldHistoryId() {
		return oldHistoryId;
	}

	public void setOldHistoryId(String oldHistoryId) {
		this.oldHistoryId = oldHistoryId;
	}

	public Long getOldLedgerId() {
		return oldLedgerId;
	}

	public void setOldLedgerId(Long oldLedgerId) {
		this.oldLedgerId = oldLedgerId;
	}

	public Integer getOldLedgerType() {
		return oldLedgerType;
	}

	public void setOldLedgerType(Integer oldLedgerType) {
		this.oldLedgerType = oldLedgerType;
	}

	public Integer getIsCanFeedBack() {
		return isCanFeedBack;
	}

	public void setIsCanFeedBack(Integer isCanFeedBack) {
		this.isCanFeedBack = isCanFeedBack;
	}

}
