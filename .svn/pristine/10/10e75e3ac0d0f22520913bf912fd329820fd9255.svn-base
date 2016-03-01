<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">
	<div style="width: 99%;">
		<table id="smsReceivedList"></table>
		<div id="smsReceivedListPager"></div>
	</div>
	<div id="smsReceivedDialog"></div>
</div>
<script type="text/javascript">
function isSend(el,options,rowData){
	if (rowData.processFlag==1) {
      return "<input type=\"checkbox\" checked='checked' onclick='clickRecord(this)' disabled/>";
	}else{
      return "<input type=\"checkbox\" disabled/>";
	}
}
$(document).ready(function(){
	$("#smsReceivedList").jqGridFunction({
		url:"${path}/interaction/sms/inbox/findSmsReceivedBoxByOwnerId.action",
		datatype: "json",
		colModel:[
	    	{name:"id",index:"id",hidden:true},
  			{name:"smsContent",index:"smsContent",width:420,label:"内容",sortable:false},
  			{name:"sourceMobile",index:"sourceMobile",width:120,label:"来源手机"},
  			{name:"receiverTime",index:"receiverTime",width:150,label:"发送时间"},
 			{name:"processFlag",index:"processFlag",width:80,label:"是否已处理",align:"center",formatter:isSend,hidden:true},
 			{name:'',label:'操作',formatter:operateFormatter,width:120}
	  	],
		scrollrow:true,
		height:$(".ui-layout-center").outerHeight()-$(".path").outerHeight()-$(".tabbox").outerHeight()-$("#nav").outerHeight()-80,
       	ondblClickRow: viewSmsReceived,
       	height:330,
       	showColModelButton:false,
		rowNum:15
	});
	
	$("#view").click(function(event){
		viewSmsReceived();
	});
	
	
});

function viewSmsReceived(selectedId){
	if(selectedId==null){
 		return;
	}
	$("#smsReceivedDialog").createDialog({
		width:600,
		height:420,
		title:"查看短信",
		url:"${path}/interaction/sms/inbox/getSmsReceivedBoxById.action?smsReceivedBox.id="+selectedId,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close"); 
		   }
		}
	});
}

var existed = true;
function isTreated(){
	var selectedId = $("#smsReceivedList").getGridParam("selrow");
	if(selectedId==null){
		 return;
	}
	$.ajax({
		async:false,
		url:"${path}/interaction/sms/inbox/isTreated.action?smsReceivedBox.id="+selectedId,
		success:function(responseData){
			existed = responseData > 0 ? false : true;			
		}
	});
	return existed; 
}
function operateFormatter(el, options, rowData){
	if(rowData.processFlag==1){
		return"<a href='javascript:;' class='selectWay' onclick=viewSmsReceived("+rowData.id+")><span>查看</span></a>";
	}
}
</script>