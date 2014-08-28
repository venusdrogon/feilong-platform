<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="feilong" uri="http://java.feilong.com/tags-common"%>
<%@ taglib prefix="feilongDisplay" uri="http://java.feilong.com/tags-display"%>

<c:set var="base" value="${pageContext.request.contextPath}" scope="request"></c:set>
<c:set var="domain_css" value="http://rs.feilong.com:8888" scope="request"></c:set>
<c:set var="domain_js" value="http://rs.feilong.com:8888" scope="request"></c:set>
<c:set var="domain_image" value="http://rs.feilong.com:8888" scope="request"></c:set>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta name="author" content="金鑫" />
<meta name="copyright" content="金鑫" />
<meta name="robots" content="all" />

<!-- 代表页面在当前窗口中以独立页面显示，可以防止自己的网页被别人当作一个frame页调用,设置有：_blank、_top、_self、_parent。 -->
<meta http-equiv="windows-Target" content="_top" />
<meta http-equiv="content-Type" content="text/html;charset=UTF-8" />
<meta http-equiv="content-Script-Type" Content="text/javascript" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="飞龙测试" />
<meta http-equiv="description" content="飞龙测试" />

<link rel="shortcut icon" href="${domain_image}/favicon.ico" type="image/x-icon" />

<%-- cache --%>
<%-- <tiles:importAttribute name="forbiddencache" toName="forbiddencache" scope="request" />
<c:if test="${true eq forbiddencache}">
	<meta http-equiv="Pragma" content="No-cache" />
	<meta http-equiv="Cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
</c:if> --%>

<%-- keywords description title--%>
<meta name="keywords" content="<fmt:message key="${meta_keywords}"></fmt:message>" />
<meta name="description" content="<fmt:message key="${meta_description}"></fmt:message>" />
<%-- <c:choose>
	<c:when test="${pageTitle != null}">
		<title>${pageTitle}</title>
	</c:when>
	<c:otherwise>
		<tiles:importAttribute name="title" toName="title_homepage" scope="request" />
		<title><fmt:message key="${title_homepage}"></fmt:message></title>
	</c:otherwise>
</c:choose> --%>
<%-- <tiles:insertAttribute name="meta" /> --%>


<feilong:isInTime beginDateString="2010年8月15日 00:00:00" endDateString="2010年8月15日 23:59:59" pattern="yyyy年MM月dd日 HH:mm:ss">
	<style type="text/css">
html {
	filter: progid:DXImageTransform.Microsoft.BasicImage(grayscale=1 );
}
</style>
</feilong:isInTime>

<feilong:isInTime beginDateString="2010年4月21日 00:00:00" endDateString="2010年4月21日 23:59:59" pattern="yyyy年MM月dd日 HH:mm:ss">
	<style type="text/css">
html {
	filter: progid:DXImageTransform.Microsoft.BasicImage(grayscale=1 );
}
</style>
</feilong:isInTime>



<link rel="shortcut icon" href="${domain_image }/favicon.ico" />

<link rel="stylesheet" href="${domain_css }/res/feilong/css/feilong-all.css" type="text/css"></link>

<script type="text/javascript" src="${domain_js}/res/feilong/js/plugins/jquery/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${domain_js}/res/feilong/js/feilong/feilong-style.js?t=20140502"></script>
<script type="text/javascript" src="${domain_js}/res/feilong/js/feilong/feilong-common.js?t=20140502"></script>
<script type="text/javascript" src="${domain_js}/res/feilong/js/feilong/feilong-util.js?t=20140502"></script>

<!-- esapi -->
<script type="text/javascript" src="${domain_js}/res/feilong/js/plugins/esapi/resources/i18n/ESAPI_Standard_en_US.properties.js"></script>
<script type="text/javascript" src="${domain_js}/res/feilong/js/plugins/esapi/esapi-compressed.js"></script>
<script type="text/javascript" src="${domain_js}/res/feilong/js/plugins/esapi/resources/Base.esapi.properties.js"></script>

<!-- flexigrid -->
<link rel="stylesheet" href="${domain_css}/res/feilong/js/plugins/jquery.flexigrid/css/flexigrid/flexigrid.css" type="text/css"></link>
<script type="text/javascript" src="${domain_js}/res/feilong/js/plugins/jquery.flexigrid/flexigrid.js"></script>

<!-- hiAlerts -->
<link rel="stylesheet" href="${domain_css}/res/feilong/js/plugins/jquery.hiAlerts/jquery.hiAlerts.css" type="text/css"></link>
<script type="text/javascript" src="${domain_js}/res/feilong/js/plugins/jquery.hiAlerts/jquery.hiAlerts-min.js"></script>

<!-- thickbox -->
<link rel="stylesheet" href="${domain_css}/res/feilong/js/plugins/jquery.thickbox/feilong.thickbox.css" type="text/css"></link>
<link rel="stylesheet" href="${domain_css}/res/feilong/js/plugins/jquery.thickbox/thickbox.css" type="text/css"></link>
<script type="text/javascript" src="${domain_js}/res/feilong/js/plugins/jquery.thickbox/thickbox.js"></script>

<!-- formValidator -->
<link type="text/css" rel="stylesheet" href="${domain_css}/res/feilong/js/plugins/jquery.formValidator3.1/style/validator.css"></link>
<script type="text/javascript" src="${domain_js}/res/feilong/js/plugins/jquery.formValidator3.1/formValidator.js"></script>
<script type="text/javascript" src="${domain_js}/res/feilong/js/plugins/jquery.formValidator3.1/formValidatorRegex.js"></script>
<script type="text/javascript" src="${domain_js}/res/feilong/js/plugins/jquery.formValidator3.1/DateTimeMask.js"></script>
<script type="text/javascript" src="${domain_js}/res/feilong/js/plugins/jquery.formValidator3.1/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${domain_js}/res/feilong/js/feilong/feilong-entity_formvalidator.js?t=20140502"></script>
<script type="text/javascript" src="${domain_js}/res/feilong/js/feilong/feilong-formValidator.js?t=20140502"></script>
<script type="text/javascript" src="${domain_js}/res/feilong/js/feilong/formValidator.js?t=20140502"></script>

<!-- xheditor -->
<script type="text/javascript" src="${domain_js}/res/feilong/js/plugins/jquery.xheditor-1.0.0-final/xheditor-zh-cn.min.js"></script>

<!-- pjax -->
<script type="text/javascript" src="${domain_js}/res/feilong/js/plugins/jquery-pjax/jquery.pjax-1.7.2.js"></script>

<style type="text/css">
#header {
	border: 1px solid #E3E197;
	background-color: #FFFFDD
}
/* 
#main {
	border: 1px solid #F8B3D0;
	background-color: #FFF5FA
}
 */
#left {
	border: 1px solid #F8B3D0;
	background-color: #FFF5FA
}

#center {
	border: 1px solid #F8B3D0;
	background-color: #FFF5FA
}

#footer {
	border: 1px solid #FFCC00;
	background-color: #FFFFF7;
}

/*
圆角
*/
div {
	-moz-border-radius: 8px;
	-webkit-border-radius: 8px;
	border-radius: 8px;
	padding: 5px
}
</style>
</head>
<body>
	<div id="container">
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>

		<div id="main">
			<tiles:insertAttribute name="left" />
			<div id="center">
				<tiles:insertAttribute name="center" />
			</div>
		</div>

		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>