package com.ck.ck181228.product_info.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.ck.ck181228.product_info.model.Product_infoModel;
import com.ck.ck181228.product_info.service.Product_infoService;
import com.ck.ck181228.user_management.controller.UserController;

@RestController
@RequestMapping("/product_infoController")
public class Product_infoController {
	@SuppressWarnings("unused")
	private static final Logger log4j = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private Product_infoService service;
	
	@RequestMapping("/insert.do")
	public String insert(Product_infoModel model) {
		return service.insert(model);
	}
	
	@RequestMapping("/delete.do")
	public String delete(Product_infoModel model,String[] code) {
		return service.delete(model,code);
	}
	
	@RequestMapping("/update.do")
	public String update(Product_infoModel model) {
		return service.update(model);
	}
	
	@RequestMapping("/selectdata.do")
	public String select(Product_infoModel model) {
		return service.selectdata(model);
	}
	
	@RequestMapping("/downloadExcel.do")
	public void downloadExcel(HttpServletResponse response) throws Exception{
		 service.downloadExcel(response);
	}
	
	@RequestMapping("/uploadExcel.do")
	public String uploadExcel(CommonsMultipartResolver multipartResolver,HttpServletRequest request) throws Exception{
		return service.uploadExcel(multipartResolver,request);
	}
}
