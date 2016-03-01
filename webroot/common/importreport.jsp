<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<link href="${resource_path}/resource/js/loadmask/jquery.loadmask.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="${resource_path}/resource/js/loadmask/jquery.loadmask.min.js"></script>

<style type="text/css">
#load {position:relative;}
#load .ui-button{width:80px;height:25px;}
#load .ui-dialog-buttonset{position:absolute;bottom:20px;right:15px;}
</style>

<form id="mForm" method="post" enctype="multipart/form-data" action="${path}/dataTransfer/importReportToDomain.action">
	<div id="upload-div" class="container container_24">
		<div class="grid_24">
		    <label class="form-lbl heightAuto" id="templateVersion">
		            <font color="red"><s:property value="@com.tianque.service.vo.ReportInfoTableTypes@getReportDisplayNames(#parameters.templates)"/></font>&nbsp;导入功能支持的数据模板为
		            <font color="red"><s:property value="@com.tianque.service.vo.ReportInfoTableTypes@getImportTemplatesReportVo(#parameters.templates).version"/></font>
		                               版本，如数据模板版本不正确，您可以下载	<a href='${path}/dataTransfer/downloadReportTemplate.action?dataTemplatesName=<s:property value="@com.tianque.service.vo.ReportInfoTableTypes@getImportTemplatesReportVo(#parameters.templates).url"/>'><b><font color="dimgray"><i>最新数据模板</i></font></b></a>
            </label>
		</div>
	    <div style="clear: both;">&nbsp;</div>
		<div class="grid_6 lable-right"  >
			<label class="form-lbl">选择文件：</label>
		</div>
		<div class="grid_18" style="">
			<input id="fileToUpload" type="file" value="" name="upload">
			<input name="dataType" value="<s:property value="#parameters.dataType"/>" type="hidden">
			<input name="templates" value="<s:property value="#parameters.templates"/>" type="hidden">
			<input id="dialog" value="<s:property value="#parameters.dialog"/>" type="hidden">
			<input name="startRow" value="<s:property value="#parameters.startRow"/>" type="hidden">
			<input name="enterpriseType" value="<s:property value="#parameters.enterpriseType"/>" type="hidden">
		</div>
		<div id="message" class="grid_14 lable-right"></div>
	</div>
</form>
<script>
var intarcalId;
var totalRowCount=0;
var currentRowCount=0;
var ticketId='';
var ticketNewId='';
var currentErrorRow=0;
var threadId;


$(document).ready(function(){
	$("#mForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				type:"json",
				success: function(data){
					var dataJson=eval("("+data+")");
					$("#<s:property value='#parameters.dialog'/>").dialog("close");
					var str = "<div id='load' class='ui-widget-border'><div style='text-align:left;margin-left:30px;margin-top:20px;margin-bottom:-60px;margin-right:30px;' id='templateVersionLabel'>"+$("#templateVersion").html()+"</div><p class='filename'>"+dataJson.uploadFileName+"</p><p id='progressbar-msg'>文件正在上传...</p><div id='progressbar'></div><span id='progressbar-value'>0%</span><div class='clear'>&nbsp;</div><div id='errorMsgDiv' style='margin-top:10px;margin-left:20px;'><table id='errorMsg'></table></div>";
					str += "<div class='clear'></div>";
					str += "<div class='ui-dialog-buttonset' id='errorMsgButton'><button id='reloadExcel' type='button' class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only'>重新上传</button><button id='cancel' type='button' class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only'>关闭</button></div>";
					str +="</div>";
					$("body").mask(str);
					$("#errorMsg").jqGridFunction({
	                    datatype: "local",
	                    width:380,
	                    height:150,
	                    colModel:[
	                        {name:'errorMsg',label:'错误信息',width:'350',sortable:false,formatter:autoHeightRow}
	                    ]
	                });
					$("#errorMsgDiv").hide();
					$("#errorMsgButton").hide();
					$("#progressbar").progressbar();
					ticketId = dataJson.ticketId;
					threadId = dataJson.threadId;
					intarcalId = setInterval(getTicketByIds,1000);
				}
			});
			return false;
		},
		rules:{
			"upload":{
				isExcel: true
			}
		},
		messages:{
			"upload":{
				isExcel: "只能选择Excel文件"
			}
		}
	});
});


