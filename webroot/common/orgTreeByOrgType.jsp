<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="getFullOrgById" var="getFullOrgById" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>
<!-- 该树 可以 识别  组织结构 类型， 分别显示 行政区域 树和职能部门树 -->
<div id="orgTree-top">
<h1 class="tree-title"><span></span>组织结构</h1>
<div id="refreshOrgTreeDiv">
	<div class="ui-widget" >
		<input id="org-tree-autocomplete" type="text" style="width:154px;height:18px;border:1px solid #A8BECE;background:#fff; position:relative;margin:5px 0 5px 2px;" value=""/>
		<button id="refreshOrgTree" class="ui-icon ui-icon-refresh" style="border:0;background-color:transparent; position:absolute;top:37px;left:140px;cursor:pointer;outline: none;"></button>
	</div>
</div>
</div>
<div id="orgTree-select" style="overflow: auto;_width:160px;">
	<div id="org_tree_navigation" style="padding:0 0 0 10px;"></div>
</div>
<input id="currentOrgId" type="hidden"/>
<s:iterator value="extTreeData.children" var="child"></s:iterator>
<script defer>
	$(document).ready(function(){
		$("#orgTree-select").height($("#orgTree-select").parent().outerHeight()-$("#orgTree-top").outerHeight());
	});
	function afterChangNode(node){
		$("#currentOrgId").val(node.attributes.id);
		if(typeof(onOrgChanged) != 'undefined'){
			onOrgChanged.call(null,node.attributes.id,isGrid());
		}
	}

	<s:if test="#getFullOrgById.organization.orgType.internalId==@com.tianque.domain.property.OrganizationType@ADMINISTRATIVE_REGION">
	var tree = $("#org_tree_navigation").initAdministrativeTree({shouldJugeMultizones:true});
	</s:if>
	<s:elseif test="#getFullOrgById.organization.orgLevel.internalId>@com.tianque.domain.property.OrganizationType@FUNCTIONAL_ORG">
	var tree = $("#org_tree_navigation").initFunctionalTree({shouldJugeMultizones:true});
	</s:elseif>

	$.addClick(tree,afterChangNode);
	function isGrid(){
		return $.getSelectedNode(tree).attributes.orgLevelInternalId==<s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>;
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

</script>