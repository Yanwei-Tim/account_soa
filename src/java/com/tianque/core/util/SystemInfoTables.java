package com.tianque.core.util;

import java.util.HashMap;
import java.util.Map;

public class SystemInfoTables {

	public static final String VILLAGEPROFILES_KEY = "VILLAGEPROFILES";
	public static final String PRIMARYORGANIZATIONS_30_KEY = "PRIMARYORGANIZATIONS_30";
	public static final String PRIMARYORGANIZATIONS_10_KEY = "PRIMARYORGANIZATIONS_10";
	public static final String PRIMARYORGANIZATIONS_20_KEY = "PRIMARYORGANIZATIONS_20";
	public static final String PRIMARYORGANIZATIONS_40_KEY = "PRIMARYORGANIZATIONS_40";
	public static final String PRIMARYORGANIZATIONS_50_KEY = "PRIMARYORGANIZATIONS_50";
	public static final String PRIMARYORGANIZATIONS_60_KEY = "PRIMARYORGANIZATIONS_60";
	public static final String POSITIVEINFOS_KEY = "POSITIVEINFOS";
	public static final String RECTIFICATIVEPERSONS_KEY = "RECTIFICATIVEPERSONS";
	public static final String IDLEYOUTHS_KEY = "IDLEYOUTHS";
	public static final String MENTALPATIENTS_KEY = "MENTALPATIENTS";
	public static final String DRUGGYS_KEY = "DRUGGYS";
	public static final String SUPERIORVISITS_KEY = "SUPERIORVISITS";
	public static final String POORPEOPLE_KEY = "POORPEOPLE";
	public static final String DANGEROUSGOODSPRACTITIONERS_KEY = "DANGEROUSGOODSPRACTITIONERS";
	public static final String ATTENTIONOBJECTS_KEY = "ATTENTIONOBJECTS";
	public static final String OVERSEAPERSONNEL_KEY = "OVERSEAPERSONNEL";
	public static final String ENTERPRISES_safetyProductionKey_KEY = "ENTERPRISES_safetyProductionKey";
	public static final String ENTERPRISES_fireSafetyKey_KEY = "ENTERPRISES_fireSafetyKey";
	public static final String ENTERPRISES_securityKey_KEY = "ENTERPRISES_securityKey";
	public static final String SCHOOLS_KEY = "SCHOOLS";
	public static final String OTHERLOCALES_KEY = "OTHERLOCALES";
	public static final String LETTINGHOUSES_KEY = "LETTINGHOUSES";
	public static final String ENTERPRISES_enterpriseKey_KEY = "ENTERPRISES_enterpriseKey";
	public static final String NEWSOCIETYFEDERATIONS_KEY = "NEWSOCIETYFEDERATIONS";
	public static final String TRAMPRESIDENTS_KEY = "TRAMPRESIDENTS";
	public static final String INHABITANTS_KEY = "INHABITANTS";
	public static final String FAMILYS_KEY = "FAMILYS";
	public static final String ESTATEINFORMATION_KEY = "ESTATEINFORMATION";
	public static final String SPECIALCAREGROUPS_KEY = "SPECIALCAREGROUPS";
	public static final String HANDICAPPEDS_KEY = "HANDICAPPEDS";
	public static final String ELDERLYPEOPLE_KEY = "ELDERLYPEOPLE";
	public static final String FAMILIESMOVEDINFORMATION_KEY = "FAMILIESMOVEDINFORMATION";
	public static final String WORKDIARYS_KEY = "WORKDIARYS";
	public static final String DAILYLOGS_KEY = "DAILYLOGS";
	public static final String WORKINGRECORD_KEY = "WORKINGRECORD";
	public static final String ISSUESNEW_KEY = "ISSUESNEW";
	// public static final String SPECIALTRADES_KEY="SPECIALTRADES";
	// public static final String HOSPITALS_KEY="HOSPITALS";
	// public static final String COMMONCOMPLEXPLACES_KEY="COMMONCOMPLEXPLACES";
	public static final String SERVICEPERSONS_KEY = "SERVICEPERSONS";
	public static final String STATEMENTSTATISTICS_KEY = "STATEMENTSTATISTICS";
	public static final String SYSTEMLOGS_KEY = "SYSTEMLOGS";
	public static final String ORGLOGINSTANALS_KEY = "ORGLOGINSTANALS";
	public static final String ISSUEDEALSTATS_KEY = "ISSUEDEALSTATS";
	public static final String ISSUESTATUESTANALS_KEY = "ISSUESTATUESTANALS";
	public static final String ISSUETYPESTANALS_KEY = "ISSUETYPESTANALS";
	public static final String USERS_KEY = "USERS";
	public static final String USERHASMULTIZONES_KEY = "USERHASMULTIZONES";
	public static final String EVALUATES_KEY = "EVALUATES";
	public static final String EXAMINESCORES_KEY = "EXAMINESCORES";
	public static final String HISTORICALISSUES_KEY = "HISTORICALISSUES";
	public static final String PUBLICLTYCASS_KEY = "PUBLICLTYCASS";
	public static final String SMSRECEIVEDBOXS_KEY = "SMSRECEIVEDBOXS";
	public static final String WORKBULLETINS_KEY = "WORKBULLETINS";
	public static final String REPORTS_KEY = "REPORTS";
	public static final String FAMILYNUM_KEY = "FAMILYNUM";
	public static final String INHABITANTNUM_KEY = "INHABITANTNUM";
	public static final String INHABITANTCONNECT_KEY = "INHABITANTCONNECT";
	public static final String SYSTEMHIGHLOGS_KEY = "SYSTEMHIGHLOGS";
	public static final String SIGNIFICANTISSUEDEALS_KEY = "SIGNIFICANTISSUEDEALS";
	public static final String KEYAREASOFINVESTIGATIONINFOS_KEY = "KEYAREASOFINVESTIGATIONINFOS";
	public static final String STATREGRADEDPOINT_KEY = "STATREGRADEDPOINT";
	
