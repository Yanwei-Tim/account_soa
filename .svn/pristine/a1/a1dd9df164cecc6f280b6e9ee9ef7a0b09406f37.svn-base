package com.tianque.plugin.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.dao.LedgerFeedBackDao;
import com.tianque.plugin.account.domain.LedgerFeedBack;
import com.tianque.plugin.account.domain.LedgerPeopleAspirations;
import com.tianque.plugin.account.domain.LedgerPoorPeople;
import com.tianque.plugin.account.domain.LedgerSteadyWork;
import com.tianque.plugin.account.service.LedgerFeedBackService;
import com.tianque.plugin.account.service.LedgerPoorPeopleService;
import com.tianque.plugin.account.service.LedgerSteadyWorkService;
import com.tianque.plugin.account.service.PeopleAspirationService;

@Service("ledgerFeedBackService")
@Transactional
public class LedgerFeedBackServiceImpl extends AbstractBaseService implements
		LedgerFeedBackService {
	@Autowired
	private LedgerFeedBackDao ledgerFeedBackDao;
	@Autowired
	private PeopleAspirationService peopleAspirationService;
	@Autowired
	private LedgerPoorPeopleService ledgerPoorPeopleService;
	@Autowired
	private LedgerSteadyWorkService ledgerSteadyWorkService;
	@Autowired
	@Qualifier("ledgerFeedBackValidatorImpl")
	private DomainValidator<LedgerFeedBack> ledgerFeedBackValidator;

	@Override
	public boolean addLedgerFeedBack(LedgerFeedBack ledgerFeedBack) {
		ValidateResult result = ledgerFeedBackValidator
				.validate(ledgerFeedBack);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
		try {
			updateFeedBackStatus(ledgerFeedBack);
			return ledgerFeedBackDao.addLedgerFeedBack(ledgerFeedBack);
		} catch (Exception e) {
			throw new ServiceValidationException("新增反馈信息失败!", e);
		}
	}

	@Override
	public List<LedgerFeedBack> getLedgerFeedByLedgerIdAndType(Long ledgerId,
			int ledgerType) {
		return ledgerFeedBackDao.getLedgerFeedByLedgerIdAndType(ledgerId,
				ledgerType);
	}

	@Override
	public Boolean isCanLedgerFeedBackByStepId(Long stepId) {
		if (stepId == null) {
			throw new BusinessValidationException("参数错误");
		}
		return ledgerFeedBackDao.isCanLedgerFeedBackByStepId(stepId);
	}

	private void updateFeedBackStatus(LedgerFeedBack ledgerFeedBack) {
		switch (ledgerFeedBack.getLedgerType()) {
		case LedgerConstants.POORPEOPLE:
			ledgerPoorPeopleService.updateFeedBackStatus(ledgerFeedBack
					.getLedgerId(), LedgerConstants.LEDGER_FEEDBACK_NO);
			break;
		case LedgerConstants.STEADYWORK:
			ledgerSteadyWorkService.updateFeedBackStatus(ledgerFeedBack
					.getLedgerId(), LedgerConstants.LEDGER_FEEDBACK_NO);
			break;
		default:
			peopleAspirationService.updateFeedBackStatus(ledgerFeedBack
					.getLedgerId(), LedgerConstants.LEDGER_FEEDBACK_NO);
			break;
		}
	}

	@Override
	public Boolean isCanLedgerFeedBackByLedgerIdAndType(Long ledgerId,
			int ledgerType) {
		if (ledgerId == null) {
			return false;
		}
		boolean feedBack = false;
		switch (ledgerType) {
		case LedgerConstants.POORPEOPLE:
			LedgerPoorPeople ledgerPoorPeople = ledgerPoorPeopleService
					.getLedgerPoorPeopleById(ledgerId);
			if (ledgerPoorPeople != null
					&& ledgerPoorPeople.getIsCanFeedBack() != null) {
				feedBack = ledgerPoorPeople.getIsCanFeedBack().intValue() == LedgerConstants.LEDGER_FEEDBACK_YES
						&& ThreadVariable.getOrganization().getId().equals(
								ledgerPoorPeople.getOrganization().getId());
			}
			break;
		case LedgerConstants.STEADYWORK:
			LedgerSteadyWork ledgerSteadyWork = ledgerSteadyWorkService
					.getLedgerSteadyWorkById(ledgerId);
			if (ledgerSteadyWork != null
					&& ledgerSteadyWork.getIsCanFeedBack() != null) {
				feedBack = ledgerSteadyWork.getIsCanFeedBack().intValue() == LedgerConstants.LEDGER_FEEDBACK_YES
						&& ThreadVariable.getOrganization().getId().equals(
								ledgerSteadyWork.getOrganization().getId());
			}
			break;
		default:

			LedgerPeopleAspirations ledgerPeopleAspirations = peopleAspirationService
					.getPeopleAspiration(ledgerId);
			if (ledgerPeopleAspirations != null
					&& ledgerPeopleAspirations.getIsCanFeedBack() != null) {
				feedBack = ledgerPeopleAspirations.getIsCanFeedBack()
						.intValue() == LedgerConstants.LEDGER_FEEDBACK_YES
						&& ThreadVariable.getOrganization().getId().equals(
								ledgerPeopleAspirations.getOrganization()
										.getId());
			}
			break;
		}

		return feedBack;
	}
}
