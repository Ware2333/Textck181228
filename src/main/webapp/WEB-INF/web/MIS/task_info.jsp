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
    <button class="layui-btn layui-btn-normal" id="add" lay-event="add">添加作业</button>
    <div class="layui-inline"><div class="layui-input-inline"><input class = "layui-input layui-input-inline" type="text" id = "task_code" placeholder = "请输入名称" style = "bottom: 5px;"></div></div>
    <button class="layui-btn layui-btn-normal" lay-event="select" style="position: relative;left: 15px;">查询</button>
  </div>
</script>
	<script type="text/javascript">
	function init2(){
		init("", "");
	}
		init("", "");
		function init(task_code, task_name) {
			table.render({
				elem : '#demo2',
				id : 'idTest',
				height : 600,
				toolbar : '#toolbarDemo',
				totalRow : true,
				url : '/Textck181228/taskInfoController/tasklist' //数据接口
				,
				where : {
					taskCode : task_code,
					taskName : task_name
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
					field : 'taskCode',
					title : '作业编号',
					unresize : true
				}, {
					field : 'taskTime',
					title : '作业发布时间',
					unresize : true
				}, {
					field : 'taskName',
					title : '作业名称',
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
						"data" : res.task
					}
				}
				,request: {
				    pageName: 'pageIndex' //页码的参数名称，默认：page
				    ,limitName: 'pageLimit' //每页数据量的参数名，默认：limit
				 }
			});
			upload.render({
			    elem: '#add' //绑定元素
			    ,url: '/Textck181228/taskupload/upload/1' //上传接口
			    ,accept:'file'
			    ,done: function(res){
			    	layer.msg("已上传"); 
			    	init("", "");
			    }
			    ,error: function(){
			      layer.msg("请检查上传文件格式是否正确")
			    }
			  });
			table.on('toolbar(test)', function(obj) {
				switch (obj.event) {
				case 'download':
					location.href="/Textck181228/User/downloadexcel.do"
					break;
				case 'select':
					init("", $("#task_name").val());
					break;
				}
				;
			});
			table.on('tool(test)', function(obj) { //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
				if (obj.event == 'del') {
					layer.confirm('确认删除？', function(index){
						  //do something
						ajax("/taskInfoController/delete", {
							id : obj.data.id
						}, "text", function(data) {
							if (data == "success") {
								layer.msg("删除成功");
								init("", "");
							} else if(data == "err"){
								layer.msg("删除失败")
							}
						});
						layer.close(index);
						}); 
					
				} else if (obj.event == 'edit') {
					openLayer('/jump/editrole.do?user_code='
							+ obj.data.user_code, init2);
				}
			});
			table.on('edit(test)', function(obj) { //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
				var json = {};
				json[obj.field] = obj.value;
				json["taskCode"] = obj.data.taskCode;
				ajax("/taskInfoController/update", json, "text", function(data) {
					if (data == "success") {
						layer.msg("已修改");
						init("","");
					} else if (data == "err") {
						layer.msg("修改失败");
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