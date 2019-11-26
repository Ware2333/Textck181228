package com.ck.ck181228.class_management.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.ck181228.class_management.mapper.ClassMapper;
import com.ck.ck181228.class_management.model.ClassModel;
import com.ck.ck181228.init.ServiceUtil.ServiceUtil;

@Service
public class ClassService extends ServiceUtil<ClassModel> {

	@Autowired
	private ClassMapper<ClassModel> mapper;

	@Override
	public ClassMapper<ClassModel> getmapper() {
		return mapper;
	}

	/**
	 * 
	 * @param classmodel
	 * @return
	 */
	public String selectclasslist(ClassModel classModel) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("classlist", getmapper().selectList(classModel));
		map.put("count", getmapper().selectCount(classModel));
		return new JSONObject(map).toString();
	}
	
	/**
	 * 
	 */
	@Override
	public String insert(ClassModel classmodel) {
		if (getmapper().selectModel(classmodel) != null) {
			return "Already exist";
		}
		return getmapper().insert(classmodel) > 0 ? "success" : "err";
	}

}
