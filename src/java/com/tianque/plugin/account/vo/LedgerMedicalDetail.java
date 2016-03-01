package com.tianque.plugin.account.vo;

import java.io.Serializable;

public class LedgerMedicalDetail extends LedgerDetail implements Serializable {
	private Integer projectCategory;//项目类别（山坪塘、饮水工程...）
	private Long equipment;//设备（套、台）
	private Double buildArea;//建设工程量
	public Integer getProjectCategory() {
		return projectCategory;
	}
	public void setProjectCategory(Integer projectCategory) {
		this.projectCategory = projectCategory;
	}
	public Long getEquipment() {
		return equipment;
	}
	public void setEquipment(Long equipment) {
		this.equipment = equipment;
	}
	public Double getBuildArea() {
		return buildArea;
	}
	public void setBuildArea(Double buildArea) {
		this.buildArea = buildArea;
	}
	
}
