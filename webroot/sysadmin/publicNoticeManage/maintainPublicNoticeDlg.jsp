<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="container container_24">
<form id="publicNoticeForm" method="post" action="/sysadmin/publicNoticeManage/maintainPublicNotice.action">
	<input id="mode" type="hidden" name="mode" value="${mode }" /> 
    <input name="isSubmit" id="isSubmit" type="hidden" value="">
	<input id="publicNoticeOrgId" type="hidden"	name="publicNotice.organization.id"		value="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().id"/>" />
	<input type="hidden" value="<s:property value="@com.tianque.core.util.ThreadVariable@getUser().id"/>"
		name="publicNotice.userId" id="userId"> 
	<input type="hidden" name="publicNotice.id" value="${publicNotice.id }" /> 
    <input type="hidden" name="publicNotice.isAttachment" id="isAttachment" value="">		
	<div class="grid_4 lable-right"><label class="form-lb1">所属网格：</label></div>
	<div class="grid_19">
	<input  type="text" id="commonPopulationOrgName"
			name="publicNotice.organization.orgName" readonly
			value="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().orgName"/>"
			class="form-txt" />
	</div>

	
	
	<div class="grid_4 lable-right" ><em class="form-req">*&nbsp;</em><label class="form-lb1">标题：</label></div>
	<div class="grid_19">
		<input  type="text" id="publicNoticeTitle" maxlength="100"
			name="publicNotice.publicNoticeTitle" style="width: 97%"
			value="${publicNotice.publicNoticeTitle}" title="请输入标题"
			class='form-txt {required:true,maxlength:100,messages:{required:"请输入标题" , maxlength:$.format("标题最多输入{0}个字符")}}' />
	</div>
    <div class="grid_1"></div>

	<div class="grid_4 lable-right">
		<label class="form-lbl">编辑者： </label>
	</div>
	<div class="grid_7">
		<input type="text"
			value="<s:property value='@com.tianque.core.util.ThreadVariable@getUser().name' />"
			readonly="readonly" id="noticeEditor"
			name="publicNotice.noticeEditor" class="form-txt"  />
	</div>

	<div class="grid_5 lable-right" >
		<label class="form-lbl">编辑时间：</label>
	</div>
	<div class="grid_7 ">
		<input type="text" name="publicNotice.editorDate" id="editorDate"
			style="width: 166px;" readonly="readonly"  class="form-txt" />
	</div>

	<div class="grid_4 lable-right" ><em class="form-req">*&nbsp;</em><label class="form-lb1">内容：</label></div>
	<div class="grid_19 heightAuto">
	<textarea id="publicNoticeMatter" rows="8" cols="77"
		name="publicNotice.publicNoticeMatter" 
		title="请输入内容"
		class="form-txt">${publicNotice.publicNoticeMatter}</textarea>
   </div>
   <div class="grid_1"></div>
	<select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"></select>
	<div id="showOverdue" style="display: none;">
		<div class="grid_4 lable-right"><label class="form-lb1">截止日期：</label></div>
		<div class="grid_8"><input type="text" readonly="readonly" id="overdueinput" class="form-txt"
				value="${overdueInput }" /></div>
		
		<div class="grid_12">
			<input type="button" class="defaultButton" id="resetOverdueDate" value="重设" />
		</div>
	</div>
		
	<div id="setOverdueDate">
		<div class="grid_4 lable-right"  >
			<label class="form-lb1">有效期：</label> 
		</div>
		<div class="grid_18" >
			<input type="radio"	value="不限" id="validityDate" name="validityDate" checked="checked"  />不限&nbsp;&nbsp;
			<input type="radio" value="3" id="validityDate" name="validityDate"  style="padding-right: 8px;"/>3天&nbsp;
			<input type="radio" value="7" id="validityDate" name="validityDate" />7天&nbsp;
			<input type="radio" value="15" id="validityDate" name="validityDate" />15天&nbsp;
			<input type="radio" value="30" id="validityDate" name="validityDate" />30天&nbsp;
			<input type="radio" id="buttonRadio" name="validityDate" value="-1" >其他
			<input type="text" id="overdueDate" name="publicNotice.overdueDate" title="点击设定时间"
				value="<s:date name='publicNotice.overdueDate' format='yyyy-MM-dd'/>"
				readonly="readonly" class="form-txt" style="width:80px;margin:0;" />
		</div>
	</div>	
</form>
<div class="clear"></div>
<div class="grid_4 lable-right"></div>
<div class="grid_20">
	<div id="custom-queue" style="width: 475px;height:80px;overflow:auto;overflow-x: hidden;border:1px solid #7F9DB9;"></div>
	<input id="custom_file_upload"  name="uploadFile" type="file" style="padding-left:20px;width:70px;" />
</div>



