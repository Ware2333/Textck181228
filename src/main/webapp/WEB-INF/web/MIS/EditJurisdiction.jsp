<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/web/header.jsp"%>
<!-- <script type="text/javascript" src="/Textck181228/web/common/layui/layui.js"></script> -->
<link rel="stylesheet" type="text/css"
	href="/Textck181228/web/common/eleTree/eleTree/eleTree.css">
<script type="text/javascript"
	src="/Textck181228/web/common/eleTree/layui/layui.js"></script>
<script type="text/javascript"
	src="/Textck181228/web/common/eleTree/layui/lay/mymodules/eleTree.js"></script>
</head>
<body style="background: white;">
	<form class="layui-form" onsubmit="return false"
		style="height: 325px; padding: 20px; margin: 20px;">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">角色名称</label>
				<div class="layui-input-inline">
					<input readonly="readonly" id="role_name" type="text" name="email"
						lay-verify="text" placeholder="请输入角色名称" autocomplete="off"
						class="layui-input" value="${rolename.role_name}">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">角色代码</label>
				<div class="layui-input-inline">
					<input readonly="readonly" id="role_code" type="text" name="email"
						lay-verify="text" placeholder="请输入角色代码" autocomplete="off"
						class="layui-input" value="${rolename.role_code}">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">角色权限</label>
				<div class="eleTree ele1" lay-filter="ele"
					style="float: left; padding-top: 5px;"></div>
			</div>
		</div>
				<div class="layui-form-item" style="float: right; padding-right: 60px">
			<div class="layui-input-block">
				<input type="submit" class="layui-btn"
					lay-submit lay-filter="demo1" style="background-color: #1E9FFF;"
					value="立即提交"></input> 
			</div>
		</div>
	</form>
	<script type="text/javascript">
		var menu = [];
		var menu_code = [];
		var newmenu_code = [];
		var tree;
		$(function() {
			<c:forEach items="${role_menu}" var="t">
			console.log(1)
			menu.push('${t.menu_code}');
			</c:forEach>
			<c:forEach items="${role_menu_code}" var="t">
			menu_code.push('${t.menu_code}');
			</c:forEach>
			layui.config({base:"/Textck181228/web/common/eleTree/layui/lay/mymodules/"}).use('eleTree', function(){
				 eleTree = layui.eleTree;
				 tree =  eleTree.render({
				    elem: '.ele1',
				    data : ${editJurisdiction},
				    showCheckbox: true,
				    indent:30,
				    defaultCheckedKeys:menu,
				    defaultExpandAll:true,
				    highlightCurrent:true,
				    accordion:true
				});
			form.on("submit(demo1)", function(data) {
				$.each(tree.getChecked(false, true),function(i,dom){
					newmenu_code.push(dom.id);
				})
				for(var i = 0;i < newmenu_code.length;i++){
					for(var a = 0;a < menu_code.length;a++){
						if(newmenu_code[i]==menu_code[a]){
							newmenu_code.splice(i,1);
							menu_code.splice(a,1);
							i--;
							a--;
						}
					}
				}
				newmenu_code.splice(0,menu_code.length);
				ajax("/privilegeController/update.do", {role_code:$("#role_code").val(),menu_codearr:menu_code,newmenu_codearr:newmenu_code}, "text", function(data){
					if(data == "success"){
						layer.msg("修改成功", {
							icon : 6,
							time : 1500
						},
								function() {
									var index = parent.layer
											.getFrameIndex(window.name); //先得到当前iframe层的索引
									parent.layer.close(index); //再执行关闭   
									parent.location.reload();
								});
					}
				});
			});
		});
		});
// 		Array.prototype.indexOf = function(val) { 
// 			for (var i = 0; i < this.length; i++) { 
// 			if (this[i] == val) return i; 
// 			} 
// 			return -1; 
// 			};
// 		Array.prototype.remove = function(val) { 
// 				var index = this.indexOf(val); 
// 				if (index > -1) { 
// 				this.splice(index, 1); 
// 				} 
// 				};
	</script>
</body>
</html>
