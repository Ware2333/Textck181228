package com.ck.ck181228.teacher_student.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.ck181228.init.CacheKit;
import com.ck.ck181228.init.ServiceUtil.ServiceUtil;
import com.ck.ck181228.teacher_student.mapper.teacherStudentMapper;
import com.ck.ck181228.teacher_student.model.teacherStudentModel;
import com.ck.ck181228.user_management.model.UserModel;

@Service
public class teacherStudentService extends ServiceUtil<teacherStudentModel> {
	@Autowired
	private teacherStudentMapper<teacherStudentModel> mapper;
	private CacheKit cac = new CacheKit();
	
	@Override
	public teacherStudentMapper<teacherStudentModel> getmapper(){
		return mapper;
	}
	
	public String jobSubmission(HttpServletRequest request, teacherStudentModel model) {
		Map<String, Object> map = new HashMap<String, Object>();
		UserModel user = cac.getByCacheModel(request);
//		model.setUserCode(user.getUser_code());
//		if(Integer.parseInt(user.getParent_code()) < 3) {
//		}
		if(Integer.parseInt(user.getParent_code()) < 3) {
			model.setParentCode(String.valueOf(Integer.parseInt(user.getParent_code())+1));
		}else if(Integer.parseInt(user.getParent_code()) == 3) {
			model.setUserCode(user.getUser_code());
			model.setParentCode(user.getParent_code());
		}
		model.setClassName(user.getClassName());
		map.put("taskUser", getmapper().selectList(model));
		map.put("count", getmapper().selectCount(model));
		return new JSONObject(map).toString();
	}
	
	public void insertts(List<teacherStudentModel> list) {
		getmapper().insertts(list);
	}
}
