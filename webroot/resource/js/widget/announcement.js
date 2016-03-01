(function($) {
	
	$.announcement = function(option) {
			var defaultOption={
				updateUrl:"#",
				content:"",
				width:$("body").width()-16,
				height:30,
				top:"0px",
				left:"0px",
				bottom:"0px",
				right:"0px",
				dataId:false
			};
			$.extend(defaultOption,option);
			var announcementName="announcement";
			var announcementValue=$.cookie(announcementName);
			if(announcementValue==null){
				$.cookie(announcementName,'', { path: '/', expires: 10 });
			};
			if(announcementValue==defaultOption.dataId){return false;}
			$("#announcement").remove();
			$("body").prepend('<div id="announcement"><div id="announcementBody"><div style="color:#FF6600;font-weight:bold;float:left;">公告：</div><div id="announcementContent"></div></div><div id="announcementClose"><a href="#"></a></div></div>');
			
			$("#announcementClose").click(function(){
				$("#announcement").hide();
				$.cookie(announcementName,defaultOption.dataId, { path: '/', expires: 10 });
			})
			
			
			announcementStyle();
			if(defaultOption.updateUrl!="#"){
				update();
			}
			else{
				$("#announcementContent").html(defaultOption.content);
			}
			function announcementStyle(){
				$("#announcement").css(
					{width:defaultOption.width},
					{height:defaultOption.height},
					{top:defaultOption.top},
					{left:defaultOption.left},
					{right:defaultOption.right},
					{bottom:defaultOption.bottom}
				);
			};
			
			function update(){
				$.ajax({
					url:defaultOption.updateUrl,
					datatype:"json",
					success:function(responseText){
						var data=eval("("+responseText+")");
						$("#announcementContent").html(data.content);
					}
				});
			}
		
	};

})(jQuery);