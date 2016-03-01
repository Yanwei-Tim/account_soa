package com.tianque.plugin.account.service;

import java.util.List;

import com.tianque.plugin.account.domain.AccountReport;
import com.tianque.plugin.account.vo.LedgerCurrentYearCollectByMonthReportStatisticalVo;
import com.tianque.plugin.account.vo.LedgerCurrentYearCollectDoneRateReportStatisticalVo;
import com.tianque.plugin.account.vo.LedgerMonthReportStatisticalVo;
import com.tianque.plugin.account.vo.ThreeRecordsReportStatisticalVo;

public interface LedgerAccountReportService {

	/**
	 * 根据查询条件得到报表信息
	 * 
	 * @param searchVo
	 * @return
	 */
	public List<ThreeRecordsReportStatisticalVo> findAccountReportBySearchVo(
			AccountReport accountReport);
	
	/**
	 * 每月收集合计统计（按月显示）
	 * @param accountReport
	 * @return
	 */
	public List<LedgerCurrentYearCollectByMonthReportStatisticalVo> getYearCollectByMonth(AccountReport accountReport);
	public List<LedgerCurrentYearCollectDoneRateReportStatisticalVo> getYearCollectDoneRate(AccountReport accountReport);

	/**
	 * 按机构层级获得报表类型
	 * @param accountReport
	 * @return
	 */
	public String judgeReportType(AccountReport accountReport);

	/**
	 * 根据查询条件得到报表信息
	 * 
	 * @param searchVo
	 * @return
	 */
	public List<ThreeRecordsReportStatisticalVo> findHomePageAccountReportVo(
			AccountReport accountReport);
	
	public List<LedgerMonthReportStatisticalVo> findMonthCollectDoneReportVo(AccountReport accountReport);
}
