package com.tianque.plugin.account.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.account.dao.ThreeRecordsIssueLogDao;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.domain.ThreeRecordsIssueMap;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStepGroup;

@Repository("threeRecordsIssueLogDao")
public class ThreeRecordsIssueLogDaoImpl extends AbstractBaseDao implements
		ThreeRecordsIssueLogDao {

	@Override
	public ThreeRecordsIssueLogNew addLog(ThreeRecordsIssueLogNew log) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"threeRecordsIssuelog.addIssueLog", log);
		return getIssueLogById(id);
	}

	@Override
	public ThreeRecordsIssueLogNew updateLog(ThreeRecordsIssueLogNew log) {
		getSqlMapClientTemplate().update("threeRecordsIssuelog.updateIssueLog",
				log);
		return getIssueLogById(log.getId());
	}

	@Override
	public ThreeRecordsIssueLogNew getIssueLogById(Long id) {
		return (ThreeRecordsIssueLogNew) getSqlMapClientTemplate()
				.queryForObject("threeRecordsIssuelog.getIssueLogById", id);
	}

	@Override
	public List<ThreeRecordsIssueLogNew> loadIssueOperationLogsByIssueId(
			Long id, Long ledgerType) {
		Map map = new HashMap();
		map.put("ledgerId", id);
		map.put("ledgerType", ledgerType);
		return getSqlMapClientTemplate().queryForList(
				"threeRecordsIssuelog.loadIssueOperationLogsByIssueId", map);
	}

	@Override
	public boolean deleteIssueLogByIssueId(Long issueId, int ledgerType) {
		Map map = new HashMap();
		map.put("ledgerId", issueId);
		map.put("ledgerType", ledgerType);
		getSqlMapClientTemplate().delete(
				"threeRecordsIssuelog.deleteIssueLogByIssueId", map);
		return true;
	}

	@Override
	public List<ThreeRecordsIssueLogNew> getIssueLogsByStepId(Long id) {
		return getSqlMapClientTemplate().queryForList(
				"threeRecordsIssuelog.getIssueLogsByStepId", id);
	}

	@Override
	public List<ThreeRecordsIssueLogNew> findIssueDealLogByIssueMapAndIssueStepGroup(
			ThreeRecordsIssueMap issueMap, ThreeRecordsIssueStepGroup group) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ledgerId", group.getLedgerId());
		map.put("ledgerType", group.getLedgerType());
		map.put("orgId", issueMap.getOrgId());
		map.put("startId", group.getEntyLog().getId());
		map.put("endId", group.getOutLog() == null ? null : group.getOutLog()
				.getId());
		return getSqlMapClientTemplate().queryForList(
				"threeRecordsIssuelog.getIssueLogsByIssuMap", map);
	}

}
