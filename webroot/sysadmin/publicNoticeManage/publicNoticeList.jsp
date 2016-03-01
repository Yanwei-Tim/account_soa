<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<link rel="stylesheet" type="text/css" href="${resource_path }/resource/js/ext/css/ext-all.css" />

<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="getFullOrgById" var="getFullOrgById" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>
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
	 <div class="btnbanner btnbannerData">
	    <div class="ui-widget">
	      <div class="userState">
		    <select name="publicNoticeLevel" id="publicNoticeLevel" class="basic-input">
		       <option value="0">本级</option>
		       <option value="1">所有下辖</option>
		    </select>
	      </div>
	 	  <div class="autosearch">
	 		<input type="text" class="basic-input" value="请输入标题关键字条件" name="searchtxt" id="searchtxt" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入标题关键字条件':this.value;" onfocus="value=(this.value=='请输入标题关键字条件')?'':this.value;"/>
			<button id="refreshOrgTree1" class="ui-icon ui-icon-refresh searchbtnico"></button>
	 	  </div>
	 	  <a href="javascript:;" id="searchByConditionButton"><span>搜索</span></a>
		  <pop:JugePermissionTag ename="searchPublicNotice">
			<a id="search" href="javascript:void(0)" ><span>高级搜索</span></a>
		  </pop:JugePermissionTag>
	    </div>
     </div>
     <span class="lineBetween "></span>
     <pop:JugePermissionTag ename="addPublicNotice">
		<a id="add" href="javascript:void(0)" ><span>新增</span></a>
	 </pop:JugePermissionTag>
	 <pop:JugePermissionTag ename="updatePublicNotice">
	    <a id="update" href="javascript:void(0)"><span>修改</span></a>
	 </pop:JugePermissionTag>
	 <pop:JugePermissionTag ename="viewPublicNotice">
	  	<a id="view" href="javascript:void(0)"><span>查看</span></a>
	 </pop:JugePermissionTag>
	 <pop:JugePermissionTag ename="deletePublicNotice">
	    <a id="delete" href="javascript:void(0)"><span>删除</span></a>
	 </pop:JugePermissionTag>
	 <pop:JugePermissionTag ename="refreshPublicNotice">
	    <a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
	 </pop:JugePermissionTag>
 	</div>

	<div style="clear: both;"></div>
	<div style="width: 100%">
		<table id="publicNoticeList"> </table>
		<div id="publicNoticeListPager"></div>
	</div>
</div>
</div>

<div id="publicNoticeDialog"></div>
<div id="other"></div>
<div id="publicNoticeListDialog"></div>

