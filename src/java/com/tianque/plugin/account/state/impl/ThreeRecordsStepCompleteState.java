package com.tianque.plugin.account.state.impl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.plugin.account.domain.BaseWorking;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperationContext;
import com.tianque.plugin.account.state.ThreeRecordsIssueStepInfo;
import com.tianque.plugin.account.state.ThreeRecordsOrganizationInfo;
import com.tianque.plugin.account.state.exception.ThreeRecordsIssueOperationException;
import com.tianque.plugin.account.state.exception.ThreeRecordsUnsupportOperationException;

public class ThreeRecordsStepCompleteState extends
		ThreeRecordsAbstractIssueState {

	@Override
	protected String getStateLabel() {
		return "本处理环节已经结束";
	}

	@Override
	public List<ThreeRecordsIssueOperate> getCando(
			ThreeRecordsIssueStepInfo step, ThreeRecordsOrganizationInfo orgInfo) {
		List<ThreeRecordsIssueOperate> result = new ArrayList<ThreeRecordsIssueOperate>();
		result.add(ThreeRecordsIssueOperate.TMPCOMMENT);
		return result;
	}

	@Override
	public void tmpComment(BaseWorking issue,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		if (!getLoginedOrg(context).equals(currentStep.getTarget())) {
			throw new ThreeRecordsIssueOperationException("不能办理其他部门的事件");
		}
	}

}
