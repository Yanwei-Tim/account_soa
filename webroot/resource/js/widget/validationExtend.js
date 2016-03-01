function orgLevelLessEqual(orgId,level){
	var bol = false;
	$.ajax({
		async:false,
		url:PATH+"/tools/org/levelCompare.action",
		data:{
			"orgId":orgId,
			"orgLevel":level
		},
		success:function(responseData){
			bol = responseData<=0;
		}
	});
	return bol;
}

jQuery.validator.addMethod("orgLevelLessEqual", function(value, element,params){
	if(params[0]==null||params[0]==undefined||params[0]==""){return false;}
	return orgLevelLessEqual(params[0],params[1])==true || orgLevelLessEqual(params[0],params[1])=="true";
});


