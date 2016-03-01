<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="center-left">
    <div class="propertyDomainList">
        <ul id="propertiesDomain">
            <s:iterator value="issueTypeDomains" id="issueTypeDomains">
                <li><a  href="javascript:void(0)" class="${domainName}" name="${id }">${domainName}</a></li>
            </s:iterator>
        </ul>
    </div>
</div>
<div class="center-right">
	<div class="content">
		<input type="hidden" name="domainId" id="domainId" value="" />
		<div class="ui-corner-all" id="nav">
			<pop:JugePermissionTag ename="addIssueType">
				<a id="add" href="javascript:void(0)"><span>新增</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="dispatchIssueType">
				<a id="update" href="javascript:void(0)"><span>修改</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="viewIssueType">
				<a id="view" href="javascript:void(0)"><span>查看</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="deleteIssueType">
				<a id="delete" href="javascript:void(0)"><span>删除</span></a>
			</pop:JugePermissionTag>
			<!--
			<a id="reload" href="javascript:void(0)" disabled="disabled"><span>刷新</span></a>
			 -->
			<s:if test="user.admin">
				<a id="lock" href="javascript:void(0)"><span>停用</span></a>
			</s:if>
			<pop:JugePermissionTag ename="moveIssueType">
				<a id="toPrevious" href="javascript:void(0)"><span><strong
						class="ui-ico-sy"></strong>上移</span>
				</a>
				<a id="toNext" href="javascript:void(0)"><span><strong
						class="ui-ico-xy"></strong>下移</span>
				</a>
				<a id="toFirst" href="javascript:void(0)"><span><strong
						class="ui-ico-top"></strong>到顶</span>
				</a>
				<a id="toEnd" href="javascript:void(0)"><span><strong
						class="ui-ico-foot"></strong>到底</span>
				</a>
			</pop:JugePermissionTag>
		</div>
		<div style="width: 100%;">
			<table id="issueTypeList"></table>
		</div>
		<div id="issueTypeDialog"></div>
	</div>
