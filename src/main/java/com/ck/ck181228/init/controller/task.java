package com.ck.ck181228.init.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.ck.ck181228.init.CacheKit;
import com.ck.ck181228.teacher_student.model.taskInfoModel;
import com.ck.ck181228.teacher_student.model.teacherStudentModel;
import com.ck.ck181228.teacher_student.service.taskInfoService;
import com.ck.ck181228.teacher_student.service.teacherStudentService;
import com.ck.ck181228.user_management.model.UserModel;
import com.ck.ck181228.user_management.service.UserService;

@Controller
@RequestMapping("/taskupload")
public class task {
	private final String PATH = "D:/task/";
	private final File file = new File("D:/usertask/");
	
	private CacheKit cac = new CacheKit();

	@Autowired
	private taskInfoService service;

	@Autowired
	private UserService userservice;

	@Autowired
	private teacherStudentService taskservice;

	/**
	 * 
	 * @param commodity_code
	 * @param request
	 * @param session
	 * @param name
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/upload/{id}")
	public String upload(String commodity_code, HttpServletRequest request, HttpSession session,@PathVariable("id")Integer id)
			throws IllegalStateException, IOException {
		SimpleDateFormat a=new SimpleDateFormat("yyyyMMddHHmmss");
		Date date=new Date();
		File f = new File(PATH);// windows系统是双右斜线
		if (!f.exists()) {// 判断文件夹是否存在
			f.mkdirs();// 创建文件夹
		}
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator<String> iter = multiRequest.getFileNames();
			// 一次遍历所有文件
			while (iter.hasNext()) {
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				if (file != null) {
					String filename = file.getOriginalFilename();
					String saveName = "";
					UserModel user = cac.getByCacheModel(request);
					if(!user.getParent_code().equals("3")) {
						saveName = "作业"+ a.format(date) + filename.substring(filename.lastIndexOf("."));
					}else {
						saveName = user.getUser_name() + a.format(date) + filename.substring(filename.lastIndexOf("."));
					}
					String path = PATH + saveName;
					file.transferTo(new File(path));// 上传
					CacheKit cachekit = new CacheKit();
					taskInfoModel taskinfomodel = new taskInfoModel();
					taskinfomodel.setTaskUrl(saveName);
					taskinfomodel.setOperationalPublisher(cachekit.getByCacheModel(request).getUser_code());
					taskinfomodel.setTaskCode(saveName);
					taskinfomodel.setTaskName("作业"+ a.format(date));
					service.insert(taskinfomodel);
					if(!user.getParent_code().equals("3")) {
						UserModel model = new UserModel();
//	                    model.setUser_code(cachekit.getByCacheModel(request).getUser_code());
						model.setParent_code(cachekit.getByCacheModel(request).getParent_code());
						model.setClassName(cachekit.getByCacheModel(request).getClassName());
						List<UserModel> list = userservice.selectList(model);
						List<teacherStudentModel> tslist = new ArrayList<teacherStudentModel>();
						for (UserModel ss : list) {
							teacherStudentModel tsmodel = new teacherStudentModel();
							tsmodel.setTaskCode(saveName);
							tsmodel.setTaskTeacher(cachekit.getByCacheModel(request).getUser_code());
							tsmodel.setUserCode(ss.getUser_code());
							tsmodel.setStatus("2");
							tslist.add(tsmodel);
						}
						taskservice.insertts(tslist);
					}else {
						teacherStudentModel tsmodel = new teacherStudentModel();
						tsmodel.setId(id);
						tsmodel.setTaskStudent(saveName);
						tsmodel.setStatus("1");
						taskservice.update(tsmodel);
					}
				}
			}
		}
		Map<String, String> result = new HashMap<>();
		result.put("code", "0");
		return new JSONObject(result).toString();
	}

	@RequestMapping("/download")
	@ResponseBody
	public void download(String filename,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        //模拟文件，myfile.txt为需要下载的文件  
//        String path = request.getSession().getServletContext().getRealPath("")+"\\"+filename;  
        //获取输入流  
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(PATH+filename)));
        //转码，免得文件名中文乱码  
        filename = URLEncoder.encode(filename,"UTF-8");  
        //设置文件下载头  
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
        response.setContentType("multipart/form-data");   
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }  
        out.close();  
    }
}
