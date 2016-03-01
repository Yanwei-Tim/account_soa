package com.tianque.plugin.account.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.PropertyDict;
import com.tianque.plugin.account.vo.LedgerAttachFileReturnVo;

/**
 * 民生诉求表:实体类(PEOPLEASPIRATIONS)
 * 
 */
public class LedgerPeopleAspirations extends BaseWorking {

	/** 反应群体人数(RESPONSEGROUPNO) */
	private Integer responseGroupNo;
	/** 诉求人类别(APPEALHUMANTYPE) */
	private PropertyDict appealHumanType;
	/** 项目类别 区分民生， 稳定， 困难 */
	private PropertyDict category;

	/** 主要愿望或诉求(APPEALCONTENT) */
	private String appealContent;
	private boolean isAnonymity;

	private LedgerAttachFileReturnVo ledgerAttachFileReturnUtil;
	/** 台账中包含的附件集合 用于在页面显示附件 */
	private List<ThreeRecordsIssueAttachFile> issueAttachFiles = new ArrayList<ThreeRecordsIssueAttachFile>();
	/** 台账处办理过程中添加的附件 用于页面显示 */
	private Map<Long, List<ThreeRecordsIssueAttachFile>> issueLogAttachFiles = new HashMap<Long, List<ThreeRecordsIssueAttachFile>>();
	/** 台账的处理记录 用于在页面上显示处理记录 */
	private List<ThreeRecordsIssueLogNew> issueDealLogs;
	/*
	 * 台账反馈信息
	 */
	private List<LedgerFeedBack> feedbacks;
	
	/*
	 * 台账回复信息
	 */
	private List<ReplyForm> replys;


	public LedgerPeopleAspirations() {
	}

	public LedgerPeopleAspirations(Integer responseGroupNo,
			PropertyDict appealHumanType, PropertyDict category,
			String appealContent) {
		super();
		this.responseGroupNo = responseGroupNo;
		this.appealHumanType = appealHumanType;
		this.category = category;
		this.appealContent = appealContent;
	}

	public Integer getResponseGroupNo() {
		return responseGroupNo;
	}

	public void setResponseGroupNo(Integer responseGroupNo) {
		this.responseGroupNo = responseGroupNo;
	}

	public PropertyDict getAppealHumanType() {
		return appealHumanType;
	}

	public void setAppealHumanType(PropertyDict appealHumanType) {
		this.appealHumanType = appealHumanType;
	}

	public PropertyDict getCategory() {
		return category;
	}

	public void setCategory(PropertyDict category) {
		this.category = category;
	}

	public String getAppealContent() {
		return appealContent;
	}

	public void setAppealContent(String appealContent) {
		this.appealContent = appealContent;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(), null, null);
	}

	public LedgerAttachFileReturnVo getLedgerAttachFileReturnUtil() {
		return ledgerAttachFileReturnUtil;
	}

	public void setLedgerAttachFileReturnUtil(
			LedgerAttachFileReturnVo ledgerAttachFileReturnUtil) {
		this.ledgerAttachFileReturnUtil = ledgerAttachFileReturnUtil;
	}

	public boolean isAnonymity() {
		return isAnonymity;
	}

	public void setAnonymity(boolean isAnonymity) {
		this.isAnonymity = isAnonymity;
	}

	public List<ThreeRecordsIssueAttachFile> getIssueAttachFiles() {
		return issueAttachFiles;
	}

	public void setIssueAttachFiles(
			List<ThreeRecordsIssueAttachFile> issueAttachFiles) {
		this.issueAttachFiles = issueAttachFiles;
	}

	public Map<Long, List<ThreeRecordsIssueAttachFile>> getIssueLogAttachFiles() {
		return issueLogAttachFiles;
	}

	public void setIssueLogAttachFiles(
			Map<Long, List<ThreeRecordsIssueAttachFile>> issueLogAttachFiles) {
		this.issueLogAttachFiles = issueLogAttachFiles;
	}

	public List<ThreeRecordsIssueLogNew> getIssueDealLogs() {
		return issueDealLogs;
	}

	public void setIssueDealLogs(List<ThreeRecordsIssueLogNew> issueDealLogs) {
		this.issueDealLogs = issueDealLogs;
	}

	public List<LedgerFeedBack> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<LedgerFeedBack> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public List<ReplyForm> getReplys() {
		return replys;
	}

	public void setReplys(List<ReplyForm> replys) {
		this.replys = replys;
	}

}
