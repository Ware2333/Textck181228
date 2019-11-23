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
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	public String role(RoleModel model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", getmapper().selectList(model));
		map.put("count", getmapper().selectCount(model));
		return new JSONObject(map).toString();
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	public String delete(RoleModel model) {
		if (model.getRole_code().equals("admin")) {
			return "admin";
		} else {
			return getmapper().deleteModel(model) > 0 ? "success" : "err";
		}
	}

	/**
	 * 
	 */
	@Override
	public String update(RoleModel model) {
		if (model.getRole_code().equals("admin")) {
			return "admin";
		} else {
			return getmapper().updateModel(model) > 0 ? "success" : "err";
		}
	}

	/**
	 * 
	 */
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
