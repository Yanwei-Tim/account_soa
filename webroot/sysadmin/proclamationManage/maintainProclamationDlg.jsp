<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24">
    <s:if test='"add".equals(mode)'>
        <form id="maintainForm" action="${path }/sysadmin/proclamationManage/addProclamation.action" method="post">
    </s:if>
    <s:if test='"edit".equals(mode)'>
        <form id="maintainForm" action="${path }/sysadmin/proclamationManage/updateProclamation.action" method="post">
    </s:if>
        <input type="hidden" id="mode" name="mode" value="${mode }"/>
        <input type="hidden" id="proclamationId" name="proclamation.id" value="${proclamation.id }"/>
        <div class="grid_3">公告内容：</div>
        <div class="grid_21" style="display:inline;float:left;height:270px;">
        	<s:if test='"view".equals(mode)'>
        		<div><s:property value="proclamation.content" escape="false"/></div>
	        </s:if>
	        <s:else>
	        	<textarea name="proclamation.content" id="proclamation_content" rows="18" cols="78" style="height:270px" >${proclamation.content}</textarea>
	        </s:else>
	    </div>
	    <div class="grid_3">&nbsp;</div>
	    <div class="grid_21">
	       <input type="checkbox"  name="proclamation.display" id="display" value="true" <s:if test='proclamation.display || "add".equals(mode)'>checked</s:if> <s:if test='"view".equals(mode)'>disabled</s:if>/>是否显示
	    </div>
    <s:if test='mode != null && !"view".equals(mode)'>
         </form>
    </s:if>
</div>

<script type="text/javascript">
$(document).ready(function(){
    	<s:if test='!"view".equals(mode)'>
            //$("#content").richText({tools:'mini'});
            $('#proclamation_content').richText();
            $('#proclamation_content').focus();
        </s:if>
        $("#maintainForm").formValidate({
        	promptPosition:"bottomLeft",
        	submitHandler:function(form){
        	    $(form).ajaxSubmit({
            	    success:function(data){
            	   var displayResult = data.display;
	        	    	if(!data.id){
	                        $.messageBox({
	                            message:data,
	                            level:"error"
	                        });
	                        return;
	                    }
	                    <s:if test='"add".equals(mode) || "copy".equals(mode)'>
	                		$("#proclamationList").addRowData(data.id,data,"first");
	                        $.messageBox({message:"成功保存新增系统公告信息！"});
	                    </s:if>
	                    <s:if test='"edit".equals(mode)'>
	                        $("#proclamationList").setRowData(data.id,data);
	                        $.messageBox({message:"成功保存系统公告修改信息！"});
	                    </s:if>
	                    $("#proclamationList").trigger("reloadGrid");
	                    $("#proclamationList").setSelection(data.id);
	                   if(!displayResult){
	                	   $("#announcement").hide();
		               }
        	        },
        	        error:function(XMLHttpRequest,textStatus,errorThrown){
            	        $.messageBox({message:"提交错误！"});
        	        }
            	});
                $("#proclamationDialog").dialog("close");
        	}
        });
    });
//-->
</script>