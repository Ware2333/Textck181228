package com.ck.ck181228.menu_management.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ck.ck181228.menu_management.model.MenuModel;
import com.ck.ck181228.menu_management.service.MenuService;
import com.ck.ck181228.user_management.controller.UserController;

@RestController
@RequestMapping("/MenuController")
public class MenuController {
	@SuppressWarnings("unused")
	private static final Logger log4j = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private MenuService service;

	//添加目录信息
	@RequestMapping("/insert.do")
	public String insert(MenuModel model) {
		return service.insert(model);
	}

	//删除目录信息
	@RequestMapping("/delete.do")
	public String delete(MenuModel model) {
		return service.delete(model);
	}

	//修改目录
	@RequestMapping("/update.do")
	public String update(MenuModel model) {
		return service.update(model);
	}

	//查询目录信息
	@RequestMapping("/selectlist.do")
	public String selectlist(MenuModel model) {
		return service.selectMenuModel(model);
	}
}