</div>
<script type="text/javascript">
    var domainId = '';
    var dialogWidth=590;
    var dialogHeight=350;
    function isEnabled(el, options, rowData){
    	if(true==rowData.enabled){
    		return "启用";
    	}else{
    		return "停用";
    	}
    }
    $(function(){
        var centerHeight=$("div.ui-layout-center").outerHeight();
    	$(".propertyDomainList").height(centerHeight-35);

        var currentDomainProperty;
        function formatInternelProperty(rowValue) {
            if(!currentDomainProperty.systemSensitive){
                return "无系统内置属性";
            }
            if(jQuery.isEmptyObject(currentDomainProperty) || jQuery.isEmptyObject(currentDomainProperty.internaleProperties)){
                return "系统错误，不能正常显示";
            }
            var properties = currentDomainProperty.internaleProperties;
            for (var prop in properties){
                if(rowValue == (properties[prop]).identify){
                    return (properties[prop]).displayName;
                }
            }
            return "系统错误，不能正常显示";
        }

        var propertyDomainId;
    	function getPropertyDomainInternalName(srcObj){
    		propertyDomainId = $(srcObj).attr("name");
    		domainId = propertyDomainId;
            $("#propertyDomainId").val(propertyDomainId);
    		$.ajax({
    			url:'${path}/sysadmin/issueTypeManage/getIssueTypeDomainById.action?issueTypeDomain.id='+ propertyDomainId,
    			cache:false,
    			success:function(data){
                      if(!data.id){
                     	 $.messageBox({
    							message:data,
    							level: "error"
    				     });
    	                return;
                      }
                     currentDomainProperty = data;
            		if(propertyDomainId!="default"){
            			$("#add").buttonEnable();
            		}
    	        }
    		});
    	}
    	$("#propertiesDomain a").click(function(){
    		var firstLoad = $("#propertiesDomain a.select").size();
    		$("#propertiesDomain a").removeClass("select");
    		$(this).addClass("select");
    		getPropertyDomainInternalName($(this));
    		if(firstLoad!=0){
    			$("#issueTypeList").setGridParam({url:"${path}/sysadmin/issueTypeManage/issueTypeList.action?issueType.issueTypeDomain.id="+propertyDomainId});
    			$("#issueTypeList").trigger("reloadGrid");
    		}
    	});
    	$("#propertiesDomain a:first").click();
        $("#issueTypeList").jqGridFunction({
        	url:"${path}/sysadmin/issueTypeManage/issueTypeList.action?issueType.issueTypeDomain.id="+propertyDomainId,
            datatype: "json",
            colModel:[
                {name:'id', index:'id', hidden:true ,sortable:false},
                {name:'issueTypeName', label:'类别名称',sortable:true,width:230},
                {name:'internalId',label:'内置属性', sortable:true,formatter: formatInternelProperty,width:300},
                {name:'content', label:'描述', sortable:true,width:350},
                <s:if test="user.admin">
	            	{name:'enabled',label:'是否启用',align:'center',sortable:true,formatter:isEnabled,width:80},
	            </s:if>
                <s:if test="user.admin">
                	{name:'org.orgName',label:'所属县区',sortable:false,width:150},
                </s:if>
                {name:'indexId',index:'indexId',hidden:true,sortable:false}
            ],
    	   	rowNum:-1,
    	    pager:false,
    	    loadComplete: function(){toggleButtons();$.loadingComp("close");},
    	    ondblClickRow: viewIssueType,
    	   	showColModelButton:false
        });

        $("#reload").click(function(){
            $("#issueTypeList").trigger("reloadGrid");
        });

        $("#lock").click(function(){
        	var selectedId = $("#issueTypeList").getGridParam('selrow');
        	if(selectedId == null){
            	$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
				return;
			}
        	var issueType = $("#issueTypeList").getRowData(selectedId);
        	$.ajax({
        		url: '${path}/sysadmin/issueTypeManage/enabledOperate.action',
        		data: {
        	        "issueType.id": function(){
        	            return issueType.id;
        	        },
        	        "issueType.enabled" : function(){
        	        	if(issueType.enabled == "启用"){
        	        		return false;
        	        	}
        	        	if(issueType.enabled == "停用"){
        	        		return true;
        	        	}
        	        }
                },
        		success:function(data){
        			if(data == true){
		       			$("#issueTypeList").trigger("reloadGrid");
        				$.messageBox({
        				    message: (issueType.enabled=="启用" ? "成功停用事件处理类型:"+ issueType.issueTypeName +"!" :  "成功启用事件处理类型:"+ issueType.issueTypeName +"!")
        				 });
        				return;
        			}
               		$.messageBox({
     					message:data,
     					level: "error"
        			});
            	}
        	});
        });

        $("#add").click(function(){
            if(propertyDomainId == ""){
                 return ;
            }
            $("#issueTypeDialog").createDialog({
                width:dialogWidth,
                height:dialogHeight,
                title:'新增事件处理类别',
                url:'${path}/sysadmin/issueTypeManage/dispatchIssueTypeOperate.action?mode=add&issueType.issueTypeDomain.id='+propertyDomainId,
                buttons:{
                	"保存":function(event){
	                    $("#maintainForm").submit();
	                },
                    "关闭":function(){
                        $(this).dialog("close");
                    }
                }
            });
        });

        $("#update").click(function(){
        	var selectedId = $("#issueTypeList").getGridParam('selrow');
        	if(selectedId == null){
            	$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
				return;
			}
        	var issueType = $("#issueTypeList").getRowData(selectedId);
        	$("#issueTypeDialog").createDialog({
                width:dialogWidth,
                height:dialogHeight,
                title:'修改事件处理类别',
                url:'${path}/sysadmin/issueTypeManage/dispatchIssueTypeOperate.action?mode=edit&issueType.id='+ issueType.id +'&issueType.issueTypeDomain.id='+propertyDomainId,
                buttons:{
                    "保存":function(event){
                        $("#maintainForm").submit();
                    },
                    "关闭":function(){
                        $(this).dialog("close");
                    }
                }
            });
        });

        $("#view").click(function(){
            var selectedId = $("#issueTypeList").getGridParam("selrow");
            if(selectedId == null){
            	$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
				return;
			}
            viewIssueType(selectedId);
        });

        $("#delete").click(function(event){
        	var selectedId = $("#issueTypeList").getGridParam('selrow');
        	if(selectedId == null){
            	$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
				return;
			}
            $.confirm({
                title:"确认删除",
                message:"该事件处理类别信息删除后,将无法恢复,您确认删除该事件处理类别信息吗?",
                width:400,
                okFunc:deleteIssueType
            });
        });

        $("#toFirst").click(function(){
        	var selectedId = $("#issueTypeList").getGridParam("selrow");
            if(selectedId == null){
            	$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
				return;
			}
            var issueType = $("#issueTypeList").getRowData(selectedId);
    		if($("#issueTypeList").jqGrid('getGridParam','selrow')){
    			$("#issueTypeList").toFirst(
    				PATH+"/sysadmin/issueTypeManage/moveIssueType.action?moveMode=first",
    				{
                        "issueType.id":issueType.id,
                        "issueType.issueTypeDomain.id":domainId
    				},
    				function(){toggleButtons();}
    			);
    		}
        });

        $("#toEnd").click(function(){
        	var selectedId = $("#issueTypeList").getGridParam("selrow");
            if(selectedId == null){
            	$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
				return;
			}
            var issueType = $("#issueTypeList").getRowData(selectedId);
    		if($("#issueTypeList").jqGrid('getGridParam','selrow')){
    			$("#issueTypeList").toEnd(
    				PATH+"/sysadmin/issueTypeManage/moveIssueType.action?moveMode=end",
    				{
                        "issueType.id":issueType.id,
                        "issueType.issueTypeDomain.id":domainId
    				},
    				function(){toggleButtons();}
    			);
    		}
        });

        $('#toPrevious').click(function(){
        	var selectedId = $("#issueTypeList").getGridParam("selrow");
            if(selectedId == null){
            	$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
				return;
			}
            var issueType = $("#issueTypeList").getRowData(selectedId);
    		if($("#issueTypeList").jqGrid('getGridParam','selrow')){
    			$("#issueTypeList").toPrevious(
    				PATH+"/sysadmin/issueTypeManage/moveIssueType.action?moveMode=previous",
    				{
                        "issueType.id":issueType.id,
                        "issueType.issueTypeDomain.id":domainId,
                        "referIssueTypeId":$("#"+selectedId+"[role='row']").prev().attr("id")
    				},
    				function(){toggleButtons();}
    			);
    		}
        });

        $('#toNext').click(function(){
        	var selectedId = $("#issueTypeList").getGridParam("selrow");
            if(selectedId == null){
            	$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
				return;
			}
            var issueType = $("#issueTypeList").getRowData(selectedId);
    		if($("#issueTypeList").jqGrid('getGridParam','selrow')){
    			$("#issueTypeList").toNext(
    				PATH+"/sysadmin/issueTypeManage/moveIssueType.action?moveMode=next",
    				{
                        "issueType.id":issueType.id,
                        "issueType.issueTypeDomain.id":domainId,
                        "referIssueTypeId":$("#"+selectedId+"[role='row']").next().attr("id")
    				},
    				function(){toggleButtons();}
    			);
    		}
        });

    });

    function toggleButtons(){
    	var rowId = $("#issueTypeList").jqGrid('getGridParam','selrow');
    	var selectedId = $("#issueTypeList").getGridParam("selrow");
        var issueType = $("#issueTypeList").getRowData(selectedId);
    	if (issueType.enabled == "停用"){
    		$("#lock span").replaceWith("<span><strong class='ui-ico-chattr'/>启用</span>");
    	}else{
    		$("#lock span").replaceWith("<span><strong class='ui-ico-lock'/>停用</span>");
    	}
    	if(rowId==undefined){

    	}else{
    		var previd=$("#"+rowId+"[role='row']",$("#issueTypeList")).prev().attr("id");
    		if(previd==undefined||previd==""){
    			$.messageBox({level:'warn',message:"第一条数据不能进行到顶或上移！"});
    			return;
    		}
    		var nextid=$("#"+rowId+"[role='row']",$("#issueTypeList")).next().attr("id");
    		if(nextid==undefined||nextid==""){
    			$.messageBox({level:'warn',message:"最后一条数据不能进行到底或下移！"});
    			return;
    		}
    	}
    }

    function viewIssueType(rowid){
        if(rowid == null){return;}
        var issueType = $("#issueTypeList").getRowData(rowid);
        $("#issueTypeDialog").createDialog({
            width:dialogWidth,
            height:dialogHeight,
            title:'查看事件处理类别',
            url:'${path}/sysadmin/issueTypeManage/dispatchIssueTypeOperate.action?mode=view&issueType.id='+ issueType.id +'&issueType.issueTypeDomain.id='+domainId,
            buttons:{
                "关闭":function(){
                    $(this).dialog("close");
                }
            }
        });
    }

    function deleteIssueType(){
    	 var selectedId = $("#issueTypeList").getGridParam("selrow");
         if(selectedId == null){return;}
         var issueType = $("#issueTypeList").getRowData(selectedId);
         $.ajax({
             url:"${path}/sysadmin/issueTypeManage/deleteIssueType.action",
             data:{
                 "issueType.id":issueType.id,
                 "issueType.issueTypeDomain.id":domainId
             },
             success:function(responseData){
                 if (responseData>0){
                     $("#issueTypeList").delRowData(selectedId);
                     $.messageBox({message:"已经成功删除该事件处理类别信息!"});
                     toggleButtons();
                 }else{
                     $.messageBox({message:responseData});
                 }
             }
         });
    }
//-->
</script>