function getTicketByIds(){
	$.ajax({
		url: '${path}/ticket/getDataImportTicketByTicketId.action',
		type: 'post',
		dataType:'json',
		data:{
			"ticketId":ticketId,
			"currentErrorRow": function(){return $("#errorMsg").getGridParam("records");}
		},
   	 	success: function(data){
	   	 	if(data == null){
                clearInterval(intarcalId);
	   	 	}
			totalRowCount=data.totalRowCount;
			currentRowCount=data.currentRowCount;
			$("#progressbar").progressbar("option", "value", currentRowCount/totalRowCount*100);
			$("#progressbar-value").text(parseInt(currentRowCount/totalRowCount*100)+"%");
			var jsonMsg = eval("("+data.description+")");
			var dataJson = data.errorMsgs;
			var state = data.state;
			$("#progressbar-msg").text(jsonMsg.msg);
			if(dataJson && dataJson.length>0){
				showErrorDiv();
	            for(var i=0;i<dataJson.length;i++){
		            var str = {errorMsg:dataJson[i]};
		            $("#errorMsg").addRowData($("#errorMsg").getGridParam("records")+i,str,"last");
	            }
	            if(state == '<s:property value="@com.tianque.state.TicketState@DOING"/>'){
	            	intarcalId = setInterval(getTicketByIds,1000);
	            }
	            showErrorMsgButton();
	        }
			if(state=='<s:property value="@com.tianque.state.TicketState@DONE"/>' && jsonMsg.successMsg){
				clearInterval(intarcalId);
				$("body").unmask();
				var succHtml = "<div id='load' class='ui-widget-border'>"+
								"<div class='ui-widget' style='margin-top:30px;'>"+
									"<div class='ui-corner-all ui-widget-border ui-state-highlight' style='width:400px;margin-top:5px;text-align:left;margin-left:5px;'><em class='ui-icon ui-icon-circle-check' style='float: left; margin-right: .3em;'></em>导入成功</div>"+
								"</div>"+
								"<div class='clear'></div>"+
								"<div class='ui-dialog-buttonset'><button id='closeImportData' type='button' class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only'>关闭</button></div>"+
								"</div>";
				$("body").mask(succHtml);
				$('#closeImportData').unbind('click');
				$('#closeImportData').bind('click', function () {$('body').unmask();	});
			}
			if(state=='<s:property value="@com.tianque.state.TicketState@TIMEOUT"/>' || state=='<s:property value="@com.tianque.state.TicketState@EXCEPTIONSTOP"/>'){
				clearInterval(intarcalId);
			}
	 	}
  	});
}

function showErrorDiv(){
	clearInterval(intarcalId);
    $("#load").attr("style","height:400px;");
    $("#errorMsgDiv").show();
}
function showErrorMsgButton(){
	$("#errorMsgButton").show();
	$('#cancel').unbind('click');
    $('#cancel').bind('click', function () {clearInterval(intarcalId);$('body').unmask(); threadStop();});
    $('#reloadExcel').bind('click',function(){
        clearInterval(intarcalId);
        $('body').unmask();
        $("#<s:property value='#parameters.dialog'/>").createDialog({
            width: 400,
            height: 220,
            title:'数据导入',
            url:"${path}/common/importreport.jsp?dataType=<s:property value='#parameters.dataType'/>&dialog=<s:property value='#parameters.dialog'/>&startRow=<s:property value='#parameters.startRow'/>&templates=<s:property value='#parameters.templates'/>",
            buttons:{
                "重新导入" : function(event){
                      clearInterval(intarcalId);
                      $("#mForm").submit();
               },
               "关闭" : function(){
                    clearInterval(intarcalId);
                    $(this).dialog("close");
               }
            },
            shouldEmptyHtml:false
        });
    });
}

function autoHeightRow(el,options,rowData){
	var data = rowData.errorMsg.replace(/[&nbsp;,\t,\n]/g,"").replace(/[ ]/g,"");
	var result = "";
	var i = 0;
	for(i=0;i<data.length/30;i++){
		result = result + data.substring(i*30,(i + 1)*30)+"\n";
	}
	result = result + data.substring(i*30);
	return "<div class='heightAuto'>" + result +  "</div>";
}

function threadStop(){
	$.ajax({
		url: '${path}/dataTransfer/reportThreadStop.action',
		data:{"threadId":threadId},
   	 	success: function(data){
	 	}
  	});
}

</script>