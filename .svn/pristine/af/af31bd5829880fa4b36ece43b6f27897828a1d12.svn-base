<%@ page language="java" pageEncoding="UTF-8"%>
<style>
	*{margin:0;padding:0;}
	a{font:normal 12px/22px 'microsoft yahei';text-decoration:none;color:#333;}
	.orgSelectBox{border:2px solid red;width:500px;height:300px;overflow:hidden;overflow-y:auto;}
	.orgBoxResult a{margin:0 3px;}
	.organizationBox h3{width:100px;}
	.organizationBox h3,.organizationlist{display:inline-block;}
	.organizationBox a{margin:0 3px;}
	.organizationBox a.cur{font-weight:bold;}
</style>
<link href="${resource_path}/resource/js/ui/jquery-ui-1.8.17.custom.css" rel="stylesheet" type="text/css"/>
<link href="${resource_path}/resource/js/ui/default.css" rel="stylesheet" type="text/css" id="cssfile"/>
<script type="text/javascript" src="/resource/js/jquery.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/ui/jquery-ui-1.8.17.custom.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/ui/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="${resource_path}/resource/js/ui/jquery.ui.datepicker-zh-CN.js"></script>
<div id="refreshOrgTreeDiv">
	<div class="ui-widget" >
		<input id="org-tree-autocomplete" type="text" style="width:154px;height:18px;border:1px solid #A8BECE;background:#fff; position:relative;margin:5px 0 5px 2px;" value=""/>
		<button id="refreshOrgTree" class="ui-icon ui-icon-refresh" style="border:0;background-color:transparent; position:absolute;top:37px;left:140px;cursor:pointer;outline: none;"></button>
	</div>
</div>
<input type="text" id="orgSelect"></input>
<script>
function stringFormatter(str){
	if(str==undefined){
		return "";
	}
	return str;
}
$("#org-tree-autocomplete").autocomplete({
	source: function(request, response) {
		$.ajax({
			url: "/sysadmin/orgManage/ajaxFindOrganizationsByOrgNameAndOrgType.action",
			data:{"organization.orgName":function(){return request.term;}},
			success: function(data) {
				response($.map(data, function(item) {
					return {
						label: item.orgName+","+stringFormatter(item.contactWay),
						value: item.orgName,
						id: item.id
					}
				}))
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
		$.ajax({
			url:"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+ui.item.id,
			success:function(data){
				$("#orgSelect").orgSelect("setOrg",data);
			}
		});
	}
});
$(function(){
	$("#orgSelect").orgSelect();
})
</script>
