<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- Content-Type --%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="shortcut icon" href="${domain_image}/favicon.ico" type="image/x-icon" />

<%-- cache --%>
<tiles:importAttribute name="forbiddencache" toName="forbiddencache" scope="request" />
<c:if test="${true eq forbiddencache}">
	<meta http-equiv="Pragma" content="No-cache" />
	<meta http-equiv="Cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
</c:if>

<%-- keywords description title--%>
<meta name="keywords" content="<fmt:message key="${meta_keywords}"></fmt:message>" />
<meta name="description" content="<fmt:message key="${meta_description}"></fmt:message>" />
<c:choose>
	<c:when test="${pageTitle != null}">
		<title>${pageTitle}</title>
	</c:when>
	<c:otherwise>
		<tiles:importAttribute name="title" toName="title_homepage" scope="request" />
		<title><fmt:message key="${title_homepage}"></fmt:message></title>
	</c:otherwise>
</c:choose>
<tiles:insertAttribute name="meta" />

<link rel="stylesheet" href="${domain_css}/res/feilong/css/feilong-all.css" type="text/css"></link>