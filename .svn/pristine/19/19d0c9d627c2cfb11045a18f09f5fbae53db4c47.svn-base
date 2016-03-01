(function($) {
	$.fn.extend( {
		userAutocomplete : function(option) {
			var defaultPostData = {
				searchChildOrg : false,
				rows : 10
			}; 
			$.extend(defaultPostData,option.postData);
			
			function getPostData(value){
				return $.extend({"user.name" : value},defaultPostData);
			};
			
			var fillIndex=2;
			
			if(option&&option.fillIndex)
				fillIndex=option.fillIndex;
			
			var defaultOption={
				delay : 500,
				minLength : 0,
				source : function(request, response) {
					if(request.term==''){
						request.term=null;
					}
					$.ajax({
						url : PATH
								+ "/sysadmin/userManage/findUserForAutocomplete.action",
						data : getPostData(request.term) ,
						success : function(_data) {
							response($.map(_data, function(rowData) {
								return {
									id : rowData[0],
									orgId : rowData[4],
									userName : rowData[1],
									label : rowData[1] + " ," + rowData[2]
											+ " ," + rowData[3],
									value : rowData[fillIndex]
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