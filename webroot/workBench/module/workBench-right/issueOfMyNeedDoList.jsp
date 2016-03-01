<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="ajaxOrganization" var="getLoginOrg" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>

<div class="center-right center-content">
	<div  style="clear: both;"></div>
	<div  style="width:99%;">
		<table id="maxIssueListForNeed"></table>
		<div id="maxIssueListForNeedPager"></div>
	</div>
	<div id="issueForBenchDialog"></div>
</div>

<script type="text/javascript">
<pop:formatterProperty name="sourceKind" domainName="@com.tianque.domain.property.PropertyTypes@SOURCE_KIND" />
var dialogWidth=780;
var dialogHeight=530;
var dialogWidthDeal=680;
var dialogHeightDeal=530;
var dialogHeightComment=400;
var superviseWidth=620;
var superviseHeight=500;

var currentOrgId= <%= ThreadVariable.getUser().getOrganization().getId()%>
$(document).ready(function(){
	jQuery("#maxIssueListForNeed").jqGridFunction({
		datatype: "local",
		sortname:'isteps.lastdealdate', 
		colModel:[
		{name:'supervisionState',index:'isteps.superviselevel',frozen:true,label:'<img src="${path}/resource/js/ui/images/grayPieces.gif" style="vertical-align:middle" />',formatter:rendSupervise,width:30},
		{name:'urgent',index:'iu.urgent',frozen:true,label:'<img src="${path}/resource/js/ui/images/bang.gif" style="vertical-align:middle" />',formatter:rendUrgent,width:30},
		{name:'issueLogId',hidden:true,frozen:true,key:true},
		{name:'issueId',hidden:true,frozen:true,key:true},
		{name:'subject',index:'iu.subject',label:'事件名称',width:150},
		{name:'dealState',index:'isteps.statecode',label:'状态',formatter:dealStateFormatter,width:100,align:"center"},
		{name:'dealTime',index:'isteps.lastdealdate',label:'最后处理时间',width:150,align:"center"},
		{name:'sourceKind',index:'iu.sourceKind',label:'来源方式',formatter:sourceKindFormatter,width:150,align:"center"},
		{name:'sourcePerson',index:'iu.sourcePerson',label:'来源人',width:100},
		{name:'occurDate',index:'iu.occurDate',label:'发生时间',hidden:true,width:150,align:"center"},
		{name:'issueStepId',frozen:true,hidden:true},
		{name:'canDoList',label:'操作',formatter:operateFormatter,width:100,align:"center"}
		],
		ondblClickRow:doubleClickTable,
		loadComplete:afterLoad,
		onSelectRow:maxSelectRow,
		height:330,
		showColModelButton:false,
		rowNum:15
	});
	if(currentOrgId!= null){
		onOrgChanged();
	}
	function doubleClickTable(){
	    var selectedId = $("#maxIssueListForNeed").getGridParam("selrow");
		var ent =  $("#maxIssueListForNeed").getRowData(selectedId);
		$("#issueForBenchDialog").createDialog({
			width:750,
			height:500,
			title:"查看我的待办事项信息",
			url:"${path}/issues/issueManage/dispatch.action?mode=view&keyId="+ent.issueId,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}

	

});

function onOrgChanged(){
	if(undefined == $("#maxIssueListForNeed").attr("id")){
		return;
	}
	$("#maxIssueListForNeed").setGridParam({
		url:'${path}/issues/issueManage/findMyNeedDoForWorkBench.action',
		datatype: "json"
	});
	$("#maxIssueListForNeed").setPostData({
		"keyId":currentOrgId,
		"page":1
	});
	$("#maxIssueListForNeed").trigger("reloadGrid");
}

function selectIssueByStepId(key){
	$("#maxIssueListForNeed").setSelection(key);	
}
function afterLoad(){
	disableButtons();
}

function disableButtons(){
	$("#maxConceptIssueInList").buttonDisable();
	$("#maxDealIssueInList").buttonDisable();
}

function maxSelectRow(){
	var selectedId = $("#maxIssueListForNeed").getGridParam("selrow");
	var rowData =  $("#maxIssueListForNeed").getRowData(selectedId);
	if( "待受理" == rowData.dealState){
		$("#maxConceptIssueInList").buttonEnable();
		$("#maxDealIssueInList").buttonDisable();
		}
	else{
		$("#maxConceptIssueInList").buttonDisable();
		$("#maxDealIssueInList").buttonEnable();
		}
}
function isLargeEvent(el, options, rowData){
	if(true == rowData.isLargeEvent){
		return "是";
	}else{
		return "否";
	}
}
function dealIssue(issueStepId){
	if(issueStepId==null){
 		return;
	}
	$("#issueForBenchDialog").createDialog({
		width:680,
		height:510,
		title:'办理',
		url:'${path}/issues/issueManage/dispatchDeal.action?mode=deal&keyId='+issueStepId,
		buttons: {
			"确定" : function(event){
				$("#issueDealForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}

function simpleDealIssue(issueStepId,dealType){
	if(issueStepId==null){
 		return;
	}
	$("#issueForBenchDialog").createDialog({
		width:350,
		height:200,
		title:'受理',
		url:'${path}/issues/issueManage/dispatchDeal.action?dealCode='+dealType+'&keyId='+issueStepId,
		buttons: {
			"确定" : function(event){
				$("#issueDealForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}
function readIssue(issueStepId,dealType){
	if(issueStepId==null){
 		return;
	}
	$("#issueForBenchDialog").createDialog({
		width:350,
		height:200,
		title:'阅读',
		url:'${path}/issues/issueManage/dispatchDeal.action?dealCode='+dealType+'&keyId='+issueStepId,
		buttons: {
			"确定" : function(event){
				$("#issueDealForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}
function reloadIssue(){
	
}

function dealStateFormatter(el, options, rowData){
	if(rowData.dealState != null){
		if(110 == rowData.dealState){
			return "待受理";
		}else if(140 == rowData.dealState){
			return "待阅读";
		}else if(500 == rowData.dealState){
			return "已办";
		}else if(1000 == rowData.dealState){
			return "已完成";
		}else {
			return "办理中";
		}
	}
}
function operateFormatter(el, options, rowData){
	if(rowData.canDoList != null){
		for(var i=0;i<rowData.canDoList.length;i++){
			var x = rowData.canDoList[i].code;
			if(x==<s:property value="@com.tianque.issue.state.IssueOperate@CONCEPT.code"/>){
        		return "<a href='javascript:void(0)' onclick=simpleDealIssue("+rowData.issueStepId+","+<s:property value="@com.tianque.issue.state.IssueOperate@CONCEPT.code"/>+") class='selectWay'><span><strong class='ui-ico-shouli'></strong>受理</span></a>";
			}
			if(x==<s:property value="@com.tianque.issue.state.IssueOperate@COMMENT.code"/>){
    			return "<a href='javascript:void(0)' onclick=dealIssue("+rowData.issueStepId+") class='selectWay'><span><strong class='ui-ico-banli'></strong>办理</span></a>";
			}
			if(x==<s:property value="@com.tianque.issue.state.IssueOperate@READ.code"/>){
    			return "<a href='javascript:void(0)' onclick=readIssue("+rowData.issueStepId+","+<s:property value="@com.tianque.issue.state.IssueOperate@READ.code"/>+") class='selectWay'><span><strong class='ui-ico-shouli'></strong>阅读</span></a>";
			}
			return "";
		}
	}
}

</script>
