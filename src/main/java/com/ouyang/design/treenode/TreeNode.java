package com.ouyang.design.treenode;

import java.util.Enumeration;
import java.util.Vector;

public class TreeNode {
	private String name;
	private TreeNode parent;
	private Vector<TreeNode> children = new Vector<>();
	public TreeNode(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TreeNode getParent() {
		return parent;
	}
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}
	
	public void addChildren(TreeNode treeNode){
		children.add(treeNode);
	}
	
	public void removeChildren(TreeNode treeNode){
		children.remove(treeNode);
	}
	
	public Enumeration<TreeNode> getChildren(){
		return children.elements();
	}
}
