package com.tianque.plugin.account.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * 申报单
 */
public class DeclareForm extends BaseDomain {
	private Long stepId; // 步骤编号
	private Long ledgerId; // 台账编号
	private Long ledgerType; // 台账类型
	private Organization sourceOrg; // 当前申报的部门ID
	private Organization targetOrg; // 上级部门ID
	private String reason; // 申报主要事项及原因
	private String handleContent; // 申报单位领导意见
	private String serialNumber; // 申报单编码
	private String name; // 申报单位联系人
	private String mobile; // 联系电话
	private String ledgerSerialNumber; // 台账编码
	private String ledgerHandleContent; // 县台账办拟办意见
	private String jointContent;// 联席会议召集人审核意见
	private Date declareDate;// 申报时间

	public Long getStepId() {
		return stepId;
	}

	public void setStepId(Long stepId) {
		this.stepId = stepId;
	}

	public Long getLedgerId() {
		return ledgerId;
	}

	public void setLedgerId(Long ledgerId) {
		this.ledgerId = ledgerId;
	}

	public Long getLedgerType() {
		return ledgerType;
	}

	public void setLedgerType(Long ledgerType) {
		this.ledgerType = ledgerType;
	}

	public Organization getSourceOrg() {
		return sourceOrg;
	}

	public void setSourceOrg(Organization sourceOrg) {
		this.sourceOrg = sourceOrg;
	}

	public Organization getTargetOrg() {
		return targetOrg;
	}

	public void setTargetOrg(Organization targetOrg) {
		this.targetOrg = targetOrg;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getHandleContent() {
		return handleContent;
	}

	public void setHandleContent(String handleContent) {
		this.handleContent = handleContent;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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

	public String getLedgerSerialNumber() {
		return ledgerSerialNumber;
	}

	public void setLedgerSerialNumber(String ledgerSerialNumber) {
		this.ledgerSerialNumber = ledgerSerialNumber;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public String getLedgerHandleContent() {
		return ledgerHandleContent;
	}

	public Date getDeclareDate() {
		return declareDate;
	}

	public void setDeclareDate(Date declareDate) {
		this.declareDate = declareDate;
	}

	public void setLedgerHandleContent(String ledgerHandleContent) {
		this.ledgerHandleContent = ledgerHandleContent;
	}

	public String getJointContent() {
		return jointContent;
	}

	public void setJointContent(String jointContent) {
		this.jointContent = jointContent;
	}

}
