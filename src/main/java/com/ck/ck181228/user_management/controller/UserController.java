package com.ck.ck181228.user_management.controller;


import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.ck.ck181228.user_management.model.UserModel;
import com.ck.ck181228.user_management.service.UserService;

@Controller
@RequestMapping("/User")
public class UserController {
	@SuppressWarnings("unused")
	private static final Logger log4j = LoggerFactory.getLogger(UserController.class);


	@Autowired
	private UserService service;
	
	@ResponseBody
	@RequestMapping(value = "/loginemail.do", produces = "application/json;charset=UTF-8")
	public String loginemail(UserModel model,HttpSession session) {
		return service.login(model,session);
	}

	@ResponseBody
	@RequestMapping("/mail.do")
	public String mail(String[] mail) throws MessagingException {
		return service.mail(mail);
	}
	
	@ResponseBody
	@RequestMapping("/fmail.do")
	public String Forz(String[] mail,HttpSession session,String user_code) throws MessagingException {
		return service.fmail(mail,session,user_code);
	}
	
	@ResponseBody
	@RequestMapping("/Thaw.do")
	public String Thaw(String user_code,String autonum,HttpSession session) {
		return service.Thaw(user_code,autonum,session);
	}

	@ResponseBody
	@RequestMapping("/insert.do")
	public String insert(UserModel model) {
		return service.insert(model);
	}
	
	@ResponseBody
	@RequestMapping("/delete.do")
	public String delete(UserModel model) {
		return service.deleteUser(model);
	}
	
	@ResponseBody
	@RequestMapping("/select.do")
	public String select(UserModel model) {
		return service.selectUserModel(model);
	}
	@ResponseBody
	@RequestMapping("/update.do")
	public String update(UserModel model) {
		return service.updateModel(model);
	}
	
	@ResponseBody
	@RequestMapping("/downloadexcel.do")
	public void excel(HttpServletResponse response) throws Exception {
		service.excel(response);
	}
	
    @ResponseBody
    @RequestMapping(value = "/uploadExcel.do")
    public String uploadExcel(CommonsMultipartResolver multipartResolver, HttpServletRequest request) throws Exception {
        return service.uploadExcel(multipartResolver, request);
    }
}
