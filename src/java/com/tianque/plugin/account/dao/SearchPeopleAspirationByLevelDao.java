package com.tianque.plugin.account.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.vo.SearchPeopleAspirationVo;
import com.tianque.plugin.account.vo.ThreeRecordsIssueViewObject;

public interface SearchPeopleAspirationByLevelDao {

	/**
	 * 代办
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsNeedDo(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows);

	/**
	 * 待审核
	 * 
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionAuditDelay(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows);

	/**
	 * 已办
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsDone(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows);

	/**
	 * 已办结
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsCompleted(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows);

	/**
	 * 上报
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsSubmit(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows);

	/**
	 * 上级交办
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsAssgin(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows);

	PageInfo<ThreeRecordsIssueViewObject> findPeriodCompletedList(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows);

	PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsFollow(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows);

	PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsFeedBack(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows);

	PageInfo<ThreeRecordsIssueViewObject> findCompletedIssueList(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows);

	PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsCreateAndDoneList(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows);

}
