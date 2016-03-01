package com.tianque.plugin.account.domain;

import java.math.BigDecimal;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.PropertyDict;

public class Town extends BaseDomain  {

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

	private String projectNumber;
	/*
	 * 项目类别
	 */
	private PropertyDict projectCategory;
	/*
	 * 项目类型
	 */
	private PropertyDict projectSubCategory;

	private Long scopeNumber;
	private Double area;
	private Double mileage;

	private PropertyDict securityType;

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
	private String otherContent;

	public Town() {
		super();
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

	public Town(LedgerPeopleAspirations peopleAspiration,
			Long beneficiaryNumber, PropertyDict buildType, String projectName,
			String projectNumber, PropertyDict projectCategory,
			PropertyDict projectSubCategory, Long scopeNumber, Double area,
			Double mileage, PropertyDict securityType,
			BigDecimal plannedInvestment, BigDecimal selfFund,
			BigDecimal gapFund, String fromAddress, String toAddress,
			String otherContent) {
		super();
		this.peopleAspiration = peopleAspiration;
		this.beneficiaryNumber = beneficiaryNumber;
		this.buildType = buildType;
		this.projectName = projectName;
		this.projectNumber = projectNumber;
		this.projectCategory = projectCategory;
		this.projectSubCategory = projectSubCategory;
		this.scopeNumber = scopeNumber;
		this.area = area;
		this.mileage = mileage;
		this.securityType = securityType;
		this.plannedInvestment = plannedInvestment;
		this.selfFund = selfFund;
		this.gapFund = gapFund;
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
		this.otherContent = otherContent;
	}

	public PropertyDict getSecurityType() {
		return securityType;
	}

	public void setSecurityType(PropertyDict securityType) {
		this.securityType = securityType;
	}

	public PropertyDict getBuildType() {
		return buildType;
	}

	public void setBuildType(PropertyDict buildType) {
		this.buildType = buildType;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	public PropertyDict getProjectCategory() {
		return projectCategory;
	}

	public void setProjectCategory(PropertyDict projectCategory) {
		this.projectCategory = projectCategory;
	}

	public PropertyDict getProjectSubCategory() {
		return projectSubCategory;
	}

	public void setProjectSubCategory(PropertyDict projectSubCategory) {
		this.projectSubCategory = projectSubCategory;
	}

	public Long getScopeNumber() {
		return scopeNumber;
	}

	public void setScopeNumber(Long scopeNumber) {
		this.scopeNumber = scopeNumber;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Double getMileage() {
		return mileage;
	}

	public void setMileage(Double mileage) {
		this.mileage = mileage;
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

	public String getOtherContent() {
		return otherContent;
	}

	public void setOtherContent(String otherContent) {
		this.otherContent = otherContent;
	}

}
