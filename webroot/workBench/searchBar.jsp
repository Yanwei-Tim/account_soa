<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:action var="keyNames" name="getPatelConfigerKeyName" namespace="/patel/patelManage" executeResult="false">
</s:action>
<div class="workBench-searchBar">
	<form>
		<div class="search-box">
				<input type="text" id="search_text" style="color:#898989" class="search-text" value="请输入关键字" onblur="value=(this.value=='')?'请输入关键字':this.value;" onfocus="value=(this.value=='请输入关键字')?'':this.value;"></input>
				<!--<select id="searchSelect" class="hidden">
	             		<option value="person">人口信息</option>
	             		 <option value="house">房屋信息</option>
	             		<OPTION VALUE="dw">单位信息</OPTION>
	             		<option value="event">事件信息</option> 
	            	</select>
		--></div>
		<a href="javascript:;" class="search-btn" id="query_search">搜索</a>
	</form>
	
</div>
		
<script>

$(function() {
	$("#searchSelect").searchSelect();
	$("#query_search").click(function(){
		var searchType = $("#searchSelect").val();
		if("person"==searchType){
			searchPopulation("人口信息");
		}
	});

	function searchPopulation(cname){
		$("#queryDialog").createDialog({
			width: 900,
			height: 600,
			title: cname,
			modal:true,
			url: '${path}/integrativeQuery/populationIntegrativeQuery/dispatchOperate.action?mode=queryForWorkBench',
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		})
   }
});
</script>
