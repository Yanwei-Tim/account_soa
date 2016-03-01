function isUpdate1(list){
	$("#update").buttonDisable();
	$("#delete").buttonDisable();
	var rowid = list.jqGrid('getGridParam','selrow');
	var row = list.jqGrid('getRowData',rowid);
	var isupdate1 =  false;
	$.ajax({
		async: false,
		url: "/sentiments/sentimentsManage/isUpdate1.action",
		data:{
			"issueNew.id":row.issueId
		},
		success:function(isOk){
			isupdate1= isOk;
		}
	});
	if(isupdate1){
		$("#update").buttonEnable();
		$("#delete").buttonEnable();
	}
}