<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="系统插件设置" class="container container_24" style="overflow:hidden;padding-top:25px;">
    <form id="maintainForm" method="post" action="" >
    	<div class="grid_8 lable-right">
			<label style="font-weight:bold">实有人口中各模块是否互斥：</label>
		</div>
		<div style="clear:both"></div>
    	<fieldset>
		    <div class="grid_8 lable-right">
				<label >约束1：实有人口中各模块是否互斥：&nbsp;&nbsp;</label>
			</div>
			<div class="grid_2">
				<input type="radio" onclick="return false;" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@ACTUAL_POPULATION_MUTEX"/>' value="true" <s:if test='"true".equals(map.get(@com.tianque.core.globalSetting.util.GlobalSetting@ACTUAL_POPULATION_MUTEX))'>checked</s:if>/>是&nbsp;&nbsp;
				<input type="radio" onclick="return false;" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@ACTUAL_POPULATION_MUTEX"/>' value="false" <s:if test='"false".equals(map.get(@com.tianque.core.globalSetting.util.GlobalSetting@ACTUAL_POPULATION_MUTEX)) || null == map.get(@com.tianque.core.globalSetting.util.GlobalSetting@ACTUAL_POPULATION_MUTEX)'>checked</s:if>/>否
			</div>
    	</fieldset>
		<div style="clear:both"></div>
	    <div class="grid_8 lable-right">
			<label style="font-weight:bold">从实有人口到业务数据的约束：</label>
		</div>
		<div style="clear:both"></div>
		<fieldset>
	    <div class="grid_8 lable-right">
			<label style="">约束2：是否可以维护业务数据（新增、修改）&nbsp;&nbsp;</label>
		</div>
		<div class="grid_2">
			<input type="radio" onclick="return false;" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@CAN_MAINTAIN_BUSINESS_POPULATION"/>' value="true" <s:if test='"true".equals(map.get(@com.tianque.core.globalSetting.util.GlobalSetting@CAN_MAINTAIN_BUSINESS_POPULATION))'>checked</s:if>/>是&nbsp;&nbsp;
			<input type="radio" onclick="return false;" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@CAN_MAINTAIN_BUSINESS_POPULATION"/>' value="false" <s:if test='"false".equals(map.get(@com.tianque.core.globalSetting.util.GlobalSetting@CAN_MAINTAIN_BUSINESS_POPULATION)) || null == map.get(@com.tianque.core.globalSetting.util.GlobalSetting@CAN_MAINTAIN_BUSINESS_POPULATION)'>checked</s:if>/>否
		</div>
		<div style="clear:both"></div>
	    <div class="grid_8 lable-right">
			<label style="">约束3：是否可以注销业务数据&nbsp;&nbsp;</label>
		</div>
		<div class="grid_2">
			<input type="radio" onclick="return false;" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@EMPHASIS_BUSINESS_POPULATION"/>' value="true" <s:if test='"true".equals(map.get(@com.tianque.core.globalSetting.util.GlobalSetting@EMPHASIS_BUSINESS_POPULATION))'>checked</s:if>/>是&nbsp;&nbsp;
			<input type="radio" onclick="return false;" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@EMPHASIS_BUSINESS_POPULATION"/>' value="false" <s:if test='"false".equals(map.get(@com.tianque.core.globalSetting.util.GlobalSetting@EMPHASIS_BUSINESS_POPULATION)) || null == map.get(@com.tianque.core.globalSetting.util.GlobalSetting@EMPHASIS_BUSINESS_POPULATION)'>checked</s:if>/>否
		</div>
		<div style="clear:both"></div>
		<div class="grid_8 lable-right">
			<label style="">约束4：实有人口中是否可以查看业务数据&nbsp;&nbsp;</label>
		</div>
		<div class="grid_2">
			<input type="radio" onclick="return false;" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@VIEW_BUSINESS_POPULATION"/>' value="true" <s:if test='"true".equals(map.get(@com.tianque.core.globalSetting.util.GlobalSetting@VIEW_BUSINESS_POPULATION))'>checked</s:if>/>是&nbsp;&nbsp;
			<input type="radio" onclick="return false;" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@VIEW_BUSINESS_POPULATION"/>' value="false" <s:if test='"false".equals(map.get(@com.tianque.core.globalSetting.util.GlobalSetting@VIEW_BUSINESS_POPULATION)) || null == map.get(@com.tianque.core.globalSetting.util.GlobalSetting@VIEW_BUSINESS_POPULATION)'>checked</s:if>/>否
		</div>
		<div style="clear:both"></div>
		<div class="grid_8 lable-right">
			<label style="">约束5：删除实有人口时是否删除业务数据&nbsp;&nbsp;</label>
		</div>
		<div class="grid_2">
			<input type="radio" onclick="return false;" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@DELETE_BUSINESS_POPULATION"/>' value="true" <s:if test='"true".equals(map.get(@com.tianque.core.globalSetting.util.GlobalSetting@DELETE_BUSINESS_POPULATION))'>checked</s:if>/>是&nbsp;&nbsp;
			<input type="radio" onclick="return false;" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@DELETE_BUSINESS_POPULATION"/>' value="false" <s:if test='"false".equals(map.get(@com.tianque.core.globalSetting.util.GlobalSetting@DELETE_BUSINESS_POPULATION)) || null == map.get(@com.tianque.core.globalSetting.util.GlobalSetting@DELETE_BUSINESS_POPULATION)'>checked</s:if>/>否
		</div>
		</fieldset>
		<div style="clear:both"></div>
		<div class="grid_8 lable-right">
			<label style="font-weight:bold">从业务数据到实有人口的约束：</label>
		</div>
		<div style="clear:both"></div>
		<fieldset>
		<div class="grid_8 lable-right">
			<label style="">约束6：业务数据新增、修改是否依赖实有人口&nbsp;&nbsp;</label>
		</div>
		<div class="grid_16">
			<input type="radio" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION"/>' value="<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_DEPENDENT"/>" <s:if test='@com.tianque.core.globalSetting.util.GlobalSetting@NOT_DEPENDENT.equals(map.get(@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION)) || null == map.get(@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION)'>checked</s:if>/>不依赖&nbsp;&nbsp;
			<input type="radio" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION"/>' value="<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION"/>" <s:if test='@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION.equals(map.get(@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION))'>checked</s:if>/>依赖，实口无人员基本信息时不允许增业务数据&nbsp;&nbsp;
			<input type="radio" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION"/>' value="<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@SYNC_ACTUAL_POPULATION"/>" <s:if test='@com.tianque.core.globalSetting.util.GlobalSetting@SYNC_ACTUAL_POPULATION.equals(map.get(@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION))'>checked</s:if>/>依赖，增加业务数据时人员基本信息同步到实口
		</div>
		<div style="clear:both"></div>
		<div class="grid_8 lable-right">
			<label style="">约束7：人口业务数据进行重新关注时是否依赖人口信息状态&nbsp;&nbsp;</label>
		</div>
		<div class="grid_2">
			<input type="radio" onclick="return false;" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@EMPHASIS_DEPENDENT_POPULATION_STATE"/>' value="true" <s:if test='"true".equals(map.get(@com.tianque.core.globalSetting.util.GlobalSetting@EMPHASIS_DEPENDENT_POPULATION_STATE))'>checked</s:if>/>是&nbsp;&nbsp;
			<input type="radio" onclick="return false;" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@EMPHASIS_DEPENDENT_POPULATION_STATE"/>' value="false" <s:if test='"false".equals(map.get(@com.tianque.core.globalSetting.util.GlobalSetting@EMPHASIS_DEPENDENT_POPULATION_STATE)) || null == map.get(@com.tianque.core.globalSetting.util.GlobalSetting@EMPHASIS_DEPENDENT_POPULATION_STATE)'>checked</s:if>/>否
		</div>
		<div style="clear:both"></div>
		<div class="grid_8 lable-right">
			<label style="">约束8：人口业务数据查看时是否包含户籍信息和流入信息&nbsp;&nbsp;</label>
		</div>
		<div class="grid_2">
			<input type="radio" onclick="return false;" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@POPULATION_BUSINESS_DATA"/>' value="true" <s:if test='"true".equals(map.get(@com.tianque.core.globalSetting.util.GlobalSetting@POPULATION_BUSINESS_DATA))'>checked</s:if>/>是&nbsp;&nbsp;
			<input type="radio" onclick="return false;" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@POPULATION_BUSINESS_DATA"/>' value="false" <s:if test='"false".equals(map.get(@com.tianque.core.globalSetting.util.GlobalSetting@POPULATION_BUSINESS_DATA)) || null == map.get(@com.tianque.core.globalSetting.util.GlobalSetting@POPULATION_BUSINESS_DATA)'>checked</s:if>/>否
		</div>
		</fieldset>
		<div style="clear:both"></div>
		<div class="grid_8">
			<button type="submit" style="height:30px;">保存</button>
			<button type="reset" style="height:30px;">重置</button>
		</div>
   	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("input[name='map.businessDependentActualPopulation']").change(function(){
		if($("input[name='map.businessDependentActualPopulation']:checked").val() == 'notDependent'){
			$("input[name='map.actualPopulationMutex']").val(["false"]);
			$("input[name='map.canMaintainBusinessPopulation']").val(["false"]);
			$("input[name='map.emphasisBusinessPopulation']").val(["false"]);
			$("input[name='map.viewBusinessPopulation']").val(["false"]);
			$("input[name='map.deleteBusinessPopulation']").val(["false"]);
			$("input[name='map.emphasisDependentPopulationState']").val(["false"]);
			$("input[name='map.populationBusinessData']").val(["false"]);
		}else{
			$("input[name='map.actualPopulationMutex']").val(["true"]);
			$("input[name='map.canMaintainBusinessPopulation']").val(["true"]);
			$("input[name='map.emphasisBusinessPopulation']").val(["true"]);
			$("input[name='map.viewBusinessPopulation']").val(["true"]);
			$("input[name='map.deleteBusinessPopulation']").val(["true"]);
			$("input[name='map.emphasisDependentPopulationState']").val(["true"]);
			$("input[name='map.populationBusinessData']").val(["true"]);
		}
	});
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
			 $.messageBox({message:"成功保存插件设置信息!"});
 	   },
 	   error: function(XMLHttpRequest, textStatus, errorThrown){
 	     	$.messageBox({message:"提交错误",level: "error"	});				
 	   }
 	});
}


</script>