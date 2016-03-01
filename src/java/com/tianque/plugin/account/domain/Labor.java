package com.tianque.plugin.account.domain;

import java.math.BigDecimal;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.PropertyDict;

public class Labor extends BaseDomain  {

	private LedgerPeopleAspirations peopleAspiration;
	/*
	 * 涉及人数
	 */
	private Long relationNumber;

	private String projectName;
	/*
	 * 项目类别
	 */
	private PropertyDict projectCategory;

	private PropertyDict projectSubCategory;

	private PropertyDict projectSubContentCategory;

	/*
	 * 工资拖欠施工单位
	 */
	private String company;
	/*
	 * 工资拖欠施工单位地址
	 */
	private String yawnAddr;
	/*
	 * 工资拖欠施工单位联系人姓名
	 */
	private String yawnContactor;
	/*
	 * 联系电话
	 */
	private String yawnMobile;

	private BigDecimal money;

	private String skill;
	private PropertyDict degree;
	private PropertyDict ageLevel;
	/*
	 * 残疾等级
	 */
	private PropertyDict crippleLevel;
	/*
	 * 身份性质
	 */
	private PropertyDict dignity;

	private String fromAddress;
	private String toAddress;
	private String otherContent;

	public String getYawnAddr() {
		return yawnAddr;
	}

	public void setYawnAddr(String yawnAddr) {
		this.yawnAddr = yawnAddr;
	}

	public String getYawnContactor() {
		return yawnContactor;
	}

	public void setYawnContactor(String yawnContactor) {
		this.yawnContactor = yawnContactor;
	}

	public String getYawnMobile() {
		return yawnMobile;
	}

	public void setYawnMobile(String yawnMobile) {
		this.yawnMobile = yawnMobile;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public PropertyDict getDegree() {
		return degree;
	}

	public void setDegree(PropertyDict degree) {
		this.degree = degree;
	}

	public PropertyDict getAgeLevel() {
		return ageLevel;
	}

	public void setAgeLevel(PropertyDict ageLevel) {
		this.ageLevel = ageLevel;
	}

	public PropertyDict getCrippleLevel() {
		return crippleLevel;
	}

	public void setCrippleLevel(PropertyDict crippleLevel) {
		this.crippleLevel = crippleLevel;
	}

	public PropertyDict getDignity() {
		return dignity;
	}

	public void setDignity(PropertyDict dignity) {
		this.dignity = dignity;
	}

	public Labor() {
		super();
	}

	public Labor(LedgerPeopleAspirations peopleAspiration, Long relationNumber,
			String projectName, PropertyDict projectCategory,
			PropertyDict projectSubCategory,
			PropertyDict projectSubContentCategory, String company,
			String yawnAddr, String yawnContactor, String yawnMobile,
			BigDecimal money, String skill, PropertyDict degree,
			PropertyDict ageLevel, PropertyDict crippleLevel,
			PropertyDict dignity, String fromAddress, String toAddress,
			String otherContent) {
		super();
		this.peopleAspiration = peopleAspiration;
		this.relationNumber = relationNumber;
		this.projectName = projectName;
		this.projectCategory = projectCategory;
		this.projectSubCategory = projectSubCategory;
		this.projectSubContentCategory = projectSubContentCategory;
		this.company = company;
		this.yawnAddr = yawnAddr;
		this.yawnContactor = yawnContactor;
		this.yawnMobile = yawnMobile;
		this.money = money;
		this.skill = skill;
		this.degree = degree;
		this.ageLevel = ageLevel;
		this.crippleLevel = crippleLevel;
		this.dignity = dignity;
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
		this.otherContent = otherContent;
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

	public Long getRelationNumber() {
		return relationNumber;
	}

	public void setRelationNumber(Long relationNumber) {
		this.relationNumber = relationNumber;
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

	public PropertyDict getProjectSubContentCategory() {
		return projectSubContentCategory;
	}

	public void setProjectSubContentCategory(
			PropertyDict projectSubContentCategory) {
		this.projectSubContentCategory = projectSubContentCategory;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
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

}
