package com.tianque.plugin.account.domain;

import java.math.BigDecimal;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.PropertyDict;

public class Traffic extends BaseDomain  {

	private LedgerPeopleAspirations peopleAspiration;
	/*
	 * 受益人口数
	 */
	private Long beneficiaryNumber;
	/*
	 * 建设性质
	 */
	private PropertyDict buildType;

	private String projectName;
	/*
	 * 项目类别
	 */
	private PropertyDict projectCategory;
	/*
	 * 道路类别
	 */
	private PropertyDict roadCategory;
	/*
	 * 路面类型
	 */
	private PropertyDict roadSurfaceCategory;
	/*
	 * 安保设施类型
	 */
	private PropertyDict securityCategory;
	/*
	 * 班线客运
	 */
	private PropertyDict passengerCategory;
	/*
	 * 城市公共交通
	 */
	private PropertyDict publicTransportCategory;
	/*
	 * 客运站管理
	 */
	private PropertyDict passengerManageCategory;
	/*
	 * 客运站建设
	 */
	private PropertyDict passengerBuildCategory;

	/*
	 * 计划投资
	 */
	private BigDecimal plannedInvestment;
	/*
	 * 自筹资金
	 */
	private BigDecimal selfFund;
	/*
	 * 缺口资金
	 */
	private BigDecimal gapFund;
	private String fromAddress;
	private String toAddress;

	private Double mileage;
	private Double impoundage;
	private Double rodeWide;
	private Double wide;
	private Double rodeLength;
	private String otherContent;
	/*
	 * 客运等级
	 */
	private PropertyDict passengerLevelCategory;
	private String remark;
	private PropertyDict contentCategory;
	/*
	 * 客运类型
	 */
	private PropertyDict projectSubCategory;

	public Traffic() {
		super();
	}

	public Traffic(LedgerPeopleAspirations peopleAspiration,
			Long beneficiaryNumber, PropertyDict buildType, String projectName,
			PropertyDict projectCategory, PropertyDict roadCategory,
			PropertyDict roadSurfaceCategory, PropertyDict securityCategory,
			PropertyDict passengerCategory,
			PropertyDict publicTransportCategory,
			PropertyDict passengerManageCategory,
			PropertyDict passengerBuildCategory, BigDecimal plannedInvestment,
			BigDecimal selfFund, BigDecimal gapFund, String fromAddress,
			String toAddress, Double mileage, Double impoundage,
			Double rodeWide, Double wide, Double rodeLength,
			String otherContent, PropertyDict passengerLevelCategory,
			String remark, PropertyDict contentCategory,
			PropertyDict projectSubCategory) {
		super();
		this.peopleAspiration = peopleAspiration;
		this.beneficiaryNumber = beneficiaryNumber;
		this.buildType = buildType;
		this.projectName = projectName;
		this.projectCategory = projectCategory;
		this.roadCategory = roadCategory;
		this.roadSurfaceCategory = roadSurfaceCategory;
		this.securityCategory = securityCategory;
		this.passengerCategory = passengerCategory;
		this.publicTransportCategory = publicTransportCategory;
		this.passengerManageCategory = passengerManageCategory;
		this.passengerBuildCategory = passengerBuildCategory;
		this.plannedInvestment = plannedInvestment;
		this.selfFund = selfFund;
		this.gapFund = gapFund;
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
		this.mileage = mileage;
		this.impoundage = impoundage;
		this.rodeWide = rodeWide;
		this.wide = wide;
		this.rodeLength = rodeLength;
		this.otherContent = otherContent;
		this.passengerLevelCategory = passengerLevelCategory;
		this.remark = remark;
		this.contentCategory = contentCategory;
		this.projectSubCategory = projectSubCategory;
	}

	public PropertyDict getProjectSubCategory() {
		return projectSubCategory;
	}

	public void setProjectSubCategory(PropertyDict projectSubCategory) {
		this.projectSubCategory = projectSubCategory;
	}

	public PropertyDict getContentCategory() {
		return contentCategory;
	}

