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

import com.ck.ck181228.init.CacheKit;
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

	/**
	 * 
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	public String login(HttpServletRequest request,UserModel model,HttpSession session) {
		model.setUser_pass(MD5.mmd(model.getUser_pass()));
		UserModel user = new UserModel();
		CacheKit cachekit = new CacheKit();
		Map<String, String> map = new HashMap<>();
		if (cachekit.getByCache(request) != null && cachekit.getByCacheModel(request).getUser_code().equals(model.getUser_code())) {
			map.put("user", "success");
			map.put("code", user.getUser_code());
			return new JSONObject(map).toString();
		} else {
			user = getmapper().selectModel(model);
			//判断用户账号密码是否正确
			if(user == null) {
				model.setUser_pass(null);
				user = getmapper().selectModel(model);
				//判断账号是否存在
				if (user != null) {
					//判断账户密码错误次数
					if (Integer.parseInt(user.getError_times()) == 5) {
						map.put("user", "err");
						map.put("state", "Frozen");
					} else {
						//设置账户密码错误次数加一
						model.setError_times(String.valueOf(Integer.parseInt(user.getError_times()) + 1));
						getmapper().updateModel(model);
						map.put("user", "err");
						//判断用户密码输错次数是否达到5次 ，冻结账户
						if (5 - Integer.parseInt(user.getError_times()) > 0) {
							map.put("state", String.valueOf(5 - Integer.parseInt(user.getError_times())));
						} else {
							//返回账户已冻结
							map.put("state", "Frozen");
							user.setState("Frozen");
							getmapper().updateModel(user);
						}
					}
				} else {
//					map.put("state", "Frozen");
					map.put("user", "reg");
				}
			}else {
				if (user.getBlacklist().equals("1")) {
					map.put("user", "Blacklist");
				} else if (user.getState().equals("Frozen")) {
					map.put("user", "Frozen");
				} else {
					cachekit.setByCache(request, user);
					map.put("user", "success");
					map.put("code", user.getUser_code());
				}
			}
		}
		return new JSONObject(map).toString();
	}

	/**
	 * 
	 * @param mail
	 * @param session
	 * @param user_code
	 * @return
	 * @throws MessagingException
	 */
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

	/**
	 * 
	 * @param mail
	 * @return
	 * @throws MessagingException
	 */
	public String mail(String[] mail) throws MessagingException {
		Mail.sendmail(mail, "admin", "账号密码重置");
		UserModel model = UserModelUtil.usermodel(mail[0], "", MD5.mmd("admin"), "", "", "", "", "");
		return getmapper().updateModel(model) > 0 ? "success" : "err";
	}

	/**
	 * 
	 * @param user_code
	 * @param autonum
	 * @param session
	 * @return
	 */
	public String Thaw(String user_code, String autonum, HttpSession session) {
		if (autonum.equals(session.getAttribute("autonum"))) {
			UserModel model = UserModelUtil.usermodel(user_code, "", "", "", "", "0", "", "normal");
			return getmapper().updateModel(model) > 0 ? "success" : "err";
		} else {
			return "err";
		}
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	public String deleteUser(UserModel model) {
		return getmapper().delete(model) > 0 ? "success" : "err";
	}

	/**
	 * 
	 */
	@Override
	public String updateModel(UserModel model) {
		UserModel usermodel = new UserModel();
		usermodel.setUser_code(model.getUser_code());
		if ("admin".equals(getmapper().selectModel(usermodel).getRole_code())) {
			return "admin";
		} else {
			return getmapper().updateModel(model) > 0 ? "success" : "err";
		}
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	public String selectUserModel(UserModel model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", getmapper().selectList(model));
		map.put("count", getmapper().selectCount(model));
		return new JSONObject(map).toString();
	}
	
	/**
	 * 
	 */
	@Override
	public String insert(UserModel model) {
		UserModel um = new UserModel();
		um.setUser_code(model.getUser_code());
		if(model.getRole_code() == "admin") {
			model.setClassName("");
		}
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

	/**
	 * 
	 * @param fis
	 * @return
	 * @throws IOException
	 */
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

	/**
	 * 
	 * @param cell
	 * @return
	 */
	private String getValue(Cell cell) {
		CellType type = cell.getCellTypeEnum();
		if (CellType.STRING.equals(type)) {
			return cell.getStringCellValue();
		} else if (CellType.NUMERIC.equals(type)) {
			return String.valueOf(cell.getNumericCellValue());
		}
		return null;
	}

	/**
	 * 
	 * @param multipartResolver
	 * @param request
	 * @return
	 * @throws Exception
	 */
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
	/**
	 * 
	 * @param response
	 * @throws Exception
	 */
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
	public List<Map<String, Object>> selectchart() {
		return getmapper().selectmap();
	}
}
