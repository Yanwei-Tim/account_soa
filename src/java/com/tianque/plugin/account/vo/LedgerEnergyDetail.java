package com.tianque.plugin.account.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class LedgerEnergyDetail extends LedgerDetail implements Serializable {
	private Integer projectCategory;//项目类别（山坪塘、饮水工程...）
	private Integer projectsubcategory;//项目类别
	private Integer securityCategory;//安保设施类型
	private Double mileage;//长度
	private BigDecimal energyNumber;//数量
	private Double capacity;//容积
	private Long securityNum;
	public Integer getProjectCategory() {
		return projectCategory;
	}
	public void setProjectCategory(Integer projectCategory) {
		this.projectCategory = projectCategory;
	}
	public Integer getProjectsubcategory() {
		return projectsubcategory;
	}
	public void setProjectsubcategory(Integer projectsubcategory) {
		this.projectsubcategory = projectsubcategory;
	}
	public Integer getSecurityCategory() {
		return securityCategory;
	}
	public void setSecurityCategory(Integer securityCategory) {
		this.securityCategory = securityCategory;
	}
	public Double getMileage() {
		return mileage;
	}
	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}
	public BigDecimal getEnergyNumber() {
		return energyNumber;
	}
	public void setEnergyNumber(BigDecimal energyNumber) {
		this.energyNumber = energyNumber;
	}
	public Double getCapacity() {
		return capacity;
	}
	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}
	public Long getSecurityNum() {
		return securityNum;
	}
	public void setSecurityNum(Long securityNum) {
		this.securityNum = securityNum;
	}
	
}
