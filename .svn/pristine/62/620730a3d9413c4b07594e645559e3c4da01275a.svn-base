function proccessTreeOption(o,self){
	var defaultOption={
		shouldJugeMultizones:false, //是否把责任区作为根节点
		rootId:false,	//树的根节点ID
		allOrg:false,	//是否不通过判断orgId
		excludeRoot:false,	//树中是否包含根节点
		orgType:false,  //树中包含的org类型
		url:'/sysadmin/orgManage/firstLoadExtTreeData.action',
		nodeUrl:'',
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
    		if(defaultOption.nodeUrl==''){
    			defaultOption.nodeUrl='/sysadmin/orgManage/ajaxOrgsForExtTree.action';
    		}
    		treePanel.loader.dataUrl = PATH+defaultOption.nodeUrl+param;
    	}
    });  
    var root = new Tree.AsyncTreeNode({
    	text : 'orgManage',
        draggable : false,
        id : (treePanelId+"-root")
    });
    treePanel.setRootNode(root);
    treePanel.render();
    if(!defaultOption.isLocalData){
		root.expand(false,false,function(n){
			if(null!=root.firstChild&&undefined!=root.firstChild){
				root.firstChild.expand(false,false,function(){
					if(defaultOption.isRootSelected){
						treePanel.getSelectionModel().select(root.firstChild);
						treePanel.fireEvent("click",root.firstChild);
					}
				})
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
	initTree : function(o){
		return proccessTreeOption(o,$(this));
	},
	initAdministrativeTree : function(o){
		o=$.extend({},o);
		o.orgType='0';
		return proccessTreeOption(o,$(this));
	},
	initFunctionalTree : function(o){
		o=$.extend({},o);
		o.orgType='1';
		return proccessTreeOption(o,$(this));
	},
	initPartyTree : function(o){
		o=$.extend({},o);
		o.orgType='2';
		return proccessTreeOption(o,$(this));
	}
});

jQuery.extend({
	toNextNode : function(tree,nodeId){
		var node = tree.getNodeById(nodeId);
		var parentNode = node.parentNode;
		if(node.isLast()){
			return;
		}
		if(node.nextSibling.isLast()){
			var nextNode=node.nextSibling;
			parentNode.insertBefore(nextNode,node);
			return;
		}
		var nextNextNode=node.nextSibling.nextSibling;
		parentNode.insertBefore(node,nextNextNode);
		
	},
	toPreviousNode : function(tree,nodeId){
		var node = tree.getNodeById(nodeId);
		if(node.isFirst()){
			return;
		}
		var parentNode = node.parentNode;
		var previousNode=node.previousSibling;
		parentNode.insertBefore(node,previousNode);
	},
	toFirstNode : function(tree,nodeId){
		var node = tree.getNodeById(nodeId);
		if(node.isFirst()){
			return;
		}
		var parentNode = node.parentNode;
		var firstChild = parentNode.firstChild;
		parentNode.insertBefore(node,firstChild);
	},
	toEndNode : function(tree,nodeId){
		var node = tree.getNodeById(nodeId);
		if(node.isLast()){
			return;
		}
		var parentNode = node.parentNode;
		parentNode.appendChild(node);
	},
	searchChild:function(tree,childPath,callBack){
		if(childPath.indexOf("/")==0){
			childPath=childPath.substring(1);
		}
		childPath="/"+tree.id+"-root/"+childPath;
		tree.selectPath(childPath,null,function(bSuccess, oSelNode){
			tree.getSelectionModel().select(oSelNode);
			tree.fireEvent("click",oSelNode);
		});
	},
	rename : function(tree,nodeId,newName){
		tree.getNodeById(nodeId).setText(newName);
	},
	setOrgNodeType : function(tree,nodeId,typeInternalId){
		tree.getNodeById(nodeId).attributes.internalId=typeInternalId;
	},
	addNodeToFirst : function(tree,newNodeData,parentNodeId){
		var newNode= new Ext.tree.TreeNode(newNodeData);
		var parentNode=tree.getNodeById(parentNodeId);
		if (parentNode.isLeaf()) {
			parentNode.leaf = false;
		}
		var firstChild = parentNode.firstChild;
		parentNode.insertBefore(newNode,firstChild);
	},
	addNodeToLast : function(tree,newNodeData,parentNodeId){
		var newNode= new Ext.tree.TreeNode(newNodeData);
		var parentNode=tree.getNodeById(parentNodeId);
		if (parentNode.isLeaf()) {
			parentNode.leaf = false;
		}
		parentNode.appendChild(newNode);
	},
	addNodeToLastByParentNode : function(tree,newNodeData,parentNode){
		var newNode= new Ext.tree.TreeNode(newNodeData);
		if (parentNode.isLeaf()) {
			parentNode.leaf = false;
		}
		parentNode.appendChild(newNode);
	},
	addClick : function(tree,fun){
		tree.on("click" , fun);
	},
	addDbClick : function(tree,fun){
		tree.on("dblclick" , fun);
	},
	getSelectedNode : function(tree){
		 return tree.getSelectionModel().getSelectedNode(); 
	},
	getSelectedNodeId : function(tree){
		var node = tree.getSelectionModel().getSelectedNode();
		if(null!=node && undefined!=node){
			return node.attributes.id; 
		}
	},
	selectRootNode : function(tree){
		tree.getSelectionModel().select(tree.getRootNode().firstChild);
		tree.fireEvent("click",tree.getRootNode().firstChild);
	},
	removeNode : function(tree,nodeId){
		var node=tree.getNodeById(nodeId);
		if(node.isLast()){
			tree.getSelectionModel().select(node.parentNode);
			tree.fireEvent("click",node.previousSibling);
		}else{
			tree.getSelectionModel().select(node.nextSibling);
			tree.fireEvent("click",node.nextSibling);
		}
		node.parentNode.removeChild(node);
	},
	disableNode : function(tree,nodeId){
		tree.getNodeById(nodeId).disable();
	},
	setTreeValue:function(id,tree){
		if (null != id && id >0){
			$.ajax({
				url:PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+id,
				success:function(data){
						$.searchChild(tree,data);
					}
			});
		}
	}
});
