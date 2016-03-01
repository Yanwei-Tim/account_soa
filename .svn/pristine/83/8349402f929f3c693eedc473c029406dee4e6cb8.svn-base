$.fn.extend({
	typeSelect:function(options){
	
		var self=$(this);
		var showItem=self.nextAll("ul:first");
		
		var defaultOption={
			width:500,
			columns:2,
			hoverDuring: 300,
            outDuring: 200,
            hoverEvent: function(){
				$(".zc-sf").hide();
				showItem.show();
				showItem.bgiframe();
				self.next().addClass("ui-widget-color");
	        },
	        outEvent: function(){
	        	self.nextAll("ul:first").find(".close").click();
				self.next().removeClass("ui-widget-color");
	        },
			position:"bottom-right",		//top-left, top-right, bottom-left, bottom-right, center
			close:function(ids,labels){}
		};
		
		$.extend(defaultOption, options);
		var hoverTimer, outTimer;
		if(options.close){
			defaultOption.close=function(){
				options.close.call(null,self.getTypeSelectValues(),self.getTypeSelectLabels());
			};
		}
		function positonOption(){
			var showItemHeight=showItem.height();
			var selfWeight=self.parent().width();
			var selfHeight=self.parent().height();
			switch (defaultOption.position) {
				case "top-left":
				    showItem.css({
						top:-(showItemHeight+10)+"px",
						left:-(defaultOption.width-selfWeight+10)+"px"
					});
				    break;
				case "top-right":
				   showItem.css({
					   top:-(showItemHeight+10)+"px",
					   right:-(defaultOption.width-selfWeight+10)+"px"
				   });
				   break;
				case "bottom-left":
					showItem.css({
						top:(selfHeight-5)+"px",
						right:"0px"
					});
				   break;
				case "bottom-right":
				    showItem.css({
						top:(selfHeight-5)+"px",
						left:"0px"
					});
				    break;
			}
		}
		//加入jquery ui样式
		function addStyle(){
			showItem.find("li.top").addClass("ui-widget-border ui-widget-color ui-widget-header");
			showItem.addClass("ui-widget-border-on");
			showItem.find(".close").addClass("ui-icon ui-icon-closethick");
			showItem.parent().css({"position":"relative","z-index":"100"})
		}
		function itemize(){
			showItem.css("width",defaultOption.width);//设置宽度
			showItem.find("li label").width(defaultOption.width/defaultOption.columns-20);
			showItem.find("li").width(defaultOption.width/defaultOption.columns);
			showItem.find("li.top").width(defaultOption.width);
			showItem.find("li.top p").width(defaultOption.width-50);
		}
		
		function bindEventToTypeSelect(){
			self.nextAll("ul:first").find(".close").click(defaultOption.close);
			self.parent().hover(function(){
				clearTimeout(outTimer);
                hoverTimer = setTimeout(defaultOption.hoverEvent, defaultOption.hoverDuring);
			},function(){
				clearTimeout(hoverTimer);
                outTimer = setTimeout(defaultOption.outEvent, defaultOption.outDuring);
			})
			;
			self.nextAll("ul:first").find("input").click(function(){
				if($(this).attr("checked")==true || $(this).attr("checked")=="checked"){
					self.attr("checked",true);
				}
				if(showItem.find("input:checked").size()>0){
					self.attr("checked",true);
				}
				else{
					self.removeAttr("checked");
				}
			})
		}
		
		itemize();
		addStyle();
		positonOption();
		bindEventToTypeSelect();
		
	},
	getTypeSelectValues:function(){
		var itemId=$(this).attr("id");
		var showItem=$(this).nextAll("ul:first");
		$(".close").parent().parent().hide();
		var _check=showItem.find(":checkbox");
		var selectValue="";
		var selectText="";
		$(_check).each(function(){
			if($(this).attr("checked")){
				selectValue=selectValue+$(this).attr("value")+',';
				selectText=selectText+$(this).parent().text()+',';
			}
		});
		if(showItem.find("input:checked").size()>0){
			$("#"+itemId).attr("checked",true);
		}
		return selectValue;
	},
	setTypeSelectValues:function(ids){
		//设置选中的值
		var selectValue=ids.split(",");
		for(i=0;i<selectValue.length;i++){
			$(this).nextAll("ul:first").find("input[value='"+selectValue[i]+"']").attr("checked", true);
		}
		$(this).attr("checked",true);
	},
	getTypeSelectLabels:function(){
		var itemId=$(this).attr("id");
		var showItem=$(this).nextAll("ul:first");
		$(".close").parent().parent().hide();
		var _check=showItem.find(":checkbox");
		var selectValue="";
		var selectText="";
		$(_check).each(function(){
			if($(this).attr("checked")){
				selectValue=selectValue+$(this).attr("value")+',';
				selectText=selectText+$(this).parent().text()+'；';
			}
		});
		if(showItem.find("input:checked").size()>0){
			$("#"+itemId).attr("checked",true);
		}
		return selectText;
	},
	resetTypeSelectLabels:function(){
		//$(defaultOption.addTo).text(self.getTypeSelectLabels());
	}
});