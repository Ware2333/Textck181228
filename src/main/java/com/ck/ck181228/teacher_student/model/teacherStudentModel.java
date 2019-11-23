package com.ck.ck181228.teacher_student.model;

import java.util.Date;

import com.ck.ck181228.init.page.Page;

import lombok.Data;

@Data
public class teacherStudentModel extends Page {
	private Integer id;
	/**
	 * 用户编号
	 */
	private String userCode;
	/**
	 * 上级
	 */
	private String parentCode;
	/**
	 * 
	 */
	private String className;
	/**
	 * 用户姓名
	 */
	private String userName;
	/**
	 * 发布老师
	 */
	private String taskTeacher;
	/**
	 * 评语
	 */
	private String info;
	/**
	 * 作业编号
	 */
	private String taskCode;
	/**
	 * 作业名称
	 */
	private String taskName;
	/**
	 * 作业状态
	 */
	private String status;
	/**
	 * 作业打分
	 */
	private int taskScoring;
	/**
	 * 打分老师
	 */
	private String gradingTeacher;
	/**
	 * 打分老师名称
	 */
	private String gradingTeacherName;
	/**
	 * 打分时间
	 */
	private Date gradTime;
	/**
	 * 发布老师名称
	 */
	private String taskTeacherName;
	
	/**
	 * 学生作业提交路径
	 */
	private String taskStudent;
}
