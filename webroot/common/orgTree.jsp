<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<script>
	var tree = $("#org_tree_navigation").initAdministrativeTree(
		{
			shouldJugeMultizones:true
			<s:if test='null!=#parameters.selectedOrgId[0] && !"".equals(#parameters.selectedOrgId[0])'>
			,isRootSelected:false
			</s:if>
		}
	);
	function isGrid(){
		return $.getSelectedNode(tree).attributes.orgLevelInternalId == <s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>;
	}

	function isCountryDownOrganization(){
		return $.getSelectedNode(tree).attributes.orgLevelInternalId <= <s:property value="@com.tianque.domain.property.OrganizationLevel@VILLAGE"/>;
	}

	function isDistrictDownOrganization(){
		return $.getSelectedNode(tree).attributes.orgLevelInternalId < <s:property value="@com.tianque.domain.property.OrganizationLevel@DISTRICT"/>;
	}
	function isTownDownOrganization(){
		return $.getSelectedNode(tree).attributes.orgLevelInternalId < <s:property value="@com.tianque.domain.property.OrganizationLevel@TOWN"/>;
	}
	function isVillage(){
		return $.getSelectedNode(tree).attributes.orgLevelInternalId == <s:property value="@com.tianque.domain.property.OrganizationLevel@VILLAGE"/>;
	}
	function isTown(){
		return $.getSelectedNode(tree).attributes.orgLevelInternalId == <s:property value="@com.tianque.domain.property.OrganizationLevel@TOWN"/>;
	}
	function isDistrict(){
		return $.getSelectedNode(tree).attributes.orgLevelInternalId == <s:property value="@com.tianque.domain.property.OrganizationLevel@DISTRICT"/>;
	}
	function isCity(){
		return $.getSelectedNode(tree).attributes.orgLevelInternalId == <s:property value="@com.tianque.domain.property.OrganizationLevel@CITY"/>;
	}
	function isProvince(){
		return $.getSelectedNode(tree).attributes.orgLevelInternalId == <s:property value="@com.tianque.domain.property.OrganizationLevel@PROVINCE"/>;
	}
	function isCountry(){
		return $.getSelectedNode(tree).attributes.orgLevelInternalId == <s:property value="@com.tianque.domain.property.OrganizationLevel@COUNTRY"/>;
	}

	
	$(document).ready(function(){
		$("#orgTree-select").height($("#orgTree-select").parents(".ui-layout-west").outerHeight()-$("#orgTree-top").outerHeight());
		var lastSelectedNode=false;
		function afterChangNode(node){
			$("#currentOrgId").val(node.attributes.id);
			$("#org-tree-autocomplete").val(node.attributes.text);
			
			if(typeof(onMenuChanged) != 'undefined' ){
				onMenuChanged.call(null,node,isGrid());
			}
			if(checkedIsNeedClickWhenDataView() && !($(".hover").find("span").text() == "辖区信息")){ 
				$(".click").removeClass("click").click();
			}else {//调用 页面的 onOrgChanged 方法
				if(typeof(onOrgChanged) != 'undefined'){
					onOrgChanged.call(null,node.attributes.id,isGrid());
				}
			}
			
			lastSelectedNode=node;
		}
		$.addClick(tree,afterChangNode);
		<s:if test='null!=#parameters.selectedOrgId[0] && !"".equals(#parameters.selectedOrgId[0])'>
		$.setTreeValue(<s:property value="#parameters.selectedOrgId[0]"/>,tree);
		</s:if>

		function stringFormatter(str){
			if(str==undefined){
				return "";
			}
			return str;
		}
		
		$("#org-tree-autocomplete").autocomplete({
			source: function(request, response) {
				$.ajax({
					url: PATH+"/sysadmin/orgManage/ajaxFindOrganizationsByOrgNameAndOrgType.action",
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

		$("#refreshOrgTree").click(function(){
			$.selectRootNode(tree);
		});
		$('#refreshOrgTree li').hover(
				function() { $(this).addClass('ui-state-hover'); },
				function() { $(this).removeClass('ui-state-hover'); }
		);
		
	});



	//记录上次点击的是 什么部门 用于判断是否需要 切换
	var lastSelectOrgLevel = -1;
	function checkedIsNeedClickWhenDataView(){
		var bool = false;
		if(!<s:property value="#parameters.isBasicInformation"/>){
			bool = false;
		}else{
			var selectOrgLevel = $.getSelectedNode(tree).attributes.orgLevelInternalId;
			if(lastSelectOrgLevel == -1){
				lastSelectOrgLevel = selectOrgLevel;
			}
			
			if(lastSelectOrgLevel < <s:property value="@com.tianque.domain.property.OrganizationLevel@DISTRICT"/> && isDistrictDownOrganization()
					 || lastSelectOrgLevel >= <s:property value="@com.tianque.domain.property.OrganizationLevel@DISTRICT"/> && !isDistrictDownOrganization()
					 ){
				bool = false;
			}else{
				bool = true;
			}

			lastSelectOrgLevel = selectOrgLevel;
		}
		return bool;
	}
</script>