package com.tianque.plugin.account.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.PropertyDomain;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.dao.ThreeAccountReportDao;
import com.tianque.plugin.account.service.ThreeAccountReportService;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;
import com.tianque.plugin.account.util.LedgerAccountReportVariable;
import com.tianque.plugin.account.vo.AccountResultVo;
import com.tianque.plugin.account.vo.ThreeRecordsReportStatisticalVo;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;
import com.tianque.userAuth.api.PropertyDictDubboService;
import com.tianque.userAuth.api.PropertyDomainDubboService;

@Service("threeAccountReportService")
public class ThreeAccountReportServiceImpl implements ThreeAccountReportService{
	
	@Autowired
	private PropertyDictDubboService propertyDictDubboService;
	@Autowired
	private PropertyDomainDubboService propertyDomainDubboService;
	@Autowired
	private ThreeAccountReportDao threeAccountReportDao;
	@Autowired
	private OrganizationDubboRemoteService organizationDubboRemoteService;
	@Override
	public List<ThreeRecordsReportStatisticalVo> findThreeAccountReportForList(
			Integer year, Integer month, Long orgId) {
		
		
		
		return null;
	}

	@Override
	public List<ThreeRecordsReportStatisticalVo> createThreeAccountRecord(Integer year, Integer month,Long orgId) {
		if(year==null || month ==null || orgId==null){
			throw new BusinessValidationException("三本台账统计报表数据生成失败，未获得正确参数");
		}
		Organization organization = organizationDubboRemoteService.getSimpleOrgById(orgId);
		if(organization==null){
			throw new BusinessValidationException("报表查询失败，未获得正确组织机构参数");
		}
		PropertyDict dict = propertyDictDubboService.getPropertyDictById(organization.getOrgLevel().getId());
		if(dict==null){
			throw new BusinessValidationException("报表查询失败，未获得当前层级参数");
		}
		int currentInternalId = dict.getInternalId(); 
		
		List<ThreeRecordsReportStatisticalVo> list = new ArrayList<ThreeRecordsReportStatisticalVo>();
		if(currentInternalId==OrganizationLevel.DISTRICT){//区县层级
			//再判断是职能部门还是行政部门
			if(organization.getOrgType().getInternalId()==OrganizationType.FUNCTIONAL_ORG){//职能部门
				
				
			}else{//行政部门
				
			}
			
		}
		if(currentInternalId==OrganizationLevel.TOWN){//乡镇报表
			
		}
		if(currentInternalId==OrganizationLevel.VILLAGE){//村社区报表
			list =  statisticsCommunityRecord(year, month, orgId);
		}
		return list;
	}
	
