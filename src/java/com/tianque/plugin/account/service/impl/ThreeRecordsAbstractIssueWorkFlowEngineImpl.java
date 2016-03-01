package com.tianque.plugin.account.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.account.ThreeRecordsIssueHelper;
import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.constants.ThreeRecordsIssueConstants;
import com.tianque.plugin.account.domain.AssignForm;
import com.tianque.plugin.account.domain.AssignFormReceiver;
import com.tianque.plugin.account.domain.BaseWorking;
import com.tianque.plugin.account.domain.DeclareForm;
import com.tianque.plugin.account.domain.ReportForm;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStepGroup;
import com.tianque.plugin.account.domain.TurnForm;
import com.tianque.plugin.account.listener.ThreeRecordsIssueChangeEvent;
import com.tianque.plugin.account.listener.ThreeRecordsIssueChangeListener;
import com.tianque.plugin.account.service.AssignFormReceiverService;
import com.tianque.plugin.account.service.AssignFormService;
import com.tianque.plugin.account.service.DeclareFormService;
import com.tianque.plugin.account.service.ReportFormService;
import com.tianque.plugin.account.service.ThreeRecordsIssueLogService;
import com.tianque.plugin.account.service.ThreeRecordsIssueProcessService;
import com.tianque.plugin.account.service.ThreeRecordsIssueWorkFlowEngine;
import com.tianque.plugin.account.service.TurnFormService;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperationContext;
import com.tianque.plugin.account.state.ThreeRecordsIssueSourceState;
import com.tianque.plugin.account.state.ThreeRecordsIssueState;
import com.tianque.plugin.account.state.ThreeRecordsIssueStepInfo;
import com.tianque.plugin.account.state.ThreeRecordsOrganizationInfo;
import com.tianque.plugin.account.util.ThreeRecordsIssueOperationUtil;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;
import com.tianque.userAuth.api.PropertyDictDubboService;

