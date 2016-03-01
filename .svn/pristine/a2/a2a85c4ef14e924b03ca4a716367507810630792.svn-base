<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div  title="请选择部门">
	<div id="organization" style="clear: both;"></div>
</div>
<script type="text/javascript">
var orgSelector;
function closeDialog(){
	var selectOrgId=$.getSelectedNode(orgSelector).attributes.id;
	if (selectOrgId!=null){
		addZone(selectOrgId);
	}
	$("#noticeDialog").dialog("close");
}
$(document).ready(function(){
	orgSelector=$("#organization").initTree();
	$.addDbClick(orgSelector,closeDialog);
});
</script>