package com.tianque.plugin.account.domain;

import com.tianque.core.base.BaseDomain;

public class AccountKeyInfo extends BaseDomain {

	private String name;
	private long curNum;
	private int step;
	private String remark;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCurNum() {
		return curNum;
	}

	public void setCurNum(long curNum) {
		this.curNum = curNum;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
