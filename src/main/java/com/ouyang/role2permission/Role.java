package com.ouyang.role2permission;

import java.util.Set;

public class Role {
	private Role parent;
	private Set<Role> children;
	private Set<Permission> permissions;
	
	public Role getParent() {
		return parent;
	}
	public void setParent(Role parent) {
		this.parent = parent;
	}
	public Set<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<Permission> permissions) {
		Set<Role> children = getChildren();
		for (Role role : children) {
			Set<Permission> childPermissions = role.getPermissions();
			childPermissions.addAll(permissions);
			role.setPermissions(childPermissions);
		}
		this.permissions = permissions;
	}
	public Set<Role> getChildren() {
		return children;
	}
	public void setChildren(Set<Role> children) {
		this.children = children;
	}
	
}
