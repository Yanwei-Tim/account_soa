package com.tianque.plugin.account.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.plugin.account.vo.LedgerPoorPeopleVo;
import com.tianque.plugin.account.vo.SearchComprehensiveVo;
import com.tianque.plugin.account.vo.ThreeRecordsCatalogVo;
import com.tianque.plugin.account.vo.ThreeRecordsViewObject;

public interface LedgerPoorPeopleComprehensiveService {
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsNeedDo(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess, Long sourceType, int supportType);
	
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsDone(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess, Long sourceType);
	
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsFeedBack(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess, Long sourceType);
	
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsSubStanceDone(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess, Long sourceType);
	
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsPeriodDone(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess, Long sourceType);
	
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsAssgin(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess, Long sourceType);
	
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsSubmit(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess, Long sourceType);
	
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsCreateAndDone(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Long functionalorgType, Integer viewProcess, Long sourceType);
	
	public Map<Integer, BigDecimal> getPoorPeopleDetail(SearchComprehensiveVo searchVo, Organization org);
	
	public Integer countPoorPeopleCreateAndDone(SearchComprehensiveVo searchVo, List<Long> orgIds,
			Integer page, Integer rows);
	
	public PageInfo<ThreeRecordsViewObject> exportPoorPeopleCreateAndDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows);
	
	public Integer countCataLog(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows);
	public PageInfo<ThreeRecordsCatalogVo> exportCataLog(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows);
}
