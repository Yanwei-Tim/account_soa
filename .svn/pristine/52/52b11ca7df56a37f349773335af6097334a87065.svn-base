
function updateIssue(){
	var selectedId = $("#issueList").getGridParam("selrow");
	if(selectedId == null){
 		return;
	}
	var rowData = $("#issueList").getRowData(selectedId);
	$("#issueDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title: '修改事件处理信息',
		url:'/issue/issueManage/dispatch.action?issueMode=edit&issueNew.id='+rowData.issueId+'&issueLogId='+rowData.issueLogId+'&organization.id='+$("#currentOrgId").val(),
		buttons: {
	   		"保存" : function(event){
   				$("#issueMaintainForm").submit();
   			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

function editIssue(keyId){
	$("#issueDialog").createDialog({
		width: issueDialogWidth,
		height: issueDialogHeight,
		title: '修改事件处理信息',
		url:'/issues/issueManage/dispatch.action?mode=edit&keyId='+keyId,
		buttons: {
	   		"保存" : function(event){
				$("#issueMaintainForm").submit();
   			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}


function deleteIssue(){
	var	selectedId = $("#issueList").getGridParam("selrow");
	var rowData = $("#issueList").getRowData(selectedId);
	if(null == selectedId){
		return;
	}
	$.ajax({
		url:"/issue/issueManage/deleteIssueById.action",
		data:{
			"issueNew.id":rowData.issueId
		},
		success:function(responseData){
			if (responseData>0){
			    $.messageBox({message:"已经成功删除该事件处理信息!"});
			    $(".message").click();
			}else{
				$.messageBox({message:"找不到要删除的事件处理信息，不能删除!"});
			}
		}
	});
}

function removeIssue(keyId){
	$.ajax({
		url:"/issues/issueManage/deleteIssue.action",
		data:{
			"keyId":keyId
		},
		success:function(responseData){
			if (responseData==true){
				$.messageBox({message:"已经成功删除该事件处理信息!"});
				reloadIssue();
			    $(".message").click();
			}else{
				$.messageBox({message:"找不到要删除的事件处理信息，不能删除!"});
			}
		}
	});
}

function removeIssueByIssueId(issueId){
	$.ajax({
		url:"/issues/issueManage/deleteIssue.action",
		data:{
			"keyId":issueId
		},
		success:function(responseData){
			if (responseData==true){
			    $.messageBox({message:"已经成功删除该事件处理信息!"});
			    reloadIssue();
			    $(".message").click();
			}else{
				$.messageBox({message:"找不到要删除的事件处理信息，不能删除!"});
			}
		}
	});
}

function settingIssueHistorical(keyId,type){
	if (!isNullObject(keyId)){
		$.ajax({
			url:'/issues/issueManage/dealIssue.action',
			data:{
				"keyId":keyId,
				"dealCode":type
			},
			success:function(data){
				if (data.issueStepId>0){			
					$.messageBox({message:"成功设置该事件的历史遗留状态!"});
					reloadIssue();
	                $(".message").click();
				}else{
				 	$.messageBox({message:data});
				}
			}
		});
	}
}

function historicalIssue(){
	var selectedId= $("#issueList").getGridParam("selrow");
	if(null ==selectedId ){
		return;
	}
		$.ajax({
		url:'/issue/issueManage/historicalIssue.action',
		data:{
			"step.id":selectedId
		},
		success:function(data){
			if (data>0){			
				$.messageBox({message:"该事件处理已设置为历史遗留!"});
                $(".message").click();
			}else{
			 	$.messageBox({message:data});
			}
		}
	});
}

function issueRightHeight(){

	function getHeight(){
		var centerLayerHeight=$(".ui-layout-center").height();
		
		//var breadHeight=$(".thisCrumbs").height();
		//var uiTabsNavHeight=$(".ui-tabs-nav").height();
		//var uiTabsNavHeight=$(".ui-corner-all").height();
		//var issueTitleHeight=$(".issueLeft .issueList").height();
		//var PagerHeight=$(".issuePagination").height();
		//var footerHeight=$(".ui-layout-south").height();
		
		if($(".ui-tabs-nav").height()==null){
			
			$(".issueLeft").height(centerLayerHeight-120);      //no ui-tabs-nav
			$(".issueLeft .issueList").height(centerLayerHeight-160);
			$(".issueRight").height(centerLayerHeight-88);
			
		}else{
			$(".issueLeft").height(centerLayerHeight-120);                //has ui-tabs-nav
			$(".issueLeft .issueList").height(centerLayerHeight-200);
			$(".issueRight").height(centerLayerHeight-118);
			
		}
	}
	getHeight();
	
	$(window).resize(function(){
		clearTimeout(window._issueRightTimer);
		window._issueRightTimer=setTimeout(function(){getHeight()},300);
	})
}

function mouseOverOrOut(){
	$(".issueLeft div").delegate(".issueList li","mouseover",function(){
		$(this).addClass("hoverCur").siblings().removeClass("hoverCur");
	});

	$(".issueLeft div").delegate(".issueList li","mouseout",function(){
		$(this).removeClass("hoverCur");
	});
}

