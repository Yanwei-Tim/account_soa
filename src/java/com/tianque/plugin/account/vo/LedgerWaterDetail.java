package com.tianque.plugin.account.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class LedgerWaterDetail extends LedgerDetail implements Serializable {
	private Integer projectCategory;//项目类别（山坪塘、饮水工程...）
	private Integer buildType;//建设性质（新建、改建...）
	private Double impoundage;//蓄水量
	private Double scatter;//分散供水量
	private Long centralized;//集中供水处
	private Double mileage;//里程
	private Long num;//数量（口）
	public Integer getProjectCategory() {
		return projectCategory;
	}
	public void setProjectCategory(Integer projectCategory) {
		this.projectCategory = projectCategory;
	}
	public Integer getBuildType() {
		return buildType;
	}
	public void setBuildType(Integer buildType) {
		this.buildType = buildType;
	}
	public Double getImpoundage() {
		return impoundage;
	}
	public void setImpoundage(Double impoundage) {
		this.impoundage = impoundage;
	}
	public Double getScatter() {
		return scatter;
	}
	public void setScatter(Double scatter) {
		this.scatter = scatter;
	}
	public Long getCentralized() {
		return centralized;
	}
	public void setCentralized(Long centralized) {
		this.centralized = centralized;
	}
	public Double getMileage() {
		return mileage;
	}
	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
	
}
