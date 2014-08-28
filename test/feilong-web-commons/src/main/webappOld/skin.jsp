<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<logic:notEmpty name="USER">
	<link rel="stylesheet" type="text/css" href="res/css/skin/1.css" id="skin"></link>
</logic:notEmpty>
<logic:empty name="USER">
	<link rel="stylesheet" type="text/css" href="res/css/skin/1.css" id="skin"></link>
</logic:empty>

