package com.tianque.plugin.account.report.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.job.JobHelper;
import com.tianque.plugin.account.constants.ThreeRecordsIssueConstants;
import com.tianque.plugin.account.report.dao.AccountReportDao;
import com.tianque.plugin.account.report.service.AccountReportService;
import com.tianque.plugin.account.report.util.AccountReportResult;
import com.tianque.plugin.account.report.util.AnalyseUtilNew;
import com.tianque.plugin.account.report.util.HistoryReportResult;
import com.tianque.plugin.account.report.util.ReportXName;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;
import com.tianque.plugin.account.state.ThreeRecordsIssueState;
import com.tianque.plugin.account.util.DealYearOrMonthUtil;
import com.tianque.tableManage.service.TableManageService;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;
import com.tianque.userAuth.api.PropertyDictDubboService;

@Service("accountReportService")
@Transactional
public class AccountReportServiceImpl implements AccountReportService {
	@Autowired
	private AccountReportDao accountReportDao;
	@Autowired
    private TableManageService tableService;
	@Autowired
    private PropertyDictDubboService propertyDictDubboService;
    @Autowired
    private OrganizationDubboRemoteService organizationDubboRemoteService;
    
    //各层级报表横纵字段数量
    private int xAll = 37, yVillage = 15, yTown = 49, yFun = 34, yJg = 24, yCounty = 57;
    
    /**
     * 初始化报表数据
     * @param year
     * @param month
     */
    @Override
    public void initMonthRepot(final int year, final int month){
    	new Thread(){
    		public void run() {
    			JobHelper.createMockAdminSession();
    			int currentYear = CalendarUtil.getNowYear();
    			int currentMonth = CalendarUtil.getNowMonth();
    			if(year != 0){
    				currentYear = year;
    			}
    			if(month != 0){
    				currentMonth = month;
    			}
    			createHistoryStatisticByType(currentYear);
    			//System.out.println("三本台账"+currentYear+"年"+currentMonth+"月，报表初始化开始...");
    			//System.out.println(currentYear+"//"+currentMonth);
    			Map<String, Object> map = new HashMap<String, Object>();
    			map.put("currentMonthBegin", CalendarUtil.getMonthStart(currentYear, currentMonth));
    			map.put("currentMonthEnd", CalendarUtil.getMonthEnd(currentYear, currentMonth));
    			map.put("year", currentYear);
    			map.put("month", currentMonth);
    			map.put("completeCode", ThreeRecordsIssueState.SUBSTANCE_CODE);
    			map.put("periodCode", ThreeRecordsIssueState.PERIOD_CODE);
    			map.put("programCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
    			map.put("yearBegin", DealYearOrMonthUtil.dealYear(currentYear));
    			map.put("submitCode", ThreeRecordsIssueOperate.SUBMIT_CODE);
    			map.put("turnCode", ThreeRecordsIssueOperate.TURN_CODE);
    			map.put("declareCode", ThreeRecordsIssueOperate.DECLARE_CODE);
    			map.put("assignCode", ThreeRecordsIssueOperate.ASSIGN_CODE);
    			
    			List<PropertyDict> createTypeList = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.LEDGER_PEOPLEASPIRATION_CREATE_TABLE_TYPE);
    			for(PropertyDict dict : createTypeList){
    				if(dict.getDisplayName().equals("上年接转")){
    					map.put("lastYearTurn", dict.getId());
    					break;
    				}
    			}
    			accountReportDao.deleteCurrentMonthData(map);
    			accountReportDao.initAllData(map);
    			accountReportDao.initAccountMonthOrgLevel(map);
    			//System.out.println("三本台账"+currentYear+"年"+currentMonth+"月，报表初始化结束...");
    		};
    	}.start();
    }
    
    /**
     * 判断查询报表日期是否是当前月
     * @param year
     * @param month
     * @return
     */
    private boolean checkDateIsCurrentMonth(int year, int month){
    	return CalendarUtil.getNowYear() == year && CalendarUtil.getNowMonth() == month;
    }
    
