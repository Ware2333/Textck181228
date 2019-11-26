package com.ck.ck181228.teacher_student.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.ck181228.init.ServiceUtil.ServiceUtil;
import com.ck.ck181228.teacher_student.mapper.taskInfoMapper;
import com.ck.ck181228.teacher_student.model.TaskInfoModel;

@Service
public class TaskInfoService extends ServiceUtil<TaskInfoModel> {
	
	@Autowired
	private taskInfoMapper<TaskInfoModel> mapper;
	
	@Override
	public taskInfoMapper<TaskInfoModel> getmapper() {
		return mapper;
	}
	
	/**
	 * 
	 * @param taskmodel
	 * @return
	 */
	public String selectTaskList(TaskInfoModel taskModel) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("task", getmapper().selectList(taskModel));
		map.put("count", getmapper().selectCount(taskModel));
		return new JSONObject(map).toString();
	}

}
