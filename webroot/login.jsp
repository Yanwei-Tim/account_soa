<%@page import="com.tianque.core.util.GridProperties"%><%@ page language="java" pageEncoding="UTF-8"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%
	String currentProject = GridProperties.CURRENT_PROJECT;
	request.setAttribute("currentProject",currentProject);
%>
<jsp:forward page="/login/${currentProject}/login.jsp"></jsp:forward>