	/***
	 * 将民生、文档类别的数据赋值给对应的字段
	 * @param voList
	 * @param stableList
	 * @param cumulativeVo
	 * @param endVo
	 * @param levelVo
	 * @param programVo
	 * @param upVo
	 */
	private void dealData( List<AccountResultVo> voList,List<AccountResultVo> stableList,ThreeRecordsReportStatisticalVo cumulativeVo,
			ThreeRecordsReportStatisticalVo endVo,ThreeRecordsReportStatisticalVo levelVo,
			ThreeRecordsReportStatisticalVo programVo,ThreeRecordsReportStatisticalVo upVo){
		if(voList!=null && voList.size()!=0){
	    	for(AccountResultVo accountVo:voList){
	    		switch(accountVo.getDealType()){
	    		case ThreeRecordsIssueOperate.COMPLETE_CODE:endVo = calculationData(endVo,accountVo);break;//实质性办结
	    		case ThreeRecordsIssueOperate.PERIOD_CODE: levelVo = calculationData(levelVo,accountVo);break;//阶段性办结
	    		case ThreeRecordsIssueOperate.PROGRAM_CODE: programVo = calculationData(programVo,accountVo);break;//程序性办结
	    		case ThreeRecordsIssueOperate.SUBMIT_CODE: upVo = calculationData(upVo,accountVo);break;//呈报乡镇数
	    		}
	    	}
	    	cumulativeVo.setWaterResourceCount(endVo.getWaterResourceCount() + levelVo.getWaterResourceCount()+programVo.getWaterResourceCount());
	    	cumulativeVo.setTrafficCount(endVo.getTrafficCount()+levelVo.getTrafficCount()+programVo.getTrafficCount());
	    	cumulativeVo.setEnergyCount(endVo.getEnergyCount()+levelVo.getEnergyCount()+programVo.getEnergyCount());
	    	cumulativeVo.setEducationCount(endVo.getEducationCount()+levelVo.getEducationCount()+programVo.getEducationCount());
	    	cumulativeVo.setScienceTechnologyCount(endVo.getScienceTechnologyCount()+levelVo.getScienceTechnologyCount()+programVo.getScienceTechnologyCount());
	    	cumulativeVo.setHealthMedicalCount(endVo.getHealthMedicalCount()+levelVo.getHealthMedicalCount()+programVo.getHealthMedicalCount());
	    	cumulativeVo.setSocialLaborCount(endVo.getSocialLaborCount()+levelVo.getSocialLaborCount()+programVo.getSocialLaborCount());
	    	cumulativeVo.setEnvironmentalProtectionCount(endVo.getEnvironmentalProtectionCount()+levelVo.getEnvironmentalProtectionCount()+programVo.getEnvironmentalProtectionCount());
	    	cumulativeVo.setPlanningAdviceManagementCount(endVo.getPlanningAdviceManagementCount()+levelVo.getPlanningAdviceManagementCount()+programVo.getPlanningAdviceManagementCount());
	    	cumulativeVo.setResourcesAgriculturalCount(endVo.getResourcesAgriculturalCount()+levelVo.getResourcesAgriculturalCount()+programVo.getResourcesAgriculturalCount());
	    	cumulativeVo.setOtherResourcesCount(endVo.getOtherResourcesCount()+levelVo.getOtherResourcesCount()+programVo.getOtherResourcesCount());
	    }
	    if(stableList!=null && stableList.size()!=0){
	    	for(AccountResultVo accountVo:stableList){
	    		switch(accountVo.getDealType()){
	    		case ThreeRecordsIssueOperate.COMPLETE_CODE:endVo = calculationDataForSteady(endVo,accountVo);break;//实质性办结
	    		case ThreeRecordsIssueOperate.PERIOD_CODE: levelVo = calculationDataForSteady(levelVo,accountVo);break;//阶段性办结
	    		case ThreeRecordsIssueOperate.PROGRAM_CODE: programVo = calculationDataForSteady(programVo,accountVo);break;//程序性办结
	    		case ThreeRecordsIssueOperate.SUBMIT_CODE: upVo = calculationDataForSteady(upVo,accountVo);break;//呈报乡镇数
	    		}
	    	}
	    	
	    	cumulativeVo.setVisitsCount(endVo.getVisitsCount()+levelVo.getVisitsCount()+programVo.getVisitsCount());
	    	cumulativeVo.setForestSoilCount(endVo.getForestSoilCount()+levelVo.getForestSoilCount()+programVo.getForestSoilCount());	
	    	cumulativeVo.setFavorablePoliciesCount(endVo.getFavorablePoliciesCount()+levelVo.getFavorablePoliciesCount()+programVo.getFavorablePoliciesCount());
	    	cumulativeVo.setCivilAdministrationIssuesCount(endVo.getCivilAdministrationIssuesCount()+levelVo.getCivilAdministrationIssuesCount()+programVo.getCivilAdministrationIssuesCount());
	    	cumulativeVo.setPopulationMedicalCount(endVo.getPopulationMedicalCount()+levelVo.getPopulationMedicalCount()+programVo.getPopulationMedicalCount());
	    	cumulativeVo.setLaborSocialSecurityCount(endVo.getLaborSocialSecurityCount()+levelVo.getLaborSocialSecurityCount()+programVo.getLaborSocialSecurityCount());
	    	cumulativeVo.setTransportationCount(endVo.getTransportationCount()+levelVo.getTransportationCount()+programVo.getTransportationCount());
	    	cumulativeVo.setUrbanConstructionAndCLECount(endVo.getUrbanConstructionAndCLECount()+levelVo.getUrbanConstructionAndCLECount()+programVo.getUrbanConstructionAndCLECount());
	    	cumulativeVo.setCpcPartyDisciplinesCount(endVo.getCpcPartyDisciplinesCount()+levelVo.getCpcPartyDisciplinesCount()+programVo.getCpcPartyDisciplinesCount());
	    	cumulativeVo.setSteadyRecordWorkEducationCount(endVo.getSteadyRecordWorkEducationCount()+levelVo.getSteadyRecordWorkEducationCount()+programVo.getSteadyRecordWorkEducationCount());
	    	cumulativeVo.setEnterpriseRestructuringCount(endVo.getEnterpriseRestructuringCount()+levelVo.getEnterpriseRestructuringCount()+programVo.getEnterpriseRestructuringCount());
	    	cumulativeVo.setEnvironmentalConservationCount(endVo.getEnvironmentalConservationCount()+levelVo.getEnvironmentalConservationCount()+programVo.getEnvironmentalConservationCount());
	    	cumulativeVo.setOrganizationPersonnelCount(endVo.getOrganizationPersonnelCount()+levelVo.getOrganizationPersonnelCount()+programVo.getOrganizationPersonnelCount());
	    	cumulativeVo.setSteadyRecordWorkOtherCount(endVo.getSteadyRecordWorkOtherCount()+levelVo.getSteadyRecordWorkOtherCount()+programVo.getSteadyRecordWorkOtherCount());
	    	cumulativeVo.setKeyPersonnelCount(endVo.getKeyPersonnelCount()+levelVo.getKeyPersonnelCount()+programVo.getKeyPersonnelCount());
	    }
	}
	
