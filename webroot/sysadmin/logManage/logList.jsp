<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div  style="float:left;height:25px;line-height:25px;">
			<label style="float:left;line-height:25px;padding-right:10px;">开始日期：</label>
			<input type="text" id="startDate" name="startDate" readonly="readonly" size="10"  style="float:left;margin-top:3px;" />
			<label style="float:left;line-height:25px;padding:0 10px;">结束日期：</label>
			<input type="text" id="endDate" name="endDate" readonly="readonly" size="10" style="float:left;margin-top:3px;" />


			<label style="float:left;padding:0 10px;line-height:25px;">模块名称：</label>
			<select id="model" name="systemLog.moduleName" style="float:left;margin-top:3px;" onchange="changgemode(this)">
			  <option value=""></option>
			   <option value="用户管理">用户管理</option>
			   <option value="部门管理">部门管理</option>
			   <option value="岗位管理">岗位管理</option>
			   <option value="系统登录">系统登录</option>
			   <option value="重点场所">重点场所</option>
			   <option value="重点人员">重点人员</option>
			   <option value="出租房">出租房</option>
			   <option value="规上企业">规上企业</option>
			   <option value="社会组织">社会组织</option>
			</select>
			<div id="div2" style="display: inline;float: left;padding:0 10px;line-height:25px;">
			<label style="float:left;padding:0 10px;line-height:25px;">操作名称：</label>
			<select id="operation" name="systemLog.operationType" style="float:left;margin-top:3px;" >
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
               <option value="13">导出</option>
			</select>
         </div>

			<label style="float:left;padding:0 10px;line-height:25px;">用户名：</label>
			<input type="text" id="user_autocomplete" name="systemLog.userName" style="width: 100px;float:left;margin-right:10px;margin-top:3px;"/>
			 <div id="div1" style="display: none; float: left;padding:0 10px;line-height:25px;">
			<label style="float:left;padding:0 10px;line-height:25px;">登录状态：</label>
			<select id="successOrFailId"  style="float:left;margin-top:3px;">
			   <option value="0">全部</option>
			   <option value="1">登录成功</option>
			   <option value="2">登录失败</option>
			</select>
			</div>


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
		<table id="logList"></table>
		<div id="logListPager"></div>
	</div>
</div>
<script type="text/javascript">
var str = "<option value=''></option>"
	+"<option value='3'>删除</option>"
	+"<option value='2'>修改</option>"
	+"<option value='1'>新增</option>"
	+"<option value='5'>导入</option>"
	+"<option value='9'>启用</option>"
	+"<option value='10'>禁用</option>"
	+"<option value='7'>锁定</option>"
	+"<option value='8'>解锁</option>"
	+"<option value='13'>导出</option>"
	+"<option value='6'>重置密码</option>";
	var str1 = "<option value=''></option>"
		+"<option value='3'>删除</option>"
		+"<option value='2'>修改</option>"
		+"<option value='1'>新增</option>"
		+"<option value='5'>导入</option>";
	var str2="<option value=''></option>"
		+"<option value='13'>导出</option>"
		+"<option value='3'>删除</option>";
