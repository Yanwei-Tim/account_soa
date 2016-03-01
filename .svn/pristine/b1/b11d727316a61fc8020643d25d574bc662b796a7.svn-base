package com.tianque.plugin.account.state.impl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.Organization;
import com.tianque.plugin.account.domain.BaseWorking;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStepGroup;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperationContext;
import com.tianque.plugin.account.state.ThreeRecordsIssueState;
import com.tianque.plugin.account.state.ThreeRecordsIssueStepInfo;
import com.tianque.plugin.account.state.ThreeRecordsOrganizationInfo;
import com.tianque.plugin.account.state.exception.ThreeRecordsIssueOperationException;
import com.tianque.plugin.account.state.exception.ThreeRecordsUnsupportOperationException;

public abstract class ThreeRecordsAbstractIssueState implements
		ThreeRecordsIssueState {

	protected abstract String getStateLabel();

	@Override
	public ThreeRecordsIssueStepGroup assign(BaseWorking issue,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		throw new ThreeRecordsUnsupportOperationException(getStateLabel()
				+ "下不能进行" + ThreeRecordsIssueOperate.ASSIGN);
	}

	@Override
	public void comment(BaseWorking issue, ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		throw new ThreeRecordsUnsupportOperationException(getStateLabel()
				+ "下不能进行" + ThreeRecordsIssueOperate.COMMENT);
	}

	@Override
	public ThreeRecordsIssueStepGroup complete(BaseWorking issue,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		throw new ThreeRecordsUnsupportOperationException(getStateLabel()
				+ "下不能进行" + ThreeRecordsIssueOperate.COMPLETE);
	}

	@Override
	public ThreeRecordsIssueStepGroup periodComplete(BaseWorking issue,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		throw new ThreeRecordsUnsupportOperationException(getStateLabel()
				+ "下不能进行" + ThreeRecordsIssueOperate.PERIOD_COMPLETE);
	}

	@Override
	public ThreeRecordsIssueStepGroup programComplete(BaseWorking issue,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		throw new ThreeRecordsUnsupportOperationException(getStateLabel()
				+ "下不能进行" + ThreeRecordsIssueOperate.PROGRAM_COMPLETE);
	}

	@Override
	public void concept(BaseWorking issue, ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		throw new ThreeRecordsUnsupportOperationException(getStateLabel()
				+ "下不能进行" + ThreeRecordsIssueOperate.CONCEPT);
	}

	@Override
	public void read(BaseWorking issue, ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		throw new ThreeRecordsUnsupportOperationException(getStateLabel()
				+ "下不能进行" + ThreeRecordsIssueOperate.READ);
	}

	@Override
	public ThreeRecordsIssueStep reportTo(BaseWorking issue,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		throw new ThreeRecordsUnsupportOperationException(getStateLabel()
				+ "下不能进行" + ThreeRecordsIssueOperate.REPORT_TO);
	}

	@Override
	public ThreeRecordsIssueStepGroup submit(BaseWorking issue,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		throw new ThreeRecordsUnsupportOperationException(getStateLabel()
				+ "下不能进行" + ThreeRecordsIssueOperate.SUBMIT);
	}

	@Override
	public ThreeRecordsIssueStepGroup giveTo(BaseWorking issue,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		throw new ThreeRecordsUnsupportOperationException(getStateLabel()
				+ "下不能进行" + ThreeRecordsIssueOperate.GIVETO);
	}

	@Override
	public ThreeRecordsIssueStepGroup back(BaseWorking issue,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		throw new ThreeRecordsUnsupportOperationException(getStateLabel()
				+ "下不能进行" + ThreeRecordsIssueOperate.BACK);
	}

	@Override
	public List<ThreeRecordsIssueOperate> getCando(
			ThreeRecordsIssueStepInfo step, ThreeRecordsOrganizationInfo orgInfo) {
		return new ArrayList<ThreeRecordsIssueOperate>();
	}

	public String getStateTag() {
		return getClass().getName();
	}

	protected boolean isSameOrg(Organization source, Organization target) {
		if (source == null && target == null) {
			return true;
		}
		if (source == null || target == null) {
			return false;
		}
		return source.equals(target);
	}

	protected boolean isCommandCenter(Organization org) {
		return org.getParentOrg() == null;
	}

	protected ThreeRecordsIssueStep constructIssueStepAndFillDefaultProperty(
			BaseWorking issue, Organization loginedOrg, Organization target) {
		ThreeRecordsIssueStep result = new ThreeRecordsIssueStep();
		result.setEntryDate(CalendarUtil.now());
		result.setLastDealDate(result.getEntryDate());
		result.setSource(loginedOrg);
		result.setTarget(target);
		result.setLedgerId(issue.getId());
		result.setLedgerType(issue.getLedgerType());
		return result;
	}

	protected void stepCompleteCurrentStepAndFillDealTime(
			ThreeRecordsIssueStep currentStep, ThreeRecordsIssueStep newstep) {
		if (currentStep.getStateCode() == ISSUEVERIFICATION_CODE) {
			// 如果是验证回退那么状态改为验证未通过
			currentStep.setState(ThreeRecordsVerificationState.class.getName());
			currentStep.setStateCode(ISSUEUNVERIFICATION_CODE);
		} else {
			currentStep.setState(ThreeRecordsStepCompleteState.class.getName());
			currentStep.setStateCode(STEPCOMPLETE_CODE);
		}
		currentStep.setLastDealDate(newstep.getEntryDate());
		currentStep.setEndDate(currentStep.getLastDealDate());
	}

	protected void stepCompleteCurrentStep(ThreeRecordsIssueStep currentStep) {
		currentStep.setState(ThreeRecordsStepCompleteState.class.getName());
		currentStep.setStateCode(STEPCOMPLETE_CODE);
		currentStep.setLastDealDate(CalendarUtil.now());
		currentStep.setEndDate(currentStep.getLastDealDate());
	}

	protected boolean notParentOrg(Organization parent, Organization child) {
		return child.getOrgInternalCode().startsWith(
				parent.getOrgInternalCode());
	}

	protected Organization getLoginedOrg(
			ThreeRecordsIssueOperationContext context) {
		return context.getCurrentLoginedOrg();
	}

	protected boolean isHighLevelOrg(int orgLevel, int sourceLevel) {
		return orgLevel > sourceLevel;
	}

	protected boolean isSameLevelOrg(int orgLevel, int sourceLevel) {
		return orgLevel == sourceLevel;
	}

	@Override
	public ThreeRecordsIssueStepGroup turn(BaseWorking issue,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		throw new ThreeRecordsUnsupportOperationException(getStateLabel()
				+ "下不能进行" + ThreeRecordsIssueOperate.ASSIGN);
	}

	@Override
	public ThreeRecordsIssueStepGroup declare(BaseWorking issue,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		throw new ThreeRecordsUnsupportOperationException(getStateLabel()
				+ "下不能进行" + ThreeRecordsIssueOperate.DECLARE);
	}

	@Override
	public void support(BaseWorking issue, ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		throw new ThreeRecordsUnsupportOperationException(getStateLabel()
				+ "下不能进行" + ThreeRecordsIssueOperate.DECLARE);
	}

	@Override
	public void tmpComment(BaseWorking issue,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		throw new ThreeRecordsUnsupportOperationException(getStateLabel()
				+ "下不能进行" + ThreeRecordsIssueOperate.COMMENT);
	}
}
