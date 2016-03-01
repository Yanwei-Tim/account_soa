<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="${keyName}Tabs">
	<ul>
	<s:iterator value="tabPatels">
		<li><a href="${url}" id="${keyName}" maxUrl="${maxUrl}">${cname}</a></li>
	</s:iterator>
	</ul>
</div>
<div id="module">
</div>
<script>
$(function(){
	$("#${keyName}Tabs").workBenchTabs();
});

</script>