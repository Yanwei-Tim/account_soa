(function($) {
	$.fn.extend( {

		houseAutoComplete : function(option) {
			var self = $(this);
			var defaultPostData = {
//				address:'',
//				community:'',
//				block:'',
//				unit:'',
//				searchType : function(){return defaultOption.searchType},
//				orgId :function(){return getCurrentOrgId();},
				rows : 10
			};
			$.extend(defaultPostData,option.postData);

			function getPostData(value){
				return $.extend({"searchCondition" : value,
								 "searchByAddress":defaultOption.searchByAddress,
								 "searchType":defaultOption.searchLevel,
								 "orgId":getCurrentOrgId()},defaultPostData);
			};

			function getDisplayText(value){
				if (value==""){
					return "...";
				}else{
					return value;
				}
			};

			function getDisplayLabel(rowData){
				if (defaultOption.searchByAddress){
					return rowData.address;
				} else if("houseCode"==defaultOption.searchLevel) {
					if(null == rowData.comnunity || rowData.comnunity == "") {
						return "编号："+getDisplayText(rowData.houseCode)+"，"+rowData.address;
					} else {
						return "编号："+getDisplayText(rowData.houseCode)+"，"+getDisplayText(rowData.comnunity)+"小区"+getDisplayText(rowData.block)+"幢"+getDisplayText(rowData.unit)+"单元"+getDisplayText(rowData.room);
					}
				} else{
					return getDisplayText(rowData.houseCode)+"，"+getDisplayText(rowData.comnunity)+"小区"+getDisplayText(rowData.block)+"幢"+getDisplayText(rowData.unit)+"单元"+getDisplayText(rowData.room);
				}
			};

			function getDisplayValue(rowData){
				if (defaultOption.searchByAddress){
					return rowData.address;
				}else if ("community"==defaultOption.searchLevel){
					return rowData.comnunity;
				}else if ("block"==defaultOption.searchLevel){
					return rowData.block;
				}else if ("unit"==defaultOption.searchLevel){
					return rowData.unit;
				}else if ("room"==defaultOption.searchLevel){
					return rowData.room;
				}else if ("houseCode"==defaultOption.searchLevel){
					return rowData.houseCode;
				}else{
					return rowData.houseId;
				}
			};

			var defaultOption={
				delay : 500,
				minLength : 0,
				searchByAddress:false,
				searchLevel:'',
				close:function(){
					//self.removeClass("errorInput").poshytip('disable');
				},
				source : function(request, response) {
					if(request.term==''){
						request.term=null;
//						response();
					}
					$.ajax({
						url : PATH + "/baseinfo/houseAutoComplete/findHousesByAddressLib.action",
						data : getPostData(request.term) ,
						success : function(_data) {
							self.data("data",_data);
							response($.map(_data, function(rowData) {
								return {
									houseId   : rowData.houseId,
									community : rowData.comnunity,
									block     : rowData.block,
									unit      : rowData.unit,
									room      : rowData.room,
									address   : rowData.address,
									houseCode : rowData.houseCode,
									addressType : rowData.addressType,
									label     : function(){return getDisplayLabel(rowData);},
									value     : function(){return getDisplayValue(rowData);}
								};
							}));
						},
						error : function() {
							$.messageBox( {
								message : "搜索失败，请重新登入！",
								level : "error"
							});
						}
					});
				}
			};
			$.extend(defaultOption,option);
			defaultOption.select=function(event,ui){
				if (option.select!=null){
					option.select(event,ui);
				}
			};
			$(this).autocomplete(defaultOption);
		}
	});

})(jQuery)
