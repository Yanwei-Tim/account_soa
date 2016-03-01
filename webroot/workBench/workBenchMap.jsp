<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="workbenchMap" >
	<iframe id="ifr" name="ifr"  height="300px" src=""></iframe>
</div>
<script type="text/javascript">
	$(function(){
		var src="<s:property value='@com.tianque.core.util.GridProperties@GIS_SERVER'/><s:property value='@com.tianque.core.util.GridProperties@GIS_INDEX_MAP'/>?sid=<s:property value='@com.tianque.core.util.ThreadVariable@getSession().sessionId'/>&organizationId="+getCurrentOrgId();
		$("#ifr").attr("src",src);
	});
</script>