package com.ck.ck181228.init.MapperUtil;

import java.util.List;

public interface Mapper<T> {
	int insert(T model);

	int delete(Object id);

	int deleteModel(T model);

	int update(T model);

	int updateModel(T model);

	List<T> select(T model);
	
	List<T>  selectList(T model);
	
	int selectCount(T model);
	
	T selectModel(T model);
}
