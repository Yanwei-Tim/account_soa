package com.tianque.plugin.account.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.constants.ThreeRecordsIssueAttachFileType;
import com.tianque.plugin.account.constants.ThreeRecordsIssueTag;
import com.tianque.plugin.account.constants.ThreeRecordsIssueViewType;
import com.tianque.plugin.account.dao.ThreeRecordsIssueDao;
import com.tianque.plugin.account.dao.ThreeRecordsIssueLogDao;
import com.tianque.plugin.account.dao.ThreeRecordsIssueProcessDao;
import com.tianque.plugin.account.domain.BaseWorking;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.domain.ThreeRecordsIssueMap;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStepGroup;
import com.tianque.plugin.account.service.ThreeRecordsIssueLogService;
import com.tianque.plugin.account.service.ThreeRecordsIssueProcessService;
import com.tianque.plugin.account.service.ThreeRecordsIssueService;
import com.tianque.plugin.account.service.ThreeRecordsIssueWorkFlowEngine;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;
import com.tianque.plugin.account.state.ThreeRecordsIssueSourceState;
import com.tianque.plugin.account.state.ThreeRecordsIssueState;
import com.tianque.plugin.account.util.CollectionUtil;
import com.tianque.plugin.account.util.ComparisonAttribute;
import com.tianque.plugin.account.util.DealYearOrMonthUtil;
import com.tianque.plugin.account.util.ThreeRecordsIssueOperationUtil;
import com.tianque.plugin.account.validate.ThreeRecordsIssueOperationLogValidator;
import com.tianque.plugin.account.vo.LedgerAttachFileReturnVo;
import com.tianque.plugin.account.vo.ThreeRecordsIssueViewObject;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;
import com.tianque.userAuth.api.PropertyDictDubboService;

