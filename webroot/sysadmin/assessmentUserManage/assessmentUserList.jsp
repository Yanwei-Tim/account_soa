<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<link rel="stylesheet" type="text/css" href="${resource_path }/resource/js/ext/css/ext-all.css" />

<div class="center-left">
	<div>
		<div class="ui-widget">
			<input id="org_autocomplete" type="text" value=""/>
			<button id="refreshOrgTree" class="ui-icon ui-icon-refresh"></button>
		</div>
	</div>
	<div class="orgTree">
		<div id="orgTree"></div>
	</div>
</div>

<div class="center-right">
	<div class="content">
		<div class="ui-corner-all" id="nav">
			<div>
				<div class="userState">
					<!-- <input type="text" id="user_autocomplete"  class="show_search_btn" value="快速检索..." /> -->
					<select class="basic-input" id="isLock" name="user.isLock">
		 					<option value="false" selected="selected">活动的</option>
		 					<option value="true">锁定的</option>
		 					<option value="all">全部</option>
					</select>
				</div>
				<pop:JugePermissionTag ename="accessmentUser">
					<a id="assessment" href="javascript:void(0)"><span>纳入考核</span></a>
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="doNotAssessmentUser">
					<a id="doNotAssessment"  href="javascript:void(0)"><span>不纳入考核</span></a>
				</pop:JugePermissionTag>
			</div>
		</div>
		<div style="clear: both;"></div>
		<div style="width: 100%">
			<table id="assessmentUserList"> </table>
			<div id="assessmentUserListPager"></div>
		</div>
	</div>
</div>
<div id="assuserMaintanceDialog"></div>
<div id="noticeDialog"></div>

<script type="text/javascript">
$.showTxtValue()

var dialogWidth=580;
var dialogHeight=540;
var currentOrgId;
var tree;
$(document).ready(function(){
	var centerHeight=$("div.ui-layout-center").height();
	$(".orgTree").height(centerHeight-70);
	currentOrgId="";

	$("#org_autocomplete").autocomplete({
		source: function(request, response) {
			$.ajax({
				url: "${path}/sysadmin/orgManage/ajaxFindOrganizationsByOrgName.action",
				data:{"organization.orgName":request.term},
				success: function(data) {
					response($.map(data, function(item) {
						return {
							label: item.orgName+","+stringFormatter(item.contactWay),
							value: item.orgName,
							id: item.id
						}
					}))
				},
				error : function(){
					$.messageBox({
						message:"搜索失败，请重新登入！",
						level:"error"
					});
				}
			})
		},
		select: function(event, ui) {
			$("#user_autocomplete").removeAttr("userId");
			$("#user_autocomplete").val("");
			$.ajax({
				url:PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+ui.item.id,
				success:function(data){
					$.searchChild(tree,data);
				}
			});
		}
	});
	var clearUserUserAutocomplete=true;

	function afterChangNode(node) {
	    var id = node.attributes.id;
	    currentOrgId=id;
	    clearUserUserAutocomplete=true;
        $("#org_autocomplete").val(node.attributes.text);
        if(clearUserUserAutocomplete){
            $("#user_autocomplete").removeAttr("userId");
        }
        findUserByOrgId(id);
	}

	tree=$("#orgTree").initTree();
	$.addClick(tree,afterChangNode);

	$("#isLock").change(function(event){
		lockUserButtonText($(event.srcElement).val());
		$("#user_autocomplete").empty();
		findUserByOrgId();
	});
	$("#user_autocomplete").userAutocomplete({
		postData: {
			searchChildOrg : true,
			searchLockStatus : function(){if(isActivedUsers()){return "unlocked"}else{return "locked"}},
			organizationId : function(){return currentOrgId;}
		},
		select : function(event, ui){
			$("#user_autocomplete").attr("userId",ui.item.id);
			clearUserUserAutocomplete=false;
			$("#assessmentUserList").trigger("reloadGrid");
		}
	});

	$("#refreshOrgTree").click(function(){
		$.selectRootNode(tree);
	});

	$('#refreshOrgTree li').hover(
			function() { $(this).addClass('ui-state-hover'); },
			function() { $(this).removeClass('ui-state-hover'); }
	);

	$("#assessmentUserList").jqGridFunction({
		url:'${path}/sysadmin/assessmentUserManage/assessmentUserList.action',
		datatype: "local",
		sortname:"id",
		postData: {
	        "user.lock":  function(){
	            return !isActivedUsers();
	        },
	        "orgId":function(){
			    return currentOrgId;
	        },
	        "user.id" : function(){
		        if($("#user_autocomplete").attr("userId")){
		        	return $("#user_autocomplete").attr("userId");
		        }else{
			        return -1;
		        }
	        }
        },
	   	colModel:[
	        {name:"id",index:"id",hidden:true,sortable:false},
	        {name:'lock',hidden:true,sortable:false},
	        {name:"userName",index:'userName',label:'用户名',width:100,sortable:true},
      		{name:"name",index:"name",label:'用户姓名',width:100,sortable:true},
	        {name:'workPhone',label:'工作电话',sortable:true,align:'center',width:130},
	        {name:'mobile',label:'手机',sortable:true,align:'center',width:100},
	        {name:'lastLoginTime',label:'最后登录时间',sortable:true,width:140},
	        {name:'admin',label:'是否超级管理员',formatter:isAdminFormatter,align:'center',width:100,sortable:false},
	        {name:'assessmented',label:'是否纳入考核',formatter:isAssessmentFormatter,align:'center',width:100,sortable:true},
	        {name:'workCompany',label:'工作单位',sortable:true,width:200}
		],
		showColModelButton:false
	});
	$("#assessment").click(function(){
        var selectId = $("#assessmentUserList").getGridParam("selrow");
        if(selectId == null){
        	$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
			return;
		}
        var assessmentUser = $("#assessmentUserList").getRowData(selectId);
        if(assessmentUser.lock == 'true'){return;}
        if(assessmentUser.assessmented && assessmentUser.assessmented=='是' ){return;}
        $.confirm({
            title:"确认将用户纳入考核",
            message:"您确定将该用户纳入考核？",
            width:400,
            okFunc:doAssessmentUser
        });
    });

	$("#doNotAssessment").click(function(){
        var selectId = $("#assessmentUserList").getGridParam("selrow");
        if(selectId == null){
        	$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
			return;
		}
        var assessmentUser = $("#assessmentUserList").getRowData(selectId);
        if(assessmentUser.lock == 'true'){return;}
        if(!assessmentUser.assessmented || assessmentUser.assessmented=='否' ){return;}
        $.confirm({
            title:"确认将用户不纳入考核",
            message:"您确定将该用户不纳入考核？",
            width:400,
            okFunc:doNotAssessmentUser
        });
    });
});

