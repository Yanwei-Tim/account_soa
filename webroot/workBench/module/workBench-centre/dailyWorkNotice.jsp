<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="clearfix">
	<div class="portlet_box">
		<div class="portlet_box_person">
			<a href="javascript:;"><img src="/resource/workBench/images/icon_Statements.png"/></a>
		</div>
		<div class="portlet_box_personData">
			<a href="javascript:;" class="number"><div id="reports"></div></a>
			<div class="downSorrow"></div>
		</div>
		<p class="portlet_box_title"><a href="javascript:;">报表</a></p>
	</div>
	<div class="portlet_box">
		<div class="portlet_box_person">
			<a href="javascript:;"><img src="/resource/workBench/images/icon_send.png"/></a>
		</div>
		<div class="portlet_box_personData">
			<a href="javascript:;" class="number"><div id="files"></div></a>
			<div class="downSorrow"></div>
		</div>
			<p class="portlet_box_title"><a href="javascript:;">收发文</a></p>
	</div>
</div>
<div class="portlet_object cf">
	<div class="portlet_object_data">
		<span class="sendData">已逾期报表:<a href="javascript:;">${overdueReports}</a></span>
		<span class="">待签收文件:<a href="javascript:;">${notsignDocs}</a></span>
	</div>
	<div class="portlet_object_data">
		<span class="sendData">即将到期报表:<a href="javascript:;">${willOverdueReports}</a></span>
		<span class="">待阅读文件:<a href="javascript:;">${notReadDocs}</a></span>
	</div>
</div>

<script>
$(document).ready(function(){
	var reportsNum = ${overdueReports}+${willOverdueReports};
	var filesNum = ${notsignDocs}+${notReadDocs};
	$("#reports").text(reportsNum);
	$("#files").text(filesNum);
});
</script>