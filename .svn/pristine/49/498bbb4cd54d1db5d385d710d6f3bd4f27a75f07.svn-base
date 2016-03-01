$.extend({
	dataDistributionPop:function(o){
		var that;
		var dfop={
			width:400,
			height:'auto',
			x:500,
			y:300,
			zIndex:999,
			content:'<div></div>'
		}
		$.extend(dfop,o);
		var pop={
			init:function(){
				this.build();
				this.style(this.tip);
			},
			build:function(){
				var tipContent=$('<div class="tips-text"></div>');
				var tip=$('<div class="tips dataDistributionPop"><div class="tips-angle diamond"></div></div>')
				tipContent.html(dfop.content);
				tip.prepend(tipContent).appendTo("body");
				this.tip=tip;
			},
			style:function(tip){
				tip.css({left:dfop.x,top:dfop.y,width:dfop.width,height:dfop.height})
			},
			destroy:function(){
				$(".dataDistributionPop").remove();
			}
		}
		pop.init();
		that=pop;
		return that;
	}
})


var incidentMenuFunction={	
	predictionScheme:function(selectedorgId,menuType){
		$.selectMenu("predictionScheme-menu");
		function afterLoad(){
			$(".submenu").hide();
			$(".path").show();
			$(".path").load(PATH+"/incident/extTreeForIncident/title.jsp",function(){
				$.ajax({
					url : PATH+'/incident/plaitScheme/index.jsp',
					cache: false,
					success : function(result) {
						proccessLoginResult(result,function(){hideLoading();$("#contentDiv").html(result);});
					}
				});
			});
		}
		plaitSchemeTreeShow(true,afterLoad);
	},
	emergencyDisposal:function(selectedorgId,menuType){
		$.selectMenu("emergencyDisposal-menu");
		menuBoxShow();
		$(".path").hide();
		$(".ui-layout-west").load("/incident/emergencyDisposal/emergencyDisposalSideBar.jsp");
		$(".submenu").hide();
		$(".path").show();
		$(".path").load(PATH+"/incident/emergencyDisposal/left/earlyWarningTitle.jsp");
	},
	incidentManagement:function(selectedorgId,menuType){
		$.selectMenu("incidentManagement-menu");
		function afterLoad(){
			$(".submenu").hide();
			$(".path").show();
			$(".path").load(PATH+"/incident/incidentManage/title.jsp",function(){
				$.ajax({
					url : PATH+'/incident/incidentManage/index.jsp',
					cache: false,
					success : function(result) {
						proccessLoginResult(result,function(){hideLoading();$("#contentDiv").html(result);});
					}
				});
			});
		}
		baseTreeShow(true,afterLoad);
	}
}

