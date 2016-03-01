<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@page import="com.tianque.core.util.ThreadVariable" %>
<% Long userId =ThreadVariable.getSession().getUserId(); %>
<div>
	<div class="displayInline" id="nav">
		<a id="comment" href="javascript:void(0)"><span><strong
			class="ui-ico-xg"></strong>点评</span>
		</a>
	</div>
	<div class="displayInline wringTips">
		<span style="">下辖日志<span style="color:red" id="countSubAllNum">${statistics.countSubAllNum}</span>篇，</span>
		<span style="">其中领导点评过<span style="color:red" id="commentSubAllNum">${statistics.commentSubAllNum}</span>篇</span>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="subLogAllList">
		</table>
	</div>
</div>

<script type="text/javascript">
$(function(){
	subLogstatistics();
	$("#subLogAllList").workBenchGridFunction({
		colModel:[
			{name:"id",index:"id",hidden:true,frozen:true},
	        {name:'commentState',sortable:false,label:'<img src="${path}/resource/js/ui/images/grayPieces.gif" style="vertical-align:middle" />',formatter:isComment,width:20},
			{name:"userId",index:"userId",hidden:true,hidedlg:true},
			{name:"title",index:"title",label:'主题'},
			{name:"organization.orgName",index:"orgName",label:'所属区域',align:'center',hidden:true,hidedlg:true},
			{name:"createDate",label:'创建时间',align:'center',formatter:'date',formatoptions:{newformat: 'Y-m-d'}},
			{name:"commentNums",label:'点评次数',align:'center',hidden:true}
		],
		mtype:'post',
		datatype: "local",
		ondblClickRow: doubleClickTable,
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
		var selectedId = $("#subLogAllList").getGridParam("selrow");
	  	setButtonEnabled(selectedId != null);
	}
	function afterLoad(){
		setButtonEnabled();
	}

	$("#subLogAllList").jqGrid('setFrozenColumns');
	$("#subLogAllList").setGridParam({
		url:"${path}/peopleLog/peopleLogManage/subLogList4Bench.action?isPeer=true",
		datatype: "json",
		page:1
	});
	$("#subLogAllList").trigger("reloadGrid");
	$("#subLogAllList").closest(".ui-jqgrid-bdiv").css({ "height" : "175" });

	$("#comment").click(function(){
		var selectedId = $("#subLogAllList").getGridParam("selrow");
		var rowData = $("#subLogAllList").getRowData(selectedId);
		if(rowData.userId==<%=userId%>){
  			return;
		}
		var id = rowData.id;
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
		})
	});


});
function doubleClickTable(){
    var selectedId = $("#subLogAllList").getGridParam("selrow");
	var rowData = $("#subLogAllList").getRowData(selectedId);
	var id = rowData.id;
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

</script>