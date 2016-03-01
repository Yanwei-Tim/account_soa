<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<ul class="tabnav">
	<s:iterator value="permissions" var="p" >
	    <pop:JugePermissionTag ename="${p.ename}">
	        <li><span>${p.cname}</span></li>
	    </pop:JugePermissionTag>
	</s:iterator>
</ul>
<div class="tabbox">
	<s:iterator value="permissions" var="p" >
		<pop:JugePermissionTag ename="${p.ename}">
			<ul class="subnavbar">
				<s:iterator value="childMap.get(#p.ename)" var="child">
					<pop:JugePermissionTag ename="${child.ename}">
						<li><a href="javascript:void(0)" id="${child.ename}"
							<s:if test='#child.leaderUrl!=null&&!"".equals(#child.leaderUrl)'>
								onclick="asyncMiddleLevelOpenListOrChartView(this,'${path}${child.normalUrl}','${path}${child.leaderUrl}')"
							</s:if>
							<s:else>
								onclick="asyncMiddleLevelOpen(this,'${path}${child.normalUrl}')"
							</s:else>
							><span>${child.cname}</span></a></li>
					</pop:JugePermissionTag>
				</s:iterator>
			 </ul>
		</pop:JugePermissionTag>
	</s:iterator>
</div>
<script>
var _clickSwitch=true;
var jstype=null;
var jsflag=null;
var mark = 0;
	$(function() {
		var _box=$('.tabbox ul');
		var _hover='hover';
		var objwidth=0;
		function showBoxMenu(i,n){
			objwidth=objwidth+$(n).outerWidth();
			if(objwidth>screen.width-250){
				$(n).after('<li class="more"><img src="'+RESOURCE_PATH+'/resource/js/ui/default/popmenu2.gif" /></li>');
				$(".popMenu").attr("showMenu","false");
				$(".more").toggle(
						function () {
							$(".popMenu").show();
							$(this).html('<img src="'+RESOURCE_PATH+'/resource/js/ui/default/popmenu1.gif" />');
							var _index=$(this).parent().index();
							$(".popMenu").children().hide();
							$(".test"+_index,$(".popMenu")).show();
							$(".popMenu").attr("showMenu","true");
							$(".ceng").show();
						},
						function () {
							$(this).html('<img src="'+RESOURCE_PATH+'/resource/js/ui/default/popmenu2.gif" />');
							var _index=$(this).parent().index();
							$(".ceng").hide();
							$(".popMenu").children().hide();
							$(".popMenu").attr("showMenu","false");
						}
				);
				return false;
			};

		};


		var _tab=$('ul.tabnav>li');
		var boxindex=0;
		_tab.click(function(){
			var _index=_tab.index(this);
			boxindex=_index;
			$(this).addClass(_hover).siblings().removeClass(_hover);
			_box.eq(_index).show().siblings().hide();
			//前一次又焦点的box
			if(!$(".test"+_index).attr("afterProccess")){
				var currentBox = _box.eq(_index);
				$.each(currentBox.children(),showBoxMenu);
				objwidth=0;
				$(".popMenu").append($("<div class='test"+_index+"'/>").append($(".more",currentBox).nextAll()));
				$(".test"+_index).attr("afterProccess",true);
			}
			if(_clickSwitch){
				$("a:first",$($(".tabbox>ul")[boxindex])).click();
			}
		});
		$(".ui-layout-center").click(function() {
			if($(".popMenu").attr("showMenu")=="true"){
				_box.eq(boxindex).children(".more").click();
			}
		});

		setTimeout('showFirstMenu()',350);
		$("#gridIntroductionManagement").removeAttr("onclick").click(function(){showJspByOrgLevel($("#gridIntroductionManagement"))});
	});

	function showJspByOrgLevel(orgIntroduce){
		jstype=null;
		jsflag=null;
		mark=null;
		if(isCountryDownOrganization()){
			if(isGrid()){
				asyncMiddleLevelOpen(orgIntroduce,'${path}/baseinfo/villageProfile/gridProfileComplete.jsp');
			}else{
				asyncMiddleLevelOpen(orgIntroduce,'${path}/baseinfo/villageProfile/villageProfileComplete.jsp');
			}
		}else{
			//alert("timeout");
			asyncMiddleLevelOpen(orgIntroduce,'${path}/baseinfo/villageProfile/introduction.jsp');
		}
	};

	function showFirstMenu(){
		var _tab=$('ul.tabnav>li');
		var _sub=$(".subnavbar>li");
		jstype='<s:property value='#parameters.charttype'/>';
		jsflag='<s:property value='#parameters.urlflag'/>';
		if(jsflag=='' || jsflag==null || jsflag == 'undefined'){
			$(_tab[0]).click();
		}else{
			var thisTab=$("#"+jsflag);
			var parentTab=thisTab.closest(".subnavbar");
			_tab.eq(parentTab.index()).addClass("hover");
			parentTab.show().siblings().hide();
			thisTab.click();
		}
	}

	function onMenuChanged(node){
		if(!$.getSelectedNode(tree)){
			return false;
		}
		var district = <s:property value="@com.tianque.domain.property.OrganizationLevel@DISTRICT"/>
		var town = <s:property value="@com.tianque.domain.property.OrganizationLevel@TOWN"/>
		var village = <s:property value="@com.tianque.domain.property.OrganizationLevel@VILLAGE"/>
		var grid = <s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>
		var level = $.getSelectedNode(tree).attributes.orgLevelInternalId;
		var orgIntroduce = $.getSelectedNode(tree).attributes.orgInternalCode;
		if(level>district){
			$("#partyIntroduceLi").hide();
			$("#utonomyLi").hide();
			$("#massesLi").hide();
			$("#postulantLi").hide();
			$("#leaderGroupLi").hide();
			$("#compositeLi").show();
			if($("#leaderGroup").hasClass("click") ||$("#partyIntroduce").hasClass("click") || $("#utonomy").hasClass("click") || $("#masses").hasClass("click") || $("#postulant").hasClass("click")){
				clickLastVisibleA();
			}

		}else if(level==district ){
			$("#partyIntroduceLi").hide();
			$("#massesLi").show();
			$("#postulantLi").show();
			$("#leaderGroupLi").show();
			$("#utonomyLi").hide();
			$("#compositeLi").show();
			isHasClass();

		}else if(level==town){
			$("#partyIntroduceLi").hide();
			$("#utonomyLi").hide();
			$("#postulantLi").show();
			$("#leaderGroupLi").show();
			$("#massesLi").show();
			$("#compositeLi").show();
			isHasClass();

		}else if(level==village){
			$("#partyIntroduceLi").show();
			$("#postulantLi").show();
			$("#leaderGroupLi").show();
			$("#utonomyLi").show();
			$("#massesLi").show();
			$("#compositeLi").show();
		}else if(level==grid){
			$("#partyIntroduceLi").show();
			$("#utonomyLi").show();
			$("#massesLi").show();
			$("#postulantLi").show();
			$("#leaderGroupLi").show();
			$("#compositeLi").show();
			}


	}
	function isHasClass(){
		if($("#partyIntroduce").hasClass("click") || $("#utonomy").hasClass("click")){
			clickLastVisibleA();
		}
	}

	setTimeout('timeonMenuChanged()',500)

	function timeonMenuChanged(){
		onMenuChanged($.getSelectedNode(tree));
	}

	function asyncMiddleLevelOpenListOrChartView(srcObj, listUrl, charUrl){
		if(!isDistrictDownOrganization()){
			asyncMiddleLevelOpen(srcObj, charUrl);
		}else{
			asyncMiddleLevelOpen(srcObj, listUrl);
		}
	}

	function clickLastVisibleA(){
		$($(".click").parent().parent().find("a:visible").first()).click();
	}

</script>