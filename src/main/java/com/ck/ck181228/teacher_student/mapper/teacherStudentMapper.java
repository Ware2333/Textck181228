package com.ck.ck181228.teacher_student.mapper;

import java.util.List;

import com.ck.ck181228.init.MapperUtil.Mapper;

public interface teacherStudentMapper<T> extends Mapper<T> {
	int insertts (List<T> list);
}
