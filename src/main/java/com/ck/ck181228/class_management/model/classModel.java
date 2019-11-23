package com.ck.ck181228.class_management.model;

import com.ck.ck181228.init.page.Page;

import lombok.Data;

@Data
public class classModel extends Page {
	private Integer id;
	//班级名称
	private String className;
}
