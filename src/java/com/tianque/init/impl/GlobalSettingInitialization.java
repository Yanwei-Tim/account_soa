package com.tianque.init.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.init.Initialization;

public class GlobalSettingInitialization implements Initialization {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private GlobalSettingService globalSettingService;

	public GlobalSettingInitialization(GlobalSettingService globalSettingService) {
		this.globalSettingService = globalSettingService;
	}

	@Override
	public void init() throws Exception {
		Map<String, String> map = getInitDataMap();
		globalSettingService.updateGlobalSetting(map);
	}

	private Map<String, String> getDefaultInitDataMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(GlobalSetting.IS_SENDER_SOLR_MSG, "false");
		map.put(GlobalSetting.IS_USE_MEMCACHED, "false");
		map.put(GlobalSetting.SYS_TITLE, "基层社会管理综合信息平台");
		map.put(GlobalSetting.SYS_HEADER_PAGE, "基层社会管理综合信息平台");
		map.put(GlobalSetting.SYS_BOTTOM_PAGE, "技术支持：杭州天阙科技有限公司");
		map.put(GlobalSetting.FRONTPAGE_BOTTOM_PAGE, "技术支持：杭州天阙科技有限公司");

		map.put(GlobalSetting.DAILYLOG_PATH, "/home/admin/share/dailylog/");
		map.put(GlobalSetting.ISSUE_PATH, "/home/admin/share/issue/");
		map.put(GlobalSetting.TMP_PATH, "/home/admin/share/tmp/");
		map.put(GlobalSetting.MAIL_PATH, "/home/admin/share/mail/");
		map.put(GlobalSetting.UPLOADFILE_PATH, "/home/admin/share/upload/");

		map.put(GlobalSetting.IS_SENDER_SOLR_MSG, "false");
		map.put(GlobalSetting.IS_SENDER_JMS_MSG, "true");
		map.put(GlobalSetting.JMS_URL, "tcp://localhost:61616");
		map.put(GlobalSetting.SOLR_URL, "http://localhost:8088/");
		map.put(GlobalSetting.JMS_QUEUE_JNDI_NAME, "tianqueQueue");

		map.put(GlobalSetting.GIS_URL, "/openLayersMap/mapView.jsp");
		map.put(GlobalSetting.UNIFIEDSEARCH_URL, "http://localhost:8076/search/index.jsp");

		map.put(GlobalSetting.SIMPLE_RELEASE, "false");
		map.put(GlobalSetting.IS_RELATED, "true");

