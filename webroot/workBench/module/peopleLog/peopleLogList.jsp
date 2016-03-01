<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@page import="com.tianque.core.util.ThreadVariable" %>
<% Long userId =ThreadVariable.getSession().getUserId(); %>
<div>
	<div class="displayInline" id="nav">
			<a id="add" href="javascript:void(0)"><span><strong
					class="ui-ico-xz"></strong>新增</span>
			</a>
	</div>
	<div class="displayInline wringTips">
		<span style="">我的日志<span style="color:red" id="countNum">${statistics.countNum}</span>篇，</span>
		<span style="">其中领导点评过<span style="color:red" id="commentNum">${statistics.commentNum}</span>篇</span>
	</div>
	<div style="clear: both;"></div>
	<div style="width:99%;">
		<table id="peopleLogList" >
		</table>
	</div>
</div>

<script type="text/javascript">

$(function(){
	myLogstatistics();

	$("#peopleLogList").workBenchGridFunction({
		colModel:[
	        {name:"id",index:"id",hidden:true,frozen:true},
	        {name:'commentState',sortable:false,label:'<img src="${path}/resource/js/ui/images/grayPieces.gif" style="vertical-align:middle" />',formatter:isComment,width:20},
	        {name:"userId",index:"userId",hidden:true,hidedlg:true},
		    {name:"title",index:"title",label:'主题'},
		    {name:"organization.orgName",index:"orgName",label:'所属区域',align:'center'},
		    {name:"createDate",label:'创建时间',align:'center',formatter:'date',formatoptions:{newformat: 'Y-m-d'}},
		    {name:"commentNums",label:'点评次数',align:'center',hidden:true}
		],
		mtype:'post',
		datatype: "local",
		ondblClickRow: doubleClickTable
	});

	$("#peopleLogList").jqGrid('setFrozenColumns');
	$("#peopleLogList").setGridParam({
		url:"${path}/peopleLog/peopleLogManage/peopleLogList4Bench.action",
		datatype: "json",
		page:1
	});
	$("#peopleLogList").trigger("reloadGrid");
	$("#peopleLogList").closest(".ui-jqgrid-bdiv").css({ "height" : "175" });

	$("#add").click(function(event){
		$("#peopleLogDialog").createDialog({
			width:800,
			height:580,
			title:'新增日志信息',
			url:'${path}/peopleLog/peopleLogManage/dispatch.action?mode=add&peopleLog.organization.id='+getCurrentOrgId(),
			buttons: {
				"保存并关闭" : function(event){
		   			$("#isSubmit").val("true");
		   			$("#maintainForm").submit();
	   				},
	   			"保存并继续" : function(event){
		   			$("#maintainForm").submit();
		   			$("#isSubmit").val("false");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
  	});



});
function doubleClickTable(){
    var selectedId = $("#peopleLogList").getGridParam("selrow");
	var rowData = $("#peopleLogList").getRowData(selectedId);
	var id = rowData.id;
	if(id==null){
		 return;
	}
	$("#logViewDialog").createDialog({
		width:800,
		height:450,
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