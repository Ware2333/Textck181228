package com.ck.ck181228.class_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ck.ck181228.class_management.model.classModel;
import com.ck.ck181228.class_management.service.classService;

@Controller
@RequestMapping("/controllerClass")
public class controllerClass {
	@Autowired
	private classService service;
	
	@ResponseBody
	@RequestMapping("/page")
	public String pageClass(classModel classmodel) {
		return service.selectclasslist(classmodel);
	}
	
	@ResponseBody
	@RequestMapping("/addClass")
	public String insertClass(classModel classmodel) {
		return service.insert(classmodel);
	}
}
