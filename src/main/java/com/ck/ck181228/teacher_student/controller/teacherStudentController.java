package com.ck.ck181228.teacher_student.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ck.ck181228.teacher_student.model.teacherStudentModel;
import com.ck.ck181228.teacher_student.service.teacherStudentService;
import com.ck.ck181228.user_management.model.UserModel;

@Controller
@RequestMapping("/teacherStudentController")
public class teacherStudentController {
	@Autowired
	private teacherStudentService service;
	
	@ResponseBody
	@RequestMapping("/jobSubmission")
	public String jobSubmission(HttpServletRequest request, teacherStudentModel model) {
		return service.jobSubmission(request,model);
	}
	
	@ResponseBody
	@RequestMapping("/update/{id}")
	public String update(@PathVariable("id")Integer id, teacherStudentModel model) {
//		teacherStudentModel model = new teacherStudentModel();
		model.setId(id);
		return service.update(model);
	}
}
