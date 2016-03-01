<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="重置用户密码" class="container container_24">
	<form id="maintainForm" class="formular" method="post"	action="${path}/sysadmin/userManage/resetUserPassword.action" >
			<input id="userId"	type="hidden" name="user.id" value="${user.id}" /> 
			<div class="grid_10">
				<label class="form-lbl">
					&nbsp;&nbsp;&nbsp;&nbsp;用户名:
	    		</label>
	    	</div>
			<div class="grid_14">
				<span>${user.userName}</span> 
			</div>
			<div class="grid_10">
				<label class="form-lbl">
					&nbsp;&nbsp;&nbsp;&nbsp;用户姓名:
	    		</label>
	    	</div>
			<div class="grid_14">
				<span>${user.name}</span> 
			</div>
			<div class="grid_10">
			<em class="form-req">*</em>
				<label class="form-lbl">请输入新密码:</label> 
			</div>
			<div class="grid_12 heightAuto">
			    <input type="password" name="user.password" id="password" 
	  				 value="" class="dialogtip form-txt" 
	  				  title="请输入由6-16位字母、数字、特殊字符任意组合的密码" maxlength="16"/>
			</div>
			<div class="grid_2"></div>
			<div class="grid_10">
			<em class="form-req">*</em>
				<label class="form-lbl">请重新输入密码:</label> 
			</div>
			<div class="grid_12">
			    <input type="password" name="confirmPwd" id="confirmPwd" 
	  				value=""
	  				class="form-txt" maxlength="16"/>
			</div>
			<div class="grid_2"></div>
	</form>
</div>
<script type="text/javascript">
var scoreResult=0;
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
$("#maintainForm").formValidate({
	promptPosition: "bottomLeft",
	submitHandler: function(form) {
        $(form).ajaxSubmit({
            success: function(data){
		    	$.messageBox({message:"成功重置用户登录密码!"});
			    $("#userMaintanceDialog").dialog("close");
     	   },
     	   error: function(XMLHttpRequest, textStatus, errorThrown){
     	      alert("提交错误");
     	   }
     	  });
	},
	rules:{
		"user.password":{
			required:true,
			minlength:6,
			maxlength:24,
			checkPassWordStrong:true
		},
		"confirmPwd":{
			equalTo:"#password"
		}
	},
	messages:{
		"user.password":{
			required:"请输入用户密码",
			minlength:$.format("密码至少需要输入{0}个字符"),
			maxlength:$.format("密码至少需要输入{0}个字符"),
			checkPassWordStrong:"密码过弱，建议输入由6-16位字母、数字、特殊字符任意两种或三种组合的密码"
		},
		"confirmPwd":{
			equalTo:"两次输入密码不一致"
		}
	}
});
$("#password").passwordCheck({
	keyUp:function(score){
	scoreResult = score;
	}
});
</script>
