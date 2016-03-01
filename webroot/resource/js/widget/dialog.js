;(function ($) {
	$.createDialog = function(o) {
		var dfop = {
			id:"",
			title : "",
			width : 300,
			height : 400,
			modal : true,
			stack : true,
			resizable : false,
			bgiframe: true,
			position:'center',
			dragStart:function(){
				$(".tip-error").remove();
				$(".tip-yellowsimple").remove();
				$("*[createMsg='true']").attr("createMsg","false");
			},
			close:function(){
				docObj.empty();
			    docObj.dialog("destroy");
			    docObj.remove();
			},
			url:false
		}
		$.extend(dfop, o);
		var docObj=$("<div id='"+dfop.id+"'/>");
		docObj.appendTo("body");
		if(o.close){
			dfop.close = function(){
				o.close.call(dfop.close,docObj);
				docObj.empty();
			    docObj.dialog("destroy");
			    docObj.remove();
			    $(".tip-error").remove();
				$(".tip-yellowsimple").remove();
				$("*[createMsg='true']").attr("createMsg","false");
			};
		}
	    getAjax(dfop.url,docObj);
		function getAjax(url,docObj) {
			docObj.empty();
			docObj.html('<div style="margin-top:40px;margin-left:'+((dfop.width-100)/2)+'px"><img src="'+RESOURCE_PATH+'/resource/images/loading.gif" alt="加载中..." /></div>');
			docObj.bgiframe();
			docObj.dialog(dfop);
			docObj.width(docObj.width()).css({"overflowX":"hidden"});
			$.ajax( {
				url : url,
				cache: false,
				success : function(result) {
					proccessLoginResult(result,function(){docObj.html(result)});
					$(".form-txt",docObj).bind("focusin",function(){$(this).addClass("ui-widget-border-on");});
					$(".form-txt",docObj).bind("focusout",function(){$(this).removeClass("ui-widget-border-on");});
				}
			});
		}
	};
	$.confirm=function(o){
		var dfop={
		    level: "info",//TODO 确认 warn 警告,alert,info
		    message: "",
		    title:"确认",
		    okFunc: false,
		    cancelFunc:false,
		    cancelBtnName:"取消",
		    okbtnName :"确定"
		};
		$.extend(dfop, o);
		var div=
			'<div title="'+dfop.title+'" >'
			+'<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>'
			+dfop.message
			+'</p>'
			+'</div>';
		var buttonStr="{'"+dfop.okbtnName+"':function(){" +
		'if(o.okFunc){'+
		'o.okFunc.call(dfop.okFunc,$(this));'+
		'}'+
		"$(this).dialog('destroy');$(this).remove();"+
		"},'"+dfop.cancelBtnName+"':function(){" +
		'if(o.cancelFunc){'+
		'o.cancelFunc.call(dfop.cancelFunc,$(this));'+
		'}'+
		"$(this).dialog('destroy');$(this).remove();"+
		"}}";

		var buttons=eval("("+buttonStr+")");

		dfop = {
			autoOpen: true,
			resizable : false,
			modal: true,
			buttons: buttons
		};
		$.extend(dfop, o);

		$(div).dialog(dfop);
	};
	$.notice=function(o){
		var dfop={
			level: 'info',//warn,alert,info
		    okbtnName: "确定",
		    title:'警告',
		    message: "",
		    okFunc: false
		};
		$.extend(dfop, o);
		var div=
			'<div title="'+dfop.title+'">'
			+'<p><span class="ui-icon ui-icon-alert" style="float:left; margin-right: .3em;"></span>'
			+dfop.message
			+'</p>'
			+'</div>';
		var buttonStr="{'"+dfop.okbtnName+"':function(){" +
		'if(o.okFunc){'+
		'o.okFunc.call(dfop.okFunc,$(this));'+
		'}'+
		"$(this).dialog('destroy');$(this).remove();"+
		"}}";

		var buttons=eval("("+buttonStr+")");

		dfop = {
				autoOpen: true,
				resizable : false,
				modal: true,
				buttons: buttons
		};
		$.extend(dfop, o);

		$(div).dialog(dfop);
	};
	$.yesNoCancelDlg=function(o){
		var dfop={
			level: 'info',//warn,alert,info
		    yesFunc: false,
		    noFunc: false,
		    cancelFunc:false,
		    message: "",
		    title:"",
		    yesBtnName: "是",
		    noBtnName:"否",
		    cancelBtnName: "取消"
		};
		$.extend(dfop, o);
		var div=
			'<div title="'+dfop.title+'">'
			+'<p><span class="ui-icon ui-icon-alert" style="float:left;margin-right: .3em;"></span>'
			+dfop.message
			+'</p>'
			+'</div>';
		var buttonStr="{'"+dfop.yesBtnName+"':function(){" +
		'if(o.yesFunc){'+
		'o.yesFunc.call(dfop.yesFunc,$(this));'+
		'}'+
		"$(this).dialog('destroy');$(this).remove();"+
		"},'"+dfop.noBtnName+"':function(){" +
		'if(o.noFunc){'+
		'o.noFunc.call(dfop.noFunc,$(this));'+
		'}'+
		"$(this).dialog('destroy');$(this).remove();"+
		"},'"+dfop.cancelBtnName+"':function(){" +
		'if(o.cancelFunc){'+
		'o.cancelFunc.call(dfop.cancelFunc,$(this));'+
		'}'+
		"$(this).dialog('destroy');$(this).remove();"+
		"}}";

		var buttons=eval("("+buttonStr+")");

		dfop = {
			autoOpen: true,
			resizable : false,
			modal: true,
			buttons: buttons
		};
		$.extend(dfop, o);

		$(div).dialog(dfop);
	};
	$.fn.createDialog = function(o) {
	    var selfId=this.selector.substring(1,this.selector.length);
	    if(this[0]==undefined){
	    	$("body").append('<div id="'+selfId+'"></div>');
	    }
	    var self=$("#"+selfId);
		var dfop = {
			title : "",
			width : 300,
			height : 400,
			modal : true,
			resizable : false,
			close : function(){},
			postData:{},
			stack : true,
			url:false,
			position:'center',
			shouldEmptyHtml:true,
			dragStart:function(){
				$(".tip-error").remove();
				$(".tip-yellowsimple").remove();
				$("*[createMsg='true']").attr("createMsg","false");
				self.css("opacity",'0.1');
				self.parent().removeClass("fadeIn");
			},
			dragStop:function(){
				self.css("opacity",'1');
			},
			loadComplete:function(){
			},
			open:function(){
				self.parent().addClass("fadeIn");
			}
		};
		$.extend(dfop, o);
		dfop.close=function(){
		    if(o.close){
			   o.close.call(dfop.close,$(this));
		    }
		    if(dfop.shouldEmptyHtml){
		    	$(this).empty();
		    }
		    $(".tip-error").remove();
		    $(".tip-yellowsimple").remove();
		    $("*[createMsg='true']").attr("createMsg","false");
		    self.parent().removeClass("fadeIn");
		    self.dialog("destroy");
		    self.parents(".ui-dialog").nextAll("#jSIPContainer").remove();
		}

		getAjax(dfop.url,self);
		function getAjax(url,docObj) {
			docObj.empty();
			docObj.html('<div style="margin-top:40px;margin-left:'+((dfop.width-100)/2)+'px"><img src="'+RESOURCE_PATH+'/resource/images/loading.gif" alt="加载中..." /></div>');
			docObj.dialog(dfop);
			docObj.width(docObj.width());
			//docObj.next().find("button:contains('保存')").click(function(){var butt=$(this);$(this).attr("disabled",true);setTimeout(function(){butt.removeAttr('disabled');},5000);});
			$.ajax( {
				url : url,
				cache: false,
				data:dfop.postData,
				success : function(result) {
					proccessLoginResult(result,function(){docObj.html(result);});
					$(".form-txt",docObj).bind("focusin",function(){$(this).addClass("ui-widget-border-on");});
					$(".form-txt",docObj).bind("focusout",function(){$(this).removeClass("ui-widget-border-on");});
					docObj.children("#dialog-form:first").removeAttr("title");
					dfop.loadComplete();
				}
			});
		}
		return self;
	};
})(jQuery);