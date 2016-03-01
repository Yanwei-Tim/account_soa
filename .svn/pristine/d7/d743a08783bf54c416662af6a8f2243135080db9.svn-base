<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="container container_24">
<form id="searchPublicNotice" action="">
	<input id="mode" type="hidden" name="mode" value="${mode }" /> <input
		id="publicNoticeOrgId" type="hidden"
		name="publicNoticeVo.organization.id"
		value="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().id"/>" />
	<div class="grid_4 lable-right">
		<label class="form-lb1">标题：</label>
	</div>
	<div class="grid_20">
		<input  type="text" id="editorTitle"
			name="publicNoticeVo.editorTitle" class="form-txt" />
	</div>
    
	<div class="grid_4 lable-right">
		<label class="form-lbl">编辑者： </label>
	</div>
	<div class="grid_7">
		<input type="text" id="noticeEditor" 
			name="publicNoticeVo.noticeEditor" class="form-txt" />
	</div>

	<div class="grid_4 lable-right" >
		<label class="form-lbl">编辑时间：</label>
    </div>
	<div class="grid_4">
		<input type="text"  name="publicNoticeVo.editstartDate"
			id="editstartDate" class="form-txt" readonly="readonly" style="width:86%"/>
	</div>
	<div class="grid_1 " style="text-align: center;">
		<label class="form-lbl">到</label>
	</div>
	<div class="grid_4">
		<input type="text" name="publicNoticeVo.editEndDate" id="editEndDate"
			class="form-txt" readonly="readonly" style="width:86%"/>
	</div>
	
	<div class="grid_4 lable-right" >
		<label class="form-lbl">有效期：</label>
	</div>
	<div class="grid_7">
		<select style="width: 120px;" id="isInValidity"
			name="publicNoticeVo.isInValidity">
			<option value="-1">全部</option>
			<option value="0">在有效期内</option>
			<option value="1">已过有效期</option>
		</select>
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">是否有有效期：</label>
	</div>
	<div class="grid_7">
		<select  id="isHaveValidity" 
			name="publicNoticeVo.isHaveValidity">
			<option value="-1">全部</option>
			<option value="1">有</option>
			<option value="0">无</option>
		</select>
	</div>

	<div id="showOverdueDate" style="display: none;">
		<div class="grid_5 lable-right">
			<label class="form-lbl">有效期截至时间：</label>
		</div>
		<div class="grid_6">
			<input type="text"  readonly="readonly"
				name="publicNoticeVo.validoverdueStartDate"
				id="validoverdueStartDate" class="form-txt" />
		</div>
		<div class="grid_1" style="text-align: center;">
			<label class="form-lbl" >到</label>
		</div>
		<div class="grid_6">
			<input type="text" name="publicNoticeVo.validoverdueEndDate"
				id="validoverdueEndDate" class="form-txt" readonly="readonly" />
		</div>
	</div>


</form>
</div>
<script type="text/javascript">
	$('#editstartDate').datePicker({
		yearRange : '1900:2030',
		dateFormat : 'yy-mm-dd',
		maxDate : '+0d',
		onSelect : function(dateText, inst) {
			if (dateText != null && dateText != '') {
				var dateUnit = dateText.split('-');
				var date = new Date(dateUnit[0], dateUnit[1] - 1, dateUnit[2]);
				$("#editEndDate").datepicker("option", "minDate", date);
			}
		}
	});

	$('#editEndDate').datePicker({
		yearRange : '1900:2030',
		dateFormat : 'yy-mm-dd',
		maxDate : '+0d',
		onSelect : function(dateText, inst) {
			if (dateText != null && dateText != '') {
				var dateUnit = dateText.split('-');
				var date = new Date(dateUnit[0], dateUnit[1] - 1, dateUnit[2]);
				$("#editstartDate").datepicker("option", "maxDate", date);
			}
		}
	});

	$('#validoverdueStartDate').datePicker(
			{
				yearRange : '1900:2030',
				dateFormat : 'yy-mm-dd',
				onSelect : function(dateText, inst) {
					if (dateText != null && dateText != '') {
						var dateUnit = dateText.split('-');
						var date = new Date(dateUnit[0], dateUnit[1] - 1,
								dateUnit[2]);
						$("#validoverdueEndDate").datepicker("option",
								"minDate", date);
					}
				}
			});

	$('#validoverdueEndDate').datePicker(
			{
				yearRange : '1900:2030',
				dateFormat : 'yy-mm-dd',
				onSelect : function(dateText, inst) {
					if (dateText != null && dateText != '') {
						var dateUnit = dateText.split('-');
						var date = new Date(dateUnit[0], dateUnit[1] - 1,
								dateUnit[2]);
						$("#validoverdueStartDate").datepicker("option",
								"maxDate", date);
					}
				}
			});

	$("#isHaveValidity").change(function() {
		if ($("#isHaveValidity").val() != "1") {
			$("#showOverdueDate").hide();

		} else {
			$("#showOverdueDate").show();
		}

	});
</script>










