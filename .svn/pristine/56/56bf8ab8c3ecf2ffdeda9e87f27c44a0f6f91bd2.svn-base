package com.tianque.plugin.account.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.vo.LedgerPoorPeopleVo;
import com.tianque.plugin.account.vo.SearchLedgerPoorPeopleVo;

public interface SearchPoorPeopleByLevelDao {

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
	PageInfo<LedgerPoorPeopleVo> findJurisdictionsNeedDo(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows);

	/**
	 * 待审核
	 * 
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionAuditDelay(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows);

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
	PageInfo<LedgerPoorPeopleVo> findJurisdictionsDone(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows);

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
	PageInfo<LedgerPoorPeopleVo> findJurisdictionsCompleted(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows);

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
	PageInfo<LedgerPoorPeopleVo> findJurisdictionsSubmit(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows);

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
	PageInfo<LedgerPoorPeopleVo> findJurisdictionsAssgin(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows);

	PageInfo<LedgerPoorPeopleVo> findPeriodCompletedList(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows);

	PageInfo<LedgerPoorPeopleVo> findJurisdictionsFollow(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows);

	PageInfo<LedgerPoorPeopleVo> findJurisdictionsFeedBack(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows);

	PageInfo<LedgerPoorPeopleVo> findCompletedIssueList(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows);

	PageInfo<LedgerPoorPeopleVo> findJurisdictionsCreateAndDoneList(
			SearchLedgerPoorPeopleVo searchVo, Integer page, Integer rows);

}
