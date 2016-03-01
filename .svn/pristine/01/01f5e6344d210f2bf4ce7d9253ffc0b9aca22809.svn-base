
function setUrgentButton(){
	resetUrgentButtonState();
}

function resetUrgentButton(rowSelected,keyId,urgentCode,cancelCode){
	if (rowSelected && $("#command").isButtonEnabled()){
		if (isNullObject(keyId)){
			$("#urgent").buttonDisable();
		}else{
			$("#urgent").buttonEnable();
			if(isUrgented($("#issueList"))){
				bindCancelIssueUrgentEvent(keyId,urgentCode,cancelCode);
			}else{
				bindUrgentIssueEvent(keyId,urgentCode,cancelCode);
			}
		}
	}
}

function bindUrgentIssueEvent(keyId,urgentCode,cancelCode){
	var urgenButton=$("#urgent");
	if (urgenButton==null || typeof(urgenButton)=="undefined"){
		return;
	}
	urgenButton.html('<span><strong class="ui-ico-jiaji"></strong>加急</span>');
	urgenButton.unbind("click");
	urgenButton.click(function(event){
		$("#issueDialog").createDialog({
			width:600,
			height:400,
			title:'加急',
			url:'/issues/issueManage/dispatchDeal.action?dealCode='+urgentCode+'&keyId='+keyId,
			buttons: {
				"确定" : function(event){
					$("#singleContentDealForm").submit();
					resetUrgentButton(true,keyId,urgentCode,cancelCode);
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
}

function bindCancelIssueUrgentEvent(keyId,urgentCode,cancelCode){
	var urgenButton=$("#urgent");
	if (isNullObject(urgenButton)){
		return;
	}
	urgenButton.html('<span><strong class="ui-ico-qxjj"></strong>取消加急</span>');
	urgenButton.unbind("click");
	urgenButton.click(function(event){
		if (!isNullObject(keyId)){
			$.confirm({
				title:"系统提示",
				message:"是否确定要取消对该事件处理的加急!",
				width:400,
				okFunc:function(){
					$.ajax({
						url:"/issues/issueManage/dealIssue.action",
						data:{
							"keyId":keyId,
							"dealCode":cancelCode
						},
						success:function(data){
							if (data.issueStepId){
								$.messageBox({message:"已经成功取消该事件处理的加急!"});
								resetUrgentButton(true,keyId,urgentCode,cancelCode);
							}else{
								$.messageBox({message:data,level:"error"});
								resetUrgentButton(false,keyId,urgentCode,cancelCode);
							}
							reloadIssue();
						}
					});
				}
			});
		}
	});
}

function resetUrgentButtonState(){
	var	selectedId = $(".issueList li.current").attr("issueStepId");
	if (isNullObject(selectedId)){
		$("#urgent").buttonDisable();
	}else{
		$("#urgent").buttonEnable();
		if(isUrgented($("#issueList"))){
			bindCancelUrgentEvent();
		}else{
			bindUrgentEvent();
		}
	}
}

function isUrgented(listGrid){
	var selectedIssue = {urgent:$(".issueList li.current").attr("urgent")};
	return selectedIssue.urgent == 1 || (selectedIssue.urgent!=undefined && selectedIssue.urgent.indexOf("immediate.gif")!=-1);
}

function bindCancelUrgentEvent(){
	var urgenButton=$("#urgent");
	if (isNullObject(urgenButton)){
		return;
	}
	urgenButton.html('<span><strong class="ui-ico-qxjj"></strong>取消加急</span>');
	urgenButton.unbind("click");
	urgenButton.click(function(event){
		var	selectedId = $(".issueList li.current").attr("issueStepId");
		if (isNullObject(selectedId)){
			return;
		}
		$.confirm({
			title:"系统提示",
			message:"是否确定要取消对该事件处理的加急!",
			width:400,
			okFunc:cancelUrgent
		});
	});
}

function bindUrgentEvent(){
	var urgenButton=$("#urgent");
	if (urgenButton==null || typeof(urgenButton)=="undefined"){
		return;
	}
	urgenButton.html('<span><strong class="ui-ico-jiaji"></strong>加急</span>');
	urgenButton.unbind("click");
	urgenButton.click(function(event){
		urgentIssue();
	});
}

function cancelUrgent(){
	var	selectedId = $(".issueList li.current").attr("issueStepId");
	if (isNullObject(selectedId) || $("#urgent").attr("disabled")=="true"){
		return;
	}
	$.ajax({
		url:"/issue/issueManage/cancelUrgentIssue.action",
		data:{
			"step.id":selectedId
		},
		success:function(data){
			if (data.issueLogId){
			    $.messageBox({message:"已经成功取消该事件处理的加急!"});
				alert(data.issueLogId)
			    resetUrgentButtonState();
			}else{
				$.messageBox({message:data,level:"error"});
				resetUrgentButtonState();
			}
			reloadIssue();
		}
	});
}

function urgentIssue(){
	var	selectedId = $(".issueList li.current").attr("issueStepId");
	if (isNullObject(selectedId) || $("#urgent").attr("disabled")=="true"){
		return;
	}
	$("#issueDialog").createDialog({
		width:690,
		height:430,
		title:'加急',
		url:'/issue/issueManage/dispatch.action?mode=urgent&step.id='+selectedId,
		buttons: {
			"确定" : function(event){
				$("#urgentIssueForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}