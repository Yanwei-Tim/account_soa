<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="上传目录设置" class="container container_24" style="overflow:hidden;padding-top:25px;">
	    <form id="maintainForm" method="post" enctype="multipart/form-data" action="${path}/sysadmin/globalSettingManage/updateGlobalLoginSetting.action" >
			
    <div class="grid_6 lable-right">
		<label >背景图：</label>
	</div>
	<div class="grid_8">
		<input type="file" id="backImageToUpload" name="backImage"/>
	</div>
	<div class="grid_10">
	</div>
	
	<div style="clear:both"></div>
	<div class="grid_6 lable-right">
		<label>前景图：</label>
	</div>
	<div class="grid_8">
		<input type="file" id="prveImageToUpLoad" name="prveImage"/>
	</div>
	<div class="grid_10">
	</div>
	<div style="clear:both"></div>
	<div class="grid_6 lable-right">
		<label>logo图片：</label>
	</div>
	<div class="grid_8">
		<input type="file" id="LogoImageToUpLoad" name="logoImage"/>
	</div>
	<div class="grid_10">
	</div>
	
	<div style="clear:both"></div>
	<div class="grid_6 lable-right">
		<label>按钮：</label>
	</div>
	<div class="grid_8">
		<input type="file" id="buttonImageToUpLoad" name="buttonImage"/>
	</div>
	<div class="grid_10">
	</div>
	<div class="grid_6 lable-right">
		<label>鼠标移动图片：</label>
	</div>
	<div class="grid_8">
		<input type="file" id="mouseMoveImageToUpLoad" name="mouseMoveImage"/>
	</div>
	<div class="grid_10">
	</div>
	<div style="clear:both"></div>
	<div class="grid_6 lable-right">
			<label></label>
	</div>
	<div class="grid_8">
		<button type="submit" style="height:30px;">保存</button>
		<button type="button" id="chongzhi" style="height:30px;">重置</button>
	</div>
	   </form>
