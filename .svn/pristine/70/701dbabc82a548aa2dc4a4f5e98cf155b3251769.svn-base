
<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />

<script type="text/javascript">
$(document).ready(function(){
<@s.if test='!"view".equals(mode)'>
	jQuery.validator.addMethod("roleNameInLevel", function(value, element){
		if(!$('#roleName').val()){
           return false;
		}else return true;
	});
	var validator = $("#maintainForm").formValidate({
		promptPosition: "topLeft",
		submitHandler: function(form) {
	         $(form).ajaxSubmit({
	             success: function(data){
	                     if(!data.id){
	                    	 $.messageBox({
								message:data,
								level: "error"
							 });
	                     	return;
	                     }
	        	   		 <@s.if test='"add".equals(mode) || "copy".equals(mode) '>
					    	$("#roleList").addRowData(data.id,data,"first");
					    	$.messageBox({message:"创建岗位成功!"});
					    	$("#maintainForm").resetForm();
				     		$("#roleDialog").dialog("close");
					     </@s.if>
					     <@s.if test='"edit".equals(mode)'>
					        $("#roleList").setRowData(data.id,data);
					        ///$("#roleList").trigger("reloadGrid");
						    $.messageBox({message:"修改岗位成功!"});
						    //reSetPatelConfig($("#roleid").val());
					     	$("#roleDialog").dialog("close");
					     </@s.if>
					     $("#roleList").setSelection(data.id);
	      	   },
	      	   error: function(XMLHttpRequest, textStatus, errorThrown){
	      	      alert("提交错误");
	      	   }
	      	  });
		},
		rules: {
			"role.roleName" :{
			   required: true,
			   exculdeParticalChar:true,
			   remote: {
	               data: {
	                    "role.roleName": function(){
	                        return $('#roleName').val();
	                    },
	                    "mode" : function(){
	                        return $('#mode').val();
	                    },
	                    "role.id" : function(){
	                        return $('#roleid').val();
	                    },
	                    "role.useInLevel.id":function(){
	                         return $('#useInl').val();
	                    }
	                },
	        	   url: "${path}/sysadmin/roleManage/validateRoleName.action",
	        	   type: "get"
	        }
			},
			"role.description":{
				maxlength:100
			},
			"role.useInLevel.id": {
				required: true
			},
			"role.workBenchType.id": {
				required: true
			}
		},
		messages: {
			"role.roleName" :{
			   required:  "岗位名称不能为空!",
			   roleNameInLevel : "岗位名称不能为空!",
			   exculdeParticalChar:"岗位名称只能输入字母，数字和中文字符",
			   remote     : "岗位名已经存在"
			},
			"role.description":{
				maxlength:$.format("备注最多输入{0}个字符")
			},
			"role.useInLevel.id": {
				required   : "请选择应用级别!"
			},
			"role.workBenchType.id": {
				required   : "请选择工作台类型!"
			}
		}
	});

</@s.if>

  	$("#permissionTree").jqGrid({
	   	url: "${path}/sysadmin/roleManage/preparePermissionTree.action?role.id=" + $("#roleid").val(),
	    treeGrid: true,
		treeGridModel : 'adjacency',
		hidegrid: false,
		datatype: "json",
		height:220,
		mtype: "POST",
		editable: false,
		caption:"权限列表",
		width: 520,
	   	colModel:[
	   		{name:"permission.ename", width:1,hidden:true,key:true,sortable:false},
	   		{name:"permission.cname",label:"模块",width:180,sortable:false},
	   		{name:"",label:"",width:20,sortable:false,formatter:choosePermissionModule},
	   		{name:"check",label:"权限",sortable:false,width: 310,editable:false,formatter:choosePermission}
	   	],
	   	rowNum:-1,
	   	treeReader : {
	   	   	level_field: "level",
			parent_id_field: "parentEname",
			leaf_field: "leaf",
			expanded_field: "expanded"
	   	},
	   	jsonReader: {
			repeatitems : false,
			id: "0"
		},
		loadComplete:function(){
			var treeList=$("#permissionTree").find(".tree-wrap-ltr");
			for(var i=0;i<treeList.length;i++){
				$(treeList[i]).prependTo($(treeList[i]).parent().parent().children("td")[1]);
			}
		}
   	});
});
function selectButton(doom){
	var selectStr = ($(doom).attr("name"));
	var parentsId = ($(doom).parents("tr:first").attr("id"));
	if(selectStr.indexOf(parentsId)==-1){
		if($(doom).attr("checked")){
            var rowid = $(doom).parents("tr:first").attr("id");
            var rc = $("#permissionTree").getInd(rowid,true);
        	$("input[name=\"permissionsMap\['"+ rc.id + "'\]\"]").attr("checked",true);
            checkParentNodes(rc);
		}else{
			$(doom).attr("checked",false);
			var buttonName = $(doom).attr("name");
		}
	}else{
		clickRecord(doom);
	}
};
function clickRecord(doom){
	var rowid = ($(doom).parents("tr:first").attr("id"));
	if($(doom).attr("checked")){
	    var rc = $("#permissionTree").getInd(rowid,true);
		checkParentNodes(rc);
	}else{
		var record = $("#permissionTree").getInd(rowid,true);
	    uncheckChildNodes(record);
	}
};

