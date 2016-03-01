package com.tianque.domain;

/**我的联系人 */
public class MyContacter extends SingleContacter {
	/** 所属用户 */
	private User owner;

	public MyContacter(){
		this.setBelongClass(MYCONTACTER);
	}
	
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
	public String getMobile() {
		return this.getMobileNumber();
	}
	
}
