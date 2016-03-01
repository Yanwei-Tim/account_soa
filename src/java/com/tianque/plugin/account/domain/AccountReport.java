package com.tianque.plugin.account.domain;

import java.util.List;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class AccountReport extends BaseDomain {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 所属网格 */
	private Organization organization;
	/** 网格内置编码 */
	private String orgInternalCode;
	private Integer orgLevelInternalId;
	/** 统计的年份 */
	private Integer reportYear;
	/** 统计的月份 */
	private Integer reportMonth;
	/** 台账明细 */
	private String content;
	private Object contentObject;
	/** 台账类型 */
	private Integer accountType;
	/** 报表类型 */
	private Integer reportType;
	/** 首页报表查看类型: 1村级， 2镇级，3县部门，县级（所有） 向下包含 */
	private Integer homePageViewType;

	private List<String> showLine;

	private List<PropertyDict> itemDicts;

	public AccountReport() {
		super();
	}

	public AccountReport(Organization organization, String orgInternalCode,
			Integer reportYear, Integer reportMonth, String content,
			Integer accountType, Integer reportType, Integer homePageViewType) {
		super();
		this.organization = organization;
		this.orgInternalCode = orgInternalCode;
		this.reportYear = reportYear;
		this.reportMonth = reportMonth;
		this.content = content;
		this.accountType = accountType;
		this.reportType = reportType;
		this.homePageViewType = homePageViewType;
	}

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

	public Integer getReportYear() {
		return reportYear;
	}

	public void setReportYear(Integer reportYear) {
		this.reportYear = reportYear;
	}

	public Integer getReportMonth() {
		return reportMonth;
	}

	public void setReportMonth(Integer reportMonth) {
		this.reportMonth = reportMonth;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Integer getReportType() {
		return reportType;
	}

	public void setReportType(Integer reportType) {
		this.reportType = reportType;
	}

	public Object getContentObject() {
		return contentObject;
	}

	public void setContentObject(Object contentObject) {
		this.contentObject = contentObject;
	}

	public List<String> getShowLine() {
		return showLine;
	}

	public void setShowLine(List<String> showLine) {
		this.showLine = showLine;
	}

	public List<PropertyDict> getItemDicts() {
		return itemDicts;
	}

	public void setItemDicts(List<PropertyDict> itemDicts) {
		this.itemDicts = itemDicts;
	}

	public Integer getOrgLevelInternalId() {
		return orgLevelInternalId;
	}

	public void setOrgLevelInternalId(Integer orgLevelInternalId) {
		this.orgLevelInternalId = orgLevelInternalId;
	}

	public Integer getHomePageViewType() {
		return homePageViewType;
	}

	public void setHomePageViewType(Integer homePageViewType) {
		this.homePageViewType = homePageViewType;
	}

}
