package com.tianque.plugin.account.constants;

import java.util.ArrayList;
import java.util.List;

import com.tianque.domain.PropertyDict;

public class LedgerReportType {

	/*** 工作月报表 */
	public static final int MONTH_REPORT = 1;
	/*** 首页报表 */
	public static final int HOME_PAGE_REPORT = 2;
	/*** 年收集 */
	public static final int YEAR_COLLECT_REPORT = 3;
	/*** 月收集 */
	public static final int MONTH_COLLECT_REPORT = 4;
	/*** 月办结 */
	public static final int MONTH_DONE_REPORT = 5;
	/** 年月收集汇总 */
	public static final int YEAR_COLLECT_MONTH_SUM = 6;
	/** 年月收集详细 */
	public static final int YEAR_COLLECT_MONTH_DETAIL = 7;
	/** 年月收集办结汇总 */
	public static final int YEAR_COLLECT_DONE_SUM = 8;
	/** 年月收集办结详细 */
	public static final int YEAR_COLLECT_DONE_DETAIL = 9;
	/*** 村级工作月报表 */
	public static final String MONTH_REPORT_VILLAGE = "villageReport";
	/*** 镇级工作月报表 */
	public static final String MONTH_REPORT_TOWN = "townReport";
	/*** 县级工作月报表 */
	public static final String MONTH_REPORT_COUNTY = "countyReport";
	/*** 县级部门工作月报表 */
	public static final String MONTH_REPORT_COUNTY_DEPARTMENT = "countyDepartmentReport";
	/*** 村级工作报表 */
	public static final Integer REPORT_VILLAGE = 1;
	/*** 镇级工作报表 */
	public static final Integer REPORT_TOWN = 2;
	/*** 县级部门工作报表 */
	public static final Integer REPORT_COUNTY_DEPARTMENT = 3;
	/*** 县级工作报表 */
	public static final Integer REPORT_COUNTY = 4;
	/*** 用于民生报表分类信息对比 */
	public static List<PropertyDict> peopleAsPirationPropertyDicts = null;
	
	
	public static final String PEOPLEASPIRATION_WATER = "水利";
	public static final String PEOPLEASPIRATION_TRAFFIC = "交通";
	public static final String PEOPLEASPIRATION_EDUCATION = "民生教育";
	public static final String PEOPLEASPIRATION_MEDICAL = "民生医疗";
	public static final String PEOPLEASPIRATION_AGRICULTURE = "农业";
	public static final String PEOPLEASPIRATION_ENERGY = "能源";
	public static final String PEOPLEASPIRATION_LABOR = "劳动";
	public static final String PEOPLEASPIRATION_ENVIRONMENT = "环境信息";
	public static final String PEOPLEASPIRATION_TOWN = "城乡规划建设信息";
	public static final String PEOPLEASPIRATION_SCIENCE = "科技文体";
	public static final String PEOPLEASPIRATION_OTHER = "其它信息";
	

	public static List<PropertyDict> initPeopleAsPirationPropertyDicts() {
		if (peopleAsPirationPropertyDicts == null) {
			peopleAsPirationPropertyDicts = new ArrayList<PropertyDict>();
			peopleAsPirationPropertyDicts.add(createPropertyDict(11L, PEOPLEASPIRATION_WATER));
			peopleAsPirationPropertyDicts.add(createPropertyDict(12L, PEOPLEASPIRATION_TRAFFIC));
			peopleAsPirationPropertyDicts.add(createPropertyDict(13L, PEOPLEASPIRATION_EDUCATION));
			peopleAsPirationPropertyDicts.add(createPropertyDict(14L, PEOPLEASPIRATION_MEDICAL));
			peopleAsPirationPropertyDicts.add(createPropertyDict(15L, PEOPLEASPIRATION_AGRICULTURE));
			peopleAsPirationPropertyDicts.add(createPropertyDict(16L, PEOPLEASPIRATION_ENERGY));
			peopleAsPirationPropertyDicts
					.add(createPropertyDict(17L, PEOPLEASPIRATION_LABOR));
			peopleAsPirationPropertyDicts
					.add(createPropertyDict(18L, PEOPLEASPIRATION_ENVIRONMENT));
			peopleAsPirationPropertyDicts.add(createPropertyDict(19L,
					PEOPLEASPIRATION_TOWN));
			peopleAsPirationPropertyDicts.add(createPropertyDict(110L, PEOPLEASPIRATION_SCIENCE));
			peopleAsPirationPropertyDicts.add(createPropertyDict(111L, PEOPLEASPIRATION_OTHER));
			// peopleAsPirationPropertyDicts.add(createPropertyDict(0L,
			// "民生工作小计"));
		}
		return peopleAsPirationPropertyDicts;
	}

	public static PropertyDict getPeopleAsPirationPropertyDict(Long id) {
		if (null != id) {
			List<PropertyDict> peopleAsPirationPropertyDicts = initPeopleAsPirationPropertyDicts();
			for (PropertyDict dict : peopleAsPirationPropertyDicts) {
				if (dict.getId().equals(id)) {
					return dict;
				}
			}
		}
		return new PropertyDict();
	}

	private static PropertyDict createPropertyDict(Long id, String name) {
		PropertyDict pro = new PropertyDict();
		pro.setId(id);
		pro.setDisplayName(name);
		return pro;
	}

}
