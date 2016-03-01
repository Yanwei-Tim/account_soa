<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div id="dialog-form" title="修改个人信息" class="container container_24" style="overflow:hidden;background:#fff;">
    <div class="grid_5 heightAuto uploadImageBox">
   		<div class="imgBox">
	   		<s:if test="null!=@com.tianque.core.util.ThreadVariable@getUser().getHeaderUrl() && ''!=@com.tianque.core.util.ThreadVariable@getUser().getHeaderUrl()">
	   			<img id="imgUrl" src="${user.headerUrl}"></img>
	   		</s:if>
	   		<s:else>
	   			<img id="imgUrl" src="/resource/system/images/avatar.jpg"></img>
	   		</s:else>
	   			<div class="shadow" id="deleteHeaderImageBox" style="display: none">
					<div class="bgc"></div>
					<div  id="deleteHeaderImage" class="deleteHeaderImage">删除头像</div>
				</div>
   		</div>
	    <form id="maintainUrlForm" method="post" action="${path}/imageUpload/uploadImg.action" enctype="multipart/form-data" name="maintainUrlForm">
	   		<div class="uploadfileBtn"><span>上传照片</span><input id="uploadImg" type="file" name="header" /></div>
	    </form>
    </div>

    <form name="loginform"  method="post" action="${path }/sysadmin/userManage/updateDetails.action" id="updateDetailsForm">
    	<div class="grid_19 heightAuto rightPanelBox">
    		<input type="hidden" value="${user.id}" name="user.id"/>
    		<input id="headerUrl" type="hidden" value="${user.headerUrl}" name="user.headerUrl"/>
	        <div class="grid_4 lable-right">
	        <em class="form-req">*</em>
	            <label class="form-label">用户名：</label>
	        </div>
	        <div class="grid_6">
	               <input readonly type="text" class="form-txt" name="user.userName" id="userName" value="${ user.userName}"/>
	        </div>
	        <div class="grid_1">
		  		
		  	</div>

	        <div class="grid_4 lable-right">
	        <em class="form-req">*</em>
	        	<label class="form-label">姓  名：</label>
	        </div>
	        <div class="grid_6">
	            <input id="name" type="text" class="form-txt" maxlength="15" name="user.name" value="${user.name}"/>
	        </div>
	        <div class="grid_1">
		  		
		  	</div>

	        <div class="grid_4 lable-right">
	        <em class="form-req">*</em>
	            <label class="form-label">手机：</label>
	        </div>
	        <div class="grid_6">
	            <input type="text" class="form-txt" maxlength="11" name="user.mobile" id="mobile" value="${user.mobile}"/>
	        </div>

	        <div class="grid_5 lable-right">	        
	        <em class="form-req">*</em>	        
	        	<label class="form-label">工作电话：</label>	        
	        </div>
	        
	        <div class="grid_6">
	            <input type="text" class="form-txt" name="user.workPhone" maxlength="13" id="workPhone" value="${user.workPhone}"/>
	        </div>
	        <div class="grid_1">
		  		
		  	</div>

	        <div class="grid_4 lable-right">
	            <label class="form-label">家庭电话：</label>
	        </div>
	        <div class="grid_6">
	            <input type="text" class="form-txt" name="user.homePhone" maxlength="13" id="homePhone" value="${user.homePhone}"/>
	        </div>
	        <div class="grid_1">
		  		&nbsp;
		  	</div>
	        <div class="grid_4 lable-right">
	            <label class="form-label" style="width:76.5px;">工作单位：</label>
	        </div>
	        <div class="grid_6">
	            <input type="text" class="form-txt" name="user.workCompany" maxlength="30" id="workCompany" value="${user.workCompany}"/>
	        </div>
    	</div>
    	<div class="grid_24">
				<div style="padding-left:8px;"><label class="form-label" style="text-align:right;">是否显示字段解释信息：</label><input type="checkbox" id="inputTip" /></div>
		</div>
    </form>
