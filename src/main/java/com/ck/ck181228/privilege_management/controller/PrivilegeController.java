package com.ck.ck181228.privilege_management.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ck.ck181228.privilege_management.model.PrivilegeModel;
import com.ck.ck181228.privilege_management.service.PrivilegeService;
import com.ck.ck181228.user_management.controller.UserController;

@RestController
@RequestMapping("/privilegeController")
public class PrivilegeController {
	@SuppressWarnings("unused")
	private static final Logger log4j = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private PrivilegeService service;
	
	@RequestMapping("/update.do")
	public String update(PrivilegeModel model,String[] menu_codearr,String[] newmenu_codearr) {
		return service.update(model,menu_codearr,newmenu_codearr);
	}
	
//	@RequestMapping("/insert.do")
//	public String insert(PrivilegeModel model) {
//		return service.insert(model);
//	}
//	@RequestMapping("/delete.do")
//	public String delete(PrivilegeModel model) {
//		return service.deleteModel(model);
//	}
}
