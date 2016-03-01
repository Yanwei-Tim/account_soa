<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="${resource_path}/resource/workBench/js/jquery.SuperSlide.js"></script>
<script type="text/javascript" src="${resource_path}/resource/workBench/js/workbench.widget.js"></script>
<script type="text/javascript" src="${resource_path}/resource/workBench/js/peopleLog/peopleLog.js" ></script>
<script type="text/javascript" src="${resource_path}/resource/workBench/js/statAnalyse/statAnalyse.js" ></script>
<script type="text/javascript" src="${resource_path}/resource/workBench/js/calendarWidget.js" ></script>
<script>
	$(function(){
		$(".tools").click(function(){
			if($(".tools_content").is(":visible")){
				$(".tools_content").hide();
			}else{
				$(".tools_content").show();
				
				$(".tools_content").hover(function(){
					$(this).show();
				},function(){
					$(this).hide();
				})
			}
			
		})
		function workbenchContentHeight(){
			var timer;
			function getHeight(){
				var getClientHeight=document.documentElement.clientHeight||document.body.clientHeight;
				$(".workbench-ctt-content").height(getClientHeight-160);
			}
			getHeight();

			$(window).resize(function(){
				clearTimeout(timer);
				timer=setTimeout(function(){getHeight();},200);
			})
		}
		workbenchContentHeight();
	})
	var setPNG=function(img,w,h) {
        if(navigator.userAgent.indexOf('MSIE 6')>-1){
            var i = "display:inline-block;";
            var s = "<span style=\"width:" + w + "px; height:" + h + "px;" + i + ";" + "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + img.src + "', sizingMethod='scale');\"></span>";
            img.outerHTML = s;
        }
    };
</script>
<div class="workbenchMain">
	<div class="workbench-body">
		<div class="workbench-ctt-left">
				<jsp:include page="/workBench/workBenchLeft.jsp" />
		</div>
		
		<div class="workbench-content">
		首页
		</div>
	</div>
	<div id="jGrowl"></div>
	<div id="peopleLogDialog"></div>
	<div id="logViewDialog"></div>
	<div id="subDialog"></div>
	<div id="workBenchplatformMessageDialog"></div>
</div>
<!--[if IE 6]>
	<script type="text/javascript" src="${resource_path}/resource/js/DD_belatedPNG.js" ></script>
	<script type="text/javascript">
		$(function(){
			DD_belatedPNG.fix('.portlet_box_person img,.ui-widget-header .ui-icon,.logo-container img,#workbench-message .message,.sys-exit,.inform li,.today li,.search-box,.message,#config');
		})
	</script>
	
<![endif]-->
