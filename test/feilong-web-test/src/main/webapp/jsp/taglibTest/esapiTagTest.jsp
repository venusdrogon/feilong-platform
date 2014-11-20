<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="feilong" uri="http://java.feilong.com/tags-common"%>
<%@ taglib prefix="e" uri="http://www.owasp.org/index.php/Category:OWASP_Enterprise_Security_API"%>

<title>esapiTest.jsp</title>
<link rel="stylesheet" href="${domain_css}/res/feilong/css/feilong-all.css" type="text/css"></link>


<script type="text/javascript">
	var val = "<div&gt;Test</div>";
	org.owasp.esapi.ESAPI.initialize();
	//alert($ESAPI.encoder().encodeForHTML(val));
</script>
<%
	pageContext.setAttribute("test", "<div&gt;Test</div>");
	pageContext.setAttribute("test1", "%27");
%>
<e:encodeForHTML>${test}</e:encodeForHTML>
<e:encodeForHTML>${test1}</e:encodeForHTML>
