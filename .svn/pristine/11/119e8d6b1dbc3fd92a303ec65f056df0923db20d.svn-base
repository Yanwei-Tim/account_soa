<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />

<script type="text/javascript">
var currentOrgId;

$(function(){
		$("#maintainForm").attr("action","${path}/sysadmin/permissionManage/updatePermissionName.action");
        $("#maintainForm").formValidate({
			promptPosition:"bottomLeft",
            submitHandler:function(form){
                $(form).ajaxSubmit({
                    success:function(data){
                        if(!data){
                            $.messageBox({
                                message:data,
                                level:"error"
                            });
                            return;
                        }
                        if(typeof(tree.getNodeById($("#id").val()))!= "undefined"){
                			$.rename(tree , $("#id").val() , $("#cname").val());
                        }
                       	$("span[class='"+$("#ename").val()+"']").text($("#cname").val());
                        $.messageBox({message:"权限名称修改成功！"});
                        $("#permissionList").trigger("reloadGrid");
                        $("#permissionMaintanceDialog").dialog("close");
                        $("#permissionList").setSelection(data.id);
                    },
                    error:function(XMLHttpRequest, textStatus, errorThrown){
                        $.messageBox({message:"提交错误！"});
                    }
                });
            },
            rules:{
                "permission.cname":{
                    required:true,
                    minlength:2,
                    maxlength:10,
                    exculdeParticalChar:true
                }
            },
            messages:{
                "permission.cname":{
                	required:"权限名称必须输入",
                    minlength:$.format("权限名称至少需要输入{0}个字符"),
                    maxlength:$.format("权限名称最多需要输入{0}个字符"),
                    exculdeParticalChar:"权限名称只能输入字母，数字和中文字符"
                }
            }
        });
});

</script>
<div class="container container_24">
		<input type="hidden" id="ename" value="${permission.ename }" />
        <form id="maintainForm" method="post"  action="" >
        <input type="hidden" name="permission.id" id="id" value="${id}" id="mode" />
       
        <div class="grid_5 lable-right">
           <label class="form-lbl">权限名称：</label>
        </div>
        <div style="display:inline;float:left;height:60px;line-height:60px;width:75%;">
            <input type="text" name="permission.cname" id="cname" maxlength="10" style="ime-mode:enabled" value="${permission.cname }" class="form-txt" title="要更改的权限名称"/>
        </div>
        </form>
</div>