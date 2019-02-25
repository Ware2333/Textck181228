package com.ck.ck181228.menu_management.model;

import java.util.ArrayList;
import java.util.List;

import com.ck.ck181228.init.page.Page;

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
	
	public String getMinimum_permissions() {
		return Minimum_permissions;
	}
	public void setMinimum_permissions(String minimum_permissions) {
		Minimum_permissions = minimum_permissions;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	private List<MenuModel> list = new ArrayList<MenuModel>();
	
	public List<MenuModel> getList() {
		return list;
	}
	public void setList(List<MenuModel> list) {
		this.list = list;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMenu_code() {
		return menu_code;
	}
	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMenu_url() {
		return menu_url;
	}
	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}
	public String getParent_code() {
		return parent_code;
	}
	public void setParent_code(String parent_code) {
		this.parent_code = parent_code;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	@Override
	public String toString() {
		return "MenuModel [id=" + id + ", menu_code=" + menu_code + ", menu_name=" + menu_name + ", menu_url="
				+ menu_url + ", parent_code=" + parent_code + ", level=" + level + ", orderby=" + orderby
				+ ", user_code=" + user_code + ", Minimum_permissions=" + Minimum_permissions + ", list=" + list + "]";
	}	
	
}
