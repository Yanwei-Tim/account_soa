package com.tianque.plugin.account.vo;

import java.io.Serializable;
public class LedgerCurrentYearCollectByMonthReportStatisticalVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String columnName;
	private String orgLevel;
	private Integer sums;
	private Integer jan;
	private Integer feb;
	private Integer mar;
	private Integer apr;
	private Integer may;
	private Integer jun;
	private Integer jul;
	private Integer aug;
	private Integer sep;
	private Integer oct;
	private Integer nov;
	private Integer dec;
	public LedgerCurrentYearCollectByMonthReportStatisticalVo(String columnName){
		this.columnName = columnName;
		initData();
	}
	
	public LedgerCurrentYearCollectByMonthReportStatisticalVo(){
		initData();
	}
	
	private void initData(){
		this.sums = 0;
		this.jan = 0;
		this.feb = 0;
		this.mar = 0;
		this.apr = 0;
		this.may = 0;
		this.jun = 0;
		this.jul = 0;
		this.aug = 0;
		this.sep = 0;
		this.oct = 0;
		this.nov = 0;
		this.dec = 0;
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
	public Integer getSums() {
		return sums;
	}
	public void setSums(Integer sums) {
		this.sums = sums;
	}
	public Integer getJan() {
		return jan;
	}
	public void setJan(Integer jan) {
		this.jan = jan;
	}
	public Integer getFeb() {
		return feb;
	}
	public void setFeb(Integer feb) {
		this.feb = feb;
	}
	public Integer getMar() {
		return mar;
	}
	public void setMar(Integer mar) {
		this.mar = mar;
	}
	public Integer getApr() {
		return apr;
	}
	public void setApr(Integer apr) {
		this.apr = apr;
	}
	public Integer getMay() {
		return may;
	}
	public void setMay(Integer may) {
		this.may = may;
	}
	public Integer getJun() {
		return jun;
	}
	public void setJun(Integer jun) {
		this.jun = jun;
	}
	public Integer getJul() {
		return jul;
	}
	public void setJul(Integer jul) {
		this.jul = jul;
	}
	public Integer getAug() {
		return aug;
	}
	public void setAug(Integer aug) {
		this.aug = aug;
	}
	public Integer getSep() {
		return sep;
	}
	public void setSep(Integer sep) {
		this.sep = sep;
	}
	public Integer getOct() {
		return oct;
	}
	public void setOct(Integer oct) {
		this.oct = oct;
	}
	public Integer getNov() {
		return nov;
	}
	public void setNov(Integer nov) {
		this.nov = nov;
	}
	public Integer getDec() {
		return dec;
	}
	public void setDec(Integer dec) {
		this.dec = dec;
	}
	
}
