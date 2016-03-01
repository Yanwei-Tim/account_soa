<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.tianque.core.util.ThreadVariable"%>
<%@page import="java.util.Date"%>
<%
	Date accessTime=null;
	if(ThreadVariable.getSession()!=null){
		accessTime=ThreadVariable.getSession().getAccessTime();
	}
%>

<%@page import="com.tianque.core.util.GridProperties"%>
<link rel="shortcut icon" href="${resource_path}/resource/images/favicon.ico" type="image/x-icon"/>
<link href="${resource_path}/resource/css/formgrid.css" rel="stylesheet" type="text/css"/>
<link href="${resource_path}/resource/css/reset.css" rel="stylesheet" type="text/css"/>
<link href="${resource_path}/resource/js/jqGrid/css/ui.jqgrid.css" rel="stylesheet" type="text/css"/>
<link href="${resource_path}/resource/js/poshytip/tip-yellowsimple/tip-yellowsimple.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="${resource_path}/resource/js/ext/css/ext-all.css" />
<link rel="stylesheet" type="text/css" id="switchSkin" />

<link type="text/css" href="${resource_path}/resource/js/imgareaselect/imgareaselect-default.css" rel="stylesheet"/>
<link href="${resource_path}/resource/js/reportList/css/sigmawidgets.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/resource/js/jrac/style.jrac.css" />
<script type="text/javascript" src="${resource_path}/resource/js/jquery.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/jquery.bgiframe.js"></script>
<script type="text/javascript" src="${resource_path}/resource/system/js/jqueryui/jquery-ui-1.9.2.custom.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/ui/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/ui/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/jsValidate/jquery.form.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/jsValidate/jquery.metadata.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/jsValidate/jquery.validate.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/jsValidate/additional-methods.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/jsValidate/messages_cn.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/validatePassword/digitalspaghetti.password.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/jqGrid/js/grid.locale-cn.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/jqGrid/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/jqGrid/plugins/grid.setcolumns.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/poshytip/jquery.poshytip.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/xheditor/xheditor-1.1.13-zh-cn.min.js"></script>
<SCRIPT type="text/javascript" src="${resource_path}/resource/js/selectInPlace/jquery-selectInPlace-0.1.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${resource_path}/resource/js/widget/uploadFile.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${resource_path}/resource/js/containers/dhtmlxchart.js"></SCRIPT>
<script type="text/javascript" src="${resource_path }/resource/js/ext/ext-base.js"></script>
<script type="text/javascript" src="${resource_path }/resource/js/ext/ext-all.js"></script>
<script type="text/javascript" src="${resource_path }/resource/js/uploadify/swfobject.js"></script>
<script type="text/javascript" src="${resource_path }/resource/js/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/reportList/sigmareport.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/reportList/sigmabase.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/highcharts/highcharts.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/highcharts/exporting.js"></script>
<SCRIPT type="text/javascript" src="${resource_path}/resource/js/imgareaselect/jquery.imgareaselect.pack.js"></SCRIPT>
<script type="text/javascript" src="${resource_path}/resource/js/jrac/jquery.jrac.js"></script>
<script type="text/javascript" src="${resource_path}/resource/system/js/paging/pagenav.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/system/js/widget/pagenav.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/jquery.raty.js"></script>
<script type="text/javascript" src="${resource_path}/resource/system/js/jquery.SuperSlide.js"></script>

