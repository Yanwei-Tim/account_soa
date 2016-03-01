<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="dialog-form" title="数据导出" class="container container_24">
<form action="" method="post" id="exceldownload">
	<input id="baseDownloadUrl" type="hidden" value=<s:property value='#parameters.downloadUrl'/> />
	<div id="download-div" class="container container_24">
		<div class="grid_20 lable-left"  >
			<label class="form-lbl">请选择要导出的数据:</label>
		</div>
   		<div class='clearLine'>&nbsp;</div>
		<div class="grid_2 lable-left"  >&nbsp;
		</div>
		<div class="grid_20 lable-left"  >
			<input type="radio" name="pageOnly" value="false" checked="checked"/>导出全部数据
		</div>
   		<div class='clearLine'>&nbsp;</div>
		<div class="grid_2 lable-left"  >&nbsp;
		</div>
		<div class="grid_20 lable-left"  >
			<input type="radio" name="pageOnly" value="true"/>导出本页数据
		</div>
	</div>
</form>
</div>
<script>

$(document).ready(function(){
	var actionUrl=$("#baseDownloadUrl").val();
	var params= "";
	if("" != "<s:property value='#parameters.gridName'/>"){
		params=$("#<s:property value='#parameters.gridName'/>").getPostData();
		actionUrl=actionUrl+"?"+$.param(params);
	}	
	$("#exceldownload").attr("action",actionUrl);
	$("form:first").submit(function(){
		if ($("#exceldownload").attr("action")=="") return false;
		return true;
	});
});
</script>