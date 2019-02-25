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
				<label class="layui-form-label">用户姓名</label>
				<div class="layui-input-inline">
					<input readonly="readonly" id="user_name" type="text" name="email"
						lay-verify="text" placeholder="请输入角色名称" autocomplete="off"
						class="layui-input" value="${user.user_name}">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">用户账号</label>
				<div class="layui-input-inline">
					<input readonly="readonly" id="user_code" type="text" name="user_code"
						lay-verify="text" placeholder="请输入角色代码" autocomplete="off"
						class="layui-input" value="${user.user_code}">
				</div>
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">角色</label>
			<div class="layui-input-inline">
				<select name="role_code" lay-verify="required" lay-search=""
					id="role_code">
					<option value="">直接选择或搜索选择</option>
					<c:forEach items="${role}" var="role">
						<option value="${role.role_code}">${role.role_name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="layui-form-item"
			style="bottom: 30px; position: absolute; right: 100px;">
			<div class="layui-input-block">
				<input type="submit" class="layui-btn" lay-submit lay-filter="demo1"
					style="background-color: #1E9FFF;" value="确认"></input> <input
					type="reset" class="layui-btn layui-btn-primary" value="重置"></input>
			</div>
		</div>
	</form>
	<script type="text/javascript">
	var role_code = '';
		$(function() {
			role_code = '${user.role_code}';
			$("#role_code").val('${user.role_code}');
			form.render("select");
		})
		form.on('submit(demo1)', function(data) {
			if(role_code!=$("#role_code").val()){
			ajax("/User/update.do", {
				user_code : $("#user_code").val(),
				role_code : $("#role_code").val(),
			}, "text", function(data) {
				if (data == "success") {
					layer.msg("修改成功", {icon:6, time:1500}, function(){
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭   
					});
				} else if (data == "err") {
					layer.msg("修改失败")
				}
			});	
			}
		});
	</script>
</body>
</html>
