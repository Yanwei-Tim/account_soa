package com.tianque.plugin.account.constants;

import java.util.ArrayList;
import java.util.List;

public class LedgerReportRow {
	// 用于拼装报表列名
	public final static String LAST_MONTH = "上月";
	public final static String THIS_MONTH = "本月";
	public final static String NEXT_MONTH = "下月";
	public final static String CUMULATIVE = "累计";
	public final static String AMONG_THEM = "其中";
	public final static String VILLAGE = "村（社区）";
	public final static String TOWN = "乡镇";
	public final static String COUNTY = "县";
	public final static String COUNT = "数";
	public final static String GATHER = "收集";
	public final static String DEPARTMENT = "部门";

	public final static String TRANSFER = "转办";
	public final static String ACCEPTANCE = "受理";
	public final static String DECLARE = "申报";
	public final static String NEW = "新建";
	public final static String ESSENCE_SERCH = "实质";
	public final static String PHASE_SERCH = "阶段";
	public final static String PROCEDURE_SERCH = "程序";

	public final static String CREATE = "建账";
	public final static String END = "办结";
	public final static String ESSENCE = "实质性";
	public final static String PHASE = "阶段性";
	public final static String PROCEDURE = "程序性";
	public final static String REPORT_VILLAGES = "呈报乡镇";
	public final static String TRANSACTION = "办理中";
	public final static String COMBINED = "合计";
	public final static String LEVEL_REPORTED = "上报";
	public final static String DIRECT = "直接";
	public final static String COUNTY_GOVERNMENT_LEADERSHIP = "县委县政府及县级领导班子有关领导";
	public final static String COUNTY_NPC_LEGISLATION_SUGGESTIONS = "县人大议案、建议、意见";
	public final static String COUNTY_CPPCC_PROPOSAL_ADVICE_OPINION = "县政协提案、建议、意见";
	public final static String CREATE_PROCEDURE_END = "建账办结";
	public final static String VILLAGE_AMONG_REPORT_END = "村级呈报件办结";
	public final static String TOWN_AMONG_REPORT_COUNTY_REPORT = "其中乡镇建账呈报县台账办";
	public final static String VILLAGE_AMONG_REPORT_COUNTY_REPORT = "其中村级呈报件呈报县台账办";
	public final static String COUNTY_RECORD_HANDLE_AFFAIRS_TRANSFER = "县台账办转办";
	public final static String THIS_DEPARTMENT_CREATE = "本部门建账";
	public final static String THIS_DEPARTMENT_COLLECT = "本部门直接收集";
	public final static String SUPERIOR_COMPETENT_DEPARTMENT = "上级主管部门和县级领导班子有关领导";
	public final static String COUNTY_RECORD_HANDLE_AFFAIRS_TRANSFER_END = "县台账办转办件办结";
	public final static String COMPLAINT_COUNTY_DEPARTMENT = "转办县级部门";
	public final static String COUNTY_JOINT_CHIEFS_OF_STAFF_MEETING = "县联席会议";
	public final static String COUNTY_GOVERNMENT = "县委县政府";
	public final static String COUNTY_JOINT_CHIEFS_OF_STAFF_MEETING_TRANSACTION = "县联席会议办理中";
	public final static String COUNTY_GOVERNMENT_TRANSACTION = "县委县政府办理中";
	public final static String COMPLAINT_TRANSACTION = "转办件办理中";
	public final static String ASSIGNED = "交办";
	public final static String LAST_YEAR_PICK_UP_TURN = "上年接转";

	public final static List<String> rows = new ArrayList<String>();
	static {
		rows.add(CUMULATIVE + END + COUNT);
		rows.add(ESSENCE);
		rows.add(PHASE);
		rows.add(PROCEDURE);
		rows.add(AMONG_THEM + REPORT_VILLAGES + COUNT);
		rows.add(COUNTY_GOVERNMENT_LEADERSHIP + ASSIGNED + COUNT);
		rows.add(COUNTY_NPC_LEGISLATION_SUGGESTIONS + ASSIGNED + COUNT);
		rows.add(COUNTY_CPPCC_PROPOSAL_ADVICE_OPINION + COUNT);
	}
}
