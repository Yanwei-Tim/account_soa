package com.tianque.plugin.account.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * 交办单接件人
 */
public class AssignFormReceiver extends BaseDomain {
	private Long assignId; // 交办单编号
	private Long stepId;// 步骤编号
	private String name; // 责任单位联系人
	private String mobile; // 责任联系电话
	private Organization targetOrg; // 主要负责部门
	private Date receiveDate;// 接件时间
	private String manager; // 责任人
	private Boolean isManage;// 是否是主办单位

	public Long getAssignId() {
		return assignId;
	}

	public void setAssignId(Long assignId) {
		this.assignId = assignId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Organization getTargetOrg() {
		return targetOrg;
	}

	public void setTargetOrg(Organization targetOrg) {
		this.targetOrg = targetOrg;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Boolean getIsManage() {
		return isManage;
	}

	public void setIsManage(Boolean isManage) {
		this.isManage = isManage;
	}

	public Long getStepId() {
		return stepId;
	}

	public void setStepId(Long stepId) {
		this.stepId = stepId;
	}

}
