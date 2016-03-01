function processPermissionTreeOption(o,self){
	var defaultOption={
		shouldJugeMultizones:false, //是否把责任区作为根节点
		rootId:false,	//树的根节点ID
		allOrg:false,	//是否不通过判断orgId
		excludeRoot:false,	//树中是否包含根节点
		orgType:false,  //树中包含的org类型
		url:'/sysadmin/permissionManage/firstLoadPermission.action',
		isLocalData:false,
		isRootSelected:true //是否选中根节点
	};
	$.extend(defaultOption,o);
	
	var url=PATH+defaultOption.url;
	if(defaultOption.orgType){
		if(url.indexOf("?")==-1){
			url=url+'?orgType='+defaultOption.orgType;
		}else{
			url=url+'&orgType='+defaultOption.orgType;
		}
	}
	if(defaultOption.rootId!=false){
		if(url.indexOf("?")==-1){
			url=url+'?rootId='+defaultOption.rootId;
		}else{
			url=url+'&rootId='+defaultOption.rootId;
		}
	}
	if(defaultOption.excludeRoot){
		if(url.indexOf("?")==-1){
			url=url+'?excludeRoot='+defaultOption.excludeRoot;
		}else{
			url=url+'&excludeRoot='+defaultOption.excludeRoot;
		}
	}
	if(defaultOption.allOrg){
		if(url.indexOf("?")==-1){
			url=url+'?allOrg='+defaultOption.allOrg;
		}else{
			url=url+'&allOrg='+defaultOption.allOrg;
		}
	}
	if(defaultOption.shouldJugeMultizones){
		if(url.indexOf("?")==-1){
			url=url+'?shouldJugeMultizones=true';
		}else{
			url=url+'&shouldJugeMultizones=true';
		}
	}
	var treePanelId=self.attr("id");
	var Tree=Ext.tree;
	
	var treePanelOption={
        animate:true, 
        enableDD:false,
        containerScroll: true,
        dropConfig: {appendOnly:true},
        rootVisible : false
    };
	if(!defaultOption.isLocalData){
		treePanelOption.loader = new Tree.TreeLoader({dataUrl:url});
	}
	var treePanel = new Tree.TreePanel(treePanelId, treePanelOption);
	
	treePanel.on('beforeload',function(node){ 
    	if(node.id!=(treePanelId+"-root")){
    		var param = '?parentId='+node.id;
    		if(defaultOption.orgType){
    			param = param + '&orgType=' + defaultOption.orgType;
    		}
    		treePanel.loader.dataUrl = PATH+'/sysadmin/permissionManage/firstLoadPermission.action'+param;
    	}
    });  
	var customRoot=new Tree.TreeNode({
		text : 'orgManage',
        draggable : false,
        id : (treePanelId+"-root")
    });
    var root = new Tree.AsyncTreeNode({
    	text : '权限树',
        draggable : false,
        id : (treePanelId+"-root")
    });
    customRoot.appendChild(root)
    treePanel.setRootNode(customRoot);
    treePanel.render();
    
    if(!defaultOption.isLocalData){
    	root.expand(false,false,function(n){root.firstChild.expand(false,false,function(){if(defaultOption.isRootSelected){treePanel.getSelectionModel().select(root.firstChild);treePanel.fireEvent("click",root.firstChild);}})});
    }
	return treePanel;
}
jQuery.fn.extend({
	initPermissionTree : function(p){
		return processPermissionTreeOption(p,$(this));
	}
});