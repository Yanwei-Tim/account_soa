package com.tianque.plugin.account.state;

import com.tianque.domain.Organization;

public class ThreeRecordsIssueStepInfo {

	private Organization operationOrg;

	private int superviseLevel;

	private boolean isContinue;

	public Organization getOperationOrg() {
		return operationOrg;
	}

	public void setOperationOrg(Organization operationOrg) {
		this.operationOrg = operationOrg;
	}

	public int getSuperviseLevel() {
		return superviseLevel;
	}

	public void setSuperviseLevel(int superviseLevel) {
		this.superviseLevel = superviseLevel;
	}

	public boolean isContinue() {
		return isContinue;
	}

	public void setContinue(boolean isContinue) {
		this.isContinue = isContinue;
	}

}
