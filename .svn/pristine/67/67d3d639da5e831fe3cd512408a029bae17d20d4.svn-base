package com.tianque.plugin.account.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.plugin.account.vo.LedgerAttachFileReturnVo;

/**
 * 回复单
 */
public class ReplyForm extends BaseDomain {
	private Long stepId; // 步骤编号
	private Long ledgerId; // 台账编号
	private Long ledgerType; // 台账类型
	private Organization sourceOrg; // 当前回复的部门ID
	private Organization targetOrg; // 回复目标部门ID
	private String reason; // 回复主要事项及原因
	private String handleContent; // 回复内容摘要
	private String serialNumber; // 回复单编码
	private String ledgerSerialNumber; // 台账编码
	private Date replyDate;// 回复时间
	private String name; // 单位联系人
	private String mobile; // 联系电话
	private LedgerAttachFileReturnVo ledgerAttachFileReturnUtil;
	/** 回复中包含的附件集合 用于在页面显示附件 */
	private List<ThreeRecordsIssueAttachFile> attachFiles = new ArrayList<ThreeRecordsIssueAttachFile>();

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

	public String getLedgerSerialNumber() {
		return ledgerSerialNumber;
	}

	public void setLedgerSerialNumber(String ledgerSerialNumber) {
		this.ledgerSerialNumber = ledgerSerialNumber;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
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

	public LedgerAttachFileReturnVo getLedgerAttachFileReturnUtil() {
		return ledgerAttachFileReturnUtil;
	}

	public void setLedgerAttachFileReturnUtil(
			LedgerAttachFileReturnVo ledgerAttachFileReturnUtil) {
		this.ledgerAttachFileReturnUtil = ledgerAttachFileReturnUtil;
	}

	public List<ThreeRecordsIssueAttachFile> getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(List<ThreeRecordsIssueAttachFile> attachFiles) {
		this.attachFiles = attachFiles;
	}

}
