<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>

<%--存放页面变量,可以直接引用--%>

<%--服务的web application 的名称--%>
<bean:define id="path" value="${pageContext.request.contextPath}"></bean:define>

<%--取得请求的URL，但不包括请求之参数字符串--%>
<bean:define id="requestURI" value="${pageContext.request.requestURI}"></bean:define>