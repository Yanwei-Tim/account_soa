package com.tianque.plugin.account.vo;

import java.io.Serializable;

public class ThreeRecordsReportVo implements Serializable {
	private long orgLevel;
	private long ledgerType;
	private Integer count;
	public long getOrgLevel() {
		return orgLevel;
	}
	public void setOrgLevel(long orgLevel) {
		this.orgLevel = orgLevel;
	}
	public long getLedgerType() {
		return ledgerType;
	}
	public void setLedgerType(long ledgerType) {
		this.ledgerType = ledgerType;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
}
