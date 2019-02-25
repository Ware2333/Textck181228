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
        <button class="layui-btn layui-btn-normal" lay-event="add">添加新角色</button>
    <div class="layui-inline"><div class="layui-input-inline"><input class = "layui-input layui-input-inline" type="text" id = "role_code" placeholder = "请输入角色代码" style = "bottom: 5px;"></div></div>
    <div class="layui-inline"><div class="layui-input-inline"><input class = "layui-input layui-input-inline" type="text" id = "role_name" placeholder = "请输入角色名称" style = "bottom: 5px; left : 5px"></div></div>
    <button class="layui-btn layui-btn-normal" lay-event="select" style="position: relative;left: 15px;">查询</button>
  </div>
</script>
<script type="text/javascript">
function init2(){
	init("","");
}
function init3(){
	location.reload();
}
init("","");
function init(role_code,role_name){
	table.render({
	    elem: '#demo2'
	    ,id: 'idTest'
	    ,height: 600
	    ,toolbar: '#toolbarDemo'
	    ,totalRow: true
	    ,url: '/Textck181228/roleController/role.do' //数据接口
	    ,where: {role_code:role_code,role_name:role_name}
	    ,page: {theme:'#2196f3',prev:'上一页',next:'下一页'} //开启分页
	    ,cols: [[ //表头
	        {field: 'id', title: 'ID',unresize: true,  sort: true, fixed: 'left',}
	      ,{field: 'role_code', title: '角色代码', unresize: true}
	      ,{field: 'role_name', title: '角色名称',edit:'text', unresize: true}
	      ,{field: 'role_note', title: '角色描述',edit:'text', unresize: true} 
	      ,{field: 'role_To_examine', title: '权限状态',  unresize: true}
	      ,{fixed: 'right', title: '操作',toolbar: '#barDemo', }
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
	table.on('toolbar(test)', function(obj){
		  switch(obj.event){
		    case 'add':
		    	openLayer('/jump/addrole.do',init2);
		    break;
		    case 'select':
				init($("#role_code").val(), $("#role_name").val());
				break;
		  };
	});
	table.on('tool(test)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
		if(obj.event=='del'){
			layer.confirm('确定删除？', function(){
				ajax("/roleController/delete.do", {role_code:obj.data.role_code}, "text", function(data){
					  if(data=="success"){
						  layer.msg("删除成功");
						  init("","");
					  }else if(data == "err"){
						  layer.msg("删除失败")
					  }
				  });
				layer.close(index);
				});  
			
		}else if(obj.event == 'edit'){
			if(obj.data.role_code=="${role_code}"){
			openLayer('/jump/editJurisdiction.do?role_code='+obj.data.role_code,init3);
			}else{
			openLayer('/jump/editJurisdiction.do?role_code='+obj.data.role_code,init2);
			}
		}
		});
	table.on('edit(test)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
		  var json = {};
		  json[obj.field] = obj.value;
		  json["role_code"] = obj.data.role_code;
		  ajax("/roleController/update.do", json, "text", function(data){
			  if(data=="success"){
				  layer.msg("已修改");
			  }else if(data == "err"){
				  layer.msg("修改失败");
			  }else if(data == "admin"){
				  layer.msg("最高管理员不可更改");
				  init("","");
			  }
		  });
		});
}
</script>
<script type="text/html" id="barDemo">
  <input type="button" class="layui-btn layui-btn-danger layui-btn-xs {{= d.role_code=='admin' ? 'layui-btn-disabled' : ''}}" lay-event="edit" value="编辑权限" {{= d.role_code=='admin' ? 'disabled' : ''}}>
  <input type="button" class="layui-btn layui-btn-danger layui-btn-xs {{= d.role_code=='admin' ? 'layui-btn-disabled' : ''}}" lay-event="del" value="删除" {{= d.role_code=='admin' ? 'disabled' : ''}}>
</script>
</body>
</html>