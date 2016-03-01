package com.tianque.plugin.account.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.vo.LedgerSteadyWorkVo;
import com.tianque.plugin.account.vo.SearchLedgerSteadyWorkVo;

public interface SearchSteadyWorkByLevelDao {

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
	PageInfo<LedgerSteadyWorkVo> findJurisdictionsNeedDo(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows);

	/**
	 * 待审核
	 * 
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionAuditDelay(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows);

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
	PageInfo<LedgerSteadyWorkVo> findJurisdictionsDone(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows);

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
	PageInfo<LedgerSteadyWorkVo> findJurisdictionsCompleted(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows);

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
	PageInfo<LedgerSteadyWorkVo> findJurisdictionsSubmit(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows);

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
	PageInfo<LedgerSteadyWorkVo> findJurisdictionsAssgin(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows);

	PageInfo<LedgerSteadyWorkVo> findPeriodCompletedList(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows);

	PageInfo<LedgerSteadyWorkVo> findJurisdictionsFollow(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows);

	PageInfo<LedgerSteadyWorkVo> findJurisdictionsFeedBack(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows);

	PageInfo<LedgerSteadyWorkVo> findCompletedIssueList(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows);

	PageInfo<LedgerSteadyWorkVo> findJurisdictionsCreateAndDoneList(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows);

}
