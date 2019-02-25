package com.ck.ck181228.customer_info.model;

import com.ck.ck181228.init.page.Page;

/**
 * @author acer
 *
 */
public class Customer_infoModel extends Page {
	private Integer id;
	/**
	 * 客户编号
	 */
	private String cust_code;
	/**
	 * 客户姓名
	 */
	private String cust_name;
	/**
	 * 客户状态
	 */
	private String status;
	/**
	 * 客户邮箱
	 */
	private String email;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCust_code() {
		return cust_code;
	}
	public void setCust_code(String cust_code) {
		this.cust_code = cust_code;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
