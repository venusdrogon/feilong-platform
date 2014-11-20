<%@page import="com.feilong.commons.core.date.DateUtil"%>
<%@page import="java.util.Date"%>

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="feilong" uri="http://java.feilong.com/tags-common"%>

<title>飞龙首页</title>
<link rel="stylesheet" href="${domain_css}/res/feilong/css/feilong-all.css" type="text/css"></link>
<%-- <%@ include file="/res/feilong/include/css/css-gray.jsp"%> --%>
<table>
	<tr>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td></td>
	</tr>
</table>
<feilong:isInTime beginDateString="2013年3月15日 14:40:00" endDateString="2013年8月15日 14:40:10" pattern="yyyy年MM月dd日 HH:mm:ss">
 		bingo  
	</feilong:isInTime>

<%-- <feilong:isInTime beginDateString="2010年4月21日 00:00:00" endDateString="2010年4月21日 23:59:59" pattern="yyyy年MM月dd日 HH:mm:ss">
 		bingo 2010年4月21日 00:00:00+2010年4月21日 23:59:59
 	</feilong:isInTime>

 --%>
<%-- <%
		request.setAttribute("onlineTime", DateUtil.string2Date("2012年10月31日 10:00:00", "yyyy年MM月dd日 HH:mm:ss"));

		//如果现在是 2012-10-31 16:45
		Date endDate = new Date();
		request.setAttribute("beginDate", DateUtil.addDay(endDate, -31));
		request.setAttribute("endDate", endDate);
	%>

	<feilong:isInTime date="${onlineTime}" beginDate="${beginDate }" endDate="${endDate}">
		bingo
 	</feilong:isInTime> --%>
