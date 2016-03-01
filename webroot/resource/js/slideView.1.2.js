/*

	2010.3.9
	SlideView Component
	developled by Robin at ELFVision and PageTalks
	used mainly in the WUSESKY Project
	
	Visit Robin's:
	http://www.elfvision.com/
	http://pagetalks.com/
	
	
*/
(function($) {
$.iSlideView = {
	build: function(user_options) {
		var defaults = {
			thumb: true,
			thumbPrefix: "thumb_",
			frame: {
				isExist: false
			},
			slideBy : 1,
			speed: 350,
			easeOut: "swing",
			easeIn: "swing",
			easeThumb: "swing",
			loop: false,
			interval: 5000
		},
			options = $.extend(defaults, user_options),
			getThumbName = function(n,prefix) {
				var arr = n.split("/");
				var tl = n.length;
				var w = arr[arr.length-1].length;
				return n.substr(0,tl-w-1)+"/"+prefix+arr[arr.length-1];
			};
		
		return $(this).each(function() {
			var x =$(this),
				list  = x.find("ul"),
				links = list.find("a"),
				images = list.find("img"),
				slidebar,
				thumbList,
				thumbCon,
				desc,
				arrow,
				back = options.leftArrow ? $(options.leftArrow) : $("<span class='arrowL arrow'><</span>"),
				next = options.rightArrow ? $(options.rightArrow) : $("<span class='arrowR arrow'>></span>"),
				xH,
				xW,
				li,
				liW,
				totalW,
				xTotalH,
				Pointer,
				total,
				tLink,
				isBusy = false,
				focusTo = function(p) {
					var offsetT = - (xH * p);
					if(isBusy) {
						list.stop();
					}
					isBusy = true;
					if(options.desc) {
						desc.html(options.desc[p]);
					} else {
						desc.html(images.eq(p).attr("alt"));
					}
					list.animate({
						top: offsetT +"px"
					},options.speed,options.easeOut,function() {
						isBusy = false;
					});
				};
			
			links.click(function() {
				return false;
			});
			
			//Insert a frame
			var xW = x.width();
			var xH = x.height();
			if(options.frame.isExist) {
				$("<div class='slideFrame' />").appendTo(x).css({
					width: x.width()-options.frame.width * 2,
					height: x.height()-options.frame.width * 2,
					border: options.frame.width+"px solid "+ options.frame.color,
					position: "absolute",
					top: 0,
					left: 0,
					zIndex: 4
				});
			}
			
			//Insert thumb list, arrows, captions
			desc = $("<p class='desc' />").appendTo(x);
			if(options.thumb || (!options.leftArrow || !options.rightArrow) ) {
				slidebar = $("<div class='slidebar' />").appendTo(x).fadeTo("fast");
			}
			if(options.thumb) {
				thumbList = $("<ul class='thumbList' />");
				thumbCon = $("<div class='wrap' />").append(thumbList).appendTo(slidebar);
				//prepare sidebar thumbs
				$.each(images,function(index,img){
					var imgId=$(img).parent().parent().attr("id");
					var imgHref=$(img).parent().attr("href");
					var imgTarget=$(img).parent().attr("target");
					if(!imgTarget){
						imgTarget='_self';
					}
					thumbList.append("<li><a href='"+imgHref+"' id='"+ (index+1) +"' class='item"+ imgId +"' target='"+imgTarget+"'></a></li>");
				});
				
				li = thumbList.find("li");
				liW = li.width();
				totalW = li.length * liW;
				thumbList.css("width",totalW).hover(function() {
					slidebar.fadeTo("fast");
				}, function() {
					slidebar.fadeTo("fast");
				});
				tLink = thumbList.find("a").each(function(i) {
					$(this).hover(function() {
						var id = this.id-1;
						(function(index){
							if(index!=Pointer) {
								focusTo(id);	
							}
						})(i);
					},function() {
						focusTo(Pointer);
					});
				});
			}
			if(!options.leftArrow || !options.rightArrow) {
				slidebar.append(next).prepend(back);
			}
			
			focusTo(0);
			
			desc.fadeTo("fast").hover(function() {
				$(this).fadeTo("fast");
			},function() {
				$(this).fadeTo("fast");
			});
			
			xTotalH = images.length * xH;
			list.css("height",xTotalH);
			
			Pointer = 0;
			total = images.length;
			
			if(!options.leftArrow) {back.fadeTo("fast");}
			
			if(options.loop) {//loop the slide
				setInterval(function() {
					next.click();
				},options.interval);
			}
			
			next.click(function() {
			if( total - Pointer > options.slideBy ) {
				if(options.thumb) {
				var offsetL =  parseInt(thumbList.css("left")) - liW * options.slideBy;
					thumbList.animate({
						left: offsetL + "px"
					},options.speed,options.easeThumb);
				}
				
				Pointer+=options.slideBy;
				focusTo(Pointer);
				
				if(!options.leftArrow) {
					back.fadeTo("fast");
				}
			} else {
				if(options.loop) {
					if(options.thumb) {
						thumbList.animate({
							left: "0px"
						},options.speed,options.easeThumb);
					}
					Pointer = 0;
					focusTo(0);
				} else {
					next.fadeTo("fast");
				}
			}
			return false;
			});
			
			back.click(function() {
			if(Pointer!=0) {
				if(options.thumb) {
					var offsetL =  parseInt(thumbList.css("left")) + liW * options.slideBy;
					thumbList.animate({
						left: offsetL + "px"
					},options.speed,options.easeThumb);
				}
				
				Pointer-=options.slideBy;
				focusTo(Pointer);
				
				if(!options.rightArrow) {
					next.fadeTo("fast");
					if(Pointer==0)//when slide back to the start
						back.fadeTo("fast");
				}
			}
			return false;
			});
		});
	}
};
$.fn.slideView = $.iSlideView.build;
})(jQuery);