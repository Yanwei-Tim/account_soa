package com.tianque.publicNotice.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;

public class PublicNotice extends BaseDomain {
	/* 所属网格 */
	private Organization organization;
	/* 网格编号 */
	private String orgInternalCode;
	/* 标题 */
	private String publicNoticeTitle;
	/* 用户id */
	private Long userId;
	/* 用户姓名 */
	private String noticeEditor;
	/* 编辑时间 */
	private Date editorDate;
	/* 内容 */
	private String publicNoticeMatter;
	/* 截止日期 */
	private Date overdueDate;
	private Date createDate;
	/* 是否有附件 */
	private Boolean isAttachment;
	private String isoverdate;
	/* 附件 */
	private List<PublicNoticeAttachFiles> noticeFiles = new ArrayList<PublicNoticeAttachFiles>();

	private PublicNoticeObject publicNoticeObject;
	// 状态 已读 未读
	private Date readtime;

	private String publicNoticeInscribe;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getPublicNoticeTitle() {
		return publicNoticeTitle;
	}

	public void setPublicNoticeTitle(String publicNoticeTitle) {
		this.publicNoticeTitle = publicNoticeTitle;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNoticeEditor() {
		return noticeEditor;
	}

	public void setNoticeEditor(String noticeEditor) {
		this.noticeEditor = noticeEditor;
	}

	public String getPublicNoticeMatter() {
		return publicNoticeMatter;
	}

	public void setPublicNoticeMatter(String publicNoticeMatter) {
		this.publicNoticeMatter = publicNoticeMatter;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getOverdueDate() {
		return overdueDate;
	}

	public void setOverdueDate(Date overdueDate) {
		this.overdueDate = overdueDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getEditorDate() {
		return editorDate;
	}

	public void setEditorDate(Date editorDate) {
		this.editorDate = editorDate;
	}

	public Boolean getIsAttachment() {
		return isAttachment;
	}

	public void setIsAttachment(Boolean isAttachment) {
		this.isAttachment = isAttachment;
	}

	public List<PublicNoticeAttachFiles> getNoticeFiles() {
		return noticeFiles;
	}

	public void setNoticeFiles(List<PublicNoticeAttachFiles> noticeFiles) {
		this.noticeFiles = noticeFiles;
	}

	public String getIsoverdate() {
		return isoverdate;
	}

	public void setIsoverdate(String isoverdate) {
		this.isoverdate = isoverdate;
	}

	public PublicNoticeObject getPublicNoticeObject() {
		return publicNoticeObject;
	}

	public void setPublicNoticeObject(PublicNoticeObject publicNoticeObject) {
		this.publicNoticeObject = publicNoticeObject;
	}

	public Date getReadtime() {
		return readtime;
	}

	public void setReadtime(Date readtime) {
		this.readtime = readtime;
	}

	public String getPublicNoticeInscribe() {
		return publicNoticeInscribe;
	}

	public void setPublicNoticeInscribe(String publicNoticeInscribe) {
		this.publicNoticeInscribe = publicNoticeInscribe;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(super.getId(), null,
				null);
	}

}
