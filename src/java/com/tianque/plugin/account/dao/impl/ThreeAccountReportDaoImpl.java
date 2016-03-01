package com.tianque.plugin.account.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.account.dao.ThreeAccountReportDao;
import com.tianque.plugin.account.vo.AccountResultVo;
import com.tianque.plugin.account.vo.ThreeRecordsReportStatisticalVo;
@Repository("threeAccountReportDao")
public class ThreeAccountReportDaoImpl extends AbstractBaseDao implements ThreeAccountReportDao {

	@Override
	public ThreeRecordsReportStatisticalVo getCumulativeRecord(
			Map<String, Object> map) {
		return (ThreeRecordsReportStatisticalVo) getSqlMapClientTemplate().queryForObject("threeAccountReport.getCumulativeRecord",map);
	}

	@Override
	public List<AccountResultVo> threeReportByPeopleLivelihoodForList(
			Map<String, Object> map) {
		return (List<AccountResultVo>) getSqlMapClientTemplate().queryForList("threeAccountReport.threeReportByPeopleLivelihoodForList",map);
	}

	@Override
	public List<AccountResultVo> threeReportByStableForList(
			Map<String, Object> map) {
		return (List<AccountResultVo>) getSqlMapClientTemplate().queryForList("threeAccountReport.threeReportByStableForList",map);
	}

	@Override
	public ThreeRecordsReportStatisticalVo threeReportByDifficultForList(
			Map<String, Object> map) {
		return (ThreeRecordsReportStatisticalVo) getSqlMapClientTemplate().queryForObject("threeAccountReport.threeReportByDifficultForList",map);
	}


}
