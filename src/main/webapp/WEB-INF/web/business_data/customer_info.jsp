<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<table id="demo2" lay-filter="test" lay-data="{id: 'idTest'}"></table>
	<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container">
    <button class="layui-btn layui-btn-normal" lay-event="add">添加新账户</button>
    <div class="layui-inline"><div class="layui-input-inline"><input class = "layui-input layui-input-inline" type="text" id = "cust_code" placeholder = "请输入账号" style = "bottom: 5px;"></div></div>
    <div class="layui-inline"><div class="layui-input-inline"><input class = "layui-input layui-input-inline" type="text" id = "cust_name" placeholder = "请输入姓名" style = "bottom: 5px; left : 5px"></div></div>
    <button class="layui-btn layui-btn-normal" lay-event="select" style="position: relative;left: 15px;">查询</button>
  </div>
</script>
	<script type="text/javascript">
	function init2(){
		init("", "");
	}
		init("", "");
		function init(user_code, user_name) {
			table.render({
			    elem: '#demo2'
			    ,id: 'idTest'
			    ,height: 600
			    ,toolbar: '#toolbarDemo'
			    ,totalRow: true
			    ,url: '/Textck181228/customer_info/select.do' //数据接口
			    ,page: {theme:'#2196f3',prev:'上一页',next:'下一页'} //开启分页
			    ,cols: [[ //表头
			      {field: 'id', title: 'ID',unresize: true,  sort: true, fixed: 'left',}
			      ,{field: 'cust_code', title: '客户编号',unresize: true}
			      ,{field: 'cust_name', title: '客户姓名', unresize: true}
			      ,{field: 'status', title: '状态',edit:'text', unresize: true} 
			      ,{field: 'email', title: '邮箱', unresize: true}
			      ,{fixed: 'right', title: '操作',toolbar: '#barDemo'}
			    ]]
			    ,parseData: function(res){
			    	return {
			    	"code":"0",
			    	"count":res.count,
			    	"data":res.data		
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
					openLayer('/jump/addcustomer.do', init2);
					break;
				case 'select':
					init($("#cust_code").val(), $("#cust_name").val());
					break;
				}
				;
			});
			table.on('tool(test)', function(obj) { //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
				if (obj.event == 'del') {
					layer.confirm('确定删除？', function(index){
						ajax("/customer_info/delete.do", {
							cust_code : obj.data.cust_code
						}, "text", function(data) {
							if (data == "success") {
								layer.msg("删除成功");
								init("", "");
							} else if (data == "err") {
								layer.msg("删除失败")
							}
						});
						layer.close(index);
						});    
					
				} else if(obj.event == 'sel'){
					location.href="/Textck181228/jump/customer_communication.do?cust_code="+obj.data.cust_code
				}
			});
			table.on('edit(test)', function(obj) { //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
				var json = {};
				json[obj.field] = obj.value;
				json["cust_code"] = obj.data.cust_code;
				console.log(json)
				ajax("/customer_info/update.do", json, "text", function(data) {
					if (data == "success") {
						layer.msg("已修改");
					} else if (data == "err") {
						layer.msg("修改失败");
					}
				});
			});
		}
	</script>
	<script type="text/html" id="barDemo">
  <input type="button" class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" value="删除">
 <input type="button" class="layui-btn layui-btn-danger layui-btn-xs" lay-event="sel" value="查看客户沟通信息">
</script>
</body>
</html>