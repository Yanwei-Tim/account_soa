package com.tianque.plugin.account.domain;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.base.BaseDomain;

/****
 * 事件处理步骤组 ：
 */

@SuppressWarnings("serial")
public class ThreeRecordsIssueStepGroup extends BaseDomain {
	/** 当前处理步骤 */
	private ThreeRecordsIssueStep keyStep;
	/** 协办 */
	private List<ThreeRecordsIssueStep> incidentalSteps;

	private ThreeRecordsIssueLogNew entyLog;

	private ThreeRecordsIssueLogNew outLog;

	/** 台 账类型 **/
	private int ledgerType;
	/*
	 * 台账编号
	 */
	private Long ledgerId;

	public ThreeRecordsIssueStep getKeyStep() {
		return keyStep;
	}

	public void setKeyStep(ThreeRecordsIssueStep keyStep) {
		this.keyStep = keyStep;
	}

	public List<ThreeRecordsIssueStep> getIncidentalSteps() {
		return incidentalSteps;
	}

	public void setIncidentalSteps(List<ThreeRecordsIssueStep> incidentalSteps) {
		this.incidentalSteps = incidentalSteps;
	}

	public boolean hasIncidentalStep() {
		return incidentalSteps != null && incidentalSteps.size() > 0;
	}

	public void addIncidentalStep(ThreeRecordsIssueStep step) {
		if (incidentalSteps == null) {
			incidentalSteps = new ArrayList<ThreeRecordsIssueStep>();
		}
		incidentalSteps.add(step);
	}

	public ThreeRecordsIssueLogNew getEntyLog() {
		return entyLog;
	}

	public void setEntyLog(ThreeRecordsIssueLogNew entyLog) {
		this.entyLog = entyLog;
	}

	public ThreeRecordsIssueLogNew getOutLog() {
		return outLog;
	}

	public void setOutLog(ThreeRecordsIssueLogNew outLog) {
		this.outLog = outLog;
	}

	public int getLedgerType() {
		return ledgerType;
	}

	public void setLedgerType(int ledgerType) {
		this.ledgerType = ledgerType;
	}

	public Long getLedgerId() {
		return ledgerId;
	}

	public void setLedgerId(Long ledgerId) {
		this.ledgerId = ledgerId;
	}

}