    @Override
    public Map<Integer, Map<Integer, Integer>> getDataReport(Long orgId, int year, int month){
    	if(year < 2016){
    		return queryHistoryData(orgId, year, month);
    	}else{
//    		if(checkDateIsCurrentMonth(year, month)){//如果是当前月，则删除当前月报表数据重新生成后再查询
//    			deleteCurrentMonthData(year, month);//删除当月数据
//    			initCurrentMonthData(year, month);//生成当月数据
//    		}
    		Map<Integer, Map<Integer, Integer>> resultMap = queryData(orgId, year, month);//查询报表数据
    		return resultMap;
    	}
//    	return null;
    }
    
    private Map<Integer, Map<Integer, Integer>> queryHistoryData(Long orgId, int year, int month){
    	Map<String, Object> map = new HashMap<String, Object>();
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONDAY, month-1);
		calendar.set(Calendar.DAY_OF_MONTH, CalendarUtil.getDay(CalendarUtil.getMonthEnd(year, month)));
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern("yyyy-MM-dd");
		map.put("reportDate", CalendarUtil.parseDate(formater.format(calendar.getTime()), "yyyy-MM-dd"));
		map.put("orgId", orgId.toString());
		
    	long functionType = propertyDictDubboService.findPropertyDictByDomainNameAndDictDisplayName(OrganizationType.ORG_TYPE_KEY, OrganizationType.FUNCTION_KEY).getId();
    	Organization org = organizationDubboRemoteService.getSimpleOrgById(orgId);
		int orgLevel = propertyDictDubboService.getPropertyDictById(org.getOrgLevel().getId()).getInternalId();
		if (orgLevel == OrganizationLevel.VILLAGE) {
    		return queryHistoryVillageData(map);
        }else if (orgLevel == OrganizationLevel.TOWN) {
        	return queryHistoryTownData(map);
        }else if (orgLevel == OrganizationLevel.DISTRICT) {
            if (org.getOrgType().getId() == functionType) {
                String strDepartment = org.getDepartmentNo();
                if (strDepartment.endsWith(ThreeRecordsIssueConstants.COUNTY_LEDGER)) {
                	return queryHistoryJgData(map);
                } else {
                	return queryHistoryFunData(map);
                }
            }else{
            	return queryHistoryAllData(map);
            }
        }
		return null;
    }
    
    private void dealHistoryResult(HistoryReportResult result, Map<Integer, Map<Integer, Integer>> reportMap, int y){
    	setMap(1, y, result.getT1(), reportMap);
    	setMap(2, y, result.getT2(), reportMap);
    	setMap(3, y, result.getT3(), reportMap);
    	setMap(4, y, result.getT4(), reportMap);
    	setMap(5, y, result.getT5(), reportMap);
    	setMap(6, y, result.getT6(), reportMap);
    	setMap(7, y, result.getT7(), reportMap);
    	setMap(8, y, result.getT8(), reportMap);
    	setMap(9, y, result.getT9(), reportMap);
    	setMap(10, y, result.getT10(), reportMap);
    	setMap(11, y, result.getT11(), reportMap);
    	setMap(12, y, result.getT12(), reportMap);
    	setMap(13, y, result.getT13(), reportMap);
    	setMap(14, y, result.getT14(), reportMap);
    	setMap(15, y, result.getT15(), reportMap);
    	setMap(16, y, result.getT16(), reportMap);
    	setMap(17, y, result.getT17(), reportMap);
    	setMap(18, y, result.getT18(), reportMap);
    	setMap(19, y, result.getT19(), reportMap);
    	setMap(20, y, result.getT20(), reportMap);
    	setMap(21, y, result.getT21(), reportMap);
    	setMap(22, y, result.getT22(), reportMap);
    	setMap(23, y, result.getT23(), reportMap);
    	setMap(24, y, result.getT24(), reportMap);
    	setMap(25, y, result.getT25(), reportMap);
    	setMap(26, y, result.getT26(), reportMap);
    	setMap(27, y, result.getT27(), reportMap);
    	setMap(28, y, result.getT28(), reportMap);
    	setMap(29, y, result.getT29(), reportMap);
    	setMap(30, y, result.getT30(), reportMap);
    	setMap(31, y, result.getT31(), reportMap);
    	setMap(32, y, result.getT32(), reportMap);
    	setMap(33, y, result.getT33(), reportMap);
    	setMap(34, y, result.getT34(), reportMap);
    	setMap(35, y, result.getT35(), reportMap);
    	setMap(36, y, result.getT36(), reportMap);
    	setMap(37, y, result.getT37(), reportMap);
    }
    
    private Map<Integer, Map<Integer, Integer>> queryHistoryVillageData(Map<String, Object> map){
    	map.put("num", yVillage);
    	Map<Integer, Map<Integer, Integer>> reportMap = initialMap(xAll, yVillage);
    	List<HistoryReportResult> list = accountReportDao.queryHistoryAccountReport(map);
    	for(int i = 0; i < list.size(); i++){
    		HistoryReportResult result = list.get(i);
    		dealHistoryResult(result, reportMap, i+1);
    	}
    	return reportMap;
    }
    private Map<Integer, Map<Integer, Integer>> queryHistoryTownData(Map<String, Object> map){
    	map.put("num", yTown);
    	Map<Integer, Map<Integer, Integer>> reportMap = initialMap(xAll, yTown);
    	List<HistoryReportResult> list = accountReportDao.queryHistoryAccountReport(map);
    	for(int i = 0; i < list.size(); i++){
    		HistoryReportResult result = list.get(i);
    		dealHistoryResult(result, reportMap, i+1);
    	}
    	return reportMap;
    }
    private Map<Integer, Map<Integer, Integer>> queryHistoryJgData(Map<String, Object> map){
    	map.put("num", yJg);
    	Map<Integer, Map<Integer, Integer>> reportMap = initialMap(xAll, yJg);
    	List<HistoryReportResult> list = accountReportDao.queryHistoryAccountReport(map);
    	for(int i = 0; i < list.size(); i++){
    		HistoryReportResult result = list.get(i);
    		dealHistoryResult(result, reportMap, i+1);
    	}
    	return reportMap;
    }
    private Map<Integer, Map<Integer, Integer>> queryHistoryFunData(Map<String, Object> map){
    	map.put("num", yFun);
    	Map<Integer, Map<Integer, Integer>> reportMap = initialMap(xAll, yFun);
    	List<HistoryReportResult> list = accountReportDao.queryHistoryAccountReport(map);
    	for(int i = 0; i < list.size(); i++){
    		HistoryReportResult result = list.get(i);
    		dealHistoryResult(result, reportMap, i+1);
    	}
    	return reportMap;
    }
    private Map<Integer, Map<Integer, Integer>> queryHistoryAllData(Map<String, Object> map){
    	map.put("num", yCounty);
    	map.put("orgId", "-1");
    	Map<Integer, Map<Integer, Integer>> reportMap = initialMap(xAll, yCounty);
    	List<HistoryReportResult> list = accountReportDao.queryHistoryAccountReport(map);
    	for(int i = 0; i < list.size(); i++){
    		HistoryReportResult result = list.get(i);
    		dealHistoryResult(result, reportMap, i+1);
    	}
    	return reportMap;
    }
    
    /**
     * 村报表
     * @param orgId
     * @param year
     * @param month
     */
    private Map<Integer, Map<Integer, Integer>> queryData(Long orgId, int year, int month){
    	List<PropertyDict> createTypeList = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.LEDGER_PEOPLEASPIRATION_CREATE_TABLE_TYPE);
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("year", year);
    	map.put("month", month);
    	map.put("currentMonthEnd", CalendarUtil.getMonthEnd(year, month));
    	map.put("currentMonthBegin", CalendarUtil.getMonthStart(year, month));
    	map.put("orgId", orgId);
    	map.put("yearBegin", DealYearOrMonthUtil.dealYear(year));
    	for(PropertyDict dict : createTypeList){
    		if(dict.getDisplayName().equals("上年接转")){
    			map.put("lastYearTurn", dict.getId());//上年转接5088
    		}else if(dict.getDisplayName().equals("新建")){
    			map.put("newCreate", dict.getId());//新建5087
    		}else if(dict.getDisplayName().equals("县委县政府及县级领导班子有关领导交办(乡镇选填)")){
    			map.put("counrtyPartyTurn", dict.getId());//县委县政府及县级领导班子有关领导交办(乡镇选填)5091
    		}else if(dict.getDisplayName().equals("县人大议案、建议、意见交办")){
    			map.put("counrtyPeopleTurn", dict.getId());//县人大议案、建议、意见交办5092
    		}else if(dict.getDisplayName().equals("县政协提案、建议、意见交办")){
    			map.put("counrtyCPPTurn", dict.getId());//县政协提案、建议、意见交办5093
    		}else if(dict.getDisplayName().equals("上级主管部门和县级领导班子有关领导交办(县级部门选填)")){
    			map.put("counrtySuperTurn", dict.getId());//5090
    		}
    	}
    	if(month == 1 && year != 2016){//上月办理中年月时间处理
    		map.put("lastMonth", 12);
    		map.put("lastMonthYear", year - 1);
    	}else{
    		map.put("lastMonth", month - 1);
    		map.put("lastMonthYear", year);
    		
    		Calendar calendar=Calendar.getInstance();//16年1月的上月办理中特殊处理，获取15年12的本月办理中数据
    		calendar.set(Calendar.YEAR, 2015);
    		calendar.set(Calendar.MONDAY, 11);
    		calendar.set(Calendar.DAY_OF_MONTH, 31);
    		SimpleDateFormat formater = new SimpleDateFormat();
    		formater.applyPattern("yyyy-MM-dd");
    		map.put("reportDate", CalendarUtil.parseDate(formater.format(calendar.getTime()), "yyyy-MM-dd"));
    	}
    	
    	map.put("completeCode", ThreeRecordsIssueState.SUBSTANCE_CODE);
    	map.put("periodCode", ThreeRecordsIssueState.PERIOD_CODE);
    	map.put("programCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
    	map.put("submitCode", ThreeRecordsIssueOperate.SUBMIT_CODE);
    	map.put("turnCode", ThreeRecordsIssueOperate.TURN_CODE);
    	map.put("assignCode", ThreeRecordsIssueOperate.ASSIGN_CODE);
    	map.put("declareCode", ThreeRecordsIssueOperate.DECLARE_CODE);
    	
    	long functionType = propertyDictDubboService.findPropertyDictByDomainNameAndDictDisplayName(OrganizationType.ORG_TYPE_KEY, OrganizationType.FUNCTION_KEY).getId();
    	
    	List<Organization> xwLxList = accountReportDao.getXwandLxOrg(functionType);//获得县委县政府和县联席组织机构ID
    	for(Organization o : xwLxList){
    		if(o.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_COMMITTEE)){
    			map.put("xwOrgId", o.getId());//县委县政府组织机构Id
    		} else {
    			map.put("lxOrgId", o.getId());//县联席组织机构Id
    		}
    	}
    	
		Map<Integer, Map<Integer, Integer>> result = null;
    	Organization org = organizationDubboRemoteService.getSimpleOrgById(orgId);
		int orgLevel = propertyDictDubboService.getPropertyDictById(org.getOrgLevel().getId()).getInternalId();
    	if (orgLevel == OrganizationLevel.VILLAGE) {
    		result =  queryVillageData(map);
    		if(month == 1 && year == 2016)
    			getVillageLastMonthDoing(result, map);//16年1月的上月办理中查询15年12月的本月办理中数据
        }else if (orgLevel == OrganizationLevel.TOWN) {
        	result = queryTownData(map, orgId);
        	if(month == 1 && year == 2016)
    			getTownLastMonthDoing(result, map);
        }else if (orgLevel == OrganizationLevel.DISTRICT) {
            if (org.getOrgType().getId() == functionType) {
                String strDepartment = org.getDepartmentNo();
                if (strDepartment.endsWith(ThreeRecordsIssueConstants.COUNTY_LEDGER)) {
                	result =  queryJgData(map);
                	if(month == 1 && year == 2016)
            			getJgLastMonthDoing(result, map);
                } else {
                	result =  queryFunData(map);
                	if(month == 1 && year == 2016)
            			getFunLastMonthDoing(result, map);
                }
            }else{
            	result = queryAllData(map, orgId, functionType);
            	if(month == 1 && year == 2016)
        			getAllLastMonthDoing(result, map);
            }
        }
    	return result;
    }
    
    private void getVillageLastMonthDoing(Map<Integer, Map<Integer, Integer>> reportMap, Map<String, Object> map){
    	List<HistoryReportResult> result = accountReportDao.getVillageLastMonthDoing(map);
    	if(!result.isEmpty()){
    		dealHistoryResult(result.get(0), reportMap, 8);
    	}
    }
    
    private void getTownLastMonthDoing(Map<Integer, Map<Integer, Integer>> reportMap, Map<String, Object> map){
    	List<HistoryReportResult> result = accountReportDao.getTwonLastMonthDoing(map);
    	if(!result.isEmpty()){
    		dealHistoryResult(result.get(0), reportMap, 8);
    		dealHistoryResult(result.get(1), reportMap, 33);
    	}
    }
    
    private void getFunLastMonthDoing(Map<Integer, Map<Integer, Integer>> reportMap, Map<String, Object> map){
    	List<HistoryReportResult> result = accountReportDao.getFunLastMonthDoing(map);
    	if(!result.isEmpty()){
    		dealHistoryResult(result.get(0), reportMap, 18);
    	}
    }
    private void getJgLastMonthDoing(Map<Integer, Map<Integer, Integer>> reportMap, Map<String, Object> map){
    	List<HistoryReportResult> result = accountReportDao.getJgLastMonthDoing(map);
    	if(!result.isEmpty()){
    		dealHistoryResult(result.get(0), reportMap, 9);
    		dealHistoryResult(result.get(1), reportMap, 10);
    		dealHistoryResult(result.get(2), reportMap, 11);
    		dealHistoryResult(result.get(3), reportMap, 12);
    	}
    }
    private void getAllLastMonthDoing(Map<Integer, Map<Integer, Integer>> reportMap, Map<String, Object> map){
    	map.put("orgId", "-1");
    	List<HistoryReportResult> result = accountReportDao.getallLastMonthDoing(map);
    	if(!result.isEmpty()){
    		dealHistoryResult(result.get(0), reportMap, 27);
    		dealHistoryResult(result.get(1), reportMap, 28);
    		dealHistoryResult(result.get(2), reportMap, 29);
    		dealHistoryResult(result.get(3), reportMap, 30);
    	}
    }
    
    private Map<Integer, Map<Integer, Integer>> queryVillageData(Map<String, Object> map){
    	Map<Integer, Map<Integer, Integer>> reportMap = initialMap(xAll, yVillage);
    	List<AccountReportResult> result = accountReportDao.queryVillagetData(map);//村社区
    	return fillReportmap(result, reportMap);
    }
    
    private Map<Integer, Map<Integer, Integer>> queryTownData(Map<String, Object> map, Long orgId){//乡镇
    	List<Organization> childOrgs = organizationDubboRemoteService.findOrganizationsByParentId(orgId);
    	List<Long> childOrgIds = new ArrayList<Long>();//下辖所有村社区
    	for(Organization childOrg : childOrgs){
    		childOrgIds.add(childOrg.getId());
    	}
    	map.put("childOrgIds", childOrgIds);
    	Map<Integer, Map<Integer, Integer>> reportMap = initialMap(xAll, yTown);
    	List<AccountReportResult> result = accountReportDao.queryTownData(map);
    	return fillReportmap(result, reportMap);
    }
    
    private Map<Integer, Map<Integer, Integer>> queryJgData(Map<String, Object> map){//台账办
    	Map<Integer, Map<Integer, Integer>> reportMap = initialMap(xAll, yJg);
    	List<AccountReportResult> result = accountReportDao.queryJgData(map);
    	return fillReportmap(result, reportMap);
    }
    
    private Map<Integer, Map<Integer, Integer>> queryFunData(Map<String, Object> map){//职能部门
    	Map<Integer, Map<Integer, Integer>> reportMap = initialMap(xAll, yFun);
    	List<AccountReportResult> result = accountReportDao.queryFunData(map);
    	return fillReportmap(result, reportMap);
    }
    
    private Map<Integer, Map<Integer, Integer>> queryAllData(Map<String, Object> map, long orgId, long functionType){//全县
    	List<PropertyDict> pros = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL);
		Long districtLevel = getGridLevel(pros,OrganizationLevel.DISTRICT);//县级level
		Long townLevel = getGridLevel(pros,OrganizationLevel.TOWN);//乡镇level
		Long villageLevel = getGridLevel(pros,OrganizationLevel.VILLAGE);//村社区level
    	
