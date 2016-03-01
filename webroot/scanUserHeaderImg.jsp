<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<img id="fileToUploadImg" name="user.headerUrl" src="${path }/<s:property value='#parameters.imgUrl'/>"/>

<form id="scanHeaderImageForm" action="/imageUpload/scanAndCutImage.action" method="post">
	<input name="srcPath" type="hidden" value="<s:property value='#parameters.imgUrl'/>"/>
	<input id="imgWidth" name="imgWidth" type="hidden" />
	<input id="imgHeight" name="imgHeight" type="hidden" />
	<input id="cropX" name="cropX" type="hidden" />
	<input id="cropY" name="cropY" type="hidden" />
	<input id="cropWidth" name="cropWidth" type="hidden" />
	<input id="cropHeight" name="cropHeight" type="hidden" />
</form>

<script>
	$(function(){
		$("#scanHeaderImageForm").formValidate({
			 promptPosition: "bottomLeft",
			 submitHandler: function(form) {
		         $(form).ajaxSubmit({
		             success: function(data){
		             	$("#imgUrl").attr("src",$("#fileToUploadImg").attr("src")+"?"+Math.random());
		             	$("#headerUrl").val($("#fileToUploadImg").attr("src")+"?"+Math.random());
		             	$("#deleteHeaderImageBox").show();
		             	$("#scanHeaderImage").dialog("close");
		         	}
		         })
			}
		});
		function registerInput(jracObject,input,event_name){
			jracObject.observator.register(event_name, input);
			input.bind(event_name, function(event, jracObject, value) {
				$(this).val(value);
			})
		}
		var cropX=(590-200)/2;
		var cropY=(480-200)/2;
		$("#fileToUploadImg").jrac({
			'crop_width': 200,
			'crop_height': 200,
			'crop_x': cropX,
			'crop_y': cropY,
			'crop_resize': false,
			'viewport_resize': false,
			'viewport_onload': function(){
				var jracObject=this;
				if(jracObject.observator.crop_consistent()){
					$("#cropX,#cropY").val("");
				}
				registerInput(jracObject,$("#cropX"),'jrac_crop_x');
				registerInput(jracObject,$("#cropY"),'jrac_crop_y');
				registerInput(jracObject,$("#imgWidth"),'jrac_image_width');
				registerInput(jracObject,$("#imgHeight"),'jrac_image_height');
				registerInput(jracObject,$("#cropWidth"),'jrac_crop_width');
				registerInput(jracObject,$("#cropHeight"),'jrac_crop_height');
			}
		})
	})
</script>