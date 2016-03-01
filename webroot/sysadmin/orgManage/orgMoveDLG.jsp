<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<jsp:include page="/includes/baseInclude.jsp" />
</head>
<body>

<form id="org-move-form" action="${path }/sysadmin/orgManage/ajaxMoveOrganization.action"
	method="post"><input type="hidden" name="organization.id"
	value="${organization.id }" />
	<input type="hidden" name="organization.parentOrg.id"
	value="" id="move-parentOrg-id"/>
<div>
<div>部门编码：<input type="text" name="organization.orgInternalCode"
	value="${organization.orgInternalCode }" readonly="readonly"/>部门名称：<input type="text"
	name="organization.orgName" value="${organization.orgName }" readonly="readonly"/></div>
<div>部门类型：<input type="text" name="organization.orgType"
	value="${organization.orgType }" readonly="readonly"/>联系方式：<input type="text"
	name="organization.contactWay" value="${organization.contactWay }" readonly="readonly"/></div>
<div>所属部门：<input id="update-org-autocomplete" type="text" name=""/></div>
<div>
</div>
</div>
<button id="testB" type="button">test</button>
</form>
<script>
$(document).ready(function(){
	$("#testB").click(function(){$("#test-dailog").dialog("close");})
	
	$("#org-move-form").submit(function(){
		alert($("#move-parentOrg-id").val());
		
		$("#org-move-form").ajaxSubmit({
			success:function(data){
				$("#orgTree").reflash("organization"+$("#orgDetail-orgId").val());
				$("#orgTree_organization"+$("#orgDetail-orgId").val()).attr("title",data.orgName);
				$("#orgTree_organization"+$("#orgDetail-orgId").val()).children("a").children("span").text(data.orgName);
				$("#orgDetail-orgName").val(data.orgName);
				$("#orgChildren-dataGrid").trigger("reloadGrid");
				$.messageBox({
					message:"部门移动成功！"
				});
			}
		});
		return false;
	})

	$("#update-org-autocomplete").autocomplete({
		source: function(request, response) {
			$.ajax({
				url: "${path }/sysadmin/orgManage/ajaxFindOrganizationsByOrgName.action",
				data:{"organization.orgName":request.term},
				success: function(data) {
					response($.map(data, function(item) {
						return {
							label: item.orgName+","+item.contactWay,
							value: item.orgName,
							id: item.id,
						}
					}))
				},
				error : function(){
					alert("error");
				}
			})
		},
		select: function(event, ui) {
			$("#move-parentOrg-id").val(ui.item.id);
		},
		open: function() {
			$(this).removeClass("ui-corner-all").addClass("ui-corner-top");
		},
		close: function() {
			$(this).removeClass("ui-corner-top").addClass("ui-corner-all");
		}
	});
});
</script>
</body>
</html>