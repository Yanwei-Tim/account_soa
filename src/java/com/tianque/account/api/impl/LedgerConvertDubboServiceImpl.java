package com.tianque.account.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.account.api.LedgerConvertDubboService;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.domain.LedgerConvert;
import com.tianque.plugin.account.service.LedgerConvertService;
import com.tianque.plugin.account.vo.LedgerConvertVo;

@Component
public class LedgerConvertDubboServiceImpl implements LedgerConvertDubboService {
	@Autowired
	private LedgerConvertService ledgerConvertService;

	@Override
	public boolean addLedgerConvert(LedgerConvert ledgerConvert) {
		return ledgerConvertService.addLedgerConvert(ledgerConvert);
	}

	@Override
	public PageInfo<LedgerConvert> findLedgerConverts(
			LedgerConvertVo ledgerConvertVo, Integer page, Integer rows) {
		return ledgerConvertService.findLedgerConverts(ledgerConvertVo, page,
				rows);
	}

	@Override
	public LedgerConvert getSimpleLedgerConvertById(long id) {
		return ledgerConvertService.getSimpleLedgerConvertById(id);
	}

	@Override
	public LedgerConvert updateLedgerConvert(LedgerConvert ledgerConvert) {
		return ledgerConvertService.updateLedgerConvert(ledgerConvert);
	}

}
