<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/web/demoheader.jsp"%>
</head>
<body>
	<div class="page-content d-flex align-items-stretch">
		<div class="content-inner" style="width: 100%">
			<div class="row" id="report2">
				<div class="col-md-4">
					<div class="card card-c2">
						<div class="dropdown pull-right menu-sett-card">
							<a id="notifications" class="nav-link" rel="nofollow"
								data-target="#" href="#" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false"> <i
								class="fa fa-ellipsis-v men"></i>
							</a>
							<ul aria-labelledby="notifications" class="dropdown-menu">
								<li><a rel="nofollow" href="#"
									class="dropdown-item nav-link">Edit</a></li>
								<li><a rel="nofollow" href="#"
									class="dropdown-item nav-link">FAQ</a></li>
								<li><a rel="nofollow" href="#"
									class="dropdown-item nav-link">Support</a></li>
							</ul>
						</div>
						<div class="header">
							<h4 class="title">数据统计图</h4>
							<p class="category">Last Campaign Performance</p>
						</div>
						<div class="content">
							<canvas class="ct-chart" id="polar-chart" height="250"></canvas>
							<canvas class="ct-chart" id="myChart4" height="250"></canvas>
							<div class="footer">
								<div class="legend">
									<i class="fa fa-circle text-info"></i> Open <i
										class="fa fa-circle text-danger"></i> 葡萄 <i
										class="fa fa-circle text-warning"></i> 蓝莓
								</div>
								<hr>
								<div class="stats">
									<i class="fa fa-clock-o"></i> Campaign sent 2 days ago
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card card-c1">
						<div class="card-header card-chart" data-background-color="green">
							<canvas class="ct-chart" id="myChart1" height="250"></canvas>
						</div>
						<div class="card-content">
							<h4 class="title">Daily Sales</h4>
							<p class="category">
								<span class="text-success"><i class="fa fa-long-arrow-up"></i>
									55% </span> increase in today sales.
							</p>
						</div>
						<div class="card-footer">
							<div class="stats">
								<i class="fa fa-clock-o"></i> updated 4 minutes ago
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--     <script src="/Textck181228/web/common/javascript/mychart.js"></script> -->
	<script type="text/javascript">
		var labels = [];
		var data = [];
		var user = [];
		var count = [];
		<c:forEach items="${user_info}" var="user_info">
		user.push("${user_info.role_name}")
		count.push("${user_info.count}")
		</c:forEach>
		<c:forEach items="${procuct_info}" var="procuct_info">
		labels.push("${procuct_info.name}")
		data.push("${procuct_info.sum}")
		</c:forEach>
		//Mychart1
		new Chart(document.getElementById("myChart1").getContext('2d'), {
			type : 'bar',
			data : {
				labels : labels,
				datasets : [ {
					label : '',
					type : 'bar',
					data : data,
					backgroundColor : [ 'rgba(255, 99, 132, 1)',
							'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
							'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
							'rgba(255, 159, 64, 1)','rgba(134, 119, 4, 2)',
							'rgba(55, 59, 64, 1)','rgba(25, 15, 64, 1)',
							'rgba(25, 159, 4, 1)'],
					borderColor : [ 'rgba(255,99,132,1)',
							'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
							'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
							'rgba(255, 159, 64, 1)','rgba(134, 119, 4, 2)',
							'rgba(55, 59, 64, 1)','rgba(25, 15, 64, 1)',
							'rgba(25, 159, 4, 1)' ],
					borderWidth : 1
				} ]
			},
			options : {
				legend : {
					display : false
				},
				title : {
					display : true,
					text : ''
				}
			}
		});

		//Mychart4
		new Chart(document.getElementById("myChart4").getContext('2d'), {
			type : 'doughnut',
			data : {
				labels : labels,
				datasets : [ {
					backgroundColor : [ "#2ecc71", "#3498db", "#95a5a6",
							"#9b59b6", "#f1c40f", "#e74c3c", "#34495e" ,"#F5F5DC","#FF4040","#FFDAB9"],
					data : data
				} ]
			},
			options : {
				legend : {
					display : false
				},
				title : {
					display : true,
					text : ''
				}
			}
		});

		// Polar chart
		new Chart(document.getElementById("polar-chart").getContext('2d'), {
			type : 'polarArea',
			data : {
				labels : user,
				datasets : [ {
					backgroundColor : [ "#2ecc71", "#3498db", "#95a5a6",
							"#9b59b6", "#f1c40f", "#e74c3c", "#34495e" ],
					data : count
				} ]
			},
			options : {
				legend : {
					display : false
				},
				title : {
					display : true,
					text : ''
				}
			}
		});
	</script>
</body>
</html>