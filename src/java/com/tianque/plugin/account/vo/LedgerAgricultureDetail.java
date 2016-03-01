package com.tianque.plugin.account.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class LedgerAgricultureDetail extends LedgerDetail implements Serializable {
	private Integer projectCategory;//项目类别（山坪塘、饮水工程...）
	private Integer projectSubCategory;
	private Long num;//数量
	private Long trainNumber;//培训次数
	private Long trainPeopleNumber;//培训人数
	private BigDecimal scopeNumber;//规模
	private BigDecimal quantities;//工程量
	public Integer getProjectCategory() {
		return projectCategory;
	}
	public void setProjectCategory(Integer projectCategory) {
		this.projectCategory = projectCategory;
	}
	public Integer getProjectSubCategory() {
		return projectSubCategory;
	}
	public void setProjectSubCategory(Integer projectSubCategory) {
		this.projectSubCategory = projectSubCategory;
	}
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
	public Long getTrainNumber() {
		return trainNumber;
	}
	public void setTrainNumber(Long trainNumber) {
		this.trainNumber = trainNumber;
	}
	public Long getTrainPeopleNumber() {
		return trainPeopleNumber;
	}
	public void setTrainPeopleNumber(Long trainPeopleNumber) {
		this.trainPeopleNumber = trainPeopleNumber;
	}
	public BigDecimal getScopeNumber() {
		return scopeNumber;
	}
	public void setScopeNumber(BigDecimal scopeNumber) {
		this.scopeNumber = scopeNumber;
	}
	public BigDecimal getQuantities() {
		return quantities;
	}
	public void setQuantities(BigDecimal quantities) {
		this.quantities = quantities;
	}
	
}
