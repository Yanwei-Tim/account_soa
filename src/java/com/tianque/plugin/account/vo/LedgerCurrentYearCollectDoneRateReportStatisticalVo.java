package com.tianque.plugin.account.vo;

import java.io.Serializable;
public class LedgerCurrentYearCollectDoneRateReportStatisticalVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String columnName;
	private String orgLevel;
	private Integer collectCount;
	private Integer doneCount;
	private String rate;
	public LedgerCurrentYearCollectDoneRateReportStatisticalVo(String columnName){
		this.columnName = columnName;
		initData();
	}
	
	public LedgerCurrentYearCollectDoneRateReportStatisticalVo(){
		initData();
	}
	
	private void initData(){
		this.collectCount = 0;
		this.doneCount = 0;
		this.rate = "0";
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getOrgLevel() {
		return orgLevel;
	}
	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

	public Integer getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}

	public Integer getDoneCount() {
		return doneCount;
	}

	public void setDoneCount(Integer doneCount) {
		this.doneCount = doneCount;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
	
}