<script type="text/javascript">
 var dialogWidth=580;
 var dialogHeight=540;
 var currentOrgId;
 var tree;

 function onOrgChanged(orgId){
	 $("#publicNoticeList").setGridParam({
		 url:"${path}/sysadmin/publicNoticeManage/publicNoticeList.action?publicNoticeLevel="+$("#publicNoticeLevel").val(),
		 datatype:"json",
		 page:1
	 });

	 $("#publicNoticeList").setPostData({
		 "organizationId":currentOrgId
	 });

	 $("#publicNoticeList").trigger("reloadGrid");
 }

 //回车
	$("#searchtxt").keydown(function(event){//检索条件

		switch(event.keyCode) {
			case 13: $("#searchByConditionButton").click();//检索
			         break;
			default: ;
		}
	});

 function showDailyLogAttachFile(){
	$.each($(".popUpMore"), function(i, n){
		$.ajax({
			url:"${path}/sysadmin/publicNoticeManage/getPublicNoticeAttachFilesById.action?publicNotice.id="+$(n).attr("publicNoticeId"),
			success:function(publicNotice){
				var popMoreData = [];
				for(var j = 0; j < publicNotice.noticeFiles.length; j++){
					popMoreData[j]={id:publicNotice.noticeFiles[j].id,
					url:'${path}/sysadmin/publicNoticeManage/downloadPublicNoticeAttachFile.action?publicNoticeAttachFile.id='+publicNotice.noticeFiles[j].id, text:publicNotice.noticeFiles[j].fileName,
							clickFun:function(){}};
				}
				$(n).popUpMore({data: popMoreData});
			}
		});
	});
}
function updateOperator(selectedId){
	var publicNotice=$("#publicNoticeList").getRowData(selectedId);
	$("#publicNoticeDialog").createDialog({
		width:650,
		height:580,
		title:'修改通知通报',
		url:'${path}/sysadmin/publicNoticeManage/dispatch.action?mode=edit&publicNotice.id='+publicNotice.id+'&organizationId='+currentOrgId,
		buttons: {
			"保存" : function(){
				    $.confirm({
					title:"确认有效期",
					message:$("#overdueDate").val()?"当前有效期是："+$("#overdueDate").val():"当前有效期是：不限",
					okFunc: function() {
						$("#publicNoticeForm").submit();
					}
				});
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}
function deleteOperator(selectedIds){
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除将无法恢复",
		okFunc: function() {
			$.ajax({
				url:"${path}/sysadmin/publicNoticeManage/deletePublicNotice.action?publicNoticeIds="+selectedIds,
				success:function(data){
				    $.messageBox({message:"已经成功删除通知通报信息!"});
					$("#publicNoticeList").trigger("reloadGrid");
					setPublicNoticeOperateButton();
				}
			});
		}
	});
}
var gridOption = {
	colModel:[
		{name:"id", index:"id",hidden:true,frozen : true,sortable:false},
		{name:"publicNoticeTitle", index:"publicNoticeTitle",label:"标题",sortable:true,width:200},
		{name:"noticeFiles", formatter:formatterAttachFile, label:'附件', width:150,sortable:false},
		{name:"noticeEditor",index:"noticeEditor",label:"编辑者",sortable:true,width:100},
		{name:"editorDate",index:"editorDate",label:"编辑时间",sortable:true,align:'center',width:100},
		{name:"organization.orgName",index:"organization.orgName",label:"所属区域",width:150,sortable:false},
		{name:"isoverdate",index:"isoverdate",label:"是否已过显示有限期",width:150,sortable:true,align:'center'}
	]
}

function viewDialog(id){
	$("#publicNoticeDialog").createDialog({
		width:800,
		height:350,
		title:"查看通知通报信息",
		url:"${path}/sysadmin/publicNoticeManage/dispatch.action?mode=view&publicNotice.id="+id+"&organizationId="+currentOrgId,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function formatterAttachFile(el,options,rowData){
	if(rowData.noticeFiles.length>0){
		return "<div style='clear:both' align='center'><a href='javascript:void(0)'  style='color:#666666' class='popUpMore' publicNoticeId='"+rowData.id+"' >附件>></div>";
	}
	return "无";
}

function validateNull(){
	if($("#publicNoticeMatter").val().replace(/[&nbsp;,<p>,</p>]/g,"").replace(/[ ]/g,"") == '' || $("#publicNoticeMatter").val().replace(/[&nbsp;,<p>,</p>]/g,"").replace(/[ ]/g,"").length == 0){
		return false;
 }
 return true;
}

function validateLength(){
    if($("#publicNoticeMatter").val().replace(/[&nbsp;,<p>,</p>]/g,"").replace(/[ ]/g,"").length >500){
        return false;
    }
    return true;
}

$(document).ready(function(){
	var centerHeight=$("div.ui-layout-center").outerHeight();
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
			});
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
        onOrgChanged(currentOrgId);
	}

	tree=$("#orgTree").initTree();
	$.addClick(tree,afterChangNode);

	$("#refreshOrgTree").click(function(){
		$.selectRootNode(tree);
	});

	$('#refreshOrgTree li').hover(
			function() {
				$(this).addClass('ui-state-hover');
			},
			function() { $(this).removeClass('ui-state-hover'); }
	);
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
	        showButtons();
		}
	});

	$("#publicNoticeList").jqGridFunction({
		mtype:'post',
		datatype: "local",
		colModel: gridOption.colModel,
		colNames: gridOption.colNames,
	  	multiselect:true,
		showColModelButton:false,
		loadComplete: function(){afterLoad();showDailyLogAttachFile();},
		ondblClickRow:viewDialog,
		onSelectRow:function(){
			setPublicNoticeOperateButton();
		}
	});
	jQuery("#publicNoticeList").jqGrid('setFrozenColumns');


	$("#add").click(function(){
		if(currentOrgId != USER_ORG_ID){
			return;
		}
		$("#publicNoticeDialog").createDialog({
			width:650,
			height:550,
			title:'新增通知通报',
			url:'${path}/sysadmin/publicNoticeManage/dispatch.action?mode=add&organizationId='+currentOrgId,

			buttons:{
				"保存并继续":function(event){
					 if(!validateNull()){
                         $.messageBox({message:"请输入内容！"});
                         return false;
                     }
             	    if(!validateLength()){
                         $.messageBox({message:"内容最多允许输入500个字符！"});
                 	    return false;
             	    }
				 $("#publicNoticeForm").submit();
				 $("#isSubmit").val("false");

				},
				"保存并关闭":function(){
					$("#isSubmit").val("true");
				    $("#publicNoticeForm").submit();
				}
			}
	});
	});

	$("#refresh").click(function(){
		$("#searchtxt").attr("value","请输入标题关键字条件");
		onOrgChanged(${loginAction.user.organization.id});
	});

	$("#refreshOrgTree1").click(function(){
		$("#searchtxt").attr("value","请输入标题关键字条件");
	});

	$("#delete").click(function(){
		var selectedIds = $("#publicNoticeList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds==null || selectedIds==""){
			$.messageBox({level:'warn',message:"请选择一条或多条数据进行删除！"});
	 		return;
		}
		deleteOperator(selectedIds);
	});

	$("#update").click(function(){
		var selectedId=getSelectedId();
		if(selectedId==null || selectedId==""){
			$.messageBox({level:'warn',message:"请选择一条数据进行操作！"});
	 		return;
		}
		updateOperator(selectedId)
	});


	function search(orgId){
		$("#publicNoticeList").setGridParam({
			url:"${path}/sysadmin/publicNoticeManage/fastSearch.action",
			datatype:"json",
			page:1
		});
		if($("#searchtxt").val() =="请输入标题关键字条件"){
			$("#publicNoticeList").setPostData({
				"organizationId":orgId,
				"publicNoticeVo.publicNoticeLevel":$("#publicNoticeLevel").val(),
				"publicNoticeVo.startDate":$("#startDate").val(),
				"publicNoticeVo.endDate":$("#endDate").val()
			});
		}else{
			$("#publicNoticeList").setPostData({
				"organizationId":orgId,
				"publicNoticeVo.publicNoticeLevel":$("#publicNoticeLevel").val(),
				"publicNoticeVo.startDate":$("#startDate").val(),
				"publicNoticeVo.endDate":$("#endDate").val(),
				"publicNoticeVo.editorTitle":$("#searchtxt").val()
			});
		}

		$("#publicNoticeList").trigger("reloadGrid");
	}

	//检索。。。。。。
	$("#searchByConditionButton").click(function(){
		search(${loginAction.user.organization.id});
	});

	function searchPublicNotice(){
		$("#publicNoticeList").setGridParam({
            url:"${path}/sysadmin/publicNoticeManage/searchPublicNotice.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		var data=$("#searchPublicNotice").serializeArray();
		var dataJson={};
		for(i=0;i<data.length;i++){
			 if (dataJson[data[i].name]) {
		      dataJson[data[i].name]=dataJson[data[i].name]+","+data[i].value;
			} else {dataJson[data[i].name] = data[i].value;
			}
		}

		$("#publicNoticeList").setPostData($.extend({"publicNoticeVo.isInValidity":$("#isInValidity").val(),"organizationId":${loginAction.user.organization.id}},dataJson));
	    $("#publicNoticeList").trigger("reloadGrid");
	}

	$("#search").click(function(event){
		$("#publicNoticeDialog").createDialog({
			width:650,
			height:240,
			title:'通知通报查询-请输入查询条件',
			url:'${path}/sysadmin/publicNoticeManage/dispatch.action?mode=search&organizationId='+currentOrgId,

			buttons: {
				"查询" : function(event){
					searchPublicNotice();
					$(this).dialog("close");
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});

	$("#view").click(function(){
		var selectedIdLast = getSelectedId();
		if(selectedIdLast==null){
			$.messageBox({level:'warn',message:"请选择一条数据进行操作！"});
	 		return;
		 }
			viewDialog(selectedIdLast);
	});


	 $("#publicNoticeLevel").change(function(){
		   $("#searchtxt").attr("value","请输入标题关键字条件");
		   onOrgChanged(${loginAction.user.organization.id});
	   });

	 function getSelectedId(){
	    var selectedIdLast ;
	    var selectedIds = $("#publicNoticeList").jqGrid("getGridParam", "selarrrow");
	    for(var i=0;i<selectedIds.length;i++){
			selectedIdLast = selectedIds[i];
	   }
	   return selectedIdLast;
	}
 });

function afterLoad(){
	 setPublicNoticeOperateButton();
}

function setPublicNoticeOperateButton(){
	var selectedCounts = getActualjqGridMultiSelectCount("publicNoticeList");
	var count = $("#publicNoticeList").jqGrid("getGridParam","records");
	var user =  $("#publicNoticeList").getRowData($("#publicNoticeList").getSelectedRowId());
	var selectedIds = getActualjqGridMultiSelectIds("publicNoticeList");
	if(selectedCounts == count && selectedCounts!=0){
		jqGridMultiSelectState("publicNoticeList", true);
	}else{
		jqGridMultiSelectState("publicNoticeList", false);
	}
}

</script>