package com.tianque.plugin.account.vo;

import java.util.Date;

import com.tianque.plugin.account.domain.LedgerConvert;

@SuppressWarnings("serial")
public class LedgerConvertVo extends LedgerConvert {
	/** 发生时间 从 */
	private Date occurFrom;
	/** 发生时间 到 */
	private Date occurEnd;
	/** 转换时间 从 */
	private Date convertBegin;
	/** 转换时间 到 */
	private Date convertEnd;
	public Date getOccurFrom() {
		return occurFrom;
	}
	public void setOccurFrom(Date occurFrom) {
		this.occurFrom = occurFrom;
	}
	public Date getOccurEnd() {
		return occurEnd;
	}
	public void setOccurEnd(Date occurEnd) {
		this.occurEnd = occurEnd;
	}
	public Date getConvertBegin() {
		return convertBegin;
	}
	public void setConvertBegin(Date convertBegin) {
		this.convertBegin = convertBegin;
	}
	public Date getConvertEnd() {
		return convertEnd;
	}
	public void setConvertEnd(Date convertEnd) {
		this.convertEnd = convertEnd;
	}

}
