package com.tianque.domain.vo;

import com.tianque.domain.MyContacter;
import com.tianque.domain.Organization;
import com.tianque.domain.SingleContacter;
import com.tianque.domain.User;
import com.tianque.domain.WorkContacter;

public class ContacterVo extends SingleContacter {
	private Organization organization;
	private User owner;
	private User fromUser;

	@Override
	public String getMobile() {
		return this.getMobileNumber();
	}

	public ContacterVo(WorkContacter workContacter) {
		this.setId(workContacter.getId());
		this.setName(workContacter.getName());
		this.setMobileNumber(workContacter.getMobileNumber());
		this.organization = workContacter.getFromUser().getOrganization();
		this.fromUser = workContacter.getFromUser();
		this.setBelongClass(workContacter.getBelongClass());
	}

	public ContacterVo(MyContacter myContacter) {
		this.setId(myContacter.getId());
		this.setName(myContacter.getName());
		this.setMobileNumber(myContacter.getMobileNumber());
		this.setOwner(myContacter.getOwner());
		this.setBelongClass(myContacter.getBelongClass());
	}

	public ContacterVo() {

	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

}
