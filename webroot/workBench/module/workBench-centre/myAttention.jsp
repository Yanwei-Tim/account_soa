<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="clearfix">
	<div class="portlet_box">
		<div class="portlet_box_person">
			<a href="javascript:;"><img onload="setPNG(this,50,50)" width="50" height="50" src="/resource/workBench/images/icon/serviceTeamManagement.png"/></a>
		</div>
		<div class="portlet_box_personData">
			<a href="javascript:;" class="number"><div id="workBench_team">${teamAmount}</div></a>
			<div class="downSorrow"></div>
		</div>
		<p class="portlet_box_title"><a href="javascript:;">服务团队</a></p>
	</div>
	<div class="portlet_box">
		<div class="portlet_box_person">
			<a href="javascript:;"><img onload="setPNG(this,50,50)" width="50" height="50" src="/resource/workBench/images/icon/serviceTeamMemberManagement.png"/></a>
		</div>
		<div class="portlet_box_personData">
			<a href="javascript:;" class="number"><div id="workBench_member">${memberAmount}</div></a>
			<div class="downSorrow"></div>
		</div>
			<p class="portlet_box_title"><a href="javascript:;">服务人员</a></p>
	</div>
</div>
	<div class="portlet_object">
		<div class="portlet_object_data">
			<span class="sendData">服务对象:&nbsp;<a href="javascript:;">${objectAmount}</a>&nbsp;人</span>
		</div>
		<div class="portlet_object_data">
			<span class="sendData">累计服务次数:&nbsp;<a href="javascript:;">${recordAmount}</a>&nbsp;次</span>
		</div>
		<div class="portlet_object_data">
			<span class="sendData">累计服务人数:&nbsp;<a href="javascript:;">${objectHasRecordAmount}</a>&nbsp;人</span>
		</div>
	</div>

<script>
$(document).ready(function(){
});
</script>