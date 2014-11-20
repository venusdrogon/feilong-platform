<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="feilong" uri="http://java.feilong.com/tags-common"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>验证码测试</title>
<link rel="stylesheet" href="${domain_css}/res/feilong/css/feilong-all.css" type="text/css"></link>
</head>
<body>
<%-- 
<img alt="" src="${pageContext.request.contextPath}/servlet/captcha"> --%>
<%-- /stickyImg
<img alt="" src="${pageContext.request.contextPath}/stickyImg"> --%>

<%-- /simpleCaptcha
<img alt="" src="${pageContext.request.contextPath}/simpleCaptcha"> --%>


/kaptcha
<img alt="" src="${pageContext.request.contextPath}/kaptcha">
 

</body>
</html>
