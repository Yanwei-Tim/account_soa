//jquery ui datepicker plug
$.datepicker._gotoToday=function(id) {
	var target = $(id);
	var inst = this._getInst(target[0]);
	if (this._get(inst, 'gotoCurrent') && inst.currentDay) {
		inst.selectedDay = inst.currentDay;
		inst.drawMonth = inst.selectedMonth = inst.currentMonth;
		inst.drawYear = inst.selectedYear = inst.currentYear;
	}
	else {
		var date = new Date();
		inst.selectedDay = date.getDate();
		inst.drawMonth = inst.selectedMonth = date.getMonth();
		inst.drawYear = inst.selectedYear = date.getFullYear();
		this._selectDate(id, this._formatDate(inst));
	}
	this._notifyChange(inst);
	this._adjustDate(target);
}

$.ajaxSetup({
	cache:false
});
jQuery.extend( {
	selectMenu:function(selectedId){
		$("#menu a").removeClass("select");
		$("#"+selectedId).addClass("select");
	}
});

$(document).ready(function(){
	$("#shouldLogin").ajaxComplete(function(event, xhr, ajaxOptions){
		 if(xhr&&xhr.readyState == 4){
			 if(xhr.status==200){
				if(xhr.responseText!=undefined && xhr.responseText.indexOf("document.location.href='/login.jsp")>=0){
					document.location.href='/login.jsp?errorMessage=您的帐号已失效或者已经在别的地方登录!';
				}
				startDate=new Date();
				$.loadingComp("close");
			 }
		}
	});
	$("#currentOrgId").live("change",function(){
		if(typeof(onOrgChanged) != 'undefined'){
			onOrgChanged.call(null,this.value,$("#currentOrgId option:selected").attr("isGrid")=="true");
		}
	});
	$("#currentIncidentTypeId").live("change",function(){
		if(typeof(onIncidentTypeChanged) != 'undefined'){
			onIncidentTypeChanged.call(null,node.attributes.id);
		}
	});
	
	
	$("a:disabled").unbind().addClass("huise");
});

///////////////////////////////////////////////////
var placeName = '';
var tabName = '';

function getCurrentOrgId(){
	var currentOrgId=$("#currentOrgId").val();
	if(currentOrgId){
		return currentOrgId;
	}else{
		return "";
	}
}
function getCurrentIncidentId(){
	var incidentTypeId=	$("#currentIncidentTypeId").val();
	if(incidentTypeId){
		return incidentTypeId;
	}else{
		return "";
	}
}

function getCurrentSelectedOrgId(){
	var currentOrgId=$("#currentIncidentTypeId").val();
	if(currentOrgId){
		return currentOrgId;
	}else{
		return "";
	}
}

function getCurrentTreeOrgId(){
	var currentOrgId=$("input[id='currentOrgId']").val();
	if(currentOrgId){
		return currentOrgId;
	}else{
		return "";
	}
}

function isNullObject(obj){
	if (obj==null || typeof(obj)=="undefined"){
		return true;
	}
	return false;
}

function popLoginDialog(){
	$.createDialog({
		id:"login-dlg",
		url:PATH+'/loginDLG.jsp?date='+new Date(),
		title:"登录超时，请重新登录",
		width:450,
		height:300,
		closeOnEscape:false,
		closeText:false,
		resizable: false
	});
	var cookie_skin = $.cookie("cssSkin");
	switch (cookie_skin) {
		case "default":
			$(".login").parent().css("background","#EAF4FD");
			break;
		case "blue":
			$(".login").parent().css("background","#EEF2FB");
			break;
		case "green":
			$(".login").parent().css("background","#F8FBEC");
			break;
	}
	$(".ui-dialog-titlebar-close").click(function(){document.location.href="/"});
}

function proccessLoginResult(result,callback){
	if(result && result.indexOf("notHasLogin")>=0){

	}else{
		callback();
	}
}

function getDate() {
	var todayDate = new Date();
	var date = todayDate.getDate();
	var month = todayDate.getMonth() + 1;
	var year = todayDate.getFullYear();
	return year + "-" + (month > 10 ? month : "0"+month)  + "-" + (date > 10 ? date : "0"+date);
}

function compareTwoDates(endDate, startDate) {
	return Date.parse(endDate) <= Date.parse(endDate);
}

