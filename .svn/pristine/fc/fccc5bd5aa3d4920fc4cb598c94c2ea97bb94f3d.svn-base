
$.fn.extend({
	treeSelect:function(options){
		var self=$(this);
		var selfId=self.attr("id");
		var defaultOption={
			store:new Ext.data.SimpleStore({fields:[],data:[[]]}), 
		    editable:false, //禁止手写及联想功能
		    mode: 'local', 
		    triggerAction:'all',
		    name:'org',
		    fieldLabel : '组织机构',
		    maxHeight: 250, 
		    tpl: "<div id="+selfId+"-tree"+" style='width:100%;overflow:auto;'>"+"</div>", //html代码
		    selectedClass:'', 
		    onSelect:Ext.emptyFn,
		    emptyText:'请选择...',
		    listWidth:180,
		    inputName:"",
		    allOrg:false,
		    url:false,
		    rootId:false
		};
		function style(){
			$("#"+selfId+"-tree").parent().parent().remove();
			self.width(self.width()-25);
			$("#"+selfId+"tree").bgiframe();
		};
		$.extend(defaultOption, options);
		style();
		var comboWithTooltip = new Ext.form.ComboBox(defaultOption);
		comboWithTooltip.applyTo(selfId);
		var url;
		if(defaultOption.url){
			url = defaultOption.url;
		}else{
			url = "/sysadmin/orgManage/firstLoadExtTreeData.action";
		}
		var tree = $("#"+selfId+"-tree").initAdministrativeTree({shouldJugeMultizones:true,allOrg:defaultOption.allOrg,url:url,rootId:defaultOption.rootId});
		$("#"+selfId+"-tree").height(defaultOption.maxHeight).width(defaultOption.listWidth);
		
		tree.on("click",function(node,e) {
			if(node != null){
				comboWithTooltip.setRawValue(node.text);
		        comboWithTooltip.collapse();
		        if ($("input[name='"+defaultOption.inputName+"']").val()!=node.id){
			        $("input[name='"+defaultOption.inputName+"']").val(node.id);
		        }
			}
		});
		return tree;
	}
});
