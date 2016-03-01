var tree;
function toggleButtons(){
	var rowId = $("#orgChildren-dataGrid").jqGrid('getGridParam','selrow');
	if(rowId==undefined){
		$("#toPrevious").buttonDisable();
		$("#toNext").buttonDisable();
		$("#toFirst").buttonDisable();
		$("#toEnd").buttonDisable();
	}else{
		$("#toPrevious").buttonEnable();
		$("#toNext").buttonEnable();
		$("#toFirst").buttonEnable();
		$("#toEnd").buttonEnable();
		var previd=$("#"+rowId+"[role='row']",$("#orgChildren-dataGrid")).prev().attr("id");
		if(previd==undefined||previd==""){
			$("#toPrevious").buttonDisable();
			$("#toFirst").buttonDisable();
		}
		var nextid=$("#"+rowId+"[role='row']",$("#orgChildren-dataGrid")).next().attr("id");
		if(nextid==undefined||nextid==""){
			$("#toNext").buttonDisable();
			$("#toEnd").buttonDisable();
		}
	}
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
		$("#update").buttonDisable();
		$("#delete").buttonDisable();
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
$(document).ready(function() {
	$("#importOrgs").click(function(event){
		$("#org-dailog").createDialog({
			width: 400,
			height: 200,
			title:'数据导入',
			url:PATH+'/common/import.jsp?dataType=orgData&dialog=org-dailog&startRow=1&templates=ORGANIZATION',
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
			toggleMaintainsButtons();
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
	   		{name:'orgName',index:'orgName',sortable:false},
	   		{name:'orgType.id',index:'orgType.id',sortable:false,formatter:orgType},
	   		{name:'orgLevel.id',index:'orgLevel.id', sortable:false,formatter:orgLevel},
	   		{name:'contactWay',index:'contactWay', sortable:false},
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
				$("#delete").buttonDisable();
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
									$("#delete").buttonDisable();
									$("#update").buttonDisable();
									$("#add").buttonDisable();
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




});