@Transactional
public abstract class ThreeRecordsAbstractIssueServiceImpl extends
		AbstractBaseService implements ThreeRecordsIssueService {

	protected abstract ThreeRecordsIssueOperationLogValidator getIssueLogValidator();

	protected abstract ThreeRecordsIssueWorkFlowEngine getIssueWorkFlowEngine();

	@Autowired
	protected ThreeRecordsIssueDao issueDao;
	@Autowired
	protected ThreeRecordsIssueProcessService issueProcessService;
	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;
	@Autowired
	private PropertyDictDubboService propertyDictDubboService;
	@Autowired
	protected ThreeRecordsIssueLogService issueLogService;
	@Autowired
	protected ThreeRecordsIssueProcessDao issueProcessDao;
	@Autowired
	protected ThreeRecordsIssueLogDao threeRecordsIssueLogDao;

	@Override
	public boolean deleteIssueByIssueId(Long issueId, int lederType) {
		try {
			removeIssueAllAttachFiles(issueId, lederType);
			issueLogService.deleteIssueLogByLedgerIdAndLedgerType(issueId,
					lederType);
			getIssueWorkFlowEngine().unRegister(issueId, lederType);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ThreeRecordsAbstractIssueServiceImpl的deleteIssueByIssueId方法出现异常，原因：",
					"删除台账信息出现错误", e);
		}
		return true;
	}

	@Override
	public ThreeRecordsIssueViewObject complete(Long stepId,
			ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		if (stepId == null || log == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		ThreeRecordsIssueStep step = getIssueWorkFlowEngine()
				.getFullIssueStepById(stepId);
		autoFillIssueLogProperty(log, ThreeRecordsIssueOperate.COMPLETE);
		validateOperationLog(ThreeRecordsIssueOperate.COMPLETE, log,
				attachFiles);
		BaseWorking bw = getSimpleBaseWorkByStepId(stepId);
		ThreeRecordsIssueStep newStep = getIssueWorkFlowEngine().complete(bw,
				step, log, attachFiles);
		bw = getSimpleBaseWorkByStepId(stepId);
		return createIssueViewObject(bw, newStep);
	}

	@Override
	public ThreeRecordsIssueViewObject submit(Long stepId,
			ThreeRecordsIssueLogNew log, Long submitTarget, Long[] tells,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		if (stepId == null || log == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		autoFillIssueLogProperty(log, ThreeRecordsIssueOperate.SUBMIT);
		validateOperationLog(ThreeRecordsIssueOperate.SUBMIT, log, attachFiles);
		BaseWorking baseWorking = getSimpleBaseWorkByStepId(stepId);
		ThreeRecordsIssueStep step = getIssueWorkFlowEngine()
				.getFullIssueStepById(stepId);
		ThreeRecordsIssueStep newStep = getIssueWorkFlowEngine().submit(
				baseWorking, step, log, submitTarget, tells, attachFiles, null);
		baseWorking = getSimpleBaseWorkByStepId(stepId);
		return createIssueViewObject(baseWorking, newStep);
	}

	@Override
	public ThreeRecordsIssueViewObject assign(Long stepId,
			ThreeRecordsIssueLogNew log, Long targetOrg, Long[] tells,
			List<ThreeRecordsIssueAttachFile> attachFiles, Long[] notices) {
		if (stepId == null || log == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		autoFillIssueLogProperty(log, ThreeRecordsIssueOperate.ASSIGN);
		validateOperationLog(ThreeRecordsIssueOperate.ASSIGN, log, attachFiles);
		ThreeRecordsIssueStep step = getIssueWorkFlowEngine()
				.getFullIssueStepById(stepId);
		if (null != log.getIssueStep()) {
			step.setItemTypeId(log.getIssueStep().getItemTypeId());
		}
		BaseWorking baseWorking = getSimpleBaseWorkByStepId(stepId);
		ThreeRecordsIssueStep newStep = getIssueWorkFlowEngine().assign(
				baseWorking, step, log, targetOrg, tells, attachFiles, notices);
		baseWorking = getSimpleBaseWorkByStepId(stepId);
		return createIssueViewObject(baseWorking, newStep);
	}

	@Override
	public ThreeRecordsIssueViewObject concept(Long stepId,
			ThreeRecordsIssueLogNew log) {
		if (stepId == null || log == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		autoFillIssueLogProperty(log, ThreeRecordsIssueOperate.CONCEPT);
		validateOperationLog(ThreeRecordsIssueOperate.CONCEPT, log, null);
		BaseWorking baseWorking = getSimpleBaseWorkByStepId(stepId);
		ThreeRecordsIssueStep step = getIssueWorkFlowEngine()
				.getFullIssueStepById(stepId);
		ThreeRecordsIssueStep newStep = getIssueWorkFlowEngine().concept(
				baseWorking, step, log);
		baseWorking = getSimpleBaseWorkByStepId(stepId);
		return createIssueViewObject(baseWorking, newStep);
	}

	@Override
	public ThreeRecordsIssueViewObject comment(Long stepId,
			ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		if (stepId == null || log == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		autoFillIssueLogProperty(log, ThreeRecordsIssueOperate.COMMENT);
		validateOperationLog(ThreeRecordsIssueOperate.COMMENT, log, attachFiles);
		ThreeRecordsIssueStep step = getIssueWorkFlowEngine()
				.getFullIssueStepById(stepId);
		BaseWorking baseWorking = getSimpleBaseWorkByStepId(stepId);
		ThreeRecordsIssueStep newStep = getIssueWorkFlowEngine().comment(
				baseWorking, step, log, attachFiles);
		baseWorking = getSimpleBaseWorkByStepId(stepId);
		return createIssueViewObject(baseWorking, newStep);
	}

	@Override
	public ThreeRecordsIssueViewObject back(Long stepId,
			ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		if (stepId == null || log == null || log.getDealOrg() == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		log.setDealType(Long.valueOf(ThreeRecordsIssueOperate.BACK.getCode()));
		log.setDealTime(CalendarUtil.now());
		validateOperationLog(ThreeRecordsIssueOperate.BACK, log, attachFiles);
		BaseWorking baseWorking = getSimpleBaseWorkByStepId(stepId);
		ThreeRecordsIssueStep step = getIssueWorkFlowEngine()
				.getFullIssueStepById(stepId);
		ThreeRecordsIssueStep newStep = getIssueWorkFlowEngine().back(
				baseWorking, step, log, attachFiles);
		baseWorking = getSimpleBaseWorkByStepId(stepId);
		return createIssueViewObject(baseWorking, newStep);
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsFollow(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Integer year,
			Integer month) {
		if (orgId == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		try {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			Organization currentOrg = ThreadVariable.getOrganization();
			if (ThreeRecordsIssueOperationUtil.orgIsFunctional(currentOrg)) {
				org = ThreadVariable.getOrganization();
			}
			List<Long> childOrg = ComparisonAttribute
					.getOrgIds(organizationDubboService
							.findOrganizationsByParentId(org.getId()));
			PageInfo<ThreeRecordsIssueViewObject> pageInfo = issueDao
					.findJurisdictionsFollow(seachValue, org, childOrg, page,
							rows, sidx, sord, issueType,year,month);
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("待跟进过程中发生错误", e);
		}
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsFeedBack(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Integer year,
			Integer month) {
		if (orgId == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		try {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			Organization currentOrg = ThreadVariable.getOrganization();
			if (ThreeRecordsIssueOperationUtil.orgIsFunctional(currentOrg)) {
				org = ThreadVariable.getOrganization();
			}
			List<Long> childOrg = ComparisonAttribute
					.getOrgIds(organizationDubboService
							.findOrganizationsByParentId(org.getId()));
			PageInfo<ThreeRecordsIssueViewObject> pageInfo = issueDao
					.findJurisdictionsFeedBack(seachValue, org, childOrg, page,
							rows, sidx, sord, issueType, year, month);
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("待反馈过程中发生错误", e);
		}
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsSubstanceDone(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Integer year,
			Integer month) {
		if (orgId == null || !StringUtil.isStringAvaliable(seachValue)) {
			throw new BusinessValidationException("参数未获得！");
		}
		try {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			Organization currentOrg = ThreadVariable.getOrganization();
			if (ThreeRecordsIssueOperationUtil.orgIsFunctional(currentOrg)) {
				org = ThreadVariable.getOrganization();
			}
			List<Long> childOrg = ComparisonAttribute
					.getOrgIds(organizationDubboService
							.findOrganizationsByParentId(org.getId()));
			PageInfo<ThreeRecordsIssueViewObject> pageInfo = issueDao
					.findJurisdictionsSubStanceDone(seachValue, org, childOrg,
							page, rows, sidx, sord, issueType, year, month);
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("实质办结过程中发生错误", e);
		}
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsPeriodDone(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Integer year,
			Integer month) {
		if (orgId == null || !StringUtil.isStringAvaliable(seachValue)) {
			throw new BusinessValidationException("参数未获得！");
		}
		try {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			Organization currentOrg = ThreadVariable.getOrganization();
			if (ThreeRecordsIssueOperationUtil.orgIsFunctional(currentOrg)) {
				org = ThreadVariable.getOrganization();
			}
			List<Long> childOrg = ComparisonAttribute
					.getOrgIds(organizationDubboService
							.findOrganizationsByParentId(org.getId()));
			PageInfo<ThreeRecordsIssueViewObject> pageInfo = issueDao
					.findJurisdictionsPeriodDone(seachValue, org, childOrg,
							page, rows, sidx, sord, issueType, year, month);
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("阶段办结进过程中发生错误", e);
		}
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsDone(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Integer year,
			Integer month) {
		if (orgId == null || !StringUtil.isStringAvaliable(seachValue)) {
			throw new BusinessValidationException("参数未获得！");
		}
		Organization org = organizationDubboService.getFullOrgById(orgId);
		Organization currentOrg = ThreadVariable.getOrganization();
		if (ThreeRecordsIssueOperationUtil.orgIsFunctional(currentOrg)) {
			org = ThreadVariable.getOrganization();
		}
		List<Long> childOrg = ComparisonAttribute
				.getOrgIds(organizationDubboService
						.findOrganizationsByParentId(org.getId()));
		PageInfo<ThreeRecordsIssueViewObject> pageInfo = issueDao
				.findJurisdictionsDone(seachValue, org, childOrg, page, rows,
						sidx, sord, issueType, year, month);
		return pageInfo;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsSubmit(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Integer year,
			Integer month) {
		if (orgId == null || !StringUtil.isStringAvaliable(seachValue)) {
			throw new BusinessValidationException("参数未获得！");
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		Organization currentOrg = ThreadVariable.getOrganization();
		if (ThreeRecordsIssueOperationUtil.orgIsFunctional(currentOrg)) {
			org = ThreadVariable.getOrganization();
		}
		List<Long> childOrg = ComparisonAttribute
				.getOrgIds(organizationDubboService
						.findOrganizationsByParentId(org.getId()));
		PageInfo<ThreeRecordsIssueViewObject> pageInfo = issueDao
				.findJurisdictionsSubmit(seachValue, org, childOrg, page, rows,
						sidx, sord, issueType, year, month);
		return pageInfo;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsNeedDo(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Integer isSupported,
			Integer year, Integer month) {
		if (orgId == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		Organization org = organizationDubboService.getFullOrgById(orgId);

		Organization currentOrg = ThreadVariable.getOrganization();
		if (ThreeRecordsIssueOperationUtil.orgIsFunctional(currentOrg)) {
			org = ThreadVariable.getOrganization();
		}
		List<Long> childOrg = ComparisonAttribute
				.getOrgIds(organizationDubboService
						.findOrganizationsByParentId(org.getId()));
		PageInfo<ThreeRecordsIssueViewObject> pageInfo = issueDao
				.findJurisdictionsNeedDo(seachValue, org, childOrg, page, rows,
						sidx, sord, issueType, isSupported,year,month);
		return pageInfo;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsAssgin(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Integer year,
			Integer month) {
		if (orgId == null || !StringUtil.isStringAvaliable(seachValue)) {
			throw new BusinessValidationException("参数未获得！");
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		List<Long> childOrg = ComparisonAttribute
				.getOrgIds(organizationDubboService
						.findOrganizationsByParentId(org.getId()));
		PageInfo<ThreeRecordsIssueViewObject> pageInfo = issueDao
				.findJurisdictionsAssgin(seachValue, org, childOrg, page, rows,
						sidx, sord, issueType,year,month);
		return pageInfo;
	}

	@Override
	public BaseWorking getSimpleBaseWorkByStepId(Long keyId) {
		BaseWorking result = issueDao.getSimpleBaseWorkByStepId(keyId);
		populationIssueProperty(result);
		return result;
	}

	@Override
	public List<ThreeRecordsIssueAttachFile> loadLedgerAndLogAttachFilesByLedgerIdAndType(
			Long ledgerId, int ledgerType) {
		return issueDao.loadIssueAttachFilesByLedgerIdAndLedgerType(ledgerId,
				ledgerType);
	}

	@Override
	public List<ThreeRecordsIssueAttachFile> loadLedgerAttachFilesByLedgerIdAndType(
			Long ledgerId, int type) {
		return issueDao.loadLedgerAttachFilesByLedgerIdAndType(ledgerId, type);
	}

	@Override
	public List<ThreeRecordsIssueLogNew> loadIssueOperationLogsByIssueId(
			Long id, Long ledgerType) {
		List<ThreeRecordsIssueLogNew> list = issueLogService
				.loadOperationLogsByIssueId(id, ledgerType);
		if (list == null || list.size() == 0) {
			return list;
		}
		for (ThreeRecordsIssueLogNew log : list) {
			if (log.getDealOrgId() == null) {
				continue;
			}
			log.setDealOrg(organizationDubboService.getSimpleOrgById(log
					.getDealOrgId()));
		}
		return list;
	}

	@Override
	public ThreeRecordsIssueAttachFile getIssueAttachFileById(Long id) {
		return issueDao.getIssueAttachFileById(id);
	}

	@Override
	public List<ThreeRecordsIssueOperate> getIssueCandoForOrg(Long stepId,
			Organization operateOrg) {
		if (stepId == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		return getIssueWorkFlowEngine().getIssueCandoForOrg(stepId, operateOrg);
	}

	@Override
	public ThreeRecordsIssueStep getIssueStepById(Long stepId) {
		return getIssueWorkFlowEngine().getFullIssueStepById(stepId);
	}

	@Override
	public PageInfo<AutoCompleteData> findAdminTargetsByName(Long stepid,
			String tag, ThreeRecordsIssueOperate operate, Long[] exceptIds,
			boolean twice, int pageIndex, int rows) {
		return getIssueWorkFlowEngine().findAdminTargetsByName(stepid, operate,
				tag, exceptIds, twice, pageIndex, rows);
	}

	@Override
	public PageInfo<AutoCompleteData> findFunctionTargetsByName(Long stepid,
			String tag, ThreeRecordsIssueOperate operate, Long[] exceptIds,
			int pageIndex, int rows) {
		return getIssueWorkFlowEngine().findFunctionTargetsByName(stepid,
				operate, tag, exceptIds, pageIndex, rows);
	}

	@Override
	public PageInfo<AutoCompleteData> findTellTargetsByName(Long stepid,
			String tag, ThreeRecordsIssueOperate operate,
			boolean transferToAdmin, Long[] exceptIds, int page, int rows) {
		return getIssueWorkFlowEngine().findTellTargetsByName(stepid, operate,
				tag, transferToAdmin, exceptIds, page, rows);
	}

	private void autoFillIssueLogProperty(ThreeRecordsIssueLogNew log,
			ThreeRecordsIssueOperate operate) {
		log.setDealType(Long.valueOf(operate.getCode()));
		log.setDealOrg(ThreadVariable.getSession().getOrganization());
		log.getDealOrg().setOrgInternalCode(
				ThreadVariable.getSession().getOrgInternalCode());
		log.setDealTime(CalendarUtil.now());
		if (log.getOperateTime() == null) {
			log.setOperateTime(CalendarUtil.now());
		}
	}

	private void validateOperationLog(ThreeRecordsIssueOperate operate,
			ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		ValidateResult vr = getIssueLogValidator().validate(operate, log,
				attachFiles);
		if (vr.hasError()) {
			throw new BusinessValidationException(vr.getErrorMessages());
		}
	}

	private ThreeRecordsIssueViewObject createIssueViewObject(
			BaseWorking issue, ThreeRecordsIssueStep step) {
		ThreeRecordsIssueViewObject issueViewObject = new ThreeRecordsIssueViewObject();
		copyPropertyFromIssue(issue, issueViewObject);
		issueViewObject.setDealTime(step.getLastDealDate());
		issueViewObject.setSuperviseLevel(step.getSuperviseLevel());
		issueViewObject.setIssueStepId(step.getId());
		issueViewObject.setSupervisionState(step.getSuperviseLevel());
		issueViewObject.setTargetOrg(step.getTarget());
		issueViewObject.setDealState(Long.valueOf(step.getStateCode()));
		return issueViewObject;
	}

	private void populationIssueProperty(BaseWorking issue) {
		if (issue == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		if (issue.getSourceKind() != null
				&& issue.getSourceKind().getId() != null) {
			issue.setSourceKind(propertyDictDubboService
					.getPropertyDictById(issue.getSourceKind().getId()));
		}
		if (issue.getCurrentStep() != null
				&& issue.getCurrentStep().getId() != null) {
			issue.setCurrentStep(getIssueStepById(issue.getCurrentStep()
					.getId()));
		}
		if (issue.getCurrentOrg() != null
				&& issue.getCurrentOrg().getId() != null) {
			issue.setCurrentOrg(organizationDubboService.getSimpleOrgById(issue
					.getCurrentOrg().getId()));
		}
		if (issue.getOccurOrg() != null && issue.getOccurOrg().getId() != null) {
			issue.setOccurOrg(organizationDubboService.getSimpleOrgById(issue
					.getOccurOrg().getId()));
		}
	}

	private void copyPropertyFromIssue(BaseWorking issue,
			ThreeRecordsIssueViewObject issueViewObject) {
		issueViewObject.setIssueId(issue.getId());
		issueViewObject.setSerialNumber(issue.getSerialNumber());
		issueViewObject.setSubject(issue.getSubject());
		issueViewObject.setStatus(issue.getStatus());
		issueViewObject.setCurrentOrg(issue.getCurrentStep() == null ? null
				: issue.getCurrentStep().getTarget());
		issueViewObject.setOccurDate(issue.getOccurDate());
		issueViewObject.setOccurOrg(issue.getOccurOrg());
		issueViewObject.setSourceKind(issue.getSourceKind());
	}

	/***************************************************************************
	 * 删除台账的附件和附件关联关系
	 * 
	 * @param issueId
	 *            台账id
	 */
	private void removeIssueAllAttachFiles(Long issueId, int ledgerType) {
		//		List<ThreeRecordsIssueAttachFile> issueAttachFiles = loadLedgerAndLogAttachFilesByLedgerIdAndType(
		//				issueId, ledgerType);
		//		if (issueAttachFiles != null && issueAttachFiles.size() > 0) {
		//			String webRootPath = FileUtil.getWebRoot();
		//			for (ThreeRecordsIssueAttachFile issueFile : issueAttachFiles) {
		//				File file = new File(webRootPath + File.separator
		//						+ issueFile.getFileActualUrl());
		//				if (file.exists()) {
		//					file.delete();
		//				}
		//			}
		//		}
		try {
			issueDao.removeAllAttachFilesFromBaseFile(issueId, ledgerType);
			issueDao.removeAllAttachFiles(issueId, ledgerType);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ThreeRecordsAbstractIssueServiceImpl的removeIssueAllAttachFiles方法出现异常，原因：",
					"刪除台账附件出现错误", e);
		}
	}

	@Override
	public List<ThreeRecordsIssueMap> getIssueMap(Long issueId, int ledgerType) {
		List<ThreeRecordsIssueStepGroup> stepGroupList = issueProcessService
				.getIssueStepGroupByIssueId(issueId, ledgerType);
		List<ThreeRecordsIssueMap> issueMapList;
		if (null != stepGroupList && stepGroupList.size() > 0) {
			issueMapList = loadIssueMap(stepGroupList);
		} else {
			issueMapList = new ArrayList<ThreeRecordsIssueMap>();
		}
		return issueMapList;
	}

	private List<ThreeRecordsIssueMap> loadIssueMap(
			List<ThreeRecordsIssueStepGroup> stepGroupList) {
		List<ThreeRecordsIssueMap> issueMapList = new ArrayList<ThreeRecordsIssueMap>();
		ThreeRecordsIssueMap issueMap = null;
		Organization organization = null;
		for (int i = 0; i < stepGroupList.size(); i++) {
			ThreeRecordsIssueStepGroup issueStepGroup = stepGroupList.get(i);
			if (i == stepGroupList.size() - 1) {
				issueStepGroup.setOutLog(issueStepGroup.getEntyLog());
			}
			issueMap = issueProcessService
					.getIssueMapByStepGroup(issueStepGroup);
			if (issueMap == null) {
				continue;
			}
			organization = organizationDubboService.getFullOrgById(issueMap
					.getOrgId());
			issueMap.setFunctionalOrg(ThreeRecordsIssueOperationUtil
					.orgIsFunctional(organization));
			issueMap.setName(organization.getOrgName());
			issueMap.setId(issueStepGroup.getId());
			issueMap.setOrgLevelInternalId(Long.valueOf(organization
					.getOrgLevel().getInternalId()));
			if (i + 1 < stepGroupList.size()) {
				issueMap.setTo(stepGroupList.get(i + 1).getId());
			}
			setRelationAndStates(issueMap);
			issueMapList.add(issueMap);
		}
		return issueMapList;
	}

	private void setRelationAndStates(ThreeRecordsIssueMap issueMap) {
		if (issueMap == null || issueMap.getDealType() == null) {
			return;
		}
		ThreeRecordsIssueOperate issueOperate = ThreeRecordsIssueOperate
				.parse((issueMap.getDealType()));
		if (issueOperate == null) {
			return;
		}
		issueMap.setRelation(issueOperate.getDesc());
	}

	public List<ThreeRecordsIssueLogNew> getIssueDealLog(
			ThreeRecordsIssueMap issueMap) {
		ThreeRecordsIssueStepGroup group = issueProcessService
				.getSimpleIssueStepGroupById(issueMap.getId());
		List<ThreeRecordsIssueLogNew> logs = issueLogService
				.findDealLogByMapAndStepGroup(issueMap, group);
		return logs;
	}

	/**
	 * 统计已办的台账数量
	 * 
	 * @param map
	 * @return
	 */
	public int getMyDoneCount(Organization organization) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", organization.getId());
		map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		map.put("issueTag", ThreeRecordsIssueTag.DONE_ISSUE);
		map.put("orgCode", organization.getOrgInternalCode());
		map.put("orgLevel", organization.getOrgLevel().getId());
		map.put("leaderView", "1");
		map.put("orgType", organization.getOrgType().getId());
		return issueDao.getMyDoneCount(map);
	}

	/**
	 * 查询待办的数量
	 * 
	 * @param map
	 * @return
	 */
	public int getJurisdictionsNeedDoCount(Organization organization) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", organization.getId());
		map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		map.put("issueTag", ThreeRecordsIssueTag.NEEDDO_ISSUE);
		map.put("orgCode", organization.getOrgInternalCode());
		map.put("orgLevel", organization.getOrgLevel().getId());
		map.put("leaderView", "1");
		map.put("orgType", organization.getOrgType().getId());
		return issueDao.getJurisdictionsNeedDoCount(map);
	}

	@Override
	public ThreeRecordsIssueViewObject turn(Long stepId,
			ThreeRecordsIssueLogNew log, Long target, Long[] tellorgs,
			List<ThreeRecordsIssueAttachFile> files, Long[] notices) {
		autoFillIssueLogProperty(log, ThreeRecordsIssueOperate.TURN);
		validateOperationLog(ThreeRecordsIssueOperate.TURN, log, files);
		BaseWorking baseWorking = getSimpleBaseWorkByStepId(stepId);
		ThreeRecordsIssueStep step = getIssueWorkFlowEngine()
				.getFullIssueStepById(stepId);
		ThreeRecordsIssueStep newStep = getIssueWorkFlowEngine().turn(
				baseWorking, step, log, target, tellorgs, files, notices);
		baseWorking = getSimpleBaseWorkByStepId(stepId);
		return createIssueViewObject(baseWorking, newStep);
	}

	@Override
	public ThreeRecordsIssueViewObject declare(Long stepId,
			ThreeRecordsIssueLogNew log, Long target, Long[] tellorgs,
			List<ThreeRecordsIssueAttachFile> files) {
		autoFillIssueLogProperty(log, ThreeRecordsIssueOperate.DECLARE);
		validateOperationLog(ThreeRecordsIssueOperate.DECLARE, log, files);
		BaseWorking baseWorking = getSimpleBaseWorkByStepId(stepId);
		ThreeRecordsIssueStep step = getIssueWorkFlowEngine()
				.getFullIssueStepById(stepId);
		ThreeRecordsIssueStep newStep = getIssueWorkFlowEngine().declare(
				baseWorking, step, log, target, tellorgs, files, null);
		baseWorking = getSimpleBaseWorkByStepId(stepId);
		return createIssueViewObject(baseWorking, newStep);
	}

	@Override
	public ThreeRecordsIssueViewObject support(Long stepId,
			ThreeRecordsIssueLogNew log, List<ThreeRecordsIssueAttachFile> files) {
		autoFillIssueLogProperty(log, ThreeRecordsIssueOperate.SUPPORT);
		validateOperationLog(ThreeRecordsIssueOperate.SUPPORT, log, files);
		ThreeRecordsIssueStep step = getIssueWorkFlowEngine()
				.getFullIssueStepById(stepId);
		BaseWorking baseWorking = getSimpleBaseWorkByStepId(stepId);
		ThreeRecordsIssueStep newStep = getIssueWorkFlowEngine().support(
				baseWorking, step, log, files);
		baseWorking = getSimpleBaseWorkByStepId(stepId);
		return createIssueViewObject(baseWorking, newStep);
	}

	@Override
	public ThreeRecordsIssueViewObject programComplete(Long stepId,
			ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		ThreeRecordsIssueStep step = getIssueWorkFlowEngine()
				.getFullIssueStepById(stepId);
		autoFillIssueLogProperty(log, ThreeRecordsIssueOperate.PROGRAM_COMPLETE);
		validateOperationLog(ThreeRecordsIssueOperate.PROGRAM_COMPLETE, log,
				attachFiles);
		BaseWorking baseWorking = getSimpleBaseWorkByStepId(stepId);
		ThreeRecordsIssueStep newStep = getIssueWorkFlowEngine()
				.programComplete(baseWorking, step, log, attachFiles);
		baseWorking = getSimpleBaseWorkByStepId(stepId);
		return createIssueViewObject(baseWorking, newStep);
	}

	@Override
	public ThreeRecordsIssueViewObject periodComplete(Long stepId,
			ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		ThreeRecordsIssueStep step = getIssueWorkFlowEngine()
				.getFullIssueStepById(stepId);
		autoFillIssueLogProperty(log, ThreeRecordsIssueOperate.PERIOD_COMPLETE);
		validateOperationLog(ThreeRecordsIssueOperate.PERIOD_COMPLETE, log,
				attachFiles);
		BaseWorking baseWorking = getSimpleBaseWorkByStepId(stepId);
		ThreeRecordsIssueStep newStep = getIssueWorkFlowEngine()
				.periodComplete(baseWorking, step, log, attachFiles);
		baseWorking = getSimpleBaseWorkByStepId(stepId);
		return createIssueViewObject(baseWorking, newStep);
	}

	@Override
	public ThreeRecordsIssueViewObject tmpComment(Long stepId,
			ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		autoFillIssueLogProperty(log, ThreeRecordsIssueOperate.TMPCOMMENT);
		validateOperationLog(ThreeRecordsIssueOperate.TMPCOMMENT, log,
				attachFiles);
		BaseWorking baseWorking = getSimpleBaseWorkByStepId(stepId);
		ThreeRecordsIssueStep step = getIssueWorkFlowEngine()
				.getFullIssueStepById(stepId);
		ThreeRecordsIssueStep newStep = getIssueWorkFlowEngine().tmpComment(
				baseWorking, step, log, attachFiles);
		baseWorking = getSimpleBaseWorkByStepId(stepId);
		return createIssueViewObject(baseWorking, newStep);
	}

	@Override
	public LedgerAttachFileReturnVo addIssueAttachFiles(
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		if (!CollectionUtil.isAvaliable(attachFiles))
			return null;
		LedgerAttachFileReturnVo attachFileReturn = new LedgerAttachFileReturnVo();
		List<Long> attachFileId = new ArrayList<Long>();
		List<String> attachFileName = new ArrayList<String>();
		Map<String, Object> params = new HashMap<String, Object>();
		try {

			for (ThreeRecordsIssueAttachFile file : attachFiles) {
				params.put("ledgerId", file.getLedgerId());
				params.put("ledgerType", file.getLedgerType());
				if (file.getReplyForm() != null) {
					params.put("replyFormId", file.getReplyForm().getId());
				}
				if (file.getIssueLog() == null) {
					params.put("fileType", ThreeRecordsIssueAttachFileType.FILE);
				} else {
					params.put("fileType",
							ThreeRecordsIssueAttachFileType.LOG_FILE);
					params.put("issueLogId", file.getIssueLog().getId());
				}
				Long id = issueDao.addIssueAttachFile(file);
				issueDao.addAttachFile(file, id);
				attachFileId.add(id);
				attachFileName.add(file.getFileName());
			}
			attachFileReturn.setAttachFileId(attachFileId);
			attachFileReturn.setAttachFileName(attachFileName);
			return attachFileReturn;

		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ThreeRecordsAbstractIssueServiceImpl的addIssueAttachFiles方法出现异常，原因：",
					"添加台账附件出现错误", e);
		}

	}

	@Override
	public List<ThreeRecordsIssueAttachFile> combineIssueAttachFile(
			String fileNameAndIdS[]) {
		if (fileNameAndIdS == null) {
			return null;
		}
		List<ThreeRecordsIssueAttachFile> list = new ArrayList<ThreeRecordsIssueAttachFile>();
		ThreeRecordsIssueAttachFile issueAttachFile = null;
		for (String fileNameAndId : fileNameAndIdS) {
			if (!StringUtil.isStringAvaliable(fileNameAndId)) {
				continue;
			}
			String[] fileName = fileNameAndId.split(",");
			if (fileNameAndId.indexOf(",") == 0
					&& fileName[1].indexOf(".") == -1) {
				fileNameAndId = fileNameAndId.substring(1);
			}
			String[] id_fileName = fileNameAndId.split(",");
			String id = id_fileName[0];
			issueAttachFile = new ThreeRecordsIssueAttachFile();
			if (StringUtil.isStringAvaliable(id)) {
				issueAttachFile = issueDao.getIssueAttachFileById(Long
						.parseLong(id));
			} else {
				StoredFile sf = null;
				try {
					sf = FileUtil.copyTmpFileToStoredFile(id_fileName[1],
							GridProperties.THREERECORDS_ATTACHFILE);
				} catch (Exception e) {
					throw new ServiceValidationException(
							"类ThreeRecordsAbstractIssueServiceImpl的combineIssueAttachFile方法出现异常，原因：",
							"台账附件出现错误", e);
				}
				issueAttachFile.setFileActualUrl(sf.getFullName());
				issueAttachFile.setFileName(id_fileName[1]);
			}
			list.add(issueAttachFile);
		}
		return list;

	}

	@Override
	public List<ThreeRecordsIssueAttachFile> findReplyFormFiles(Long ledgerId,
			int type) {
		return issueDao.findReplyFormFiles(ledgerId, type);
	}

	@Override
	public List<ThreeRecordsIssueAttachFile> findReplyFormFilesByReplyId(
			Long replyId) {
		return issueDao.findReplyFormFilesByReplyId(replyId);
	}

	@Override
	public PageInfo<AutoCompleteData> findNoticeTargetsByName(Long stepid,
			String tag, ThreeRecordsIssueOperate operate, Long[] exceptIds,
			int page, int rows) {
		return getIssueWorkFlowEngine().findNoticeTargetsByName(stepid,
				operate, tag, exceptIds, page, rows);
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsCreateAndDone(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType,Integer year,Integer month) {
		if (orgId == null || !StringUtil.isStringAvaliable(seachValue)) {
			throw new BusinessValidationException("参数未获得！");
		}
		try {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			Organization currentOrg = ThreadVariable.getOrganization();
			if (ThreeRecordsIssueOperationUtil.orgIsFunctional(currentOrg)) {
				org = ThreadVariable.getOrganization();
			}
			List<Long> childOrg = ComparisonAttribute
					.getOrgIds(organizationDubboService
							.findOrganizationsByParentId(org.getId()));
			PageInfo<ThreeRecordsIssueViewObject> pageInfo = issueDao
					.findJurisdictionsCreateAndDone(seachValue, org, childOrg,
							page, rows, sidx, sord, issueType,year,month);
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("阶段办结进过程中发生错误", e);
		}
	}

	@Override
	public ThreeRecordsIssueLogNew getLastStepByIssueIdAndIssueType(
			Long ledgerId, int ledgerType) {
		if (ledgerId == null) {
			throw new BusinessValidationException("根据台账ID查询台账最后处理时间时，台账ID为空！");
		}
		List<ThreeRecordsIssueStep> steps = issueProcessDao
				.getIssueStepByLedgerIdAndType(ledgerId, ledgerType);
		ThreeRecordsIssueStep step = null;
		Long time = 0l;
		for (ThreeRecordsIssueStep st : steps) {
			if (st.getLastDealDate().getTime() >= time) {
				time = st.getLastDealDate().getTime();
				step = st;
			}
		}
		ThreeRecordsIssueLogNew lastLog = null;
		if (step != null) {
			List<ThreeRecordsIssueLogNew> logs = threeRecordsIssueLogDao
					.getIssueLogsByStepId(step.getId());
			Long logId = 0l;
			for (ThreeRecordsIssueLogNew log : logs) {
				if (log.getId() >= logId) {
					lastLog = log;
				}
			}
		}
		if (lastLog != null && lastLog.getOperateTime() == null) {//如果受理时operateTime为空，用dealTime填充(稳定台账)
			lastLog.setOperateTime(lastLog.getDealTime());
		}
		return lastLog;
	}

	@Override
	public Map<String, Integer> getIssueCount(Long orgId, Long issueType, Integer year, Integer month) {
		if (orgId == null || issueType == null) {
			throw new BusinessValidationException("参数未获得！");
		}
		Organization org = organizationDubboService.getFullOrgById(orgId);
		Organization currentOrg = ThreadVariable.getOrganization();
		if (ThreeRecordsIssueOperationUtil.orgIsFunctional(currentOrg)) {
			org = ThreadVariable.getOrganization();
		}
		List<Long> childOrg = ComparisonAttribute
				.getOrgIds(organizationDubboService
						.findOrganizationsByParentId(org.getId()));

		Map<String, Object> parMap = new HashMap<String, Object>();
		if (issueType != null) {
			parMap.put("issueType", issueType);
		}
		if (childOrg != null && childOrg.size() > 0) {
			parMap.put("targetOrgs", childOrg);
		}
		parMap.put("seachValue", "present");
		parMap.put("orgCode", org.getOrgInternalCode());
		parMap.put("orgId", org.getId());
//		parMap.put("year", DealYearOrMonthUtil.dealYear(year));
//		parMap.put("month", DealYearOrMonthUtil.dealMonth(month));
		parMap.put("yearMonth", DealYearOrMonthUtil.dealYearMonth(year, month));

		Map<String, Integer> result = new HashMap<String, Integer>();
		result.put(ThreeRecordsIssueViewType.NEED,
				issueDao.getJurisdictionsNeedDoCount(initNeedDoPar(parMap)));
		result.put(ThreeRecordsIssueViewType.DONE,
				issueDao.getJurisdictionsDone(initDonePar(parMap)));
		result.put(ThreeRecordsIssueViewType.PERIOD,
				issueDao.getJurisdictionsPeriodDone(initPeriodDonePar(parMap)));
		result.put(ThreeRecordsIssueViewType.COMPLETED, issueDao
				.getJurisdictionsSubstanceDone(initCompletedPar(parMap)));
		result.put(ThreeRecordsIssueViewType.CREATE_DONE, issueDao
				.getJurisdictionsCreateAndDone(initCreateAndDonePar(parMap)));
		result.put(ThreeRecordsIssueViewType.FEEDBACK,
				issueDao.getJurisdictionsFeedBack(initFeedbackPar(parMap)));
		result.put(ThreeRecordsIssueViewType.ASSIGN,
				issueDao.getJurisdictionsAssginCount(initAssignPar(parMap)));
		result.put(ThreeRecordsIssueViewType.SUBMIT,
				issueDao.getJurisdictionsSubmit(initSubmitPar(parMap)));
		result.put(ThreeRecordsIssueViewType.SUPPORT,
				issueDao.getJurisdictionsNeedDoCount(initSupportPar(parMap)));
		result.put(ThreeRecordsIssueViewType.NOTICE,
				issueDao.getJurisdictionsNeedDoCount(initNoticePar(parMap)));
		return result;
	}

	private Map<String, Object> initParMap(Map<String, Object> map) {
		Map<String, Object> parMap = new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			parMap.put(entry.getKey(), entry.getValue());
		}
		return parMap;
	}

	private Map<String, Object> initNeedDoPar(Map<String, Object> map) {
		Map<String, Object> parMap = initParMap(map);
		parMap.put("isSupported", LedgerConstants.OPERATE_TYPE_SPONSOR);
		parMap.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		return parMap;
	}

	private Map<String, Object> initDonePar(Map<String, Object> map) {
		Map<String, Object> parMap = initParMap(map);
		parMap.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		parMap.put("substanceCode", ThreeRecordsIssueState.SUBSTANCE_CODE);
		parMap.put("issueTag", ThreeRecordsIssueTag.DONE_ISSUE);
		parMap.put("complete", ThreeRecordsIssueOperate.COMPLETE_CODE);
		parMap.put("completeStatus", ThreeRecordsIssueState.COMPLETE);
		return parMap;
	}

	private Map<String, Object> initPeriodDonePar(Map<String, Object> map) {
		Map<String, Object> parMap = initParMap(map);
		parMap.put("completeCode", ThreeRecordsIssueState.PERIOD_CODE);
		return parMap;
	}

	private Map<String, Object> initCompletedPar(Map<String, Object> map) {
		Map<String, Object> parMap = initParMap(map);
		parMap.put("completeCode", ThreeRecordsIssueState.SUBSTANCE_CODE);
		return parMap;
	}

	private Map<String, Object> initCreateAndDonePar(Map<String, Object> map) {
		Map<String, Object> parMap = initParMap(map);
		parMap.put("issueTag", ThreeRecordsIssueTag.DONE_ISSUE);
		return parMap;
	}

	private Map<String, Object> initFeedbackPar(Map<String, Object> map) {
		Map<String, Object> parMap = initParMap(map);
		parMap.put("verification", ThreeRecordsIssueState.VERIFICATION);// 台账表中已验证状态，值为300
		parMap.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);// 台账流程表中办理中的状态，值为500
		parMap.put("periodCode", ThreeRecordsIssueState.PERIOD_CODE);// 台账流程表中阶段办结的状态，值为600
		parMap.put("issueCompleteCode", ThreeRecordsIssueState.SUBSTANCE_CODE);// 台账流程表中已实质办结的状态，值为700
		parMap.put("issueTag", ThreeRecordsIssueTag.DONE_ISSUE);
		return parMap;
	}

	private Map<String, Object> initAssignPar(Map<String, Object> map) {
		Map<String, Object> parMap = initParMap(map);
		parMap.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		parMap.put("assgin", ThreeRecordsIssueSourceState.assign);
		parMap.put("issueTag", ThreeRecordsIssueTag.ASSIGN_ISSUE);
		return parMap;
	}

	private Map<String, Object> initSubmitPar(Map<String, Object> map) {
		Map<String, Object> parMap = initParMap(map);
		parMap.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		parMap.put("submit", ThreeRecordsIssueSourceState.submit);
		parMap.put("issueTag", ThreeRecordsIssueTag.SUBMIT_ISSUE);
		return parMap;
	}

	private Map<String, Object> initSupportPar(Map<String, Object> map) {
		Map<String, Object> parMap = initParMap(map);
		parMap.put("isSupported", LedgerConstants.OPERATE_TYPE_SUPPORT);
		parMap.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		return parMap;
	}

	private Map<String, Object> initNoticePar(Map<String, Object> map) {
		Map<String, Object> parMap = initParMap(map);
		parMap.put("isSupported", LedgerConstants.OPERATE_TYPE_NOTICE);
		parMap.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		return parMap;
	}

}
