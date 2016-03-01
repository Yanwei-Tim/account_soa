<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />

<s:action var="getParentOrgById" name="ajaxOrganization"
	namespace="/sysadmin/orgManage" executeResult="false">
	<s:param name="organization.id" value="#parameters.parentId"></s:param>
	<s:param name="mode" value="#parameters.mode"></s:param>
</s:action>
<s:action var="getParentOrgType" name="getPropertyDictById"
	namespace="/sysadmin/propertyManage" ignoreContextParams="true"
	executeResult="false">
	<s:param name="propertyDict.id"
		value="#getParentOrgById.organization.orgType.id">
	</s:param>
	<s:param name="propertyDomain.domainName"
		value="@com.tianque.domain.property.PropertyTypes@ORGANIZATION_TYPE">
	</s:param>
</s:action>
<s:action var="getParentOrgLevel" name="getPropertyDictById"
	namespace="/sysadmin/propertyManage" ignoreContextParams="true"
	executeResult="false">
	<s:param name="propertyDict.id"
		value="#getParentOrgById.organization.orgLevel.id">
	</s:param>
	<s:param name="propertyDomain.domainName"
		value="@com.tianque.domain.property.PropertyTypes@ORGANIZATION_LEVEL">
	</s:param>
</s:action>
<s:action var="getOrgLevels" name="findPropertyDictByDomainName"
	namespace="/sysadmin/propertyManage" ignoreContextParams="true">
	<s:param name="propertyDomain.domainName"
		value="@com.tianque.domain.property.PropertyTypes@ORGANIZATION_LEVEL">
	</s:param>
	<s:param name="propertyDict.internalId"
		value="@com.tianque.domain.property.OrganizationLevel@getLowerLevel(#getParentOrgLevel.propertyDict.internalId)"></s:param>
</s:action>

<s:action var="getOrgTypes" name="getOrgTypeListWhenAdd"
	namespace="/sysadmin/orgManage" ignoreContextParams="true"
	executeResult="false">
	<s:param name="organization.orgType.internalId"
		value="#getParentOrgType.propertyDict.internalId">
	</s:param>
	<s:param name="organization.orgLevel.internalId"
		value="#getParentOrgLevel.propertyDict.internalId">
	</s:param>
</s:action>
<div class="container container_24">
<form action="${path }/sysadmin/orgManage/ajaxAddOrganization.action"
	method="post" id="org-create-form"><input type="hidden"
	name="organization.parentOrg.id"
	value="<s:property value="#parameters.parentId"/>"
	id="create-parentOrg-id" />
<div class="grid_5 lable-right"><em class="form-req">*&nbsp;</em><label class="form-lbl">部门名称：</label>
</div>
<div class="grid_7"><input type="text" name="organization.orgName"
	id="create-organization-orgName" class="form-txt" maxlength="20" /></div>
<div class="grid_1"></div>
<div class="grid_4 lable-right"><label class="form-lbl">联系电话：</label>
</div>
<div class="grid_7"><input type="text"
	name="organization.contactWay" class="form-txt" maxlength="15" /></div>
<div class="grid_5 lable-right"><label class="form-lbl">部门类型：</label>
</div>
<div class="grid_7"><s:select id="orgType-select"
	list="#getOrgTypes.orgTypes" value="" name="organization.orgType.id"
	listKey="id" listValue="displayName" cssClass="form-txt">
</s:select></div>
<div id="parentOrg-select" style="display: none;">
<div class="grid_1"></div>
<div class="grid_4 lable-right"><label class="form-lbl">上级职能部门：</label>
</div>
<div class="grid_7"><select id="parentFunOrg-select"
	class="form-txt" name="organization.parentFunOrg.id">
	<option></option>
</select></div>
</div>
<div id="orgLevel-select">
<div class="grid_1"></div>
<div class="grid_4 lable-right"><label class="form-lbl">部门级别：</label>
</div>
<div class="grid_7"><s:select list="#getOrgLevels.propertyDicts" id="orgLevel"
	value="" name="organization.orgLevel.id" listKey="id"
	listValue="displayName" cssClass="form-txt">