</div>
<script type="text/javascript">
var existed = true;
$(document).ready(function(){
	jQuery.validator.addMethod("checkExtbackImageFileName", function(value, element){
		if($("#backImageToUpload").val()==null || $("#backImageToUpload").val()==""){
			return true;
		}
		if($("#backImageToUpload").val().substring($("#backImageToUpload").val().lastIndexOf(".")+1,$("#backImageToUpload").val().length) != "jpg"
			&& $("#backImageToUpload").val().substring($("#backImageToUpload").val().lastIndexOf(".")+1,$("#backImageToUpload").val().length) != "JPG"
			&& $("#backImageToUpload").val().substring($("#backImageToUpload").val().lastIndexOf(".")+1,$("#backImageToUpload").val().length)!="gif"
			&& $("#backImageToUpload").val().substring($("#backImageToUpload").val().lastIndexOf(".")+1,$("#backImageToUpload").val().length) != "GIF"){
			return false;
		 }
		return true;
	});
	jQuery.validator.addMethod("checkExtprveImageFileName",function(value,element){
		if($("#prveImageToUpLoad").val()==null || $("#prveImageToUpLoad").val()==""){
			return true;
		} 
		if($("#prveImageToUpLoad").val().substring($("#prveImageToUpLoad").val().lastIndexOf(".")+1,$("#prveImageToUpLoad").val().length) != "jpg"
			&& $("#prveImageToUpLoad").val().substring($("#prveImageToUpLoad").val().lastIndexOf(".")+1,$("#prveImageToUpLoad").val().length) != "JPG"
			&& $("#prveImageToUpLoad").val().substring($("#prveImageToUpLoad").val().lastIndexOf(".")+1,$("#prveImageToUpLoad").val().length)!="gif"
			&& $("#prveImageToUpLoad").val().substring($("#prveImageToUpLoad").val().lastIndexOf(".")+1,$("#prveImageToUpLoad").val().length) != "GIF"){
			return false;
		 }
		 return true;
	});
	jQuery.validator.addMethod("checkExtbuttonImageFileName", function(value, element){
		if($("#buttonImageToUpLoad").val()==null || $("#buttonImageToUpLoad").val()==""){
			return true;
		}
		if($("#buttonImageToUpLoad").val().substring($("#buttonImageToUpLoad").val().lastIndexOf(".")+1,$("#buttonImageToUpLoad").val().length) != "jpg"
			&& $("#buttonImageToUpLoad").val().substring($("#buttonImageToUpLoad").val().lastIndexOf(".")+1,$("#buttonImageToUpLoad").val().length) != "JPG"
			&& $("#buttonImageToUpLoad").val().substring($("#buttonImageToUpLoad").val().lastIndexOf(".")+1,$("#buttonImageToUpLoad").val().length)!="gif"
			&& $("#buttonImageToUpLoad").val().substring($("#buttonImageToUpLoad").val().lastIndexOf(".")+1,$("#buttonImageToUpLoad").val().length) != "GIF"){
			return false;
		 }
		return true;
	});
	jQuery.validator.addMethod("checkExtmouseMoveImageFileName", function(value, element){
		if($("#mouseMoveImageToUpLoad").val()==null || $("#mouseMoveImageToUpLoad").val()==""){
			return true;
		}
		if($("#mouseMoveImageToUpLoad").val().substring($("#mouseMoveImageToUpLoad").val().lastIndexOf(".")+1,$("#mouseMoveImageToUpLoad").val().length) != "jpg"
			&& $("#mouseMoveImageToUpLoad").val().substring($("#mouseMoveImageToUpLoad").val().lastIndexOf(".")+1,$("#mouseMoveImageToUpLoad").val().length) != "JPG"
			&& $("#mouseMoveImageToUpLoad").val().substring($("#mouseMoveImageToUpLoad").val().lastIndexOf(".")+1,$("#mouseMoveImageToUpLoad").val().length)!="gif"
			&& $("#mouseMoveImageToUpLoad").val().substring($("#mouseMoveImageToUpLoad").val().lastIndexOf(".")+1,$("#mouseMoveImageToUpLoad").val().length) != "GIF"){
			return false;
		 }
		return true;
	});
	jQuery.validator.addMethod("checkExtLogoImageFileName", function(value, element){
		if($("#LogoImageToUpLoad").val()==null || $("#LogoImageToUpLoad").val()==""){
			return true;
		}
		if($("#LogoImageToUpLoad").val().substring($("#LogoImageToUpLoad").val().lastIndexOf(".")+1,$("#LogoImageToUpLoad").val().length) != "jpg"
			&& $("#LogoImageToUpLoad").val().substring($("#LogoImageToUpLoad").val().lastIndexOf(".")+1,$("#LogoImageToUpLoad").val().length) != "JPG"
			&& $("#LogoImageToUpLoad").val().substring($("#LogoImageToUpLoad").val().lastIndexOf(".")+1,$("#LogoImageToUpLoad").val().length)!="gif"
			&& $("#LogoImageToUpLoad").val().substring($("#LogoImageToUpLoad").val().lastIndexOf(".")+1,$("#LogoImageToUpLoad").val().length) != "GIF"){
			return false;
		 }
		return true;
	});
	
	<s:if test='!"view".equals(mode)'>
		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
			     doAjaxSubmit();
			},
			rules:{
				"backImage":{
				   checkExtbackImageFileName:true
			   },
			   "prveImage":{
				   checkExtprveImageFileName:true
			   },
			   "logoImage":{
				   checkExtLogoImageFileName:true
			   },
			   "buttonImage":{
				   checkExtbuttonImageFileName:true
			   },
			   "mouseMoveImage":{
				   checkExtmouseMoveImageFileName:true
			   }
			},
			messages:{
				"backImage":{
				   checkExtbackImageFileName:"图片只能为 jpg,gif格式的"
			   },
			   "prveImage":{
				   checkExtprveImageFileName:"图片只能为 jpg,gif格式的"
			   },
			   "logoImage":{
				   checkExtLogoImageFileName:"图片只能为 jpg,gif格式的"
			   },
			   "buttonImage":{
				   checkExtbuttonImageFileName:"图片只能为 jpg,gif格式的"
			   },
			   "mouseMoveImage":{
				   checkExtmouseMoveImageFileName:"图片只能为 jpg,gif格式的"
			   }
			}
		});
	</s:if>

	$("#chongzhi").click(function(){
		$.ajax({
			url:"${path}/sysadmin/globalSettingManage/resetGlobalLoginSetting.action",
			data:{
			},
			success:function(data){
				var jsonData = eval("("+data+")");
				if(jsonData.backImage=="" && jsonData.prevImage=="" 
					&& jsonData.buttonImage=="" && jsonData.mouseMoveImage==""){
					$.messageBox({message:"重置成功！"});
				}else{
					 $.messageBox({ message:"重置失败!",level: "error"});
				}
			}
		});
	})
	$.loadingComp("close");
});

