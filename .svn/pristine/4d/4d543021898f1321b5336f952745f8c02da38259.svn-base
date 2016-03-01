<!DOCTYPE html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<title>我的工作台</title>
<jsp:include page="/includes/baseInclude.jsp" />
<jsp:include page="/includes/jsInclude.jsp" />
<script type="text/javascript" src="${resource_path}/resource/workBench/js/jquery.SuperSlide.js"></script>
<script type="text/javascript" src="${resource_path}/resource/workBench/js/workbench.widget.js"></script>
<script type="text/javascript" src="${resource_path}/resource/workBench/js/peopleLog/peopleLog.js" ></script>
<script type="text/javascript" src="${resource_path}/resource/workBench/js/statAnalyse/statAnalyse.js" ></script>
<script type="text/javascript" src="${resource_path}/resource/workBench/js/calendarWidget.js" ></script>
<script>
$(function(){
	$(".menu .sysMenu").hover(function(){
		clearTimeout(window._menuHideTimer);
		window._menuShowTimer=setTimeout(function(){
			$(".sysMenuList").fadeIn(200);
		},300)
	},function(){
		clearTimeout(window._menuShowTimer);
		window._menuHideTimer=setTimeout(function(){
			$(".sysMenuList").fadeOut(200);
		},300);
	})
	
	//注销按钮
	$('#exit').bind({
	    click: function() {
		var announcementName="announcement";
		var vlue = $.cookie(announcementName);
		if(vlue!=null){
			$.cookie(announcementName,null, { path: '/', expires: 10 });
		}
	   }
	});
	
	//获取用户未读消息
	function getMessageByUser(){
		var messageOption={
				url:'${path}/sysadmin/userMessage/findUserMessages.action',
				notAcceptNote:{title:"未受理短信",link:"/module.jsp#interactionManagement-smsInboxManagement"
					<pop:JugePermissionTag ename="smsManagement">,limits:true </pop:JugePermissionTag>},
				notAcceptOnlineAppeals:{title:"未阅读平台消息",link:"/module.jsp#interactionManagement-pmInboxManagement"
					<pop:JugePermissionTag ename="pmManagement">,limits:true</pop:JugePermissionTag>},
				backlog:{title:"待办事项",link:"/module.jsp#serviceWork-myIssueListManagement"
					<pop:JugePermissionTag ename="serviceWork">,limits:true</pop:JugePermissionTag>},
				sessionTimeout:<s:property value='@com.tianque.core.util.GridProperties@SESSION_TIME_OUT'/>
			}
		$("body").messageTip(messageOption);
	}
	
	//消息弹出组件
	function messageFun(){
		this.process=function(msgNum){
			var messageOption={
					notAcceptNote:{title:"未受理短信",link:"/module.jsp#interactionManagement-smsInboxManagement"
						<pop:JugePermissionTag ename="smsManagement">,limits:true </pop:JugePermissionTag>},
					notAcceptOnlineAppeals:{title:"未阅读平台消息",link:"/module.jsp#interactionManagement-pmInboxManagement"
						<pop:JugePermissionTag ename="pmManagement">,limits:true</pop:JugePermissionTag>},
					backlog:{title:"待办事项",link:"/module.jsp#serviceWork-myIssueListManagement"
						<pop:JugePermissionTag ename="serviceWork">,limits:true</pop:JugePermissionTag>},
					sessionTimeout: <s:property value='@com.tianque.core.util.GridProperties@SESSION_TIME_OUT'/>,
					data:msgNum
				}
			$("body").messageTip(messageOption);
		};
		this.sign="msgNum";
	}
	
	//公告组件
	function announcementFun(){
		this.process=function(data){
			if( data!=null && data.id!=null ){
				$.announcement({content:data.content,dataId:data.id,display:data.display});
			}
		};
		this.sign="proclamation";
	}

	//组件管理器
	function componentManager(){
		var myArray=new Array();
		var Interval;
		this.add=function(fn){
			myArray[myArray.length]=fn;
		}
		this.start=function(time){
			if(time==undefined){time=30000};
		};
		this.stop=function(){
			clearInterval(Interval);
		};
		return this;
	}

	//弹出消息
	function messagePop(){
		$(".message-tip").remove();
		$.ajax({
			url:"${path}/sysadmin/userMessage/findUserMessages.action",
			success:function(data){
				var messageNum = 0;
				if(data&&data.personnelMessageNum){
					messageNum=data.personnelMessageNum;
				}
		        if(messageNum>0){
		        	$.cookie("messageTip",true, { path: '/', expires: 10 });
		        }
				$("body").messageTip({
					url:'${path}/sysadmin/userMessage/findUserMessages.action',
					notAcceptNote:{title:"未受理短信",link:"/module.jsp#interactionManagement-smsInboxManagement"
						<pop:JugePermissionTag ename="smsManagement">,limits:true </pop:JugePermissionTag>},
					notAcceptOnlineAppeals:{title:"未阅读平台消息",link:"/module.jsp#interactionManagement-pmInboxManagement"
						<pop:JugePermissionTag ename="pmManagement">,limits:true</pop:JugePermissionTag>},
					backlog:{title:"待办事项",link:"/module.jsp#serviceWork-myIssueListManagement"
						<pop:JugePermissionTag ename="serviceWork">,limits:true</pop:JugePermissionTag>},
					data:data
				});
			}
		});
	}
	
	var supervisor=new componentManager();
	supervisor.add(new messageFun());
	supervisor.add(new announcementFun());
	supervisor.start();

	//手动点击弹出消息
	$("#message").click(function(){
		messagePop();
	});

	messagePop();

	$("#workbenchMenu li").hover(function(){
		$(this).addClass("cur");
	},function(){
		$(this).removeClass("cur");
	});
	
	$("#workbenchMenu .sysMenu").hover(function(){
		clearTimeout(window._menuHideTimer);
		window._menuShowTimer=setTimeout(function(){
			$(".sysMenuList").slideDown("slow");
		},300)
	},function(){
		clearTimeout(window._menuShowTimer);
		window._menuHideTimer=setTimeout(function(){
			$(".sysMenuList").slideUp(200);
		},300);
	});

});
</script>
</head>
<body style="padding-top:65px;">
	<div class="workbench-header">
		<s:action namespace="/sysadmin/menuManage" name="getNavigationList"  executeResult="true" ></s:action>		
		<input type="hidden" id="currentOrgId" value="1" />
	</div>
	<div id="msg-tips-index" class="tipsbox">
		<p id="loading" class="loadbox"><img src="/resource/workBench/images/loading_comment.gif" alt="loading">正在获取信息，请稍候...</p>
	</div>
