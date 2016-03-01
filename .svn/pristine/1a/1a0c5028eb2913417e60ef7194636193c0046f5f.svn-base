function proccessIncidentTypeTreeOption (o,self){
	var defaultOption={
		rootId:false,	//树的根节点ID
		showDegreeRule :true, //是否展开第三层节点数据
		excludeRoot:false,	//树中是否包含根节点
		url:'/incident/systemManage/firstLoadExtTreeData.action',
		isLocalData:false,
		isRootSelected:true, //是否选中根节点
		loadCom:function(){}
	};
	$.extend(defaultOption,o);
	
	var url=PATH+defaultOption.url;
	
	if(!defaultOption.showDegreeRule){
		if(url.indexOf("?")==-1){
			url=url+'?showDegreeRule='+defaultOption.showDegreeRule;
		}else{
			url=url+'&showDegreeRule='+defaultOption.showDegreeRule;
		}
	}
	if(defaultOption.excludeRoot){
		if(url.indexOf("?")==-1){
			url=url+'?excludeRoot='+defaultOption.excludeRoot;
		}else{
			url=url+'&excludeRoot='+defaultOption.excludeRoot;
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
    		var param;
    		if(node.attributes.degreed){
    			 param = '?incidentTypeId='+node.attributes.id + '&degreed='+node.attributes.degreed+ '&showDegreeRule='+defaultOption.showDegreeRule;
    		}else{
    			 param = '?subjectionTypeId='+node.attributes.propertyDictId + '&showDegreeRule='+defaultOption.showDegreeRule;
    		}
    		treePanel.loader.dataUrl = PATH+'/incident/systemManage/ajaxIncidentsForExtTree.action'+param;
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
//初始化树
jQuery.fn.extend({
	plaitSchemeTree : function(o){
		return proccessIncidentTypeTreeOption(o,$(this));
	},
	/*系统管理*/
	incidentTypeTree : function(o){
		o=$.extend({},o);
		o.showDegreeRule=false;
		return proccessIncidentTypeTreeOption(o,$(this));
	},
	/*案例库*/
	caseLibarayTree : function(o){
		o=$.extend({},o);
		o.excludeRoot=true;
		return proccessIncidentTypeTreeOption(o,$(this));
	}
});


jQuery.extend({
	rename : function(tree,nodeId,newName){
		tree.getNodeById(nodeId).setText(newName);
	},
	removeNode : function(tree,nodeId){
		var node=tree.getNodeById(nodeId);
		
		node.parentNode.removeChild(node);
	},
	addNode:function(newNode,selNode){
		// var newNode = new Ext.tree.TreeNode({text:txt}); //新结点  
        // var selNode = tree.getSelectionModel().getSelectedNode();//选择的结点  
          //在同级结点之前添加结点  
        //  selNode.appendChild(newNode,selNode); 
		 //在同级结点之后添加新结点  
         // selNode.parentNode.insertBefore(newNode,selNode);  
		//添加子节点
        selNode.appendChild(newNode);  
	}
});

