<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员登录</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>

<script type="text/javascript">
	$(function(){
		$("#confirmOrder").click(function(){
			$("#confirmOrderForm").submit();
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
</style>
</head>

<body>
	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<div style="margin: 0 auto; margin-top: 10px; width: 950px;">
				<strong>订单详情</strong>
				<table class="table table-bordered">
					<tbody>
						<tr class="warning">
							<th colspan="5">订单编号:${sessionScope.order.oid }</th>
						</tr>
						<tr class="warning">
							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
						</tr>

						<c:forEach items="${sessionScope.order.orderitemList }"
							var="orderitem">

							<tr class="active">
								<td width="60" width="40%"><input type="hidden" name="id"
									value="22"> <img
									src="${pageContext.request.contextPath}/${orderitem.pro.pimage }"
									width="70" height="60"></td>
								<td width="30%"><a target="_blank">${orderitem.pro.pname }</a></td>
								<td width="20%">￥${orderitem.pro.shop_price}</td>
								<td width="10%">${orderitem.count}</td>
								<td width="15%"><span class="subtotal">￥${orderitem.subtotal}</span></td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>
			<div style="text-align: right; margin-right: 120px;">
				商品金额: <strong style="color: #ff6600;">￥${sessionScope.order.total }元</strong>
			</div>

		</div>

		<div>
				<!-- 对于提交到表单的数据，我们一般不加参数。而是使用隐藏字段将参数传递过去 -->
			<form class="form-horizontal" id="confirmOrderForm" action="${pageContext.request.contextPath}/productCentre" method="post"
				style="margin-top: 5px; margin-left: 150px;">
				<hr />
				
				<!-- 将表单的信息提交到servlet这个servlet实现了第三方支付平台的支付接口 这里使用隐藏域添加数据到服务端-->
				<input type="hidden" value="confirmOrder"  name="method" />
				<!-- 将本订单所属的用户id发送到服务端 -->
				<input type="hidden" value="${sessionScope.order.user.uid }"  name="uid" />
				
				<div class="form-group">
					<label for="username" class="col-sm-1 control-label">地址</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="username"
							placeholder="请输入收货地址">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-1 control-label">收货人</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="inputPassword3"
							value="${sessionScope.order.name }" placeholder="请输收货人">
					</div>
				</div>
				<div class="form-group">
					<label for="confirmpwd" class="col-sm-1 control-label">电话</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="confirmpwd"
							value="${sessionScope.order.user.email }" placeholder="请输入联系方式">
					</div>
				</div>


				<hr />

				<div style="margin-top: 5px; margin-left: 150px;">
					<strong>选择银行：</strong>
					<p>
						<br /> <input type="radio" name="pd_FrpId" value="ICBC-NET-B2C"
							checked="checked" />工商银行 <img src="./bank_img/icbc.bmp"
							align="middle" />&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio"
							name="pd_FrpId" value="BOC-NET-B2C" />中国银行 <img
							src="./bank_img/bc.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pd_FrpId" value="ABC-NET-B2C" />农业银行 <img
							src="./bank_img/abc.bmp" align="middle" /> <br /> <br /> <input
							type="radio" name="pd_FrpId" value="BOCO-NET-B2C" />交通银行 <img
							src="./bank_img/bcc.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pd_FrpId" value="PINGANBANK-NET" />平安银行
						<img src="./bank_img/pingan.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pd_FrpId" value="CCB-NET-B2C" />建设银行 <img
							src="./bank_img/ccb.bmp" align="middle" /> <br /> <br /> <input
							type="radio" name="pd_FrpId" value="CEB-NET-B2C" />光大银行 <img
							src="./bank_img/guangda.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pd_FrpId" value="CMBCHINA-NET-B2C" />招商银行
						<img src="./bank_img/cmb.bmp" align="middle" />

					</p>
					<hr />
					<p style="text-align: right; margin-right: 100px;">


						<a href="javascript:void(0)" id="confirmOrder"> <img
							src="./images/finalbutton.gif" width="204" height="51" border="0" />
						</a>


					</p>
					<hr />

				</div>
			</form>
		</div>

	</div>

	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>

</body>

</html>