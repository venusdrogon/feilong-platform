<?xml version="1.0" encoding="GBK" ?>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<title>swfobjectTest.jsp</title>

		<script type="text/javascript" src="${pageContext.request.contextPath}/res/feilong/js/plugins/swfobject-2.2.js"></script>
	</head>
	<body>
		<div id="flashcontent">
			This text is replaced by the Flash movie.
		</div>
		<script type="text/javascript">
	swfobject.embedSWF("banner.swf", "flashcontent", "740", "350", "10.0.0");
</script>
	</body>
</html>