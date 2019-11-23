<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>wopop</title>
		<%@include file="/WEB-INF/web/header.jsp"%>
		<style type="text/css">
			.fo {
				display: none;
			}

			.bor {
				border: 1px solid red;
			}

			.bord {
				border: 1px solid #cad2db;
			}
		</style>
	</head>
	<body class="login" mycollectionplug="bind">
		<div class="login_m">
			<div class="login_logo">
				<img src="/Textck181228/web/common/Wopop_files/logo.png" width="196" height="46">
			</div>
			<div class="login_boder">
				<div class="login_padding " id="login_model">
					<h2>邮箱</h2>
					<label> <input type="text" id="text1" class="txt_input txt_input2 bord" onfocus="if(this.placeholder=='邮箱') this.placeholder=''"
						 onblur="if(this.placeholder=='') this.placeholder='邮箱'" placeholder="邮箱">
					</label>
					<span style="position: absolute; margin: 80px auto;">
						<font color="#FF0000" id="con" class="fo"></font>
					</span>
					<h2>密码</h2>
					<label> <input type="password" name="textfield2" id="text2" class="txt_input bord" onfocus="if (placeholder =='******'){placeholder =''}"
						 onblur="if (placeholder ==''){placeholder='******'}" placeholder="******">
					</label>
					<p class="forgot">
						<a id="iforget" href="javascript:forget();">忘记密码?</a>
					</p>
					<div class="rem_sub">
						<div class="rem_sub_l">
							<input type="checkbox" name="checkbox" id="save_me"> <label for="checkbox">记住我</label>
						</div>
						<label> <input type="submit" class="sub_button" name="button" id="button" value="登录" style="opacity: 0.7;">
						</label>
					</div>
				</div>
				<div id="Frozen" class="login_padding fo">
					<h2>邮箱</h2>
					<label>
						<button id="mailbtn" style="cursor:pointer;position: absolute;margin: 4px 230px; width: 70px;height: 30px;border: 1px solid;border-color: #c9c9c9; border-radius: 5%; ">获取验证码</button>
						<input type="text" id="mail1" class="txt_input txt_input2 bord" onfocus="if (placeholder =='请输入邮箱'){placeholder =''}"
						 onblur="if (placeholder ==''){placeholder='请输入邮箱'}" placeholder="请输入邮箱">
					</label>
					<h2>验证码</h2>
					<label> <input type="text" name="textfield3" id="mail2" class="txt_input bord" onfocus="if (placeholder =='请输入验证码'){placeholder =''}"
						 onblur="if (placeholder ==''){placeholder='请输入验证码'}" placeholder="请输入验证码">
					</label>
					<div class="rem_sub">
						<label> <input type="button" class="sub_buttons" name="button" id="sendmail" value="验证邮箱" style="opacity: 0.7;">
							<input type="button" class="sub_button" name="button" id="ret2" value="返回" style="opacity: 0.7;">
						</label>
					</div>
				</div>
				<div id="forget_model" class="login_padding fo">
					<h1>重置密码</h1>
					<br>
					<div class="forget_model_h2">（请在下面输入您注册的电子邮件，系统会自动重置用户密码并将其发送到用户注册的电子邮件地址。）</div>
					<label> <input type="text" id="pass1" class="txt_input txt_input2 bord" onfocus="if (placeholder =='请输入邮箱'){placeholder =''}"
						 onblur="if (placeholder ==''){placeholder='请输入邮箱'}" placeholder="请输入邮箱">
					</label>
					<div class="rem_sub">
						<div class="rem_sub_l"></div>
						<label> <input type="button" class="sub_buttons" name="button" id="email" value="验证邮箱" style="opacity: 0.7;">
							<input type="button" class="sub_button" name="button" id="ret3" value="返回" style="opacity: 0.7;">
						</label>
					</div>
				</div>
				<!--login_padding  Sign up end-->
			</div>
			<!--login_boder end-->
		</div>
		<!--login_m end-->
		<br>
		<br>
	</body>
	<script type="text/javascript">
		var a = 60;
		//忘记密码按钮
		function forget() {
			$("#login_model").addClass("fo");
			$("#forget_model").removeClass("fo");
			$("#Frozen").addClass("fo");
		}
		//返回按钮
		$("input[id^='ret']").click(function() {
			$("#forget_model").addClass("fo");
			$("#login_model").removeClass("fo");
			$("#Frozen").addClass("fo");
		})
		//登录按钮
		$("#button").click(function() {
			if ($("#text1").val().length == 0 && $("#text2").val().length == 0) {
				re();
				$("#con").html("请输入账户和密码");
			} else if ($("#text1").val().length == 0) {
				ge();
				$("#con").html("请输入账户");
			} else if ($("#text2").val().length == 0) {
				$("#text2").removeClass("bord");
				$("#text2").addClass("bor");
				$("#con").removeClass("fo");
				$("#con").html("请输入密码");
			} else {
				ajax("/User/loginemail.do", {
					user_code: $("#text1").val(),
					user_pass: $("#text2").val()
				}, "json", function(data) {
					if (data.user == "err") {
						re();
						if (data.state == "Frozen") {
							re();
							$("#con").html("密码错误次数已达上限,请<a style='cursor: pointer;' href='javascript:Frozen()'>立即解冻</a>");
						} else {
							re();
							$("#con").html("请输入正确的密码,还可输入" + data.state + "次");
						}
					} else if (data.user == "Blacklist") {
						re();
						$("#con").html("您在黑名单中,请及时联系管理员");
					} else if (data.user == "Frozen") {
						re();
						$("#con").html("账户被冻结   <a style='cursor: pointer;' href='javascript:Frozen()'>现在解冻</a>");
					} else if (data.user == "reg") {
						re();
						$("#con").html("无此账号");
					}else if(data.user == "success"){
						location.href = "/Textck181228/jump/index"
					}
				});
			}
		})
		//账号密码框变红
		function re() {
			$("input[id^='text']").removeClass("bord");
			$("input[id^='text']").addClass("bor");
			$("#con").removeClass("fo");
		}
		//账号框变红
		function ge() {
			$("#text1").removeClass("bord");
			$("#text1").addClass("bor");
			$("#con").removeClass("fo");
		}
		//重置密码验证邮箱按钮
		$("#email").click(function() {
			ajax("/User/mail.do", {
				mail: $("#pass1").val()
			}, "text", function(data) {
				if (data == "success") {
					$("#ret3").click();
				} else if (data == "err") {

				}
			})
		})
		//发送验证邮箱按钮
		$("#mailbtn").click(function() {
			if ($("#mail1").val().length == 0) {
				$("#mail1").removeClass("bord")
				$("#mail1").addClass("bor")
			} else {
				$("#mailbtn").html(a);
				$("#mailbtn").attr("disabled", true);
				automail();
				b = setInterval(function() {
					if (--a == 0) {
						$("#mailbtn").attr("disabled", false);
						$("#mailbtn").html("获取验证码");
						clearInterval(b);
					} else {
						$("#mailbtn").html(a);
					}
				}, 1000);
			}
		})

		var VC = "";
		//发送验证邮箱验证码方法
		function automail() {
			ajax("/User/fmail.do", {
				mail: $("#mail1").val(),
				user_code:$("#text1").val()
			}, "text", function(data) {
				VC = data;
			});
		}
		//邮箱验证按钮
		$("#sendmail").click(function() {
			if ($("input[id^='mail']").val().length == 0) {
				$("input[id^='mail']").removeClass("bord");
				$("input[id^='mail']").addClass("bor");
			} else if ($("#mail1").val().length == 0) {
				$("#mail1").removeClass("bord");
				$("#mail1").addClass("bor");
			} else if ($("#mail2").val().length == 0) {
				$("#mail2").removeClass("bord");
				$("#mail2").addClass("bor");
			} else {
				ajax("/User/Thaw.do", {
					"user_code": $("#text1").val(),
					"autonum": $("#mail2").val()
				}, "text", function(data) {
					if (data == "success") {
						$("input[id^='text']").removeClass("bor");
						$("input[id^='text']").addClass("bord");
						$("#con").addClass("fo");
						layer.msg("账户已解冻");
						$("#ret2").click();
					} else if (data == "err") {
						$("#con").removeClass("fo");
						$("#con").html("邮箱或验证码错误");
					}
				});
			}
		});
		//显示冻结层
		function Frozen() {
			$("#Frozen").removeClass("fo");
			$("#login_model").addClass("fo");
			$("#forget_model").addClass("fo");
		}
		//显示登录层
		$("#ret").click(function() {
			$("#Frozen").addClass("fo");
			$("#forget_model").addClass("fo");
			$("#login_model").removeClass("fo");
		})
	</script>
</html>
