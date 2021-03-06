package com.tianque.plugin.account.service.impl;

import java.math.BigDecimal;
import java.text.Bidi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.NDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.DateUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.account.constants.CompleteType;
import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.constants.LedgerPeopleTableName;
import com.tianque.plugin.account.constants.ThreeRecordsIssueTag;
import com.tianque.plugin.account.constants.ThreeRecordsIssueViewType;
import com.tianque.plugin.account.dao.ThreeRecordsComprehensiveDao;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.service.ThreeRecordsComprehensiveService;
import com.tianque.plugin.account.service.ThreeRecordsIssueLogService;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;
import com.tianque.plugin.account.state.ThreeRecordsIssueSourceState;
import com.tianque.plugin.account.state.ThreeRecordsIssueState;
import com.tianque.plugin.account.util.ComparisonAttribute;
import com.tianque.plugin.account.util.ThreeRecordsIssueOperationUtil;
import com.tianque.plugin.account.vo.LedgerAgricultureDetail;
import com.tianque.plugin.account.vo.LedgerDetail;
import com.tianque.plugin.account.vo.LedgerEducationDetail;
import com.tianque.plugin.account.vo.LedgerEnergyDetail;
import com.tianque.plugin.account.vo.LedgerEnvironmentDetail;
import com.tianque.plugin.account.vo.LedgerLaborDetail;
import com.tianque.plugin.account.vo.LedgerMedicalDetail;
import com.tianque.plugin.account.vo.LedgerOtherDetail;
import com.tianque.plugin.account.vo.LedgerPeopleSubstanceDesc;
import com.tianque.plugin.account.vo.LedgerScienceDetail;
import com.tianque.plugin.account.vo.LedgerTownDetail;
import com.tianque.plugin.account.vo.LedgerTrafficDetail;
import com.tianque.plugin.account.vo.LedgerWaterDetail;
import com.tianque.plugin.account.vo.SearchComprehensiveVo;
import com.tianque.plugin.account.vo.ThreeRecordsCatalogVo;
import com.tianque.plugin.account.vo.ThreeRecordsIssueViewObject;
import com.tianque.plugin.account.vo.ThreeRecordsViewObject;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;
import com.tianque.userAuth.api.PropertyDictDubboService;

