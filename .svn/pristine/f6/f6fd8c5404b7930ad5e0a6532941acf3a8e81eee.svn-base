<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="queryDialog"></div>
<s:action name="getMyAttention" var="getMyAttention" namespace="/workBence/tableConfigManage" executeResult="false">
</s:action>
<div class="workbench clearfix" id="workBench_center">
	<div class="column column-jurisdiction" id="">
		<div class="portlet">
		<s:if test="'lower'.equals(#getMyAttention.personalWorkBenchType)">
			<div class="portlet-header">重点服务对象</div>
			<div class="portlet-content portlet-content-jurisdiction">
				<div id="workBench_MyVisitRecord" class="informationTrain">
					<s:action name="findMyVisitRecordForBigTypeByOrgInternalCode" namespace="/workBench/myVisitRecordManage" executeResult="true">
					</s:action>
				</div>
			</div>
		
		</s:if>
		<s:else>
		<div class="portlet-header">辖区工作开展情况</div>
			<div class="portlet-content portlet-content-jurisdiction">
				<div id="workBench_MyAttention" class="informationTrain">
					<s:action name="findJurisdictionWorkInfo" namespace="/workBench/jurisdictionWork" executeResult="true">
					</s:action>
				</div>
			</div>
		</s:else>
		</div>
	</div>

	<div class="column column-daily" id="">
		<div class="portlet">
			<div class="portlet-header">日常工作提醒</div>
		<div class="portlet-content daily-content">
				<div id="workBench_DailyWorkNotice" class="informationTrain">
				<s:action name="findDailyWorkNoticeInfo" namespace="/workBench/dailyWorkNotice" executeResult="true">
				</s:action>
				</div>
			</div>
		</div>
	</div>

</div>

<script>

$(function() {
	function portletHeight(){
		var timer;
		function getPortlet(){
			var clientHeight=$(".workbench-ctt-content").height();
			var portletHeight=$(".column-jurisdiction").height();
			var colomnHeight=clientHeight-portletHeight-75;
			var clientWidth=document.documentElement.clientWidth||document.body.clientWidth;
			var mainWidth=$(".workbench-content").width();
			var workbenchMap=$(".workbenchTabList #workbenchMap").height();
			$(".column-daily .daily-content").height(workbenchMap-26);
			var columnDailyHeight=$(".column-daily").height();
			//$(".workbenchTabList #workbenchMap").height(columnDailyHeight-1);
			$(".workbenchManage").width(mainWidth-260);
			$(".workbenchMap").width(mainWidth-264);
			$(".sysMenuList").width(clientWidth);
			$("#workbenchMap #ifr").width(mainWidth-270);
		}
		getPortlet();
		$(window).resize(function(){
			clearTimeout(timer);
			timer=setTimeout(function(){
				getPortlet();
			},1);
		})
	}
	portletHeight();

	$("#workBench_center .portlet_box_list a").click(function(){
		var rel=$(this).attr("href");
		var url = rel.split("#")[1];
		showPageByTopMenu(url);
	});
    function visitRecordTabs(){
        var root = $("#workBench_MyVisitRecord"),
            roll_box = root.find(".portlet_box_list"),
            btn_left = root.find(".portlet_roll_left"),
            btn_right = root.find(".portlet_roll_right"),
            roll_box_item = roll_box.find(".portlet_box"),
            
            boxLength = roll_box.find(".portlet_box").length,
            curPostion =0,
            curLoadSort =0,
            outerWidth = roll_box.find(".portlet_box").outerWidth(),
            pannelLength=outerWidth*boxLength,
            prepareLoad;

        function init(){

            //如果只有两个那么隐藏标签
            if(boxLength < 3){
                root.children("a").hide();
            }
            $("#workBench_MyVisitRecord").find(".portlet_box_list").css("width",pannelLength);
           roll_box.css("width",pannelLength);  //预置宽度
            
            roll_box_item.eq(curLoadSort).addClass("cur");
            var firstId = roll_box_item.eq(curLoadSort).attr("id");
            domLoad(firstId)

            //添加各种事件并加载数据
            roll_box_item.click(function(){
                var self = $(this),
                    index = $(this).index(),
                    id = self.attr("id");
                if( index != curLoadSort ){
                    roll_box_item.removeClass("cur");
                    self.addClass("cur")
                    domLoad(id)
                    curLoadSort = index;
                }
                return false;
                
            }).hover(
                function(){
                    
                    var self = $(this),
                        index = $(this).index(),
                        id = self.attr("id");
                    if( index != curLoadSort ){
                        prepareLoad = setTimeout(function(){
                            roll_box_item.removeClass("cur")
                            self.addClass("cur")
                            domLoad(id)
                            curLoadSort = index;
                        },300)
                    }
                },function(){
                    clearTimeout(prepareLoad);
                }
            );
            root.delegate(".portlet_roll_left","click",leftRolling);
            root.delegate(".portlet_roll_right","click",rightRolling);
        
        }
        function leftRolling(){
            if(curPostion > 0){
                roll_box.animate({left:-(curPostion*outerWidth - outerWidth)},200)
                curPostion = curPostion - 1;
            }
        }
        function rightRolling(){
            if(curPostion < boxLength-2){
                roll_box.animate({left:-(curPostion*outerWidth + outerWidth)},200)
                curPostion = curPostion + 1;
            }
        }
        function domLoad(id){
            /* 发送请求捞取页面
            */
            $.ajax({
                url:"/workBench/myVisitRecordManage/findMyVisitRecordByPersonType.action?personType=" + id,
                success:function(dom){
                    root.find(".portlet_object").html(dom)
                }
            }); 
        }
        init()
    }
    visitRecordTabs()

	$(document).unbind("click").on("click",function(e){
		var $target=$(e.target);
		if(!($target.closest("#workbench-message")[0])){
			$(".workbench-message").hide();
		}
		if(!($target.closest("#customPanel")[0]) && !($target.is(".workbench-setting"))){
			$("#customPanel").hide();
		}
		if(!($target.closest(".panelconfig")[0])&& !($target.is(".panel-set"))){
			$(".panelconfig").hide();
		}
		if(!($target.closest(".workMemoTip")[0]) && !($target.is(".ui-state-default"))){
			$(".workMemoTip").hide();
		}
		if(!($target.closest("#chooseEvent")[0])){
			$("#chooseMenu").hide();
		}
		if(!($target.closest("#maxChooseEvent")[0])){
			$("#maxChooseMenu").hide();
		}
		if(!($target.closest(".updownList")[0])){
			$(".updwonlist-Content").hide();
		}
	})

	setTimeout(function(){
		$("#msg-tips-index").hide();
	},1000);
	
	$("#navigationMap").click(function(){
		$("#queryDialog").createDialog({
			width: 600,
			height: 600,
			title:'系统功能导航',
			url:'${path}/sysadmin/menuManage/getNavigationMap.action',
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
});
</script>
