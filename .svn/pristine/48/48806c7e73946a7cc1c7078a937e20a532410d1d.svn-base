package com.tianque.plugin.account.service;

import java.util.List;

import com.tianque.plugin.account.vo.ThreeRecordsReportStatisticalVo;

public interface ThreeAccountReportService {

	/***
	 * 查询三本台账月报表数据
	 * @param year 年份
	 * @param month 月份
	 * @param orgId 组织机构参数
	 * @return
	 */
	public List<ThreeRecordsReportStatisticalVo> findThreeAccountReportForList(Integer year,Integer month,Long orgId);
	
	/***
	 * 三本台账月报表数据生成
	 * @param year
	 * @param month
	 * @param statisticsType 报表类别(0：县级报表 1：县职能报表 2：乡镇报表 3：村报表)
	 */
	public List<ThreeRecordsReportStatisticalVo> createThreeAccountRecord(Integer year,Integer month,Long orgId);
}
