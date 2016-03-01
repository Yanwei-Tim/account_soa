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
import com.tianque.plugin.account.dao.DeclareFormDao;
import com.tianque.plugin.account.domain.DeclareForm;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.service.DeclareFormService;
import com.tianque.plugin.account.service.LedgerPoorPeopleService;
import com.tianque.plugin.account.service.LedgerSteadyWorkService;
import com.tianque.plugin.account.service.ThreeRecordsIssueService;
import com.tianque.plugin.account.service.ThreeRecordsKeyGeneratorService;
import com.tianque.plugin.account.util.ThreeRecordsIssueOperationUtil;
import com.tianque.plugin.account.vo.DeclareFormVo;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;

@Service("declareFormService")
@Transactional
public class DeclareFormServiceImpl implements DeclareFormService {

	@Autowired
	private DeclareFormDao declareFormDao;

	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;

	@Autowired
	private ThreeRecordsKeyGeneratorService threeRecordsKeyGeneratorService;
	@Autowired
	protected ThreeRecordsIssueService threeRecordsIssueService;
	@Autowired
	private LedgerPoorPeopleService ledgerPoorPeopleService;
	@Autowired
	private LedgerSteadyWorkService ledgerSteadyWorkService;
	@Autowired
	@Qualifier("declareFormValidatorImpl")
	private DomainValidator<DeclareForm> declareFormValidator;

	@Override
	public DeclareForm addDeclareForm(DeclareForm declareForm) {
		ValidateResult result = declareFormValidator.validate(declareForm);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
		try {
			declareForm = declareFormDao.addDeclareForm(declareForm);
			findOrg(declareForm);
			return declareForm;
		} catch (Exception e) {
			throw new ServiceValidationException("新增申报单失败!", e);
		}
	}

	public PageInfo<DeclareForm> findDeclareForms(DeclareFormVo declareFormVo,
			Integer page, Integer rows) {
		if (declareFormVo == null) {
			throw new BusinessValidationException("申报单查询参数不对!");
		}
		PageInfo<DeclareForm> pageInfo = declareFormDao.findDeclareForms(
				declareFormVo, page, rows);
		return pageInfo;
	}

	@Override
	public DeclareForm updateDeclareForm(DeclareForm declareForm) {
		if (declareForm == null || declareForm.getId() == null) {
			throw new BusinessValidationException("申报单更新参数不对!");
		}
		try {
			return declareFormDao.updateDeclareForm(declareForm);
		} catch (Exception e) {
			throw new ServiceValidationException("更新申报单失败!", e);
		}
	}

	@Override
	public DeclareForm getSimpleDeclareFormById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！申报单编号不能为空");
		}
		DeclareForm DeclareForm = declareFormDao.getSimpleDeclareFormById(id);
		findOrg(DeclareForm);
		return DeclareForm;
	}

	/*
	 * 查询上级部门和操作部门
	 */
	private void findOrg(DeclareForm declareForm) {
		if (declareForm == null) {
			return;
		}
		if (declareForm.getSourceOrg() != null
				&& declareForm.getSourceOrg().getId() != null) {
			declareForm.setSourceOrg(organizationDubboService
					.getSimpleOrgById(declareForm.getSourceOrg().getId()));
		}
		if (declareForm.getTargetOrg() != null
				&& declareForm.getTargetOrg().getId() != null) {
			declareForm.setTargetOrg(organizationDubboService
					.getSimpleOrgById(declareForm.getTargetOrg().getId()));
		}
	}

	@Override
	public DeclareForm getSimpleDeclareFormByStepId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！");
		}
		DeclareForm declareForm = declareFormDao
				.getSimpleDeclareFormByStepId(id);
		findOrg(declareForm);
		return declareForm;
	}

	@Override
	public DeclareForm createTemporaryDeclareForm(ThreeRecordsIssueLogNew log,
			Long keyId) {
		if (keyId == null || log == null) {
			throw new BusinessValidationException("参数错误！");
		}
		try {
			DeclareForm declareForm = new DeclareForm();
			declareForm.setSerialNumber(threeRecordsKeyGeneratorService
					.getNextFormKey(ThreeRecordsIssueOperationUtil
							.getLedgerPrefix(log.getLedgerType()),
							LedgerConstants.DECLARE_FORM));
			declareForm.setLedgerId(log.getLedgerId());
			declareForm.setLedgerType(Long.valueOf(log.getLedgerType()));
			declareForm.setMobile(log.getMobile());
			declareForm.setName(log.getDealUserName());
			declareForm.setDeclareDate(log.getOperateTime());
			declareForm.setTargetOrg(log.getTargeOrg());
			declareForm.setSourceOrg(log.getDealOrg());
			declareForm.setStepId(keyId);
			findOrg(declareForm);
			return declareForm;
		} catch (Exception e) {
			throw new ServiceValidationException("创建临时申报单失败!", e);
		}
	}

	@Override
	public DeclareForm saveDeclareFormAndCompletePorcess(
			ThreeRecordsIssueLogNew operation, Long[] tellOrgIds,
			List<ThreeRecordsIssueAttachFile> files, DeclareForm declareForm) {
		if (declareForm == null || declareForm.getStepId() == null
				|| operation == null || declareForm.getLedgerType() == null) {
			throw new BusinessValidationException("申报单添加时参数不对!");
		}
		try {
			declareForm = addDeclareForm(declareForm);

			if (declareForm.getLedgerType().intValue() == LedgerConstants.STEADYWORK) {
				ledgerSteadyWorkService.declare(declareForm.getStepId(),
						operation, operation.getTargeOrg().getId(), tellOrgIds,
						files);
			} else if (declareForm.getLedgerType().intValue() == LedgerConstants.POORPEOPLE) {
				ledgerPoorPeopleService.declare(declareForm.getStepId(),
						operation, operation.getTargeOrg().getId(), tellOrgIds,
						files);
			} else {
				threeRecordsIssueService.declare(declareForm.getStepId(),
						operation, operation.getTargeOrg().getId(), tellOrgIds,
						files);
			}

			return getSimpleDeclareFormById(declareForm.getId());
		} catch (Exception e) {
			throw new ServiceValidationException("新增申报单失败!", e);
		}

	}

}
