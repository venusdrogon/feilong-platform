<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="feilongDisplay" uri="http://java.feilong.com/tags-display"%>
<%@ taglib prefix="feilong" uri="http://java.feilong.com/tags-common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
    $(function() {
	$("#main a").attr("target", "_self");
    });
</script>

<style type="text/css">
<!--
.feilongGotoInput {
	width: 25px
}
-->
</style>

<%-- <feilong:siteMap /> --%>
<form action="" method="get">
	<input type="text" name="name" value="${param.name}" />
	<button type="submit" class="btn_submit feilongButton">提交</button>
</form>

<a href="${base}/jsp/taglibTest/pager.html">24款较经典的Page翻页分页css代码</a>

<%-- 	<feilongDisplay:table styleId="feilongTable" styleClass="dataTable" id="feiLongBackWarnEntity" name="pageModel"
		property="datas" indexId="j">
		<feilongDisplay:column title="编号">${j+1}</feilongDisplay:column>
		<feilongDisplay:column title="^_^">${feiLongBackWarnEntity.description}</feilongDisplay:column>
		<feilongDisplay:column title="邮件3">${feiLongBackWarnEntity.description}</feilongDisplay:column>
		<feilongDisplay:column title="邮件4">${feiLongBackWarnEntity.description}</feilongDisplay:column>
		<feilongDisplay:column title="操作">
			<a href="#" class="a_operate_delete">删除</a>
		</feilongDisplay:column>
	</feilongDisplay:table>
	<feilongDisplay:isEmpty>
		<div class="failWarn">没有发现数据</div>
	</feilongDisplay:isEmpty> 
	${pageModel.count }--%>
<%-- ${requestScope['org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE']} --%>

<feilongDisplay:pager skin="meneame" count="10000" charsetType="utf-8" maxIndexPages="3" maxShowPageNo="100" pageParamName="page" pageSize="10"
	locale="${locale}" vmPath="velocity/feilong-default-pager.vm" pagerHtmlAttributeName="feilongPagerHtml1" />

${feilongPagerHtml1 }
<%--
		<feilonghtml:html tagType="div" styleClass="failWarn" toDecrypt="true">91770951621165951055CD44B0758DC1</feilonghtml:html>
	--%>
