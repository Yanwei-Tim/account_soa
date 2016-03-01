package com.tianque.publicNotice.domain;

import com.tianque.core.base.BaseDomain;

public class PublicNoticeAttachFiles extends BaseDomain {
	/* 通知通告id */
	private Long noticeId;
	/* 通知通告附件名 */
	private String fileName;
	/* 通知通告附件URL */
	private String fileActualUrl;

	public Long getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
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

}
