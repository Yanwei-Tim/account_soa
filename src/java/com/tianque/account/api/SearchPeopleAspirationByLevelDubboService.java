package com.tianque.account.api;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.vo.SearchPeopleAspirationVo;
import com.tianque.plugin.account.vo.ThreeRecordsIssueViewObject;

public interface SearchPeopleAspirationByLevelDubboService {

	/**
	 * 查询待办民生台账
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsNeedDo(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 查询已办民生台账
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsDone(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 查询已办结民生台账
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsCompleted(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 查询上报民生台账
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsSubmit(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 查询上级交办民生台账
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsAssgin(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 查询阶段完成民生台账
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<ThreeRecordsIssueViewObject> findPeriodCompletedList(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 查询已完成民生台账
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<ThreeRecordsIssueViewObject> findCompletedIssueList(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 查询反馈民生台账
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsFeedBack(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 查询待跟进民生台账
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsFollow(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 查询已办和新建的民生台账
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsCreateAndDoneList(
			SearchPeopleAspirationVo searchVo, Integer page, Integer rows,
			String sidx, String sord);

}
