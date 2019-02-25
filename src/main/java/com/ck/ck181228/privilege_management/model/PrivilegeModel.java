package com.ck.ck181228.privilege_management.model;

import com.ck.ck181228.init.page.Page;

public class PrivilegeModel extends Page {
	private Integer id;
	//角色编号
	private String role_code;
	//菜单编号
	private String menu_code;
	//菜单名称
	private String menu_name;
	//上级菜单
	private String parent_code;
	
	public String getParent_code() {
		return parent_code;
	}
	public void setParent_code(String parent_code) {
		this.parent_code = parent_code;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRole_code() {
		return role_code;
	}
	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}
	public String getMenu_code() {
		return menu_code;
	}
	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
	}
	
}