	private  List<ThreeRecordsReportStatisticalVo> statisticsCommunityRecord(Integer year,Integer month,Long orgId){
		List<ThreeRecordsReportStatisticalVo> list = new ArrayList<ThreeRecordsReportStatisticalVo>();
		Map<String,Object> map = getConditionMap(year, month, orgId);
		Date endDate = CalendarUtil.getNextMonthStart(year, month);
		//累计建账数
		ThreeRecordsReportStatisticalVo vo= new ThreeRecordsReportStatisticalVo();
	    vo = threeAccountReportDao.getCumulativeRecord(map);//查询累计建账数
	    vo.setColumnName(LedgerAccountReportVariable.CUMULATIVE_ACCOUNT_COUNT);
	    list.add(vo);
	   //查询上年转接数 
	    //获得民生建表类型大类Id
	    PropertyDomain domain = propertyDomainDubboService.getPropertyDomainByDomainName(PropertyTypes.LEDGER_PEOPLEASPIRATION_CREATE_TABLE_TYPE);
	    if(domain==null){
	    	throw new BusinessValidationException("上年转接数查询出错");
	    }
	    vo= new ThreeRecordsReportStatisticalVo();
	    map.put("historyType", domain.getId());
	    vo = threeAccountReportDao.getCumulativeRecord(map);
	    vo.setColumnName(LedgerAccountReportVariable.LAST_YEAR_TRANSFER_COUNT);
	    list.add(vo);
	    map.remove("historyType");
		
		//查询民生类、稳定类的办结数
	    Map<String,Object> peopleLivelihoodMap = new HashMap<String,Object>();
	    peopleLivelihoodMap.put("endDate", endDate);
	    peopleLivelihoodMap.put("orgId", orgId);
	    peopleLivelihoodMap.put("currentStatistics", "false");
	    peopleLivelihoodMap.put("endCode", ThreeRecordsIssueOperate.endCodeList);
	    List<AccountResultVo> voList = threeAccountReportDao.threeReportByPeopleLivelihoodForList(peopleLivelihoodMap);//民生类别数据
	    List<AccountResultVo> stableList = threeAccountReportDao.threeReportByPeopleLivelihoodForList(peopleLivelihoodMap);//稳定类别数据
	    ThreeRecordsReportStatisticalVo cumulativeVo = new ThreeRecordsReportStatisticalVo();
	    
    	ThreeRecordsReportStatisticalVo endVo = new ThreeRecordsReportStatisticalVo();
    	
    	ThreeRecordsReportStatisticalVo levelVo = new ThreeRecordsReportStatisticalVo();
    	
    	ThreeRecordsReportStatisticalVo programVo = new ThreeRecordsReportStatisticalVo();
    	
    	ThreeRecordsReportStatisticalVo upVo = new ThreeRecordsReportStatisticalVo();
    	
    	
    	List<Integer> endCode = new ArrayList<Integer>();
	    endCode.add(ThreeRecordsIssueOperate.COMPLETE_CODE);
    	peopleLivelihoodMap.put("endCode", endCode);
    	endVo = threeAccountReportDao.threeReportByDifficultForList(peopleLivelihoodMap);//困难类办结数
    	List<Integer> periodCode = new ArrayList<Integer>();
    	periodCode.add(ThreeRecordsIssueOperate.PERIOD_CODE);
    	peopleLivelihoodMap.put("endCode", periodCode);
    	levelVo = threeAccountReportDao.threeReportByDifficultForList(peopleLivelihoodMap);//困难类办结数
    	List<Integer> programCode = new ArrayList<Integer>();
    	programCode.add(ThreeRecordsIssueOperate.PERIOD_CODE);
    	peopleLivelihoodMap.put("endCode", programCode);
    	programVo = threeAccountReportDao.threeReportByDifficultForList(peopleLivelihoodMap);//困难类办结数
    	List<Integer> submitCode = new ArrayList<Integer>();
    	submitCode.add(ThreeRecordsIssueOperate.PERIOD_CODE);
    	peopleLivelihoodMap.put("endCode",submitCode);
    	upVo = threeAccountReportDao.threeReportByDifficultForList(peopleLivelihoodMap);//困难类办结数
    	
    	dealData(voList, stableList, cumulativeVo, endVo, levelVo, programVo, upVo);
    	cumulativeVo.setColumnName(LedgerAccountReportVariable.CUMULATIVE_END_COUNT);
    	endVo.setColumnName(LedgerAccountReportVariable.END_COUNT);
    	levelVo.setColumnName(LedgerAccountReportVariable.LEVEL_COUNT);
    	programVo.setColumnName(LedgerAccountReportVariable.PROGRAM_COUNT);
    	upVo.setColumnName(LedgerAccountReportVariable.UP_TOWN_COUNT);
	    list.add(cumulativeVo);
    	list.add(endVo);
    	list.add(levelVo);
    	list.add(programVo);
    	list.add(upVo);
		//查询上月办理中
    	peopleLivelihoodMap.put("endCode", ThreeRecordsIssueOperate.endCodeList);
    	peopleLivelihoodMap.put("currentStatistics", "true");
    	peopleLivelihoodMap.put("endDate", CalendarUtil.getLastMonthEnd(year, month));
    	ThreeRecordsReportStatisticalVo lastMonthDoing = new ThreeRecordsReportStatisticalVo();
    	lastMonthDoing = threeAccountReportDao.threeReportByDifficultForList(peopleLivelihoodMap);//困难类办结数
    	lastMonthDoing.setColumnName(LedgerAccountReportVariable.LAST_MONTH_COUNT);
    	
    	List<AccountResultVo> lastMonthList = threeAccountReportDao.threeReportByPeopleLivelihoodForList(peopleLivelihoodMap);//上月办理中民生类别数据
	    List<AccountResultVo> lastMonthStableList = threeAccountReportDao.threeReportByPeopleLivelihoodForList(peopleLivelihoodMap);//上月办理中稳定类别数据
	    if(lastMonthList!=null && lastMonthList.size()!=0){
	    	for(AccountResultVo currentVo : lastMonthList){
	    		lastMonthDoing = calculationData(lastMonthDoing, currentVo);
	    	}
	    }
	    if(lastMonthStableList!=null && lastMonthStableList.size()!=0){
	    	for(AccountResultVo currentVo : lastMonthStableList){
	    		lastMonthDoing = calculationDataForSteady(lastMonthDoing, currentVo);
	    	}
	    }
	    list.add(lastMonthDoing);
		//查询本月建账数
	    ThreeRecordsReportStatisticalVo currentCountVo= new ThreeRecordsReportStatisticalVo();
	    map.put("startDate", CalendarUtil.getBeforeMonthFirstDayByTime(year,month));
	    currentCountVo = threeAccountReportDao.getCumulativeRecord(map);//查询累计建账数
	    currentCountVo.setColumnName(LedgerAccountReportVariable.CURRENT_MONTH_COUNT);
	    list.add(currentCountVo);
	    
	    //本月实质办结数
	    peopleLivelihoodMap.put("endDate", endDate);
	    peopleLivelihoodMap.put("startDate", CalendarUtil.getBeforeMonthFirstDayByTime(year,month));
	    peopleLivelihoodMap.put("orgId", orgId);
	    peopleLivelihoodMap.put("currentStatistics", "false");
	    peopleLivelihoodMap.put("endCode", ThreeRecordsIssueOperate.endCodeList);
	    List<AccountResultVo> voCurrentList = threeAccountReportDao.threeReportByPeopleLivelihoodForList(peopleLivelihoodMap);//民生类别数据
	    List<AccountResultVo> stableCurrentList = threeAccountReportDao.threeReportByPeopleLivelihoodForList(peopleLivelihoodMap);//稳定类别数据
	    cumulativeVo = new ThreeRecordsReportStatisticalVo();
	   
    	endVo = new ThreeRecordsReportStatisticalVo();
    	
    	levelVo = new ThreeRecordsReportStatisticalVo();
    	
    	programVo = new ThreeRecordsReportStatisticalVo();
    	
    	upVo = new ThreeRecordsReportStatisticalVo();
    	
    	peopleLivelihoodMap.put("endCode", endCode);
    	endVo = threeAccountReportDao.threeReportByDifficultForList(peopleLivelihoodMap);//困难类办结数
    	peopleLivelihoodMap.put("endCode",periodCode );
    	levelVo = threeAccountReportDao.threeReportByDifficultForList(peopleLivelihoodMap);//困难类办结数
    	peopleLivelihoodMap.put("endCode",programCode );
    	programVo = threeAccountReportDao.threeReportByDifficultForList(peopleLivelihoodMap);//困难类办结数
    	peopleLivelihoodMap.put("endCode", submitCode);
    	upVo = threeAccountReportDao.threeReportByDifficultForList(peopleLivelihoodMap);//困难类办结数
    	dealData(voCurrentList, stableCurrentList, cumulativeVo, endVo, levelVo, programVo, upVo);
    	cumulativeVo.setColumnName(LedgerAccountReportVariable.CURRENT_CUMULATIVE_COUNT);
    	endVo.setColumnName(LedgerAccountReportVariable.CURRENT_MONTH_END_COUNT);
    	levelVo.setColumnName(LedgerAccountReportVariable.CURRENT_MONTH_LEVEL_COUNT);
    	programVo.setColumnName(LedgerAccountReportVariable.CURRENT_MONTH_PROGRAM_COUNT);
    	upVo.setColumnName(LedgerAccountReportVariable.CURRENT_MONTH_UPTOWN_COUNT);
	    list.add(cumulativeVo);
    	list.add(endVo);
    	list.add(levelVo);
    	list.add(programVo);
    	list.add(upVo);
	    //本月办理中
    	peopleLivelihoodMap.put("endDate", endDate);
 	    peopleLivelihoodMap.put("startDate", CalendarUtil.getBeforeMonthFirstDayByTime(year,month));
    	peopleLivelihoodMap.put("currentStatistics", "true");
    	ThreeRecordsReportStatisticalVo currentDoing = new ThreeRecordsReportStatisticalVo();
    	currentDoing = threeAccountReportDao.threeReportByDifficultForList(peopleLivelihoodMap);//困难类办结数
    	currentDoing.setColumnName(LedgerAccountReportVariable.CURRENT_MONTH_DOING_COUNT);
    	List<AccountResultVo> currentList = threeAccountReportDao.threeReportByPeopleLivelihoodForList(peopleLivelihoodMap);//上月办理中民生类别数据
		List<AccountResultVo> currentStableList = threeAccountReportDao
				.threeReportByStableForList(peopleLivelihoodMap);//上月办理中稳定类别数据
	    if(currentList!=null && currentList.size()!=0){
	    	for(AccountResultVo currentVo : currentList){
	    		currentDoing = calculationData(currentDoing, currentVo);
	    	}
	    }
	    if(currentStableList!=null && currentStableList.size()!=0){
	    	for(AccountResultVo currentVo : currentStableList){
	    		currentDoing = calculationDataForSteady(currentDoing, currentVo);
	    	}
	    }
	    list.add(currentDoing);
	    //计算合计 小计
		return typeSubtotal(list);
	}
	
