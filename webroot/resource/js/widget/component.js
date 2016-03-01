
$.TianqueComponent=function(option){
	var defaultOption={
		list:{
			multiselect:true
		}
	}
	$.extend(defaultOption,option);
	new ComponentFactory(defaultOption);
}

var ComponentFactory=function(option){
	for (var key in option){
		this[key](option[key]);
	}
}
ComponentFactory.prototype.afterLoad=function(option){
	
}
ComponentFactory.prototype.list=function(option){
	$("#"+option.id).jqGridFunction(option);
}
ComponentFactory.prototype.buttons=function(option){
	for(var i=0;i<option.length;i++){
		if(option[i].click){
			$("#"+option[i].id).bind("click",{option:option[i]},function(event){
				if(!event.data.option.before||event.data.option.before()){
					$("#"+event.data.option.click.dialogId).createDialog(event.data.option.click);
				}
			});
		}
		if(option[i].change){
			$("#"+option[i].id).bind("change",{option:option[i]},function(event){
				if(!event.data.option.before||event.data.option.before()){
					event.data.option.change();
				}
			});
		}
	}
}