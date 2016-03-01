package com.tianque.account.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.account.api.SearchSteadyWorkByLevelDubboService;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.service.SearchSteadyWorkByLevelService;
import com.tianque.plugin.account.vo.LedgerSteadyWorkVo;
import com.tianque.plugin.account.vo.SearchLedgerSteadyWorkVo;

@Component
public class SearchSteadyWorkByLevelDubboServiceImpl implements
		SearchSteadyWorkByLevelDubboService {

	@Autowired
	protected SearchSteadyWorkByLevelService searchSteadyWorkByLevelService;

	@Override
	public PageInfo<LedgerSteadyWorkVo> findCompletedIssueList(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchSteadyWorkByLevelService.findCompletedIssueList(searchVo,
				page, rows, sidx, sord);
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsAssgin(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchSteadyWorkByLevelService.findJurisdictionsAssgin(searchVo,
				page, rows, sidx, sord);
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsCompleted(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchSteadyWorkByLevelService.findJurisdictionsCompleted(
				searchVo, page, rows, sidx, sord);
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsDone(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchSteadyWorkByLevelService.findJurisdictionsDone(searchVo,
				page, rows, sidx, sord);
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsFeedBack(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchSteadyWorkByLevelService.findJurisdictionsFeedBack(
				searchVo, page, rows, sidx, sord);
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsFollow(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchSteadyWorkByLevelService.findJurisdictionsFollow(searchVo,
				page, rows, sidx, sord);
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsNeedDo(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchSteadyWorkByLevelService.findJurisdictionsNeedDo(searchVo,
				page, rows, sidx, sord);
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsSubmit(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchSteadyWorkByLevelService.findJurisdictionsSubmit(searchVo,
				page, rows, sidx, sord);
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findPeriodCompletedList(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchSteadyWorkByLevelService.findPeriodCompletedList(searchVo,
				page, rows, sidx, sord);
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsCreateAndDoneList(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchSteadyWorkByLevelService
				.findJurisdictionsCreateAndDoneList(searchVo, page, rows, sidx,
						sord);
	}

}
