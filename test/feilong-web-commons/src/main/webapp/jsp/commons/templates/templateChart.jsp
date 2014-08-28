<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<c:set var="base" value="${pageContext.request.contextPath}" scope="request"></c:set>
<c:set var="domain_css" value="http://rs.feilong.com:8888" scope="request"></c:set>
<c:set var="domain_js" value="http://rs.feilong.com:8888" scope="request"></c:set>
<c:set var="domain_image" value="http://rs.feilong.com:8888" scope="request"></c:set>

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

<link rel="shortcut icon" href="${domain_image}/favicon.ico" />

<link rel="stylesheet" href="${domain_css}/res/feilong/css/feilong-all.css?t=20140502" type="text/css"></link>



<script type="text/javascript" src="${domain_js}/res/feilong/js/plugins/jquery/jquery-1.9.1.js"></script>

<!-- amcharts -->
<script src="${domain_js}/res/feilong/js/plugins/amcharts/amcharts.js?t=20140502" type="text/javascript"></script>
<script src="${domain_js}/res/feilong/js/feilong/feilong-amcharts2.js?t=20140502" type="text/javascript"></script>

<script type="text/javascript" src="${domain_js}/res/feilong/js/plugins/jquery-pjax/jquery.pjax-1.7.2.js?t=20140502"></script>

<link rel="stylesheet" href="${base}/jsp/amchartsTest/style.css?t=20140502" type="text/css">

<style type="text/css">
#header {
	border: 1px solid #E3E197;
	background-color: #FFFFDD
}
 
#main {
/*
	border: 1px solid #F8B3D0;
	background-color: #FFF5FA ;
	
	text-align:center
	*/
}

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
	background-color: #FFFFF7
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
	<div id="container" style="width:88%">
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>


<!--  style="border:1px solid #000" -->
		<div id="main" >
			<tiles:insertAttribute name="main" />
		</div>
	</div>
</body>
</html>