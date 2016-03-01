<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<link rel="stylesheet" type="text/css" href="${resource_path }/resource/js/ext/css/ext-all.css" />

<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="getFullOrgById" var="getFullOrgById" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>

<div class="center-left">
	<div class="ui-widget">
		<input id="org_autocomplete" type="text" class="ui-autocomplete-input" value=""/>
		<button id="refreshOrgTree" class="ui-icon ui-icon-refresh"></button>
	</div>
	<div class="orgTree">
		<div id="orgTree"></div>
	</div>
</div>

<div class="center-right">
	<div id="nav">
		<div class="userState">
			<!-- <input type="text" id="user_autocomplete"  class="show_search_btn" value="快速检索..." /> -->
			<select class="basic-input" id="isLock" name="user.isLock">
 					<option value="false" selected="selected">活动的</option>
 					<option value="true">锁定的</option>
 					<option value="all">全部</option>
			</select>
			<pop:JugePermissionTag ename="searchUserListData">
				<a id="searchUser" href="javascript:void(0)"><span>高级搜索</span></a>
			</pop:JugePermissionTag>
		</div>
		<span class="lineBetween "></span>
		<!--  -->
		<pop:JugePermissionTag ename="userListUpdate">
			<a id="update"  href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="usersListResetPassword">
			<a id="resetPwd" href="javascript:void(0)"><span>重置密码</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="usersListLockUsers">
			<a id="lockUser" href="javascript:void(0)"><span>锁定</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="usersListView">
			<a id="lookUser" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
		<pop:JugePermissionTag ename="usersListReUsed">
		    <a id="used" style="display: none;" href="javascript:void(0)"><span>启用</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="usersListStopUsed">
		    <a id="stopUsed" style="display: none;" href="javascript:void(0)"><span>禁用</span></a>
	    </pop:JugePermissionTag>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%">
		<table id="userList"> </table>
		<div id="userListPager"></div>
	</div>
</div>
<div id="userMaintanceDialog"></div>
<div id="noticeDialog"></div>

