<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div  title="请选择部门">
	<div id="organization" style="clear: both;"></div>
</div>

<script type="text/javascript">
var orgSelector;
function closeDialog(){
	var selectOrgId=$.getSelectedNode(orgSelector).attributes.id;
	var selectOrgName=$.getSelectedNode(orgSelector).attributes.text;
	var parentNode=$.getSelectedNode(orgSelector).parentNode;
	var village=<s:property value="@com.tianque.domain.property.OrganizationLevel@VILLAGE"/>
	if($.getSelectedNode(orgSelector).attributes.orgLevelInternalId > village){
		$.confirm({
			title:"提示信息",
			message:"来源网格选择无法选择乡镇(街道)以上级别！",
			width:300,
			height:180,
			cancelFunc:function(){
				$("#noticeDialog").dialog("close");
			}
		});
	}else{
		if(parentNode&&!parentNode.isRoot)
			selectOrgName=parentNode.attributes.text+"->"+selectOrgName;
		if (selectOrgId!=null){
			setZone(selectOrgId,selectOrgName);
		}
		$("#noticeDialog").dialog("close");
	}
}
$(document).ready(function(){
	orgSelector=$("#organization").initTree();
	$.addDbClick(orgSelector,closeDialog);
});
</script>