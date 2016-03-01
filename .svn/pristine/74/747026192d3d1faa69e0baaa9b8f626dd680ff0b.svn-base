package com.tianque.plugin.account.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

public class ThreeRecordsTopIssue extends BaseDomain {

	public ThreeRecordsTopIssue() {

	}

	public ThreeRecordsTopIssue(Long issueId, int issueTag, Long orgId) {
		this.issueId = issueId;
		this.issueTag = issueTag;
		this.orgId = orgId;
	}

	public static final int TOP = 1;

	public static final int CANCEL_TOP = 0;

	/** 台账id **/
	private Long issueId;
	/** 台账分类 **/
	private int issueTag;
	/** 置顶网格id **/
	private Long orgId;
	/** 置顶状态 **/
	private int topState;
	/** 置顶时间 **/
	private Date topDate;

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public int getIssueTag() {
		return issueTag;
	}

	public void setIssueTag(int issueTag) {
		this.issueTag = issueTag;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public int getTopState() {
		return topState;
	}

	public void setTopState(int topState) {
		this.topState = topState;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getTopDate() {
		return topDate;
	}

	public void setTopDate(Date topDate) {
		this.topDate = topDate;
	}

	// public String getEncryptId() {
	// return BaseDomainIdEncryptUtil
	// .encryptDomainId(this.issueId, null, null);
	// }

}
