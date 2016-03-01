package com.tianque.core.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GridProperties {
	private static Logger logger = LoggerFactory
			.getLogger(GridProperties.class);
	private static Properties properties = null;

	private static Properties getGridProperties() {
		if (properties == null) {
			properties = new Properties();
			try {
				properties.load(GridProperties.class.getClassLoader()
						.getResourceAsStream("grid.properties"));
			} catch (IOException e) {
				logger.error("加载grid.properties出错！");
			}
		}
		return properties;
	}

	public static String getKey(String key) {
		return getGridProperties().getProperty(key);
	}

	public static final int ORG_TREE_AUTOCOMPLETE_SEARCH_NUM = Integer
			.valueOf((String) getGridProperties().get(
					"orgTreeAutoCompleteSearchNum"));

	private static String fileSeparator = File.separator;

	public static final String CURRENT_VERSION = getGridProperties()
			.getProperty("currentVersion");
	public static final String DUBBO_WHITE_LIST = getGridProperties()
			.getProperty("dubbo.whiteList");
	// start 上传文件路径

	public static final String UPLOAD_FILE_FOLDER = "uploadFile"
			+ fileSeparator + "upload";

	public static final String ORGANIZATION_EXCEL_FOLDER = "uploadFile"
			+ fileSeparator + "upload";

	public static final String TMP = "uploadFile" + fileSeparator + "tmp";

	public static final String ISSUE_ATTACHFILE = "uploadFile" + fileSeparator
			+ "issue";

	public static final String DAILY_DIRECTORY = "uploadFile" + fileSeparator
			+ "dailylog";

	public static final String ITEM_ATTACHFILE = "uploadFile" + fileSeparator
			+ "issue";

	public static final String DOCUMENTS_ATTACHFILE = "uploadFile"
			+ fileSeparator + "documents";

	public static final String WORK_BULLETIN = "uploadFile" + fileSeparator
			+ "issue";

	public static final String DAILYLOG_PATH = "uploadFile" + fileSeparator
			+ "dailylog";

	public static final String WORKINGRECORD_PATH = "uploadFile"
			+ fileSeparator + "upload";

	public static final String MAIL_ATTACHFILE_PATH = "uploadFile"
			+ fileSeparator + "mail";

	public static final String DOWNLOAD_TEMP_FILE_FOLDER = "uploadFile"
			+ fileSeparator + "tmp";
	public static final String RESOURCEPOOL_PATH = "uploadFile" + fileSeparator
			+ "upload";
	public static final String SERVICERECORD_PATH = "uploadFile"
			+ fileSeparator + "upload";

	// 通知通报附件的上传路径
	public static final String PUBLICNOTICE_ATTACHFILE = "uploadFile"
			+ fileSeparator + "upload";

	// 民情日志附件的上传路径
	public static final String PEOPLELOG_ATTACHFILE = "uploadFile"
			+ fileSeparator + "upload";

	// end 上传文件路径

	public static final String BBS_BASEURL = getGridProperties().getProperty(
			"bbs.baseurl");

	public static final String IS_ASYNC_BBS_USER = getGridProperties()
			.getProperty(GlobalValue.environment + ".isAsyncBbsUser");

	public static final String RESOURCE_PATH = getGridProperties().getProperty(
			"production.resourcePath");

	public static final long SESSION_TIME_OUT = Long
			.valueOf(getGridProperties().getProperty("sessionTimeOut"));

	public static final String CURRENT_PROJECT = getGridProperties()
			.getProperty("currentProject");

	public static final String SYS_BEGIN_USE_YEAR = getGridProperties()
			.getProperty("sysBeginUseYear");

	public static final long MAX_MAIL_ATTACH_FILE_SIZE = 2 * 1024 * 1024;
	public static final long MAX_MAIL_SIZE = 15 * 1024 * 1024;
	public static final long SINGLE_MAIL_CONTENT_LENGTH = 200;

	public static final String TIANQUE_GRID_JS_VERSION = getGridProperties()
			.getProperty("tianqueGridJsVersion");

	public static final String TEMPORARYRESIDENT_UPLOAD = getGridProperties()
			.getProperty(GlobalValue.environment + ".temporaryResidentFolder");

	public static final String IS_NEED_UNCONCEPTEDSTATE = getGridProperties()
			.getProperty("isNeedUnConceptedState");

	public static final String OPENFIRESERVER = getGridProperties()
			.getProperty("openfireServer");

	public static final String WEB_APP_URL = getGridProperties().getProperty(
			"webAppUrl");

	static {
		logger.info("ORG_TREE_AUTOCOMPLETE_SEARCH_NUM:"
				+ ORG_TREE_AUTOCOMPLETE_SEARCH_NUM);
		logger.info("SYS_BEGIN_USE_YEAR:" + SYS_BEGIN_USE_YEAR);
		logger.info("UPLOAD_FILE_FOLDER:" + UPLOAD_FILE_FOLDER);
		logger.info("SESSION_TIME_OUT:" + SESSION_TIME_OUT);
	}

	public static final String GIS_INDEX_MAP = getGridProperties().getProperty(
			"gis.indexmap");

	public static final String GIS_BOUND_BUILD_DATA = getGridProperties()
			.getProperty("gis.boundBuildData");

	public static final String GIS_SERVER = getGridProperties().getProperty(
			"gis.server");

	/** 历史平台消息模版名称 */
	public static final String HISTORY_MESSAGE_TEMPLATE = getGridProperties()
			.getProperty("historyMessageTemplate");
	/** 社区矫正人员到期提醒消息模版名称 */
	public static final String RECTIFICATIVE_PERSON_MESSAGE_TEMPLATE = getGridProperties()
			.getProperty("rectificativePersonMessageTemplate");
	/** 刑释解教人员到期提醒消息模版名称 */
	public static final String POSITIVEINFO_MESSAGE_TEMPLATE = getGridProperties()
			.getProperty("positiveInfoMessageTemplate");
	/** 重点青少年到期提醒消息模版名称 */
	public static final String IDLEYOUTHM_ESSAGE_TEMPLATE = getGridProperties()
			.getProperty("idleYouthMessageTemplate");
	/** 实口标记为老年人提醒消息模版名称 */
	public static final String ELDERLY_PEOPLE_MESSAGE_TEMPLATE = getGridProperties()
			.getProperty("elderlyPeopleMessageTemplate");
	// 是否启用Schedule
	public static final boolean ISSCHEDULE = "true".equals(getGridProperties()
			.getProperty("isSchedule"));
	public static final String THREERECORDS_ATTACHFILE = "uploadFile"
			+ fileSeparator + "threeRecords";
}
