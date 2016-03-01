package com.tianque.plugin.account.dao;

import java.util.List;
import java.util.Map;

import com.tianque.plugin.account.vo.AccountResultVo;
import com.tianque.plugin.account.vo.ThreeRecordsReportStatisticalVo;

public interface ThreeAccountReportDao {

	/**
	 * 查询村社区数据
	 * @param map
	 * @return
	 */
	public ThreeRecordsReportStatisticalVo getCumulativeRecord(Map<String,Object> map);
	
	/***
	 * 累计/本月 办结数/办理中数/上报数 民生
	 */
	public List<AccountResultVo> threeReportByPeopleLivelihoodForList(Map<String,Object> map);
	
	/***
	 * 累计/本月 办结数/办理中数/上报数 稳定
	 */
	public List<AccountResultVo> threeReportByStableForList(Map<String,Object> map);
	
	/***
	 * 累计/本月 办结数/办理中数/上报数 困难
	 */
	public ThreeRecordsReportStatisticalVo threeReportByDifficultForList(Map<String,Object> map);
}
