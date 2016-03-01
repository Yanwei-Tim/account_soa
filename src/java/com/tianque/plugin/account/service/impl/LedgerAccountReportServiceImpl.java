package com.tianque.plugin.account.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.constants.LedgerReportFunctionalDepartments;
import com.tianque.plugin.account.constants.LedgerReportRow;
import com.tianque.plugin.account.constants.LedgerReportType;
import com.tianque.plugin.account.constants.ThreeRecordsIssueConstants;
import com.tianque.plugin.account.dao.LedgerReportDao;
import com.tianque.plugin.account.domain.AccountReport;
import com.tianque.plugin.account.domain.LedgerReportGroupCount;
import com.tianque.plugin.account.service.LedgerAccountReportService;
import com.tianque.plugin.account.state.ThreeRecordsIssueSourceState;
import com.tianque.plugin.account.state.ThreeRecordsIssueState;
import com.tianque.plugin.account.util.ComparisonAttribute;
import com.tianque.plugin.account.vo.LedgerCurrentYearCollectByMonthReportStatisticalVo;
import com.tianque.plugin.account.vo.LedgerCurrentYearCollectDoneRateReportStatisticalVo;
import com.tianque.plugin.account.vo.LedgerMonthReportStatisticalVo;
import com.tianque.plugin.account.vo.ThreeRecordsReportStatisticalVo;
import com.tianque.plugin.account.vo.ThreeRecordsReportVo;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;
import com.tianque.userAuth.api.PropertyDictDubboService;
import com.tianque.userAuth.api.PropertyDomainDubboService;

@Service("ledgerAccountReportService")
public class LedgerAccountReportServiceImpl implements LedgerAccountReportService {

	@Autowired
	private LedgerReportDao ledgerReportDao;
	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;
	@Autowired
	private PropertyDictDubboService propertyDictDubboService;
	@Autowired
	private PropertyDomainDubboService propertyDomainDubboService;

	@Override
	public List<ThreeRecordsReportStatisticalVo> findAccountReportBySearchVo(
			AccountReport accountReport) {
		if (accountReport == null || accountReport.getOrganization() == null
				|| accountReport.getOrganization().getId() == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			return dispatchByLedgerConstantsAndReportType(accountReport);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类AccountReportService的addAccountReport方法出现异常，原因：", "获取三本台账报表信息失败", e);
		}
	}

	/**
	 * 如果类型为空或为0，则表示查询台账所有类型的报表
	 * 
	 * @param accountType
	 * @return
	 */
	private Boolean isAllReportType(Integer accountType) {
		return accountType == null || accountType == 0;
	}
	
	@Override
	public List<LedgerMonthReportStatisticalVo> findMonthCollectDoneReportVo(
			AccountReport accountReport) {
		if (accountReport == null || accountReport.getOrganization() == null
				|| accountReport.getOrganization().getId() == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			return getMonthCollectOrDone(accountReport);
		} catch (Exception e) {
			return null;
		}
	}
	
