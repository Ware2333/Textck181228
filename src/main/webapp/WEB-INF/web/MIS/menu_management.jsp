<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/web/header.jsp"%>
<script type="text/javascript" src="/Textck181228/web/common/layui/layui.js"></script>
</head>
<body>
<table id="demo2" lay-filter="test" lay-data="{id: 'idTest'}"></table>
<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container">
    <button class="layui-btn layui-btn-normal" lay-event="add">添加新菜单</button>
    <div class="layui-inline"><div class="layui-input-inline"><input class = "layui-input layui-input-inline" type="text" id = "menu_code" placeholder = "请输入账号" style = "bottom: 5px;"></div></div>
    <div class="layui-inline"><div class="layui-input-inline"><input class = "layui-input layui-input-inline" type="text" id = "menu_name" placeholder = "请输入姓名" style = "bottom: 5px; left : 5px"></div></div>
    <button class="layui-btn layui-btn-normal" lay-event="select" style="position: relative;left: 15px;">查询</button>
  </div>
</script>
<script type="text/javascript">
function init2(){
	init("","");
}
init("","");
function init(menu_code,menu_name){
	table.render({
	    elem: '#demo2'
	    ,id: 'idTest'
	    ,height: 600
	    ,toolbar: '#toolbarDemo'
	    ,totalRow: true
	    ,url: '/Textck181228/MenuController/selectlist.do' //数据接口
	    ,where: {menu_code:menu_code,menu_name:menu_name}
	    ,page: {theme:'#2196f3',prev:'上一页',next:'下一页'} //开启分页
	    ,cols: [[ //表头
	      {field: 'id', title: 'ID',unresize: true,  sort: true, fixed: 'left',}
	      ,{field: 'menu_code', title: '菜单编号', unresize: true}
	      ,{field: 'menu_name', title: '菜单名称',edit:'text', unresize: true}
	      ,{field: 'menu_url', title: '菜单地址',edit:'text', unresize: true} 
	      ,{field: 'parent_code', title: '上级菜单编号',edit:'text', unresize: true}
	      ,{fixed: 'right', title: '操作',toolbar: '#barDemo'}
	    ]]
	    ,parseData: function(res){
	    	return {
	    	"code":"0",
	    	"count":res.count,
	    	"data":res.menu		
	    	}
	    }
	    ,request: {
	        pageName: 'pageIndex' //页码的参数名称，默认：page
	        ,limitName: 'pageLimit' //每页数据量的参数名，默认：limit
	        }
	  });
	table.on('toolbar(test)', function(obj){
		  switch(obj.event){
		    case 'add':
		    	openLayer('/jump/addmenu.do',init2);
		    break;
		    case 'select':
				init($("#menu_code").val(), $("#menu_name").val());
				break;
		  };
	});
	table.on('tool(test)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
		if(obj.event=='del'){
			layer.confirm('确定删除？', function(index){
				ajax("/MenuController/delete.do", {menu_code:obj.data.menu_code}, "text", function(data){
					  if(data=="success"){
						  layer.msg("删除成功");
						  init("","");
					  }else if(data == "undelete"){
						  layer.msg("删除失败,基础菜单不可删除");
					  }else if(data == "Occupied"){
						  layer.msg("被占用,无法删除");
					  }else if(data == "err"){
						  layer.msg("删除失败");
					  }
				  });
				layer.close(index);
				});    
			
		}
		});
	table.on('edit(test)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
		  var json = {};
		  json[obj.field] = obj.value;
		  json["menu_code"] = obj.data.menu_code;
		  ajax("/MenuController/update.do", json, "text", function(data){
			  if(data=="success"){
				  layer.msg("已修改");
			  }else if(data == "err"){
				  layer.msg("修改失败");
			  }else if(data == "admin"){
				  layer.msg("最高级管理员不可修改");
				  init("","");
			  }
		  });
		});
}
</script>
<script type="text/html" id="barDemo">
  <input type="button" class="layui-btn layui-btn-danger layui-btn-xs {{= d.menu_code=='add_menu' || d.menu_code=='admin' || d.menu_code == 'add_user'|| d.menu_code == 'add_role'||d.menu_code=='Role_management'? 'layui-btn-disabled' : ''}}" lay-event="del" value="删除" {{= d.menu_code=='add_menu' ? 'disabled' : ''}}>
</script>
</body>
</html>