package com.tianque.plugin.account.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.account.dao.LedgerSteadyWorkPeopleInfoDao;
import com.tianque.plugin.account.domain.LedgerSteadyWorkPeopleInfo;

@Repository("ledgerSteadyWorkPeopleInfoDao")
public class LedgerSteadyWorkPeopleInfoDaoImpl extends AbstractBaseDao
		implements LedgerSteadyWorkPeopleInfoDao {

	@Override
	public LedgerSteadyWorkPeopleInfo getLedgerSteadyWorkPeopleInfoById(Long id) {
		return (LedgerSteadyWorkPeopleInfo) getSqlMapClientTemplate()
				.queryForObject(
						"ledgerSteadyWorkPeopleInfo.getLedgerSteadyWorkPeopleInfoById",
						id);
	}

	@Override
	public Long addLedgerSteadyWorkPeopleInfo(
			LedgerSteadyWorkPeopleInfo ledgerpoorpeopleMember) {
		return (Long) getSqlMapClientTemplate().insert(
				"ledgerSteadyWorkPeopleInfo.addLedgerSteadyWorkPeopleInfo",
				ledgerpoorpeopleMember);
	}

	@Override
	public void updateLedgerSteadyWorkPeopleInfo(
			LedgerSteadyWorkPeopleInfo ledgerpoorpeopleMember) {
		getSqlMapClientTemplate().update(
				"ledgerSteadyWorkPeopleInfo.updateLedgerSteadyWorkPeopleInfo",
				ledgerpoorpeopleMember);

	}

	@Override
	public void deleteLedgerSteadyWorkPeopleInfoById(Long id) {
		getSqlMapClientTemplate()
				.delete("ledgerSteadyWorkPeopleInfo.deleteLedgerSteadyWorkPeopleInfoById",
						id);
	}

	@Override
	public void deleteLedgerSteadyWorkPeopleInfoByLedgerSteadyWorkId(Long id) {
		getSqlMapClientTemplate()
				.delete("ledgerSteadyWorkPeopleInfo.deleteLedgerSteadyWorkPeopleInfoByLedgerSteadyWorkId",
						id);

	}

	@Override
	public List<LedgerSteadyWorkPeopleInfo> findByLedgerSteadyWork(
			Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList(
				"ledgerSteadyWorkPeopleInfo.findByLedgerSteadyWork", map);
	}

}
