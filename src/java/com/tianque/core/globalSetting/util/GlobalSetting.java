package com.tianque.core.globalSetting.util;

import org.jivesoftware.smack.XMPPConnection;

public class GlobalSetting {

	public static final String ISSUE_PATH = "issuePath";
	public static final String MAIL_PATH = "mailPath";
	public static final String DAILYLOG_PATH = "dailylogPath";
	public static final String TMP_PATH = "tmpPath";
	public static final String UPLOADFILE_PATH = "uploadFilePath";
	public static final String DOWNLOADFOLDER_PATH = "downloadFolderPath";
	public static final String FILEMANAGE_PATH = "filemanagePath";
	public static final String WORKBULLETIN = "workBulletinPath";

	public static final String SYS_TITLE = "sysTitle";
	public static final String SYS_HEADER_PAGE = "sysHeaderPage";
	public static final String SYS_BOTTOM_PAGE = "sysBottomPage";
	public static final String FRONTPAGE_BOTTOM_PAGE = "frontpageBottomPage";

	public static final String IS_SENDER_SOLR_MSG = "isSenderSolrMsg";
	public static final String SOLR_URL = "solrUrl";
	public static final String IS_SENDER_JMS_MSG = "isSenderJmsMsg";
	public static final String JMS_URL = "jmsUrl";
	public static final String JMS_QUEUE_JNDI_NAME = "jmsQueueJdniName";

	public static final String IS_USE_MEMCACHED = "isUseMemecached";
	public static final String MEMCACHED_URLS = "memecachedUrls";

	public static final String MOBILE_VERSION = "mobileVersion";
	public static final String MOBILE_APK_PATH = "mobileApkPath";

	public static final String BACK_IMAGE = "backImage";
	public static final String PREV_IMAGE = "prevImage";
	public static final String BUTTON_IMAGE = "buttonImage";
	public static final String MOUSE_MOVE_IMAGE = "mouseMoveImage";
	public static final String LOGO_IMAGE = "logoImage";

	public static final String GIS_URL = "gisUrl";
	public static final String UNIFIEDSEARCH_URL = "unifiedSearchUrl";

	public static final String SIMPLE_RELEASE = "simpleRelease";

	public static final String ORGANIZATION_EXCEL_PATH = "organizationExcelPath";
	public static final String IS_RELATED = "isRelated";

	public static final String NOT_DEPENDENT = "notDependent";
	public static final String NOT_ADD_POPULATION = "notAddPopulation";
	public static final String SYNC_ACTUAL_POPULATION = "syncActualPopulation";

	public static final String ACTUAL_POPULATION_MUTEX = "actualPopulationMutex";
	public static final String CAN_MAINTAIN_BUSINESS_POPULATION = "canMaintainBusinessPopulation";
	public static final String EMPHASIS_BUSINESS_POPULATION = "emphasisBusinessPopulation";
	public static final String VIEW_BUSINESS_POPULATION = "viewBusinessPopulation";
	public static final String DELETE_BUSINESS_POPULATION = "deleteBusinessPopulation";
	public static final String BUSINESS_DEPENDENT_ACTUAL_POPULATION = "businessDependentActualPopulation";
	public static final String EMPHASIS_DEPENDENT_POPULATION_STATE = "emphasisDependentPopulationState";
	public static final String POPULATION_BUSINESS_DATA = "populationBusinessData";

	public static final String NEED_DO_ISSUEPM_TITLE = "needDoIssuePmTitle";
	public static final String NEED_DO_ISSUEPM_CONTENT = "needDoIssuePmContent";
	public static final String NEED_DO_MYISSUELIST_URL = "needDoIssueUrl";

	public static final String SERIOUS_ISSUE_TITLE = "seriousIssuePmTitle";
	public static final String SERIOUS_ISSUE_CONTENT = "seriousIssuePmContent";
	public static final String SERIOUS_ISSUE_URL = "seriousIssueUrl";

	public static final String ISSUEPM_TITLE = "issuePmTitle";
	public static final String ISSUEPM_CONTENT = "issuePmContent";
	public static final String MYISSUELIST_URL = "myIssueListUrl";

	public static final String START_DAILY_YEAR_PM_TITLE = "startDailyYearPmTitle";
	public static final String START_DAILY_YEAR_PM_CONTENT = "startDailyYearPmContent";
	public static final String START_DAILY_YEAR_URL = "myWorkingDailyUrl";

	public static final String STATED_REPORTS_REPORT_PM_TITLE = "statedReportsReportPmTitle";
	public static final String STATED_REPORTS_REPORT_PM_CONTENT = "statedReportsReportPmContent";
	public static final String STATED_REPORTS_REPORT_URL = "statedReportsReportUrl";

