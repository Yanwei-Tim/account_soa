function reEmphasise_restraint(dialogListId, selectedId) {
	var params="";
	for(var i=0;i<selectedId.length;i++){
		var data=$("#"+dialogListId).getRowData(selectedId[i])
		if(i==0)
			params += "idCardNos="+$(data.idCardNo).text()+"&orgId="+data["organization.id"];
		else params += "&idCardNos="+$(data.idCardNo).text();
	}
	$.ajax({
		url:PATH+'/commonPopulation/commonPopulationManage/isActualPopulationDeathOrEmphasisByIdCardNoAndOrgId.action?'+params,
		success:function(data){
			if(!data){
				$.messageBox({message:"选中的人员信息在实有人口中是注销或死亡状态，不能重新关注，请确认勾选的数据!",level:"error"});
				return;
			}
			doReEmphasise(selectedId);
		}
	});
}

