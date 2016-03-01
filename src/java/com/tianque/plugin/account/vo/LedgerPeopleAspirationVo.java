package com.tianque.plugin.account.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.plugin.account.domain.LedgerPeopleAspirations;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;

@SuppressWarnings("serial")
public class LedgerPeopleAspirationVo implements Serializable {
	/*
	 * 民生信息
	 */
	private LedgerPeopleAspirations peopleAspirations;
	/** 台账中包含的附件集合 用于在页面显示附件 */
	private List<ThreeRecordsIssueAttachFile> issueAttachFiles = new ArrayList<ThreeRecordsIssueAttachFile>();
	/** 台账处办理过程中添加的附件 用于页面显示 */
	private Map<Long, List<ThreeRecordsIssueAttachFile>> issueLogAttachFiles = new HashMap<Long, List<ThreeRecordsIssueAttachFile>>();
	/** 台账的处理记录 用于在页面上显示处理记录 */
	private List<ThreeRecordsIssueLogNew> issueDealLogs;

	public LedgerPeopleAspirations getPeopleAspirations() {
		return peopleAspirations;
	}

	public void setPeopleAspirations(LedgerPeopleAspirations peopleAspirations) {
		this.peopleAspirations = peopleAspirations;
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

}
