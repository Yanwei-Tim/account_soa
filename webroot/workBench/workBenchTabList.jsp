<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
				<a class="tools">
					<ul class="tools_content hidden">
						<li class=""><label><input type="checkbox"/>&nbsp;我的待办事项</label></li>
						<li class=""><label><input type="checkbox"/>&nbsp;短信收件箱</label></li>
						<li class=""><label><input type="checkbox"/>&nbsp;平台收件箱</label></li>
					</ul>
				</a>
<div id="baseTabs" style="height:500px;">
	<s:if test='@com.tianque.domain.property.WorkBenchType@SUPER_LEVEL_NAME.equals(role.workBenchType.displayName)'>
	<ul>
		<li><a href="/workBench/module/workBench-right/actualPopulationTendencyChart.jsp" id="issueList1">实有人口</a></li>
		<li><a href="/workBench/module/workBench-right/attentionPopulationPie.jsp?type=all_attention_population" id="issueList3">重点人口</a></li>
		<li><a href="/workBench/module/workBench-right/houseInfoColumn.jsp?type=actualHouse" id="issueList4">实有房屋</a></li>
	</ul>
	</s:if>
	<s:if test='@com.tianque.domain.property.WorkBenchType@MIDDLE_LEVEL_NAME.equals(role.workBenchType.displayName)'>
	<ul>
		<li><a href="/workBench/module/workBench-right/issueOfMyNeedDoList.jsp" id="issueList1">我的待办事项</a></li>
		<li><a href="/workBench/module/workBench-right/smsReceivedBoxList.jsp" id="issueList2">短信收件箱</a></li>
		<li><a href="/workBench/module/workBench-right/workBenchPmsmsList.jsp" id="issueList3">平台收件箱</a></li>
		<li><a href="/workBench/module/workBench-right/subLogListAll.jsp" id="issueList4">民情日志</a></li>
	</ul>
	</s:if>
	<s:if test='@com.tianque.domain.property.WorkBenchType@LOWER_LEVEL_NAME.equals(role.workBenchType.displayName)'>
	<ul>
		<li><a href="/workBench/module/workBench-right/issueOfMyNeedDoList.jsp" id="issueList1">我的待办事项</a></li>
		<li><a href="/workBench/module/workBench-right/smsReceivedBoxList.jsp" id="issueList2">短信收件箱</a></li>
		<li><a href="/workBench/module/workBench-right/workBenchPmsmsList.jsp" id="issueList3">平台收件箱</a></li>
	</ul>
	</s:if>
</div>
<script>
$(function(){
	$("#baseTabs").tabs();
});
</script>
