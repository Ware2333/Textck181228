package com.ck.ck181228.class_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ck.ck181228.class_management.model.ClassModel;
import com.ck.ck181228.class_management.service.ClassService;

@Controller
@RequestMapping("/controllerClass")
public class ControllerClass {
	@Autowired
	private ClassService service;
	
	@ResponseBody
	@RequestMapping("/page")
	public String pageClass(ClassModel classmodel) {
		return service.selectclasslist(classmodel);
	}
	
	@ResponseBody
	@RequestMapping("/addClass")
	public String insertClass(ClassModel classmodel) {
		return service.insert(classmodel);
	}
}
