<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="系统插件设置" class="container container_24" style="overflow:hidden;padding-top:25px;">
    <form id="maintainForm" method="post" action="" >
    <div style="display: none">
	    <div class="grid_4 lable-right">
			<label >启用搜索引擎：</label>
		</div>
		<div class="grid_2">
			<input id="isSenderMsg" type="radio" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@IS_SENDER_SOLR_MSG"/>' value="true" <s:if test='"true".equals(map.get(@com.tianque.core.globalSetting.util.GlobalSetting@IS_SENDER_SOLR_MSG))'>checked</s:if>/>是
		</div>
		<div class="grid_2">
			<input type="radio" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@IS_SENDER_SOLR_MSG"/>' value="false" <s:if test='"false".equals(map.get(@com.tianque.core.globalSetting.util.GlobalSetting@IS_SENDER_SOLR_MSG))'>checked</s:if>/>否
		</div>
		<div class="grid_12">
			<button id="solrInit">初始化</button>
		</div>
		
		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<label>Solr的URL：</label>
		</div>
		<div class="grid_8">
			<input type="text" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@SOLR_URL"/>'
			 class="form-txt" maxlength="50" id="sysHeaderPage" value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@SOLR_URL)"/>" />
		</div>
		<div class="grid_12">
			用于显示Solr的URL
		</div>
		</div>
		
		 <div class="grid_4 lable-right">
			<label style="font-weight:bold">启用MQ消息中间件：</label>
		</div>
		<div class="grid_2">
			<input id="isSenderMsg" type="radio" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@IS_SENDER_JMS_MSG"/>' value="true" <s:if test='"true".equals(map.get(@com.tianque.core.globalSetting.util.GlobalSetting@IS_SENDER_JMS_MSG))'>checked</s:if>/>是
		</div>
		<div class="grid_2">
			<input type="radio" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@IS_SENDER_JMS_MSG"/>' value="false" <s:if test='"false".equals(map.get(@com.tianque.core.globalSetting.util.GlobalSetting@IS_SENDER_JMS_MSG))'>checked</s:if>/>否
		</div>
		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<label style="font-weight:bold">JMS中间件的URL：</label>
		</div>
		<div class="grid_8">
			<input type="text" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@JMS_URL"/>'
			 class="form-txt" maxlength="50" id="sysHeaderPage" value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@JMS_URL)"/>" />
		</div>
		<div class="grid_12">
			用于显示JMS中间件的URL
		</div>
		
		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<label style="font-weight:bold">消息队列JDNI名称：</label>
		</div>
		<div class="grid_8">
			<input type="text" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@JMS_QUEUE_JNDI_NAME"/>'
			 class="form-txt" maxlength="100" id="frontpageBottomPage" value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@JMS_QUEUE_JNDI_NAME)"/>"/>
		</div>
		<div class="grid_12">
			用于显示消息队列JDNI名称
		</div>
		<div class="grid_4 lable-right">
			<label style="font-weight:bold">远程会议密钥：</label>
		</div>
		<div class="grid_8">
			<input type="text" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@VIDEO_CONFERENCE_PKI"/>'
			 class="form-txt" maxlength="100" id="video_PKI" value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@VIDEO_CONFERENCE_PKI)"/>"/>
		</div>
		<div class="grid_12">
			用于获取远程会议公钥&nbsp;&nbsp;<a href="javascript:void(0);" onclick="getMd5()">点此获取</a>
		</div>
		<div class="grid_4 lable-right">
			<label style="font-weight:bold">远程会议公钥：</label>
		</div>
		<div class="grid_8">
			<input type="hidden" id="exp" />
			<input type="text" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@VIDEO_CONFERENCE_KEY"/>'
			 class="form-txt" maxlength="100" id="video_KEY" value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@VIDEO_CONFERENCE_KEY)"/>"/>
		</div>
		<div class="grid_12">
			用于远程会议验证&nbsp;&nbsp;<a href="javascript:void(0);" onclick="getCreateCode()">点此获取</a>
		</div>
		<div style="clear:both"></div>
		<div class="grid_6"></div>
		<div class="grid_8">
			<button type="submit" style="height:30px;">保存</button>
			<button type="reset" style="height:30px;">重置</button>
		</div>
   	</form>
</div>
<script type="text/javascript">
var existed = true;
function showSolrInit(isShow){
	if(isShow){
		$("#solrInit").show();
	}else{
		$("#solrInit").hide();
	}
}
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

	$("#solrInit").click(function(){
		$.ajax({
			url:"${path}/solrManage/initSolr.action",
			success:function(data){
				$.messageBox({message:"成功初始化搜索引擎库!"});
			}
		});
	});
	showSolrInit(<s:property value='map.get(@com.tianque.core.globalSetting.util.GlobalSetting@IS_SENDER_SOLR_MSG))'/>);
	$.loadingComp("close");
});

function doAjaxSubmit(){
	$("#maintainForm").ajaxSubmit({
        success: function(data){
			 $.messageBox({message:"成功保存插件设置信息!"});
			 showSolrInit($("#isSenderMsg").attr("checked"));
 	   },
 	   error: function(XMLHttpRequest, textStatus, errorThrown){
 	     	$.messageBox({message:"提交错误",level: "error"	});				
 	   }
 	});
}

function getMd5() {
	$.getJSON('http:\//115.236.101.203:8898/?r=/api/getMD5&callback=?', 
			{str:$('#video_PKI').val()}, 
			function(json) {
				$('#video_PKI').val(json.md5);
			}
	);
}

function getCreateCode() {
	$.getJSON('http:\//115.236.101.203:8898/?r=/api/createCode&callback=?', 
			{
				host:window.location.hostname, 
				exp:$('#exp').val(), 
				key:encodeURIComponent($('#video_PKI').val())
			}, 
			function(json) {
				$('#video_KEY').val(json.code);
			}
	);
}

function doNothing(){}

</script>