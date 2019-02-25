package com.ck.ck181228.order_info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ck.ck181228.order_info.model.Order_infoModel;
import com.ck.ck181228.order_info.service.Order_infoService;

@RestController
@RequestMapping("/order_Controller")
public class Order_Controller {
	@Autowired
	private Order_infoService service;

	@RequestMapping("/insert.do")
	public String insert(Order_infoModel model) {
		return service.insert(model);
	}

	@RequestMapping("/delete.do")
	public String delete(Order_infoModel model) {
		return service.deleteModel(model);
	}

	@RequestMapping("/update.do")
	public String update(Order_infoModel model) {
		return service.updateModel(model);
	}

	@RequestMapping("/select.do")
	public String select(Order_infoModel model) {
		return service.selectdata(model);
	}
}
