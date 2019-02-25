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
    <button class="layui-btn layui-btn-normal" lay-event="add">添加新商品</button>
    <button class="layui-btn layui-btn-normal" lay-event="download">下载模板</button>
    <button class="layui-btn layui-btn-normal" lay-event="Batch_deletion">批量删除</button>
    <button class="layui-btn layui-btn-normal" lay-event="upload" id="upload">上传文件</button>
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
		function init(code, name) {
			table.render({
			    elem: '#demo2'
			    ,id: 'idTest'
			    ,height: 600
			    ,toolbar: '#toolbarDemo'
			    ,totalRow: true
			    ,url: '/Textck181228/product_infoController/selectdata.do' //数据接口
			    ,where: {code:code,name:name}
			    ,page: {theme:'#2196f3',prev:'上一页',next:'下一页'} //开启分页
			    ,cols: [[ //表头
			        {type: 'checkbox', fixed: 'left'}
			      ,{field: 'id', title: 'ID',unresize: true,  sort: true, fixed: 'left',}
			      ,{field: 'code', title: '商品编号', unresize: true}
			      ,{field: 'name', title: '商品名称',edit:'text',unresize: true}
			      ,{field: 'sum', title: '数量',edit:'text', sort: true, unresize: true} 
			      ,{field: 'cost', title: '成本',  sort: true, unresize: true}
			      ,{field: 'cost_unit_price', title: '成本单价',  sort: true,  unresize: true}
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
				var check = table.checkStatus('idTest');
				switch (obj.event) {
				case 'add':
					openLayer('/jump/addproduct.do', init2);
					break;
				case 'download':
					location.href="/Textck181228/product_infoController/downloadExcel.do";
					break;
				case 'Batch_deletion':
					var code = [];
					if(check.data.length>0){
						layer.confirm('is not?', function(index){
							$.each(check.data,function(i,dom){
								code[i] = dom.code
							})
							ajax("/product_infoController/delete.do", {code:code}, "text", function(data){
								if(data=="success"){
									layer.msg("删除成功,删除"+check.data.length+"条记录")
									init2();
								}
							})
							}); 
						
					}else{
						layer.msg("请勾选要删除的数据")
					}
					break;
				case 'select':
					init($("#code").val(), $("#name").val());
					break;
				}
				;
			});
			upload.render({
			    elem: '#upload' //绑定元素
			    ,url: '/Textck181228/User/uploadExcel.do' //上传接口
			    ,accept:'file'
			    ,exts:'xls|xlsx'
			    ,done: function(res){
			    	layer.msg("已上传"+res.count+"条记录,"+res.ncount+"已存在"); 
			    	init("", "");
			    }
			    ,error: function(){
			      layer.msg("请检查上传文件格式是否正确");
			    }
			  });
			table.on('tool(test)', function(obj) { //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
				if (obj.event == 'del') {
					layer.confirm('确定删除?', function(index){
						ajax("/product_infoController/delete.do", {
							code : obj.data.code
						}, "text", function(data) {
							if (data == "success") {
								layer.msg("删除成功");
								init("", "");
							}
						});
						  layer.close(index);
						});   

				}
			});
			table.on('edit(test)', function(obj) { //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
				var json = {};
				json[obj.field] = obj.value;
				json["code"] = obj.data.code;
				ajax("/product_infoController/update.do", json, "text", function(data) {
					if (data == "success") {
						layer.msg("已修改");
						init("","")
					}  else if (data == "err") {
						layer.msg("修改失败");
					}
				});
			});
		}
	</script>
	<script type="text/html" id="barDemo">
  <input type="button" class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" value="删除">
</script>
</body>
</html>