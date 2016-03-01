﻿﻿<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
GlobalSettingService globalSettingService = (GlobalSettingService)WebApplicationContextUtils.getWebApplicationContext(this.getServletContext()).getBean("globalSettingService");
String frontPageBottom = globalSettingService.getGlobalValue(GlobalSetting.FRONTPAGE_BOTTOM_PAGE);
String backImage = globalSettingService.getGlobalValue(GlobalSetting.BACK_IMAGE);
String prevImage = globalSettingService.getGlobalValue(GlobalSetting.PREV_IMAGE);
String buttonImage = globalSettingService.getGlobalValue(GlobalSetting.BUTTON_IMAGE);
String mouseMoveImage = globalSettingService.getGlobalValue(GlobalSetting.MOUSE_MOVE_IMAGE);
request.setAttribute("backImage",backImage);
request.setAttribute("prevImage",prevImage);
request.setAttribute("buttonImage",buttonImage);
request.setAttribute("mouseMoveImage",mouseMoveImage);
%>
<!DOCTYPE html>
<html>
<head>
<%@page import="com.tianque.core.globalSetting.service.GlobalSettingService"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.tianque.core.globalSetting.util.GlobalSetting"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>基层社会管理综合信息系统</title>
<jsp:include page="/includes/baseInclude.jsp" />
<link rel="shortcut icon" href="${resource_path}/resource/images/favicon.ico" type="image/x-icon"/>
<link type="text/css" href="${resource_path }/resource/js/poshytip/tip-error/tip-error.css" rel="stylesheet" />
<link type="text/css" href="${resource_path }/resource/js/jsValidate/css/validationEngine.jquery.css" rel="stylesheet" />
<link rel="shortcut icon" href="${resource_path }/resource/images/favicon.ico" type="image/x-icon"/>
<style type="text/css">
	body,h1,h2,h3,h4,h5,h6,p,blockquote,dl,dt,dd,ul,ol,li,pre,fieldset,lengend,button,input,textarea,th,td {margin: 0;padding: 0;}
	html,body{width:100%;height:100%;overflow:hidden;}
	.dcd-logbg{width:100%;height:100%;position:absolute;top:0;left:0;overflow:hidden;}
	.dcd-topBg{width:1000px;height:171px;margin-left:-500px;background:url(/resource/system/images/login/red/topbg.png) no-repeat;position:absolute;top:0px;left:50%;}
	.dcd-logCon{width:748px;height:639px;margin:-370px 0 0 -374px;background:url(/resource/system/images/login/red/loginf.png) no-repeat;position:absolute;top:50%;left:50%;}
	.copyright{width: 850px;margin:0 0 0 -425px;font:12px/45px "";color:#9E251D;position:absolute;bottom:0;left:50%;text-align: center;}
	.dcd-loginC{width:850px;height:82px;margin:460px auto 0 auto;background:url(/resource/system/images/login/red/logo.png) no-repeat top center;}
	.dcd-logform{width:850px;height:45px;margin:0 auto;}
	.dcd-logform .inputSearch{float:left;width:295px;height:45px;*display:inline;*zoom:1;margin:0 5px;padding:0 0 0 15px;font:bold 15px/45px "microsoft yahei";color:#95A2AE;background:url(/resource/system/images/login/red/textbg.png) no-repeat;}
	.dcd-logform .inputSearchTxt{background-position:0 0;}
	.dcd-logform .inputSearchPwd{background-position:0 -52px;}
	.dcd-logform .inputSearchSecret{width:95px;height:40px;margin:2px 0 0;padding:0px;background:none;}
	.dcd-logform .inputSearch .searchU,.searchT{float:left;height:30px;}
	.dcd-logform .inputSearch .searchT{width:140px;}
	.dcd-logform .inputSearch .inputText{width:180px;height:23px;margin:12px 0 0 4px;border:none;font:12px/19px "microsoft yahei";background:transparent;}
	.dcd-logform .inputSearch .errorInput{background:#fee; border:1px solid #DB0027;}
	.dcd-logform .inputSearchSecret a{width:95px;height:40px;display:inline-block;*display:inline;*zoom:1;text-decoration:none;color:#fff;text-align:center;background: url(/resource/system/images/login/red/formbtn.png) no-repeat;}
	.dcd-logform .inputSearchCheckbox{margin:0 0 0 10px;_margin:10px 0 0 10px;font:12px/45px "";color:#fff;}
	#loginInfo{text-align:center;height:30px;font: bold 16px/30px '';color:#9E251D;}
</style>

<script type="text/javascript" src="${resource_path}/resource/js/jquery.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/poshytip/jquery.poshytip.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/jquery.bgiframe.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/jsValidate/jquery.form.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/jsValidate/jquery.validate.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/widget/formValidate.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/widget/widget.js"></script>
<script type="text/javascript">
$.fn.extend({
    backgroundajust:function(options){
		 var $self=$(this);
		 
		 var hx=document.documentElement||document.body;
		 var w=$self.width()||1000;
		 var h=$self.height()||1000;
		 var c=1.6;
		 var pw =hx.offsetWidth || 1024;
		 var ph=hx.offsetHeight || 768;
		 var pc = pw / ph;
		function bgFunc(){
		 if (c < pc){
				$self.width(pw);
				$self.height( 'auto' );
				$self.css( "marginLeft" ,"0px" );
				$self.css( "marginTop" ,-(pw / c - ph) / 2+'px' );
		   }else{
				$self.width( 'auto' );
				$self.height(ph);
				$self.css( "marginTop" ,"0px" );
				$self.css( "marginLeft" ,-(ph * c - pw) / 2 + 'px' );
			}
		} 
		bgFunc();
	}
})
$(function(){
	var timer;
	$("#bg").backgroundajust();
	$(window).resize(function(){	 
		 clearTimeout(timer);
		 timer=setTimeout($("#bg").backgroundajust(),200);
	})
    $("#username").focus();

    $("#sub").click(function(){
        $('#loginForm').submit();
    });
    $("#changeValidateCode").click(function(){
        $("#validateCodeImg").attr("src","${path }/validateCodeServlet?date="+new Date());
    });

    $("#username,#password").bind("keydown",function(evt){
        var evt = window.event?window.event:evt;
        if(evt.keyCode==13){
            $('#loginForm').submit();
        }
    });

    if($.cookie('username')){            //判断cookie是否为空，如果不为空继续下一步。
         $('#username').val($.cookie('username'));     //从cookie里面取出里面的值。
         $('#remuser').attr("checked","checked");
    }
    $("#loginForm").formValidate({
        submitHandler: function(form) {
    	$("#loginInfo").text("系统登录中...");
        $("#submithref").attr('disabled', true);
            $(form).ajaxSubmit({
                success:function(data){
                    $("#submithref").attr('disabled', false);
                    if(data==null||data==true){
                        if($.cookie("failureTimes")){
                            $.cookie("failureTimes",0,{path: '/'});
                        }
                        var userName=$('#username').val();
                        if($('#remuser').attr('checked')){
                            $.cookie('username',userName,{ path: '/',expires:7});
                        }else{
                            $.cookie('username',null,{ path: '/',expires:0});
                        }    
                        document.location.href="${path}/module.jsp#index";
                    }else if(data==false){
                        document.location.href="${path}/sessionManage/toFirstPasswordUpdate.action";
                    }else{
                        if($.cookie("failureTimes")){
                            $.cookie("failureTimes",parseInt($.cookie("failureTimes"))+1);
                        }else{
                            $.cookie("failureTimes",1,{path: '/'});
                        }
                        var jsonMsg=eval("("+data+")");
                        if(jsonMsg.userName){
                            var inputObject=$("input[name='userName']");
                             $("#loginInfo").text("用户名或密码错误，请重新输入");
                            if($.cookie("failureTimes")>=3||parseInt(jsonMsg.failureTimes)>=3){
                                $(".login-yztext").show();
                                $("#validateCodeImg").attr("src","${path }/validateCodeServlet?date="+new Date());
                            }
                        }
                        if(jsonMsg.validateCode){
                            var inputObject=$("input[name='validateCode']");
                            inputObject.dialogtip({
                                content:jsonMsg.validateCode,alignX: 'center',alignY: 'top'
                            });
                            inputObject.poshytip('show');
                        }
                    }
                }
            });
            return false;
        },
        rules: {
            userName:{
                required:true
                //isDigitStrAndUnderline:true
            },
            password:{
                required:true
            }
        },
        messages: {
            userName:{
                required:"用户名不能为空！"
                //isDigitStrAndUnderline:"用户名只能由数字、字母、下划线组成"
            },
            password:{
                required:"密码不能为空！"
            }
        }
    });
});
</script>
</head>
<body>
<div class="dcd-login" id="productLogin">
    <div class="dcd-logbg">
    	<img id="bg" src="/resource/system/images/login/red/bg.jpg" />
    </div>
       <div class="dcd-topBg"></div>
    <div class="dcd-logCon">
       	<div class="dcd-loginC"></div>
    	<div class="dcd-logform">
           	<form method="post" action="${path}/sessionManage/login.action" id="loginForm">
           		<div id="loginInfo">${errorMessage}
                    <s:property value="#parameters.errorMessage"/>
                </div>
                <div class="inputSearch inputSearchTxt"><div class="searchU"><label>用户名：</label></div><div class="searchT"><input id="username" type="text" class="inputText" name="userName" /></div></div>
					<div class="inputSearch inputSearchPwd"><div class="searchU"><label>密码：</label></div><div class="searchT"><input id="password" type="password" class="inputText" name="password" /></div></div>
					<div class="inputSearch inputSearchSecret"><a href="javascript:;" class="dcd-login" id="submithref" name="submithref" onclick="$('#loginForm').submit();"></a></div>
					<div class="inputSearch inputSearchSecret inputSearchCheckbox"><label><input type="checkbox" id="remuser" /><span>&nbsp;记住用户名</span></label></div>
            </form>
        </div>
    </div>
    <div class="copyright"><%=frontPageBottom %></div>
</div>
<!--[if IE 6]>
<script type="text/javascript" src="${resource_path}/resource/js/DD_belatedPNG.js"></script>
<script type="text/javascript">
	//解决IE6下 PNG 图片兼容的JS插件
	DD_belatedPNG.fix('#productLogin .dcd-logCon');
	DD_belatedPNG.fix('#productLogin .dcd-logform .inputSearch a');
    DD_belatedPNG.fix('#productLogin .dcd-logform .inputSearch .searchT');
    DD_belatedPNG.fix('#productLogin .dcd-topBg');
	DD_belatedPNG.fix('#productLogin ..dcd-loginC');
    DD_belatedPNG.fix('#productLogin .dcd-logform .inputSearch');
</script>
<![endif]-->
</body>
</html>
