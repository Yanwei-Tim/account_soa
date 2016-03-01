package com.tianque.plugin.account.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * 转办单
 */
public class TurnForm extends BaseDomain {
	private Long stepId; // 步骤编号
	private Long ledgerId; // 台账编号
	private Long ledgerType; // 台账类型
	private Organization targetOrg; // 转办部门ID
	private String reason; // 转办主要事项及原因
	private String serialNumber; // 转办单编码
	private String name; // 承办单位联系人
	private String mobile; // 联系电话
	private String manager;// 承办责任人
	private String ledgerSerialNumber; // 台账编码
	private String subOpinion; // 转办单位分管领导意 见
	private String opinion;// 转办单位主要领导意 见
	private Date turnDate;// 转办时间
	private Date handleStartDate;// 办理起始时间
	private Date handleEndDate;// 办理结束时间
	private Date receiveDate;// 接件时间
	private String handleResult;// 办理结果

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

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getLedgerSerialNumber() {
		return ledgerSerialNumber;
	}

	public void setLedgerSerialNumber(String ledgerSerialNumber) {
		this.ledgerSerialNumber = ledgerSerialNumber;
	}

	public String getSubOpinion() {
		return subOpinion;
	}

	public void setSubOpinion(String subOpinion) {
		this.subOpinion = subOpinion;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getTurnDate() {
		return turnDate;
	}

	public void setTurnDate(Date turnDate) {
		this.turnDate = turnDate;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getHandleStartDate() {
		return handleStartDate;
	}

	public void setHandleStartDate(Date handleStartDate) {
		this.handleStartDate = handleStartDate;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getHandleEndDate() {
		return handleEndDate;
	}

	public void setHandleEndDate(Date handleEndDate) {
		this.handleEndDate = handleEndDate;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getHandleResult() {
		return handleResult;
	}

	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}

}
