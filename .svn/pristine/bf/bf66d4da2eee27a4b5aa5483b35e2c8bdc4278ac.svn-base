$.fn.orgSelect=function(options,orgIds){
	var self=$(this);
	var selfId=self.attr("id");
	var dfop={
		url:'/sysadmin/orgManage/ajaxOrgsForExtTree.action?orgType=0',
		level:[
			{
				org:'COUNTRY',
				text:'全国',
				orgLevelInternalId:60
			},
			{
				org:'PROVINCE',
				text:'省',
				orgLevelInternalId:50
			},
			{
				org:'CITY',
				text:'市',
				orgLevelInternalId:40
			},
			{
				org:'DISTRICT',
				text:'区',
				orgLevelInternalId:30
			},
			{
				org:'TOWN',
				text:'乡镇（街道）',
				orgLevelInternalId:20
			},
			{
				org:'VILLAGE',
				text:'村（社区）',
				orgLevelInternalId:10
			},
			{
				org:'GRID',
				text:'片组片格',
				orgLevelInternalId:0
			}
		],
		loadComplete:function(){
		},
		onSelect:function(orgId){
			
		}
	}
	if(typeof options=='object'){
		$.extend(dfop,options);
		self.data("dfop",dfop);
	}
	var $orgBox,$orgBoxResult,$orgBoxSearchBox;
	$orgBox=$("#"+selfId+"box");
	$orgBoxResult=$orgBox.find(".orgBoxResult");
	$orgBoxSearchBox=$orgBox.find(".orgBoxSearchBox");
	function todoAjax(url,callback){//Ajax
		var jsonData;
		$(".orgLoadBox").show();
		$.ajax({
			url:url,
			async:false,
			success:function(jsonData){
				callback(jsonData);
				$(".orgLoadBox").hide();
			}
		})
	}
	function buildDom(jsonData){
		var $thisBox;
		var buildData=jsonData;
		if(buildData=='' && self.data("setOrg")!=true){
			$orgBox.closest(".ui-dialog").find(".ui-dialog-buttonpane").find("button:contains('确定')").click();
			return false;
		}
		for(var index=0;index<dfop.level.length;index++){//构建组织机构窗口
			var that=dfop.level[index];
			if(buildData==''){return false;}
			if(that.orgLevelInternalId==buildData[0].orgLevelInternalId){
				$(".organizationBox").hide();
				$thisBox=$orgBox.find(".organizationBox").show();
				$thisBox.show().find("h3").html(that.text);
				$thisBox.find(".organizationlist").empty();
			}
		}
		for(var i=0;i<buildData.length;i++){//build 组织机构列表
			var $item=$('<a href="javascript:;"></a>').attr({
				orgId:buildData[i].id,
				orgLevelInternalId:buildData[i].orgLevelInternalId,
				leaf:buildData[i].leaf
			}).append("<strong>"+buildData[i].text+"</strong>").append("<span class='arrows'></span>");
			if(buildData[i].leaf==true){
				$item.addClass("leaf");
			}
			$thisBox.find(".organizationlist").append($item).show();
		}
		bindEvent(buildData);
	}
	function bindEvent(jsonData){//绑定事件
		$orgBox.find(".organizationlist").unbind().delegate("a","click",function(){
			var that=$(this).addClass("cur");
			var thisId=$(that).attr("orgId");
			var loadUrl=dfop.url+'&parentId='+thisId;
			var thisNextDom=$(that).closest(".organizationBox").nextAll();
			thisNextDom.each(function(index,thisDom){
				var listDom=$(thisDom).find(".organizationlist");
				if(listDom.find("a")[0]){
					$(thisDom).hide();
					listDom.empty();
				}
			})
			selectOrg(that);
			todoAjax(loadUrl,buildDom);
			$orgBox.find(".orgBoxResult").find("a").removeClass("cur").end().append($(that));
		});
		$orgBox.find(".orgBoxResult").unbind().delegate("a","click",function(event){
			var thisId=$(this).attr("orgId");
			var loadUrl=dfop.url+'&parentId='+thisId;
			$(this).addClass("cur").nextAll("a").remove();
			todoAjax(loadUrl,buildDom);
			selectOrg($(this));
		});
	}
	function setOrgs(data){//遍历org
		self.data("setOrg",true);
		var dfop=self.data("dfop");
		for(var i=0;i<data.length;i++){
			var leaf='false';
			if(data[i].subCount>0){
				leaf='true';
			}
			var thisDom=$('<a href="javascript:;" orgid="'+data[i].id+'" orglevelinternalid="'+data[i].orgLevel.internalId+'" leaf="'+leaf+'"><strong>'+data[i].orgName+'</strong><span class="arrows"></span></a>');
			$orgBox.find(".orgBoxResult").append(thisDom);
		}
		$orgBox.find(".orgBoxResult a:last").addClass("cur").click();
		var loadUrl=dfop.url+'&parentId='+data[data.length-1].id;
		if(data[data.length-1].subCount>0){
			todoAjax(loadUrl,buildDom);
		}else{
			$orgBox.find(".organizationBox").hide();
		}
		self.data("setOrg",false);
	}
	function selectOrg(that){//组织机构选中事件
		var orgLevelInternalId=that.attr("orgLevelInternalId");
		$("#orgSelectInput").attr({
			"orgLevelInternalId":orgLevelInternalId,
			text:$.trim(that.text()),
			value:$.trim(that.attr("orgId"))
		})
		$("#currentCoordinate").html("所选区域："+that.text());
	}
	switch(options){
		case 'setOrgs':
			setOrgs(orgIds);
			break;
		default:
			bindEvent();
	}
}