package com.tianque.plugin.account.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.DateUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.plugin.account.constants.ThreeRecordsIssueTag;
import com.tianque.plugin.account.constants.ThreeRecordsIssueViewType;
import com.tianque.plugin.account.dao.LedgerPoorPeopleComprehensiveDao;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;
import com.tianque.plugin.account.state.ThreeRecordsIssueSourceState;
import com.tianque.plugin.account.state.ThreeRecordsIssueState;
import com.tianque.plugin.account.vo.LedgerPeopleSubstanceDesc;
import com.tianque.plugin.account.vo.LedgerPoorPeopleDetail;
import com.tianque.plugin.account.vo.LedgerPoorPeopleVo;
import com.tianque.plugin.account.vo.SearchComprehensiveVo;
import com.tianque.plugin.account.vo.ThreeRecordsCatalogVo;
import com.tianque.plugin.account.vo.ThreeRecordsViewObject;

@Repository("ledgerPoorPeopleComprehensiveDao")
public class LedgerPoorPeopleComprehensiveDaoImpl extends AbstractBaseDao<Object> implements
		LedgerPoorPeopleComprehensiveDao {
	
	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsNeedDo(
			SearchComprehensiveVo searchVo, List<Organization> orgs,
			List<Long> childOrg, Integer page, Integer rows,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			Integer isSupported) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSupported", isSupported);
		map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		map.put("tag", ThreeRecordsIssueTag.NEEDDO_ISSUE);
		initMap(map, searchVo, orgs, childOrg, functionalOrgType, sourceType);
		PageInfo<LedgerPoorPeopleVo> result = createIssueVoPageInfoInstance(getJurisdictionsNeedDoCount(map), rows, page);
		if (ThreeRecordsIssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList("ledgerPoorPeopleComprehensive.findJurisdictionsNeedDo", map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList("ledgerPoorPeopleComprehensive.findJurisdictionsNeedDo", map, (page - 1) * rows, rows));
		}
		return result;
	}
	@Override
	public Integer getJurisdictionsNeedDoCount(Map<String, Object> map){
		return (Integer) getSqlMapClientTemplate().queryForObject("ledgerPoorPeopleComprehensive.countfindJurisdictionsNeedDo", map);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsDone(
			SearchComprehensiveVo searchVo, List<Organization> orgs,
			List<Long> childOrg, Integer page, Integer rows,
			Long functionalOrgType, Integer viewProcess, Long sourceType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		map.put("substanceCode", ThreeRecordsIssueState.SUBSTANCE_CODE);
		map.put("issueTag", ThreeRecordsIssueTag.DONE_ISSUE);
		map.put("complete", ThreeRecordsIssueOperate.COMPLETE_CODE);
		map.put("completeStatus", ThreeRecordsIssueState.COMPLETE);
		initMap(map, searchVo, orgs, childOrg, functionalOrgType, sourceType);
		PageInfo<LedgerPoorPeopleVo> result = createIssueVoPageInfoInstance(getJurisdictionsDoneCount(map), rows, page);
		if (ThreeRecordsIssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList("ledgerPoorPeopleComprehensive.findJurisdictionsDone", map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList("ledgerPoorPeopleComprehensive.findJurisdictionsDone", map, (page - 1) * rows, rows));
		}
		return result;
	}
	
	private Integer getJurisdictionsDoneCount(Map<String, Object> map){
		return (Integer) getSqlMapClientTemplate().queryForObject("ledgerPoorPeopleComprehensive.countfindJurisdictionsDone", map);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsFeedBack(
			SearchComprehensiveVo searchVo, List<Organization> orgs,
			List<Long> childOrg, Integer page, Integer rows,
			Long functionalOrgType, Integer viewProcess, Long sourceType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("verification", ThreeRecordsIssueState.VERIFICATION);// 台账表中已验证状态，值为300
		map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);// 台账流程表中办理中的状态，值为500
		map.put("periodCode", ThreeRecordsIssueState.PERIOD_CODE);// 台账流程表中阶段办结的状态，值为600
		map.put("issueCompleteCode", ThreeRecordsIssueState.SUBSTANCE_CODE);// 台账流程表中已实质办结的状态，值为700
		map.put("issueTag", ThreeRecordsIssueTag.DONE_ISSUE);
		initMap(map, searchVo, orgs, childOrg, functionalOrgType, sourceType);
		PageInfo<LedgerPoorPeopleVo> result = createIssueVoPageInfoInstance(getJurisdictionsFeedBackCount(map), rows, page);
		if (ThreeRecordsIssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList("ledgerPoorPeopleComprehensive.findJurisdictionsFeedBack", map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList("ledgerPoorPeopleComprehensive.findJurisdictionsFeedBack", map, (page - 1) * rows, rows));
		}
		return result;
	}
	
	private Integer getJurisdictionsFeedBackCount(Map<String, Object> map){
		return (Integer) getSqlMapClientTemplate().queryForObject("ledgerPoorPeopleComprehensive.countfindJurisdictionsFeedBack", map);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsSubStanceDone(
			SearchComprehensiveVo searchVo, List<Organization> orgs,
			List<Long> childOrg, Integer page, Integer rows,
			Long functionalOrgType, Integer viewProcess, Long sourceType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("completeCode", ThreeRecordsIssueState.SUBSTANCE_CODE);
		initMap(map, searchVo, orgs, childOrg, functionalOrgType, sourceType);
		PageInfo<LedgerPoorPeopleVo> result = createIssueVoPageInfoInstance(getJurisdictionsSubstanceDoneCount(map), rows, page);
		if (ThreeRecordsIssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList("ledgerPoorPeopleComprehensive.findJurisdictionsSubstanceDone", map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList("ledgerPoorPeopleComprehensive.findJurisdictionsSubstanceDone", map, (page - 1) * rows, rows));
		}
		return result;
	}
	
	private Integer getJurisdictionsSubstanceDoneCount(Map<String, Object> map){
		return (Integer) getSqlMapClientTemplate().queryForObject("ledgerPoorPeopleComprehensive.countfindJurisdictionsSubstanceDone", map);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsPeriodDone(
			SearchComprehensiveVo searchVo, List<Organization> orgs,
			List<Long> childOrg, Integer page, Integer rows,
			Long functionalOrgType, Integer viewProcess, Long sourceType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("completeCode", ThreeRecordsIssueState.PERIOD_CODE);
		initMap(map, searchVo, orgs, childOrg, functionalOrgType, sourceType);
		PageInfo<LedgerPoorPeopleVo> result = createIssueVoPageInfoInstance(getJurisdictionsPeriodDoneCount(map), rows, page);
		if (ThreeRecordsIssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList("ledgerPoorPeopleComprehensive.findJurisdictionsPeriodDone", map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList("ledgerPoorPeopleComprehensive.findJurisdictionsPeriodDone", map, (page - 1) * rows, rows));
		}
		return result;
	}
	
	private Integer getJurisdictionsPeriodDoneCount(Map<String, Object> map){
		return (Integer) getSqlMapClientTemplate().queryForObject("ledgerPoorPeopleComprehensive.countfindJurisdictionsPeriodDone", map);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsAssgin(
			SearchComprehensiveVo searchVo, List<Organization> orgs,
			List<Long> childOrg, Integer page, Integer rows,
			Long functionalOrgType, Integer viewProcess, Long sourceType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		map.put("assgin", ThreeRecordsIssueSourceState.assign);
		map.put("issueTag", ThreeRecordsIssueTag.ASSIGN_ISSUE);
		initMap(map, searchVo, orgs, childOrg, functionalOrgType, sourceType);
		PageInfo<LedgerPoorPeopleVo> result = createIssueVoPageInfoInstance(getJurisdictionsAssginCount(map), rows, page);
		if (ThreeRecordsIssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList("ledgerPoorPeopleComprehensive.findJurisdictionsAssgin", map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList("ledgerPoorPeopleComprehensive.findJurisdictionsAssgin", map, (page - 1) * rows, rows));
		}
		return result;
	}
	
	private Integer getJurisdictionsAssginCount(Map<String, Object> map){
		return (Integer) getSqlMapClientTemplate().queryForObject("ledgerPoorPeopleComprehensive.countfindJurisdictionsAssgin", map);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsSubmit(
			SearchComprehensiveVo searchVo, List<Organization> orgs,
			List<Long> childOrg, Integer page, Integer rows,
			Long functionalOrgType, Integer viewProcess, Long sourceType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		map.put("submit", ThreeRecordsIssueSourceState.submit);
		map.put("issueTag", ThreeRecordsIssueTag.SUBMIT_ISSUE);
		initMap(map, searchVo, orgs, childOrg, functionalOrgType, sourceType);
		PageInfo<LedgerPoorPeopleVo> result = createIssueVoPageInfoInstance(getJurisdictionsSubmitCount(map), rows, page);
		if (ThreeRecordsIssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList("ledgerPoorPeopleComprehensive.findJurisdictionsSubmit", map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList("ledgerPoorPeopleComprehensive.findJurisdictionsSubmit", map, (page - 1) * rows, rows));
		}
		return result;
	}
	
	private Integer getJurisdictionsSubmitCount(Map<String, Object> map){
		return (Integer) getSqlMapClientTemplate().queryForObject("ledgerPoorPeopleComprehensive.countfindJurisdictionsSubmit", map);
	}

	@Override
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsCreateAndDone(
			SearchComprehensiveVo searchVo, List<Organization> orgs,
			List<Long> childOrg, Integer page, Integer rows,
			Long functionalOrgType, Integer viewProcess, Long sourceType) {
		Map<String, Object> map = new HashMap<String, Object>();
		initMap(map, searchVo, orgs, childOrg, functionalOrgType, sourceType);
		PageInfo<LedgerPoorPeopleVo> result = createIssueVoPageInfoInstance(getJurisdictionsCreateAndDoneCount(map), rows, page);
		if (ThreeRecordsIssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList("ledgerPoorPeopleComprehensive.findJurisdictionsCreateAndDone", map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList("ledgerPoorPeopleComprehensive.findJurisdictionsCreateAndDone", map, (page - 1) * rows, rows));
		}
		return result;
	}
	
	private Integer getJurisdictionsCreateAndDoneCount(Map<String, Object> map){
		return (Integer) getSqlMapClientTemplate().queryForObject("ledgerPoorPeopleComprehensive.countfindJurisdictionsCreateAndDone", map);
	}
	
	private PageInfo<LedgerPoorPeopleVo> createIssueVoPageInfoInstance(
			int totalRecord, int pageSize, int pageIndex) {
		PageInfo<LedgerPoorPeopleVo> result = new PageInfo<LedgerPoorPeopleVo>();
		result.setTotalRowSize(totalRecord);
		result.setCurrentPage(pageIndex);
		result.setPerPageSize(pageSize);
		return result;
	}
	@Override
	public PageInfo<ThreeRecordsViewObject> exportCreateAndDone(
			SearchComprehensiveVo searchVo, List<Organization> orgs, List<Long> childOrg,
			Integer page, Integer rows){
		Map<String, Object> map = new HashMap<String, Object>();
		initMap(map, searchVo, orgs, childOrg, null, null);
		PageInfo<ThreeRecordsViewObject> result = new PageInfo<ThreeRecordsViewObject>();
		result.setTotalRowSize(getJurisdictionsCreateAndDoneCount(map));
		result.setCurrentPage(rows);
		result.setPerPageSize(page);
		List<ThreeRecordsViewObject> list = getSqlMapClientTemplate().queryForList(
				"ledgerPeopleBaseSituation.getPoorPeopleBaseSituation" , map, (page - 1) * rows, rows);
		result.setResult(list);
		return result;
	}
	@Override
	public Integer exportCountCreateAndDone(
			SearchComprehensiveVo searchVo, List<Organization> orgs, List<Long> childOrg,
			Integer page, Integer rows){
		Map<String, Object> map = new HashMap<String, Object>();
		initMap(map, searchVo, orgs, childOrg, null, null);
		return getJurisdictionsCreateAndDoneCount(map);
	}
	
	@Override
	public Integer countCataLog(SearchComprehensiveVo searchVo, List<Organization> orgs,
			List<Long> childOrg, Integer page, Integer rows, Map<String, Object> map){
		initMap(map, searchVo, orgs, childOrg, null, null);
		return getCountCataLog(map, searchVo);
	}
	@Override
	public PageInfo<ThreeRecordsCatalogVo> exportCataLog(SearchComprehensiveVo searchVo, List<Organization> orgs,
			List<Long> childOrg, Integer page, Integer rows, Map<String, Object> map, String query){
		initMap(map, searchVo, orgs, childOrg, null, null);
		PageInfo<ThreeRecordsCatalogVo> result = new PageInfo<ThreeRecordsCatalogVo>();
		result.setTotalRowSize(getCountCataLog(map, searchVo));
		result.setCurrentPage(rows);
		result.setPerPageSize(page);
		List<ThreeRecordsCatalogVo> list = getSqlMapClientTemplate().queryForList("ledgerPoorPeopleComprehensive." + query, map, (page - 1) * rows, rows);
		result.setResult(list);
		return result;
	}
	
	private Integer getCountCataLog(Map<String, Object> map, SearchComprehensiveVo searchVo){
		int count = 0;
		if (ThreeRecordsIssueViewType.NEED.equalsIgnoreCase(searchVo.getDoneType())) {
			count = getJurisdictionsNeedDoCount(map);
		} else if (ThreeRecordsIssueViewType.DONE.equalsIgnoreCase(searchVo.getDoneType())) {
			count = getJurisdictionsDoneCount(map);
		} else if (ThreeRecordsIssueViewType.PERIOD.equalsIgnoreCase(searchVo.getDoneType())) {
			count = getJurisdictionsPeriodDoneCount(map);
		} else if (ThreeRecordsIssueViewType.COMPLETED.equalsIgnoreCase(searchVo.getDoneType())) {
			count = getJurisdictionsSubstanceDoneCount(map);
		} else if (ThreeRecordsIssueViewType.FEEDBACK.equalsIgnoreCase(searchVo.getDoneType())) {
			count = getJurisdictionsFeedBackCount(map);
		} else if (ThreeRecordsIssueViewType.ASSIGN.equalsIgnoreCase(searchVo.getDoneType())) {
			count = getJurisdictionsAssginCount(map);
		} else if (ThreeRecordsIssueViewType.SUBMIT.equalsIgnoreCase(searchVo.getDoneType())) {
			count = getJurisdictionsSubmitCount(map);
		} else if (ThreeRecordsIssueViewType.SUPPORT.equalsIgnoreCase(searchVo.getDoneType())) {
			count = getJurisdictionsNeedDoCount(map);
		} else if (ThreeRecordsIssueViewType.NOTICE.equalsIgnoreCase(searchVo.getDoneType())) {
			count = getJurisdictionsNeedDoCount(map);
		} else if (ThreeRecordsIssueViewType.CREATE_DONE.equalsIgnoreCase(searchVo.getDoneType())) {
			count = getJurisdictionsCreateAndDoneCount(map);
		} 
		return count;
	}
	
	private void initMap(Map<String, Object> map, SearchComprehensiveVo searchVo, List<Organization> orgs,
			List<Long> childOrg, Long functionalOrgType, Long sourceType){
		List<String> codeList = new ArrayList<String>();
		List<Long> idList = new ArrayList<Long>();
		for(Organization org : orgs){
			codeList.add(org.getOrgInternalCode());
			idList.add(org.getId());
		}
		if (!codeList.isEmpty()) {
			map.put("orgCode", codeList);
		}
		if (!idList.isEmpty()) {
			map.put("orgId", idList);
		}
		if(searchVo.getLedgerType() != null){
			map.put("issueType", searchVo.getLedgerType());
		}
		if(StringUtil.isStringAvaliable(searchVo.getPoorType())){
			map.put("poorType", searchVo.getPoorType());
		}
		if(StringUtil.isStringAvaliable(searchVo.getPermanentAddress())){
			map.put("permanentAddress", searchVo.getPermanentAddress());
		}
		if(StringUtil.isStringAvaliable(searchVo.getSearchValue())){
			map.put("seachValue", searchVo.getSearchValue());
		}
		if (sourceType != null) {
			map.put("sourceType", sourceType);
		}
		if (childOrg != null && childOrg.size() > 0) {
			map.put("targetOrgs", childOrg);
		}
		if(functionalOrgType != null){
			map.put("functionalOrgType", functionalOrgType);
		}
		if(searchVo.getBeginCreateDate() != null){
			map.put("beginCreateDate", searchVo.getBeginCreateDate());
		}
		if(searchVo.getEndCreateDate() != null){
			map.put("endCreateDate", DateUtil.getEndTime(searchVo.getEndCreateDate()));
		}
	}
	
	@Override
	public List<LedgerPoorPeopleDetail> getCountGroupByPoorType(Map<String, Object> map){
		return getSqlMapClientTemplate().queryForList("ledgerPoorPeopleComprehensive.getCountGroupByPoorType", map);
	}
	@Override
	public List<LedgerPoorPeopleDetail> getCountGroupByRequiredType(Map<String, Object> map){
		return getSqlMapClientTemplate().queryForList("ledgerPoorPeopleComprehensive.getCountGroupByRequiredType", map);
	}
	
	@Override
	public List<LedgerPeopleSubstanceDesc> getCountGroupByDesc(Map<String, Object> map){
		return getSqlMapClientTemplate().queryForList("ledgerPoorPeopleComprehensive.getCountGroupByDesc", map);
	}
}
