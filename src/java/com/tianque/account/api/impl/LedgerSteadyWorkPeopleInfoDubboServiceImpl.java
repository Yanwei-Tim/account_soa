package com.tianque.account.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.account.api.LedgerSteadyWorkPeopleInfoDubboService;
import com.tianque.plugin.account.domain.LedgerSteadyWork;
import com.tianque.plugin.account.domain.LedgerSteadyWorkPeopleInfo;
import com.tianque.plugin.account.service.LedgerSteadyWorkPeopleInfoService;

@Component
public class LedgerSteadyWorkPeopleInfoDubboServiceImpl implements
		LedgerSteadyWorkPeopleInfoDubboService {

	@Autowired
	private LedgerSteadyWorkPeopleInfoService ledgerSteadyWorkPeopleInfoService;

	@Override
	public LedgerSteadyWorkPeopleInfo addLedgerSteadyWorkPeopleInfo(
			LedgerSteadyWorkPeopleInfo ledgerSteadyWorkPeopleInfo) {
		return ledgerSteadyWorkPeopleInfoService
				.addLedgerSteadyWorkPeopleInfo(ledgerSteadyWorkPeopleInfo);
	}

	@Override
	public void addLedgerSteadyWorkPeopleInfos(LedgerSteadyWork ledgerSteadyWork) {
		ledgerSteadyWorkPeopleInfoService
				.addLedgerSteadyWorkPeopleInfos(ledgerSteadyWork);
	}

	@Override
	public void deleteLedgerSteadyWorkPeopleInfoById(Long id) {
		ledgerSteadyWorkPeopleInfoService
				.deleteLedgerSteadyWorkPeopleInfoById(id);

	}

	@Override
	public void deleteLedgerSteadyWorkPeopleInfoByLedgerSteadyWorkId(Long id) {
		ledgerSteadyWorkPeopleInfoService
				.deleteLedgerSteadyWorkPeopleInfoByLedgerSteadyWorkId(id);

	}

	@Override
	public List<LedgerSteadyWorkPeopleInfo> findByLedgerSteadyWork(
			LedgerSteadyWork ledgerSteadyWork) {
		return ledgerSteadyWorkPeopleInfoService
				.findByLedgerSteadyWork(ledgerSteadyWork);
	}

	@Override
	public LedgerSteadyWorkPeopleInfo getLedgerSteadyWorkPeopleInfoById(Long id) {
		return ledgerSteadyWorkPeopleInfoService
				.getLedgerSteadyWorkPeopleInfoById(id);
	}

	@Override
	public void updateLedgerSteadyWorkPeopleInfo(
			LedgerSteadyWorkPeopleInfo ledgerSteadyWorkPeopleInfo) {
		ledgerSteadyWorkPeopleInfoService
				.updateLedgerSteadyWorkPeopleInfo(ledgerSteadyWorkPeopleInfo);
	}

}
