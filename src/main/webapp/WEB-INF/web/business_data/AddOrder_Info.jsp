<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
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
		<input type="hidden" name="user_code" value="${code}">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">顾客姓名</label>
				<div class="layui-input-inline">
					<select name="cust_code" lay-filter="modules"
						lay-verify="cust_code" lay-search="" id="parent_code">
						<option value="">直接选择或搜索选择</option>
						<c:forEach items="${cust}" var="cust">
							<option value="${cust.cust_code}">${cust.cust_name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">商品名称</label>
				<div class="layui-input-inline">
					<select name="prod_code" lay-filter="modules"
						lay-verify="prod_code" lay-search="" id="parent_code">
						<option value="">直接选择或搜索选择</option>
						<c:forEach items="${produ}" var="produ">
							<option value="${produ.code}">${produ.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">数量</label>
				<div class="layui-input-inline">
					<input id="status" type="text" lay-verify="required" name="count"
						lay-verify="text" placeholder="请输入数量" autocomplete="off"
						class="layui-input">
				</div>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">顾客状态</label>
			<div class="layui-input-inline">
				<input id="status" type="text" lay-verify="required" name="status"
					lay-verify="text" placeholder="请输入状态" autocomplete="off"
					class="layui-input">
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
		formSubmit("/order_Controller/insert.do", 'submit(demo1)', "text",
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
								});
					} else if (data == "err") {
						layer.msg("添加失败", {
							icon : 6,
							time : 1500
						},
								function() {
									var index = parent.layer
											.getFrameIndex(window.name); //先得到当前iframe层的索引
									parent.layer.close(index); //再执行关闭   
								});
					}
				});
		form.verify({
			cust_code : function(value) {
				if (value == "") {
					return '请选择顾客';
				}
			},
			prod_code : function(value) {
				if (value == "") {
					return '请选择商品';
				}
			}
		});
		form.render("select");
		form.render("checkbox");
	</script>
</body>
</html>
