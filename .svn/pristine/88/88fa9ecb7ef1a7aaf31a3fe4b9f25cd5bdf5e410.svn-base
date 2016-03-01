package com.tianque.domain;

/**工作联系人 */
public class WorkContacter extends SingleContacter {
	/** 关联用户 */
	private User fromUser;

	public WorkContacter(){
		this.setBelongClass(WORKCONTACTER);
	}
	
	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	@Override
	public String getMobile() {
		return this.getMobileNumber();
	}
	
}
