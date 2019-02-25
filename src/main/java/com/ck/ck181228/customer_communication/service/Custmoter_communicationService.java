package com.ck.ck181228.customer_communication.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.ck181228.customer_communication.mapper.Customer_communicationMapper;
import com.ck.ck181228.customer_communication.model.Customer_communicationModel;
import com.ck.ck181228.init.ServiceUtil.ServiceUtil;
import com.ck.ck181228.init.isempty.IsEmpty;

@Service("custmoter_communicationService")
public class Custmoter_communicationService extends ServiceUtil<Customer_communicationModel> {
	@Autowired
	private Customer_communicationMapper<Customer_communicationModel> mapper;

	@Override
	public Customer_communicationMapper<Customer_communicationModel> getmapper() {
		return mapper;
	}

	/**
	 * 查询客户沟通信息的内容以及数目
	 */
	public String Customer_communication(Customer_communicationModel model) {
		if (IsEmpty.str(model.getCust_code())) {
			model.setCust_code("%" + model.getCust_code() + "%");
		}
		int count = getmapper().selectCount(model);
		int pages = count > 0 ? (count < 10 ? 1 : (count % 10 == 0 ? ((count / 10) + 1) : (count / 10))) : 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", getmapper().selectList(model));
		map.put("pages", pages);
		return new JSONObject(map).toString();
	}
}
