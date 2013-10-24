<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="feilongNetPay" uri="http://java.feilong.com/tags-netpay"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>test</title>
	</head>
	<body>
		<feilongNetPay:yicheng createTime="<%=new Date()%>" orderId="50215154" amount="0.11" callBackUrl="http://www.jumbomart.cn/index.htm?index=true" />
	</body>
</html>
