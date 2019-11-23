package com.ck.ck181228.role_management.model;

import com.ck.ck181228.init.page.Page;

import lombok.Data;

@Data
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
}
