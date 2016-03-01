package com.tianque.domain;

import java.util.List;

/** 我的群组 */
public class MyGroup extends MultipleContacter {
	/** 所属用户 */
	private User owner;
	/** 备注*/
	private String remark;
	/** 拥有的联系人*/
	List<SingleContacter> singleContacters;
	
	@Override
	public String getMobile() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public MyGroup(){
		this.setBelongClass(MYGROUP);
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<SingleContacter> getSingleContacters() {
		return singleContacters;
	}

	public void setSingleContacters(List<SingleContacter> singleContacters) {
		this.singleContacters = singleContacters;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
