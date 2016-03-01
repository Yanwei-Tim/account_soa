<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="页眉页脚设置" class="container container_24" style="overflow:hidden;padding-top:25px;">
	<s:if test='!"view".equals(mode)'>
	    <form id="maintainForm" method="post" action="" >
	</s:if>
			
    <div class="grid_4 lable-right">
		<label >系统title：</label>
	</div>
	<div class="grid_8">
		<input type="text" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@SYS_TITLE"/>'
		 class="form-txt" maxlength="50" id="sysTitle" 
		 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@SYS_TITLE)"/>"/>
	</div>
	<div class="grid_12">
		用于显示登入系统后的title
	</div>
	
	<div style="clear:both"></div>
	<div class="grid_4 lable-right">
		<label>系统页眉：</label>
	</div>
	<div class="grid_8">
		<input id="sysHeader" type="text" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@SYS_HEADER_PAGE"/>'
		 class="form-txt" maxlength="50" id="sysHeaderPage" 
		 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@SYS_HEADER_PAGE)"/>" />
	</div>
	<div class="grid_12">
		用于显示系统页眉内容
	</div>
	
	<div style="clear:both"></div>
	<div class="grid_4 lable-right">
		<label>系统页脚：</label>
	</div>
	<div class="grid_8">
		<input id="sysBottom" type="text" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@SYS_BOTTOM_PAGE"/>'
		 class="form-txt" maxlength="100" id="sysBottomPage" 
		 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@SYS_BOTTOM_PAGE)"/>"/>
	</div>
	<div class="grid_12">
		用于显示系统页脚内容
	</div>
	
	<div style="clear:both"></div>
	<div class="grid_4 lable-right">
		<label>首页页脚：</label>
	</div>
	<div class="grid_8">
		<input type="text" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@FRONTPAGE_BOTTOM_PAGE"/>'
		 class="form-txt" maxlength="100" id="frontpageBottomPage" 
		 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@FRONTPAGE_BOTTOM_PAGE)"/>"/>
	</div>
	<div class="grid_12">
		用于显示首页页脚内容
	</div>
	<div class="grid_6">
	</div>
	
	<div class="grid_8">
		<button type="submit" style="height:30px;">保存</button>
		<button type="reset" style="height:30px;">重置</button>
	</div>
   <s:if test='!"view".equals(mode)'>
	   </form>
	</s:if>
</div>
<script type="text/javascript">
var existed = true;
$(document).ready(function(){
	<s:if test='!"view".equals(mode)'>
		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
				doAjaxSubmit();
			},
			rules:{
				'map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@SYS_TITLE"/>':{
					required:true,
					exculdeParticalChar:true,
					minlength:2,
					maxlength:50
				},
				'map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@SYS_HEADER_PAGE"/>':{
					required:true,
					exculdeParticalChar:true,
					minlength:2,
					maxlength:50
				}
			},
			messages:{
				'map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@SYS_TITLE"/>':{
					required:"请输入系统title",
					exculdeParticalChar:"系统title不能有特殊字符",
					minlength:$.format("系统title最少需要输入{0}个字符"),
					maxlength:$.format("系统title最多只能输入{0}个字符")
				},
				'map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@SYS_HEADER_PAGE"/>':{
					required:"请输入系统页眉",
					exculdeParticalChar:"系统页眉不能有特殊字符",
					minlength:$.format("系统页眉最少需要输入{0}个字符"),
					maxlength:$.format("系统页眉最多只能输入{0}个字符")
                }
			}
			});
	</s:if>

	$("#maintainForm").attr("action","${path}/sysadmin/globalSettingManage/updateGlobalSetting.action");
	$.loadingComp("close");
});

function doAjaxSubmit(){
	$("#maintainForm").ajaxSubmit({
        success: function(data){
			 $.messageBox({message:"成功保存页眉页脚设置信息!"});
			 $("#navigation-title").text($("#sysHeader").val());
			 $("#bottom").text($("#sysBottom").val());
 	   },
 	   error: function(XMLHttpRequest, textStatus, errorThrown){
 	     $.messageBox({message:"提交错误",level: "error"	});				
 	   }
 	});
}

function doNothing(){}

</script>