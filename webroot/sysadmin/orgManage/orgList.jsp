<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/includes/baseInclude.jsp" />

<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="getFullOrgById" var="getFullOrgById" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>

<div class="center-left">
	<div id="refreshOrgTreeDiv">
		<div class="ui-widget">
			<input id="org-tree-autocomplete" type="text" value=""/>
			<button id="refreshOrgTree" class="ui-icon ui-icon-refresh"></button>
		</div>
	</div>
	<div class="orgTree">
		<div id="orgTree"></div>
	</div>
</div>
<div class="center-right">
	<div class="content">
		<div id="content-top">
			<div class="ui-corner-all" id="nav">
				<pop:JugePermissionTag ename="addOrganization">
					<a id="add" href="javascript:;"><span>新增</span></a>
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="updateOrganization">
					<a id="update" href="javascript:void(0)"><span>修改</span></a>
				</pop:JugePermissionTag>
				<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
				<s:if test="#loginAction.user.admin">
					<a id="delete" href="javascript:void(0)"><span>删除</span></a>
				</s:if>

				<pop:JugePermissionTag ename="deleteGridOrganization">
				   <s:if test="!#loginAction.user.admin">
				      <a id="deletegrid" href="javascript:void(0)" ><span>删除网格</span></a>
                   </s:if>
                </pop:JugePermissionTag>

					<pop:JugePermissionTag ename="importOrgs">
						<a id="importOrgs" href="javascript:void(0)"><span>导入</span></a>
					</pop:JugePermissionTag>
					<!--
					<s:if test="#loginAction.user.admin">
					<a id="marge" href="javascript:void(0)"><span>合并</span></a>
				</s:if>
				-->
			</div>
			<div style="clear:both"></div>
			<div class="container container_24 zt_grid_0722" >
				<input type="hidden" value="" id="orgDetail-orgId" />
				<div class="grid_4">
					<label >部门名称：</label>
				</div>
				<div class="grid_8">
					<input type="text" readonly="readonly" id="orgDetail-orgName" />
				</div>
				<div class="grid_4">
					<label>联系电话：</label>
				</div>
				<div class="grid_8">
					<input type="text" readonly="readonly" id="orgDetail-contactWay" />
				</div>
				<div style="clear:both"></div>
				<div class="grid_4">
					<label>部门类型：</label>
				</div>
				<div class="grid_8">
					<input type="text" readonly="readonly" id="orgDetail-orgType" />
				</div>
				<div class="grid_4">
					<label>部门级别：</label>
				</div>
				<div class="grid_8">
					<input type="text" readonly="readonly" id="orgDetail-orgLevel" />
				</div>
				<div style="clear:both"></div>
				<div class="grid_4">
					<label>备注：</label>
				</div>
				<div class="grid_8">
					<input type="text" readonly="readonly" id="orgDetail-remark" />
				</div>
				<div class="grid_4">
					<label>部门编号：</label>
				</div>
				<div class="grid_8">
					<input type="text" readonly="readonly" id="orgDetail-departmentNo" />
				</div>
				<div style="clear:both"></div>
			</div>
			<div style="clear:both"></div>
			<pop:JugePermissionTag ename="moveOrganization" >
				<div class="ui-corner-all" id="nav">
					<a id="toPrevious" href="javascript:void(0)"><span>上移</span></a>
					<a id="toNext" href="javascript:void(0)"><span>下移</span></a>
					<a id="toFirst" href="javascript:void(0)"><span>到顶</span></a>
					<a id="toEnd" href="javascript:void(0)"><span>到底</span></a>
				</div>
			</pop:JugePermissionTag>
			<div style="clear:both"></div>
		</div>
		<div style="width: 100%">
			<table id="orgChildren-dataGrid"></table>
			<div id="orgChildren-dataGridPager"></div>
		</div>
	</div>
</div>
<div id="org-dailog"></div>
<s:action name="findPropertyDictByDomainName" namespace="/sysadmin/propertyManage" var="getOrganizationTypeProperties" ignoreContextParams="true">
	<s:param name="propertyDomain.domainName" value="@com.tianque.domain.property.PropertyTypes@ORGANIZATION_TYPE">
	</s:param>
</s:action>
<s:action name="findPropertyDictByDomainName" namespace="/sysadmin/propertyManage" var="getOrganizationLevelProperties" ignoreContextParams="true">
	<s:param name="propertyDomain.domainName" value="@com.tianque.domain.property.PropertyTypes@ORGANIZATION_LEVEL">
	</s:param>
