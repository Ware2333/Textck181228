package com.ck.ck181228.menu_management.model;

import java.util.ArrayList;
import java.util.List;

import com.ck.ck181228.init.page.Page;

import lombok.Data;

@Data
public class MenuModel extends Page {
	private Integer id;
	//菜单编号
	private String menu_code;
	//菜单名称
	private String menu_name;
	//菜单地址
	private String menu_url;
	//上级菜单编号
	private String parent_code;
	//菜单等级
	private String level;
	//排序
	private String orderby;
	//用户编号
	private String user_code;
	//最小权限是否应用
	private String Minimum_permissions;
	private List<MenuModel> list = new ArrayList<MenuModel>();

	
}
