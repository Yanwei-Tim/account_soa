<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:iterator value="permissions" var="p" >
	<pop:JugePermissionTag ename="${p.ename}">
		<ul class="pagelist">
			<s:iterator value="childMap.get(#p.ename)" var="child">
				<pop:JugePermissionTag ename="${child.ename}">
					<li><a href="javascript:void(0)" id="${child.ename}" onclick="showPageByTopMenu('basicInformation','${child.ename}')">
						<span>${child.cname}</span></a></li>
				</pop:JugePermissionTag>
			</s:iterator>
		 </ul>
	</pop:JugePermissionTag>
</s:iterator>
<script>
</script>