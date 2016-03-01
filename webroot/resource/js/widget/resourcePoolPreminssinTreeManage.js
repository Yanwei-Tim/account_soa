function proccessResourcePoolPremissionTreeOption(o,self){
	var defaultOption={
		shouldJugeMultizones:false, //是否把责任区作为根节点
		rootId:false,	//树的根节点ID
		allOrg:false,	//是否不通过判断orgId
		excludeRoot:false,	//树中是否包含根节点
		orgType:false,  //树中包含的org类型
		url:'/interation/resourcePool/firstLoadExtTreeData.action',
		isLocalData:false,
		isRootSelected:true, //是否选中根节点
		loadCom:function(){}
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
	var viewObjtypeId = defaultOption.viewObjtypeId;
	if(viewObjtypeId!=null && typeof(viewObjtypeId)!="undefined"){
		if(url.indexOf("?")==-1){
			url=url+'?viewObjtypeId='+viewObjtypeId;
		}else{
			url=url+'&viewObjtypeId='+viewObjtypeId;
		}
	}
	var resourcePoolId = defaultOption.resourcePoolId;
	if(resourcePoolId!=null && typeof(resourcePoolId)!="undefined"){
		if(url.indexOf("?")==-1){
			url=url+'?resourcePoolId='+resourcePoolId;
		}else{
			url=url+'&resourcePoolId='+resourcePoolId;
		}
	}
	var treePanelId=self.attr("id");
	var Tree=Ext.tree;
	
	var treePanelOption={
        animate:false, 
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
    		if(resourcePoolId!=null && typeof(resourcePoolId)!="undefined"){
    			param = param + '&resourcePoolId=' + resourcePoolId;
    		}
    		treePanel.loader.dataUrl = PATH+'/interation/resourcePool/ajaxOrgsForExtTree.action'+param;
    	}
    	node.attributes.isLoaded = true;
    });
	
	//treePanel.on('checkchange', function(node, checked) {
	//	if(node.attributes.isLoaded){
	//		node.expand();  
	//	}
	//	node.attributes.checked = checked; 
	//	node.eachChild(function(child) { 
	//		child.ui.toggleCheck(checked);  
	//		child.attributes.checked = checked;  
	//		child.fireEvent('checkchange', child, checked);  
	//	});  
	//});
    var root = new Tree.AsyncTreeNode({
    	text : 'orgManage',
        draggable : false,
        id : (treePanelId+"-root")
    });
    treePanel.setRootNode(root);
    treePanel.render();
    if(!defaultOption.isLocalData){
		root.expand(false,false,function(n){
			if(null!=root.childNodes&&undefined!=root.childNodes){
				for(var i = 0; i < root.childNodes.length; i++){
					root.childNodes[i].expand();
				}
			}
		});
    }
    root.expand(false,false,function(){
    	treePanel.exFunc();
    	defaultOption.loadCom();
    });
    treePanel.exFunc = function(){}
	return treePanel;
}
jQuery.fn.extend({
	initResourcePoolPremissionTree  : function(o){
	    //取消双击事件
		Ext.override(Ext.tree.TreeNodeUI, { 
		    onDblClick : function(e) { 
				e.preventDefault(); 
				if (this.node.disabled) {
					return; 
				} 
				if (this.checkbox && !this.node.hasChildNodes()) { 
				     return;
				} 
				if (!this.animating && this.node.hasChildNodes()) { 
				     var isExpand = this.node.ownerTree.doubleClickExpand; 
				    if (isExpand) { 
				    	return;
				    }else{
				    	 this.node.toggle(); 
				    }	 
				} 
		        this.fireEvent("dblclick", this.node, e); 
			} 
		});

	
		return proccessResourcePoolPremissionTreeOption(o,$(this));
	},
	disableNode : function(tree,nodeId){
		tree.getNodeById(nodeId).disable();
	}
});
