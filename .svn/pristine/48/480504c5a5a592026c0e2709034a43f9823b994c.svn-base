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

public class ThreeRecordsUnReadState extends ThreeRecordsAbstractIssueState {

	@Override
	protected String getStateLabel() {
		return "未阅读";
	}

	@Override
	public void read(BaseWorking issue, ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		if (currentStep == null || context == null
				|| context.getTargetOrg() == null) {
			throw new ThreeRecordsIssueOperationException("参数未获得！");
		}
		if (!isSameOrg(getLoginedOrg(context), context.getTargetOrg())) {
			throw new ThreeRecordsIssueOperationException("不能阅读非本部门的事件");
		}
		stepCompleteCurrentStep(currentStep);
	}

	@Override
	public List<ThreeRecordsIssueOperate> getCando(
			ThreeRecordsIssueStepInfo step, ThreeRecordsOrganizationInfo orgInfo) {
		if (step == null || step.getOperationOrg() == null || orgInfo == null
				|| orgInfo.getOrganization() == null) {
			throw new ThreeRecordsIssueOperationException("参数未获得！");
		}
		List<ThreeRecordsIssueOperate> result = new ArrayList<ThreeRecordsIssueOperate>();
		if (step.getOperationOrg().equals(orgInfo.getOrganization())) {
			result.add(ThreeRecordsIssueOperate.READ);
		}
		return result;
	}
}
