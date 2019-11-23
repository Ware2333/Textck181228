package com.ck.ck181228.class_management.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.ck181228.class_management.mapper.classMapper;
import com.ck.ck181228.class_management.model.classModel;
import com.ck.ck181228.init.ServiceUtil.ServiceUtil;
import com.ck.ck181228.teacher_student.model.taskInfoModel;
import com.ck.ck181228.user_management.model.UserModel;

@Service
public class classService extends ServiceUtil<classModel> {

	@Autowired
	private classMapper<classModel> mapper;

	@Override
	public classMapper<classModel> getmapper() {
		return mapper;
	}

	/**
	 * 
	 * @param classmodel
	 * @return
	 */
	public String selectclasslist(classModel classmodel) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("classlist", getmapper().selectList(classmodel));
		map.put("count", getmapper().selectCount(classmodel));
		return new JSONObject(map).toString();
	}
	
	/**
	 * 
	 */
	@Override
	public String insert(classModel classmodel) {
		if (getmapper().selectModel(classmodel) != null) {
			return "Already exist";
		}
		return getmapper().insert(classmodel) > 0 ? "success" : "err";
	}

}
