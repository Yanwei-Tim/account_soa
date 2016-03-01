package com.tianque.plugin.account.dao;

import java.util.List;
import java.util.Map;

import com.tianque.plugin.account.domain.LedgerReportGroupCount;
import com.tianque.plugin.account.vo.LedgerCurrentYearCollectByMonthReportStatisticalVo;
import com.tianque.plugin.account.vo.LedgerCurrentYearCollectDoneRateReportStatisticalVo;
import com.tianque.plugin.account.vo.LedgerMonthReportStatisticalVo;
import com.tianque.plugin.account.vo.ThreeRecordsReportStatisticalVo;
import com.tianque.plugin.account.vo.ThreeRecordsReportVo;

public interface LedgerReportDao {

	public List<LedgerReportGroupCount> getReportAccountStepsCount(Map searchMap);

	public List<LedgerReportGroupCount> getReportGroupCount(Map searchMap);

	public List<LedgerReportGroupCount> getReportStateCodeCount(Map searchMap);
	
	
	//9-1日三本台账首页报表-村社区层级查询数据---王超
	public  ThreeRecordsReportStatisticalVo getThreeAccountReportForVillage(Map<String,Object> map);

	//查询只能部门下辖数据
	public  List<ThreeRecordsReportStatisticalVo> getJurisdictionAccout(Map<String,Object> map);
	
	public  List<ThreeRecordsReportVo> getAllDistrictCount(Map<String,Object> map);
	
	public  List<ThreeRecordsReportVo> getYearCollect(Map<String,Object> map);
	
	public  List<LedgerMonthReportStatisticalVo> getLedgerPeopleMonthDone(Map<String,Object> map);
	public  List<LedgerMonthReportStatisticalVo> getLedgerPeopleMonthCollect(Map<String,Object> map);
	
	public  List<LedgerMonthReportStatisticalVo> getLedgerPoorPeopleMonthDone(Map<String,Object> map);
	public  List<LedgerMonthReportStatisticalVo> getLedgerPoorPeopleMonthCollect(Map<String,Object> map);
	
	public  List<LedgerMonthReportStatisticalVo> getLedgerSteadyWorkMonthDone(Map<String,Object> map);
	public  List<LedgerMonthReportStatisticalVo> getLedgerSteadyWorkMonthCollect(Map<String,Object> map);
	
	public List<LedgerCurrentYearCollectByMonthReportStatisticalVo> getYearCollectByMonthSum(Map<String, Object> map);
	public List<LedgerCurrentYearCollectByMonthReportStatisticalVo> getYearCollectByMonthDetail(Map<String, Object> map);
	
	public List<LedgerCurrentYearCollectDoneRateReportStatisticalVo> getYearCollectDoneRateSum(Map<String, Object> map);
	public List<LedgerCurrentYearCollectDoneRateReportStatisticalVo> getYearCollectDoneRateDetail(Map<String, Object> map);
}
