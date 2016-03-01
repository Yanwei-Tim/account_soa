package com.tianque.plugin.account.domain;

import com.tianque.domain.PropertyDict;

/** 三本台账报表统计辅助类 */
public class LedgerReportGroupCount {

	/** 分组行：（建表类型） */
	private PropertyDict rowType;
	private String items;
	/** 统计数 */
	private Integer count;
	/** 状态 */
	private Integer stateCode;
	/** 台账类型 */
	private Integer ledgerType;
	/** 分组列：（统计大类） */
	private PropertyDict item;

	public PropertyDict getRowType() {
		return rowType;
	}

	public void setRowType(PropertyDict rowType) {
		this.rowType = rowType;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public Integer getLedgerType() {
		return ledgerType;
	}

	public void setLedgerType(Integer ledgerType) {
		this.ledgerType = ledgerType;
	}

	public PropertyDict getItem() {
		return item;
	}

	public void setItem(PropertyDict item) {
		this.item = item;
	}
}
