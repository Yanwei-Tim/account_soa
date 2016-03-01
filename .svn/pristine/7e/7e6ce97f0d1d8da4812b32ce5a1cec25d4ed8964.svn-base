function rendSupervise(el, options, rowData){
	if(1==rowData.supervisionState || 1==el){
		return "<img src='/resource/js/ui/images/normalcard.gif'>";
	}else if(100==rowData.supervisionState){
		return "<img src='/resource/js/ui/images/yellowcard.gif'>";
	}else if(200==rowData.supervisionState){
		return "<img src='/resource/js/ui/images/redcard.gif'>";
	}else{
		return "";
	}
	
}
function rendUrgent(el, options, rowData){
	if(1==rowData.urgent || el==true){
		return "<img src='/resource/js/ui/images/immediate.gif'>";
	}else{
		return "";
	}
}