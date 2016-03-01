package com.tianque.plugin.account.service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.plugin.account.vo.LedgerSteadyWorkVo;
import com.tianque.plugin.account.vo.SearchComprehensiveVo;
import com.tianque.plugin.account.vo.ThreeRecordsCatalogVo;
import com.tianque.plugin.account.vo.ThreeRecordsViewObject;

public interface LedgerSteadyWorkComprehensiveService {

	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsNeedDo(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows, Long functionalorgType, Integer viewProcess, int supportType);

	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsDone(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows, Long functionalOrgType, Integer viewProcess);

	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsAssgin(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows, Long functionalOrgType, Integer viewProcess);

	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsFeedBack(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows, Long functionalOrgType, Integer viewProcess);

	public 	PageInfo<LedgerSteadyWorkVo> findJurisdictionsPeriodDone(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows, Long functionalOrgType, Integer viewProcess);

	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsSubstanceDone(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows, Long functionalOrgType, Integer viewProcess);

	public 	PageInfo<LedgerSteadyWorkVo> findJurisdictionsSubmit(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows, Long functionalOrgType, Integer viewProcess);

	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsCreateAndDone(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows, Long functionalOrgType, Integer viewProcess);
	
	public Map<Integer, BigDecimal> getSteadyWorkDetail(SearchComprehensiveVo searchVo, Organization org);
	
	public Integer countPoorPeopleCreateAndDone(SearchComprehensiveVo searchVo, List<Long> orgIds,Integer page, Integer rows);
	
	public PageInfo<ThreeRecordsViewObject> exportPoorPeopleCreateAndDone(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows);
	
	public Integer countCataLog(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows);
	
	public PageInfo<ThreeRecordsCatalogVo> exportCataLog(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows);
}
