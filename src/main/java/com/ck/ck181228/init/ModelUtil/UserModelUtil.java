package com.ck.ck181228.init.ModelUtil;

import com.ck.ck181228.init.isempty.IsEmpty;
import com.ck.ck181228.user_management.model.UserModel;

public class UserModelUtil {
	public static UserModel usermodel(String user_code,String user_name,String user_pass,String role_code,String parent_code,String error_times,String blacklist,String state) {
		UserModel model = new UserModel();
		if(IsEmpty.str(user_code)) {
			model.setUser_code(user_code);
		}
		if(IsEmpty.str(user_name)) {
			model.setUser_name(user_name);
		}
		if(IsEmpty.str(user_pass)) {
			model.setUser_pass(user_pass);
		}
		if(IsEmpty.str(role_code)) {
			model.setRole_code(role_code);
		}
		if(IsEmpty.str(parent_code)) {
			model.setParent_code(parent_code);
		}
		if(IsEmpty.str(error_times)) {
			model.setError_times(error_times);
		}
		if(IsEmpty.str(blacklist)) {
			model.setBlacklist(blacklist);
		}
		if(IsEmpty.str(state)) {
			model.setState(state);
		}	
		return model;
	}
}
