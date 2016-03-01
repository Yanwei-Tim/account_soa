<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
	<div style="width: 100%">
		<table id="publicNoticeList"> </table>
		<div id="publicNoticeListPager"></div>
	</div>
<div id="publicNoticeDialog"></div>
<script type="text/javascript">
function formatterAttachFile(el,options,rowData){
	if(rowData.noticeFiles.length>0){
		return "<div style='clear:both' align='center'><a href='javascript:showDailyLogAttachFile("+rowData.id+");'  style='color:#666666' class='popUpMore' publicNoticeId='"+rowData.id+"' >附件>></a></div>";
	}
	return "无";
}
function showDailyLogAttachFile(){
	$.each($(".popUpMore"), function(i, n){
		$.ajax({
			url:"${path}/sysadmin/publicNoticeManage/getPublicNoticeAttachFilesById.action?publicNotice.id="+$(n).attr("publicNoticeId"),
			success:function(publicNotice){
				var popMoreData = [];
				for(var j = 0; j < publicNotice.noticeFiles.length; j++){
					popMoreData[j]={id:publicNotice.noticeFiles[j].id, url:'${path}/sysadmin/publicNoticeManage/downloadPublicNoticeAttachFile.action?publicNoticeAttachFile.id='+publicNotice.noticeFiles[j].id, text:publicNotice.noticeFiles[j].fileName,clickFun:function(){}};
				}
				$(n).popUpMore({data: popMoreData});
			}
		});
	});
}
$(function(){

 var gridOption={
			colModel:[
				{name:"id", index:"id",hidden:true,frozen : true},
				{name:"publicNoticeTitle", index:"publicNoticeTitle",label:"标题"},
				{name:"noticeFiles", formatter:formatterAttachFile, label:'附件',sortable:false, width:150, align:'center'},
				{name:"noticeEditor",index:"noticeEditor",label:"编辑者",align:'center'},
				{name:"editorDate",index:"editorDate",label:"编辑时间",align:'center'},
				{name:"organization.orgName",index:"organization.orgName",label:"所属区域",sortable:false,width:100},
				{name:"isoverdate",index:"isoverdate",label:"是否已过显示有限期",width:150,align:'center'}
				]
 };
	$("#publicNoticeList").jqGridFunction({
		mtype:'post',
		datatype: "local",
		colModel: gridOption.colModel,
		loadComplete:showDailyLogAttachFile,
		ondblClickRow:function(data){
			doubleClickTable(data);
		}
	});
	jQuery("#publicNoticeList").jqGrid('setFrozenColumns');
	$("#publicNoticeList").setGridParam({
		url:"${path}/workBench/noticeManage/findNoticeForHistoryByOrgCode.action",
		datatype: "json",
		page:1
	});
	$("#publicNoticeList").trigger("reloadGrid");
	$("#publicNoticeList").closest(".ui-jqgrid-bdiv").css({ "height" : "328" });
	 function viewDialog(id){
			$("#publicNoticeDialog").createDialog({
				width:800,
				height:480,
				title:"查看通知通告信息",
				url:"${path}/workBench/noticeManage/dispatchOperate.action?publicNotice.id="+id,
				buttons: {
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
	}

	function doubleClickTable(data){
		var selectedId = $("#publicNoticeList").getGridParam("selrow");
		var rowData = $("#publicNoticeList").getRowData(selectedId);
		var id = rowData.id;
		if(id==null){
			 return;
		}
		viewDialog(id);
	}
});

</script>

