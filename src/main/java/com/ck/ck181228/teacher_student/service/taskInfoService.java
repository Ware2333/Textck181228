package com.ck.ck181228.teacher_student.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.ck181228.init.ServiceUtil.ServiceUtil;
import com.ck.ck181228.teacher_student.mapper.taskInfoMapper;
import com.ck.ck181228.teacher_student.model.taskInfoModel;

@Service
public class taskInfoService extends ServiceUtil<taskInfoModel> {
	
	@Autowired
	private taskInfoMapper<taskInfoModel> mapper;
	
	@Override
	public taskInfoMapper<taskInfoModel> getmapper() {
		return mapper;
	}
	
	/**
	 * 
	 * @param taskmodel
	 * @return
	 */
	public String selectTaskList(taskInfoModel taskmodel) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("task", getmapper().selectList(taskmodel));
		map.put("count", getmapper().selectCount(taskmodel));
		return new JSONObject(map).toString();
	}

}
