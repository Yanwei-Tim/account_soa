function excelDownload(actionUrl,gridName,dialogId,title){
    var ieset = navigator.userAgent;  
    if(ieset.indexOf("MSIE 6.0") > -1){//浏览器判断 如果是IE6
		$("#"+dialogId).createDialog({
			width: 250,
			height: 200,
			title:title,
			url:'${path}/common/exportExcel.jsp',
			postData:{
				gridName:gridName,
				downloadUrl:actionUrl
				},
			buttons: {
	   			"导出" : function(event){
					$("#exceldownload").submit();
	        		$(this).dialog("close");
   				},
	   			"关闭" : function(){
	        		$(this).dialog("close");
	   			}
			},
			shouldEmptyHtml:false
		});
    }else{
    	if(gridName != ""){
    		actionUrl=actionUrl+"?"+$.param($("#"+gridName).getPostData());
    	}
    	var htmlIF = "<iframe style='display: none;' src='"+actionUrl+"'></iframe>";
    	$("body").append(htmlIF);
    }
}