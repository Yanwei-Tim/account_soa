package com.tianque.plugin.account.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * 台账反馈
 * */
public class LedgerFeedBack extends BaseDomain {
	/** 台账id */
	private Long ledgerId;
	/** 台账类型 */
	private int ledgerType;
	/** 反馈类型 */
	private Integer feedBackType;
	/** 反馈意见 */
	private Integer feedBackOpinion;
	/** 评论的用户 */
	private String evaluateUser;
	private Organization evaluateOrganization;
	private String remark;
	private Long stepId;

	public Long getLedgerId() {
		return ledgerId;
	}

	public void setLedgerId(Long ledgerId) {
		this.ledgerId = ledgerId;
	}

	public int getLedgerType() {
		return ledgerType;
	}

	public void setLedgerType(int ledgerType) {
		this.ledgerType = ledgerType;
	}

	public Integer getFeedBackType() {
		return feedBackType;
	}

	public void setFeedBackType(Integer feedBackType) {
		this.feedBackType = feedBackType;
	}

	public Integer getFeedBackOpinion() {
		return feedBackOpinion;
	}

	public void setFeedBackOpinion(Integer feedBackOpinion) {
		this.feedBackOpinion = feedBackOpinion;
	}

	public String getEvaluateUser() {
		return evaluateUser;
	}

	public void setEvaluateUser(String evaluateUser) {
		this.evaluateUser = evaluateUser;
	}

	public Organization getEvaluateOrganization() {
		return evaluateOrganization;
	}

	public void setEvaluateOrganization(Organization evaluateOrganization) {
		this.evaluateOrganization = evaluateOrganization;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getStepId() {
		return stepId;
	}

	public void setStepId(Long stepId) {
		this.stepId = stepId;
	}

}