	public static final String VILLAGEPROFILES_DISPLAYNAME = "基础信息-辖区信息";
	public static final String PRIMARYORGANIZATIONS_30_DISPLAYNAME = "基础信息-基层综治组织";
	public static final String PRIMARYORGANIZATIONS_10_DISPLAYNAME = "基础信息-基层党组织";
	public static final String PRIMARYORGANIZATIONS_20_DISPLAYNAME = "基础信息-基层自治组织";
	public static final String PRIMARYORGANIZATIONS_40_DISPLAYNAME = "基础信息-群防群治队伍";
	public static final String PRIMARYORGANIZATIONS_50_DISPLAYNAME = "基础信息-社会志愿者队伍";
	public static final String PRIMARYORGANIZATIONS_60_DISPLAYNAME = "基础信息-专项工作领导小组";

	public static final String POSITIVEINFOS_DISPLAYNAME = "基础信息-刑释解教人员";
	public static final String RECTIFICATIVEPERSONS_DISPLAYNAME = "基础信息-社区矫正人员";
	public static final String IDLEYOUTHS_DISPLAYNAME = "基础信息-重点青少年";
	public static final String MENTALPATIENTS_DISPLAYNAME = "基础信息-精神病人员";
	public static final String DRUGGYS_DISPLAYNAME = "基础信息-吸毒人员";
	
	public static final String SUPERIORVISITS_DISPLAYNAME = "基础信息-重点上访人员";
	public static final String POORPEOPLE_DISPLAYNAME = "基础信息-需要救助人员";
	public static final String DANGEROUSGOODSPRACTITIONERS_DISPLAYNAME = "基础信息-危险品从业人员";
	public static final String ATTENTIONOBJECTS_DISPLAYNAME = "基础信息-其他人员";
	public static final String OVERSEAPERSONNEL_DISPLAYNAME = "基础信息-境外人员";

	public static final String ENTERPRISES_safetyProductionKey_DISPLAYNAME = "基础信息-安全生产重点";
	public static final String ENTERPRISES_fireSafetyKey_DISPLAYNAME = "基础信息-消防安全重点";
	public static final String ENTERPRISES_securityKey_DISPLAYNAME = "基础信息-治安重点";
	public static final String SCHOOLS_DISPLAYNAME = "基础信息-学校";
	public static final String OTHERLOCALES_DISPLAYNAME = "基础信息-其他场所";