	public static final String STATED_REPORTS_RUSHTO_PM_TITLE = "statedReportsRushToPmTitle";
	public static final String STATED_REPORTS_RUSHTO_PM_CONTENT = "statedReportsRushToPmContent";
	public static final String STATED_REPORTS_RUSHTO_URL = "statedReportsRushToUrl";

	public static final String STATED_REPORTS_BACK_PM_TITLE = "statedReportsBackPmTitle";
	public static final String STATED_REPORTS_BACK_PM_CONTENT = "statedReportsBackPmContent";
	public static final String STATED_REPORTS_BACK_URL = "statedReportsBackUrl";

	public static final String UN_STATED_REPORTS_REPORT_PM_TITLE = "unStatedReportsReportPmTitle";
	public static final String UN_STATED_REPORTS_REPORT_PM_CONTENT = "unStatedReportsReportPmContent";
	public static final String UN_STATED_REPORTS_REPORT_URL = "unStatedReportsReportUrl";

	public static final String UN_STATED_REPORTS_BACK_PM_TITLE = "unStatedReportsBackPmTitle";
	public static final String UN_STATED_REPORTS_BACK_PM_CONTENT = "unStatedReportsBackPmContent";
	public static final String UN_STATED_REPORTS_BACK_URL = "unStatedReportsBackUrl";

	public static final String URGE_DOCUMENT_PM_TITLE = "urgeDocumentPmTitle";
	public static final String URGE_DOCUMENT_PM_CONTENT = "urgeDocumentPmContent";
	public static final String RECEIVE_DOCUMENT_URL = "receiveDocumentUrl";

	public static final String SHARING_INFORMATION_PM_TITLE = "sharingInformationPmTitle";
	public static final String SHARING_INFORMATION_PM_CONTENT = "sharingInformationPmContent";
	public static final String SHARING_INFORMATION_URL = "sharingInformationUrl";

	public static final String EVALUATE_ACTIVESTANDARD_PM_TITLE = "evaluateActiveStandardPmtTtle";
	public static final String EVALUATE_ACTIVESTANDARD_PM_CONTENT = "evaluateActiveStandardPmContent";
	public static final String EVALUATE_ACTIVESTANDARD_URL = "evaluateActiveStandardUrl";

	public static final String EVALUATE_REPORT_PM_TITLE = "evaluateReportPmtTtle";
	public static final String EVALUATE_REPORT_PM_CONTENT = "evaluateReportPmContent";
	public static final String EVALUATE_REPORT_URL = "evaluateReportUrl";

	public static final String EVALUATE_RUSHTO_PM_TITLE = "evaluateRushtoPmtTtle";
	public static final String EVALUATE_RUSHTO_PM_CONTENT = "evaluateRushtoPmContent";
	public static final String EVALUATE_RUSHTO_URL = "evaluateRushtoUrl";

	public static final String EVALUATE_BACK_PM_TITLE = "evaluateBackPmtTtle";
	public static final String EVALUATE_BACK_PM_CONTENT = "evaluateBackPmContent";
	public static final String EVALUATE_BACK_URL = "evaluateBackUrl";

	public static final String RECTIFICATIVE_PERSON_PM_TITLE = "rectificativePersonPmtTtle";
	public static final String RECTIFICATIVE_PERSON_PM_CONTENT = "rectificativePersonPmContent";
	public static final String RECTIFICATIVE_PERSON_URL = "rectificativePersonUrl";

	public static final String POSITIVEINFO_PM_TITLE = "positiveinfoPmtTtle";
	public static final String POSITIVEINFO_PM_CONTENT = "positiveinfoPmContent";
	public static final String POSITIVEINFO_URL = "positiveinfoBackUrl";

	public static final String IDLEYOUTH_PM_TITLE = "idleyouthPmtTtle";
	public static final String IDLEYOUTH_PM_CONTENT = "idleyouthPmContent";
	public static final String IDLEYOUTH_URL = "idleyouthUrl";

	public static final String ELDERLY_PEOPLE_PM_TITLE = "elderlyPeoplePmtTtle";
	public static final String ELDERLY_PEOPLE_PM_CONTENT = "elderlyPeoplePmContent";
	public static final String ELDERLY_PEOPLE_URL = "elderlyPeopleUrl";
	/**
	 * 视频会议——公钥
	 */
	public static final String VIDEO_CONFERENCE_PKI = "videocConferencePKI";
	/**
	 * 视频会议——密钥
	 */
	public static final String VIDEO_CONFERENCE_KEY = "videocConferenceKey";

	/**
	 * xmpp im的连接
	 */
	public static XMPPConnection conn = null;

}
