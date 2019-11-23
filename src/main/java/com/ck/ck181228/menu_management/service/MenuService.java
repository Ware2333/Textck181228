package com.ck.ck181228.menu_management.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.ck181228.init.ServiceUtil.ServiceUtil;
import com.ck.ck181228.init.isempty.IsEmpty;
import com.ck.ck181228.menu_management.mapper.MenuMapper;
import com.ck.ck181228.menu_management.model.MenuModel;
import com.ck.ck181228.privilege_management.model.PrivilegeModel;
import com.ck.ck181228.privilege_management.service.PrivilegeService;

@Service("menuService")
public class MenuService extends ServiceUtil<MenuModel> {
	@Autowired
	private MenuMapper<MenuModel> mapper;

	@Autowired
	private PrivilegeService service;

	@Override
	public MenuMapper<MenuModel> getmapper() {
		return mapper;
	}

	/**删除目录信息,验证目录编号是否已占用,占用不可删除,一级目录不可删除
	 * @param model
	 * @return
	 * 参数：MenuModel
	 * 返回：字符串类型判断操作是否成功
	 */
	public String delete(MenuModel model) {
		MenuModel no = new MenuModel();
		no.setParent_code(model.getMenu_code());
		PrivilegeModel pmodel = new PrivilegeModel();
		pmodel.setMenu_code(model.getMenu_code());
		List<MenuModel> list = getmapper().selectList(no);
		if (!IsEmpty.lis(list)) {
			service.deleteModel(pmodel);
			return getmapper().deleteModel(model) > 0 ? "success" : "err";
		} else {
			return "Occupied";
		}
	}

	/**)
	 * @see com.ck.ck181228.init.ServiceUtil.ServiceUtil#update(com.ck.ck181228.init.page.Page)
	 * 重写修改方法,最高级管理员目录信息不可修改
	 * 参数：MenuModel
	 * 返回：字符串判断操作是否成功
	 */
	@Override
	public String update(MenuModel model) {
		if (model.getMenu_code().equals("admin")) {
			return "admin";
		} else {
			return getmapper().updateModel(model) > 0 ? "success" : "err";
		}
	}

	
	/**
	 * @see com.ck.ck181228.init.ServiceUtil.ServiceUtil#insert(com.ck.ck181228.init.page.Page)
	 *  重写添加方法,验证目录编号是否已存在,判断是否是一级菜单,一级菜单无地址信息
	 *  参数：MenuModel
	 *  返回：字符串判断操作是否成功
	 */
	@Override
	public String insert(MenuModel model) {
		MenuModel menumodel = new MenuModel();
		menumodel.setMenu_code(model.getParent_code());
		
		MenuModel mm = new MenuModel();
		mm.setMenu_code(model.getMenu_code());
		
		PrivilegeModel pmodel = new PrivilegeModel();
		pmodel.setMenu_code(model.getMenu_code());
		pmodel.setRole_code("admin");//默认最高级管理员拥有所有权限
		List<MenuModel> li = getmapper().select(menumodel);
		if(IsEmpty.lis(li)) {//判断是否是一级菜单，设置菜单级别
			model.setLevel("2");
		}else {
			model.setLevel("1");
		}
		if (!IsEmpty.lis(getmapper().select(mm))) {
			if (IsEmpty.str(model.getMenu_url())) {//判断是否存在菜单路径
				model.setMenu_url("/Textck181228/jump/" + model.getMenu_url());
			}
			service.insert(pmodel);
			return getmapper().insert(model) > 0 ? "success" : "err";
		} else {
			return "Already exist";
		}
	}

	/**查询菜单信息,判断是否存在查询条件,返回菜单内容以及数目
	 * @param model
	 * @return
	 * 参数：MenuModel
	 * 返回：字符串判断操作是否成功
	 */
	public String selectMenuModel(MenuModel model) {
		Map<String, Object> map = new HashMap<String, Object>();
		model.setPageOn(true);
		map.put("menu", getmapper().select(model));
		map.put("count", getmapper().selectCount(model));
		return new JSONObject(map).toString();
	}

	
	/**
	 * @param um
	 * @return
	 * 查询菜单并根据上下级管理
	 * 参数：MenuModel
	 * 返回：MenuModel结果集
	 */
	public List<MenuModel> selectMenu(MenuModel um) {
		List<MenuModel> list = getmapper().selectList(um);
		List<MenuModel> result = new ArrayList<MenuModel>();
		for (MenuModel ss : list) {
			if(ss == null) {
				return result;
			}
			if (ss.getParent_code().equals("0")) {
				result.add(ss);
			}
			for (MenuModel res : result) {
				if (ss.getParent_code().equals(res.getMenu_code())) {
					res.getList().add(ss);
				}
			}
		}
		return result;
	}
	
	public List<MenuModel> selectMenuli(MenuModel um) {
		List<MenuModel> list = getmapper().select(um);
		List<MenuModel> result = new ArrayList<MenuModel>();
		for (MenuModel ss : list) {
			if (ss.getParent_code().equals("0")) {
				result.add(ss);
			}
			for (MenuModel res : result) {
				if (ss.getParent_code().equals(res.getMenu_code())) {
					res.getList().add(ss);
				}
			}
		}
		return result;
	}
}
