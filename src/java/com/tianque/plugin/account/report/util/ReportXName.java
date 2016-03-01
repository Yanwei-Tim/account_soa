package com.tianque.plugin.account.report.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.util.LedgerAccountReportVariable;
import com.tianque.userAuth.api.PropertyDictDubboService;

public class ReportXName {
	private static ReportXName xName;
	private Map<Long, Integer> ledgerTypeX = new HashMap<Long, Integer>();//X坐标集合
	
	public final static int ALL_Y = 1;//合计--Y坐标
	
	public final static int PEOPLE_ALL_Y = 2;//民生--小计--Y坐标
	public final static int PEOPLE_WATER_Y = 3;//民生--水利--Y坐标
	public final static int PEOPLE_TRAFFIC_Y = 4;//民生--交通--Y坐标
	public final static int PEOPLE_ENERGY_Y = 5;//民生--能源--Y坐标
	public final static int PEOPLE_EDUCATION_Y = 6;//民生--教育--Y坐标
	public final static int PEOPLE_SCIENCE_Y = 7;//民生--科技--Y坐标
	public final static int PEOPLE_MEDICAL_Y = 8;//民生--医疗--Y坐标
	public final static int PEOPLE_LABOR_Y = 9;//民生--劳动--Y坐标
	public final static int PEOPLE_ENVIRONMENT_Y = 10;//民生--环境--Y坐标
	public final static int PEOPLE_TOWN_Y = 11;//民生--城乡--Y坐标
	public final static int PEOPLE_AGRICULTURE_Y = 12;//民生--农业--Y坐标
	public final static int PEOPLE_OTHER_Y = 13;//民生--其它--Y坐标
	
	
	public final static int POOR_ALL_Y = 14;//困难--小计--Y坐标
	public final static int POOR_HOUSE_Y = 15;//困难--住房--Y坐标
	public final static int POOR_LIFE_Y = 16;//困难--生活--Y坐标
	public final static int POOR_MEDICAL_Y = 17;//困难--医疗--Y坐标
	public final static int POOR_OBTAIN_Y = 18;//困难--就业--Y坐标
	public final static int POOR_SCHOOL_Y = 19;//困难--就学--Y坐标
	public final static int POOR_OTHER_Y = 20;//困难--其他--Y坐标
	
	public final static int STEADY_ALL_Y = 21;//稳定--小计--Y坐标
	public final static int STEADY_INVOLVING_LAW_Y = 22;//稳定--涉法涉诉--Y坐标
	public final static int STEADY_SOIL_WATER_Y = 23;//稳定--林水土--Y坐标
	public final static int STEADY_GOVERNMENT_FINANCE_Y = 24;//稳定--惠农政策--Y坐标
	public final static int STEADY_CIVIL_AFFAIRS_Y = 25;//稳定--民政问题--Y坐标
	public final static int STEADY_POPULATION_TREATMENT_Y = 26;//稳定--人口与医疗--Y坐标
	public final static int STEADY_LABOR_SECURITY_Y = 27;//稳定--劳动保障--Y坐标
	public final static int STEADY_TRANSPORTATION_Y = 28;//稳定--交通运输--Y坐标
	public final static int STEADY_URBAN_CONSTRUCTION_Y = 29;//稳定--城建及综合执法--Y坐标
	public final static int STEADY_PARTY_Y = 30;//稳定--党纪政纪--Y坐标
	public final static int STEADY_EDUCATION_Y = 31;//稳定--教育--Y坐标
	public final static int STEADY_ENTERPRISE_Y = 32;//稳定--企业改制--Y坐标
	public final static int STEADY_ENVIRONMENTAL_SCIENCE_Y = 33;//稳定--环境保护--Y坐标
	public final static int STEADY_ORGANIZATION_Y = 34;//稳定--组织人事--Y坐标
	public final static int STEADY_OTHER_Y = 35;//稳定--其他类--Y坐标
	public final static int STEADY_PEOPLE_Y = 36;//稳定--重点人员--Y坐标
	public final static int STEADY_OTHER_STEADY_Y = 37;//稳定--其它--Y坐标
	private ReportXName(PropertyDictDubboService propertyDictDubboService){
		initPeopleLedger();
		initPoorType(propertyDictDubboService);
		initSteadyWorkType(propertyDictDubboService);
	}
	
	private void initPeopleLedger(){
		ledgerTypeX.put((long)LedgerConstants.PEOPLEASPIRATION_WATER, PEOPLE_WATER_Y);
		ledgerTypeX.put((long)LedgerConstants.PEOPLEASPIRATION_TRAFFIC, PEOPLE_TRAFFIC_Y);
		ledgerTypeX.put((long)LedgerConstants.PEOPLEASPIRATION_ENERGY, PEOPLE_ENERGY_Y);
		ledgerTypeX.put((long)LedgerConstants.PEOPLEASPIRATION_EDUCATION, PEOPLE_EDUCATION_Y);
		ledgerTypeX.put((long)LedgerConstants.PEOPLEASPIRATION_SCIENCE, PEOPLE_SCIENCE_Y);
		ledgerTypeX.put((long)LedgerConstants.PEOPLEASPIRATION_MEDICAL, PEOPLE_MEDICAL_Y);
		ledgerTypeX.put((long)LedgerConstants.PEOPLEASPIRATION_LABOR, PEOPLE_LABOR_Y);
		ledgerTypeX.put((long)LedgerConstants.PEOPLEASPIRATION_ENVIRONMENT, PEOPLE_ENVIRONMENT_Y);
		ledgerTypeX.put((long)LedgerConstants.PEOPLEASPIRATION_TOWN, PEOPLE_TOWN_Y);
		ledgerTypeX.put((long)LedgerConstants.PEOPLEASPIRATION_AGRICULTURE, PEOPLE_AGRICULTURE_Y);
		ledgerTypeX.put((long)LedgerConstants.PEOPLEASPIRATION_OTHER, PEOPLE_OTHER_Y);
	}
	
