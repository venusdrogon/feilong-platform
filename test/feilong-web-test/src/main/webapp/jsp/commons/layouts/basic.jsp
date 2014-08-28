<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<c:set var="base" value="${pageContext.request.contextPath}" scope="request"></c:set>

<meta name="author" content="金鑫" />
<meta name="copyright" content="金鑫" />
<meta name="robots" content="all" />

<!-- 代表页面在当前窗口中以独立页面显示，可以防止自己的网页被别人当作一个frame页调用,设置有：_blank、_top、_self、_parent。 -->
<meta http-equiv="windows-Target" content="_top" />
<meta http-equiv="content-Type" content="text/html;charset=UTF-8" />
<meta http-equiv="content-Script-Type" Content="text/javascript" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="飞龙测试" />
<meta http-equiv="description" content="飞龙测试" />

<link rel="shortcut icon" href="${base}/favicon.ico" />

<link rel="stylesheet" href="${base}/res/feilong/css/feilong-all.css" type="text/css"></link>




<script type="text/javascript" src="${base}/res/feilong/js/plugins/jquery-pjax/jquery.pjax-1.7.2.js"></script>

<style type="text/css">
#header {
	border: 1px solid #E3E197;
	background-color: #FFFFDD
}
/* 
#main {
	border: 1px solid #F8B3D0;
	background-color: #FFF5FA
}
 */
#left {
	border: 1px solid #F8B3D0;
	background-color: #FFF5FA
}

#center {
	border: 1px solid #F8B3D0;
	background-color: #FFF5FA
}

#footer {
	border: 1px solid #FFCC00;
	background-color: #FFFFF7;
}

/*
圆角
*/
div {
	-moz-border-radius: 8px;
	-webkit-border-radius: 8px;
	border-radius: 8px;
	padding: 5px
}
</style>
</head>
<body>
	<div id="container">
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>

		<div id="main">
			<tiles:insertAttribute name="left" />
			<div id="center">
				<tiles:insertAttribute name="center" />
			</div>
		</div>

		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>