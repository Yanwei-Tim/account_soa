<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div  title="请选择部门">
		<div id="organization" style="clear: both;"></div>
	</div>
<form id="maintainOrgForm" method="post" action="${path}/sysadmin/mergeOrganization/mergeOrganizationByOrgId.action">
	<input name="orgId" id="orgId" value="<s:property value="#parameters.orgId"/>" type="hidden">
	<input name="mergeOrgId" id="mergeOrgId" value="" type="hidden">
</form >
<script type="text/javascript">
var orgSelector;
function closeDialog(){
	var selectOrgId=$.getSelectedNode(orgSelector).attributes.id;
	if (selectOrgId!=null){
		$("#mergeOrgId").val(selectOrgId);
	}
}
$(document).ready(function(){
	orgSelector=$("#organization").initTree();
	$.addDbClick(orgSelector,closeDialog);
	$("#maintainOrgForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
         $(form).ajaxSubmit({
             success: function(data){
                     if(data){
                    	 $.messageBox({message:"组织机构数据合并成功!"});
     					$("#org-dailog").dialog("close");
                     }
      	   },
	      error: function(data){
      		 $.messageBox({
					message:data,
					level: "error"
				 });
	      	   }
	       });
		},
		rules:{
			},
		messages:{
		}
	});
});
</script>