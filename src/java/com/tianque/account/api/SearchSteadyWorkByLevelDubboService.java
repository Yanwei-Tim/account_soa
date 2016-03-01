package com.tianque.account.api;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.vo.LedgerSteadyWorkVo;
import com.tianque.plugin.account.vo.SearchLedgerSteadyWorkVo;

public interface SearchSteadyWorkByLevelDubboService {

	/**
	 * 查询待办稳定工作台账
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsNeedDo(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 查询已办稳定工作台账
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsDone(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 查询已办结稳定工作台账
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsCompleted(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 查询上报稳定工作台账
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsSubmit(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 查询上级交办稳定工作台账
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsAssgin(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 查询阶段完成稳定工作台账
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<LedgerSteadyWorkVo> findPeriodCompletedList(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 查询已完成稳定工作台账
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<LedgerSteadyWorkVo> findCompletedIssueList(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 查询反馈稳定工作台账
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsFeedBack(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 查询待跟进稳定工作台账
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsFollow(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 查询已办和新建稳定工作台账
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsCreateAndDoneList(
			SearchLedgerSteadyWorkVo searchVo, Integer page, Integer rows,
			String sidx, String sord);

}
