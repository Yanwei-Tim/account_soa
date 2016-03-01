package com.tianque.plugin.account.state.impl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.util.GridProperties;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.constants.ThreeRecordsIssueConstants;
import com.tianque.plugin.account.domain.BaseWorking;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStepGroup;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperationContext;
import com.tianque.plugin.account.state.ThreeRecordsIssueSourceState;
import com.tianque.plugin.account.state.ThreeRecordsIssueStepInfo;
import com.tianque.plugin.account.state.ThreeRecordsOrganizationInfo;
import com.tianque.plugin.account.state.exception.ThreeRecordsIssueOperationException;
import com.tianque.plugin.account.state.exception.ThreeRecordsUnsupportOperationException;

public class ThreeRecordsIssueProgramCompleteState extends
		ThreeRecordsAbstractIssueState {

	@Override
	protected String getStateLabel() {
		return "程序性办结";
	}

	@Override
	public List<ThreeRecordsIssueOperate> getCando(
			ThreeRecordsIssueStepInfo step, ThreeRecordsOrganizationInfo orgInfo) {
		if (step == null || step.getOperationOrg() == null || orgInfo == null
				|| orgInfo.getOrganization() == null) {
			throw new ThreeRecordsIssueOperationException("参数未获得！");
		}
		List<ThreeRecordsIssueOperate> result = new ArrayList<ThreeRecordsIssueOperate>();
		if (!step.isContinue()) {
			result.add(ThreeRecordsIssueOperate.TMPCOMMENT);
			return result;
		}
		if (orgInfo.getOrganization().getOrgLevel().getInternalId() == OrganizationLevel.DISTRICT) {
			if (orgInfo.getOrganization().getDepartmentNo().endsWith(
					ThreeRecordsIssueConstants.COUNTY_LEDGER)) {
				result.add(ThreeRecordsIssueOperate.TURN);
			} else if (orgInfo.getOrganization().getDepartmentNo().endsWith(
					ThreeRecordsIssueConstants.COUNTY_JOINT)
					|| orgInfo.getOrganization().getDepartmentNo().endsWith(
							ThreeRecordsIssueConstants.COUNTY_COMMITTEE)) {
				result.add(ThreeRecordsIssueOperate.LEVEL_ASSIGN);
			} else {
				result.add(ThreeRecordsIssueOperate.DECLARE);
				result.add(ThreeRecordsIssueOperate.PERIOD_COMPLETE);
				result.add(ThreeRecordsIssueOperate.PROGRAM_COMPLETE);
				result.add(ThreeRecordsIssueOperate.COMPLETE);
				if (!(orgInfo.getOrganization().getOrgLevel().getInternalId() == OrganizationLevel.VILLAGE)) {
					result.add(ThreeRecordsIssueOperate.ASSIGN);
				}
			}

		}
		if (step.getOperationOrg().equals(orgInfo.getOrganization())) {
			result.add(ThreeRecordsIssueOperate.COMMENT);
			if (!orgInfo.isLeafOrg()
					&& !result.contains(ThreeRecordsIssueOperate.ASSIGN)) {
				if (!(orgInfo.getOrganization().getOrgLevel().getInternalId() == OrganizationLevel.VILLAGE)) {
					result.add(ThreeRecordsIssueOperate.ASSIGN);
				}
			}
			if (orgInfo.getOrganization().getOrgLevel().getInternalId() != OrganizationLevel.DISTRICT) {
				result.add(ThreeRecordsIssueOperate.COMPLETE);
				result.add(ThreeRecordsIssueOperate.PROGRAM_COMPLETE);
				result.add(ThreeRecordsIssueOperate.PERIOD_COMPLETE);
			}
			if (!orgInfo.isTopOrg()
					&& orgInfo.getOrganization().getOrgLevel().getInternalId() != OrganizationLevel.DISTRICT) {
				result.add(ThreeRecordsIssueOperate.SUBMIT);
			}
		}
		return result;
	}

	@Override
	public ThreeRecordsIssueStepGroup back(BaseWorking issue,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		if (currentStep == null || currentStep.getTarget() == null
				|| context == null || context.getTargetOrg() == null) {
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
		if (currentStep == null || currentStep.getTarget() == null
				|| context == null || context.getTargetOrg() == null) {
			throw new ThreeRecordsIssueOperationException("参数未获得！");
		}
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
			keyStep.setState(ThreeRecordsIssueProgramCompleteState.class
					.getName());
			keyStep.setStateCode(DEALING_CODE);
		}
		stepCompleteCurrentStepAndFillDealTime(currentStep, keyStep);
		result.setKeyStep(keyStep);
		issue.setCurrentStep(keyStep);
		issue.setCurrentOrg(keyStep.getTarget());
		issue.setStatus(DEALING);
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

	@Override
	public void comment(BaseWorking issue, ThreeRecordsIssueStep currentStep,
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

	@Override
	public ThreeRecordsIssueStepGroup declare(BaseWorking issue,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		if (currentStep == null || currentStep.getTarget() == null
				|| context == null || context.getTargetOrg() == null) {
			throw new ThreeRecordsIssueOperationException("参数未获得！");
		}
		if (!getLoginedOrg(context).equals(currentStep.getTarget())) {
			throw new ThreeRecordsIssueOperationException("不能申报其他部门的事件");
		}
		return transferIssueToOtherOrg(issue, currentStep,
				getLoginedOrg(context), context.getTargetOrg(), context
						.getTellOrgs(), true, context.getNoticeOrgs());
	}

	private ThreeRecordsIssueStepGroup transferIssueToOtherOrg(
			BaseWorking issue, ThreeRecordsIssueStep currentStep,
			Organization loginedOrg, Organization target,
			List<Organization> tellOrgs, boolean tellHighOrg,
			List<Organization> noticeOrgs) {
		if (currentStep == null || issue == null || issue.getId() == null) {
			throw new ThreeRecordsIssueOperationException("参数未获得！");
		}
		ThreeRecordsIssueStepGroup result = new ThreeRecordsIssueStepGroup();
		ThreeRecordsIssueStep keyStep = constructIssueStepAndFillDefaultProperty(
				issue, loginedOrg, target);
		keyStep.setBackTo(currentStep);
		result.setLedgerId(issue.getId());
		result.setLedgerType(issue.getLedgerType());
		keyStep.setItemTypeId(currentStep.getItemTypeId());
		if ("true".equals(GridProperties.IS_NEED_UNCONCEPTEDSTATE)) {
			keyStep.setState(ThreeRecordsUnConceptedState.class.getName());
			keyStep.setStateCode(UNCONCEPTED_CODE);
		} else {
			keyStep.setState(ThreeRecordsDealingState.class.getName());
			keyStep.setStateCode(DEALING_CODE);
		}
		if (!tellHighOrg) {
			keyStep.setAssign(ThreeRecordsIssueSourceState.assign);
		}
		stepCompleteCurrentStepAndFillDealTime(currentStep, keyStep);
		if (tellOrgs != null) {
			ThreeRecordsIssueStep tellStep = null;
			for (Organization org : tellOrgs) {
				if (!org.equals(target)) {
					tellStep = constructIssueStepAndFillDefaultProperty(issue,
							loginedOrg, org);
					tellStep
							.setIsSupported(LedgerConstants.OPERATE_TYPE_SUPPORT);
					tellStep.setState(ThreeRecordsUnConceptedState.class
							.getName());
					result.addIncidentalStep(constructTellStep(issue,
							loginedOrg, org, tellHighOrg));
				}
			}
		}
		if (noticeOrgs != null) {
			ThreeRecordsIssueStep tellStep = null;
			for (Organization org : noticeOrgs) {
				if (!org.equals(target)) {
					tellStep = constructIssueStepAndFillDefaultProperty(issue,
							loginedOrg, org);
					tellStep
							.setIsSupported(LedgerConstants.OPERATE_TYPE_NOTICE);
					tellStep.setState(ThreeRecordsUnReadState.class.getName());
					tellStep.setStateCode(UNREAD_CODE);
					result.addIncidentalStep(tellStep);
				}
			}
		}
		result.setKeyStep(keyStep);
		issue.setCurrentStep(keyStep);
		issue.setCurrentOrg(keyStep.getTarget());
		issue.setStatus(DEALING);
		return result;
	}

	private ThreeRecordsIssueStep constructTellStep(BaseWorking issue,
			Organization loginedOrg, Organization tellorg, boolean tellHighOrg) {
		if (issue == null || issue.getId() == null) {
			throw new ThreeRecordsIssueOperationException("参数未获得！");
		}
		ThreeRecordsIssueStep tellStep = constructIssueStepAndFillDefaultProperty(
				issue, loginedOrg, tellorg);
		tellStep.setState(ThreeRecordsUnConceptedState.class.getName());
		tellStep.setStateCode(UNCONCEPTED_CODE);
		tellStep.setIsSupported(LedgerConstants.OPERATE_TYPE_SUPPORT);
		if (!tellHighOrg) {
			tellStep.setAssign(ThreeRecordsIssueSourceState.assign);
		}
		return tellStep;
	}

	@Override
	public ThreeRecordsIssueStepGroup periodComplete(BaseWorking issue,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		if (issue == null || issue.getId() == null) {
			throw new ThreeRecordsIssueOperationException("参数未获得！");
		}
		if (!getLoginedOrg(context).equals(currentStep.getTarget())) {
			throw new ThreeRecordsIssueOperationException("不能办结其他部门的事件");
		}
		return transferIssueForPeriodComplete(issue, currentStep,
				getLoginedOrg(context), context.getTargetOrg());

	}

	private ThreeRecordsIssueStepGroup transferIssueForPeriodComplete(
			BaseWorking issue, ThreeRecordsIssueStep currentStep,
			Organization loginedOrg, Organization target) {
		if (currentStep == null || issue == null || issue.getId() == null) {
			throw new ThreeRecordsIssueOperationException("参数未获得！");
		}
		ThreeRecordsIssueStepGroup result = new ThreeRecordsIssueStepGroup();
		ThreeRecordsIssueStep keyStep = constructIssueStepAndFillDefaultProperty(
				issue, loginedOrg, target);
		keyStep.setBackTo(currentStep);
		keyStep.setItemTypeId(currentStep.getItemTypeId());
		keyStep.setState(ThreeRecordsIssuePeriodCompleteState.class.getName());
		keyStep.setStateCode(PERIOD_CODE);// 结案
		keyStep.setLedgerId(currentStep.getLedgerId());
		keyStep.setLedgerType(issue.getLedgerType());
		stepCompleteCurrentStepAndFillDealTime(currentStep, keyStep);
		result.setKeyStep(keyStep);
		return result;
	}

	@Override
	public ThreeRecordsIssueStepGroup submit(BaseWorking issue,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		if (currentStep == null || currentStep.getTarget() == null) {
			throw new ThreeRecordsIssueOperationException("参数未获得！");
		}
		if (!getLoginedOrg(context).equals(currentStep.getTarget())) {
			throw new ThreeRecordsIssueOperationException("不能上报其他部门的事件");
		}
		// 台账办
		return transferIssueToOtherOrg(issue, currentStep,
				getLoginedOrg(context), context.getTargetOrg(), context
						.getTellOrgs(), true, context.getNoticeOrgs());
	}

	@Override
	public ThreeRecordsIssueStepGroup assign(BaseWorking issue,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		if (currentStep == null || issue == null || issue.getId() == null) {
			throw new ThreeRecordsIssueOperationException("参数未获得！");
		}
		if (!getLoginedOrg(context).equals(currentStep.getTarget())) {
			throw new ThreeRecordsIssueOperationException("不能交办其他部门的事件");
		}
		Organization org = getLoginedOrg(context);
		if (org.getOrgLevel().getInternalId() != OrganizationLevel.DISTRICT) {
			if (getLoginedOrg(context).getId() > 0
					&& (currentStep.getEmergencyLevel() == null || currentStep
							.getEmergencyLevel().getId() == null)) {
				if (getLoginedOrg(context).getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG) {
					if (!context.getTargetOrg().getParentFunOrg().equals(
							getLoginedOrg(context))) {
						throw new ThreeRecordsIssueOperationException(
								"不能将事件交办给非直属下级部门");
					}
				} else if (!context.getTargetOrg().getParentOrg().equals(
						getLoginedOrg(context))) {
					throw new ThreeRecordsIssueOperationException(
							"不能将事件交办给非直属下级部门");
				}
			}
		}
		return transferIssueToOtherOrg(issue, currentStep,
				getLoginedOrg(context), context.getTargetOrg(), context
						.getTellOrgs(), false, context.getNoticeOrgs());
	}

	@Override
	public ThreeRecordsIssueStepGroup complete(BaseWorking issue,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		if (currentStep == null || issue == null || issue.getId() == null) {
			throw new ThreeRecordsIssueOperationException("参数未获得！");
		}
		if (!getLoginedOrg(context).equals(currentStep.getTarget())) {
			throw new ThreeRecordsIssueOperationException("不能办结其他部门的事件");
		}
		return transferIssueForComplete(issue, currentStep,
				getLoginedOrg(context), context.getTargetOrg());

	}

	@Override
	public ThreeRecordsIssueStepGroup programComplete(BaseWorking issue,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException {
		if (currentStep == null || currentStep.getTarget() == null) {
			throw new ThreeRecordsIssueOperationException("参数未获得！");
		}
		if (!getLoginedOrg(context).equals(currentStep.getTarget())) {
			throw new ThreeRecordsIssueOperationException("不能办结其他部门的事件");
		}
		return transferIssueForProgramComplete(issue, currentStep,
				getLoginedOrg(context), context.getTargetOrg());

	}

	private ThreeRecordsIssueStepGroup transferIssueForComplete(
			BaseWorking issue, ThreeRecordsIssueStep currentStep,
			Organization loginedOrg, Organization target) {
		if (currentStep == null || issue == null || issue.getId() == null) {
			throw new ThreeRecordsIssueOperationException("参数未获得！");
		}
		ThreeRecordsIssueStepGroup result = new ThreeRecordsIssueStepGroup();
		ThreeRecordsIssueStep keyStep = constructIssueStepAndFillDefaultProperty(
				issue, loginedOrg, target);
		keyStep.setBackTo(currentStep);
		keyStep.setItemTypeId(currentStep.getItemTypeId());
		keyStep.setState(ThreeRecordsIssueCompleteState.class.getName());
		keyStep.setStateCode(SUBSTANCE_CODE);// 结案
		keyStep.setLedgerId(currentStep.getLedgerId());
		keyStep.setLedgerType(issue.getLedgerType());
		stepCompleteCurrentStepAndFillDealTime(currentStep, keyStep);
		result.setKeyStep(keyStep);
		return result;
	}

	private ThreeRecordsIssueStepGroup transferIssueForProgramComplete(
			BaseWorking issue, ThreeRecordsIssueStep currentStep,
			Organization loginedOrg, Organization target) {
		ThreeRecordsIssueStepGroup result = new ThreeRecordsIssueStepGroup();
		ThreeRecordsIssueStep keyStep = constructIssueStepAndFillDefaultProperty(
				issue, loginedOrg, target);
		keyStep.setBackTo(currentStep);
		keyStep.setItemTypeId(currentStep.getItemTypeId());
		keyStep.setState(ThreeRecordsIssueProgramCompleteState.class.getName());
		keyStep.setStateCode(STEPCOMPLETE_CODE);// 结案
		keyStep.setLedgerId(currentStep.getLedgerId());
		keyStep.setLedgerType(issue.getLedgerType());
		stepCompleteCurrentStepAndFillDealTime(currentStep, keyStep);
		result.setLedgerType(issue.getLedgerType());
		result.setKeyStep(keyStep);
		return result;
	}

}