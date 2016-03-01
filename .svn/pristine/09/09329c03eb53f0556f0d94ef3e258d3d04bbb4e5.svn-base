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

public class ThreeRecordsIssueCompleteState extends
		ThreeRecordsAbstractIssueState {

	@Override
	protected String getStateLabel() {
		return "已完成待验证";
	}

	@Override
	public List<ThreeRecordsIssueOperate> getCando(
			ThreeRecordsIssueStepInfo step, ThreeRecordsOrganizationInfo orgInfo) {
		if (step == null || step.getOperationOrg() == null
				|| step.getOperationOrg().getOrgLevel() == null
				|| orgInfo == null || orgInfo.getOrganization() == null
				|| orgInfo.getOrganization().getOrgLevel() == null) {
			throw new ThreeRecordsIssueOperationException("参数未获得！");
		}
		List<ThreeRecordsIssueOperate> result = new ArrayList<ThreeRecordsIssueOperate>();
		result.add(ThreeRecordsIssueOperate.VERIFICATION);
		result.add(ThreeRecordsIssueOperate.BACK);
		return result;
	}

	@Override
	public ThreeRecordsIssueStepGroup back(BaseWorking issue,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		if (issue == null || issue.getCreateOrg() == null
				|| currentStep == null || currentStep.getBackTo() == null) {
			throw new ThreeRecordsIssueOperationException("参数未获得！");
		}
		if (!getLoginedOrg(context).equals(issue.getCreateOrg())) {
			throw new ThreeRecordsIssueOperationException("不能回退其他部门的事件");
		}
		if (currentStep.getBackTo() == null) {
			throw new ThreeRecordsIssueOperationException("事件处于第一个处理环节，不能回退");
		}
		return backIssueToSourceStep(issue, currentStep, context);
	}

	private ThreeRecordsIssueStepGroup backIssueToSourceStep(BaseWorking issue,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context) {
		ThreeRecordsIssueStepGroup result = new ThreeRecordsIssueStepGroup();
		ThreeRecordsIssueStep keyStep = constructIssueStepAndFillDefaultProperty(
				issue, getLoginedOrg(context), currentStep.getBackTo()
						.getTarget());
		keyStep.setBackTo(currentStep.getBackTo().getBackTo());
		if ("true".equals(GridProperties.IS_NEED_UNCONCEPTEDSTATE)) {
			// 如果不是在本级那么设置为待受理
			keyStep.setState(ThreeRecordsUnConceptedState.class.getName());
			keyStep.setStateCode(UNCONCEPTED_CODE);
		} else {
			// 如果是在本级则设置为待 办
			keyStep.setState(ThreeRecordsIssueCompleteState.class.getName());
			keyStep.setStateCode(DEALING_CODE);
		}
		stepCompleteCurrentStepAndFillDealTime(currentStep, keyStep);
		result.setKeyStep(keyStep);
		issue.setCurrentStep(keyStep);
		issue.setCurrentOrg(keyStep.getTarget());
		issue.setStatus(DEALING);
		// currentStep.setSuperviseLevel(NO_SUPERVISE);
		return result;
	}

}