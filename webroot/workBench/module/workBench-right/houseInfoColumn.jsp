<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%
	String type = request.getParameter("type");
	request.setAttribute("type",type);
%>
<div id="${type}Column" class="SigmaReport" style="height:230px;"></div>

<script type="text/javascript">
$(document).ready(function() {
	var chartCount=$("#${type}Column").columnChart({
		url: "${path}/baseInfo/statisticManage/getActualHouseStatisticColumn.action?type=${type}",
		moduleName:'实有房屋',
		textx:-150,
		quantity:'个数'
	});
});



</script>