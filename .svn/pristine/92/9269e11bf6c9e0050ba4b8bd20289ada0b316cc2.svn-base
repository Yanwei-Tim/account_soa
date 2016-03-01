<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="手机版本设置" class="container container_24" style="overflow:hidden;padding-top:25px;">
    <form id="maintainForm" method="post" action="" enctype="multipart/form-data">
	  
		<div class="grid_4 lable-right">
			<label>手机版本：</label>
		</div>
		<div class="grid_8">
			<input type="text" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_VERSION"/>'
			 class="form-txt" maxlength="50" id="mobileVersion" value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@MOBILE_VERSION)"/>" />
		</div>
		<div class="grid_12">
			如:1.0.23
		</div>
		
		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<label>上传安装文件：</label>
		</div>
		<div class="grid_8">
		<input id="fileToUpload" type="file" value="" name="upload">
		</div>
		
		<div style="clear:both"></div>
		<div class="grid_5 lable-right">
			<label></label>
		</div>
		<div class="grid_8">
			<!--  <input type="button" value="保存" id="submitBtn"> -->
			<button type="submit" style="height:30px;">保存</button>
			<button type="reset" style="height:30px;">重置</button>
		</div>
   	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	var fileLength = document;
	jQuery.validator.addMethod("checkFullFile", function(value, element){
		if($("#fileToUpload").val() == null || $("#fileToUpload").val()==""){
			return false;
		 }
		return true;
	});
	jQuery.validator.addMethod("checkFileExtName", function(value, element){
		if($("#fileToUpload").val().substring($("#fileToUpload").val().lastIndexOf(".")+1,$("#fileToUpload").val().length) != "apk"
			&& $("#fileToUpload").val().substring($("#fileToUpload").val().lastIndexOf(".")+1,$("#fileToUpload").val().length) != "APK"){
			return false;
		 }
		return true;
	});

	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
			doAjaxSubmit();
		},
       rules:{
			"upload":{
			checkFullFile:true,
			checkFileExtName:true
		   }
		},
		messages:{
			"upload":{
			checkFullFile:"文件为空不能上传",
			checkFileExtName:"文件只能为apk格式的"
		   }
		}
	});

	//$("#submitBtn").click(function(){
		//$("#maintainForm").submit();
	//});

	
	$("#maintainForm").attr("action","${path}/sysadmin/globalSettingManage/updateMobileVersion.action");

	$.loadingComp("close");
});

function doAjaxSubmit(){
	$("#maintainForm").ajaxSubmit({
        success: function(data){
			 $.messageBox({message:"成功保存手机版本设置信息!"});
 	   },
 	   error: function(XMLHttpRequest, textStatus, errorThrown){
 	     	$.messageBox({message:"提交错误",level: "error"	});				
 	   }
 	});
}

function doNothing(){}

</script>