		map.put(GlobalSetting.ACTUAL_POPULATION_MUTEX, "true");
		map.put(GlobalSetting.CAN_MAINTAIN_BUSINESS_POPULATION, "true");
		map.put(GlobalSetting.EMPHASIS_BUSINESS_POPULATION, "true");
		map.put(GlobalSetting.VIEW_BUSINESS_POPULATION, "true");
		map.put(GlobalSetting.DELETE_BUSINESS_POPULATION, "true");
		map.put(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION, GlobalSetting.SYNC_ACTUAL_POPULATION);
		map.put(GlobalSetting.EMPHASIS_DEPENDENT_POPULATION_STATE, "true");
		map.put(GlobalSetting.POPULATION_BUSINESS_DATA, "true");
		/** 待办事项提醒消息模版 */
		map.put(GlobalSetting.NEED_DO_ISSUEPM_TITLE, "【事件名称】（【服务单号】）待办提醒！");
		map.put(GlobalSetting.NEED_DO_ISSUEPM_CONTENT, "【事件名称】（【服务单号】）已流转到本部门，请抓紧办理！");
		map.put(GlobalSetting.NEED_DO_MYISSUELIST_URL, "serviceWork-myIssueListManagement");
		/** 重大事项提醒消息模版 */
		map.put(GlobalSetting.SERIOUS_ISSUE_TITLE, "【事件名称】（【服务单号】）重大紧急事项提醒！");
		map.put(GlobalSetting.SERIOUS_ISSUE_CONTENT, "【事件发生时间】在【发生地点】发生【事件名称】（【服务单号】），涉及人数【涉及人数】人，请抓紧解决！");
		map.put(GlobalSetting.SERIOUS_ISSUE_URL, "serviceWork-myIssueListManagement");
		/** 事件信息反馈消息模版 */
		map.put(GlobalSetting.ISSUEPM_TITLE, "【事件名称】事件（【服务单号】）处理提醒！");
		map.put(GlobalSetting.ISSUEPM_CONTENT, "【事件名称】事件(【服务单号】)被【处理部门名称】于【处理时间】【操作类型】于【目标部门名称】。");
		map.put(GlobalSetting.MYISSUELIST_URL, "serviceWork-myIssueListManagement");
		/** 台账状态提醒消息模版 */
		map.put(GlobalSetting.START_DAILY_YEAR_PM_TITLE, "【台帐年份】年【台帐创建部门名称】直属下辖台帐目录已经启用,请及时维护!");
		map.put(GlobalSetting.START_DAILY_YEAR_PM_CONTENT, "【台帐年份】年【台帐创建部门名称】直属下辖台帐目录已经启用,请及时维护!");
		map.put(GlobalSetting.START_DAILY_YEAR_URL, "workingManageSystem-myWorkingRecordManagement");
		/** 定期类报表上报提醒消息模版 */
		map.put(GlobalSetting.STATED_REPORTS_REPORT_PM_TITLE, "【报表名称】已上报,请查阅！");
		map.put(GlobalSetting.STATED_REPORTS_REPORT_PM_CONTENT, "【报表名称】已于【上报时间】上报,请查阅！");
		map.put(GlobalSetting.STATED_REPORTS_REPORT_URL, "workingManageSystem-areaWorkingRecordManagement");
		/** 定期类报表催报提醒消息模版 */
		map.put(GlobalSetting.STATED_REPORTS_RUSHTO_PM_TITLE, "【报表名称】还未上报,请及时上报！");
		map.put(GlobalSetting.STATED_REPORTS_RUSHTO_PM_CONTENT, "【报表名称】还未上报,请及时上报！");
		map.put(GlobalSetting.STATED_REPORTS_RUSHTO_URL, "workingManageSystem-myWorkingRecordManagement");
		/** 定期类报表回退提醒消息模版 暂时未使用 */
		map.put(GlobalSetting.STATED_REPORTS_BACK_PM_TITLE, "【报表名称】已被【执行回退操作的部门】退回,请及时更新并重新上报！");
		map.put(GlobalSetting.STATED_REPORTS_BACK_PM_CONTENT, "【报表名称】已被【执行回退操作的部门】于【回退时间】退回,请及时更新并重新上报！");
		map.put(GlobalSetting.STATED_REPORTS_BACK_URL, "workingManageSystem-myWorkingRecordManagement");
		/** 非定期类报表上报提醒消息模版 */
		map.put(GlobalSetting.UN_STATED_REPORTS_REPORT_PM_TITLE, "【报表所属部门】上报【数量】条【报表类型】记录,请查阅！");
		map.put(GlobalSetting.UN_STATED_REPORTS_REPORT_PM_CONTENT, "【报表所属部门】于【上报时间】上报【数量】条【报表类型】记录,请查阅！");
		map.put(GlobalSetting.UN_STATED_REPORTS_REPORT_URL, "workingManageSystem-myWorkingRecordManagement");
		/** 非定期类报表回退提醒消息模版 暂时未使用 */
		map.put(GlobalSetting.UN_STATED_REPORTS_BACK_PM_TITLE, "【数量】条【报表类型】记录已被【执行回退操作的部门】退回，请注意！");
		map.put(GlobalSetting.UN_STATED_REPORTS_BACK_PM_CONTENT, "【数量】条【报表类型】记录已被【执行回退操作的部门】退回，请注意！");
		map.put(GlobalSetting.UN_STATED_REPORTS_BACK_URL, "workingManageSystem-myWorkingRecordManagement");
		/** 文件签收提醒消息模版 */
		map.put(GlobalSetting.URGE_DOCUMENT_PM_TITLE, "《【文件名称】》文件未签收,请及时签收");
		map.put(GlobalSetting.URGE_DOCUMENT_PM_CONTENT, "【发文单位】于【发文时间】发了《【文件名称】》文件,请及时签收！");
		map.put(GlobalSetting.RECEIVE_DOCUMENT_URL, "workingManageSystem-receiveDocumentsManagement");
		/** 共享资料提醒消息模版 */
		map.put(GlobalSetting.SHARING_INFORMATION_PM_TITLE, "【共享文件数量】份资料共享提醒！");
		map.put(GlobalSetting.SHARING_INFORMATION_PM_CONTENT,
				"【共享文件部门名称】的用户【共享文件用户姓名】于【共享时间】共享了【共享文件名称】，存放于【共享目录名称】目录下，请查阅！");
		map.put(GlobalSetting.SHARING_INFORMATION_URL, "workingManageSystem-sharingInformationManagement");
		/** 考核目录状态提醒消息模版 */
		map.put(GlobalSetting.EVALUATE_ACTIVESTANDARD_PM_TITLE, "【年份】年【考核目录名称】已经启用,请及时自评！");
		map.put(GlobalSetting.EVALUATE_ACTIVESTANDARD_PM_CONTENT, "【年份】年【考核目录名称】已经启用,请及时自评！");
		map.put(GlobalSetting.EVALUATE_ACTIVESTANDARD_URL, "evaluateManagement-selfEvaluate");
		/** 自评结果上报提醒消息模版 */
		map.put(GlobalSetting.EVALUATE_REPORT_PM_TITLE, "【自评部门】【考核标准时间】【考核标准名称】自评结果已上报,请及时审阅并评分！");
		map.put(GlobalSetting.EVALUATE_REPORT_PM_CONTENT, "【自评部门】【考核标准时间】【考核标准名称】自评结果已于【上报时间】上报,请及时审阅并评分！");
		map.put(GlobalSetting.EVALUATE_REPORT_URL, "evaluateManagement-evaluating");
		/** 自评结果催报提醒消息模版 */
		map.put(GlobalSetting.EVALUATE_RUSHTO_PM_TITLE, "【自评部门】【考核标准时间】【考核标准名称】自评结果还未上报,请及时上报！");
		map.put(GlobalSetting.EVALUATE_RUSHTO_PM_CONTENT, "【自评部门】【考核标准时间】【考核标准名称】自评结果还未上报,请及时上报！");
		map.put(GlobalSetting.EVALUATE_RUSHTO_URL, "evaluateManagement-selfEvaluate");
		/** 自评结果回退提醒消息模版 */
		map.put(GlobalSetting.EVALUATE_BACK_PM_TITLE, "【考核标准时间】【考核标准名称】已被【执行回退操作的部门】退回,请及时更新并重新上报！");
		map.put(GlobalSetting.EVALUATE_BACK_PM_CONTENT, "【自评部门】【考核标准时间】【考核标准名称】已被【执行回退操作的部门】于【回退时间】退回,请及时更新并重新上报！");
		map.put(GlobalSetting.EVALUATE_BACK_URL, "evaluateManagement-selfEvaluate");
		/** 社区矫正人员到期提醒消息模版 */
		map.put(GlobalSetting.RECTIFICATIVE_PERSON_PM_TITLE, "【时间】当天有【人员到期数量】个社区矫正人员已到期！");
		map.put(GlobalSetting.RECTIFICATIVE_PERSON_PM_CONTENT,
				"【时间】当天有【人员到期数量】个社区矫正人员已到期，系统自动把他们列为取消关注状态！请及时把他们转入为刑释解教人员！");
		map.put(GlobalSetting.RECTIFICATIVE_PERSON_URL, "peopleManageSystem-rectificativePersonManageMent");
		/** 刑释解教人员到期提醒消息模版 */
		map.put(GlobalSetting.POSITIVEINFO_PM_TITLE, "【时间】当天有【人员到期数量】个刑释解教人员已到期！");
		map.put(GlobalSetting.POSITIVEINFO_PM_CONTENT, "【时间】当天有【人员到期数量】个刑释解教人员已到期，系统自动把他们列为取消关注状态！");
		map.put(GlobalSetting.POSITIVEINFO_URL, "peopleManageSystem-positiveInfoManagement");
		/** 重点青少年到期提醒消息模版 */
		map.put(GlobalSetting.IDLEYOUTH_PM_TITLE, "【时间】当天有【人员到期数量】个重点青少年已到期！");
		map.put(GlobalSetting.IDLEYOUTH_PM_CONTENT, "【时间】当天有【人员到期数量】个重点青少年已到期，系统自动把他们列为取消关注状态！");
		map.put(GlobalSetting.IDLEYOUTH_URL, "peopleManageSystem-idleYouthManagement");
		/** 实有人口标记为老年人提醒消息模版 */
		map.put(GlobalSetting.ELDERLY_PEOPLE_PM_TITLE, "【时间】当天有【人员到期数量】个【实口类型】已满足老年人条件！");
		map.put(GlobalSetting.ELDERLY_PEOPLE_PM_CONTENT, "【时间】当天有【人员到期数量】个【实口类型】已满足老年人条件（满65周岁），系统自动把他们标记为老年人！");
		map.put(GlobalSetting.ELDERLY_PEOPLE_URL, "peopleManageSystem");

		return map;
	}

	protected Map<String, String> getInitDataMap() {
		return getDefaultInitDataMap();
	}

}
