package com.ck.ck181228.customer_info.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.ck181228.customer_communication.model.Customer_communicationModel;
import com.ck.ck181228.customer_communication.service.Custmoter_communicationService;
import com.ck.ck181228.customer_info.mapper.Customer_infoMapper;
import com.ck.ck181228.customer_info.model.Customer_infoModel;
import com.ck.ck181228.init.ServiceUtil.ServiceUtil;

@Service("customer_infoService")
public class Customer_infoService extends ServiceUtil<Customer_infoModel> {
	@Autowired
	private Customer_infoMapper<Customer_infoModel> mapper;
	@Autowired
	private Custmoter_communicationService service;

	@Override
	public Customer_infoMapper<Customer_infoModel> getmapper() {
		return mapper;
	}

	/**
	 * 查询客户信息内容以及数目
	 */
	public String selectcustomer(Customer_infoModel model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", getmapper().selectCount(model));
		map.put("data", getmapper().selectList(model));
		return new JSONObject(map).toString();
	}

	@Override
	public String deleteModel(Customer_infoModel model) {
		Customer_communicationModel cmodel = new Customer_communicationModel();
		cmodel.setCust_code(model.getCust_code());
		service.deleteModel(cmodel);
		return getmapper().deleteModel(model) > 0 ? "success" : "err";
	}
}