<script type="text/javascript">
$.showTxtValue()
var dialogWidth=580;
var dialogHeight=540;
var currentOrgId;
var tree;
$(document).ready(function(){
	var loginUser = '<s:property value="#loginAction.user.userName"/>';
	if(loginUser=="admin"){
		$("#used").attr("style","display");
		$("#stopUsed").attr("style","display");
	}else{
		$("#used").attr("style","display: none;");
		$("#stopUsed").attr("style","display: none;");
	}

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
           // $("#user_autocomplete").val("");
        }
        findUserByOrgId(id);
        setUserOperateButton();
    	//setUserOperateButton();
	}

	var orgTypeCurrent = '<s:property value="#getFullOrgById.organization.orgType.internalId"/>';
	if(orgTypeCurrent == '1'){
		tree=$("#orgTree").initFunctionalTree();
	}else{
		tree=$("#orgTree").initTree();
	}
	$.addClick(tree,afterChangNode);


	$("#isLock").change(function(event){
		lockUserButtonText($(event.srcElement).val());
		$("#user_autocomplete").empty();
		findUserByOrgId();
		setUserOperateButton();
		if( $("#isLock").val() == "true"){
		}
	});
	$("#user_autocomplete").userAutocomplete({
		postData: {
			searchChildOrg : true,
			searchLockStatus : function(){if(isActivedUsers()=="unlocked"){return "unlocked";}else if(isActivedUsers()=="locked"){return "locked";}return "all";},
			organizationId : function(){return currentOrgId;}
		},
		select : function(event, ui){
			$("#user_autocomplete").attr("userId",ui.item.id);
			clearUserUserAutocomplete=false;
			$("#userList").trigger("reloadGrid");
		}
	});

	$("#refreshOrgTree").click(function(){
		$.selectRootNode(tree);
	});

	$('#refreshOrgTree li').hover(
			function() { $(this).addClass('ui-state-hover'); },
			function() { $(this).removeClass('ui-state-hover'); }
	);

	$("#update").click(function(event){
		var selectedId = getSelectedId();
		if(selectedId==null){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		var user =  $("#userList").getRowData(selectedId);
		$("#userMaintanceDialog").createDialog({
			width: dialogWidth,
			height: dialogHeight,
			title:'修改用户',
			url:'${path}/sysadmin/userManage/dispatchUserOperate.action?mode=edit&user.id='+user.id+"&organizationId="+currentOrgId,
			buttons: {
		   		"保存" : function(event){
				var isAdmin = $("#userIsAdmin").val();
				var roles=$("#roleIds").find("option")
		   		if(roles.size()==0 && isAdmin=="false"){
		   			$.messageBox({
						message:"岗位不能为空",
						level:"warn"
                     });
		   			return;
			   	}else{
			   		$("#maintainForm").submit();
				}
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#resetPwd").click(function(event){
		var selectedId = getSelectedId();
		if(selectedId==null){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		var user =  $("#userList").getRowData(selectedId);
		var currentLoginUserId='<s:property value="#loginAction.user.id"/>';

		$("#userMaintanceDialog").createDialog({
			width: 350,
			height: 300,
			title:'重置用户登录密码',
			url:'${path}/sysadmin/userManage/dispatchUserOperate.action?mode=resetPwd&user.id='
				+user.id,
			buttons: {
		   		"保存" : function(event){
		   			$("#maintainForm").submit();
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#lockUser").click(function(){
		var title;
		var message;
		var selectedId = getSelectedId();
		if(selectedId==null){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		var user =  $("#userList").getRowData(selectedId);

	    if(user.admin=='true'){
        	$.notice({
				level:'warn',
				message:'系统管理员无法锁定'
			});
			return ;
	    }

	    var currentLoginUserId='<s:property value="#loginAction.user.id"/>';
	    if(user.id==currentLoginUserId){
	    	$.notice({
				level:'warn',
				message:'当前用户不允许进行锁定'
			});
			return ;
	    }
	    if(user.lock == "false"){
	        title = "确认锁定";
	        message = "锁定后，该用户将无法登录系统，您确定锁定该用户吗？";
	    }else if(user.lock == "true"){
	        title = "确认解锁";
	        message = "解锁后用户将可以登录系统，您确定解锁吗?";
	    }else{
		    return ;
		}
		$.confirm({
				title:title,
				message:message,
				width: 400,
				okFunc: userLockOperate
		});
	});
	$("#lookUser").click(function(event){
		var selectedId = getSelectedId();
		if(selectedId==null){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		viewUserInfo(selectedId);
	});
	$("#refresh").click(function(){
		$("#user_autocomplete").removeAttr("userId");
		refresh();
	});
	$("#used").click(function(){
		var allValue = getSelectedIds();
		if(allValue.length ==0){
			$.messageBox({level:"earn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		$.confirm({
				title:"启用该帐号",
				message:"是否启用该帐号",
				okFunc: function() {
					$.ajax({
						url:"${path}/sysadmin/userManage/reUsed.action?userIds="+allValue,
						success:function(data){
							if(data){
								$.messageBox({message:"已经成功启用该帐号"});
							}else{
								$.messageBox({message:"该帐号启用失败"});
							}
							$("#userList").trigger("reloadGrid");
					}
				});
			}
		});
	});
	$("#stopUsed").click(function(){
		var allValue = getSelectedIds();
		if(allValue.length ==0){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		$.confirm({
				title:"禁用该账号",
				message:"您是否确定禁用该账号",
				okFunc: function() {
					$.ajax({
						url:"${path}/sysadmin/userManage/stopUsed.action?userIds="+allValue,
						success:function(data){
							if(data){
								$.messageBox({message:"已经成功禁用该帐号"});
							}else{
								$.messageBox({level:'error',message:"该帐号禁用失败"});
							}
							$("#userList").trigger("reloadGrid");
					}
				});
			}
		});
	});
	$("#searchUser").click(function(event){
		$("#userMaintanceDialog").createDialog({
			width: 570,
			height: 350,
			title:'用户查询-请输入查询条件',
			url:'${path}/sysadmin/userManage/dispatchUserOperate.action?mode=search&organizationId='+currentOrgId+'&user.lock='+$('#isLock').val()+'&searchLockStatus='+$('#isLock').val(),
			buttons: {
		   		"查询" : function(event){
				    $("#maintainForm").submit();
		   			$(this).dialog("close");
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	function searchUserListData(){
		var roleIds=$("#roleIds").val();
		var userName=$("#userName").val();
		var name =$("#name").val();

		$("#userList").setGridParam({
			url:"${path}/sysadmin/searchUserListData/searchUserList.action",
			datatype: "json",
			page:1
		});

		var searchCondition = {
			"roleIds":roleIds,
			"user.userName":userName,
			"user.name":$("#name").val()
		}
		$("#userList").setPostData({
			"aaaa":roleIds
			//"user.userName":"qw"
			});
		$("#userList").trigger("reloadGrid");
	}
	function getSelectedIds(){
		var selectedIds = $("#userList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
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
    function isShutDown(el,options,rowData){
        if(rowData.shutDown){
            return "是";
        }
        return "否";
    }
    function getRoleNames(el,options,rowData){
        var j=0;
        var roleNames="";
        if(rowData.roles!=null && rowData.roles.length >0){
        	var n = rowData.roles.length;
            for(var m=0;m<n;m++){
            	j++;
                if(j!=n){
                    roleNames+=rowData.roles[m].roleName+",";
                }else{
                	roleNames+=rowData.roles[m].roleName;
                }
            }
        }else{
        	roleNames="系统管理员"
        }
        return roleNames;
    }

	function refresh(){
		$("#userList").setGridParam({
			url:"${path}/sysadmin/userManage/getUserList.action",
			datatype: "json",
			page:1
		});
		$("#userList").setPostData({
			"searchLockStatus":  function(){
                return isActivedUsers();
	        },
	        "organizationId":function(){
			    return currentOrgId;
	        },
	        "user.id" : function(){
		        if($("#user_autocomplete").attr("userId")){
		        	return $("#user_autocomplete").attr("userId");
		        }else{
			        return -1;
		        }
	        },
	        searchChildOrg : true
	   	});
		$("#userList").trigger("reloadGrid");
	}
	$("#userList").jqGridFunction({
		url:'${path}/sysadmin/userManage/getUserList.action',
		datatype: "local",
		postData: {
	        "searchLockStatus":  function(){
	            return isActivedUsers();
	        },
	        "organizationId":function(){
			    return currentOrgId;
	        },
	        "user.id" : function(){
		        if($("#user_autocomplete").attr("userId")){
		        	return $("#user_autocomplete").attr("userId");
		        }else{
			        return -1;
		        }
	        },
	        searchChildOrg : true
        },
	   	colModel:[
	        {name:"id",index:"id",hidden:true,sortable:false},
	        {name:"userName",index:'userName',sortable:true,label:'用户名',width:100},
      		{name:"name",index:"name",sortable:true,label:'用户姓名',width:100},
	        {name:'workPhone',label:'工作电话',sortable:true,align:'center',width:140},
	        {name:'mobile',label:'手机',sortable:true,align:'center',width:100},
	        {name:'lastLoginTime',label:'最后登录时间',sortable:true,width:140},
	        {name:'admin',label:'是否超级管理员',sortable:false,formatter:isAdminFormatter,align:'center',width:100},
	        {name:"shutDown",index:"name",label:'是否启用',sortable:false,formatter:isShutDown,align:'center',width:70},
	        {name:"roles",index:"name",label:'所在岗位',sortable:true,formatter:getRoleNames,width:90},
	        {name:"organization.orgLevel.displayName",sortable:false,index:"displayName",label:'所在层级',width:100},
	        {name:"lock",index:"lock",label:'是否锁定',sortable:true,hidden:true,hidedlg:true,width:70},
	        {name:'workCompany',label:'工作单位',sortable:true,width:200}
	        <s:if test="@com.tianque.component.ThreadVariable@getUser().isAdmin()">
	        ,
	        {name:'',label:'同步用户',sortable:true,formatter: asyncoUserFormmater,align:'center'}
	        </s:if>
		],
		multiselect:true,
		onSelectAll:function(aRowids,status){},
		showColModelButton:false,
		loadComplete: afterLoad,
		ondblClickRow: doubleClickTable,
		onSelectRow:function(){setUserOperateButton();}
	});
});
<s:if test="@com.tianque.component.ThreadVariable@getUser().isAdmin()">
function asyncoUserFormmater(el,options,rowData){
    return "<button onclick='asyncoUser("+rowData["id"]+")'>同步</button>";
}
function asyncoUser(userid){
	$.ajax({
		url: '${path}/sysadmin/userManage/asyncoUser.action?user.id='+userid,
		success:function(data){
			if(data==true){
				$.messageBox({
				    message: "用户同步成功"
				});
			}
		}
	});
}
</s:if>
function isActivedUsers(){
	if($("#isLock").val() == "false"){return "unlocked";}
	   if($("#isLock").val() == "true"){return "locked";}
	   return "all";
}

function userLockOperate(){
	var selectedId = $("#userList").getGridParam("selrow");
	if(selectedId==null){
 		return;
	}
	var user =  $("#userList").getRowData(selectedId);
	var activedUsers = isActivedUsers();
	$.ajax({
		url: '${path}/sysadmin/userManage/lockOperate.action',
		data: {
	        "user.id": function(){
	            return user.id;
	        },
	        "searchLockStatus" : function(){
	            return user.lock;
	        }
        },
		success:function(data){
			if(data == true){
				$("#userList").delRowData(user.id);
				$.messageBox({
				    message: ((user.lock=="false") ? "成功锁定用户:"+ user.userName +"!" :  "成功解锁用户:"+ user.userName +"!")
				 });
				$("#userList").trigger("reloadGrid");
				return;
			}
          		$.messageBox({
				message:data,
				level: "error"
			});
    	}
	});
}


function findUserByOrgId(){
	$("#refresh").click();
}

function setUserautocompleteLockstatus(lock){
	if (lock == "true"){
		$("#user_autocomplete").option( "searchLockStatus", "locked");
	}else{
		$("#user_autocomplete").option( "searchLockStatus", "unlocked");
	}
}

function lockUserButtonText(lock){
	if (lock=="true"){
		$("#lockUser span").replaceWith("<span><strong class='ui-ico-chattr'/>解锁</span>");
	}else{
		$("#lockUser span").replaceWith("<span><strong class='ui-ico-lock'/>锁定</span>");
	}
}

function afterLoad(){
	setUserOperateButton();
}

function setUserOperateButton(){
	var selectedCounts = getActualjqGridMultiSelectCount("userList");
	var count = $("#userList").jqGrid("getGridParam","records");
	var user =  $("#userList").getRowData($("#userList").getSelectedRowId());
	var selectedIds = $("#userList").jqGrid("getGridParam", "selarrrow");
	if(selectedCounts == count && selectedCounts!=0){
		jqGridMultiSelectState("userList", true);
	}else{
		jqGridMultiSelectState("userList", false);
	}
	useredOrStopUsedButtom(selectedIds);

}

function lockButtom(){
	$("#update").buttonDisable();
	$("#resetPwd").buttonDisable();
	$("#lockUser").buttonEnable();
	$("#lookUser").buttonEnable();
	if($("#userList").getSelectedRowId()){
		$("#lockUser").buttonEnable();
		$("#lookUser").buttonEnable();
	}else{
		$("#lockUser").buttonDisable();
		$("#lookUser").buttonDisable();
	}
}

function unlockButtom(){
	if($("#userList").getSelectedRowId()){
		$("#update").buttonEnable();
		$("#resetPwd").buttonEnable();
		$("#lockUser").buttonEnable();
		$("#lookUser").buttonEnable();
	}else{
		$("#update").buttonDisable();
		$("#resetPwd").buttonDisable();
		$("#lockUser").buttonDisable();
		$("#lookUser").buttonDisable();
	}
}

function useredOrStopUsedButtom(strs){
	var used = false;
	var stopused = false;
	if(isActivedUsers()){
		if(strs.length==0){
			jqGridMultiSelectState("userList", false);
		}
		for(var i=0; i<strs.length;i++){
			var user = $("#userList").getRowData(strs[i]);
			if(user["shutDown"]=="是"){
				used = true;
			}
			if(user["shutDown"]=="否"){
				stopused = true;
			}
		}
	}
}
function viewUserInfo(rowid){
	if(rowid==null){
 		return;
	}
	var user =  $("#userList").getRowData(rowid);
	$("#userMaintanceDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:'查看用户信息',
		modal : true,
		url:'${path}/sysadmin/userManage/dispatchUserOperate.action?mode=view&user.id='+user.id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function doubleClickTable(rowid){
	var userInfo =  $("#userList").getRowData(rowid);
	if(userInfo==null || userInfo.id==null){
 		return;
	}
	$("#userMaintanceDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:'查看用户信息',
		modal : true,
		url:'${path}/sysadmin/userManage/dispatchUserOperate.action?mode=view&user.id='+userInfo.id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function stringFormatter(str){
	if(str==undefined){
		return "";
	}
	return str;
}
function getSelectedId(){
	var selectIdLast;
	var selectedIds = $("#userList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectIdLast = selectedIds[i];
	}
	return selectIdLast;
}
</script>
