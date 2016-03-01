<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<div class="container container_24">
	<form id="maintainForm" action="" method="post">
		<input type="hidden" name="user.lock" value='<s:property value="#parameters.isLock"/>'/>
		<input type="hidden" name="searchLockStatus" value='<s:property value="#parameters.isLock"/>'/>
		<div class="grid_4 lable-right">
			<label class="form-lbl">用户名：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="user.userName" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">用户姓名：</label>
		</div>
		<div class="grid_7">
	    	<input type="text" name="user.name" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">所属部门：</label>
		</div>
		<div class="grid_7">
	    	<input id="userOrganization" type="text" name="user.organization.orgName" class="form-txt" />
	    	<input id="userOrganizationId" type="hidden" name="user.organization.id" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">是否管理员：</label>
		</div>
		<div class="grid_7">
		    <select name="user.ignoreIsAdminOrNot" id="user.ignoreIsAdminOrNot">
		       <option value="0"></option>
		       <option value="1">否</option>
		       <option value="2">是</option>
		    </select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">家庭电话：</label>
		</div>
		<div class="grid_7">
	    	<input type="text" name="user.homePhone" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">工作电话：</label>
		</div>
		<div class="grid_7">
	    	<input type="text" name="user.workPhone" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">手机号码：</label>
		</div>
		<div class="grid_7">
	    	<input type="text" name="user.mobile" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">最后登入日期：</label>
		</div>
		<div class="grid_7">
	    	<input type="text" name="user.lastLoginTime" readonly id="lastLoginDate" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">查询日期：</label>
		</div>
		<div class="grid_7">
	    	<input type="text" name="user.timeforQuery" readonly id="timeforQuery" class="form-txt"/>
		</div>
		<div class="grid_7">
	    	<input type="text" style="border: 0px;color: red;width: 200px" value="用于查询此日期之后未登录的用户"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<!--
		<div class="grid_4 lable-right">
			<label class="form-lbl">所在岗位：</label>
		</div>
		<div class="grid_7">
	    	<input type="text" name="" class="form-txt" />
		</div>
		 -->
	</form>
</div>

<script type="text/javascript">
$(document).ready(function(){

	var self=$("#userOrganization");
	var selfId=self.attr("id");
	var defaultOption={
		store:new Ext.data.SimpleStore({fields:[],data:[[]]}),
	    editable:false, //禁止手写及联想功能
	    mode: 'local',
	    triggerAction:'all',
	    name:'org',
	    fieldLabel : '组织机构',
	    maxHeight: 250,
	    tpl: "<div id="+selfId+"-tree"+" style='width:100%;overflow:auto;'>"+"</div>", //html代码
	    selectedClass:'',
	    onSelect:Ext.emptyFn,
	    //emptyText:'请选择...',
	    listWidth:180,
	    inputName:"user.organization.id",
	    allOrg:false,
	    url:false
	};
	function style(){
		$("#"+selfId+"-tree").parent().parent().remove();
		self.width(self.width()-25);
		$("#"+selfId+"tree").bgiframe();
	};
	style();
	var comboWithTooltip = new Ext.form.ComboBox(defaultOption);
	comboWithTooltip.applyTo(selfId);
	var url;
	if(defaultOption.url){
		url = defaultOption.url;
	}else{
		url = "/sysadmin/orgManage/firstLoadExtTreeData.action";
	}
	var tree ;
	$("#"+selfId+"-tree").height(defaultOption.maxHeight).width(defaultOption.listWidth);
	$.ajax({
		url:PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+	<s:property value="#parameters.orgId"/>,
		success:function(data){
			tree = $("#"+selfId+"-tree").initAdministrativeTree({shouldJugeMultizones:true,allOrg:defaultOption.allOrg,url:url});
			tree.on("click",function(node,e) {
		        comboWithTooltip.setRawValue(node.text);
		        comboWithTooltip.collapse();
		        $("input[name='"+defaultOption.inputName+"']").val(node.id);
			});
			$.searchChild(tree,data);
		}
	});

	//$("#userOrganization").treeSelect({inputName:"user.organization.id"});
	$('#timeforQuery').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'}
			);
	$('#lastLoginDate').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'});
	$("#maintainForm").formValidate({
		promptPosition: "bottomRight",
		submitHandler: function(form){
			var params = $(form).serialize();
			jQuery("#userList").setPostData({});
			var url = jQuery("#userList").getGridParam("url");
			jQuery("#userList").setGridParam({"url":"${path}/sysadmin/userManage/searchUsers.action?"+params});
			$("#userList").trigger("reloadGrid");
			//jQuery("#userList").setGridParam({"url":url});
			jQuery("#userList").setPostData({
				"user.lock":  function(){
		            return !isActivedUsers();
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
		        searchChildOrg : false
	       	});
			$.ajax({
				url:PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+$("#userOrganizationId").val(),
				success:function(data){
					if(data.indexOf("/")==0){
						data=data.substring(1);
					}
					data="/"+tree.id+"-root/"+data;
					tree.selectPath(data,null,function(bSuccess, oSelNode){
						tree.getSelectionModel().select(oSelNode);
					});
				}
			});
		},
		rules:{},
		messages:{}
	});
});
</script>
