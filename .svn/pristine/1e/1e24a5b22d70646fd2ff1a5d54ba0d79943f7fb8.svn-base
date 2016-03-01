package com.tianque.plugin.account.dao.impl;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.constants.ThreeRecordsIssueTag;
import com.tianque.plugin.account.dao.SearchPoorPeopleByLevelDao;
import com.tianque.plugin.account.state.ThreeRecordsIssueSourceState;
import com.tianque.plugin.account.state.ThreeRecordsIssueState;
import com.tianque.plugin.account.vo.LedgerPoorPeopleVo;
import com.tianque.plugin.account.vo.SearchLedgerPoorPeopleVo;

@Repository("searchPoorPeopleByLevelDao")
public class SearchPoorPeopleByLevelDaoImpl extends AbstractBaseDao implements
		SearchPoorPeopleByLevelDao {

	private PageInfo<LedgerPoorPeopleVo> createIssueVoPageInfoInstance(
			int totalRecord, int pageSize, int pageIndex) {
		PageInfo<LedgerPoorPeopleVo> result = new PageInfo<LedgerPoorPeopleVo>();
		result.setTotalRowSize(totalRecord);
		result.setCurrentPage(pageIndex);
		result.setPerPageSize(pageSize);
		return result;
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsNeedDo(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows) {
		searchVo.setCompleteCode(ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		searchVo.setTag(ThreeRecordsIssueTag.NEEDDO_ISSUE);
		PageInfo<LedgerPoorPeopleVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsNeedDoCount(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchLedgerPoorPeople.findJurisdictionsNeedDo", searchVo,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionAuditDelay(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows) {
		PageInfo<LedgerPoorPeopleVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsAuditDelayCount(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchLedgerPoorPeople.findJurisdictionAuditDelay", searchVo,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsDone(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows) {
		searchVo.setCompleteCode(ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		searchVo.setTag(ThreeRecordsIssueTag.DONE_ISSUE);
		PageInfo<LedgerPoorPeopleVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsDone(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchLedgerPoorPeople.findJurisdictionsDone", searchVo,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsCompleted(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows) {
		searchVo.setCompleteCode(ThreeRecordsIssueState.ISSUECOMPLETE_CODE);
		searchVo.setTag(ThreeRecordsIssueTag.COMPLETED_ISSUE);
		PageInfo<LedgerPoorPeopleVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsCompleted(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchLedgerPoorPeople.findJurisdictionsCompleted", searchVo,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsSubmit(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows) {
		searchVo.setCompleteCode(ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		searchVo.setSubmit(ThreeRecordsIssueSourceState.submit);
		searchVo.setTag(ThreeRecordsIssueTag.SUBMIT_ISSUE);
		PageInfo<LedgerPoorPeopleVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsSubmit(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchLedgerPoorPeople.findJurisdictionsSubmit", searchVo,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsAssgin(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows) {
		searchVo.setCompleteCode(ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		searchVo.setAssgin(ThreeRecordsIssueSourceState.assign);
		searchVo.setTag(ThreeRecordsIssueTag.ASSIGN_ISSUE);
		PageInfo<LedgerPoorPeopleVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsAssginCount(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchLedgerPoorPeople.findJurisdictionsAssgin", searchVo,
				(page - 1) * rows, rows));
		return result;
	}

	private int getJurisdictionsNeedDoCount(SearchLedgerPoorPeopleVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchLedgerPoorPeople.countJurisdictionsNeedDo", searchVo);
	}

	private int getJurisdictionsAuditDelayCount(
			SearchLedgerPoorPeopleVo searchVo) {
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"searchLedgerPoorPeople.countJurisdictionsAuditDelay",
						searchVo);
	}

	private int getJurisdictionsDone(SearchLedgerPoorPeopleVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchLedgerPoorPeople.countJurisdictionsDone", searchVo);
	}

	private int getJurisdictionsCompleted(SearchLedgerPoorPeopleVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchLedgerPoorPeople.countJurisdictionsCompleted", searchVo);
	}

	private int getJurisdictionsSubmit(SearchLedgerPoorPeopleVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchLedgerPoorPeople.countJurisdictionsSubmit", searchVo);
	}

	private int getJurisdictionsAssginCount(SearchLedgerPoorPeopleVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchLedgerPoorPeople.countJurisdictionsAssgin", searchVo);
	}

	private int getJurisdictionsFollowCount(SearchLedgerPoorPeopleVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchLedgerPoorPeople.countJurisdictionsFollow", searchVo);
	}

	private int getJurisdictionsPeroidDoneCount(
			SearchLedgerPoorPeopleVo searchVo) {
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"searchLedgerPoorPeople.countJurisdictionsPeriodDone",
						searchVo);
	}

	private int getJurisdictionCreateAndDoneCount(
			SearchLedgerPoorPeopleVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchLedgerPoorPeople.countJurisdictionsCreateAndDone",
				searchVo);
	}

	private int getJurisdictionsFeedBackCount(SearchLedgerPoorPeopleVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchLedgerPoorPeople.countJurisdictionsFeedBack", searchVo);
	}

	private int getJurisdictionsCompletedCount(SearchLedgerPoorPeopleVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchLedgerPoorPeople.countJurisdictionsSubstanceDone",
				searchVo);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findCompletedIssueList(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows) {

		searchVo.setCompleteCode(ThreeRecordsIssueState.SUBSTANCE_CODE);
		PageInfo<LedgerPoorPeopleVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsCompletedCount(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchLedgerPoorPeople.findJurisdictionsSubstanceDone",
				searchVo, (page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsFeedBack(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows) {
		searchVo.setTag(ThreeRecordsIssueTag.DONE_ISSUE);
		searchVo.setPeriodCode(ThreeRecordsIssueState.PERIOD_CODE);
		searchVo.setCompleteCode(ThreeRecordsIssueState.STEPCOMPLETE_CODE);// 台账流程表中办理中的状态，值为500
		searchVo.setVerification(ThreeRecordsIssueState.VERIFICATION);// 台账表中已验证状态，值为300
		searchVo.setIssueCompleteCode(ThreeRecordsIssueState.SUBSTANCE_CODE);// 台账流程表中已实质办结的状态，值为700
		PageInfo<LedgerPoorPeopleVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsFeedBackCount(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchLedgerPoorPeople.findJurisdictionsFeedBack", searchVo,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsFollow(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows) {
		searchVo.setTag(ThreeRecordsIssueTag.DONE_ISSUE);
		searchVo.setCompleteCode(ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		PageInfo<LedgerPoorPeopleVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsFollowCount(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchLedgerPoorPeople.findJurisdictionsFollow", searchVo,
				(page - 1) * rows, rows));
		return null;
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findPeriodCompletedList(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows) {
		searchVo.setCompleteCode(ThreeRecordsIssueState.PERIOD_CODE);
		PageInfo<LedgerPoorPeopleVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsPeroidDoneCount(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchLedgerPoorPeople.findJurisdictionsPeriodDone", searchVo,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsCreateAndDoneList(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows) {
		searchVo.setCompleteCode(ThreeRecordsIssueState.PERIOD_CODE);
		PageInfo<LedgerPoorPeopleVo> result = createIssueVoPageInfoInstance(
				getJurisdictionCreateAndDoneCount(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchLedgerPoorPeople.findJurisdictionsCreateAndDone",
				searchVo, (page - 1) * rows, rows));
		return result;
	}
}
