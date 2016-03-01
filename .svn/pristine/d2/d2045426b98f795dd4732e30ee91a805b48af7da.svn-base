<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
		<div style="float: right;white-space:nowrap;">
				<select id="isSuccess" name="grade">
						<option value="-1" selected="selected">全部</option>
	 					<option value="1">成功</option>
	 					<option value="0">失败</option>
				</select>
		</div>
	</div>
	<div style="width: 100%;">
		<table id="jobMonitorList"></table>
		<div id="jobMonitorListPager"></div>
	</div>
</div>
<script type="text/javascript">

function onOrgChanged(){
	$("#jobMonitorList").setGridParam({
		url:"${path}/sysadmin/jobMonitor/findJobMonitor.action",
		datatype: "json",
		page:1
	});
	$("#jobMonitorList").setPostData({
    	"grade":$("#isSuccess").val()
   	});
	$("#jobMonitorList").trigger("reloadGrid");
}

$(document).ready(function(){
	$("#jobMonitorList").jqGridFunction({
		datatype: "local",
	   	colModel:[
		       {name:"jobname",index:'jobname',label:"job名称"},
		       {name:'startDate',index:'jobname',label:"开始时间"},
		       {name:'entDate',index:'jobname',label:"结束时间", width:200},
		       {name:'remark',index:'jobname',label:"其他信息"},
		       {name:'success',index:'success',label:"是否成功",formatter :successFormatter}
	   	]
	});
	onOrgChanged();
	$("#reload").click(function(){
		onOrgChanged();
	});
	
	$("#isSuccess").change(function(){
		onOrgChanged();
	});
});


function successFormatter(el, options, rowData){
	var str="";
	if(rowData.success==1)
		str += "是";
	else
		str += "否";
	return str;
}
</script>