</div>
<div id="scanHeaderImage"></div>
<script type="text/javascript">
    $(document).ready(function(){
    	if($.cookie("inputtip")==null || $.cookie("inputtip")=="true"){
			$.cookie("inputtip",true, { path: '/', expires: 10 });
			$("#inputTip").attr("checked","true");
		}
    	else{
    		$.cookie("inputtip",false, { path: '/', expires: 10 });
    		$("#inputTip").removeAttr("checked");
        };
    	$("#inputTip").click(function(){
			if($(this).attr("checked")){
				$.cookie("inputtip",true, { path: '/', expires: 10 });
			}
			else{
				$.cookie("inputtip",false, { path: '/', expires: 10 });
			}
		})
		if($("#headerUrl").val()=='' || $("#headerUrl").val()==null){
    		$("#deleteHeaderImageBox").hide();
    	}else{
    		$("#deleteHeaderImageBox").show();
    	}
        $("#updateDetailsForm").formValidate({
            promptPosition :"bottomLeft",
            submitHandler: function(form) {
                $(form).ajaxSubmit({
                    success:function(data){
                        if(data==true){
                        	if($("#headerUrl").val()=='' || $("#headerUrl").val()==null){
                        		$(".pic img").attr("src","/resource/system/images/admin.png");
                        	}else{
                        		$(".pic img").attr("src",$("#headerUrl").val()+"?"+Math.random());
                        	}
                        	$("#personelInfo").html("欢迎您！"+$("#name").val());
                        	$("#settingDialog").dialog("close");
                        	//showPageByTopMenu("index");
                        }else{
                            var inputObject=$("input[name='currentPassword']");
                            inputObject.dialogtip({
                                content: data,alignX: 'right',alignY: 'center'
                            });
                            inputObject.poshytip('show');
                        }
                    }
                });
                return false;
            },
            rules: {
                "user.userName": {
                    required: true
                },
                "user.name" :{
                    required: true
                },
                "user.mobile" :{
                    required: true,
                    mobile:true
                },
                "user.workPhone":{
                	required: true,
                    telephone:true
                },
                "user.homePhone":{
                	telephone:true
                },
                "user.workCompany":{
                	maxlength:30
                }
            },
            messages: {
                "user.userName": {
                    required: "请输入用户名！"
                },
                "user.name" :{
                    required: "请输入姓名！"
                },
                "user.mobile" :{
                    required: "请输入手机！",
                    mobile:"请输入11位以1开头的手机！"
                },
                "user.workPhone":{
                	required: "请输入工作电话！",
                    telephone:"请输入以数字和“-”组成的电话！"
                },
                "user.homePhone":{
                    telephone:"请输入以数字和“-”组成的电话！"
                },
                "user.workCompany":{
                	maxlength:$.format("工作单位最多输入30个字符")
                }
            }
        });

      $("#maintainUrlForm").formValidate({
		 promptPosition: "bottomLeft",
		 submitHandler: function(form) {
	         $(form).ajaxSubmit({
	             success: function(data){
		             if(data == null){
		            	 $.messageBox({message:"上传失败，请上传正确格式的图像",level: "error"});
		            	 return;
			         }
			         if(data == "false"){
			        	 $.messageBox({message:"上传文件大于1M上传失败！",level: "error"});
		            	 return;
				     }
					$("#scanHeaderImage").createDialog({
						width: 600,
						height: 600,
						title:"头像截图",
						url:'${path}/scanUserHeaderImg.jsp?imgUrl='+data,
						close:function(){
					        $("#uploadImg").val("");
						},
						buttons: {
							"保存" : function(event){
						      $("#scanHeaderImageForm").submit();
						   },
						   "关闭" : function(){
						        $(this).dialog("close");
						   }
						}
					});
	         	}
	         })
		}
	});
    	$("#uploadImg").change(function(){
    		$("#maintainUrlForm").submit();
    	});
    	$("#deleteHeaderImage").click(function(){
    		$("#imgUrl").attr("src","/resource/system/images/avatar.jpg");
    		$("#headerUrl").val("");
    		$("#deleteHeaderImageBox").hide();
    	});

    });
</script>