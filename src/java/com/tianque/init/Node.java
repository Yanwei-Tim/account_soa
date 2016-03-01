package com.tianque.init;

import java.util.List;
import java.util.Vector;

import com.tianque.domain.Permission;

@SuppressWarnings("serial")
public class Node implements java.io.Serializable {

	public Node() {
	}

	public Node(Permission permission) {
		this.permission = permission;
	}
	private Permission permission;
	private int level;
	private boolean check = false;
	private boolean expanded = false;
	private boolean leaf = false;
	private String parentEname;
	private Vector<Node> children = new Vector<Node>();
	
	public void add(Node node) {
		children.add(node);
	}

	public void remove(Node tree) {
		children.remove(tree);
	}
	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getParentEname() {
		return parentEname;
	}

	public void setParentEname(String parentEname) {
		this.parentEname = parentEname;
	}
	public List<Node> getChildren() {
		return children;
	}
	public void setParentTree(Node node){
		
	}

}