public abstract class ThreeRecordsAbstractIssueWorkFlowEngineImpl extends
		AbstractBaseService implements ThreeRecordsIssueWorkFlowEngine {
	@Autowired
	protected ThreeRecordsIssueProcessService threeRecordsIssueProcessService;
	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;
	@Autowired
	private PropertyDictDubboService propertyDictDubboService;

	@Autowired
	private ThreeRecordsIssueLogService issueLogService;

	@Autowired
	private TurnFormService turnFormService;

	@Autowired
	private DeclareFormService declareFormService;

	@Autowired
	private ReportFormService reportFormService;

	@Autowired
	private AssignFormService assignFormService;

	@Autowired
	private AssignFormReceiverService assignFormReceiverService;


	/***
	 * 创建台账处理步骤实对象
	 * 
	 * @param baseWork
	 *            台账
	 * @return
	 */
	protected abstract ThreeRecordsIssueStep createEntryIssueStep(
			BaseWorking baseWork, Long sourceKindId);

	/***
	 * 获取台账监听器类
	 * 
	 * @return
	 */
	protected abstract List<ThreeRecordsIssueChangeListener> getIssueChangeListeners();

	@Override
	public ThreeRecordsIssueStep register(BaseWorking baseWork) {
		if (baseWork == null || baseWork.getOrganization() == null
				|| baseWork.getOrganization().getId() == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		baseWork.setCreateOrg(organizationDubboService.getFullOrgById(baseWork
				.getOrganization().getId()));
		baseWork.setOccurOrg(organizationDubboService.getFullOrgById(baseWork
				.getOccurOrg().getId()));
		PropertyDict sourceKind = propertyDictDubboService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.SOURCE_KIND,
						ThreeRecordsIssueConstants.PC_INPUT);
		ThreeRecordsIssueStep result = threeRecordsIssueProcessService
				.addLedgerStep(createEntryIssueStep(baseWork,
						sourceKind != null ? sourceKind.getId() : 0l));
		ThreeRecordsIssueStepGroup issueStepGroup = new ThreeRecordsIssueStepGroup();
		issueStepGroup.setKeyStep(result);
		issueStepGroup.setLedgerId(baseWork.getId());
		issueStepGroup.setLedgerType(baseWork.getLedgerType());
		fetchSourceAndTargetOrg(result, result);
		fireIssueEntry(baseWork, result);
		fireIssueGroup(issueStepGroup);
		return result;
	}

	@Override
	public boolean unRegister(Long ledgerId, int ledgerType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ledgerId", ledgerId);
		map.put("ledgerType", ledgerType);
		threeRecordsIssueProcessService.deleteLedgerStepsByIdAndType(ledgerId,
				ledgerType);
		threeRecordsIssueProcessService.deleteLedgerStepGroupsByIdAndType(
				ledgerId, ledgerType);
		return true;
	}

	@Override
	public ThreeRecordsIssueStep getFullIssueStepById(Long stepId) {
		ThreeRecordsIssueStep step = getSimpleIssueStepById(stepId);
		fetchSourceAndTargetOrg(step, step);
		return step;
	}

	@Override
	public ThreeRecordsIssueStep complete(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		if (step == null || step.getTarget() == null || log == null
				|| log.getDealType() == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		try {
			ThreeRecordsIssueOperationContext context = constructIssueOperationContext(
					step.getTarget(), null, null);
			ThreeRecordsIssueStepGroup steps = saveStepGroup(
					ThreeRecordsIssueHelper.constructIssueStateFromStep(step)
							.complete(baseWork, step, context), log
							.getDealType());
			if (steps.getKeyStep() != null
					&& steps.getKeyStep().getSource().getId().intValue() == steps
							.getKeyStep().getTarget().getId().intValue()) {
				step.setIsFeedBack(0);
			}
			threeRecordsIssueProcessService.updateIssueStepExceptOrg(step);
			fireIssueChanged(baseWork, steps,
					ThreeRecordsIssueOperate.COMPLETE, log, attachFiles);
			ThreeRecordsIssueStep result = getFullIssueStepById(step.getId());
			fireIssueComplete(baseWork, result, log, attachFiles);
			fireIssueGroup(steps);
			return result;
		} catch (Exception e) {
			throw new ServiceValidationException("办结过程中发生错误", e);
		}
	}

	@Override
	public ThreeRecordsIssueStep submit(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log,
			Long targetOrg, Long[] tells,
			List<ThreeRecordsIssueAttachFile> attachFiles, Long[] notices) {
		if (step == null || step.getTarget() == null || log == null
				|| log.getDealType() == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		try {
			List<Organization> tellsList = null;
			ThreeRecordsIssueOperationContext context = null;
			tellsList = loadFullOrganizations(tells);
			context = constructOperationContext(targetOrg, tellsList,
					loadFullOrganizations(notices));
			ThreeRecordsIssueStepGroup steps = ThreeRecordsIssueHelper
					.constructIssueStateFromStep(step).submit(baseWork, step,
							context);
			steps = saveStepGroup(steps, log.getDealType());
			step.setSubmit(ThreeRecordsIssueSourceState.submit);
			threeRecordsIssueProcessService.updateIssueStepExceptOrg(step);
			fireIssueChanged(baseWork, steps, ThreeRecordsIssueOperate.SUBMIT,
					log, attachFiles);
			fireIssueGroup(steps);
			updateReportForm(baseWork, log, step.getId());
			return steps.getKeyStep();
		} catch (Exception e) {
			throw new ServiceValidationException("上报过程中发生错误", e);
		}
	}

	@Override
	public ThreeRecordsIssueStep assign(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log,
			Long targetOrg, Long[] tells,
			List<ThreeRecordsIssueAttachFile> attachFiles, Long[] notices) {
		if (step == null || step.getTarget() == null || log == null
				|| log.getDealType() == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		try {
			ThreeRecordsIssueOperationContext context;
			context = constructOperationContext(targetOrg,
					loadFullOrganizations(tells),
					loadFullOrganizations(notices));
			ThreeRecordsIssueStepGroup steps = saveStepGroup(
					ThreeRecordsIssueHelper.constructIssueStateFromStep(step)
							.assign(baseWork, step, context), log.getDealType());
			threeRecordsIssueProcessService.updateIssueStepExceptOrg(step);
			fireIssueChanged(baseWork, steps, ThreeRecordsIssueOperate.ASSIGN,
					log, attachFiles);
			fireIssueGroup(steps);
			updateAssignForm(baseWork, log, tells, steps.getId(), step
					.getId());
			return steps.getKeyStep();
		} catch (Exception e) {
			throw new ServiceValidationException("交办过程中发生错误", e);
		}
	}

	@Override
	public ThreeRecordsIssueStep concept(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log) {
		if (step == null || step.getTarget() == null || log == null
				|| log.getDealType() == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		try {
			ThreeRecordsIssueOperationContext context = constructIssueOperationContext(
					loadFullCurrentLoginedOrganization(), null, null);
			ThreeRecordsIssueHelper.constructIssueStateFromStep(step).concept(
					baseWork, step, context);
			step = getFullIssueStepById(threeRecordsIssueProcessService
					.updateIssueStepExceptOrg(step).getId());
			fireIssueChanged(baseWork, step, ThreeRecordsIssueOperate.CONCEPT,
					log, new ArrayList<ThreeRecordsIssueAttachFile>());
			conceptForm(step, log);
			return step;
		} catch (Exception e) {
			throw new ServiceValidationException("受理过程中发生错误", e);
		}
	}

	@Override
	public ThreeRecordsIssueStep back(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		if (step == null || step.getTarget() == null || log == null
				|| log.getDealType() == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		try {
			ThreeRecordsIssueStep backTo = getSimpleIssueStepById(step
					.getBackTo().getId());
			step.setBackTo(backTo);
			ThreeRecordsIssueOperationContext context;
			context = constructIssueOperationContext(
					loadFullOrganization(backTo.getTarget().getId()), null,
					null);
			ThreeRecordsIssueStepGroup steps = saveStepGroup(
					ThreeRecordsIssueHelper.constructIssueStateFromStep(step)
							.back(baseWork, step, context), log.getDealType());
			threeRecordsIssueProcessService.updateIssueStepExceptOrg(step);
			fireIssueChanged(baseWork, steps, ThreeRecordsIssueOperate.BACK,
					log, attachFiles);
			steps.setLedgerType(baseWork.getLedgerType());
			fireIssueGroup(steps);
			return steps.getKeyStep();
		} catch (Exception e) {
			throw new ServiceValidationException("回退时发生错误", e);
		}
	}

	@Override
	public ThreeRecordsIssueStep comment(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		if (step == null || step.getTarget() == null || log == null
				|| log.getDealType() == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		try {
			ThreeRecordsIssueOperationContext context = constructIssueOperationContext(
					loadFullCurrentLoginedOrganization(), null, null);
			ThreeRecordsIssueHelper.constructIssueStateFromStep(step).comment(
					baseWork, step, context);
			step = getFullIssueStepById(threeRecordsIssueProcessService
					.updateIssueStepExceptOrg(step).getId());
			fireIssueChanged(baseWork, step, ThreeRecordsIssueOperate.COMMENT,
					log, attachFiles);
			return step;
		} catch (Exception e) {
			throw new ServiceValidationException("填写处理意见过程中发生错误", e);
		}
	}

	@Override
	public ThreeRecordsIssueStep tmpComment(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		if (step == null || step.getTarget() == null || log == null
				|| log.getDealType() == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		try {
			ThreeRecordsIssueOperationContext context = constructIssueOperationContext(
					loadFullCurrentLoginedOrganization(), null, null);
			ThreeRecordsIssueHelper.constructIssueStateFromStep(step)
					.tmpComment(baseWork, step, context);
			step = getFullIssueStepById(threeRecordsIssueProcessService
					.updateIssueStepExceptOrg(step).getId());
			fireIssueChanged(baseWork, step,
					ThreeRecordsIssueOperate.TMPCOMMENT, log, attachFiles);
			return step;
		} catch (Exception e) {
			throw new ServiceValidationException("添加处理措施过程中发生错误", e);
		}
	}

	@Override
	public void fireIssueChanged(BaseWorking baseWork,
			ThreeRecordsIssueOperate operate, ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		if (operate == null || baseWork == null || log == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		ThreeRecordsIssueChangeEvent event = new ThreeRecordsIssueChangeEvent(
				log, attachFiles, operate);
		if (getIssueChangeListeners() != null) {
			for (ThreeRecordsIssueChangeListener listener : getIssueChangeListeners()) {
				listener.onChanged(baseWork, null, event);
			}
		}
	}

	@Override
	public List<ThreeRecordsIssueOperate> getIssueCandoForOrg(Long stepId,
			Organization org) {
		if (org == null || org.getId() == null || stepId == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		ThreeRecordsIssueStep step = getSimpleIssueStepById(stepId);
		if (step.getIsSupported() == LedgerConstants.OPERATE_TYPE_SUPPORT
				&& step.getStateCode() != ThreeRecordsIssueState.STEPCOMPLETE_CODE) {
			List<ThreeRecordsIssueOperate> result = new ArrayList<ThreeRecordsIssueOperate>();
			// result.add(ThreeRecordsIssueOperate.COMMENT);
			// result.add(ThreeRecordsIssueOperate.SUPPORT);
			result.add(ThreeRecordsIssueOperate.TMPCOMMENT);
			return result;
		}
		fetchFullSourceAndTargetOrg(step);
		org = loadFullOrganization(org.getId());
		ThreeRecordsIssueStepInfo stepInfo = new ThreeRecordsIssueStepInfo();
		stepInfo.setOperationOrg(step.getTarget());
		stepInfo.setSuperviseLevel(step.getSuperviseLevel());
		if (step.getDealType() != null) {
			stepInfo.setContinue(ThreeRecordsIssueOperate.PROGRAM_COMPLETE
					.getCode() == step.getDealType().intValue());
		}
		ThreeRecordsOrganizationInfo organizationInfo = new ThreeRecordsOrganizationInfo();
		organizationInfo
				.setLeafOrg((org.getSubCount() + org.getSubCountFun()) == 0);
		organizationInfo.setTopOrg(org.getParentOrg() == null);
		organizationInfo.setOrganization(org);
		try {
			return ThreeRecordsIssueHelper.constructIssueStateFromStep(step)
					.getCando(stepInfo, organizationInfo);
		} catch (Exception e) {
			throw new ServiceValidationException("获取可以进行的操作时发生以下错误", e);
		}
	}

	@Override
	public PageInfo<AutoCompleteData> findAdminTargetsByName(Long stepid,
			ThreeRecordsIssueOperate operate, String tag, Long[] exceptId,
			boolean twice, int page, int rows) {
		if (operate == null || stepid == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		ThreeRecordsIssueStep step = getFullIssueStepById(stepid);
		if (ThreeRecordsIssueOperate.SUBMIT.equals(operate)) {
			return findSubmitAdminTargetsByName(tag, exceptId, step);
		}
		if (ThreeRecordsIssueOperate.ASSIGN.equals(operate)) {
			return findAssignAdminTargetsByName(tag, exceptId, step, page, rows);
		}
		if (ThreeRecordsIssueOperate.TURN.equals(operate)) {
			return findTurnAdminTargetsByName(tag, exceptId, step, page, rows);
		}
		if (ThreeRecordsIssueOperate.DECLARE.equals(operate)) {
			return findDeclareAdminTargetsByName(tag, exceptId, twice, step);
		}
		return createEmptyAutoCompleteDataPage();
	}

	@Override
	public PageInfo<AutoCompleteData> findFunctionTargetsByName(Long stepid,
			ThreeRecordsIssueOperate operate, String tag, Long[] exceptIds,
			int pageIndex, int rows) {
		if (operate == null || stepid == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		ThreeRecordsIssueStep step = getFullIssueStepById(stepid);
		if (ThreeRecordsIssueOperate.SUBMIT.equals(operate)) {
			return findSubmitFunctionTargetsByName(tag, exceptIds, step,
					pageIndex, rows);
		}
		if (ThreeRecordsIssueOperate.ASSIGN.equals(operate)) {
			return findAssignFunctionTargetsByName(tag, exceptIds, step,
					pageIndex, rows);
		}
		if (ThreeRecordsIssueOperate.TURN.equals(operate)) {
			return findTurnFunctionTargetsByName(tag, exceptIds,
					ThreeRecordsIssueConstants.COUNTY_EXCEPT, step, pageIndex,
					rows);
		}
		if (ThreeRecordsIssueOperate.LEVEL_ASSIGN.equals(operate)) {
			return findLevelAssignFunctionTargetsByName(tag, exceptIds,
					ThreeRecordsIssueConstants.COUNTY_EXCEPT, step, pageIndex,
					rows);
		}
		return createEmptyAutoCompleteDataPage();
	}

	@Override
	public PageInfo<AutoCompleteData> findTellTargetsByName(Long stepid,
			ThreeRecordsIssueOperate operate, String tag,
			boolean transferToAdmin, Long[] exceptIds, int page, int rows) {
		if (operate == null || stepid == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		ThreeRecordsIssueStep step = getFullIssueStepById(stepid);
		if (ThreeRecordsIssueOperate.GIVETO.equals(operate)) {
			return findGiveToTellsByName(tag, exceptIds, step, transferToAdmin,
					page, rows);
		}
		if (ThreeRecordsIssueOperate.SUBMIT.equals(operate)) {
			return findSubmitTellsByName(tag, exceptIds, step, transferToAdmin,
					page, rows);
		}
		if (ThreeRecordsIssueOperate.ASSIGN.equals(operate)) {
//			return findAssignTellsByName(tag, exceptIds, step, transferToAdmin,
//					page, rows);
			return findAssignAdminTargetsByName(tag, exceptIds, step, page, rows);
		}
		if (ThreeRecordsIssueOperate.TURN.equals(operate) || ThreeRecordsIssueOperate.LEVEL_ASSIGN.equals(operate)) {
			return findTurnFunctionTargetsByName(null, tag, exceptIds,
					ThreeRecordsIssueConstants.COUNTY_EXCEPT, step, page,
					rows);
		}
		return createEmptyAutoCompleteDataPage();
	}
	
	private PageInfo<AutoCompleteData> findTurnFunctionTargetsByName(PropertyDict dict,
			String tag, Long[] exceptIds, String[] exceptDeptNos,
			ThreeRecordsIssueStep step, int pageIndex, int rows) {
		if (step == null || step.getTarget() == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		Organization org = organizationDubboService.getFullOrgById(step
				.getTarget().getId());
		return organizationDubboService.findChildOrgsByParentIdAndName(dict,
				org.getParentOrg().getId(), tag, exceptIds, exceptDeptNos,
				pageIndex, rows);
	}

	private PageInfo<AutoCompleteData> findGiveToTellsByName(String tag,
			Long[] exceptIds, ThreeRecordsIssueStep step,
			boolean transferToAdmin, int page, int rows) {
		if (exceptIds == null || exceptIds.length == 0) {
			return createEmptyAutoCompleteDataPage();
		} else {
			Organization transferTo = loadFullOrganization(exceptIds[0]);
			if (isAdminOrg(transferTo)) {
				PropertyDict dict = propertyDictDubboService
						.findPropertyDictByDomainNameAndInternalId(
								OrganizationType.ORG_TYPE_KEY,
								OrganizationType.FUNCTIONAL_ORG).get(0);
				return organizationDubboService.findChildOrgsByParentIdAndName(
						dict, transferTo.getId(), tag, exceptIds, null, page,
						rows);
			}
			return findParentAndSiblingFunOrgs(tag, exceptIds, transferTo,
					page, rows);

		}
	}

	private PageInfo<AutoCompleteData> findParentAndSiblingFunOrgs(String tag,
			Long[] exceptIds, Organization funOrg, int page, int rows) {
		Organization parent = organizationDubboService.getSimpleOrgById(funOrg
				.getParentOrg().getId());
		PropertyDict dict = propertyDictDubboService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationType.ORG_TYPE_KEY,
						OrganizationType.FUNCTIONAL_ORG).get(0);
		PageInfo<AutoCompleteData> result = organizationDubboService
				.findChildOrgsByParentIdAndName(dict, parent.getId(), tag,
						exceptIds, null, page, rows);
		if (!inExceptOrg(parent.getId(), exceptIds)) {
			insertOrgToPages(parent, result);
		}
		return result;
	}

	private PageInfo<AutoCompleteData> findAssignTellsByName(String tag,
			Long[] exceptIds, ThreeRecordsIssueStep step,
			boolean transferToAdmin, int page, int rows) {
		Organization operationOrg = organizationDubboService
				.getFullOrgById(ThreadVariable.getSession().getOrganization()
						.getId());
		if (isAdminOrg(operationOrg)) {
			PropertyDict dict = propertyDictDubboService
					.findPropertyDictByDomainNameAndInternalId(
							OrganizationType.ORG_TYPE_KEY,
							OrganizationType.FUNCTIONAL_ORG).get(0);
			return organizationDubboService.findChildOrgsByParentIdAndName(
					dict,
					ThreadVariable.getSession().getOrganization().getId(), tag,
					exceptIds, null, page, rows);
		} else {
			PageInfo<AutoCompleteData> result = createEmptyAutoCompleteDataPage();
			if (!inExceptOrg(operationOrg.getParentOrg().getId(), exceptIds)) {
				insertOrgToPages(organizationDubboService
						.getSimpleOrgById(operationOrg.getParentOrg().getId()),
						result);
			}
			return result;
		}
	}

	private PageInfo<AutoCompleteData> findSubmitTellsByName(String tag,
			Long[] exceptIds, ThreeRecordsIssueStep step,
			boolean transferToAdmin, int page, int rows) {
		Organization operationOrg = organizationDubboService
				.getFullOrgById(ThreadVariable.getSession().getOrganization()
						.getId());
		if (transferToAdmin) {
			if (isAdminOrg(operationOrg)) {
				PropertyDict dict = propertyDictDubboService
						.findPropertyDictByDomainNameAndInternalId(
								OrganizationType.ORG_TYPE_KEY,
								OrganizationType.FUNCTIONAL_ORG).get(0);
				return organizationDubboService.findChildOrgsByParentIdAndName(
						dict, step.getTarget().getParentOrg().getId(), tag,
						exceptIds, null, page, rows);
			}
			return createEmptyAutoCompleteDataPage();

		} else {
			if (isAdminOrg(operationOrg)) {
				PropertyDict dict = propertyDictDubboService
						.findPropertyDictByDomainNameAndInternalId(
								OrganizationType.ORG_TYPE_KEY,
								OrganizationType.FUNCTIONAL_ORG).get(0);
				PageInfo<AutoCompleteData> result = organizationDubboService
						.findChildOrgsByParentIdAndName(dict, operationOrg
								.getParentOrg().getId(), tag, exceptIds, null,
								page, rows);
				if (!inExceptOrg(operationOrg.getParentOrg().getId(), exceptIds)) {
					insertOrgToPages(organizationDubboService
							.getSimpleOrgById(operationOrg.getParentOrg()
									.getId()), result);
				}
				return result;
			}
			PageInfo<AutoCompleteData> result = createEmptyAutoCompleteDataPage();
			if (!inExceptOrg(operationOrg.getParentOrg().getId(), exceptIds)) {
				insertOrgToPages(organizationDubboService
						.getSimpleOrgById(operationOrg.getParentOrg().getId()),
						result);
			}
			return result;
		}
	}

	private boolean isAdminOrg(Organization org) {
		return OrganizationType.ADMINISTRATIVE_REGION == org.getOrgType()
				.getInternalId();
	}

	private PageInfo<AutoCompleteData> createEmptyAutoCompleteDataPage() {
		return createAutoCompleteDataPage(1, 0, 0,
				new ArrayList<AutoCompleteData>());
	}

	private PageInfo<AutoCompleteData> createAutoCompleteDataPage(
			int pageIndex, int pagesize, int totalCount,
			List<AutoCompleteData> data) {
		PageInfo<AutoCompleteData> result = new PageInfo<AutoCompleteData>();
		result.setCurrentPage(pageIndex);
		result.setPerPageSize(pagesize == 0 ? 1 : pagesize);
		result.setTotalRowSize(totalCount);
		result.setResult(data);
		return result;
	}

	private boolean inExceptOrg(Long id, Long[] exceptIds) {
		if (id == null || ArrayUtils.isEmpty(exceptIds)) {
			return false;
		}
		for (Long exceptId : exceptIds) {
			if (exceptId.equals(id)) {
				return true;
			}
		}
		return false;
	}

	private AutoCompleteData convertToAutoCompleteData(Organization org) {
		AutoCompleteData autoCompleteData = new AutoCompleteData();
		autoCompleteData.setValue(org.getId().toString());
		autoCompleteData.setLabel(org.getOrgName());
		autoCompleteData.setDesc(org.getRemark());
		return autoCompleteData;
	}

	private PageInfo<AutoCompleteData> findAssignFunctionTargetsByName(
			String tag, Long[] exceptIds, ThreeRecordsIssueStep step,
			int pageIndex, int rows) {
		if (step == null || step.getTarget() == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		/*
		 * 注释内容为镇职能部门处理台账时执能部门的下级职能部门
		 */
		// Organization org = organizationDubboService.getFullOrgById(step
		// .getTarget().getId());
		// if (OrganizationType.FUNCTIONAL_ORG ==
		// org.getOrgType().getInternalId()) {
		// List<AutoCompleteData> list = organizationDubboService
		// .findChildFunOrgsByParentFunIdAndName(org.getId(), tag,
		// exceptIds);
		// int size = (list == null || list.size() == 0) ? 0 : list.size();
		// return createAutoCompleteDataPage(pageIndex, rows, size, list);
		// } else {
		PropertyDict dict = propertyDictDubboService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationType.ORG_TYPE_KEY,
						OrganizationType.FUNCTIONAL_ORG).get(0);
		return organizationDubboService
				.findChildOrgsByParentIdAndName(dict, step.getTarget().getId(),
						tag, exceptIds, null, pageIndex, rows);
		// }
	}

	private PageInfo<AutoCompleteData> findTurnFunctionTargetsByName(
			String tag, Long[] exceptIds, String[] exceptDeptNos,
			ThreeRecordsIssueStep step, int pageIndex, int rows) {
		if (step == null || step.getTarget() == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		Organization org = organizationDubboService.getFullOrgById(step
				.getTarget().getId());
		PropertyDict dict = propertyDictDubboService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationType.ORG_TYPE_KEY,
						OrganizationType.FUNCTIONAL_ORG).get(0);
		return organizationDubboService.findChildOrgsByParentIdAndName(dict,
				org.getParentOrg().getId(), tag, exceptIds, exceptDeptNos,
				pageIndex, rows);
	}

	private PageInfo<AutoCompleteData> findLevelAssignFunctionTargetsByName(
			String tag, Long[] exceptIds, String[] exceptDeptNos,
			ThreeRecordsIssueStep step, int pageIndex, int rows) {
		if (step == null || step.getTarget() == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		Organization org = organizationDubboService.getFullOrgById(step
				.getTarget().getId());
		PropertyDict dict = propertyDictDubboService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationType.ORG_TYPE_KEY,
						OrganizationType.FUNCTIONAL_ORG).get(0);
		return organizationDubboService.findChildOrgsByParentIdAndName(dict,
				org.getParentOrg().getId(), tag, exceptIds, exceptDeptNos,
				pageIndex, rows);
	}

	private PageInfo<AutoCompleteData> findAssignAdminTargetsByName(String tag,
			Long[] exceptIds, ThreeRecordsIssueStep step, int page, int rows) {
		PropertyDict dict = propertyDictDubboService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationType.ORG_TYPE_KEY,
						OrganizationType.ADMINISTRATIVE_REGION).get(0);
		Organization o = organizationDubboService.getFullOrgById(step
				.getTarget().getId());
		if (o != null
				&& o.getOrgLevel().getInternalId() == OrganizationLevel.DISTRICT)
			return organizationDubboService.findChildOrgsByParentIdAndName(
					dict, o.getParentOrg().getId(), tag, exceptIds, null, page,
					rows);
		else

			return organizationDubboService.findChildOrgsByParentIdAndName(
					dict, step.getTarget().getId(), tag, exceptIds, null, page,
					rows);
	}

	private PageInfo<AutoCompleteData> findSubmitAdminTargetsByName(String tag,
			Long[] exceptIds, ThreeRecordsIssueStep step) {
		if (step == null || step.getTarget() == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		Organization org = organizationDubboService.getFullOrgById(step
				.getTarget().getId());
		// 台账办
		if (org.getParentOrg() == null
				|| inExceptOrg(org.getParentOrg().getId(), exceptIds)) {
			return createEmptyAutoCompleteDataPage();
		} else if (org.getOrgLevel().getInternalId() != OrganizationLevel.TOWN) {
			PageInfo<AutoCompleteData> result = createAutoCompleteDataPage(1,
					1, 1, new ArrayList<AutoCompleteData>());
			result.getResult().add(
					convertToAutoCompleteData(organizationDubboService
							.getSimpleOrgById(org.getParentOrg().getId())));
			return result;
		} else {
			Organization queryOrganization = null;
			Organization parent = organizationDubboService.getSimpleOrgById(org
					.getParentOrg().getId());
			if (ThreeRecordsIssueOperationUtil.orgIsFunctional(org)) {
				queryOrganization = parent;
			} else {
				queryOrganization = organizationDubboService
						.getOrgByDepartmentNo(parent.getDepartmentNo()
								+ ThreeRecordsIssueConstants.COUNTY_LEDGER);
			}
			if (queryOrganization == null) {
				return createEmptyAutoCompleteDataPage();
			}
			PageInfo<AutoCompleteData> result = createAutoCompleteDataPage(1,
					1, 1, new ArrayList<AutoCompleteData>());
			result.getResult()
					.add(convertToAutoCompleteData(queryOrganization));
			return result;
		}
	}

	private PageInfo<AutoCompleteData> findTurnAdminTargetsByName(String tag,
			Long[] exceptIds, ThreeRecordsIssueStep step, int page, int rows) {
		Organization org = step.getTarget();
		if (org.getParentOrg() == null
				|| inExceptOrg(org.getParentOrg().getId(), exceptIds)) {
			return createEmptyAutoCompleteDataPage();
		} else {

			PageInfo<AutoCompleteData> result = createAutoCompleteDataPage(1,
					1, 1, new ArrayList<AutoCompleteData>());
			result.getResult().add(
					convertToAutoCompleteData(organizationDubboService
							.getSimpleOrgById(org.getParentOrg().getId())));

			PropertyDict dict = propertyDictDubboService
					.findPropertyDictByDomainNameAndInternalId(
							OrganizationType.ORG_TYPE_KEY,
							OrganizationType.ADMINISTRATIVE_REGION).get(0);
			return organizationDubboService.findChildOrgsByParentIdAndName(
					dict, step.getTarget().getId(), tag, exceptIds, null, page,
					rows);
		}
	}

	private PageInfo<AutoCompleteData> findDeclareAdminTargetsByName(
			String tag, Long[] exceptIds, boolean twice,
			ThreeRecordsIssueStep step) {
		Organization org = step.getTarget();
		if (org.getParentOrg() == null
				|| inExceptOrg(org.getParentOrg().getId(), exceptIds)) {
			return createEmptyAutoCompleteDataPage();
		} else {
			Organization parent = organizationDubboService.getSimpleOrgById(org
					.getParentOrg().getId());
			Organization departmentOrg = organizationDubboService
					.getOrgByDepartmentNo(parent.getDepartmentNo()
							+ (twice ? ThreeRecordsIssueConstants.COUNTY_COMMITTEE
									: ThreeRecordsIssueConstants.COUNTY_JOINT));
			if (departmentOrg == null) {
				return createEmptyAutoCompleteDataPage();
			}
			PageInfo<AutoCompleteData> result = createAutoCompleteDataPage(1,
					1, 1, new ArrayList<AutoCompleteData>());
			result.getResult().add(convertToAutoCompleteData(departmentOrg));
			return result;
		}
	}

	private PageInfo<AutoCompleteData> findSubmitFunctionTargetsByName(
			String tag, Long[] exceptIds, ThreeRecordsIssueStep step,
			int pageIndex, int rows) {
		Organization org = organizationDubboService.getFullOrgById(step
				.getTarget().getId());
		if (org.getParentOrg() == null) {
			return createEmptyAutoCompleteDataPage();
		}

		// 注释内容为镇部门处理台账时选择执能部门的上级职能部门
		// else if (OrganizationType.FUNCTIONAL_ORG
		// == org.getOrgType() .getInternalId()) { List<AutoCompleteData> list =
		// organizationDubboService .findParentFunOrgsByIdAndName(org.getId(),
		// tag, exceptIds); int size = (list == null || list.size() == 0) ? 0 :
		// list.size(); return createAutoCompleteDataPage(pageIndex, rows, size,
		// list);
		//		 
		// }

		else {
			PropertyDict dict = propertyDictDubboService
					.findPropertyDictByDomainNameAndInternalId(
							OrganizationType.ORG_TYPE_KEY,
							OrganizationType.FUNCTIONAL_ORG).get(0);
			return organizationDubboService.findChildOrgsByParentIdAndName(
					dict, org.getParentOrg().getId(), tag, exceptIds, null,
					pageIndex, rows);
		}
	}

	private void insertOrgToPages(Organization org,
			PageInfo<AutoCompleteData> datas) {
		datas.getResult().add(0, convertToAutoCompleteData(org));
		datas.setPerPageSize(datas.getResult().size());
		datas.setTotalRowSize(datas.getResult().size());
	}

	private Organization loadFullCurrentLoginedOrganization() {
		return loadFullOrganization(ThreadVariable.getSession()
				.getOrganization().getId());
	}

	private List<Organization> loadFullOrganizations(Long[] tells) {
		List<Organization> result = new ArrayList<Organization>();
		if (tells != null && tells.length > 0) {
			for (Long id : tells) {
				result.add(loadFullOrganization(id));
			}
		}
		return result;
	}

	private Organization loadFullOrganization(Long id) {
		return null == id ? null : organizationDubboService.getFullOrgById(id);
	}

	private ThreeRecordsIssueStep addAndReloadFullIssueStep(
			ThreeRecordsIssueStep step, Long dealType) {
		step.setDealType(dealType);
		fetchSourceAndTargetOrg(step, step);
		ThreeRecordsIssueStep result = threeRecordsIssueProcessService
				.addLedgerStep(step);
		fetchSourceAndTargetOrg(result, step);
		return result;
	}

	private ThreeRecordsIssueStep getSimpleIssueStepById(Long stepId) {
		return threeRecordsIssueProcessService.getSimpleIssueStepById(stepId);
	}

	private ThreeRecordsIssueStepGroup saveStepGroup(
			ThreeRecordsIssueStepGroup steps, Long dealType) {

		steps
				.setKeyStep(addAndReloadFullIssueStep(steps.getKeyStep(),
						dealType));
		steps.setLedgerId(steps.getKeyStep().getLedgerId());
		if (steps.hasIncidentalStep()) {
			for (int index = 0; index < steps.getIncidentalSteps().size(); index++) {
				steps.getIncidentalSteps().set(
						index,
						addAndReloadFullIssueStep(steps.getIncidentalSteps()
								.get(index), dealType));
			}
		}
		return steps;
	}

	private void saveGroupId(ThreeRecordsIssueStepGroup steps) {
		ThreeRecordsIssueStep keyStep = steps.getKeyStep();
		List<ThreeRecordsIssueStep> incidentalSteps = steps
				.getIncidentalSteps();
		if (null != keyStep) {
			keyStep.setGroupId(steps.getId());
			threeRecordsIssueProcessService.updateGroupId(keyStep);
		}
		if (null != incidentalSteps && incidentalSteps.size() > 0) {
			for (ThreeRecordsIssueStep step : incidentalSteps) {
				step.setGroupId(steps.getId());
				threeRecordsIssueProcessService.updateGroupId(step);
			}
		}
	}

	private void fireIssueGroup(ThreeRecordsIssueStepGroup issueStepGroup) {
		issueStepGroup.setEntyLog(issueLogService.getLogsByStepId(
				issueStepGroup.getKeyStep().getId()).get(0));
		issueStepGroup.setLedgerType(issueStepGroup.getKeyStep()
				.getLedgerType());
		ThreeRecordsIssueStepGroup stepGroup = threeRecordsIssueProcessService
				.addIssueStepGroup(issueStepGroup);
		stepGroup.setIncidentalSteps(issueStepGroup.getIncidentalSteps());
		saveGroupId(stepGroup);
		List<ThreeRecordsIssueStepGroup> list = threeRecordsIssueProcessService
				.getIssueStepGroupByIssueId(stepGroup.getLedgerId(), stepGroup
						.getLedgerType());
		if (null != list && list.size() > 1) {
			ThreeRecordsIssueStepGroup threeRecordsIssueStepGroup = list
					.get(list.size() - 2);
			threeRecordsIssueStepGroup.setOutLog(issueStepGroup.getEntyLog());
			threeRecordsIssueProcessService
					.updateOutLog(threeRecordsIssueStepGroup);
		}
		updateStepStateCodeBySupportOrNotice(stepGroup);
	}

	private void updateStepStateCodeBySupportOrNotice(
			ThreeRecordsIssueStepGroup stepGroup) {
		if (stepGroup == null || !stepGroup.hasIncidentalStep()) {
			return;
		}
		List<ThreeRecordsIssueStep> steps = threeRecordsIssueProcessService
				.getStepSupportByLedgerIdAndType(stepGroup.getLedgerId(),
						stepGroup.getLedgerType());
		if (steps == null || steps.size() == 0) {
			return;
		}
		List<Long> stepIds = new ArrayList<Long>();
		for (ThreeRecordsIssueStep step : stepGroup.getIncidentalSteps()) {
			for (ThreeRecordsIssueStep issueStep : steps) {
				if (step.getTarget() == null
						|| issueStep.getTarget() == null
						|| !(step.getTarget().getId().equals(issueStep
								.getTarget().getId()))
						|| step.getIsSupported() != issueStep.getIsSupported()) {
					continue;
				}
				if (!step.getId().equals(issueStep.getId())) {
					stepIds.add(issueStep.getId());
				}
			}
		}
		if (stepIds.size() > 0) {
			threeRecordsIssueProcessService
					.updateStepStateCodeBySupportOrNotice(stepIds);
		}
	}

	private ThreeRecordsIssueOperationContext constructIssueOperationContext(
			Organization target, List<Organization> tells,
			List<Organization> notices) {
		Organization org = organizationDubboService
				.getFullOrgById(ThreadVariable.getSession().getOrganization()
						.getId());
		ThreeRecordsIssueOperationContext context = new ThreeRecordsIssueOperationContext(
				org, target, "", ThreadVariable.getSession().getUserRealName());
		if (tells != null && tells.size() > 0) {
			for (Organization tellorg : tells) {
				context.addTellOrg(tellorg);
			}
		}
		if (notices != null && notices.size() > 0) {
			for (Organization noticeOrg : notices) {
				context.addNoticeOrg(noticeOrg);
			}
		}
		return context;
	}

	private void fetchSourceAndTargetOrg(ThreeRecordsIssueStep step,
			ThreeRecordsIssueStep oldStep) {
		if (step.getSource().getId() < 0) {
			step.setSource(oldStep.getSource());
		} else {
			step.setSource(organizationDubboService.getSimpleOrgById(step
					.getSource().getId()));
		}
		if (step.getTarget().getId() < 0) {
			step.setTarget(oldStep.getTarget());
		} else {
			step.setTarget(organizationDubboService.getSimpleOrgById(step
					.getTarget().getId()));
		}
	}

	private void fetchFullSourceAndTargetOrg(ThreeRecordsIssueStep step) {
		step.setSource(loadFullOrganization(step.getSource().getId()));
		step.setTarget(loadFullOrganization(step.getTarget().getId()));
	}

	private void fireIssueEntry(BaseWorking baseWork, ThreeRecordsIssueStep step) {
		if (getIssueChangeListeners() != null) {
			for (ThreeRecordsIssueChangeListener listener : getIssueChangeListeners()) {
				listener.onEntry(baseWork, step);
			}
		}
	}

	private void fireIssueComplete(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		ThreeRecordsIssueChangeEvent event = new ThreeRecordsIssueChangeEvent(
				log, attachFiles, ThreeRecordsIssueOperate.COMPLETE);
		if (getIssueChangeListeners() != null) {
			for (ThreeRecordsIssueChangeListener listener : getIssueChangeListeners()) {
				listener.onComplete(baseWork, step, event);
			}
		}
	}

	@Override
	public void fireIssueChanged(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueOperate operate,
			ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		ThreeRecordsIssueChangeEvent event = new ThreeRecordsIssueChangeEvent(
				log, attachFiles, operate);
		ThreeRecordsIssueStepGroup steps = new ThreeRecordsIssueStepGroup();
		steps.setKeyStep(step);
		if (getIssueChangeListeners() != null) {
			for (ThreeRecordsIssueChangeListener listener : getIssueChangeListeners()) {
				listener.onChanged(baseWork, steps, event);
			}
		}
	}

	private void fireIssueChanged(BaseWorking baseWork,
			ThreeRecordsIssueStepGroup steps, ThreeRecordsIssueOperate operate,
			ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		ThreeRecordsIssueChangeEvent event = new ThreeRecordsIssueChangeEvent(
				log, attachFiles, operate);
		if (getIssueChangeListeners() != null) {
			for (ThreeRecordsIssueChangeListener listener : getIssueChangeListeners()) {
				listener.onChanged(baseWork, steps, event);
			}
		}

	}

	/**
	 * 台账上报时候，获取IssueOperationContext信息
	 * 
	 * @param targetOrg
	 * @param tells
	 *            （上报需要协办的部门信息列表）
	 * @return
	 */
	private ThreeRecordsIssueOperationContext constructOperationContext(
			Long targetOrg, List<Organization> tells, List<Organization> notices) {
		Organization target = loadFullOrganization(targetOrg);
		ThreeRecordsIssueOperationContext context = constructIssueOperationContext(
				target, tells, notices);
		return context;
	}

	@Override
	public ThreeRecordsIssueStep turn(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log,
			Long targetOrg, Long[] tells,
			List<ThreeRecordsIssueAttachFile> attachFiles, Long[] notices) {
		if (baseWork == null || step == null || log == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		try {
			ThreeRecordsIssueOperationContext context = constructOperationContext(
					targetOrg, loadFullOrganizations(tells),
					loadFullOrganizations(notices));
			ThreeRecordsIssueStepGroup steps = saveStepGroup(
					ThreeRecordsIssueHelper.constructIssueStateFromStep(step)
							.turn(baseWork, step, context), log.getDealType());
			threeRecordsIssueProcessService.updateIssueStepExceptOrg(step);
			fireIssueChanged(baseWork, steps, ThreeRecordsIssueOperate.TURN,
					log, attachFiles);
			fireIssueGroup(steps);
			updateTurnForm(baseWork, log, step.getId());
			return steps.getKeyStep();
		} catch (Exception e) {
			throw new ServiceValidationException("转办过程中发生错误", e);
		}
	}

	@Override
	public ThreeRecordsIssueStep declare(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log,
			Long targetOrg, Long[] tells,
			List<ThreeRecordsIssueAttachFile> attachFiles, Long[] notices) {
		if (baseWork == null || step == null || log == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		try {
			ThreeRecordsIssueOperationContext context = constructOperationContext(
					targetOrg, loadFullOrganizations(tells),
					loadFullOrganizations(notices));
			ThreeRecordsIssueStepGroup steps = saveStepGroup(
					ThreeRecordsIssueHelper.constructIssueStateFromStep(step)
							.declare(baseWork, step, context), log
							.getDealType());
			step.setSubmit(ThreeRecordsIssueSourceState.submit);
			threeRecordsIssueProcessService.updateIssueStepExceptOrg(step);
			fireIssueChanged(baseWork, steps, ThreeRecordsIssueOperate.DECLARE,
					log, attachFiles);
			fireIssueGroup(steps);
			updateDeclareForm(baseWork, log, step.getId());
			return steps.getKeyStep();
		} catch (Exception e) {
			throw new ServiceValidationException("申报过程中发生错误", e);
		}
	}

	@Override
	public ThreeRecordsIssueStep support(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		if (baseWork == null || step == null || log == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		try {
			ThreeRecordsIssueOperationContext context = constructIssueOperationContext(
					loadFullCurrentLoginedOrganization(), null, null);
			ThreeRecordsIssueHelper.constructIssueStateFromStep(step).support(
					baseWork, step, context);
			step = getFullIssueStepById(threeRecordsIssueProcessService
					.updateIssueStepExceptOrg(step).getId());
			fireIssueChanged(baseWork, step, ThreeRecordsIssueOperate.SUPPORT,
					log, new ArrayList<ThreeRecordsIssueAttachFile>());
			return step;
		} catch (Exception e) {
			throw new ServiceValidationException("协助办理过程中发生错误", e);
		}
	}

	@Override
	public ThreeRecordsIssueStep programComplete(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		if (baseWork == null || step == null || log == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		try {
			ThreeRecordsIssueOperationContext context = constructIssueOperationContext(
					step.getTarget(), null, null);
			ThreeRecordsIssueStepGroup steps = saveStepGroup(
					ThreeRecordsIssueHelper.constructIssueStateFromStep(step)
							.programComplete(baseWork, step, context), log
							.getDealType());
			if (steps.getKeyStep() != null
					&& steps.getKeyStep().getSource().getId().intValue() == steps
							.getKeyStep().getTarget().getId().intValue())
				step.setIsFeedBack(0);
			threeRecordsIssueProcessService.updateIssueStepExceptOrg(step);
			fireIssueChanged(baseWork, steps,
					ThreeRecordsIssueOperate.PROGRAM_COMPLETE, log, attachFiles);
			ThreeRecordsIssueStep result = getFullIssueStepById(step.getId());
			fireIssueGroup(steps);
			return result;
		} catch (Exception e) {
			throw new ServiceValidationException("程序性办结过程中发生错误", e);
		}
	}

	@Override
	public ThreeRecordsIssueStep periodComplete(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		if (baseWork == null || step == null || log == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		try {
			ThreeRecordsIssueOperationContext context = constructIssueOperationContext(
					step.getTarget(), null, null);
			ThreeRecordsIssueStepGroup steps = saveStepGroup(
					ThreeRecordsIssueHelper.constructIssueStateFromStep(step)
							.periodComplete(baseWork, step, context), log
							.getDealType());
			if (steps.getKeyStep() != null
					&& steps.getKeyStep().getSource().getId().intValue() == steps
							.getKeyStep().getTarget().getId().intValue())
				step.setIsFeedBack(0);
			threeRecordsIssueProcessService.updateIssueStepExceptOrg(step);
			fireIssueChanged(baseWork, steps,
					ThreeRecordsIssueOperate.PERIOD_COMPLETE, log, attachFiles);
			ThreeRecordsIssueStep result = getFullIssueStepById(step.getId());
			fireIssueGroup(steps);
			return result;
		} catch (Exception e) {
			throw new ServiceValidationException("阶段办结过程中发生错误", e);
		}
	}

	@Override
	public PageInfo<AutoCompleteData> findNoticeTargetsByName(Long stepid,
			ThreeRecordsIssueOperate operate, String tag, Long[] exceptIds,
			int page, int rows) {
		if (operate == null || stepid == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		ThreeRecordsIssueStep step = getFullIssueStepById(stepid);
		if (ThreeRecordsIssueOperate.ASSIGN.equals(operate)
				|| ThreeRecordsIssueOperate.LEVEL_ASSIGN.equals(operate)) {
			return findAssignNoticesByName(tag, exceptIds, step, page, rows);
		} else if (ThreeRecordsIssueOperate.TURN.equals(operate)) {
//			return findTurnFunctionTargetsByName(tag, exceptIds, ThreeRecordsIssueConstants.COUNTY_EXCEPT, step,
//					page, rows);
			return findTurnFunctionTargetsByName(null, tag, exceptIds,
					ThreeRecordsIssueConstants.COUNTY_EXCEPT, step, page,
					rows);
		}
		return createEmptyAutoCompleteDataPage();
	}

	/*
	 * 根据台账基本信息,工作措施更新呈报单
	 */
	private ReportForm updateReportForm(BaseWorking baseWork,
			ThreeRecordsIssueLogNew log, Long keyId) {
		if (baseWork == null || log == null) {
			throw new BusinessValidationException("参数错误！");
		}
		ReportForm reportForm = reportFormService
				.getSimpleReportFormByStepId(keyId);
		if (reportForm == null) {
			return null;
		}
		reportForm.setTargetOrg(log.getTargeOrg());
		reportForm.setSourceOrg(log.getDealOrg());
		reportForm.setLedgerId(log.getLedgerId());
		reportForm.setLedgerSerialNumber(baseWork.getSerialNumber());
		reportForm.setLedgerType(Long.valueOf(log.getLedgerType()));
		if (log.getIssueStep() != null) {
			reportForm.setStepId(log.getIssueStep().getId());
		}
		reportForm = reportFormService.updateReportForm(reportForm);
		return reportForm;
	}

	/**
	 * 根据基本台账，操作措施更新转办单
	 * 
	 */
	private TurnForm updateTurnForm(BaseWorking baseWork,
			ThreeRecordsIssueLogNew log, Long stepId) {
		if (baseWork == null || log == null) {
			throw new BusinessValidationException("参数错误！");
		}
		TurnForm turnForm = turnFormService.getSimpleTurnFormByStepId(stepId);
		if (turnForm == null) {
			return null;
		}
		turnForm.setLedgerId(log.getLedgerId());
		turnForm.setLedgerType(Long.valueOf(log.getLedgerType()));
		turnForm.setLedgerSerialNumber(baseWork.getSerialNumber());
		turnForm.setTargetOrg(log.getTargeOrg());
		if (log.getIssueStep() != null) {
			turnForm.setStepId(log.getIssueStep().getId());
		}
		turnForm = turnFormService.updateTurnForm(turnForm);
		return turnForm;
	}

	/**
	 * 根据基本台账，操作措施更新申报单信息
	 */
	private void updateDeclareForm(BaseWorking baseWork,
			ThreeRecordsIssueLogNew log, Long keyId) {
		if (baseWork == null || log == null || keyId == null) {
			throw new BusinessValidationException("参数错误！");
		}
		if (log.getIssueStep() != null && log.getIssueStep().getId() != null) {
			DeclareForm declareForm = declareFormService
					.getSimpleDeclareFormByStepId(keyId);
			if (declareForm == null) {
				return;
			}
			declareForm.setLedgerId(log.getLedgerId());
			declareForm.setLedgerType(Long.valueOf(log.getLedgerType()));
			declareForm.setLedgerSerialNumber(baseWork.getSerialNumber());
			if (log.getIssueStep() != null) {
				declareForm.setStepId(log.getIssueStep().getId());
			}
			declareForm.setTargetOrg(log.getTargeOrg());
			declareForm.setSourceOrg(log.getDealOrg());
			declareForm.setLedgerSerialNumber(baseWork.getSerialNumber());
			declareFormService.updateDeclareForm(declareForm);

		}
	}

	/**
	 * 根据基本台账，操作措施,协办部门更新交办单（县委，县联席生成交办单）
	 */
	private AssignForm updateAssignForm(BaseWorking baseWork,
			ThreeRecordsIssueLogNew log, Long[] tells, Long groupId, Long keyId) {
		if (baseWork == null || log == null || log.getDealOrg() == null
				|| log.getDealOrg().getId() == null) {
			throw new BusinessValidationException("参数错误！");
		}
		Organization dealOrg = organizationDubboService.getSimpleOrgById(log
				.getDealOrg().getId());
		int type = LedgerConstants.COUNTY_NO_ASSIGN_TYPE;
		if (dealOrg.getDepartmentNo().endsWith(
				ThreeRecordsIssueConstants.COUNTY_JOINT)) {
			type = LedgerConstants.COUNTY_JOINT_ASSIGN_TYPE;
		} else if (dealOrg.getDepartmentNo().endsWith(
				ThreeRecordsIssueConstants.COUNTY_COMMITTEE)) {
			type = LedgerConstants.COUNTY_COMMITTEE_ASSIGN_TYPE;
		}
		if (type == LedgerConstants.COUNTY_NO_ASSIGN_TYPE) {
			return null;
		}

		AssignForm assignForm = assignFormService
				.getSimpleAssignFormByStepId(keyId);
		if (assignForm == null) {
			return null;
		}
		assignForm.setLedgerId(log.getLedgerId());
		assignForm.setLedgerType(Long.valueOf(log.getLedgerType()));
		assignForm.setLedgerSerialNumber(baseWork.getSerialNumber());
		assignForm.setAssignType(type);
		// assignForm.setHandleContent(log.getContent());
		assignForm.setSourceOrg(log.getDealOrg());
		List<AssignFormReceiver> receivers = assignFormReceiverService
				.findAssignFormReceiversByAssignId(assignForm.getId());
		if (receivers != null && receivers.size() > 0) {
			List<ThreeRecordsIssueStep> steps = threeRecordsIssueProcessService
					.getIssueStepByGroupId(groupId);
			ThreeRecordsIssueStep step = null;
			for (AssignFormReceiver receiver : receivers) {
				step = getStepByOrgId(steps, receiver.getTargetOrg().getId());
				if (step == null) {
					continue;
				}
				receiver.setIsManage(receiver.getTargetOrg().getId().equals(
						log.getTargeOrg().getId()));
				receiver.setStepId(step.getId());
			}
		}

		if (log.getIssueStep() != null) {
			assignForm.setStepId(log.getIssueStep().getId());
		}
		assignForm.setReceivers(receivers);
		assignForm = assignFormService.updateAssignForm(assignForm);
		return assignForm;
	}

	private ThreeRecordsIssueStep getStepByOrgId(
			List<ThreeRecordsIssueStep> steps, Long orgId) {
		if (orgId == null || steps == null) {
			return null;
		}
		for (ThreeRecordsIssueStep step : steps) {
			if (step.getTarget() == null || step.getTarget().getId() == null) {
				continue;
			}
			if (step.getTarget().getId().intValue() == orgId.intValue()) {
				return step;
			}
		}
		return null;
	}

	private void conceptForm(ThreeRecordsIssueStep step,
			ThreeRecordsIssueLogNew log) {
		if (step == null || log == null || step.getDealType() == null
				|| log.getDealOrg() == null || log.getDealOrg().getId() == null) {
			throw new BusinessValidationException("参数错误！");
		}
		if (step.getDealType().intValue() == ThreeRecordsIssueOperate.TURN
				.getCode()) {
			TurnForm turnForm = turnFormService.getSimpleTurnFormByStepId(step
					.getId());
			if (turnForm != null) {
				turnForm.setMobile(log.getMobile());
				turnForm.setName(log.getDealUserName());
				turnForm.setReceiveDate(log.getCreateDate());
				turnFormService.updateTurnForm(turnForm);
			}
		} else if (step.getDealType().intValue() == ThreeRecordsIssueOperate.ASSIGN
				.getCode()) {
			AssignFormReceiver assignFormReceiver = assignFormReceiverService
					.getSimpleAssignFormReceiverById(step.getId(), log
							.getDealOrg().getId());
			if (assignFormReceiver != null) {
				assignFormReceiver.setMobile(log.getMobile());
				assignFormReceiver.setName(log.getDealUserName());
				assignFormReceiver.setReceiveDate(new Date());
				assignFormReceiverService
						.updateAssignFormReceiver(assignFormReceiver);
			}
		}

	}


	private PageInfo<AutoCompleteData> findAssignNoticesByName(String tag,
			Long[] exceptIds, ThreeRecordsIssueStep step, int page, int rows) {
		Organization operationOrg = organizationDubboService
				.getFullOrgById(ThreadVariable.getSession().getOrganization()
						.getId());

		return organizationDubboService.findChildOrgsByParentIdAndName(null,
				operationOrg.getParentOrg().getId(), tag, exceptIds, ThreeRecordsIssueConstants.LX_XW_EXCEPT,
				page, rows);
	}
}
