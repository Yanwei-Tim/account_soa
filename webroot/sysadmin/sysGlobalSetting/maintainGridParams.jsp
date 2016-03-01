<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="系统插件设置" class="container container_24" style="overflow:hidden;padding-top:25px;">
    <form id="maintainForm" method="post" action="" >
	    <div class="grid_4 lable-right">
			<label >gis地理信息系统url：</label>
		</div>
		<div class="grid_10">
			<input id="gisUrl" class="form-txt" type="text" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@GIS_URL"/>' 
			value='<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@GIS_URL)"/>'  />
		</div>
		<div style="clear: both;"></div>
		 <div class="grid_4 lable-right">
			<label >统一搜索系统url：</label>
		</div>
		<div class="grid_10">
			<input id="unifiedSearchUrl" class="form-txt" type="text" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@UNIFIEDSEARCH_URL"/>' 
			value='<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@UNIFIEDSEARCH_URL)"/>'  />
		</div>
		<div style="clear: both;"></div>
		<div class="grid_6"></div>
		<div class="grid_14">
			<button type="submit" style="height:30px;">保存</button>
			<button type="reset" style="height:30px;">重置</button>
		</div>
   	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	<s:if test='!"view".equals(mode)'>
		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
				doAjaxSubmit();
			},
			rules:{
			},
			messages:{
			}
		});
	</s:if>

	$("#maintainForm").attr("action","${path}/sysadmin/globalSettingManage/updateGlobalSetting.action");

	$.loadingComp("close");
});

function doAjaxSubmit(){
	$("#maintainForm").ajaxSubmit({
        success: function(data){
			 $.messageBox({message:"成功保存设置信息!"});
 	   },
 	   error: function(XMLHttpRequest, textStatus, errorThrown){
 	     	$.messageBox({message:"提交错误",level: "error"	});				
 	   }
 	});
}

function doNothing(){}

</script>