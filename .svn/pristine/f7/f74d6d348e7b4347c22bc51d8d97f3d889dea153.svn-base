package com.tianque.plugin.account.dao.impl;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.constants.ThreeRecordsIssueTag;
import com.tianque.plugin.account.dao.SearchPeopleAspirationByLevelDao;
import com.tianque.plugin.account.state.ThreeRecordsIssueSourceState;
import com.tianque.plugin.account.state.ThreeRecordsIssueState;
import com.tianque.plugin.account.vo.SearchPeopleAspirationVo;
import com.tianque.plugin.account.vo.ThreeRecordsIssueViewObject;

@Repository("SearchPeopleAspirationByLevelDaoImpl")
public class SearchPeopleAspirationByLevelDaoImpl extends AbstractBaseDao
		implements SearchPeopleAspirationByLevelDao {

	private PageInfo<ThreeRecordsIssueViewObject> createIssueVoPageInfoInstance(
			int totalRecord, int pageSize, int pageIndex) {
		PageInfo<ThreeRecordsIssueViewObject> result = new PageInfo<ThreeRecordsIssueViewObject>();
		result.setTotalRowSize(totalRecord);
		result.setCurrentPage(pageIndex);
		result.setPerPageSize(pageSize);
		return result;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsNeedDo(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows) {
		searchVo.setCompleteCode(ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		searchVo.setTag(ThreeRecordsIssueTag.NEEDDO_ISSUE);
		PageInfo<ThreeRecordsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsNeedDoCount(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchPeopleAspirationByLevel.findJurisdictionsNeedDo",
				searchVo, (page - 1) * rows, rows));

		return result;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionAuditDelay(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows) {
		PageInfo<ThreeRecordsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsAuditDelayCount(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchPeopleAspirationByLevel.findJurisdictionAuditDelay",
				searchVo, (page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsDone(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows) {
		searchVo.setCompleteCode(ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		searchVo.setTag(ThreeRecordsIssueTag.DONE_ISSUE);
		PageInfo<ThreeRecordsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsDone(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchPeopleAspirationByLevel.findJurisdictionsDone",
				searchVo, (page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsCompleted(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows) {
		searchVo.setCompleteCode(ThreeRecordsIssueState.ISSUECOMPLETE_CODE);
		searchVo.setTag(ThreeRecordsIssueTag.COMPLETED_ISSUE);
		PageInfo<ThreeRecordsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsCompleted(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchPeopleAspirationByLevel.findJurisdictionsCompleted",
				searchVo, (page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsSubmit(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows) {
		searchVo.setCompleteCode(ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		searchVo.setSubmit(ThreeRecordsIssueSourceState.submit);
		searchVo.setTag(ThreeRecordsIssueTag.SUBMIT_ISSUE);
		PageInfo<ThreeRecordsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsSubmit(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchPeopleAspirationByLevel.findJurisdictionsSubmit",
				searchVo, (page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsAssgin(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows) {
		searchVo.setCompleteCode(ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		searchVo.setAssgin(ThreeRecordsIssueSourceState.assign);
		searchVo.setTag(ThreeRecordsIssueTag.ASSIGN_ISSUE);
		PageInfo<ThreeRecordsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsAssginCount(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchPeopleAspirationByLevel.findJurisdictionsAssgin",
				searchVo, (page - 1) * rows, rows));
		return result;
	}

	private int getJurisdictionsNeedDoCount(SearchPeopleAspirationVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchPeopleAspirationByLevel.countJurisdictionsNeedDo",
				searchVo);
	}

	private int getJurisdictionsAuditDelayCount(
			SearchPeopleAspirationVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchPeopleAspirationByLevel.countJurisdictionsAuditDelay",
				searchVo);
	}

	private int getJurisdictionsDone(SearchPeopleAspirationVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchPeopleAspirationByLevel.countJurisdictionsDone",
				searchVo);
	}

	private int getJurisdictionsCompleted(SearchPeopleAspirationVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchPeopleAspirationByLevel.countJurisdictionsCompleted",
				searchVo);
	}

	private int getJurisdictionsSubmit(SearchPeopleAspirationVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchPeopleAspirationByLevel.countJurisdictionsSubmit",
				searchVo);
	}

	private int getJurisdictionsAssginCount(SearchPeopleAspirationVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchPeopleAspirationByLevel.countJurisdictionsAssgin",
				searchVo);
	}

	private int getJurisdictionsFollowCount(SearchPeopleAspirationVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchPeopleAspirationByLevel.countJurisdictionsFollow",
				searchVo);
	}

	private int getJurisdictionsPeroidDoneCount(
			SearchPeopleAspirationVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchPeopleAspirationByLevel.countJurisdictionsPeriodDone",
				searchVo);
	}

	private int getJurisdictionsCreateAndDoneCount(
			SearchPeopleAspirationVo searchVo) {
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"searchPeopleAspirationByLevel.countJurisdictionsCreateAndDone",
						searchVo);
	}

	private int getJurisdictionsFeedBackCount(SearchPeopleAspirationVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchPeopleAspirationByLevel.countJurisdictionsFeedBack",
				searchVo);
	}

	private int getJurisdictionsCompletedCount(SearchPeopleAspirationVo searchVo) {
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"searchPeopleAspirationByLevel.countJurisdictionsSubstanceDone",
						searchVo);
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findCompletedIssueList(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows) {

		searchVo.setCompleteCode(ThreeRecordsIssueState.SUBSTANCE_CODE);
		PageInfo<ThreeRecordsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsCompletedCount(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchPeopleAspirationByLevel.findJurisdictionsSubstanceDone",
				searchVo, (page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsFeedBack(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows) {

		searchVo.setTag(ThreeRecordsIssueTag.DONE_ISSUE);
		searchVo.setPeriodCode(ThreeRecordsIssueState.PERIOD_CODE);
		searchVo.setCompleteCode(ThreeRecordsIssueState.STEPCOMPLETE_CODE);// 事件流程表中办理中的状态，值为500
		searchVo.setVerification(ThreeRecordsIssueState.VERIFICATION);// 事件表中已验证状态，值为300
		searchVo.setIssueCompleteCode(ThreeRecordsIssueState.SUBSTANCE_CODE);// 事件流程表中已实质办结的状态，值为700
		PageInfo<ThreeRecordsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsFeedBackCount(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchPeopleAspirationByLevel.findJurisdictionsFeedBack",
				searchVo, (page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsFollow(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows) {

		searchVo.setTag(ThreeRecordsIssueTag.DONE_ISSUE);
		searchVo.setCompleteCode(ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		PageInfo<ThreeRecordsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsFollowCount(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchPeopleAspirationByLevel.findJurisdictionsFollow",
				searchVo, (page - 1) * rows, rows));
		return null;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findPeriodCompletedList(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows) {
		searchVo.setCompleteCode(ThreeRecordsIssueState.PERIOD_CODE);
		PageInfo<ThreeRecordsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsPeroidDoneCount(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchPeopleAspirationByLevel.findJurisdictionsPeriodDone",
				searchVo, (page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsCreateAndDoneList(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows) {

		PageInfo<ThreeRecordsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsCreateAndDoneCount(searchVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchPeopleAspirationByLevel.findJurisdictionsCreateAndDone",
				searchVo, (page - 1) * rows, rows));
		return result;
	}
}
