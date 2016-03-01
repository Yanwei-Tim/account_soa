function getData(){
	var dataObject={
			id:($(".issueList li.current").attr("id")),
			issueStepId:($(".issueList li.current").attr("issueStepId")),
			supervisionState:($(".issueList li.current").attr("supervisionState")),
			issueLogId:($(".issueList li.current").attr("issueLogId"))
	};
	return dataObject;
}

function setSuperviseButtons(){
	resetSuperviseButtonsState();
}

function resetSuperviseButtonsState(){
	//var	selectedId = $("#issueList").getGridParam("selrow");
	var	selectedId = $(".issueList li.current").attr("issueStepId");
	var hasSelected=!isNullObject(selectedId);
	resetCancelSuperviseButtonState(hasSelected);
	resetRedSuperviseButtonState(hasSelected);
	resetYellowSuperviseButtonState(hasSelected);
	resetNormalSuperviseButtonState(hasSelected);
}

function resetRedSuperviseButtonState(hasSelected){
	if (hasSelected && !redSupervised()){
		$("#redCardIssue").buttonEnable();
	}else{
		$("#redCardIssue").buttonDisable();
	}
}

function resetYellowSuperviseButtonState(hasSelected){
	if (hasSelected && !redSupervised() && !yellowSupervised()){
		$("#yellowCardIssue").buttonEnable();
	}else{
		$("#yellowCardIssue").buttonDisable();
	}
}

function resetNormalSuperviseButtonState(hasSelected){
	if (hasSelected && !redSupervised() && !yellowSupervised() && !normalSupervised()){
		$("#normalIssue").buttonEnable();
	}else{
		$("#normalIssue").buttonDisable();
	}
}

function resetCancelSuperviseButtonState(hasSelected){
	if (hasSelected && (normalSupervised() || yellowSupervised() || redSupervised())){
		$("#cancleSupervise").buttonEnable();
	}else{
		$("#cancleSupervise").buttonDisable();
	}
}

function redSupervised(){
	//var selectedIssue=$("#issueList").getRowData($("#issueList").getGridParam("selrow"));
	var selectedIssue = getData();
	return !isNullObject(selectedIssue) && (selectedIssue.supervisionState == 200 || (selectedIssue.supervisionState!=undefined && selectedIssue.supervisionState.indexOf("redcard.gif")!=-1));
}

function yellowSupervised(){
	//var selectedIssue=$("#issueList").getRowData($("#issueList").getGridParam("selrow"));
	var selectedIssue = getData();
	return !isNullObject(selectedIssue) && (selectedIssue.supervisionState == 100 || (selectedIssue.supervisionState!=undefined && selectedIssue.supervisionState.indexOf("yellowcard.gif")!=-1));
}

function normalSupervised(){
	//var selectedIssue=$("#issueList").getRowData($("#issueList").getGridParam("selrow"));
	var selectedIssue = getData();
	return !isNullObject(selectedIssue) && (selectedIssue.supervisionState == 1 || (selectedIssue.supervisionState!=undefined && selectedIssue.supervisionState.indexOf("normalcard.gif")!=-1));
}

function superviseIssue(keyId,typeCode){
	if (!isNullObject(keyId)){
		$("#issueDialog").createDialog({
	        width:550,
	        height:350,
	        title:'督办',
			url:'/issues/issueManage/dispatchDeal.action?dealCode='+typeCode+'&keyId='+keyId,
	        buttons: {
	            "确定" : function(event){
	                $("#superviseIssueForm").submit();
	            },
	            "关闭" : function(){
	                $(this).dialog("close");
	            }
	        }
	    });
	}
}

function cancelSupervise(keyId,typeCode){
	if (!isNullObject(keyId)){
		$.ajax({
			url:'/issues/issueManage/dealIssue.action',
	        data:{
	            "dealCode":typeCode,
	            "keyId":keyId
	        },
	        success:function(data){
	            if(data.issueStepId>0){
					$("#issueList").setRowData(keyId,data);
	                $.messageBox({message:"成功取消对该部门的督办!"});
	                reloadIssue();
	                resetSuperviseButtonsState();
	            }else{
	                $.messageBox({message:data});
	            }
	        }
	    });
	}
	
}

function normalIssue(){
	//var selectedIssue=$("#issueList").getRowData($("#issueList").getGridParam("selrow"));
	var selectedIssue = getData();
	if(isNullObject(selectedIssue) || isNullObject(selectedIssue.issueLogId)){
		return;
	}
	$("#issueDialog").createDialog({
        width:550,
        height:350,
        title:'普通督办',
		url:'/issue/issueBusinessManage/dispatchDeal.action?dealType=81&stepId='+selectedIssue.issueLogId,
        buttons: {
            "确定" : function(event){
                $("#superviseIssueForm").submit();
            },
            "关闭" : function(){
                $(this).dialog("close");
            }
        }
    });
}

function yellowCardIssue(){
	//var selectedIssue=$("#issueList").getRowData($("#issueList").getGridParam("selrow"));
	var selectedIssue = getData();
	if(isNullObject(selectedIssue) || isNullObject(selectedIssue.issueLogId)){
		return;
	}
    $("#issueDialog").createDialog({
        width:550,
        height:350,
        title:'黄牌督办',
		url:'/issue/issueBusinessManage/dispatchDeal.action?dealType=83&stepId='+selectedIssue.issueLogId,
        buttons: {
            "确定" : function(event){
                $("#superviseIssueForm").submit();
            },
            "关闭" : function(){
                $(this).dialog("close");
            }
        }
    });
}

function redCardIssue(){
	//var selectedIssue=$("#issueList").getRowData($("#issueList").getGridParam("selrow"));
	var selectedIssue = getData();
	if(isNullObject(selectedIssue) || isNullObject(selectedIssue.issueLogId)){
		return;
	}
    $("#issueDialog").createDialog({
        width:550,
        height:350,
        title:'红牌督办',
		url:'/issue/issueBusinessManage/dispatchDeal.action?dealType=86&stepId='+selectedIssue.issueLogId,
        buttons: {
            "确定" : function(event){
                $("#superviseIssueForm").submit();
            },
            "关闭" : function(){
                $(this).dialog("close");
            }
        }
    });
}


function cancleSuperviseIssue(){
	//var selectedIssue=$("#issueList").getRowData($("#issueList").getGridParam("selrow"));
	var selectedIssue = getData();
	if(isNullObject(selectedIssue) || isNullObject(selectedIssue.issueLogId)){
		return;
	}
	var oldIssueLogId = selectedIssue.issueLogId;
	$.ajax({
		url:'/issue/issueBusinessManage/cancelSupervise.action?stepId='+selectedIssue.issueLogId,
        data:{
            "issueLogNew.issue.id":selectedIssue.issueId,
            "issueLogNew.id":selectedIssue.issueLogId
        },
        success:function(data){
            if(data.issueLogId>0){
                $.messageBox({message:"对此处理过程的督办取消成功!"});
                resetSuperviseButtonsState();
            }else{
                $.messageBox({message:data});
            }
        }
    });
}
