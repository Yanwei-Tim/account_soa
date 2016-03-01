<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<html>
<head>
	<script type="text/javascript" src="${resource_path}/resource/js/jquery.min.js"></script>
	<script type="text/javascript">
	function updateParentGisInfo(buildingId,centerX,centerY){
		window.parent.$("#houseInfoBuildingId").val(buildingId);
    	window.parent.$("#houseInfoCenterX").val(centerX);
    	window.parent.$("#houseInfoCenterY").val(centerY);
	}
	function setXYPointerFromParentWindow(makerInfo){
		if(window.parent.$("#houseInfoCenterX").val()&&window.parent.$("#houseInfoCenterY").val()){
			makerInfo.x=window.parent.$("#houseInfoCenterX").val();
			makerInfo.y=window.parent.$("#houseInfoCenterY").val();
		}
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
	function getImgMarker(){
		var makerInfo = { id: "L0", x: 8390, y: 9384, msg: "marker", imgUrl: "${path}/resource/edushiGis/images/searchMark.jpg" };
        var sopro = getImgObject(makerInfo);
        if(!(window.parent.$("#houseInfoCenterX").val()&&window.parent.$("#houseInfoCenterY").val())){
	        vEdushiMap.onMapMouseMove=function(e){
	        	vEdushiMap.moveEntity("_L0", vEdushiMap.PointerX(), vEdushiMap.PointerY());
	        };
        }
        var _Layer = vEdushiMap.NewMapLayer('_Layer', 200); //创建图层
        vEdushiMap.appendEntity(sopro, _Layer, false, makerInfo.x, makerInfo.y, 20, 20, 8, 20, true);
        return sopro;
	}
	function onfnLoad() {
        vEdushiMap.onSpotClick=function(spot){
        	updateParentGisInfo(spot.ID,vEdushiMap.PointerX(),vEdushiMap.PointerY());
        	if(window.parent.vEdushiMap.getEntityInfo("_L0")){
        		window.parent.moveMarker(vEdushiMap.PointerX(),vEdushiMap.PointerY());
        	}else{
        		window.parent.appendImgMarker();
        	}
        	vEdushiMap.onMapMouseMove=function(){};
        }
        var sopro = getImgMarker();
        sopro.onclick = function() {
        	vEdushiMap.getEntityInfo("_L0").exX=8;
        	vEdushiMap.getEntityInfo("_L0").eyY=20;
        	vEdushiMap.onMapMouseMove=function(e){
            	vEdushiMap.moveEntity("_L0", vEdushiMap.PointerX(), vEdushiMap.PointerY());
            };
        }
        
	}
	
	$(function(){
		var mapUrl="<s:property value='@com.tianque.gis.util.GisGlobalValue@GIS_API_PATH'/>?City=xining&L=zh-chs&MapID=EdushiMap&x=8390&y=9384&w=560&h=420&eye=1&ew=190&eh=145&e=gb2312&z=3&v=4";
		$.getScript(mapUrl,function(){
			setTimeout("onfnLoad()",500);
		})
		
	})
	
	
	</script>
</head>
<body>
<div id="EdushiMap" style="z-index: 1; top: 27px; left: 0px;"/>
</body>
</html>