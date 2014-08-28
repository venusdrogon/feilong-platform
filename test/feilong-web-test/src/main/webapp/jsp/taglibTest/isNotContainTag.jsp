<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.feilong.commons.core.date.DateUtil"%>
<%@page import="java.util.Date"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="feilong" uri="http://java.feilong.com/tags-common"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>是否包含</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/res/feilong/css/feilong-all.css" type="text/css"></link>
<%@ include file="/res/feilong/include/css/css-gray.jsp"%>
</head>
<body>

	<%
		List<String> list = new ArrayList<String>();

		list.add("关羽");
		list.add("吕布");
		request.setAttribute("list", list);
	%>

	<feilong:isNotContain collection="关羽,吕布" value="张飞">
		bingo-string<br>
	</feilong:isNotContain>

	<feilong:isNotContain collection="${list }" value="张飞">
		bingo-list<br>
	</feilong:isNotContain>
</body>
</html>