function compareDateWithNow(currentDate) {
	return Date.parse(currentDate) >= Date.parse(getDate());
}

function asyncMiddleLevelOpen(srcObj, url) {
	if($(srcObj).hasClass("click")){
		return;
	}
	$.loadingComp("open");
	if($(".hover").text()=="重点场所" || $(".hover").text() =="重点人员" || $(".hover").text() =="出租房"  || $(".hover").text() =="规上企业"  ||$(".hover").text() =="社会组织" ){
		placeName = $(".hover").text();
		$("#path-button").show();
	}else{
		$("#path-button").hide();
	}
	document.title = $(srcObj).text();
	$("#currentPosition").text($(srcObj).text());
	$("#parentPosition").text($(srcObj).parent().parent().prev().text());
	$("#baseLine").nextAll(":not(.ui-autocomplete):not('.ui-datepicker')").remove();
	$("#baseLine").nextAll(":not(.ui-autocomplete):not('.ui-datepicker'):hidden").remove();
	$("#contentDiv").html("");
	$("#loading").css("display","block");
	$(".content").removeAttr("style");
	$.ajax({
		url : url,
		cache: false,
		success : function(result) {
			proccessLoginResult(result,function(){hideLoading();$("#contentDiv").html(result);});
		},
		error:function(err){
			$(".dialog_loading").hide();
			$.messageBox({message:'系统出错，请刷新页面重试',level:'error'});
		}
	});

	$(".subnavbar li>a").removeClass("click");
	$(".popMenu div li>a").removeClass("click");
	$(srcObj).addClass("click");
}

function asyncOpen(srcObj, url) {
	if($(srcObj).hasClass("accordion-click")){
		return;
	}
	$.loadingComp("open");
	document.title = $(srcObj).text();
	$("#currentPosition").text($(srcObj).text());
	$("#parentPosition").text($(srcObj).parent().parent().prev().text());
	$("#baseLine").nextAll().remove();
	$("#baseLine").nextAll(":hidden").remove();
	$("#contentDiv").html("");
	$("#loading").css("display","block");
	$(".content").removeAttr("style");
	$.ajax({
		url : url,
		cache: false,
		success : function(result) {
			proccessLoginResult(result,function(){hideLoading();$("#contentDiv").html(result);});
		},
		error:function(err){
			$(".dialog_loading").hide();
			$.messageBox({message:'系统出错，请刷新页面重试',level:'error'});
		}
	});
	$("#workaccordion div dl dd>a").removeClass("accordion-click");
	$("#accordion div ul li>a").removeClass("accordion-click");
	$(srcObj).addClass("accordion-click");
}
var currentState;
var treeState="treeState";
var normalState="normalState";
function issueAsyncOpen(srcObj, url,currentStateParam) {
	currentState=normalState;
	if($(srcObj).hasClass("accordion-click")){
		return;
	}
	document.title = $(srcObj).text();
	$("#currentPosition").text($(srcObj).text());
	$("#parentPosition").text($(srcObj).parent().parent().prev().text());
	$("#baseLine").nextAll().remove();
	$("#baseLine").nextAll(":hidden").remove();
	$("#contentDiv").html("");
	$("#loading").css("display","block");
	$("#workaccordion > div > dl > dd>a").removeClass("accordion-click");
	$(srcObj).addClass("accordion-click");
	if(currentStateParam==normalState){
		$(".submenu").hide();
		$(".path").show();
		$.ajax({
			url : url,
			cache: false,
			success : function(result) {
				proccessLoginResult(result,function(){hideLoading();$("#contentDiv").html(result);});
			},
			error:function(err){
				$(".dialog_loading").hide();
				$.messageBox({message:'系统出错，请刷新页面重试',level:'error'});
			}
		});
	}else{
		$(".path").hide();
		$(".submenu").show();
		$(".submenu a").removeClass("click");
		$(".submenu ul:first li:first").click();
	}
}

