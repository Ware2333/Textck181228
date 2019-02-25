package com.ck.ck181228.user_management.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.ck.ck181228.init.MD5.MD5;
import com.ck.ck181228.init.ModelUtil.UserModelUtil;
import com.ck.ck181228.init.ServiceUtil.ServiceUtil;
import com.ck.ck181228.init.isempty.IsEmpty;
import com.ck.ck181228.init.mail.Mail;
import com.ck.ck181228.user_management.mapper.UserMapper;
import com.ck.ck181228.user_management.model.UserModel;

@Service("userService")
public class UserService extends ServiceUtil<UserModel> {

	private static Random r = new Random();
	private static char[] chs = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	@Autowired
	private UserMapper<UserModel> mapper;

	@Override
	public UserMapper<UserModel> getmapper() {
		return mapper;
	}

	//判断用户账户密码是否正确,用户状态是否被冻结,是否被记入黑名单,用户密码错误次数,达到上线之后修改状态为冻结
	public String login(UserModel model, HttpSession session) {
		model.setUser_pass(MD5.mmd(model.getUser_pass()));
		UserModel no = new UserModel();
		no.setUser_code(model.getUser_code());
		List<UserModel> list = getmapper().selectList(model);
		Map<String, String> map = new HashMap<>();
		if (!IsEmpty.lis(list)) {
			List<UserModel> n = getmapper().selectList(no);
			if (!IsEmpty.lis(n)) {
				map.put("user", "reg");
			} else if (Integer.parseInt(n.get(0).getError_times()) == 5) {
				map.put("user", "err");
				map.put("state", "Frozen");
			} else {
				no.setError_times(String.valueOf(Integer.parseInt(n.get(0).getError_times()) + 1));
				getmapper().updateModel(no);
				map.put("user", "err");
				if (5 - Integer.parseInt(no.getError_times()) > 0) {
					map.put("state", String.valueOf(5 - Integer.parseInt(no.getError_times())));
				} else {
					map.put("state", "Frozen");
					no.setState("Frozen");
					getmapper().updateModel(no);
				}
			}
		} else {
			if (list.get(0).getBlacklist().equals("1")) {
				map.put("user", "Blacklist");
			} else if (list.get(0).getState().equals("Frozen")) {
				map.put("user", "Frozen");
			} else {
				session.setAttribute("user", list.get(0).getUser_name());
				session.setAttribute("code", list.get(0).getUser_code());
				session.setAttribute("role_code", list.get(0).getRole_code());
				map.put("user", "success");
			}
		}
		return new JSONObject(map).toString();
	}

	//发送账户解冻邮件,编辑邮件格式内容
	public String fmail(String[] mail, HttpSession session, String user_code) throws MessagingException {
		StringBuffer num = new StringBuffer();
		int index;
		for (int i = 0; i < 5; i++) {
			index = r.nextInt(chs.length);
			num.append(chs[index]);
		}
		Mail.sendmail(mail,
				"<div style='border-radius: 1%;margin: 0 auto;position: relative;border: 1px solid;width: 450px; height: 600px;background-color: #0f4769;'><div style='padding-top:32px;'><span style='color: #66c0f4;font-size:24px;padding-top: 16px;line-height: 170%;padding: 30px;'>Your account number "
						+ user_code
						+ "</span><br /><span style='color: #c6d4df;font-size: 17px;line-height: 170%;padding-top: 16px;padding: 30px;'>Here is the user Guard code you need to thaw to account "
						+ user_code + ":</span>"
						+ "<h1 style='color: #66c0f4;font-size: 24px;line-height: 170%;padding: 30px;'>"
						+ String.valueOf(num) + "</h1></div></div>",
				"Unfreeze Verification Code");
		session.setAttribute("autonum", String.valueOf(num));
		return String.valueOf(num);
	}

	//发送账户密码重置邮件,编辑邮件内容
	public String mail(String[] mail) throws MessagingException {
		Mail.sendmail(mail, "admin", "账号密码重置");
		UserModel model = UserModelUtil.usermodel(mail[0], "", MD5.mmd("admin"), "", "", "", "", "");
		return getmapper().updateModel(model) > 0 ? "success" : "err";
	}

