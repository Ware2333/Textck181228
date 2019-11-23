<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/web/header.jsp"%>
</head>
<body>
	<table id="demo2" lay-filter="test" lay-data="{id: 'idTest'}"></table>
	<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container">
    <button class="layui-btn layui-btn-normal" lay-event="add">添加班级</button>
    <div class="layui-inline"><div class="layui-input-inline"><input class = "layui-input layui-input-inline" type="text" id = "task_name" placeholder = "请输入班级" style = "bottom: 5px; left : 5px"></div></div>
    <button class="layui-btn layui-btn-normal" lay-event="select" style="position: relative;left: 15px;">查询</button>
  </div>
</script>
	<script type="text/javascript">
	function init2(){
		init("");
	}
		init("");
		function init(class_name) {
			table.render({
				elem : '#demo2',
				id : 'idTest',
				height : 600,
				toolbar : '#toolbarDemo',
				totalRow : true,
				url : '/Textck181228/controllerClass/page' //数据接口
				,
				where : {
					className : class_name
				},
				page : {
					theme : '#2196f3',
					prev : '上一页',
					next : '下一页'
				} //开启分页
				,
				cols : [ [ //表头
				{
					field : 'id',
					title : 'ID',
					unresize : true,
					sort : true,
					fixed : 'left',
				}, {
					field : 'className',
					title : '班级',
					sort : true,
					edit : 'text',
					unresize : true
				}, {
					fixed : 'right',
					title : '操作',
					toolbar : '#barDemo',
				} ] ],
				parseData : function(res) {
					return {
						"code" : "0",
						"count" : res.count,
						"data" : res.classlist
					}
				}
				,request: {
				    pageName: 'pageIndex' //页码的参数名称，默认：page
				    ,limitName: 'pageLimit' //每页数据量的参数名，默认：limit
				 }
			});
			table.on('toolbar(test)', function(obj) {
				switch (obj.event) {
				case 'add':
					if("${code}" != "admin"){
						layer.msg("您没有此权限");
						return;
					}
					openLayer('/jump/addClass', init2);
					break;
				case 'select':
					init($("#task_code").val(), $("#task_name").val());
					break;
				}
				;
			});
			table.on('tool(test)', function(obj) { //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
				if("${code}" != "admin"){
					layer.msg("您没有此权限");
					return;
				}
				if (obj.event == 'del') {
					layer.confirm('确认删除？', function(index){
						  //do something
						ajax("/User/delete.do", {
							user_code : obj.data.taskCode
						}, "text", function(data) {
							if (data == "success") {
								layer.msg("删除成功");
								init("");
							} else if(data == "err"){
								layer.msg("删除失败")
							}
						});
						layer.close(index);
						}); 
				}
			});
			table.on('edit(test)', function(obj) { //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
				var json = {};
				json[obj.field] = obj.value;
				json["id"] = obj.data.id;
				ajax("/User/update.do", json, "text", function(data) {
					if (data == "success") {
						layer.msg("已修改");
						init("");
					} else if (data == "err") {
						layer.msg("修改失败");
					} else if(data == "admin"){
						layer.msg("最高级管理员不可修改");
					}
				});
			});
		}
	</script>
	<script type="text/html" id="barDemo">
  <input type="button" class="layui-btn layui-btn-danger layui-btn-xs {{= d.user_code=='admin' ? 'layui-btn-disabled' : ''}}" lay-event="del" value="删除" {{= d.user_code=='admin' ? 'disabled' : ''}}>
</script>
</body>
</html>