<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/web/header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="/Textck181228/web/common/eleTree/eleTree/eleTree.css">
<script type="text/javascript"
	src="/Textck181228/web/common/eleTree/layui/layui.js"></script>
<script type="text/javascript"
	src="/Textck181228/web/common/eleTree/layui/lay/mymodules/eleTree.js"></script>
</head>
<body style="background: white;">
	<form class="layui-form" onsubmit="return false"
		style="height: 100%; padding: 20px; margin: 20px;">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">角色名称</label>
				<div class="layui-input-inline">
					<input lay-verify="required" id="role_name" type="text"
						name="email" lay-verify="text" placeholder="请输入角色名称"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">角色代码</label>
				<div class="layui-input-inline">
					<input lay-verify="required" id="role_code" type="text"
						name="email" lay-verify="text" placeholder="请输入角色代码"
						autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">审核状态</label>
			<div class="layui-input-inline">
				<input lay-verify="required" id="role_To_examine" type="text"
					name="role_To_examine" lay-verify="text" readonly="readonly"
					autocomplete="off" class="layui-input" value="未拥有权限">
			</div>                                                                                                                                                                                                                                                                                                                                                                                                                  
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">角色描述</label>
			<div class="layui-input-block">
				<textarea lay-verify="required" id="role_note" name="desc"
					placeholder="请输入内容" class="layui-textarea" style="width: 515px;"></textarea>
			</div>
		</div>
		<!-- 				<div class="eleTree ele1" lay-filter="ele"></div> -->
		<!-- 		<div class="layui-form-item"> -->
		<!-- 				<label class="layui-form-label">权限</label> -->
		<!-- 			</div> -->
		<div class="layui-form-item" style="float: right; padding-right: 60px">
			<div class="layui-input-block">
				<input readonly="readonly" type="submit" class="layui-btn"
					lay-submit lay-filter="demo1" style="background-color: #1E9FFF;"
					value="立即提交"></input> <input type="reset"
					class="layui-btn layui-btn-primary" value="重置"></input>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		form.on('submit(demo1)', function(data) {
			var role_To_examine = "";
			if ($("input[id='role_To_examine']").prop("checked")) {
				role_To_examine = "已拥有权限";
			} else {
				role_To_examine = "未拥有权限";
			}
			ajax("/roleController/insert.do", {
				role_code : $("#role_code").val(),
				role_name : $("#role_name").val(),
				role_note : $("#role_note").val(),
				role_To_examine : role_To_examine
			}, "text", function(data) {
				if (data == "success") {
					layer.msg("添加成功", {
						icon : 6,
						time : 1500
					}, function() {
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭   
					});
				} else if (data == "Already exist") {
					layer.msg("编号已存在")
				} else if (data == "err") {
					layer.msg("添加失败")
				}
			});
		});
	</script>
</body>
</html>
