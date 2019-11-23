<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/web/header.jsp"%>
<script type="text/javascript"
	src="/Textck181228/web/common/layui/layui.js"></script>
</head>
<body>
	<form class="layui-form" onsubmit="return false"
		style="height: 325px; padding: 20px; margin: 20px; background: white;">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">班级名称</label>
				<div class="layui-input-inline">
					<input id="username" type="text" lay-verify="required"
						name="className" lay-verify="text" placeholder="请输入班级名称"
						autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item"
			style="bottom: 30px; position: absolute; right: 100px;">
			<div class="layui-input-block">
				<input type="submit" class="layui-btn" lay-submit lay-filter="demo1"
					style="background-color: #1E9FFF;" value="立即提交"></input> <input
					type="reset" class="layui-btn layui-btn-primary" value="重置"></input>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		formSubmit("/controllerClass/addClass", 'submit(demo1)', "text", function(data) {
			if (data == "success") {
				layer.msg("添加成功", {
					icon : 6,
					time : 1500
				}, function() {
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index); //再执行关闭   
				});
			} else if (data == "Already exist") {
				layer.msg("班级已存在")
			} else if (data == "err") {
				layer.msg("添加失败")
			}
		});
		form.verify({
			role_code : function(value) {
				if (value == "") {
					return '请选择角色';
				}
			}
		});
		form.render();
	</script>
</body>
</html>
