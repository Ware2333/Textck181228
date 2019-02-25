package com.ck.ck181228.init.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ck.ck181228.customer_info.model.Customer_infoModel;
import com.ck.ck181228.customer_info.service.Customer_infoService;
import com.ck.ck181228.menu_management.model.MenuModel;
import com.ck.ck181228.menu_management.service.MenuService;
import com.ck.ck181228.order_info.model.Order_infoModel;
import com.ck.ck181228.privilege_management.model.PrivilegeModel;
import com.ck.ck181228.privilege_management.service.PrivilegeService;
import com.ck.ck181228.product_info.model.Product_infoModel;
import com.ck.ck181228.product_info.service.Product_infoService;
import com.ck.ck181228.role_management.model.RoleModel;
import com.ck.ck181228.role_management.service.RoleService;
import com.ck.ck181228.user_management.controller.UserController;
import com.ck.ck181228.user_management.model.UserModel;
import com.ck.ck181228.user_management.service.UserService;

@Controller
@RequestMapping("/jump")
public class controllerutil {
	@SuppressWarnings("unused")
	private static final Logger log4j = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private MenuService service;

	@Autowired
	private PrivilegeService privilegeservice;

	@Autowired
	private RoleService roleservice;

	@Autowired
	private UserService userservice;

	@Autowired
	private Product_infoService product_infoservice;

	@Autowired
	private Customer_infoService customer_infoservice;

	@RequestMapping("/login.do")
	public String login() {
		return "login";
	}

	/**
	 * 查询商品以及用户数据放到域中跳转页面 参数:Model域存放结果集,Product_infoModel 返回类型:String
	 */
	@RequestMapping("/demo.do")
	public String demo(Model model, Product_infoModel product_infomodel) {
		model.addAttribute("procuct_info", product_infoservice.selectList(product_infomodel));
		model.addAttribute("user_info", userservice.selectchart());
		return "MIS/demo";
	}

	@RequestMapping("/index.do")
	public String index(MenuModel um, Model model, HttpSession session) {
		um.setUser_code(String.valueOf(session.getAttribute("code")));
		model.addAttribute("menu", service.selectMenu(um));
		return "MIS/index";
	}

	@RequestMapping("/role.do")
	public String role() {
		return "MIS/role";
	}

	@RequestMapping("/user.do")
	public String user() {
		return "MIS/user_management";
	}

	@RequestMapping("/menu.do")
	public String menu() {
		return "MIS/menu_management";
	}

	@RequestMapping("/addrole.do")
	public String addrole(Model model) {
		List<MenuModel> list = service.selectMenu(new MenuModel());
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		for (MenuModel ss : list) {
			List<Map<String, Object>> list3 = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("label", ss.getMenu_name());
			map.put("id", ss.getMenu_code());
			for (MenuModel aa : ss.getList()) {
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("label", aa.getMenu_name());
				map1.put("id", aa.getMenu_code());
				list3.add(map1);
			}
			map.put("children", list3);
			list2.add(map);
		}
		model.addAttribute("allMenu", new JSONArray(list2));
		return "MIS/AddRole";
	}

	@RequestMapping("/AddCustomer_Communication_Info.do")
	public String AddCustomer_Communication_Info(Customer_infoModel Customer_infoModelmodel, Model model) {
		model.addAttribute("customer", customer_infoservice.selectList(Customer_infoModelmodel));
		return "business_data/AddCustomer_Communication_Info";
	}

	@RequestMapping("/addproduct.do")
	public String addproduct() {
		return "business_data/AddProduct";
	}

	@RequestMapping("/customer_info.do")
	public String customer_info() {
		return "business_data/customer_info";
	}

	@RequestMapping("/addcustomer.do")
	public String addcustomer() {
		return "business_data/AddCustomer";
	}

	@RequestMapping("/order_info.do")
	public String order_info() {
		return "business_data/order_info";
	}

	@RequestMapping("/addorder_info.do")
	public String addorder_info(Model model, Order_infoModel ordermodel) {
		model.addAttribute("cust", customer_infoservice.selectList(new Customer_infoModel()));
		model.addAttribute("produ", product_infoservice.selectList(new Product_infoModel()));
		return "business_data/AddOrder_Info";
	}

	@RequestMapping("/product_info.do")
	public String product_info() {
		return "business_data/product_info";
	}

	@RequestMapping("/customer_communication_info.do")
	public String customer_communication_info() {
		return "business_data/customer_communication_info";
	}

	@RequestMapping("/customer_communication.do")
	public String customer_communication(Customer_infoModel custmodel, Model model) {
		model.addAttribute("cust", customer_infoservice.selectModel(custmodel));
		return "business_data/customer_communication";
	}

	@RequestMapping("/editJurisdiction.do")
	public String Jurisdiction(Model model, MenuModel no, PrivilegeModel privilegemodel, RoleModel rolemodel) {
		rolemodel.setRole_code(privilegemodel.getRole_code());
		List<PrivilegeModel> plist = privilegeservice.selectList(privilegemodel);
		privilegemodel.setParent_code("0");
		List<PrivilegeModel> plist1 = privilegeservice.selectList(privilegemodel);
		model.addAttribute("role_menu_code", plist);
		model.addAttribute("role_menu", plist1);
		model.addAttribute("rolename", roleservice.selectModel(rolemodel));
		List<MenuModel> list = service.selectMenuli(new MenuModel());
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		for (MenuModel ss : list) {
			List<Map<String, Object>> list3 = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("label", ss.getMenu_name());
			map.put("id", ss.getMenu_code());
			for (MenuModel aa : ss.getList()) {
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("label", aa.getMenu_name());
				map1.put("id", aa.getMenu_code());
				list3.add(map1);
			}
			map.put("children", list3);
			list2.add(map);
		}
		model.addAttribute("editJurisdiction", new JSONArray(list2));
		return "MIS/EditJurisdiction";
	}

	@RequestMapping("/editrole.do")
	public String editrole(Model model, UserModel usermodel, RoleModel rolemodel) {
		model.addAttribute("role", roleservice.selectList(rolemodel));
		model.addAttribute("user", userservice.selectModel(usermodel));
		return "MIS/EditRole";
	}

	@RequestMapping("/addmenu.do")
	public String addmenu(Model model, MenuModel mm) {
		List<MenuModel> list = service.select(new MenuModel());
		model.addAttribute("addmenu", list);
		model.addAttribute("menucode", list);
		return "MIS/AddMenu";
	}

	@RequestMapping("/adduser.do")
	public String add(Model model) {
		List<RoleModel> rlist = roleservice.selectList(new RoleModel());
		model.addAttribute("Role", rlist);
		return "MIS/AddUser";
	}

	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("code");
		session.removeAttribute("role_code");
		return "login";
	}
}
