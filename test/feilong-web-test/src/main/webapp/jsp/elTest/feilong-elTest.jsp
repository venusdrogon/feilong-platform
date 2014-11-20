<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="feilongDisplay" uri="http://java.feilong.com/tags-display"%>
<%@ taglib prefix="feilong" uri="http://java.feilong.com/tags-common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fl" uri="http://java.feilong.com/tags-el"%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>飞龙首页</title>
<link rel="stylesheet" href="${domain_css}/res/feilong/css/feilong-all.css" type="text/css"></link>

</head>
<body>

	<table style="width:400px">
		<tr>
			<td>\${fl:isContain("a,b,c","a")}:</td>
			<td>${fl:isContain("a,b,c","a")}</td>
		</tr>
		<tr>
			<td>\${fl:isContain("a,b,c","aa")}:</td>
			<td>${fl:isContain("a,b,c","aa")}</td>
		</tr>
	</table>
 
</body>
</html>