//    	Organization cuuOrg = organizationDubboRemoteService.getSimpleOrgById(orgId);
//    	List<Long> allVillageOrgIds = new ArrayList<Long>();//全县村社区
//        List<Long> allTownOrgIds = new ArrayList<Long>();//全县乡镇
//        List<Long> allFunOrgIds = new ArrayList<Long>();//全县职能部门
//        List<Long> allOrgIds = new ArrayList<Long>();//所有部门
//    	List<Organization> allChildOrgs = accountReportDao.queryAllChildOrgByorgInternalCode(cuuOrg.getOrgInternalCode());
//    	for(Organization org : allChildOrgs){
//    		int orgLevel = propertyDictDubboService.getPropertyDictById(org.getOrgLevel().getId()).getInternalId();
//    		if(orgLevel == OrganizationLevel.VILLAGE){
//    			allVillageOrgIds.add(org.getId());
//    			allOrgIds.add(org.getId());
//    		}else if(orgLevel == OrganizationLevel.TOWN){
//    			allTownOrgIds.add(org.getId());
//    			allOrgIds.add(org.getId());
//    		}else if(orgLevel == OrganizationLevel.DISTRICT){
//    			if (org.getOrgType().getId() == functionType && !orgIsJg(org)) {
//    				allFunOrgIds.add(org.getId());
//    				allOrgIds.add(org.getId());
//    			}
//    		}
//    	}
    	
    	map.put("districtLevel", districtLevel);
    	map.put("townLevel", townLevel);
    	map.put("villageLevel", villageLevel);
    	
