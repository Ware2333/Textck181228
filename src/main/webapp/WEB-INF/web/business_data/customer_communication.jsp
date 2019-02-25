<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/web/header.jsp"%>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>${cust.cust_name}</legend>
</fieldset>
 
<ul class="layui-timeline" id="LAY_demo1"></ul>
<script type="text/javascript">
var cust_code = "${cust.cust_code}"
flow.load({
    elem: '#LAY_demo1' //流加载容器
    ,scrollElem: '#LAY_demo1' //滚动条所在元素，一般不用填，此处只是演示需要。
    ,done: function(page, next){ //执行下一页的回调
        var lis = [];
    	ajax("/customer_communicationController/select.do?pageIndex="+page, {cust_code:cust_code}, "json", function(data){
    		$.each(data.data,function(i,dom){
    			lis.push('<li class="layui-timeline-item"><i class="layui-icon layui-timeline-axis"></i><div class="layui-timeline-content layui-text"><h3 class="layui-timeline-title">'+dom.TIME+'</h3><p>'+dom.content+'</p></div></li>');
    		})
    		next(lis.join(''), page < data.pages); 
    	})
    }
  });
</script>
</body>
</html>