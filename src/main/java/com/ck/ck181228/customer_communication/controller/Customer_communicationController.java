package com.ck.ck181228.customer_communication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ck.ck181228.customer_communication.model.Customer_communicationModel;
import com.ck.ck181228.customer_communication.service.Custmoter_communicationService;

@RestController
@RequestMapping("/customer_communicationController")
public class Customer_communicationController {
	@Autowired
	private Custmoter_communicationService service;
	
	/**
	 *添加客户沟通信息
	 */
	@RequestMapping("/insert.do")
	public String insert(Customer_communicationModel model) {
		return service.insert(model);
	}
	
	/**
	 * 删除客户沟通信息
	 */
	@RequestMapping("/delete.do")
	public String delete(Customer_communicationModel model) {
		return service.delete(model);
	}
	
	/**
	 * 查询客户沟通信息
	 */
	@RequestMapping("/select.do")
	public String select(Customer_communicationModel model) {
		return service.Customer_communication(model);
	}
	
	
	/**
	 * 修改客户沟通信息
	 */
	@RequestMapping("/update.do")
	public String update(Customer_communicationModel model) {
		return service.update(model);
	}
}
