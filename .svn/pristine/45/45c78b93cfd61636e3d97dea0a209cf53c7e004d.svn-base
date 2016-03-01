package com.tianque.domain;

import com.tianque.core.base.BaseDomain;

@SuppressWarnings("serial")
public class IssueType extends BaseDomain {
	private Long id;
	private IssueTypeDomain issueTypeDomain;
	private int internalId;
	private String issueTypeName;
	private int indexId;
	private String content;
	private String simplePinYin;
	private String fullPinYin;
	private Organization org;
	private String orgInternalCode;
	private boolean personalized;
	private String orgName;

	private boolean enabled;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public boolean isPersonalized() {
		return personalized;
	}

	public void setPersonalized(boolean personalized) {
		this.personalized = personalized;
	}

	public int getInternalId() {
		return internalId;
	}

	public void setInternalId(int internalId) {
		this.internalId = internalId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public IssueTypeDomain getIssueTypeDomain() {
		return issueTypeDomain;
	}

	public void setIssueTypeDomain(IssueTypeDomain issueTypeDomain) {
		this.issueTypeDomain = issueTypeDomain;
	}

	public String getIssueTypeName() {
		return issueTypeName;
	}

	public void setIssueTypeName(String issueTypeName) {
		this.issueTypeName = issueTypeName;
	}

	public int getIndexId() {
		return indexId;
	}

	public void setIndexId(int indexId) {
		this.indexId = indexId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSimplePinYin() {
		return simplePinYin;
	}

	public void setSimplePinYin(String simplePinYin) {
		if (simplePinYin != null && simplePinYin.length() > 10) {
			simplePinYin = simplePinYin.substring(0, 10);
		}
		this.simplePinYin = simplePinYin;
	}

	public String getFullPinYin() {
		return fullPinYin;
	}

	public void setFullPinYin(String fullPinYin) {
		if (fullPinYin != null && fullPinYin.length() > 30) {
			fullPinYin = fullPinYin.substring(0, 30);
		}
		this.fullPinYin = fullPinYin;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
