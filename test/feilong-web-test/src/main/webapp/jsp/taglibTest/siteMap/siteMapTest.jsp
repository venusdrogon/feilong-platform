<%@page import="java.io.File"%>
<%@page import="com.feilong.taglib.display.breadcrumb.BreadCrumbEntity"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="feilong" uri="http://java.feilong.com/tags-common"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>siteMap test</title>
<link rel="stylesheet" href="${domain_css}/res/feilong/css/feilong-all.css" type="text/css"></link>


</head>
<body>

	<%
		List<BreadCrumbEntity> siteMapEntityList = new ArrayList<BreadCrumbEntity>();
			BreadCrumbEntity sme_1 = new BreadCrumbEntity(1, "Root1", "Root1_title", "/test1.jsp", 0);
			BreadCrumbEntity sme_2 = new BreadCrumbEntity(2, "Root2", "Root2_title", "/sku/test2.jsp", 1);
			BreadCrumbEntity sme_3 = new BreadCrumbEntity(3, "Root3", "Root3_title", "/sku/test3.jsp", 1);
			BreadCrumbEntity sme_4 = new BreadCrumbEntity(4, "Root4", "Root4_title", "/sku/sku/test4.jsp", 2);
			BreadCrumbEntity sme_5 = new BreadCrumbEntity(5, "Root5", "Root5_title", "/sku/sku/test5.jsp", 2);
			BreadCrumbEntity sme_6 = new BreadCrumbEntity(6, "Root6", "Root6_title", "/salesorder/test6.jsp", 4);
			BreadCrumbEntity sme_7 = new BreadCrumbEntity(7, "Root7", "Root7_title", "/test7.jsp", 6);
			BreadCrumbEntity sme_8 = new BreadCrumbEntity(8, "Root8", "Root8_title", "/siteMapTest.jsp", 7);
			siteMapEntityList.add(sme_1);
			siteMapEntityList.add(sme_2);
			siteMapEntityList.add(sme_3);
			siteMapEntityList.add(sme_4);
			siteMapEntityList.add(sme_5);
			siteMapEntityList.add(sme_6);
			siteMapEntityList.add(sme_7);
			siteMapEntityList.add(sme_8);
			session.setAttribute("siteMapEntityList", siteMapEntityList);
	%>
	<feilong:siteMap connector=">" siteMapEntityList="${siteMapEntityList}" />
</body>
</html>
