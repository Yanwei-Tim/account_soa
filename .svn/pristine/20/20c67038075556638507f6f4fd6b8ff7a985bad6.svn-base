/**
 * 组织机构人员自动填充
 */
(function($) {
	$.fn.extend( {
		searchPrimaryOrganizationPersonAutoComplete:function(option){
			var defaultPostData ={
				rows : 10
			};
			
			$.extend(defaultPostData,option.postData);
			
			function getPostData(value){
				return $.extend({"name" : value},defaultPostData);
			};
			
			var fillIndex=0;
			if(option&&option.fillIndex){
				fillIndex=option.fillIndex;
			}
			var defaultOption={
				delay : 500,
				minLength : 0,
				source : function(request, response) {
					$.ajax({
						url : PATH
								+ "/baseinfo/primaryOrganizationManage/searchPrimaryOrganizationPersonnelForAutoComplete.action?organizationId="+$("#organizationId").val(),
						data : getPostData(request.term.toLowerCase()) ,
						success : function(_data) {
							response($.map(_data, function(rowData) {
								return {
									name : rowData[0],
									birthday : rowData[1],
									gender : rowData[2],
									position : rowData[3],
									telephone : rowData[4],
									moblie : rowData[5],
									remark : rowData[6],
									label :  rowData[0] + " ," + rowData[1]+ " ," 
										+ rowData[7]+ " ," + rowData[3],
									value : rowData[fillIndex]
								};
							}));
						},
						error : function() {
							$.messageBox({message:"数据提交出错！"});
						}
					});
				},
				open:function(){
					$(".ui-autocomplete").css("z-index","100000");
				}
			};
			$.extend(defaultOption,option);
			$(this).autocomplete(defaultOption);
		}
	});
})(jQuery)