function checkParentNode(prevDom,i){
	if($(".tree-minus",prevDom).hasClass("tree-minus") && $("#permissionTree").getInd(prevDom.attr("id"),true).level==i){
		$("input[name=\"permissionsMap\['"+ prevDom.attr("id") + "'\]\"]").attr("checked",true);
    }else{
    	checkParentNode(prevDom.prev("tr"),i);
    }
}
function checkParentNodes(rc){
	if(rc.level==1){
       return;
    }
    for(var i=rc.level-1;i>=1;i--){
        var prevDom = $("#"+rc.id).prev("tr");
        checkParentNode(prevDom,i);
    }
};

function uncheckParentNode(nextDom,level){
	if($("#permissionTree").getInd(nextDom.attr("id"),true).level>level){
		$("input[name=\"permissionsMap\['"+ nextDom.attr("id") + "'\]\"]").attr("checked",false);
    	uncheckParentNode(nextDom.next("tr"),level);
    }else if ($("#permissionTree").getInd(nextDom.attr("id"),true).level<=level){
        return;
    }
}

function uncheckChildNodes(record){
   	if(record.level==3){
		$("input[type='checkbox']","#"+record.id).attr("checked",false);
		return ;
   	}
   	var nextDom = $("#"+record.id).next("tr");
   	uncheckParentNode(nextDom,record.level);
};

function choosePermissionModule(el,options,rowData){
	var bchk =  rowData.check ?  " checked='checked' " : "";
	var submitName = " name=\"permissionsMap['"+ rowData.permission.ename + "']\" ";
	if (rowData.permission.permissionType==0) return;
   <@s.if test='!"view".equals(mode)'>
      return "<input type=\"checkbox\" " + submitName + bchk  + " onclick='clickRecord(this)'/>";
   </@s.if>
   <@s.else>
      return "<input type=\"checkbox\" disabled " + submitName + bchk  + " onclick='clickRecord(this)'/>";
   </@s.else>
};
function choosePermission(el,options,rowData){
	if (rowData.leaf){
		var result="";
		var index;
		var moduleName=rowData.permission.ename;
		for (index = 0;index<rowData.children.length;++index){
			var buttonPermission=rowData.children[index];
			var bchk =  buttonPermission.check ?  "checked='checked'" : "";
			var submitName = "name=\"permissionsMap['"+ buttonPermission.permission.ename + "']\" ";
			var submitId = "id=\""+ buttonPermission.permission.ename + "\" ";
			<@s.if test='!"view".equals(mode)'>
		      result=result+"<div style='float:left'>"+buttonPermission.permission.cname+"<input type=\"checkbox\" " + submitName + bchk + " onclick='selectButton(this)'/>"+"</div>";
			</@s.if>
			<@s.else>
		      result=result+"<div style='float:left'>"+buttonPermission.permission.cname+"<input type=\"checkbox\" disabled " + submitName + bchk  + " onclick='selectButton(this)'/>"+"</div>";
		   </@s.else>
			   if((index+1)%3==0){
				   result=result+"<br/>";
			   }
		}
		return result;
	}else{
		return "";
	}
};

