package com.ck.ck181228.user_management.model;

import java.util.ArrayList;
import java.util.List;

import com.ck.ck181228.init.page.Page;

public class UserModel extends Page {
	private Integer id;
	//用户编号
	private String user_code;
	//用户姓名
	private String user_name;
	//用户密码
	private String user_pass;
	//角色编号
	private String role_code;
	//上级编号
	private String parent_code;
	private List<UserModel> list=new ArrayList<>();
	//密码错误次数
	private String error_times;
	//黑名单状态
	private String blacklist;
	//冻结状态
	private String state;
	//角色名字
	private String role_name;
	
	
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<UserModel> getList() {
		return list;
	}
	public void setList(List<UserModel> list) {
		this.list = list;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pass() {
		return user_pass;
	}
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	public String getRole_code() {
		return role_code;
	}
	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}
	public String getParent_code() {
		return parent_code;
	}
	public void setParent_code(String parent_code) {
		this.parent_code = parent_code;
	}
	public String getError_times() {
		return error_times;
	}
	public void setError_times(String error_times) {
		this.error_times = error_times;
	}
	public String getBlacklist() {
		return blacklist;
	}
	public void setBlacklist(String blacklist) {
		this.blacklist = blacklist;
	}
	
}
