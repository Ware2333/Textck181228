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
<style type="text/css">
.asd {
	visibility: hidden;
}
</style>
</head>
<body>
	<form class="layui-form" onsubmit="return false"
		style="height: 325px; padding: 20px; margin: 20px; background: white;">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">菜单名称</label>
				<div class="layui-input-inline">
					<input lay-verify="required" id="menu_name" type="text"
						name="menu_name" lay-verify="text" placeholder="请输入菜单名称"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">上级菜单</label>
				<div class="layui-input-inline">
					<select name="parent_code" lay-filter="modules"
						lay-verify="parent_code" lay-search="" id="parent_code">
						<option value="">直接选择或搜索选择</option>
						<option value="0">无上级</option>
						<optgroup label="一级菜单">
							<c:forEach items="${addmenu}" var="menu">
								<c:if test="${menu.parent_code=='0'}">
									<option value="${menu.menu_code}">${menu.menu_name}</option>
								</c:if>
							</c:forEach>
						</optgroup>
						<c:forEach items="${addmenu}" var="menu">
							<c:if test="${menu.parent_code=='0'}">
								<optgroup label="${menu.menu_name}">
										<c:forEach items="${menucode}" var="menucode">
											<c:if test="${menu.menu_code==menucode.parent_code}">
												<option value="${menucode.menu_code}">${menucode.menu_name}</option>
											</c:if>
										</c:forEach>
								</optgroup>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>

		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">菜单编号</label>
				<div class="layui-input-inline">
					<input lay-verify="required" id="menu_code" type="text"
						name="menu_code" lay-verify="text" placeholder="请输入菜单编号"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline" name="menu">
				<label class="layui-form-label">菜单地址</label>
				<div class="layui-input-inline">
					<input id="menu_url" type="text" name="menu_url"
						placeholder="(/controller/*.do)" autocomplete="off"
						class="layui-input">
				</div>
			</div>
		</div>
<!-- 		<div class="layui-form-item"> -->
<!-- 			<div class="layui-inline"> -->
<!-- 				<label class="layui-form-label">级别</label> -->
<!-- 				<div class="layui-input-inline"> -->
<!-- 					<input id="level" type="text" name="level" lay-verify="text" -->
<!-- 						placeholder="请输入菜单级别" autocomplete="off" class="layui-input"> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
		<div class="layui-form-item"
			style="bottom: 30px; position: absolute; right: 100px;">
			<div class="layui-input-block">
				<input type="submit" class="layui-btn" lay-submit lay-filter="demo1"
					style="background-color: #1E9FFF;" value="确定"></input> <input
					type="reset" class="layui-btn layui-btn-primary" value="重置"></input>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		formSubmit("/MenuController/insert.do", 'submit(demo1)', "text",
				function(data) {
					if (data == "success") {
						layer.msg("添加成功", {
							icon : 6,
							time : 1500
						},
								function() {
									var index = parent.layer
											.getFrameIndex(window.name); //先得到当前iframe层的索引
									parent.layer.close(index); //再执行关闭   
									parent.location.reload();
								});
					} else if (data == "Already exist") {
						layer.msg("编号已存在")
					} else if (data == "err") {
						layer.msg("添加失败")
					}
				})
		form.verify({
			parent_code : function(value) {
				if (value == "") {
					return '请选择上级菜单';
				}
			}
		});
		form.render("select");
	</script>
</body>
</html>
