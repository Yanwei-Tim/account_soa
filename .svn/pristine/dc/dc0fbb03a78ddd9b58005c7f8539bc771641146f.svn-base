<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<script type="text/javascript">
	function proclamationPolling(){
		$.ajax({
			url:"${path}/sysadmin/proclamationManage/getDisplayProclamation.action",
				success:function(data){
					if(data!=null){
					if(data.id){
						$.announcement({content:data.content,dataId:data.id});
					}
				}
			}
		});
	}
	setInterval("proclamationPolling()",30000);
</script>