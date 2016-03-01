;(function ($) {
	$.fn.personnelComplete = function(option){
		var self=$(this);
		var defaultOption={
			postDataFunction	: function(){},
			loadingImg  : RESOURCE_PATH+'/resource/js/selectInPlace/ajax-loader.gif',
			loadingText : "正在加载...",
			multiple	: true,
			url			: "",
			dynamic		: true,
			param 		: "tag",
			height      : "auto",
			width		: "auto",
			htmlMethod	: function (item){
				var imgHtml='';
				if(item.image!=undefined && item.image!=null && item.image!=""){
					imgHtml='<img src="'+item.image+'"/>';
				}else{
					imgHtml='<img src="'+RESOURCE_PATH+'/resource/js/selectInPlace/defaultHeader.jpg"/>';
				}
				var desc=item.desc;
				if(desc==null||desc==undefined){desc="";}
				return '<li style="width:'+this.width+'px">'+imgHtml+'<div class="title">'+item.label+'</div><div>&nbsp;'+desc+'</div></li>';
			},
			fullMessage : "不能选择更多...",
			valueMethod : function getValue(item){
		    	return item.value;
		    },
			max 		: 20,
			select:function(data){},
			itemClose   : function(data){},
			labelMethod : function(elem){
		    	defaultOption.select($(elem).data('data'));
		    	var itemHtml='<LI class="bit-box">'+elem.children(".title").text()+'<A class="closebutton" href="#"></A>';
		    	return itemHtml;
		    }
		};
		$.extend(defaultOption,option);
		$(this).selectInPlace(defaultOption);
		if(defaultOption.height!="auto"){
			$("#holder_"+self.attr("id")).css({"height":defaultOption.height+"px","overflow":"hidden","overflow-y":"auto"});
		};
	};
	$.fn.setPersonnelCompleteVal = function(option){
		var htmlObj = $('<li class="bit-box">'+option.label+'<a class="closebutton" href="#"></a></li>').data('data',option.value);
		var value=$(this).val();
		if(value!=""){
			$(this).val(option.value+","+value);
		}else{
			$(this).val(option.value);
		}
		$(this).next().prepend(htmlObj);
	};
	$.fn.resetPersonnelCompleteVal = function(option){
		var htmlObj = $('<li class="bit-box">'+option.label+'<a class="closebutton" href="#"></a></li>').data('data',option.value);
		var value=$(this).val();
		$(this).val(option.value);
		$(this).next().children(".bit-box").remove();
		$(this).next().prepend(htmlObj);
	};
	$.fn.clearPersonnelComplete = function(){
		var value=$(this).val();
		$(this).val("");
		$(this).next().children(".bit-box").remove();
	};
	$.fn.removePersonnelCompeleteVal = function(option){
		this.next().children(":contains('"+option.orgName+"')").remove();
		var value = String($(this).val());
		var temp = value.substring(value.indexOf(option.id));
		if(value.indexOf(',')!=-1 && temp.indexOf(',') != -1){
			var last = temp.substring(value.indexOf(',')+1);
			value = value.substring(0, value.indexOf(option.id)).concat(last);
			$(this).val(value);
		}else if(value.indexOf(',')!=-1 && temp.indexOf(',') == -1){
			$(this).val(value.substring(0, value.indexOf(option.id)-1));
		}else{
			$(this).val("");
		}
	};
	$.fn.removePersonnelCompeleteValue = function(val){
		var self=$(this);
		var valueArr=new Array()
		var thisValue=self.val();
		var newValue="";
		var thisId=val;
		var valueArr=thisValue.split(",");
		var thisIndex=-1;
		for(var i=0;i<valueArr.length;i++){
			if(thisId==valueArr[i]){
				thisIndex=i;
				continue;
			};
			newValue=newValue+valueArr[i]+",";
		};
		self.val(newValue.substring(0,newValue.length-1));
		self.next().children(".bit-box:eq("+thisIndex+")").remove();
	};
})(jQuery);