	//判断验证码知否一致,一致即解冻账户
	public String Thaw(String user_code, String autonum, HttpSession session) {
		if (autonum.equals(session.getAttribute("autonum"))) {
			UserModel model = UserModelUtil.usermodel(user_code, "", "", "", "", "0", "", "normal");
			return getmapper().updateModel(model) > 0 ? "success" : "err";
		} else {
			return "err";
		}
	}

	//删除用户
	public String deleteUser(UserModel model) {
		return getmapper().delete(model) > 0 ? "success" : "err";
	}

	//重写修改方法,判断是否最高级管理员,最高级管理员不可删除
	@Override
	public String updateModel(UserModel model) {
		if (model.getUser_code().equals("admin")) {
			return "admin";
		} else {
			return getmapper().updateModel(model) > 0 ? "success" : "err";
		}
	}

	//判断查询条件,返回结果内容以及结果条目
	public String selectUserModel(UserModel model) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (IsEmpty.str(model.getUser_code())) {
			model.setUser_code("%" + model.getUser_code() + "%");
		}
		if (IsEmpty.str(model.getUser_name())) {
			model.setUser_name("%" + model.getUser_name() + "%");
		}
		map.put("user", getmapper().selectList(model));
		map.put("count", getmapper().selectCount(model));
		return new JSONObject(map).toString();
	}

	//重写添加方法,判断用户账户是否已存在
	@Override
	public String insert(UserModel model) {
		UserModel um = new UserModel();
		um.setUser_code(model.getUser_code());
		List<UserModel> list = getmapper().selectList(um);
		if (!IsEmpty.lis(list)) {
			model.setUser_pass("123456");
			model.setError_times("0");
			model.setBlacklist("0");
			model.setState("normal");
			model.setUser_pass(MD5.mmd(model.getUser_pass()));
			return getmapper().insert(model) > 0 ? "success" : "err";
		} else {
			return "Already exist";
		}
	}

	//读取文件,回显到页面
	private int[] parse(InputStream fis) throws IOException {
		// 由输入流得到工作簿
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		// 得到工作表
		XSSFSheet sheet = workbook.getSheet("user");
//        List<UserModel> list = new ArrayList<>();
		int i = 0;
		int a = 0;
		int[] b = new int[2];
		for (Row row : sheet) {
			++a;
			if (0 == row.getRowNum()) {
				continue;
			}
			UserModel userModel = new UserModel();
			userModel.setUser_code(getValue(row.getCell(0)));
			userModel.setUser_name(getValue(row.getCell(1)));
			userModel.setUser_pass("admin");
			userModel.setRole_code(getValue(row.getCell(2)));
			userModel.setError_times("0");
			userModel.setBlacklist("0");
			userModel.setState("0");
			i += insert(userModel) == "success" ? 1 : 0;
		}
		workbook.close();
		fis.close();
		b[0] = i;
		b[1] = a - i - 1;
		return b;
	}

	//获取文件行内容
	private String getValue(Cell cell) {
		CellType type = cell.getCellTypeEnum();
		if (CellType.STRING.equals(type)) {
			return cell.getStringCellValue();
		} else if (CellType.NUMERIC.equals(type)) {
			return String.valueOf(cell.getNumericCellValue());
		}
		return null;
	}

	//上传文件,获取添加成功以及添加失败数目
	public String uploadExcel(CommonsMultipartResolver multipartResolver, HttpServletRequest request) throws Exception {
		Map<String, Object> result = new HashMap<>();
		result.put("code", "0");
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				result.put("count", parse(file.getInputStream())[0]);
				result.put("ncount", parse(file.getInputStream())[1]);
			}
		}
		return new JSONObject(result).toString();
	}

	//编辑表格样式
	public void excel(HttpServletResponse response) throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("user");
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell1 = row.createCell(0);
		cell1.setCellValue("账号");
		XSSFCell cell2 = row.createCell(1);
		cell2.setCellValue("姓名");
		XSSFCell cell3 = row.createCell(2);
		cell3.setCellValue("角色");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;filename=User.xlsx");
		OutputStream out = response.getOutputStream();
		workbook.write(out);
		workbook.close();
		out.close();
	}
	
	/**
	 * @return
	 */
	public List<Map<String, Object>> selectchart(){
		return getmapper().selectmap();
	}
}
