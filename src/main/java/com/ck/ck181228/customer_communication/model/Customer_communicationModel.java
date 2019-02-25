package com.ck.ck181228.customer_communication.model;


import com.ck.ck181228.init.page.Page;

/**
 * @author acer
 *
 */
public class Customer_communicationModel extends Page {
	private Integer id;
	/**
	 * 用户编号
	 */
	private String user_code;
	/**
	 * 客户编号
	 */
	private String cust_code;
	/**
	 * 沟通客户时间
	 */
	private String TIME;
	/**
	 * 客户意向
	 */
	private String type;
	/**
	 * 沟通内容
	 */
	private String content;
	/**
	 * 顾客姓名
	 */
	private String cust_name;
	private String user_name;
	
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getCust_code() {
		return cust_code;
	}
	public void setCust_code(String cust_code) {
		this.cust_code = cust_code;
	}
	public String getTIME() {
		return TIME;
	}
	public void setTIME(String tIME) {
		TIME = tIME;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
