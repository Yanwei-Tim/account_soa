<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="上传目录设置" class="container container_24"  style="overflow:hidden;padding-top:25px;">
	<s:if test='!"view".equals(mode)'>
	    <form id="maintainForm" method="post" action="" >
	</s:if>
			
    <div class="grid_6 lable-right">
		<label >临时文件目录：</label>
	</div>
	<div class="grid_8">
		<input type="text" 
		name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@TMP_PATH"/>'
		 class="form-txt" maxlength="50" id="tmpPath" 
		 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@TMP_PATH)"/>"/>
	</div>
	<div class="grid_10">
		上传文件的临时存放目录
	</div>
	
	<div style="clear:both"></div>
	<div class="grid_6 lable-right">
		<label>事件处理目录：</label>
	</div>
	<div class="grid_8">
		<input type="text" 
		name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@ISSUE_PATH"/>'
		 class="form-txt" maxlength="50" id="issuePath" 
		 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@ISSUE_PATH)"/>" />
	</div>
	<div class="grid_10">
		事件处理附件存放的目录 
	</div>
	
	<div style="clear:both"></div>
	<div class="grid_6 lable-right">
		<label>工作台帐目录：</label>
	</div>
	<div class="grid_8">
		<input type="text" 
		name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@DAILYLOG_PATH"/>'
		 class="form-txt" maxlength="100" id="dailylogPath" 
		 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@DAILYLOG_PATH)"/>"/>
	</div>
	<div class="grid_10">
		工作台帐文件附件存放的目录
	</div>
	
	<div style="clear:both"></div>
	<div class="grid_6 lable-right">
		<label>互动交流目录：</label>
	</div>
	<div class="grid_8">
		<input type="text" 
		name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@MAIL_PATH"/>'
		 class="form-txt" maxlength="100" id="mailPath" 
		 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@MAIL_PATH)"/>"/>
	</div>
	<div class="grid_10">
		互动交流附件存放的目录
	</div>
	
	<div style="clear:both"></div>
	<div class="grid_6 lable-right">
		<label>网格介绍、人员头像和系统中附件目录：</label>
	</div>
	<div class="grid_8">
		<input type="text" 
		name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@UPLOADFILE_PATH"/>'
		 class="form-txt" maxlength="100" id="uploadFilePath" 
		 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@UPLOADFILE_PATH)"/>"/>
	</div>
	<div class="grid_10">
		网格介绍、人员头像图片和系统中附件存放的目录
	</div>
	
	<div class="grid_8">
	</div>
	
	<div class="grid_8">
		<button type="submit" style="height:30px;">保存</button>
		<button type="reset" style="height:30px;">重置</button>
	</div>
   <s:if test='!"view".equals(mode)'>
	   </form>
	</s:if>
</div>
<script type="text/javascript">
var existed = true;
$(document).ready(function(){
	<s:if test='!"view".equals(mode)'>
		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
				doAjaxSubmit();
			}
			});
	</s:if>

	$("#maintainForm").attr("action","${path}/sysadmin/globalSettingManage/updateFileDirectorySetting.action");
	$.loadingComp("close");
});

function doAjaxSubmit(){
	$("#maintainForm").ajaxSubmit({
        success: function(data){
			$.messageBox({message:"成功保存上传目录设置信息!"});
 	   },
 	   error: function(XMLHttpRequest, textStatus, errorThrown){
 	     $.messageBox({message:"提交错误",level: "error"	});				
 	   }
 	});
}

function doNothing(){}

</script>