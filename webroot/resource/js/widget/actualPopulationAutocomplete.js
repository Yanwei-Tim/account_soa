(function($) {
	$.fn.extend( {
		actualPopulationAutocomplete : function(option) {
			var self=$(this);
			var defaultPostData = {
				rows : 10
			}; 
			$.extend(defaultPostData,option.postData);
			
			function getPostData(value){
				return $.extend(defaultPostData,{"idCardNo":value});
			};
			
			var defaultOption={
				delay : 500,
				minLength : 0,
				source : function(request, response) {
					if(request.term==''){
						request.term=null;
					}
					$.ajax({
						url : PATH
								+ "/commonPopulation/commonPopulationManage/findActualPopulationsByOrgIdAndIdCardNo.action",
						data : getPostData(request.term) ,
						success : function(_data) {
							self.data("size",_data.length);
							response($.map(_data, function(rowData) {
								return {
									id : rowData["id"],
									orgId : rowData.organization.id,
									name : rowData["name"],
									idCardNo : rowData["idCardNo"],
									label : rowData["idCardNo"] + " ," + rowData["name"],
									value : rowData["idCardNo"]
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
			$(this).autocomplete(defaultOption);
		}
	});
})(jQuery)