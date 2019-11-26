package com.ck.ck181228.teacher_student.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ck.ck181228.init.CacheKit;
import com.ck.ck181228.teacher_student.model.TaskInfoModel;
import com.ck.ck181228.teacher_student.service.TaskInfoService;

@Controller
@RequestMapping("/taskInfoController")
public class TaskInfoController {
	@Autowired
	private TaskInfoService service;
	CacheKit cac = new CacheKit();
	
	/**
	 * 
	 * @param taskmodel
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insert")
	public String insertTaskInfo(TaskInfoModel taskmodel) {
		return service.insert(taskmodel);
	}
	
	/**
	 * 
	 * @param taskmodel
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update")
	public String deleteTaskInfo(TaskInfoModel taskmodel) {
		return service.updateModel(taskmodel);
	}
	
	/**
	 * 
	 * @param taskmodel
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public String updateTaskInfo(TaskInfoModel taskmodel) {
		return service.delete(taskmodel);
	}
	
	/**
	 * 
	 * @param taskmodel
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/tasklist")
	public String selectTaskInfo(TaskInfoModel taskmodel,HttpServletRequest request) {
		taskmodel.setOperationalPublisher(cac.getByCacheModel(request).getUser_code());
		return service.selectTaskList(taskmodel);
	}
}
