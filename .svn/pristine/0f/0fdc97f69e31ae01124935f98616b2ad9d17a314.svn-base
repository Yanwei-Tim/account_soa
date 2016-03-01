<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div id="EdushiMap"></div>
<div class="PictureUpload">
	<ul>
		<li class="upPhoto-biaozhu">
			<span class="fileFind"><input id="fileToUpload" type="button" value="" name="header" /></span>
		</li>
	</ul>
</div>
<script>

function initMap(){
	if(typeof vEdushiMap =='undefined' || typeof vEdushiMap.Body.NewMapLayer !='function'){
		setTimeout("initMap()",50);
        return;
    } 
	var mapView = new TQMap.Map({tbeginLevel:1,vEdushiMap:vEdushiMap,veyeEdushiMap:veyeEdushiMap});
	mapView.removeFlgContextMenu();
	mapView.addZoomBar("EdushiMap");
	mapView.loadMethod();
	mapView.setOnMapMouseWheel(function(e){
		e = e || event;
		e.wheelDelta > 0 ? $("#EzoomMax").click() : $("#EzoomMin").click();
	})
	return mapView;
}

function setXYPointerFromParentWindow(makerInfo){
	makerInfo.x = $("#houseInfoCenterX").val();
	makerInfo.y = $("#houseInfoCenterY").val();
}
function getImgObject(makerInfo){
    setXYPointerFromParentWindow(makerInfo);
	var sopro = vEdushiMap.$C('img'); //创建img标记
    sopro.id = "_" + makerInfo.id; //设置img标记ID
    sopro.src = makerInfo.imgUrl; //设置img标记图片地址
    sopro.title = makerInfo.msg;
    sopro.msg = makerInfo.msg;
    sopro.x = makerInfo.x;
    sopro.y = makerInfo.y;
    sopro.style.cursor = "pointer";
    return sopro;
}
function moveMapToMarker(x,y){
	vEdushiMap.MoveTo(x,y);
}
function appendImgMarker(){
	if($("#houseInfoCenterX").val() && $("#houseInfoCenterY").val()){
		var makerInfo = { id: "L0", x: 8390, y: 9384, msg: "marker", imgUrl: "${path}/resource/edushiGis/images/searchMark.jpg" };
	    var sopro = getImgObject(makerInfo);
	    var _Layer = vEdushiMap.NewMapLayer('_Layer', 200); //创建图层
	    vEdushiMap.appendEntity(sopro, _Layer, false, makerInfo.x, makerInfo.y, 20, 20, 8, 20, true);
	    moveMapToMarker(makerInfo.x, makerInfo.y);
	    return sopro;
	}
}

function moveMarker(x,y){
	vEdushiMap.moveEntity("_L0", x,y);
	moveMapToMarker(x,y);
}

$(function(){
	function init(){
		var mapInitWidth=150;
		var mapInitHight=175;
		var mapUrl="<s:property value='@com.tianque.gis.util.GisGlobalValue@GIS_API_PATH'/>?City=xnc&L=zh-chs&MapID=EdushiMap&x=8390&y=9384&w="+mapInitWidth+"&h="+mapInitHight+"&eye=1&ew=190&eh=145&e=gb2312&z=3&v=4";
		$.getScript(mapUrl,function(){
		    var map = initMap();
		})
	}
	init();
	$("#fileToUpload").click(function(){
		$.createDialog({
			title : "标注位置",
			width : 600,
			height : 500,
			url:'${path}/common/mapIframe.jsp'
		});
	});
	setTimeout("appendImgMarker()",1000);
})
</script>