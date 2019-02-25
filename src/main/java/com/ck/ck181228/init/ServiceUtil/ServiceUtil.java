package com.ck.ck181228.init.ServiceUtil;

import java.util.List;

import com.ck.ck181228.init.MapperUtil.Mapper;
import com.ck.ck181228.init.page.Page;

public abstract class ServiceUtil<T extends Page> {
	public abstract Mapper<T> getmapper();

	public String insert(T model) {
		return getmapper().insert(model) > 0 ? "success" : "err";
	}

	public String delete(Object id) {
		return getmapper().delete(id) > 0 ? "success" : "err";
	}

	public String deleteModel(T model) {
		return getmapper().deleteModel(model) > 0 ? "success" : "err";
	}

	public String update(T model) {
		return getmapper().update(model) > 0 ? "success" : "err";
	}

	public String updateModel(T model) {
		return getmapper().updateModel(model) > 0 ? "success" : "err";
	}

	public List<T> select(T model) {
		return getmapper().select(model);
	}

	public List<T> selectList(T model) {
		return getmapper().selectList(model);
	}

	public T selectModel(T model) {
		return getmapper().selectModel(model);
	}

	public int selectCount(T model) {
		return getmapper().selectCount(model);
	}
}
