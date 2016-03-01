<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<style>
	.calendar{padding:0;}
	#gview_workCalendar .ui-jqgrid-btable .ui-widget-content{background:#fff;opacity:1;filter: Alpha(Opacity=100);}
	.calendar span.red{color:#ff6600;}
	.ui-state-highlight, .ui-widget-content .ui-state-highlight, .ui-widget-header .ui-state-highlight  {border: 1px solid #a6c9e2; background: #CEE6FB; color: #363636; }
	.ui-state-highlight a, .ui-widget-content .ui-state-highlight a,.ui-widget-header .ui-state-highlight a { color: #363636; }
	.calendar td.ui-state-highlight,.calendar td.ui-state-hover{border:0;}
	.calendar-bar .button{width:72px;height:23px;line-height:23px;*line-height:20px;vertical-align:middle;}
</style>

<div class="content">
<div class="ui-widget-border ui-widget-border-on calendar-bar" style="margin-bottom:5px;">
<p class="left"><label class="form-label">年份：</label> <select name="calendar" class="calendar-select" id="calendar">
	<s:iterator value="set">
		<option value='<s:property />' selected="selected"><s:property />
	</s:iterator>
</select> <INPUT name="flg" value="${flg}" id="flg" type="hidden"/><span><input type="button" class="button" value="初始化" id="initialize"/></span>
<span><input type="button" class="button" value="节假日" id="week" />
<input type="button" class="button" value="工作日" id="work" /> </span>
</p>
	<p class="right" align="right">
				*红色表示节假日,黑色表示工作日
	</p>
<input name="monthAndDay" id="monthAndDay" value="${monthAndDay}" type="hidden"></div>
<table class="calendar" width="100%" id="workCalendar"></table>
<input type="hidden" id="calendarDate" value="" />
<div id="plist47"></div>
<div class="clear"></div>
</div>
<script type="text/javascript">
	var date;
$(function() {
	function isAdminFormatter(el,options,rowData){
		var colText=rowData[options.colModel.name];
		var colTextJsonData=eval("("+colText+")");
		var year=$("#calendar").attr("value");
		if(colTextJsonData.day=="0"||colTextJsonData.day==null){return "";}
		if(colTextJsonData.holiday=="1"||colTextJsonData.holiday=="7"){
			return "<span day='"+colTextJsonData.day+"' class='red'>"+colTextJsonData.day+ "<em>" + wek(options,colTextJsonData)+"</em>" +"</span>";
			}
		return "<span day='"+colTextJsonData.day+"'>"+colTextJsonData.day + "<em>" + wek(options,colTextJsonData)+"</em>" +"</span>";
	}
	function wek(options,colTextJsonData){
		var _month;
		var thisText;
		switch (options.colModel.name) {
			case "january":
				_month=1;
				break;
			case "february":
				_month=2;
				break;
			case "march":
				_month=3;
				break;
			case "april":
				_month=4;
				break;
			case "may":
				_month=5;
				break;
			case "june":
				_month=6;
				break;
			case "july":
				_month=7;
				break;
			case "august":
				_month=8;
				break;
			case "september":
				_month=9;
				break;
			case "october":
				_month=10;
				break;
			case "november":
				_month=11;
				break;
			case "december":
				_month=12;
				break;
		}
		var year=$("#calendar").attr("value");
		var weekAll = new Array("星期天","星期一","星期二","星期三","星期四","星期五","星期六");
		var _thisDay=new Date(year,_month-1,colTextJsonData.day);
		var week=weekAll[_thisDay.getDay()];
		return week;
	}
	$.fn.extend({
		jqCalendarGrid:function(option){
		var defaultOption={
			datatype: "local",
			width:1000,
			height:400,
			colNames:['一月','二月', '三月', '四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
			colModel:[
				{name:'january',index:'january', width:50,align:"center",sorttype:"int",formatter:isAdminFormatter,sortable:false},
				{name:'february',index:'february', width:50,align:"center",sorttype:"int",formatter:isAdminFormatter,sortable:false},
				{name:'march',index:'march', width:50,align:"center",sorttype:"int",formatter:isAdminFormatter,sortable:false},
				{name:'april',index:'april', width:50,align:"center",sorttype:"int",formatter:isAdminFormatter,sortable:false},
				{name:'may',index:'may', width:50,align:"center",sorttype:"int",formatter:isAdminFormatter,sortable:false},
				{name:'june',index:'june', width:50,align:"center",sorttype:"int",formatter:isAdminFormatter,sortable:false},
				{name:'july',index:'july', width:50,align:"center",sorttype:"int",formatter:isAdminFormatter,sortable:false},
				{name:'august',index:'august', width:50,align:"center",sorttype:"int",formatter:isAdminFormatter,sortable:false},
				{name:'september',index:'september', width:50,align:"center",sorttype:"int",formatter:isAdminFormatter,sortable:false},
				{name:'october',index:'october', width:50,align:"center",sorttype:"int",formatter:isAdminFormatter,sortable:false},
				{name:'november',index:'november', width:50,align:"center",sorttype:"int",formatter:isAdminFormatter,sortable:false},
				{name:'december',index:'december', width:50,align:"center", sorttype:"int",formatter:isAdminFormatter,sortable:false}
			],
			gridview:true,
			altRows:true,
			viewrecords:true,
			loadComplete:function(){
				$.loadingComp("close");
			}
		}
		defaultOption.width=screen.width-$(".ui-layout-west").width()-$(".center-left").width()-35;
		$(window).resize(function(){
			defaultOption.width=$(".ui-layout-center").width();
		});

		$.extend(defaultOption,option);
		$(this).jqGrid(defaultOption).navGrid('#plist47',{
		edit:false,add:false,del:false
	    });
	    var day="["+$("#monthAndDay").val()+"]";
		var mydata =eval("("+day+")");
		for(var i=0;i<=mydata.length;i++){
			jQuery(this).jqGrid('addRowData',i+1,mydata[i]);
		}
	}
	});
	var option={
		onCellSelect:function(rowid,iCol,cellcontent,event){
			date="";
			var _e = event || window.event;
			var _dateValue=$("#calendarDate").attr("value");
			var _month=$("#"+rowid+" td:eq("+iCol+")").index()+1;
			var _day=$("#"+rowid+" td:eq("+iCol+")"+" span").attr("day");
			if($("#"+rowid+" td:eq("+iCol+")").hasClass("ui-state-highlight")&&$("#"+rowid+" td:eq("+iCol+")").text()!=""){
					_thisdateValue=_month+"-"+_day+",";
					_dateValue=_dateValue.replace(_thisdateValue,"");
					$("#calendarDate").attr("value",_dateValue);
					$("#"+rowid+" td:eq("+iCol+")").removeClass("ui-state-highlight");
					date+=$("#calendarDate").attr("value");
			}

			else if($(".ui-state-highlight").size()<=1&&_e.ctrlKey==false){
				if(_dateValue==""){
					$("#"+rowid+" td:eq("+iCol+")").addClass("ui-state-highlight");
					_dateValue=_dateValue+_month+"-"+_day+",";
					$("#calendarDate").attr("value",_dateValue);
					date+=$("#calendarDate").attr("value");
				}
				else{
					if($(".ui-state-highlight").size()==1){
						$("td.ui-state-highlight").removeClass("ui-state-highlight");
						$("#"+rowid+" td:eq("+iCol+")").addClass("ui-state-highlight");
						$("#calendarDate").attr("value","");
						_thisdateValue=_month+"-"+_day+",";
						$("#calendarDate").attr("value",_thisdateValue);
						date+=$("#calendarDate").attr("value");
					}
				}
			}
			else if(_e.ctrlKey&&$("#"+rowid+" td:eq("+iCol+")").text()!=""){
					$("#"+rowid+" td:eq("+iCol+")").addClass("ui-state-highlight");
					_dateValue=_dateValue+_month+"-"+_day+",";
					$("#calendarDate").attr("value",_dateValue);
					date+=$("#calendarDate").attr("value");
			}
		},
		onSelectRow:function(rowid){
			$("#"+rowid).removeClass("ui-state-hover").removeClass("ui-state-highlight");
	}
}

	$("#workCalendar").jqCalendarGrid(option);
	//日历行 指向样式移除
	$(".calendar tr").hover(function(){
		$(this).css("background","#fff");
	},function(){
		$(this).css("background","#fff");
	});
	//日历单元格样式加载
	$(".calendar tr td").hover(function(){
		$(this).addClass("ui-state-hover");
	},function(){
		$(this).removeClass("ui-state-hover");
	});
	$("#week").click(function(){
		var year=$("#calendar").val();
		if(date==null||date==""
			|| date.substring(date.lastIndexOf("-")+1,date.length)=="undefined,"){
			 $.messageBox({
					message:"请先选择日期",
					level: "warn"
				 });
			 return;
		}
		$.ajax({
            url: '${path}/sysadmin/workCalendarManger/updateWorkCalendarIsWeek.action?workCalendar.year='+year+'&monthAndDay='+date,
            type: 'post',
            dataType:'json',
            success: function(data){
                if(data==true){
                	$.messageBox({message:"修改日期成功!"});
                	$(".ui-state-highlight").each(function(){
            			$(this).removeClass("ui-state-highlight");
            			$(this).find("span").addClass("red");
            			$("#calendarDate").attr("value","");
            			date="";
            		});

                }else{
                	$("#calendarDate").attr("value","");
                	date="";
                	$(".ui-state-highlight").each(function(){
            			$(this).removeClass("ui-state-highlight");
            		});
                	$.messageBox({
						message:data,
						level: "error"
					});
                }
          	 }
        });
	});

	$("#work").click(function(){
		var year=$("#calendar").val();
		if(date==null||date=="" || date.substring(date.lastIndexOf("-")+1,date.length)=="undefined,"){
			 $.messageBox({
					message:"请先选择日期",
					level: "warn"
				 });
			 return;
		}
		$.ajax({
            url: '${path}/sysadmin/workCalendarManger/updateWorkCalendarIsWork.action?workCalendar.year='+year+'&monthAndDay='+date,
            type: 'post',
            dataType:'json',
            success: function(data){
                if(data==true){
                	$.messageBox({message:"修改日期成功!"});
                	$(".ui-state-highlight").each(function(){
            			$(this).removeClass("ui-state-highlight");
            			$(this).find("span").removeClass("red");
            			$("#calendarDate").attr("value","");
            			date="";
            		});
                }else{
                	$(".ui-state-highlight").each(function(){
            			$(this).removeClass("ui-state-highlight");
            		});
                	$("#calendarDate").attr("value","");
                	date="";
                	$.messageBox({
							message:data,
							level: "error"
					});
                }
          	 }
        });
	});

	$("#initialize").click(function(){
		var year=$("#calendar").val();
		$.ajax({
            url:'${path}/sysadmin/workCalendarManger/addWorkCalendar.action?workCalendar.year='+year,
            type: 'post',
            dataType:'json',
            success: function(data){
                if(data.monthAndDay!=null||data.monthAndDay!=""){
                	$.messageBox({message:"初始化年份成功!"});
                	jQuery(".calendar").jqGrid('clearGridData',false);
                	var day="["+data+"]";
        			var mydata =eval("("+day+")");
        			for(var i=0;i<=mydata.length;i++){
        				jQuery(".calendar").jqGrid('addRowData',i+1,mydata[i]);
        			}
                }
          	 }
        });
	});

	$("#calendar").change(function(){
		var year=$("#calendar").val();
		$("#calendarDate").attr("value","");
		date="";
		$.ajax({
            url: '${path}/sysadmin/workCalendarManger/isYear.action?workCalendar.year='+year,
            type: 'post',
            dataType:'json',
            success: function(data){
                if(data){

                }
          	 }
       	 });
		$.ajax({
            url: '${path}/sysadmin/workCalendarManger/findWorkCalendar.action?mode=change&workCalendar.year='+year,
            type: 'post',
            dataType:'json',
            success: function(data){
            	jQuery(".calendar").jqGrid('clearGridData',false);
            	var day="["+data+"]";
    			var mydata =eval("("+day+")");
    			for(var i=0;i<=mydata.length;i++){
    				jQuery(".calendar").jqGrid('addRowData',i+1,mydata[i]);
    			}
            }
       	 });
	});

});
</script>