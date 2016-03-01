package com.tianque.plugin.account.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.plugin.account.vo.SearchComprehensiveVo;
import com.tianque.plugin.account.vo.ThreeRecordsCatalogVo;
import com.tianque.plugin.account.vo.ThreeRecordsIssueViewObject;
import com.tianque.plugin.account.vo.ThreeRecordsViewObject;

public interface ThreeRecordsComprehensiveService {
	/**
	 * 待办/抄告/协办
	 * @param searchVo
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param isSupported
	 * @return
	 */
	PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsNeedDo(
			SearchComprehensiveVo searchVo, List<Long> orgIds,
			Integer page, Integer rows, Integer isSupported);
	
	/**
	 * 程序性办结
	 * @param searchVo
	 * @param orgId
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds,
			Integer page, Integer rows);
	
	/**
	 * 阶段性办结
	 * @param searchVo
	 * @param orgId
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsPeriodDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds,
			Integer page, Integer rows);
	
	
	/**
	 * 实质性办结
	 * @param searchVo
	 * @param orgId
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsSubStanceDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds,
			Integer page, Integer rows);
	
	
	/**
	 * 待反馈
	 * @param searchVo
	 * @param orgId
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsFeedBack(
			SearchComprehensiveVo searchVo,  List<Long> orgIds,
			Integer page, Integer rows);

	
	/**
	 * 交办
	 * @param searchVo
	 * @param orgId
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsAssgin(
			SearchComprehensiveVo searchVo, List<Long> orgIds,
			Integer page, Integer rows);

	/**
	 * 上报
	 * @param searchVo
	 * @param orgId
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsSubmit(
			SearchComprehensiveVo searchVo, List<Long> orgIds,
			Integer page, Integer rows);
	
	
	/**
	 * 已办
	 * @param searchVo
	 * @param orgId
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsCreateAndDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds,
			Integer page, Integer rows);
	
	/**
	 * 导出综合查询详细信息计数
	 * @param searchVo
	 * @param orgIds
	 * @param page
	 * @param rows
	 * @return
	 */
	public Integer countfindJurisdictionsCreateAndDone(SearchComprehensiveVo searchVo, List<Long> orgIds,
			Integer page, Integer rows);
	
	/**
	 * 导出综合查询详细信息
	 * @param searchVo
	 * @param orgIds
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<ThreeRecordsViewObject> exportCreateAndDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds,
			Integer page, Integer rows);
	
	public Map<Integer, BigDecimal> getPeopleDetail(SearchComprehensiveVo searchVo, Organization org);
	
	public PageInfo<ThreeRecordsCatalogVo> exportCataLog(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows);
	public Integer countCataLog(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows);
}
