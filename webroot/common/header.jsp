<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="photo">
	<s:if test='"location".equals(#parameters.type[0])'>
	<img id="headerImg" name="population.imgUrl" src="${path }/resource/images/locationHead.png"/>
	</s:if>
	<s:else>
	<img id="headerImg" name="population.imgUrl" src="${path }/resource/images/head.png"/>
	</s:else>
	
	<div class="shadow" style="display: none">
		<div class="bgc"></div>
		<div id="deleteHeaderImage" class="txt">删除当前图片</div>
	</div>
</div>

<form id="maintainUrlForm" method="post" action="${path}/imageUpload/uploadImg.action" enctype="multipart/form-data" name="maintainUrlForm">
	<div class="PictureUpload">
		<ul>
			<s:if test='"location".equals(#parameters.type[0])'>
				<li class="upPhoto1">
					<span class="fileFind"><input id="fileToUpload" type="file" value="" name="header" /></span>
				</li>
			</s:if>
			<s:else>
				<li class="upPhoto2">
					<span class="fileFind"><input id="fileToUpload" type="file" value="" name="header" /></span>
				</li>
			</s:else>	
			
		</ul>
	</div>
</form>
<script>
	$(function(){
		$("#maintainUrlForm").formValidate({
			 promptPosition: "bottomLeft",
			 submitHandler: function(form) {
		         $(form).ajaxSubmit({
		             success: function(data){
			             if(data == null){
			            	 $.messageBox({message:"上传失败，请上传正确格式的图像",level: "error"});
			            	 return;
				         }
				         if(data == "false"){
				        	 $.messageBox({message:"上传文件大于1M上传失败！",level: "error"});
			            	 return;
					     }
						$("#scanHeaderImage").createDialog({
							width: 600,
							height: 600,
							title:"头像截图",
							url:'${path}/baseinfo/commonPopulation/scanHeaderImage.jsp?imgUrl='+data,
							close:function(){
						        $("#fileToUpload").val("");
							},
							buttons: {
								"保存" : function(event){
							      $("#scanHeaderImageForm").submit();
							   },
							   "关闭" : function(){
							        $(this).dialog("close");
							   }
							}
						});
		         	}
		         })
			}
		});
		$("#fileToUpload").change(function(){
			$("#maintainUrlForm").submit();
		});
		$("#deleteHeaderImage").click(function(){
			$("#headerImg").attr("src","${path }/resource/images/head.png");
			$("#_imgUrl").val("");
			$(".shadow").hide();
			$(".PictureUpload").show();
		});
	})
	
</script>