</s:action>
<script type="text/javascript" >
var tree;
function toggleButtons(){
	var rowId = $("#orgChildren-dataGrid").jqGrid('getGridParam','selrow');
}

//当节点失去焦点后，失效维护按钮
function toggleMaintainsButtons(){
	var node = $.getSelectedNode(tree);
	if($("#orgDetail-orgId").val()&&$("#orgDetail-orgId").val()!=""){
		if(node.attributes.orgTypeInternalId==0&&node.attributes.orgLevelInternalId!=0){
			$("#add").buttonEnable();
		}else{
			$("#add").buttonDisable();
		}
		$("#update").buttonEnable();
		$("#delete").buttonEnable();
	}else{
		$("#add").buttonDisable();
	}
}

function isNode(){
	return $.getSelectedNode(tree).hasChildNodes();
}

function updateOrgDetail(data){
	$("#org-tree-autocomplete").val(data.orgName);
	if(data==""){
		$("#orgDetail-orgInternalCode").val("");
		$("#orgDetail-orgName").val("");
		$("#orgDetail-orgType").val("");
		$("#orgDetail-orgLevel").val("");
		$("#orgDetail-contactWay").val("");
		$("#orgDetail-orgId").val("");
		$("#orgDetail-remark").val("");
		$("#orgDetail-departmentNo").val("");
	}else{
		$("#orgDetail-orgInternalCode").val(data.orgInternalCode);
		$("#orgDetail-orgName").val(data.orgName);
		$("#orgDetail-orgType").val(data.orgType.displayName);
		$("#orgDetail-orgLevel").val(data.orgLevel.displayName);
		if(data.contactWay!=null){
			$("#orgDetail-contactWay").val(data.contactWay);
		}else{
			$("#orgDetail-contactWay").val("");
		}
		$("#orgDetail-orgId").val(data.id);
		if(data.remark!=null){
			$("#orgDetail-remark").val(data.remark);
		}else{
			$("#orgDetail-remark").val("");
		}
		if(data.departmentNo!=null){
			$("#orgDetail-departmentNo").val(data.departmentNo);
		}else{
			$("#orgDetail-departmentNo").val("");
		}
		if(isNode()){
			$("#marge").buttonDisable();
		}else{
			$("#marge").buttonEnable();
		}
	}
}
var orgTypeData = new Array();
var orgTypeInternalIdArray = new Array();
<s:iterator value="#getOrganizationTypeProperties.propertyDicts" var="propertyDict">
orgTypeData[<s:property value="#propertyDict.id"/>]='<s:property value="#propertyDict.displayName"/>';
orgTypeInternalIdArray[<s:property value="#propertyDict.id"/>]='<s:property value="#propertyDict.internalId"/>';
</s:iterator>

var orgTypeCurrent = '<s:property value="#getFullOrgById.organization.orgType.internalId"/>';

var orgType = function(el,options,rowData){
	if(rowData["orgType.id"]){
		return orgTypeData[rowData["orgType.id"]];
	}
	if(!rowData.orgType){
		return "";
	}
	return orgTypeData[rowData.orgType.id];
}

var orgLevelData = new Array();
<s:iterator value="#getOrganizationLevelProperties.propertyDicts" var="propertyDict">
orgLevelData[<s:property value="#propertyDict.id"/>]='<s:property value="#propertyDict.displayName"/>';
</s:iterator>

