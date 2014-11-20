<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
<title>尊宝网－特价区</title>
<link href="${domain_css}/scrollTest/greencolor/css/newmyaccount.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${domain_js}/res/feilong/js/plugins/jquery/jquery-1.4.2.js"></script>
</head>
<body>
	<div class="main">
		<div class="rollBox">
			<div class="LeftBotton"></div>
			<div class="Cont">
				<div class="ScrCont">
					<div class="div_scroolList">
						<%
							for (int i = 0; i < 20; i++){
						%>
						<!--xx元起循环-->
						<!--循环开始-->
						<div class="pic">
							<img src="${pageContext.request.contextPath}/scrollTest/greencolor/images/sales/small_EyeFi.jpg" />
							<%=i%>微波炉 <br> <font class="font3">市场价：<strike>500</strike>
							</font> <br> <font class="font4">特惠价：</font><font class="font5">450</font>
						</div>
						<%
							}
						%>
					</div>
				</div>
			</div>
			<div class="RightBotton"></div>
		</div>
		<!--
			**********************************************************************************
			-->
		<div class="rollBox">
			<div class="LeftBotton"></div>
			<div class="Cont">
				<div class="ScrCont">
					<div class="div_scroolList">
						<%
							for (int i = 0; i < 20; i++){
						%>
						<!--xx元起循环-->
						<!--循环开始-->
						<div class="pic">
							<img src="${pageContext.request.contextPath}/scrollTest/greencolor/images/sales/small_EyeFi.jpg" />
							<%=i%>微波炉 <br> <font class="font3">市场价：<strike>500</strike>
							</font> <br> <font class="font4">特惠价：</font><font class="font5">450</font>
						</div>
						<%
							}
						%>
					</div>
				</div>
			</div>
			<div class="RightBotton"></div>
		</div>
	</div>
	<script type="text/javascript" src="${domain_js}/scrollTest/greencolor/js/special_price.js"></script>
</body>
</html>