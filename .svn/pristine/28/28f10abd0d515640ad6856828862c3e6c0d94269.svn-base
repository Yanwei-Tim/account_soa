<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<!DOCTYPE html>
<html>
<head>
<title>密码修改</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="shortcut icon" href="${resource_path}/resource/images/favicon.ico" type="image/x-icon"/>
<link type="text/css" href="${resource_path}/resource/css/reset.css" rel="stylesheet" />
<link type="text/css" href="${resource_path}/resource/css/guide.css" rel="stylesheet" />
<script type="text/javascript" src="${resource_path}/resource/js/jquery.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/jquery.bgiframe.js"></script>

<link href="${resource_path}/resource/js/jsValidate/css/validationEngine.jquery.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${resource_path}/resource/js/jsValidate/jquery.form.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/jsValidate/jquery.validate.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/widget/formValidate.js"></script>

<script type="text/javascript" src="${resource_path}/resource/js/poshytip/jquery.poshytip.min.js"></script>
<link href="${resource_path}/resource/js/poshytip/tip-yellowsimple/tip-yellowsimple.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="${resource_path}/resource/js/widget/widget.js"></script>

</head>
<body>
	<div id="header">
		<div id="header-top">
			<div id="header-left"></div>
			<div id="header-center"><b>社会管理综合信息系统</b><strong>Beta 2.0</strong>
				<ul id="font">
					<li class="home"></li>
					<li class="exit"></li>
				</ul>
			</div>
			<div id="header-right"></div>
		</div>
	</div>

	<div id="getpassword">
		<s:if test='"editPass".equals(mode)'>
			<div id="top">修改个人登入密码</div>
		</s:if>
		<s:else>
			<div id="top">初次登入本系统请及时修改初始密码</div>
		</s:else>
		<div class="form-list">
			<form name="loginform"  method="post" action="${path }/sysadmin/userManage/updatePasswordEmail.action" id="firstUpdatePassForm">
				<div style="color: red">${errorMessage }</div>
				<input type="hidden" value="<s:property value="session.userId"/>" name="user.id"/>
				<div class="row">
					<label class="form-label"><em>*</em>旧密码：</label>
					<div class="collection">
						<input type="password" class="form-txt" name="oldPassword"/>
					</div>
				</div>
				<div class="row">
					<label class="form-label"><em>*</em>新密码：</label>
					<div class="collection">
						<input id="password" type="password" class="form-txt" name="currentPassword"/>
					</div>
				</div>
				<div class="row">
					<label class="form-label"><em>*</em>再输一遍：</label>
					<div class="collection">
						<input type="password" class="form-txt" name="validatePassword"/>
					</div>
				</div>
				<div class="row" style="text-align:center;">
					<div class="collection">
						<input type="button" class="form-btn" value="确认修改" />
					</div>
					<div class="collection">
						<input type="button" class="form-btn" value="返回" onClick="javascript:history.back(-1)"/>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div id="bottom">
			<p>技术开发：杭州天阙科技有限公司&nbsp;&nbsp;&nbsp;&nbsp;短信平台：10639696&nbsp;&nbsp;&nbsp;&nbsp;电话：1656454645&nbsp;&nbsp;&nbsp;&nbsp;QQ：1468487484</p>
	</div>
<script type="text/javascript"> 
	$(document).ready(function(){
		$(".form-btn").click(function(){$("#firstUpdatePassForm").submit();});
		$("#firstUpdatePassForm").formValidate({
			promptPosition :"bottomLeft",
			submitHandler: function(form) {
				$(form).ajaxSubmit({
					success:function(data){
						if(data==true){
							document.location.href="${path}/index.jsp";
						}else{
							var inputObject=$("input[name='currentPassword']");
							inputObject.dialogtip({
								content: data,alignX: 'right',alignY: 'center'
							});
							inputObject.poshytip('show');
						}
					}
				});
				return false;
			},
			rules: {
				oldPassword: {
					required: true,
					minlength: 6,
					maxlength: 24
				},
				currentPassword :{
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
					required: "请输入新密码！",
					minlength: $.format("至少需要{0}个字符"),
					maxlength: $.format("不能大于{0}个字符")
				},
				validatePassword :{
					equalTo:"密码和确认密码不相符",
					required: "请重新输入密码！",
					minlength: $.format("至少需要{0}个字符"),
					maxlength: $.format("不能大于{0}个字符")
				}
			}
		});
	});
</script> 
</body>
</html>