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
		request.setAttribute("onlineTime", DateUtil.string2Date("2012年10月31日 10:00:00", "yyyy年MM月dd日 HH:mm:ss"));

		//如果现在是 2012-10-31 16:45
		Date endDate = new Date();
		request.setAttribute("beginDate", DateUtil.addDay(endDate, -31));
		request.setAttribute("endDate", endDate);
	%>

	<feilong:isContain collection="关羽,吕布,张飞" value="张飞">
		bingo
 	</feilong:isContain>
</body>
</html>
