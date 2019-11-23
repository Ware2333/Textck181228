package com.ck.ck181228.user_management.model;

import java.util.ArrayList;
import java.util.List;

import com.ck.ck181228.init.page.Page;

import lombok.Data;

@Data
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
	//班级id
	private String classId;
	//班级
	private String className;
	//班级名称
	private String cname;
	
}
