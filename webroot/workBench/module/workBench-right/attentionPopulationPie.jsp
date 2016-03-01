<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%	
	Long orgId = ThreadVariable.getSession().getOrganization().getId();
	String type = request.getParameter("type");
	request.setAttribute("type",type);
%>

<div id="nav" class="ui-corner-all">
		<select name="queryYear" id="queryYear"  style="float:left;">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;" name="queryMonth" id="queryMonth">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
		<a id="search" href="javascript:void(0)"><span>查询</span></a>
</div> 
<div id="positiveinfoPie" class="SigmaReport" style="height:190px;width:100%;"></div>

<script type="text/javascript">
$(document).ready(function() {
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYear.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#queryYear").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
			getmonth();
		}
	});
	$("#isNowYear").val($("#queryYear").val());
	$("#isNowMonth").val($("#queryMonth").val());
	onOrgChanged();
	$("#search").click(function(){
		onOrgChanged();
	});
	$("#queryYear").change(function(){
		$("#queryMonth").empty();
		getmonth();
	});
});

function onOrgChanged(){
	$("#positiveinfoPie").pieChart({
		url:"${path}/baseInfo/statisticManage/getStatisticPie.action?orgId="+<%=orgId%>+"&year="+$("#queryYear").val()+"&month="+$("#queryMonth").val()+"&type=${type}",
		moduleName:"重点人口",
		onClick:function(event){
			setOptionsWhenShowInfo(event.point.name,<%=orgId%>);
			showInfo(url, title, 900, 500,$("#queryYear").val(),$("#queryMonth").val());
		}
	});
}
function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForMonth.action?currenYear="+$("#queryYear").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#queryMonth").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
		}
	});
}
function setOptionsWhenShowInfo(name, orgId){
	if(name.indexOf("刑释解教人员") != -1){
		title='刑释解教人员';
		url = '${path}/baseinfo/positiveInfo/positiveInfoListForStatistics.jsp?orgIdForStat='+orgId+'&searchType=fast';
	}else if(name.indexOf("社区矫正人员") != -1){
		title='社区矫正人员';
		url = '${path}/baseinfo/rectify/rectifyListForStatistics.jsp?orgIdForStat='+orgId+'&searchType=fast';
	}else if(name.indexOf("重点青少年") != -1){
		title='重点青少年';
		url = '${path}/baseinfo/idleYouth/idleYouthListForStatistics.jsp?orgIdForStat='+orgId+'&searchType=fast';
	}else if(name.indexOf("精神病人员") != -1){
		title='精神病人员';
		url = '${path}/baseinfo/mentalPatient/mentalPatientlistForStatistics.jsp?orgIdForStat='+orgId+'&searchType=fast';
	}else if(name.indexOf("吸毒人员") != -1){
		title='吸毒人员';
		url = '${path}/baseinfo/druggyManage/druggyManageListForStatistics.jsp?orgIdForStat='+orgId+'&searchType=fast';
	}else if(name.indexOf("重点上访人员") != -1){
		title='重点上访人员';
		url = '${path}/baseinfo/superiorVisit/superiorVisitListForStatistics.jsp?orgIdForStat='+orgId+'&searchType=fast';
	}else if(name.indexOf("需要救助人员") != -1){
		title='需要救助人员';
		url = '${path}/baseinfo/poorPeopleManage/poorPeopleListForStatistics.jsp?orgIdForStat='+orgId+'&searchType=fast';
	}else if(name.indexOf("危险品从业人员") != -1){
		title='危险品从业人员';
		url = '${path}/baseinfo/dangerousGoodsPractitioner/dangerousGoodsPractitionerListForStatistisc.jsp?orgIdForStat='+orgId+'&searchType=fast';
	}else if(name.indexOf("其他人员") != -1){
		title='其他人员';
		url = '${path}/baseinfo/otherAttentionPersonnel/otherAttentionPersonnelListForStatistics.jsp?orgIdForStat='+orgId+'&searchType=fast';
	}else if(name.indexOf("境外人员") != -1){
		title='境外人员';
		url='${path}/baseinfo/overseaPersonnel/overseaPersonnelListForStatistics.jsp?orgIdForStat='+orgId+'&searchType=fast';
	}else if(name.indexOf("育龄妇女") != -1){
		title='育龄妇女';
		url='${path}/baseinfo/nurturesWomen/nurturesWomenStatistics.jsp?orgIdForStat='+orgId+'&searchType=fast';
	}
	else if(name.indexOf("优抚对象") != -1){
		title='优抚对象';
		url='${path}/baseinfo/civilAdministration/optimalObject/optimalObjectStatistics.jsp?orgIdForStat='+orgId+'&searchType=fast';
	}
	else if(name.indexOf("老年人") != -1){
		title='老年人';
		url='${path}/baseinfo/elderlyPeople/elderlyPeopleStatistics.jsp?orgIdForStat='+orgId+'&searchType=fast';
	}
	else if(name.indexOf("需救助人员") != -1){
		title='需救助人员';
		url='${path}/baseinfo/civilAdministration/aidNeedPopulation/aidNeedPopulationListForStatistics.jsp?orgIdForStat='+orgId+'&searchType=fast';
	}
	else if(name.indexOf("残疾人") != -1){
		title='残疾人';
		url='${path}/baseinfo/handicapped/handicappedListForStatistics.jsp?orgIdForStat='+orgId+'&searchType=fast';
	}
	else if(name.indexOf("失业") != -1){
		title='失业人员';
		url='${path}/baseinfo/civilAdministration/unemployedPeople/unemployedPeopleListForStatistics.jsp?orgIdForStat='+orgId+'&searchType=fast';
	}
}

function showInfo(url, title, width, height,year,month){
	
	if(year==$("#isNowYear").val() && month==$("#isNowMonth").val()){
		
		$("#infoList").createDialog({
			width: width,
			height: height,
			title: title+'信息',
			modal:true,
			url: url,
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   			}
				}
			});
	}
}
function shwoInfoInDialog(name, orgId){
	setOptionsWhenShowInfo(name, orgId);
	showInfo(url, title, width, height,$("#year").val(),$("#month").val());
}
</script>