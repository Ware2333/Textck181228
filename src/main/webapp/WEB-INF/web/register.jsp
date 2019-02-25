<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<%@include file="/WEB-INF/web/header.jsp"%>
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/index.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/prism.css">
<script type="text/javascript" src="<%=path%>/js/quietflow.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/index.js"></script>
<script type="text/javascript" src="<%=path%>/js/prism.js"></script>
	</head>
	<body style="background-color: #009f95;">
		<fieldset class="layui-elem-field" style="margin: 110px auto; padding-top: 10px; padding-bottom: 10px; width: 390px; opacity: 90%;">
			<legend>注册</legend>
			<div class="from-user">
				<form class="layui-form" onsubmit="return false">
					<input type="hidden" name="action" value="register">
					<div class="layui-form-item">
						<label class="layui-form-label">姓名</label>
						<div class="layui-input-inline">
							<input type="text" name="username" placeholder="请输入姓名" id="username" autocomplete="off" class="layui-input"
							 lay-verify="required" style="background-color: transparent;">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">密码</label>
						<div class="layui-input-inline">
							<input type="password" name="password1" maxlength="12" id="password1" lay-verify="pass" placeholder="请输入密码"
							 autocomplete="off" class="layui-input" lay-verify="required" style="background-color: transparent;">
						</div>
						<!--     <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div> -->
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">确认密码</label>
						<div class="layui-input-inline">
							<input type="password" name="password" maxlength="12" id="password2" lay-verify="pass" placeholder="请确认密码"
							 autocomplete="off" class="layui-input" lay-verify="required" style="background-color: transparent;">
						</div>
						<!--     <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div> -->
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">分组选择框</label>
							<div class="layui-input-inline">
								<select name="userque">
									<option value="">请选择问题</option>
									<optgroup label="城市记忆">
										<option value="你工作的第一个城市">你工作的第一个城市</option>
										<option value="你的第一个工作">你的第一个工作</option>
									</optgroup>
									<optgroup label="学生时代">
										<option value="你的学号">你的学号</option>
										<option value="你最喜欢的老师">你最喜欢的老师</option>
									</optgroup>
									<optgroup label="青春时代">
										<option value="你的LOL账号">你的LOL账号</option>
										<option value="你的LOL密码">你的LOL密码</option>
									</optgroup>
								</select>
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">答案</label>
						<div class="layui-input-inline">
							<input type="text" name="userans" placeholder="请输入答案" id="userans" autocomplete="off" class="layui-input"
							 lay-verify="required" style="background-color: transparent;">
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<input type="submit" class="layui-btn" lay-submit lay-filter="formDemo" value="注册"> <input type="button" class="layui-btn layui-btn-primary"
							 value="返回" id="returnlogin">
						</div>
					</div>
				</form>
			</div>
		</fieldset>
	</body>
	<script type="text/javascript">
		$("#returnlogin").click(function() {
			window.history.go(-1);
// 			location.href = con.net+"/web/shop/index.jsp";
		});
		form.on('submit(formDemo)', function(data) {
			if (/[\u4e00-\u9fa5]/.test($("#username").val())) {
				if ($("#password1").val() == $("#password2").val()) {//if($("input[name='password2']").val().length>=6)
					if($("input[name='password']").val().length>=6){
					$.ajax({
						url: con.net + "/User/register.do",
						data: data.field,
						dataType: "json",
						type: "post",
// 						contentType:"application/json",
						success: function(data) {
							if (data.mes == "success"&&data.code!="0") {
								alert("您的账号为:" + data.code + ",请牢记");
								window.location.href = con.net+"/web/login.jsp";
							} else if (data.mes == "loginHaFailed"&&data.code=="0") {
								layer.alert('注册失败');
							} else if (data.mes == "repeat"&&data.code=="0") {
								layer.alert('账号已存在');
							}
						}
					});
					}else{
						layer.alert("请输入至少6位数密码");
					}
				} else {
					layer.alert('两次密码输入不一致');
				}	
			} else {
				layer.alert('请输入正确格式');
				return;
			}
		});
		form.render("select")
		
		//location.back();
	</script>
</html>
