<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.tianque.core.util.BaseInfoTables"%>
<%@page import="com.tianque.service.util.PopulationType"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%
    String type=request.getParameter("type");
	request.setAttribute("type",type);
	String largeType=request.getParameter("largeType");
	if(BaseInfoTables.ACTUALPOPULATION_KEY.equals(largeType)){
	        request.setAttribute("typeName",
			BaseInfoTables.getTypeDisplayNames(type)==null?"总况":BaseInfoTables.getTypeDisplayNames(type));
	        request.setAttribute("largeType",largeType);
	}else{
		request.setAttribute("typeName",
				PopulationType.getCnameByPopulationType(type)==null?"总况":PopulationType.getCnameByPopulationType(type));
	}
%>
<div id="stat${type}" style="padding-top:5px;">
	<div id="date${type}" style="float: right; display: none;">
		<select id="year${type}" onchange="">
		</select> <label>年</label> <select id="month${type}" onchange="">
		</select> <label>月</label> <a id="searchDate${type}"
			href="javascript:void(0)"><span>查询</span></a>
	</div>
	<select id="chartType${type}" style="margin-left: 11px;">
		<option>区域分布图</option>
		<option>列表信息</option>
		<option>趋势图</option>
		<s:if test="@com.tianque.service.util.PopulationCatalog@parse(#attr.type)==null">
			<s:if test="@com.tianque.core.util.BaseInfoTables@POPULATION_KEY.equals(#attr.type)">
			    <option>类型分布图</option>
			 </s:if>
		</s:if>
		<s:if test="@com.tianque.service.util.PopulationCatalog@parse(#attr.type).getStatisticListSetting().getDomainName()!=null">
		   <option>类型分布图</option>
		</s:if>
		<s:if test="#attr.type.indexOf('all')>=0">
		   <option>类型分布图</option>
		</s:if>
	</select>

	<div id="SimplyColumn${type}" class="SigmaReport listBox"
		style="display: block;"></div>

	<div id="gridbox${type}" class="SigmaReport listBox"></div>

	<div id="trend${type}" class="SigmaReport listBox"></div>

	<div id="pie${type}" class="SigmaReport listBox"
		style="width: 300px;"></div>
</div>
<script type="text/javascript">
$(function() {
	var loadTime=false;
	var orgId=<s:property value="@com.tianque.util.ThreadVariable@getUser().getOrganization().getId()"/>;
	 var columnChartUrl= "${path}/baseInfo/statisticManage/getStatisticColumn.action?type=${type}&orgId="+orgId;
	 var trendChartUrl="${path}/baseInfoStat/tendencyChart/showTendencyChartForPositiveinfo.action?type=${type}&organization.id="+orgId;
	 var listChartUrl="${path}/baseInfo/statisticManage/getBaseInfoStatisticList.action?orgId="+orgId+"&type=${type}";
	 var pieChartUrl="${path}/baseInfo/statisticManage/getStatisticPie.action?orgId="+orgId+"&type=${type}";
	 var columns = [
				{name:"orgName",caption:"区域",mode:"string"},
				{name:"general",caption:"总况",children:[
					{name:"baseinfoStatisticDetailVo[index].typeName",caption:"类型",mode:"string"},
					{name:"baseinfoStatisticDetailVo[index].sum",caption:"数量",mode:"number",format:"#"}
				]}
			];
	 <s:if test="@com.tianque.core.util.BaseInfoTables@ACTUALPOPULATION_KEY.equals(#attr.largeType)">
	  columnChartUrl="${path}/baseInfoStat/statisticsPopulation/getStatisticsPopulationColumn.action?populationType=${type}&orgId="+orgId;
	  trendChartUrl="${path}/baseInfoStat/tendencyChart/showTendencyChartForPopulation.action?typeTableName=${type}&organization.id="+orgId;
	  listChartUrl="${path}/baseInfoStat/statisticsPopulation/getStatisticsPopulationList.action?orgId="+orgId+"&populationType=${type}";
	  pieChartUrl="${path}/baseInfoStat/statisticsPopulation/getStatisticsPopulationPie.action?orgId="+orgId;
	   columns = [
						{name:"org.orgName",caption:"区域",width:80,mode:"string"},
						{name:"populationDetailDatas[index].name",caption:"类型",height:250,width:80,mode:"string"},
						{name:"populationDetailDatas[index].amount",caption:"数量",width:50},
						{name:"populationDetailDatas[index].amountPercentage",caption:"百分比",width:50,mode:"number",format:"#.00%"}
					];
	 </s:if>
	
	 columnChart("SimplyColumn${type}","${typeName}",columnChartUrl,false);
	
	$("#year${type}").change(function(){
		$("#month${type}").empty();
		getmonth($("#year${type}").val(),function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#month${type}").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
			});
	});

	$("#chartType${type}").change(function(){
		var index=$(this).get(0).selectedIndex+1;//索引
		var thisSelect=$(this).find("option:selected");
		var isCreate=thisSelect.data("isCreate");
		if(index==1){
			$("#date${type}").hide();
		}
		if(index==2){
			if(!loadTime){
				 getYear(function (responseData){
				     for(var i = 0;i<responseData.length;i++){
						$("#year${type}").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
					}
					getmonth(responseData[0],function(responseData){
						for(var i = 0;i<responseData.length;i++){
							$("#month${type}").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
						}
						loadTime=true;
						});

				 });
			}
			setTimeout(function(){
				$("#date${type}").show();
				if(!isCreate){
					listChart("gridbox${type}","${typeName}",listChartUrl+"&year="+$("#year${type}").val()+"&month="+$("#month${type}").val(),columns,false);
					thisSelect.data("isCreate",true);
				}

				},100);
			
		}if(index==3){
			$("#date${type}").hide();
			if(!isCreate){
				trendChart("trend${type}","${typeName}",trendChartUrl,false);
				thisSelect.data("isCreate",true);
			}
		}if(index==4){
			if(!loadTime){
				 getYear(function (responseData){
				     for(var i = 0;i<responseData.length;i++){
						$("#year${type}").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
					}
					getmonth(responseData[0],function(responseData){
						for(var i = 0;i<responseData.length;i++){
							$("#month${type}").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
						}
						loadTime=true;
						});

				 });
			}
			setTimeout(function(){
				$("#date${type}").show();
				if(!isCreate){
					 pieChart("pie${type}","${typeName}",pieChartUrl+"&year="+$("#year${type}").val()+"&month="+$("#month${type}").val(),false);
					thisSelect.data("isCreate",true);
				}

				},100);
			
		}
		$(".listBox",$("#stat${type}")).hide();
		$(".listBox",$("#stat${type}")).eq(index-1).show();

		
	});

	$("#isNowYear${type}").val($("#year${type}").val());
	$("#isNowMonth${type}").val($("#month${type}").val());

  $("#searchDate${type}").click(function(){
		if($("#chartType${type}").get(0).selectedIndex==1){
			listChart("gridbox${type}","${typeName}",listChartUrl+"&year="+$("#year${type}").val()+"&month="+$("#month${type}").val(),columns,false);
			isCreate=false;
		}else if($("#chartType${type}").get(0).selectedIndex==3){
			 pieChart("pie${type}","${typeName}",pieChartUrl+"&year="+$("#year${type}").val()+"&month="+$("#month${type}").val(),false);
			isCreate=false;
		}
  }); 
});


</script>