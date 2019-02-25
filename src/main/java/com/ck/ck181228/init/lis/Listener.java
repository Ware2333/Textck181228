package com.ck.ck181228.init.lis;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ck.ck181228.init.MD5.MD5;
import com.ck.ck181228.init.ModelUtil.UserModelUtil;
import com.ck.ck181228.init.isempty.IsEmpty;
import com.ck.ck181228.user_management.mapper.UserMapper;
import com.ck.ck181228.user_management.model.UserModel;

/**
 * Application Lifecycle Listener implementation class Listener
 *
 */
@WebListener
public class Listener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public Listener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		@SuppressWarnings("unchecked")
		UserMapper<UserModel> userMapper = (UserMapper<UserModel>) webApplicationContext.getBean("userMapper");
		UserModel usermodel = UserModelUtil.usermodel("admin", "admin", MD5.mmd("admin"), "admin", "0", "0", "0", "normal");
		UserModel no = UserModelUtil.usermodel(usermodel.getUser_code(), "", usermodel.getUser_pass(), "", "", "", "", "");
		if (!IsEmpty.lis(userMapper.selectList(no))) {
			userMapper.insert(usermodel);
		}
	}
}
