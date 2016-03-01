<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<style>
.hidden{display: none;}
</style>
<div id="dialog-form" title="平台消息模版设置" class="container container_24"  style="overflow:auto;padding-top:25px;height: 470px;">
	
	<div  style="height: auto;padding-left: 298px;color: red;">
		注意：
		<br>
		<br>
		1、消息标题和内容中方括号(【】)及其里面的内容会在发送平台消息时替换为实际的内容请不要改动，否则会会造成消息标题或内容不正确。
		<br>
		<br>
		2、URL是指#后面的那一部分。
	</div>
	<hr width="90%">
	<s:if test='!"view".equals(mode)'>
	    <form id="maintainForm" method="post" action="" >
	</s:if>
	
	<div class="grid_8 lable-right">
		<label style="font-weight:bold">请选择模版：</label>
	</div>
	<select id="messageTemplate" class="basic-input">
		<option value="needDoissueTemplate">待办事项提醒消息模版</option>
		<option value="seriousIssueTemplate">重大事项提醒消息模版</option>
		<option value="issueTemplate">事件信息反馈消息模版</option>
		<option value="startDailyYearTemplate">台账状态提醒消息模版</option>
		<option value="statedReportsReportTemplate">定期类报表上报提醒消息模版</option>
		<option value="statedReportsRushToTemplate">定期类报表催报提醒消息模版</option>
		<option value="unStatedReportsReportTemplate">非定期类报表上报提醒消息模版</option>
		<option value="documentTemplate">文件签收提醒消息模版</option>
		<option value="sharingInformationTemplate">共享资料提醒消息模版</option>
		<option value="evaluateActiveStandardTemplate">考核目录状态提醒消息模版</option>
		<option value="evaluateReportTemplate">自评结果上报提醒消息模版</option>
		<option value="evaluateRushtoTemplate">自评结果催报提醒消息模版</option>
		<option value="evaluateBackTemplate">自评结果回退提醒消息模版</option>
		<option value="rectificativePersonTemplate">社区矫正人员到期提醒消息模版</option>
		<option value="positiveinfoTemplate">刑释解教人员到期提醒消息模版</option>
		<option value="idleyouthTemplate">重点青少年到期提醒消息模版</option>
		<option value="elderlyPeopleTemplate">实有人口标记为老年人提醒消息模版</option>
	</select>
	
	<div style="clear:both"></div>
	<div id="needDoissueTemplate" class="hidden">
		<div class="grid_8 lable-right">
			<label style="font-weight:bold">待办事项提醒消息模版设置：</label>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息标题：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@NEED_DO_ISSUE_TITLE"/>'
			 class="form-txt" maxlength="50" 
			 value='<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@NEED_DO_ISSUEPM_TITLE)"/>'/>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息内容：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8 heightAuto">
			<textarea rows="3"  name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@NEED_DO_ISSUEPM_CONTENT"/>' class="form-txt "><s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@NEED_DO_ISSUEPM_CONTENT)"/>
			</textarea>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >待办事项提醒消息URL：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@NEED_DO_MYISSUELIST_URL"/>'
			 class="form-txt" maxlength="50" 
			 value='<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@NEED_DO_MYISSUELIST_URL)"/>'/>
		</div>
	</div>
	
	<div style="clear:both"></div>
	<div id="seriousIssueTemplate" class="hidden">
		<div class="grid_8 lable-right">
			<label style="font-weight:bold">重大事项提醒消息模版设置：</label>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息标题：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@SERIOUS_ISSUE_TITLE"/>'
			 class="form-txt" maxlength="50" 
			 value='<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@SERIOUS_ISSUE_TITLE)"/>'/>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息内容：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8 heightAuto">
			<textarea rows="3"  name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@SERIOUS_ISSUE_CONTENT"/>' class="form-txt "><s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@SERIOUS_ISSUE_CONTENT)"/>
			</textarea>
		</div>
		
		<div style="clear:both"></div>
		<%-- 
		<div class="grid_8 lable-right">
			<label >重大事项提醒消息URL：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@SERIOUS_ISSUE_URL"/>'
			 class="form-txt" maxlength="50" 
			 value='<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@SERIOUS_ISSUE_URL)"/>'/>
		</div>
		--%>
	</div>
	
	<div style="clear:both"></div>
		<div id="issueTemplate" class="hidden">
		<div class="grid_8 lable-right">
			<label style="font-weight:bold">事件信息反馈消息模版设置：</label>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息标题：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@ISSUEPM_TITLE"/>'
			 class="form-txt" maxlength="50" 
			 value='<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@ISSUEPM_TITLE)"/>'/>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息内容：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8 heightAuto">
			<textarea rows="3"  name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@ISSUEPM_CONTENT"/>' class="form-txt "><s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@ISSUEPM_CONTENT)"/>
			</textarea>
		</div>
		
		<div style="clear:both"></div>
		<%-- 
		<div class="grid_8 lable-right">
			<label >事件信息反馈消息URL：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@MYISSUELIST_URL"/>'
			 class="form-txt" maxlength="50" 
			 value='<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@MYISSUELIST_URL)"/>'/>
		</div>
		--%>
	</div>
	
	<div style="clear:both"></div>
	
	<div id="startDailyYearTemplate" class="hidden">
		<div class="grid_8 lable-right">
			<label style="font-weight:bold">台账状态提醒消息模版设置：</label>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息标题：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@START_DAILY_YEAR_PM_TITLE"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@START_DAILY_YEAR_PM_TITLE)"/>"/>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息内容：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8 heightAuto">
			<textarea rows="3"  name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@START_DAILY_YEAR_PM_CONTENT"/>' class="form-txt "><s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@START_DAILY_YEAR_PM_CONTENT)"/>	
			</textarea>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >台账状态提醒消息URL：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@START_DAILY_YEAR_URL"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@START_DAILY_YEAR_URL)"/>"/>
		</div>
	</div>
	
	<div style="clear:both"></div>
	
	<div id="statedReportsReportTemplate" class="hidden">
		<div class="grid_8 lable-right">
			<label style="font-weight:bold">定期类报表上报提醒消息模版设置：</label>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息标题：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@STATED_REPORTS_REPORT_PM_TITLE"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@STATED_REPORTS_REPORT_PM_TITLE)"/>"/>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息内容：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8 heightAuto">
			<textarea rows="3"  name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@STATED_REPORTS_REPORT_PM_CONTENT"/>' class="form-txt "><s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@STATED_REPORTS_REPORT_PM_CONTENT)"/>	
			</textarea>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >定期类报表上报提醒消息URL：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@STATED_REPORTS_REPORT_URL"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@STATED_REPORTS_REPORT_URL)"/>"/>
		</div>
	</div>
	
	<div style="clear:both"></div>
	
	<div id="statedReportsRushToTemplate" class="hidden">
		<div class="grid_8 lable-right">
			<label style="font-weight:bold">定期类报表催报提醒消息模版设置：</label>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息标题：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@STATED_REPORTS_RUSHTO_PM_TITLE"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@STATED_REPORTS_RUSHTO_PM_TITLE)"/>"/>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息内容：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8 heightAuto">
			<textarea rows="3"  name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@STATED_REPORTS_RUSHTO_PM_CONTENT"/>' class="form-txt "><s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@STATED_REPORTS_RUSHTO_PM_CONTENT)"/>	
			</textarea>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >定期类报表催报提醒消息URL：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@STATED_REPORTS_RUSHTO_URL"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@STATED_REPORTS_RUSHTO_URL)"/>"/>
		</div>
	</div>
	
	
	<div style="clear:both"></div>
	
	<div id="unStatedReportsReportTemplate" class="hidden">
		<div class="grid_8 lable-right">
			<label style="font-weight:bold">非定期类报表上报提醒消息模版设置：</label>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息标题：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@UN_STATED_REPORTS_REPORT_PM_TITLE"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@UN_STATED_REPORTS_REPORT_PM_TITLE)"/>"/>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息内容：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8 heightAuto">
			<textarea rows="3"  name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@UN_STATED_REPORTS_REPORT_PM_CONTENT"/>' class="form-txt "><s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@UN_STATED_REPORTS_REPORT_PM_CONTENT)"/>	
			</textarea>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >非定期类报表上报提醒消息URL：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@UN_STATED_REPORTS_REPORT_URL"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@UN_STATED_REPORTS_REPORT_URL)"/>"/>
		</div>
	</div>
	
	
	<div style="clear:both"></div>
	
	<div id="documentTemplate" class="hidden">
		<div class="grid_8 lable-right">
			<label style="font-weight:bold">文件签收提醒消息模版设置：</label>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息标题：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@URGE_DOCUMENT_PM_TITLE"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@URGE_DOCUMENT_PM_TITLE)"/>"/>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息内容：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8 heightAuto">
			<textarea rows="3"  name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@URGE_DOCUMENT_PM_CONTENT"/>' class="form-txt "><s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@URGE_DOCUMENT_PM_CONTENT)"/>	
			</textarea>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >文件签收提醒消息URL：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@RECEIVE_DOCUMENT_URL"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@RECEIVE_DOCUMENT_URL)"/>"/>
		</div>
	</div>
	
	<div style="clear:both"></div>
	
	<div id="sharingInformationTemplate" class="hidden">
		<div class="grid_8 lable-right">
			<label style="font-weight:bold">共享资料提醒消息模版设置：</label>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息标题：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@SHARING_INFORMATION_PM_TITLE"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@SHARING_INFORMATION_PM_TITLE)"/>"/>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息内容：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8 heightAuto">
			<textarea rows="3"  name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@SHARING_INFORMATION_PM_CONTENT"/>' class="form-txt "><s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@SHARING_INFORMATION_PM_CONTENT)"/>	
			</textarea>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >共享资料提醒消息URL：</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@SHARING_INFORMATION_URL"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@SHARING_INFORMATION_URL)"/>"/>
		</div>		
	</div>
	
	<div style="clear:both"></div>
	
	<div id="evaluateActiveStandardTemplate" class="hidden">
		<div class="grid_8 lable-right">
			<label style="font-weight:bold">考核目录状态提醒消息模版设置：</label>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息标题：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@SEVALUATE_ACTIVESTANDARD_PM_TITLE"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@EVALUATE_ACTIVESTANDARD_PM_TITLE)"/>"/>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息内容：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8 heightAuto">
			<textarea rows="3"  name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@EVALUATE_ACTIVESTANDARD_PM_CONTENT"/>' class="form-txt "><s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@EVALUATE_ACTIVESTANDARD_PM_CONTENT)"/></textarea>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >考核目录状态提醒消息URL：</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@EVALUATE_ACTIVESTANDARD_URL"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@EVALUATE_ACTIVESTANDARD_URL)"/>"/>
		</div>		
	</div>
	
	<div style="clear:both"></div>
	
	<div id="evaluateReportTemplate" class="hidden">
		<div class="grid_8 lable-right">
			<label style="font-weight:bold">自评结果上报提醒消息模版设置：</label>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息标题：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@EVALUATE_REPORT_PM_TITLE"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@EVALUATE_REPORT_PM_TITLE)"/>"/>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息内容：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8 heightAuto">
			<textarea rows="3"  name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@EVALUATE_REPORT_PM_CONTENT"/>' class="form-txt "><s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@EVALUATE_REPORT_PM_CONTENT)"/></textarea>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >自评结果上报提醒消息URL：</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@EVALUATE_REPORT_URL"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@EVALUATE_REPORT_URL)"/>"/>
		</div>		
	</div>
	
	<div style="clear:both"></div>
	
	<div id="evaluateRushtoTemplate" class="hidden">
		<div class="grid_8 lable-right">
			<label style="font-weight:bold">自评结果催报提醒消息模版设置：</label>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息标题：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@EVALUATE_RUSHTO_PM_TITLE"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@EVALUATE_RUSHTO_PM_TITLE)"/>"/>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息内容：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8 heightAuto">
			<textarea rows="3"  name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@EVALUATE_RUSHTO_PM_CONTENT"/>' class="form-txt "><s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@EVALUATE_RUSHTO_PM_CONTENT)"/></textarea>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >自评结果催报提醒消息URL：</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@EVALUATE_RUSHTO_URL"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@EVALUATE_RUSHTO_URL)"/>"/>
		</div>		
	</div>
	
	<div style="clear:both"></div>
	
	<div id="evaluateBackTemplate" class="hidden">
		<div class="grid_8 lable-right">
			<label style="font-weight:bold">自评结果回退提醒消息模版设置：</label>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息标题：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@EVALUATE_BACK_PM_TITLE"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@EVALUATE_BACK_PM_TITLE)"/>"/>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息内容：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8 heightAuto">
			<textarea rows="3"  name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@EVALUATE_BACK_PM_CONTENT"/>' class="form-txt "><s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@EVALUATE_BACK_PM_CONTENT)"/></textarea>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >自评结果回退提醒消息URL：</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@EVALUATE_BACK_URL"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@EVALUATE_BACK_URL)"/>"/>
		</div>		
	</div>
	<div style="clear:both"></div>
	
	
	
	
	
	<div id="rectificativePersonTemplate" class="hidden">
		<div class="grid_8 lable-right">
			<label style="font-weight:bold">社区矫正人员到期提醒消息模版设置：</label>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息标题：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@RECTIFICATIVE_PERSON_PM_TITLE"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@RECTIFICATIVE_PERSON_PM_TITLE)"/>"/>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息内容：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8 heightAuto">
			<textarea rows="3"  name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@RECTIFICATIVE_PERSON_PM_CONTENT"/>' class="form-txt "><s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@RECTIFICATIVE_PERSON_PM_CONTENT)"/></textarea>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >社区矫正人员到期提醒消息模URL：</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@RECTIFICATIVE_PERSON_URL"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@RECTIFICATIVE_PERSON_URL)"/>"/>
		</div>		
	</div>
	<div style="clear:both"></div>
	
	<div id="positiveinfoTemplate" class="hidden">
		<div class="grid_8 lable-right">
			<label style="font-weight:bold">刑释解教人员到期提醒消息模版置：</label>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息标题：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@POSITIVEINFO_PM_TITLE"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@POSITIVEINFO_PM_TITLE)"/>"/>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息内容：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8 heightAuto">
			<textarea rows="3"  name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@POSITIVEINFO_PM_CONTENT"/>' class="form-txt "><s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@POSITIVEINFO_PM_CONTENT)"/></textarea>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >刑释解教人员到期提醒消息URL：</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@POSITIVEINFO_URL"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@POSITIVEINFO_URL)"/>"/>
		</div>		
	</div>
	<div style="clear:both"></div>
	
	<div id="idleyouthTemplate" class="hidden">
		<div class="grid_8 lable-right">
			<label style="font-weight:bold">重点青少年到期提醒消息模版设置：</label>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息标题：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@IDLEYOUTH_PM_TITLE"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@IDLEYOUTH_PM_TITLE)"/>"/>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息内容：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8 heightAuto">
			<textarea rows="3"  name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@IDLEYOUTH_PM_CONTENT"/>' class="form-txt "><s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@IDLEYOUTH_PM_CONTENT)"/></textarea>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >重点青少年到期提醒消息URL：</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@IDLEYOUTH_URL"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@IDLEYOUTH_URL)"/>"/>
		</div>		
	</div>
	<div style="clear:both"></div>
	
	<div id="elderlyPeopleTemplate" class="hidden">
		<div class="grid_8 lable-right">
			<label style="font-weight:bold">实有人口标记为老年人提醒消息模版设置：</label>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息标题：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@ELDERLY_PEOPLE_PM_TITLE"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@ELDERLY_PEOPLE_PM_TITLE)"/>"/>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >消息内容：&nbsp;&nbsp;</label>
		</div>
		<div class="grid_8 heightAuto">
			<textarea rows="3"  name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@ELDERLY_PEOPLE_PM_CONTENT"/>' class="form-txt "><s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@ELDERLY_PEOPLE_PM_CONTENT)"/></textarea>
		</div>
		
		<div style="clear:both"></div>
		
		<div class="grid_8 lable-right">
			<label >实有人口标记为老年人提醒消息URL：</label>
		</div>
		<div class="grid_8">
			<input type="text" 
			name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@ELDERLY_PEOPLE_URL"/>'
			 class="form-txt" maxlength="50" 
			 value="<s:property value="map.get(@com.tianque.core.globalSetting.util.GlobalSetting@ELDERLY_PEOPLE_URL)"/>"/>
		</div>		
	</div>
	
	<div style="clear:both"></div>
	
	<div class="grid_8"></div>

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

	$("#messageTemplate").change(function(){
			$(".hidden").hide();
			var id = $(this).val();
			$("#"+id).show();
		});
	
	$("#messageTemplate").change();
	
	<s:if test='!"view".equals(mode)'>
		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
				doAjaxSubmit();
			}
			});
	</s:if>

	$("#maintainForm").attr("action","${path}/sysadmin/globalSettingManage/updateGlobalSetting.action");
	$.loadingComp("close");
});

function doAjaxSubmit(){
	$("#maintainForm").ajaxSubmit({
        success: function(data){
			$.messageBox({message:"成功保存平台消息设置信息!"});
 	   },
 	   error: function(XMLHttpRequest, textStatus, errorThrown){
 	     $.messageBox({message:"提交错误",level: "error"	});				
 	   }
 	});
}

function doNothing(){}

</script>