package com.tianque.controller.vo;

import org.apache.struts2.ServletActionContext;

import com.tianque.core.vo.ExtTreeData;
import com.tianque.domain.Permission;

public class PermissionTreeData extends ExtTreeData{
	
	public PermissionTreeData(Permission permission , boolean isFolder) {
		text = permission.getCname();
		id = permission.getId();
		this.leaf = !isFolder;
		convertPermissionNode(permission);
	}
	
	
	private void convertPermissionNode(Permission permission) {
		if(permission.getPermissionType() == 1){
			cls = "folder";
		} else {
			cls = "file";	
			leaf = true;
			expanded = false;
			icon = ServletActionContext.getRequest().getContextPath()
			+ "/resource/js/ext/treeImages/Leaf.gif";
		}
	}
	
}