	private ThreeRecordsReportStatisticalVo calculationDataForSteady(ThreeRecordsReportStatisticalVo vo,AccountResultVo accountResultVo){
		Map<String,Long> dictMaps = getPropertyDictIds(PropertyTypes.STEADY_RECORD_WORK_TYPE);//查询稳定大类
		if(accountResultVo.getAccountType().equals(Integer.parseInt(dictMaps.get(LedgerAccountReportVariable.INVOLVING_LAW_KEY).toString()))){
			vo.setVisitsCount(accountResultVo.getData());
		}
		if(accountResultVo.getAccountType().equals(Integer.parseInt(dictMaps.get(LedgerAccountReportVariable.SOIL_WATER_KEY).toString()))){
			vo.setForestSoilCount(accountResultVo.getData());	
		}
		if(accountResultVo.getAccountType().equals(Integer.parseInt(dictMaps.get(LedgerAccountReportVariable.GOVERNMENT_FINANCE_KEY).toString()))){
			vo.setFavorablePoliciesCount(accountResultVo.getData());
		}
		if(accountResultVo.getAccountType().equals(Integer.parseInt(dictMaps.get(LedgerAccountReportVariable.CIVIL_AFFAIRS_KEY).toString()))){
			vo.setCivilAdministrationIssuesCount(accountResultVo.getData());
		}
		if(accountResultVo.getAccountType().equals(Integer.parseInt(dictMaps.get(LedgerAccountReportVariable.POPULATION_TREATMENT_KEY).toString()))){
			vo.setPopulationMedicalCount(accountResultVo.getData());
		}
		if(accountResultVo.getAccountType().equals(Integer.parseInt(dictMaps.get(LedgerAccountReportVariable.LABOR_SECURITY_KEY).toString()))){
			vo.setLaborSocialSecurityCount(accountResultVo.getData());
		}
		if(accountResultVo.getAccountType().equals(Integer.parseInt(dictMaps.get(LedgerAccountReportVariable.TRANSPORTATION_KEY).toString()))){
			vo.setTransportationCount(accountResultVo.getData());	
		}
		if(accountResultVo.getAccountType().equals(Integer.parseInt(dictMaps.get(LedgerAccountReportVariable.URBAN_CONSTRUCTION_KEY).toString()))){
			vo.setUrbanConstructionAndCLECount(accountResultVo.getData());
		}
		if(accountResultVo.getAccountType().equals(Integer.parseInt(dictMaps.get(LedgerAccountReportVariable.PARTY_KEY).toString()))){
			vo.setCpcPartyDisciplinesCount(accountResultVo.getData());
		}
		if(accountResultVo.getAccountType().equals(Integer.parseInt(dictMaps.get(LedgerAccountReportVariable.EDUCATION_KEY).toString()))){
			vo.setSteadyRecordWorkEducationCount(accountResultVo.getData());
		}
		if(accountResultVo.getAccountType().equals(Integer.parseInt(dictMaps.get(LedgerAccountReportVariable.ENTERPRISE_KEY).toString()))){
			vo.setEnterpriseRestructuringCount(accountResultVo.getData());
		}
		if(accountResultVo.getAccountType().equals(Integer.parseInt(dictMaps.get(LedgerAccountReportVariable.ENVIRONMENTAL_SCIENCE_KEY).toString()))){
			vo.setEnvironmentalConservationCount(accountResultVo.getData());	
		}
		if(accountResultVo.getAccountType().equals(Integer.parseInt(dictMaps.get(LedgerAccountReportVariable.ORGANIZATION_KEY).toString()))){
			vo.setOrganizationPersonnelCount(accountResultVo.getData());
		}
		if(accountResultVo.getAccountType().equals(Integer.parseInt(dictMaps.get(LedgerAccountReportVariable.OTHER_KEY).toString()))){
			vo.setSteadyRecordWorkOtherCount(accountResultVo.getData());
		}
		if(accountResultVo.getAccountType().equals(Integer.parseInt(dictMaps.get(LedgerAccountReportVariable.PEOPLE_KEY).toString()))){
			vo.setKeyPersonnelCount(accountResultVo.getData());
		}
		return vo;
	}
	
