package com.tianque.plugin.account.constants;

import org.apache.commons.collections.map.ListOrderedMap;

import java.util.Map;

/**
 * Created by daniel on 2015/10/26.
 */
public class LedgerMonthReportType {

    public static final Map<Integer, String> ACCOUNT_TYPE = new ListOrderedMap();

    public static final String paPrefix= "PEOPLEASPIRATION";
    public static final String ppPrefix = "POORPEOPLE";
    public static final String sPrefix = "STEATY";
            
    public static final String paWater= paPrefix+"_WATER";
    public static final String paTraffic= paPrefix+"_TRAFFIC";
    public static final String paEnergy= paPrefix+"_ENERGY";
    public static final String paEducation= paPrefix+"_EDUCATION";
    public static final String paScience= paPrefix+"_SCIENCE";
    public static final String paMedical= paPrefix+"_MEDICAL";
    public static final String paLabor= paPrefix+"_LABOR";
    public static final String paEnvironment= paPrefix+"_ENVIRONMENT";
    public static final String paTown= paPrefix+"_TOWN";
    public static final String paAgriculture= paPrefix+"_AGRICULTURE";
    public static final String paOther= paPrefix+"_OTHER";

    public static final String ppHouse=ppPrefix+"_HOUSE";
    public static final String ppLife=ppPrefix+"_LIFE";
    public static final String ppMedical=ppPrefix+"_MEDICAL";
    public static final String ppEmployment =ppPrefix+"_EMPLOYMENT";
    public static final String ppSchool=ppPrefix+"_SCHOOL";
    public static final String ppOther=ppPrefix+"_OTHER";

    public static final String sInvolvingLaw= sPrefix+"_INVOLVING_LAW";
    public static final String sSoilWater= sPrefix+"_SOIL_WATER";
    public static final String sGovernmentFinance= sPrefix+"_GOVERNMENT_FINANCE";
    public static final String sCivilAffairs= sPrefix+"_CIVIL_AFFAIRS";
    public static final String sPopulationTreatment= sPrefix+"_POPULATION_TREATMENT";
    public static final String sLaborSecurity= sPrefix+"_LABOR_SECURITY";
    public static final String sTransportration= sPrefix+"_TRANSPORTATION";
    public static final String sUrbanConstruction= sPrefix+"_URBAN_CONSTRUCTION";
    public static final String sParty= sPrefix+"_PARTY";
    public static final String sEducation= sPrefix+"_EDUCATION";
    public static final String sEnterprise= sPrefix+"_ENTERPRISE";
    public static final String sEnvironmentScience= sPrefix+"_ENVIRONMENTAL_SCIENCE";
    public static final String sOraganization= sPrefix+"_ORGANIZATION";
    public static final String sOther= sPrefix+"_OTHER";
    public static final String sPeople= sPrefix+"_PEOPLE";
    public static final String sOtherPeople= sPrefix+"_OTHER_PEOPLE";

    static {

        ACCOUNT_TYPE.put(11, paWater);/*民生-水利*/
        ACCOUNT_TYPE.put(12, paTraffic);/*民生-交通*/
        ACCOUNT_TYPE.put(13, paEnergy);/*民生-能源*/
        ACCOUNT_TYPE.put(14, paEducation);/*民生-教育*/
        ACCOUNT_TYPE.put(15, paScience);/*民生-科技文体*/
        ACCOUNT_TYPE.put(16, paMedical);/*民生-医疗卫生*/
        ACCOUNT_TYPE.put(17, paLabor);/*民生-劳动与社会保障*/
        ACCOUNT_TYPE.put(18, paEnvironment);/*民生-环境保护*/
        ACCOUNT_TYPE.put(19, paTown);/*民生-城乡规划建设与管理*/
        ACCOUNT_TYPE.put(110, paAgriculture);/*民生-农业*/
        ACCOUNT_TYPE.put(111, paOther);/*民生-其他*/

        ACCOUNT_TYPE.put(21, ppHouse);/*困难-住房*/
        ACCOUNT_TYPE.put(22, ppLife);/*困难-生活*/
        ACCOUNT_TYPE.put(23, ppMedical);/*困难-医疗*/
        ACCOUNT_TYPE.put(23, ppEmployment);/*困难-就业*/
        ACCOUNT_TYPE.put(25, ppSchool);/*困难-就学*/
        ACCOUNT_TYPE.put(26, ppOther);/*困难-就学*/

        ACCOUNT_TYPE.put(31, sInvolvingLaw);/*稳定-涉法涉诉*/
        ACCOUNT_TYPE.put(32, sSoilWater);/*稳定-林水土*/
        ACCOUNT_TYPE.put(33, sGovernmentFinance);/*稳定-惠农政策及村（社区）政务财务*/
        ACCOUNT_TYPE.put(34, sCivilAffairs);/*稳定-民政问题*/
        ACCOUNT_TYPE.put(35, sPopulationTreatment);/*稳定-人口与医疗卫生*/
        ACCOUNT_TYPE.put(36, sLaborSecurity);/*稳定-劳动保障*/
        ACCOUNT_TYPE.put(37, sTransportration);/*稳定-交通运输*/
        ACCOUNT_TYPE.put(38, sUrbanConstruction);/*稳定-城建及综合执法*/
        ACCOUNT_TYPE.put(39, sParty);/*稳定-党纪政纪*/
        ACCOUNT_TYPE.put(310, sEducation);/*稳定-教育*/
        ACCOUNT_TYPE.put(311, sEnterprise);/*稳定-企业改制*/
        ACCOUNT_TYPE.put(312, sEnvironmentScience);/*稳定-环境保护*/
        ACCOUNT_TYPE.put(313, sOraganization);/*稳定-组织人事*/
        ACCOUNT_TYPE.put(314, sOther);/*稳定-其他类*/
        ACCOUNT_TYPE.put(315, sPeople);/*稳定-重点人员*/
        ACCOUNT_TYPE.put(316, sOtherPeople);/*稳定-其他*/
    }

}