function doAssessmentUser(){
	var selectId = $("#assessmentUserList").getGridParam("selrow");
	if(selectId == null){return;}
	var assessmentUserVo = $("#assessmentUserList").getRowData(selectId);
    var assessmentUserVo = $("#assessmentUserList").getRowData(selectId);
    $.ajax({
    	url:"${path}/sysadmin/assessmentUserManage/assessmentUser.action",
        data:{
                "assessmentUser.userId":assessmentUserVo.id
            },
        success:function(data){
                $("#assessmentUserList").setRowData(data.id,data);
                $.messageBox({message:"已成功将该用户纳入考核!"});
            }
    });
}

function doNotAssessmentUser(){
	var selectId = $("#assessmentUserList").getGridParam("selrow");
    if(selectId == null){return;}
    var assessmentUserVo = $("#assessmentUserList").getRowData(selectId);
    $.ajax({
        url:"${path}/sysadmin/assessmentUserManage/doNotAssessmentUser.action",
        data:{
                "assessmentUser.userId":assessmentUserVo.id,
                "assessmentUser.userName":assessmentUserVo.name
            },
        success:function(data){
            	$("#assessmentUserList").setRowData(data.id,data);
                $.messageBox({message:"已成功将该用户设置为不纳入考核!"});
            }
    });
}

function showButtons(){
	var selectId = $("#assessmentUserList").getGridParam("selrow");
	if(selectId == null){return;}
	var assessmentUser = $("#assessmentUserList").getRowData(selectId);
	if(assessmentUser.lock == 'false'){
	    if(assessmentUser.assessmented && assessmentUser.assessmented != '是'){
	        $("#assessment").buttonEnable();
	        $("#doNotAssessment").buttonDisable();
	    }else{
	        $("#assessment").buttonDisable();
	        $("#doNotAssessment").buttonEnable();
	    }
	}
}

function findUserByOrgId(){
	$("#assessmentUserList").setGridParam({datatype:'json'});
	$("#assessmentUserList").trigger("reloadGrid");
}

function setUserautocompleteLockstatus(lock){
	if (lock == "true"){
		$("#user_autocomplete").option( "searchLockStatus", "locked");
	}else{
		$("#user_autocomplete").option( "searchLockStatus", "unlocked");
	}
}

function stringFormatter(str){
	if(str==undefined){
		return "";
	}
	return str;
}
function isActivedUsers(){
	if( $("#isLock").val() == "false"){ return true;}
    return false;
}


function isAdminFormatter(el,options,rowData){
    if(rowData.admin){
        return "是";
    }
    if(rowData["admin"]){
        return "是";
    }
    return "否";
}

function isAssessmentFormatter(el,options,rowData){
    if(rowData.assessmented)
        return "是";
    else
        return "否";
}

</script>
