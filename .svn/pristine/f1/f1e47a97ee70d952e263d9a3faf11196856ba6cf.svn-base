<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div id="actualPopulationTendencyChart" class="SigmaReport" style="height:230px;"></div>

<script type="text/javascript">
$(document).ready(function() {
	$("#actualPopulationTendencyChart").lineChart({
		url: "${path}/baseInfoStat/tendencyChart/showActualPopulationTendencyChart.action",
		moduleName:"实有人口增长趋势图"
	});
});
</script>