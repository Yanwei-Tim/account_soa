package com.tianque.plugin.account.state.impl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.util.GridProperties;
import com.tianque.plugin.account.domain.BaseWorking;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStepGroup;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperationContext;
import com.tianque.plugin.account.state.ThreeRecordsIssueStepInfo;
import com.tianque.plugin.account.state.ThreeRecordsOrganizationInfo;
import com.tianque.plugin.account.state.exception.ThreeRecordsIssueOperationException;
import com.tianque.plugin.account.state.exception.ThreeRecordsUnsupportOperationException;

public class ThreeRecordsNewIssueState extends ThreeRecordsAbstractIssueState {

	@Override
	protected String getStateLabel() {
		return "新建";
	}

	@Override
	public ThreeRecordsIssueStepGroup giveTo(BaseWorking issue,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		if (currentStep == null || currentStep.getTarget() == null
				|| context == null || context.getTargetOrg() == null) {
			throw new ThreeRecordsIssueOperationException("参数未获得！");
		}
		if (!isCommandCenter(currentStep.getTarget())) {
			throw new ThreeRecordsUnsupportOperationException("事件不在指挥中心，不能交办");
		} else if (isCommandCenter(context.getTargetOrg())) {
			throw new ThreeRecordsIssueOperationException("不能将事件交办给指挥中心");
		} else if (notParentOrg(context.getTargetOrg(), currentStep.getTarget())) {
			throw new ThreeRecordsIssueOperationException("不能将事件交办给上级部门");
		}
		ThreeRecordsIssueStepGroup result = new ThreeRecordsIssueStepGroup();
		ThreeRecordsIssueStep step = constructIssueStepAndFillDefaultProperty(
				issue, context.getCurrentLoginedOrg(), context.getTargetOrg());
		if ("true".equals(GridProperties.IS_NEED_UNCONCEPTEDSTATE)) {
			step.setState(ThreeRecordsUnConceptedState.class.getName());
			step.setStateCode(UNCONCEPTED_CODE);
		} else {
			step.setState(ThreeRecordsDealingState.class.getName());
			step.setStateCode(DEALING_CODE);
		}
		stepCompleteCurrentStepAndFillDealTime(currentStep, step);
		result.setKeyStep(step);
		issue.setCurrentStep(step);
		issue.setCurrentOrg(step.getTarget());
		issue.setStatus(DEALING);
		return result;
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
			if (isCommandCenter(orgInfo.getOrganization())) {
				result.add(ThreeRecordsIssueOperate.GIVETO);
			} else {
				result.add(ThreeRecordsIssueOperate.REPORT_TO);
			}
		}
		return result;
	}
}
