<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="${resource_path}/resource/js/validatePassword/digitalspaghetti.password.js"></script>
<jsp:include page="/includes/baseInclude.jsp" />
<style>
.password-strength-bar{text-align:center;}
</style>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />

<div id="dialog-form" title="修改密码" class="container container_24" style="height:120px;width:390px;text-align:left;">
	<div style="color: red">${errorMessage }</div>
	<div class='clearLine'>&nbsp;</div>
	<form name="loginform"  method="post" action="${path }/sysadmin/userManage/updatePasswordEmail.action" id="firstUpdatePassForm">
		<input type="hidden" value="<s:property value="#loginAction.user.id"/>" name="user.id" id="userId"/>
		<div  class="grid_5 lable-right" >
		<em class="form-req">*</em>
			<label class="form-label">旧密码：</label>
		</div>
		<div class="grid_15">
			<input type="password" class="form-txt" name="oldPassword" id="oldPassword" maxlength="16"/>
		</div>
		<div class="grid_1">	  		
	  	</div>

		<div class='clearLine'>&nbsp;</div>
		<div  class="grid_5 lable-right" >
		<em class="form-req">*</em>
			<label class="form-label">新密码：</label>
		</div>
		<div class="grid_15 heightAuto">
		<input id="password" type="password" name="currentPassword"  title="请输入由6-16位字母、数字、特殊字符任意组合的密码"  class="dialogtip form-txt" maxlength="16"/>
		</div>
		<div class="grid_1">	  		
	  	</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
		<em class="form-req">*</em>
			<label class="form-label">确认密码：</label>
		</div>
		<div class="grid_15">
			<input type="password" class="form-txt" name="validatePassword" maxlength="16"/>
		</div>
		<div class="grid_1">	  		
	  	</div>
	</form>
</div>
<script type="text/javascript"> 
var scoreResult=0;
var bol = true;
$(".dialogtip").inputtip();
jQuery.validator.addMethod("checkPassWordStrong", function(value, element){
	if(value==null||value==undefined||value==""){return true}
	var passwordVal=$(".password-strength-bar").text();
	if(passwordVal=="弱"){
		return false;
	}else{
		return true;
	}
});
jQuery.validator.addMethod("oldPasswordIsRight", function(value, element){
	if(value==null||value==undefined||value==""){return true}
	$.ajax({
		async:false,
		url:"${path}/sysadmin/userManage/oldPasswordIsRight.action",
		data:{
			"oldPassword":$("#oldPassword").val(),
			"user.id":$('#userId').val()
		},
		success:function(responseData){
			bol = responseData;		
		}
	});
	return bol; 
});
	$(document).ready(function(){
		$("#firstUpdatePassForm").formValidate({
			promptPosition :"bottomLeft",
			submitHandler: function(form) {
				$(form).ajaxSubmit({
					success:function(data){
						if(data==true){
							 $.messageBox({message:"成功保存用户修改信息!"});
							$("#maintainDlg").dialog("close");
							$("#settingDialog").dialog("close");
						}else{
							var inputObject=$("input[name='oldPassword']");
							inputObject.dialogtip({
								content: '旧密码输入错误',alignX: 'right',alignY: 'center'
							});
							inputObject.poshytip('show');
						}
					}
				});
				return false;
			},
			rules: {
				oldPassword: {
					required: true
				},
				currentPassword :{
					notEqualTo:"#oldPassword",
					required: true,
					minlength: 6,
					maxlength: 24,
					checkPassWordStrong:true
				},
				validatePassword :{
					equalTo:"#password",
					required: true,
					minlength: 6,
					maxlength: 24
				}
			},
			messages: {
				oldPassword: {
					required: "请输入旧密码！",
					minlength  : $.format("至少需要{0}个字符"),
					maxlength  : $.format("不能大于{0}个字符")
				},
				currentPassword :{
					notEqualTo:"新密码不能和旧密码相同",
					required: "请输入新密码！",
					minlength: $.format("至少需要{0}个字符"),
					maxlength: $.format("不能大于{0}个字符"),
					checkPassWordStrong:"密码过弱，建议输入由6-16位字母、数字、特殊字符任意两种或三种组合的密码"
				},
				validatePassword :{
					equalTo:"两次输入密码不一致",
					required: "请重新输入密码！",
					minlength: $.format("至少需要{0}个字符"),
					maxlength: $.format("不能大于{0}个字符")
				}
			}
		});
	});
	$("#password").passwordCheck({
		keyUp:function(score){
		scoreResult = score;
		}
	});
</script> 
