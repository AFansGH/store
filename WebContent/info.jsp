<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>商城信息展示</title>
		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
		<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</head>

	<body>
		<div class="container-fluid">

			<!-- 引入header.jsp -->
			<jsp:include page="/header.jsp"></jsp:include>

			<div class="container-fluid">
				<div class="main_con">
					<h2>公司简介</h2>
					<hr/>
					<div>
						<p>本网上商城为某马视频配套练习项目，本人主要以学习的目的实现本商城</p>					
						<p>本商场主要技术点有以下几点：1.商品的分类分页 2.对于具体商品点击的信息展示 3.浏览记录的实现 4.验证码的校验，以及自动登陆的实现
						5.购物车的实现 6.对于servlet的抽取 7.对于订单数据进入数据库的事务处理</p>	
					</div>
				</div>
			</div>
		</div>
		
		<!-- 引入footer.jsp -->
		<jsp:include page="/footer.jsp"></jsp:include>

	</body>

</html>