function hideLoading(){
	$("#loading").css("display","none");
}
var Convert = {
    StringToJSON: function(str) {
		return eval('(' + str + ')');
    },
    ToJSONString: function(obj) {
        switch(typeof(obj))
        {
            case 'object':
                var ret = [];
                if (obj instanceof Array)
                {
                    for (var i = 0, len = obj.length; i < len; i++)
                    {
                        ret.push(Convert.ToJSONString(obj[i]));
                    }
                    return '[' + ret.join(',') + ']';
                }
                else if (obj instanceof RegExp)
                {
                    return obj.toString();
                }
                else
                {
                    for (var a in obj)
                    {
                        ret.push(a + ':' + Convert.ToJSONString(obj[a]));
                    }
                    return '{' + ret.join(',') + '}';
                }
            case 'function':
                return 'function() {}';
            case 'number':
                return obj.toString();
            case 'string':
                return "\"" + obj.replace(/(\\|\")/g, "\\$1").replace(/\n|\r|\t/g, function(a) {return ("\n"==a)?"\\n":("\r"==a)?"\\r":("\t"==a)?"\\t":"";}) + "\"";
            case 'boolean':
                return obj.toString();
            default:
                return obj.toString();

        }
    }
};


var menuFunction={
		commandCenterManagement:function(selectedorgId,menuType){
			menuBoxShow();
			$.selectMenu("commandCenterManagement-menu");
			$(".submenu").hide();
			$(".ui-layout-west").load("/commandCenter/commandCenterManagementSideBar.jsp?urlflag="+menuType,function(){
				$(".path").show();
				$(".path").load("/commandCenter/commandCenterManagementTitle.jsp",function(){
					$("#currentPosition").text("信息列表");
				});
				$("#commandCenterManagement").click();
				$.dialogLoading("close");
			});
		},
		gridIntroductionInformation:function(selectedorgId,menuType){
			$.selectMenu("gridIntroductionInformation-menu");
			if(!lowLevel){
			    function afterLoad(){
			    	$(".path").hide();
			    	$(".submenu").show();
			    	$(".subnav").load("/sysadmin/menuManage/getHighLevelBaseInfoMenuList.action?ename=gridIntroductionInformationManage&urlflag="+menuType,function(){$.dialogLoading("close");});
			    }
			    baseTreeShow(true,afterLoad);
			}else{
				menuBoxShow();
				$(".submenu").hide();
				$(".path").show();
				$(".path").load("/baseinfo/lowLevelTitle.jsp",function(){
					$(".ui-layout-west").load("/sysadmin/menuManage/getLowLevelBaseInfoMenuList.action?ename=gridIntroductionInformationManage&urlflag="+menuType,function(){
						$.dialogLoading("close");
					});
				});
			}
		},		
		actualPopulation:function(selectedorgId,menuType){
			$.selectMenu("actualPopulation-menu");
			if(!lowLevel){
				function afterLoad(){
					$(".path").hide();
					$(".submenu").show();
					$(".subnav").load("/sysadmin/menuManage/getHighLevelBaseInfoMenuList.action?ename=actualPopulationManagement&urlflag="+menuType,function(){$.dialogLoading("close");});
				}
				baseTreeShow(true,afterLoad);
			}else{
				menuBoxShow();
				$(".submenu").hide();
				$(".path").show();
				$(".path").load("/baseinfo/lowLevelTitle.jsp",function(){
					$(".ui-layout-west").load("/sysadmin/menuManage/getLowLevelBaseInfoMenuList.action?ename=actualPopulationManagement&urlflag="+menuType,function(){
						$.dialogLoading("close");
					});
				});
			}
		},
		comprehensiveStability:function(selectedorgId,menuType){
			$.selectMenu("comprehensiveStability-menu");
			if(!lowLevel){
				function afterLoad(){
					$(".path").hide();
					$(".submenu").show();
					$(".subnav").load("/sysadmin/menuManage/getHighLevelBaseInfoMenuList.action?ename=comprehensiveStability&urlflag="+menuType,function(){$.dialogLoading("close");});
				}
				baseTreeShow(true,afterLoad);
			}else{
				menuBoxShow();
				$(".submenu").hide();
				$(".path").show();
				$(".path").load("/baseinfo/lowLevelTitle.jsp",function(){
					$(".ui-layout-west").load("/sysadmin/menuManage/getLowLevelBaseInfoMenuList.action?ename=comprehensiveStability&urlflag="+menuType,function(){
						$.dialogLoading("close");
					});
				});
			}
		},
		birthInformation:function(selectedorgId,menuType){
			$.selectMenu("birthInformation-menu");
			if(!lowLevel){
				function afterLoad(){
					$(".path").hide();
					$(".submenu").show();
					$(".subnav").load("/sysadmin/menuManage/getHighLevelBaseInfoMenuList.action?ename=birthInformationManage&urlflag="+menuType,function(){$.dialogLoading("close");});
				}
				baseTreeShow(true,afterLoad);
			}else{
				menuBoxShow();
				$(".submenu").hide();
				$(".path").show();
				$(".path").load("/baseinfo/lowLevelTitle.jsp",function(){
					$(".ui-layout-west").load("/sysadmin/menuManage/getLowLevelBaseInfoMenuList.action?ename=birthInformationManage&urlflag="+menuType,function(){
						$.dialogLoading("close");
					});
				});
			}
		},
		civiAdministration:function(selectedorgId,menuType){
			$.selectMenu("civiAdministration-menu");
			if(!lowLevel){
				function afterLoad(){
					$(".path").hide();
					$(".submenu").show();
					$(".subnav").load("/sysadmin/menuManage/getHighLevelBaseInfoMenuList.action?ename=civiAdministrationManagement&urlflag="+menuType,function(){$.dialogLoading("close");});
				}
				baseTreeShow(true,afterLoad);
			}else{
				menuBoxShow();
				$(".submenu").hide();
				$(".path").show();
				$(".path").load("/baseinfo/lowLevelTitle.jsp",function(){
					$(".ui-layout-west").load("/sysadmin/menuManage/getLowLevelBaseInfoMenuList.action?ename=civiAdministrationManagement&urlflag="+menuType,function(){
						$.dialogLoading("close");
					});
				});
			}
		},
		houseInformation:function(selectedorgId,menuType){
			$.selectMenu("houseInformation-menu");
			if(!lowLevel){
				function afterLoad(){
					$(".path").hide();
					$(".submenu").show();
					$(".subnav").load("/sysadmin/menuManage/getHighLevelBaseInfoMenuList.action?ename=houseInformationManage&urlflag="+menuType,function(){$.dialogLoading("close");});
				}
				baseTreeShow(true,afterLoad);
			}else{
				menuBoxShow();
				$(".submenu").hide();
				$(".path").show();
				$(".path").load("/baseinfo/lowLevelTitle.jsp",function(){
					$(".ui-layout-west").load("/sysadmin/menuManage/getLowLevelBaseInfoMenuList.action?ename=houseInformationManage&urlflag="+menuType,function(){
						$.dialogLoading("close");
					});
				});
			}
		},
		actualCompany:function(selectedorgId,menuType){
			$.selectMenu("actualCompany-menu");
			if(!lowLevel){
				function afterLoad(){
					$(".path").hide();
					$(".submenu").show();
					$(".subnav").load("/sysadmin/menuManage/getHighLevelBaseInfoMenuList.action?ename=actualCompanyMenuManage&urlflag="+menuType,function(){$.dialogLoading("close");});
				}
				baseTreeShow(true,afterLoad);
			}else{
				menuBoxShow();
				$(".submenu").hide();
				$(".path").show();
				$(".path").load("/baseinfo/lowLevelTitle.jsp",function(){
					$(".ui-layout-west").load("/sysadmin/menuManage/getLowLevelBaseInfoMenuList.action?ename=actualCompanyMenuManage&urlflag="+menuType,function(){
						$.dialogLoading("close");
					});
				});
			}
		},
		partyConstruction:function(selectedorgId,menuType){
			$.selectMenu("partyConstruction-menu");
			if(!lowLevel){
				function afterLoad(){
					$(".path").hide();
					$(".submenu").show();
					$(".subnav").load("/sysadmin/menuManage/getHighLevelBaseInfoMenuList.action?ename=party_construction_Management&urlflag="+menuType,function(){$.dialogLoading("close");});
				}
				baseTreeShow(true,afterLoad);
			}else{
				menuBoxShow();
				$(".submenu").hide();
				$(".path").show();
				$(".path").load("/baseinfo/lowLevelTitle.jsp",function(){
					$(".ui-layout-west").load("/sysadmin/menuManage/getLowLevelBaseInfoMenuList.action?ename=party_construction_Management&urlflag="+menuType,function(){
						$.dialogLoading("close");
					});
				});
			}
		},
		twoNewOrganization:function(selectedorgId,menuType){
			$.selectMenu("twoNewOrganization-menu");
			if(!lowLevel){
				function afterLoad(){
					$(".path").hide();
					$(".submenu").show();
					$(".subnav").load("/sysadmin/menuManage/getHighLevelBaseInfoMenuList.action?ename=twoNewOrganizationManagement&urlflag="+menuType,function(){$.dialogLoading("close");});
				}
				baseTreeShow(true,afterLoad);
			}else{
				menuBoxShow();
				$(".submenu").hide();
				$(".path").show();
				$(".path").load("/baseinfo/lowLevelTitle.jsp",function(){
					$(".ui-layout-west").load("/sysadmin/menuManage/getLowLevelBaseInfoMenuList.action?ename=twoNewOrganizationManagement&urlflag="+menuType,function(){
						$.dialogLoading("close");
					});
				});
			}
		},
		basicInformation:function(selectedorgId,menuType){
			$.selectMenu("basicInformation-menu");
			if(!lowLevel){
				function afterLoad(){
					$(".path").hide();
					$(".submenu").show();
					$(".subnav").load("/sysadmin/menuManage/getHighLevelBaseInfoMenuList.action?ename=basicInformation&urlflag="+menuType,function(){$.dialogLoading("close");});
				}
				baseTreeShow(true,afterLoad);
			}else{
				menuBoxShow();
				$(".submenu").hide();
				$(".path").show();
				$(".path").load("/baseinfo/lowLevelTitle.jsp",function(){
					$(".ui-layout-west").load("/sysadmin/menuManage/getLowLevelBaseInfoMenuList.action?ename=basicInformation&urlflag="+menuType,function(){
						$.dialogLoading("close");
					});
				});

			}
		},
		disputeMediate:function(selectedOrgId,menuType){//矛盾纠纷
			menuBoxShow();
			$.selectMenu("dispute-menu");
			$(".path").load("/issue/highLevelTitle.jsp");
			$(".path").hide();
			$(".ui-layout-west").load("/issue/sysSideBar.jsp?urlflag="+menuType,function(){
				$(".submenu").show();
				$(".subnav").load("/issue/topSideBar.jsp?urlflag="+menuType);
				$.dialogLoading("close");
			});
		},
		digitalCity :function(selectedorgId,menuType){
			$.selectMenu("digitalCityManagement-menu");
			if(!lowLevel){
				function afterLoad(){
					$(".path").hide();
					$(".submenu").show();
					$(".subnav").load("/sysadmin/menuManage/getHighLevelBaseInfoMenuList.action?ename=digitalCityManagement&urlflag="+menuType,function(){$.dialogLoading("close");});
				}
				baseTreeShow(true,afterLoad);
			}else{
				menuBoxShow();
				$(".submenu").hide();
				$(".path").show();
				$(".ui-layout-west").load("/sysadmin/menuManage/getLowLevelBaseInfoMenuList.action?ename=digitalCityManagement&urlflag="+menuType,function(){
				});
				$(".path").load("/baseinfo/lowLevelTitle.jsp",function(){
					$.dialogLoading("close");
				});

			}
		},
		report:function(selectedorgId,menuType){
			$.selectMenu("report-menu");
			menuBoxShow();
			$(".ui-layout-west").load("/report/reportSideBar.jsp?urlflag="+menuType,function(){
				$(".path").hide();
				$(".submenu").show();
				$(".subnav").load("/report/reportTopMenu.jsp?urlflag="+menuType,function(){
					$.dialogLoading("close");
				});
			});
		},
		workingRecordMenu:function(selectedOrgId,menuType){//新日常工作
			menuBoxShow();
			$.selectMenu("workingRecord-menu");
			$(".submenu").hide();
			$(".ui-layout-west").load("/workingRecord/workingRecordSideBar.jsp?urlflag="+menuType,function(){
				$(".path").show();
				$(".path").load("/workingRecord/workingRecordTitle.jsp",function(){
					$.dialogLoading("close");
				});
			});
		},
		issue:function(selectedOrgId,menuType){//事件处理
			menuBoxShow();
			$.selectMenu("issue-menu");
			$(".path").load("/issue/highLevelTitle.jsp");
			$(".path").hide();
			$(".ui-layout-west").load("/issue/sysSideBar.jsp?urlflag="+menuType,function(){
				$(".submenu").show();
				$(".subnav").load("/issue/topSideBar.jsp?urlflag="+menuType);
				$.dialogLoading("close");
			});
		},

		peopleLog:function(selectedOrgId,menuType){//日志处理
			$.selectMenu("peopleLog-menu");
			menuBoxShow();
			$(".ui-layout-west").load("/peopleLog/sysSideBar_log.jsp?urlflag="+menuType,function(){
				$(".path").load("/peopleLog/highLevelTitle_log.jsp");
				$(".path").show();
				$(".submenu").hide();
				//$(".subnav").load("/peopleLog/test1.jsp?urlflag="+menuType);
				$.dialogLoading("close");
			});
		},

		statAnalyse:function(selectedOrgId,menuType){
			function afterLoad(){
				$.selectMenu("statAnalyse-menu");
				$(".path").hide();
				//treeBoxShow();
				$(".submenu").show();
				$(".subnav").load("/sysadmin/menuManage/getHighLevelBaseInfoMenuList.action?ename=statAnalyseManage&urlflag="+menuType,function(){
					$.dialogLoading("close");
				});
			}
			baseTreeShow(true,afterLoad);
		},
		examineAssessment:function(selectedOrgId,menuType){
			$.selectMenu("examineAssessment-menu");
			$(".submenu").hide();
			$(".ui-layout-west").load("/examineAssessment/examineAssessmentSideBar.jsp?urlflag="+menuType,function(){
				$(".path").show();
				$(".path").load("/examineAssessment/examineAssessmentTitle.jsp");
				$.dialogLoading("close");
			});
		},
		examine:function(selectedOrgId,menuType){
			$.selectMenu("examine-menu");
			$(".path").hide();
			$(".ui-layout-west").load("/common/orgTree.jsp?selectedOrgId="+selectedOrgId+"&urlflag="+menuType,function(){
				$(".submenu").show();
				$(".subnav").load("/examine/examineSideBar.jsp?urlflag="+menuType,function(){
					$.dialogLoading("close");
				});
			});
		},
		interactionManagement:function(selectedOrgId,menuType){
			menuBoxShow();
			$.selectMenu("interactionManagement-menu");
			$(".submenu").hide();
			$(".ui-layout-west").load("/interaction/interactionSideBar.jsp?urlflag="+menuType);
			$(".path").show();
			$(".path").load("/interaction/interactionTitle.jsp");
			$.dialogLoading("close");
		},
		integrativeQuery:function(selectedOrgId,menuType){
			menuBoxShow();
			$.selectMenu("integrativeQueryManagement-menu");
			$(".submenu").hide();
			$(".ui-layout-west").load("/integrativeQuery/integrativeQuerySideBar.jsp?urlflag="+menuType);
			$(".path").show();
			$(".path").load("/integrativeQuery/population/integrativeQueryTitle.jsp");
			$.dialogLoading("close");
		},
		
		callCenterManagement:function(selectedOrgId,menuType){//呼叫中心
			menuBoxShow();
			$.selectMenu("callCenterManagement-menu");
			$(".path").load("/callCenter/highLevelTitle.jsp");
			$(".ui-layout-west").load("/callCenter/sysSideBar.jsp?urlflag="+menuType,function(){
				$(".submenu").show();
				$(".subnav").load("/callCenter/topSideBar.jsp?urlflag="+menuType);
				$.dialogLoading("close");
			});
		},
		systemManagement:function(selectedOrgId,menuType){
			menuBoxShow();
			$.selectMenu("systemManagement-menu");
			$(".path").show();
			$(".submenu").hide();
			$(".ui-layout-west").load("/sysadmin/sysSideBar.jsp?urlflag="+menuType,function(){
				$(".path").load("/sysadmin/sysadminTitle.jsp");
				$.dialogLoading("close");
			});
		},
		evaluate:function(selectedOrgId,menuType){
			menuBoxShow();
			$.selectMenu("evaluate-menu");
			$(".submenu").hide();
			$(".ui-layout-west").load("/evaluate/evaluateSideBar.jsp?urlflag="+menuType,function(){
				$(".path").show();
				$(".path").load("/evaluate/evaluateTitle.jsp");
				$.dialogLoading("close");
			});
		},
		interactionManagementpmManagement:function(selectedOrgId,menuType){
			menuBoxShow();
			$.selectMenu("interactionManagement-menu");
			$(".submenu").hide();
			$(".ui-layout-west").load("/interaction/interactionSideBar.jsp?urlflag="+menuType,function(){
				$(".path").show();
				$(".path").load("/interaction/interactionTitle.jsp",function(){
					$("#currentPosition").text("平台消息");
				});
				$("#pmManagement").click();
				$.dialogLoading("close");
			});
		},
		getIssueData:function(selectedOrgId){//事件处理数据视图
			$.selectMenu("issue-menu");
			menuBoxShow();
			$(".path").hide();
			$("#contentDiv").empty();
			$(".ui-layout-west").load("/issue/sysSideBar.jsp");
			$(".subnav").load("/issue/topSideBar.jsp");
		},
		getIssueChart:function(selectedOrgId){
			baseTreeShow();
			$(".subnav").empty();
			$(".path").empty();
			$(".path").show();
			$(".submenu").hide();
			$(".ui-layout-west").load("/common/orgTree.jsp?selectedOrgId="+selectedOrgId);
			$(".path").load("/issue/highLevelTitle.jsp");
			$("#contentDiv").load("/issue/highLevelChart.jsp");
		},
		dailylog:function(){//日常工作
			menuBoxShow();
			$.selectMenu("dailylog-menu");
			$(".submenu").hide();
			$(".ui-layout-west").load("/dailylog/dailySideBar.jsp",function(){
				$(".path").show();
				$(".path").load("/dailylog/dailylogTitle.jsp",function(){
					$.dialogLoading("close");
				});
			});
		},
		getBaseInfoChartView:function(selectedOrgId){//基础信息图表视图
			$.selectMenu("basicInformation-menu");
			var siteName='';
			if(placeName=="重点场所"){
				siteName = "place"
			}else if(placeName=="出租房"){
				siteName = "lettingHouse"
			}else if(placeName=="规上企业"){
				siteName = "ent"
			}else if(placeName=="社会组织"){
				siteName = "new"
			}else if(placeName="重点人员"){
				siteName = "pen"
			}
			treeBoxShow();
			$(".path").empty();
			$(".subnav").empty();
			$(".path").show();
			$(".submenu").hide();
			$(".path").load("/baseinfo/highLevelTitle.jsp");
			$("#contentDiv").load("/baseinfo/highLevelChart.jsp?siteName="+siteName,function(){$.dialogLoading("close");});
		},
		getBaseInfoDataView:function(selectedOrgId,typeName){//基础信息数据视图
			$.selectMenu("basicInformation-menu");
			var urlflag;
			var charttype;
			if(typeName== "重点场所"){
				urlflag = "safetyProductionKey";
				charttype = 2;
			}else if(typeName== "重点人员"){
				urlflag = "positiveinfos";
				charttype = 1;
			}
			else if(typeName== "出租房"){
				urlflag = "lettingHouses";
				charttype = 3;
			}
			else if(typeName== "规上企业"){
				urlflag = "enterpriseKey";
				charttype = 4;
			}
			else if(typeName== "社会组织"){
				urlflag = "newSocietyFederations";
				charttype = 5;
			}
			var subMenuHeight=$(".submenu").height();
			if($("#orgTree-select").size()==0){
				$(".ui-layout-west").load("/common/orgTree.jsp?selectedOrgId="+selectedOrgId);
			}

			$(".submenu").hide();
			$(".path").empty();
			$(".subnav").empty();
			$(".submenu").show().height(0);
			$(".path").hide();
			$(".subnav").load("/baseinfo/middleLevelSideBar.jsp?urlflag="+ urlflag +"&charttype="+charttype,function(){
				$(".submenu").height(subMenuHeight);
			});
		}

	}

$.extend({
	selectMapMenu:function(o){
		var dfop={
			id:'',
			jsflag:''
		}
		$.extend(dfop,o);
		var jsflag=dfop.jsflag;
		$("#"+dfop.id).accordionFunction("h1","div");
		if(jsflag==undefined || jsflag=='' || !$("#"+jsflag)[0]){
			$('#'+dfop.id+' > div > dl > dd:first > a').click();
		}else{
			var thisTab=$("#"+jsflag).click();
			var $parentTab=thisTab.closest(".ui-accordion-content");
			if($parentTab[0]){
				var $parentTitle=$parentTab.prev();
				$parentTitle.addClass("ui-state-active ui-corner-top");
				$(".ui-accordion-content").hide();
				$parentTab.show()
			}
		}
	}
})