//    	map.put("allVillage", allVillageOrgIds);
//    	map.put("allTown", allTownOrgIds);
//    	map.put("allFun", allFunOrgIds);
//    	map.put("allOrg", allOrgIds);
    	Map<Integer, Map<Integer, Integer>> reportMap = initialMap(xAll, yCounty);
    	List<AccountReportResult> result = accountReportDao.queryAllData(map);
    	return fillReportmap(result, reportMap);
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
    
    private boolean orgIsJg(Organization org){
    	return org.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_LEDGER)
    	|| org.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_COMMITTEE)
    	|| org.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_JOINT);
    }
    
    /**
     * 根据查询结果向map集合填充数据
     * @param result
     * @return
     */
    private Map<Integer, Map<Integer, Integer>> fillReportmap(List<AccountReportResult> result,Map<Integer, Map<Integer, Integer>> reportMap){
    	for(AccountReportResult r : result){
    		setMap(ReportXName.getInstance(propertyDictDubboService).getX((long)r.getLedgerType()), r.getY(), r.getSum(), reportMap);
    	}
    	return dealXSubtotalTotal(reportMap);
    }
    
    /**
     * 处理台账类型小计合计
     * @param reportMap
     */
    private Map<Integer, Map<Integer, Integer>> dealXSubtotalTotal(Map<Integer, Map<Integer, Integer>> reportMap){
    	for(Entry<Integer, Map<Integer, Integer>> entry : reportMap.entrySet()){
    		Map<Integer, Integer> xMap = entry.getValue();
    		int peopleSubtotal = 0;
    		for(int x = ReportXName.PEOPLE_WATER_Y; x <= ReportXName.PEOPLE_OTHER_Y; x++){
    			peopleSubtotal += xMap.get(x);
    		}
    		xMap.put(ReportXName.PEOPLE_ALL_Y, peopleSubtotal);//民生小计
    		
    		int poorSubtotal = 0;
    		for(int x = ReportXName.POOR_HOUSE_Y; x <= ReportXName.POOR_OTHER_Y; x++){
    			poorSubtotal += xMap.get(x);
    		}
    		xMap.put(ReportXName.POOR_ALL_Y, poorSubtotal);//困难小计
    		
    		int steadySubtotal = 0;
    		for(int x = ReportXName.STEADY_INVOLVING_LAW_Y; x <= ReportXName.STEADY_OTHER_STEADY_Y; x++){
    			steadySubtotal += xMap.get(x);
    		}
    		xMap.put(ReportXName.STEADY_ALL_Y, steadySubtotal);//稳定小计
    				
    		xMap.put(ReportXName.ALL_Y, peopleSubtotal + poorSubtotal + steadySubtotal);//合计
    	}
    	return reportMap;
    }
    
    /**
     * 累计数据进入报表map
     * @param x
     * @param y
     * @param value
     * @param reportMap
     * @return
     */
    private void setMap(int x,int y,int value,Map<Integer, Map<Integer, Integer>> reportMap){
        reportMap.get(y).put(x,value);
    }
    
    /**
     * 初始化报表map
     *
     * @param x
     * @param y
     * @return
     */
    private Map<Integer, Map<Integer, Integer>> initialMap(int x, int y) {
        Map<Integer, Map<Integer, Integer>> reportMap = new ListOrderedMap();
        for (int i = 1; i <= y; i++) {
            Map<Integer, Integer> xMap = new ListOrderedMap();
            for (int j = 1; j <= x; j++) {
                xMap.put(j, 0);
            }
            reportMap.put(i, xMap);
        }
        return reportMap;
    }
    
    /**
     * 删除当前月报表数据
     * @param year
     * @param month
     */
    public void deleteCurrentMonthData(int year, int month){
//    	accountReportDao.deleteCurrentMonthData();
    }
    
    /**
     * 生成本月报表数据
     * @param year
     * @param month
     */
    private void initCurrentMonthData(int year, int month){
    	accountReportDao.initCurrentMonthData();
    }
    
    /**
     * 判断数据库表是否存在,创建表、索引
     *
     * @param year
     * @param month
     */
    private boolean createHistoryStatisticByType(int year) {
        boolean isCreate = tableService.createAnalyseTable(
                AnalyseUtilNew.ACCOUNT_REPORTDATA_TABLE_NAME,
                AnalyseUtilNew.ACCOUNT_REPORTDATA_TABLE_NAME_SQL, year);
        if(isCreate){
        	tableService.createAnalyseIndex(AnalyseUtilNew.ACCOUNT_REPORTDATA_TABLE_NAME + "_" + year, "countType", "orgId");
        }
        return isCreate;
    }
}
