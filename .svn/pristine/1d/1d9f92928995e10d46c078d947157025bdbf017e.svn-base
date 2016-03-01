package com.tianque.plugin.account.vo;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;
import com.tianque.plugin.account.domain.LedgerPoorPeople;

@SuppressWarnings("serial")
public class LedgerPoorPeopleVo extends LedgerPoorPeople {

	private Organization targetOrg;

	private Date lastdealDate;

	private Integer stateCode;

	private Long stepId;

	/** 是否可以反馈 */
	private Integer superviseLevel;

	/** 延期状态 ，默认是未申请 */
	private Integer delayState = 0;

	/** 是否置顶 */
	private Integer topState;

	private String state;

	@JSON(format = "yyyy-MM-dd")
	public Date getLastdealDate() {
		return lastdealDate;
	}

	public void setLastdealDate(Date lastdealDate) {
		this.lastdealDate = lastdealDate;
	}

	public Long getStepId() {
		return stepId;
	}

	public void setStepId(Long stepId) {
		this.stepId = stepId;
	}

	public Integer getSuperviseLevel() {
		return superviseLevel;
	}

	public void setSuperviseLevel(Integer superviseLevel) {
		this.superviseLevel = superviseLevel;
	}

	public Integer getDelayState() {
		return delayState;
	}

	public void setDelayState(Integer delayState) {
		this.delayState = delayState;
	}

	public Integer getTopState() {
		return topState;
	}

	public void setTopState(Integer topState) {
		this.topState = topState;
	}

	public Organization getTargetOrg() {
		return targetOrg;
	}

	public void setTargetOrg(Organization targetOrg) {
		this.targetOrg = targetOrg;
	}

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public String getEncryptIdByIssueStepId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(getStepId(), null, null);
	}

	public String getEncryptIdByIssueId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(), null, null);
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