function doAjaxSubmit(){
	$("#maintainForm").ajaxSubmit({
        success: function(data){
		var jsonData=eval("("+data+")");
		if((jsonData.errorBackImageType!="" && jsonData.errorBackImageType!=undefined)
			|| (jsonData.errorPrveImageType!="" && jsonData.errorPrveImageType!=undefined)
			|| (jsonData.errorButtonImageType!=""&& jsonData.errorButtonImageType!=undefined) 
			|| (jsonData.errorMouseMoveImageType!=""&&jsonData.errorMouseMoveImageType!=undefined)
			|| (jsonData.errorlogoImageType!=""&&jsonData.errorlogoImageType!=undefined)){
			
			var str = "";
			if(jsonData.errorBackImageType!="" && jsonData.errorBackImageType!=undefined){str = str+jsonData.errorBackImageType+"<br/>"}
			if(jsonData.errorPrveImageType!="" && jsonData.errorPrveImageType!=undefined){str = str+jsonData.errorPrveImageType+"<br/>"}
			if(jsonData.errorButtonImageType!=""&& jsonData.errorButtonImageType!=undefined){str = str+jsonData.errorButtonImageType+"<br/>"}
			if(jsonData.errorMouseMoveImageType!=""&&jsonData.errorMouseMoveImageType!=undefined){str = str+jsonData.errorMouseMoveImageType+"<br/>"}
			if(jsonData.errorlogoImageType!=""&&jsonData.errorlogoImageType!=undefined){str = str+jsonData.errorlogoImageType+"<br/>"}
			$.messageBox({message:str});
			return;
		}else if((jsonData.backImageLength!="" && jsonData.backImageLength!=undefined )
			|| (jsonData.prveImageLength !="" && jsonData.prveImageLength !=undefined)
			|| (jsonData.buttonImageLength !="" && jsonData.buttonImageLength !=undefined) 
			|| (jsonData.mouseMoveLength!="" && jsonData.mouseMoveLength!=undefined)
			|| (jsonData.logoImageLength!="" && jsonData.logoImageLength!=undefined)){
			
			var strLength = ""
			if(jsonData.backImageLength!="" && jsonData.backImageLength!=undefined){strLength = strLength+jsonData.backImageLength+"<br/>"}
			if(jsonData.prveImageLength !="" && jsonData.prveImageLength !=undefined){strLength = strLength+jsonData.prveImageLength+"<br/>"}
			if(jsonData.buttonImageLength !="" && jsonData.buttonImageLength !=undefined){strLength = strLength+jsonData.buttonImageLength+"<br/>"}
			if(jsonData.mouseMoveLength!="" && jsonData.mouseMoveLength!=undefined){strLength = strLength+jsonData.mouseMoveLength+"<br/>"}
			if(jsonData.logoImageLength!="" && jsonData.logoImageLength!=undefined){strLength = strLength+jsonData.logoImageLength+"<br/>"}
		    $.messageBox({message:strLength});
		    return;
		}else{
			
        	 $.messageBox({message:"成功保存图片!"});
        }
 	   }
 	});
}

function doNothing(){}

</script>