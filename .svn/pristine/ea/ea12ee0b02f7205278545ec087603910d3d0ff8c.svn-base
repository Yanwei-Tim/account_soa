<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<div class="systemHeader">
	<div class="logo-container">
		<img src="/resource/system/images/logo.png" />
	</div>
	<div class="product-menu">
		<ul id="topMenu">
			<li class="sysMenu"><a href="javascript:;">导航切换</a>
				<dl class="sysMenuList cf">
				<s:iterator value="permissions" var="p" >
                    <pop:JugePermissionTag ename="${p.ename}">
                    <s:if test="'gisManageSystem'.equals(#p.ename)">
                      <dd><a target="_blank" href="<s:property value='@com.tianque.core.util.GridProperties@GIS_SERVER'/>${gisUrl}?sid=<s:property value="@com.tianque.core.util.ThreadVariable@getSession().sessionId"/>" rel="" id="gisHref"><strong class="gisManageSystem"></strong><s:property value="#request.name"/></a></dd>
                     </s:if>
                     <s:elseif test="'unifiedSearchManageSystem'.equals(#p.ename)">
                     <dd><a target="_blank" href="${unifiedSearchUrl}?plugin=402881e43ca8df7b013ca8e4f68a0001&sessionId=<s:property value="@com.tianque.core.util.ThreadVariable@getSession().sessionId"/>" rel="" id="tqsearchHref"><strong class="unifiedSearchManageSystem"></strong><s:property value="#request.name"/></a></dd>
                    </s:elseif >
                     <s:else>
                     <dd><a href="/module.jsp#${p.ename}" rel="${p.ename}" id="${p.ename}-menu"><strong class="${p.ename}"></strong><s:property value="#request.name"/></a></dd>
                     </s:else>
                    </pop:JugePermissionTag>
				</s:iterator>

				</dl>
			</li>
		</ul>
	</div>
    <div class="login-window">
    	<s:if test="null!=@com.tianque.core.util.ThreadVariable@getUser().getHeaderUrl()">
    		<div class="pic" id="settingInfoPic" title="个人设置"><img src="<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getHeaderUrl()"/>" alt="" /></div>
    	</s:if>
    	<s:else>
	        <div class="pic" id="settingInfoPic" title="个人设置"><img src="/resource/system/images/admin.png" alt="" /></div>
    	</s:else>
        <div class="cont">
            <div class="personal"><span id="personelInfo">欢迎您！<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getName()"/></span><span id="modifyRoleLi" style = "display:none"><a id="modifyRole" href="javascript:;">[用户切换]</a></span>
            </div>
            <ul class="switchFunction">
            	<li class="fli" title="个人设置"><span id="settingInfo">个人设置</span></li>
            	<li id="functionContList" class="fli"><span id="functionInfo" class="functionInfo">风格设置</span>
            		<ul class="functionCont hidden">
            			<li class="func"><strong class="setC skinC"></strong><span>换肤</span>
            				<ul class="list hidden" id="loopSkinCont">
            					<li><a value="blue" href="javascript:;"><span class="blue-color"></span>商务蓝</a></li>
            					<li><a value="red" href="javascript:;"><span class="red-color"></span>中国红</a></li>
            					<li><a value="green" href="javascript:;"><span class="green-color"></span>草原绿</a></li>
            				</ul>
            			</li>
            			<li class="func"><strong class="setC fontC"></strong><span>字号设置</span>
            				<ul class="list hidden" id="loopTypeFaceCont">
            					<li><a class="default" href="javascript:;">小字号</a></li>
            					<li><a class="big" href="javascript:;">大字号</a></li>
            				</ul>
            			</li>
            		</ul>
            	</li>
			</ul>
        </div>
    </div>
    <ul class="header-right-toolMenu">
		<li title="主页"><a href="/module.jsp#index" onclick="showPageByTopMenu('index')" class="user-config" id="config"></a></li>
		<li title="退出"><a id="exit" href="${path}/sessionManage/logout.action?isIndexJsp=true&indexPath=<s:property value="#parameters.indexPath[0]"/>" class="sys-exit"></a></li>
	</ul>
    <div class="switchSkin_red_rightBg"></div>
</div>
<div id="shouldLogin"></div>
<div id="settingDialog"></div>
<div id="maintainDlg"></div>
<!--[if IE 6]>
	<script type="text/javascript" src="${resource_path}/resource/js/DD_belatedPNG.js" ></script>
	<script type="text/javascript">
		$(function(){
			DD_belatedPNG.fix('.login-window img,.logo-container img,#menu li strong,#top-submenu li strong,.header-right-toolMenu a,dl.sysMenuList dd a strong,.switchFunction #functionInfo');
			$(".sysMenuList").bgiframe();
		})
	</script>
<![endif]-->
