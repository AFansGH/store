<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>网站注册页面</title>
		<style>
			#contanier{
				border: 0px solid white;
				width: 1300px;
				margin: auto;
			}
			
			#top{
				border: 0px solid white;
				width: 100%;
				height: 50px;
			}
			#menu{
				border: 0px solid white;
				height: 40px;
				background-color: black;
				padding-top: 10px;
				margin-bottom: 15px;
				margin-top: 10px;
			}
			.top{
				border: 0px solid white;
				width: 405px;
				height: 100%;
				float: left;
				padding-left: 25px;
			}
			#top1{
				padding-top: 15px;
			}
			#bottom{
				margin-top: 13px;
				text-align: center;
			}
			
			#form{
				height: 500px;
				padding-top: 70px;
				background-image: url(../img/regist_bg.jpg);
				margin-bottom: 10px;
			}
			a{
				text-decoration: none;
			}
			
			label.error{
				background:url(../img/unchecked.gif) no-repeat 10px 3px;
				padding-left: 30px;
				font-family:georgia;
				font-size: 15px;
				font-style: normal;
				color: red;
			}
			
			label.success{
				background:url(../img/checked.gif) no-repeat 10px 3px;
				padding-left: 30px;
				color: deepskyblue;
			}
			
			#father{
				border: 0px solid white;
				padding-left: 307px;
			}
			
			#form2{
				border: 5px solid gray;
				width: 660px;
				height: 450px;
			}
			
		</style>
		<script type="text/javascript" src="../js/jquery-1.8.3.js" ></script>
		<script type="text/javascript" src="../js/jquery.validate.min.js" ></script>
		<script type="text/javascript" src="../js/messages_zh.js" ></script>
		<script>
			$(function(){
				$("#registForm").validate({
					rules :{
						user:{
							required:true,
							minlength:5
						},
						password:{
							required:true,
							minlength:6
						},
						sex:{
							required:true,
						}
					},
					messages:{
						user:{
							required:"用户名不能为空",
							minlength:"用户名至少为5个字段"
						},
						password:{
							required:"密码不能为空",
							minlength:"密码至少6位"
						},
						sex:{
							required:"性别必须勾选",
						}
					},
					errorElement:"label",		//label是validate的默认标签，失败了就显示这个标签 默认是不可见的
					success: function(label){	//验证成功绑定的方法
						label.text("通过");			//将label的内容清空
						$(label).addClass("success");	//所有的label加上一个类名，显示不同的风格
					}
				});
			});
		</script>
		
		
		
	</head>
	<body>
		<div id="contanier">
			<div id="top">
				<div class="top">
					<img src="../img/logo2.png" height="47px"/>
				</div>
				<div class="top">
					<img src="../img/header.png" height="45px" />
				</div>
				<div class="top" id="top1">
					<a href="#">登录</a>
					<a href="#">注册</a>
					<a href="#">购物车</a>
				</div>
			</div>
			<div id="menu">
				<a href="#"><font size="5" color="white">首页</font></a>&nbsp;&nbsp;&nbsp;&nbsp;         
				<a href="#"><font color="white">电脑办公</font></a>&nbsp;&nbsp;&nbsp;&nbsp;  
				<a href="#"><font color="white">手机数码</font></a>&nbsp;&nbsp;&nbsp;&nbsp;  
				<a href="#"><font color="white">鞋靴箱包</font></a>	
			</div>
			<div id="form">
				<form action="#" method="get" id="registForm">
					<div id="father">
						<div id="form2">
							<table border="0px" width="100%" height="100%" align="center" cellpadding="0px" cellspacing="0px" bgcolor="white">
								<tr>
									<td colspan="2" >
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<font size="5">会员注册</font>&nbsp;&nbsp;&nbsp;USER REGISTER 
									</td>
								</tr>
								<tr>
									<td width="180px">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;
										<label for="user" >用户名</label>
									</td>
									<td>
										<em style="color: red;">*</em>&nbsp;&nbsp;&nbsp;<input type="text" name="user" size="35px" id="user"/>
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;
										密码
									</td>
									<td>
										<em style="color: red;">*</em>&nbsp;&nbsp;&nbsp;<input type="password"  name="password" size="35px" id="password" />
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;
										确认密码
									</td>
									<td>
										<em style="color: red;">*</em>&nbsp;&nbsp;&nbsp;<input type="password" name="repassword" size="35px"/>
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;
										Email
									</td>
									<td>
										<em style="color: red;">*</em>&nbsp;&nbsp;&nbsp;<input type="text" name="email" size="35px" id="email"/>
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;
										姓名
									</td>
									<td>
										<em style="color: red;">*</em>&nbsp;&nbsp;&nbsp;<input type="text" name="username" size="35px"/>
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;
										性别
									</td>
									<td>
										<span style="margin-right: 155px;">
											<em style="color: red;">*</em>&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="男"/>男
											<input type="radio" name="sex" value="女"/>女<em></em>
										</span>
										<label for="sex" class="error" style="display: none;"></label>
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;
										出生日期
									</td>
									<td>
										<em style="color: red;">*</em>&nbsp;&nbsp;&nbsp;<input type="text" name="birthday"  size="35px"/>
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;
										验证码
									</td>
									<td>
										<em style="color: red;">*</em>&nbsp;&nbsp;&nbsp;<input type="text" name="yanzhengma" />
										<img src="../img/yanzhengma.png" style="height: 18px;width: 85px;"/>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="submit" value="注      册" height="50px"/>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</form>
			</div>
			<div>
				<img src="../img/footer.jpg"  width="100%"/>
			</div>
			<div id="bottom">
				<a href="../案例一：网站信息显示页面/网站信息显示页面.html">关于我们</a>
				<a href="#">联系我们</a>
				<a href="#">招贤纳士</a>
				<a href="#">法律声明</a>
				<a href="../案例三：网站友情链接显示页面/网站友情链接显示页面.html">友情链接</a>
				<a href="#">支付方式</a>
				<a href="#">配送方式</a>
				<a href="#">服务声明</a>
				<a href="#">广告声明</a>
				<p>
					Copyright © 2005-2016 传智商城 版权所有 
				</p>
			</div>
		</div>
	</body>
</html>
