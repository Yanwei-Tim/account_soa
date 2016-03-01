package com.tianque.plugin.account.vo;

import java.io.Serializable;

public class LedgerScienceDetail extends LedgerDetail implements Serializable {
	private Integer projectCategory;//项目类别（山坪塘、饮水工程...）
	private Integer projectsubcategory;//项目类别
	private Long scienceScope;//数量（数、个、场）
	private Long publicizeNum;//宣传次数
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
	public Long getScienceScope() {
		return scienceScope;
	}
	public void setScienceScope(Long scienceScope) {
		this.scienceScope = scienceScope;
	}
	public Long getPublicizeNum() {
		return publicizeNum;
	}
	public void setPublicizeNum(Long publicizeNum) {
		this.publicizeNum = publicizeNum;
	}
	
}
