<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="上传目录设置" class="container container_24" style="overflow:hidden;padding-top:25px;">
	<form id="maintainForm" method="post" enctype="multipart/form-data" action="${path}/sysadmin/globalSettingManage/uploadOrganizationExcel.action" >
	    <div class="grid_6 lable-right">
			<label >组织机构excel：</label>
		</div>
		<div class="grid_8">
			<input type="file" id="organizationExcel" name="organizationExcel" />
		</div>
		<div class="grid_8">
			<button type="button" id="submitForm" style="height:30px;">保存</button>
			<button type="button" id="reset" style="height:30px;">重置</button>
		</div>
	</form>
</div>
<script type="text/javascript">
function submitFile(){
	$("#maintainForm").ajaxSubmit({
        success: function(data){
        	if(data){
				$.messageBox({message:"上传已经成功!"});
	        }else{
				$.messageBox({message:"上传失败!"});
	        }
		}
 	});
}
function validateFile(){
	if($("#organizationExcel").val().substring($("#organizationExcel").val().lastIndexOf(".") + 1 , $("#organizationExcel").val().length) != "xls"){
		return false;
	}
	return true;
}
$(document).ready(function(){
	$("#submitForm").click(function(){
		if(validateFile()){
			submitFile();
		}else{
			$.messageBox({message:"请上传excel格式的文件!" , level:"error"});
		}
	})
	
	$("#reset").click(function(){
		$("#organizationExcel").val("");
	})
});
</script>