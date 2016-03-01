package com.tianque.plugin.account.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.account.dao.LedgerReportDao;
import com.tianque.plugin.account.domain.LedgerReportGroupCount;
import com.tianque.plugin.account.vo.LedgerCurrentYearCollectByMonthReportStatisticalVo;
import com.tianque.plugin.account.vo.LedgerCurrentYearCollectDoneRateReportStatisticalVo;
import com.tianque.plugin.account.vo.LedgerMonthReportStatisticalVo;
import com.tianque.plugin.account.vo.ThreeRecordsReportStatisticalVo;
import com.tianque.plugin.account.vo.ThreeRecordsReportVo;

@Repository("ledgerReportDao")
public class LedgerReportDaoImpl extends AbstractBaseDao implements
		LedgerReportDao {

	@Override
	public List<LedgerReportGroupCount> getReportAccountStepsCount(Map searchMap) {
		List<LedgerReportGroupCount> resultList = getSqlMapClientTemplate()
				.queryForList("ledgerReport.getReportAccountStepsCount",
						searchMap);
		return resultList;
	}

	@Override
	public List<LedgerReportGroupCount> getReportGroupCount(Map searchMap) {
		List<LedgerReportGroupCount> resultList = getSqlMapClientTemplate()
				.queryForList("ledgerReport.getReportGroupCount", searchMap);
		return resultList;
	}

	@Override
	public List<LedgerReportGroupCount> getReportStateCodeCount(Map searchMap) {
		List<LedgerReportGroupCount> resultList = getSqlMapClientTemplate()
				.queryForList("ledgerReport.getStateCodeCount", searchMap);
		return resultList;
	}

	@Override
	public ThreeRecordsReportStatisticalVo getThreeAccountReportForVillage(
			Map<String, Object> map) {
		return (ThreeRecordsReportStatisticalVo) getSqlMapClientTemplate().queryForObject("ledgerReport.getThreeAccountReportForVillage",map);
	}

	@Override
	public List<ThreeRecordsReportStatisticalVo> getJurisdictionAccout(
			Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList("ledgerReport.getJurisdictionAccout",map);
	}
	
	@Override
	public  List<ThreeRecordsReportVo> getAllDistrictCount(Map<String,Object> map){
		return getSqlMapClientTemplate().queryForList("ledgerReport.getAllDistrictCount",map);
	}
	
	@Override
	public  List<ThreeRecordsReportVo> getYearCollect(Map<String,Object> map){
		return getSqlMapClientTemplate().queryForList("ledgerReport.getYearCollectAnalysis",map);
	}
	
	@Override
	public List<LedgerCurrentYearCollectByMonthReportStatisticalVo> getYearCollectByMonthSum(Map<String, Object> map){
		return getSqlMapClientTemplate().queryForList("ledgerReport.getYearCollectByMonthSum", map);
	}
	
	@Override
	public List<LedgerCurrentYearCollectByMonthReportStatisticalVo> getYearCollectByMonthDetail(Map<String, Object> map){
		return getSqlMapClientTemplate().queryForList("ledgerReport.getYearCollectByMonthDetail", map);
	}
	
	@Override
	public List<LedgerCurrentYearCollectDoneRateReportStatisticalVo> getYearCollectDoneRateSum(Map<String, Object> map){
		return getSqlMapClientTemplate().queryForList("ledgerReport.getYearCollectDoneRateSum", map);
	}
	
	@Override
	public List<LedgerCurrentYearCollectDoneRateReportStatisticalVo> getYearCollectDoneRateDetail(Map<String, Object> map){
		return getSqlMapClientTemplate().queryForList("ledgerReport.getYearCollectDoneRateDetail", map);
	}
	
	@Override
	public  List<LedgerMonthReportStatisticalVo> getLedgerPeopleMonthDone(Map<String,Object> map){
		return getSqlMapClientTemplate().queryForList("ledgerReport.getMonthLedgerPeopleDone",map);
	}
	@Override
	public  List<LedgerMonthReportStatisticalVo> getLedgerPeopleMonthCollect(Map<String,Object> map){
		return getSqlMapClientTemplate().queryForList("ledgerReport.getMonthLedgerPeopleCollect", map);
	}
	
	@Override
	public  List<LedgerMonthReportStatisticalVo> getLedgerPoorPeopleMonthDone(Map<String,Object> map){
		return getSqlMapClientTemplate().queryForList("ledgerReport.getMonthLedgerPoorPeopleDone", map);
	}
	@Override
	public  List<LedgerMonthReportStatisticalVo> getLedgerPoorPeopleMonthCollect(Map<String,Object> map){
		return getSqlMapClientTemplate().queryForList("ledgerReport.getMonthLedgerPoorPeopleCollect", map);
	}
	
	@Override
	public  List<LedgerMonthReportStatisticalVo> getLedgerSteadyWorkMonthDone(Map<String,Object> map){
		return getSqlMapClientTemplate().queryForList("ledgerReport.getMonthLedgerSteadyWorkDone", map);
	}
	@Override
	public  List<LedgerMonthReportStatisticalVo> getLedgerSteadyWorkMonthCollect(Map<String,Object> map){
		return getSqlMapClientTemplate().queryForList("ledgerReport.getMonthLedgerSteadyWorkCollect", map);
	}
}
