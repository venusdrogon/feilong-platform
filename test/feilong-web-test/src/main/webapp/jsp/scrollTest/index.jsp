<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>超级经典一套鼠标控制左右滚动图片带自动翻滚</title>
		<link href="${pageContext.request.contextPath}/greencolor/css/feilongScroll.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<span id="span_test">1</span>
		<% for(int i=0;i<5;i++){%>
		<div class="rollBox">
			<div class="LeftBotton"></div>
			<div class="Cont" >
				<div class="ScrCont">
					<div class="List1">
						<div class="pic">
							<a href="/" target="_blank"><img src=" http://www.codefans.net/jscss/demoimg/wall_s1.jpg" width="109" height="87" /> </a>
							<p>
								<a href="#" target="_blank">风景美如画</a>
							</p>
						</div>
						<div class="pic">
							<a href="/" target="_blank"><img src=" http://www.codefans.net/jscss/demoimg/wall_s2.jpg" width="109" height="87" /> </a>
							<p>
								<a href="#" target="_blank">我爱源码爱好者</a>
							</p>
						</div>
						<div class="pic">
							<a href="/" target="_blank"><img src=" http://www.codefans.net/jscss/demoimg/wall_s3.jpg" width="109" height="87" /> </a>
							<p>
								<a href="#" target="_blank">学习型源码站</a>
							</p>
						</div>
						<div class="pic">
							<a href="/" target="_blank"><img src=" http://www.codefans.net/jscss/demoimg/wall_s4.jpg" width="109" height="87" /> </a>
							<p>
								<a href="#" target="_blank">每一款都测试</a>
							</p>
						</div>
						<div class="pic">
							<a href="/" target="_blank"><img src=" http://www.codefans.net/jscss/demoimg/wall_s5.jpg" width="109" height="87" alt="你难道不喜欢" /> </a>
							<p>
								<a href="#" target="_blank">你难道不喜欢</a>
							</p>
						</div>
						<div class="pic">
							<a href="/" target="_blank"><img src=" http://www.codefans.net/jscss/demoimg/wall_s6.jpg" width="109" height="87" /> </a>
							<p>
								<a href="#" target="_blank">你太令我失望了</a>
							</p>
						</div>
						<div class="pic">
							<a href="/" target="_blank"><img src=" http://www.codefans.net/jscss/demoimg/wall_s7.jpg" width="109" height="87" /> </a>
							<p>
								<a href="#" target="_blank">今天早点睡</a>
							</p>
						</div>
					</div>
					<div class="List2"></div>
				</div>
			</div>
			<div class="RightBotton""></div>
		</div>
		<br />
		<%} %>
		<script type="text/javascript" src="${pageContext.request.contextPath}/greencolor/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/greencolor/js/feilong-scroll.js"></script>
	</body>
</html>
