package com.tianque.account.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.account.api.SearchPeopleAspirationByLevelDubboService;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.service.SearchPeopleAspirationByLevelService;
import com.tianque.plugin.account.vo.SearchPeopleAspirationVo;
import com.tianque.plugin.account.vo.ThreeRecordsIssueViewObject;

@Component
public class SearchPeopleAspirationByLevelDubboServiceImpl implements
		SearchPeopleAspirationByLevelDubboService {
	@Autowired
	protected SearchPeopleAspirationByLevelService searchPeopleAspirationByLevelService;

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findCompletedIssueList(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchPeopleAspirationByLevelService.findCompletedIssueList(
				searchVo, page, rows, sidx, sord);
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsAssgin(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchPeopleAspirationByLevelService.findJurisdictionsAssgin(
				searchVo, page, rows, sidx, sord);
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsCompleted(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchPeopleAspirationByLevelService.findJurisdictionsCompleted(
				searchVo, page, rows, sidx, sord);
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsDone(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchPeopleAspirationByLevelService.findJurisdictionsDone(
				searchVo, page, rows, sidx, sord);
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsFeedBack(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchPeopleAspirationByLevelService.findJurisdictionsFeedBack(
				searchVo, page, rows, sidx, sord);
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsFollow(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchPeopleAspirationByLevelService.findJurisdictionsFollow(
				searchVo, page, rows, sidx, sord);
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsNeedDo(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchPeopleAspirationByLevelService.findJurisdictionsNeedDo(
				searchVo, page, rows, sidx, sord);
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsSubmit(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchPeopleAspirationByLevelService.findJurisdictionsSubmit(
				searchVo, page, rows, sidx, sord);
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findPeriodCompletedList(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchPeopleAspirationByLevelService.findPeriodCompletedList(
				searchVo, page, rows, sidx, sord);
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsCreateAndDoneList(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return searchPeopleAspirationByLevelService
				.findJurisdictionsCreateAndDoneList(searchVo, page, rows, sidx,
						sord);
	}

}
