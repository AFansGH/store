<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员登录</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/jquery.validate.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$("#checkCode").click(
				function() {
					$("#checkCode").prop(
							"src",
							"${pageContext.request.contextPath}/checkCode?time="
									+ new Date().toString());
				});
		$("#username").focus(
				function() {
					$("#loginError").css("display", "none")
				});
		$("#password").focus(
				function() {
					$("#loginError").css("display", "none")
				});
		
		
		$("#inputCheckCode").focus(
				function() {
					$("#checkCodeEror").css("display", "none")
				});
		
		
		$("#loginForm").validate({
			
			rules:{
				username:{
					required : true
				},
				password:{
					required : true
				},
				checkCode:{
					required : true
				}
			},
			messages:{
				username:{
					required : "请输入用户名"
				},
				password:{
					required : "请输入密码"
				},
				checkCode:{
					required : "验证码不能为空"
				}
			},
			errorElement:"labelErr"
		});
		
		
		
		
	});
</script>





<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css" />

<style>
body {
	margin-top: 20px;
	margin: 0 auto;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}

.container .row div {
	/* position:relative;
				 float:left; */
	
}

font {
	color: #666;
	font-size: 22px;
	font-weight: normal;
	padding-right: 17px;
}

labelErr {
	color: red;
}

</style>
</head>
<body>

	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>


	<div class="container"
		style="width: 100%; height: 460px; background: #FF2C4C url('images/loginbg.jpg') no-repeat;">
		<div class="row">
			<div class="col-md-7">
				<!--<img src="./image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
			</div>

			<div class="col-md-5">
				<div
					style="width: 440px; border: 1px solid #E7E7E7; padding: 20px 0 20px 30px; border-radius: 5px; margin-top: 60px; background: #fff;">
					<font>会员登录</font>USER LOGIN
					<div>&nbsp;</div>
					<form class="form-horizontal"
						action="${pageContext.request.contextPath }/userCentre?method=login"
						method="post"
						id="loginForm">
						<div class="form-group">
							<label for="username" class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="username" id="username" value="${username }"
									placeholder="请输入用户名"> <span id="loginError"
									style="color: red">${requestScope.loginError }</span>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
							<div class="col-sm-6">
								<input type="password" class="form-control" name="password" id="password"
									placeholder="请输入密码">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">验证码</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="inputCheckCode"
									name="checkCode" placeholder="请输入验证码">
									
									<span id="checkCodeEror" style="color: red">${requestScope.checkCodeEror }</span>
							</div>
							<div class="col-sm-3">
								<img id="checkCode"
									src="${pageContext.request.contextPath }/checkCode" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="checkbox">
								
								
									<label> <input type="checkbox"  name="autoLogin" value="" checked="checked"> 自动登录
									</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 <label> <input type="checkbox" name="remberUsername" value="" checked="checked"> 记住用户名
									</label>
									
									
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<input type="submit" width="100" value="登录" name="submit"
									style="background: url('./images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>