$(document).ready(function(){

	$('#startDate').datePicker({
		yearRange:'1930:2030',
		dateFormat:'yy-mm-dd',
		minDate:'-1m',
    	maxDate:'+0d'
        	});
	$('#endDate').datePicker({
		yearRange:'1930:2030',
		dateFormat:'yy-mm-dd',
		minDate:'-1m',
    	maxDate:'+0d'
        	});
    $("#startDate").datepicker("setDate" ,"+0d");
    $("#endDate").datepicker("setDate" , "+1d");

	jQuery("#logList").jqGridFunction({
		url:"${path}/sysadmin/logManage/findSystemLogByOrgCode.action",
		postData:{
			"startDate": function (){ return $("#startDate").val()},
			"endDate":   function (){ return $("#endDate").val()},
			"systemLog.moduleName":function (){ return $("#model").val()},
			"systemLog.operationType":  function (){ return $("#operation").val()},
			"systemLog.userName":  function (){ return $("#user_autocomplete").val()}
		},

		datatype: "json",
	   	colModel:[
			{name:'id', index:'id',hidden:true,sortable:false },
	   		{name:'operateTime', label:'时间', index:'operateTime', align:'center',sortable:true},
	   		{name:'operation', label:'操作描述',sortable:true},
	   		{name:'operationType', label:'操作类型',formatter:formatteroperationType,sortable:true},
	   		{name:'moduleName',label:'模块名称',sortable:true },
	   		{name:'userName', label:'用户名',sortable:true },
	   		{name:'clientIp', label:'访问IP',sortable:true }
	   	],
	   	shrinkToFit:true,
	   	showColModelButton:false
	});


	$("#reload").click(function(){
		$("#model").val("");
		$("#successOrFailId").val(0);
		$("#user_autocomplete").val("");
		$("#operation").empty();
		$("#operation").append(str);
		$('#div1').hide();
		$('#div2').show();
		$("#logList").trigger("reloadGrid");
	});

	$('#search').click(function(){
		if(!check()) return;
		$("#logList").trigger("reloadGrid");
	});

	$("#today").click(function(){
		$("#model").val("");
		$("#operation").val("");
		$("#user_autocomplete").val("");
		$("#successOrFailId").val(0);
	    $("#startDate").datepicker( "setDate" ,"+0d");
	    $("#endDate").datepicker( "setDate" , "+1d");
		$("#logList").trigger("reloadGrid");
	});

	$('#yesterday').click(function(){
		$("#model").val("");
		$("#operation").val("");
		$("#user_autocomplete").val("");
		$("#successOrFailId").val(0);
	    $("#startDate").datepicker( "setDate" ,"-1d");
	    $("#endDate").datepicker( "setDate","-1d");
		$("#logList").trigger("reloadGrid");
	});

	$('#weekly').click(function(){
		$("#model").val("");
		$("#operation").val("");
		$("#user_autocomplete").val("")
		$("#successOrFailId").val(0);
		$("#startDate").datepicker( "setDate" ,"-"+ new Date().getDay() + "d");
	    $("#endDate").datepicker( "setDate" , "+1d");
		$("#logList").trigger("reloadGrid");
	});



});

// 功能: 1)去除字符串前后所有空格
// 2)去除字符串中所有空格(包括中间空格,需要设置第2个参数为:g)
/**function trim(str,is_global) {
	var result = str.replace(/(^\s+)|(\s+$)/g,"");
	if(is_global.toLowerCase()=="g")
		result = result.replace(/\s/g,"");
	return result;
}*/
function diffDays(startDate,endDate){
	return Math.abs((endDate - startDate)/(3600*24*1000));
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
	if(operationType=='11'){
		   return "登录";
		}
	if(operationType=='12'){
		   return "登出";
		}
	if(operationType=='13'){
		   return "导出";
		}
	return "";
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

function changgemode(text){
    if(text.value=='系统登录'){
    	$("#logList").setGridParam({
    		url:"${path}/sysadmin/logManage/logList.action",
    		postData:{
    			"startDate": function (){ return $("#startDate").val()},
    			"endDate":   function (){ return $("#endDate").val()},
    			"successOrFailId":function (){ return $("#successOrFailId").val()},
    			"systemLog.userName":  function (){ return $("#user_autocomplete").val()}
    		},
    		datatype: "json",
    		page:1
    	});

    	$("#logList").trigger("reloadGrid");
    }
    if(text.value!='系统登录'){
    	$("#logList").setGridParam({
    	url:"${path}/sysadmin/logManage/findSystemLogByOrgCode.action",
		postData:{
			"startDate": function (){ return $("#startDate").val()},
			"endDate":   function (){ return $("#endDate").val()},
			"systemLog.moduleName":function (){ return $("#model").val()},
			"systemLog.operationType":  function (){ return $("#operation").val()},
			"systemLog.userName":  function (){ return $("#user_autocomplete").val()}
		},
		datatype: "json",
		page:1
    	});
    	$("#logList").trigger("reloadGrid");
    }

	text = text.value;
	if(text!=null &&text=="岗位管理"){
		$('#div1').hide();
		$('#div2').show();
		$("#operation option:[value=5]").remove();
         $("#operation option:[value=6]").remove();
         $("#operation option:[value=7]").remove();
         $("#operation option:[value=8]").remove();
         $("#operation option:[value=9]").remove();
         $("#operation option:[value=10]").remove();
	}else if(text!=null && text=="部门管理"){
		$('#div1').hide();
		$('#div2').show();
		$("#operation").empty();
		$("#operation").append(str1);

	}else if(text!=null && text=="系统登录"){
		$('#div1').show();
    	$('#div2').hide();
	}else if(text!=null && (text=="重点场所"||text=="重点人员"||text=="出租房"||text=="规上企业"||text=="社会组织")){
		$('#div1').hide();
		$('#div2').show();
		$("#operation").empty();
		$("#operation").append(str2);
	}else{
		$('#div1').hide();
		$('#div2').show();
		$("#operation").empty();
		$("#operation").append(str);

	}


}

</script>