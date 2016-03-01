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
import com.tianque.plugin.account.dao.TurnFormDao;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.domain.TurnForm;
import com.tianque.plugin.account.service.LedgerPoorPeopleService;
import com.tianque.plugin.account.service.LedgerSteadyWorkService;
import com.tianque.plugin.account.service.ThreeRecordsIssueService;
import com.tianque.plugin.account.service.ThreeRecordsKeyGeneratorService;
import com.tianque.plugin.account.service.TurnFormService;
import com.tianque.plugin.account.util.ThreeRecordsIssueOperationUtil;
import com.tianque.plugin.account.vo.TurnFormVo;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;

@Service("turnFormService")
@Transactional
public class TurnFormServiceImpl implements TurnFormService {

	@Autowired
	private TurnFormDao turnFormDao;

	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;
	@Autowired
	protected ThreeRecordsIssueService threeRecordsIssueService;

	@Autowired
	private ThreeRecordsKeyGeneratorService threeRecordsKeyGeneratorService;

	@Autowired
	private LedgerPoorPeopleService ledgerPoorPeopleService;
	@Autowired
	private LedgerSteadyWorkService ledgerSteadyWorkService;

	@Autowired
	@Qualifier("turnFormValidatorImpl")
	private DomainValidator<TurnForm> turnFormValidator;

	@Override
	public TurnForm addTurnForm(TurnForm turnForm) {
		ValidateResult result = turnFormValidator.validate(turnForm);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
		try {
			turnForm = turnFormDao.addTurnForm(turnForm);
			findOrg(turnForm);
			return turnForm;
		} catch (Exception e) {
			throw new ServiceValidationException("新增转办单失败!", e);
		}
	}

	public PageInfo<TurnForm> findTurnForms(TurnFormVo TurnFormVo,
			Integer page, Integer rows) {
		if (TurnFormVo == null) {
			throw new BusinessValidationException("转办单查询参数不对!");
		}
		PageInfo<TurnForm> pageInfo = turnFormDao.findTurnForms(TurnFormVo,
				page, rows);
		return pageInfo;
	}

	@Override
	public TurnForm updateTurnForm(TurnForm TurnForm) {
		if (TurnForm == null || TurnForm.getId() == null) {
			throw new BusinessValidationException("转办单更新参数不对!");
		}
		try {
			return turnFormDao.updateTurnForm(TurnForm);
		} catch (Exception e) {
			throw new ServiceValidationException("更新转办单失败!", e);
		}
	}

	@Override
	public TurnForm getSimpleTurnFormById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！转办单编号不能为空");
		}
		TurnForm TurnForm = turnFormDao.getSimpleTurnFormById(id);
		findOrg(TurnForm);
		return TurnForm;
	}

	/*
	 * 查询上级部门和操作部门
	 */
	private void findOrg(TurnForm turnForm) {
		if (turnForm == null) {
			return;
		}
		if (turnForm.getTargetOrg() != null
				&& turnForm.getTargetOrg().getId() != null) {
			turnForm.setTargetOrg(organizationDubboService
					.getSimpleOrgById(turnForm.getTargetOrg().getId()));
		}
	}

	@Override
	public TurnForm getSimpleTurnFormByStepId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！");
		}
		TurnForm TurnForm = turnFormDao.getSimpleTurnFormByStepId(id);
		findOrg(TurnForm);
		return TurnForm;
	}

	@Override
	public TurnForm createTemporaryTurnForm(ThreeRecordsIssueLogNew log,
			Long keyId) {
		if (keyId == null || log == null) {
			throw new BusinessValidationException("参数错误！");
		}
		try {
			TurnForm turnForm = new TurnForm();
			turnForm.setSerialNumber(threeRecordsKeyGeneratorService
					.getNextFormKey(ThreeRecordsIssueOperationUtil
							.getLedgerPrefix(log.getLedgerType()),
							LedgerConstants.TURN_FORM));
			turnForm.setLedgerId(log.getLedgerId());
			turnForm.setLedgerType(Long.valueOf(log.getLedgerType()));
			// turnForm.setLedgerSerialNumber(baseWork.getSerialNumber());
			// turnForm.setMobile(log.getMobile());
			// turnForm.setName(log.getDealUserName());
			turnForm.setHandleStartDate(log.getOperateTime());
			turnForm.setHandleEndDate(log.getDealDeadline());
			// turnForm.setSubOpinion(log.getContent());
			turnForm.setTurnDate(log.getOperateTime());
			turnForm.setTargetOrg(log.getTargeOrg());
			turnForm.setStepId(keyId);
			findOrg(turnForm);
			return turnForm;
		} catch (Exception e) {
			throw new ServiceValidationException("创建临时转办单失败!", e);
		}
	}

	@Override
	public TurnForm saveTurnFormAndCompletePorcess(
			ThreeRecordsIssueLogNew operation, Long[] tellOrgIds,
			Long[] notices, List<ThreeRecordsIssueAttachFile> files,
			TurnForm turnForm) {
		if (turnForm == null || turnForm.getStepId() == null
				|| operation == null || operation.getTargeOrg() == null
				|| turnForm.getLedgerType() == null) {
			throw new BusinessValidationException("转办单添加时参数不对!");
		}
		try {
			turnForm = addTurnForm(turnForm);

			if (turnForm.getLedgerType().intValue() == LedgerConstants.STEADYWORK) {
				ledgerSteadyWorkService.turn(turnForm.getStepId(), operation,
						operation.getTargeOrg().getId(), tellOrgIds, files,
						notices);
			} else if (turnForm.getLedgerType().intValue() == LedgerConstants.POORPEOPLE) {
				ledgerPoorPeopleService.turn(turnForm.getStepId(), operation,
						operation.getTargeOrg().getId(), tellOrgIds, files,
						notices);
			} else {
				threeRecordsIssueService.turn(turnForm.getStepId(), operation,
						operation.getTargeOrg().getId(), tellOrgIds, files,
						notices);
			}

			return getSimpleTurnFormById(turnForm.getId());
		} catch (Exception e) {
			throw new ServiceValidationException("新增转办单失败!", e);
		}
	}

}
