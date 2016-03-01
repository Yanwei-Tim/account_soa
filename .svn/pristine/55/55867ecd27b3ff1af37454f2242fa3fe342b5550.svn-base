package com.tianque.plugin.account.state.impl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.util.CalendarUtil;
import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.domain.BaseWorking;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperationContext;
import com.tianque.plugin.account.state.ThreeRecordsIssueStepInfo;
import com.tianque.plugin.account.state.ThreeRecordsOrganizationInfo;
import com.tianque.plugin.account.state.exception.ThreeRecordsIssueOperationException;
import com.tianque.plugin.account.state.exception.ThreeRecordsUnsupportOperationException;

public class ThreeRecordsUnConceptedState extends
		ThreeRecordsAbstractIssueState {

	@Override
	protected String getStateLabel() {
		return "待受理";
	}

	@Override
	public void concept(BaseWorking issue, ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		if (currentStep == null || context == null
				|| context.getTargetOrg() == null) {
			throw new ThreeRecordsIssueOperationException("参数未获得！");
		}
		if (!isSameOrg(currentStep.getTarget(), context.getTargetOrg())) {
			throw new ThreeRecordsIssueOperationException("不能受理其他部门的事件");
		}
		if (currentStep.getIsSupported() == LedgerConstants.OPERATE_TYPE_SUPPORT) {
			currentStep.setLastDealDate(CalendarUtil.now());
			currentStep.setState(ThreeRecordsSupportingState.class.getName());
			currentStep.setStateCode(SUPPORTING_CODE);
			// issue.setStatus(DEALING);

		} else if (currentStep.getIsSupported() == LedgerConstants.OPERATE_TYPE_NOTICE) {
		} else {
			currentStep.setLastDealDate(CalendarUtil.now());
			currentStep.setState(ThreeRecordsDealingState.class.getName());
			currentStep.setStateCode(DEALING_CODE);
			// issue.setStatus(DEALING);
		}
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
			result.add(ThreeRecordsIssueOperate.CONCEPT);
		}
		if (isHighLevelOrg(orgInfo.getOrganization().getOrgLevel()
				.getInternalId(), step.getOperationOrg().getOrgLevel()
				.getInternalId())) {
			result.add(ThreeRecordsIssueOperate.INSTRUCT);
		}
		return result;
	}

}
