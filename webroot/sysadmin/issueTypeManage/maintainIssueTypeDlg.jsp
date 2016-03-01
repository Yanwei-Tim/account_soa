<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>

<script type="text/javascript">
var currentOrgId;

$(function(){
	
    <s:if test="user.admin && issueType.issueTypeDomain.personalized">
    <s:if test='"add".equals(mode)'>
	$("#orgName").treeSelect({
		inputName:"orgId",
		onSelect: getCurrentOrgIdInIssueTypePage
	});
	</s:if>
	</s:if>
    <s:if test='!"view".equals(mode)'>
	    <s:if test='"add".equals(mode)'>
		    $("#maintainForm").attr("action","${path}/sysadmin/issueTypeManage/addIssueType.action");
		</s:if>
		<s:if test='"edit".equals(mode)'>
	        $("#maintainForm").attr("action","${path}/sysadmin/issueTypeManage/updateIssueType.action");
	    </s:if>
        $("#maintainForm").formValidate({
        	promptPosition:"bottomLeft",
            submitHandler:function(form){
                $(form).ajaxSubmit({
                    success:function(data){
                        if(!data.id){
                            $.messageBox({
                                message:data,
                                level:"error"
                            });
                            return;
                        }
                        <s:if test='"add".equals(mode) || "copy".equals(mode)'>
                            $("#issueTypeList").addRowData(data.id,data,"last");
                            $.messageBox({message:"成功保存新事件处理类别信息！"});
                        </s:if>
                        <s:if test='"edit".equals(mode)'>
                            $("#issueTypeList").setRowData(data.id,data);
                            $.messageBox({message:"成功保存事件处理类别修改信息！"});
                        </s:if>
                        $("#issueTypeDialog").dialog("close");
                        $("#issueTypeList").setSelection(data.id);
                    },
                    error:function(XMLHttpRequest, textStatus, errorThrown){
                        $.messageBox({message:"提交错误！"});
                    }
                });
            },
            rules:{
                "issueType.issueTypeName":{
                    required:true,
                    minlength:2,
                    maxlength:50,
                    exculdeParticalChar:true,
                    remote:checkIssueTypeName
                },
                <s:if test="gridInternalProperty!=null">
	                "issueType.internalId":{
	                    required:true
	                },
                </s:if>
                "issueType.content":{
                    maxlength:200
                }
            },
            messages:{
                "issueType.issueTypeName":{
                    required:"事件处理类别名称必须输入",
                    minlength:$.format("事件处理类别名称至少需要输入{0}个字符"),
                    maxlength:$.format("事件处理类别名称最多需要输入{0}个字符"),
                    exculdeParticalChar:"事件处理类别名称只能输入字母，数字和中文字符",
                    remote:"事件处理类别已存在"
                },
                <s:if test="gridInternalProperty!=null">
	                "issueType.internalId":{
	                    required:"内置属性必选"
	                },
	            </s:if>
                "issueType.content":{
                    maxlength:$.format("描述最多需要输入{0}个字符")
                }
            }
        });
    </s:if>
    $("#gridInternalProperty").val(${issueType.internalId});
});

function getCurrentOrgIdInIssueTypePage() {
	currentOrgId = $("#orgId").val();
	$.ajax({
		async: false,
		url: "${path}/sysadmin/issueTypeManage/validateIssueTypeName.action",
		data:{
            "issueType.issueTypeName": function(){ return $('#issueTypeName').val()},
            "mode" : function(){ return $('#mode').val()},
            "issueType.id" : function(){ return $('#typeid').val()},
            "issueType.issueTypeDomain.id":function(){return $("#domainId").val()},
            "orgId":currentOrgId
		}
	});
}

