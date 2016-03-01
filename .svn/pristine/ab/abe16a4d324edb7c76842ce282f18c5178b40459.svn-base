<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@page import="com.tianque.core.globalSetting.service.GlobalSettingService"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.tianque.core.globalSetting.util.GlobalSetting"%><s:action name="ajaxOrganization" var="getLoginOrg" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"></s:param>
</s:action>
<%
GlobalSettingService globalSettingService = (GlobalSettingService)WebApplicationContextUtils.getWebApplicationContext(this.getServletContext()).getBean("globalSettingService");
String sysTitle = globalSettingService.getGlobalValue(GlobalSetting.SYS_TITLE);
String sysBottomPage = globalSettingService.getGlobalValue(GlobalSetting.SYS_BOTTOM_PAGE);
String sysHeaderPage = globalSettingService.getGlobalValue(GlobalSetting.SYS_HEADER_PAGE);
request.setAttribute("sysHeaderPage",sysHeaderPage);
%>
<span><%=sysBottomPage %></span>
