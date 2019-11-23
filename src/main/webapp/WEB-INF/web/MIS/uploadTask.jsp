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
	<div class="layui-form" onsubmit="return false"
		style="height: 325px; padding: 20px; margin: 20px; background: white;">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">用户名</label>
				<div class="layui-input-inline">
					<input id="userName" type="text" lay-verify="userName"
						name="userName" lay-verify="text" placeholder="请输入用户名"
						autocomplete="off" class="layui-input" value="${userName}">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">作业</label>
				<div class="layui-input-inline">
					<input id="taskCode" type="text" name="taskCode"
						value="${taskCode}" lay-verify="email" placeholder=""
						autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item"
			style='<c:if test="${user == 3}">display:none</c:if>'>
			<div class="layui-inline">
				<label class="layui-form-label">评分</label>
				<div class="layui-input-inline">
					<input id="taskScoring" type="text" name="taskScoring"
						value="${taskUser.taskScoring}" lay-verify="email" placeholder=""
						autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item"
			style='<c:if test="${user == 3}">display:none</c:if>'>
			<div class="layui-inline">
				<label class="layui-form-label">评语</label>
				<div class="layui-input-inline">
					<input id="info" type="text" name="info"
						value="${taskUser.info}" lay-verify="email" placeholder=""
						autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item"
			style="bottom: 30px; position: absolute; right: 100px;">
			<div class="layui-input-block">
				<c:if test="${user == 3}">
					<input id="upload" type="button" class="layui-btn"
						style="background-color: #1E9FFF;" value="上传"></input>
				</c:if>
				<c:if test="${user != 3}">
					<input id="update" type="button" class="layui-btn"
						style="background-color: #1E9FFF;" value="修改"></input>
				</c:if>
				<input type="reset" class="layui-btn layui-btn-primary" value="重置"></input>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		upload.render({
			elem : '#upload' //绑定元素
			,
			url : '/Textck181228/taskupload/upload/${id}' //上传接口
			,
			accept : 'file',
			data : {
				userName : $("#userName").val(),
				taskCode : $("#taskCode").val()
			},
			done : function(res) {
				layer.msg("已上传");
				// 			init("", "");
			},
			error : function() {
				layer.msg("请检查上传文件格式是否正确")
			}
		});
		$("#update").click(function(){
			ajax("/teacherStudentController/update/${id}", {taskScoring:$("#taskScoring").val(),info:$("#info").val()}, "text", function(data){
				console.log(data)
				if(data == "success"){
					layer.msg("修改成功");
				}
			});
		})
// 		formSubmit("/User/insert.do", 'submit(demo1)', "text", function(data) {
// 			if (data == "success") {
// 				layer.msg("添加成功", {
// 					icon : 6,
// 					time : 1500
// 				}, function() {
// 					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
// 					parent.layer.close(index); //再执行关闭   
// 				});
// 			} else if (data == "Already exist") {
// 				layer.msg("编号已存在")
// 			} else if (data == "err") {
// 				layer.msg("添加失败")
// 			}
// 		});
// 		form.verify({
// 			role_code : function(value) {
// 				if (value == "") {
// 					return '请选择角色';
// 				}
// 			},
// 			parent_code : function(value) {
// 				if (value == '') {
// 					return '请选择等级';
// 				}
// 			}
// 		});
// 		form.on('select(role_code)', function(data) {
// 			if (data.value == 'admin') {
// 				$("#parent_code").val(1);
// 				$("#classlist").css("display", "none");
// 				$("#demo").attr("checked", true);
// 				form.render("redio");
// 			} else if (data.value == "") {
// 				$("#classlist").css("display", "block");
// 			}
// 		})
// 		form.render();
	</script>
</body>
</html>
