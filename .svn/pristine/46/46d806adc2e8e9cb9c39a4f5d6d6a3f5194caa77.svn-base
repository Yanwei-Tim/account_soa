function processDailydirectoryTreeOption(o,self){
	
	
	var defaultOption={
		shouldJugeMultizones:false, //是否把责任区作为根节点
		rootId:false,	//树的根节点ID
		allOrg:false,	//是否不通过判断orgId
		excludeRoot:false,	//树中是否包含根节点
		url:'/daily/dailyDirectoryManage/firstLoadDailydirectory.action',
		isLocalData:false,
		isRootSelected:true, //是否选中根节点
		isLoadDailyLogs:false,
		afterNodeExpanded:false
	};
	$.extend(defaultOption,o);
	
	var url=PATH+defaultOption.url;
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
		treePanelOption.loader = new Tree.TreeLoader({dataUrl:url+'?dailyYearId='+o.dailyYearId+'&initTree='+self.attr("id")});
	}
	var treePanel = new Tree.TreePanel(treePanelId, treePanelOption);
	treePanel.on('beforeload',function(node){ 
    	if(node.id!=(treePanelId+"-root")){
    		var param = '?dailyYearId='+o.dailyYearId + '&parentId='+node.id;
    		if(defaultOption.isLoadDailyLogs && node.attributes.leaf==2){
    			treePanel.loader.dataUrl = PATH+'/workingRecord/workingRecordManage/findExtTreeDataByDirectoryId.action'+param+'&initTree='+self.attr("id");
    		}else{
    			treePanel.loader.dataUrl = PATH+defaultOption.url+param+'&initTree='+self.attr("id");
    		}
    	}
    });  
	if(defaultOption.afterNodeExpanded){
		treePanel.on("load",defaultOption.afterNodeExpanded);
	}
	var customRoot=new Tree.TreeNode({
		text : 'orgManage',
        draggable : false,
        id : (treePanelId+"dailyDirectory-root"),
        dailyYearId : o.dailyYearId
    });
    var root = new Tree.AsyncTreeNode({
    	text : '台账目录树',
        draggable : false,
        id : (treePanelId+"-root"),
        leaf:false,
        dailyYearId : o.dailyYearId
    });
    customRoot.appendChild(root);
    treePanel.setRootNode(customRoot);
    treePanel.render();
    
    function expandNode(node){
    	node.expand(false,false,function(){
    		if(!node.firstChild.leaf){
    			expandNode(node.firstChild);
    			/*
    			node.firstChild.expand(false,false,function(){
    				if(defaultOption.isRootSelected){
    					treePanel.getSelectionModel().select(node.firstChild);
    					treePanel.fireEvent("click",node.firstChild);
    				}
    			})
    			*/
    		}else{
    			if(defaultOption.isRootSelected){
					treePanel.fireEvent("click",node);
				}
    		}
    	});
    }
    
    if(!defaultOption.isLocalData){
    	expandNode(root);
    	/*
    	root.expand(false,false,function(){
    		if(root.firstChild){
    			root.firstChild.expand(false,false,function(){
    				if(defaultOption.isRootSelected){
    					treePanel.getSelectionModel().select(root.firstChild);
    					treePanel.fireEvent("click",root.firstChild);
    				}
    			})
    		}
		});
		*/
    }
	return treePanel;
}

jQuery.fn.extend({
	initDailydirectoryTree : function(p){
		return processDailydirectoryTreeOption(p,$(this));
	}
});