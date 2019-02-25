package com.ck.ck181228.privilege_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.ck181228.init.ServiceUtil.ServiceUtil;
import com.ck.ck181228.init.isempty.IsEmpty;
import com.ck.ck181228.privilege_management.mapper.PrivilegeMapper;
import com.ck.ck181228.privilege_management.model.PrivilegeModel;
import com.ck.ck181228.role_management.model.RoleModel;
import com.ck.ck181228.role_management.service.RoleService;

@Service("privilegeService")
public class PrivilegeService extends ServiceUtil<PrivilegeModel> {
	@Autowired
	private PrivilegeMapper<PrivilegeModel> mapper;
	@Autowired
	private RoleService service;

	@Override
	public PrivilegeMapper<PrivilegeModel> getmapper() {
		return mapper;
	}

	// 修改角色权限,判断权限是否有移除,是否有添加
	public String update(PrivilegeModel model, String[] menu_codearr, String[] newmenu_codearr) {
		int a = 0;
		if (menu_codearr != null) {
			for (String ss : menu_codearr) {
				model.setMenu_code(ss);
				a = getmapper().deleteModel(model);
				RoleModel roleModel = new RoleModel();
				PrivilegeModel pmodel = new PrivilegeModel();
				pmodel.setRole_code(model.getRole_code());
				if (!IsEmpty.lis(getmapper().selectList(pmodel))) {
					roleModel.setRole_To_examine("未拥有权限");
					roleModel.setRole_code(model.getRole_code());
					service.updateModel(roleModel);
				}
			}
		}
		if (newmenu_codearr != null) {
			RoleModel roleModel = new RoleModel();
			roleModel.setRole_To_examine("已拥有权限");
			roleModel.setRole_code(model.getRole_code());
			service.updateModel(roleModel);
			for (String ss : newmenu_codearr) {
				model.setMenu_code(ss);
				a = getmapper().insert(model);
			}
		}
		return a > 0 ? "success" : "err";
	}
}
