package com.tianque.plugin.account.dao;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.plugin.account.vo.LedgerPeopleSubstanceDesc;
import com.tianque.plugin.account.vo.LedgerSteadyWorkVo;
import com.tianque.plugin.account.vo.LedgerStedayWorkDetail;
import com.tianque.plugin.account.vo.SearchComprehensiveVo;
import com.tianque.plugin.account.vo.ThreeRecordsCatalogVo;
import com.tianque.plugin.account.vo.ThreeRecordsViewObject;

public interface LedgerSteadyWorkComprehensiveDao {

	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsNeedDo(SearchComprehensiveVo searchVo, List<Organization> orgs, List<Long> childOrg,
			Integer page, Integer rows,Long functionalOrgType, Integer viewProcess, Integer isSupported);
	
	public Integer getJurisdictionsNeedDoCount(Map<String, Object> map);

	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsDone(SearchComprehensiveVo searchVo, List<Organization> orgs, List<Long> childOrg,
			Integer page, Integer rows,Long functionalOrgType, Integer viewProcess);
	
	public Integer getJurisdictionsDone(Map<String, Object> map);

	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsSubmit(SearchComprehensiveVo searchVo, List<Organization> orgs, List<Long> childOrg,
			Integer page, Integer rows,Long functionalOrgType, Integer viewProcess);

	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsSubStanceDone(SearchComprehensiveVo searchVo, List<Organization> orgs, List<Long> childOrg,
			Integer page, Integer rows,Long functionalOrgType, Integer viewProcess);

	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsPeriodDone(SearchComprehensiveVo searchVo, List<Organization> orgs, List<Long> childOrg,
			Integer page, Integer rows,Long functionalOrgType, Integer viewProcess);

	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsFeedBack(SearchComprehensiveVo searchVo, List<Organization> orgs, List<Long> childOrg,
			Integer page, Integer rows,Long functionalOrgType, Integer viewProcess);

	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsAssgin(SearchComprehensiveVo searchVo, List<Organization> orgs, List<Long> childOrg,
			Integer page, Integer rows,Long functionalOrgType, Integer viewProcess);

	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsCreateAndDone(SearchComprehensiveVo searchVo, List<Organization> orgs, List<Long> childOrg,
			Integer page, Integer rows,Long functionalOrgType, Integer viewProcess);
	
	public List<LedgerStedayWorkDetail> getCountGroupBySteadyWorkType(Map<String, Object> map);
	public List<LedgerStedayWorkDetail> getCountGroupBySteadyWorkWarnLevel(Map<String, Object> map);
	public List<LedgerPeopleSubstanceDesc> getCountGroupByDesc(Map<String, Object> map);
	
	public PageInfo<ThreeRecordsViewObject> exportCreateAndDone(
			SearchComprehensiveVo searchVo, List<Organization> orgs, List<Long> childOrg,
			Integer page, Integer rows);
	
	public Integer exportCountCreateAndDone(
			SearchComprehensiveVo searchVo, List<Organization> orgs, List<Long> childOrg,
			Integer page, Integer rows);
	
	public Integer countCataLog(SearchComprehensiveVo searchVo, List<Organization> orgs,
			List<Long> childOrg, Integer page, Integer rows, Map<String, Object> map);
	
	public PageInfo<ThreeRecordsCatalogVo> exportCataLog(SearchComprehensiveVo searchVo, List<Organization> orgs,
			List<Long> childOrg, Integer page, Integer rows, Map<String, Object> map, String query);
}