	private void initSteadyWorkType(PropertyDictDubboService propertyDictDubboService){
		List<PropertyDict> steadyWorkTypeList = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.STEADY_RECORD_WORK_TYPE);
		for(PropertyDict dict : steadyWorkTypeList){
			if(dict.getDisplayName().equals(LedgerAccountReportVariable.INVOLVING_LAW_KEY)){
				ledgerTypeX.put(dict.getId(), STEADY_INVOLVING_LAW_Y);
			}else if(dict.getDisplayName().equals(LedgerAccountReportVariable.SOIL_WATER_KEY)){
				ledgerTypeX.put(dict.getId(), STEADY_SOIL_WATER_Y);
			}else if(dict.getDisplayName().equals(LedgerAccountReportVariable.GOVERNMENT_FINANCE_KEY)){
				ledgerTypeX.put(dict.getId(), STEADY_GOVERNMENT_FINANCE_Y);
			}else if(dict.getDisplayName().equals(LedgerAccountReportVariable.CIVIL_AFFAIRS_KEY)){
				ledgerTypeX.put(dict.getId(), STEADY_CIVIL_AFFAIRS_Y);
			}else if(dict.getDisplayName().equals(LedgerAccountReportVariable.POPULATION_TREATMENT_KEY)){
				ledgerTypeX.put(dict.getId(), STEADY_POPULATION_TREATMENT_Y);
			}else if(dict.getDisplayName().equals(LedgerAccountReportVariable.LABOR_SECURITY_KEY)){
				ledgerTypeX.put(dict.getId(), STEADY_LABOR_SECURITY_Y);
			}else if(dict.getDisplayName().equals(LedgerAccountReportVariable.TRANSPORTATION_KEY)){
				ledgerTypeX.put(dict.getId(), STEADY_TRANSPORTATION_Y);
			}else if(dict.getDisplayName().equals(LedgerAccountReportVariable.URBAN_CONSTRUCTION_KEY)){
				ledgerTypeX.put(dict.getId(), STEADY_URBAN_CONSTRUCTION_Y);
			}else if(dict.getDisplayName().equals(LedgerAccountReportVariable.PARTY_KEY)){
				ledgerTypeX.put(dict.getId(), STEADY_PARTY_Y);
			}else if(dict.getDisplayName().equals(LedgerAccountReportVariable.EDUCATION_KEY)){
				ledgerTypeX.put(dict.getId(), STEADY_EDUCATION_Y);
			}else if(dict.getDisplayName().equals(LedgerAccountReportVariable.ENTERPRISE_KEY)){
				ledgerTypeX.put(dict.getId(), STEADY_ENTERPRISE_Y);
			}else if(dict.getDisplayName().equals(LedgerAccountReportVariable.ENVIRONMENTAL_SCIENCE_KEY)){
				ledgerTypeX.put(dict.getId(), STEADY_ENVIRONMENTAL_SCIENCE_Y);
			}else if(dict.getDisplayName().equals(LedgerAccountReportVariable.ORGANIZATION_KEY)){
				ledgerTypeX.put(dict.getId(), STEADY_ORGANIZATION_Y);
			}else if(dict.getDisplayName().equals(LedgerAccountReportVariable.OTHER_KEY)){
				ledgerTypeX.put(dict.getId(), STEADY_OTHER_Y);
			}else if(dict.getDisplayName().equals(LedgerAccountReportVariable.PEOPLE_KEY)){
				ledgerTypeX.put(dict.getId(), STEADY_PEOPLE_Y);
			}else if(dict.getDisplayName().equals(LedgerAccountReportVariable.OTHER_STEADY_KEY)){
				ledgerTypeX.put(dict.getId(), STEADY_OTHER_STEADY_Y);
			}
		}
	}
	
	private void initPoorType(PropertyDictDubboService propertyDictDubboService){
		List<PropertyDict> poorTypeList = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.LEDGER_POOR_PEOPLE_DIFFICULT_TYPE);
		for(PropertyDict dict : poorTypeList){
			if(dict.getDisplayName().equals(LedgerAccountReportVariable.HOUSE_KEY)){
				ledgerTypeX.put(dict.getId(), POOR_HOUSE_Y);
			}else if(dict.getDisplayName().equals(LedgerAccountReportVariable.LIFE_KEY)){
				ledgerTypeX.put(dict.getId(), POOR_LIFE_Y);
			}else if(dict.getDisplayName().equals(LedgerAccountReportVariable.MEDICAL_CARE_KEY)){
				ledgerTypeX.put(dict.getId(), POOR_MEDICAL_Y);
			}else if(dict.getDisplayName().equals(LedgerAccountReportVariable.OBTAIN_EMPLOYMENT_KEY)){
				ledgerTypeX.put(dict.getId(), POOR_OBTAIN_Y);
			}else if(dict.getDisplayName().equals(LedgerAccountReportVariable.SCHOOL_KEY)){
				ledgerTypeX.put(dict.getId(), POOR_SCHOOL_Y);
			}else if(dict.getDisplayName().equals(LedgerAccountReportVariable.OTHER_DEFICULT_KEY)){
				ledgerTypeX.put(dict.getId(), POOR_OTHER_Y);
			}
		}
	}
	
	
	public static synchronized ReportXName getInstance(PropertyDictDubboService propertyDictDubboService){
		if(xName == null)
			xName = new ReportXName(propertyDictDubboService);
		return xName;
	}
	
	public int getX(Long ledgerType){
		return ledgerTypeX.get(ledgerType);
	}
	
}
