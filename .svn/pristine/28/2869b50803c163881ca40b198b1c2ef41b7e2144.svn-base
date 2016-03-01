//标题（中英文、数字、引号、括号、空格、书名号、减号、下划线）
jQuery.validator.addMethod("titleStr", function(value, element){
	if(value==null||value==undefined||value=="" ){return true};
	var patrn=/^(?:[\u4e00-\u9fa5]*\w*-*_*\s*）*\"*\'*<*>*《*》*（*\(*\)*)+$/;  
	if (!patrn.exec(value.replace(/[ ]/g,""))) return false  
	return true  
});
//地址（中英文、数字、括号、空格、减号、#号）
jQuery.validator.addMethod("addressStr", function(value, element){
	if(value==null||value==undefined||value=="" ){return true};
	var patrn=/^(?:[\u4e00-\u9fa5]*\w*-*\s*）*#*（*\(*\)*)+$/;  
	if (!patrn.exec(value.replace(/[ ]/g,""))) return false  
	return true  
});
//多姓名（中英文、数字、括号、空格、减号、#号）
jQuery.validator.addMethod("multiNames", function(value, element){
	if(value==null||value==undefined||value=="" ){return true};
	var patrn=/^(?:[\u4e00-\u9fa5]*\w*,*\s*，*、*)+$/;  
	if (!patrn.exec(value.replace(/[ ]/g,""))) return false  
	return true  
});
//数字、字母
jQuery.validator.addMethod("isDigitAndStr", function(value, element){
	if(value==null||value==undefined||value=="" ){return true};
	var patrn=/^[A-Za-z0-9]+$/;
	if (!patrn.exec(value.replace(/[ ]/g,""))) return false
	return true
});
//数字、字母、下划线
jQuery.validator.addMethod("isDigitStrAndUnderline", function(value, element){
	if(value==null||value==undefined||value=="" ){return true};
	var patrn=/^[A-Za-z0-9_]+$/;
	if (!patrn.exec(value.replace(/[ ]/g,""))) return false
	return true
});
//数字、字母、短划线
jQuery.validator.addMethod("isCodeValidate", function(value, element){
	if(value==null||value==undefined||value=="" ){return true};
	var patrn=/^[A-Za-z0-9//-]+$/;
	if (!patrn.exec(value.replace(/[ ]/g,""))) return false
	return true
});
//不包含特殊字符
jQuery.validator.addMethod("exculdeParticalChar", function(value, element){
	if(value==null||value==undefined||value=="" ){return true};
	var patrn=/^(?:[A-Za-z0-9\u4E00-\u9FA5]*[\(|\（]*[\)|\）]*-*)+$/;
	if (!patrn.exec(value.replace(/[ ]/g,""))) return false
	return true
});
//不包含有冒号的特殊字符
jQuery.validator.addMethod("exculdePartical", function(value, element){
	if(value==null||value==undefined||value=="" ){return true};
	var patrn=/^(?:[A-Za-z0-9\u4E00-\u9FA5]*[\(|\（\:\]*[\：)|\）]*-*)+$/;
	if (!patrn.exec(value.replace(/[ ]/g,""))) return false
	return true
});
//电话号码
jQuery.validator.addMethod("telephone", function(value, element){
	if(value==null||value==undefined||value=="" ){return true};
	var patrn=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
	if (!patrn.exec(value.replace(/[ ]/g,""))) return false
	return true
});
//手机
jQuery.validator.addMethod("mobile", function(value, element) {
	var length = value.length;
	var mobile = /^((1[0-9]{2})+\d{8})$/;
	return this.optional(element) || (length == 11 && mobile.test(value));
});

//身高
jQuery.validator.addMethod("stature", function(value, element) {
	var stature = /^[0-9]*[1-9][0-9]*$/;
	return this.optional(element) || (value <= 300 && stature.test(value));
});

//正整数
jQuery.validator.addMethod("positiveInteger", function(value, element) {
	var positiveInteger = /^[0-9]*[1-9][0-9]*$/;
	return this.optional(element) || (positiveInteger.test(value));
});

//一位到两位小数的正实数
jQuery.validator.addMethod("posNumWiPot", function(value, element) {
	var positiveInteger = /^[0-9]+(.[0-9]{0,2})?$/;
	return this.optional(element) || (positiveInteger.test(value));
});



//非负整数
jQuery.validator.addMethod("nonNegativeInteger", function(value, element) {
	var positiveInteger = /^\d+$/;
	return this.optional(element) || (positiveInteger.test(value));
});

//电子邮箱
jQuery.validator.addMethod("email", function(value, element) {
	if(value==null||value==undefined||value=="" ){return true};
	var email = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if (!email.exec(value)) return false  ;
	return true;
});

//不合法字符集
jQuery.validator.addMethod("IllegalStr", function(value, element) {
	var patrn=/^(?:[\u4e00-\u9fa5]*\w*\s*)+$/;
	if (!patrn.exec(value)) return false
	return true
});
//身份证号码
jQuery.validator.addMethod("idCard", function(value, element){
	 return checkIdcard(value.toUpperCase(),element);
});

//生日
jQuery.validator.addMethod("birthDay", function(value, element){
	if(value==null||value==undefined||value=="" ){return true};
	var d=new Date();
	var dat=d.getYear() + "-"+(d.getMonth() + 1) + "-"+d.getDate() + " ";//当前日期
   return new Date(Date.parse(dat.replace("-", "/"))) > new Date(Date.parse(value.replace("-", "/")));

});
//excel
jQuery.validator.addMethod("isExcel", function(fileName, element){
	var suffix = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
	if(suffix!="xls"){
		return false;
	}
	return true ;
});
//不等于
jQuery.validator.addMethod("notEqualTo", function(value,element,param){
	var target=$(param).unbind(".validate-equalTo").bind("blur.validate-equalTo",function(){$(element).valid();});
	return value!=target.val();
});
//验证网址
jQuery.validator.addMethod("isUrl", function(value, element) {
	var patrn=/[a-zA-z]+:\/\/[^\s]+/;
	if (!patrn.exec(value)) return false;
	return true
});
//验证日期
jQuery.validator.addMethod("isDate", function(value, element) {
	var patrn=/^\d{4}(\-|\/|.)\d{1,2}\1\d{1,2}$/;
	if (!patrn.exec(value)) return false;
	return true
});
//仅中文
jQuery.validator.addMethod("isChinese", function(value, element) {
	var patrn=/^[\u4e00-\u9fa5]+$/;
	if (!patrn.exec(value)) return false;
	return true
});

;(function ($) {
	$.fn.formValidate = function(o){
		var dfop = {
			focusInvalid : false,
			showErrors:function(errorMap, errorList){
				for(var i=0;i<this.successList.length;i++){
					var inputDoc=document.getElementsByName(this.successList[i].name)[0];
					$(inputDoc).removeClass("errorInput");
					$(inputDoc).poshytip('disable');
					$(inputDoc).attr("createMsg","false");
				}
				for ( var name in errorMap ) {
					var inputDoc=document.getElementsByName(name)[0];
					var inputObject=$(inputDoc);
					if(inputObject.css("display")=="none" || inputObject.attr("type")=="hidden"){
						if(inputObject.next().css("display")!="none"){
							inputObject = inputObject.next();
						}else{
							inputObject = inputObject.parent();
						}
					}
					else if (inputObject.attr("type") == "radio" || inputObject.attr("type") == "checkbox") {
						inputObject = inputObject.parent();
					}
					inputObject.addClass("errorInput");
					$(inputDoc).poshytip('disable');
					var option={
						alignX:'center',
						alignY:'bottom',
						showOn: 'focus',
						content:errorMap[name]
					}
					if(inputObject[0].tagName==='SELECT' || inputObject.hasClass("x-form-text")){
						option.alignX='right';
						option.alignY='center';
					}
					inputObject.dialogtip(option);
					$(".tip-error").bgiframe();
					inputObject.attr("createMsg","true");
				}
			},
			ignore:""
		}
		$.extend(dfop, o);
		return $(this).validate(dfop);
	};
})(jQuery);
function showErrorsForTab(errorMap, errorList){
	for(var i=0;i<this.successList.length;i++){
		var inputDoc=document.getElementsByName(this.successList[i].name)[0];
		$(inputDoc).removeClass("errorInput").poshytip('disable');
		$(inputDoc).attr("createMsg","false");
	}
	var i = 0;
	for(var name in errorMap){
		var inputDoc=document.getElementsByName(name)[0];
		var inputObject=$(inputDoc);
		if(inputObject.css("display")=="none" || inputObject.attr("type")=="hidden"){
			if(inputObject.next().css("display")!="none"){
				inputObject = inputObject.next();
			}else{
				inputObject = inputObject.parent();
			}
		}
		if(inputObject.attr("createMsg")==undefined||inputObject.attr("createMsg")=="false"){
			var option={
				alignX:'center',
				alignY:'bottom',
				showOn: 'focus',
				content:errorMap[name]
			}
			if(inputObject[0].tagName==='SELECT'){
				option.alignX='right';
				option.alignY='center';
			}
			inputObject.dialogtip(option);
			$(".tip-error").bgiframe();
			$(".tip-error").css("cursor","pointer");
			$(".tip-error").click(function(){
				var inputObj = $(document.getElementsByName($(this).find(".inputName").attr("inputName"))[0]);

				if(inputObj.css("display")=="none"){
					inputObj = inputObj.next();
				}

				inputObj.attr("createMsg","false");
				$(this).remove();
			});
			inputObject.attr("createMsg","true");
		}
		var containerId;
		if(i==0){
			containerId = inputObject.parents(".container:first").attr("id");
			var currentTab = $("a[href='#"+containerId+"']");
			currentTab.click();
		}
		if(inputObject.parents(".container:first").attr("id") == containerId){

			if(inputObject.css("display")=="none"){
				inputObject = inputObject.next();
			}

			inputObject.addClass("errorInput");
		}
		i++;
	}
}
