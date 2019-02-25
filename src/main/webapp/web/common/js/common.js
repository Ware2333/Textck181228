var form = layui.form;
var $ = layui.jquery;
var layer = layui.layer;
var element = layui.element;
var laypage = layui.laypage;
var laydate = layui.laydate;
var con ={net:"/Textck181228"};
var upload = layui.upload;
var laydit = layui.laydit;
var table = layui.table;
var flow = layui.flow;
var tree = layui.tree;

function update(url,end) {
	layer.open({
		type : 2, //2将页面添加到弹出层显示
		title : "数据维护",
		closeBtn : 1, //关闭按钮
		shadeClose : true, //点击空白处关闭弹出层
		skin : 'layui-layer-molv',
		area : [ '700px', '460px' ],//窗口大小
		maxmin : true, //最大最小按钮
		end : end,
		content : [con.net+url],
		anim : 5,//弹出动画
		isOutAnim : true,//关闭动画
		success : function() {
			
		}
	});
}

	function goJspLogin() {
		location.href = con.app + '/web/login.jsp';
	}
	function goJspReg() {
		location.href = con.app + '/web/reg.jsp';
	}
	function goLogout() {
		openConfirm(function(index) {
			layer.close(index);
			location.href = con.app + "/UserServlet?action=logout";
		}, '确定注销？');
	}
	function openConfirm(func, title) {
		title = title ? title : "确定进行该操作？";
		layer.confirm(title, {
			icon : 3,
			title : '提示'
		}, func);
	}

	function formSubmit(url, submit, dataType, func) {
		form.on(submit, function(data) {
			ajax(url, data.field, dataType, func);
		});
	}
	function ajax(url, field, dataType, func) {
		$.ajax({
			url : con.net + url,
			data : field,
			dataType : dataType,
			type : 'post',
			traditional: true,
			success : func
		});
	}
	function openLayer(url, end) {
		layer.open({
			type : 2,
			area : [ '800px', '450px' ],
			fixed : false, // 不固定
			maxmin : true,
			end : end,
			content : con.net + url
		});
	}
	function closeThis(time) {
		time = time ? time : 200;
		setTimeout(function() {
			// 先得到当前iframe层的索引
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		}, time)
	}
	function setPageInfo(elem, count, curr, limit, jump) {
		laypage.render({
			elem : elem,
			count : count,
			curr : curr,
			limit : limit,
			layout : [ 'count', 'prev', 'page', 'next', 'limit', 'skip' ],
			jump : jump
		});
	}
	
	function PageInfo(elem, count, curr, limit, jump) {
		laypage.render({
			elem : elem,
			count : count,
			curr : curr,
			limit : limit,
			limits : [16,32,48,64],
			layout : [ 'count', 'prev', 'page', 'next', 'limit', 'skip' ],
			jump : jump
		});
	}
	

