package com.ck.ck181228.privilege_management.model;

import com.ck.ck181228.init.page.Page;

import lombok.Data;

@Data
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
	
}
