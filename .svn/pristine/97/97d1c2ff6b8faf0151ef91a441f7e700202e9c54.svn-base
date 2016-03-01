<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content" >
   <div class="ui-corner-all" id="nav">
   <div  style="float:left;height:25px;line-height:25px;">
			<label style="float:left;line-height:25px;padding-right:10px;">开始日期：</label>
			<input type="text" id="startDate" name="startDate" readonly="readonly" size="10"  style="float:left;margin-top:3px;" />
			<label style="float:left;line-height:25px;padding:0 10px;">结束日期：</label>
			<input type="text" id="endDate" name="endDate" readonly="readonly" size="10" style="float:left;margin-top:3px;" />
			<label style="float:left;padding:0 10px;line-height:25px;">模块名称：</label>
			<select id="model" name="log.modelName" style="float:left;margin-top:3px;" onchange="changgemode(this)">
			  <option value=""></option>
			   <option value="用户管理">用户管理</option>
			   <option value="部门管理">部门管理</option>
			   <option value="岗位管理">岗位管理</option>
			</select>
			<label style="float:left;padding:0 10px;line-height:25px;">操作名称：</label>
			<select id="operation" name="log.operationType" style="float:left;margin-top:3px;" >
			   <option value=""></option>
			   <option value="1">新增</option>
			   <option value="2">修改</option>
			   <option value="3">删除</option>
			   <option value="5">导入</option>
			   <option value="9">启用</option>
			   <option value="10">禁用</option>
			   <option value="7">锁定</option>
			   <option value="8">解锁</option>
			   <option value="6">重置密码</option>

			</select>


			<label style="float:left;padding:0 10px;line-height:25px;">操作用户：</label>
			<input type="text" id="user_autocomplete" name="log.operationUserName" style="width: 100px;float:left;margin-right:10px;margin-top:3px;"/>

			<a id="search" href="javascript:void(0)"><span>查询</span></a>
		</div>
		<div class="clear"></div>
	     <span style="float:left;padding:0;margin:0;text-indent:0;line-height:25px;">常用日期：</span>
		<a id="today" href="javascript:void(0)"><span>今天</span></a>
		<a id="yesterday" href="javascript:void(0)"><span>昨天</span></a>
		<a id="weekly" href="javascript:void(0)"><span>本周</span></a>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>

   </div>
   <div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="highLogList"> </table>
		<div id="highLogListPager"></div>
	</div>

	<div id="highLogDialog"></div>
	<div id="noticeDialog"></div>
