/**
 * 根据户号或身份证号异步查询常住人员信息
 */
(function($) {
	$.fn.extend( {
		inhabitantAutocomplete : function(option) {
			var defaultPostData = {
				rows : 10
			}; 
			$.extend(defaultPostData,option.postData);
			
			function getPostData(value){
				return $.extend({"idCardNo" : value},defaultPostData);
			};
			var fillIndex=1;
			
			if(option&&option.fillIndex)
				fillIndex=option.fillIndex;
			
			var defaultOption={
				delay : 500,
				minLength : 1,
				source : function(request, response) {
					$.ajax({
						url : PATH
								+ "/baseinfo/searchInhabitantAutocomplete/searchInhabitantAutocomplete.action",
						data : getPostData(request.term.toLowerCase()) ,
						success : function(_data) {
							response($.map(_data, function(rowData) {
								return {
									yhh : rowData[0],
									idCardNo : rowData[1],
									headName : rowData[2],
									houseAddress : rowData[3],
									name : rowData[6],
									gender : rowData[7],
									label : rowData[0] + " ," + rowData[1]
											+ " ," + rowData[2]+ " ," + rowData[3]+" ,"+rowData[6]+" ,"+rowData[8],
									value : rowData[fillIndex]
								};
							}));
						},
						error : function() {
							$.messageBox({message:"数据提交出错！"});
						}
					});
				}
			};
			$.extend(defaultOption,option);
			$(this).autocomplete(defaultOption);
		},
		//孕妇
		pregnantPersonAutocomplete : function(option) {
			var defaultPostData = {
				rows : 10,
				gender:0
			}; 
			$.extend(defaultPostData,option.postData);
			
			function getPostData(value){
				return $.extend({"idCardNo" : value},defaultPostData);
			};
			
			var fillIndex=1;
			
			if(option&&option.fillIndex)
				fillIndex=option.fillIndex;
			
			var defaultOption={
				delay : 500,
				minLength : 1,
				source : function(request, response) {
					$.ajax({
						url : PATH
								+ "/baseinfo/searchInhabitantAutocomplete/searchPregnantPersonAutocomplete.action",
						data : getPostData(request.term.toLowerCase()) ,
						success : function(_data) {
							response($.map(_data, function(rowData) {
								return {
									yhh : rowData[0],
									idCardNo : rowData[1],
									headName : rowData[2],
									houseAddress : rowData[3],
									name : rowData[6],
									gender : rowData[7],
									label : rowData[0] + " ," + rowData[1]
											+ " ," + rowData[2]+ " ," + rowData[3]+" ,"+rowData[6]+" ,"+rowData[8],
									value : rowData[fillIndex]
								};
							}));
						},
						error : function() {
							$.messageBox({message:"数据提交出错！"});
						}
					});
				}
			};
			$.extend(defaultOption,option);
			$(this).autocomplete(defaultOption);
		},
		pregnantPersonManAutocomplete : function(option) {
			var defaultPostData = {
				rows : 10,
				gender:1
			}; 
			$.extend(defaultPostData,option.postData);
			
			function getPostData(value){
				return $.extend({"idCardNo" : value},defaultPostData);
			};
			
			var fillIndex=1;
			
			if(option&&option.fillIndex)
				fillIndex=option.fillIndex;
			
			var defaultOption={
				delay : 500,
				minLength : 1,
				source : function(request, response) {
					$.ajax({
						url : PATH
								+ "/baseinfo/searchInhabitantAutocomplete/searchPregnantPersonAutocomplete.action",
						data : getPostData(request.term.toLowerCase()) ,
						success : function(_data) {
							response($.map(_data, function(rowData) {
								return {
									yhh : rowData[0],
									idCardNo : rowData[1],
									headName : rowData[2],
									houseAddress : rowData[3],
									name : rowData[6],
									gender : rowData[7],
									label : rowData[0] + " ," + rowData[1]
											+ " ," + rowData[2]+ " ," + rowData[3]+" ,"+rowData[6]+" ,"+rowData[8],
									value : rowData[fillIndex]
								};
							}));
						},
						error : function() {
							$.messageBox({message:"数据提交出错！"});
						}
					});
				}
			};
			$.extend(defaultOption,option);
			$(this).autocomplete(defaultOption);
		},
		
		//李锋添加关于填写义工编号，自动补充义工姓名、性别、编号。
		volunteerAutocomplete : function(option) {
			var defaultPostData = {
				rows : 10
			}; 
			$.extend(defaultPostData,option.postData);
			
			function getPostData(value){
				return $.extend({"volunteerNumber" : value},defaultPostData);
			};
			
			var fillIndex=0;
			
			if(option&&option.fillIndex)
				fillIndex=option.fillIndex;
			
			var defaultOption={
				delay : 500,
				minLength : 1,
				source : function(request, response) {
					$.ajax({
						url : PATH
								+ "/issue/volunteerService/searchVolunteerAutocomplete.action?ownerOrg.id="+getCurrentOrgId(),
						data : getPostData(request.term.toLowerCase()) ,
						success : function(_data) {
							response($.map(_data, function(rowData) {
								return {
									volunteerNumber : rowData[0],
									id : rowData[1],
									vname : rowData[2],
									vsex : rowData[3],
									label : rowData[0] + " ," 
											+ " ," + rowData[1]+ " ," + rowData[2]+ " ," + rowData[4],
									value : rowData[fillIndex]
								};
							}));
						},
						error : function() {
						}
					});
				}
			};
			$.extend(defaultOption,option);
			$(this).autocomplete(defaultOption);
		},
	
	familyAutocomplete : function(option) {
			var defaultPostData = {
				rows : 10
			}; 
			$.extend(defaultPostData,option.postData);
			
			function getPostData(value){
				return $.extend({"yhh" : value},defaultPostData);
			};
			
			var fillIndex=0;
			
			if(option&&option.fillIndex)
				fillIndex=option.fillIndex;
			
			var defaultOption={
				delay : 500,
				minLength : 1,
				source : function(request, response) {
					$.ajax({
						url : PATH
								+ "/baseinfo/searchInhabitantAutocomplete/searchFamilyAutocomplete.action",
						data : getPostData(request.term.toLowerCase()) ,
						success : function(_data) {
							response($.map(_data, function(rowData) {
								return {
									yhh : rowData[0],
									headIdCardNo : rowData[5],
									headName : rowData[2],
									houseAddress : rowData[3],
									telephone : rowData[4],
									label : rowData[0] + " ," + rowData[5]
											+ " ," + rowData[2]+ " ," + rowData[3]+" ," + rowData[4],
									value : rowData[fillIndex]
								};
							}));
						},
						error : function() {
						}
					});
				}
			};
			$.extend(defaultOption,option);
			$(this).autocomplete(defaultOption);
		},
		
		searchInhabitantAutocomplete : function(option) {
			var defaultPostData = {
				rows : 10
			}; 
			$.extend(defaultPostData,option.postData);
			
			function getPostData(value){
				return $.extend({"idCardNo" : value},defaultPostData);
			};
			
			var fillIndex=2;
			
			if(option&&option.fillIndex)
				fillIndex=option.fillIndex;
			
			var defaultOption={
				delay : 500,
				minLength : 15,
				source : function(request, response) {
					$.ajax({
						url : PATH
								+ "/baseinfo/searchInhabitantAutocomplete/searchInhabitantByIdCardNo.action",
						data : getPostData(request.term.toLowerCase()) ,
						success : function(_data) {
							response($.map(_data, function(rowData) {
								return {
									id : rowData[0],
									name : rowData[1],
									idCardNo : rowData[2],
									label : rowData[1] + " ," + rowData[2],
									value : rowData[fillIndex]
								};
							}));
						},
						error : function() {
						}
					});
				}
			};
			$.extend(defaultOption,option);
			$(this).autocomplete(defaultOption);
		}
	});
})(jQuery)