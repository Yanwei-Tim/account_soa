function isComment(el, options, rowData){
	if(rowData.commentNums > 0){
		return "<img src='"+RESOURCE_PATH+"/resource/js/ui/images/normalcard.gif'>";
	}else{
		return "";
	}
}


function myLogstatistics(){
	$.ajax({
		async: false,
		url: PATH+"/peopleLog/commentLogManage/getStatisticsByOrgId.action",
		success:function(data){
			$("#countNum").html(data.countNum);
			$("#commentNum").html(data.commentNum);
		}
	});
}
function subLogstatistics(){
	$.ajax({
		async: false,
		url: PATH+"/peopleLog/commentLogManage/getStatisticsByOrgCode.action",
		success:function(data){
			$("#countSubMeNum").html(data.countSubMeNum);
			$("#commentSubMeNum").html(data.commentSubMeNum);
			$("#countSubAllNum").html(data.countSubAllNum);
			$("#commentSubAllNum").html(data.commentSubAllNum)
		}
	});
}
function doubleClickTable(){
    var selectedId = $("#peopleLogList").getGridParam("selrow");
	var rowData = $("#peopleLogList").getRowData(selectedId);
	var id = rowData.id;
	if(id==null){
		 return;
	}
	$("#logViewDialog").createDialog({
		width:600,
		height:450,
		title:"查看日志信息",
		url:PATH+"/peopleLog/commentLogManage/dispatchOperate.action?mode=view&peopleLog.id="+id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
