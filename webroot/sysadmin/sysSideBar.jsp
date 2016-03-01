<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="ajaxOrganization" var="getLoginOrg" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>

<div id="accordion">
	<pop:JugePermissionTag ename="baseSystemManagement">
	<div>
	  	<h1><a href="javascript:void(0)" id="baseSystemManagement" class="baseSystemManagement blurMenuClass"><s:property value="#request.name"/></a></h1>
		<ul>
			<pop:JugePermissionTag ename="userManagement">
				<li><a href="javascript:void(0)" id="userManagement" onclick="asyncOpen(this,'${path}/sysadmin/userManage/userList.jsp')"><span class="userManagement"><s:property value="#request.name"/></span></a></li>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="roleManagement">
				<li><a href="javascript:void(0)" id="roleManagement" onclick="asyncOpen(this,'${path}/sysadmin/roleManage/roleList.jsp')"><span class="roleManagement"><s:property value="#request.name"/></span></a></li>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="organizationManagement">
			   <li><a href="javascript:void(0)"  id="organizationManagement" onclick="asyncOpen(this,'${path}/sysadmin/orgManage/orgList.jsp')"><span class="organizationManagement"><s:property value="#request.name"/></span></a></li>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="usersListManagement">
			   <li><a href="javascript:void(0)" id="usersListManagement" onclick="asyncOpen(this,'${path}/sysadmin/userManage/users.jsp')"><span class="usersListManagement"><s:property value="#request.name"/></span></a></li>
		    </pop:JugePermissionTag>
		    <pop:JugePermissionTag ename="publicNoticeManagement">
		       <li><a href="javascript:void(0)" id="publicNoticeManagement" onclick="asyncOpen(this,'${path}/sysadmin/publicNoticeManage/publicNoticeList.jsp')" ><span class="publicNoticeManagement"><s:property value="#request.name" /></span></a></li>
		    </pop:JugePermissionTag>
	
		    <pop:JugePermissionTag ename="publicNoticeCCKManagement">
		       <li><a href="javascript:void(0)" id="publicNoticeCCKManagement" onclick="asyncOpen(this,'${path}/sysadmin/publicNoticeManage/publicNoticeCCKList.jsp')"><span class="publicNoticeManagementCCK"><s:property value="#request.name" /></span></a></li>
		    </pop:JugePermissionTag>
		 
		    <pop:JugePermissionTag ename="usersListManagement">
		       <li><a href="javascript:void(0)" id="usersListManagement" onclick="asyncOpen(this,'${path}/gis3D/sysadmin/layerManage.jsp')"><span class="usersListManagement">gis辖区管理</span></a></li>
		    </pop:JugePermissionTag>
		</ul>
 	</div>
 	</pop:JugePermissionTag>
 	<div>
 		<pop:JugePermissionTag ename="advancedSystemManagement">
	  		<h1><a href="javascript:void(0)" id="advancedSystemManagement" class="advancedSystemManagement blurMenuClass" ><s:property value="#request.name"/></a></h1>
	  	</pop:JugePermissionTag>
		<ul>

			<pop:JugePermissionTag ename="systemLogsManagement">
			<s:if test="#loginAction.user.admin">
				<li><a href="javascript:void(0)" id="systemLogsManagement" onclick="asyncOpen(this,'${path}/sysadmin/logManage/logList.jsp')"><span class="systemLogsManagement"><s:property value="#request.name"/></span></a></li>
			</s:if>
			</pop:JugePermissionTag>

<!--			<pop:JugePermissionTag ename="systemhighLogsManagement">-->
<!---->
<!--				<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/sysadmin/highlogManage/highlogList.jsp')"><span class="systemhighLogsManagement"><s:property value="#request.name"/></span></a></li>-->
<!---->
<!--			</pop:JugePermissionTag>-->
			<pop:JugePermissionTag ename="onlineSessions">
			   	 <li><a href="javascript:void(0)" id="onlineSessions" onclick="asyncOpen(this,'${path}/sysadmin/onlineSessions.jsp')"><span class="onlineSessions"><s:property value="#request.name"/></span></a></li>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="propertyDictManagement">
				<li><a href="javascript:void(0)" id="propertyDictManagement" onclick="asyncOpen(this,'${path }/sysadmin/propertyManage/findPropertyDomain.action')"><span class="propertyDictManagement"><s:property value="#request.name"/></span></a></li>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="systemNoticeManageMent">
				<s:if test="#loginAction.user.admin">
					<li><a href="javascript:void(0)" id="systemNoticeManageMent" onclick="asyncOpen(this,'${path }/sysadmin/proclamationManage/proclamationsList.action')"><span class="systemNoticeManageMent"><s:property value="#request.name"/></span></a></li>
				</s:if>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="issueTypesManagement">
                <li><a href="javascript:void(0)" id="issueTypesManagement" onclick="asyncOpen(this,'${path}/sysadmin/issueTypeManage/findIssueTypeDomains.action')"><span class="issueTypesManagement"><s:property value="#request.name"/></span></a></li>
            </pop:JugePermissionTag>
     		<pop:JugePermissionTag ename="workCalendarManagement">
               <li><a href="javascript:void(0)" id="workCalendarManagement" onclick="asyncOpen(this,'${path}/sysadmin/workCalendarManger/findWorkCalendar.action')"><span class="workCalendarManagement"><s:property value="#request.name"/></span></a></li>
            </pop:JugePermissionTag>
            <pop:JugePermissionTag ename="userExamineManageMent">
	            <s:if test="#loginAction.user.admin">
	                <li><a href="javascript:void(0)" id="userExamineManageMent" onclick="asyncOpen(this,'${path }/sysadmin/assessmentUserManage/assessmentUserList.jsp')"><span class="userExamineManageMent""><s:property value="#request.name"/></span></a></li>
	            </s:if>
	        </pop:JugePermissionTag>
            <pop:JugePermissionTag ename="globalSettingManageMent">
	            <s:if test="#loginAction.user.admin">
	                <li><a href="javascript:void(0)" id="globalSettingManageMent" onclick="asyncOpen(this,'${path }/sysadmin/sysGlobalSetting/sideBar.jsp')"><span class="globalSettingManageMent"><s:property value="#request.name"/></span></a></li>
	            </s:if>
	        </pop:JugePermissionTag>
      		<pop:JugePermissionTag ename="permissionManageMent">
	            <s:if test="#loginAction.user.admin">
	            	<li><a href="javascript:void(0)" id="permissionManageMent" onclick="asyncOpen(this,'${path }/sysadmin/permissionManage/permissionManageList.jsp')"><span class="permissionManageMent"><s:property value="#request.name"/></span></a></li>
	            </s:if>
	        </pop:JugePermissionTag>
	        <s:if test="#loginAction.user.admin">
	            <li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/sysadmin/jobMonitor/jobMonitor.jsp')"><span class="permissionManageMent">job监控</span></a></li>
	        </s:if>
		</ul>
  	</div>
</div>
<script>
$(function() {
	var jsflag='<s:property value='#parameters.urlflag'/>';
	if(jsflag==undefined || jsflag=='' || !$("#"+jsflag)[0]){
		$('#accordion > div > ul > li:first > a').click();
	}
	$.selectMapMenu({
		id:'accordion',
		jsflag:jsflag
	});
});
</script>