var orgLevel = function(el,options,rowData){
	if(rowData["orgLevel.id"]){
		return orgLevelData[rowData["orgLevel.id"]];
	}
	if(!rowData.orgLevel){
		return "";
	}
	return orgLevelData[rowData.orgLevel.id];
}
$(function(){

	var centerHeight=$("div.ui-layout-center").outerHeight();
	$(".orgTree").height(centerHeight-70);

	$("#importOrgs").click(function(event){
		$("#org-dailog").createDialog({
			width: 400,
			height: 220,
			title:'数据导入',
			url:PATH+'/common/import.jsp?dataType=orgData&dialog=org-dailog&startRow=6&templates=ORGANIZATION',
			buttons:{
				"提交" : function(event){
			      $("#mForm").submit();
			   },
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			},
			shouldEmptyHtml:false
		});
	});

	//双击行操作
	function doubleClickTable(rowid){
		$.createDialog({
			width:680,
			height:250,
			title:'查看部门详细',
			url:PATH+'/sysadmin/orgManage/toOrgDetailJsp.action?organization.id='+ rowid,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}

	function afterChangNode(node){
		var nodeId = node.attributes.id;
		$.get(PATH+"/sysadmin/orgManage/ajaxOrganization.action", {"organization.id":nodeId},function(data){
			updateOrgDetail(data);
			$("#orgChildren-dataGrid").setGridParam({
				url:PATH+"/sysadmin/orgManage/ajaxOrgPage.action"+'?parentId='+nodeId,
				datatype:'json'
			});
			$("#orgChildren-dataGrid").trigger("reloadGrid");
		});
	}
	function stringFormatter(str){
		if(str==undefined){
			return "";
		}
		return str;
	}
	$("#org-tree-autocomplete").autocomplete({
		source: function(request, response) {
			$.ajax({
				url: PATH+"/sysadmin/orgManage/ajaxFindOrganizationsByOrgName.action",
				data:{"organization.orgName":function(){return request.term;}},
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
			$.ajax({
				url:PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+ui.item.id,
				success:function(data){
					$.searchChild(tree,data);
				}
			});
		}
	});
	if(orgTypeCurrent == '1'){
		tree=$("#orgTree").initFunctionalTree();
	}else{
		tree=$("#orgTree").initTree();
	}

	$.addClick(tree,afterChangNode);



	$("#orgChildren-dataGrid").jqGridFunction({
	   	url:PATH+'/sysadmin/orgManage/ajaxOrgPage.action',
		datatype: "local",
	   	colNames:['id','子部门名称',  '子部门类型','子部门级别','子部门联系电话', '备注'],
	   	colModel:[
	   		{name:'id',index:'id',hidden:true,sortable:false},
	   		{name:'orgName',index:'orgName',sortable:true},
	   		{name:'orgType.id',index:'orgType.id',sortable:true,formatter:orgType},
	   		{name:'orgLevel.id',index:'orgLevel.id', sortable:true,formatter:orgLevel},
	   		{name:'contactWay',index:'contactWay', sortable:true},
	   		{name:'remark',index:'remark', sortable:false}
	   	],
	    loadComplete: function(){toggleButtons();},
		ondblClickRow: doubleClickTable,
	    onSelectRow:function(){toggleButtons();},
	    rowNum:-1,
	    pager:false,
	    shrinkToFit:true,
	    showColModelButton:false,
	    height:$(".ui-layout-west").height()-$(".path").height()-$("#content-top").height()-80
	});

	$("#refreshOrgTree").click(function(){
		$.selectRootNode(tree);
	    $("#org-tree-autocomplete").val("");
	    $("#orgChildren-dataGrid").setGridParam({url:PATH+"/sysadmin/orgManage/ajaxOrgPage.action"});
	    $("#orgChildren-dataGrid").trigger("reloadGrid");
	});

/////为按钮添加事件
	$('#toEnd').click(function(){
		if($("#orgChildren-dataGrid").jqGrid('getGridParam','selrow')){
			$("#orgChildren-dataGrid").toEnd(
				PATH+"/sysadmin/orgManage/ajaxMoveOrganization.action",
				{
					"organization.id":$("#orgChildren-dataGrid").jqGrid('getGridParam','selrow'),
					position:"last",
					"organization.parentOrg.id":$("#orgDetail-orgId").val()
				},
				function(){$.toEndNode(tree,$("#orgChildren-dataGrid").jqGrid('getGridParam','selrow'));toggleButtons();}
			);
		}else{
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
	});
	$('#toFirst').click(function(){
		if($("#orgChildren-dataGrid").jqGrid('getGridParam','selrow')){
			$("#orgChildren-dataGrid").toFirst(
				PATH+"/sysadmin/orgManage/ajaxMoveOrganization.action",
				{
					"organization.id":$("#orgChildren-dataGrid").jqGrid('getGridParam','selrow'),
					position:"first",
					"organization.parentOrg.id":$("#orgDetail-orgId").val()
				},
				function(){$.toFirstNode(tree,$("#orgChildren-dataGrid").jqGrid('getGridParam','selrow'));toggleButtons();}
			);
		}else{
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
	});
	$('#toPrevious').click(function(){
		if($("#orgChildren-dataGrid").jqGrid('getGridParam','selrow')){
			$("#orgChildren-dataGrid").toPrevious(
				PATH+"/sysadmin/orgManage/ajaxMoveOrganization.action",
				{
					"organization.id" : $("#orgChildren-dataGrid").jqGrid('getGridParam','selrow'),
					position:"before",
					"organization.parentOrg.id":$("#orgDetail-orgId").val(),
					"referOrgId":$("#orgChildren-dataGrid").getSelectedRowDom().prev().attr("id")
				},
				function(){$.toPreviousNode(tree,$("#orgChildren-dataGrid").jqGrid('getGridParam','selrow'));toggleButtons();}
			);
		}else{
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
	});
	$('#toNext').click(function(){
		if($("#orgChildren-dataGrid").jqGrid('getGridParam','selrow')){
			$("#orgChildren-dataGrid").toNext(
				PATH+"/sysadmin/orgManage/ajaxMoveOrganization.action",
				{
					"organization.id" : $("#orgChildren-dataGrid").jqGrid('getGridParam','selrow'),
					position:"after",
					"organization.parentOrg.id":$("#orgDetail-orgId").val(),
					"referOrgId":$("#orgChildren-dataGrid").getSelectedRowDom().next().attr("id")
				},
				function(){$.toNextNode(tree,$("#orgChildren-dataGrid").jqGrid('getGridParam','selrow'));toggleButtons();}
			);
		}else{
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
	});


	$("#add").click(function(){
		if($("#orgDetail-orgLevel").val()!='片组片格'){
		$("#org-dailog").createDialog({
			width:680,
			height:250,
			title:'新建部门',
			url:PATH+'/sysadmin/orgManage/orgCreateDLG.jsp?parentId='+$("#orgDetail-orgId").val(),
			buttons :{
				"保存" : function(){
					$("#org-create-form").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
		}else{
			$.messageBox({level:"warn",message:"网格层级不能再新增部门！"});
	 		return;
		}
	});

	$("#marge").click(function(){
		$("#org-dailog").createDialog({
			width:280,
			height:400,
			title:'部门合并',
			url:PATH+'/sysadmin/orgManage/mergeTree.jsp?orgId='+$("#orgDetail-orgId").val(),
			buttons :{
				"保存" : function(){
					closeDialog();
					$("#maintainOrgForm").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});

	$("#update").click(function(){
		$("#org-dailog").createDialog({
			width:680,
			height:250,
			title:'修改部门',
			url:PATH+'/sysadmin/orgManage/toUpdateOrgJsp.action?mode=edit&organization.id='+$("#orgDetail-orgId").val(),
			buttons :{
				"保存" : function(){
					$("#org-update-form").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});

	$("#delete").click(function(){

		$.confirm({
			title:"删除部门",
			message:"部门一经删除，无法恢复，确定删除此部门吗？",
			okFunc: function() {
				$.ajax({
					url:PATH+'/sysadmin/orgManage/checkDeleteOrgById.action?organization.id='+$("#orgDetail-orgId").val(),
					success:function(data){
						if(data != true){
							$.messageBox({
								message:data
							});
							return;
						}
						$.ajax({
							url:PATH+'/sysadmin/orgManage/ajaxDeleteById.action?organization.id='+$("#orgDetail-orgId").val(),
							success:function(data){
								if(data == true){
									$.messageBox({
										message:"部门删除成功！"
									});
									var childNode = $.getSelectedNode(tree);
									var parentNode = childNode.parentNode;
									parentNode.removeChild(childNode);
								}else{
									$.notice({
										level: 'info',//warn,alert,info
									    okbtnName: "确定",
									    title:'警告',
									    message: "<div class='alertCon'>不能删除此部门，已被当前以下模块引用：<div class='alert-messagetip'>"+data+"</div></div>",
									    okFunc: false,
									    width:400
									});
									$("#delete").buttonEnable();
								}
							},error:function(){
								$.messageBox({
									message:"不能删除此部门，此部门已被引用"
								});
								$("#delete").buttonEnable();
							}
						});
					}
				});
			}
		});

	});

	$("#deletegrid").click(function(){
        var node = $.getSelectedNode(tree);
	    if($.getSelectedNode(tree).attributes.orgLevelInternalId != <s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>){
	       $.confirm({
			title:"提示",
			message:"只能删除网格"
			})
			return;
	   }
		$.confirm({
			title:"删除部门",
			message:"部门一经删除，无法恢复，确定删除此部门吗？",
			okFunc: function() {
				$.ajax({
					url:PATH+'/sysadmin/orgManage/ajaxDeletegridById.action?organization.id='+$("#orgDetail-orgId").val(),
					success:function(data){
						if(data == true){
							$.messageBox({
								message:"部门删除成功！"
							});
							var childNode = $.getSelectedNode(tree);
							var parentNode = childNode.parentNode;
							parentNode.removeChild(childNode);
					    }
			         }
				});
			}
		});
	});
});

</script>
<!--  <script type="text/javascript" src="${resource_path }/resource/js/widget/orgList/orgList.js"></script>-->