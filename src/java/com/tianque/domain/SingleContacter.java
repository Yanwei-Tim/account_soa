package com.tianque.domain;

public abstract class SingleContacter extends Contacter {

	/** 联系手机*/
	private String mobileNumber;
	/** 备注*/
	private String remark;
	/***/
	private String userName;
	
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public abstract String getMobile();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
