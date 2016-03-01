package com.tianque.plugin.account.domain;

import java.math.BigDecimal;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.PropertyDict;

public class Environment extends BaseDomain  {

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
	 * 项目类型
	 */
	private PropertyDict projectSubCategory;

	private PropertyDict unitCategory;
	private Double governNumber;

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
	/*
	 * 建设规模及建设内容
	 */
	private String content;

	public Environment() {
		super();
	}

	public Environment(LedgerPeopleAspirations peopleAspiration,
			Long beneficiaryNumber, PropertyDict buildType, String projectName,
			PropertyDict projectCategory, PropertyDict projectSubCategory,
			PropertyDict unitCategory, Double governNumber,
			BigDecimal plannedInvestment, BigDecimal selfFund,
			BigDecimal gapFund, String fromAddress, String toAddress,
			String otherContent, String content) {
		super();
		this.peopleAspiration = peopleAspiration;
		this.beneficiaryNumber = beneficiaryNumber;
		this.buildType = buildType;
		this.projectName = projectName;
		this.projectCategory = projectCategory;
		this.projectSubCategory = projectSubCategory;
		this.unitCategory = unitCategory;
		this.governNumber = governNumber;
		this.plannedInvestment = plannedInvestment;
		this.selfFund = selfFund;
		this.gapFund = gapFund;
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
		this.otherContent = otherContent;
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Double getGovernNumber() {
		return governNumber;
	}

	public void setGovernNumber(Double governNumber) {
		this.governNumber = governNumber;
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

	public PropertyDict getUnitCategory() {
		return unitCategory;
	}

	public void setUnitCategory(PropertyDict unitCategory) {
		this.unitCategory = unitCategory;
	}

}