<div class="workbench-body">
	<div class="workbench-ctt-left">
			<jsp:include page="/workBench/workBenchLeft.jsp" />
	</div>
	
	<div class="workbench-content">
		<div class="workbench-toolBar">
			<jsp:include page="/workBench/searchBar.jsp" />
		</div>
		<div class="workbench-ctt-content">
			<div class="workbench-center">
				<jsp:include page="/workBench/workBench.jsp" />
			</div>
			<div class="workbench-right">
				<div class="workbenchTabList">
					<s:action name="getWorkBenchTabPatelConfiger" namespace="/workBence/tableConfigManage" executeResult="true">
						<s:param name="keyName">workBench</s:param>
					</s:action>
				</div>
				<br>
				<div class="workbenchTabList" style="height:320px;">
					<jsp:include page="/workBench/workBenchMap.jsp" />
				</div>
			</div>
		</div>
	</div>
</div>
<div id="bottom">
	<jsp:include page="/includes/footer.jsp"></jsp:include>
</div>
<div id="jGrowl"></div>
<div id="peopleLogDialog"></div>
<div id="logViewDialog"></div>
<div id="subDialog"></div>
<div id="workBenchplatformMessageDialog"></div>
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
</script>
<!--[if IE 6]>
	<script type="text/javascript" src="${resource_path}/resource/js/DD_belatedPNG.js" ></script>
	<script type="text/javascript">
		$(function(){
			DD_belatedPNG.fix('.logo-container img,#workbench-message .message,.sys-exit,.inform li,.today li,.search-box,.message,#config');
		})
	</script>
<![endif]-->
</body>
</html>