function checkIssueTypeName() {
	$.ajax({
		async: false,
		data:{
        "issueType.issueTypeName": function(){ return $('#issueTypeName').val()},
        "mode" : function(){ return $('#mode').val()},
        "issueType.id" : function(){ return $('#typeid').val()},
        "issueType.issueTypeDomain.id":function(){return $("#domainId").val()}
        <s:if test="@com.tianque.core.util.ThreadVariable@getUser().admin && issueType.issueTypeDomain.personalized">
        ,"orgId":$("#orgId").val()
        </s:if>
    },
    url:"${path}/sysadmin/issueTypeManage/validateIssueTypeName.action",
    type:"get"
	});
}

function setCurrentOrgId() {
	if(currentOrgId == null && currentOrgId=="") {
		currentOrgId = $("#orgId").val();
	}
	return $("#orgId").val();
}
</script>
<div class="container container_24">
    <s:if test='!"view".equals(mode)'>
        <form id="maintainForm" method="post"  action="" >
    </s:if>
        <input type="hidden" name="mode" value="${mode}" id="mode" />
        <input type="hidden" name="issueType.id" value="${issueType.id}" id="typeid" /> 
        <input type="hidden" name="issueType.issueTypeDomain.id" value="${issueType.issueTypeDomain.id}" id="domainId" />
        <input type="hidden" name="issueType.indexId" value="${issueType.indexId}" id="indexId" /> 
        <div class="grid_5 lable-right">
        <em class="form-req">*</em>
           <label class="form-lbl">类别名称：</label>
        </div>
        <div style="display:inline;float:left;height:60px;line-height:60px;width:75%;">
            <textarea style="height:40px;" name="issueType.issueTypeName" class="form-select" 
            <s:if test='"view".equals(mode)'>disabled='true'</s:if> maxlength="50">${issueType.issueTypeName}</textarea>
        </div>
        <s:if test='!"view".equals(mode)'>
        <div class="grid_1">            
        </div>
        </s:if>
         
        <div class='clearLine'>&nbsp;</div>
        <s:if test="user.admin && issueType.issueTypeDomain.personalized">
         
         
        <div class="grid_5 lable-right">
           <label class="form-lbl">所属县区：</label>
        </div>
        <s:if test='"add".equals(mode)'>
        <div style="display:inline;float:left;height:30px;line-height:60px;width:75%;">
        	<input type="text" id="orgName" width="200"/>
			<input type="hidden" name="orgId" id="orgId"/>
		</div>
		</s:if>
		 <s:if test='!"add".equals(mode)'>
	        <div style="display:inline;float:left;height:30px;line-height:30px;width:75%;">
	            <input type="text" id="org-name" class="form-txt" value="${issueType.org.orgName}" maxlength="20" disabled />
				<input type="hidden" name="orgId" id="orgId" value="${issueType.org.id}" />
			</div>
    	</s:if> 
       </s:if>
        <div class='clearLine'>&nbsp;</div>
        <s:if test="gridInternalProperty!=null">
	    <div class="grid_5 lable-right">
	     <em class="form-req">*</em>
	        <label class="form-lbl"> 内置属性：</label>
	    </div>
	    <div style="display:inline;float:left;height:30px;line-height:30px;width:75%;">
	        <select name="issueType.internalId" <s:if test='"view".equals(mode)'>disabled</s:if> class="form-txt" id="gridInternalProperty">
	           <s:iterator value="gridInternalProperty" id="id">
	                 <option value="${identify}" >${displayName}</option>
	           </s:iterator>
	        </select>
	    </div>
	    <s:if test='!"view".equals(mode)'>
        <div class="grid_1">           
        </div>
        </s:if>
	    </s:if>
	    <div class='clearLine'>&nbsp;</div>
        <div class="grid_5 lable-right">
            <label class="form-lbl">描述：</label>
        </div>
        <div style="display:inline;float:left;height:125px;line-height:125px;width:75%;">
            <textarea style="height:110px;" name="issueType.content" class="form-select" 
            <s:if test='"view".equals(mode)'>disabled='true'</s:if> maxlength="200">${issueType.content}</textarea>
        </div>
    <s:if test='!"view".equals(mode)'>
        </form>
    </s:if>
</div>