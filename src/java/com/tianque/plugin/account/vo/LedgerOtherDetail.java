package com.tianque.plugin.account.vo;

import java.io.Serializable;

public class LedgerOtherDetail extends LedgerDetail implements Serializable {
	private Integer projectCategory;//项目类别（山坪塘、饮水工程...）
	public Integer getProjectCategory() {
		return projectCategory;
	}
	public void setProjectCategory(Integer projectCategory) {
		this.projectCategory = projectCategory;
	}
}
