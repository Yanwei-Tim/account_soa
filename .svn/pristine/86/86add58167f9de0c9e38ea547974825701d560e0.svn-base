<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="级别设置" class="container container_24" style="overflow:hidden;padding-top:25px;">
	<s:if test='!"view".equals(mode)'>
	    <form id="maintainForm" method="post" action="" >
	</s:if>
			
    <div class="grid_6 lable-right">
		<label >人员信息块状管理设置：</label>
	</div>
	<div class="grid_3">
		<input id="personInfoChangeLevel" type="checkbox" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@IS_RELATED"/>' value="true" <s:if test='"true".equals(map.get(@com.tianque.core.globalSetting.util.GlobalSetting@IS_RELATED))'>checked</s:if>/>&nbsp;&nbsp;&nbsp;&nbsp;人员块状管理
	</div>
	<div class="grid_8">
		<button type="submit" style="height:30px;">保存</button>
		<button type="reset" style="height:30px;">重置</button>
	</div>
   <s:if test='!"view".equals(mode)'>
	   </form>
	</s:if>
	<div class="clear" style="height:25px"></div>
	<hr width="90%">
	<div class="grid_6 lable-right"><label><font color="#000000"  size="3px">依次关联：</font></label></div>
	<div class="grid_18" style="height: auto;">
	分别按常住人口、流动人口、重点人员依次为准进行关联。
	<br>
	（注：重点人员中的子类，任一子类的信息变化均影响其他子类）
	<br>
	1） 常住人口信息有变动时，对应的流口、重点人员信息都发生变化。
	<br>
	2） 流动人口信息有变动时，对应的重点人员信息发生变化，但常口信息不发生变化。
	<br>
	3） 重点人员信息有变动时，对应的常口信息和流口信息均不发生变化。
	<br>
	</div>
	<div class="grid_6 lable-right form-req" style="height: auto;"><label><font color="#000000"  size="3px">完全关联：</font></label></div>
	<div class="grid_18" style="height: auto;">
	任一类人员信息的变化，其余两类人员信息同时发生变化。
	<br>
	</div>
	<div class="grid_6 lable-right"><label><font color="#000000"  size="3px">无关联：</font></label></div>
	<div class="grid_18" style="height: auto;">
	各类人员相互独立，互不影响。
	</div>
	
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
			},
			messages:{
			}
		});
	</s:if>
	$("#maintainForm").attr("action","${path}/sysadmin/globalSettingManage/updateGlobalSetting.action");
});

function doAjaxSubmit(){
	$("#maintainForm").ajaxSubmit({
        success: function(data){
			 $.messageBox({message:"设置成功!"});
 	   },
 	   error: function(XMLHttpRequest, textStatus, errorThrown){
 	     	$.messageBox({message:"提交错误",level: "error"	});				
 	   }
 	});
}

</script>