<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<span>责任区：
	<s:action var="findMultizonesWithParentOrgNameByUserId" name="findMultizonesWithParentOrgNameByUserId" namespace="/sysadmin/orgManage" executeResult="false" ignoreContextParams="true"></s:action>
	<select id="currentOrgId" class="zeren">
		<s:iterator value="#findMultizonesWithParentOrgNameByUserId.organizations" var="org">
			<option value="<s:property value="#org.id"/>" isGrid="<s:property value="#org.orgLevel.internalId==@com.tianque.domain.property.OrganizationLevel@GRID"/>">
				<s:property value="#org.orgName"/>
			</option>
		</s:iterator>
	</select>
</span>
<script>
function isGrid(){
	return $("#currentOrgId option:selected").attr("isGrid")=="true";
}

function isCountryDownOrganization(){
	return true;
}

</script>