<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<jsp:include page="/includes/baseInclude.jsp" />
<div class="container container_24">
    <div class="grid_5 lable-right">
       <label class="form-lbl lable-color">显示名称：</label>
    </div>
    <div class="grid_7 view-color">
    	${propertyDict.displayName}
    </div>
	<s:if test="gridInternalProperty!=null">
	<div class="grid_5 lable-right">
       	<label class="form-lbl lable-color"> 内置属性：</label>
	</div>
    <div class="grid_7 view-color">
       	<s:iterator value="gridInternalProperty" id="id">
			<s:if test="propertyDict.internalId==#id.identify">
	            ${displayName}
	      	</s:if>
       	</s:iterator>
	</div>
	</s:if>
</div>
