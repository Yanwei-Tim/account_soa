<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@page import="com.tianque.core.util.ThreadVariable" %>
<% Long userId =ThreadVariable.getSession().getUserId(); %>
<div>
	<div class="displayInline" id="nav">
			<a id="addPersonelMessage" href="javascript:void(0)" ><span>新增平台短信</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width:99%;">
		<table id="personelMessageList"></table>
	</div>
</div>

<script type="text/javascript">


$(function(){
	$("#personelMessageList").workBenchGridFunction({
		colModel:[
	        {name:"id",index:"id",hidden:true,key:true},
		    {name:"title",index:'',width:280,label:"标题"},
		    {name:'messageType',width:100,hidden:true,label:"消息类型",sortable:false,formatter:messageTypeFormatter},
		    {name:'sendUserName',width:120,label:"发件人",index:""},
		    {name:'sendTime',width:220,label:"时间",index:''},
		    {name:'diffDay',width:60,hidden:true,align:"center",label:"有效期",index:''},
		    {name:'readed',width:80,hidden:true,label:"是否已读",index:"",sortable:false,formatter:readFormatter}
		],
		mtype:'post',
		datatype: "local",
		ondblClickRow: doubleClickTable
	});

	$("#personelMessageList").jqGrid('setFrozenColumns');
	$("#personelMessageList").setGridParam({
		url:"${path}/interactive/personnerlMessageTragetManage/findUnReadPersonnelMessageTargetForPageByUserId.action",
		datatype: "json",
		page:1
	});
	$("#personelMessageList").closest(".ui-jqgrid-bdiv").css({ "height" : "175" });
	$("#personelMessageList").trigger("reloadGrid");
	var dialogWidth=800;
	var dialogHeight=580;

	$("#addPersonelMessage").click(function(event){
		$("#personelMessageDialog").createDialog({
			width: 800,
			height: 560,
			title:'新建平台信息',
			url:'${path}/interactive/personnerlMessageManage/dispatch.action',
			buttons: {
				"发送" : function(event){
			      $("#maintainForm").submit();
			      $(".message").click();
			   },
			   "关闭" : function(){
			        $(this).dialog("close");
					$(".message").click();
			   }
			}
		});
	});
});

function readFormatter(el, options, rowData){
	var str="";
	if(rowData.readed==true){
		str="是";
	}
	if(rowData.readed==false){
		str="否";
	}
	return str;
}
function messageTypeFormatter(el, options, rowData){
	var str="";
	if(rowData.messageType=="2"){
		str="人工发送";
	}
	if(rowData.messageType=="1"){
		str="系统发送";
	}
	if(rowData.messageType=="3"){
		str="回复";
	}
	return str;
}

function doubleClickTable(){
    var selectedId = $("#personelMessageList").getGridParam("selrow");
	var rowData = $("#personelMessageList").getRowData(selectedId);
	var id = rowData.id;
	if(id==null){
		 return;
	}
	$("#personelMessageDialog").createDialog({
		width:600,
		height:400,
		title:"查看平台信息",
		url:"${path}/interactive/personnerlMessageTragetManage/getPersonnelMessageTargetByIds.action?mode=view&id="+id,
		buttons: {
		   "关闭" : function(){
			   $(this).dialog("close");
			   $(".message").click();
		   }
		},
		close:function(){
			$("#personelMessageList").trigger("reloadGrid");
			if(typeof(personelMessageMaxList) != 'undefined'){
		    	 $("#personelMessageMaxList").trigger("reloadGrid");
		  	}
			$(".message").click();
		}
	});
}

//回复平台信息，如果成功，刷新收件箱列表， 刷新收件箱计数器使其自加一。
function restorMessage(id){
	$("#workBenchplatformMessageDialog").createDialog({
		width: 600,
		height: 450,
		title:'回复平台信息',
		url:'${path}/interactive/personnerlMessageTragetManage/getPersonnelMessageTargetById.action?mode=restore&personnelMessageTarget.id='+id,
		buttons: {
			"发送" : function(event){
		      $("#maintainForm").submit();
		 	  refreshMyUnReadCount();
			  reloadMessageCount();
			  $("#personelMessageList").trigger("reloadGrid");
			  if(typeof(personelMessageMaxList) != 'undefined'){
			    	 $("#personelMessageMaxList").trigger("reloadGrid");
			  }
			  $(this).dialog("close");
			  $(".message").click();
		   },
		   "关闭" : function(){
			    $(".message").click();
			    $("#personelMessageList").trigger("reloadGrid");
		        $(this).dialog("close");
		        $(".message").click();
		   }
		}
	});
}

function refreshMyUnReadCount(){
}
function reloadMessageCount(){
}
function getMessageByUser(){
	messagePop();
}
</script>