	public static final String LETTINGHOUSES_DISPLAYNAME = "基础信息-出租房";

	public static final String ENTERPRISES_enterpriseKey_DISPLAYNAME = "基础信息-规上企业";

	public static final String NEWSOCIETYFEDERATIONS_DISPLAYNAME = "基础信息-社会组织";

	public static final String TRAMPRESIDENTS_DISPLAYNAME = "流动人口";

	public static final String INHABITANTS_DISPLAYNAME = "基础信息-常住人口";
	public static final String INHABITANTCONNECT_DISPLAYNAME = "基础信息-常住人口连接表";
	public static final String FAMILYS_DISPLAYNAME = "基础信息-常住家庭";
	public static final String ESTATEINFORMATION_DISPLAYNAME = "基础信息-房产信息";
	public static final String SPECIALCAREGROUPS_DISPLAYNAME = "基础信息-优抚对象";
	public static final String HANDICAPPEDS_DISPLAYNAME = "基础信息-残疾人";
	public static final String ELDERLYPEOPLE_DISPLAYNAME = "基础信息-老年人";
	public static final String FAMILYNUM_DISPLAYNAME = "基础信息-常家庭数量表";
	public static final String INHABITANTNUM_DISPLAYNAME = "基础信息-常在人口数量表";

	public static final String FAMILIESMOVEDINFORMATION_DISPLAYNAME = "基础信息-申请迁出人员列表";

	// public static final String SPECIALTRADES_DISPLAYNAME = "基础信息-特种行业";
	// public static final String HOSPITALS_DISPLAYNAME = "基础信息-医院";
	// public static final String COMMONCOMPLEXPLACES_DISPLAYNAME =
	// "基础信息-公共复杂场所";
	public static final String SERVICEPERSONS_DISPLAYNAME = "基础信息-辖区信息";

	public static final String WORKDIARYS_DISPLAYNAME = "日常工作";// 工作日志
	public static final String DAILYLOGS_DISPLAYNAME = "日常工作";// 我的帐台
	public static final String WORKINGRECORD_DISPLAYNAME = "日常工作";// 我的帐台
	public static final String WORKBULLETINS_DISPLAYNAME = "日常工作";
	public static final String REPORTS_DISPLAYNAME = "日常工作";
	public static final String SYSTEMHIGHLOGS_DISPLAYNAME = "日常工作";//系统高级日志
	
	

	public static final String STATEMENTSTATISTICS_DISPLAYNAME = "研判分析";
	public static final String ORGLOGINSTANALS_DISPLAYNAME = "研判分析";// 登录统计
	public static final String ISSUEDEALSTATS_DISPLAYNAME = "研判分析";// 事件处理统计
	public static final String ISSUESTATUESTANALS_DISPLAYNAME = "研判分析";// 事件状态统计
	public static final String ISSUETYPESTANALS_DISPLAYNAME = "研判分析";// 事件类型统计

	public static final String SYSTEMLOGS_DISPLAYNAME = "系统管理";// 系统日志
	public static final String USERS_DISPLAYNAME = "系统管理";// 用户表
	public static final String USERHASMULTIZONES_DISPLAYNAME = "系统管理";// 用户关联

	public static final String EVALUATES_DISPLAYNAME = "考核评估";// 评估表
	public static final String EXAMINESCORES_DISPLAYNAME = "考核评估";// 分值

	public static final String ISSUESNEW_DISPLAYNAME = "事件处理";// 事件处理
	public static final String HISTORICALISSUES_DISPLAYNAME = "事件处理";// 历史遗留表
	// public static final String OVERTIMEISSUELOG_DISPLAYNAME = "事件处理";
	public static final String PUBLICLTYCASS_DISPLAYNAME = "事件处理";// 宣传案例
	public static final String SIGNIFICANTISSUEDEALS_DISPLAYNAME = "事件处理";//重大矛盾纠纷排查调处情况表
	public static final String KEYAREASOFINVESTIGATIONINFOS_DISPLAYNAME = "事件处理";//治安重点地区情况表
	public static final String STATREGRADEDPOINT_DISPLAYNAME = "事件处理";
	

