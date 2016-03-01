package com.tianque.plugin.account.dao;

import java.util.List;

import com.tianque.plugin.account.domain.LedgerFeedBack;

public interface LedgerFeedBackDao {

	public Boolean isCanLedgerFeedBackByStepId(Long stepId);

	public List<LedgerFeedBack> getLedgerFeedByLedgerIdAndType(Long ledgerId,
			int ledgerType);

	public boolean addLedgerFeedBack(LedgerFeedBack ledgerFeedBack);

}
