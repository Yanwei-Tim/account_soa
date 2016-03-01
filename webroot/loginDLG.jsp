<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:include page="/includes/baseInclude.jsp" />
<style>
.out_box{border:1px solid #ccc;border-top:0;background:#fff; font:12px/20px Arial;color:#333;text-align:left;}
.list_box{height:25px;line-height:25px;cursor:pointer;padding:0 7px;}
.list_box span,.focus_box span{width:auto;}
.focus_box{background:#39e;color:#fff;height:25px;line-height:25px;cursor:pointer;padding:0 7px;}
</style>
<script type="text/javascript" src="${resource_path}/resource/js/mailAutoComplute/jquery.mailAutoComplete.js"></script>
<div class="login">
	<form id="loginForm-dlg" action="${path}/sessionManage/loginForDlg.action" method="post">
		<dd><span>用户名：</span><input type="text" class="username" name="userName" id="username"></dd>
		<dd><span>密&nbsp;&nbsp;码：</span><input type="password" class="password" name="password" maxlength="24"></dd>
		<dd><a id="loginForm-dlg-submit" href="javascript:void(0)" class="loginbutton"></a></dd>
	</form>
</div>
<script>
	$(document).ready(function(){
		$(".username").focus();
		$("#validateCodeImg-dlg").attr("src","${path }/validateCodeServlet?date="+new Date());
		$("#changeValidateCode-dlg").click(function(){
			$("#validateCodeImg-dlg").attr("src","${path }/validateCodeServlet?date="+new Date());
		});
		$("#username").mailAutoComplete({
		    boxClass: "out_box", //外部box样式
		    listClass: "list_box", //默认的列表样式
		    focusClass: "focus_box", //列表选样式中
		    markCalss: "mark_box", //高亮样式
		    autoClass: false,
		    textHint: true, //提示文字自动隐藏
		    hintText: "请输入用户",
		    mailArr:["sg","hzsg","husg","nbsg","jxsg","qzsg",
		        	 "jhsg","sxsg","wzsg","tzsg","zssg","lssg"]
		});
		$("#loginForm-dlg").formValidate({		
			submitHandler:function(form){
			  $(form).ajaxSubmit({
				  success:function(data){
				  	if(data==true){
					  isPopLoginDialog=false;
					  startDate=new Date();
			          $("#login-dlg").dialog("close");
				       
				  	}else{
				  		var jsonMsg=eval("("+data+")");
						if(jsonMsg.userName){
							var inputObject=$("input[name='userName']");
							inputObject.dialogtip({
								content:jsonMsg.userName,alignX: 'right',alignY: 'center'
							});
							inputObject.poshytip('show');
							if($.cookie("failureTimes")>=3||parseInt(jsonMsg.failureTimes)>=3){
								$(".login-img").show();
								$("#validateCodeImg").attr("src","${path }/validateCodeServlet?date="+new Date());
							}
						}
						if(jsonMsg.validateCode){
							var inputObject=$("input[name='validateCode']");
							inputObject.dialogtip({
								content:jsonMsg.validateCode,alignX: 'right',alignY: 'center'
							});
							inputObject.poshytip('show');
						}
				  	}
				  }
			  });
			},
			rules: {
				userName:{
					required:true,
					//isDigitStrAndUnderline:true
				},
				password:{
					required:true
				}
			},
			messages: {
				userName:{
					required:"用户名不能为空！",
					//isDigitStrAndUnderline:"用户名只能由数字、字母、下划线组成"
				},
				password:{
					required:"密码不能为空！"
				}
			}
		});
		$("#loginForm-dlg-submit").click(function(){$("#loginForm-dlg").submit();});
	});
</script>