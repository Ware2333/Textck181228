package com.ck.ck181228.role_management.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.ck181228.init.ServiceUtil.ServiceUtil;
import com.ck.ck181228.init.isempty.IsEmpty;
import com.ck.ck181228.privilege_management.mapper.PrivilegeMapper;
import com.ck.ck181228.privilege_management.model.PrivilegeModel;
import com.ck.ck181228.role_management.mapper.RoleMapper;
import com.ck.ck181228.role_management.model.RoleModel;

@Service("roleService")
public class RoleService extends ServiceUtil<RoleModel> {
	@Autowired
	private RoleMapper<RoleModel> mapper;

	@Autowired
	private PrivilegeMapper<PrivilegeModel> privilegemapper;

	public PrivilegeMapper<PrivilegeModel> getprivilegemapper() {
		return privilegemapper;
	}

	@Override
	public RoleMapper<RoleModel> getmapper() {
		return mapper;
	}

	// 判断是否存在查询字段,存在即拼接,返回结果数目以及内容
	public String role(RoleModel model) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (IsEmpty.str(model.getRole_code())) {
			model.setRole_code("%" + model.getRole_code() + "%");
		}
		if (IsEmpty.str(model.getRole_name())) {
			model.setRole_name("%" + model.getRole_name() + "%");
		}
		map.put("data", getmapper().selectList(model));
		map.put("count", getmapper().selectCount(model));
		return new JSONObject(map).toString();
	}

	// 删除,判断是否为最高级管理员
	public String delete(RoleModel model) {
		if (model.getRole_code().equals("admin")) {
			return "admin";
		} else {
			return getmapper().deleteModel(model) > 0 ? "success" : "err";
		}
	}

	// 重写修改方法,最高级管理员不可修改
	@Override
	public String update(RoleModel model) {
		if (model.getRole_code().equals("admin")) {
			return "admin";
		} else {
			return getmapper().updateModel(model) > 0 ? "success" : "err";
		}
	}

	// 判断角色编号是否存在,不存在即添加用户权限
	@Override
	public String insert(RoleModel model) {
		RoleModel no = new RoleModel();
		no.setRole_code(model.getRole_code());
		List<RoleModel> list = getmapper().selectList(no);
		if (!IsEmpty.lis(list)) {
			return getmapper().insert(model) > 0 ? "success" : "err";
		} else {
			return "Already exist";
		}
	}
}