</div>
<script type="text/javascript">
$(document).ready(function(){
	
	function isAttachFileValue(){
	if($("#attachFileNames").val()){
		$("#isAttachment").val("true");
	}else{
		$("#isAttachment").val("false");
	}
	}
	
	if ($("#mode").val() == 'edit') {
	$("#setOverdueDate").hide();
	}

    $("#resetOverdueDate").click(function() {
	$("#setOverdueDate").show();
	});


	function getNewOverdueDate(selectValue) {
		$.ajax({
			 url : '${path}/sysadmin/publicNoticeManage/updateNewOverdue.action',
			 type : 'post',
			 dataType : 'json',
			 data : {
					  "validityDate" : selectValue,
					  "publicNotice.editorDate" : $("#editorDate").val()
					 },
			 success : function(data) {
					   $("#overdueDate").val(data);
						}
			});
      }

 	$(":radio").each(function() {
		$("#overdueDate").hide();
		$(this).click(function() {
			if ($(this).attr("id") == 'buttonRadio'	&& $(this).attr("checked")) {
				$("#overdueDate").val("");
				$("#overdueDate").show();
			 }else {
					$("#overdueDate").hide();
					getNewOverdueDate($(this).val());
					}
			});
     });

	if ($("#mode").val() == 'edit') {
		$("#showOverdue").show();
		}

	$("#editorDate").val(new Date());

	$('#overdueDate').datePicker({
		yearRange : '1900:2030',
		dateFormat : 'yy-mm-dd',
		minDate : '+0d'
	});

	$("#publicNoticeTitle").focus();
	$('#publicNoticeMatter').richImg();
	$("#publicNoticeForm").formValidate({
		showErrors : showErrorsForTab,
		promptPosition : "bottomLeft",
		submitHandler : function(form) {
			isAttachFileValue();
			$(form).ajaxSubmit({
				success : function(data) {
					if (!data.id) {
						$.messageBox({
							message : data,
							level : "error"
						});
					return;
					}
					if ("add" == $("#mode").val()) {
						$("#publicNoticeList").addRowData(data.id,data,"first");
						$("#publicNoticeList").setSelection(data.id);
						 
						if ($("#isSubmit").val() == "true"){
							$("#publicNoticeDialog").dialog("close");
						} else {
								emptyObject();
								}
						$.messageBox({message : "通知通报添加成功"
						});

						$("#peopleLogList").trigger("reloadGrid");
					}
					if ("edit" == $("#mode").val()) {
						$("#publicNoticeList").setRowData(data.id,data);
						$.messageBox({message : "通知通报修改成功！"
						});
					$("#publicNoticeList").setSelection(data.id);
					$("#publicNoticeDialog").dialog("close");
					}
					$("#refresh").click();

				},
				error : function(
						XMLHttpRequest,
						textStatus,
						errorThrown){
						alert("提交错误");
						}
			});
		},
		rules : {

				},
		messages : {}
	});
	
	function emptyObject() {
		$("#publicNoticeForm")[0].reset();
		$("#attachFileNames").html("");
		$("#custom-queue").html("");
		$("#editorDate").val(new Date());
		$("#overdueDate").hide();
	}
						
//附件
	$('#custom_file_upload').uploadFile({
		queueID:"custom-queue",
		queueSizeLimit:10,
		selectInputId:"attachFileNames"
	});

	fillFile();
						
});

function fillFile(){
<s:if test="publicNotice.noticeFiles!=null && publicNotice.noticeFiles.size>0">
	<s:iterator value="publicNotice.noticeFiles">
        $("#custom-queue").addUploadFileValue({
	          id:"<s:property value='id'/>",
	          filename:"<s:property value='fileName'/>",
	          link:"${path}/sysadmin/publicNoticeManage/downloadPublicNoticeAttachFile.action?publicNoticeAttachFile.id=<s:property value='id'/>",
	          onRemove:function(id){deletePublicNoticeAttachFile(id)}
		});
        $("<option>").attr("id","<s:property value='id'/>").val("<s:property value='fileName' escape='false'/>").attr("selected",true).appendTo($("#attachFileNames"));
        </s:iterator>
	</s:if>
}

function removeAttach(fileName){
	$("#attachFileNames").find("option[value="+fileName+"]").remove();
}

function removeAttachById(id){
	$("#attachFileNames").find("option[id="+id+"]").remove();
}

function deletePublicNoticeAttachFile(id){
	$.ajax({
		url:"${path}/sysadmin/publicNoticeManage/deletePublicNoticeAttachFile.action?publicNoticeAttachFile.id="+id,
		type:'GET',
		dataType:'json',
		success : function(_data){
			if(_data==true){
				removeAttachById(id);
			}
		},
		error : function(){
			$.messageBox({
				message : "加载失败，请刷新页面！",
				level : "error"
			});
		}
	});
}
	
</script>