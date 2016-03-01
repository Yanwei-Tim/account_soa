<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<s:set value="#attr.ename" var="topename"/>
<div class="accordionMenuTit">
    <div id="accordionMenuBDT" class="accordionMenuBDT">
        <span class="icon ${ename}"></span>
        <span class="tit">${cname}</span>
    </div>
</div>
<div id="accordion" class="leftAccordion">
	<s:iterator value="permissions" var="p">
		<pop:JugePermissionTag ename="${p.ename}">
			<s:if test="childMap.get(#p.ename)==null || childMap.get(#p.ename).size()==0">
				<div class="noSubMenu">
					<a href="#${topename}-${p.ename}"
							id="${p.ename}" baseUrl="${p.normalUrl}"
							leaderUrl="${p.leaderUrl}"><span>${p.cname}</span></a>
				</div>
			</s:if>
			<s:else>
				<h3 class="uiMenuBase">
                    <span class="ui-icon"></span>${p.cname}
				</h3>
				<div class="uiContBase">
					<ul>
						<s:iterator value="childMap.get(#p.ename)" var="child">
							<pop:JugePermissionTag ename="${child.ename}">
								<li><a href="#${topename}-${child.ename}"
									id="${child.ename}" baseUrl="${child.normalUrl}"
									leaderUrl="${child.leaderUrl}"><span>${child.cname}</span></a></li>
							</pop:JugePermissionTag>
						</s:iterator>
						<!--  
						<s:if test="#p.cname.equals('系统管理')">
							<pop:JugePermissionTag ename="userManagement">
								 <li><a href="javascript:void(0)" id="usersListManagement" onclick="asyncOpen(this,'${path}/gis3D/sysadmin/layerManage.jsp')"><span class="usersListManagement">gis辖区管理</span></a></li>
							</pop:JugePermissionTag>
						</s:if>
						-->
					</ul>
				</div>
			</s:else>
		</pop:JugePermissionTag>
	</s:iterator>
</div>
<script type="text/javascript">
	$(function() {
        var accordion = $("#accordion");
        accordion.find(".uiMenuBase").on("click",function(){
            $(this).next(".uiContBase").toggle().end().toggleClass("uiMenuCur");
        })

        accordion.find(".uiMenuBase").hover(function(){
            $(this).addClass("uiMenuBaseHover")
        },function(){
            $(this).removeClass("uiMenuBaseHover")
        })

		$("#accordion a").click(function(){
			var baseUrl=$(this).attr("baseUrl");
			var leaderUrl=$(this).attr("leaderUrl");

			$('#accordion a').removeClass("cur");
			$(this).addClass("cur");

			if(baseUrl==undefined && leaderUrl==undefined){
				return false;
			}
			var ename=$(this).attr("id");
			if(ename=="gridIntroductionManagement"){
			if(isCountryDownOrganization()){
				if(isGrid()){
					baseUrl='${path}/hotModuel/baseinfo/villageProfile/gridProfileComplete.jsp';
				}else{
					baseUrl='${path}/hotModuel/baseinfo/villageProfile/villageProfileComplete.jsp';
				}
			}else{
				baseUrl='${path}/hotModuel/baseinfo/villageProfile/introduction.jsp';
			}
			}
			baseLoad(this,baseUrl,leaderUrl);
		});

		var jsflag='<s:property value='#parameters.urlflag'/>';
		if(jsflag==undefined || jsflag=='' || !$("#"+jsflag)[0]){
			$('#accordion a').eq(0).click().closest(".uiContBase:hidden").prev().click();
		}else{
			$('#accordion a#'+jsflag).click().closest(".uiContBase:hidden").prev().click();
		}
	});
</script>