<s:if test='!"production".equals(@com.tianque.core.util.GlobalValue@environment)'>
	<script type="text/javascript" src="${resource_path}/resource/js/widget/userAutocompele.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/js/widget/idCheckUtil.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/js/widget/dialog.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/js/widget/actualPopulationDialog.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/js/widget/formValidate.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/js/widget/gridUtil.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/js/widget/idCheckUtil.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/js/widget/AreaData.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/js/widget/threeSelect.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/js/widget/propertyDictAutocomplete.js"></script>
	<SCRIPT type="text/javascript" src="${resource_path}/resource/js/widget/richText.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="${resource_path}/resource/js/widget/personnelComplete.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="${resource_path}/resource/js/widget/typeSelect.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="${resource_path}/resource/js/widget/announcement.js"></SCRIPT>
	<script type="text/javascript" src="${resource_path }/resource/js/widget/orgTreeManage.js"></script>
	
	<script type="text/javascript" src="${resource_path }/resource/js/widget/resourcePoolPreminssinTreeManage.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/js/widget/resourcePoolTreeManage.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/js/widget/permissionTreeManage.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/js/widget/dailydirectoryTreeManage.js"></script>
	<SCRIPT type="text/javascript" src="${resource_path}/resource/js/widget/treeSelect.js"></SCRIPT>
	<script type="text/javascript" src="${resource_path }/resource/js/widget/issueManage/formatter.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/js/widget/issueManage/issueManage.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/js/widget/issueManage/supervise.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/js/widget/issueManage/urgent.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/js/widget/charts.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/js/widget/printArea.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/js/widget/jqgridMultiCheck.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/js/widget/excelDownload.js"></script>

	<script type="text/javascript" src="${resource_path}/resource/js/widget/inhabitantAutocomplete.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/js/widget/primaryOrganizationAutoComplete.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/js/widget/actualPopulationAutocomplete.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/js/widget/houseAutoComplete.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/js/widget/validationExtend.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/js/widget/uuid.js"></script>

</s:if>
<s:else>
	<script type="text/javascript" src="${resource_path }/resource/js/widget/tianqueGrid-<%= GridProperties.TIANQUE_GRID_JS_VERSION %>.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/js/widget/issueManage/formatter.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/js/widget/issueManage/issueManage.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/js/widget/issueManage/supervise.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/js/widget/issueManage/urgent.js"></script>
</s:else>

<script type="text/javascript" src="${resource_path}/resource/system/js/widget/spin.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/system/js/widget/widget.js"></script>
<script type="text/javascript" src="${resource_path }/resource/system/js/init.js"></script>
<script type="text/javascript" src="${resource_path }/resource/js/widget/incident/init.js"></script>
<script type=text/javascript src="${resource_path}/resource/js/widget/incident/incidentTypeTreeManager.js"></script>
<script src="${resource_path }/resource/js/widget/component.js" type="text/javascript"></script>
<script type="text/javascript" src="${resource_path }/resource/edushiGis/js/tqMap/tqMap.js" ></script>
<script type=text/javascript src="${resource_path}/resource/edushiGis/js/tqMap/lib/Map.js"></script>

<script>
<%if(accessTime!=null){%>
var sessionTimeOut=<%=GridProperties.SESSION_TIME_OUT%>;
var accessTime=<%=accessTime.getTime()%>;
var intervalId;
function sendAjaxToValidateSession(){
	$.ajax({
		url : "${path}/sessionManage/getCurrentSession.action" ,
		success : function(data){
			if((new Date() - new Date(accessTime))>sessionTimeOut){
				clearInterval(intervalId);
				//TODO login
				$.createDialog({
					title : "登入",
					url:"${path}/login.jsp",
					close:function(){
						return false;
					}
				});
			}else{
				accessTime=new Date(data.accessTime);
			}
		}
	});
}
//sendAjaxToValidateSession();
//intervalId=setInterval("sendAjaxToValidateSession()",5000);
<%}%>

switch($.cookie('skinColor')){
	case 'blue':
		$("#switchSkin").attr("href","/resource/system/css/switchSkin-blue.css");
    	break;
	case 'green':
		$("#switchSkin").attr("href","/resource/system/css/switchSkin-green.css");
    	break;
	case 'red':
		$("#switchSkin").attr("href","/resource/system/css/switchSkin-red.css");
    	break;
    default:
    	$("#switchSkin").attr("href","/resource/system/css/switchSkin-red.css");
}

</script>
