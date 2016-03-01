
function doActualHouseAction(dailogName,id,data){
	$("#"+dailogName).data("houseInfo",{id:id});
	var button=$("#"+dailogName).data("button");
	if("next"==button){
		$("#"+dailogName).createFrameDialog("next");
		var next=$("#"+dailogName).data("data")[$("#"+dailogName).data("index")].buttons.next;
		if(typeof(next) == "function"){
			next(data);
		}
	}else if("prev"==button){
		var prev=$("#"+dailogName).data("data")[$("#"+dailogName).data("index")].buttons.prev;
		if(typeof(prev) == "function"){
			prev(data);
		}
	}else if("save"==button){
		$("#"+dailogName).createFrameDialog("save");
		var save=$("#"+dailogName).data("data")[$("#"+dailogName).data("index")].buttons.save;
		if(typeof(save) == "function"){
			save(data);
		}
	}else if("saveContinue"==button){
		$("#"+dailogName).data("population",{});
		$("#"+dailogName).data("location",{});
		$("#"+dailogName).createFrameDialog("saveContinue",{},"location");
		var saveContinue=$("#"+dailogName).data("startData")[0].buttons.saveContinue;
		if(typeof(saveContinue) == "function"){
			saveContinue(data);
		}
	}
}

function doAction(dailogName,id,data){
	$("#"+dailogName).data("population",{id:id});
	$("#"+dailogName).data("location",{id:id});
	var button=$("#"+dailogName).data("button");
	if("next"==button){
		$("#"+dailogName).createActualPopulationDialog("next");
		var next=$("#"+dailogName).data("data")[$("#"+dailogName).data("index")].buttons.next;
		if(typeof(next) == "function"){
			next(data);
		}
	}else if("prev"==button){
		var prev=$("#"+dailogName).data("data")[$("#"+dailogName).data("index")].buttons.prev;
		if(typeof(prev) == "function"){
			prev(data);
		}
	}else if("save"==button){
		$("#"+dailogName).createActualPopulationDialog("save");
		var save=$("#"+dailogName).data("data")[$("#"+dailogName).data("index")].buttons.save;
		if(typeof(save) == "function"){
			save(data);
		}
	}else if("saveContinue"==button){
		$("#"+dailogName).data("population",{});
		$("#"+dailogName).data("location",{});
		$("#"+dailogName).createActualPopulationDialog("saveContinue");
		var saveContinue=$("#"+dailogName).data("startData")[0].buttons.saveContinue;
		if(typeof(saveContinue) == "function"){
			saveContinue(data);
		}
	}
}

function doLocationAction(dailogName,id,data,locationName){
	$("#"+dailogName).data("population",{id:id});
	$("#"+dailogName).data("location",{id:id,locationName:locationName});
	var button=$("#"+dailogName).data("button");
	if("next"==button){
		$("#"+dailogName).createFrameDialog("next",{},"location");
		var next=$("#"+dailogName).data("data")[$("#"+dailogName).data("index")].buttons.next;
		if(typeof(next) == "function"){
			next(data);
		}
	}else if("prev"==button){
		var prev=$("#"+dailogName).data("data")[$("#"+dailogName).data("index")].buttons.prev;
		if(typeof(prev) == "function"){
			prev(data);
		}
	}else if("save"==button){
		$("#"+dailogName).createFrameDialog("save",{},"location");
		var save=$("#"+dailogName).data("data")[$("#"+dailogName).data("index")].buttons.save;
		if(typeof(save) == "function"){
			save(data);
		}
	}else if("saveContinue"==button){
		$("#"+dailogName).data("population",{});
		$("#"+dailogName).data("location",{});
		$("#"+dailogName).createFrameDialog("saveContinue",{},"location");
		var saveContinue=$("#"+dailogName).data("data")[$("#"+dailogName).data("index")].buttons.saveContinue;
		if(typeof(saveContinue) == "function"){
			saveContinue(data);
		}
	}
}

