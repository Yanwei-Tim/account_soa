package com.tianque.account.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.account.api.SearchPoorPeopleByLevelDubboService;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.service.SearchPoorPeopleByLevelService;
import com.tianque.plugin.account.vo.LedgerPoorPeopleVo;
import com.tianque.plugin.account.vo.SearchLedgerPoorPeopleVo;

@Component
public class SearchPoorPeopleByLevelDubboServiceImpl implements
		SearchPoorPeopleByLevelDubboService {
	@Autowired
	protected SearchPoorPeopleByLevelService searchPoorPeopleByLevelService;

	@Override
	public PageInfo<LedgerPoorPeopleVo> findCompletedIssueList(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchPoorPeopleByLevelService.findCompletedIssueList(searchVo,
				page, rows, sidx, sord);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsAssgin(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchPoorPeopleByLevelService.findJurisdictionsAssgin(searchVo,
				page, rows, sidx, sord);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsCompleted(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchPoorPeopleByLevelService.findJurisdictionsCompleted(
				searchVo, page, rows, sidx, sord);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsDone(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchPoorPeopleByLevelService.findJurisdictionsDone(searchVo,
				page, rows, sidx, sord);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsFeedBack(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchPoorPeopleByLevelService.findJurisdictionsFeedBack(
				searchVo, page, rows, sidx, sord);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsFollow(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchPoorPeopleByLevelService.findJurisdictionsFollow(searchVo,
				page, rows, sidx, sord);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsNeedDo(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchPoorPeopleByLevelService.findJurisdictionsNeedDo(searchVo,
				page, rows, sidx, sord);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsSubmit(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchPoorPeopleByLevelService.findJurisdictionsSubmit(searchVo,
				page, rows, sidx, sord);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findPeriodCompletedList(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchPoorPeopleByLevelService.findPeriodCompletedList(searchVo,
				page, rows, sidx, sord);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsCreateAndDoneList(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchPoorPeopleByLevelService
				.findJurisdictionsCreateAndDoneList(searchVo, page, rows, sidx,
						sord);
	}

}
