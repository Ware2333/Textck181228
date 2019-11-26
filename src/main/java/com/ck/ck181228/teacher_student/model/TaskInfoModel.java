package com.ck.ck181228.teacher_student.model;

import java.util.Date;

import com.ck.ck181228.init.page.Page;

import lombok.Data;

@Data
public class TaskInfoModel extends Page {
	private Integer id;
	//作业编号
	private String taskCode;
	//作业发布时间
	private Date taskTime;
	//作业名称
	private String taskName;
	//作业发布人
	private String OperationalPublisher;
	//作业地址
	private String taskUrl;
}
