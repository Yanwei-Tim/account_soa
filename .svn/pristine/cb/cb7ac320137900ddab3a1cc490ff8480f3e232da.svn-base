<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@page import="com.tianque.core.util.ThreadVariable" %>
<% Long userId =ThreadVariable.getSession().getUserId(); %>
<div>
	<div style="clear: both;"></div>
	<div style="width:99%;">
		<table id="workBenchplatformMessageList"></table>
		<div id="workBenchplatformMessageListPager"></div>
	</div>
</div>

<script type="text/javascript">


$(function(){
	$("#workBenchplatformMessageList").jqGridFunction({
		url:"${path}/interactive/inboxPlatformMessageManage/findUnreadInboxPlatformMessage.action",
		postData:{
			datatype: "json",
			page:1
		},
		colModel:[
	        {name:"id",index:"id",hidden:true,key:true},
		    {name:"title",index:'',width:400,label:"标题"},
		    {name:'sender.name',width:140,label:"发件人",align:"center",index:""},
		    {name:'sendDate',width:160,label:"发送时间",index:'',align:"center"},
 			{name:'',label:'操作',formatter:operateFormatter,width:120,align:"center"}
		],
		height:330,
		rowNum:15,
		mtype:'post',
		datatype: "json",
		showColModelButton:false,
		ondblClickRow: doubleClickTable
	});

	$("#workBenchplatformMessageList").trigger("reloadGrid");

});


function operateFormatter(el, options, rowData){
	return"<a href='javascript:;' class='selectWay' onclick=viewPlatformMessage("+rowData.id+")><span>查看</span></a>";
}

function doubleClickTable(){
    var selectedId = $("#workBenchplatformMessageList").getGridParam("selrow");
	if(selectedId==null){
		 return;
	}
	viewPlatformMessage(selectedId);
}

function viewPlatformMessage(id){
	if(id==null){
		 return;
	}
	$("#workBenchplatformMessageDialog").createDialog({
		width:800,
		height:400,
		title:"查看平台消息",
		url:"${path}/interactive/inboxPlatformMessageManage/getInboxPlatformMessageById.action?mode=view&platformMessage.id="+id,
		buttons: {
		   "回复":function(){
			   $(this).dialog("close");
			   replyPlatformMessage(id);
		   },
		   "关闭" : function(){
			   $("#workBenchplatformMessageList").trigger("reloadGrid");
		       $(this).dialog("close");
		       $(".message").click();
		   }
		},
	});
}

function replyPlatformMessage(id){
	$("#workBenchplatformMessageDialog").createDialog({
		width: 600,
		height: 400,
		title:'回复平台消息',
		url:'${path}/interactive/inboxPlatformMessageManage/getInboxPlatformMessageById.action?mode=reply&platformMessage.id='+id,
		buttons: {
			"发送" : function(event){
			     $("#maintainForm").submit();
		   },
		   "关闭" : function(){
			   	$(this).dialog("close");
			    getMessageByUser();
			    $("#workBenchplatformMessageList").trigger("reloadGrid");
		   }
		}
	});
}

</script>