</s:select></div>
</div>
	<div class="grid_5 lable-right">
		<em class="form-req">*&nbsp;</em>
		<label class="form-lbl">部门编号：</label>
	</div>
	<div class="grid_18">
		<input type="text" id="org-departmentNo" name="organization.departmentNo" class="form-txt" />
	</div>
<div class="grid_1" id="stars"></div>

<div class="grid_5 lable-right"><label class="form-lbl">备注：</label>
</div>
<div id="org-remark" class="grid_19"><textarea
	name="organization.remark" class="form-txt"></textarea></div>

</form>
</div>
<script>
$(document).ready(function(){
	var parentDepartmentNo = '<s:property value="#getParentOrgById.organization.departmentNo" />';
	var isWange = false;
	if(parentDepartmentNo.length == 12){
		$.messageBox({message:"请输入3位网格编号!"});
		$("#org-departmentNo").val(parentDepartmentNo);
		isWange = true;
	}
	if(parseInt(<s:property value="#getOrgLevels.propertyDicts.get(0).internalId" />) == 0){
		//$("#org-departmentNo").attr("readonly","");
	}
	$("#create-organization-orgName").autocomplete({
		source: function(request, response) {
			$.ajax({
				url: PATH+"/sysadmin/orgManage/autoFindOrganizationsByOrgNameOrOrgInternalCode.action",
				data:{"fieldCode":function(){return request.term;},
					  "orgLevel":<s:property value="#getOrgLevels.propertyDicts.get(0).internalId" />,
					  "organization.departmentNo":parentDepartmentNo
					},
				success: function(data) {
					if(data == null){
						return;
					}
					if(!$.isArray(data)){
						$.messageBox({
							message:data,
							level:"error"
						});
						return ;
					}
					response(
						$.map(data, function(item){
										return {
											label: item.orgName+","+item.departmentNo,
											value: item.orgName,
											id: item.id,
											departmentNo:item.departmentNo
										}
						}
					))
				},
				error : function(){
					$.messageBox({
						message:"搜索失败，请重新登入！",
						level:"error"
					});
				}
			})
		},
		select: function(event, ui) {
			$("#org-departmentNo").val(ui.item.departmentNo);
		}
	});
	$.ajax({
		async:false,
		url:"${path}/sysadmin/orgManage/isDistrictOfAdministrativeRegion.action",
		data:{
			"organization.parentOrg.id":$("#create-parentOrg-id").val(),
			"organization.orgType.id":$('#orgType-select').val()
		},
		success:function(responseData){
			if(responseData){
				$("#stars").append("<em class='form-req'>*</em>");
			}else{return;}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
	      		alert("提交数据时发生错误");
  		    }
	});
	jQuery.validator.addMethod("checkOrgNoLength",function(value,element){
		if( value.length!=0){
			if(isNaN(value) || value.length < 2)
		    {
		        return false;
		    }
		}
		return true;
	});
	$("#orgType-select").change(function(){
		if($("#create-parentOrg-id").val()!=null && $('#orgType-select').val()!=null){
			$.ajax({
				async:false,
				url:"${path}/sysadmin/orgManage/isDistrictOfAdministrativeRegion.action",
				data:{
					"organization.parentOrg.id":$("#create-parentOrg-id").val(),
					"organization.orgType.id":$('#orgType-select').val(),
					"organization.orgLevel.id":$('#orgLevel').val()
				},
				success:function(responseData){
					if(!responseData){
						$("#stars").children().empty();
					}else{
						$("#stars").append("<em class='form-req'>*</em>");
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
      	      		alert("提交数据时发生错误");
   	   		    }
			});
		}

		if(orgTypeInternalIdArray[$("#orgType-select").val()]==<s:property value="@com.tianque.domain.property.OrganizationType@FUNCTIONAL_ORG"/>){
			$("#org-remark").removeClass("grid_19").addClass("grid_19");
			$("#parentOrg-select").css("display","block");
			$("#orgLevel-select").css("display","none");
			$("#parentFunOrg-select").html("");
			if($("#parentFunOrg-select").children().length==0){
				$.ajax({
					url : "${path}/sysadmin/orgManage/findOrganizationsByParentIdAndFunctionalOrgType.action",
					data: {parentId : function(){if($.getSelectedNode(tree).parentNode.attributes.id!="orgTree-root"){return $.getSelectedNode(tree).parentNode.attributes.id}else{return 0;}}},
					dataType : "json",
					async: false,
					success : function(data){
						$("#parentFunOrg-select").append($("<option></option>"));
						for(var i=0;i<data.length;i++){
							var op=$("<option></option>");
							op.attr("value",data[i].id).text(data[i].orgName);
							$("#parentFunOrg-select").append(op);
						}
					}
				});
			}
		}else{
			$("#parentFunOrg-select").html("");
			$("#org-remark").removeClass("grid_7").addClass("grid_19");
			$("#parentOrg-select").css("display","none");
			$("#orgLevel-select").css("display","block");
		}
	});

	$.validator.setDefaults({
		highlight: function(input) {
			$(input).addClass("ui-state-highlight");
		},
		unhighlight: function(input) {
			$(input).removeClass("ui-state-highlight");
		}
	});

	$("#org-create-form").formValidate({
		promptPosition :"bottomLeft",
		submitHandler: function(form) {
			if($("#org-departmentNo").val() == "" || null == $("#org-departmentNo").val()){
				$.messageBox({message:"请保持部门编号不为空!",level:"error"})
				return;
			}
			if(isWange){
				if($("#org-departmentNo").val().length != 15){
					$.messageBox({message:"请输入3位部门编号!",level:"error"});
					return;
				}
			}
			$("#org-create-form").ajaxSubmit({
				success:function(data){
					if(data.organization){
						$.addNodeToLast(tree,data.extTreeData,$("#create-parentOrg-id").val());
						$("#orgChildren-dataGrid").addRowData(data.organization.id,data.organization,"last");
						$("#orgChildren-dataGrid").setSelection(data.organization.id);
						$.messageBox({
							message:"部门添加成功！"

						});
						$("#orgChildren-dataGrid").trigger("reloadGrid");
						$("#org-dailog").dialog("close");
						return ;
					}else{
						$.messageBox({
							message:data,
							level:"error"
	                    });
						return ;
					}
				}

			});
			return false;
		},
		rules: {
			"organization.orgName": {
				required: true,
				exculdePartical:true,
				minlength: 2,
				remote: {
                   data: {
                        "organization.orgName": function(){
                            return $('#create-organization-orgName').val();
                        },
                        "parentId" : function(){
                            return $("#orgDetail-orgId").val();
                        }
                   },
            	   url: "${path}/sysadmin/orgManage/validateOrgName.action",
            	   type: "post"
	            }
			},
			"organization.departmentNo": {
				required:true,
				exculdeParticalChar :true,
				remote: {
                   data: {
                        "organization.departmentNo": function(){
                            return $('#org-departmentNo').val();
                            }
                   },
            	   url: "${path}/sysadmin/orgManage/validateRepeatDepartmentNo.action",
            	   type: "post"
	            }
			},
			"organization.contactWay":{
				telephone:true
			},
			"organization.remark":{
				maxlength: 200
			}

		},
		messages: {
			"organization.orgName": {
				required: "请输入部门名称",
				exculdePartical:"部门名称只能输入字母，数字和中文字符",
				minlength  : $.format("至少需要{0}个字符"),
				telephone:"联系方式不能有特殊字符",
				remote:"部门名已经存在"
			},
			"organization.departmentNo": {
				required:"该编号为自动获取，没有读取到" + $("#create-organization-orgName").val() +"对应的部门编号，请联系管理员之后再操作!",
				exculdeParticalChar : "部门编号不能有特殊字符",
				remote:"部门编号已经存在"
			},
			"organization.contactWay":{
				telephone:"请填写正确的联系电话"
			},
			"organization.remark":{
				maxlength  : $.format("部门备注不能大于{0}个字符")
			}
		}
	});
});

function validateDeptNoRequired(){
	var result=false;
	$.ajax({
		async:false,
		url:"${path}/sysadmin/orgManage/isDistrictOfAdministrativeRegion.action",
		data:{
			"organization.parentOrg.id":$("#create-parentOrg-id").val(),
			"organization.orgType.id":$('#orgType-select').val()
		},
		success:function(responseData){
			if(!responseData){
				result= false;
			}else{
				result= true;
			}
		}
	});
	return result;
}
</script>
