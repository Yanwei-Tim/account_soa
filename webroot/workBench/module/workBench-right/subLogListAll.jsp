<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@page import="com.tianque.core.util.ThreadVariable" %>
<% Long userId =ThreadVariable.getSession().getUserId(); %>
<div>
	<div style="clear: both;"></div>
	<div style="width:100%;">
		<table id="workBench_PeopleLogList"></table>
		<div id="workBench_PeopleLogListPager"></div>
	</div>
</div>

<script type="text/javascript">
$(function(){
	subLogstatistics();
	$("#workBench_PeopleLogList").jqGridFunction({
		colModel:[
			{name:"id",index:"id",hidden:true},
	        {name:'commentState',sortable:false,label:'<img src="${path}/resource/js/ui/images/grayPieces.gif" style="vertical-align:middle" />',formatter:isComment,width:20},
			{name:"userId",index:"userId",hidden:true,hidedlg:true},
			{name:"title",index:"title",label:'标题'},
			{name:"createUser",index:"createUser",label:'创建人',width:80},
			{name:"organization.orgName",index:"orgName",label:'创建部门',align:'center',hidedlg:true},
			{name:"createDate",label:'创建时间',align:'center',formatter:'date',formatoptions:{newformat: 'Y-m-d'}},
			{name:"commentNums",label:'点评次数',align:'center',width:80},
			{name:'',label:'操作',formatter:peopleLogOperateFormatter,width:140}
		],
		mtype:'post',
		datatype: "local",
		height:250,
		rowNum:15,
		ondblClickRow: doubleClickTable,
		showColModelButton:false,
	  	onSelectRow: function(data){if(toggleButtonState){toggleButtonState(data);}},
	  	loadComplete: function(data){if(afterLoad){afterLoad(data);}}
	});

	function setButtonEnabled(enabled){
		if (enabled){
			$("#comment").buttonEnable();
		}else{
			$("#comment").buttonDisable();
		}
	}
	function toggleButtonState(){
		var selectedId = $("#workBench_PeopleLogList").getGridParam("selrow");
	  	setButtonEnabled(selectedId != null);
	}
	function afterLoad(){
		setButtonEnabled();
	}

	$("#workBench_PeopleLogList").setGridParam({
		url:"${path}/peopleLog/peopleLogManage/subLogList4Bench.action?isPeer=true",
		datatype: "json",
		page:1
	});
	$("#workBench_PeopleLogList").trigger("reloadGrid");

	$("#comment").click(function(){
		var selectedId = $("#workBench_PeopleLogList").getGridParam("selrow");
		var rowData = $("#workBench_PeopleLogList").getRowData(selectedId);
		if(rowData.userId==<%=userId%>){
  			return;
		}
		var id = rowData.id;
		if(id==null){
			 return;
		}
		commentWorkBenchPeopleLog(id);
	});


});
function doubleClickTable(){
    var selectedId = $("#workBench_PeopleLogList").getGridParam("selrow");
	var rowData = $("#workBench_PeopleLogList").getRowData(selectedId);
	var id = rowData.id;
	if(id==null){
		 return;
	}
	viewWorkBenchPeopleLog(id);
}
function viewWorkBenchPeopleLog(id){
	if(id==null){
		 return;
	}
	$("#logViewDialog").createDialog({
		width:800,
		height:420,
		title:"查看日志信息",
		url:"${path}/peopleLog/commentLogManage/dispatchOperate.action?mode=view&peopleLog.id="+id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
function commentWorkBenchPeopleLog(id){
	if(id==null){
		 return;
	}
	$("#subDialog").createDialog({
		width:800,
		height:600,
		title:"评论日志",
		url:"${path}/peopleLog/peopleLogManage/dispatchAction.action?mode=add&dailogName=subDialog&logId="+id,
		buttons: {
			"保存":function(){
				$("#commentForm").submit();
			},
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
	});
}

function peopleLogOperateFormatter(el, options, rowData){
	return "<a href='javascript:;' class='selectWay' onclick=viewWorkBenchPeopleLog("+rowData.id+")><span>查看</span></a>"+" "+"<a href='javascript:;'  class='selectWay' onclick=commentWorkBenchPeopleLog("+rowData.id+")><span>点评</span></a>";
}

</script>