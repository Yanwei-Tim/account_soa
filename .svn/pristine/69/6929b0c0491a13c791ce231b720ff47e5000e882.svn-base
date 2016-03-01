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

public class ThreeRecordsSupportingState extends ThreeRecordsAbstractIssueState {

	@Override
	protected String getStateLabel() {
		return "添加措施";
	}

	@Override
	public void support(BaseWorking issue, ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		if (!isSameOrg(getLoginedOrg(context), context.getTargetOrg())) {
			throw new ThreeRecordsIssueOperationException("不能协助办理非本部门的事件");
		}
		stepCompleteCurrentStep(currentStep);
	}

	@Override
	public List<ThreeRecordsIssueOperate> getCando(
			ThreeRecordsIssueStepInfo step, ThreeRecordsOrganizationInfo orgInfo) {
		List<ThreeRecordsIssueOperate> result = new ArrayList<ThreeRecordsIssueOperate>();
		if (step.getOperationOrg().equals(orgInfo.getOrganization())) {
			result.add(ThreeRecordsIssueOperate.READ);
		}
		return result;
	}

	@Override
	public void tmpComment(BaseWorking issue,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		if (currentStep == null || currentStep.getTarget() == null
				|| context == null || context.getTargetOrg() == null) {
			throw new ThreeRecordsIssueOperationException("参数未获得！");
		}
		if (!getLoginedOrg(context).equals(currentStep.getTarget())) {
			throw new ThreeRecordsIssueOperationException("不能办理其他部门的事件");
		}
	}

}
