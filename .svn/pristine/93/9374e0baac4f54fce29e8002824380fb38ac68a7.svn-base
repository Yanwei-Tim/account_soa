<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title>修改用户密码</title>
		<jsp:include page="/includes/baseInclude.jsp" />
		<jsp:include page="/includes/jsInclude.jsp" />
	</head>
	<body>
		<div class="container container_24" style="width: 960px;margin-top: 50px">
			<div style="color: red">${errorMessage }</div>
			<form method="post" action="${path }/sysadmin/userManage/updatePasswordEmail.action" id="firstUpdatePassForm">
				<input type="hidden" value="<s:property value="@com.tianque.component.ThreadVariable@getSession()@getUserId()"/>" name="user.id"/>
				<div class="grid_6">
					<label>旧密码：<em class="form-req">*</em></label>
				</div>
				<div class="grid_18">
					<input type="password"  name="oldPassword" value="" id="oldPassword" />
				</div>
				<div class="grid_6">
					<label>新密码：<em class="form-req">*</em></label>
				</div>
				<div class="grid_18">
					<input type="password" id="password" name="currentPassword" value="" />
				</div>
				<div class="grid_6">
					<label>请重新输入新密码：<em class="form-req">*</em></label>
				</div>
				<div class="grid_18">
					<input type="password" name="validatePassword" value="" />
				</div>
				<div class="grid_24">
					<button name="" type="submit">修改</button>
				</div>
			</form>
		</div>
	</body>
	<script type="text/javascript"> 
	$("#firstUpdatePassForm").formValidate({
		rules: {
			oldPassword: {
				required: true
			},
			currentPassword :{
				notEqualTo:"#oldPassword",
				required: true,
				minlength: 6,
				maxlength: 24
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
				notEqualTo:"不能和旧密码相同",
				required: "请输入新密码！",
				minlength: $.format("至少需要{0}个字符"),
				maxlength: $.format("不能大于{0}个字符")
			},
			validatePassword :{
				equalTo:"两次输入密码不一致",
				required: "请重新输入密码！",
				minlength: $.format("至少需要{0}个字符"),
				maxlength: $.format("不能大于{0}个字符")
			}
		}
	});
	</script> 
</html>