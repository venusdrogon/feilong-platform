<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<pg:index>
	<span class="a_chinesePage">总数${pageModel.maxElements}</span>
	<span class="a_chinesePage"><span class="currentPage">${pageModel.currentPageNo}</span>/<span class="allPage">${pageModel.allPageNo}</span> </span>
	<%--       每页${pageModel.maxPageItems}行  --%>
	<pg:first export="pageUrl" unless="current">
		<a class="a_chinesePage" href="${pageUrl}&pageNo=1">首页</a>
	</pg:first>
	<pg:prev export="pageUrl">
		<a class="a_chinesePage" href="${pageUrl}&pageNo=${pageModel.prePageNo}">上一页</a>
	</pg:prev>
	<pg:pages>
		<c:choose>
			<c:when test="${pageNumber == currentPageNumber}">
				<span class="activeNumber">${pageNumber}</span>
			</c:when>
			<c:otherwise>
				<a class="a_elsePageNumber" href="${pageUrl}&pageNo=${pageNumber}">${pageNumber}</a>
			</c:otherwise>
		</c:choose>
	</pg:pages>
	<pg:next export="pageUrl">
		<a class="a_chinesePage" href="${pageUrl}&pageNo=${pageModel.nextPageNo}">下一页</a>
	</pg:next>
	<pg:last export="pageUrl" unless="current">
		<a class="a_chinesePage" href="${pageUrl}&pageNo=${pageModel.allPageNo}">末页</a>
	</pg:last>
	<%--
	<pg:first export="pageUrl">
					<input type="hidden" id="ipageurl" value="${pageUrl}">
					<input type="hidden" id="maxes" value="${pageModel.allPageNo}">
				</pg:first>
				<input type="text" size="4" name="page_no" id="page_no" />
				<button type="button" id="goto_page_no">
					跳转
				</button>
	--%>
</pg:index>