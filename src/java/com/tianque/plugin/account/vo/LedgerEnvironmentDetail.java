package com.tianque.plugin.account.vo;

import java.io.Serializable;

public class LedgerEnvironmentDetail extends LedgerDetail implements Serializable {
	private Integer projectCategory;//项目类别（山坪塘、饮水工程...）
	private Integer projectSubCategory;
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
}
