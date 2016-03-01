$.fn.workBenchGridFunction=function(option){
	var self=$(this);
	var defaultOption={
		datatype: "json",
		rowList:[10,15,20,30,50,80,100],
		gridview:true,
		viewrecords:true,
		jsonReader:{
			repeatitems:false,
			id:"0"
		},
		autowidth:true,
		scrollrows:true,
	   	sortname: 'id',
	    sortorder: "desc",
		pager: false,
		loadComplete:function(){
		},
		shrinkToFit:true,
		rowNum:7,
		height:170,
		showColModelButton:false
	}
	$.extend(defaultOption,option);
	self.jqGrid(defaultOption);
};
$.lunarInsert=function(o){
	var dfop={
		defaultDate:''
	}
	$.extend(dfop,o);
	var dateArr=dfop.defaultDate.split("-");
	var thisDate={
		year:dateArr[0],
		month:dateArr[1],
		day:dateArr[2]
	}
	var lunar=$.lunarCalendar(thisDate);
	var lunarDate=lunar.sx+'年'+lunar.lunarMonth+lunar.lunarDay;
	var week='星期'+lunar.week;
	var solarTerms=lunar.solarTerms==''?'':lunar.solarTerms;
	$(".lunarCalendar #today").html(parseInt(thisDate.day,10)+'<span>'+solarTerms+'</span>');
	$(".lunarCalendar #today_info").empty()
			.append('<div>'+lunar.sYear+'年'+lunar.sMonth+'月'+lunar.sDay+'日'+"&nbsp;&nbsp;"+week+'</div>')
			.append('<div>'+lunarDate+"<span>"+lunar.solarFestival+'</span></div>')
			//.append(lunar.dom);
},
$.fn.extend({
	workbenchModule:function(o,panelData){
		var self=$(this);
		var dfop={
			/*data:[
				{id:"informationTrain",title:"信息直通车",url:"/workBench/module/informationTrain.jsp"},
				{id:"basicInformation",title:"基础信息",url:"/sysadmin/menuManage/getLowLevelBaseInfoMenuListByPageList.action?ename=basicInformation"},
				{id:"statAnalyse",title:"统计分析",url:"/workBench/module/tabMenuList.jsp"},
				{id:"interactionManagement",title:"短信交流",url:"/workBench/module/tabMenuList.jsp"},
				{id:"issue",title:"事件处理",url:"/workBench/module/tabMenuList.jsp"},
				{id:"workingRecordMenu",title:"工作台帐",url:"/workBench/module/tabMenuList.jsp"}
			],
			*/
			url:'',
			custom:'customPanel',
			scroll:false,
			connectWith: ".column",
			cancel:'.portlet-content',
			delay: 200,
			forceHelperSize:true,
			tolerance: 'pointer',
			containment: '.workbench',//始钟只能在该容器中拖动
			dropOnEmpty: false,
			revert: true,
			start:function(event,ui){
				$(".ui-sortable-placeholder").width($(ui.item).width()).height($(ui.item).height());//辅助框宽高
			},
			stop:function(event,ui){
				$.ajax({
					type: "POST",
					url: "/patel/patelManage/buildConfiguration.action",
					data: { keyNames: $("#workbench").workbenchModule("getValue"),mode:'edit' },
					success:function(dom){

					}
				});
			}
		}
		$.extend(dfop,o);
		var positionPanel=function(){
			
		}
		var createPortlet=function(data){//创建面板
			var title=data.title;
			var content=data.content;
			var url=data.url;
			if($("#"+data.id)[0]){return false;}
			var $column=$("<div />").addClass("column").attr("id",data.id).data("data",data);
			var $portlet=$("<div />").addClass("portlet ui-widget ui-helper-clearfix");
			var $portletHeader=$("<div />").addClass("portlet-header ui-widget-header").text(data.title);
			var $portletHeaderIcon=$("<div class='panelBtn'><a href='javascript:;' class='panel-refresh' title='刷新'></a><a href='javascript:;' class='panel-set' title='设置'></a><a href='javascript:;' class='panel-max' title='最大化'></a><a href='javascript:;' class='panel-close' title='关闭'></a></div>");
			var $portletContent=$("<div />").addClass("portlet-content");
			if(data.maxurl!=undefined){
				$column.attr("maxurl",data.maxurl);
			}
			self.append($column.append($portlet));
			$portlet.append($portletHeader.append($portletHeaderIcon)).append($portletContent);
			$.ajax({
				type: "POST",
				url: "/patel/patelManage/buildConfiguration.action",
				data: { keyName:data.id,mode:'add' },
				success:function(flag){
					if(flag==true){
						$.messageBox({message:"添加标签成功"});
					}
					$.ajax({
						url:'/patel/patelManage/getTabPatelConfiger.action',
						data:{keyName:data.id},
						success:function(dom){
							$portletContent.html(dom);
							positionPanel();
						}
					})
				}
			});
		};
		var removePortlet=function(eName){//移除面板
			$.ajax({
				type: "POST",
				url: "/patel/patelManage/buildConfiguration.action",
				data: { keyName: eName,mode:'delete' },
				success:function(data){
					if(data==true){
						var thisIndex=$("#"+eName).index();
						if(thisIndex!=-1){
							$("#"+eName).remove();
							$("#"+dfop.custom+" input:checkbox[value='"+eName+"']").attr("checked", false);
						}
						$.messageBox({message:"删除标签成功"});
					}
				}
			})
		};
		/*
		var build=function(){//构建面板
			for(var i in dfop.data){
				if(typeof dfop.data[i] == 'object'){
					createPortlet(dfop.data[i]);
				}
			}
		}
		*/
		switch(o){
			case 'add'://添加面板
				if(typeof panelData=='object'){
					var checkbox=$("#"+dfop.custom+" input:checkbox[value='"+panelData.id+"']");
					if(checkbox.attr("maxurl")){
						panelData.maxurl=checkbox.attr("maxurl");
					}
					createPortlet(panelData);
					checkbox.attr("checked", true);
				}
				return false;
				break;
			case 'remove'://删除面板
				if(typeof panelData=='string'){
					removePortlet(panelData);
				}
				return false;
				break;
			case 'getValue'://获取组件Value
				var panelData= "";
				$('.column').each(function(i){
					panelData=panelData+","+$(this).data("data").id;
				});
				return panelData.substr(1);
				break;
			case 'getLength'://获取组件数量
				return $('.column').size();
				break;
		}
		var check=function(name){
			if(name=='' || name==undefined || name==null){
				return false;
			}
			return true;
		}
		var bindEvent=function(){
			$("#"+dfop.custom+" input[value=statAnalyse]").attr("disabled","disabled");
			$("#"+dfop.custom).delegate("input:checkbox","click",function(){//选择面板
				var id=$(this).attr("value");
				var title=$(this).parent().text();
				var url=$(this).attr("loadUrl");
				var parentTitle=$(this).closest("dl").parent().children("label").text();
				if(parentTitle!=''){
					parentTitle=parentTitle+'-';
				}
				if(!(check(id) && check(title))){
					alert("id,title不能为空");
					return false;
				}
				if($(this).attr("checked")){
					self.workbenchModule("add",{id:id,title:parentTitle+title});
				}else{
					self.workbenchModule("remove",id);
				}
			})
			self.delegate(".panel-refresh","click",function() {//刷新事件
				var thisPanel=$(this).closest(".column");
				var thisPanelDate=thisPanel.data("data");
				var thisTabs=$(this).closest(".column").find(".ui-tabs:visible");
				var thisIndex=thisTabs.find(".ui-tabs-selected").index();
				thisTabs.tabs("load",thisIndex);
				//thisPanel.find(".portlet-content").load(thisPanelDate.url)
			});
			self.delegate(".panel-set","click",function() {//设置事件
				var configPanel=$(this).closest(".portlet-header").next().find(".panelconfig");
				if(!configPanel.find(".configPanelClose")[0]){
					var arraw=$('<div class="tips-angle diamond"></div>');
					
					/*var configPanelClose=$('<a href="javascript:;" class="configPanelClose">X</a>');
					configPanelClose.click(function(){
						configPanel.hide();
					});*/
					configPanel.prepend(arraw);
				}
				if(configPanel.is(":visible")){
					configPanel.hide();
				}else{
					configPanel.show();
				}
			});
			self.delegate(".panel-max","click",function() {//最大化事件
				var thisPanel=$(this).closest(".column")
				var thisPanelData=thisPanel.data("data");
				var selectTab=thisPanel.find(".ui-tabs").find(".ui-tabs-nav").find("li.ui-tabs-selected").find("a");
				var maxUrl;
				var dialogTitle=selectTab.text();
				var thisDialogId=thisPanelData.id+selectTab.attr("id")+"Dialog";
				if($("#"+thisDialogId)[0]){
					$("#"+thisDialogId).dialog("open");
					return false;
				}
				if(selectTab.attr("maxurl")=='' || selectTab.attr("maxurl")==undefined){
					if(thisPanel.attr("maxurl")!='' || thisPanel.attr("maxurl")!=undefined){
						maxUrl=thisPanel.attr("maxurl");
					}
				}else{
					thisPanelData.title=thisPanelData.title+"-";
					maxUrl=selectTab.attr("maxurl");
				}
				function maxStatAnalyseStyle(){
					var panelSize=$(".maxStatAnalyse").size()/2;
					var panelHeight=$("#maxstat${type}").parent().height()/panelSize-20;
					$(".maxStatAnalyse").height(panelHeight)
				}
				$("body").append('<div id="'+thisDialogId+'" />');
				$("#"+thisDialogId).createDialog({//构建dialog
					url:maxUrl,
					shouldEmptyHtml:false,
					width:document.documentElement.clientWidth-10,
					height:document.documentElement.clientHeight-10,
					minWidth:300,
					minHeight:280,
					zIndex:999,
					modal:false,
					resizable:true,
				    title:thisPanelData.title+dialogTitle,
				    close:function(){
				    	$("#"+thisDialogId).remove();
				    },
				    resizeStop:function(){
				    	
				    }
				});
			});

			self.delegate(".panel-close","click",function() {//删除事件
				var thisPanelData=$(this).closest(".column").data("data");
				self.workbenchModule("remove",thisPanelData.id);
			});
		}()
		var init=function(){//获取组件value
			$('.column').each(function(i){
				var $portletHeaderIcon=$("<div class='panelBtn'><a href='javascript:;' class='panel-refresh' title='刷新'></a><a href='javascript:;' class='panel-set' title='设置'></a><a href='javascript:;' class='panel-max' title='最大化'></a><a href='javascript:;' class='panel-close' title='关闭'></a></div>");
				$(this).find(".portlet-header").append($portletHeaderIcon);
			});
			$("#"+dfop.custom).find("input:checkbox").each(function(i){
				var thisData={};
				thisData.id=$(this).attr("value");
				thisData.title=$(this).parent().text();
				thisData.url=$(this).attr("loadUrl");
				$("#"+thisData.id).data("data",thisData);
			});
			positionPanel();
		}();
		$(window).resize(function(){
			clearTimeout(window._timer);
			window._timer=setTimeout(positionPanel,500);
		})
		self.sortable(dfop).data("data",dfop.data);
		return self;
	},
	workBenchTabs:function(o,data){
		var self=$(this);
		var selfId=self.attr("id");
		var keyName=selfId.substr(0,selfId.length-4);
		var dfop={
			tabTemplate: "<li><a href='#{href}'>#{label}</a></li>",
			ajaxOptions: {
				error: function( xhr, status, index, anchor ) {
					$( anchor.hash ).html("读取页面出错了。");
				}
			},
			cache:false
		}
		$.extend(dfop,o);
		self.find("ul:first").sortable({
			//delay: 10,
			placeholder: "ui-state-highlight",
			start:function(event,ui){
				$(".ui-tabs .ui-state-highlight").width($(ui.item).width()).height($(ui.item).height());//辅助框宽高
			},
			stop:function(event,ui){
				var thisValue ="";
				self.find("a").each(function(){
					thisValue=thisValue+","+$(this).attr("id");
				})
				$.ajax({
					type: "POST",
					url: "/patel/patelManage/buildTabConfiguration.action",
					data: { keyName:keyName,keyNames:thisValue.substr(1),mode:'edit' },
					success:function(data){
						if(data==true){
							//$.messageBox({message:"添加标签成功"});
						}
					}
				});
			}
		});
		switch(o){
			case 'add':
				if(typeof data=='object' && data!=undefined){
					var dom=self.find("li").filter(function(index) {//获取该dom元素节点
						  return $(this).text() == data.title;
					});
					if(!dom[0]){
						self.tabs("add",data.url,data.title);
					}
				}
				return false;
				break;
			case 'remove':
				if(typeof data=='string'){
					var dom=self.find(".ui-tabs-nav").find("li").filter(function(index) {//获取该dom元素节点
						var thisData=$.trim(data);
						var thisText=$.trim($(this).text());
						return thisText == thisData;
					});
					if(dom.index()!=-1){
						self.tabs("remove",dom.index());
					}
				}
				return false;
				break;
			case 'getValue'://获取组件Value
				var panelData="";
				self.find(".ui-tabs-nav").find("li").each(function(i){
					var thisText=$(this).text();
					var dom=$("#"+selfId+"Config").find("li").filter(function(index) {//获取该dom元素节点
						  return $(this).text() == thisText;
					});
					panelData=panelData+","+dom.eq(0).find("input:checkbox").attr("value");
				});
				return panelData.substr(1);
				break;
			case 'getLength'://获取组件数量
				var panelDataNum=0;
				self.find(".ui-tabs-nav").find("li").each(function(i){
					panelDataNum++;
				});
				return panelDataNum;
				break;
		}
		self.tabs(dfop);
		$("#"+selfId+"Config li input").click(function(){//配置层checkbox选中事件
			var title=$(this).parent().text();
			var loadUrl=$(this).attr("loadUrl");
			var maxUrl=$(this).attr("maxurl");
			var tabKeyName=$(this).val();
			var selectCheckbox=$("#"+selfId+"Config li input:checked");
			if(selectCheckbox.size()>4){
				$.messageBox({level:"error",message:"标签最多只能添加4个"});
				return false;
			}
			if($(this).attr("checked")){
				$.ajax({
					type: "POST",
					url: "/patel/patelManage/buildTabConfiguration.action",
					data: { keyName:keyName,tabKeyName:tabKeyName,mode:'add' },
					success:function(data){
						if(data==true){
							$("#"+selfId).workBenchTabs("add",{title:title,url:loadUrl});
							var dom=self.find(".ui-sortable").find("a").filter(function(index) {//获取该dom元素节点
								var thisData=$.trim(title);
								var thisText=$.trim($(this).text());
								return thisText == thisData;
							});
							if(dom.index()!=-1){
								dom.attr("maxurl",maxUrl);
							}
							$.messageBox({message:"添加标签成功"});
						}
					}
				});

			}else{
				$("#"+selfId).workBenchTabs("remove",title);
				if($("#"+selfId).workBenchTabs("getLength")==0){
					$("#workbench").workbenchModule("remove",keyName);
				}
				$.ajax({
					type: "POST",
					url: "/patel/patelManage/buildTabConfiguration.action",
					data: { keyName:keyName,tabKeyName:tabKeyName,mode:'delete'},
					success:function(data){
						if(data==true){
							$.messageBox({message:"移除标签成功"});
						}
					}
				});
			}
		})
	},
	searchSelect:function(options){
		var $self=$(this);
		var $options=$self.children("option");
		var $downlist=$("<div class='updownList'></div>");
		var $createDl=$("<dl/>");
		var $createDt=$("<dt/>");
		var $createDd=$("<div class='updwonlist-Content'></div>");
		var $createUl=$("<ul/>");
		var $c=$options.first().text();

		$self.after($downlist);
		$downlist.append($createDl);
		$createDl.append($createDt);
		$createDl.append($createDd);
		$createDd.append($createUl);
		$createDt.text($c);

		var defaultOptions={
				updownlist:'.updownList',
				updownlistC:'.updwonlist-Content',
				current:'cur',
				change:function(thisText,thisValue){

				}
		}
		$.extend(defaultOptions,options);
		var init=function(){
			$options.each(function(index){
				var $createLl=$("<li/>");
				var thisValue=$(this).attr("value");
				var $con=$(this).text();
				$createUl.append($createLl);
				$createLl.text($con).data("value",thisValue);
				$createLl.click(function(){
					var thisText=$(this).text();
					var thisValue=$(this).data("value");
					$(this).addClass(defaultOptions.current).siblings().removeClass(defaultOptions.current);
					$createDt.text($(this).text());
					$self.val(thisValue);
					$createDd.hide();
					defaultOptions.change(thisText,thisValue);
				})
			 })
		}();

		var clickUnit=function(){
			$createDt.bind("click",function(e){
				if($createDd.is(":visible")){
					 $createDd.hide();
				}else{
					 $createDd.show();
				}
			})

			$(document).on("click",function(e){
				var $target=$(e.target);
				if(!($target.is(defaultOptions.updownlistC) || $target.closest(defaultOptions.updownlistC)[0] || $target.closest(defaultOptions.updownlist)[0])){
					$createDd.hide();
				}
			})
		}()
	},
	workMemoPicker:function(o){
		var self=$(this);
		var selfId=self.attr("id");
		Array.prototype.uniq = function() {
	        var temp = {}, len = this.length;

	        for(var i=0; i < len; i++)  {
	            if(typeof temp[this[i]] == "undefined") {
	                temp[this[i]] = 1;
	            }
	        }
	        this.length = 0;
	        len = 0;
	        for(var i in temp) {
	        	i=i.replace(/0\d/g, function(num){//只在日期时作用
					return num.substring(1);
				});

	            this[len++] = i;
	        }
	        return this;
	    }
		var loadDate=function(thisYear,thisMonth){
			$.ajax({
				url:'/workMemo/workMemoManage/getDaysByUserIdAndDate.action',
				type:'POST',
				data:{year:thisYear,month:thisMonth},
				success:function(date){
					//var date=[12,27];//此处需要发送ajax请求，到后台load有数据的date
					if(date!=null && date!=undefined){
						/*for(var i=0;i<date.length;i++){
							var temp=date[i];
							if(temp.charAt(0)=='0'){
								date[i]=temp.substring(1);
							}
						}*/
						date.uniq();
					}

					setTimeout(function(){
						setDatePicker(date);
					},200);
				}
			});
			
		}
		var upDate=function(){
			var selfDate=[];
			self.find("a.hasData").each(function(){
				var thisDate=$(this).text();
				selfDate.push(thisDate);
			})
			self.data("data",selfDate);
		}
		var dfop={
			showButtonPanel: false,
			yearRange: '1900:2030',
			onChangeMonthYear:function(year, month, inst){
				loadDate(year,month);
			},
			onSelect:function(dateText, inst){
				$(".workMemoTip").remove();
				var dom=$(this).find("a").filter(function(index) {//获取该dom元素节点
					  return $(this).text() == inst.selectedDay;
				});
				var thisYearDate=$("#workMemo").data("data");//获取当前有数据的日期
				setTimeout(function(){//处理与jquery ui的冲突问题
					setDatePicker(thisYearDate);
				},10);
				var dateArr=dfop.defaultDate.split("-");
				var thisDate={
					year:dateArr[0],
					month:dateArr[1],
					day:dateArr[2]
				}
				var todayTime=new Date(thisDate.year,parseInt(thisDate.month, 10)-1,thisDate.day).getTime();
				var selectTime=new Date(inst.currentYear,inst.currentMonth,inst.currentDay).getTime();
				if(dom.data("hasData")){//当前无数据，则返回
					$(".addworkmemos").hide();
					initDatePickerDom(dom,dateText,inst);
				}else{
					if(todayTime<=selectTime){
						showAddworkmemos(inst);
					}else{
						$(".addworkmemos").hide();
					}
				};
				var instDate=inst.currentYear+'-'+(inst.currentMonth+1)+'-'+inst.currentDay;
				$(".addworkmemos #addWorkDate").text(inst.currentYear+'年'+(inst.currentMonth+1)+'月'+inst.currentDay+'日');
				$(".addworkmemos #workDate").val(instDate);
				$(".addworkmemos #text").val("");
				$.lunarInsert({defaultDate:instDate});
			}
		}
		$.extend(dfop,o);
		var initDatePickerDom=function(dom,dateText,inst){
			var workMemoTip=$("<div />").addClass("workMemoTip");
			var workMemoTipCtt=$("<ul />");
			var appBtn=$('<a href="javascript:;">继续新增备忘</a>');
			workMemoTip.appendTo("body").append('<div class="tips-angle diamond"></div>');

			$.ajax({
				url:'/workMemo/workMemoManage/getMemoContentsByUserIdAndAddMemoDate.action',
				type:'POST',
				data:{date:inst.currentYear+"-"+((inst.currentMonth+1)<10 ? ("0"+(inst.currentMonth+1)) : (inst.currentMonth+1))+"-"+(inst.currentDay<10 ? ("0"+inst.currentDay) : inst.currentDay)},
				success:function(data){
					for(var i=0;i<data.length;i++){
						workMemoTipCtt.append('<li >'+data[i].memoContents+'<a href="javascript:;" id="'+data[i].id+'">删除</a></li>');
					}
				}
			});


			//tip定位以及插入数据
			workMemoTip.append('<h1 class="title">'+dateText+'</h1>').append(workMemoTipCtt).css({
				top:(dom.offset().top-27)+"px",
				left:(dom.offset().left+28)+"px"
			});
			var dateArr=dfop.defaultDate.split("-");
			var todayTime=new Date(dateArr[0],parseInt(dateArr[1],10)-1,dateArr[2]).getTime();
			var selectTime=new Date(inst.currentYear,inst.currentMonth,inst.currentDay).getTime();
			if(selectTime>=todayTime){
				workMemoTip.append(appBtn);
			}

			appBtn.click(function(){
				showAddworkmemos(inst);
			})
			workMemoTipCtt.delegate("a","click",function() {//删除事件
				var that=$(this);
				var id=that.attr("id");
				$.confirm({
					title:"确认删除",
					message:"确定要删除吗?一经删除将无法恢复",
					okFunc: function() {
						$.ajax({
							url:"/workMemo/workMemoManage/deleteWorkMemoById.action?workMemoId="+id,
							type:'POST',
							success:function(data){
								$.messageBox({message:"已经成功删除该备忘录!"});
								var today=new Date(dfop.defaultDate).getFullYear()+'-'+(new Date(dfop.defaultDate).getMonth()+1)+'-'+new Date(dfop.defaultDate).getDate();
								var currentDay=$(".addworkmemos #workDate").val();
								if(today==currentDay){
									$('.today ul li#'+data).remove();
								}
								that.parents("li").remove();
								if($(".workMemoTip ul li").size()<=0){
									var dom=$("#workMemo").find("a").filter(function(index) {
										return $(this).text() ==currentDay.split("-")[2];
									});
									dom.removeClass("hasData").data("hasData",false);
									upDate();
									$(".workMemoTip").hide();
								}
							}
						});
					}
				});
			})
		}
		var showAddworkmemos=function(inst){
			$(".workMemoTip").remove();
			$(".addworkmemos").show();
		}
		var setDatePicker=function(data){
			for(var i in data){
				if(typeof data[i] != 'function' && data[i]!=undefined){
					var thisDate=data[i];
					var dom=$("#"+selfId).find("a").filter(function(index) {
						  return $(this).text() == thisDate;
					});
					dom.addClass("hasData").data("hasData",true);
				}
			}
			upDate();
		}
		switch(o){
			case 'update':
				upDate();
				return false;
				break;
		}
		$("#workMemo").datePicker(dfop);
		var dateArr=dfop.defaultDate.split("-");
		loadDate(dateArr[0],dateArr[1]);
	},
	informationTrain:function(){
		var self=$(this);
		var selfId=self.attr("id");
		$("#"+selfId+"Config").delegate("input:checkbox","click",function(){
			var id=$(this).attr("value");
			var text=$(this).parent().text();
			var url=$(this).attr("loadUrl");
			var imageUrl=$(this).attr("imageUrl");
			var thisIcon=$('<a />').attr("href",url).attr("class","icon").attr("id",id).append('<img src="'+imageUrl+'" />').append(text);
			if($(this).attr("checked")){
				$(".informationTrain #"+id).remove();
				$(".informationTrain").append(thisIcon);
				$.ajax({
					type: "POST",
					url: "/patel/patelManage/buildTabConfiguration.action",
					data: { keyName:'informationTrain',tabKeyName:id,mode:'add' },
					success:function(data){
						if(data==true){
					//		$.messageBox({message:"添加标签成功"});
						}
					}
				});
			}else{
				$(".informationTrain #"+id).remove();
				if(getInformationTrainValue()==""){
					$("#workbench").workbenchModule("remove",'informationTrain');
				}
				$.ajax({
					type: "POST",
					url: "/patel/patelManage/buildTabConfiguration.action",
					data: { keyName:'informationTrain',tabKeyName:id,mode:'delete' },
					success:function(data){
						if(data==true){
					//		$.messageBox({message:"添加标签成功"});
						}
					}
				});
			}
		})

		self.sortable({
			revert: true,
			placeholder: "ui-state-highlight",
			start:function(event,ui){
				$("#informationTrainTabs .ui-state-highlight").width($(ui.item).width()).height($(ui.item).height());//辅助框宽高
			},
			stop:function(){
				$.ajax({
					type: "POST",
					url: "/patel/patelManage/buildTabConfiguration.action",
					data: { keyName:'informationTrain',keyNames:getInformationTrainValue(),mode:'edit' },
					success:function(data){
						if(data==true){
							$.messageBox({message:"移动菜单成功"});
						}
					}
				});
			}
		});
		function getInformationTrainValue(){
			var thisValue ="";
			   self.find("a").each(function(){
			   thisValue=thisValue+","+$(this).attr("id");
			})
			return thisValue.substr(1);
		}
	}
});