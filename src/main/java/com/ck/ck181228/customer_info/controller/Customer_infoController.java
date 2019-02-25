package com.ck.ck181228.customer_info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ck.ck181228.customer_info.model.Customer_infoModel;
import com.ck.ck181228.customer_info.service.Customer_infoService;

@RestController
@RequestMapping("/customer_info")
public class Customer_infoController {
	
	@Autowired
	private Customer_infoService service;
	
	/**
	 * 添加客户信息
	 */
	@RequestMapping("/insert.do")
	public String insert(Customer_infoModel model) {
		return service.insert(model);
	}
	
	/**
	 * 删除客户信息
	 */
	@RequestMapping("/delete.do")
	public String delete(Customer_infoModel model) {
		return service.deleteModel(model);
	}

	/**
	 * 修改客户信息
	 */
	@RequestMapping("/update.do")
	public String update(Customer_infoModel model) {
		return service.updateModel(model);
	}

	/**
	 * 查询客户信息
	 */
	@RequestMapping("/select.do")
	public String select(Customer_infoModel model) {
		return service.selectcustomer(model);
	}

}
