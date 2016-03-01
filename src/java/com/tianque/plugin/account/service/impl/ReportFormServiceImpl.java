package com.tianque.plugin.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.dao.ReportFormDao;
import com.tianque.plugin.account.domain.ReportForm;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.service.LedgerPoorPeopleService;
import com.tianque.plugin.account.service.LedgerSteadyWorkService;
import com.tianque.plugin.account.service.ReportFormService;
import com.tianque.plugin.account.service.ThreeRecordsIssueService;
import com.tianque.plugin.account.service.ThreeRecordsKeyGeneratorService;
import com.tianque.plugin.account.util.ThreeRecordsIssueOperationUtil;
import com.tianque.plugin.account.vo.ReportFormVo;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;

@Service("reportFormService")
@Transactional
public class ReportFormServiceImpl implements ReportFormService {

	@Autowired
	private ReportFormDao reportFormDao;

	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;

	@Autowired
	@Qualifier("reportFormValidatorImpl")
	private DomainValidator<ReportForm> reportFormValidator;

	@Autowired
	protected ThreeRecordsIssueService threeRecordsIssueService;

	@Autowired
	private LedgerPoorPeopleService ledgerPoorPeopleService;
	@Autowired
	private LedgerSteadyWorkService ledgerSteadyWorkService;

	@Autowired
	private ThreeRecordsKeyGeneratorService threeRecordsKeyGeneratorService;

	@Override
	public ReportForm addReportForm(ReportForm reportForm) {
		ValidateResult result = reportFormValidator.validate(reportForm);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
		try {
			reportForm = reportFormDao.addReportForm(reportForm);
			findOrg(reportForm);
			return reportForm;
		} catch (Exception e) {
			throw new ServiceValidationException("新增呈报单失败!", e);
		}
	}

	public PageInfo<ReportForm> findReportForms(ReportFormVo reportFormVo,
			Integer page, Integer rows) {
		if (reportFormVo == null) {
			throw new BusinessValidationException("呈报单查询参数不对!");
		}
		PageInfo<ReportForm> pageInfo = reportFormDao.findReportForms(
				reportFormVo, page, rows);
		return pageInfo;
	}

	@Override
	public ReportForm updateReportForm(ReportForm reportForm) {
		if (reportForm == null || reportForm.getId() == null) {
			throw new BusinessValidationException("呈报单更新参数不对!");
		}
		try {
			reportFormDao.updateReportForm(reportForm);
			return reportForm;
		} catch (Exception e) {
			throw new ServiceValidationException("更新呈报单失败!", e);
		}
	}

	@Override
	public ReportForm getSimpleReportFormById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！呈报单编号不能为空");
		}
		ReportForm reportForm = reportFormDao.getSimpleReportFormById(id);
		findOrg(reportForm);
		return reportForm;
	}

	/*
	 * 查询上级部门和操作部门
	 */
	private void findOrg(ReportForm reportForm) {
		if (reportForm == null) {
			return;
		}
		if (reportForm.getSourceOrg() != null
				&& reportForm.getSourceOrg().getId() != null) {
			reportForm.setSourceOrg(organizationDubboService
					.getSimpleOrgById(reportForm.getSourceOrg().getId()));
		}
		if (reportForm.getTargetOrg() != null
				&& reportForm.getTargetOrg().getId() != null) {
			reportForm.setTargetOrg(organizationDubboService
					.getSimpleOrgById(reportForm.getTargetOrg().getId()));
		}
	}

	@Override
	public ReportForm getSimpleReportFormByStepId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！");
		}
		List<ReportForm> list = reportFormDao.findSimpleReportFormByStepId(id);
		if (list == null || list.size() == 0) {
			return null;
		}
		ReportForm reportForm = list.get(0);
		findOrg(reportForm);
		return reportForm;
	}

	@Override
	public ReportForm createTemporaryReportForm(ThreeRecordsIssueLogNew log,
			Long keyId) {
		if (keyId == null || log == null) {
			throw new BusinessValidationException("参数错误！");
		}
		try {
			ReportForm reportForm = new ReportForm();
			reportForm.setSerialNumber(threeRecordsKeyGeneratorService
					.getNextFormKey(ThreeRecordsIssueOperationUtil
							.getLedgerPrefix(log.getLedgerType()),
							LedgerConstants.REPORT_FORM));
			reportForm.setMobile(log.getMobile());
			reportForm.setName(log.getDealUserName());
			// reportForm.setHandleContent(log.getContent());
			reportForm.setReportDate(log.getOperateTime());
			reportForm.setTargetOrg(log.getTargeOrg());
			reportForm.setSourceOrg(log.getDealOrg());
			reportForm.setLedgerId(log.getLedgerId());
			reportForm.setLedgerType(Long.valueOf(log.getLedgerType()));
			reportForm.setStepId(keyId);
			findOrg(reportForm);
			if (log.getIssueStep() != null) {
				reportForm.setStepId(log.getIssueStep().getId());
			}

			return reportForm;

		} catch (Exception e) {
			throw new ServiceValidationException("创建临时呈报单失败!", e);
		}
	}

	@Override
	public ReportForm saveReportFormAndCompletePorcess(
			ThreeRecordsIssueLogNew operation, Long[] tellOrgIds,
			List<ThreeRecordsIssueAttachFile> files, ReportForm reportForm) {
		if (reportForm == null || reportForm.getStepId() == null
				|| operation == null || reportForm.getLedgerType() == null) {
			throw new BusinessValidationException("呈报单添加时参数不对!");
		}
		try {
			reportForm = addReportForm(reportForm);
			if (reportForm.getLedgerType().intValue() == LedgerConstants.STEADYWORK) {
				ledgerSteadyWorkService.submit(reportForm.getStepId(),
						operation, operation.getTargeOrg().getId(), tellOrgIds,
						files);
			} else if (reportForm.getLedgerType().intValue() == LedgerConstants.POORPEOPLE) {
				ledgerPoorPeopleService.submit(reportForm.getStepId(),
						operation, operation.getTargeOrg().getId(), tellOrgIds,
						files);
			} else {
				threeRecordsIssueService.submit(reportForm.getStepId(),
						operation, operation.getTargeOrg().getId(), tellOrgIds,
						files);
			}

			return getSimpleReportFormById(reportForm.getId());
		} catch (Exception e) {
			throw new ServiceValidationException("新增呈报单失败!", e);
		}

	}

}
