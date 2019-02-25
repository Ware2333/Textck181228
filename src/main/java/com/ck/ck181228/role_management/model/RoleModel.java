package com.ck.ck181228.role_management.model;

import com.ck.ck181228.init.page.Page;

public class RoleModel extends Page {
	private Integer id;
	//角色编号
	private String role_code;
	//角色名字
	private String role_name;
	//检查角色是否通过
	private String role_To_examine;
	//角色备注
	private String role_note;
	
	public String getRole_To_examine() {
		return role_To_examine;
	}
	public void setRole_To_examine(String role_To_examine) {
		this.role_To_examine = role_To_examine;
	}
	public String getRole_note() {
		return role_note;
	}
	public void setRole_note(String role_note) {
		this.role_note = role_note;
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
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
}
