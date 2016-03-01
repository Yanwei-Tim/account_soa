<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<div id="tabList">
	<ul>
		<s:iterator value="permissions" var="p">
			<pop:JugePermissionTag ename="${p.ename}">
			<li><a href="${path}${p.normalUrl}">${p.cname}</a></li>
			</pop:JugePermissionTag>
		</s:iterator>
	</ul>
</div>
<script>
	$(function() {
		$("#tabList").tabs({
			cache:false,
			load:function(){
				setOrgSelect();
			},
			select:function(){
				$("#tabList .ui-tabs-panel").empty();
				$("#baseLine").nextAll(":not(.ui-autocomplete):not('.ui-datepicker')").remove();
				$("#baseLine").nextAll(":not(.ui-autocomplete):not('.ui-datepicker'):hidden").remove();
			}
		});
	});
</script>