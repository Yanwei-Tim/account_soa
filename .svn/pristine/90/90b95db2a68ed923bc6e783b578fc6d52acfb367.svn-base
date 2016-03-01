	function jqGridMultiSelectState(jqgridId, selected){
		if (selected){
			$("#cb_"+jqgridId).attr("checked",'true');
		}else{
			$("#cb_"+jqgridId).removeAttr("checked");
		}
	}
	
	function getActualjqGridMultiSelectCount(jqgridId){
		var selectedIds=getActualjqGridMultiSelectIds(jqgridId);
		if (selectedIds==null || typeof(selectedIds)=="undefined"){
			return 0;
		}else{
			return selectedIds.length;
		}
	}

	function getActualjqGridMultiSelectIds(jqgridId){
		return $("#"+jqgridId).jqGrid("getGridParam", "selarrrow");
	}
	
	function _existedInArray(array,value){
		if (array==null || typeof(array)=="undefined") return false;
		for (var index=0;index<array.length;index++){
			var existedValue=array[index];
			if (existedValue==value) return true;
		}
		return false;
	}

	