package com.tianque.plugin.account.report.dao;

import java.util.List;
import java.util.Map;

import com.tianque.domain.Organization;
import com.tianque.plugin.account.report.util.AccountReportResult;
import com.tianque.plugin.account.report.util.HistoryReportResult;

public interface AccountReportDao {
	public void deleteCurrentMonthData(Map<String, Object> map);
	
	public void initCurrentMonthData();
	
	public List<AccountReportResult> queryVillagetData(Map<String, Object> map);
	
	public List<AccountReportResult> queryTownData(Map<String, Object> map);
	
	public List<AccountReportResult> queryFunData(Map<String, Object> map);
	
	public List<AccountReportResult> queryJgData(Map<String, Object> map);
	
	public List<AccountReportResult> queryAllData(Map<String, Object> map);
	
	public List<Organization> queryAllChildOrgByorgInternalCode(String orgInternalCode);
	
	public List<Organization> getXwandLxOrg(long funOrgId);

	public void initAllData(Map<String, Object> map);
	
	public List<HistoryReportResult> queryHistoryAccountReport(Map<String, Object> map);
	
	public void initAccountMonthOrgLevel(Map<String, Object> map);
	
	
	public List<HistoryReportResult> getVillageLastMonthDoing(Map<String, Object> map);
	public List<HistoryReportResult> getTwonLastMonthDoing(Map<String, Object> map);
	public List<HistoryReportResult> getFunLastMonthDoing(Map<String, Object> map);
	public List<HistoryReportResult> getJgLastMonthDoing(Map<String, Object> map);
	public List<HistoryReportResult> getallLastMonthDoing(Map<String, Object> map);
}
