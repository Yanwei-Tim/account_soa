package com.tianque.plugin.account.constants;

import java.util.HashMap;
import java.util.Map;

public class LedgerPeopleTableName {
	public static final String WATER_TABLE_NAME = "ledgerwaterresource";
	public static final String TRAFFIC_TABLE_NAME = "ledgertraffic";
	public static final String EDUCATION_TABLE_NAME = "ledgereducation";
	public static final String MEDICAL_TABLE_NAME = "ledgermedical";
	public static final String AGRICULTURE_TABLE_NAME = "ledgeragriculture";
	public static final String ENERGY_TABLE_NAME = "ledgerenergy";
	public static final String LABOR_TABLE_NAME = "ledgerlabor";
	public static final String ENVIRONMENT_TABLE_NAME = "ledgerenvironment";
	public static final String TOWN_TABLE_NAME = "ledgertown";
	public static final String SCIENCE_TABLE_NAME = "ledgerscience";
	public static final String OTHER_TABLE_NAME = "ledgerother";
	
	public static final Map<Integer, String> TABLENAME = new HashMap<Integer, String>();
	static {
		TABLENAME.put(LedgerConstants.PEOPLEASPIRATION_WATER, WATER_TABLE_NAME);
		TABLENAME.put(LedgerConstants.PEOPLEASPIRATION_TRAFFIC, TRAFFIC_TABLE_NAME);
		TABLENAME.put(LedgerConstants.PEOPLEASPIRATION_EDUCATION, EDUCATION_TABLE_NAME);
		TABLENAME.put(LedgerConstants.PEOPLEASPIRATION_MEDICAL, MEDICAL_TABLE_NAME);
		TABLENAME.put(LedgerConstants.PEOPLEASPIRATION_AGRICULTURE, AGRICULTURE_TABLE_NAME);
		TABLENAME.put(LedgerConstants.PEOPLEASPIRATION_ENERGY, ENERGY_TABLE_NAME);
		TABLENAME.put(LedgerConstants.PEOPLEASPIRATION_LABOR, LABOR_TABLE_NAME);
		TABLENAME.put(LedgerConstants.PEOPLEASPIRATION_ENVIRONMENT, ENVIRONMENT_TABLE_NAME);
		TABLENAME.put(LedgerConstants.PEOPLEASPIRATION_TOWN, TOWN_TABLE_NAME);
		TABLENAME.put(LedgerConstants.PEOPLEASPIRATION_SCIENCE, SCIENCE_TABLE_NAME);
		TABLENAME.put(LedgerConstants.PEOPLEASPIRATION_OTHER, OTHER_TABLE_NAME);
	}

	public static String getLedgerTableName(Integer ledgerType) {
		return TABLENAME.get(ledgerType);
	}
}
