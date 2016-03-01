package com.tianque.account.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.account.api.LedgerFeedBackDubboService;
import com.tianque.plugin.account.domain.LedgerFeedBack;
import com.tianque.plugin.account.service.LedgerFeedBackService;

@Component
public class LedgerFeedBackDubboServiceImpl implements
		LedgerFeedBackDubboService {
	@Autowired
	private LedgerFeedBackService ledgerFeedBackService;

	@Override
	public boolean addLedgerFeedBack(LedgerFeedBack ledgerFeedBack) {
		return ledgerFeedBackService.addLedgerFeedBack(ledgerFeedBack);
	}

	@Override
	public List<LedgerFeedBack> getLedgerFeedByLedgerIdAndType(Long ledgerId,
			int ledgerType) {
		return ledgerFeedBackService.getLedgerFeedByLedgerIdAndType(ledgerId,
				ledgerType);
	}

	@Override
	public Boolean isCanLedgerFeedBackByStepId(Long stepId) {
		return ledgerFeedBackService.isCanLedgerFeedBackByStepId(stepId);
	}

	@Override
	public Boolean isCanLedgerFeedBackByLedgerIdAndType(Long ledgerId,
			int ledgerType) {
		return ledgerFeedBackService.isCanLedgerFeedBackByLedgerIdAndType(
				ledgerId, ledgerType);
	}

}
