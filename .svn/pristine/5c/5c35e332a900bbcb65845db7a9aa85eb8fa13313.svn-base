function isUpdate(list){
	$("#update").buttonDisable();
	$("#delete").buttonDisable();
	var rowid = list.jqGrid('getGridParam','selrow');
	var row = list.jqGrid('getRowData',rowid);	
	var isupdate =  false;
	$.ajax({
		async: false,
		url: "/sentiments/sentimentsManage/isUpdate.action",
		data:{
			"issueNew.id":row.issueId
		},
		success:function(isOk){
			isupdate= isOk;
		}
	});
	if(isupdate){
		$("#update").buttonEnable();
		$("#delete").buttonEnable();
	}
}