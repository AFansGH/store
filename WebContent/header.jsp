<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- 登录 注册 购物车... -->

<!-- 这里使用的是ajax技术完成导航栏，我们为每个导航栏加上地址 -->
<script type="text/javascript">
	$(function(){
		var content = "";
		
		$.ajax({
			url:"${pageContext.request.contextPath}//productCentre?method=categoryList",
			async:true,
			type:"POST",
			success:function(data){
				for(var i = 0; i < data.length; i++){
					content += "<li><a href='${pageContext.request.contextPath}/productCentre?method=productListById&cid="+data[i].cid+"'>"+data[i].cname+"</a></li>";
				}
				$("#navbar-nav").html(content);		//应该放到响应方法体中
			},
			dataType:"json"
		});		
	});
</script>


<script type="text/javascript">
<!--
	用于用户退出登陆的提示信息
//-->
		function loginOut(){
			var flag = confirm("确认退出登录吗？");
			if(flag){
				location.href='${pageContext.request.contextPath }/userCentre?method=loginOut ';
			}
		}

	
</script>


<div class="container-fluid">
	<div class="col-md-4">
		<img src="img/logo2.png" />
	</div>
	<div class="col-md-5">
		<img src="img/header.png" />
	</div>
	<div class="col-md-3" style="padding-top:20px">
		<ol class="list-inline" id="Inlines">
		
			<c:if test="${empty user }">
				<li><a href="login.jsp">登录</a></li>
				<li><a href="register.jsp">注册</a></li>
			</c:if>
			<c:if test="${!empty user }">
				<li><a href="#">
				<span style="color:red">${user.username }</span>
				</a></li>
				<li ><a href="javascript:void(0)"  onclick="loginOut()" >退出</a></li>
				
		<!-- ${pageContext.request.contextPath }/userCentre?method=loginOut -->		
				
			</c:if>
			<li><a href="cart.jsp">购物车</a></li>
			<li><a href="${pageContext.request.contextPath }/userCentre?method=myOrders">我的订单</a></li>
		</ol>
	</div>
</div>

<!-- 导航条 -->
<div class="container-fluid">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath }/default.jsp">首页</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav"  id="navbar-nav">
				
				
					
					
				</ul>
				<form class="navbar-form navbar-right" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</div>
	</nav>
</div>




