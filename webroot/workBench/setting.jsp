<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
    <div id="superiorVisit" class="container container_24">
		<div id=tabs>
			<ul>
				<li><a href="${path}/sysadmin/userManage/toPersonalDetailsUpdate.action">修改个人信息</a> </li>
				<li><a href="${path }/passwordUpdateDlg.jsp">修改密码</a> </li>
				<li><a href="${path }/sysadmin/userManage/findUserHasPlatformMessageTypeByUserId.action">订阅平台消息</a> </li>
			</ul>
   		</div>
  </div>
	<script>
	$(function() {
		$( "#tabs").tabs();
	});
	</script>