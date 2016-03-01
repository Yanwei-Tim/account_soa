
<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />

<script type="text/javascript">
<@pop.formatterProperty name="useInLevel" domainName="@com.tianque.domain.property.PropertyTypes@ORGANIZATION_LEVEL" />
$(document).ready(function(){

	$("#roleList").jqGridFunction({
	   	url:'${path}/sysadmin/roleManage/roleList.action',
		colNames:['id','岗位名称','应用层级','岗位描述'],
	   	colModel:[
	   	    {name:'id',index:'id',hidden:true,sortable:false},
	   		{name:'roleName',sortable:true,index:'roleName'},
	   		{name:'useInLevel',index:'useInLevel',sortable:true,formatter:useInLevelFormatter},
	   		{name:'description',sortable:false}

	   	],
	   	shrinkToFit:true,
	   	showColModelButton:false
	    <@pop.JugePermissionTag ename="viewRole">
        	,ondblClickRow: doubleClickTable
  		</@pop.JugePermissionTag>
	});

	$("#reload").click(function(){
		$("#roleList").trigger("reloadGrid");
	});

	$("#add").click(function(event){
		$("#roleDialog").createDialog({
			width:550,
			height:520,
			title:'新增岗位',
			url:'${path}/sysadmin/roleManage/prepareRole.action?mode=add',
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
	$("#search").click(function(){
		$("#roleDialog").createDialog({
			width:550,
			height:200,
			title:"岗位查询-请输入查询条件",
			url:"${path}/sysadmin/roleManage/searchRoles.ftl",
			buttons:{
              "查询":function(event){
				submitSearchRoles();
				$(this).dialog("close");
	          },
	          "关闭":function(event){
	        	  $(this).dialog("close");
		      }
			}
		});
	});

	$("#update").click(function(event){
		var selectedId = $("#roleList").getGridParam("selrow");
		if(selectedId==null){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		var role =  $("#roleList").getRowData(selectedId);
		reSetPatelConfig(role.id);
		$("#roleDialog").createDialog({
			width:550,
			height:520,
			title:'修改岗位',
			modal : true,
			resizable : true,
			url:'${path}/sysadmin/roleManage/prepareRole.action?mode=edit&role.id='+ role.id,
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
	$("#copy").click(function(){
		var selectedId = $("#roleList").getGridParam("selrow");
		if(selectedId==null){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		var role =  $("#roleList").getRowData(selectedId);

		$("#roleDialog").createDialog({
			width:550,
			height:520,
			title:'新增复制岗位',
			modal : true,
			resizable : true,
			url:'${path}/sysadmin/roleManage/prepareRole.action?mode=copy&role.id='+ role.id,
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

	$("#view").click(function(){
		var selectedId = $("#roleList").getGridParam("selrow");
		if(selectedId==null){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		doubleClickTable(selectedId);
	});

	$("#delete").click(function(){
		var selectedId = $("#roleList").getGridParam("selrow");
		if(selectedId==null){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		$.confirm({
			title:"确认删除",
			message:"岗位删除后，将无法恢复,您确认删除该岗位吗?",
			width: 400,
			okFunc: deleteRole
		});
	});


});
function submitSearchRoles(){
	var roleUseInLevelId = $("#useInl").val();
	var roleName = $("#roleName").val();
	var roleDescription = $("#description").val();

	$("#roleList").setGridParam({
		url:"${path}/sysadmin/searchRoleManage/searchRoles.action",
		datatype: "json",
		page:1
	});

	var searchCondition={
		"role.useInLevel.id":roleUseInLevelId,
		"role.roleName":roleName,
		"role.description":roleDescription
	};
	$("#roleList").setPostData(searchCondition);
	$("#roleList").trigger("reloadGrid");

	$("#roleList").setGridParam({url:"${path}/sysadmin/roleManage/roleList.action"});
	$("#roleList").setPostData({});
}

function deleteRole(selectedId){
	var selectedId = $("#roleList").getGridParam("selrow");
	var role = $("#roleList").getRowData(selectedId);
	$.ajax({
		url:'${path}/sysadmin/roleManage/deleteRole.action?role.id='+ role.id,
		success:function(data){
			if(data == true){
				$("#roleList").delRowData(role.id);
				$.messageBox({ message:"成功删除岗位!"});
				return;
			}
            $.messageBox({
				message:data,
				level: "error"
			});
        }
	});
}

function doubleClickTable(rowid){
		var role =  $("#roleList").getRowData(rowid);
		$("#roleDialog").createDialog({
			width:550,
			height:520,
			title:'查看岗位',
			modal : true,
			resizable : true,
			url:'${path}/sysadmin/roleManage/prepareRole.action?mode=view&role.id='+ role.id,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
}

function reSetPatelConfig(roleId){
	$.ajax({
		async: false ,
		url:"${path }/sysadmin/userManage/reSetPatelConfig.action",
	   	data:{
		"roleId":roleId
		}
	});
}
</script>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<@pop.JugePermissionTag ename="searchRoles">
		    <a id="search" href="javascript:void(0)"><span>高级搜索</span></a>
	    </@pop.JugePermissionTag>
	    <span class="lineBetween "></span>
		<@pop.JugePermissionTag ename="addRole">
				<a id="add" href="javascript:;"><span>新增</span></a>
		</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename="updateRole">
				<a id="update" href="javascript:void(0)"><span>修改</span></a>
		</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename="viewRole">
				<a id="view" href="javascript:void(0)"><span>查看</span></a>
		</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename="deleteRole">
			<a id="delete"  disabled="disabled" href="javascript:void(0)"><span>删除</span></a>
		</@pop.JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
		<@pop.JugePermissionTag ename="copyRole">
			<a id="copy" href="javascript:void(0)"><span>复制</span></a>
		</@pop.JugePermissionTag>
	</div>
	<div style="width: 100%;">
		<table id="roleList"></table>
		<div id="roleListPager"></div>
	</div>
	<div id="roleDialog" style="overflow: hidden"></div>
</div>
