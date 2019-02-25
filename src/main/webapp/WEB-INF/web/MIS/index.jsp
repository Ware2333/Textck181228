<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
<%@include file="/WEB-INF/web/demoheader.jsp"%>
<%@include file="/WEB-INF/web/header.jsp"%>
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport">
</head>
<body>
	<header class="header">
		<nav class="navbar navbar-expand-lg ">
			<div class="search-box">
				<button class="dismiss">
					<i class="icon-close"></i>
				</button>
				<form id="searchForm" action="#" role="search">
					<input type="search" placeholder="Search Now" class="form-control">
				</form>
			</div>
			<div class="container-fluid ">
				<div
					class="navbar-holder d-flex align-items-center justify-content-between">
					<div class="navbar-header">
						<a href="index.html" class="navbar-brand">
							<div class="brand-text brand-big hidden-lg-down">
								<img src="/Textck181228/web/common/img/logo-white.png"
									alt="Logo" class="img-fluid">
							</div>
							<div class="brand-text brand-small">
								<img src="/Textck181228/web/common/img/logo-icon.png" alt="Logo"
									class="img-fluid">
							</div>
						</a> <a id="toggle-btn" href="#" class="menu-btn active"> <span></span>
							<span></span> <span></span>
						</a>
					</div>
				</div>
				<ul
					class="nav-menu list-unstyled d-flex flex-md-row align-items-md-center">
					<!-- Expand-->

					<!-- Search-->

					<!-- Notifications-->
					<li class="nav-item dropdown"><a id="notifications"
						class="nav-link" rel="nofollow" data-target="#" href="#"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i
							class="fa fa-bell-o"></i><span class="noti-numb-bg"></span><span
							class="badge">12</span></a>
						<ul aria-labelledby="notifications" class="dropdown-menu">
							<li><a rel="nofollow" href="#"
								class="dropdown-item nav-link">
									<div class="notification">
										<div class="notification-content">
											<i class="fa fa-envelope bg-red"></i>你有6条新消息
										</div>
										<div class="notification-time">
											<small>4分钟前</small>
										</div>
									</div>
							</a></li>
							<li><a rel="nofollow" href="#"
								class="dropdown-item nav-link">
									<div class="notification">
										<div class="notification-content">
											<i class="fa fa-twitter bg-skyblue"></i>你有两个追随者
										</div>
										<div class="notification-time">
											<small>4分钟前</small>
										</div>
									</div>
							</a></li>
							<li><a rel="nofollow" href="#"
								class="dropdown-item nav-link">
									<div class="notification">
										<div class="notification-content">
											<i class="fa fa-upload bg-blue"></i>重启服务器
										</div>
										<div class="notification-time">
											<small>4分钟前</small>
										</div>
									</div>
							</a></li>
							<li><a rel="nofollow" href="#"
								class="dropdown-item nav-link">
									<div class="notification">
										<div class="notification-content">
											<i class="fa fa-twitter bg-skyblue"></i>你有两个追随者
										</div>
										<div class="notification-time">
											<small>10分钟前</small>
										</div>
									</div>
							</a></li>
							<li><a rel="nofollow" href="#"
								class="dropdown-item all-notifications text-center"> <strong>查看所有通知</strong></a></li>
						</ul></li>
					<!-- Messages                        -->
					<li class="nav-item dropdown"><a id="messages"
						class="nav-link logout" rel="nofollow" data-target="#" href="#"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i
							class="fa fa-envelope-o"></i><span class="noti-numb-bg"></span><span
							class="badge">10</span></a>
						<ul aria-labelledby="messages" class="dropdown-menu">
							<li><a rel="nofollow" href="#" class="dropdown-item d-flex">
									<div class="msg-profile">
										<img src="/Textck181228/web/common/img/avatar-1.jpg" alt="..."
											class="img-fluid rounded-circle">
									</div>
									<div class="msg-body">
										<h3 class="h5 msg-nav-h3">Jason Doe</h3>
										<span>给你发消息</span>
									</div>
							</a></li>
							<li><a rel="nofollow" href="#" class="dropdown-item d-flex">
									<div class="msg-profile">
										<img src="/Textck181228/web/common/img/avatar-2.jpg" alt="..."
											class="img-fluid rounded-circle">
									</div>
									<div class="msg-body">
										<h3 class="h5 msg-nav-h3">Frank Williams</h3>
										<span>给你发消息</span>
									</div>
							</a></li>
							<li><a rel="nofollow" href="#" class="dropdown-item d-flex">
									<div class="msg-profile">
										<img src="/Textck181228/web/common/img/avatar-3.jpg" alt="..."
											class="img-fluid rounded-circle">
									</div>
									<div class="msg-body">
										<h3 class="h5 msg-nav-h3">Ashley Wood</h3>
										<span>给你发消息</span>
									</div>
							</a></li>
							<li><a rel="nofollow" href="#"
								class="dropdown-item all-notifications text-center"> <strong>查看所有消息</strong></a></li>
						</ul></li>
					<li class="nav-item dropdown"><a id="profile"
						class="nav-link logout" data-target="#" href="#"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img
							src="/Textck181228/web/common/img/avatar-1.jpg" alt="..."
							class="img-fluid rounded-circle"
							style="height: 30px; width: 30px;"></a>
						<ul aria-labelledby="profile" class="dropdown-menu profile">
							<li><a rel="nofollow" href="#" class="dropdown-item d-flex">
									<div class="msg-profile">
										<img src="/Textck181228/web/common/img/avatar-1.jpg" alt="..."
											class="img-fluid rounded-circle">
									</div>
									<div class="msg-body">
										<h3 class="h5">${user}</h3>
										<span>${code}</span>
									</div>
							</a>
								<hr></li>
							<li><a rel="nofollow" href="profile.html"
								class="dropdown-item">
									<div class="notification">
										<div class="notification-content">
											<i class="fa fa-user "></i>我的消息
										</div>
									</div>
							</a></li>
							<li><a rel="nofollow" href="profile.html"
								class="dropdown-item">
									<div class="notification">
										<div class="notification-content">
											<i class="fa fa-envelope-o"></i>收件箱
										</div>
									</div>
							</a></li>
							<li><a rel="nofollow" href="profile.html"
								class="dropdown-item">
									<div class="notification">
										<div class="notification-content">
											<i class="fa fa-cog"></i>设置
										</div>
									</div>
							</a>
								<hr></li>
							<li><a rel="nofollow" href="javascript:;"
								class="dropdown-item">
									<div class="notification">
										<div class="notification-content">
											<i class="fa fa-power-off"></i>注销
										</div>
									</div>
							</a></li>
						</ul></li>

				</ul>
			</div>
		</nav>
	</header>
	<div class="page-content d-flex align-items-stretch">

		<!--***** SIDE NAVBAR *****-->
		<nav class="side-navbar">
			<div class="sidebar-header d-flex align-items-center">
				<div class="avatar">
					<img src="/Textck181228/web/common/img/avatar-1.jpg" alt="..."
						class="img-fluid rounded-circle">
				</div>
				<div class="title">
					<h1 class="h4">${user}</h1>
				</div>
			</div>
			<hr>
			<!-- Sidebar Navidation Menus-->
			<ul class="list-unstyled">
				<li><a class="not-found" href="javascript:;"
					data-url="/Textck181228/jump/demo.do">主页</a></li>
				<c:forEach items="${menu}" var="menu">
					<c:if test="${empty menu.list}">
						<li><a class="not-found" href="javascript:;"
							data-url="${menu.menu_url}">${menu.menu_name}</a></li>
					</c:if>
					<c:if test="${!empty menu.list}">
						<li><a aria-expanded="false" data-toggle="collapse"
							href="#${menu.menu_code}">${menu.menu_name} </a>
							<ul id="${menu.menu_code}" class="collapse list-unstyled">
								<c:forEach items="${menu.list}" var="childmenu">
									<li><a class="not-found" href="javascript:;"
										data-url="${childmenu.menu_url}">${childmenu.menu_name}</a></li>
								</c:forEach>
							</ul></li>
					</c:if>
				</c:forEach>
			</ul>
		</nav>
		<iframe name='rightiframe' width="100%" height="100%"
			style="width: 100%; height: 620px;" frameborder=0
			src="/Textck181228/jump/demo.do"></iframe>
	</div>
	<script type="text/javascript">
		$(".not-found").click(function() {
			window.open($(this).data('url'), "rightiframe");
		});

		$(".dropdown-item").click(function() {
			layer.confirm('真的要注销吗?', {icon: 2, title:'注销'}, function(index){
			location.href = "/Textck181228/jump/logout.do"
				  layer.close(index);
				});
		})
	</script>
</body>
</html>
