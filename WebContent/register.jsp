<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head></head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员注册</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/jquery.validate.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$.validator.addMethod("isExist", function(value, element, params) {
		flag = false;
		$.ajax({
			"url" : "${pageContext.request.contextPath}/userCentre?method=isExist",
			"async" : false,
			"type" : "POST",
			"data" : {
				"username" : value
			},
			"success" : function(data) {
				flag = data.flag;
			},
			"dataType" : "json"
		});
		return flag;
	}
	);

	$(function() {
		$("#checkCode").click(function(){
			$("#checkCode").prop("src","${pageContext.request.contextPath}/checkCode?time="+new Date().toString());
		});
		
		 $("#checked").focus(function(){
			$("#spanCheck").css("display","none");
		});
		
		$("#myform").validate({
			rules : {
				username : {
					required : true,
					isExist : true,
					minlength : 5
				},
				password : {
					required : true,
					minlength : 6
				},
				repassword : {
					required : true,
					equalTo : "#password"
				},
				email : {
					required : true,
					email : true
				},
				name : {
					required : true
				},
				birthday : {
					required : true
				},
				sex : {
					required : true
				},
				checked :{
					required : true
				}
			},
			messages : {
				username : {
					required : "用户名不能为空",
					isExist:"用户名已被占用",
					minlength : "用户名最少为5个字段"
				},
				password : {
					required : "密码不能为空",
					minlength : "密码最少为6个字段"
				},
				repassword : {
					required : "请确认密码",
					equalTo : "两次输入不一致"
				},
				email : {
					required : "邮箱不能为空",
					email : "邮箱格式不正确"
				},
				name : {
					required : "姓名不能为空"
				},
				birthday : {
					required : "生日不能为空"
				},
				sex : {
					required : "暂不支持无性别人士"
				},
				checked :{
					required : "请输入验证码"
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

font {
	color: #3164af;
	font-size: 18px;
	font-weight: normal;
	padding: 0 10px;
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
		style="width: 100%; background: url('image/regist_bg.jpg');">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8"
				style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
				<font>会员注册</font>USER REGISTER


				<form id="myform" class="form-horizontal" style="margin-top: 5px;"
					action="${pageContext.request.contextPath }/userCentre?method=register" method="post">


					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名</label> <span
							class="col-sm-6"> <input type="text" class="form-control"
							id="username" name="username" placeholder="请输入用户名">
						</span>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
						<span class="col-sm-6"> <input type="password"
							class="form-control" id="password" name="password"
							placeholder="请输入密码">
						</span>
					</div>
					<div class="form-group">
						<label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
						<span class="col-sm-6"> <input type="password"
							class="form-control" id="confirmpwd" name="repassword"
							placeholder="请输入确认密码">
						</span>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
						<span class="col-sm-6"> <input type="email"
							class="form-control" id="inputEmail3" name="email"
							placeholder="Email">
						</span>
					</div>
					<div class="form-group">
						<label for="usercaption" class="col-sm-2 control-label">姓名</label>
						<span class="col-sm-6"> <input type="text"
							class="form-control" id="usercaption" name="name"
							placeholder="请输入姓名">
						</span>
					</div>
					<div class="form-group opt">
						<label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
						<span class="col-sm-6"> <label class="radio-inline">
								<input type="radio" name="sex" id="inlineRadio" value="man">
								男
						</label> <label class="radio-inline"> <input type="radio"
								name="sex" id="inlineRadio" value="woman"> 女
						</label> <labelErr for="sex" generated="true" class="error" style="display: none"></labelErr>
						</span>
					</div>
					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">出生日期</label> <span
							class="col-sm-6"> <input type="date" class="form-control"
							name="birthday">
						</span>
					</div>

					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">验证码</label> <span
							class="col-sm-3"> <input type="text" class="form-control" id="checked"
							name="checked">

						</span>
						<div class="col-sm-2">
							<img id="checkCode" src="${pageContext.request.contextPath }/checkCode" />
							<span id="spanCheck"> ${requestScope.checkCodeEror } </span>
						</div>

					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" width="100" value="注册" name="submit"
								style="background: url('./images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
						</div>
					</div>
				</form>
			</div>

			<div class="col-md-2"></div>

		</div>
	</div>

	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>




