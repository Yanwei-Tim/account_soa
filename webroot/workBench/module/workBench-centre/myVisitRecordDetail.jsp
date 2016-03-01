<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:iterator value="myVisits" var="p">
	<div class="portlet_object_data">
	<span class="displayData">${p.type}:<a href="javascript:;">${p.amount}</a></span><span>未帮扶:<a href="javascript:;">${p.recordCount}</a></span>
	</div>
</s:iterator>