

window.moveTo(0,0); 
if(document.all){ 
	top.window.resizeTo(screen.availWidth,screen.availHeight); 
}
else if(document.layers||document.getElementById){
	if(top.window.outerHeight<screen.availHeight||top.window.outerWidth<screen.availWidth){
		top.window.outerHeight = screen.availHeight;
		top.window.outerWidth = screen.availWidth;
	}
}
var startDate=new Date();
var isPopLoginDialog=false;
$.extend({
	httpData: function( xhr, type, s ) {
		var ct = xhr.getResponseHeader("content-type") || "",
			xml = type === "xml" || !type && ct.indexOf("xml") >= 0,
			data = xml ? xhr.responseXML : xhr.responseText;
	
		if ( xml && data.documentElement.nodeName === "parsererror" ) {
			jQuery.error( "parsererror" );
		}

		// Allow a pre-filtering function to sanitize the response
		// s is checked to keep backwards compatibility
		if ( s && s.dataFilter ) {
			data = s.dataFilter( data, type );
		}

		// The filter can actually parse the response
		if ( typeof data === "string" ) {
			// Get the JavaScript object, if JSON is used.
			if ( type === "json" || !type && ct.indexOf("json") >= 0 ) {
				data = jQuery.parseJSON( data );

			// If the type is "script", eval it in global context
			} else if ( type === "script" || !type && ct.indexOf("javascript") >= 0 ) {
				jQuery.globalEval( data );
			}
		}

		return data;
	},
	handleError: function( s, xhr, status, e ) {
		// If a local callback was specified, fire it
		if ( s.error ) {
			s.error.call( s.context, xhr, status, e );
		}

		// Fire the global callback
		if ( s.global ) {
			jQuery.triggerGlobal( s, "ajaxError", [xhr, s, e] );
		}
	},
	layout:function(option){
		// 布局
		var defaultOption={
		west__showOverflowOnHover: false
		,   fxSpeed:               "fast"
		,	north__closable:		true
		,	north__resizable:		true
		,	north__spacing_open:	0
		,	south__closable:		true
		,	south__resizable:		true
		,	south__spacing_open:	0
		,	west__fxSettings_open: { easing: "easeOutBounce", duration: 100 }
		}
		
			
		
		$.extend(defaultOption,option);

	    myLayout = $('body').layout(defaultOption);
	},
	topmenu:function(){
		var menuW=document.documentElement.clientWidth-$("#header-bottom-right").width()-60;
		if($("#menu ul li").size()*84<menuW){
			return ;
		}
		$(".ui-layout-north").before('<ul id="top-submenu" class="ui-corner-all"><p id="top-submenu-arrows"></p></ul>');
		$("#menu").width(menuW).append('<a id="topmenuon"><span id="arr"></span></a>');
		var currentBox=$("#menu").find("li");
		var objwidth=0;
		currentBox.each(function(){
			objwidth=objwidth+$(this).innerWidth();
			if(objwidth>menuW){
				$("#top-submenu").append($(this));
				$("#topmenuon").show();
			};
		});
		$("#topmenuon").toggle(
			function () {
				var moreWidth=0;
				$(this).addClass("hover");
				$("#top-submenu").show();
				$("#top-submenu").attr("showMenu","true");
				$("#top-submenu").find("li").each(function(){
					moreWidth=moreWidth+$(this).innerWidth();
				});
				$("#top-submenu").css("right",$("#header-bottom-right").width()+15);
				$("#top-submenu-arrows").css("left",moreWidth-20+"px");
			},
			function () {
				$(this).removeClass("hover");
				$("#top-submenu").hide();
				$("#top-submenu").attr("showMenu","false");
			}
		);
		$("body").click(function() {
			if($("#top-submenu").attr("showMenu")=="true"){
				$("#topmenuon").click();
			}
		});
	},
	messageBox : function(options) {
       var defaultOption={
    	   message: false,
		   sticky: false,
		   closer:false,
    	   level: "success",
    	   position: 'center',
    	   glue: 'before',
		   speed: 'fast',
		   life:3000,
		   beforeOpen:function(e,m,o) {
			   $("#jGrowl .close").remove();
			   $("#jGrowl .jGrowl-notification:not(:last)").hide();
		   }
       };
       $.extend(defaultOption, options);

	    switch (defaultOption.level) {
		   case "info":
			   defaultOption.message='<div class="info">'+defaultOption.message+'</div>';
			   break;
		   case "error":
			   defaultOption.message='<div class="error">'+defaultOption.message+'</div>';
			   break;
		   case "success":
			   defaultOption.message='<div class="success">'+defaultOption.message+'</div>';
		}

		switch (defaultOption.level) {

		   case "info":
		   	   $("#jGrowl jGrowl-notification").addClass("error");
			   break;
		   case "error":
			   $("#jGrowl jGrowl-notification").addClass("error");
			   break;
		   case "success":
			   $("#jGrowl jGrowl-notification").addClass("success");
			   break;
		   default :
			   $("#jGrowl jGrowl-notification").addClass("error");
		}
       $("#jGrowl").jGrowl(defaultOption.message,defaultOption);
	},
	switchSkin : function(skinName){
		if(skinName==null||skinName==""){
			$("#cssfile").attr("href",RESOURCE_PATH+"/resource/js/ui/default.css"); // 设置不同皮肤
		}else{
			$("#cssfile").attr("href",RESOURCE_PATH+"/resource/js/ui/"+ skinName +".css"); // 设置不同皮肤
		}
		$.cookie( "cssSkin" ,  skinName , { path: '/', expires: 10 });
	},
	tab:function(option){
		  var defaultOption={
		   tab:"",
		   box:"",
		   hover:""
		  }
		  $.extend(defaultOption,option);
		  var _tab=$(defaultOption.tab);
		  var _box=$(defaultOption.box);
		  var _hover=defaultOption.hover;
		  var _index;
		  _tab.click(function(){
		   _index=_tab.index(this);// 获取当前点击的索引值
		   $(this).addClass(_hover).siblings().removeClass(_hover);
		   _box.eq(_index).show().siblings().hide();
		  }).eq(0).click();
	},
	dialogLoading:function(option){
		var _init=function(){
			$("body").prepend('<div class="dialog_loading"><div class="loadingcon"></div></div>')
		};
		if(typeof(option)=='string'){
				if(option=="open"){
					_init();
					$(".dialog_loading").show();
				};
				if(option=="close"){
					$(".dialog_loading").remove();
				}
		};
	},
	loadingComp:function(option){
		var _init=function(){
			$("body").prepend('<div class="dialog_loading"><div class="loadingcon"></div></div>')
		};
		if(typeof(option)=='string'){
				if(option=="open"){
					_init();
					$(".dialog_loading").show();
				};
				if(option=="close"){
					$(".dialog_loading").remove();
				}
		};
	},
	tabMenuBoxList:function(){
		var _box=$('.tabbox ul');
		var _hover='hover';
		var objwidth=0;
		function showBoxMenu(i,n){
			objwidth=objwidth+$(n).outerWidth();
			if(objwidth>screen.width-265){
				$(n).after('<li class="more"><img src="'+RESOURCE_PATH+'/resource/js/ui/default/popmenu2.gif" /></li>');
				$(".popMenu").attr("showMenu","false");
				$(".more").toggle(
						function () {
							$(".popMenu").show();
							$(this).html('<img src="'+RESOURCE_PATH+'/resource/js/ui/default/popmenu1.gif" />');
							var _index=$(this).parent().index();
							$(".popMenu").children().hide();
							$(".test"+_index,$(".popMenu")).show();
							$(".popMenu").attr("showMenu","true");
							$(".ceng").show();
						},
						function () {
							$(this).html('<img src="'+RESOURCE_PATH+'/resource/js/ui/default/popmenu2.gif" />');
							var _index=$(this).parent().index();
							$(".ceng").hide();
							$(".popMenu").children().hide();
							$(".popMenu").attr("showMenu","false");
						}
				);
				return false;
			};

		};

		$(".subnavbar li>a").each(function(){
			var subWidth=$(this).outerWidth()+17;
			$(this).parent().css("width",subWidth);
		}).bind("click",function(){
			$(this).addClass("click").parent().siblings().children("a").removeClass("click");
		});

		var _tab=$('ul.tabnav>li');
		var boxindex=0;
		_tab.click(function(){
			var _index=_tab.index(this);
			boxindex=_index;
			$(this).addClass(_hover).siblings().removeClass(_hover);
			_box.eq(_index).show().siblings().hide();
			// 前一次又焦点的box
			if(!$(".test"+_index).attr("afterProccess")){
				var currentBox = _box.eq(_index);
				$.each(currentBox.children(),showBoxMenu);
				objwidth=0;
				$(".popMenu").append($("<div class='test"+_index+"'/>").append($(".more",currentBox).nextAll()));
				$(".test"+_index).attr("afterProccess",true);
			}
			$("a:first",$($(".tabbox>ul")[boxindex])).click();
		});
		$(".ui-layout-center").click(function() {
			if($(".popMenu").attr("showMenu")=="true"){
				_box.eq(boxindex).children(".more").click();
			}
		});
		$('ul.tabnav>li:first').click();
	}

});
function enableSaveButton(option) {
	var dlgFirstButton = $('.ui-dialog-buttonpane:visible').find('button:first');
	if (dlgFirstButton == null) {
		return;
	}
	if (option) {
		dlgFirstButton.show();
	} else {
		dlgFirstButton.hide();
	}
}
$.fn.extend({
	serializeObject:function(option) {
		var defaultOption = {
			excludeWhenNull:false
		};
		$.extend(defaultOption,option);
		function excludeWhenNull(name,value){
			if(defaultOption.excludeWhenNull){
				for(var i=0;i<defaultOption.excludeWhenNull.length;i++){
					if(defaultOption.excludeWhenNull[i]==name&&(value==""||value==null||value==undefined)){
						return false;
					}
				}
			}
			return true;
		}
	    var o = {};
	    var a = this.serializeArray();
	    $.each(a, function() {
			if(excludeWhenNull(this.name,this.value)){
		        if (o[this.name]) {
		            if (!o[this.name].push) {
		                o[this.name] = [ o[this.name] ];
		            }
	            	o[this.name].push(this.value || '');
		        } else {
					o[this.name] = this.value || '';
		        }
			}
	    });
	    return o;
	},
	
	getTabId:function(indexId){
		return $($($(this).children()[0]).children()[indexId]).find("a").attr("id");
	},
	
	tabFunction:function(options,formName){
		if(null!=formName){
			$.extend(options,{
				select: function(event, ui) {
					if($("#"+formName).hasClass('ui-tabs-hide')){
						enableSaveButton(true);
					}else{
						enableSaveButton(false);
					}
				}
			});
		}
		var defaultOption={
			select: function(event, ui) {$(".tip-yellowsimple,.tip-error").remove();$("input[createMsg='true']",$(this)).attr("createMsg","false")}
		};
		$.extend(defaultOption,options);
		$(this).tabs(defaultOption);
	},

	popup:function(popDom,closeButton){
			$(this).click(function(){
				$(popDom).hide();
				$(this).parent().children(popDom).show();
				$(this).parent().css("z-index","1");
				$(popDom).bgiframe();
			});
			$(closeButton).click(function(){
				$(this).parent().parent().hide();
			});
		},
	messageTip : function(options){
		var self=$(this);
		var defaultOption={
			url:"#",
			show:true,
			width:120,
			cache:true,
			arrowPositionLeft:70,
			notAcceptNote:{title:"未受理短信",link:"javascript:void(0)",num:0,limits:false},
			notAcceptOnlineAppeals:{title:"未受理平台消息",link:"javascript:void(0)",num:0,limits:false},
			backlog:{title:"待办事项",link:"javascript:void(0)",num:0,limits:false},
			sessionTimeout:7200000
	   };
	   $(".message-tip").remove();
		if(options=='show'){
			$(".message-tip").remove();
			$.cookie("messageTip",true, { path: '/', expires: 10 });
		}
	    var live=function(cookieName){
			$(".tip-close").click(function(){
				$(".message-tip").remove();
				$.cookie(cookieName,false, { path: '/', expires: 10 });
			});
			if(defaultOption.show==true){
				$(".message-tip").show();
			}
			else{
				$(".message-tip").hide();
			};
			$("#noAcceptNote").bind("click",function(){
				showPageByTopMenu('interactionManagement');
			});
			$("#unreadPlatformNews").bind("click",function(){
				showPageByTopMenu('interactionManagementpmManagement');
			});
			$("#backlogMessage").bind("click",function(){
				showPageByTopMenu('getIssueData');
			});
	    }
        $.extend(defaultOption, options);

	    var data=defaultOption.data;
		if(data!=null){
			data.messageNum = data.myNeedDoNum+data.personnelMessageNum+data.smsReceivedBoxNum;
		}else if(data!=null && data.messageNum>0){
			$("#header-bottom-right .message:eq(0)").html("<strong class='newmessages'></strong><a href='javascript:void(0)'>消息("+data.messageNum+")</a>");
			$(".message .messageButton:eq(0)").html("<span class=newmessage></span>消息("+data.messageNum+")");
		} else if(data!=null && data.messageNum == 0){
			$("#header-bottom-right .message:eq(0)").html("<strong class='nomessages'></strong><a href='javascript:void(0)'>消息(0)</a>");
			$(".message .messageButton:eq(0)").html("<span class=newmessage></span>消息(0)");
			return false;
		}else{
			return false;
		}
    	var cookieName="messageTip";
		var cookieValue=$.cookie("messageTip");
		if(cookieValue==null){
			$.cookie(cookieName,true, { path: '/', expires: 10 });
		};
		if(cookieValue=='false'){
			return false;
		};
		if(data==null || !data.smsReceivedBoxNum){
			defaultOption.notAcceptNote.num=0;
		}else{
			defaultOption.notAcceptNote.num=data.smsReceivedBoxNum;
		}
		if(data==null || !data.personnelMessageNum){
			defaultOption.notAcceptOnlineAppeals.num=0;
		}else{
			defaultOption.notAcceptOnlineAppeals.num=data.personnelMessageNum;
		}
		if(data==null || !data.myNeedDoNum){
			defaultOption.backlog.num=0;
		}else{
			defaultOption.backlog.num=data.myNeedDoNum;
		}
		var notAcceptNoteHtml='';
        var notAcceptOnlineAppealsHtml='';
        var backlogHtml='';
        if(defaultOption.notAcceptNote.limits){
        	notAcceptNoteHtml='<p>'+defaultOption.notAcceptNote.title+'<a href="'+defaultOption.notAcceptNote.link+'" id="noAcceptNote">'+defaultOption.notAcceptNote.num+'</a>条  </p>';
        };
        if(defaultOption.notAcceptOnlineAppeals.limits){
        	notAcceptOnlineAppealsHtml='<p>'+defaultOption.notAcceptOnlineAppeals.title+'<a href="'+defaultOption.notAcceptOnlineAppeals.link+'" id="unreadPlatformNews">'+defaultOption.notAcceptOnlineAppeals.num+'</a>条</p>';
        };
       if(defaultOption.backlog.limits){
    	    backlogHtml='<p>'+defaultOption.backlog.title+'<a href="'+defaultOption.backlog.link+'" id="backlogMessage">'+defaultOption.backlog.num+'</a>条</p>';
       };
       var html='<div class="message-tip"><div class="tip-message" style="position:relative;width:'+defaultOption.width+'px;display:block;"><a class="tip-close"></a><div class="tip-inner tip-bg-image">'
			+'<div id="messageContent"><div class="message-center">'
			+ notAcceptNoteHtml
			+ notAcceptOnlineAppealsHtml
			+ backlogHtml
			+'</div></div></div><div class="tip-arrow tip-arrow-top" style="top:0px;left:'+defaultOption.arrowPositionLeft+'px;position:absolute"></div></div></div>'
		self.prepend(html);
       	live(cookieName);

	},
	dialogtip:function(option){
		var defaultOption={
			className: 'tip-error',
			showOn: 'none',
			alignTo: 'target',
			hideTimeout:0,
			showTimeout:0,
			alignX: 'center',
			alignY: 'bottom',
			offsetX: 0,
			offsetY: 5
		}
		$.extend(defaultOption,option);
		$(this).poshytip(defaultOption);
	},
	showTip:function(option){
		var self=$(this);
		var defaultOption={
			className: 'tip-yellowsimple',
			hideTimeout:0,
			showTimeout:0,
			offsetX: 5,
			offsetY: 5,
			showOn: 'focus',
			alignTo: 'target',
			alignX: 'right',
			alignY: 'center',
			positionRight:false
		};
		var outTimer,inTimer;
		$.extend(defaultOption,option);
		self.poshytip(defaultOption);
		self.hover(function(){
			clearTimeout(outTimer);
			self.poshytip('show');
		},function(){
			outTimer=setTimeout(function(){
				self.poshytip('hide')
			},3000);
			$(".tip-yellowsimple").hover(function(){
				clearTimeout(outTimer);
				self.poshytip('show');
			},function(){
				outTimer=setTimeout(function(){
					self.poshytip('hide')
				},1000);
			});
		});
		self.removeAttr("title");
	},
	inputtip:function(option){
		var inputtipName="inputtip";
		var inputtipValue=$.cookie("inputtip");
		if(inputtipValue==null){
			$.cookie(inputtipName,true, { path: '/', expires: 10 });
		};
		if(inputtipValue=='false'){
			return;
		}else{
			var self=$(this);
			var defaultOption={
				className: 'tip-yellowsimple',
				hideTimeout:0,
				showTimeout:0,
				offsetX: 5,
				offsetY: 5,
				showOn: 'focus',
				alignTo: 'target',
				alignX: 'right',
				alignY: 'center',
				liveEvents:true
			}
			$.extend(defaultOption,option);
			var tipMsg = self.attr("tipMsg");
			if(tipMsg && tipMsg!=""){
				defaultOption.content=tipMsg;
			}
			self.poshytip(defaultOption);
			self.bind("change",function(){
				$(this).poshytip('hide');
			})
			$(".tip-yellowsimple").bgiframe();
			// $(this).removeAttr("title");
		}
	},
	
	setButtonEnabled:function(enabled){
		if (enabled){
			$(this).buttonEnable();
		}else{
			$(this).buttonDisable();
		}
	},

	isButtonEnabled:function(){
		return !($(this).attr("disabled")=="true" || $(this).attr("disabled")=="disabled");
	},
	
	buttonDisable:function(){
		$(this).addClass("huise").addClass("disable").attr("disabled",true).removeAttr("href");
		$(this).hover(
				function () {
				$(this).removeClass("hover");
				},
				function () {
				$(this).removeClass("hover");
				}
		);
	},
	buttonEnable:function(){
		$(this).removeClass("huise").removeClass("disable").attr("disabled",false).attr("href","javascript:void(0)");
		$(this).hover(
			function () {
			$(this).addClass("hover");
			},
			function () {
			$(this).removeClass("hover");
			}
		);
	},
	datePicker : function(o) {
		var self = $(this);
		var dfop={
			showWeek: false,
			changeMonth: true,
			changeYear: true,
			yearSuffix: '',
			dateFormat:'yy-mm-dd',
			showButtonPanel: true,
			showClearButton:true
		};
		$.extend(dfop,o);
		if(!$("#ui-datepicker-div").attr("id")){
			$.datepicker.initialized = false;
		}
		$(this).datepicker(dfop);
	},
	dateTimePicker : function(o) {
		var self = $(this);
		var dfop={
			showWeek: false,
			changeMonth: true,
			changeYear: true,
			yearSuffix: '',
			dateFormat:'yy-mm-dd',
			showButtonPanel: true,
			showClearButton:true,
			showSecond: true,
			timeFormat: 'HH:mm:ss',
			stepHour: 1,
			stepMinute: 5,
			stepSecond: 10,
			showSecond:false,
			showMinute:false,
			currentText: '今天',
			clearText: '清除',
			closeText: '关闭',
			amNames: ['上午', 'A'],
			pmNames: ['下午', 'P'],
			timeSuffix: '',
			timeOnlyTitle: '选择时间',
			timeText: '时间',
			hourText: '时',
			minuteText: '分',
			secondText: '秒'
		};
		$.extend(dfop,o);
		$(this).datetimepicker(dfop);
	},
	accordionFunction:function(selector,content,option){
		// 左边菜单
		var self=$(this);
		var defaultOption={
				collapsible: true,
				header: "> " + content + " > " + selector,
				autoHeight: false
		};
		$.extend(defaultOption,option);
		self.accordion(defaultOption);
	},
	hoverDisplay:function(biaoqian){
		// 显示隐藏
	   $(this).children(biaoqian).hide();
	   $(this).hover(function(){
			$(this).children(biaoqian).stop(true,true).slideDown(400);
		},function(){
			$(this).children(biaoqian).stop(true,true).slideUp("fast");
		});
	},
	hoverChange:function(hoverClass){
		// 指向更改class
	   $(this).hover(
		  function () {
			$(this).css("cursor","hand");
			$(this).addClass(hoverClass);
		  },
		  function () {
			$(this).removeClass(hoverClass);
		  }
		);
	},
	popUpMore:function(options){
		var self=$(this);
		var selfId=$(this).attr("id");
		var conId=selfId+new Date().getTime();
		var popUpCon='';
		var thisWindow = {
			l: $(window).scrollLeft(),
			t: $(window).scrollTop(),
			w: $(window).width(),
			h: $(window).height()
		};
		var defaultOption={
			data:[],
			className: 'tip-yellowsimple',
			hideTimeout:0,
			showTimeout:0,
			offsetX: 5,
			offsetY: 0,
			showOn: 'none',
			alignTo: 'target',
			alignX: 'right',
			alignY: 'center',
			openNew:true,
			content:function(){}
		}
		$.extend(defaultOption,options);
		var target='_blank';
		if(defaultOption.openNew!=true){
			target='_self'
		}
		defaultOption.content=function(){
			for(var i=0;i<defaultOption.data.length;i++){
				if(defaultOption.data[i].dailyDirectoryid){
					popUpCon=popUpCon+'<li><a href="'+defaultOption.data[i].url+'" id="'+selfId+'_item'+defaultOption.data[i].id+'" thisId = "'+defaultOption.data[i].id+'" dailyDirectoryId="'+defaultOption.data[i].dailyDirectoryid+'" target="'+target+'">'+defaultOption.data[i].text+'</a></li>';
				}else{
					popUpCon=popUpCon+'<li><a href="'+defaultOption.data[i].url+'" id="'+selfId+'_item'+defaultOption.data[i].id+'" thisId = "'+defaultOption.data[i].id+'" target="'+target+'">'+defaultOption.data[i].text+'</a></li>';
				}
			}
			return '<div class="popupcon" id="'+conId+'">'+'<a class="tip_close"></a>'+popUpCon+'</div>';
		};
		bindItemEvent=function(){
			for(var i=0;i<defaultOption.data.length;i++){
				var thisItem=$(".popupcon").find("li:eq("+i+") a");
				thisItem.bind("click",defaultOption.data[i].clickFun);
			};
		};

		var init=function(){
			var tipMsg = self.attr("tipMsg");
			if(tipMsg && tipMsg!=""){
				inputOption.content=tipMsg;
			}
			self.poshytip(defaultOption);
			$(".tip-yellowsimple").bgiframe();
		};
		var style=function(){
			var thisTop=$("#"+conId).closest(".tip-yellowsimple:first").css("top");
			thisTop=Number(thisTop.substring(0,thisTop.length-2));
		}
		var closeEvent=function(){
			$(".ui-layout-north").click(function(){
				$("#"+conId).closest(".tip-yellowsimple:first").remove();
			})
			$(".ui-layout-west").click(function(){
				$("#"+conId).closest(".tip-yellowsimple:first").remove();
			})
			$(".ui-layout-south").click(function(){
				$("#"+conId).closest(".tip-yellowsimple:first").remove();
			})
			$(".center-left").click(function(){
				$("#"+conId).closest(".tip-yellowsimple:first").remove();
			})
			$("#nav").click(function(){
				$("#"+conId).closest(".tip-yellowsimple:first").remove();
			})
			$(".path").click(function(){
				$("#"+conId).closest(".tip-yellowsimple:first").remove();
			})
		}
		self.bind("click",function(){// alert(33333333)
			$(".tip-yellowsimple:first").remove();
			popUpCon='';
			if(self.offset().left+170>thisWindow.w){
				defaultOption.alignX="left";
			}
			else{
				defaultOption.alignX="right";
			};
			init();
			self.poshytip("show");
			style();
			bindItemEvent();
			$("#"+conId+" .tip_close:first").bind("click",function(){
				self.poshytip("hide");
				$("#"+conId).closest(".tip-yellowsimple:first").remove();
			})
			closeEvent();
		});
	},
	passwordCheck:function(options){
		digitalspaghetti.password.el.passwordMinChar=null;
		digitalspaghetti.password.el.passwordStrengthBar=null;
		var self=$(this);
		var selfId=$(this).attr("id");
		var selfScore=0;
		var defaultOption={
			displayMinChar: false,
			minChar: 6,
			minCharText: '密码个数最少为 %d 个字符',
			colors: ["#ff0000", "#ff0099", "#99cc00", "#00ccff", "#00ccff"],
			scores: [20, 20, 50, 50],
			verdicts:	['弱', '弱', '中等', '强', '强'],
			raisePower: 1.4,
			debug: false,
			scoreVal:0,
			keyUp:function(selfScore){}
		}
		$.extend(defaultOption,options);
		self.pstrength(defaultOption);
		var word,score;
		self.keyup(function(){
			var word=self.attr("value");
			// defaultOption.scoreVal=self.pstrength.validationRules(word,score);
			selfScore=defaultOption.scoreVal;
			defaultOption.keyUp(selfScore);
		});
	},
	hoverEvent:function(thisEvent){
		var self=$(this);
		var selfId=self.children("a:first").attr("name");
		self.hover(
			function(){
				self.addClass("hover");
				self.children("span:first").addClass("delItem");
				self.children("span:first").bind("click",thisEvent);
			},
			function(){
				self.removeClass("hover");
				self.children("span:first").removeClass("delItem");
				self.children("span:first").unbind("click");
			}
		)
	},
	datepickers:function(o){
		var self=$(this);
		var dfop={
			defaultDate: "+1w",
			changeMonth: true,
			changeYear: true,
			showWeek:false,
			yearSuffix: '',
			numberOfMonths: 1,
			datas:'.dates',
			yearRange: '1900:2030',
			maxDate:'+0d',
			onSelect: function(selectedDate) {
				
			},
			onClose:function(selectedDate){
				var option = $(this).data("flag") == "from" ? "minDate" : "maxDate",
				instance = $( this ).data( "datepicker" ),
				date = $.datepicker.parseDate(
					instance.settings.dateFormat ||
					$.datepicker._defaults.dateFormat,
					selectedDate, instance.settings );
				if(dates.not(this).attr("value")=='' && $(this).attr("value") != ''){
					dates.not(this).focus();
				}
			}
		}
		$.extend(dfop,o);
		self.eq(0).data("flag","from");
		var dates=self.datepicker(dfop);
		self.eq(0).datepicker("option",dfop.from);
		self.eq(1).datepicker("option",dfop.to);
	},
	dateWeek:function(o){
		var self=$(this);
		var dfop={
			timer:1000
		}
		$.extend(dfop,o)
		var init=function(){
			var todayDate = new Date();
			var date = todayDate.getDate();
			var month = todayDate.getMonth() + 1;
			var year = todayDate.getFullYear();
			var weeks = [ "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" ];
			var hh = todayDate.getHours();
			var mm = todayDate.getMinutes();
			var ss = todayDate.getTime() % 60000;
			ss = (ss - (ss % 1000)) / 1000;
			var clock = hh + ':';
			if (mm < 10){
				clock += '0';
			}
			clock += mm + ':';
			if (ss < 10){
				clock += '0';
			}
			clock += ss;
			var string;
			string = year + "-" + month + "-" + date + "," + clock;
			self.html(string);
			return string;
		}
		setInterval(init,dfop.timer);
	}
});
$.extend({
	sigmaReportLayout:function(){

		// 列表信息 和 柱图 饼图 外层容器计算高度
		$(".SigmaReport").height(
			$(".ui-layout-center").outerHeight() - $(".submenu").outerHeight() - $("#nav").outerHeight()+20
		);
		$(".SigmaReport").width(
			$(".ui-layout-center").outerWidth()-$("#chartsTabs ul:eq(0)").width() -40
		);
		// 列表信息
		$(".ui-tabs-panel").height(
			$(".ui-layout-center").outerHeight() - $(".submenu").outerHeight() - $("#nav").outerHeight() - 20
		);

		$(".ui-tabs-panel").width(
			$(".ui-layout-center").outerWidth()-$("#chartsTabs ul:eq(0)").width() -40
		);


		$(".zt_tabs_style div#chartsTabs div.ui-tabs-panel").width(
				$(".ui-layout-center").outerWidth()-$("#chartsTabs ul:eq(0)").width() -40
		).height(
			$(".ui-layout-center").outerHeight() - $(".submenu").outerHeight() - $("#nav").outerHeight() - 20
		);
	},
	gridboxHeight:function(){
		$(".SigmaReport").height($(".ui-layout-center").height()-150);
	},
	// 点击隐藏input框中的文字
	showTxtValue:function(){
		var f = $("input.show_search_btn");
		var t = $("input.show_search_btn").attr("value");
		f.focusin(function(){
			if(f.attr("value")==t){
				f.attr({"value":""})
			}
		}).focusout(function(){
		if(f.attr("value")==""){
			f.attr("value",t)
			}
		})
	}
});
$.ajaxSetup({
	beforeSend:function(){
		$(".ui-dialog").find(".ui-dialog-buttonpane").find("button:contains('保存')").attr("disabled",true);
		setTimeout(function(){
			$(".ui-dialog").find(".ui-dialog-buttonpane").find("button:contains('保存')").removeAttr("disabled");
		},5000)
	},
	complete:function(){
		$(".ui-dialog").find(".ui-dialog-buttonpane").find("button:contains('保存')").removeAttr("disabled");
	}
})