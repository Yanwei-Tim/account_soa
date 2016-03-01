package com.tianque.plugin.account.dao.impl;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.constants.ThreeRecordsIssueTag;
import com.tianque.plugin.account.dao.SearchSteadyWorkByLevelDao;
import com.tianque.plugin.account.state.ThreeRecordsIssueSourceState;
import com.tianque.plugin.account.state.ThreeRecordsIssueState;
import com.tianque.plugin.account.vo.LedgerSteadyWorkVo;
import com.tianque.plugin.account.vo.SearchLedgerSteadyWorkVo;

@Repository("SearchSteadyWorkByLevelDaoImpl")
public class SearchSteadyWorkByLevelDaoImpl extends AbstractBaseDao implements
		SearchSteadyWorkByLevelDao {

	private PageInfo<LedgerSteadyWorkVo> createIssueVoPageInfoInstance(
			int totalRecord, int pageSize, int pageIndex) {
		PageInfo<LedgerSteadyWorkVo> result = new PageInfo<LedgerSteadyWorkVo>();
		result.setTotalRowSize(totalRecord);
		result.setCurrentPage(pageIndex);
		result.setPerPageSize(pageSize);
		return result;
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsNeedDo(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows) {
		searchVo.setCompleteCode(ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		searchVo.setTag(ThreeRecordsIssueTag.NEEDDO_ISSUE);
		PageInfo<LedgerSteadyWorkVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsNeedDoCount(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchLedgerSteadyWork.findJurisdictionsNeedDo", searchVo,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionAuditDelay(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows) {
		PageInfo<LedgerSteadyWorkVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsAuditDelayCount(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchLedgerSteadyWork.findJurisdictionAuditDelay", searchVo,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsDone(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows) {
		searchVo.setCompleteCode(ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		PageInfo<LedgerSteadyWorkVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsDone(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchLedgerSteadyWork.findJurisdictionsDone", searchVo,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsCompleted(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows) {
		searchVo.setCompleteCode(ThreeRecordsIssueState.ISSUECOMPLETE_CODE);
		searchVo.setTag(ThreeRecordsIssueTag.COMPLETED_ISSUE);
		PageInfo<LedgerSteadyWorkVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsCompleted(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchLedgerSteadyWork.findJurisdictionsCompleted", searchVo,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsSubmit(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows) {
		searchVo.setCompleteCode(ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		searchVo.setSubmit(ThreeRecordsIssueSourceState.submit);
		searchVo.setTag(ThreeRecordsIssueTag.SUBMIT_ISSUE);
		PageInfo<LedgerSteadyWorkVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsSubmit(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchLedgerSteadyWork.findJurisdictionsSubmit", searchVo,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsAssgin(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows) {
		searchVo.setCompleteCode(ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		searchVo.setAssgin(ThreeRecordsIssueSourceState.assign);
		searchVo.setTag(ThreeRecordsIssueTag.ASSIGN_ISSUE);
		PageInfo<LedgerSteadyWorkVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsAssginCount(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchLedgerSteadyWork.findJurisdictionsAssgin", searchVo,
				(page - 1) * rows, rows));
		return result;
	}

	private int getJurisdictionsNeedDoCount(SearchLedgerSteadyWorkVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchLedgerSteadyWork.countJurisdictionsNeedDo", searchVo);
	}

	private int getJurisdictionsAuditDelayCount(
			SearchLedgerSteadyWorkVo searchVo) {
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"searchLedgerSteadyWork.countJurisdictionsAuditDelay",
						searchVo);
	}

	private int getJurisdictionsDone(SearchLedgerSteadyWorkVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchLedgerSteadyWork.countJurisdictionsDone", searchVo);
	}

	private int getJurisdictionsCompleted(SearchLedgerSteadyWorkVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchLedgerSteadyWork.countJurisdictionsCompleted", searchVo);
	}

	private int getJurisdictionsSubmit(SearchLedgerSteadyWorkVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchLedgerSteadyWork.countJurisdictionsSubmit", searchVo);
	}

	private int getJurisdictionsAssginCount(SearchLedgerSteadyWorkVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchLedgerSteadyWork.countJurisdictionsAssgin", searchVo);
	}

	private int getJurisdictionsFollowCount(SearchLedgerSteadyWorkVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchLedgerSteadyWork.countJurisdictionsFollow", searchVo);
	}

	private int getJurisdictionsPeroidDoneCount(
			SearchLedgerSteadyWorkVo searchVo) {
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"searchLedgerSteadyWork.countJurisdictionsPeriodDone",
						searchVo);
	}
	private int getJurisdictionsCreateAndDoneCount(
			SearchLedgerSteadyWorkVo searchVo) {
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"searchLedgerSteadyWork.countJurisdictionsCreateAndDone",
						searchVo);
	}
	

	private int getJurisdictionsFeedBackCount(SearchLedgerSteadyWorkVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchLedgerSteadyWork.countJurisdictionsFeedBack", searchVo);
	}

	private int getJurisdictionsCompletedCount(SearchLedgerSteadyWorkVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchLedgerSteadyWork.countJurisdictionsSubstanceDone",
				searchVo);
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findCompletedIssueList(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows) {
		searchVo.setCompleteCode(ThreeRecordsIssueState.SUBSTANCE_CODE);
		PageInfo<LedgerSteadyWorkVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsCompletedCount(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchLedgerSteadyWork.findJurisdictionsSubstanceDone",
				searchVo, (page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsFeedBack(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows) {
		searchVo.setTag(ThreeRecordsIssueTag.DONE_ISSUE);
		searchVo.setPeriodCode(ThreeRecordsIssueState.PERIOD_CODE);
		searchVo.setCompleteCode(ThreeRecordsIssueState.STEPCOMPLETE_CODE);// 事件流程表中办理中的状态，值为500
		searchVo.setVerification(ThreeRecordsIssueState.VERIFICATION);// 事件表中已验证状态，值为300
		searchVo.setIssueCompleteCode(ThreeRecordsIssueState.SUBSTANCE_CODE);// 事件流程表中已实质办结的状态，值为700
		PageInfo<LedgerSteadyWorkVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsFeedBackCount(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchLedgerSteadyWork.findJurisdictionsFeedBack", searchVo,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsFollow(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows) {
		searchVo.setTag(ThreeRecordsIssueTag.DONE_ISSUE);
		searchVo.setCompleteCode(ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		PageInfo<LedgerSteadyWorkVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsFollowCount(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchLedgerSteadyWork.findJurisdictionsFollow", searchVo,
				(page - 1) * rows, rows));
		return null;
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findPeriodCompletedList(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows) {
		searchVo.setCompleteCode(ThreeRecordsIssueState.PERIOD_CODE);
		PageInfo<LedgerSteadyWorkVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsPeroidDoneCount(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchLedgerSteadyWork.findJurisdictionsPeriodDone", searchVo,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsCreateAndDoneList(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows) {
		searchVo.setCompleteCode(ThreeRecordsIssueState.PERIOD_CODE);
		PageInfo<LedgerSteadyWorkVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsCreateAndDoneCount(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchLedgerSteadyWork.findJurisdictionsCreateAndDone", searchVo,
				(page - 1) * rows, rows));
		return result;
	}
}
