package com.tianque.plugin.account.dao;

import java.util.List;
import java.util.Map;

import com.tianque.plugin.account.domain.LedgerSteadyWorkPeopleInfo;

public interface LedgerSteadyWorkPeopleInfoDao {

	public LedgerSteadyWorkPeopleInfo getLedgerSteadyWorkPeopleInfoById(Long id);

	public Long addLedgerSteadyWorkPeopleInfo(
			LedgerSteadyWorkPeopleInfo ledgerSteadyWorkPeopleInfo);

	public void updateLedgerSteadyWorkPeopleInfo(
			LedgerSteadyWorkPeopleInfo ledgerSteadyWorkPeopleInfo);

	public void deleteLedgerSteadyWorkPeopleInfoById(Long id);

	public void deleteLedgerSteadyWorkPeopleInfoByLedgerSteadyWorkId(Long id);

	public List<LedgerSteadyWorkPeopleInfo> findByLedgerSteadyWork(
			Map<String, Object> map);

}
