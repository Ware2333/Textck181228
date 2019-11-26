package com.ck.ck181228.init.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ck.ck181228.class_management.model.ClassModel;
import com.ck.ck181228.class_management.service.ClassService;
import com.ck.ck181228.init.CacheKit;
import com.ck.ck181228.menu_management.model.MenuModel;
import com.ck.ck181228.menu_management.service.MenuService;
import com.ck.ck181228.privilege_management.model.PrivilegeModel;
import com.ck.ck181228.privilege_management.service.PrivilegeService;
import com.ck.ck181228.role_management.model.RoleModel;
import com.ck.ck181228.role_management.service.RoleService;
import com.ck.ck181228.teacher_student.model.TeacherStudentModel;
import com.ck.ck181228.teacher_student.service.TeacherStudentService;
import com.ck.ck181228.user_management.controller.UserController;
import com.ck.ck181228.user_management.model.UserModel;
import com.ck.ck181228.user_management.service.UserService;

@Controller
@RequestMapping("/jump")
public class ControllerUtil {
	CacheKit cac = new CacheKit();
	@SuppressWarnings("unused")
	private static final Logger log4j = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private MenuService service;

	@Autowired
	private PrivilegeService privilegeService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private TeacherStudentService teacherStudentService;

	@RequestMapping("/login.do")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/taskinfo")
	public String taskInfo() {
		return "MIS/task_info";
	}
	
	@RequestMapping("/Students")
	public String Students() {
		return "MIS/Students";
	}

	@RequestMapping("/logout")
	public String logout() {
		cac.delRedisRelevantKey();
		return "login";
	}
	

	/**
	 * 
	 * @param request
	 * @param um
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request,MenuModel um, Model model) {
		um.setUser_code(cac.getByCacheModel(request).getUser_code());
		model.addAttribute("code", cac.getByCacheModel(request).getUser_code());
		model.addAttribute("menu", service.selectMenu(um));
		return "MIS/index";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/role.do")
	public String role() {
		return "MIS/role";
	}
	
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/demo.do")
	public String demo() {
		return "MIS/demo";
	}
	
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/userTask")
	public String user_task(Model model,HttpServletRequest request) {
		model.addAttribute("user", cac.getByCacheModel(request));
		return "MIS/TeacherStudent";
	}
	
	@RequestMapping("/uploadTask/{taskCode}/{userName}/{id}")
	public String uploadTask(HttpServletRequest request, Model model, @PathVariable("taskCode")String taskCode, @PathVariable("userName")String userName,@PathVariable("id")Integer id) {
		model.addAttribute("userName", userName);
		model.addAttribute("taskCode", taskCode);
		model.addAttribute("id", id);
		TeacherStudentModel tmodel = new TeacherStudentModel();
		tmodel.setId(id);
		model.addAttribute("user", cac.getByCacheModel(request).getParent_code());
		model.addAttribute("taskUser", teacherStudentService.selectModel(tmodel));
		return "MIS/uploadTask";
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/classinfo")
	public String classinfo(Model model,HttpServletRequest request) {
		model.addAttribute("code",cac.getByCacheModel(request).getRole_code());
		return "MIS/ClassManagement";
	}
	
	@RequestMapping("/addClass")
	public String addClass() {
		return "MIS/AddClass";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/user.do")
	public String user(Model model,HttpServletRequest request) {
		model.addAttribute("code", cac.getByCacheModel(request).getUser_code());
		return "MIS/user_management";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/menu.do")
	public String menu() {
		return "MIS/menu_management";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
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
		model.addAttribute(	"allMenu", new JSONArray(list2));
		return "MIS/AddRole";
	}

	/**
	 * 
	 * @param model
	 * @param no
	 * @param privilegemodel
	 * @param rolemodel
	 * @return
	 */
	@RequestMapping("/editJurisdiction.do")
	public String Jurisdiction(Model model, MenuModel no, PrivilegeModel privilegemodel, RoleModel rolemodel) {
		rolemodel.setRole_code(privilegemodel.getRole_code());
		List<PrivilegeModel> plist = privilegeService.selectList(privilegemodel);
		privilegemodel.setParent_code("0");
		List<PrivilegeModel> plist1 = privilegeService.selectList(privilegemodel);
		model.addAttribute("role_menu_code", plist);
		model.addAttribute("role_menu", plist1);
		model.addAttribute("rolename", roleService.selectModel(rolemodel));
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
		JSONArray jsonArray = new JSONArray(list2);
		model.addAttribute("editJurisdiction", new JSONArray(list2));
		return "MIS/EditJurisdiction";
	}
	
	/**
	 * 
	 * @param model
	 * @param usermodel
	 * @param rolemodel
	 * @return
	 */
	@RequestMapping("/editrole.do")
	public String editrole(Model model, UserModel usermodel, RoleModel rolemodel) {
		model.addAttribute("role", roleService.selectList(rolemodel));
		model.addAttribute("user", userService.selectModel(usermodel));
		return "MIS/EditRole";
	}
	
	/**
	 * 
	 * @param model
	 * @param mm
	 * @return
	 */
	@RequestMapping("/addmenu.do")
	public String addmenu(Model model, MenuModel mm) {
		List<MenuModel> list = service.select(new MenuModel());
		model.addAttribute("addmenu", list);
		model.addAttribute("menucode", list);
		return "MIS/AddMenu";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/adduser.do")
	public String add(Model model) {
		model.addAttribute("Role", roleService.selectList(new RoleModel()));
		model.addAttribute("classlist", classService.selectList(new ClassModel()));
		return "MIS/AddUser";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/addproduct.do")
	public String addproduct() {
		return "business_data/AddProduct";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/customer_info.do")
	public String customer_info() {
		return "business_data/customer_info";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/addcustomer.do")
	public String addcustomer() {
		return "business_data/AddCustomer";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/order_info.do")
	public String order_info() {
		return "business_data/order_info";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/product_info.do")
	public String product_info() {
		return "business_data/product_info";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/customer_communication_info.do")
	public String customer_communication_info() {
		return "business_data/customer_communication_info";
	}
}
