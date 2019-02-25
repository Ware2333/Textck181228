package com.ck.ck181228.user_management.mapper;

import java.util.List;
import java.util.Map;

import com.ck.ck181228.init.MapperUtil.Mapper;

public interface UserMapper<T> extends Mapper<T> {

	List<Map<String, Object>> selectmap();

}