	public static final String SMSRECEIVEDBOXS_DISPLAYNAME = "互动交流";// 短信接收（上行表）

	public final static Map<String, String> keyTables = new HashMap<String, String>();

	static {
		keyTables.put(VILLAGEPROFILES_KEY, VILLAGEPROFILES_DISPLAYNAME);
		keyTables.put(PRIMARYORGANIZATIONS_30_KEY,PRIMARYORGANIZATIONS_30_DISPLAYNAME);
		keyTables.put(PRIMARYORGANIZATIONS_10_KEY,PRIMARYORGANIZATIONS_10_DISPLAYNAME);
		keyTables.put(PRIMARYORGANIZATIONS_20_KEY,PRIMARYORGANIZATIONS_20_DISPLAYNAME);
		keyTables.put(PRIMARYORGANIZATIONS_40_KEY,PRIMARYORGANIZATIONS_40_DISPLAYNAME);
		keyTables.put(PRIMARYORGANIZATIONS_50_KEY,PRIMARYORGANIZATIONS_50_DISPLAYNAME);
		keyTables.put(PRIMARYORGANIZATIONS_60_KEY,PRIMARYORGANIZATIONS_60_DISPLAYNAME);
		keyTables.put(POSITIVEINFOS_KEY, POSITIVEINFOS_DISPLAYNAME);
		keyTables.put(RECTIFICATIVEPERSONS_KEY,RECTIFICATIVEPERSONS_DISPLAYNAME);
		keyTables.put(IDLEYOUTHS_KEY, IDLEYOUTHS_DISPLAYNAME);
		keyTables.put(MENTALPATIENTS_KEY, MENTALPATIENTS_DISPLAYNAME);
		keyTables.put(DRUGGYS_KEY, DRUGGYS_DISPLAYNAME);
		keyTables.put(SUPERIORVISITS_KEY, SUPERIORVISITS_DISPLAYNAME);
		keyTables.put(POORPEOPLE_KEY, POORPEOPLE_DISPLAYNAME);
		keyTables.put(DANGEROUSGOODSPRACTITIONERS_KEY,DANGEROUSGOODSPRACTITIONERS_DISPLAYNAME);
		keyTables.put(ATTENTIONOBJECTS_KEY, ATTENTIONOBJECTS_DISPLAYNAME);
		keyTables.put(OVERSEAPERSONNEL_KEY, OVERSEAPERSONNEL_DISPLAYNAME);
		keyTables.put(ENTERPRISES_safetyProductionKey_KEY,ENTERPRISES_safetyProductionKey_DISPLAYNAME);
		keyTables.put(ENTERPRISES_fireSafetyKey_KEY,ENTERPRISES_fireSafetyKey_DISPLAYNAME);
		keyTables.put(ENTERPRISES_securityKey_KEY,ENTERPRISES_securityKey_DISPLAYNAME);
		keyTables.put(SCHOOLS_KEY, SCHOOLS_DISPLAYNAME);
		keyTables.put(OTHERLOCALES_KEY, OTHERLOCALES_DISPLAYNAME);
		keyTables.put(LETTINGHOUSES_KEY, LETTINGHOUSES_DISPLAYNAME);
		keyTables.put(ENTERPRISES_enterpriseKey_KEY,ENTERPRISES_enterpriseKey_DISPLAYNAME);
		keyTables.put(NEWSOCIETYFEDERATIONS_KEY,NEWSOCIETYFEDERATIONS_DISPLAYNAME);
		keyTables.put(TRAMPRESIDENTS_KEY, TRAMPRESIDENTS_DISPLAYNAME);
		keyTables.put(INHABITANTS_KEY, INHABITANTS_DISPLAYNAME);
		keyTables.put(FAMILYS_KEY, FAMILYS_DISPLAYNAME);
		keyTables.put(ESTATEINFORMATION_KEY, ESTATEINFORMATION_DISPLAYNAME);
		keyTables.put(SPECIALCAREGROUPS_KEY, SPECIALCAREGROUPS_DISPLAYNAME);
		keyTables.put(HANDICAPPEDS_KEY, HANDICAPPEDS_DISPLAYNAME);
		keyTables.put(ELDERLYPEOPLE_KEY, ELDERLYPEOPLE_DISPLAYNAME);
		keyTables.put(FAMILIESMOVEDINFORMATION_KEY,FAMILIESMOVEDINFORMATION_DISPLAYNAME);
		keyTables.put(WORKDIARYS_KEY, WORKDIARYS_DISPLAYNAME);
		keyTables.put(DAILYLOGS_KEY, DAILYLOGS_DISPLAYNAME);
		keyTables.put(WORKINGRECORD_KEY, WORKINGRECORD_DISPLAYNAME);
		keyTables.put(ISSUESNEW_KEY, ISSUESNEW_DISPLAYNAME);
		// keyTables.put(SPECIALTRADES_KEY, SPECIALTRADES_DISPLAYNAME);
		// keyTables.put(HOSPITALS_KEY, HOSPITALS_DISPLAYNAME);
		// keyTables.put(COMMONCOMPLEXPLACES_KEY,
		// COMMONCOMPLEXPLACES_DISPLAYNAME);
		keyTables.put(SERVICEPERSONS_KEY, SERVICEPERSONS_DISPLAYNAME);
		keyTables.put(STATEMENTSTATISTICS_KEY, STATEMENTSTATISTICS_DISPLAYNAME);
		keyTables.put(DAILYLOGS_KEY, DAILYLOGS_DISPLAYNAME);
		keyTables.put(WORKINGRECORD_KEY, WORKINGRECORD_DISPLAYNAME);
		keyTables.put(WORKBULLETINS_KEY, WORKBULLETINS_DISPLAYNAME);
		keyTables.put(REPORTS_KEY, REPORTS_DISPLAYNAME);
		keyTables.put(ORGLOGINSTANALS_KEY, ORGLOGINSTANALS_DISPLAYNAME);
		keyTables.put(ISSUEDEALSTATS_KEY, ISSUEDEALSTATS_DISPLAYNAME);
		keyTables.put(ISSUESTATUESTANALS_KEY, ISSUESTATUESTANALS_DISPLAYNAME);
		keyTables.put(ISSUETYPESTANALS_KEY, ISSUETYPESTANALS_DISPLAYNAME);
		keyTables.put(SYSTEMLOGS_KEY, SYSTEMLOGS_DISPLAYNAME);
		keyTables.put(USERS_KEY, USERS_DISPLAYNAME);
		keyTables.put(USERHASMULTIZONES_KEY, USERHASMULTIZONES_DISPLAYNAME);
		keyTables.put(EVALUATES_KEY, EVALUATES_DISPLAYNAME);
		keyTables.put(EXAMINESCORES_KEY, EXAMINESCORES_DISPLAYNAME);
		keyTables.put(HISTORICALISSUES_KEY, HISTORICALISSUES_DISPLAYNAME);
		keyTables.put(PUBLICLTYCASS_KEY, PUBLICLTYCASS_DISPLAYNAME);
		keyTables.put(SMSRECEIVEDBOXS_KEY, SMSRECEIVEDBOXS_DISPLAYNAME);
		keyTables.put(FAMILYNUM_KEY, FAMILYNUM_DISPLAYNAME);
		keyTables.put(INHABITANTNUM_KEY, INHABITANTNUM_DISPLAYNAME);
		keyTables.put(INHABITANTCONNECT_KEY, INHABITANTCONNECT_DISPLAYNAME);
		keyTables.put(SYSTEMHIGHLOGS_KEY, SYSTEMHIGHLOGS_DISPLAYNAME);
		keyTables.put(SIGNIFICANTISSUEDEALS_KEY, SIGNIFICANTISSUEDEALS_DISPLAYNAME);
		keyTables.put(KEYAREASOFINVESTIGATIONINFOS_KEY, KEYAREASOFINVESTIGATIONINFOS_DISPLAYNAME);
		keyTables.put(STATREGRADEDPOINT_KEY, STATREGRADEDPOINT_DISPLAYNAME);

	}

	public static String getKeytables(String key) {
		return keyTables.get(key);
	}

}