</div>
<script type="text/javascript">
$(document).ready(function(){

	$('#startDate').datePicker({yearRange:'1930:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'-0d'});
	$('#endDate').datePicker({yearRange:'1930:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d'});
    $("#startDate").datepicker("setDate" ,"-1d");
    $("#endDate").datepicker("setDate" , "+1d");


   $("#highLogList").jqGridFunction({
		url:"${path}/sysadmin/highlogManage/findSystemhighLogByOrgCode.action",
		postData:{
			"startDate": function (){ return $("#startDate").val()},
			"endDate":   function (){ return $("#endDate").val()},
			"log.modelName":function (){ return $("#model").val()},
			"log.operationType":  function (){ return $("#operation").val()},
			"log.operationUserName":  function (){ return $("#user_autocomplete").val()}
		},

    datatype: "json",
	colModel:[
    {name:"id", index:"id", hidden:true},
	{name:"operationDate", index:"operationDate",label:"时间",width:100},
	{name:"operationContent", index:"operationContent", width:200, label:"操作描述",formatter:formatteroperationContent},
	{name:"modelName", index:"modelName", width:160, label:"模块名称"},
	{name:"operationType", index:"operationType", width:160, label:"操作名称",formatter:formatteroperationType},
	{name:"operationUserName", index:"operationUserName", width:160, label:"操作用户"},
	{name:"organization.orgName", index:"organization.orgName", width:160, label:"网格",hidden:true},
	{name:"clientIp", index:"clientIp", width:160, label:"访问IP"}
      ],

		shrinkToFit:true,
	   	showColModelButton:false
	});
	    $("#reload").click(function(){
		$("#user_autocomplete").val("");
		$("#model").val("");
		$("#operation").val("");
		$("#highLogList").trigger("reloadGrid");
	});
	    $('#search').click(function(){
			if(!check()) return;

			$("#highLogList").trigger("reloadGrid");

		});

		$("#today").click(function(){
			$("#user_autocomplete").val("");
			$("#model").val("");
			$("#operation").val("");
		    $("#startDate").datepicker( "setDate" ,"+1d");
		    $("#endDate").datepicker( "setDate" , "+1d");
			$("#highLogList").trigger("reloadGrid");
		});

		$('#yesterday').click(function(){
			$("#user_autocomplete").val("");
			$("#model").val("");
			$("#operation").val("");
		    $("#startDate").datepicker( "setDate" ,"-1d");
		    $("#endDate").datepicker( "setDate","-1d");
			$("#highLogList").trigger("reloadGrid");
		});

		$('#weekly').click(function(){
			$("#user_autocomplete").val("");
			$("#model").val("");
			$("#operation").val("");
			$("#startDate").datepicker( "setDate" ,"-"+ new Date().getDay() + "d");
		    $("#endDate").datepicker( "setDate" , "+1d");
			$("#highLogList").trigger("reloadGrid");
		});


});

function diffDays(startDate,endDate){
	return Math.abs((endDate - startDate)/(3600*24*1000));
}

function check() {
    var startDate = $("#startDate").datepicker( "getDate");
    var endDate = $("#endDate").datepicker( "getDate");

	if(diffDays(startDate,endDate)>7){
		$.notice({
			message:"开始日期和结束日期相差不能超过7天!",
			width: 400
		});
		return false;
	}
	if(startDate>endDate){
		$.notice({
			message:"开始日期不能大于结束日期!",
			width: 400
		});
		return false;
	}
	return true;
}
function formatteroperationType(el,options,rowData){
	var operationType=rowData.operationType;
	if(operationType=='1'){
	   return "新增";
	}
	if(operationType=='2'){
		   return "修改";
		}
	if(operationType=='3'){
		   return "删除";
		}
	if(operationType=='5'){
		   return "导入";
		}
	if(operationType=='6'){
		   return "重置密码";
		}
	if(operationType=='7'){
		   return "锁定";
		}
	if(operationType=='8'){
		   return "解锁";
		}
	if(operationType=='9'){
		   return "启用";
		}
	if(operationType=='10'){
		   return "禁用";
		}
	return "";
}
function getString(operationType){
	if(operationType=='1'){
		   return "新增";
		}
		if(operationType=='2'){
			   return "修改";
			}
		if(operationType=='3'){
			   return "删除";
			}
		if(operationType=='5'){
			   return "导入";
			}
		if(operationType=='6'){
			   return "重置密码";
			}
		if(operationType=='7'){
			   return "锁定";
			}
		if(operationType=='8'){
			   return "解锁";
			}
		if(operationType=='9'){
			   return "启用";
			}
		if(operationType=='10'){
			   return "禁用";
			}
		return "";

}
function formatteroperationContent(el,options,rowData){
	var datavalue=getString(rowData.operationType);

	var data=rowData.modelName;
	if(datavalue=='新增'&&data=='岗位管理'){
		return rowData.operationUserName+datavalue+"岗位"+rowData.name;
	}
	if(datavalue=='新增'&&data=='部门管理'){
		return rowData.operationUserName+"在"+rowData.organization.orgName+datavalue+"部门"+rowData.name;
	}
	if(datavalue=='新增'&&data=='用户管理'){
		return rowData.operationUserName+"在"+rowData.organization.orgName+datavalue+"用户"+rowData.name;
	}
	if(datavalue=='导入'&&data=='用户管理'){
		return rowData.operationUserName+"在"+rowData.organization.orgName+datavalue+"用户"+rowData.name;
	}
	if(datavalue=='导入'&&data=='部门管理'){
		return rowData.operationUserName+"在"+rowData.organization.orgName+datavalue+"部门"+rowData.name;
	}
	if(datavalue=='修改'&&data=='岗位管理'){
		return rowData.operationUserName+"修改"+"岗位"+rowData.name;
	}
	if(datavalue=='修改'&&data=='部门管理'){
		return rowData.operationUserName+"在"+rowData.organization.orgName+datavalue+"部门"+rowData.name;
	}
	if(datavalue=='修改'&&data=='用户管理'){
		return rowData.operationUserName+"在"+rowData.organization.orgName+datavalue+"用户"+rowData.name;
	}
	if(datavalue=='删除'&&data=='岗位管理'){
		return rowData.operationUserName+datavalue+"岗位"+rowData.name;
	}
	if(datavalue=='删除'&&data=='用户管理'){
		return rowData.operationUserName+datavalue+"用户"+rowData.name;
	}
	if(datavalue=='删除'&&data=='部门管理'){
		return rowData.operationUserName+datavalue+"部门"+rowData.name;
	}
	if(datavalue=='锁定'){
		return rowData.operationUserName+datavalue+"用户"+rowData.name;
	}
	if(datavalue=='解锁'){
		return rowData.operationUserName+"对"+"用户"+rowData.name+datavalue;
	}
	if(datavalue=='禁用'){
		return rowData.operationUserName+datavalue+"用户"+rowData.name;
	}
	if(datavalue=='启用'){
		return rowData.operationUserName+datavalue+"用户"+rowData.name;
	}
	if(datavalue=='重置密码'){
		return rowData.operationUserName+"对用户"+rowData.name+datavalue;
	}

}
function changgemode(text){

	var str = "<option value=''></option>"
	+"<option value='3'>删除</option>"
	+"<option value='2'>修改</option>"
	+"<option value='1'>新增</option>"
	+"<option value='5'>导入</option>"
	+"<option value='9'>启用</option>"
	+"<option value='10'>禁用</option>"
	+"<option value='7'>锁定</option>"
	+"<option value='8'>解锁</option>"
	+"<option value='6'>重置密码</option>";
	var str1 = "<option value=''></option>"
		+"<option value='3'>删除</option>"
		+"<option value='2'>修改</option>"
		+"<option value='1'>新增</option>"
		+"<option value='5'>导入</option>";
	text = text.value;
	if(text!=null &&text=="岗位管理"){
		$("#operation option:[value=5]").remove();
         $("#operation option:[value=6]").remove();
         $("#operation option:[value=7]").remove();
         $("#operation option:[value=8]").remove();
         $("#operation option:[value=9]").remove();
         $("#operation option:[value=10]").remove();
	}else if(text!=null && text=="部门管理"){
		$("#operation").empty();
		$("#operation").append(str1);

	}else{
		$("#operation").empty();
		$("#operation").append(str);

	}


}

</script>