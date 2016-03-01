<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<jsp:include page="/includes/baseInclude.jsp" />      
<!DOCTYPE html>
<head>
<title>出错提示</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
body{width:100%;height:100%;text-align:center;margin:0 auto;}
.error .header{background:#405770;height:25px;}
.error .content{background:#F6F6F6;border:1px solid #E3E3E3;height:106px;}
.error .errormessage { position:relative;margin:0 auto;width:400px;}
.error .errormessage p{color:#666666;font-family:"微软雅黑";font-size:32px;height:60px;line-height:45px;margin-top:22px;}
.error .errormessage a{font-size:12px;color:#990000;display:block;position:absolute;bottom:0px;right:0px;}
.error .errormessage p img{ vertical-align:middle;padding-right:10px;}
</style>
</head>
<body>
<div class="error">
	<div class="header"></div>
	<div class="content">
		<div class="errormessage">
			<p><img src="${resource_path}/resource/images/wz/err.gif" /><span><%=exception.getMessage() %></span></p><a href="<%=request.getContextPath() %>">点此返回系统>></a>
		</div>
	</div>
	
</div>
</body>
</html>