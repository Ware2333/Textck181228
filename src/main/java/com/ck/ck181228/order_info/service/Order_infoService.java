package com.ck.ck181228.order_info.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.ck181228.init.ServiceUtil.ServiceUtil;
import com.ck.ck181228.init.isempty.IsEmpty;
import com.ck.ck181228.order_info.mapper.Order_infoMapper;
import com.ck.ck181228.order_info.model.Order_infoModel;

@Service("order_infoService")
public class Order_infoService extends ServiceUtil<Order_infoModel> {
	@Autowired
	private Order_infoMapper<Order_infoModel> mapper;

	@Override
	public Order_infoMapper<Order_infoModel> getmapper() {
		return mapper;
	}

	public String selectdata(Order_infoModel model) {
		if (IsEmpty.str(model.getUser_code())) {
			model.setUser_code("%" + model.getUser_code() + "%");
		}
		if (IsEmpty.str(model.getCust_code())) {
			model.setCust_code("%" + model.getCust_code() + "%");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("order", getmapper().selectList(model));
		map.put("count", getmapper().selectCount(model));
		return new JSONObject(map).toString();
	}
}