;(function ($) {
	$.fn.createFrameDialog=function(o,item,type){
		if(!type){
			type="location";
		}
		var self=$(this);
		var submitFun=function(container){
			$(".dlgUrlBox form:first").submit();
		}
		function selectLeftMenu(index){
			$(".dlgLeftMenu").children("li").eq(index).addClass("cur").siblings().removeClass("cur");
			$(".dlgUrlBoxLoading").show();
		}
		function leftMenuClick(that,adddata){
			var clickFun=function(){
				var index=$(that).index();
				self.data("index",index);
				operateAfterButtonClick(index,adddata);
				selectLeftMenu(index);
			}
			clickFun();
		}
		var urlLoad=function(url,index){
			var context=self.data(type);
			var postData={};
			if(null!=self.data("model") && "add"==self.data("model") && context.id!=null && isNaN(context.id)){
				postData={"cacheId.id":context.id};
			}else if(null!=self.data("model") && "add"==self.data("model") && url.indexOf(type+".id")<0){
				postData=eval("({'"+type+".id':context.id,'"+type+".name':context.locationName})");
			}else if(context.id!=null && isNaN(context.id)){
				postData={"cacheId.id":context.id};
			}
			if(self.data("houseInfo")&&self.data("houseInfo").id){
				postData = $.extend(postData,{"cacheId.houseInfoId":self.data("houseInfo").id});
			}
			postData = $.extend(postData,{"contextId":$("#contextId").val()});
			$.ajax({
				url:url,
				async:false,
				cache:false,
				data:postData,
				success:function(data){
					$(".dlgUrlBox").html("");
					$(".dlgUrlBox").html(data);
					selectLeftMenu(index);
					$(".dlgUrlBoxLoading").hide();
					self.data("index",index);
					$(".dlgUrlBox input,.dlgUrlBox select,.dlgUrlBox textarea").one("focusin",function(){
						$(this).attr("defaultValue",$(this).attr("value"));
					});
					$(".dlgUrlBox input,.dlgUrlBox select,.dlgUrlBox textarea").focusout(function(){
						if($(this).attr("defaultValue")!=$(this).attr("value")){
							$(".dlgUrlBox form").data("update",true);
						}
					})
				}
			})
		}
		var btnFun=function(buttonsList,thisdata,index){
			if(thisdata[index].buttons.prev != undefined){
				var prevBtn=$('<input type="button" />').attr("value","上一步").addClass("prevBtn");
				prevBtn.bind("click",function(){
					self.data("button","prev");
					if(type=="population")
						self.createActualPopulationDialog("prev");
					else
						self.createFrameDialog("prev");
				})
				buttonsList.append(prevBtn);
			}
			if(thisdata[index].buttons.next != undefined){
				var nextBtn=$('<input id="_nextBtn" type="button" />').attr("value","下一步");
				nextBtn.bind("click",function(){
					self.data("button","next");
					submitFun();
					var next=$(self).data("data")[$(self).data("index")].buttons.next;
					if(typeof(next) == "function"){
						next();
					}
				})
				buttonsList.append(nextBtn);
			}
			if(thisdata[index].buttons.save != undefined){
				var saveBtn=$('<input id="_saveBtn" type="button" />').attr("value","保存并关闭").addClass("saveBtn");
				saveBtn.bind("click",function(){
					self.data("button","save");
					submitFun();
					var save=$(self).data("data")[$(self).data("index")].buttons.save;
					if(typeof(save) == "function"){
						save();
					}
				})
				buttonsList.append(saveBtn);
			}
			if(thisdata[index].buttons.saveContinue != undefined){
				var saveContinueBtn=$('<input id="_saveContinueBtn" type="button" />').attr("value","保存并继续").addClass("saveContinueBtn");
				saveContinueBtn.bind("click",function(){
					self.data("button","saveContinue");
					$("#contextId").val(Math.uuid());
					submitFun();
					var saveContinue=$(self).data("data")[$(self).data("index")].buttons.saveContinue;
					if(typeof(saveContinue) == "function"){
						saveContinue();
					}
				})
				buttonsList.append(saveContinueBtn);
			}
		}
		var menuInit=function(data,leftMenu){
			var thisData=data;
			for(var i in thisData){
				if(!thisData[i].hidden){
					var thisItem=$('<a href="javascript:;"></a>').text(thisData[i].title);
					if( thisData[i].title!= undefined && thisData[i].title!=null && thisData[i].title!="" ){
						var li = $("<li></li>").append(thisItem)
						leftMenu.append(li);
						li.data("data",thisData[i]);//缓存data
					}
				}
			}
		};
		
		var operateAfterButtonClick=function(index){
			var buttonList=$(".dlgBtnList");
			$(".dlgUrlBoxLoading").show();
			if(index>0){
				$(".PictureUpload").hide();
				$(".shadow").hide();
			}else{
				$(".PictureUpload").show();
			}
			var data = self.data("data");
			urlLoad(data[index].url,index);
			buttonList.empty();
			btnFun(buttonList,data,index);
		}
		
		function initDialog(o){
			self.data(type,{});
			self.data("houseInfo",{});
			var dfop = {
				data:[],
				width:840,
				height:600,
				model: "add",
				title:"",
				buttons:{},
				url:PATH+'/common/maintain.jsp?type='+type,
				loadComplete:function(){
					var leftMenu=$(".dlgLeftMenu");
					var buttonsList=$(".dlgBtnList");
					btnFun(buttonsList,dfop.data,0);
					menuInit(dfop.data,leftMenu)
					leftMenu.children().first().addClass("cur first");
					/*leftMenu.children().click(function(){
						leftMenuClick(this);
					})*/
					urlLoad(dfop.data[0].url);
				}
			}
			$.extend(dfop, o);
			self.data("data",dfop.data);
			if(o.model==undefined){
				self.data("model",dfop.model);
			}else{
				self.data("model",o.model);
			}
			self.data("index",0);
			self.createDialog(dfop);
		}
		
		function showMenu(index){
			
		}
		
		function hideMenu(index){
			
		}
		
		function doOperate(o,thisData,type){
			var thisIndex=$(".dlgLeftMenu li.cur").index();
			var sunIndex=$(".dlgLeftMenu li").size();
			switch(o){
				case 'next':
					if(thisIndex+1<sunIndex){
						operateAfterButtonClick(thisIndex+1);
						$(".dlgLeftMenu li").eq(thisIndex).addClass("complete");
					}
					break;
				case 'prev':
					if(thisIndex-1>=0){
						operateAfterButtonClick(thisIndex-1);
					}
					break;
				case 'save':
					self.dialog("close");
					break;
				case 'saveContinue':
					self.data("data",self.data("startData"));
					operateAfterButtonClick(0);
					if(type=="population"){
						$("#headerImg",self).attr("src",PATH+"/resource/images/head.png");					
					}else if(type=="location"){
						$("#headerImg",self).attr("src",PATH+"/resource/images/locationHead.png");					
					}
					
					$("#fileToUpload",self).val("");
					$(".dlgLeftMenu li").removeClass("complete");
					//当点击保存继续按钮时清空与该人口关联的人口类型业务标签
					$(".dlgLeftMenu li a[id=1]").parent().remove();
					break;
				case 'show':
					break;
				case 'hide':
					break;
				case 'add':
					if(typeof thisData=='object'){
						var data=self.data("data");
						var leftMenu=$(".dlgLeftMenu");
						var buttonsList=$(".dlgBtnList");
						for(var i=0;i<thisData.length;i++){
							if($('.dlgLeftMenu li:contains('+thisData[i].title+')')[0]!=undefined) {
								return;
							}
							var sum=leftMenu.find("li").size();
							var index=thisData[i].index==undefined ? sum : thisData[i].index;
							var thisItem=$('<a id='+index+' href="javascript:;"></a>').text(thisData[i].title);
							var li = $("<li></li>").append(thisItem);
							leftMenu.find("li").eq(index-1).after(li);
							/*li.click(function(event){
								leftMenuClick(this,thisData[i]);
								buttonsList.empty();
								btnFun(buttonsList,thisData,0);
							});
							*/
							data.splice(index,0,thisData[i]);
						}
						self.data("data",data);
					} 
					break;
				case 'remove':
					if(typeof thisData=='string'){
						if($('.dlgLeftMenu li:contains('+thisData+')')[0]==undefined) {
							return;
						}
						var thisIndex=$('.dlgLeftMenu li:contains('+thisData+')').index();//获取该行的index
						var data=self.data("data");
						$('.dlgLeftMenu li:contains('+thisData+')').remove();//删除该行
						data.splice(thisIndex,1);//删除数组
						self.data("data",data);//缓存数组到data中
					}
					break;
				case 'updata':
					if(thisData != undefined){
						var buttonsList=$(".dlgBtnList");
						for(var i=0;i<thisData.length;i++){
							var thisDom=$('.dlgLeftMenu li:contains('+thisData[i].title+')');
							var domData=thisDom.data("data");
							domData.buttons=thisData[i].buttons;
							thisDom.data("data",domData);
							if(thisIndex==thisDom.index()){
								buttonsList.empty();
								btnFun(buttonsList,self.data("data"),thisIndex);
							}
						}
					}
					break;
			}
		}
		if(typeof(o)!="string"){
			initDialog(o);
			if(o.data!=undefined){
				var startData=new Array();
				for(var i=0;i<o.data.length;i++){
					startData.push(o.data[i]);
				}
				self.data("startData",startData);
			}
		}else{
			doOperate(o,item,type);
		}
	};
	$.fn.createActualPopulationDialog = function(o,item) {
		$(this).createFrameDialog(o,item,"population");
	};

})(jQuery);