	function getmonth(year,callback){
		$.ajax({
			async: false,
			url: PATH+"/stat/currentTime/getCurrentTimeForMonth.action?currenYear="+year,
			success:function(responseData){
			callback(responseData);
			}
		});
	}
	function columnChart(name,moduleName,url,isMax){
		if(isMax){
			$("#"+name).columnChart({
				url: url,
				moduleName:moduleName,
				textx:-150,
				quantity:'个数'
			},{legend:{itemWidth:180,itemStyle:{height:20},symbolWidth:50}});
		}else{
		 $("#"+name).columnChart({
			url: url,
			moduleName:moduleName,
			textx:-150,
			quantity:'个数'
		},{title:false,workbenchColum:true,yAxis:{title:{text:''}},legend:{ enabled:false}});
		}
	}
	function trendChart(name,moduleName,url,isMax){
		if(isMax){
		 $("#"+name).lineChart({
			url: url,
			moduleName:moduleName
		});}else{
			$("#"+name).lineChart({
				url: url,
				moduleName:moduleName,
				width:800
			},{title:false,yAxis:{title:{text:''}},legend:{ enabled:false}});
		}
		
	}


	function listChart(name,moduleName,url,columns,isMax){
		$.ajax({
			url:url,
			success:function(data){
				if(data == null){
					$.messageBox({
						message:"查询的月份没有数据生成",
						level: "error"
					});
					return;
				}
				var context = {};
				
				var grid = new SigmaReport(name,context,columns,'SigmaReport','',moduleName,false);
				grid.bindData(data);
				
			}
		})
	}

	function pieChart(name,moduleName,url,isMax){
		if(isMax){
		 $("#"+name).pieChart({
			url:url,
			moduleName:moduleName
		});}else{
			 $("#"+name).pieChart({
					url:url,
					moduleName:moduleName,
					width:$("#"+name).parent().width(),
					height:200
				},{title:false,plotOptions:{pie:{ dataLabels: {enabled: false}}}});
		}
	}
	function getYear(callback){
	$.ajax({
		async: false,
		url: PATH+"/stat/currentTime/getCurrentTimeForYear.action",
		success:function(responseData){
		callback(responseData);
		}
	});
	}
	