package com.ck.ck181228.product_info.model;

import com.ck.ck181228.init.page.Page;

public class Product_infoModel extends Page {
	private Integer id;
	//商品编号
	private String code;
	//商品名称
	private String name;
	//商品数量
	private Integer sum;
	//商品成本
	private Integer cost;
	//商品成本单价
	private Integer cost_unit_price;
	
	public Integer getCost_unit_price() {
		return cost_unit_price;
	}
	public void setCost_unit_price(Integer cost_unit_price) {
		this.cost_unit_price = cost_unit_price;
	}
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	
}
