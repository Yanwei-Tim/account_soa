package com.tianque.plugin.account.domain;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.tianque.core.base.BaseDomain;

public class ThreeRecordsIssueAttachFile extends BaseDomain {

	/** 附件文件名称 */
	private String fileName;
	/** 附件文件的路径 */
	private String fileActualUrl;
	/** 附件所属的台账 */
	/** 附件所属的台账办理记录 (在台账办理过程中添加的附件) */
	private ThreeRecordsIssueLogNew issueLog;
	/*
	 * 回复单
	 */
	private ReplyForm replyForm;

	/** 台 账类型 **/
	private int ledgerType;
	/*
	 * 台账编号
	 */
	private Long ledgerId;
	private String fileType;

	public ThreeRecordsIssueLogNew getIssueLog() {
		return issueLog;
	}

	public void setIssueLog(ThreeRecordsIssueLogNew issueLog) {
		this.issueLog = issueLog;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileActualUrl() {
		return fileActualUrl;
	}

	public void setFileActualUrl(String fileActualUrl) {
		this.fileActualUrl = fileActualUrl;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ThreeRecordsIssueAttachFile)) {
			return false;
		}
		if (!((getClass().isAssignableFrom(obj.getClass())) || (obj.getClass()
				.isAssignableFrom(getClass())))) {
			return false;
		}

		ThreeRecordsIssueAttachFile file = (ThreeRecordsIssueAttachFile) obj;

		if (getId() == null || file.getId() == null) {
			return false;
		}

		return getId().equals(file.getId());

	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(19999, 88).toHashCode();
	}

	public int getLedgerType() {
		return ledgerType;
	}

	public void setLedgerType(int ledgerType) {
		this.ledgerType = ledgerType;
	}

	public Long getLedgerId() {
		return ledgerId;
	}

	public void setLedgerId(Long ledgerId) {
		this.ledgerId = ledgerId;
	}

	public ReplyForm getReplyForm() {
		return replyForm;
	}

	public void setReplyForm(ReplyForm replyForm) {
		this.replyForm = replyForm;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}
