package com.tianque.plugin.account.report.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.domain.Organization;
import com.tianque.plugin.account.constants.ThreeRecordsIssueConstants;
import com.tianque.plugin.account.report.dao.AccountReportDao;
import com.tianque.plugin.account.report.util.AccountReportResult;
import com.tianque.plugin.account.report.util.HistoryReportResult;

@Repository("accountReportDao")
public class AccountReportDaoImpl extends AbstractBaseDao implements AccountReportDao {

	@Override
	public void deleteCurrentMonthData(Map<String, Object> map) {
		getSqlMapClientTemplate().delete("accountReport.deleteCurrentMonthData", map);
	}

	@Override
	public void initCurrentMonthData() {
		
	}

	@Override
	public List<AccountReportResult> queryVillagetData(Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList("accountReport.queryVillageData", map);
	}

	@Override
	public List<AccountReportResult> queryTownData(Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList("accountReport.queryTownData", map);
	}

	@Override
	public List<AccountReportResult> queryFunData(Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList("accountReport.queryFunData", map);
	}

	@Override
	public List<AccountReportResult> queryJgData(Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList("accountReport.queryJgData", map);
	}

	@Override
	public List<AccountReportResult> queryAllData(Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList("accountReport.queryAllData", map);
	}

	@Override
	public List<Organization> queryAllChildOrgByorgInternalCode(
			String orgInternalCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		return getSqlMapClientTemplate().queryForList("accountReport.queryAllChildOrgByorgInternalCode", map);
	}
	
	@Override
	public List<Organization> getXwandLxOrg(long funOrgId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("xwNo", ThreeRecordsIssueConstants.COUNTY_COMMITTEE);
		map.put("lxNo", ThreeRecordsIssueConstants.COUNTY_JOINT);
		map.put("funOrgId", funOrgId);
		return getSqlMapClientTemplate().queryForList("accountReport.findLxAndxW", map);
	}

	@Override
	public void initAllData(Map<String, Object> map) {
		getSqlMapClientTemplate().insert("accountReport.insertCurrenMonthAccount", map);
	}

	@Override
	public List<HistoryReportResult> queryHistoryAccountReport(
			Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList("accountHistoryReport.queryHistoryAccountReport", map);
	}
	
	@Override
	public void initAccountMonthOrgLevel(Map<String, Object> map){
		getSqlMapClientTemplate().update("accountReport.initAccountMonthOrgLevel", map);
	}

	@Override
	public List<HistoryReportResult> getVillageLastMonthDoing(
			Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList("accountHistoryReport.villageLastMonthDoing", map);
	}

	@Override
	public List<HistoryReportResult> getTwonLastMonthDoing(
			Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList("accountHistoryReport.townLastMonthDoing", map);
	}

	@Override
	public List<HistoryReportResult> getFunLastMonthDoing(
			Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList("accountHistoryReport.funLastMonthDoing", map);
	}

	@Override
	public List<HistoryReportResult> getJgLastMonthDoing(Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList("accountHistoryReport.jgLastMonthDoing", map);
	}

	@Override
	public List<HistoryReportResult> getallLastMonthDoing(
			Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList("accountHistoryReport.allLastMonthDoing", map);
	}

}
