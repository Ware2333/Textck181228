package com.ck.ck181228.role_management.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ck.ck181228.role_management.model.RoleModel;
import com.ck.ck181228.role_management.service.RoleService;
import com.ck.ck181228.user_management.controller.UserController;
@RestController
@RequestMapping("/roleController")
public class RoleController {
	@SuppressWarnings("unused")
	private static final Logger log4j = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private RoleService service;
	
	@RequestMapping("/update.do")
	public String update(RoleModel model) {
		return service.update(model);
	}
	
	@RequestMapping("/role.do")
	public String role(RoleModel model) {
		return service.role(model);
	}
	
	@RequestMapping("/insert.do")
	public String insert(RoleModel model) {
		return service.insert(model);
	}
	
	@RequestMapping("/delete.do")
	public String delete(RoleModel model) {
		return service.delete(model);
	}
}
