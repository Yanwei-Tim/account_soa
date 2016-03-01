/**
 * JQuery AutoComplete Plugin - SelectInPlace
 * Powered by http://www.codigg.com
 */
(function($) {

$.fn.selectInPlace = function(options) {
	
	var settings = {
		data		: null,		
		postDataFunction	: null,
		url 		: "",				
		param		: "keyword",		
		dynamic 	: false, 			
		cache		: false,
		minchar		: 1,				
		opacity		: 1.0,				
		zindex		: 20000,			
		height		: 200,				
		delay		: 0,				
		max			: 5,				
		loadingImg	: RESOURCE_PATH+'/resource/js/selectInPlace/ajax-loader.gif',
		loadingText	: 'Loading...',		
		autoChange	: true,				
		type		: "GET",			
		multiple	: true,				
		separator	: ',',				
		labelMethod	: formatText,		
		htmlMethod	: formatHtml,		
		valueMethod	: getValue,	
		fullMessage	: "you cannot select more .",
		width       : 0
	};
	
	var opts = $.extend({}, settings, options);
	
	// container
	var jsH = "jSIPHover";
	var jH  = "."+jsH;
	var jC  = "#jSIPContainer";
	if($(jC).length == 0){
		$("body").append('<div id="jSIPContainer"></div>');
		$(jC).hide();
		$(jC).css({
			position: "absolute",
			opacity: opts.opacity,
			zIndex: opts.zindex
		});
	}
	
	// 
	if(!opts.dynamic){
		if(opts.url!='' && !opts.data){
			$.getJSON(opts.url, function(json){
				opts.data = json;
			});
		}
	}
	
	// outerHTML funciton for cross-browser
	$.fn.outer = function() {
		 return $($('<div></div>').html(this.clone())).html();
    };
    
	// bind click method
	function doclick(event){
		if($(jC).css('display')!='none'){
			var hide = true;
			var target = $(event.target);
			if(target.parent().hasClass('bit-input')
					&& target.attr('id')==$(jC).data('field') )
				hide = false;
			else if(target.hasClass('bit-input')
					&& target.firstChild().attr('id') == $(jC).data('field'))
				hide = false;
			else if(target.hasClass('holder')
					&& $('#'+target.attr('id')+' textarea').attr('id') == $(jC).data('field') )
				hide = false;
			
			if(hide){
				// hide and clear the old data
				$(jC).hide();
				$("ul.holder[id^='holder_'] textarea").val('').data("before",'');
			}
		}
	}
	
	$(document).bind("click", doclick);
	
    // 
    function live(holderId){
	    $("#"+holderId).live("click" , function(){
	    	var id = $(this).attr('id');
	    	$("#"+id+" textarea[id^='input_']").focus();
	    });
	    
	    $("#"+holderId+" .closebutton").live("click",function(){
	    	var p = $(this).parent();
	    	options.itemClose(p.data("data"));
	    	for(var i=0;i<=10;i++){
	    		if(p.hasClass("bit-box"))
	    			break;
	    		p = p.parent();
	    	}
	    	var oe = p.parent().prev();
	    	var vs = $.trim(oe.val())==''? new Array(): oe.val().split(opts.separator);
	    	var v = p.data("data")+'';
	    	vs = $.grep(vs,function(item,i){return item!=v;});
	    	oe.val(vs.join(opts.separator));
	    	p.remove();
	    });
	    
	    $("#"+holderId+" .bit-box").live("mouseover",function(){
	    	$(this).addClass("bit-box-focus");
	    }).live("mouseout",function(){
	    	$(this).removeClass("bit-box-focus");
	    });
    }
    
    var timeout = null;
    
    // label for selected items
    function formatText(elem){
    	return '<LI class="bit-box">'+elem.children(".title").text()+'<A class="closebutton" href="#"></A>';
    }
    
    function setAutochange(textBox){
    	if (opts.autoChange){
			$(textBox).val($(jH).children(".title").text());
			$(textBox).data('before',$(jH).children(".title").text());
    	}
    }
    
    function formatHtml(item){
    	return '<li><img src="'+item.image+'"/><div class="title">'+item.label+'</div><div>&nbsp;'+item.desc+'</div></li>';
    }
    
    function getValue(item){
    	return item.value;
    }
    
    function getdata(field){
    	var ov = $("#"+field).val();
		return $.trim(ov)==''? new Array(): ov.split(opts.separator);
    }
	
	function setdata(textBox,field){
		var v = $(jH).data("data")+'';
		var html = opts.labelMethod($(jH));
		var e = $(html).data("data",v);
		if(opts.multiple){
			var vs = getdata(field);
			if($.inArray(v,vs) < 0 ){
				vs.push(v);
				$("#"+field).val(vs.join(opts.separator));
				$(textBox).parent().before(e);
			}
		}else{
			var t = $(textBox).parent().prev();
			if($.trim(t.outer())=='')
				$(textBox).parent().before(e);
			else
				t.replaceWith(e);
			$("#"+field).val(v);
		}
		$(textBox).data('before','').val('').focus();
	}
	
	function bind(textBox,field){
		$(jC+" ul li").bind("mouseover",	function(){
			$(jH).removeClass(jsH);
			$(this).addClass(jsH);
			setAutochange(textBox);
		});
		$(jC+" ul li").click(function(){
			$(this).addClass(jsH);
			setdata(textBox,field);
		});
		$(".jSIPLoading").hide();	
	}
	
	function showContainer(holderId){
		// show suggestions container
		var offSet = $("#"+holderId).offset();
		$(jC).css({
			position: "absolute",
			top: offSet.top + $("#"+holderId).outerHeight() + "px",
			left: offSet.left,
			width: $("#"+holderId).outerWidth()-2 + "px"
		}).show();
	}
	
	function adjustHeight(holderId){
		var outerheight = $(jC +" ul").outerHeight();
		if(outerheight > opts.height){
			$(jC + " ul").css({
				height: opts.height+"px"
			});
		}
	}
	
	function getRegExp(key){
		return new RegExp("^"+key,"i");
	}
	
	function match(item,re){
		return re==null ? true : item.label.search(re)>-1;
	}
	
	function action(holderId,textBox,id){
		var vs = getdata(id);
		if ($(".jSIPLoading").length == 0){
			$('<div class="jSIPLoading"><img src="'+opts.loadingImg+'" align="bottom" /> '+ opts.loadingText+'</div>').prependTo("#jSIPContainer");
			$('<div class="jSIPMessage"></div>').prependTo("#jSIPContainer");
		}
		$(".jSIPMessage").hide();
		$(jC).find('ul').remove();
		$(jC).data('field',textBox.id);
		
		if(opts.multiple && vs.length>=opts.max){
			$(".jSIPLoading").hide();
			$(".jSIPMessage").html(opts.fullMessage).show();
			showContainer(holderId);
		}else{
			$(".jSIPLoading").show();
			$(".jSIPMessage").hide();
			
			$(jC).find('ul').remove();
			
			var postData = '';
			if (opts.param == '')
				postData = $(textBox).serialize();
			else 
				postData = opts.param + "=" + $(textBox).val();
			
			if(!opts.cache){
				postData+="&_r="+Math.random();	
			}
			var data=options.postDataFunction();
			postData=postData+"&"+$.param(data);
			// 
			if(!opts.dynamic){
				showContainer(holderId);
				var key = $(textBox).val();
				var re = key==''? null : getRegExp(key);
				$(jC).append('<ul></ul>');
				var parent = $(jC+" ul");
				$.each(opts.data, function(i,item){
					var v = opts.valueMethod(item);
					if(match(item,re) && $.inArray(v+'',vs)==-1){
						var e = $(opts.htmlMethod(item)).data('data',v);
						parent.append(e);
					}
				});
				adjustHeight(holderId);
				bind(textBox,id);
			}else{
				if(timeout)
					clearTimeout(timeout);
				timeout = setTimeout(function () {
					showContainer(holderId);
					$.ajax({
						url:opts.url,
						data:postData,
						type:'post',
						dataType:"json",
						complete:function(json){
							var jsonData=eval("("+json.responseText+")");
							$(jC).children().remove();
							$(jC).append('<ul style="width:'+(opts.width+10)+'px"></ul>');
							var parent = $(jC+" ul");
							$.each(jsonData, function(i,item){
								var v = opts.valueMethod(item);
								if($.inArray(v+'',vs)==-1){
									var e = $(opts.htmlMethod(item)).data('data',v);
									parent.append(e);
								}
							});
							adjustHeight(holderId);
							bind(textBox,id);
						}
					});
				}, opts.delay);
			}
		}
	}
	
	var idx = 1;
	
	return this.each(function(){
		var containerWidth = $(this).outerWidth();
		var id = $(this).attr('id');
		if(!id){
			id = 'field_'+ (idx++) ;
			$(this).attr('id',id);
		}
		// create a new input box
		var holderId="holder_"+id;
		var inputId="input_"+id;
		
		$(this).after('<ul class="holder" id="'+holderId+'"><li class="bit-input"><textarea id="'+inputId+'"/></li></ul>');
		
		// replace the origin one
		var h = $(this).outer().replace(/type=\"?text\"?/, 'type="hidden"');
		if(h.indexOf('type="hidden"')<0){
			h = h.replace(/<input /gi, '<input type="hidden" ');
		}
		$(this).replaceWith(h);
		$(this).remove();
		
		live(holderId);
		
		
		$("#"+holderId).css({
			width:containerWidth+"px"
		});
		
		if(opts.minchar<=0){
			$("#"+holderId).click(function(e){
				var textBox = $('#'+holderId+' textarea')[0];
				action(holderId,textBox,id);
			});
		}
		
		$("#"+inputId).keyup(function(e){
			var textBox = this;
			var beforeVal = $(this).data('before');
			var textVal = this.value;
			
			if(textVal != beforeVal)
				$(textBox).data('before',textBox.value);
			
			if(e.keyCode==8 && beforeVal==''){
				$(textBox).parent().prev().children(".closebutton").click();
			}else if (textVal.length >= opts.minchar){
				if (e.keyCode == 27 ) {// escape key
					$(jC).hide();
				}else if (e.keyCode == 13 ) { // enter key
					if ($(jH).length == 1){
						setdata(textBox,id);
					}
					$(jC).hide();
				}else if (e.keyCode == 40) { // down
					if ($(jH).length == 1) {
						if (!$(jH).next().length == 0) {
							$(jH).next().addClass(jsH);
							$(".jSIPHover:eq(0)").removeClass(jsH);
							setAutochange(textBox);
						}
					}else {
						$("#jSIPContainer ul li:first-child").addClass(jsH);
						setAutochange(textBox);
					}
					
					var scrHeight=0;
					var selectLI=$(".jSIPHover:eq(0)").text();
					var maxHeight=$("#jSIPContainer ul").height();
					$("#jSIPContainer ul li").each(function(i,n){
						scrHeight=scrHeight+$(this).outerHeight();
						if($(this).text()==selectLI)
						return false;
					})
					$(".jSIPHover:eq(0)").parent().scrollTop(scrHeight-maxHeight);
				}else if (e.keyCode == 38) {
					// if any suggestion is highlighted
					if ($(jH).length == 1 ) {
						if (!$(jH).prev().length == 0) {
							$(jH).prev().addClass(jsH);
							$(".jSIPHover:eq(1)").removeClass(jsH);
							setAutochange(textBox);
						}
						// if is first child
						else {
							$(jH).removeClass(jsH);
						}
					}
					var scrHeight=0;
					var selectLI=$(".jSIPHover:eq(0)").text();
					var maxHeight=$("#jSIPContainer ul").height();
					var HeightLi=$(".jSIPHover:eq(0)").outerHeight();
					$("#jSIPContainer ul li").each(function(i,n){
						scrHeight=scrHeight+$(this).outerHeight();
						if($(this).text()==selectLI)
						return false;
					})
					$(".jSIPHover:eq(0)").parent().scrollTop(scrHeight-HeightLi);
				}else if (this.value != beforeVal){
					action(holderId,textBox,id)
				}
			}else{
				$(jH).removeClass(jsH);
				$(jC).hide();
			}
		});
		
	});
};
})(jQuery);