	private ThreeRecordsReportStatisticalVo calculationData(ThreeRecordsReportStatisticalVo vo,AccountResultVo accountResultVo){
		try{
		if(vo!=null && accountResultVo!=null ){
			switch(accountResultVo.getAccountType()){
			case LedgerConstants.PEOPLEASPIRATION_WATER:  vo.setWaterResourceCount(accountResultVo.getData()); break;//水利
			case LedgerConstants.PEOPLEASPIRATION_TRAFFIC: vo.setTrafficCount(accountResultVo.getData()); break;//交通
			case LedgerConstants.PEOPLEASPIRATION_ENERGY: vo.setEnergyCount(accountResultVo.getData()); break;//能源
			case LedgerConstants.PEOPLEASPIRATION_EDUCATION: vo.setEducationCount(accountResultVo.getData()); break;//教育
			case LedgerConstants.PEOPLEASPIRATION_SCIENCE: vo.setScienceTechnologyCount(accountResultVo.getData()); break;//科技文体
			case LedgerConstants.PEOPLEASPIRATION_MEDICAL: vo.setHealthMedicalCount(accountResultVo.getData()); break;//医疗
			case LedgerConstants.PEOPLEASPIRATION_LABOR: vo.setSocialLaborCount(accountResultVo.getData()); break;//劳动与社会保障
			case LedgerConstants.PEOPLEASPIRATION_ENVIRONMENT: vo.setEnvironmentalProtectionCount(accountResultVo.getData()); break;//环境保护
			case LedgerConstants.PEOPLEASPIRATION_TOWN: vo.setPlanningAdviceManagementCount(accountResultVo.getData()); break;//城乡建设管理
			case LedgerConstants.PEOPLEASPIRATION_AGRICULTURE: vo.setResourcesAgriculturalCount(accountResultVo.getData()); break;//农业
			case LedgerConstants.PEOPLEASPIRATION_OTHER: vo.setOtherResourcesCount(accountResultVo.getData()); break;//其他
			}
		}
		}catch(Exception e){
			throw new ServiceValidationException("查询报表失败",e);
		}
		return vo;
	}
	
	
	private Map<String,Object> getConditionMap(Integer year,Integer month,Long orgId){
		Map<String,Object> map = new HashMap<String,Object>();
		Date endDate = CalendarUtil.getNextMonthStart(year, month);
		map.put("endDate", endDate);
		map.put("orgId", orgId);
		map.put("water", LedgerConstants.PEOPLEASPIRATION_WATER);//水利
		map.put("traffic", LedgerConstants.PEOPLEASPIRATION_TRAFFIC);//交通
		map.put("energy", LedgerConstants.PEOPLEASPIRATION_ENERGY);//能源
		map.put("education", LedgerConstants.PEOPLEASPIRATION_EDUCATION);//教育
		map.put("science", LedgerConstants.PEOPLEASPIRATION_SCIENCE);//科技文体
		map.put("medical", LedgerConstants.PEOPLEASPIRATION_MEDICAL);//医疗卫生
		map.put("labor", LedgerConstants.PEOPLEASPIRATION_LABOR);//劳动与社会保障
		map.put("environment", LedgerConstants.PEOPLEASPIRATION_ENVIRONMENT);//环境保护
		map.put("town", LedgerConstants.PEOPLEASPIRATION_TOWN);//城乡规划建设与管理
		map.put("agriculture", LedgerConstants.PEOPLEASPIRATION_AGRICULTURE);//农业
		map.put("other", LedgerConstants.PEOPLEASPIRATION_OTHER);//其他
		Map<String,Long> dictMap = getPropertyDictIds(PropertyTypes.LEDGER_POOR_PEOPLE_DIFFICULT_TYPE);//获得困难群众困难类型大类字典项ID
		map.put("lifeCount", dictMap.get(LedgerAccountReportVariable.LIFE_KEY));
		map.put("medicalCount", dictMap.get(LedgerAccountReportVariable.MEDICAL_CARE_KEY));
		map.put("housingCount", dictMap.get(LedgerAccountReportVariable.HOUSE_KEY));
		map.put("goSchoolCount", dictMap.get(LedgerAccountReportVariable.SCHOOL_KEY));
		map.put("employmentCount", dictMap.get(LedgerAccountReportVariable.OBTAIN_EMPLOYMENT_KEY));
		map.put("ledgerPoorPeopleOtherCount", dictMap.get(LedgerAccountReportVariable.OTHER_DEFICULT_KEY));
		Map<String,Long> dictMaps = getPropertyDictIds(PropertyTypes.STEADY_RECORD_WORK_TYPE);//获得稳定类型大类字典项ID
		map.put("visits", dictMaps.get(LedgerAccountReportVariable.INVOLVING_LAW_KEY));
		map.put("forestSoil", dictMaps.get(LedgerAccountReportVariable.SOIL_WATER_KEY));
		map.put("favorablePolicies", dictMaps.get(LedgerAccountReportVariable.GOVERNMENT_FINANCE_KEY));
		map.put("civilAdministrationIssues", dictMaps.get(LedgerAccountReportVariable.CIVIL_AFFAIRS_KEY));
		map.put("populationMedical", dictMaps.get(LedgerAccountReportVariable.POPULATION_TREATMENT_KEY));
		map.put("laborSocialSecurity", dictMaps.get(LedgerAccountReportVariable.LABOR_SECURITY_KEY));
		map.put("transportation", dictMaps.get(LedgerAccountReportVariable.TRANSPORTATION_KEY));
		map.put("urbanConstructionAndCLE", dictMaps.get(LedgerAccountReportVariable.URBAN_CONSTRUCTION_KEY));
		map.put("cpcPartyDisciplines", dictMaps.get(LedgerAccountReportVariable.PARTY_KEY));
		map.put("steadyRecordWorkEducation", dictMaps.get(LedgerAccountReportVariable.EDUCATION_KEY));
		map.put("enterpriseRestructuring", dictMaps.get(LedgerAccountReportVariable.ENTERPRISE_KEY));
		map.put("steadyRecordWorkEnvironmentalProtection", dictMaps.get(LedgerAccountReportVariable.ENVIRONMENTAL_SCIENCE_KEY));
		map.put("organizationPersonnel", dictMaps.get(LedgerAccountReportVariable.ORGANIZATION_KEY));
		map.put("steadyRecordWorkOther", dictMaps.get(LedgerAccountReportVariable.OTHER_KEY));
		map.put("keyPersonnel", dictMaps.get(LedgerAccountReportVariable.PEOPLE_KEY));
		return map;
	}

	
	/***
	 * 小计与合计
	 * @param list
	 * @return
	 */
	private List<ThreeRecordsReportStatisticalVo> typeSubtotal(List<ThreeRecordsReportStatisticalVo> list){
		if(list!=null && list.size()!=0){
			int peopleLivelihoodCount=  0 ;//民生工作小计
			int difficultPeopleCount=0;//困难群众小计
			int stableWorkCount = 0;//稳定工作小计
			for(ThreeRecordsReportStatisticalVo vo:list){
				peopleLivelihoodCount = vo.getWaterResourceCount()+vo.getTrafficCount()+vo.getEducationCount()+vo.getHealthMedicalCount()
				+vo.getResourcesAgriculturalCount()+vo.getEnergyCount()+vo.getSocialLaborCount()+vo.getEnvironmentalProtectionCount()+
				vo.getPlanningAdviceManagementCount()+vo.getScienceTechnologyCount()+vo.getOtherResourcesCount();
				
				difficultPeopleCount=vo.getLifeCount()+vo.getMedicalCount()+vo.getHousingCount()+vo.getGoSchoolCount()+vo.getEmploymentCount()+vo.getLedgerPoorPeopleOtherCount();
				
				stableWorkCount = vo.getVisitsCount()+vo.getForestSoilCount()+vo.getFavorablePoliciesCount()+vo.getCivilAdministrationIssuesCount()
				+vo.getPopulationMedicalCount()+vo.getLaborSocialSecurityCount()+vo.getTransportationCount()+vo.getUrbanConstructionAndCLECount()
				+vo.getCpcPartyDisciplinesCount()+vo.getSteadyRecordWorkEducationCount()+vo.getEnterpriseRestructuringCount()+vo.getEnvironmentalConservationCount()
				+vo.getOrganizationPersonnelCount()+vo.getSteadyRecordWorkOtherCount()+vo.getKeyPersonnelCount();
				
				vo.setPeopleAsWorkTotal(peopleLivelihoodCount);
				vo.setLedgerPoorPeopleTotal(difficultPeopleCount);
				vo.setSteadyRecordWorkTotal(stableWorkCount);
				vo.setTotal(peopleLivelihoodCount+difficultPeopleCount+stableWorkCount);
			}
		}
		return list;
	}
	
	/***
	 * 获得困难群众字典IDs
	 * @return
	 */
	private Map<String,Long> getPropertyDictIds(String domainName){
		Map<String,Long> map = new HashMap<String,Long>();
		List<PropertyDict> proList = propertyDictDubboService.findPropertyDictByDomainName(domainName);
		if(proList!=null && proList.size()!=0){
			for(PropertyDict propertyDict:proList){
				map.put(propertyDict.getDisplayName(), propertyDict.getId());
			}
		}
		return map;
	}
}
