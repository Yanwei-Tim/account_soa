<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="logo-container">
	<a href="javascript:;"><img src="/resource/workBench/images/logo.png" /></a>
</div>
<div class="workbench-menu">
	<ul id="workbenchMenu">
		<li class="sysMenu"><a href="#">导航切换</a>
			<dl class="sysMenuList">
			<pop:JugePermissionTag ename="basicInformation">
					<dd><a href="/module.jsp#basicInformation"><strong class="basicInformation"></strong><s:property value="#request.name"/></a></dd>
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="digitalCityManagement">
					<dd><a href="/module.jsp#digitalCity"><strong class="digitalCityManagement"></strong><s:property value="#request.name"/></a></dd>
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="dailyLogManage">
					<dd><a href="/module.jsp#workingRecordMenu"><strong class="dailyLogManage"></strong><s:property value="#request.name"/></a></dd>
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="serviceWork">
					<dd><a href="/module.jsp#issue"><strong class="serviceWork"></strong><s:property value="#request.name"/></a></dd>
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="statAnalyseManage">
				    <dd><a href="/module.jsp#statAnalyse"><strong class="statAnalyseManage"></strong><s:property value="#request.name"/></a></dd>
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="evaluateManagement">
					<dd><a href="/module.jsp#evaluate"><strong class="evaluateManagement"></strong><s:property value="#request.name"/></a></dd>
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="callCenterManagement">
					<dd><a href="/module.jsp#callCenter"><strong class="callCenterManagement"></strong><s:property value="#request.name"/></a></dd>
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="commandCenterManagement">
					<dd><a href="/module.jsp#commandCenterManagement"><strong class="commandCenterManagement"></strong><s:property value="#request.name"/></a></dd>
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="incidentSystem">
					<dd><a href="/incident/index.jsp"><strong class="incidentSystem"></strong><s:property value="#request.name"/></a></dd>
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="interactionManagement">
					<dd><a href="/module.jsp#interactionManagement"><strong class="interactionManagement"></strong><s:property value="#request.name"/></a></dd>
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="peopleLog">
				<dd><a href="/module.jsp#peopleLog"><strong class="peopleLog"></strong><s:property value="#request.name"/></a></dd>
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="integrativeQueryManagement">
					<dd><a href="/module.jsp#integrativeQuery"><strong class="integrativeQueryManagement"></strong><s:property value="#request.name"/></a></dd>
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="systemManagement">
					<dd><a href="/module.jsp#systemManagement"><strong class="systemManagement"></strong><s:property value="#request.name"/></a></dd>
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="gisSystem">
					<dd><a href="/gis3D/gisView.jsp" target="_blank"><strong class="gisSystem"></strong>地理信息系统</a></dd>
				</pop:JugePermissionTag>
			</dl>
		</li>
		<!-- <li><a href="/desktop/"><span>桌面版</span></a></li> -->
	</ul>
	<ul class="header-right-toolMenu">
		<li id="message"><strong ></strong><a href="javascript:;" class="message"><span id="msg"><s:property value="unReadCount"/></span></a></li>
		<li id=""><a href="javascript:;" class="user-config" id="config"></a></li>
		<li><a id="exit" href="${path}/sessionManage/logout.action?isIndexJsp=true&indexPath=<s:property value="#parameters.indexPath[0]"/>" class="sys-exit"></a></li>
	</ul>
</div>
<div id="settingDialog">
</div>
<script>
$(function(){
	$('#exit').bind({
	    click: function() {
		var announcementName="announcement";
		var vlue = $.cookie(announcementName);
		if(vlue!=null){
			$.cookie(announcementName,null, { path: '/', expires: 10 });
		}
	   }
	});

	function getMessageByUser(){
		var messageOption={
			url:'${path}/sysadmin/userMessage/findUserMessages.action'

				,notAcceptOnlineAppeals:{title:"未阅读平台消息",link:"javascript:void(0)"
					<pop:JugePermissionTag ename="pmManagement">,limits:true</pop:JugePermissionTag>}

				,sessionTimeout: <s:property value='@com.tianque.core.util.GridProperties@SESSION_TIME_OUT'/>
			}
		$("body").messageTip(messageOption);
	}

	function messageFun(){//消息弹出组件
		this.process=function(msgNum){
			var messageOption={

				notAcceptOnlineAppeals:{title:"未阅读平台消息",link:"javascript:void(0)"
					<pop:JugePermissionTag ename="pmManagement">,limits:true</pop:JugePermissionTag>}

				,sessionTimeout: <s:property value='@com.tianque.core.util.GridProperties@SESSION_TIME_OUT'/>
				,data:msgNum
			}
			$("body").messageTip(messageOption);
		};
		this.sign="msgNum";
	}
	function announcementFun(){//公告组件
		this.process=function(data){
			if( data!=null && data.id!=null ){
				$.announcement({content:data.content,dataId:data.id,display:data.display});
			}
		};
		this.sign="proclamation";
	}

	function componentManager(){//组件管理器
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
	function messagePop(){
		$(".message-tip").remove();
		$.ajax({
			url:"${path}/sysadmin/userMessage/findUserMessages.action",
			success:function(data){
				var messageNum = 0;

				if(data&&data.personnelMessageNum){
					messageNum=messageNum+data.personnelMessageNum;
				}

		        $("#msg").html(messageNum);
		        if(messageNum>0){
		        	$.cookie("messageTip",true, { path: '/', expires: 10 });
		        }
				$("body").messageTip({
					url:'${path}/sysadmin/userMessage/findUserMessages.action'
					,notAcceptOnlineAppeals:{title:"未阅读平台消息",link:"javascript:void(0)"
						<pop:JugePermissionTag ename="pmManagement">,limits:true</pop:JugePermissionTag>}
					,data:data
				});
			}
		});
	}
	var supervisor=new componentManager();
	supervisor.add(new messageFun());
	supervisor.add(new announcementFun());
	supervisor.start();


	$(".message").click(function(){
		messagePop();
	});

	messagePop();

	$("#workbenchMenu li").hover(function(){
		$(this).addClass("cur");
	},function(){
		$(this).removeClass("cur");
	})
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
	})

	$("#config").click(function(){
		$("#settingDialog").createDialog({
			width:450,
			height:280,
			title:"设置",
			url:"${path}/workBench/setting.jsp",
			buttons: {
				"保存" : function(event){
				var selected=$( "#tabs").tabs('option', 'selected');
				if(selected==0){
					$("#updateDetailsForm").submit();
				}else if(selected==1){
					$("#firstUpdatePassForm").submit();
					}
   			     },
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});

		});
})
</script>