	private List<LedgerMonthReportStatisticalVo> getMonthCollectOrDone(AccountReport accountReport){
		if (accountReport.getOrganization().getId() == null) {
			throw new BusinessValidationException("查询出错，未获得组织机构参数");
		}
		Organization organization = organizationDubboService.getFullOrgById(accountReport.getOrganization().getId());
		if (organization == null) {
			throw new BusinessValidationException("查询出错，组织机构数据不正确");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("water", LedgerConstants.PEOPLEASPIRATION_WATER);
		map.put("traffic", LedgerConstants.PEOPLEASPIRATION_TRAFFIC);
		map.put("energy", LedgerConstants.PEOPLEASPIRATION_ENERGY);
		map.put("education", LedgerConstants.PEOPLEASPIRATION_EDUCATION);
		map.put("science", LedgerConstants.PEOPLEASPIRATION_SCIENCE);
		map.put("medical", LedgerConstants.PEOPLEASPIRATION_MEDICAL);
		map.put("labor", LedgerConstants.PEOPLEASPIRATION_LABOR);
		map.put("environment", LedgerConstants.PEOPLEASPIRATION_ENVIRONMENT);
		map.put("town", LedgerConstants.PEOPLEASPIRATION_TOWN);
		map.put("agriculture", LedgerConstants.PEOPLEASPIRATION_AGRICULTURE);
		map.put("other", LedgerConstants.PEOPLEASPIRATION_OTHER);
		map.put("poorPeople", LedgerConstants.POORPEOPLE);
		map.put("steadyWork", LedgerConstants.STEADYWORK);
		map.put("beginDate", CalendarUtil.getMonthStart(accountReport.getReportYear(), accountReport.getReportMonth()));
		map.put("endDate", CalendarUtil.getMonthEnd(accountReport.getReportYear(), accountReport.getReportMonth()));
		//获得民生建表类型大类Id
		PropertyDict createTableTypePreTransferDict = propertyDictDubboService
				.findPropertyDictByDomainNameAndDictDisplayName(PropertyTypes.LEDGER_PEOPLEASPIRATION_CREATE_TABLE_TYPE, "上年接转");
		if (createTableTypePreTransferDict == null) {
			throw new BusinessValidationException("上年转接数查询出错");
		}
		map.put("createTableTypePreTransfer", createTableTypePreTransferDict.getId());
		
		Organization userOrg = organizationDubboService.getSimpleOrgById(ThreadVariable.getUser().getOrganization().getId());
		long functionType = propertyDictDubboService.findPropertyDictByDomainNameAndDictDisplayName(
				OrganizationType.ORG_TYPE_KEY, OrganizationType.FUNCTION_KEY).getId();
		if(userOrg.getOrgType().getId() == functionType && !userOrg.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_LEDGER) 
				&& !userOrg.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_COMMITTEE) 
				&& !userOrg.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_JOINT)){//如果登陆部门时职能部门并且不是联席、县委、台账办则直接用该职能部门统计
			organization = userOrg;
		}
		map.put("orgCode", organization.getOrgInternalCode());
		
		List<LedgerMonthReportStatisticalVo> res = null;
		String allNo = "";
		String parcent = "";
		if(accountReport.getReportType().intValue() == LedgerReportType.MONTH_COLLECT_REPORT){
			allNo = "本月收集数";
			parcent = "占本月本类%";
			if(accountReport.getAccountType() == LedgerConstants.POORPEOPLE){
				initPoorTypeMap(map);
				res = ledgerReportDao.getLedgerPoorPeopleMonthCollect(map);
			}else if(accountReport.getAccountType() == LedgerConstants.STEADYWORK){
				initSteadyWorkType(map);
				res = ledgerReportDao.getLedgerSteadyWorkMonthCollect(map);
			}else{
				res = ledgerReportDao.getLedgerPeopleMonthCollect(map);
			}
			dealLedgerMonthrReportResult(res, userOrg, organization, functionType, parcent, allNo);
		}else if(accountReport.getReportType().intValue() == LedgerReportType.MONTH_DONE_REPORT){
			allNo = "本月办结数";
			parcent = "占本月办结%";
			if(accountReport.getAccountType() == LedgerConstants.POORPEOPLE){
				initPoorTypeMap(map);
				res = ledgerReportDao.getLedgerPoorPeopleMonthDone(map);
			}else if(accountReport.getAccountType() == LedgerConstants.STEADYWORK){
				initSteadyWorkType(map);
				res = ledgerReportDao.getLedgerSteadyWorkMonthDone(map);
			}else{
				res = ledgerReportDao.getLedgerPeopleMonthDone(map);
			}
			dealLedgerMonthrReportResult(res, userOrg, organization, functionType, parcent, allNo);
		}
		return res;
	}
	
	private void dealLedgerMonthrReportResult(List<LedgerMonthReportStatisticalVo> res,
			Organization userOrg, Organization organization, long functionType, String parcent, String allNo){
		LedgerMonthReportStatisticalVo disVo = new LedgerMonthReportStatisticalVo("县级部门", allNo);
		LedgerMonthReportStatisticalVo dispVo = new LedgerMonthReportStatisticalVo("县级部门", parcent);//百分比
		LedgerMonthReportStatisticalVo townVo = new LedgerMonthReportStatisticalVo("乡镇", allNo);
		LedgerMonthReportStatisticalVo townpVo = new LedgerMonthReportStatisticalVo("乡镇", parcent);//百分比
		LedgerMonthReportStatisticalVo villageVo = new LedgerMonthReportStatisticalVo("村级", allNo);
		LedgerMonthReportStatisticalVo villagepVo = new LedgerMonthReportStatisticalVo("村级", parcent);//百分比
		LedgerMonthReportStatisticalVo sumVo = new LedgerMonthReportStatisticalVo("合计", allNo);
		LedgerMonthReportStatisticalVo sumpVo = new LedgerMonthReportStatisticalVo("合计", parcent);//百分比
		List<PropertyDict> pros = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL);
		Long districtLevel = getGridLevel(pros,OrganizationLevel.DISTRICT);//县级level
		Long townLevel = getGridLevel(pros,OrganizationLevel.TOWN);//乡镇level
		Long villageLevel = getGridLevel(pros,OrganizationLevel.VILLAGE);//村社区level
		for(LedgerMonthReportStatisticalVo vo : res){
			if(vo.getOrgLevel().startsWith(String.valueOf(districtLevel))){
				vo.setColumnName("县级部门");
				if(vo.getOrgLevel().endsWith("%")){
					dispVo = vo;
				}else{
					disVo = vo;
				}
			}else if(vo.getOrgLevel().startsWith(String.valueOf(townLevel))){
				vo.setColumnName("乡镇");
				if(vo.getOrgLevel().endsWith("%")){
					townpVo = vo;
				}else{
					townVo = vo;
				}
			}else if(vo.getOrgLevel().startsWith(String.valueOf(villageLevel))){
				vo.setColumnName("村级");
				if(vo.getOrgLevel().endsWith("%")){
					villagepVo = vo;
				}else{
					villageVo = vo;
				}
			}else {
				vo.setColumnName("合计");
				if(vo.getOrgLevel().endsWith("%")){
					sumpVo = vo;
				}else{
					sumVo = vo;
				}
			}
		}
		setMonthShowList(res, userOrg, disVo, dispVo, townVo, townpVo, villageVo, villagepVo, sumVo, sumpVo, organization, functionType);
		for(LedgerMonthReportStatisticalVo vo : res){
			if(StringUtil.isStringAvaliable(vo.getOrgLevel())){
				if(vo.getOrgLevel().endsWith("%")){
					vo.setOrgLevel(parcent);
				}else{
					vo.setOrgLevel(allNo);
				}
			}
		}
	}
	
	private void setMonthShowList(List<LedgerMonthReportStatisticalVo> res, Organization userOrg, LedgerMonthReportStatisticalVo disVo,
			LedgerMonthReportStatisticalVo dispVo, LedgerMonthReportStatisticalVo townVo, LedgerMonthReportStatisticalVo townpVo,
			LedgerMonthReportStatisticalVo villageVo, LedgerMonthReportStatisticalVo villagepVo,
			LedgerMonthReportStatisticalVo sumVo, LedgerMonthReportStatisticalVo sumpVo, Organization organization, long functionType){
		res.clear();
		final Long orgType = propertyDictDubboService.findPropertyDictByDomainNameAndDictDisplayName(
				OrganizationType.ORG_TYPE_KEY, OrganizationType.ADMINISTRATIVE_KEY).getId();//获得行政部门字典ID
		if (orgType.equals(userOrg.getOrgType().getId())) {//登陆账号为行政部门
			res.add(villageVo);res.add(villagepVo);
			if (organization.getOrgLevel().getInternalId() == OrganizationLevel.DISTRICT) {
				res.add(townVo);res.add(townpVo);
				res.add(disVo);res.add(dispVo);
			}else if (organization.getOrgLevel().getInternalId() == OrganizationLevel.TOWN) {
				res.add(townVo);res.add(townpVo);
			}
		}else{//登陆账号为职能部门
			if (!userOrg.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_LEDGER) 
					&& !userOrg.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_COMMITTEE) 
					&& !userOrg.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_JOINT)) {//非三大jg部门的职能部门只显示本部门数据
				res.add(disVo);res.add(dispVo);
			}else{
				if(organization.getOrgType().getId() == functionType){//登陆部门为三大jg部门时，选择某个职能部门统计时，只显示本职能部门数据（包括三大jg部门）
					res.add(disVo);res.add(dispVo);
				}else{
					res.add(villageVo);res.add(villagepVo);
					if (organization.getOrgLevel().getInternalId() == OrganizationLevel.DISTRICT) {
						res.add(townVo);res.add(townpVo);
						res.add(disVo);res.add(dispVo);
					}else if (organization.getOrgLevel().getInternalId() == OrganizationLevel.TOWN) {
						res.add(townVo);res.add(townpVo);
					}
				}
			}
		}
		res.add(sumVo);res.add(sumpVo);
	}
	
	private void initPoorTypeMap(Map<String, Object> map){
		List<PropertyDict> poorTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.LEDGER_POOR_PEOPLE_DIFFICULT_TYPE);
		for(PropertyDict dict : poorTypes){
			if(dict.getDisplayName().equals("生活")){
				map.put("lifeCount", dict.getId());
			}else if(dict.getDisplayName().equals("医疗")){
				map.put("medicalCount", dict.getId());
			}else if(dict.getDisplayName().equals("住房")){
				map.put("housingCount", dict.getId());
			}else if(dict.getDisplayName().equals("就学")){
				map.put("goSchoolCount", dict.getId());
			}else if(dict.getDisplayName().equals("就业")){
				map.put("employmentCount", dict.getId());
			}else if(dict.getDisplayName().equals("其他困难")){
				map.put("ledgerPoorPeopleOtherCount", dict.getId());
			}
		}
	}
	
	private void initSteadyWorkType(Map<String, Object> map){
		List<PropertyDict> steadyWorkTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.STEADY_RECORD_WORK_TYPE);
		for(PropertyDict dict : steadyWorkTypes){
			if(dict.getDisplayName().equals("涉法涉诉")){
				map.put("visitsCount", dict.getId());
			}else if(dict.getDisplayName().equals("林水土")){
				map.put("forestSoilCount", dict.getId());
			}else if(dict.getDisplayName().equals("惠农政策及村（社区）政务、财务")){
				map.put("favorablePoliciesCount", dict.getId());
			}else if(dict.getDisplayName().equals("民政问题")){
				map.put("civilAdministrationIssuesCount", dict.getId());
			}else if(dict.getDisplayName().equals("人口与医疗卫生")){
				map.put("populationMedicalCount", dict.getId());
			}else if(dict.getDisplayName().equals("劳动保障")){
				map.put("laborSocialSecurityCount", dict.getId());
			}else if(dict.getDisplayName().equals("交通运输")){
				map.put("transportationCount", dict.getId());
			}else if(dict.getDisplayName().equals("城建及综合执法")){
				map.put("urbanConstructionAndCLECount", dict.getId());
			}else if(dict.getDisplayName().equals("党纪政纪")){
				map.put("cpcPartyDisciplinesCount", dict.getId());
			}else if(dict.getDisplayName().equals("教育")){
				map.put("steadyRecordWorkEducationCount", dict.getId());
			}else if(dict.getDisplayName().equals("企业改制")){
				map.put("enterpriseRestructuringCount", dict.getId());
			}else if(dict.getDisplayName().equals("环境保护")){
				map.put("steadyEnvironmental", dict.getId());
			}else if(dict.getDisplayName().equals("组织人事")){
				map.put("organizationPersonnelCount", dict.getId());
			}else if(dict.getDisplayName().equals("其他稳定工作")){
				map.put("steadyRecordWorkOtherCount", dict.getId());
			}else if(dict.getDisplayName().equals("重点人员")){
				map.put("keyPersonnelCount", dict.getId());
			}else if(dict.getDisplayName().equals("其他")){
				map.put("steadyWorkOther", dict.getId());
			} 
		}
	}

	/**
	 * 报表适配器方法：根据参数获取相应Service和对应的列字典项，分发请求
	 * 
	 * @param accountReport
	 *            报表参数
	 */
	private List<ThreeRecordsReportStatisticalVo> dispatchByLedgerConstantsAndReportType(
			AccountReport accountReport) {
		List<ThreeRecordsReportStatisticalVo> vos = null;
		int reportType = accountReport.getReportType().intValue();
		switch (reportType) {
		case LedgerReportType.MONTH_REPORT:
			vos = constructMonthMouldContent(accountReport);
			break;
		case LedgerReportType.HOME_PAGE_REPORT:
			vos = getAccountTheHomePageInfo(accountReport);
			break;
		case LedgerReportType.YEAR_COLLECT_REPORT:
			vos = getAccountTheHomePageInfo(accountReport);
			break;
		default:
			throw new BusinessValidationException("无法定位报表类型");
		}
		return vos;
	}
	
	@Override
	public List<LedgerCurrentYearCollectDoneRateReportStatisticalVo> getYearCollectDoneRate(AccountReport accountReport){
		if (accountReport == null || accountReport.getOrganization() == null
				|| accountReport.getOrganization().getId() == null) {
			throw new BusinessValidationException("参数错误");
		}
		Organization organization = organizationDubboService.getFullOrgById(accountReport.getOrganization().getId());
		if (organization == null) {
			throw new BusinessValidationException("查询出错，组织机构数据不正确");
		}
		List<LedgerCurrentYearCollectDoneRateReportStatisticalVo> list = new ArrayList<LedgerCurrentYearCollectDoneRateReportStatisticalVo>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountType", accountReport.getAccountType());
		map.put("endDate", CalendarUtil.getMonthEnd(accountReport.getReportYear(), accountReport.getReportMonth()));
		map.put("beginDate", CalendarUtil.getCurrentYearBegin(accountReport.getReportYear()));
		Organization userOrg = organizationDubboService.getSimpleOrgById(ThreadVariable.getUser().getOrganization().getId());
		long functionType = propertyDictDubboService.findPropertyDictByDomainNameAndDictDisplayName(
				OrganizationType.ORG_TYPE_KEY, OrganizationType.FUNCTION_KEY).getId();
		if(userOrg.getOrgType().getId() == functionType && !userOrg.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_LEDGER) 
				&& !userOrg.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_COMMITTEE) 
				&& !userOrg.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_JOINT)){//如果登陆部门时职能部门并且不是联席、县委、台账办则直接用该职能部门统计
			organization = userOrg;
		}
		map.put("orgCode", organization.getOrgInternalCode());
		int reportType = accountReport.getReportType().intValue();
		switch (reportType) {
		case LedgerReportType.YEAR_COLLECT_DONE_SUM:
			list = getYearCollectDoneRateSum(map, organization, userOrg, functionType);
			break;
		case LedgerReportType.YEAR_COLLECT_DONE_DETAIL:
			list = getYearCollectDoneRateDetail(map, organization);
			break;
		default:
			break;
		}
		return list;
	}
	
	private List<LedgerCurrentYearCollectDoneRateReportStatisticalVo> getYearCollectDoneRateSum(Map<String, Object> map, Organization organization, 
			Organization userOrg, long functionType){
		List<LedgerCurrentYearCollectDoneRateReportStatisticalVo> res = ledgerReportDao.getYearCollectDoneRateSum(map);
		List<PropertyDict> pros = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL);
		Long districtLevel = getGridLevel(pros,OrganizationLevel.DISTRICT);//县级level
		Long townLevel = getGridLevel(pros,OrganizationLevel.TOWN);//乡镇level
		Long villageLevel = getGridLevel(pros,OrganizationLevel.VILLAGE);//村社区level
		final Long orgType = propertyDictDubboService.findPropertyDictByDomainNameAndDictDisplayName(
				OrganizationType.ORG_TYPE_KEY, OrganizationType.ADMINISTRATIVE_KEY).getId();//获得行政部门字典ID
		LedgerCurrentYearCollectDoneRateReportStatisticalVo disVo = new LedgerCurrentYearCollectDoneRateReportStatisticalVo("县级部门");
		LedgerCurrentYearCollectDoneRateReportStatisticalVo townVo = new LedgerCurrentYearCollectDoneRateReportStatisticalVo("乡镇");
		LedgerCurrentYearCollectDoneRateReportStatisticalVo villageVo = new LedgerCurrentYearCollectDoneRateReportStatisticalVo("村（社区）");
		LedgerCurrentYearCollectDoneRateReportStatisticalVo sumVo = new LedgerCurrentYearCollectDoneRateReportStatisticalVo("合计");
		for(LedgerCurrentYearCollectDoneRateReportStatisticalVo vo : res){
			if(vo.getOrgLevel().equals(String.valueOf(districtLevel))){
				vo.setColumnName("县级部门");
				disVo = vo;
			}else if(vo.getOrgLevel().equals(String.valueOf(townLevel))){
				vo.setColumnName("乡镇");
				townVo = vo;
			}else if(vo.getOrgLevel().equals(String.valueOf(villageLevel))){
				vo.setColumnName("村（社区）");
				villageVo = vo;
			}else{
				vo.setColumnName("合计");
				sumVo = vo;
			}
		}
		return initShowYearDoneRateList(orgType, userOrg, organization, disVo, townVo, villageVo, sumVo, functionType);
	}
	
	private List<LedgerCurrentYearCollectDoneRateReportStatisticalVo> initShowYearDoneRateList(Long orgType, Organization userOrg, Organization organization,
			LedgerCurrentYearCollectDoneRateReportStatisticalVo disVo, LedgerCurrentYearCollectDoneRateReportStatisticalVo townVo, 
			LedgerCurrentYearCollectDoneRateReportStatisticalVo villageVo,
			LedgerCurrentYearCollectDoneRateReportStatisticalVo sumVo, long functionType){
		List<LedgerCurrentYearCollectDoneRateReportStatisticalVo> list = new ArrayList<LedgerCurrentYearCollectDoneRateReportStatisticalVo>();
		list.add(sumVo);
		if (orgType.equals(userOrg.getOrgType().getId())) {//登陆账号为行政部门
			if (organization.getOrgLevel().getInternalId() == OrganizationLevel.DISTRICT) {
				list.add(disVo);
				list.add(townVo);
			}else if (organization.getOrgLevel().getInternalId() == OrganizationLevel.TOWN) {
				list.add(townVo);
			}
			list.add(villageVo);
		}else{//登陆账号为职能部门
			if (!userOrg.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_LEDGER) 
					&& !userOrg.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_COMMITTEE) 
					&& !userOrg.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_JOINT)) {//非三大jg部门的职能部门只显示本部门数据
				disVo.setColumnName(organization.getOrgName());
				list.add(disVo);
			}else{
				if(organization.getOrgType().getId() == functionType){//登陆部门为三大jg部门时，选择某个职能部门统计时，只显示本职能部门数据（包括三大jg部门）
					disVo.setColumnName(organization.getOrgName());
					list.add(disVo);
				}else{
					if (organization.getOrgLevel().getInternalId() == OrganizationLevel.DISTRICT) {
						list.add(disVo);
						list.add(townVo);
					}else if (organization.getOrgLevel().getInternalId() == OrganizationLevel.TOWN) {
						list.add(townVo);
					}
					list.add(villageVo);
				}
			}
		}
		return list;
	}
	
	private List<LedgerCurrentYearCollectDoneRateReportStatisticalVo> getYearCollectDoneRateDetail(Map<String, Object> map, Organization organization){
		List<LedgerCurrentYearCollectDoneRateReportStatisticalVo> res = new ArrayList<LedgerCurrentYearCollectDoneRateReportStatisticalVo>();
		long functionType = propertyDictDubboService.findPropertyDictByDomainNameAndDictDisplayName(
				OrganizationType.ORG_TYPE_KEY, OrganizationType.FUNCTION_KEY).getId();//职能部门
		List<PropertyDict> pros = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL);
		Long districtLevel = getGridLevel(pros,OrganizationLevel.DISTRICT);//县级level
		Long townLevel = getGridLevel(pros,OrganizationLevel.TOWN);//乡镇level
		Long villageLevel = getGridLevel(pros,OrganizationLevel.VILLAGE);//村社区level
		if(organization.getOrgLevel().getId()==districtLevel.longValue()){
			map.put("orgLevel", townLevel);
			map.put("orgType", functionType);
		}else if(organization.getOrgLevel().getId()==townLevel.longValue()){
			map.put("orgLevel", villageLevel);
		}else{
			return res;
		}
		res = ledgerReportDao.getYearCollectDoneRateDetail(map);
		Collections.reverse(res);//反转集合顺序,把合计排在第一行
		return res;
	}
	
	@Override
	public List<LedgerCurrentYearCollectByMonthReportStatisticalVo> getYearCollectByMonth(AccountReport accountReport){
		if (accountReport == null || accountReport.getOrganization() == null
				|| accountReport.getOrganization().getId() == null) {
			throw new BusinessValidationException("参数错误");
		}
		Organization organization = organizationDubboService.getFullOrgById(accountReport.getOrganization().getId());
		if (organization == null) {
			throw new BusinessValidationException("查询出错，组织机构数据不正确");
		}
		List<LedgerCurrentYearCollectByMonthReportStatisticalVo> list = new ArrayList<LedgerCurrentYearCollectByMonthReportStatisticalVo>();
		Map<String, Object> map = new HashMap<String, Object>();
//		获得民生建表类型大类Id
		PropertyDict createTableTypePreTransferDict = propertyDictDubboService
				.findPropertyDictByDomainNameAndDictDisplayName(PropertyTypes.LEDGER_PEOPLEASPIRATION_CREATE_TABLE_TYPE, "上年接转");
		if (createTableTypePreTransferDict == null) {
			throw new BusinessValidationException("上年转接数查询出错");
		}
		map.put("accountType", accountReport.getAccountType());
		map.put("createTableTypePreTransfer", createTableTypePreTransferDict.getId());
		map.put("endDate", CalendarUtil.getCurrentYearEnd(accountReport.getReportYear()));
		map.put("beginDate", CalendarUtil.getCurrentYearBegin(accountReport.getReportYear()));
		Organization userOrg = organizationDubboService.getSimpleOrgById(ThreadVariable.getUser().getOrganization().getId());
		long functionType = propertyDictDubboService.findPropertyDictByDomainNameAndDictDisplayName(
				OrganizationType.ORG_TYPE_KEY, OrganizationType.FUNCTION_KEY).getId();
		if(userOrg.getOrgType().getId() == functionType && !userOrg.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_LEDGER) 
				&& !userOrg.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_COMMITTEE) 
				&& !userOrg.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_JOINT)){//如果登陆部门时职能部门并且不是联席、县委、台账办则直接用该职能部门统计
			organization = userOrg;
		}
		map.put("orgCode", organization.getOrgInternalCode());
		int reportType = accountReport.getReportType().intValue();
		switch (reportType) {
		case LedgerReportType.YEAR_COLLECT_MONTH_SUM:
			list = getYearCollectByMonthSum(map, organization, userOrg, functionType);
			break;
		case LedgerReportType.YEAR_COLLECT_MONTH_DETAIL:
			list = getYearCollectByMonthDetail(map, organization);
			break;
		default:
			break;
		}
		return list;
	}
	
	private List<LedgerCurrentYearCollectByMonthReportStatisticalVo> getYearCollectByMonthSum(Map<String, Object> map, Organization organization, 
			Organization userOrg, long functionType){
		List<LedgerCurrentYearCollectByMonthReportStatisticalVo> res = ledgerReportDao.getYearCollectByMonthSum(map);
		List<PropertyDict> pros = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL);
		Long districtLevel = getGridLevel(pros,OrganizationLevel.DISTRICT);//县级level
		Long townLevel = getGridLevel(pros,OrganizationLevel.TOWN);//乡镇level
		Long villageLevel = getGridLevel(pros,OrganizationLevel.VILLAGE);//村社区level
		final Long orgType = propertyDictDubboService.findPropertyDictByDomainNameAndDictDisplayName(
				OrganizationType.ORG_TYPE_KEY, OrganizationType.ADMINISTRATIVE_KEY).getId();//获得行政部门字典ID
		LedgerCurrentYearCollectByMonthReportStatisticalVo disVo = new LedgerCurrentYearCollectByMonthReportStatisticalVo("县级部门");
		LedgerCurrentYearCollectByMonthReportStatisticalVo townVo = new LedgerCurrentYearCollectByMonthReportStatisticalVo("乡镇");
		LedgerCurrentYearCollectByMonthReportStatisticalVo villageVo = new LedgerCurrentYearCollectByMonthReportStatisticalVo("村（社区）");
		LedgerCurrentYearCollectByMonthReportStatisticalVo sumVo = new LedgerCurrentYearCollectByMonthReportStatisticalVo("合计");
		for(LedgerCurrentYearCollectByMonthReportStatisticalVo vo : res){
			if(vo.getOrgLevel().equals(String.valueOf(districtLevel))){
				vo.setColumnName("县级部门");
				disVo = vo;
			}else if(vo.getOrgLevel().equals(String.valueOf(townLevel))){
				vo.setColumnName("乡镇");
				townVo = vo;
			}else if(vo.getOrgLevel().equals(String.valueOf(villageLevel))){
				vo.setColumnName("村（社区）");
				villageVo = vo;
			}else{
				vo.setColumnName("合计");
				sumVo = vo;
			}
		}
		return initShowYearMonthCollectList(orgType, userOrg, organization, disVo, townVo, villageVo, sumVo, functionType);
	}
	private List<LedgerCurrentYearCollectByMonthReportStatisticalVo> getYearCollectByMonthDetail(Map<String, Object> map, Organization organization){
		List<LedgerCurrentYearCollectByMonthReportStatisticalVo> res = new ArrayList<LedgerCurrentYearCollectByMonthReportStatisticalVo>();
		long functionType = propertyDictDubboService.findPropertyDictByDomainNameAndDictDisplayName(
				OrganizationType.ORG_TYPE_KEY, OrganizationType.FUNCTION_KEY).getId();//职能部门
		List<PropertyDict> pros = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL);
		Long districtLevel = getGridLevel(pros,OrganizationLevel.DISTRICT);//县级level
		Long townLevel = getGridLevel(pros,OrganizationLevel.TOWN);//乡镇level
		Long villageLevel = getGridLevel(pros,OrganizationLevel.VILLAGE);//村社区level
		if(organization.getOrgLevel().getId()==districtLevel.longValue()){
			map.put("orgLevel", townLevel);
			map.put("orgType", functionType);
		}else if(organization.getOrgLevel().getId()==townLevel.longValue()){
			map.put("orgLevel", villageLevel);
		}else{
			return res;
		}
		res = ledgerReportDao.getYearCollectByMonthDetail(map);
		Collections.reverse(res);//反转集合顺序,把合计排在第一行
		return res;
	}
	
	private List<LedgerCurrentYearCollectByMonthReportStatisticalVo> initShowYearMonthCollectList(Long orgType, Organization userOrg, Organization organization,
			LedgerCurrentYearCollectByMonthReportStatisticalVo disVo, LedgerCurrentYearCollectByMonthReportStatisticalVo townVo, 
			LedgerCurrentYearCollectByMonthReportStatisticalVo villageVo,
			LedgerCurrentYearCollectByMonthReportStatisticalVo sumVo, long functionType){
		List<LedgerCurrentYearCollectByMonthReportStatisticalVo> list = new ArrayList<LedgerCurrentYearCollectByMonthReportStatisticalVo>();
		list.add(sumVo);
		if (orgType.equals(userOrg.getOrgType().getId())) {//登陆账号为行政部门
			if (organization.getOrgLevel().getInternalId() == OrganizationLevel.DISTRICT) {
				list.add(disVo);
				list.add(townVo);
			}else if (organization.getOrgLevel().getInternalId() == OrganizationLevel.TOWN) {
				list.add(townVo);
			}
			list.add(villageVo);
		}else{//登陆账号为职能部门
			if (!userOrg.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_LEDGER) 
					&& !userOrg.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_COMMITTEE) 
					&& !userOrg.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_JOINT)) {//非三大jg部门的职能部门只显示本部门数据
				disVo.setColumnName(organization.getOrgName());
				list.add(disVo);
			}else{
				if(organization.getOrgType().getId() == functionType){//登陆部门为三大jg部门时，选择某个职能部门统计时，只显示本职能部门数据（包括三大jg部门）
					disVo.setColumnName(organization.getOrgName());
					list.add(disVo);
				}else{
					if (organization.getOrgLevel().getInternalId() == OrganizationLevel.DISTRICT) {
						list.add(disVo);
						list.add(townVo);
					}else if (organization.getOrgLevel().getInternalId() == OrganizationLevel.TOWN) {
						list.add(townVo);
					}
					list.add(villageVo);
				}
			}
		}
		return list;
	}
	
	/***
	 * 首页报表查询
	 */
	private List<ThreeRecordsReportStatisticalVo> getAccountTheHomePageInfo(
			AccountReport accountReport) {
		if (accountReport.getOrganization().getId() == null) {
			throw new BusinessValidationException("查询出错，未获得组织机构参数");
		}
		Organization organization = organizationDubboService.getFullOrgById(accountReport.getOrganization().getId());
		if (organization == null) {
			throw new BusinessValidationException("查询出错，组织机构数据不正确");
		}
		List<ThreeRecordsReportStatisticalVo> list = new ArrayList<ThreeRecordsReportStatisticalVo>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("endDate", CalendarUtil.now());
		map.put("beginDate", CalendarUtil.getLastYearEnd(CalendarUtil.getNowYear()));
		Organization userOrg = organizationDubboService.getSimpleOrgById(ThreadVariable.getUser().getOrganization().getId());
		long functionType = propertyDictDubboService.findPropertyDictByDomainNameAndDictDisplayName(
				OrganizationType.ORG_TYPE_KEY, OrganizationType.FUNCTION_KEY).getId();
		if(userOrg.getOrgType().getId() == functionType && !userOrg.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_LEDGER) 
				&& !userOrg.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_COMMITTEE) 
				&& !userOrg.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_JOINT)){//如果登陆部门时职能部门并且不是联席、县委、台账办则直接用该职能部门统计
			organization = userOrg;
		}
		map.put("orgCode", organization.getOrgInternalCode());
		if(accountReport.getReportType().intValue() == LedgerReportType.HOME_PAGE_REPORT){
			list = dealTotal(getAllDistrictCount(list, organization, map, functionType, userOrg));
		}else if(accountReport.getReportType().intValue() == LedgerReportType.YEAR_COLLECT_REPORT){
			list = dealLedgerTotal(getYearCollect(list, organization, map, functionType, userOrg, accountReport));
		}
		return list;
	}
	private List<ThreeRecordsReportStatisticalVo> getYearCollect(List<ThreeRecordsReportStatisticalVo> list, Organization organization, Map<String, Object> map,
			long functionType, Organization userOrg, AccountReport accountReport){
		map.put("beginDate", CalendarUtil.getLastYearEnd(accountReport.getReportYear()));
		map.put("endDate", CalendarUtil.getMonthEnd(accountReport.getReportYear(), accountReport.getReportMonth()));
		PropertyDict createTableTypePreTransferDict = propertyDictDubboService
				.findPropertyDictByDomainNameAndDictDisplayName(PropertyTypes.LEDGER_PEOPLEASPIRATION_CREATE_TABLE_TYPE, "上年接转");
		if (createTableTypePreTransferDict == null) 
			throw new BusinessValidationException("上年转接数查询出错");
		map.put("createTableTypePreTransfer", createTableTypePreTransferDict.getId());
		
		List<PropertyDict> pros = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL);
		Long districtLevel = getGridLevel(pros,OrganizationLevel.DISTRICT);//县级level
		Long townLevel = getGridLevel(pros,OrganizationLevel.TOWN);//乡镇level
		Long villageLevel = getGridLevel(pros,OrganizationLevel.VILLAGE);//村社区level
		final Long orgType = propertyDictDubboService.findPropertyDictByDomainNameAndDictDisplayName(
				OrganizationType.ORG_TYPE_KEY, OrganizationType.ADMINISTRATIVE_KEY).getId();//获得行政部门字典ID
		List<ThreeRecordsReportVo> res = ledgerReportDao.getYearCollect(map);
		ThreeRecordsReportStatisticalVo disVo = new ThreeRecordsReportStatisticalVo("县");
		ThreeRecordsReportStatisticalVo townVo = new ThreeRecordsReportStatisticalVo("乡镇");
		ThreeRecordsReportStatisticalVo villageVo = new ThreeRecordsReportStatisticalVo("村（社区）");
		List<PropertyDict> poorTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.LEDGER_POOR_PEOPLE_DIFFICULT_TYPE);
		List<PropertyDict> steadyWorkTypes = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.STEADY_RECORD_WORK_TYPE);
		for(ThreeRecordsReportVo vo : res){
			initLedgerPeopleVo(disVo, townVo, villageVo, vo, districtLevel, townLevel, villageLevel);
			initLedgerPoorPeopleVo(disVo, townVo, villageVo, vo, districtLevel, townLevel, villageLevel, poorTypes);
			initLedgerSteadyWorkVo(disVo, townVo, villageVo, vo, districtLevel, townLevel, villageLevel, steadyWorkTypes);
		}
		return initShowList(orgType, userOrg, organization, list, disVo, townVo, villageVo, functionType);
	}
	
	private List<ThreeRecordsReportStatisticalVo> getAllDistrictCount(List<ThreeRecordsReportStatisticalVo> list, Organization organization, Map<String, Object> map,
			long functionType, Organization userOrg){
		List<PropertyDict> pros = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL);
		Long districtLevel = getGridLevel(pros,OrganizationLevel.DISTRICT);//县级level
		Long townLevel = getGridLevel(pros,OrganizationLevel.TOWN);//乡镇level
		Long villageLevel = getGridLevel(pros,OrganizationLevel.VILLAGE);//村社区level
		final Long orgType = propertyDictDubboService.findPropertyDictByDomainNameAndDictDisplayName(
				OrganizationType.ORG_TYPE_KEY, OrganizationType.ADMINISTRATIVE_KEY).getId();//获得行政部门字典ID
		List<ThreeRecordsReportVo> res = ledgerReportDao.getAllDistrictCount(map);
		ThreeRecordsReportStatisticalVo disVo = new ThreeRecordsReportStatisticalVo("县");
		ThreeRecordsReportStatisticalVo townVo = new ThreeRecordsReportStatisticalVo("乡镇");
		ThreeRecordsReportStatisticalVo villageVo = new ThreeRecordsReportStatisticalVo("村（社区）");
		for(ThreeRecordsReportVo vo : res){
			initLedgerPeopleVo(disVo, townVo, villageVo, vo, districtLevel, townLevel, villageLevel);
			if(vo.getLedgerType() == LedgerConstants.POORPEOPLE){
				if(vo.getOrgLevel() == districtLevel){
					disVo.setLedgerPoorPeopleTotal(vo.getCount());
				}else if(vo.getOrgLevel() == townLevel){
					townVo.setLedgerPoorPeopleTotal(vo.getCount());
				}if(vo.getOrgLevel() == villageLevel){
					villageVo.setLedgerPoorPeopleTotal(vo.getCount());
				}
			}if(vo.getLedgerType() == LedgerConstants.STEADYWORK){
				if(vo.getOrgLevel() == districtLevel){
					disVo.setSteadyRecordWorkTotal(vo.getCount());
				}else if(vo.getOrgLevel() == townLevel){
					townVo.setSteadyRecordWorkTotal(vo.getCount());
				}if(vo.getOrgLevel() == villageLevel){
					villageVo.setSteadyRecordWorkTotal(vo.getCount());
				}
			}
		}
		return initShowList(orgType, userOrg, organization, list, disVo, townVo, villageVo, functionType);
	}
	
	private List<ThreeRecordsReportStatisticalVo> initShowList(Long orgType, Organization userOrg, Organization organization, List<ThreeRecordsReportStatisticalVo> list,
			ThreeRecordsReportStatisticalVo disVo, ThreeRecordsReportStatisticalVo townVo, ThreeRecordsReportStatisticalVo villageVo,long functionType){
		if (orgType.equals(userOrg.getOrgType().getId())) {//登陆账号为行政部门
			if (organization.getOrgLevel().getInternalId() == OrganizationLevel.DISTRICT) {
				list.add(disVo);
				list.add(townVo);
			}else if (organization.getOrgLevel().getInternalId() == OrganizationLevel.TOWN) {
				list.add(townVo);
			}
			list.add(villageVo);
		}else{//登陆账号为职能部门
			if (!userOrg.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_LEDGER) 
					&& !userOrg.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_COMMITTEE) 
					&& !userOrg.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_JOINT)) {//非三大jg部门的职能部门只显示本部门数据
				disVo.setColumnName(organization.getOrgName());
				list.add(disVo);
			}else{
				if(organization.getOrgType().getId() == functionType){//登陆部门为三大jg部门时，选择某个职能部门统计时，只显示本职能部门数据（包括三大jg部门）
					disVo.setColumnName(organization.getOrgName());
					list.add(disVo);
				}else{
					if (organization.getOrgLevel().getInternalId() == OrganizationLevel.DISTRICT) {
						list.add(disVo);
						list.add(townVo);
					}else if (organization.getOrgLevel().getInternalId() == OrganizationLevel.TOWN) {
						list.add(townVo);
					}
					list.add(villageVo);
				}
			}
		}
		return list;
	}
	
	private void initLedgerPoorPeopleVo(ThreeRecordsReportStatisticalVo disVo, ThreeRecordsReportStatisticalVo townVo, ThreeRecordsReportStatisticalVo villageVo,
			ThreeRecordsReportVo vo, Long districtLevel, Long townLevel, Long villageLevel, List<PropertyDict> poorTypes){
		if(getDisplayName(poorTypes, vo.getLedgerType()).equals("生活")){
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setLifeCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setLifeCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setLifeCount(vo.getCount());
			}
		}else if(getDisplayName(poorTypes, vo.getLedgerType()).equals("医疗")){
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setMedicalCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setMedicalCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setMedicalCount(vo.getCount());
			}
		}else if(getDisplayName(poorTypes, vo.getLedgerType()).equals("住房")){
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setHousingCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setHousingCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setHousingCount(vo.getCount());
			}
		}else if(getDisplayName(poorTypes, vo.getLedgerType()).equals("就学")){
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setGoSchoolCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setGoSchoolCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setGoSchoolCount(vo.getCount());
			}
		}else if(getDisplayName(poorTypes, vo.getLedgerType()).equals("就业")){
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setEmploymentCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setEmploymentCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setEmploymentCount(vo.getCount());
			}
		}else if(getDisplayName(poorTypes, vo.getLedgerType()).equals("其他困难")){
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setLedgerPoorPeopleOtherCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setLedgerPoorPeopleOtherCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setLedgerPoorPeopleOtherCount(vo.getCount());
			}
		}
	}
	
	private void initLedgerSteadyWorkVo(ThreeRecordsReportStatisticalVo disVo, ThreeRecordsReportStatisticalVo townVo, ThreeRecordsReportStatisticalVo villageVo,
			ThreeRecordsReportVo vo, Long districtLevel, Long townLevel, Long villageLevel, List<PropertyDict> steadyWorkTypes){
		if(getDisplayName(steadyWorkTypes, vo.getLedgerType()).equals("涉法涉诉")){
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setVisitsCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setVisitsCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setVisitsCount(vo.getCount());
			}
		}else if(getDisplayName(steadyWorkTypes, vo.getLedgerType()).equals("林水土")){
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setForestSoilCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setForestSoilCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setForestSoilCount(vo.getCount());
			}
		}else if(getDisplayName(steadyWorkTypes, vo.getLedgerType()).equals("惠农政策及村（社区）政务、财务")){
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setFavorablePoliciesCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setFavorablePoliciesCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setFavorablePoliciesCount(vo.getCount());
			}
		}else if(getDisplayName(steadyWorkTypes, vo.getLedgerType()).equals("民政问题")){
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setCivilAdministrationIssuesCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setCivilAdministrationIssuesCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setCivilAdministrationIssuesCount(vo.getCount());
			}
		}else if(getDisplayName(steadyWorkTypes, vo.getLedgerType()).equals("人口与医疗卫生")){
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setPopulationMedicalCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setPopulationMedicalCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setPopulationMedicalCount(vo.getCount());
			}
		}else if(getDisplayName(steadyWorkTypes, vo.getLedgerType()).equals("劳动保障")){
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setLaborSocialSecurityCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setLaborSocialSecurityCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setLaborSocialSecurityCount(vo.getCount());
			}
		}else if(getDisplayName(steadyWorkTypes, vo.getLedgerType()).equals("交通运输")){
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setTransportationCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setTransportationCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setTransportationCount(vo.getCount());
			}
		}else if(getDisplayName(steadyWorkTypes, vo.getLedgerType()).equals("城建及综合执法")){
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setUrbanConstructionAndCLECount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setUrbanConstructionAndCLECount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setUrbanConstructionAndCLECount(vo.getCount());
			}
		}else if(getDisplayName(steadyWorkTypes, vo.getLedgerType()).equals("党纪政纪")){
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setCpcPartyDisciplinesCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setCpcPartyDisciplinesCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setCpcPartyDisciplinesCount(vo.getCount());
			}
		}else if(getDisplayName(steadyWorkTypes, vo.getLedgerType()).equals("教育")){
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setSteadyRecordWorkEducationCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setSteadyRecordWorkEducationCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setSteadyRecordWorkEducationCount(vo.getCount());
			}
		}else if(getDisplayName(steadyWorkTypes, vo.getLedgerType()).equals("企业改制")){
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setEnterpriseRestructuringCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setEnterpriseRestructuringCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setEnterpriseRestructuringCount(vo.getCount());
			}
		}else if(getDisplayName(steadyWorkTypes, vo.getLedgerType()).equals("环境保护")){
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setSteadyRecordWorkEnvironmentalProtectionCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setSteadyRecordWorkEnvironmentalProtectionCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setSteadyRecordWorkEnvironmentalProtectionCount(vo.getCount());
			}
		}else if(getDisplayName(steadyWorkTypes, vo.getLedgerType()).equals("组织人事")){
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setOrganizationPersonnelCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setOrganizationPersonnelCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setOrganizationPersonnelCount(vo.getCount());
			}
		}else if(getDisplayName(steadyWorkTypes, vo.getLedgerType()).equals("其他稳定工作")){
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setSteadyRecordWorkOtherCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setSteadyRecordWorkOtherCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setSteadyRecordWorkOtherCount(vo.getCount());
			}
		}else if(getDisplayName(steadyWorkTypes, vo.getLedgerType()).equals("重点人员")){
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setKeyPersonnelCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setKeyPersonnelCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setKeyPersonnelCount(vo.getCount());
			}
		}else if(getDisplayName(steadyWorkTypes, vo.getLedgerType()).equals("其他")){
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setSteadyWorkOther(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setSteadyWorkOther(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setSteadyWorkOther(vo.getCount());
			}
		} 
	}
	
	private String getDisplayName(List<PropertyDict> proTypes, long dId){
		for(PropertyDict dict : proTypes){
			if(dict.getId().longValue() == dId){
				return dict.getDisplayName().trim();
			}
		}
		return "";
	}
	
	private void initLedgerPeopleVo(ThreeRecordsReportStatisticalVo disVo, ThreeRecordsReportStatisticalVo townVo, ThreeRecordsReportStatisticalVo villageVo, 
			ThreeRecordsReportVo vo, Long districtLevel, Long townLevel, Long villageLevel) {
		if (vo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_WATER) {
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setWaterResourceCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setWaterResourceCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setWaterResourceCount(vo.getCount());
			}
		} else if (vo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_AGRICULTURE) {
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setResourcesAgriculturalCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setResourcesAgriculturalCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setResourcesAgriculturalCount(vo.getCount());
			}
		}
		if (vo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_EDUCATION) {
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setEducationCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setEducationCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setEducationCount(vo.getCount());
			}
		}
		if (vo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_ENERGY) {
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setEnergyCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setEnergyCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setEnergyCount(vo.getCount());
			}
		}
		if (vo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_ENVIRONMENT) {
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setEnvironmentalProtectionCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setEnvironmentalProtectionCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setEnvironmentalProtectionCount(vo.getCount());
			}
		}
		if (vo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_LABOR) {
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setSocialLaborCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setSocialLaborCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setSocialLaborCount(vo.getCount());
			}
		}
		if (vo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_MEDICAL) {
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setHealthMedicalCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setHealthMedicalCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setHealthMedicalCount(vo.getCount());
			}
		}
		if (vo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_OTHER) {
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setOtherResourcesCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setOtherResourcesCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setOtherResourcesCount(vo.getCount());
			}
		}
		if (vo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_SCIENCE) {
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setScienceTechnologyCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setScienceTechnologyCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setScienceTechnologyCount(vo.getCount());
			}
		}
		if (vo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_TOWN) {
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setPlanningAdviceManagementCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setPlanningAdviceManagementCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setPlanningAdviceManagementCount(vo.getCount());
			}
		}
		if (vo.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_TRAFFIC) {
			if (vo.getOrgLevel() == districtLevel) {
				disVo.setTrafficCount(vo.getCount());
			} else if (vo.getOrgLevel() == townLevel) {
				townVo.setTrafficCount(vo.getCount());
			} else if (vo.getOrgLevel() == villageLevel) {
				villageVo.setTrafficCount(vo.getCount());
			}
		}
	}

	private List<ThreeRecordsReportStatisticalVo> findAccountVoForDistrict(
			List<ThreeRecordsReportStatisticalVo> list, Organization organization,
			Map<String, Object> map) {
		//		Long parentOrgId = organization.getParentOrg().getId();
		//		Organization parentOrg = organizationDubboService.getSimpleOrgById(parentOrgId);
		map.put("orgCode", organization.getOrgInternalCode());
		if (organization.getOrgLevel().getInternalId() == OrganizationLevel.DISTRICT) {
			Long districtLevel = getGridLevel(
					propertyDictDubboService
							.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL),
					OrganizationLevel.DISTRICT);//获取乡镇level
			map.put("orgLevel", districtLevel);
			//		map.put("orgType", propertyDictDubboService
			//				.findPropertyDictByDomainNameAndDictDisplayName(
			//						OrganizationType.ORG_TYPE_KEY,
			//						OrganizationType.FUNCTIONAL_ORG_KEY).getId());//职能部门type
			ThreeRecordsReportStatisticalVo depVo = dealThreeRecordsReportStatisticalVo(ledgerReportDao
					.getJurisdictionAccout(map));
			depVo.setColumnName("县");
			list.add(depVo);
		}
		if (organization.getOrgLevel().getInternalId() == OrganizationLevel.DISTRICT
				|| organization.getOrgLevel().getInternalId() == OrganizationLevel.TOWN) {
			map.put("currentUserOrgId", null);//下辖查询所有
			Long townLevel = getGridLevel(
					propertyDictDubboService
							.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL),
					OrganizationLevel.TOWN);//获取乡镇level
			map.put("orgLevel", townLevel);
			ThreeRecordsReportStatisticalVo townVo = dealThreeRecordsReportStatisticalVo(ledgerReportDao
					.getJurisdictionAccout(map));
			townVo.setColumnName("乡镇");
			list.add(townVo);
		}
		if (organization.getOrgLevel().getInternalId() == OrganizationLevel.DISTRICT
				|| organization.getOrgLevel().getInternalId() == OrganizationLevel.TOWN
				|| organization.getOrgLevel().getInternalId() == OrganizationLevel.VILLAGE) {
			Long villageLevel = getGridLevel(
					propertyDictDubboService
							.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL),
					OrganizationLevel.VILLAGE);//获取村社区level
			map.put("orgLevel", villageLevel);
			//		map.put("orgType", propertyDictDubboService
			//				.findPropertyDictByDomainNameAndDictDisplayName(
			//						OrganizationType.ORG_TYPE_KEY,
			//						OrganizationType.ADMINISTRATIVE_KEY).getId());
			ThreeRecordsReportStatisticalVo villageVo = dealThreeRecordsReportStatisticalVo(ledgerReportDao
					.getJurisdictionAccout(map));
			villageVo.setColumnName("村(社区)");
			list.add(villageVo);
		}
		return list;
	}
	
	private List<ThreeRecordsReportStatisticalVo> dealLedgerTotal(List<ThreeRecordsReportStatisticalVo> list) {
		if (list != null && list.size() != 0) {
			ThreeRecordsReportStatisticalVo summary = new ThreeRecordsReportStatisticalVo("合计");
			for (ThreeRecordsReportStatisticalVo vo : list) {
				int peopleAsWorkTotal = 0;//民生合计
				peopleAsWorkTotal = vo.getWaterResourceCount() + vo.getTrafficCount() + vo.getEducationCount() + vo.getHealthMedicalCount()
						+ vo.getResourcesAgriculturalCount() + vo.getEnergyCount() + vo.getSocialLaborCount()
						+ vo.getEnvironmentalProtectionCount() + vo.getPlanningAdviceManagementCount() + vo.getScienceTechnologyCount()
						+ vo.getOtherResourcesCount();
				vo.setPeopleAsWorkTotal(peopleAsWorkTotal);
				summary.setWaterResourceCount(summary.getWaterResourceCount() + vo.getWaterResourceCount());
				summary.setTrafficCount(summary.getTrafficCount() + vo.getTrafficCount());
				summary.setEducationCount(summary.getEducationCount() + vo.getEducationCount());
				summary.setHealthMedicalCount(summary.getHealthMedicalCount() + vo.getHealthMedicalCount());
				summary.setResourcesAgriculturalCount(summary.getResourcesAgriculturalCount() + vo.getResourcesAgriculturalCount());
				summary.setEnergyCount(summary.getEnergyCount() + vo.getEnergyCount());
				summary.setSocialLaborCount(summary.getSocialLaborCount() + vo.getSocialLaborCount());
				summary.setEnvironmentalProtectionCount(summary.getEnvironmentalProtectionCount() + vo.getEnvironmentalProtectionCount());
				summary.setPlanningAdviceManagementCount(summary.getPlanningAdviceManagementCount() + vo.getPlanningAdviceManagementCount());
				summary.setScienceTechnologyCount(summary.getScienceTechnologyCount() + vo.getScienceTechnologyCount());
				summary.setOtherResourcesCount(summary.getOtherResourcesCount() + vo.getOtherResourcesCount());
				summary.setPeopleAsWorkTotal(summary.getPeopleAsWorkTotal() + vo.getPeopleAsWorkTotal());
				
				int ledgerPoorPeopleTotal = 0;//困难合计
				ledgerPoorPeopleTotal = vo.getLifeCount() + vo.getMedicalCount() + vo.getHousingCount() + vo.getGoSchoolCount()
						+ vo.getEmploymentCount() + vo.getLedgerPoorPeopleOtherCount();
				vo.setLedgerPoorPeopleTotal(ledgerPoorPeopleTotal);
				summary.setLifeCount(summary.getLifeCount() + vo.getLifeCount());
				summary.setMedicalCount(summary.getMedicalCount() + vo.getMedicalCount());
				summary.setHousingCount(summary.getHousingCount() + vo.getHousingCount());
				summary.setGoSchoolCount(summary.getGoSchoolCount() + vo.getGoSchoolCount());
				summary.setEmploymentCount(summary.getEmploymentCount() + vo.getEmploymentCount());
				summary.setLedgerPoorPeopleOtherCount(summary.getLedgerPoorPeopleOtherCount() + vo.getLedgerPoorPeopleOtherCount());
				summary.setLedgerPoorPeopleTotal(summary.getLedgerPoorPeopleTotal() + vo.getLedgerPoorPeopleTotal());
				
				int steadyRecordWorkTotal = 0;//稳定合计
				steadyRecordWorkTotal = vo.getVisitsCount() + vo.getForestSoilCount() + vo.getFavorablePoliciesCount() + vo.getCivilAdministrationIssuesCount()
						+ vo.getPopulationMedicalCount() + vo.getLaborSocialSecurityCount() + vo.getTransportationCount() + vo.getUrbanConstructionAndCLECount()
						+ vo.getCpcPartyDisciplinesCount() + vo.getSteadyRecordWorkEducationCount() + vo.getEnterpriseRestructuringCount()
						+ vo.getSteadyRecordWorkEnvironmentalProtectionCount() + vo.getOrganizationPersonnelCount() + vo.getSteadyRecordWorkOtherCount()
						+ vo.getKeyPersonnelCount() + vo.getSteadyWorkOther();
				vo.setSteadyRecordWorkTotal(steadyRecordWorkTotal);
				summary.setVisitsCount(summary.getVisitsCount() + vo.getVisitsCount());
				summary.setForestSoilCount(summary.getForestSoilCount() + vo.getForestSoilCount());
				summary.setFavorablePoliciesCount(summary.getFavorablePoliciesCount() + vo.getFavorablePoliciesCount());
				summary.setCivilAdministrationIssuesCount(summary.getCivilAdministrationIssuesCount() + vo.getCivilAdministrationIssuesCount());
				summary.setPopulationMedicalCount(summary.getPopulationMedicalCount() + vo.getPopulationMedicalCount());
				summary.setLaborSocialSecurityCount(summary.getLaborSocialSecurityCount() + vo.getLaborSocialSecurityCount());
				summary.setTransportationCount(summary.getTransportationCount() + vo.getTransportationCount());
				summary.setUrbanConstructionAndCLECount(summary.getUrbanConstructionAndCLECount() + vo.getUrbanConstructionAndCLECount());
				summary.setCpcPartyDisciplinesCount(summary.getCpcPartyDisciplinesCount() + vo.getCpcPartyDisciplinesCount());
				summary.setSteadyRecordWorkEducationCount(summary.getSteadyRecordWorkEducationCount() + vo.getSteadyRecordWorkEducationCount());
				summary.setEnterpriseRestructuringCount(summary.getEnterpriseRestructuringCount() + vo.getEnterpriseRestructuringCount());
				summary.setSteadyRecordWorkEnvironmentalProtectionCount(summary.getSteadyRecordWorkEnvironmentalProtectionCount() + vo.getSteadyRecordWorkEnvironmentalProtectionCount());
				summary.setOrganizationPersonnelCount(summary.getOrganizationPersonnelCount() + vo.getOrganizationPersonnelCount());
				summary.setSteadyRecordWorkOtherCount(summary.getSteadyRecordWorkOtherCount() + vo.getSteadyRecordWorkOtherCount());
				summary.setKeyPersonnelCount(summary.getKeyPersonnelCount() + vo.getKeyPersonnelCount());
				summary.setSteadyWorkOther(summary.getSteadyWorkOther() + vo.getSteadyWorkOther());
				summary.setSteadyRecordWorkTotal(summary.getSteadyRecordWorkTotal() + vo.getSteadyRecordWorkTotal());
			}
			list.add(summary);
		}
		return list;
	}

	/***
	 * 计算小计和合计
	 */
	private List<ThreeRecordsReportStatisticalVo> dealTotal(
			List<ThreeRecordsReportStatisticalVo> list) {
		if (list != null && list.size() != 0) {
			ThreeRecordsReportStatisticalVo summary = new ThreeRecordsReportStatisticalVo("汇总");
			for (ThreeRecordsReportStatisticalVo vo : list) {
				int peopleAsWorkTotal = 0;//小计
				peopleAsWorkTotal = vo.getWaterResourceCount() + vo.getTrafficCount()
						+ vo.getEducationCount() + vo.getHealthMedicalCount()
						+ vo.getMedicalCount() + vo.getResourcesAgriculturalCount()
						+ vo.getEnergyCount() + vo.getSocialLaborCount()
						+ vo.getEnvironmentalProtectionCount()
						+ vo.getPlanningAdviceManagementCount() + vo.getScienceTechnologyCount()
						+ vo.getOtherResourcesCount();
				vo.setPeopleAsWorkTotal(peopleAsWorkTotal);
				vo.setTotal(peopleAsWorkTotal + vo.getLedgerPoorPeopleTotal()
						+ vo.getSteadyRecordWorkTotal());

				summary.setWaterResourceCount(summary.getWaterResourceCount()
						+ vo.getWaterResourceCount());
				summary.setTrafficCount(summary.getTrafficCount() + vo.getTrafficCount());
				summary.setEducationCount(summary.getEducationCount() + vo.getEducationCount());
				summary.setHealthMedicalCount(summary.getHealthMedicalCount()
						+ vo.getHealthMedicalCount());
				summary.setMedicalCount(summary.getMedicalCount() + vo.getMedicalCount());
				summary.setResourcesAgriculturalCount(summary.getResourcesAgriculturalCount()
						+ vo.getResourcesAgriculturalCount());
				summary.setEnergyCount(summary.getEnergyCount() + vo.getEnergyCount());
				summary.setSocialLaborCount(summary.getSocialLaborCount()
						+ vo.getSocialLaborCount());
				summary.setEnvironmentalProtectionCount(summary.getEnvironmentalProtectionCount()
						+ vo.getEnvironmentalProtectionCount());
				summary.setPlanningAdviceManagementCount(summary.getPlanningAdviceManagementCount()
						+ vo.getPlanningAdviceManagementCount());
				summary.setScienceTechnologyCount(summary.getScienceTechnologyCount()
						+ vo.getScienceTechnologyCount());
				summary.setOtherResourcesCount(summary.getOtherResourcesCount()
						+ vo.getOtherResourcesCount());
				summary.setLedgerPoorPeopleTotal(summary.getLedgerPoorPeopleTotal()
						+ vo.getLedgerPoorPeopleTotal());
				summary.setSteadyRecordWorkTotal(summary.getSteadyRecordWorkTotal()
						+ vo.getSteadyRecordWorkTotal());
				summary.setTotal(summary.getTotal() + vo.getTotal());
				summary.setPeopleAsWorkTotal(summary.getPeopleAsWorkTotal()
						+ vo.getPeopleAsWorkTotal());
			}
			list.add(summary);
		}
		return list;
	}

	/***
	 * 数据累计
	 */
	private ThreeRecordsReportStatisticalVo dealThreeRecordsReportStatisticalVo(
			List<ThreeRecordsReportStatisticalVo> voList) {
		ThreeRecordsReportStatisticalVo countVo = new ThreeRecordsReportStatisticalVo();
		if (voList == null || voList.size() == 0) {
			return countVo;
		}
		/**合计*/
		//		int peopleAsWorkTotal= 0;
		/*** 水利 */
		int waterResourceCount = 0;
		/*** 交通 */
		int trafficCount = 0;
		/*** 教育 */
		int educationCount = 0;
		/*** 医疗卫生 */
		int healthMedicalCount = 0;
		/*** 农业资源 */
		int resourcesAgriculturalCount = 0;
		/*** 能源 */
		int energyCount = 0;
		/*** 劳动和社会保障 */
		int socialLaborCount = 0;
		/*** 环境保护 */
		int environmentalProtectionCount = 0;
		/*** 城乡规划建议管理 */
		int planningAdviceManagementCount = 0;
		/*** 科技文体 */
		int scienceTechnologyCount = 0;
		/*** 其他资源 */
		int otherResourcesCount = 0;
		/*** 小计 困难*/
		int ledgerPoorPeopleTotal = 0;
		/*** 小计 稳定*/
		int steadyRecordWorkTotal = 0;
		for (ThreeRecordsReportStatisticalVo vo : voList) {
			waterResourceCount += vo.getWaterResourceCount() == null ? 0 : vo
					.getWaterResourceCount();
			trafficCount += vo.getTrafficCount() == null ? 0 : vo.getTrafficCount();
			educationCount += vo.getEducationCount() == null ? 0 : vo.getEducationCount();
			healthMedicalCount += vo.getHealthMedicalCount() == null ? 0 : vo
					.getHealthMedicalCount();
			resourcesAgriculturalCount += vo.getResourcesAgriculturalCount() == null ? 0 : vo
					.getResourcesAgriculturalCount();
			energyCount += vo.getEnergyCount() == null ? 0 : vo.getEnergyCount();
			socialLaborCount += vo.getSocialLaborCount() == null ? 0 : vo.getSocialLaborCount();
			environmentalProtectionCount += vo.getEnvironmentalProtectionCount() == null ? 0 : vo
					.getEnvironmentalProtectionCount();
			planningAdviceManagementCount += vo.getPlanningAdviceManagementCount() == null ? 0 : vo
					.getPlanningAdviceManagementCount();
			scienceTechnologyCount += vo.getScienceTechnologyCount() == null ? 0 : vo
					.getScienceTechnologyCount();
			otherResourcesCount += vo.getOtherResourcesCount() == null ? 0 : vo
					.getOtherResourcesCount();
			ledgerPoorPeopleTotal += vo.getLedgerPoorPeopleTotal() == null ? 0 : vo
					.getLedgerPoorPeopleTotal();
			steadyRecordWorkTotal += vo.getSteadyRecordWorkTotal() == null ? 0 : vo
					.getSteadyRecordWorkTotal();
		}
		//		peopleAsWorkTotal = waterResourceCount+trafficCount+educationCount+healthMedicalCount+resourcesAgriculturalCount+energyCount
		//		+socialLaborCount+environmentalProtectionCount+planningAdviceManagementCount+scienceTechnologyCount+otherResourcesCount;
		//		countVo.setPeopleAsWorkTotal(peopleAsWorkTotal);
		countVo.setWaterResourceCount(waterResourceCount);
		countVo.setTrafficCount(trafficCount);
		countVo.setEducationCount(educationCount);
		countVo.setHealthMedicalCount(healthMedicalCount);
		countVo.setResourcesAgriculturalCount(resourcesAgriculturalCount);
		countVo.setEnergyCount(energyCount);
		countVo.setSocialLaborCount(socialLaborCount);
		countVo.setEnvironmentalProtectionCount(environmentalProtectionCount);
		countVo.setPlanningAdviceManagementCount(planningAdviceManagementCount);
		countVo.setScienceTechnologyCount(scienceTechnologyCount);
		countVo.setOtherResourcesCount(otherResourcesCount);
		countVo.setLedgerPoorPeopleTotal(ledgerPoorPeopleTotal);
		countVo.setSteadyRecordWorkTotal(steadyRecordWorkTotal);
		return countVo;
	}

	/** 获取社区层级的orgLevel */
	private Long getGridLevel(List<PropertyDict> list, int level) {
		if (list != null && list.size() != 0) {
			for (PropertyDict dict : list) {
				if (dict.getInternalId() == level) {
					return dict.getId();
				}
			}
		}
		return null;
	}

	/**
	 * 台账类型判断
	 * 
	 * @param accountReport
	 * @return
	 */
	private List<ThreeRecordsReportStatisticalVo> constructMonthMouldContent(
			AccountReport accountReport) {
		if (accountReport == null || accountReport.getOrganization() == null) {
			throw new BusinessValidationException("参数错误！");
		}
		accountReport.setOrganization(organizationDubboService.getSimpleOrgById(accountReport
				.getOrganization().getId()));
		Integer organizationLevel = accountReport.getOrgLevelInternalId();
		if (ComparisonAttribute.isVillageOrganization(organizationLevel)
				|| ComparisonAttribute.isGridOrganization(organizationLevel)) {
			return constructVillageMouldContent(accountReport);
		}
		if (ComparisonAttribute.isTownOrganization(organizationLevel)) {
			return constructTownMouldContent(accountReport);
		}
		if (ComparisonAttribute.isDistrictOrganization(organizationLevel)) {
			if (ComparisonAttribute.isRecordHandleAffairs(accountReport.getOrganization())) {
				return constructCountyDepartmentContent(accountReport);
			}
			return constructCountyMouldContent(accountReport);
		}
		return null;
	}

	/**
	 * 县台账办月报表
	 * 
	 * @param accountReport
	 * @return
	 */
	public List<ThreeRecordsReportStatisticalVo> constructCountyDepartmentContent(
			AccountReport accountReport) {
		List<ThreeRecordsReportStatisticalVo> vos = new ArrayList<ThreeRecordsReportStatisticalVo>();
		Long orgId = accountReport.getOrganization().getId();
		Integer accountType = accountReport.getAccountType();
		String targetInternalCode = null;
		Long orgParentId = null;
		Organization targetOrg = null;
		/*** 县委县政府 */
		Organization countyPartyCommitteeGovernment = getDepartmentOrganization(
				ThreeRecordsIssueConstants.COUNTY_COMMITTEE, accountReport.getOrganization()
						.getParentOrg().getId());
		if (null == countyPartyCommitteeGovernment) {
			countyPartyCommitteeGovernment = new Organization();
		}
		/*** 县联席会议 */
		Organization jointMeeting = getDepartmentOrganization(
				ThreeRecordsIssueConstants.COUNTY_JOINT, accountReport.getOrganization()
						.getParentOrg().getId());
		if (null == jointMeeting) {
			jointMeeting = new Organization();
		}
		// 乡镇呈报数
		vos.add(cumulativeStateCodeCount(
				assemblyMap(null, null, null, ThreeRecordsIssueState.DEALING_CODE, accountType,
						targetInternalCode, orgId, ThreeRecordsIssueSourceState.submit, null,
						orgId, null), LedgerReportRow.CUMULATIVE + LedgerReportRow.TOWN
						+ LedgerReportRow.LEVEL_REPORTED + LedgerReportRow.COUNT));

		// 申报县联席会议数
		targetOrg = jointMeeting;
		vos.add(cumulativeStateCodeCount(
				assemblyMap(null, null, null, ThreeRecordsIssueState.DEALING_CODE, accountType,
						targetInternalCode, orgId, ThreeRecordsIssueSourceState.submit, orgId,
						targetOrg.getId(), null), LedgerReportRow.CUMULATIVE
						+ LedgerReportRow.DECLARE
						+ LedgerReportRow.COUNTY_JOINT_CHIEFS_OF_STAFF_MEETING
						+ LedgerReportRow.COUNT));

		// 申报县委县政府数
		targetOrg = countyPartyCommitteeGovernment;
		vos.add(cumulativeStateCodeCount(
				assemblyMap(null, null, null, ThreeRecordsIssueState.DEALING_CODE, accountType,
						targetInternalCode, orgId, ThreeRecordsIssueSourceState.submit, orgId,
						targetOrg.getId(), null), LedgerReportRow.CUMULATIVE
						+ LedgerReportRow.DECLARE + LedgerReportRow.COUNTY_GOVERNMENT
						+ LedgerReportRow.COUNT));
		// 转办县级部门数

		// 县联席会议交办数
		vos.add(cumulativeOtherStateCodeCount(
				assemblyMap(
						assemblyMap(null, null, null, null, accountType, targetInternalCode, orgId,
								ThreeRecordsIssueSourceState.submit, null, null,
								ThreeRecordsIssueSourceState.assign), jointMeeting.getId(), orgId,
						LedgerReportRow.ACCEPTANCE, null, null, LedgerReportRow.ASSIGNED, null,
						null, null), LedgerReportRow.CUMULATIVE
						+ LedgerReportRow.COUNTY_JOINT_CHIEFS_OF_STAFF_MEETING
						+ LedgerReportRow.ASSIGNED + LedgerReportRow.COUNT));
		// 县委县政府交办数
		vos.add(cumulativeOtherStateCodeCount(
				assemblyMap(
						assemblyMap(null, null, null, null, accountType, targetInternalCode, orgId,
								ThreeRecordsIssueSourceState.submit, null, null,
								ThreeRecordsIssueSourceState.assign),
						countyPartyCommitteeGovernment.getId(), orgId, LedgerReportRow.ACCEPTANCE,
						null, null, LedgerReportRow.ASSIGNED, null, null, null),
				LedgerReportRow.CUMULATIVE + LedgerReportRow.COUNTY_GOVERNMENT
						+ LedgerReportRow.ASSIGNED + LedgerReportRow.COUNT));
		// 上月县联席会议办理中数
		vos.add(cumulativeStateCodeCount(
				assemblyMap(jointMeeting.getId(), getFirstDayDate(0, 2, 0, accountReport),
						getLastDayDate(0, 2, 0, accountReport),
						ThreeRecordsIssueState.DEALING_CODE, accountType, targetInternalCode,
						orgParentId, null, null, null, null), LedgerReportRow.LAST_MONTH
						+ LedgerReportRow.COUNTY_JOINT_CHIEFS_OF_STAFF_MEETING_TRANSACTION
						+ LedgerReportRow.COUNT));
		// 上月县委县政府办理中数
		vos.add(cumulativeStateCodeCount(
				assemblyMap(countyPartyCommitteeGovernment.getId(),
						getFirstDayDate(0, 2, 0, accountReport),
						getLastDayDate(0, 2, 0, accountReport),
						ThreeRecordsIssueState.DEALING_CODE, accountType, targetInternalCode,
						orgParentId, null, null, null, null), LedgerReportRow.LAST_MONTH
						+ LedgerReportRow.COUNTY_GOVERNMENT_TRANSACTION + LedgerReportRow.COUNT));
		// 上月转办件办理中

		// 乡镇呈报数
		vos.add(cumulativeStateCodeCount(
				assemblyMap(null, getFirstDayDate(0, 1, 0, accountReport),
						getLastDayDate(0, 1, 0, accountReport),
						ThreeRecordsIssueState.DEALING_CODE, accountType, targetInternalCode,
						orgId, ThreeRecordsIssueSourceState.submit, null, orgId, null),
				LedgerReportRow.THIS_MONTH + LedgerReportRow.TOWN + LedgerReportRow.LEVEL_REPORTED
						+ LedgerReportRow.COUNT));

		// 申报县联席会议数
		targetOrg = jointMeeting;
		vos.add(cumulativeStateCodeCount(
				assemblyMap(null, getFirstDayDate(0, 1, 0, accountReport),
						getLastDayDate(0, 1, 0, accountReport),
						ThreeRecordsIssueState.DEALING_CODE, accountType, targetInternalCode,
						orgId, ThreeRecordsIssueSourceState.submit, orgId, targetOrg.getId(), null),
				LedgerReportRow.THIS_MONTH + LedgerReportRow.DECLARE
						+ LedgerReportRow.COUNTY_JOINT_CHIEFS_OF_STAFF_MEETING
						+ LedgerReportRow.COUNT));
		// 申报县委县政府数
		targetOrg = countyPartyCommitteeGovernment;
		vos.add(cumulativeStateCodeCount(
				assemblyMap(null, getFirstDayDate(0, 1, 0, accountReport),
						getLastDayDate(0, 1, 0, accountReport),
						ThreeRecordsIssueState.DEALING_CODE, accountType, targetInternalCode,
						orgId, ThreeRecordsIssueSourceState.submit, orgId, targetOrg.getId(), null),
				LedgerReportRow.THIS_MONTH + LedgerReportRow.DECLARE
						+ LedgerReportRow.COUNTY_GOVERNMENT + LedgerReportRow.COUNT));
		// 转办县级部门数

		// 县联席会议交办数
		vos.add(cumulativeOtherStateCodeCount(
				assemblyMap(
						assemblyMap(null, getFirstDayDate(0, 1, 0, accountReport),
								getLastDayDate(0, 1, 0, accountReport), null, accountType,
								targetInternalCode, orgId, ThreeRecordsIssueSourceState.submit,
								null, null, null), jointMeeting.getId(), orgId,
						LedgerReportRow.ACCEPTANCE, null, null, LedgerReportRow.ASSIGNED, null,
						null, null), LedgerReportRow.THIS_MONTH
						+ LedgerReportRow.COUNTY_JOINT_CHIEFS_OF_STAFF_MEETING
						+ LedgerReportRow.ASSIGNED + LedgerReportRow.COUNT));
		// 县委县政府交办数
		vos.add(cumulativeOtherStateCodeCount(
				assemblyMap(
						assemblyMap(null, getFirstDayDate(0, 1, 0, accountReport),
								getLastDayDate(0, 1, 0, accountReport), null, accountType,
								targetInternalCode, orgId, ThreeRecordsIssueSourceState.submit,
								null, null, null), countyPartyCommitteeGovernment.getId(), orgId,
						LedgerReportRow.ACCEPTANCE, null, null, LedgerReportRow.ASSIGNED, null,
						null, null), LedgerReportRow.THIS_MONTH + LedgerReportRow.COUNTY_GOVERNMENT
						+ LedgerReportRow.ASSIGNED + LedgerReportRow.COUNT));
		// 本月县联席会议办理中数
		vos.add(cumulativeStateCodeCount(
				assemblyMap(jointMeeting.getId(), getFirstDayDate(0, 1, 0, accountReport),
						getLastDayDate(0, 1, 0, accountReport),
						ThreeRecordsIssueState.DEALING_CODE, accountType, targetInternalCode,
						orgParentId, null, null, null, null), LedgerReportRow.THIS_MONTH
						+ LedgerReportRow.COUNTY_JOINT_CHIEFS_OF_STAFF_MEETING_TRANSACTION
						+ LedgerReportRow.COUNT));
		// 本月县委县政府办理中数
		vos.add(cumulativeStateCodeCount(
				assemblyMap(countyPartyCommitteeGovernment.getId(),
						getFirstDayDate(0, 1, 0, accountReport),
						getLastDayDate(0, 1, 0, accountReport),
						ThreeRecordsIssueState.DEALING_CODE, accountType, targetInternalCode,
						orgParentId, null, null, null, null), LedgerReportRow.THIS_MONTH
						+ LedgerReportRow.COUNTY_GOVERNMENT_TRANSACTION + LedgerReportRow.COUNT));
		// 本月转办件办理中

		return vos;
	}

	/**
	 * 县级月报表
	 * 
	 * @param accountReport
	 *            报表参数
	 * @param reportCountService
	 *            具体Service实现
	 * @param itemDicts
	 *            生成报表列的字典项
	 */
	public List<ThreeRecordsReportStatisticalVo> constructCountyMouldContent(
			AccountReport accountReport) {
		List<ThreeRecordsReportStatisticalVo> vos = new ArrayList<ThreeRecordsReportStatisticalVo>();
		if (accountReport == null || accountReport.getOrganization() == null
				|| accountReport.getOrganization().getParentOrg() == null) {
			return vos;
		}
		Long orgId = accountReport.getOrganization().getId();
		Integer accountType = accountReport.getAccountType();
		String nextLayer = null;// 本级
		String targetInternalCode = null;
		Long orgParentId = null;
		Long sourceOrgId = null;
		Long targetOrgId = null;

		/*** 县台账办 */
		Organization countyRecordHandleAffairs = getDepartmentOrganization(
				ThreeRecordsIssueConstants.COUNTY_LEDGER, accountReport.getOrganization()
						.getParentOrg().getId());
		if (null == countyRecordHandleAffairs) {
			countyRecordHandleAffairs = new Organization();
		}

		/*** 县委县政府 */
		Organization countyPartyCommitteeGovernment = getDepartmentOrganization(
				ThreeRecordsIssueConstants.COUNTY_COMMITTEE, accountReport.getOrganization()
						.getParentOrg().getId());
		if (null == countyPartyCommitteeGovernment) {
			countyPartyCommitteeGovernment = new Organization();
		}

		/*** 县联席会议 */
		Organization jointMeeting = getDepartmentOrganization(
				ThreeRecordsIssueConstants.COUNTY_JOINT, accountReport.getOrganization()
						.getParentOrg().getId());
		if (null == jointMeeting) {
			jointMeeting = new Organization();
		}

		// 县级部门累计 收集 合计
		nextLayer = LedgerReportRow.COUNTY + LedgerReportRow.DEPARTMENT
				+ LedgerReportRow.CUMULATIVE + LedgerReportRow.GATHER;

		ThreeRecordsReportStatisticalVo countyCumulativeTotalVo = new ThreeRecordsReportStatisticalVo(
				nextLayer + LedgerReportRow.COMBINED);
		// 县级部门累计 累计收集 县台账办转办数 小计
		ThreeRecordsReportStatisticalVo villageLevelReported = cumulativeOtherStateCodeCount(
				assemblyMap(
						assemblyMap(null, null, null, ThreeRecordsIssueState.DEALING_CODE,
								accountType, targetInternalCode, orgParentId,
								ThreeRecordsIssueSourceState.submit, null, null, null),
						countyRecordHandleAffairs.getId(), orgId, LedgerReportRow.ACCEPTANCE, null,
						null, LedgerReportRow.TRANSFER, null, null, null), nextLayer
						+ LedgerReportFunctionalDepartments.RECORD_HANDLE_AFFAIRS
						+ LedgerReportRow.TRANSFER + LedgerReportRow.COUNT);
		// 县级部门累计 累计收集 县台账办转办数 其中上年接转数
		List<String> statisticsRows = new ArrayList<String>();
		statisticsRows.add(LedgerReportRow.LAST_YEAR_PICK_UP_TURN);
		ThreeRecordsReportStatisticalVo villageLevelReportedPickUpTurn = cumulativeCreateTableTypeCount(
				assemblyMap(
						assemblyMap(null, null, null, null, accountType, targetInternalCode,
								orgParentId, null, countyRecordHandleAffairs.getId(), null, null),
						orgId, null, LedgerReportRow.ACCEPTANCE, null, null,
						LedgerReportRow.TRANSFER, null, null, null),
				nextLayer + LedgerReportFunctionalDepartments.RECORD_HANDLE_AFFAIRS
						+ LedgerReportRow.TRANSFER, statisticsRows).get(0);

		// 县级部门累计 累计收集 本部门建账数 小计
		ThreeRecordsReportStatisticalVo countyCreateTotal = new ThreeRecordsReportStatisticalVo(
				nextLayer + LedgerReportRow.COUNTY + LedgerReportRow.DEPARTMENT
						+ LedgerReportRow.CUMULATIVE + LedgerReportRow.CREATE
						+ LedgerReportRow.COUNT);
		// 县级部门累计 累计收集 本部门建账数 其中上年接转数
		ThreeRecordsReportStatisticalVo countyLevelReportedPickUpTurn = cumulativeCreateTableTypeCount(
				assemblyMap(orgId, null, null, null, accountType, targetInternalCode, orgParentId,
						null, null, null, null), nextLayer, statisticsRows).get(0);
		// 县级部门累计 累计收集 其中 本部门直接收集数
		statisticsRows.clear();
		statisticsRows.add(LedgerReportRow.DIRECT + LedgerReportRow.GATHER);
		ThreeRecordsReportStatisticalVo countyCreate = cumulativeCreateTableTypeCount(
				assemblyMap(orgId, null, null, null, accountType, targetInternalCode, orgParentId,
						null, sourceOrgId, targetOrgId, null),
				nextLayer + LedgerReportRow.AMONG_THEM + LedgerReportRow.COUNTY, statisticsRows)
				.get(0);
		// 县级部门累计 累计收集 其中 上级主管部门和县级领导班子有关领导交办数
		statisticsRows.clear();
		statisticsRows.add(LedgerReportRow.COUNTY_GOVERNMENT_LEADERSHIP + LedgerReportRow.ASSIGNED);
		ThreeRecordsReportStatisticalVo countyGovernmentLeadershipCreate = cumulativeCreateTableTypeCount(
				assemblyMap(orgId, null, null, null, accountType, targetInternalCode, orgParentId,
						null, sourceOrgId, targetOrgId, null),
				nextLayer + LedgerReportRow.AMONG_THEM, statisticsRows).get(0);

		// 县级部门累计 累计收集 其中 县人大议案建议意见交办数
		statisticsRows.clear();
		statisticsRows.add(LedgerReportRow.COUNTY_NPC_LEGISLATION_SUGGESTIONS
				+ LedgerReportRow.ASSIGNED);
		ThreeRecordsReportStatisticalVo countyNPCLegislationSuggestionsCreate = cumulativeCreateTableTypeCount(
				assemblyMap(orgId, null, null, null, accountType, targetInternalCode, orgParentId,
						null, sourceOrgId, targetOrgId, null),
				nextLayer + LedgerReportRow.AMONG_THEM, statisticsRows).get(0);

		// 县级部门累计 累计收集 其中 县政协提案建议意见交办数
		statisticsRows.clear();
		statisticsRows.add(LedgerReportRow.COUNTY_CPPCC_PROPOSAL_ADVICE_OPINION
				+ LedgerReportRow.ASSIGNED);
		ThreeRecordsReportStatisticalVo countyCPPCCProposalAdviceOpinionCreate = cumulativeCreateTableTypeCount(
				assemblyMap(orgId, null, null, null, accountType, targetInternalCode, orgParentId,
						null, sourceOrgId, targetOrgId, null),
				nextLayer + LedgerReportRow.AMONG_THEM, statisticsRows).get(0);

		List<ThreeRecordsReportStatisticalVo> villageTotalVos = new ArrayList<ThreeRecordsReportStatisticalVo>();
		villageTotalVos.add(countyLevelReportedPickUpTurn);
		villageTotalVos.add(countyCreate);
		villageTotalVos.add(countyGovernmentLeadershipCreate);
		villageTotalVos.add(countyNPCLegislationSuggestionsCreate);
		villageTotalVos.add(countyCPPCCProposalAdviceOpinionCreate);

		ComparisonAttribute.statisticsTotal(countyCreateTotal, villageTotalVos);
		ComparisonAttribute.statisticsTotal(countyCumulativeTotalVo, villageLevelReported);
		ComparisonAttribute.statisticsTotal(countyCumulativeTotalVo, countyCreateTotal);

		vos.add(countyCumulativeTotalVo);
		vos.add(villageLevelReported);
		vos.add(villageLevelReportedPickUpTurn);
		vos.add(countyCreateTotal);
		vos.addAll(villageTotalVos);

		// 县级部门累计办结 合计
		nextLayer = LedgerReportRow.COUNTY + LedgerReportRow.DEPARTMENT
				+ LedgerReportRow.CUMULATIVE + LedgerReportRow.END;

		ThreeRecordsReportStatisticalVo countyCumulativeEndTotalVo = new ThreeRecordsReportStatisticalVo(
				nextLayer + LedgerReportRow.COMBINED);
		// 县级部门累计办结 本部门建账办结数
		ThreeRecordsReportStatisticalVo countyCreateEndVo = cumulativeEndCount(
				assemblyMap(orgId, null, null, null, accountType, targetInternalCode, orgParentId,
						null, sourceOrgId, targetOrgId, null),
				nextLayer + LedgerReportRow.COUNTY + LedgerReportRow.CREATE, false, true).get(0);
		// 县级部门累计办结 县台账办转办件办结数
		ThreeRecordsReportStatisticalVo countyLevelReportedEnd = new ThreeRecordsReportStatisticalVo(
				nextLayer + LedgerReportFunctionalDepartments.RECORD_HANDLE_AFFAIRS
						+ LedgerReportRow.TRANSFER + LedgerReportRow.END + LedgerReportRow.COUNT);
		// 县级部门累计办结 其中 实质性办结数
		ThreeRecordsReportStatisticalVo countyLevelReportedEssenceEnd = cumulativeOtherStateCodeCount(
				assemblyMap(
						assemblyMap(null, null, null, ThreeRecordsIssueState.DEALING_CODE,
								accountType, targetInternalCode, orgParentId,
								ThreeRecordsIssueSourceState.submit, null, null, null), null, null,
						null, countyRecordHandleAffairs.getId(), null, LedgerReportRow.TRANSFER,
						null, null, LedgerReportRow.ESSENCE_SERCH + LedgerReportRow.END), nextLayer
						+ LedgerReportRow.AMONG_THEM + LedgerReportRow.TRANSFER
						+ LedgerReportRow.ESSENCE + LedgerReportRow.END + LedgerReportRow.COUNT);
		// 县级部门累计办结 其中 阶段性办结数
		ThreeRecordsReportStatisticalVo villageLevelReportedPhaseEnd = cumulativeStateCodeCount(
				assemblyMap(
						assemblyMap(null, null, null, ThreeRecordsIssueState.DEALING_CODE,
								accountType, targetInternalCode, null,
								ThreeRecordsIssueSourceState.submit, null, null, null), null, null,
						null, countyRecordHandleAffairs.getId(), null, LedgerReportRow.TRANSFER,
						null, null, LedgerReportRow.PHASE_SERCH + LedgerReportRow.END), nextLayer
						+ LedgerReportRow.AMONG_THEM + LedgerReportRow.TRANSFER
						+ LedgerReportRow.PHASE + LedgerReportRow.END + LedgerReportRow.COUNT);
		// 县级部门累计办结 其中 程序性办结数
		ThreeRecordsReportStatisticalVo villageLevelReportedProcedureEnd = cumulativeStateCodeCount(
				assemblyMap(
						assemblyMap(orgParentId, null, null, ThreeRecordsIssueState.DEALING_CODE,
								accountType, targetInternalCode, orgId,
								ThreeRecordsIssueSourceState.submit, null, null, null), null, null,
						null, countyRecordHandleAffairs.getId(), null, LedgerReportRow.TRANSFER,
						null, null, LedgerReportRow.PROCEDURE_SERCH + LedgerReportRow.END),
				nextLayer + LedgerReportRow.AMONG_THEM + LedgerReportRow.TRANSFER
						+ LedgerReportRow.PROCEDURE + LedgerReportRow.END + LedgerReportRow.COUNT);
		// 县级部门累计办结 其中 申报县联席会议数
		ThreeRecordsReportStatisticalVo jointMeetingDeclareProcedureEnd = cumulativeStateCodeCount(
				assemblyMap(
						assemblyMap(orgParentId, null, null, ThreeRecordsIssueState.DEALING_CODE,
								accountType, targetInternalCode, orgId,
								ThreeRecordsIssueSourceState.submit, null, null, null),
						jointMeeting.getId(), null, LedgerReportRow.ACCEPTANCE, orgId,
						jointMeeting.getId(), LedgerReportRow.DECLARE, null, null, null), nextLayer
						+ LedgerReportRow.AMONG_THEM + LedgerReportRow.DECLARE
						+ LedgerReportRow.COUNTY_JOINT_CHIEFS_OF_STAFF_MEETING
						+ LedgerReportRow.COUNT);
		// 县级部门累计办结 其中 申报县委县政府数
		ThreeRecordsReportStatisticalVo countyGovernmentDeclareProcedureEnd = cumulativeStateCodeCount(
				assemblyMap(
						assemblyMap(orgParentId, null, null, ThreeRecordsIssueState.DEALING_CODE,
								accountType, targetInternalCode, orgId,
								ThreeRecordsIssueSourceState.submit, null, null, null),
						countyPartyCommitteeGovernment.getId(), null, LedgerReportRow.ACCEPTANCE,
						orgId, countyPartyCommitteeGovernment.getId(), LedgerReportRow.DECLARE,
						null, null, null), nextLayer + LedgerReportRow.AMONG_THEM
						+ LedgerReportRow.DECLARE + LedgerReportRow.COUNTY_GOVERNMENT
						+ LedgerReportRow.COUNT);
		villageTotalVos.clear();
		villageTotalVos.add(countyLevelReportedEssenceEnd);
		villageTotalVos.add(villageLevelReportedPhaseEnd);
		villageTotalVos.add(villageLevelReportedProcedureEnd);
		villageTotalVos.add(jointMeetingDeclareProcedureEnd);
		villageTotalVos.add(countyGovernmentDeclareProcedureEnd);
		ComparisonAttribute.statisticsTotal(countyLevelReportedEnd, villageTotalVos);
		ComparisonAttribute.statisticsTotal(countyCumulativeEndTotalVo, countyCreateEndVo);
		ComparisonAttribute.statisticsTotal(countyCumulativeEndTotalVo, countyLevelReportedEnd);
		vos.add(countyCumulativeEndTotalVo);
		vos.add(countyCreateEndVo);
		vos.add(countyLevelReportedEnd);
		vos.addAll(villageTotalVos);
		// 县级部门 上月办理中
		nextLayer = LedgerReportRow.COUNTY + LedgerReportRow.DEPARTMENT;
		vos.add(cumulativeStateCodeCount(
				assemblyMap(orgId, getFirstDayDate(0, 2, 0, accountReport),
						getLastDayDate(0, 2, 0, accountReport),
						ThreeRecordsIssueState.DEALING_CODE, accountType, targetInternalCode,
						orgParentId, null, sourceOrgId, targetOrgId, null), nextLayer
						+ LedgerReportRow.LAST_MONTH + LedgerReportRow.TRANSACTION
						+ LedgerReportRow.COUNT));
		// 县级部门本月收集 合计
		nextLayer += LedgerReportRow.THIS_MONTH + LedgerReportRow.GATHER;
		ThreeRecordsReportStatisticalVo countyGatherThisMonthTotalVo = new ThreeRecordsReportStatisticalVo(
				nextLayer + LedgerReportRow.COMBINED);
		// 县级部门本月收集 县台账办转办数
		ThreeRecordsReportStatisticalVo countyLevelReportedThisMonthEnd = cumulativeStateCodeCount(
				assemblyMap(
						assemblyMap(orgParentId, getFirstDayDate(0, 1, 0, accountReport),
								getLastDayDate(0, 1, 0, accountReport),
								ThreeRecordsIssueState.DEALING_CODE, accountType,
								targetInternalCode, orgId, ThreeRecordsIssueSourceState.submit,
								null, null, null), null, null, LedgerReportRow.ACCEPTANCE,
						countyRecordHandleAffairs.getId(), null, LedgerReportRow.TRANSFER, null,
						null, null), nextLayer
						+ LedgerReportFunctionalDepartments.RECORD_HANDLE_AFFAIRS
						+ LedgerReportRow.TRANSFER + LedgerReportRow.COUNT);
		// 县级部门本月收集 本部门建账数
		ThreeRecordsReportStatisticalVo countyCreateThisMonthCount = new ThreeRecordsReportStatisticalVo(
				nextLayer + LedgerReportRow.COUNTY + LedgerReportRow.DEPARTMENT
						+ LedgerReportRow.CREATE + LedgerReportRow.COUNT);
		// 县级部门本月收集 本部门直接收集数
		statisticsRows.clear();
		statisticsRows.add(LedgerReportRow.DIRECT + LedgerReportRow.GATHER);
		ThreeRecordsReportStatisticalVo countyDirectGatherThisMonth = cumulativeCreateTableTypeCount(
				assemblyMap(orgId, getFirstDayDate(0, 1, 0, accountReport),
						getLastDayDate(0, 1, 0, accountReport), null, accountType,
						targetInternalCode, orgParentId, null, sourceOrgId, targetOrgId, null),
				nextLayer + LedgerReportRow.COUNTY + LedgerReportRow.DEPARTMENT, statisticsRows)
				.get(0);
		// 县级部门本月收集 上级主管部门和县级领导班子有关领导交办数
		statisticsRows.clear();
		statisticsRows.add(LedgerReportRow.COUNTY_GOVERNMENT_LEADERSHIP + LedgerReportRow.ASSIGNED);
		ThreeRecordsReportStatisticalVo countyCountyGovernmentLeadershipAssignedThisMonth = cumulativeCreateTableTypeCount(
				assemblyMap(orgId, getFirstDayDate(0, 1, 0, accountReport),
						getLastDayDate(0, 1, 0, accountReport), null, accountType,
						targetInternalCode, orgParentId, null, sourceOrgId, targetOrgId, null),
				nextLayer, statisticsRows).get(0);
		// 县级部门本月收集 县人大议案建议意见交办数
		statisticsRows.clear();
		statisticsRows.add(LedgerReportRow.COUNTY_NPC_LEGISLATION_SUGGESTIONS
				+ LedgerReportRow.ASSIGNED);
		ThreeRecordsReportStatisticalVo countyCountyNPCLegislationSuggestionsAssigned = cumulativeCreateTableTypeCount(
				assemblyMap(orgId, getFirstDayDate(0, 1, 0, accountReport),
						getLastDayDate(0, 1, 0, accountReport), null, accountType,
						targetInternalCode, orgParentId, null, sourceOrgId, targetOrgId, null),
				nextLayer, statisticsRows).get(0);
		// 县级部门本月收集 县政协提案建议意见交办数
		statisticsRows.clear();
		statisticsRows.add(LedgerReportRow.COUNTY_CPPCC_PROPOSAL_ADVICE_OPINION
				+ LedgerReportRow.ASSIGNED);
		ThreeRecordsReportStatisticalVo countyCountyCPPCCProposalAdviceOpinionAssigned = cumulativeCreateTableTypeCount(
				assemblyMap(orgId, getFirstDayDate(0, 1, 0, accountReport),
						getLastDayDate(0, 1, 0, accountReport), null, accountType,
						targetInternalCode, orgParentId, null, sourceOrgId, targetOrgId, null),
				nextLayer, statisticsRows).get(0);
		villageTotalVos.clear();
		villageTotalVos.add(countyDirectGatherThisMonth);
		villageTotalVos.add(countyCountyGovernmentLeadershipAssignedThisMonth);
		villageTotalVos.add(countyCountyNPCLegislationSuggestionsAssigned);
		villageTotalVos.add(countyCountyCPPCCProposalAdviceOpinionAssigned);
		ComparisonAttribute.statisticsTotal(countyCreateThisMonthCount, villageTotalVos);
		ComparisonAttribute.statisticsTotal(countyGatherThisMonthTotalVo,
				countyLevelReportedThisMonthEnd);
		ComparisonAttribute.statisticsTotal(countyGatherThisMonthTotalVo,
				countyCreateThisMonthCount);
		vos.add(countyGatherThisMonthTotalVo);
		vos.add(countyLevelReportedThisMonthEnd);
		vos.add(countyCreateThisMonthCount);
		vos.addAll(villageTotalVos);

		// 县级部门本月办结 合计
		nextLayer = LedgerReportRow.COUNTY + LedgerReportRow.DEPARTMENT
				+ LedgerReportRow.THIS_MONTH + LedgerReportRow.END;
		ThreeRecordsReportStatisticalVo countyEndThisMonthTotalVo = new ThreeRecordsReportStatisticalVo(
				nextLayer + LedgerReportRow.COMBINED);
		// 县级部门本月办结 本部门建账办结数
		ThreeRecordsReportStatisticalVo countyCreateEndThisMonthVo = cumulativeEndCount(
				assemblyMap(orgId, getFirstDayDate(0, 1, 0, accountReport),
						getLastDayDate(0, 1, 0, accountReport), null, accountType,
						targetInternalCode, orgParentId, null, sourceOrgId, targetOrgId, null),
				nextLayer + LedgerReportRow.COUNTY + LedgerReportRow.DEPARTMENT
						+ LedgerReportRow.CREATE, false, true).get(0);

		// 县级部门本月办结 县台账办转办件办结数
		ThreeRecordsReportStatisticalVo countyLevelReportedEndThisMonth = new ThreeRecordsReportStatisticalVo(
				nextLayer + LedgerReportFunctionalDepartments.RECORD_HANDLE_AFFAIRS
						+ LedgerReportRow.TRANSFER + LedgerReportRow.END + LedgerReportRow.COUNT);
		// 县级部门本月办结 其中 实质性办结数
		ThreeRecordsReportStatisticalVo countyLevelReportedEssenceEndThisMonth = cumulativeOtherStateCodeCount(
				assemblyMap(
						assemblyMap(null, getFirstDayDate(0, 1, 0, accountReport),
								getLastDayDate(0, 1, 0, accountReport),
								ThreeRecordsIssueState.DEALING_CODE, accountType,
								targetInternalCode, orgParentId,
								ThreeRecordsIssueSourceState.submit, null, null, null), null, null,
						null, countyRecordHandleAffairs.getId(), null, LedgerReportRow.TRANSFER,
						null, null, LedgerReportRow.ESSENCE_SERCH + LedgerReportRow.END), nextLayer
						+ LedgerReportRow.AMONG_THEM + LedgerReportRow.TRANSFER
						+ LedgerReportRow.ESSENCE + LedgerReportRow.END + LedgerReportRow.COUNT);
		// 县级部门本月办结 其中 阶段性办结数
		ThreeRecordsReportStatisticalVo villageLevelReportedPhaseEndThisMonth = cumulativeStateCodeCount(
				assemblyMap(
						assemblyMap(null, getFirstDayDate(0, 1, 0, accountReport),
								getLastDayDate(0, 1, 0, accountReport),
								ThreeRecordsIssueState.DEALING_CODE, accountType,
								targetInternalCode, null, ThreeRecordsIssueSourceState.submit,
								null, null, null), null, null, null,
						countyRecordHandleAffairs.getId(), null, LedgerReportRow.TRANSFER, null,
						null, LedgerReportRow.PHASE_SERCH + LedgerReportRow.END), nextLayer
						+ LedgerReportRow.AMONG_THEM + LedgerReportRow.TRANSFER
						+ LedgerReportRow.PHASE + LedgerReportRow.END + LedgerReportRow.COUNT);
		// 县级部门本月办结 其中 程序性办结数
		ThreeRecordsReportStatisticalVo villageLevelReportedProcedureEndThisMonth = cumulativeStateCodeCount(
				assemblyMap(
						assemblyMap(null, getFirstDayDate(0, 1, 0, accountReport),
								getLastDayDate(0, 1, 0, accountReport),
								ThreeRecordsIssueState.DEALING_CODE, accountType,
								targetInternalCode, orgId, ThreeRecordsIssueSourceState.submit,
								null, null, null), null, null, null,
						countyRecordHandleAffairs.getId(), null, LedgerReportRow.TRANSFER, null,
						null, LedgerReportRow.PROCEDURE_SERCH + LedgerReportRow.END), nextLayer
						+ LedgerReportRow.AMONG_THEM + LedgerReportRow.TRANSFER
						+ LedgerReportRow.PROCEDURE + LedgerReportRow.END + LedgerReportRow.COUNT);
		// 县级部门本月办结 其中 申报县联席会议数
		ThreeRecordsReportStatisticalVo jointMeetingDeclareProcedureEndThisMonth = cumulativeStateCodeCount(
				assemblyMap(
						assemblyMap(null, getFirstDayDate(0, 1, 0, accountReport),
								getLastDayDate(0, 1, 0, accountReport),
								ThreeRecordsIssueState.DEALING_CODE, accountType,
								targetInternalCode, orgId, ThreeRecordsIssueSourceState.submit,
								null, null, null), jointMeeting.getId(), null,
						LedgerReportRow.ACCEPTANCE, orgId, jointMeeting.getId(),
						LedgerReportRow.DECLARE, null, null, null), nextLayer
						+ LedgerReportRow.AMONG_THEM + LedgerReportRow.DECLARE
						+ LedgerReportRow.COUNTY_JOINT_CHIEFS_OF_STAFF_MEETING
						+ LedgerReportRow.COUNT);
		// 县级部门本月办结 其中 申报县委县政府数
		ThreeRecordsReportStatisticalVo countyGovernmentDeclareProcedureEndThisMonth = cumulativeStateCodeCount(
				assemblyMap(
						assemblyMap(null, getFirstDayDate(0, 1, 0, accountReport),
								getLastDayDate(0, 1, 0, accountReport),
								ThreeRecordsIssueState.DEALING_CODE, accountType,
								targetInternalCode, orgId, ThreeRecordsIssueSourceState.submit,
								null, null, null), countyPartyCommitteeGovernment.getId(), null,
						LedgerReportRow.ACCEPTANCE, orgId, countyPartyCommitteeGovernment.getId(),
						LedgerReportRow.DECLARE, null, null, null), nextLayer
						+ LedgerReportRow.AMONG_THEM + LedgerReportRow.DECLARE
						+ LedgerReportRow.COUNTY_GOVERNMENT + LedgerReportRow.COUNT);

		villageTotalVos.clear();
		villageTotalVos.add(countyLevelReportedEssenceEndThisMonth);
		villageTotalVos.add(villageLevelReportedPhaseEndThisMonth);
		villageTotalVos.add(villageLevelReportedProcedureEndThisMonth);
		villageTotalVos.add(jointMeetingDeclareProcedureEndThisMonth);
		villageTotalVos.add(countyGovernmentDeclareProcedureEndThisMonth);
		ComparisonAttribute.statisticsTotal(countyLevelReportedEndThisMonth, villageTotalVos);
		ComparisonAttribute.statisticsTotal(countyEndThisMonthTotalVo, countyCreateEndThisMonthVo);
		ComparisonAttribute.statisticsTotal(countyEndThisMonthTotalVo,
				countyLevelReportedEndThisMonth);
		vos.add(countyEndThisMonthTotalVo);
		vos.add(countyCreateEndThisMonthVo);
		vos.add(countyLevelReportedEndThisMonth);
		vos.addAll(villageTotalVos);

		// 县级部门本月办理中
		vos.add(cumulativeStateCodeCount(
				assemblyMap(orgId, getFirstDayDate(0, 1, 0, accountReport),
						getLastDayDate(0, 1, 0, accountReport),
						ThreeRecordsIssueState.DEALING_CODE, accountType, targetInternalCode,
						orgParentId, null, sourceOrgId, targetOrgId, null), LedgerReportRow.COUNTY
						+ LedgerReportRow.DEPARTMENT + LedgerReportRow.THIS_MONTH
						+ LedgerReportRow.TRANSACTION + LedgerReportRow.COUNT));

		return vos;
	}

	/**
	 * 镇级月报表
	 * 
	 * @param accountReport
	 *            报表参数
	 * @param reportCountService
	 *            具体Service实现
	 * @param itemDicts
	 *            生成报表列的字典项
	 */
	public List<ThreeRecordsReportStatisticalVo> constructTownMouldContent(
			AccountReport accountReport) {
		List<ThreeRecordsReportStatisticalVo> vos = new ArrayList<ThreeRecordsReportStatisticalVo>();
		Long orgId = null;
		Integer accountType = accountReport.getAccountType();
		String nextLayer = ComparisonAttribute.getNextLayer(accountReport.getOrgLevelInternalId());// 下级
		String targetInternalCode = null;
		Long orgParentId = accountReport.getOrganization().getId();
		Long sourceOrgId = null;
		Long targetOrgId = null;

		/*** 县台账办 */
		Organization countyRecordHandleAffairs = getDepartmentOrganization(
				ThreeRecordsIssueConstants.COUNTY_LEDGER, accountReport.getOrganization()
						.getParentOrg().getId());
		if (null == countyRecordHandleAffairs) {
			countyRecordHandleAffairs = new Organization();
		}

		// 村级累计建账
		vos.add(cumulativeCreateCount(
				assemblyMap(orgId, null, null, null, accountType, targetInternalCode, orgParentId,
						null, sourceOrgId, targetOrgId, null), nextLayer
						+ LedgerReportRow.CUMULATIVE + LedgerReportRow.CREATE
						+ LedgerReportRow.COUNT));
		// 村级累计其中上年接转数
		List<String> statisticsRows = new ArrayList<String>();
		statisticsRows.add(LedgerReportRow.LAST_YEAR_PICK_UP_TURN);
		vos.add(cumulativeCreateTableTypeCount(
				assemblyMap(orgId, null, null, null, accountType, targetInternalCode, orgParentId,
						null, sourceOrgId, targetOrgId, null), nextLayer, statisticsRows).get(0));
		// 村级累计累计办结数、实质性、阶段性、程序性办、其中呈报乡镇数结数
		vos.addAll(cumulativeEndCount(
				assemblyMap(orgId, null, null, null, accountType, targetInternalCode, orgParentId,
						null, sourceOrgId, targetOrgId, null), nextLayer, false, true));
		// 村级累计上月办理
		vos.add(cumulativeStateCodeCount(
				assemblyMap(orgId, getFirstDayDate(0, 2, 0, accountReport),
						getLastDayDate(0, 2, 0, accountReport),
						ThreeRecordsIssueState.DEALING_CODE, accountType, targetInternalCode,
						orgParentId, null, sourceOrgId, targetOrgId, null), nextLayer
						+ LedgerReportRow.LAST_MONTH + LedgerReportRow.TRANSACTION
						+ LedgerReportRow.COUNT));
		// 村级累计本月建账
		vos.add(cumulativeCreateCount(
				assemblyMap(orgId, getFirstDayDate(0, 1, 0, accountReport),
						getLastDayDate(0, 1, 0, accountReport), null, accountType,
						targetInternalCode, orgParentId, null, sourceOrgId, targetOrgId, null),
				nextLayer + LedgerReportRow.THIS_MONTH + LedgerReportRow.CREATE
						+ LedgerReportRow.COUNT));

		// 村级累计本月累计办结数
		vos.addAll(cumulativeEndCount(
				assemblyMap(orgId, getFirstDayDate(0, 1, 0, accountReport),
						getLastDayDate(0, 1, 0, accountReport), null, accountType,
						targetInternalCode, orgParentId, null, sourceOrgId, targetOrgId, null),
				nextLayer, false, true));

		// 村级累计本月办理
		vos.add(cumulativeStateCodeCount(
				assemblyMap(orgId, getFirstDayDate(0, 1, 0, accountReport),
						getLastDayDate(0, 1, 0, accountReport),
						ThreeRecordsIssueState.DEALING_CODE, accountType, targetInternalCode,
						orgParentId, null, sourceOrgId, targetOrgId, null), nextLayer
						+ LedgerReportRow.THIS_MONTH + LedgerReportRow.TRANSACTION
						+ LedgerReportRow.COUNT));

		// 乡镇累计收集 合计
		nextLayer = LedgerReportRow.TOWN + LedgerReportRow.CUMULATIVE + LedgerReportRow.GATHER;
		ThreeRecordsReportStatisticalVo villageTotalVo = new ThreeRecordsReportStatisticalVo(
				nextLayer + LedgerReportRow.COMBINED);

		// 乡镇累计收集 村级呈报数小计
		ThreeRecordsReportStatisticalVo villageLevelReported = cumulativeOtherStateCodeCount(
				assemblyMap(
						assemblyMap(orgId, getFirstDayDate(0, 1, 0, accountReport),
								getLastDayDate(0, 1, 0, accountReport),
								ThreeRecordsIssueState.DEALING_CODE, accountType,
								targetInternalCode, orgParentId,
								ThreeRecordsIssueSourceState.submit, null, null, null),
						orgParentId, null, LedgerReportRow.ACCEPTANCE, null, null,
						LedgerReportRow.LEVEL_REPORTED, null, null, null), nextLayer
						+ LedgerReportRow.VILLAGE + LedgerReportRow.LEVEL_REPORTED
						+ LedgerReportRow.COUNT);
		// 乡镇累计收集 村级呈报其中上年接转数
		statisticsRows.clear();
		statisticsRows.add(LedgerReportRow.LAST_YEAR_PICK_UP_TURN);
		ThreeRecordsReportStatisticalVo villageLevelPickUpTurn = cumulativeCreateTableTypeCount(
				assemblyMap(orgId, null, null, null, accountType, targetInternalCode, orgParentId,
						null, sourceOrgId, targetOrgId, null),
				nextLayer + LedgerReportRow.AMONG_THEM + LedgerReportRow.VILLAGE, statisticsRows)
				.get(0);

		// 乡镇累计收集 乡镇建账数小计
		orgId = orgParentId;
		orgParentId = null;
		ThreeRecordsReportStatisticalVo townCreateCount = cumulativeCreateCount(
				assemblyMap(orgId, null, null, null, accountType, targetInternalCode, orgParentId,
						null, sourceOrgId, targetOrgId, null), nextLayer + LedgerReportRow.TOWN
						+ LedgerReportRow.CUMULATIVE + LedgerReportRow.CREATE
						+ LedgerReportRow.COUNT);

		// 乡镇累计收集 乡镇建账其中上年接转数
		statisticsRows.clear();
		statisticsRows.add(LedgerReportRow.LAST_YEAR_PICK_UP_TURN);
		ThreeRecordsReportStatisticalVo townLevelPickUpTurn = cumulativeCreateTableTypeCount(
				assemblyMap(orgId, null, null, null, accountType, targetInternalCode, orgParentId,
						null, sourceOrgId, targetOrgId, null),
				nextLayer + LedgerReportRow.AMONG_THEM + LedgerReportRow.TOWN, statisticsRows).get(
				0);
		// 乡镇累计收集 乡镇本级直接收集数
		statisticsRows.clear();
		statisticsRows.add(LedgerReportRow.DIRECT + LedgerReportRow.GATHER);
		ThreeRecordsReportStatisticalVo townDirectGather = cumulativeCreateTableTypeCount(
				assemblyMap(orgId, null, null, null, accountType, targetInternalCode, orgParentId,
						null, sourceOrgId, targetOrgId, null),
				nextLayer + LedgerReportRow.AMONG_THEM + LedgerReportRow.TOWN, statisticsRows).get(
				0);

		// 乡镇累计收集 县委县政府及县级领导班子有关领导交办数
		statisticsRows.clear();
		statisticsRows.add(LedgerReportRow.COUNTY_GOVERNMENT_LEADERSHIP + LedgerReportRow.ASSIGNED);
		ThreeRecordsReportStatisticalVo townCountyGovernmentLeadershipAssigned = cumulativeCreateTableTypeCount(
				assemblyMap(orgId, null, null, null, accountType, targetInternalCode, orgParentId,
						null, sourceOrgId, targetOrgId, null),
				nextLayer + LedgerReportRow.AMONG_THEM + LedgerReportRow.TOWN, statisticsRows).get(
				0);
		// 乡镇累计收集 县人大议案建议意见交办数
		statisticsRows.clear();
		statisticsRows.add(LedgerReportRow.COUNTY_NPC_LEGISLATION_SUGGESTIONS
				+ LedgerReportRow.ASSIGNED);
		ThreeRecordsReportStatisticalVo townCountyNPCLegislationSuggestionsAssigned = cumulativeCreateTableTypeCount(
				assemblyMap(orgId, null, null, null, accountType, targetInternalCode, orgParentId,
						null, sourceOrgId, targetOrgId, null),
				nextLayer + LedgerReportRow.AMONG_THEM + LedgerReportRow.TOWN, statisticsRows).get(
				0);
		// 乡镇累计收集 县政协提案建议意见交办数
		statisticsRows.clear();
		statisticsRows.add(LedgerReportRow.COUNTY_CPPCC_PROPOSAL_ADVICE_OPINION
				+ LedgerReportRow.ASSIGNED);
		ThreeRecordsReportStatisticalVo townCountyCPPCCProposalAdviceOpinionAssigned = cumulativeCreateTableTypeCount(
				assemblyMap(orgId, null, null, null, accountType, targetInternalCode, orgParentId,
						null, sourceOrgId, targetOrgId, null),
				nextLayer + LedgerReportRow.AMONG_THEM + LedgerReportRow.TOWN, statisticsRows).get(
				0);

		List<ThreeRecordsReportStatisticalVo> villageTotalVos = new ArrayList<ThreeRecordsReportStatisticalVo>();
		villageTotalVos.add(villageLevelReported);
		villageTotalVos.add(townCreateCount);
		villageTotalVos.add(townDirectGather);
		villageTotalVos.add(townCountyGovernmentLeadershipAssigned);
		villageTotalVos.add(townCountyNPCLegislationSuggestionsAssigned);
		villageTotalVos.add(townCountyCPPCCProposalAdviceOpinionAssigned);
		ComparisonAttribute.statisticsTotal(villageTotalVo, villageTotalVos);
		vos.add(villageTotalVo);
		vos.add(villageLevelReported);
		vos.add(villageLevelPickUpTurn);
		vos.add(townCreateCount);
		vos.add(townLevelPickUpTurn);
		vos.add(townDirectGather);
		vos.add(townCountyGovernmentLeadershipAssigned);
		vos.add(townCountyNPCLegislationSuggestionsAssigned);
		vos.add(townCountyCPPCCProposalAdviceOpinionAssigned);

		// 乡镇累计办结 合计
		nextLayer = LedgerReportRow.TOWN + LedgerReportRow.CUMULATIVE + LedgerReportRow.END;
		ThreeRecordsReportStatisticalVo villageTotalEndVo = new ThreeRecordsReportStatisticalVo(
				nextLayer + LedgerReportRow.COMBINED);
		// 乡镇累计办结 乡镇建账办结数
		ThreeRecordsReportStatisticalVo villageEndVo = cumulativeEndCount(
				assemblyMap(orgId, null, null, null, accountType, targetInternalCode, orgParentId,
						null, sourceOrgId, targetOrgId, null),
				nextLayer + LedgerReportRow.TOWN + LedgerReportRow.CREATE, false, true).get(0);

		// 乡镇累计办结 村级呈报件办结数
		ThreeRecordsReportStatisticalVo villageLevelReportedEnd = cumulativeStateCodeCount(
				assemblyMap(
						assemblyMap(orgParentId, null, null, ThreeRecordsIssueState.DEALING_CODE,
								accountType, targetInternalCode, orgId,
								ThreeRecordsIssueSourceState.submit, null, null, null), null, null,
						null, null, orgId, LedgerReportRow.LEVEL_REPORTED, null, null,
						LedgerReportRow.END), nextLayer + LedgerReportRow.VILLAGE
						+ LedgerReportRow.LEVEL_REPORTED + LedgerReportRow.END
						+ LedgerReportRow.COUNT);

		// 乡镇累计办结 其中 实质性办结
		ThreeRecordsReportStatisticalVo villageLevelReportedEssenceEnd = cumulativeStateCodeCount(
				assemblyMap(
						assemblyMap(orgParentId, null, null, ThreeRecordsIssueState.DEALING_CODE,
								accountType, targetInternalCode, orgId,
								ThreeRecordsIssueSourceState.submit, null, null, null), null, null,
						null, null, orgId, LedgerReportRow.LEVEL_REPORTED, null, null,
						LedgerReportRow.ESSENCE_SERCH + LedgerReportRow.END), nextLayer
						+ LedgerReportRow.VILLAGE + LedgerReportRow.LEVEL_REPORTED
						+ LedgerReportRow.ESSENCE + LedgerReportRow.END + LedgerReportRow.COUNT);
		// 乡镇累计办结 其中 阶段性办结
		ThreeRecordsReportStatisticalVo villageLevelReportedPhaseEnd = cumulativeStateCodeCount(
				assemblyMap(
						assemblyMap(orgParentId, null, null, ThreeRecordsIssueState.DEALING_CODE,
								accountType, targetInternalCode, orgId,
								ThreeRecordsIssueSourceState.submit, null, null, null), null, null,
						null, null, orgId, LedgerReportRow.LEVEL_REPORTED, null, null,
						LedgerReportRow.PHASE_SERCH + LedgerReportRow.END), nextLayer
						+ LedgerReportRow.VILLAGE + LedgerReportRow.LEVEL_REPORTED
						+ LedgerReportRow.PHASE + LedgerReportRow.END + LedgerReportRow.COUNT);
		// 乡镇累计办结 其中 程序性办结
		ThreeRecordsReportStatisticalVo villageLevelReportedProcedureEnd = cumulativeStateCodeCount(
				assemblyMap(
						assemblyMap(orgParentId, null, null, ThreeRecordsIssueState.DEALING_CODE,
								accountType, targetInternalCode, orgId,
								ThreeRecordsIssueSourceState.submit, null, null, null), null, null,
						null, null, orgId, LedgerReportRow.LEVEL_REPORTED, null, null,
						LedgerReportRow.PROCEDURE_SERCH + LedgerReportRow.END), nextLayer
						+ LedgerReportRow.VILLAGE + LedgerReportRow.LEVEL_REPORTED
						+ LedgerReportRow.PROCEDURE + LedgerReportRow.END + LedgerReportRow.COUNT);
		// 乡镇累计办结 其中乡镇建账呈报县台账办数
		ThreeRecordsReportStatisticalVo townLevelReportedRecordHandleAffairs = cumulativeStateCodeCount(
				assemblyMap(
						assemblyMap(orgId, null, null, ThreeRecordsIssueState.DEALING_CODE,
								accountType, targetInternalCode, orgParentId,
								ThreeRecordsIssueSourceState.submit, null, null, null),
						countyRecordHandleAffairs.getId(), null, LedgerReportRow.ACCEPTANCE, null,
						null, LedgerReportRow.LEVEL_REPORTED, null, null, null), nextLayer
						+ LedgerReportRow.AMONG_THEM + LedgerReportRow.TOWN
						+ LedgerReportRow.CREATE + LedgerReportRow.LEVEL_REPORTED
						+ LedgerReportFunctionalDepartments.RECORD_HANDLE_AFFAIRS
						+ LedgerReportRow.COUNT);

		// 乡镇累计办结 其中村级呈报件呈报县台账办数
		ThreeRecordsReportStatisticalVo villageLevelReportedRecordHandleAffairs = cumulativeStateCodeCount(
				assemblyMap(
						assemblyMap(orgParentId, null, null, ThreeRecordsIssueState.DEALING_CODE,
								accountType, targetInternalCode, orgId,
								ThreeRecordsIssueSourceState.submit, null, null, null),
						countyRecordHandleAffairs.getId(), null, LedgerReportRow.ACCEPTANCE, null,
						null, LedgerReportRow.LEVEL_REPORTED, null, null, null), nextLayer
						+ LedgerReportRow.AMONG_THEM + LedgerReportRow.VILLAGE
						+ LedgerReportRow.CREATE + LedgerReportRow.LEVEL_REPORTED
						+ LedgerReportFunctionalDepartments.RECORD_HANDLE_AFFAIRS
						+ LedgerReportRow.COUNT);

		ComparisonAttribute.statisticsTotal(villageLevelReportedEnd,
				townLevelReportedRecordHandleAffairs);
		ComparisonAttribute.statisticsTotal(villageLevelReportedEnd,
				villageLevelReportedRecordHandleAffairs);

		ComparisonAttribute.statisticsTotal(villageTotalEndVo, villageEndVo);
		ComparisonAttribute.statisticsTotal(villageTotalEndVo, villageLevelReportedEnd);

		vos.add(villageTotalEndVo);
		vos.add(villageEndVo);
		vos.add(villageLevelReportedEnd);
		vos.add(villageLevelReportedEssenceEnd);
		vos.add(villageLevelReportedPhaseEnd);
		vos.add(villageLevelReportedProcedureEnd);
		vos.add(townLevelReportedRecordHandleAffairs);
		vos.add(villageLevelReportedRecordHandleAffairs);

		// 乡镇上月办理中
		vos.add(cumulativeStateCodeCount(
				assemblyMap(orgId, getFirstDayDate(0, 2, 0, accountReport),
						getLastDayDate(0, 2, 0, accountReport),
						ThreeRecordsIssueState.DEALING_CODE, accountType, targetInternalCode,
						orgParentId, null, sourceOrgId, targetOrgId, null), LedgerReportRow.TOWN
						+ LedgerReportRow.LAST_MONTH + LedgerReportRow.TRANSACTION
						+ LedgerReportRow.COUNT));
		// 乡镇本月收集 合计
		nextLayer = LedgerReportRow.TOWN + LedgerReportRow.THIS_MONTH + LedgerReportRow.GATHER;
		ThreeRecordsReportStatisticalVo villageTotalThisMonthVo = new ThreeRecordsReportStatisticalVo(
				nextLayer + LedgerReportRow.COMBINED);

		// 乡镇本月收集 村级呈报数
		ThreeRecordsReportStatisticalVo villageLevelReportedVo = cumulativeStateCodeCount(
				assemblyMap(
						assemblyMap(orgParentId, getFirstDayDate(0, 1, 0, accountReport),
								getLastDayDate(0, 1, 0, accountReport),
								ThreeRecordsIssueState.DEALING_CODE, accountType,
								targetInternalCode, orgId, ThreeRecordsIssueSourceState.submit,
								null, null, null), null, orgId, LedgerReportRow.ACCEPTANCE, null,
						null, LedgerReportRow.LEVEL_REPORTED, null, null, null), nextLayer
						+ LedgerReportRow.VILLAGE + LedgerReportRow.LEVEL_REPORTED
						+ LedgerReportRow.COUNT);

		// 乡镇本月收集 乡镇建账数
		ThreeRecordsReportStatisticalVo townCreateCountThisMonth = new ThreeRecordsReportStatisticalVo(
				nextLayer + LedgerReportRow.TOWN + LedgerReportRow.CUMULATIVE
						+ LedgerReportRow.CREATE + LedgerReportRow.COUNT);

		// 乡镇本月收集 其中 乡镇本级直接收集数
		statisticsRows.clear();
		statisticsRows.add(LedgerReportRow.DIRECT + LedgerReportRow.GATHER);
		ThreeRecordsReportStatisticalVo townDirectGatherThisMonth = cumulativeCreateTableTypeCount(
				assemblyMap(orgId, getFirstDayDate(0, 1, 0, accountReport),
						getLastDayDate(0, 1, 0, accountReport), null, accountType,
						targetInternalCode, orgParentId, null, sourceOrgId, targetOrgId, null),
				nextLayer + LedgerReportRow.AMONG_THEM + LedgerReportRow.TOWN, statisticsRows).get(
				0);

		// 乡镇本月收集 其中 县委县政府及县级领导班子有关领导交办数
		statisticsRows.clear();
		statisticsRows.add(LedgerReportRow.COUNTY_GOVERNMENT_LEADERSHIP + LedgerReportRow.ASSIGNED);
		ThreeRecordsReportStatisticalVo townCountyGovernmentThisMonth = cumulativeCreateTableTypeCount(
				assemblyMap(orgId, getFirstDayDate(0, 1, 0, accountReport),
						getLastDayDate(0, 1, 0, accountReport), null, accountType,
						targetInternalCode, orgParentId, null, sourceOrgId, targetOrgId, null),
				nextLayer + LedgerReportRow.AMONG_THEM, statisticsRows).get(0);
		// 乡镇本月收集 其中 县人大议案建议意见交办数
		statisticsRows.clear();
		statisticsRows.add(LedgerReportRow.COUNTY_NPC_LEGISLATION_SUGGESTIONS
				+ LedgerReportRow.ASSIGNED);
		ThreeRecordsReportStatisticalVo townCountyNPCLegislationThisMonth = cumulativeCreateTableTypeCount(
				assemblyMap(orgId, getFirstDayDate(0, 1, 0, accountReport),
						getLastDayDate(0, 1, 0, accountReport), null, accountType,
						targetInternalCode, orgParentId, null, sourceOrgId, targetOrgId, null),
				nextLayer + LedgerReportRow.AMONG_THEM, statisticsRows).get(0);
		// 乡镇本月收集 其中 县政协提案建议意见交办数
		statisticsRows.clear();
		statisticsRows.add(LedgerReportRow.COUNTY_CPPCC_PROPOSAL_ADVICE_OPINION);
		ThreeRecordsReportStatisticalVo townCountyCPPCCProposalThisMonth = cumulativeCreateTableTypeCount(
				assemblyMap(orgId, getFirstDayDate(0, 1, 0, accountReport),
						getLastDayDate(0, 1, 0, accountReport), null, accountType,
						targetInternalCode, orgParentId, null, sourceOrgId, targetOrgId, null),
				nextLayer + LedgerReportRow.AMONG_THEM, statisticsRows).get(0);

		villageTotalVos.clear();
		villageTotalVos.add(townDirectGatherThisMonth);
		villageTotalVos.add(townCountyGovernmentThisMonth);
		villageTotalVos.add(townCountyNPCLegislationThisMonth);
		villageTotalVos.add(townCountyCPPCCProposalThisMonth);

		ComparisonAttribute.statisticsTotal(townCreateCountThisMonth, townDirectGatherThisMonth);
		ComparisonAttribute.statisticsTotal(villageTotalThisMonthVo, villageLevelReportedVo);
		ComparisonAttribute.statisticsTotal(villageTotalThisMonthVo, townCreateCountThisMonth);

		vos.add(villageTotalThisMonthVo);
		vos.add(villageLevelReportedVo);
		vos.add(townCreateCountThisMonth);
		vos.addAll(villageTotalVos);
		// 乡镇本月办结 合计
		nextLayer = LedgerReportRow.TOWN + LedgerReportRow.THIS_MONTH + LedgerReportRow.END;
		ThreeRecordsReportStatisticalVo villageTotalThisMonthEndVo = new ThreeRecordsReportStatisticalVo(
				nextLayer + LedgerReportRow.COMBINED);
		// 乡镇建账办结数
		ThreeRecordsReportStatisticalVo villageEndThisMonthVo = cumulativeEndCount(
				assemblyMap(orgId, getFirstDayDate(0, 1, 0, accountReport),
						getLastDayDate(0, 1, 0, accountReport), null, accountType,
						targetInternalCode, orgParentId, null, sourceOrgId, targetOrgId, null),
				nextLayer + LedgerReportRow.TOWN + LedgerReportRow.CREATE, false, true).get(0);
		// 乡镇本月办结 村级呈报件办结数
		ThreeRecordsReportStatisticalVo villageLevelReportedEndThisMonthVo = new ThreeRecordsReportStatisticalVo(
				nextLayer + LedgerReportRow.VILLAGE + LedgerReportRow.LEVEL_REPORTED
						+ LedgerReportRow.END + LedgerReportRow.COUNT);

		// 乡镇本月办结 其中 实质性办结数
		ThreeRecordsReportStatisticalVo villageLevelReportedEssenceEndThisMonthVo = cumulativeStateCodeCount(
				assemblyMap(
						assemblyMap(orgParentId, getFirstDayDate(0, 1, 0, accountReport),
								getLastDayDate(0, 1, 0, accountReport),
								ThreeRecordsIssueState.DEALING_CODE, accountType,
								targetInternalCode, orgId, ThreeRecordsIssueSourceState.submit,
								null, null, null), null, null, null, null, orgId,
						LedgerReportRow.LEVEL_REPORTED, null, null, LedgerReportRow.ESSENCE_SERCH
								+ LedgerReportRow.END), nextLayer + LedgerReportRow.VILLAGE
						+ LedgerReportRow.LEVEL_REPORTED + LedgerReportRow.ESSENCE
						+ LedgerReportRow.END + LedgerReportRow.COUNT);
		// 乡镇本月办结 其中 阶段性办结数
		ThreeRecordsReportStatisticalVo villageLevelReportedPhaseEndThisMonthVo = cumulativeStateCodeCount(
				assemblyMap(
						assemblyMap(orgParentId, getFirstDayDate(0, 1, 0, accountReport),
								getLastDayDate(0, 1, 0, accountReport),
								ThreeRecordsIssueState.DEALING_CODE, accountType,
								targetInternalCode, orgId, ThreeRecordsIssueSourceState.submit,
								null, null, null), null, null, null, null, orgId,
						LedgerReportRow.LEVEL_REPORTED, null, null, LedgerReportRow.PHASE_SERCH
								+ LedgerReportRow.END), nextLayer + LedgerReportRow.VILLAGE
						+ LedgerReportRow.LEVEL_REPORTED + LedgerReportRow.PHASE
						+ LedgerReportRow.END + LedgerReportRow.COUNT);
		// 乡镇本月办结 其中 程序性办结数
		ThreeRecordsReportStatisticalVo villageLevelReportedProcedureEndThisMonthVo = cumulativeStateCodeCount(
				assemblyMap(
						assemblyMap(orgParentId, getFirstDayDate(0, 1, 0, accountReport),
								getLastDayDate(0, 1, 0, accountReport),
								ThreeRecordsIssueState.DEALING_CODE, accountType,
								targetInternalCode, orgId, ThreeRecordsIssueSourceState.submit,
								null, null, null), null, null, null, null, orgId,
						LedgerReportRow.LEVEL_REPORTED, null, null, LedgerReportRow.PROCEDURE_SERCH
								+ LedgerReportRow.END), nextLayer + LedgerReportRow.VILLAGE
						+ LedgerReportRow.LEVEL_REPORTED + LedgerReportRow.PROCEDURE
						+ LedgerReportRow.END + LedgerReportRow.COUNT);
		// 乡镇本月办结 其中乡镇建账呈报县台账办数
		ThreeRecordsReportStatisticalVo townLevelReportedRecordHandleAffairsThisMonth = cumulativeStateCodeCount(
				assemblyMap(
						assemblyMap(orgId, getFirstDayDate(0, 1, 0, accountReport),
								getLastDayDate(0, 1, 0, accountReport),
								ThreeRecordsIssueState.DEALING_CODE, accountType,
								targetInternalCode, orgParentId,
								ThreeRecordsIssueSourceState.submit,
								countyRecordHandleAffairs.getId(), null, null),
						countyRecordHandleAffairs.getId(), null, LedgerReportRow.ACCEPTANCE, null,
						null, LedgerReportRow.LEVEL_REPORTED, null, null, null), nextLayer
						+ LedgerReportRow.AMONG_THEM + LedgerReportRow.TOWN
						+ LedgerReportRow.CREATE + LedgerReportRow.LEVEL_REPORTED
						+ LedgerReportFunctionalDepartments.RECORD_HANDLE_AFFAIRS
						+ LedgerReportRow.COUNT);

		// 乡镇本月办结 其中村级呈报件呈报县台账办数
		ThreeRecordsReportStatisticalVo villageLevelReportedRecordHandleAffairsThisMonth = cumulativeStateCodeCount(
				assemblyMap(
						assemblyMap(orgParentId, getFirstDayDate(0, 1, 0, accountReport),
								getLastDayDate(0, 1, 0, accountReport),
								ThreeRecordsIssueState.DEALING_CODE, accountType,
								targetInternalCode, orgId, ThreeRecordsIssueSourceState.submit,
								null, null, null), countyRecordHandleAffairs.getId(), null,
						LedgerReportRow.ACCEPTANCE, null, null, LedgerReportRow.LEVEL_REPORTED,
						null, null, null), nextLayer + LedgerReportRow.AMONG_THEM
						+ LedgerReportRow.VILLAGE + LedgerReportRow.CREATE
						+ LedgerReportRow.LEVEL_REPORTED
						+ LedgerReportFunctionalDepartments.RECORD_HANDLE_AFFAIRS
						+ LedgerReportRow.COUNT);
		villageTotalVos.clear();
		villageTotalVos.add(villageLevelReportedEssenceEndThisMonthVo);
		villageTotalVos.add(villageLevelReportedPhaseEndThisMonthVo);
		villageTotalVos.add(villageLevelReportedProcedureEndThisMonthVo);
		villageTotalVos.add(townLevelReportedRecordHandleAffairsThisMonth);
		villageTotalVos.add(villageLevelReportedRecordHandleAffairsThisMonth);

		ComparisonAttribute.statisticsTotal(villageLevelReportedEndThisMonthVo, villageTotalVos);
		ComparisonAttribute.statisticsTotal(villageTotalThisMonthEndVo, villageEndThisMonthVo);
		ComparisonAttribute.statisticsTotal(villageTotalThisMonthEndVo,
				villageLevelReportedEndThisMonthVo);
		vos.add(villageTotalThisMonthEndVo);
		vos.add(villageEndThisMonthVo);
		vos.add(villageLevelReportedEndThisMonthVo);
		vos.addAll(villageTotalVos);

		// 乡镇本月办理中
		vos.add(cumulativeStateCodeCount(
				assemblyMap(orgId, getFirstDayDate(0, 1, 0, accountReport),
						getLastDayDate(0, 1, 0, accountReport),
						ThreeRecordsIssueState.DEALING_CODE, accountType, targetInternalCode,
						orgParentId, null, sourceOrgId, targetOrgId, null), LedgerReportRow.TOWN
						+ LedgerReportRow.THIS_MONTH + LedgerReportRow.TRANSACTION
						+ LedgerReportRow.COUNT));
		return vos;
	}

	/**
	 * 村、社区月报表
	 * 
	 * @param accountReport
	 *            报表参数
	 * @param reportCountService
	 *            具体Service实现
	 * @param itemDicts
	 *            生成报表列的字典项
	 */
	public List<ThreeRecordsReportStatisticalVo> constructVillageMouldContent(
			AccountReport accountReport) {
		List<ThreeRecordsReportStatisticalVo> vos = new ArrayList<ThreeRecordsReportStatisticalVo>();
		Long orgId = accountReport.getOrganization().getId();
		Integer accountType = accountReport.getAccountType();
		String targetInternalCode = null;
		String nextLayer = "";
		Long orgParentId = null;
		boolean isOnlyAll = false;
		boolean isStatisticalSubmits = true;
		Long sourceOrgId = null;
		Long targetOrgId = null;

		// 累计建账
		vos.add(cumulativeCreateCount(
				assemblyMap(orgId, null, null, null, accountType, targetInternalCode, orgParentId,
						null, sourceOrgId, targetOrgId, null), LedgerReportRow.CUMULATIVE
						+ LedgerReportRow.CREATE + LedgerReportRow.COUNT));
		// 其中上年接转数
		List<String> statisticsRows = new ArrayList<String>();
		statisticsRows.add(LedgerReportRow.LAST_YEAR_PICK_UP_TURN);
		vos.add(cumulativeCreateTableTypeCount(
				assemblyMap(orgId, null, null, null, accountType, targetInternalCode, orgParentId,
						null, sourceOrgId, targetOrgId, null), nextLayer, statisticsRows).get(0));

		// 累计办结数
		vos.addAll(cumulativeEndCount(
				assemblyMap(orgId, null, null, null, accountType, targetInternalCode, orgParentId,
						null, sourceOrgId, targetOrgId, null), nextLayer
						+ LedgerReportRow.CUMULATIVE, isOnlyAll, isStatisticalSubmits));
		// 上月办理中
		vos.add(cumulativeStateCodeCount(
				assemblyMap(orgId, getFirstDayDate(0, 2, 0, accountReport),
						getLastDayDate(0, 2, 0, accountReport),
						ThreeRecordsIssueState.DEALING_CODE, accountType, targetInternalCode,
						orgParentId, null, sourceOrgId, targetOrgId, null),
				LedgerReportRow.LAST_MONTH + LedgerReportRow.TRANSACTION + LedgerReportRow.COUNT));
		// 本月建账
		vos.add(cumulativeCreateCount(
				assemblyMap(orgId, getFirstDayDate(0, 1, 0, accountReport),
						getLastDayDate(0, 1, 0, accountReport), null, accountType,
						targetInternalCode, orgParentId, null, sourceOrgId, targetOrgId, null),
				LedgerReportRow.THIS_MONTH + LedgerReportRow.CREATE + LedgerReportRow.COUNT));

		// 计本月办结数
		vos.addAll(cumulativeEndCount(
				assemblyMap(orgId, getFirstDayDate(0, 1, 0, accountReport),
						getLastDayDate(0, 1, 0, accountReport), null, accountType,
						targetInternalCode, orgParentId, null, sourceOrgId, targetOrgId, null),
				nextLayer + LedgerReportRow.THIS_MONTH, isOnlyAll, isStatisticalSubmits));

		// 计算本月办理中
		vos.add(cumulativeStateCodeCount(
				assemblyMap(orgId, getFirstDayDate(0, 1, 0, accountReport),
						getLastDayDate(0, 1, 0, accountReport),
						ThreeRecordsIssueState.DEALING_CODE, accountType, targetInternalCode,
						orgParentId, null, sourceOrgId, targetOrgId, null), nextLayer
						+ LedgerReportRow.THIS_MONTH + LedgerReportRow.TRANSACTION
						+ LedgerReportRow.COUNT));

		return vos;
	}

	/**
	 * 计算建表类型数
	 * 
	 * @param orgId
	 * @param orgId
	 */
	private List<ThreeRecordsReportStatisticalVo> cumulativeCreateTableTypeCount(
			Map<String, Object> serchMap, String rowKey, List<String> statisticsRows) {
		return getCumulativeCreateTableTypeCounts(serchMap, rowKey, statisticsRows);
	}

	/**
	 * 计算累计建账
	 * 
	 * @param orgId
	 * @param orgId
	 */
	private ThreeRecordsReportStatisticalVo cumulativeCreateCount(Map<String, Object> serchMap,
			String rowKey) {
		return getReportGroupCount(serchMap, new ThreeRecordsReportStatisticalVo(rowKey));
	}

	/**
	 * 计算累计办结数(包括其它办结)
	 * 
	 * @param orgId
	 * @param orgId
	 */
	private List<ThreeRecordsReportStatisticalVo> cumulativeEndCount(Map<String, Object> serchMap,
			String rowKey, boolean isOnlyAll, boolean isStatisticalSubmits) {
		return getCumulativeEndCounts(serchMap, rowKey,
				LedgerReportRow.END + LedgerReportRow.COUNT, isOnlyAll, isStatisticalSubmits);
	}

	/**
	 * 计算各种状态台账数
	 * 
	 * @param orgId
	 * @param orgId
	 */
	private ThreeRecordsReportStatisticalVo cumulativeStateCodeCount(Map<String, Object> serchMap,
			String rowKey) {
		return getReportAccountStepsCount(serchMap, new ThreeRecordsReportStatisticalVo(rowKey));

	}

	/**
	 * 计算需要受理后按状态统计
	 * 
	 * @param serchMap
	 * @param rowKey
	 * @return
	 */
	private ThreeRecordsReportStatisticalVo cumulativeOtherStateCodeCount(
			Map<String, Object> serchMap, String rowKey) {
		return getReportAccountOtherStepsCount(serchMap,
				new ThreeRecordsReportStatisticalVo(rowKey));
	}

	/**
	 * 计算累计办结数(包括其它办结)按建账类型
	 * 
	 * @param orgId
	 * @param orgId
	 */
	private List<ThreeRecordsReportStatisticalVo> getCumulativeCreateTableTypeCounts(
			Map<String, Object> searchMap, String rowKey, List<String> statisticsRows) {
		if (null == searchMap) {
			throw new BusinessValidationException("查询条件未获得");
		}
		Map<String, ThreeRecordsReportStatisticalVo> voMap = new HashMap<String, ThreeRecordsReportStatisticalVo>();
		Map<String, List<LedgerReportGroupCount>> createTableTypeMap = new HashMap<String, List<LedgerReportGroupCount>>();
		getCumulativeCreateTableTypeVoMap(statisticsRows, rowKey, voMap, createTableTypeMap);
		Integer accountType = Integer.valueOf(searchMap.get("ledgerType").toString());
		if (isAllReportType(accountType)) {
			// 民生
			searchMap.put("ledgerType", LedgerConstants.PEOPLEASPIRATION);
			List<LedgerReportGroupCount> peopleList = getReportStateCodeCount(searchMap);
			getCumulativeCreateTableTypeCount(peopleList, voMap, LedgerConstants.PEOPLEASPIRATION,
					initCreateTableTypeMap(createTableTypeMap));
			// 困难
			searchMap.put("ledgerType", LedgerConstants.POORPEOPLE);
			List<LedgerReportGroupCount> poorPeopleList = getReportStateCodeCount(searchMap);
			getCumulativeCreateTableTypeCount(poorPeopleList, voMap, LedgerConstants.POORPEOPLE,
					initCreateTableTypeMap(createTableTypeMap));
			// 稳定
			searchMap.put("ledgerType", LedgerConstants.STEADYWORK);
			List<LedgerReportGroupCount> steadyWorkList = getReportStateCodeCount(searchMap);
			getCumulativeCreateTableTypeCount(steadyWorkList, voMap, LedgerConstants.STEADYWORK,
					initCreateTableTypeMap(createTableTypeMap));
		} else {
			List<LedgerReportGroupCount> list = getReportStateCodeCount(searchMap);
			getCumulativeCreateTableTypeCount(list, voMap, accountType,
					initCreateTableTypeMap(createTableTypeMap));
		}
		List<ThreeRecordsReportStatisticalVo> vos = new ArrayList<ThreeRecordsReportStatisticalVo>();
		for (Map.Entry<String, ThreeRecordsReportStatisticalVo> entry : voMap.entrySet()) {
			if (!entry.getKey().equals(
					LedgerReportRow.CUMULATIVE + LedgerReportRow.CREATE + LedgerReportRow.COUNT)) {
				calculateTotal(entry.getValue());
				vos.add(entry.getValue());
			}
		}
		return vos;
	}

	private void getCumulativeCreateTableTypeVoMap(List<String> statisticsRows, String rowKey,
			Map<String, ThreeRecordsReportStatisticalVo> voMap,
			Map<String, List<LedgerReportGroupCount>> createTableTypeMap) {
		List<PropertyDict> createTableTypeDicts = propertyDictDubboService
				.findPropertyDictByDomainName(PropertyTypes.LEDGER_PEOPLEASPIRATION_CREATE_TABLE_TYPE);
		boolean isAll = statisticsRows == null || statisticsRows.size() <= 0 ? true : false;
		String statisticsRow = "";
		for (int i = 0; i < statisticsRows.size() || isAll; i++) {
			if (!isAll) {
				statisticsRow = statisticsRows.get(i);
			}
			for (PropertyDict dict : createTableTypeDicts) {
				String disName = dict.getDisplayName();
				if (disName.indexOf(LedgerReportRow.NEW) > -1) {
					disName = LedgerReportRow.DIRECT + LedgerReportRow.GATHER;
				}
				if (isAll || disName.indexOf(statisticsRow) > -1) {
					voMap.put(disName, new ThreeRecordsReportStatisticalVo(rowKey + disName
							+ LedgerReportRow.COUNT));
				} else {
					continue;
				}
				createTableTypeMap.put(disName, new ArrayList<LedgerReportGroupCount>());
			}
			isAll = false;
		}
	}

	private Map<String, List<LedgerReportGroupCount>> initCreateTableTypeMap(
			Map<String, List<LedgerReportGroupCount>> createTableTypeMap) {
		for (Map.Entry<String, List<LedgerReportGroupCount>> entry : createTableTypeMap.entrySet()) {
			entry.getValue().clear();
		}
		return createTableTypeMap;
	}

	private Map<String, ThreeRecordsReportStatisticalVo> getCumulativeCreateTableTypeCount(
			List<LedgerReportGroupCount> list, Map<String, ThreeRecordsReportStatisticalVo> map,
			Integer ledgerType, Map<String, List<LedgerReportGroupCount>> createTableTypeMap) {
		// 建账数
		for (LedgerReportGroupCount ledgerReportGroupCount : list) {
			String rowTypeName = ledgerReportGroupCount.getRowType().getDisplayName();
			if (rowTypeName.indexOf(LedgerReportRow.NEW) > -1) {
				rowTypeName = LedgerReportRow.DIRECT + LedgerReportRow.GATHER;
			}
			if (null != ledgerReportGroupCount.getRowType()) {
				List<LedgerReportGroupCount> createTableTypeList = createTableTypeMap
						.get(rowTypeName);
				if (null != createTableTypeList) {
					createTableTypeList.add(ledgerReportGroupCount);
				}
			}
		}
		for (Map.Entry<String, List<LedgerReportGroupCount>> entry : createTableTypeMap.entrySet()) {
			ComparisonAttribute.calculationRatioVo(entry.getValue(), map.get(entry.getKey()),
					ledgerType);
		}
		return map;
	}

	private ThreeRecordsReportStatisticalVo getReportAccountStepsCount(
			Map<String, Object> searchMap, ThreeRecordsReportStatisticalVo vo) {
		if (null == searchMap) {
			throw new BusinessValidationException("查询条件未获得");
		}
		Integer accountType = Integer.valueOf(searchMap.get("ledgerType").toString());
		if (isAllReportType(accountType)) {
			// 民生
			searchMap.put("ledgerType", LedgerConstants.PEOPLEASPIRATION);
			ComparisonAttribute.calculationRatioVo(getReportAccountStepsCount(searchMap), vo,
					LedgerConstants.PEOPLEASPIRATION);
			// 困难
			searchMap.put("ledgerType", LedgerConstants.POORPEOPLE);
			ComparisonAttribute.calculationRatioVo(getReportAccountStepsCount(searchMap), vo,
					LedgerConstants.POORPEOPLE);
			// 稳定
			searchMap.put("ledgerType", LedgerConstants.STEADYWORK);
			ComparisonAttribute.calculationRatioVo(getReportAccountStepsCount(searchMap), vo,
					LedgerConstants.STEADYWORK);
		} else {
			ComparisonAttribute.calculationRatioVo(getReportAccountStepsCount(searchMap), vo,
					accountType);
		}
		calculateTotal(vo);
		return vo;
	}

	private ThreeRecordsReportStatisticalVo getReportAccountOtherStepsCount(
			Map<String, Object> searchMap, ThreeRecordsReportStatisticalVo vo) {
		if (null == searchMap) {
			throw new BusinessValidationException("查询条件未获得");
		}
		Integer accountType = Integer.valueOf(searchMap.get("ledgerType").toString());
		if (isAllReportType(accountType)) {
			// 民生
			searchMap.put("ledgerType", LedgerConstants.PEOPLEASPIRATION);
			ComparisonAttribute.calculationRatioVo(getReportStateCodeCount(searchMap), vo,
					LedgerConstants.PEOPLEASPIRATION);
			// 困难
			searchMap.put("ledgerType", LedgerConstants.POORPEOPLE);
			ComparisonAttribute.calculationRatioVo(getReportStateCodeCount(searchMap), vo,
					LedgerConstants.POORPEOPLE);
			// 稳定
			searchMap.put("ledgerType", LedgerConstants.STEADYWORK);
			ComparisonAttribute.calculationRatioVo(getReportStateCodeCount(searchMap), vo,
					LedgerConstants.STEADYWORK);
		} else {
			ComparisonAttribute.calculationRatioVo(getReportStateCodeCount(searchMap), vo,
					accountType);
		}
		calculateTotal(vo);
		return vo;
	}

	/**
	 * 计算累计办结数(包括其它办结)
	 * 
	 * @param orgId
	 * @param orgId
	 */
	private List<ThreeRecordsReportStatisticalVo> getCumulativeEndCounts(
			Map<String, Object> searchMap, String rowKey, String lastRowKey, boolean isOnlyAll,
			boolean isStatisticalSubmits) {
		if (null == searchMap) {
			throw new BusinessValidationException("查询条件未获得");
		}
		Map<String, ThreeRecordsReportStatisticalVo> voMap = getVoMapEnd(rowKey, lastRowKey,
				isOnlyAll);
		// 其中程报乡镇数
		voMap.put(LedgerReportRow.AMONG_THEM + LedgerReportRow.REPORT_VILLAGES
				+ LedgerReportRow.COUNT, new ThreeRecordsReportStatisticalVo(rowKey
				+ LedgerReportRow.AMONG_THEM + LedgerReportRow.REPORT_VILLAGES
				+ LedgerReportRow.COUNT));

		Integer accountType = Integer.valueOf(searchMap.get("ledgerType").toString());
		if (isAllReportType(accountType)) {
			// 民生
			searchMap.put("ledgerType", LedgerConstants.PEOPLEASPIRATION);
			Map<String, List<LedgerReportGroupCount>> peopleListMap = getEndCount(searchMap,
					isOnlyAll, isStatisticalSubmits);
			getCumulativeEndCount(peopleListMap, voMap, LedgerConstants.PEOPLEASPIRATION,
					isOnlyAll, isStatisticalSubmits);
			// 困难
			searchMap.put("ledgerType", LedgerConstants.POORPEOPLE);
			Map<String, List<LedgerReportGroupCount>> poorPeopleListMap = getEndCount(searchMap,
					isOnlyAll, isStatisticalSubmits);
			getCumulativeEndCount(poorPeopleListMap, voMap, LedgerConstants.POORPEOPLE, isOnlyAll,
					isStatisticalSubmits);
			// 稳定
			searchMap.put("ledgerType", LedgerConstants.STEADYWORK);
			Map<String, List<LedgerReportGroupCount>> steadyWorkListMap = getEndCount(searchMap,
					isOnlyAll, isStatisticalSubmits);
			getCumulativeEndCount(steadyWorkListMap, voMap, LedgerConstants.STEADYWORK, isOnlyAll,
					isStatisticalSubmits);
		} else {
			Map<String, List<LedgerReportGroupCount>> peopleListMap = getEndCount(searchMap,
					isOnlyAll, isStatisticalSubmits);
			getCumulativeEndCount(peopleListMap, voMap, accountType, isOnlyAll,
					isStatisticalSubmits);
		}
		return assemblyVos(voMap);
	}

	private Map<String, List<LedgerReportGroupCount>> getEndCount(Map<String, Object> searchMap,
			boolean isOnlyAll, boolean isStatisticalSubmits) {
		Map<String, List<LedgerReportGroupCount>> map = new HashMap<String, List<LedgerReportGroupCount>>();
		searchMap.remove("acceptanceStateCode");
		searchMap.remove("levelReportedStateCode");
		// 实质性办结数
		searchMap.put("endStateCode", LedgerReportRow.ESSENCE_SERCH + LedgerReportRow.END);
		map.put(LedgerReportRow.ESSENCE + LedgerReportRow.END, getReportStateCodeCount(searchMap));
		// 阶段性办结数
		searchMap.put("endStateCode", LedgerReportRow.PHASE_SERCH + LedgerReportRow.END);
		map.put(LedgerReportRow.PHASE + LedgerReportRow.END, getReportStateCodeCount(searchMap));
		// 程序性办结数
		searchMap.put("endStateCode", LedgerReportRow.PROCEDURE_SERCH + LedgerReportRow.END);
		map.put(LedgerReportRow.PROCEDURE + LedgerReportRow.END, getReportStateCodeCount(searchMap));
		// 累计办结数
		List<LedgerReportGroupCount> cumulativeEnds = new ArrayList<LedgerReportGroupCount>();
		for (Entry<String, List<LedgerReportGroupCount>> entry : map.entrySet()) {
			cumulativeEnds.addAll(entry.getValue());
		}
		if (isStatisticalSubmits) {
			searchMap.remove("endStateCode");
			// 其中程报乡镇数
			searchMap.put("acceptanceStateCode", LedgerReportRow.ACCEPTANCE);
			searchMap.put("levelReportedStateCode", LedgerReportRow.LEVEL_REPORTED);
			map.put(LedgerReportRow.AMONG_THEM + LedgerReportRow.REPORT_VILLAGES
					+ LedgerReportRow.COUNT, getReportStateCodeCount(searchMap));
		}
		if (isOnlyAll) {
			map.clear();
		}
		if (!isStatisticalSubmits) {
			map.remove(LedgerReportRow.AMONG_THEM + LedgerReportRow.REPORT_VILLAGES
					+ LedgerReportRow.COUNT);
		}
		map.put(LedgerReportRow.CUMULATIVE + LedgerReportRow.END + LedgerReportRow.COUNT,
				cumulativeEnds);
		return map;
	}

	/**
	 * 统计list中的各种办结数
	 * 
	 * @param list
	 *            源数据
	 * @param map
	 *            存储数据
	 * @param ledgerType
	 *            数据类型
	 * @param isOnlyAll
	 *            是否只统计所有
	 * @param isStatisticalSubmits
	 *            是否统计上报
	 * @return
	 */
	private Map<String, ThreeRecordsReportStatisticalVo> getCumulativeEndCount(
			Map<String, List<LedgerReportGroupCount>> peopleListMap,
			Map<String, ThreeRecordsReportStatisticalVo> map, Integer ledgerType,
			boolean isOnlyAll, boolean isStatisticalSubmits) {

		// 累计办结数
		ComparisonAttribute.calculationRatioVo(
				peopleListMap.get(LedgerReportRow.CUMULATIVE + LedgerReportRow.END
						+ LedgerReportRow.COUNT),
				map.get(LedgerReportRow.CUMULATIVE + LedgerReportRow.END + LedgerReportRow.COUNT),
				ledgerType);
		if (!isOnlyAll) {
			// 实质性办结数
			ComparisonAttribute.calculationRatioVo(
					peopleListMap.get(LedgerReportRow.ESSENCE + LedgerReportRow.END),
					map.get(LedgerReportRow.ESSENCE), ledgerType);
			// 阶段性办结数
			ComparisonAttribute.calculationRatioVo(
					peopleListMap.get(LedgerReportRow.PHASE + LedgerReportRow.END),
					map.get(LedgerReportRow.PHASE), ledgerType);
			// 程序性办结数
			ComparisonAttribute.calculationRatioVo(
					peopleListMap.get(LedgerReportRow.PROCEDURE + LedgerReportRow.END),
					map.get(LedgerReportRow.PROCEDURE), ledgerType);
			if (isStatisticalSubmits) {
				// 其中程报乡镇数
				ComparisonAttribute.calculationRatioVo(
						peopleListMap.get(LedgerReportRow.AMONG_THEM
								+ LedgerReportRow.REPORT_VILLAGES + LedgerReportRow.COUNT),
						map.get(LedgerReportRow.AMONG_THEM + LedgerReportRow.REPORT_VILLAGES
								+ LedgerReportRow.COUNT), ledgerType);
			}
		}
		return map;
	}

	private ThreeRecordsReportStatisticalVo getReportGroupCount(Map<String, Object> searchMap,
			ThreeRecordsReportStatisticalVo vo) {
		if (null == searchMap) {
			throw new BusinessValidationException("查询条件未获得");
		}
		Integer accountType = Integer.valueOf(searchMap.get("ledgerType").toString());
		if (isAllReportType(accountType)) {
			// 民生
			searchMap.put("ledgerType", LedgerConstants.PEOPLEASPIRATION);
			ComparisonAttribute.calculationRatioVo(getReportStateCodeCount(searchMap), vo,
					LedgerConstants.PEOPLEASPIRATION);
			// 困难
			searchMap.put("ledgerType", LedgerConstants.POORPEOPLE);
			ComparisonAttribute.calculationRatioVo(getReportStateCodeCount(searchMap), vo,
					LedgerConstants.POORPEOPLE);
			// 稳定
			searchMap.put("ledgerType", LedgerConstants.STEADYWORK);
			ComparisonAttribute.calculationRatioVo(getReportStateCodeCount(searchMap), vo,
					LedgerConstants.STEADYWORK);
		} else {
			ComparisonAttribute.calculationRatioVo(getReportStateCodeCount(searchMap), vo,
					accountType);
		}
		calculateTotal(vo);
		return vo;
	}

	private List<ThreeRecordsReportStatisticalVo> assemblyVos(
			Map<String, ThreeRecordsReportStatisticalVo> voMap) {
		List<ThreeRecordsReportStatisticalVo> vos = new ArrayList<ThreeRecordsReportStatisticalVo>();
		if (null == voMap) {
			return vos;
		}
		List<String> rowKeys = LedgerReportRow.rows;
		for (String rowKey : rowKeys) {
			ThreeRecordsReportStatisticalVo vo = voMap.get(rowKey);
			if (null != vo) {
				calculateTotal(vo);
				vos.add(vo);
			}
		}
		return vos;
	}

	private ThreeRecordsReportStatisticalVo calculateTotal(ThreeRecordsReportStatisticalVo vo) {
		Integer peopleAsWorkTotal = null == vo.getPeopleAsWorkTotal() ? 0 : vo
				.getPeopleAsWorkTotal();
		Integer ledgerPoorPeopleTotal = null == vo.getLedgerPoorPeopleTotal() ? 0 : vo
				.getLedgerPoorPeopleTotal();
		Integer steadyRecordWorkTotal = null == vo.getSteadyRecordWorkTotal() ? 0 : vo
				.getSteadyRecordWorkTotal();
		vo.setTotal(peopleAsWorkTotal + ledgerPoorPeopleTotal + steadyRecordWorkTotal);
		return vo;
	}

	/**
	 * 获取三种办结VO
	 * 
	 * @param rowKey
	 * @return
	 */
	private Map<String, ThreeRecordsReportStatisticalVo> getVoMapEnd(String rowKey,
			String lastRowKey, boolean isOnlyAll) {
		Map<String, ThreeRecordsReportStatisticalVo> voMap = new HashMap<String, ThreeRecordsReportStatisticalVo>();
		// 累计办结数
		voMap.put(LedgerReportRow.CUMULATIVE + LedgerReportRow.END + LedgerReportRow.COUNT,
				new ThreeRecordsReportStatisticalVo(rowKey + lastRowKey));
		if (!isOnlyAll) {
			// 实质性办结数
			voMap.put(LedgerReportRow.ESSENCE, new ThreeRecordsReportStatisticalVo(rowKey
					+ LedgerReportRow.ESSENCE + lastRowKey));
			// 阶段性办结数
			voMap.put(LedgerReportRow.PHASE, new ThreeRecordsReportStatisticalVo(rowKey
					+ LedgerReportRow.PHASE + lastRowKey));
			// 程序性办结数
			voMap.put(LedgerReportRow.PROCEDURE, new ThreeRecordsReportStatisticalVo(rowKey
					+ LedgerReportRow.PROCEDURE + lastRowKey));
		}
		return voMap;
	}

	/**
	 * 根据参数组装Map
	 * 
	 * @param orgId
	 *            机构ID
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param stateCode
	 *            台账状态码
	 * @param accountType
	 *            台账类型
	 * @param targetInternalCode
	 *            目标处理部门 机构CODE
	 * @param orgParentId
	 *            机构父ID
	 * @param submit
	 *            是否提交（上报呈报）
	 * @param sourceOrgId
	 *            源处理部门机构ID
	 * @param targetOrgId
	 *            目标处理部门机构ID
	 * @return
	 */
	private Map<String, Object> assemblyMap(Long orgId, Date startDate, Date endDate,
			Integer stateCode, Integer accountType, String targetInternalCode, Long orgParentId,
			Integer submit, Long sourceOrgId, Long targetOrgId, Integer assign) {
		Integer isSearchOrg = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != orgId && 0 != orgId) {
			map.put("orgId", orgId);
		}
		if (null != startDate) {
			map.put("startDate", startDate);
		}
		if (null != endDate) {
			map.put("endDate", endDate);
		}
		if (null != stateCode) {
			map.put("stateCode", stateCode);
		}
		if (null != accountType) {
			map.put("ledgerType", accountType);
		}
		if (null != orgParentId) {
			isSearchOrg = 1;
			map.put("orgParentId", orgParentId);
			Long orgType = propertyDictDubboService.getPropertyDictByDomainName(
					OrganizationType.ADMINISTRATIVE_KEY).getId();
			if (null != orgType) {
				map.put("orgType", orgType);
			}
		}
		if (!StringUtils.isEmpty(targetInternalCode)) {
			map.put("targetInternalCode", targetInternalCode);
		}
		if (null != submit) {
			map.put("submit", submit);
		}
		if (null != sourceOrgId) {
			map.put("source", sourceOrgId);
		}
		if (null != targetOrgId) {
			map.put("target", targetOrgId);
		}
		if (null != assign) {
			map.put("assign", assign);
		}
		if (isSearchOrg == 1) {
			map.put("isSearchOrg", isSearchOrg);
		}
		return map;
	}

	/**
	 * 
	 * @param map
	 * @param acceptanceDeal
	 *            受理-处理机构ID
	 * @param acceptanceTarget
	 *            受理-目标机构ID
	 * @param acceptanceStateCode
	 *            受理CODE
	 * @param levelReportedDeal
	 *            上报、交办等 -处理机构ID 6
	 * @param levelReportedTarget
	 *            上报、交办等 -目标机构ID 5 6上报5
	 * @param levelReportedStateCode
	 *            上报、交办等CODE
	 * @param endDeal
	 *            办结-处理机构ID
	 * @param endTarget
	 *            办结-目标机构ID
	 * @param endStateCode
	 *            办结CODE
	 * @return
	 */
	private Map<String, Object> assemblyMap(Map<String, Object> map, Long acceptanceDeal,
			Long acceptanceTarget, String acceptanceStateCode, Long levelReportedDeal,
			Long levelReportedTarget, String levelReportedStateCode, Long endDeal, Long endTarget,
			String endStateCode) {
		if (null != acceptanceDeal) {
			map.put("acceptanceDeal", acceptanceDeal);
		}
		if (null != acceptanceTarget) {
			map.put("acceptanceTarget", acceptanceTarget);
		}
		if (null != acceptanceStateCode) {
			map.put("acceptanceStateCode", LedgerReportRow.ACCEPTANCE);
		}
		if (null != levelReportedDeal) {
			map.put("levelReportedDeal", levelReportedDeal);
		}
		if (null != levelReportedTarget) {
			map.put("levelReportedTarget", levelReportedTarget);
		}
		if (null != levelReportedStateCode) {
			map.put("levelReportedStateCode", levelReportedStateCode);
		}
		if (null != endDeal) {
			map.put("endDeal", endDeal);
		}
		if (null != endTarget) {
			map.put("endTarget", endTarget);
		}
		if (null != endStateCode) {
			map.put("endStateCode", endStateCode);
		}
		return map;
	}

	@Override
	public String judgeReportType(AccountReport accountReport) {
		if (accountReport == null || accountReport.getOrganization() == null
				|| accountReport.getOrganization().getId() == null
				|| accountReport.getOrgLevelInternalId() == null) {
			throw new BusinessValidationException("参数错误");
		}
		Integer orgLevel = accountReport.getOrgLevelInternalId();
		// 村
		if (ComparisonAttribute.isVillageOrganization(orgLevel)) {
			return LedgerReportType.MONTH_REPORT_VILLAGE;
		}
		if (ComparisonAttribute.isTownOrganization(orgLevel)) {
			return LedgerReportType.MONTH_REPORT_TOWN;
		}
		if (ComparisonAttribute.isDistrictOrganization(orgLevel)) {
			if (ComparisonAttribute.isRecordHandleAffairs(accountReport.getOrganization())) {
				return LedgerReportType.MONTH_REPORT
						+ LedgerReportType.MONTH_REPORT_COUNTY_DEPARTMENT;
			}
			return LedgerReportType.MONTH_REPORT + LedgerReportType.MONTH_REPORT_COUNTY;
		}
		return LedgerReportFunctionalDepartments.NO_RESULTS_TYPE;
	}

	public Integer getOrgType(AccountReport accountReport) {
		if (accountReport == null || accountReport.getOrganization() == null
				|| accountReport.getOrganization().getId() == null
				|| accountReport.getOrgLevelInternalId() == null) {
			throw new BusinessValidationException("参数错误");
		}
		Integer orgLevel = accountReport.getOrgLevelInternalId();
		// 村
		if (ComparisonAttribute.isVillageOrganization(orgLevel)) {
			return LedgerReportType.REPORT_VILLAGE;
		}
		if (ComparisonAttribute.isTownOrganization(orgLevel)) {
			return LedgerReportType.REPORT_TOWN;
		}
		if (ComparisonAttribute.isDistrictOrganization(orgLevel)) {
			if (isCountyDepartment(accountReport.getOrganization())) {
				return LedgerReportType.REPORT_COUNTY_DEPARTMENT;
			}
			return LedgerReportType.REPORT_COUNTY;
		}
		return null;
	}

	@Override
	public List<ThreeRecordsReportStatisticalVo> findHomePageAccountReportVo(
			AccountReport accountReport) {
		if (accountReport == null || accountReport.getOrganization() == null
				|| accountReport.getOrganization().getId() == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			return dispatchByLedgerConstantsAndReportType(accountReport);
		} catch (Exception e) {
//			throw new ServiceValidationException(
//					"类AccountReportService的addAccountReport方法出现异常，原因：", "获取三本台账首页报表信息失败", e);
//					"类AccountReportService的addAccountReport方法出现异常，原因：", "请使用市级以下账号查看", e);
			return null;
		}
	}

	/**
	 * 台账类型判断
	 * 
	 * @param accountReport
	 * @return
	 */
	private List<ThreeRecordsReportStatisticalVo> constructHomePageMouldContent(
			AccountReport accountReport) {
		if (null == accountReport || accountReport.getOrganization() == null) {
			throw new BusinessValidationException("参数错误！");
		}
		accountReport.setOrganization(organizationDubboService.getSimpleOrgById(accountReport
				.getOrganization().getId()));
		Integer homePageViewType = accountReport.getHomePageViewType() == null ? getOrgType(accountReport)
				: accountReport.getHomePageViewType();
		if (null == homePageViewType) {
			throw new BusinessValidationException("无法定位报表类型！请使用市级以下组织机构账号查看！");
		}
		List<ThreeRecordsReportStatisticalVo> vos = new ArrayList<ThreeRecordsReportStatisticalVo>();
		Long orgId = accountReport.getOrganization().getId();
		String searchOrgCode = accountReport.getOrganization().getOrgInternalCode();
		Integer accountType = accountReport.getAccountType();
		String targetInternalCode = null;
		Long sourceOrgId = null;
		Long targetOrgId = null;
		Integer isSearchOrg = 0;
		Boolean isNotSearchDepartmentData = Boolean.FALSE;
		Map<String, Object> map = assemblyMap(orgId, null, null, null, accountType,
				targetInternalCode, null, null, sourceOrgId, targetOrgId, null);
		if (homePageViewType >= LedgerReportType.REPORT_COUNTY) {
			// 县级累计建账
			vos.add(cumulativeCreateCount(map, LedgerReportRow.COUNTY));
			map.remove("orgId");
			map.put("ledgerType", 0);
			if (null != orgId) {
				isSearchOrg = 1;
				map.put("orgParentId", orgId);
				Long orgType = propertyDictDubboService.getPropertyDictByDomainName(
						LedgerReportFunctionalDepartments.FUNCTIONAL_DEPARTMENTS).getId();
				if (null != orgType) {
					map.put("orgType", orgType);
				}
				map.put("isSearchOrg", isSearchOrg);
			} else {
				isNotSearchDepartmentData = Boolean.TRUE;
			}
		}
		if (homePageViewType >= LedgerReportType.REPORT_COUNTY_DEPARTMENT) {
			// 县部门级累计建账
			if (isNotSearchDepartmentData) {
				vos.add(new ThreeRecordsReportStatisticalVo(LedgerReportRow.COUNTY
						+ LedgerReportRow.DEPARTMENT));
			} else {
				vos.add(cumulativeCreateCount(map, LedgerReportRow.COUNTY
						+ LedgerReportRow.DEPARTMENT));
			}
			map.remove("orgIdList");
			map.remove("orgId");
			map.remove("orgParentId");
			map.remove("orgType");
			map.remove("isSearchOrg");
			map.put("ledgerType", 0);
			map.remove("departmentType");
			if (getOrgType(accountReport) == LedgerReportType.REPORT_COUNTY_DEPARTMENT) {
				orgId = accountReport.getOrganization().getParentOrg().getId();
				searchOrgCode = organizationDubboService.getSimpleOrgById(orgId)
						.getOrgInternalCode();
			}
			if (null != orgId) {
				isSearchOrg = 1;
				map.put("orgParentId", orgId);
				Long orgType = propertyDictDubboService.getPropertyDictByDomainName(
						OrganizationType.ADMINISTRATIVE_KEY).getId();
				if (null != orgType) {
					map.put("orgType", orgType);
				}
				map.put("isSearchOrg", isSearchOrg);
			}
		}
		if (homePageViewType >= LedgerReportType.REPORT_TOWN) {
			// 镇级累计建账
			vos.add(cumulativeCreateCount(map, LedgerReportRow.TOWN));
			map.remove("orgId");
			map.remove("orgIdList");
			map.remove("orgParentId");
			map.remove("orgType");
			map.remove("isSearchOrg");
			map.put("ledgerType", 0);
			isSearchOrg = 1;
			if (getOrgType(accountReport) == LedgerReportType.REPORT_TOWN) {
				map.put("orgParentId", orgId);
			} else {
				map.put("searchOrgCode", searchOrgCode);
				map.put("orgLevel", getPropertyDict(OrganizationLevel.VILLAGE).getId());
			}
			map.put("isSearchOrg", isSearchOrg);
		}
		if (homePageViewType >= LedgerReportType.REPORT_VILLAGE) {
			// 村级累计建账
			vos.add(cumulativeCreateCount(map, LedgerReportRow.VILLAGE));
		}
		return vos;
	}

	private List<PropertyDict> getOrganizationLevel() {
		return propertyDictDubboService
				.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL);
	}

	private PropertyDict getPropertyDict(Integer level) {
		List<PropertyDict> propertyDicts = getOrganizationLevel();
		if (null != propertyDicts && propertyDicts.size() > 0 && level != null) {
			for (PropertyDict propertyDict : propertyDicts) {
				if (propertyDict.getInternalId() == level) {
					return propertyDict;
				}
			}
		}
		return null;
	}

	private Organization getDepartmentOrganization(String departmentNo, Long parentId) {
		List<Organization> organizations = organizationDubboService.findByDepartmentNoAndParentId(
				departmentNo, parentId);
		return organizations == null || organizations.size() <= 0 ? new Organization()
				: organizations.get(0);
	}

	private List<LedgerReportGroupCount> getReportAccountStepsCount(Map<String, Object> searchMap) {
		List<LedgerReportGroupCount> list = ledgerReportDao.getReportAccountStepsCount(searchMap);
		for (LedgerReportGroupCount count : list) {
			try {
				ComparisonAttribute.loadPropertyDictValue(count, count.getClass(),
						propertyDictDubboService);
			} catch (Exception e) {
				throw new ServiceValidationException("参数错误，转换失败！", e);
			}
		}
		return list;
	}

	private List<LedgerReportGroupCount> getReportStateCodeCount(Map<String, Object> searchMap) {
		List<LedgerReportGroupCount> list = ledgerReportDao.getReportStateCodeCount(searchMap);
		for (LedgerReportGroupCount count : list) {
			try {
				ComparisonAttribute.loadPropertyDictValue(count, count.getClass(),
						propertyDictDubboService);
			} catch (Exception e) {
				throw new ServiceValidationException("参数错误，转换失败！", e);
			}
		}
		return list;
	}

	private Date getFirstDayDate(Integer year, Integer month, Integer day,
			AccountReport accountReport) {
		return ComparisonAttribute.getDateUtil(year, month, day, accountReport, Boolean.FALSE);
	}

	private Date getLastDayDate(Integer year, Integer month, Integer day,
			AccountReport accountReport) {
		return ComparisonAttribute.getDateUtil(year, month, day, accountReport, Boolean.TRUE);
	}

	/**
	 * 是否是县部门
	 * 
	 * @param organization
	 * @return
	 */
	private Boolean isCountyDepartment(Organization organization) {
		if (null == organization || organization.getOrgType() == null
				|| organization.getOrgType().getId() == null) {
			return false;
		}
		if (organization
				.getOrgType()
				.getId()
				.equals(propertyDictDubboService.getPropertyDictByDomainName(
						LedgerReportFunctionalDepartments.FUNCTIONAL_DEPARTMENTS).getId())) {
			return true;
		}
		return false;
	}
}