@Service("threeRecordsComprehensiveService")
@Transactional
public class ThreeRecordsComprehensiveServiceImpl extends AbstractBaseService implements
		ThreeRecordsComprehensiveService {

	@Autowired
	protected ThreeRecordsComprehensiveDao comprehensiveDao;
	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;
	@Autowired
	private ThreeRecordsIssueLogService issueLogService;
	@Autowired
	private PropertyDictDubboService propertyDictDubboService;
	
	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsNeedDo(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows, Integer isSupported) {
		if (orgIds.isEmpty()) {
			throw new BusinessValidationException("参数未获得！");
		}
		List<Organization> orgs = getOrgs(orgIds);
		List<Long> childOrg = getChildIds(orgs);
		orgIsFun(orgs, searchVo);
		return comprehensiveDao.findJurisdictionsNeedDo(searchVo, orgs, childOrg, page, rows, isSupported);
	}
	
	private List<Organization> getOrgs(List<Long> orgIds){
		List<Organization> orgs = new ArrayList<Organization>();
		for(Long orgId : orgIds){
			Organization org = organizationDubboService.getFullOrgById(orgId);
			orgs.add(org);
		}
		return orgs;
	}
	
	private List<Long> getChildIds(List<Organization> orgs){
		List<Long> childOrg = new ArrayList<Long>();
		for(Organization org : orgs){
			List<Long> childIds = ComparisonAttribute.getOrgIds(organizationDubboService.findOrganizationsByParentId(org.getId()));
			if(childIds != null && !childIds.isEmpty()){
				childOrg.addAll(childIds);
			}
		}
		return childOrg;
	}
	
	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows) {
		if (orgIds.isEmpty()) {
			throw new BusinessValidationException("参数未获得！");
		}
		List<Organization> orgs = getOrgs(orgIds);
		List<Long> childOrg = getChildIds(orgs);
		orgIsFun(orgs, searchVo);
		PageInfo<ThreeRecordsIssueViewObject> result = comprehensiveDao.findJurisdictionsDone(searchVo, orgs, childOrg, page, rows);
		
		if(StringUtil.isStringAvaliable(searchVo.getDetailDoneType()) && !searchVo.getDetailDoneType().equals(CompleteType.ALL_QUERY)){
			return getDeatilIssues(searchVo, page, rows, result);
		}else{
			return result;
		}
	}
	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsPeriodDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows) {
		if (orgIds.isEmpty()) {
			throw new BusinessValidationException("参数未获得！");
		}
		List<Organization> orgs = getOrgs(orgIds);
		List<Long> childOrg = getChildIds(orgs);
		orgIsFun(orgs, searchVo);
		PageInfo<ThreeRecordsIssueViewObject> result = comprehensiveDao.findJurisdictionsPeriodDone(searchVo, orgs, childOrg, page, rows);
		
		if(StringUtil.isStringAvaliable(searchVo.getDetailDoneType()) && !searchVo.getDetailDoneType().equals(CompleteType.ALL_QUERY)){
			return getDeatilIssues(searchVo, page, rows, result);
		}else{
			return result;
		}
	}
	
	private PageInfo<ThreeRecordsIssueViewObject> createIssueVoPageInfoInstance(
			int totalRecord, int pageSize, int pageIndex) {
		PageInfo<ThreeRecordsIssueViewObject> result = new PageInfo<ThreeRecordsIssueViewObject>();
		result.setTotalRowSize(totalRecord);
		result.setCurrentPage(pageIndex);
		result.setPerPageSize(pageSize);
		return result;
	}
	
	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsSubStanceDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows) {
		if (orgIds.isEmpty()) {
			throw new BusinessValidationException("参数未获得！");
		}
		List<Organization> orgs = getOrgs(orgIds);
		List<Long> childOrg = getChildIds(orgs);
		orgIsFun(orgs, searchVo);
		PageInfo<ThreeRecordsIssueViewObject> result = comprehensiveDao.findJurisdictionsSubStanceDone(searchVo, orgs, childOrg, page, rows);
		
		if(StringUtil.isStringAvaliable(searchVo.getDetailDoneType()) && !searchVo.getDetailDoneType().equals(CompleteType.ALL_QUERY)){
			return getDeatilIssues(searchVo, page, rows, result);
		}else{
			return result;
		}
	}
	
	private PageInfo<ThreeRecordsIssueViewObject> getDeatilIssues(SearchComprehensiveVo searchVo, Integer page, Integer rows, 
			PageInfo<ThreeRecordsIssueViewObject> result){
		List<ThreeRecordsIssueViewObject> list = new ArrayList<ThreeRecordsIssueViewObject>();
		for(ThreeRecordsIssueViewObject issue : result.getResult()){
			List<ThreeRecordsIssueLogNew> logs = issueLogService.getLogsByStepId(issue.getIssueStepId());
			for(ThreeRecordsIssueLogNew log : logs){
				if(log.getDealDescription().contains(searchVo.getDetailDoneType())){
					list.add(issue);
					break;
				}
			}
		}
		PageInfo<ThreeRecordsIssueViewObject> results = createIssueVoPageInfoInstance(list.size(), rows, page);
		results.setResult(list);
		return results;
	}
	
	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsFeedBack(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows) {
		if (orgIds.isEmpty()) {
			throw new BusinessValidationException("参数未获得！");
		}
		List<Organization> orgs = getOrgs(orgIds);
		List<Long> childOrg = getChildIds(orgs);
		orgIsFun(orgs, searchVo);
		return comprehensiveDao.findJurisdictionsFeedBack(searchVo, orgs, childOrg, page, rows);
	}
	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsAssgin(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows) {
		if (orgIds.isEmpty()) {
			throw new BusinessValidationException("参数未获得！");
		}
		List<Organization> orgs = getOrgs(orgIds);
		List<Long> childOrg = getChildIds(orgs);
		orgIsFun(orgs, searchVo);
		return comprehensiveDao.findJurisdictionsAssgin(searchVo, orgs, childOrg, page, rows);
	}
	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsSubmit(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows) {
		if (orgIds.isEmpty()) {
			throw new BusinessValidationException("参数未获得！");
		}
		List<Organization> orgs = getOrgs(orgIds);
		List<Long> childOrg = getChildIds(orgs);
		orgIsFun(orgs, searchVo);
		return comprehensiveDao.findJurisdictionsSubmit(searchVo, orgs, childOrg, page, rows);
	}
	@Override
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsCreateAndDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page,
			Integer rows) {
		if (orgIds.isEmpty()) {
			throw new BusinessValidationException("参数未获得！");
		}
		List<Organization> orgs = getOrgs(orgIds);
		List<Long> childOrg = getChildIds(orgs);
		orgIsFun(orgs, searchVo);
		return comprehensiveDao.findJurisdictionsCreateAndDone(searchVo, orgs, childOrg, page, rows);
	}
	@Override
	public Integer countfindJurisdictionsCreateAndDone(SearchComprehensiveVo searchVo, List<Long> orgIds,
			Integer page, Integer rows){
		if (orgIds.isEmpty()) {
			throw new BusinessValidationException("参数未获得！");
		}
		List<Organization> orgs = getOrgs(orgIds);
		List<Long> childOrg = getChildIds(orgs);
		orgIsFun(orgs, searchVo);
		return comprehensiveDao.exportCountCreateAndDone(searchVo, orgs, childOrg, page, rows);
	}
	@Override
	public PageInfo<ThreeRecordsViewObject> exportCreateAndDone(
			SearchComprehensiveVo searchVo, List<Long> orgIds,
			Integer page, Integer rows){
		if (orgIds.isEmpty()) {
			throw new BusinessValidationException("参数未获得！");
		}
		List<Organization> orgs = getOrgs(orgIds);
		List<Long> childOrg = getChildIds(orgs);
		orgIsFun(orgs, searchVo);
		String query = "getWaterBaseSituation";
		if(searchVo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_TRAFFIC){
			query = "getTrafficBaseSituation";
		}else if(searchVo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_AGRICULTURE){
			query = "getAgricultureBaseSituation";
		}else if(searchVo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_EDUCATION){
			query = "getEducationBaseSituation";
		}else if(searchVo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_ENERGY){
			query = "getEnergyBaseSituation";
		}else if(searchVo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_ENVIRONMENT){
			query = "getEnvironmentBaseSituation";
		}else if(searchVo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_LABOR){
			query = "getLaborBaseSituation";
		}else if(searchVo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_MEDICAL){
			query = "getMedicalBaseSituation";
		}else if(searchVo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_OTHER){
			query = "getOtherBaseSituation";
		}else if(searchVo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_SCIENCE){
			query = "getScienceBaseSituation";
		}else if(searchVo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_TOWN){
			query = "getTownBaseSituation";
		}
		return comprehensiveDao.exportCreateAndDone(searchVo, orgs, childOrg, page, rows, query);
	}
	
	@Override
	public Integer countCataLog(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows){
		if (orgIds.isEmpty()) {
			throw new BusinessValidationException("参数未获得！");
		}
		List<Organization> orgs = getOrgs(orgIds);
		List<Long> childOrg = getChildIds(orgs);
		orgIsFun(orgs, searchVo);
		Map<String, Object> map = new HashMap<String, Object>();
		initDoneTypeMap(map, searchVo);
		return comprehensiveDao.countCataLog(searchVo, orgs, childOrg, page, rows, map);
	}
	@Override
	public PageInfo<ThreeRecordsCatalogVo> exportCataLog(SearchComprehensiveVo searchVo, List<Long> orgIds, Integer page, Integer rows){
		if (orgIds.isEmpty()) {
			throw new BusinessValidationException("参数未获得！");
		}
		List<Organization> orgs = getOrgs(orgIds);
		List<Long> childOrg = getChildIds(orgs);
		orgIsFun(orgs, searchVo);
		String query = "getNeedoneCataLog";
		Map<String, Object> map = new HashMap<String, Object>();
		initDoneTypeMap(map, searchVo);
		if (ThreeRecordsIssueViewType.NEED.equalsIgnoreCase(searchVo.getDoneType())) {
			query = "getNeedoneCataLog";
		} else if (ThreeRecordsIssueViewType.DONE.equalsIgnoreCase(searchVo.getDoneType())) {
			query = "getDoneCataLog";
		} else if (ThreeRecordsIssueViewType.PERIOD.equalsIgnoreCase(searchVo.getDoneType())) {
			query = "getPeriodDoneCataLog";
		} else if (ThreeRecordsIssueViewType.COMPLETED.equalsIgnoreCase(searchVo.getDoneType())) {
			query = "getSubstanceDoneCataLog";
		} else if (ThreeRecordsIssueViewType.FEEDBACK.equalsIgnoreCase(searchVo.getDoneType())) {
			query = "getFeedBackCataLog";
		} else if (ThreeRecordsIssueViewType.ASSIGN.equalsIgnoreCase(searchVo.getDoneType())) {
			query = "getAssginCataLog";
		} else if (ThreeRecordsIssueViewType.SUBMIT.equalsIgnoreCase(searchVo.getDoneType())) {
			query = "getSubmitCataLog";
		} else if (ThreeRecordsIssueViewType.SUPPORT.equalsIgnoreCase(searchVo.getDoneType())) {
			query = "getNeedoneCataLog";
		} else if (ThreeRecordsIssueViewType.NOTICE.equalsIgnoreCase(searchVo.getDoneType())) {
			query = "getNeedoneCataLog";
		} else if (ThreeRecordsIssueViewType.CREATE_DONE.equalsIgnoreCase(searchVo.getDoneType())) {
			query = "getCreateAndDoneCataLog";
		} else {
			throw new BusinessValidationException("参数未获得！");
		}
		return comprehensiveDao.exportCataLog(searchVo, orgs, childOrg, page, rows, map, query);
	}
	
	private void initDoneTypeMap(Map<String, Object> map, SearchComprehensiveVo searchVo){
		if (ThreeRecordsIssueViewType.NEED.equalsIgnoreCase(searchVo.getDoneType())) {
			map.put("isSupported", LedgerConstants.OPERATE_TYPE_SPONSOR);
			map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		} else if (ThreeRecordsIssueViewType.DONE.equalsIgnoreCase(searchVo.getDoneType())) {
			map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
			map.put("substanceCode", ThreeRecordsIssueState.SUBSTANCE_CODE);
			map.put("issueTag", ThreeRecordsIssueTag.DONE_ISSUE);
			map.put("complete", ThreeRecordsIssueOperate.COMPLETE_CODE);
		} else if (ThreeRecordsIssueViewType.PERIOD.equalsIgnoreCase(searchVo.getDoneType())) {
			map.put("completeCode", ThreeRecordsIssueState.PERIOD_CODE);
		} else if (ThreeRecordsIssueViewType.COMPLETED.equalsIgnoreCase(searchVo.getDoneType())) {
			map.put("completeCode", ThreeRecordsIssueState.SUBSTANCE_CODE);
		} else if (ThreeRecordsIssueViewType.FEEDBACK.equalsIgnoreCase(searchVo.getDoneType())) {
			map.put("verification", ThreeRecordsIssueState.VERIFICATION);// 台账表中已验证状态，值为300
			map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);// 台账流程表中办理中的状态，值为500
			map.put("periodCode", ThreeRecordsIssueState.PERIOD_CODE);// 台账流程表中阶段办结的状态，值为600
			map.put("issueCompleteCode", ThreeRecordsIssueState.SUBSTANCE_CODE);// 台账流程表中已实质办结的状态，值为700
			map.put("issueTag", ThreeRecordsIssueTag.DONE_ISSUE);
		} else if (ThreeRecordsIssueViewType.ASSIGN.equalsIgnoreCase(searchVo.getDoneType())) {
			map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
			map.put("assgin", ThreeRecordsIssueSourceState.assign);
			map.put("issueTag", ThreeRecordsIssueTag.ASSIGN_ISSUE);
		} else if (ThreeRecordsIssueViewType.SUBMIT.equalsIgnoreCase(searchVo.getDoneType())) {
			map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
			map.put("submit", ThreeRecordsIssueSourceState.submit);
			map.put("issueTag", ThreeRecordsIssueTag.SUBMIT_ISSUE);
		} else if (ThreeRecordsIssueViewType.SUPPORT.equalsIgnoreCase(searchVo.getDoneType())) {
			map.put("isSupported", LedgerConstants.OPERATE_TYPE_SUPPORT);
		} else if (ThreeRecordsIssueViewType.NOTICE.equalsIgnoreCase(searchVo.getDoneType())) {
			map.put("isSupported", LedgerConstants.OPERATE_TYPE_NOTICE);
		} else if (ThreeRecordsIssueViewType.CREATE_DONE.equalsIgnoreCase(searchVo.getDoneType())) {
			map.put("issueTag", ThreeRecordsIssueTag.DONE_ISSUE);
		}
	}
	
	private void orgIsFun(List<Organization> orgs, SearchComprehensiveVo searchVo){
		for(Organization org : orgs){
			org.setOrgType(propertyDictDubboService.getPropertyDictById(org.getOrgType().getId()));
			if(ThreeRecordsIssueOperationUtil.orgIsFunctional(org)){
				searchVo.setSearchValue("present");
				break;
			}
		}
	}
	
	private void initMap(Map<String, Object> map, SearchComprehensiveVo searchVo, Organization org){
		map.put("orgCode", org.getOrgInternalCode());
		if(searchVo.getSearchValue() != null){
			map.put("seachValue", searchVo.getSearchValue());
		}
		if(searchVo.getLedgerType() != null){
			map.put("issueType", searchVo.getLedgerType());
			map.put("tableName", LedgerPeopleTableName.getLedgerTableName(Integer.parseInt(searchVo.getLedgerType()+"")));
		}
		if(searchVo.getProjectCategory() != null){
			map.put("projectCategory", searchVo.getProjectCategory());
		}
		if(searchVo.getLedgerType() != LedgerConstants.PEOPLEASPIRATION_LABOR){
			if(StringUtil.isStringAvaliable(searchVo.getBuildAddress())){
				map.put("buildAddress", searchVo.getBuildAddress());
			}
			if(searchVo.getBuildType() != null){
				map.put("buildType", searchVo.getBuildType());
			}
			if(searchVo.getIsGtPlannedInvestment() != null){
				map.put("isGtPlannedInvestment", searchVo.getIsGtPlannedInvestment());
			}
			if(searchVo.getPlannedInvestment() != null){
				map.put("plannedInvestment", searchVo.getPlannedInvestment());
			}
			if(searchVo.getIsGtbeneficiaryNumber() != null){
				map.put("isGtbeneficiaryNumber", searchVo.getIsGtbeneficiaryNumber());
			}
			if(searchVo.getBeneficiaryNumber() != null){
				map.put("beneficiaryNumber", searchVo.getBeneficiaryNumber());
			}
			if(searchVo.getIsGtSelfFund() != null){
				map.put("isGtSelfFund", searchVo.getIsGtSelfFund());
			}
			if(searchVo.getSelfFund() != null){
				map.put("selfFund", searchVo.getSelfFund());
			}
			if(searchVo.getIsGtGapFund() != null){
				map.put("isGtGapFund", searchVo.getIsGtGapFund());
			}
			if(searchVo.getGapFund() != null){
				map.put("gapFund", searchVo.getGapFund());
			}
		}
		if(searchVo.getBeginCreateDate() != null){
			map.put("beginCreateDate", searchVo.getBeginCreateDate());
		}
		if(searchVo.getEndCreateDate() != null){
			map.put("endCreateDate",  DateUtil.getEndTime(searchVo.getEndCreateDate()));
		}
		if(StringUtil.isStringAvaliable(searchVo.getAppealContent())){
			map.put("appealContent", searchVo.getAppealContent());
		}
		map.put("isSupported", 0);
		map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		map.put("substanceCode", ThreeRecordsIssueState.SUBSTANCE_CODE);
		map.put("issueTag", ThreeRecordsIssueTag.DONE_ISSUE);
		map.put("complete", ThreeRecordsIssueOperate.COMPLETE_CODE);
	}
	
	@Override
	public Map<Integer, BigDecimal> getPeopleDetail(SearchComprehensiveVo searchVo, Organization org){
		Map<String, Object> map = new HashMap<String, Object>();
		initMap(map, searchVo, org);
		if(searchVo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_WATER){
			return getWaterDetail(map);
		}else if(searchVo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_TRAFFIC){
			return getTrafficDetail(map);
		}else if(searchVo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_EDUCATION){
			return getEducationDetail(map);
		}else if(searchVo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_ENERGY){
			return getEnergyDetail(map);
		}else if(searchVo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_SCIENCE){
			return getScienceDetail(map);
		}else if(searchVo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_MEDICAL){
			return getMedicalDetail(map);
		}else if(searchVo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_LABOR){
			return getLaborDetail(map);
		}else if(searchVo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_ENVIRONMENT){
			return getEnvironmentDetail(map);
		}else if(searchVo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_TOWN){
			return getTownDetail(map);
		}else if(searchVo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_AGRICULTURE){
			return getAgricultureDetail(map);
		}else if(searchVo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_OTHER){
			return getOtherDetail(map);
		}
		return null;
	}
	
	private Map<Integer, BigDecimal> getOtherDetail(Map<String, Object> map){
		Map<Integer, BigDecimal> rMap = initResultMap(23);
		List<LedgerOtherDetail> aList = comprehensiveDao.getAllOtherResult(map);
		List<PropertyDict> proTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.OTHER_PROJECT);
		int count = 0,responseGroupNo = 0,beneficiaryNumber = 0;
		float plannedInvestment = 0,selfFund = 0,gapFund = 0;
		for(LedgerOtherDetail other : aList){
			count = count + other.getCount();//1
			responseGroupNo = responseGroupNo + other.getResponseGroupNo();//2
			plannedInvestment = plannedInvestment + other.getPlannedInvestment().floatValue();//3
			selfFund = selfFund + other.getSelfFund().floatValue();//4
			gapFund = gapFund + other.getGapFund().floatValue();//5
			beneficiaryNumber = beneficiaryNumber + other.getBeneficiaryNumber();//6
			if(getDisplayName(proTypes, other.getProjectCategory()).equals("建设工程")){
				rMap.put(9, new BigDecimal(other.getCount()));
				addLedgerDetailMap(rMap, 7, 8, 10, 11, 12, 13, new BigDecimal(other.getCount()), new BigDecimal(other.getResponseGroupNo()),
						other.getPlannedInvestment(), other.getSelfFund(), other.getGapFund(), new BigDecimal(other.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, other.getProjectCategory()).equals("其他")){
				addLedgerDetailMap(rMap, 14, 15, 16, 17, 18, 19, new BigDecimal(other.getCount()), new BigDecimal(other.getResponseGroupNo()),
						other.getPlannedInvestment(), other.getSelfFund(), other.getGapFund(), new BigDecimal(other.getBeneficiaryNumber()));
			}
		}
		addLedgerDetailMap(rMap, 1, 2, 3, 4, 5, 6, new BigDecimal(count), new BigDecimal(responseGroupNo),
				new BigDecimal(plannedInvestment), new BigDecimal(selfFund), new BigDecimal(gapFund), new BigDecimal(beneficiaryNumber));
		addLedgerDoneMap(rMap, map, 20, 21, 22, 23);
		return rMap;
	}
	
	private Map<Integer, BigDecimal> getAgricultureDetail(Map<String, Object> map){
		Map<Integer, BigDecimal> rMap = initResultMap(61);
		List<LedgerAgricultureDetail> aList = comprehensiveDao.getAllAgricultureResult(map);
		List<LedgerAgricultureDetail> subList = comprehensiveDao.getSubAgricultureResult(map);
		List<PropertyDict> proTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.AGRICULTURE_PROJECT);
		List<PropertyDict> subTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.AGRICULTURE_PROJECT_SUB);
		int count = 0,responseGroupNo = 0,beneficiaryNumber = 0;
		float plannedInvestment = 0,selfFund = 0,gapFund = 0;
		for(LedgerAgricultureDetail a : aList){
			count = count + a.getCount();//1
			responseGroupNo = responseGroupNo + a.getResponseGroupNo();//2
			plannedInvestment = plannedInvestment + a.getPlannedInvestment().floatValue();//3
			selfFund = selfFund + a.getSelfFund().floatValue();//4
			gapFund = gapFund + a.getGapFund().floatValue();//5
			beneficiaryNumber = beneficiaryNumber + a.getBeneficiaryNumber();//6
			if(getDisplayName(proTypes, a.getProjectCategory()).equals("农业产业化")){
				addLedgerDetailMap(rMap, 7, 8, 9, 10, 11, 12, new BigDecimal(a.getCount()), new BigDecimal(a.getResponseGroupNo()),
						a.getPlannedInvestment(), a.getSelfFund(), a.getGapFund(), new BigDecimal(a.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, a.getProjectCategory()).equals("田间工程")){
				addLedgerDetailMap(rMap, 22, 23, 24, 25, 26, 27, new BigDecimal(a.getCount()), new BigDecimal(a.getResponseGroupNo()),
						a.getPlannedInvestment(), a.getSelfFund(), a.getGapFund(), new BigDecimal(a.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, a.getProjectCategory()).equals("电力提灌站")){
				addLedgerDetailMap(rMap, 34, 35, 36, 37, 38, 39, new BigDecimal(a.getCount()), new BigDecimal(a.getResponseGroupNo()),
						a.getPlannedInvestment(), a.getSelfFund(), a.getGapFund(), new BigDecimal(a.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, a.getProjectCategory()).equals("农业培训")){
				addLedgerDetailMap(rMap, 42, 43, 44, 45, 46, 47, new BigDecimal(a.getCount()), new BigDecimal(a.getResponseGroupNo()),
						a.getPlannedInvestment(), a.getSelfFund(), a.getGapFund(), new BigDecimal(a.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, a.getProjectCategory()).equals("其它")){
				addLedgerDetailMap(rMap, 52, 53, 54, 55, 56, 57, new BigDecimal(a.getCount()), new BigDecimal(a.getResponseGroupNo()),
						a.getPlannedInvestment(), a.getSelfFund(), a.getGapFund(), new BigDecimal(a.getBeneficiaryNumber()));
			}
		}
		addLedgerDetailMap(rMap, 1, 2, 3, 4, 5, 6, new BigDecimal(count), new BigDecimal(responseGroupNo),
				new BigDecimal(plannedInvestment), new BigDecimal(selfFund), new BigDecimal(gapFund), new BigDecimal(beneficiaryNumber));
		for(LedgerAgricultureDetail a : subList){
			if(a.getProjectSubCategory() == null)
				continue;
			if(getDisplayName(subTypes, a.getProjectSubCategory()).equals("粮食生产")){
				rMap.put(13, a.getScopeNumber());
			}else if(getDisplayName(subTypes, a.getProjectSubCategory()).equals("油料生产")){
				rMap.put(14, a.getScopeNumber());
			}else if(getDisplayName(subTypes, a.getProjectSubCategory()).equals("食用菌")){
				rMap.put(15, a.getScopeNumber());
			}else if(getDisplayName(subTypes, a.getProjectSubCategory()).equals("中药材")){
				rMap.put(16, a.getScopeNumber());
			}else if(getDisplayName(subTypes, a.getProjectSubCategory()).equals("大棚西瓜")){
				rMap.put(17, a.getScopeNumber());
			}else if(getDisplayName(subTypes, a.getProjectSubCategory()).equals("设施蔬菜")){
				rMap.put(18, a.getScopeNumber());
			}else if(getDisplayName(subTypes, a.getProjectSubCategory()).equals("干果生产")){
				rMap.put(19, a.getScopeNumber());
			}else if(getDisplayName(subTypes, a.getProjectSubCategory()).equals("水果生产")){
				rMap.put(20, a.getScopeNumber());
			}else if(getDisplayName(subTypes, a.getProjectSubCategory()).equals("其他")){
				rMap.put(21, a.getScopeNumber());
			}else if(getDisplayName(subTypes, a.getProjectSubCategory()).equals("排灌沟渠")){
				rMap.put(28, a.getQuantities());
			}else if(getDisplayName(subTypes, a.getProjectSubCategory()).equals("作业道")){
				rMap.put(29, a.getQuantities());
			}else if(getDisplayName(subTypes, a.getProjectSubCategory()).equals("堰塘防渗")){
				rMap.put(30, a.getQuantities());
			}else if(getDisplayName(subTypes, a.getProjectSubCategory()).equals("机耕道")){
				rMap.put(31, a.getQuantities());
			}else if(getDisplayName(subTypes, a.getProjectSubCategory()).equals("蓄水池")){
				rMap.put(32, a.getQuantities());
			}else if(getDisplayName(subTypes, a.getProjectSubCategory()).equals("其它")){
				rMap.put(33, a.getQuantities());
			}else if(getDisplayName(subTypes, a.getProjectSubCategory()).equals("电力提灌站")){
				rMap.put(40, new BigDecimal(a.getNum()));
			}else if(getDisplayName(subTypes, a.getProjectSubCategory()).equals("机沉井")){
				rMap.put(41, new BigDecimal(a.getNum()));
			}else if(getDisplayName(subTypes, a.getProjectSubCategory()).equals("农业培训")){
				rMap.put(48, new BigDecimal(a.getTrainNumber()));
				rMap.put(49, new BigDecimal(a.getTrainPeopleNumber()));
			}else if(getDisplayName(subTypes, a.getProjectSubCategory()).equals("农机培训")){
				rMap.put(50, new BigDecimal(a.getTrainNumber()));
				rMap.put(51, new BigDecimal(a.getTrainPeopleNumber()));
			} 
		}
		addLedgerDoneMap(rMap, map, 58, 59, 60, 61);
		return rMap;
	}
	
	private Map<Integer, BigDecimal> getTownDetail(Map<String, Object> map){
		Map<Integer, BigDecimal> rMap = initResultMap(74);
		List<LedgerTownDetail> aList = comprehensiveDao.getAllTownResult(map);
		List<PropertyDict> proTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.TOWN_PROJECT);
		int count = 0,responseGroupNo = 0,beneficiaryNumber = 0;
		float plannedInvestment = 0,selfFund = 0,gapFund = 0;
		for(LedgerTownDetail town : aList){
			count = count + town.getCount();//1
			responseGroupNo = responseGroupNo + town.getResponseGroupNo();//2
			plannedInvestment = plannedInvestment + town.getPlannedInvestment().floatValue();//3
			selfFund = selfFund + town.getSelfFund().floatValue();//4
			gapFund = gapFund + town.getGapFund().floatValue();//5
			beneficiaryNumber = beneficiaryNumber + town.getBeneficiaryNumber();//6
			if(getDisplayName(proTypes, town.getProjectCategory()).equals("城市管理")){
				rMap.put(9, new BigDecimal(town.getArea()));
				rMap.put(10, new BigDecimal(town.getScopeNumber()));
				addLedgerDetailMap(rMap, 7, 8, 11, 12, 13, 14, new BigDecimal(town.getCount()), new BigDecimal(town.getResponseGroupNo()),
						town.getPlannedInvestment(), town.getSelfFund(), town.getGapFund(), new BigDecimal(town.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, town.getProjectCategory()).equals("城市街道")){
				rMap.put(17, new BigDecimal(town.getScopeNumber()));
				rMap.put(18, new BigDecimal(town.getMileage()));
				addLedgerDetailMap(rMap, 15, 16, 19, 20, 21, 22, new BigDecimal(town.getCount()), new BigDecimal(town.getResponseGroupNo()),
						town.getPlannedInvestment(), town.getSelfFund(), town.getGapFund(), new BigDecimal(town.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, town.getProjectCategory()).equals("市政公共设施")){
				rMap.put(25, new BigDecimal(town.getScopeNumber()));
				rMap.put(26, new BigDecimal(town.getArea()));
				rMap.put(27, new BigDecimal(town.getMileage()));
				addLedgerDetailMap(rMap, 23, 24, 28, 29, 30, 31, new BigDecimal(town.getCount()), new BigDecimal(town.getResponseGroupNo()),
						town.getPlannedInvestment(), town.getSelfFund(), town.getGapFund(), new BigDecimal(town.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, town.getProjectCategory()).equals("市政环卫")){
				rMap.put(34, new BigDecimal(town.getScopeNumber()));
				rMap.put(35, new BigDecimal(town.getArea()));
				addLedgerDetailMap(rMap, 32, 33, 36, 37, 38, 39, new BigDecimal(town.getCount()), new BigDecimal(town.getResponseGroupNo()),
						town.getPlannedInvestment(), town.getSelfFund(), town.getGapFund(), new BigDecimal(town.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, town.getProjectCategory()).equals("村镇规划建设管理")){
				rMap.put(42, new BigDecimal(town.getScopeNumber()));
				rMap.put(43, new BigDecimal(town.getArea()));
				rMap.put(44, new BigDecimal(town.getMileage()));
				addLedgerDetailMap(rMap, 40, 41, 45, 46, 47, 48, new BigDecimal(town.getCount()), new BigDecimal(town.getResponseGroupNo()),
						town.getPlannedInvestment(), town.getSelfFund(), town.getGapFund(), new BigDecimal(town.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, town.getProjectCategory()).equals("住房保障")){
				rMap.put(51, new BigDecimal(town.getArea()));
				addLedgerDetailMap(rMap, 49, 50, 52, 53, 54, 55, new BigDecimal(town.getCount()), new BigDecimal(town.getResponseGroupNo()),
						town.getPlannedInvestment(), town.getSelfFund(), town.getGapFund(), new BigDecimal(town.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, town.getProjectCategory()).equals("建筑质量安全")){
				rMap.put(58, new BigDecimal(town.getScopeNumber()));
				rMap.put(59, new BigDecimal(town.getArea()));
				rMap.put(60, new BigDecimal(town.getMileage()));
				addLedgerDetailMap(rMap, 56, 57, 61, 62, 63, 64, new BigDecimal(town.getCount()), new BigDecimal(town.getResponseGroupNo()),
						town.getPlannedInvestment(), town.getSelfFund(), town.getGapFund(), new BigDecimal(town.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, town.getProjectCategory()).equals("其他")){
				addLedgerDetailMap(rMap, 65, 66, 67, 68, 69, 70, new BigDecimal(town.getCount()), new BigDecimal(town.getResponseGroupNo()),
						town.getPlannedInvestment(), town.getSelfFund(), town.getGapFund(), new BigDecimal(town.getBeneficiaryNumber()));
			}
		}
		addLedgerDetailMap(rMap, 1, 2, 3, 4, 5, 6, new BigDecimal(count), new BigDecimal(responseGroupNo),
				new BigDecimal(plannedInvestment), new BigDecimal(selfFund), new BigDecimal(gapFund), new BigDecimal(beneficiaryNumber));
		addLedgerDoneMap(rMap, map, 71, 72, 73, 74);
		return rMap;
	}
	
	private Map<Integer, BigDecimal> getEnvironmentDetail(Map<String, Object> map){
		Map<Integer, BigDecimal> rMap = initResultMap(52);
		List<LedgerEnvironmentDetail> aList = comprehensiveDao.getAllEnvironmentResult(map);
		List<LedgerEnvironmentDetail> subList = comprehensiveDao.getSubEnvironmentResult(map);
		List<PropertyDict> proTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.ENVIRONMENT_PROJECT);
		List<PropertyDict> subTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.ENVIRONMENT_PROJECT_SUB);
		int count = 0,responseGroupNo = 0,beneficiaryNumber = 0;
		float plannedInvestment = 0,selfFund = 0,gapFund = 0;
		for(LedgerEnvironmentDetail e : aList){
			count = count + e.getCount();//1
			responseGroupNo = responseGroupNo + e.getResponseGroupNo();//2
			plannedInvestment = plannedInvestment + e.getPlannedInvestment().floatValue();//3
			selfFund = selfFund + e.getSelfFund().floatValue();//4
			gapFund = gapFund + e.getGapFund().floatValue();//5
			beneficiaryNumber = beneficiaryNumber + e.getBeneficiaryNumber();//6
			if(getDisplayName(proTypes, e.getProjectCategory()).equals("工业企业")){
				addLedgerDetailMap(rMap, 7, 8, 9, 10, 11, 12, new BigDecimal(e.getCount()), new BigDecimal(e.getResponseGroupNo()),
						e.getPlannedInvestment(), e.getSelfFund(), e.getGapFund(), new BigDecimal(e.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, e.getProjectCategory()).equals("农村环保")){
				addLedgerDetailMap(rMap, 18, 19, 20, 21, 22, 23, new BigDecimal(e.getCount()), new BigDecimal(e.getResponseGroupNo()),
						e.getPlannedInvestment(), e.getSelfFund(), e.getGapFund(), new BigDecimal(e.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, e.getProjectCategory()).equals("生活污染源")){
				addLedgerDetailMap(rMap, 30,31, 32, 33, 34, 35, new BigDecimal(e.getCount()), new BigDecimal(e.getResponseGroupNo()),
						e.getPlannedInvestment(), e.getSelfFund(), e.getGapFund(), new BigDecimal(e.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, e.getProjectCategory()).equals("电磁辐射")){
				addLedgerDetailMap(rMap, 40, 41, 42, 43, 44, 45, new BigDecimal(e.getCount()), new BigDecimal(e.getResponseGroupNo()),
						e.getPlannedInvestment(), e.getSelfFund(), e.getGapFund(), new BigDecimal(e.getBeneficiaryNumber()));
			}
		}
		addLedgerDetailMap(rMap, 1, 2, 3, 4, 5, 6, new BigDecimal(count), new BigDecimal(responseGroupNo),
				new BigDecimal(plannedInvestment), new BigDecimal(selfFund), new BigDecimal(gapFund), new BigDecimal(beneficiaryNumber));
		for(LedgerEnvironmentDetail e : subList){
			if(e.getProjectSubCategory() == null)
				continue;
			if(getDisplayName(subTypes, e.getProjectSubCategory()).equals("废水")){
				rMap.put(13, new BigDecimal(e.getCount()));
			}else if(getDisplayName(subTypes, e.getProjectSubCategory()).equals("废气")){
				rMap.put(14, new BigDecimal(e.getCount()));
			}else if(getDisplayName(subTypes, e.getProjectSubCategory()).equals("噪声")){
				rMap.put(15, new BigDecimal(e.getCount()));
			}else if(getDisplayName(subTypes, e.getProjectSubCategory()).equals("固体废弃物")){
				rMap.put(16, new BigDecimal(e.getCount()));
			}else if(getDisplayName(subTypes, e.getProjectSubCategory()).equals("其他工业物")){
				rMap.put(17, new BigDecimal(e.getCount()));
			}else if(getDisplayName(subTypes, e.getProjectSubCategory()).equals("畜禽养殖污染")){
				rMap.put(24, new BigDecimal(e.getCount()));
			}else if(getDisplayName(subTypes, e.getProjectSubCategory()).equals("土壤污染")){
				rMap.put(25, new BigDecimal(e.getCount()));
			}else if(getDisplayName(subTypes, e.getProjectSubCategory()).equals("白色污染")){
				rMap.put(26, new BigDecimal(e.getCount()));
			}else if(getDisplayName(subTypes, e.getProjectSubCategory()).equals("河流污染")){
				rMap.put(27, new BigDecimal(e.getCount()));
			}else if(getDisplayName(subTypes, e.getProjectSubCategory()).equals("水库污染")){
				rMap.put(28, new BigDecimal(e.getCount()));
			}else if(getDisplayName(subTypes, e.getProjectSubCategory()).equals("其他污染")){
				rMap.put(29, new BigDecimal(e.getCount()));
			}else if(getDisplayName(subTypes, e.getProjectSubCategory()).equals("生活污水（含地沟油）")){
				rMap.put(36, new BigDecimal(e.getCount()));
			}else if(getDisplayName(subTypes, e.getProjectSubCategory()).equals("垃圾处理")){
				rMap.put(37, new BigDecimal(e.getCount()));
			}else if(getDisplayName(subTypes, e.getProjectSubCategory()).equals("生活噪声")){
				rMap.put(38, new BigDecimal(e.getCount()));
			}else if(getDisplayName(subTypes, e.getProjectSubCategory()).equals("其他生活污染")){
				rMap.put(39, new BigDecimal(e.getCount()));
			}else if(getDisplayName(subTypes, e.getProjectSubCategory()).equals("电力设施")){
				rMap.put(46, new BigDecimal(e.getCount()));
			}else if(getDisplayName(subTypes, e.getProjectSubCategory()).equals("通信网络")){
				rMap.put(47, new BigDecimal(e.getCount()));
			}else if(getDisplayName(subTypes, e.getProjectSubCategory()).equals("三类射线辐射")){
				rMap.put(48, new BigDecimal(e.getCount()));
			}
		}
		addLedgerDoneMap(rMap, map, 49, 50, 51, 52);
		return rMap;
	}
	
	private Map<Integer, BigDecimal> getLaborDetail(Map<String, Object> map){
		Map<Integer, BigDecimal> rMap = initResultMap(29);
		List<LedgerLaborDetail> aList = comprehensiveDao.getAllLaborResult(map);
		List<LedgerLaborDetail> subList = comprehensiveDao.getSubLaborResult(map);
		List<LedgerLaborDetail> subContentList = comprehensiveDao.getSubCotentLaborResult(map);
		List<PropertyDict> proTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.LABOR_PROJECT);
		List<PropertyDict> subTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.LABOR_PROJECT_SUB);
		List<PropertyDict> subContentTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.LABOR_PROJECT_CONTENT);
		int count = 0,responseGroupNo = 0;
		for(LedgerLaborDetail labor : aList){
			count = count + labor.getCount();
			responseGroupNo = responseGroupNo + labor.getResponseGroupNo();
			if(getDisplayName(proTypes, labor.getProjectCategory()).equals("就业")){
				rMap.put(3, new BigDecimal(labor.getCount()));
				rMap.put(4, new BigDecimal(labor.getResponseGroupNo()));
				rMap.put(5, new BigDecimal(labor.getCount()));
			}else if(getDisplayName(proTypes, labor.getProjectCategory()).equals("社会保障")){
				rMap.put(10, new BigDecimal(labor.getCount()));
				rMap.put(11, new BigDecimal(labor.getResponseGroupNo()));
				rMap.put(12, new BigDecimal(labor.getCount()));
			}else if(getDisplayName(proTypes, labor.getProjectCategory()).equals("农民工工资")){
				rMap.put(19, new BigDecimal(labor.getCount()));
				rMap.put(20, new BigDecimal(labor.getResponseGroupNo()));
				rMap.put(21, new BigDecimal(labor.getCount()));
				rMap.put(22, new BigDecimal(labor.getRelationNumber()));
				rMap.put(23, labor.getMoney());
			}else if(getDisplayName(proTypes, labor.getProjectCategory()).equals("其他")){
				rMap.put(24, new BigDecimal(labor.getCount()));
				rMap.put(25, new BigDecimal(labor.getResponseGroupNo()));
			}
		}
		rMap.put(1, new BigDecimal(count));
		rMap.put(2, new BigDecimal(responseGroupNo));
		for(LedgerLaborDetail labor : subList){
			if(getDisplayName(subTypes, labor.getProjectSubCategory()).equals("求职意愿登记")){
				rMap.put(6, new BigDecimal(labor.getCount()));
			}else if(getDisplayName(subTypes, labor.getProjectSubCategory()).equals("就业登记")){
				rMap.put(7, new BigDecimal(labor.getCount()));
			}else if(getDisplayName(subTypes, labor.getProjectSubCategory()).equals("失业登记")){
				rMap.put(8, new BigDecimal(labor.getCount()));
			}else if(getDisplayName(subTypes, labor.getProjectSubCategory()).equals("就业技能培训")){
				rMap.put(9, new BigDecimal(labor.getCount()));
			}else if(getDisplayName(subTypes, labor.getProjectSubCategory()).equals("养老保险")){
				rMap.put(13, new BigDecimal(labor.getCount()));
			}else if(getDisplayName(subTypes, labor.getProjectSubCategory()).equals("工伤保险")){
				rMap.put(16, new BigDecimal(labor.getCount()));
			}else if(getDisplayName(subTypes, labor.getProjectSubCategory()).equals("生育保险")){
				rMap.put(17, new BigDecimal(labor.getCount()));
			}else if(getDisplayName(subTypes, labor.getProjectSubCategory()).equals("失业保险")){
				rMap.put(18, new BigDecimal(labor.getCount()));
			} 
		}
		for(LedgerLaborDetail labor : subContentList){
			if(getDisplayName(subTypes, labor.getProjectSubCategory()).equals("医疗保险")){
				if(getDisplayName(subContentTypes, labor.getProjectSubContentCategory()).equals("城镇职工医疗保险")){
					rMap.put(14, new BigDecimal(labor.getCount()));
				}else if(getDisplayName(subContentTypes, labor.getProjectSubContentCategory()).equals("城镇居民医疗保险")){
					rMap.put(15, new BigDecimal(labor.getCount()));
				}
			}
		}
		addLedgerDoneMap(rMap, map, 26, 27, 28, 29);
		return rMap;
	}
	
	private Map<Integer, BigDecimal> getMedicalDetail(Map<String, Object> map){
		Map<Integer, BigDecimal> rMap = initResultMap(49);
		List<LedgerMedicalDetail> aList = comprehensiveDao.getAllMedicalResult(map);
		List<PropertyDict> proTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.MEDICAL_PROJECT);
		int count = 0,responseGroupNo = 0,beneficiaryNumber = 0;
		float plannedInvestment = 0,selfFund = 0,gapFund = 0;
		long equipment = 0;
		double buildArea = 0;
		for(LedgerMedicalDetail medical : aList){
			count = count + medical.getCount();//1
			responseGroupNo = responseGroupNo + medical.getResponseGroupNo();//2
			plannedInvestment = plannedInvestment + medical.getPlannedInvestment().floatValue();//3
			selfFund = selfFund + medical.getSelfFund().floatValue();//4
			gapFund = gapFund + medical.getGapFund().floatValue();//5
			beneficiaryNumber = beneficiaryNumber + medical.getBeneficiaryNumber();//6
			equipment = equipment + medical.getEquipment();//7
			buildArea = buildArea + medical.getBuildArea();//8
			if(getDisplayName(proTypes, medical.getProjectCategory()).equals("食品卫生")){
				rMap.put(11, new BigDecimal(medical.getEquipment()));
				addLedgerDetailMap(rMap, 9, 10, 12, 13, 14, 15, new BigDecimal(medical.getCount()), new BigDecimal(medical.getBuildArea()), 
						medical.getPlannedInvestment(), medical.getSelfFund(), medical.getGapFund(), new BigDecimal(medical.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, medical.getProjectCategory()).equals("公共卫生服务")){
				rMap.put(18, new BigDecimal(medical.getEquipment()));
				addLedgerDetailMap(rMap, 16, 17, 19, 20, 21, 22, new BigDecimal(medical.getCount()), new BigDecimal(medical.getBuildArea()), 
						medical.getPlannedInvestment(), medical.getSelfFund(), medical.getGapFund(), new BigDecimal(medical.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, medical.getProjectCategory()).equals("医疗服务")){
				rMap.put(25, new BigDecimal(medical.getEquipment()));
				addLedgerDetailMap(rMap, 23, 24, 26, 27, 28, 29, new BigDecimal(medical.getCount()), new BigDecimal(medical.getBuildArea()), 
						medical.getPlannedInvestment(), medical.getSelfFund(), medical.getGapFund(), new BigDecimal(medical.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, medical.getProjectCategory()).equals("新农合")){
				rMap.put(30, new BigDecimal(medical.getCount()));
				rMap.put(31, new BigDecimal(medical.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, medical.getProjectCategory()).equals("服务能力建设")){
				rMap.put(34, new BigDecimal(medical.getEquipment()));
				addLedgerDetailMap(rMap, 32, 33, 35, 36, 37, 38, new BigDecimal(medical.getCount()), new BigDecimal(medical.getBuildArea()), 
						medical.getPlannedInvestment(), medical.getSelfFund(), medical.getGapFund(), new BigDecimal(medical.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, medical.getProjectCategory()).equals("其他")){
				rMap.put(41, new BigDecimal(medical.getEquipment()));
				addLedgerDetailMap(rMap, 39, 40, 42, 43, 44, 45, new BigDecimal(medical.getCount()), new BigDecimal(medical.getBuildArea()), 
						medical.getPlannedInvestment(), medical.getSelfFund(), medical.getGapFund(), new BigDecimal(medical.getBeneficiaryNumber()));
			}
		}
		addLedgerDetailMap(rMap, 1, 2, 3, 4, 5, 6, new BigDecimal(count), new BigDecimal(responseGroupNo),
				new BigDecimal(plannedInvestment), new BigDecimal(selfFund), new BigDecimal(gapFund), new BigDecimal(beneficiaryNumber));
		rMap.put(7, new BigDecimal(equipment));
		rMap.put(8, new BigDecimal(buildArea));
		addLedgerDoneMap(rMap, map, 46, 47, 48, 49);
		return rMap;
	}
	
	private Map<Integer, BigDecimal> getScienceDetail(Map<String, Object> map){
		Map<Integer, BigDecimal> rMap = initResultMap(67);
		List<LedgerScienceDetail> aList = comprehensiveDao.getAllScienceResult(map);
		List<PropertyDict> proTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.SCIENCE_PROJECT);
		List<PropertyDict> subTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.SCIENCE_PROJECT_SUB);
		int count = 0,beneficiaryNumber = 0;
		float plannedInvestment = 0,selfFund = 0,gapFund = 0;
		for(LedgerScienceDetail science : aList){
			count = count + science.getCount();//1
			plannedInvestment = plannedInvestment + science.getPlannedInvestment().floatValue();//3
			selfFund = selfFund + science.getSelfFund().floatValue();//4
			gapFund = gapFund + science.getGapFund().floatValue();//5
			beneficiaryNumber = beneficiaryNumber + science.getBeneficiaryNumber();//6
			if(getDisplayName(proTypes, science.getProjectCategory()).equals("广播电视")){
				if(science.getProjectsubcategory() != null){
					if(getDisplayName(subTypes, science.getProjectsubcategory()).equals("电视“户户通”")){
						addLedgerDetailMap(rMap, 6, 7, 8, 9, 10, 11, new BigDecimal(science.getCount()), new BigDecimal(science.getScienceScope()), 
								science.getPlannedInvestment(), science.getSelfFund(), science.getGapFund(), new BigDecimal(science.getBeneficiaryNumber()));
					}else if(getDisplayName(subTypes, science.getProjectsubcategory()).equals("广播“村村响”")){
						addLedgerDetailMap(rMap, 12, 13, 14, 15, 16, 17, new BigDecimal(science.getCount()), new BigDecimal(science.getScienceScope()), 
								science.getPlannedInvestment(), science.getSelfFund(), science.getGapFund(), new BigDecimal(science.getBeneficiaryNumber()));
					}else if(getDisplayName(subTypes, science.getProjectsubcategory()).equals("电影“月月放”")){
						addLedgerDetailMap(rMap, 18, 19, 20, 21, 2, 23, new BigDecimal(science.getCount()), new BigDecimal(science.getScienceScope()), 
								science.getPlannedInvestment(), science.getSelfFund(), science.getGapFund(), new BigDecimal(science.getBeneficiaryNumber()));
					}
				}
			}else if(getDisplayName(proTypes, science.getProjectCategory()).equals("旅游")){
				if(science.getProjectsubcategory() != null){
					if(getDisplayName(subTypes, science.getProjectsubcategory()).equals("A级旅游景区（点）管理")){
						addLedgerDetailMap(rMap, 24, 25, 26, 27, 28, 29, new BigDecimal(science.getCount()), new BigDecimal(science.getScienceScope()), 
								science.getPlannedInvestment(), science.getSelfFund(), science.getGapFund(), new BigDecimal(science.getBeneficiaryNumber()));
					}else if(getDisplayName(subTypes, science.getProjectsubcategory()).equals("星级农家乐管理")){
						addLedgerDetailMap(rMap, 30, 31, 32, 33, 34, 35, new BigDecimal(science.getCount()), new BigDecimal(science.getScienceScope()), 
								science.getPlannedInvestment(), science.getSelfFund(), science.getGapFund(), new BigDecimal(science.getBeneficiaryNumber()));
					}
				}
			}else if(getDisplayName(proTypes, science.getProjectCategory()).equals("文化")){
				if(science.getProjectsubcategory() != null){
					if(getDisplayName(subTypes, science.getProjectsubcategory()).equals("乡镇综合文化站")){
						addLedgerDetailMap(rMap, 36, 37, 38, 39, 40, 41, new BigDecimal(science.getCount()), new BigDecimal(science.getScienceScope()), 
								science.getPlannedInvestment(), science.getSelfFund(), science.getGapFund(), new BigDecimal(science.getBeneficiaryNumber()));
					}else if(getDisplayName(subTypes, science.getProjectsubcategory()).equals("村（社区）农家书屋")){
						addLedgerDetailMap(rMap, 0, 42, 43, 44, 45, 46, null, new BigDecimal(science.getScienceScope()), 
								science.getPlannedInvestment(), science.getSelfFund(), science.getGapFund(), new BigDecimal(science.getBeneficiaryNumber()));
					}
				}
			}else if(getDisplayName(proTypes, science.getProjectCategory()).equals("体育")){
				if(science.getProjectsubcategory() != null){
					if(getDisplayName(subTypes, science.getProjectsubcategory()).equals("全民健身路径")){
						addLedgerDetailMap(rMap, 0, 47, 48, 49, 50, 51, null, new BigDecimal(science.getScienceScope()), 
								science.getPlannedInvestment(), science.getSelfFund(), science.getGapFund(), new BigDecimal(science.getBeneficiaryNumber()));
					}
				}
			}else if(getDisplayName(proTypes, science.getProjectCategory()).equals("科技、科协项目")){
				addLedgerDetailMap(rMap, 0, 52, 53, 54, 55, 56, null, new BigDecimal(science.getCount()), 
						science.getPlannedInvestment(), science.getSelfFund(), science.getGapFund(), new BigDecimal(science.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, science.getProjectCategory()).equals("科技、科协宣传")){
				rMap.put(57, new BigDecimal(science.getPublicizeNum()));
				rMap.put(58, new BigDecimal(science.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, science.getProjectCategory()).equals("其它")){
				addLedgerDetailMap(rMap, 59, 60, 61, 62, 63, 0, new BigDecimal(science.getCount()), 
						science.getPlannedInvestment(), science.getSelfFund(), science.getGapFund(), new BigDecimal(science.getBeneficiaryNumber()), null);
			} 
		}
		addLedgerDetailMap(rMap, 1, 2, 3, 4, 5, 0, new BigDecimal(count), new BigDecimal(plannedInvestment), 
				new BigDecimal(selfFund), new BigDecimal(gapFund), new BigDecimal(beneficiaryNumber), null);
		addLedgerDoneMap(rMap, map, 64, 65, 66, 67);
		return rMap;
	}
	
	private Map<Integer, BigDecimal> getEnergyDetail(Map<String, Object> map){
		Map<Integer, BigDecimal> rMap = initResultMap(67);
		List<LedgerEnergyDetail> aList = comprehensiveDao.getAllEnergyResult(map);
		List<LedgerEnergyDetail> subList = comprehensiveDao.getSubEnergyResult(map);
		List<LedgerEnergyDetail> subSecList = comprehensiveDao.getSubSecEnergyResult(map);
		List<PropertyDict> proTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.ENERGY_PROJECT);
		List<PropertyDict> subTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.ENERGY_PROJECT_SUB_TYPE);
		List<PropertyDict> subSecTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.ENERGY_SECURITY_CATEGORY);
		int count = 0,responseGroupNo = 0,beneficiaryNumber = 0;
		float plannedInvestment = 0,selfFund = 0,gapFund = 0;
		for(LedgerEnergyDetail energy : aList){
			count = count + energy.getCount();//1
			responseGroupNo = responseGroupNo + energy.getResponseGroupNo();//2
			plannedInvestment = plannedInvestment + energy.getPlannedInvestment().floatValue();//3
			selfFund = selfFund + energy.getSelfFund().floatValue();//4
			gapFund = gapFund + energy.getGapFund().floatValue();//5
			beneficiaryNumber = beneficiaryNumber + energy.getBeneficiaryNumber();//6
			if(getDisplayName(proTypes, energy.getProjectCategory()).equals("天然气")){
				addLedgerDetailMap(rMap, 7, 8, 14, 15, 16, 0, new BigDecimal(energy.getCount()), new BigDecimal(energy.getResponseGroupNo()), 
						new BigDecimal(energy.getSecurityNum()), energy.getPlannedInvestment(), new BigDecimal(energy.getBeneficiaryNumber()), null);
			}else if(getDisplayName(proTypes, energy.getProjectCategory()).equals("石油液化气")){
				addLedgerDetailMap(rMap, 17, 18, 24, 25, 26, 0, new BigDecimal(energy.getCount()), new BigDecimal(energy.getResponseGroupNo()), 
						new BigDecimal(energy.getSecurityNum()), energy.getPlannedInvestment(), new BigDecimal(energy.getBeneficiaryNumber()), null);
			}else if(getDisplayName(proTypes, energy.getProjectCategory()).equals("汽柴油")){
				addLedgerDetailMap(rMap, 27, 28, 34, 35, 0, 0, new BigDecimal(energy.getCount()), new BigDecimal(energy.getResponseGroupNo()), 
						energy.getPlannedInvestment(), new BigDecimal(energy.getBeneficiaryNumber()), null, null);
			}else if(getDisplayName(proTypes, energy.getProjectCategory()).equals("电力")){
				addLedgerDetailMap(rMap, 36, 37, 45, 46, 47, 48, new BigDecimal(energy.getCount()), new BigDecimal(energy.getResponseGroupNo()), 
						energy.getPlannedInvestment(), energy.getSelfFund(), energy.getGapFund(), new BigDecimal(energy.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, energy.getProjectCategory()).equals("沼气池")){
				addLedgerDetailMap(rMap, 49, 50, 54, 55, 56, 57, new BigDecimal(energy.getCount()), new BigDecimal(energy.getResponseGroupNo()), 
						energy.getPlannedInvestment(), energy.getSelfFund(), energy.getGapFund(), new BigDecimal(energy.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, energy.getProjectCategory()).equals("其他")){
				addLedgerDetailMap(rMap, 58, 59, 60, 61, 62, 63, new BigDecimal(energy.getCount()), new BigDecimal(energy.getResponseGroupNo()), 
						energy.getPlannedInvestment(), energy.getSelfFund(), energy.getGapFund(), new BigDecimal(energy.getBeneficiaryNumber()));
			}
		}
		addLedgerDetailMap(rMap, 1, 2, 3, 4, 5, 6, new BigDecimal(count), new BigDecimal(responseGroupNo),
				new BigDecimal(plannedInvestment), new BigDecimal(selfFund), new BigDecimal(gapFund), new BigDecimal(beneficiaryNumber));
		for(LedgerEnergyDetail energy : subList){
			if(getDisplayName(proTypes, energy.getProjectCategory()).equals("天然气")){
				if(getDisplayName(subTypes, energy.getProjectsubcategory()).equals("管道建设")){
					rMap.put(9, new BigDecimal(energy.getSecurityNum()));
					rMap.put(10, new BigDecimal(energy.getMileage()));
				}else if(getDisplayName(subTypes, energy.getProjectsubcategory()).equals("管道养护")){
					rMap.put(11, new BigDecimal(energy.getSecurityNum()));
					rMap.put(12, new BigDecimal(energy.getMileage()));
				}else if(getDisplayName(subTypes, energy.getProjectsubcategory()).equals("户表工程")){
					rMap.put(13, new BigDecimal(energy.getCount()));
				}
			}else if(getDisplayName(proTypes, energy.getProjectCategory()).equals("石油液化气	")){
				if(getDisplayName(subTypes, energy.getProjectsubcategory()).equals("液化管道建设")){
					rMap.put(19, new BigDecimal(energy.getSecurityNum()));
					rMap.put(20, new BigDecimal(energy.getMileage()));
				}else if(getDisplayName(subTypes, energy.getProjectsubcategory()).equals("液化管道养护")){
					rMap.put(21, new BigDecimal(energy.getSecurityNum()));
					rMap.put(22, new BigDecimal(energy.getMileage()));
				}else if(getDisplayName(subTypes, energy.getProjectsubcategory()).equals("液化户表工程")){
					rMap.put(23, new BigDecimal(energy.getCount()));
				}
			}else if(getDisplayName(proTypes, energy.getProjectCategory()).equals("电力")){
				if(getDisplayName(subTypes, energy.getProjectsubcategory()).equals("电网设施")){
					rMap.put(38, new BigDecimal(energy.getCount()));
					rMap.put(39, new BigDecimal(energy.getMileage()));
					rMap.put(40, new BigDecimal(energy.getCount()));
				}else if(getDisplayName(subTypes, energy.getProjectsubcategory()).equals("电网维护")){
					rMap.put(41, new BigDecimal(energy.getCount()));
					rMap.put(42, new BigDecimal(energy.getMileage()));
					rMap.put(43, new BigDecimal(energy.getCount()));
				}else if(getDisplayName(subTypes, energy.getProjectsubcategory()).equals("电力户表工程")){
					rMap.put(44, new BigDecimal(energy.getCount()));
				}
			}else if(getDisplayName(proTypes, energy.getProjectCategory()).equals("沼气池")){
				if(getDisplayName(subTypes, energy.getProjectsubcategory()).equals("沼气池建设")){
					rMap.put(51, energy.getEnergyNumber());
					rMap.put(52, new BigDecimal(energy.getCapacity()));
				}else if(getDisplayName(subTypes, energy.getProjectsubcategory()).equals("沼气池安全设施建设")){
					rMap.put(53, new BigDecimal(energy.getCount()));
				}
			}
		}
		
		for(LedgerEnergyDetail energy : subSecList){
			if(getDisplayName(proTypes, energy.getProjectCategory()).equals("汽柴油")){
				if(energy.getSecurityCategory() == null)
					continue;
				if(getDisplayName(subSecTypes, energy.getSecurityCategory()).equals("防护栏")){
					rMap.put(29, new BigDecimal(energy.getCount()));
				}else if(getDisplayName(subSecTypes, energy.getSecurityCategory()).equals("防撞墩")){
					rMap.put(30, new BigDecimal(energy.getCount()));
				}else if(getDisplayName(subSecTypes, energy.getSecurityCategory()).equals("减速带")){
					rMap.put(31, new BigDecimal(energy.getCount()));
				}else if(getDisplayName(subSecTypes, energy.getSecurityCategory()).equals("防火设施")){
					rMap.put(33, new BigDecimal(energy.getCount()));
				}else if(getDisplayName(subSecTypes, energy.getSecurityCategory()).equals("安全标志标牌")){
					rMap.put(32, new BigDecimal(energy.getCount()));
				}
			}
		}
		addLedgerDoneMap(rMap, map, 64, 65, 66, 67);
		return rMap;
	}
	
	private Map<Integer, BigDecimal> getEducationDetail(Map<String, Object> map){
		Map<Integer, BigDecimal> rMap = initResultMap(93);
		List<LedgerEducationDetail> aList = comprehensiveDao.getAllEductionResult(map);
		List<LedgerEducationDetail> buildList = comprehensiveDao.getBuildEductionResult(map);
		List<LedgerEducationDetail> buildAreaist = comprehensiveDao.getBuildEductionAreaResult(map);
		List<LedgerEducationDetail> subBuildList = comprehensiveDao.getSubBuildEducationResult(map);
		List<LedgerEducationDetail> disList = comprehensiveDao.getDisEducationResult(map);
		List<LedgerEducationDetail> roadList = comprehensiveDao.getRoadEducationResult(map);
		List<PropertyDict> proTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.EDUCATION_PROJECT);
		List<PropertyDict> buildTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.EDUCATION_BUILD_TYPE);
		List<PropertyDict> subTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.EDUCATION_PROJECT_SUB);
		List<PropertyDict> disTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.EDUCATION_DISTANCE_TYPE);
		List<PropertyDict> roadTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.EDUCATION_ROAD_CONDITION_TYPE);
		int count = 0,responseGroupNo = 0,beneficiaryNumber = 0;
		float plannedInvestment = 0,selfFund = 0,gapFund = 0;
		double buildArea = 0.0;
		for(LedgerEducationDetail education : aList){
			count = count + education.getCount();//1
			responseGroupNo = responseGroupNo + education.getResponseGroupNo();//2
			plannedInvestment = plannedInvestment + education.getPlannedInvestment().floatValue();//3
			selfFund = selfFund + education.getSelfFund().floatValue();//4
			gapFund = gapFund + education.getGapFund().floatValue();//5
			beneficiaryNumber = beneficiaryNumber + education.getBeneficiaryNumber();//6
			buildArea = buildArea + education.getBuildArea();
		}
		rMap.put(3, new BigDecimal(buildArea));
		addLedgerDetailMap(rMap, 1, 2, 4, 5, 6, 7, new BigDecimal(count), new BigDecimal(responseGroupNo),
				new BigDecimal(plannedInvestment), new BigDecimal(selfFund), new BigDecimal(gapFund), new BigDecimal(beneficiaryNumber));
		count = 0;responseGroupNo = 0;beneficiaryNumber = 0;
		plannedInvestment = 0;selfFund = 0;gapFund = 0;
		int count1 = 0,responseGroupNo1 = 0,beneficiaryNumber1 = 0;
		float plannedInvestment1 = 0,selfFund1 = 0,gapFund1 = 0;
		for(LedgerEducationDetail education : buildList){
			if(getDisplayName(proTypes, education.getProjectCategory()).equals("工程建设")){
				count = count + education.getCount();
				responseGroupNo = responseGroupNo + education.getResponseGroupNo();//2
				plannedInvestment = plannedInvestment + education.getPlannedInvestment().floatValue();//3
				selfFund = selfFund + education.getSelfFund().floatValue();//4
				gapFund = gapFund + education.getGapFund().floatValue();//5
				beneficiaryNumber = beneficiaryNumber + education.getBeneficiaryNumber();//6
				if(getDisplayName(subTypes, education.getProjectsubcategory()).equals("幼儿园建设")){
					addLedgerDetailMap(rMap, 0, 0, 24, 25, 26, 27, null, null, education.getPlannedInvestment(), education.getSelfFund(), 
							education.getGapFund(), new BigDecimal(education.getBeneficiaryNumber()));
				}else if(getDisplayName(subTypes, education.getProjectsubcategory()).equals("农村薄弱学校改造")){
					addLedgerDetailMap(rMap, 0, 0, 32, 33, 34, 35, null, null, education.getPlannedInvestment(), education.getSelfFund(), 
							education.getGapFund(), new BigDecimal(education.getBeneficiaryNumber()));
				}else if(getDisplayName(subTypes, education.getProjectsubcategory()).equals("危房改造")){
					addLedgerDetailMap(rMap, 0, 0, 40, 41, 42, 43, null, null, education.getPlannedInvestment(), education.getSelfFund(), 
							education.getGapFund(), new BigDecimal(education.getBeneficiaryNumber()));
				}else if(getDisplayName(subTypes, education.getProjectsubcategory()).equals("教师周转房建设")){
					addLedgerDetailMap(rMap, 0, 0, 48, 49, 50, 51, null, null, education.getPlannedInvestment(), education.getSelfFund(), 
							education.getGapFund(), new BigDecimal(education.getBeneficiaryNumber()));
				}else if(getDisplayName(subTypes, education.getProjectsubcategory()).equals("维修维护")){
					rMap.put(52, new BigDecimal(education.getBuildArea()));
					addLedgerDetailMap(rMap, 0, 0, 53, 54, 55, 56, null, null, education.getPlannedInvestment(), education.getSelfFund(), 
							education.getGapFund(), new BigDecimal(education.getBeneficiaryNumber()));
				}
			}else if(getDisplayName(proTypes, education.getProjectCategory()).equals("就学")){
				count1 = count1 + education.getCount();
				responseGroupNo1 = responseGroupNo1 + education.getResponseGroupNo();
				plannedInvestment1 = plannedInvestment1 + education.getPlannedInvestment().floatValue();
				selfFund1 = selfFund1 + education.getSelfFund().floatValue();
				gapFund1 = gapFund1 + education.getGapFund().floatValue();
//				beneficiaryNumber1 = beneficiaryNumber1 + education.getBeneficiaryNumber();
				if(getDisplayName(subTypes, education.getProjectsubcategory()).equals("贫困大学新生资助")){
					rMap.put(63, new BigDecimal(education.getCount()));
				}else if(getDisplayName(subTypes, education.getProjectsubcategory()).equals("两免一补")){
					rMap.put(64, new BigDecimal(education.getCount()));
				}else if(getDisplayName(subTypes, education.getProjectsubcategory()).equals("高中及中职学生学杂费及生活困难补助")){
					rMap.put(65, new BigDecimal(education.getCount()));
				}else if(getDisplayName(subTypes, education.getProjectsubcategory()).equals("进城农民工子女就读")){
					rMap.put(66, new BigDecimal(education.getCount()));
				}else if(getDisplayName(subTypes, education.getProjectsubcategory()).equals("上学路途难")){
					addLedgerDetailMap(rMap, 0, 0, 76, 77, 78, 79, null, null, education.getPlannedInvestment(), education.getSelfFund(), 
							education.getGapFund(), new BigDecimal(education.getBeneficiaryNumber()));
				}
			}else if(getDisplayName(proTypes, education.getProjectCategory()).equals("其他")){
				addLedgerDetailMap(rMap, 0, 0, 86, 87, 88, 89, null, null, education.getPlannedInvestment(), education.getSelfFund(), 
						education.getGapFund(), new BigDecimal(education.getBeneficiaryNumber()));
			}
		}
		addLedgerDetailMap(rMap, 8, 9, 14, 15, 16, 17, new BigDecimal(count), new BigDecimal(responseGroupNo),
				new BigDecimal(plannedInvestment), new BigDecimal(selfFund), new BigDecimal(gapFund), new BigDecimal(beneficiaryNumber));
		rMap.put(19, new BigDecimal(beneficiaryNumber));
		addLedgerDetailMap(rMap, 57, 58, 59, 60, 62, 62, new BigDecimal(count1), new BigDecimal(responseGroupNo1),
				new BigDecimal(plannedInvestment1), new BigDecimal(selfFund1), new BigDecimal(gapFund1), new BigDecimal(count1));
		
		double xArea = 0,gArea = 0,kArea = 0,wArea = 0;
		for(LedgerEducationDetail education : subBuildList){
			if(getDisplayName(proTypes, education.getProjectCategory()).equals("工程建设")){
				if(education.getProjectsubcategory() == null || education.getBuildType() == null)
					continue;
				if(getDisplayName(subTypes, education.getProjectsubcategory()).equals("幼儿园建设")){
					if(getDisplayName(buildTypes, education.getBuildType()).equals("新建")){
						xArea = xArea + education.getBuildArea();
						rMap.put(20, new BigDecimal(education.getBuildArea()));
					}else if(getDisplayName(buildTypes, education.getBuildType()).equals("改建")){
						gArea = gArea + education.getBuildArea();
						rMap.put(21, new BigDecimal(education.getBuildArea()));
					}else if(getDisplayName(buildTypes, education.getBuildType()).equals("扩建")){
						kArea = kArea + education.getBuildArea();
						rMap.put(22, new BigDecimal(education.getBuildArea()));
					}else if(getDisplayName(buildTypes, education.getBuildType()).equals("维修")){
						wArea = wArea + education.getBuildArea();
						rMap.put(23, new BigDecimal(education.getBuildArea()));
					}
				}else if(getDisplayName(subTypes, education.getProjectsubcategory()).equals("农村薄弱学校改造")){
					if(getDisplayName(buildTypes, education.getBuildType()).equals("新建")){
						xArea = xArea + education.getBuildArea();
						rMap.put(28, new BigDecimal(education.getBuildArea()));
					}else if(getDisplayName(buildTypes, education.getBuildType()).equals("改建")){
						gArea = gArea + education.getBuildArea();
						rMap.put(29, new BigDecimal(education.getBuildArea()));
					}else if(getDisplayName(buildTypes, education.getBuildType()).equals("扩建")){
						kArea = kArea + education.getBuildArea();
						rMap.put(30, new BigDecimal(education.getBuildArea()));
					}else if(getDisplayName(buildTypes, education.getBuildType()).equals("维修")){
						wArea = wArea + education.getBuildArea();
						rMap.put(31, new BigDecimal(education.getBuildArea()));
					}
				}else if(getDisplayName(subTypes, education.getProjectsubcategory()).equals("危房改造")){
					if(getDisplayName(buildTypes, education.getBuildType()).equals("新建")){
						xArea = xArea + education.getBuildArea();
						rMap.put(36, new BigDecimal(education.getBuildArea()));
					}else if(getDisplayName(buildTypes, education.getBuildType()).equals("改建")){
						gArea = gArea + education.getBuildArea();
						rMap.put(37, new BigDecimal(education.getBuildArea()));
					}else if(getDisplayName(buildTypes, education.getBuildType()).equals("扩建")){
						kArea = kArea + education.getBuildArea();
						rMap.put(38, new BigDecimal(education.getBuildArea()));
					}else if(getDisplayName(buildTypes, education.getBuildType()).equals("维修")){
						wArea = wArea + education.getBuildArea();
						rMap.put(39, new BigDecimal(education.getBuildArea()));
					}
				}else if(getDisplayName(subTypes, education.getProjectsubcategory()).equals("教师周转房建设")){
					if(getDisplayName(buildTypes, education.getBuildType()).equals("新建")){
						xArea = xArea + education.getBuildArea();
						rMap.put(44, new BigDecimal(education.getBuildArea()));
					}else if(getDisplayName(buildTypes, education.getBuildType()).equals("改建")){
						gArea = gArea + education.getBuildArea();
						rMap.put(45, new BigDecimal(education.getBuildArea()));
					}else if(getDisplayName(buildTypes, education.getBuildType()).equals("扩建")){
						kArea = kArea + education.getBuildArea();
						rMap.put(46, new BigDecimal(education.getBuildArea()));
					}else if(getDisplayName(buildTypes, education.getBuildType()).equals("维修")){
						wArea = wArea + education.getBuildArea();
						rMap.put(47, new BigDecimal(education.getBuildArea()));
					}
				}
				
			}
		}
		rMap.put(10, new BigDecimal(xArea));rMap.put(11, new BigDecimal(gArea));rMap.put(12, new BigDecimal(kArea));rMap.put(13, new BigDecimal(wArea));
		for(LedgerEducationDetail education : buildAreaist){
			if(getDisplayName(proTypes, education.getProjectCategory()).equals("其他")){
				if(education.getBuildType() == null)
					continue;
				if(getDisplayName(buildTypes, education.getBuildType()).equals("新建")){
					rMap.put(82, new BigDecimal(education.getBuildArea()));
				}else if(getDisplayName(buildTypes, education.getBuildType()).equals("改建")){
					rMap.put(83, new BigDecimal(education.getBuildArea()));
				}else if(getDisplayName(buildTypes, education.getBuildType()).equals("扩建")){
					rMap.put(84, new BigDecimal(education.getBuildArea()));
				}else if(getDisplayName(buildTypes, education.getBuildType()).equals("维修")){
					rMap.put(85, new BigDecimal(education.getBuildArea()));
				}
			}
		}
		for(LedgerEducationDetail education : disList){
			if(getDisplayName(proTypes, education.getProjectCategory()).equals("就学")){
				if(education.getProjectsubcategory() == null || education.getDistanceCategory() == null)
					continue;
				if(getDisplayName(subTypes, education.getProjectsubcategory()).equals("上学路途难")){
					if(getDisplayName(disTypes, education.getDistanceCategory()).equals("三公里")){
						rMap.put(67, new BigDecimal(education.getCount()));
					}else if(getDisplayName(disTypes, education.getDistanceCategory()).equals("四公里")){
						rMap.put(68, new BigDecimal(education.getCount()));
					}else if(getDisplayName(disTypes, education.getDistanceCategory()).equals("五公里")){
						rMap.put(69, new BigDecimal(education.getCount()));
					}else if(getDisplayName(disTypes, education.getDistanceCategory()).equals("六公里")){
						rMap.put(70, new BigDecimal(education.getCount()));
					}else if(getDisplayName(disTypes, education.getDistanceCategory()).equals("七公里及以上")){
						rMap.put(71, new BigDecimal(education.getCount()));
					}
				}
			}
		}
		for(LedgerEducationDetail education : roadList){
			if(getDisplayName(proTypes, education.getProjectCategory()).equals("就学")){
				if(education.getProjectsubcategory() == null || education.getRoadConditionCategory() == null)
					continue;
				if(getDisplayName(subTypes, education.getProjectsubcategory()).equals("上学路途难")){
					if(getDisplayName(roadTypes, education.getRoadConditionCategory()).equals("山路")){
						rMap.put(72, new BigDecimal(education.getCount()));
					}else if(getDisplayName(roadTypes, education.getRoadConditionCategory()).equals("渡河")){
						rMap.put(73, new BigDecimal(education.getCount()));
					}else if(getDisplayName(roadTypes, education.getRoadConditionCategory()).equals("过水库")){
						rMap.put(74, new BigDecimal(education.getCount()));
					}else if(getDisplayName(roadTypes, education.getRoadConditionCategory()).equals("乘车")){
						rMap.put(75, new BigDecimal(education.getCount()));
					}
				}
			}
		}
		
		addLedgerDoneMap(rMap, map, 90, 91, 92, 93);
		return rMap;
	}
	
	/**
	 *处理办结情况统计 
	 */
	private void addLedgerDoneMap(Map<Integer, BigDecimal> rMap, Map<String, Object> map, int y1, int y2, int y3, int y4){
		List<LedgerPeopleSubstanceDesc> sDescs = comprehensiveDao.countSubstanceDoneByDesc(map);
		for(LedgerPeopleSubstanceDesc sDesc : sDescs){
			if(sDesc.getDealdescription().trim().equals(CompleteType.REALCOMPLETE_NAME)){//问题终止、、REALCOMPLETE_NAME实质性办结
				rMap.put(y1, new BigDecimal(sDesc.getCount()));//实质性办结--实质办结
			}else{
				rMap.put(y2, new BigDecimal(sDesc.getCount()));//实质性办结--问题终止
			}
		}
		List<String> cList = new ArrayList<String>();
		cList.add(map.get("orgCode").toString());
		map.put("orgCode", cList);
		rMap.put(y3, new BigDecimal(comprehensiveDao.cuntfindJurisdictionsDone(map)));//程序性办结
		map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		rMap.put(y4, new BigDecimal(comprehensiveDao.countfindJurisdictionsNeedDo(map)));//待办--办理中
	}
	
	private Map<Integer, BigDecimal> getTrafficDetail(Map<String, Object> map){
		List<LedgerTrafficDetail> aList = comprehensiveDao.getAllTrafficResult(map);
		List<LedgerTrafficDetail> buildList = comprehensiveDao.getBuildTrafficResult(map);
		List<LedgerTrafficDetail> subList = comprehensiveDao.getSubCategoryTrafficResult(map);
		List<LedgerTrafficDetail> passSubList = comprehensiveDao.getPassSubCategoryTrafficResult(map);
		Map<Integer, BigDecimal> rMap = initResultMap(82);
		List<PropertyDict> proTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.TRAFFIC_PROJECT);
		List<PropertyDict> buildTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.TRAFFIC_BUILD_TYPE);
		List<PropertyDict> subTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.TRAFFIC_PASSTYPE);
		List<PropertyDict> passSubTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.TRAFFIC_PASSENGER_MANAGE);
		int count = 0,responseGroupNo = 0,beneficiaryNumber = 0;
		float plannedInvestment = 0,selfFund = 0,gapFund = 0;
		for(LedgerTrafficDetail traffic : aList){
			count = count + traffic.getCount();//1
			responseGroupNo = responseGroupNo + traffic.getResponseGroupNo();//2
			plannedInvestment = plannedInvestment + traffic.getPlannedInvestment().floatValue();//3
			selfFund = selfFund + traffic.getSelfFund().floatValue();//4
			gapFund = gapFund + traffic.getGapFund().floatValue();//5
			beneficiaryNumber = beneficiaryNumber + traffic.getBeneficiaryNumber();//6
			if(getDisplayName(proTypes, traffic.getProjectCategory()).equals("道路建设")){
				rMap.put(17, new BigDecimal(traffic.getWide()));
				addLedgerDetailMap(rMap, 7, 8, 18, 19, 20, 21, new BigDecimal(traffic.getCount()), new BigDecimal(traffic.getResponseGroupNo()), 
						traffic.getPlannedInvestment(), traffic.getSelfFund(), traffic.getGapFund(), new BigDecimal(traffic.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, traffic.getProjectCategory()).equals("道路维护")){
				rMap.put(24, new BigDecimal(traffic.getMileage()));
				rMap.put(25, new BigDecimal(traffic.getWide()));
				addLedgerDetailMap(rMap, 22, 23, 26, 27, 28, 29, new BigDecimal(traffic.getCount()), new BigDecimal(traffic.getResponseGroupNo()),
						traffic.getPlannedInvestment(), traffic.getSelfFund(), traffic.getGapFund(), new BigDecimal(traffic.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, traffic.getProjectCategory()).equals("桥梁建设")){
				rMap.put(40, new BigDecimal(traffic.getRodeWide()));
				addLedgerDetailMap(rMap, 30, 31, 41, 42, 43, 44, new BigDecimal(traffic.getCount()), new BigDecimal(traffic.getResponseGroupNo()),
						traffic.getPlannedInvestment(), traffic.getSelfFund(), traffic.getGapFund(), new BigDecimal(traffic.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, traffic.getProjectCategory()).equals("桥梁维护")){
				rMap.put(47, new BigDecimal(traffic.getCount()));
				rMap.put(48, new BigDecimal(traffic.getRodeLength()));
				rMap.put(49, new BigDecimal(traffic.getRodeWide()));
				addLedgerDetailMap(rMap, 45, 46, 50, 51, 52, 53, new BigDecimal(traffic.getCount()), new BigDecimal(traffic.getResponseGroupNo()),
						traffic.getPlannedInvestment(), traffic.getSelfFund(), traffic.getGapFund(), new BigDecimal(traffic.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, traffic.getProjectCategory()).equals("安保工程")){
				rMap.put(56, new BigDecimal(traffic.getCount()));
				rMap.put(57, new BigDecimal(traffic.getMileage()));
				addLedgerDetailMap(rMap, 54, 55, 58, 59, 60, 61, new BigDecimal(traffic.getCount()), new BigDecimal(traffic.getResponseGroupNo()),
						traffic.getPlannedInvestment(), traffic.getSelfFund(), traffic.getGapFund(), new BigDecimal(traffic.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, traffic.getProjectCategory()).equals("客运")){
				addLedgerDetailMap(rMap, 62, 63, 69, 70, 71, 72, new BigDecimal(traffic.getCount()), new BigDecimal(traffic.getResponseGroupNo()),
						traffic.getPlannedInvestment(), traffic.getSelfFund(), traffic.getGapFund(), new BigDecimal(traffic.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, traffic.getProjectCategory()).equals("其他")){
				addLedgerDetailMap(rMap, 73, 74, 75, 76, 77, 78, new BigDecimal(traffic.getCount()), new BigDecimal(traffic.getResponseGroupNo()),
						traffic.getPlannedInvestment(), traffic.getSelfFund(), traffic.getGapFund(), new BigDecimal(traffic.getBeneficiaryNumber()));
			}
		}
		addLedgerDetailMap(rMap, 1, 2, 3, 4, 5, 6, new BigDecimal(count), new BigDecimal(responseGroupNo),
				new BigDecimal(plannedInvestment), new BigDecimal(selfFund), new BigDecimal(gapFund), new BigDecimal(beneficiaryNumber));
		
		for(LedgerTrafficDetail traffic : buildList){
			if(traffic.getBuildType() == null)
				continue;
			if(getDisplayName(proTypes, traffic.getProjectCategory()).equals("道路建设")){
				if(getDisplayName(buildTypes, traffic.getBuildType()).equals("新建")){
					rMap.put(9, new BigDecimal(traffic.getCount()));
					rMap.put(13, new BigDecimal(traffic.getMileage()));
				}else if(getDisplayName(buildTypes, traffic.getBuildType()).equals("改建")){
					rMap.put(10, new BigDecimal(traffic.getCount()));
					rMap.put(14, new BigDecimal(traffic.getMileage()));
				}else if(getDisplayName(buildTypes, traffic.getBuildType()).equals("扩建")){
					rMap.put(11, new BigDecimal(traffic.getCount()));
					rMap.put(15, new BigDecimal(traffic.getMileage()));
				}else if(getDisplayName(buildTypes, traffic.getBuildType()).equals("维修")){
					rMap.put(12, new BigDecimal(traffic.getCount()));
					rMap.put(16, new BigDecimal(traffic.getMileage()));
				}
			}else if(getDisplayName(proTypes, traffic.getProjectCategory()).equals("桥梁建设")){
				if(getDisplayName(buildTypes, traffic.getBuildType()).equals("新建")){
					rMap.put(32, new BigDecimal(traffic.getCount()));
					rMap.put(36, new BigDecimal(traffic.getRodeLength()));
				}else if(getDisplayName(buildTypes, traffic.getBuildType()).equals("改建")){
					rMap.put(33, new BigDecimal(traffic.getCount()));
					rMap.put(37, new BigDecimal(traffic.getRodeLength()));
				}else if(getDisplayName(buildTypes, traffic.getBuildType()).equals("扩建")){
					rMap.put(34, new BigDecimal(traffic.getCount()));
					rMap.put(38, new BigDecimal(traffic.getRodeLength()));
				}else if(getDisplayName(buildTypes, traffic.getBuildType()).equals("维修")){
					rMap.put(35, new BigDecimal(traffic.getCount()));
					rMap.put(39, new BigDecimal(traffic.getRodeLength()));
				}
			}
		}
		
		for(LedgerTrafficDetail traffic : subList){
			if(traffic.getProjectSubCategory() == null)
				continue;
			if(getDisplayName(proTypes, traffic.getProjectCategory()).equals("客运")){
				if(getDisplayName(subTypes, traffic.getProjectSubCategory()).equals("班线客运")){
					rMap.put(64, new BigDecimal(traffic.getCount()));
				}else if(getDisplayName(subTypes, traffic.getProjectSubCategory()).equals("城市公共交通")){
					rMap.put(65, new BigDecimal(traffic.getCount()));
				}else if(getDisplayName(subTypes, traffic.getProjectSubCategory()).equals("客运站管理")){
					rMap.put(66, new BigDecimal(traffic.getCount()));
				}
			}
		}
		
		for(LedgerTrafficDetail traffic : passSubList){
			if(traffic.getProjectSubCategory() == null)
				continue;
			if(traffic.getPassengerCategory() == null){
				continue;
			}
			if(getDisplayName(passSubTypes, traffic.getPassengerCategory()).equals("招呼站")){
				rMap.put(68, new BigDecimal(traffic.getCount()));
			}else if(getDisplayName(passSubTypes, traffic.getPassengerCategory()).equals("客运站")){
				rMap.put(67, new BigDecimal(traffic.getCount()));
			}
		}
		
		addLedgerDoneMap(rMap, map, 79, 80, 81, 82);
		return rMap;
	}
	
	private Map<Integer, BigDecimal> getWaterDetail(Map<String, Object> map){
		Map<Integer, BigDecimal> rMap = initResultMap(82);
		List<LedgerWaterDetail> allList = comprehensiveDao.getAllWaterResult(map);//总体数据
		List<LedgerWaterDetail> buildList = comprehensiveDao.getBuildWaterResult(map);//建设性质数据
		List<PropertyDict> proTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.LEDGER_WATEER_PROJECT);
		List<PropertyDict> buildTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.LEDGER_WATEER_BUILD_TYPE);
		int count = 0,responseGroupNo = 0,beneficiaryNumber = 0;
		float plannedInvestment = 0,selfFund = 0,gapFund = 0;
		for(LedgerWaterDetail water : allList){
			count = count + water.getCount();//1
			responseGroupNo = responseGroupNo + water.getResponseGroupNo();//2
			plannedInvestment = plannedInvestment + water.getPlannedInvestment().floatValue();//3
			selfFund = selfFund + water.getSelfFund().floatValue();//4
			gapFund = gapFund + water.getGapFund().floatValue();//5
			beneficiaryNumber = beneficiaryNumber + water.getBeneficiaryNumber();//6
			if(getDisplayName(proTypes, water.getProjectCategory()).equals("山坪塘")){
				rMap.put(13, new BigDecimal(water.getImpoundage()));
				addLedgerDetailMap(rMap, 7, 8, 14, 15, 16, 17, new BigDecimal(water.getCount()), new BigDecimal(water.getResponseGroupNo()),
						water.getPlannedInvestment(), water.getSelfFund(), water.getGapFund(), new BigDecimal(water.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, water.getProjectCategory()).equals("石河堰")){
				rMap.put(24, new BigDecimal(water.getImpoundage()));
				addLedgerDetailMap(rMap, 18, 19, 25, 26, 27, 28, new BigDecimal(water.getCount()), new BigDecimal(water.getResponseGroupNo()),
						water.getPlannedInvestment(), water.getSelfFund(), water.getGapFund(), new BigDecimal(water.getBeneficiaryNumber()));
			}else if(getDisplayName(proTypes, water.getProjectCategory()).equals("蓄水池")){
				rMap.put(35, new BigDecimal(water.getImpoundage()));
				addLedgerDetailMap(rMap, 29, 30, 36, 37, 38, 39, new BigDecimal(water.getCount()), new BigDecimal(water.getResponseGroupNo()),
						water.getPlannedInvestment(), water.getSelfFund(), water.getGapFund(), new BigDecimal(water.getBeneficiaryNumber()));
			} else if(getDisplayName(proTypes, water.getProjectCategory()).equals("饮水工程")){
				rMap.put(46, new BigDecimal(water.getScatter()));
				addLedgerDetailMap(rMap, 40, 41, 47, 48, 49, 50, new BigDecimal(water.getCount()), new BigDecimal(water.getResponseGroupNo()),
						water.getPlannedInvestment(), water.getSelfFund(), water.getGapFund(), new BigDecimal(water.getBeneficiaryNumber()));
			} else if(getDisplayName(proTypes, water.getProjectCategory()).equals("渠道")){
				addLedgerDetailMap(rMap, 51, 52, 61, 62, 63, 64, new BigDecimal(water.getCount()), new BigDecimal(water.getResponseGroupNo()),
						water.getPlannedInvestment(), water.getSelfFund(), water.getGapFund(), new BigDecimal(water.getBeneficiaryNumber()));
			} else if(getDisplayName(proTypes, water.getProjectCategory()).equals("中小河流治理")){
				rMap.put(67, new BigDecimal(water.getCount()));
				rMap.put(68, new BigDecimal(water.getMileage()));
				addLedgerDetailMap(rMap, 65, 66, 69, 70, 71, 72, new BigDecimal(water.getCount()), new BigDecimal(water.getResponseGroupNo()),
						water.getPlannedInvestment(), water.getSelfFund(), water.getGapFund(), new BigDecimal(water.getBeneficiaryNumber()));
			} else if(getDisplayName(proTypes, water.getProjectCategory()).equals("其他")){
				addLedgerDetailMap(rMap, 73, 74, 75, 76, 77, 78, new BigDecimal(water.getCount()), new BigDecimal(water.getResponseGroupNo()),
						water.getPlannedInvestment(), water.getSelfFund(), water.getGapFund(), new BigDecimal(water.getBeneficiaryNumber()));
			} 
		}
		addLedgerDetailMap(rMap, 1, 2, 3, 4, 5, 6, new BigDecimal(count), new BigDecimal(responseGroupNo),
				new BigDecimal(plannedInvestment), new BigDecimal(selfFund), new BigDecimal(gapFund), new BigDecimal(beneficiaryNumber));
		
		for(LedgerWaterDetail water : buildList){//建设性质
			if(water.getBuildType() == null)
				continue;
			if(getDisplayName(proTypes, water.getProjectCategory()).equals("山坪塘")){
				if(getDisplayName(buildTypes, water.getBuildType()).equals("新建")){
					rMap.put(9, new BigDecimal(water.getNum()));
				}else if(getDisplayName(buildTypes, water.getBuildType()).equals("改建")){
					rMap.put(10, new BigDecimal(water.getNum()));
				}else if(getDisplayName(buildTypes, water.getBuildType()).equals("扩建")){
					rMap.put(11, new BigDecimal(water.getNum()));
				}else if(getDisplayName(buildTypes, water.getBuildType()).equals("维修")){
					rMap.put(12, new BigDecimal(water.getNum()));
				}
			}else if(getDisplayName(proTypes, water.getProjectCategory()).equals("石河堰")){
				if(getDisplayName(buildTypes, water.getBuildType()).equals("新建")){
					rMap.put(20, new BigDecimal(water.getNum()));
				}else if(getDisplayName(buildTypes, water.getBuildType()).equals("改建")){
					rMap.put(21, new BigDecimal(water.getNum()));
				}else if(getDisplayName(buildTypes, water.getBuildType()).equals("扩建")){
					rMap.put(22, new BigDecimal(water.getNum()));
				}else if(getDisplayName(buildTypes, water.getBuildType()).equals("维修")){
					rMap.put(23, new BigDecimal(water.getNum()));
				}
			}else if(getDisplayName(proTypes, water.getProjectCategory()).equals("蓄水池")){
				if(getDisplayName(buildTypes, water.getBuildType()).equals("新建")){
					rMap.put(31, new BigDecimal(water.getNum()));
				}else if(getDisplayName(buildTypes, water.getBuildType()).equals("改建")){
					rMap.put(32, new BigDecimal(water.getNum()));
				}else if(getDisplayName(buildTypes, water.getBuildType()).equals("扩建")){
					rMap.put(33, new BigDecimal(water.getNum()));
				}else if(getDisplayName(buildTypes, water.getBuildType()).equals("维修")){
					rMap.put(34, new BigDecimal(water.getNum()));
				}
			}else if(getDisplayName(proTypes, water.getProjectCategory()).equals("饮水工程")){
				if(getDisplayName(buildTypes, water.getBuildType()).equals("新建")){
					rMap.put(42, new BigDecimal(water.getCentralized()));
				}else if(getDisplayName(buildTypes, water.getBuildType()).equals("改建")){
					rMap.put(43, new BigDecimal(water.getCentralized()));
				}else if(getDisplayName(buildTypes, water.getBuildType()).equals("扩建")){
					rMap.put(44, new BigDecimal(water.getCentralized()));
				}else if(getDisplayName(buildTypes, water.getBuildType()).equals("维修")){
					rMap.put(45, new BigDecimal(water.getCentralized()));
				}
			}else if(getDisplayName(proTypes, water.getProjectCategory()).equals("渠道")){
				if(getDisplayName(buildTypes, water.getBuildType()).equals("新建")){
					rMap.put(53, new BigDecimal(water.getCount()));
					rMap.put(57, new BigDecimal(water.getMileage()));
				}else if(getDisplayName(buildTypes, water.getBuildType()).equals("改建")){
					rMap.put(54, new BigDecimal(water.getCount()));
					rMap.put(58, new BigDecimal(water.getMileage()));
				}else if(getDisplayName(buildTypes, water.getBuildType()).equals("扩建")){
					rMap.put(55, new BigDecimal(water.getCount()));
					rMap.put(59, new BigDecimal(water.getMileage()));
				}else if(getDisplayName(buildTypes, water.getBuildType()).equals("维修")){
					rMap.put(56, new BigDecimal(water.getCount()));
					rMap.put(60, new BigDecimal(water.getMileage()));
				}
			}
		}
		
		addLedgerDoneMap(rMap, map, 79, 80, 81, 82);
		return rMap;
	}
	
	private String getDisplayName(List<PropertyDict> proTypes, int dId){
		for(PropertyDict dict : proTypes){
			if(dict.getId().longValue() == dId){
				return dict.getDisplayName().trim();
			}
		}
		return "";
	}
	
	private Map<Integer, BigDecimal> initResultMap(int num){
		Map<Integer, BigDecimal> rMap = new HashMap<Integer, BigDecimal>();
		for(int i = 1; i <= num; i++){
			rMap.put(i, new BigDecimal(0));
		}
		return rMap;
	}
	
	private void addLedgerDetailMap(Map<Integer, BigDecimal> rMap, int y1, int y2, int y3, int y4, int y5, int y6, 
			BigDecimal b1, BigDecimal b2, BigDecimal b3, BigDecimal b4, BigDecimal b5, BigDecimal b6){
		if(b1 != null)
			rMap.put(y1, b1);
		if(b2 != null)
			rMap.put(y2, b2);
		if(b3 != null)
			rMap.put(y3, b3);
		if(b4 != null)
			rMap.put(y4, b4);
		if(b5 != null)
			rMap.put(y5, b5);
		if(b6 != null)
			rMap.put(y6, b6);
	}
	
}