	public void setContentCategory(PropertyDict contentCategory) {
		this.contentCategory = contentCategory;
	}

	public PropertyDict getPassengerLevelCategory() {
		return passengerLevelCategory;
	}

	public void setPassengerLevelCategory(PropertyDict passengerLevelCategory) {
		this.passengerLevelCategory = passengerLevelCategory;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOtherContent() {
		return otherContent;
	}

	public void setOtherContent(String otherContent) {
		this.otherContent = otherContent;
	}

	public LedgerPeopleAspirations getPeopleAspiration() {
		return peopleAspiration;
	}

	public void setPeopleAspiration(LedgerPeopleAspirations peopleAspiration) {
		this.peopleAspiration = peopleAspiration;
	}

	public Long getBeneficiaryNumber() {
		return beneficiaryNumber;
	}

	public void setBeneficiaryNumber(Long beneficiaryNumber) {
		this.beneficiaryNumber = beneficiaryNumber;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public PropertyDict getProjectCategory() {
		return projectCategory;
	}

	public void setProjectCategory(PropertyDict projectCategory) {
		this.projectCategory = projectCategory;
	}

	public PropertyDict getRoadCategory() {
		return roadCategory;
	}

	public void setRoadCategory(PropertyDict roadCategory) {
		this.roadCategory = roadCategory;
	}

	public PropertyDict getRoadSurfaceCategory() {
		return roadSurfaceCategory;
	}

	public void setRoadSurfaceCategory(PropertyDict roadSurfaceCategory) {
		this.roadSurfaceCategory = roadSurfaceCategory;
	}

	public PropertyDict getSecurityCategory() {
		return securityCategory;
	}

	public void setSecurityCategory(PropertyDict securityCategory) {
		this.securityCategory = securityCategory;
	}

	public PropertyDict getPassengerCategory() {
		return passengerCategory;
	}

	public void setPassengerCategory(PropertyDict passengerCategory) {
		this.passengerCategory = passengerCategory;
	}

	public PropertyDict getPublicTransportCategory() {
		return publicTransportCategory;
	}

	public void setPublicTransportCategory(PropertyDict publicTransportCategory) {
		this.publicTransportCategory = publicTransportCategory;
	}

	public PropertyDict getPassengerManageCategory() {
		return passengerManageCategory;
	}

	public void setPassengerManageCategory(PropertyDict passengerManageCategory) {
		this.passengerManageCategory = passengerManageCategory;
	}

	public PropertyDict getPassengerBuildCategory() {
		return passengerBuildCategory;
	}

	public void setPassengerBuildCategory(PropertyDict passengerBuildCategory) {
		this.passengerBuildCategory = passengerBuildCategory;
	}

	public Double getRodeWide() {
		return rodeWide;
	}

	public void setRodeWide(Double rodeWide) {
		this.rodeWide = rodeWide;
	}

	public Double getRodeLength() {
		return rodeLength;
	}

	public void setRodeLength(Double rodeLength) {
		this.rodeLength = rodeLength;
	}

	public BigDecimal getPlannedInvestment() {
		return plannedInvestment;
	}

	public void setPlannedInvestment(BigDecimal plannedInvestment) {
		this.plannedInvestment = plannedInvestment;
	}

	public BigDecimal getSelfFund() {
		return selfFund;
	}

	public void setSelfFund(BigDecimal selfFund) {
		this.selfFund = selfFund;
	}

	public BigDecimal getGapFund() {
		return gapFund;
	}

	public void setGapFund(BigDecimal gapFund) {
		this.gapFund = gapFund;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public PropertyDict getBuildType() {
		return buildType;
	}

	public void setBuildType(PropertyDict buildType) {
		this.buildType = buildType;
	}

	public Double getMileage() {
		return mileage;
	}

	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}

	public Double getImpoundage() {
		return impoundage;
	}

	public void setImpoundage(Double impoundage) {
		this.impoundage = impoundage;
	}

	public Double getWide() {
		return wide;
	}

	public void setWide(Double wide) {
		this.wide = wide;
	}

}
