<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="zoneInfo_panel" style="height:20px;display:none;white-space:nowrap;width:90%;border: 1px solid #FFFFFF;">
		<span style="float:left;margin-left:10px;" id="zone_name"></span>
		<span id="remove_button" style="float:right">
			<ul id="selectzones"> 
			<li id="removeZone" title="删除部门"><a href="javascript:void(0)">[删除]</a></li>
			</ul>
		</span>
</div>
<select id="localzoneIds" name="localzoneIds" multiple="multiple" style="display:none"></select>
<div  title="请选择部门">
	<div id="organization" class="ui-widget-border" style="float:left;width:47%;height:320px;overflow:auto;"></div>
	<div id="allSelected" class="addgrid-center" style="float:left;width:5%;height:320px;"><img src="${resource_path}/resource/images/sele.jpg" /></div>
	<div id="selectedTable" class="addgrid-right ui-widget-border" style="float:left;width:44%;height:320px;overflow:auto;">&nbsp;</div>
</div>
<script type="text/javascript">
var orgSelector;

function selectZone(){
	if($.getSelectedNode(orgSelector).attributes.orgLevelInternalId!=0){
		return ;
	}
	var selectOrgId=$.getSelectedNode(orgSelector).attributes.id;
	var selectOrgName=$.getSelectedNode(orgSelector).attributes.text;
	if (hasLocalSelectZone(selectOrgId)) return;
	
	if (selectOrgId!=null){
		addLocalZone(selectOrgId,selectOrgName);
		addZoneToSelectedTable(selectOrgId,selectOrgName);
	}
}

function commitZone(){
	var zones=$("#localzoneIds").find("option");
	$("#zoneIds").empty();
	for (var index=0;index<zones.length;index++){
		addZone(zones[index].value,zones[index].text);
	}
	reviewZoneNameList();
}

function addZoneToSelectedTable(id,name){
	if(null != $.getSelectedNode(orgSelector)){
		if($.getSelectedNode(orgSelector).attributes.orgLevelInternalId!=0){
			return ;
		}
	}
	var tempSelector=$("#zoneInfo_panel");
	var newSelector=tempSelector.clone(true);
	newSelector.appendTo("#selectedTable");
	newSelector.show();
	newSelector.attr("id", "zone_"+id);
	newSelector.find("span[id='zone_name']").text(name);
	var removeButton=newSelector.find("li[id='removeZone']");
	removeButton.attr("id", id);
	removeButton.click(function(){removeZone(this)});
}

function initExistsZones(){
	var zones=$("#zoneIds").find("option");
	$("#localzoneIds").empty();
	for (var index=0;index<zones.length;index++){
		addZoneToSelectedTable(zones[index].value,zones[index].text);
		addLocalZone(zones[index].value,zones[index].text);
	}
}

function addLocalZone(zoneId,zoneName){
	if (hasLocalSelectZone(zoneId)) return;
	$("<option>").attr("id","local_zoneId_"+zoneId).val(zoneId).attr("selected",true).text(zoneName).appendTo($("#localzoneIds"));
}

function removeZone(zone){
	$("#zone_"+zone.id).remove();
	$("#local_zoneId_"+zone.id).remove();
}

function hasLocalSelectZone(zoneId){
	var zones=$("#localzoneIds").find("option");
	for (var index=0;index<zones.length;index++){
		if (zoneId==zones[index].value){
			return true;
		}
	}
	return false;
}
$(document).ready(function(){
	orgSelector=$("#organization").initAdministrativeTree({parentId:${organizationId},isRoot:true});
	initExistsZones();
	$.addDbClick(orgSelector,selectZone);
});
</script>