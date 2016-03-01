package com.tianque.plugin.account.constants;

import java.util.HashMap;
import java.util.Map;

public class LedgerReportStatisticalFieldMapping {

	// 用于报表民生、稳定、困难群众字典项与VO属性信息对比
	private static final Map<String, String> THREE_RECORDS_ISSUE_MAPPING_MAP = new HashMap<String, String>();
	static {
		/*** 民生工作 */
		THREE_RECORDS_ISSUE_MAPPING_MAP.put("民生工作小计", "peopleAsWorkTotal");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put(LedgerReportType.PEOPLEASPIRATION_WATER, "waterResourceCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put(LedgerReportType.PEOPLEASPIRATION_TRAFFIC, "trafficCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put(LedgerReportType.PEOPLEASPIRATION_EDUCATION, "educationCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put(LedgerReportType.PEOPLEASPIRATION_MEDICAL, "healthMedicalCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put(LedgerReportType.PEOPLEASPIRATION_AGRICULTURE,
				"resourcesAgriculturalCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put(LedgerReportType.PEOPLEASPIRATION_ENERGY, "energyCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put(LedgerReportType.PEOPLEASPIRATION_LABOR, "socialLaborCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put(LedgerReportType.PEOPLEASPIRATION_ENVIRONMENT,
				"environmentalProtectionCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put(LedgerReportType.PEOPLEASPIRATION_TOWN,
				"planningAdviceManagementCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put(LedgerReportType.PEOPLEASPIRATION_SCIENCE, "scienceTechnologyCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put(LedgerReportType.PEOPLEASPIRATION_OTHER, "otherResourcesCount");
		/*** 困难群众 */
		THREE_RECORDS_ISSUE_MAPPING_MAP.put("困难群众小计", "ledgerPoorPeopleTotal");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put("生活", "lifeCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put("医疗", "medicalCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put("住房", "housingCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put("就学", "goSchoolCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put("就业", "employmentCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put("其他困难",
				"ledgerPoorPeopleOtherCount");
		/*** 稳定工作 */
		THREE_RECORDS_ISSUE_MAPPING_MAP.put("稳定工作小计", "steadyRecordWorkTotal");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put("涉法涉诉", "visitsCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put("林水土", "forestSoilCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put("惠农政策及村（社区）政务、财务",
				"favorablePoliciesCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put("民政问题",
				"civilAdministrationIssuesCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP
				.put("人口与医疗卫生", "populationMedicalCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put("劳动保障", "laborSocialSecurityCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put("交通运输", "transportationCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put("城建及综合执法",
				"urbanConstructionAndCLECount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put("党纪政纪", "cpcPartyDisciplinesCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put("教育",
				"steadyRecordWorkEducationCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put("企业改制",
				"enterpriseRestructuringCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put("环境保护",
				"steadyRecordWorkEnvironmentalProtectionCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put("组织人事",
				"organizationPersonnelCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put("其他稳定工作",
				"steadyRecordWorkOtherCount");
		THREE_RECORDS_ISSUE_MAPPING_MAP.put("重点人员", "keyPersonnelCount");
	}

	public static Map<String, String> getThreeRecordsIssueMappingMap() {
		return THREE_RECORDS_ISSUE_MAPPING_MAP;
	}
}
