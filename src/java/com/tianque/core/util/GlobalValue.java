/**
 * tianque-com.tianque.common.util-GlobalValue.java Created on Apr 7, 2009
 * Copyright (c) 2010 by 杭州天阙科技有限公司
 */
package com.tianque.core.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Title: ***<br>
 * 
 * @author <a href=mailto:nifeng@hztianque.com>倪峰</a><br>
 * 
 * @description ***<br/>
 * 
 * @version 1.0
 */
public class GlobalValue {

	public static String environment;

	public static boolean isInitApp = false;

	public static final String JOB_COOKIE = "JOB_COOKIE";

	public final static String LOGIN_SESSION_ID = "sid";
	public final static String OLD_LOGIN_SESSION_ID = "oldSid";
	public final static String CURRENT_ORG_ID = "currentOrgId";
	public final static String LOGIN_USER_ID = "userId";
	public final static String LOGIN_ORGANIZATION = "loginOrganization";
	public final static String PERMISSION_TYPE_MENU = "1";
	public final static String PERMISSION_TYPE_BUTTON = "2";
	public final static String PERMISSIONS = "permissions";
	public final static String NOT_HAVE_PERMISSION_RESULT = "notHavePermissionResult";
	public final static String LOGIN_FAILURE_MSG = "login_failure_msg";
	public final static String SHI_LAYER = "oracle_gis:GISSHIJIEREGION";
	public final static String XIAN_LAYER = "tianqueMap:GISXIANJIEREGIO";
	public final static String UPLOAD_ORG = "uploadOrg";
	public final static String CHECK_ORG_OR_NOT = "checkOrgOrNot";
	public final static String DATAFROM = "dataFrom";
	public final static String SQLS_TABLE_PATH = getProjectPath()
			+ File.separator + "database/tables";

	public static String getCreateSQLPath() {
		return getProjectPath() + File.separator
				+ "database/tables/001_createTables.sql";
	}

	public static final String JOB_TABLES = getProjectPath() + File.separator
			+ "database/tables/003_quarz.sql";

	public static String getVersionCleanTablesFileName(String version) {
		return getProjectPath() + File.separator + "database/tables/clean"
				+ version + "Tables.sql";
	}

	public static String getVersionCreateTablesFileName(String version) {
		return getProjectPath() + File.separator + "database/tables/create"
				+ version + "Tables.sql";
	}

	public final static List<String> DO_NOT_FIRST_LOGIN_VALIDATE_PATH = new ArrayList<String>();
	static {
		DO_NOT_FIRST_LOGIN_VALIDATE_PATH.add("login");
		DO_NOT_FIRST_LOGIN_VALIDATE_PATH.add("logout");
		DO_NOT_FIRST_LOGIN_VALIDATE_PATH.add("updatePasswordEmail");
		DO_NOT_FIRST_LOGIN_VALIDATE_PATH.add("toFirstPasswordUpdate");
		DO_NOT_FIRST_LOGIN_VALIDATE_PATH.add("mockLogin");
	}

	public final static List<String> IS_NOT_LOGIN_VALIDATE_PATH = new ArrayList<String>();
	static {
		String isNotLoginValidatePaths = GridProperties
				.getKey("isNotValidatePath");
		String[] isNotLoginValidatePathArray = isNotLoginValidatePaths
				.split("\\;");
		for (String path : isNotLoginValidatePathArray) {
			IS_NOT_LOGIN_VALIDATE_PATH.add(path);
		}
	}

	public static void main(String[] args) {
		System.out.println(getProjectPath());
	}

	/**
	 * getAppPath需要一个当前程序使用的Java类的class属性参数，它可以返回打包过的
	 * Java可执行文件（jar，war）所处的系统目录名或非打包Java程序所处的目录
	 * 
	 * @param cls为Class类型
	 * @return 返回值为该类所在的Java程序运行的目录
	 */
	public static String getProjectPath() {
		return System.getProperty("user.dir");
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		GlobalValue.environment = environment;
	}

}
