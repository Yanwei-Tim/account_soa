<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">

    <div class="ui-corner-all" id="nav">
        <a id="add" href="javascript:void(0)"><span>新增</span></a>
        <a id="update" href="javascript:void(0)"><span>修改</span></a>
        <a id="reload" href="javascript:void(0)"><span>刷新</span></a>
    </div>
    <div style="width:100%">
        <table id="proclamationList"></table>
        <div id="proclamationListPager"></div>
    </div>
    <div id="proclamationDialog" style="overflow: hidden"></div>
</div>
<script type="text/javascript">
    var dialogWidth = 680;
    var dialogHeight = 450;
    $(function(){
        $("#proclamationList").jqGridFunction({
            url:"${path}/sysadmin/proclamationManage/findProclamations.action",
            datatype:"json",
            colModel:[
                      {name:'id',index:'id',hidden:true,sortable:false},
                      {name:'content',label:'内容',sortable:true,width:800},
                      {name:'createDate',label:'创建时间',sortable:true,align:'center'},
                      {name:'display',label:'是否显示',sortable:true,align:'center',formatter:displayFormatter}
            ],
            loadComplete:function(){},
            ondblClickRow:dbClickTable,
            onSelectRow:function(){}
        });

        $("#add").click(function(){
        	<s:if test='!(user.userName).equals("admin")'>
		    	$.messageBox({level:'warn',message:"只有admin用户才能新增系统公告！"});
				return;
			</s:if>
            $("#proclamationDialog").createDialog({
                width:dialogWidth,
                height:dialogHeight,
                title:"新增系统公告",
                url:"${path}/sysadmin/proclamationManage/dispatch.action?mode=add",
                buttons:{
                    "保存":function(event){
                        if(!validateNull()){
                            $.messageBox({message:"请输入内容！"});
                            return false;
                        }
                	    if(!validateLength()){
                            $.messageBox({level:'warn',message:"内容最多允许输入500个字符！"});
                    	    return false;
                	    }
                        if(validateHasDisplay()){
                            $.confirm({
                                title:"确认继续添加",
                                message:"目前仍有一个系统公告在显示，确认新增后将会显示新的公告！",
                                okFunc:function(){
                            	   $("#maintainForm").submit();
                                },
                                cancelFunc:function(){
                                    $("#proclamationDialog").dialog("close");
                                }
                            });
                        }else{
                            $("#maintainForm").submit();
                        }
                    },
                    "关闭":function(){
                        $(this).dialog("close");
                    }
                }
            });
        });

        $("#update").click(function(){
        	<s:if test='!(user.userName).equals("admin")'>
		    	$.messageBox({level:'warn',message:"只有admin用户才能修改系统公告！"});
				return;
			</s:if>
            var selectedId = $("#proclamationList").getGridParam('selrow');
            if(selectedId == null || selectedId == undefined){
            	$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
    			return;
    		}
            var rowData = $("#proclamationList").getRowData(selectedId);
            $("#proclamationDialog").createDialog({
                width:dialogWidth,
                height:dialogHeight,
                title:"修改系统公告",
                url:"${path}/sysadmin/proclamationManage/dispatch.action?mode=edit&proclamationId="+rowData.id,
                buttons:{
                    "保存":function(event){
	                	if(!validateNull()){
                            $.messageBox({message:"请输入内容！"});
	                        return false;
	                    }
	                    if(!validateLength()){
                            $.messageBox({level:'warn',message:"内容最多允许输入500个字符！"});
	                        return false;
	                    }
	                	if(validateHasDisplay()){
	                        $.confirm({
	                            title:"确认修改",
	                            message:"目前仍有一个系统公告在显示，确认修改后将显示此条公告！",
	                            okFunc:function(){
	                               $("#maintainForm").submit();
	                            },
                                cancelFunc:function(){
                                    $("#proclamationDialog").dialog("close");
                                }
	                        });
	                    }else{
	                        $("#maintainForm").submit();
	                    }
                    },
                    "关闭":function(){
                        $(this).dialog("close");
                    }
                }
            });
        });

        $("#reload").click(function(){
            $("#proclamationList").trigger("reloadGrid");
        });
    });

    function displayFormatter(el,options,rowData){
        if(rowData.display == '1')
            return "是";
        else
            return "否";
    }

    function validateHasDisplay(){
        if(!$("#display").attr("checked")){
            return false;
        }else{
            var flag = false;
	        $.ajax({
	            url:"${path}/sysadmin/proclamationManage/validateHasDisplay.action",
	            async:false,
	            data:{
	                "mode":$("#mode").val(),
	                "proclamationId":$("#proclamationId").val()
	            },
	            success:function(data){
	                if(data){
	                	flag = true;
	                }
	            },
	            error:function(){
	            	flag = false;
	            }
	        });
	        return flag;
        }
    }

    function validateNull(){
    	if($("#proclamation_content").val().replace(/[&nbsp;,<p>,</p>]/g,"").replace(/[ ]/g,"") == '' || $("#proclamation_content").val().replace(/[&nbsp;,<p>,</p>]/g,"").replace(/[ ]/g,"").length == 0){
    		return false;
	    }
	    return true;
    }

    function validateLength(){
        if($("#proclamation_content").val().replace(/[&nbsp;,<p>,</p>]/g,"").replace(/[ ]/g,"").length >500){
            return false;
        }
        return true;
    }

    function dbClickTable(){
    	var selectedId = $("#proclamationList").getGridParam('selrow');
        if(selectedId == null || selectedId == undefined){return;};
        var rowData = $("#proclamationList").getRowData(selectedId);
        $("#proclamationDialog").createDialog({
            width:dialogWidth,
            height:dialogHeight,
            title:"查看系统公告",
            url:"${path}/sysadmin/proclamationManage/dispatch.action?mode=view&proclamationId="+rowData.id,
            buttons:{
                "关闭":function(){
                    $(this).dialog("close");
                }
            }
        });
    }
</script>