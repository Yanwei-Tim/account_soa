<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="globalSettingTab">
	<ul class="subnavbar">
			<li><a href="${path}/sysadmin/globalSettingManage/dispatch.action?mode=fileDirectory">上传目录设置</a></li>
			<li><a href="${path}/sysadmin/globalSettingManage/dispatch.action?mode=sysHeaderAndBottomPage">页眉页脚设置</a></li>
	</ul>
</div>
<div>
	<div class="content_contact" >
	  	<div id="contentDiv_contact"></div>
	</div>
</div>
<script type="text/javascript">
$(function() {
	$("#globalSettingTab").tabs({cache:false,beforeLoad:function(ui){
		$("#maintainForm", $("#contentDiv")).remove();
		$(ui.panel).height($(".ui-layout-center").height()-60)
	}});
});
</script>