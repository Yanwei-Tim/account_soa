<!DOCTYPE html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:action name="ajaxOrganization" var="getLoginOrg" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"></s:param>
</s:action>
<%
GlobalSettingService globalSettingService = (GlobalSettingService)WebApplicationContextUtils.getWebApplicationContext(this.getServletContext()).getBean("globalSettingService");
String sysTitle = globalSettingService.getGlobalValue(GlobalSetting.SYS_TITLE);
String sysBottomPage = globalSettingService.getGlobalValue(GlobalSetting.SYS_BOTTOM_PAGE);
String sysHeaderPage = globalSettingService.getGlobalValue(GlobalSetting.SYS_HEADER_PAGE);
String simpleRelease = globalSettingService.getGlobalValue(GlobalSetting.SIMPLE_RELEASE);
request.setAttribute("sysHeaderPage",sysHeaderPage);
request.setAttribute("simpleRelease",simpleRelease);
%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.tianque.core.globalSetting.service.GlobalSettingService"%>
<%@page import="com.tianque.core.globalSetting.util.GlobalSetting"%>
<title><%=sysTitle %></title>
<jsp:include page="/includes/baseInclude.jsp" />
<jsp:include page="/includes/jsInclude.jsp" />
<jsp:include page="/includes/baseJs.jsp" />
<script type="text/javascript">
var setPNG=function(img,w,h) {
    if(navigator.userAgent.indexOf('MSIE 6')>-1){
        var i = "display:inline-block;";
        var s = "<span style=\"width:" + w + "px; height:" + h + "px;" + i + ";" + "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + img.src + "', sizingMethod='scale');\"></span>";
        img.outerHTML = s;
    }
};
$(function() {
	$.layout();
	$('<div id="baseLine"></div>').appendTo('body');
	$("#commonMenuBtn").click(function(){
		$(document).click(function(e){
			var $target=$(e.target);
			if(!($target.is("#commonMenuTip") || $target.closest("#commonMenuBtn")[0])){
				$("#commonMenuTip").hide();
				$("#commonMenuBtn").removeClass("common-menu-button-cur");
			}
		})
		if($("#commonMenuTip:hidden")[0]){
			$("#commonMenuTip").show();
			$(this).addClass("common-menu-button-cur");
		}else{
			$("#commonMenuTip").hide();
			$(this).removeClass("common-menu-button-cur");
		}
	})
	$("#commonMenuTip a").click(function(){
		var rel=$(this).attr("rel");
		showPageByTopMenu(rel);
	})
	$("#commonMenuTip").bgiframe();
});
</script>
</head>
<body>
	<div id="globalOrgBox"></div>
	<div class="ui-layout-north">
		<s:action namespace="/sysadmin/menuManage" name="getNavigationList"  executeResult="true" ></s:action>		
		<input type="hidden" id="currentOrgId"
		 orglevelinternalid="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getOrgLevel().getInternalId()" />" 
		 text="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getOrgName()" />" 
		 leafNum="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getSubCount()" />" 
		 value='<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getId()"/>' />
	</div>
	
	<div class="ui-layout-west">
		
	</div>
	<div class="slideResizer">
		<div class="slideToggler" title="缩进"></div>
	</div>
	<div class="ui-layout-center">
	<jsp:include page="/includes/breadcrumbTrail.jsp"></jsp:include>
		<div class="content">
			<div id="loading" style="display: none;color:#999;text-align:center;height:32px;line-height:32px;margin-top:200px;"><img
				src="${resource_path}/resource/images/loading.gif" alt="加载中..." style="vertical-align:middle;margin-right:5px;" />加载中，请稍候...</div>
			<div id="contentDiv"></div>
		</div>
	</div>
	<div class="ui-layout-south">
		<div id="bottom">
			<jsp:include page="/includes/footer.jsp"></jsp:include>
		</div>
	</div>
	<div id="jGrowl"></div>
	<s:action name="findMyFeaturesByUserId"  namespace="/workBench/moduelClickCount" executeResult="false" var='getMyFeatures'>
	</s:action>
	<div class="common-menu-button" id="commonMenuBtn" title="常用功能"><span class="menu-icon lq"></span><span class="menu-icon kY"></span><span class="menu-icon kZ"></span><span class="menu-icon la"></span></div>
	<div class="common-menu-tip" id="commonMenuTip">
		<h3>常用功能菜单</h3>
		<ul>
			<s:iterator value="#getMyFeatures.moduelClicks" var="tab">
				<li>
					<a href="${tab.url}" rel="${tab.rel}"><img onload="setPNG(this,16,16)" src="${tab.imgUrl }"/><span>${tab.permission.cname}</span></a>
				</li>
			</s:iterator>
		</ul>
	</div>
</body>
</html>