</script>
<div id="dialog-form" title="岗位维护" class="container container_24">
<@s.if test='"edit".equals(mode)'>
	<form id="maintainForm"  method="post" action="${path}/sysadmin/roleManage/updateRole.action">
</@s.if>
<@s.if test='"add".equals(mode) || "copy".equals(mode) '>
	<form id="maintainForm"  method="post" action="${path}/sysadmin/roleManage/addRole.action">
</@s.if>
  <input id="mode"	type="hidden" name="mode" value="${mode}" />
  <input id="roleid" type="hidden" name="role.id" value="${role.id}" />
  <input id="createUser" type="hidden" name="role.createUser"	value="${role.createUser}" />
  <input id="createDate" type="hidden" name="role.createDate"
      value='<@s.date name="role.createDate" format="yyyy-MM-dd HH:mm:ss"/>' />

<div class="grid_5 lable-right">
<em class="form-req">*</em>
	<label class="form-lbl">应用层级：</label>
	
</div>
<div class="grid_7">
<@s.if test='"view".equals(mode)  '>
         <select name="role.useInLevel.id" class="form-select" id="useInl"
		    <@s.if test='"view".equals(mode)'>disabled='true'</@s.if>>
		   		<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@ORGANIZATION_LEVEL" defaultValue="${role.useInLevel.id}" />
	</select>
</@s.if>	
			
<@s.if test='"edit".equals(mode) || "add".equals(mode) || "copy".equals(mode)'>
	<select name="role.useInLevel.id" class="form-select"   id="useInl">
		<@s.if test='"add".equals(mode)'>
			<option></option>
		</@s.if>
		<@s.iterator value="orgLevelDict">
			<option value='<@s.property value="id"/>' 
				<@s.if test="role.useInLevel.id == id">
					selected="selected"
				</@s.if>
			> 
				<@s.property value="displayName"/>
			</option>
		</@s.iterator>	
	</select>	
</@s.if>		
</div>

<div class="grid_5 lable-right">
<em class="form-req">*</em>
     <label class="form-lbl">岗位名称：</label>
  </div>
  <div class="grid_7">
    <input type="text" name="role.roleName" id="roleName" maxlength="20"
	  <@s.if test='"view".equals(mode)'>readonly</@s.if> value="${role.roleName}"  class="form-txt" />
  </div>
  
<div class='clearLine'>&nbsp;</div>
<!--  -->
<div class="grid_5 lable-right">
<em class="form-req">*</em>
	<label class="form-lbl">工作台类型：</label>
	
</div>
<div class="grid_7">
     <select name="role.workBenchType.id" class="form-select" id="useInl"
		 <@s.if test='"view".equals(mode)'>disabled='true'</@s.if>>
		 <@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@WORKBENCH_TYPE" defaultValue="${(role.workBenchType.id)!}" />
	</select>
</div>

<div class='clearLine'>&nbsp;</div>
  <div class="grid_5 lable-right">
  	<label class="form-lbl">岗位描述： </label>
  </div>
  <div class="grid_18">
    <textarea name="role.description" id="description"
	  class="form-txt" style="height: 3em;"
	  <@s.if test='"view".equals(mode)'>readonly</@s.if>>${role.description}</textarea>
  </div>
  <div class='clearLine' style="height: 30px">&nbsp;</div>
  <div style="line-height:normal;!important;">
     <table id="permissionTree" style="*border-bottom:1px solid #a6c9e2;line-height:40px;width:500px  height:205px;overflow-x:auto"></table>
  </div>
<@s.if test='!"view".equals(mode)'>

</form>
</@s.if>
</div>