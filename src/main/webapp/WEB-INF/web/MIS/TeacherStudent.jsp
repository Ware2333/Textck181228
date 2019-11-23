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
    <div class="layui-inline"><div class="layui-input-inline"><input class = "layui-input layui-input-inline" type="text" id = "user_code" placeholder = "请输入账号" style = "bottom: 5px;"></div></div>
    <div class="layui-inline"><div class="layui-input-inline"><input class = "layui-input layui-input-inline" type="text" id = "user_name" placeholder = "请输入姓名" style = "bottom: 5px; left : 5px"></div></div>
    <button class="layui-btn layui-btn-normal" lay-event="select" style="position: relative;left: 15px;">查询</button>
  </div>
</script>
	<script type="text/javascript">
	function init2(){
		init("", "");
	}
		init("", "");
		dl();
		function dl(){
			if('${code}' != 'admin'){
				$('table.layui-table thead tr th:eq(5)').addClass('layui-hide');
			}
		}
		function init(user_code, user_name) {
			table.render({
				elem : '#demo2',
				id : 'idTest',
				height : 600,
				totalRow : true,
				url : '/Textck181228/teacherStudentController/jobSubmission' //数据接口
				,
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
						fixed : 'left'
					},{
						field : 'userName',
						title : '学生姓名',
						unresize : true
					}, {
						field : 'taskCode',
						title : '作业',
						edit : 'text',
						unresize : true
					}, {
						fixed : 'right',
						title : '操作',
						toolbar : '#barDemo'
					} ] ],
				parseData : function(res) {
					console.log(res)
					return {
						"code" : "0",
						"count" : res.count,
						"data" : res.taskUser
					}
				}
				,request: {
				    pageName: 'pageIndex' //页码的参数名称，默认：page
				    ,limitName: 'pageLimit' //每页数据量的参数名，默认：limit
				 }
			});
			table.on('toolbar(test)', function(obj) {
				switch (obj.event) {
				case 'select':
					init($("#user_code").val(), $("#user_name").val());
					break;
				}
				;
			});
			table.on('tool(test)', function(obj) { //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
				if(obj.event == 'download'){
					if(${user.parent_code} == 3){
						location.href="/Textck181228/taskupload/download?filename="+obj.data.taskCode
					}else{
						if(obj.data.taskStudent == null){
							layer.msg("学生未提交");
							return;
						}
					location.href="/Textck181228/taskupload/download?filename="+obj.data.taskStudent
					}
				} else if (obj.event == 'edit') {
					openLayer('/jump/uploadTask/'+obj.data.taskCode+'/'+obj.data.userName+'/'+obj.data.id, init2);
				}
			});
		}
	</script>
	<script type="text/html" id="barDemo">
  <input type="button" class="layui-btn layui-btn-danger layui-btn-xs" lay-event="edit" value="查看详情">
<input type="button" class="layui-btn layui-btn-danger layui-btn-xs" lay-event="download" value="下载" >
</script>
</body>
</html>