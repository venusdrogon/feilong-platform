<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="java.util.Enumeration"%>

<%@ taglib prefix="feilongDisplay" uri="http://java.feilong.com/tags-display"%>
<%@ taglib prefix="feilong" uri="http://java.feilong.com/tags-common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fl" uri="http://java.feilong.com/tags-el"%>

<style type="text/css">
td {
	font-size: 14px
}

table .td_title {
	text-align: left;
	font-size: 15px;
	width: 250px;
}
</style>

<table class="dataTable">
	<tr>
		<td class="td_title">request.getPathInfo():</td>
		<td>
			<%
				out.print(request.getPathInfo());
			%>
		</td>
		<td>相对于servlet的路径</td>
	</tr>
	<tr>
		<td class="td_title">request.getServletPath():</td>
		<td>
			<%
				out.print(request.getServletPath());
			%>
		</td>
		<td></td>
	</tr>
	<tr>
		<td class="td_title">request.getQueryString():</td>
		<td>
			<%
				out.print(request.getQueryString());
			%>
		</td>
		<td>查询参数</td>
	</tr>
	<tr>
		<td class="td_title">request.getContextPath():</td>
		<td>
			<%
				out.print(request.getContextPath());
			%>
		</td>
		<td></td>
	</tr>

	<tr>
		<td class="td_title">request.getRequestURL():</td>
		<td>
			<%
				out.print(request.getRequestURL());
			%>
		</td>
		<td></td>
	</tr>
	<tr>
		<td class="td_title">request.getRequestURI():</td>
		<td>
			<%
				out.print(request.getRequestURI());
			%>
		</td>
		<td></td>
	</tr>
	<tr>
		<td class="td_title">request.getRealPath():</td>
		<td>
			<%
				out.print(request.getRealPath("baiduzhidaoTest.jsp"));
			%>
		</td>
		<td>文件全路径</td>
	</tr>
	<tr>
		<td class="td_title">request.getAuthType</td>
		<td>
			<%
				out.print(request.getAuthType());
			%>
		</td>
		<td></td>
	</tr>

	<tr>
		<td class="td_title">request.getCharacterEncoding</td>
		<td>
			<%
				out.print(request.getCharacterEncoding());
			%>
		</td>
		<td></td>
	</tr>
	<tr>
		<td class="td_title">request.getContentLength</td>
		<td>
			<%
				out.print(request.getContentLength());
			%>
		</td>
		<td></td>
	</tr>
	<tr>
		<td class="td_title">request.getLocalAddr</td>
		<td>
			<%
				out.print(request.getLocalAddr());
			%>
		</td>
		<td></td>
	</tr>
	<tr>
		<td class="td_title">request.getLocalName</td>
		<td>
			<%
				out.print(request.getLocalName());
			%>
		</td>
		<td></td>
	</tr>
	<tr>
		<td class="td_title">request.getLocalPort</td>
		<td>
			<%
				out.print(request.getLocalPort());
			%>
		</td>
		<td></td>
	</tr>
	<tr>
		<td class="td_title">request.getLocale</td>
		<td>
			<%
				out.print(request.getLocale());
			%>
		</td>
		<td></td>
	</tr>
	<tr>
		<td class="td_title">request.getLocales</td>
		<td>
			<%
				out.print(request.getLocales());
			%>
		</td>
		<td></td>
	</tr>
	<tr>
		<td class="td_title">request.getMethod</td>
		<td>
			<%
				out.print(request.getMethod());
			%>
		</td>
		<td></td>
	</tr>
	<tr>
		<td class="td_title">request.getPathTranslated</td>
		<td>
			<%
				out.print(request.getPathTranslated());
			%>
		</td>
		<td></td>
	</tr>



	<tr>
		<td class="td_title">request.getProtocol</td>
		<td>
			<%
				out.print(request.getProtocol());
			%>
		</td>
		<td></td>
	</tr>
	<tr>
		<td class="td_title">request.getRemoteAddr</td>
		<td>
			<%
				out.print(request.getRemoteAddr());
			%>
		</td>
		<td></td>
	</tr>
	<tr>
		<td class="td_title">request.getRemoteHost</td>
		<td>
			<%
				out.print(request.getRemoteHost());
			%>
		</td>
		<td></td>
	</tr>
	<tr>
		<td class="td_title">request.getRemoteUser</td>
		<td>
			<%
				out.print(request.getRemoteUser());
			%>
		</td>
		<td></td>
	</tr>
	<tr>
		<td class="td_title">request.getRequestedSessionId</td>
		<td>
			<%
				out.print(request.getRequestedSessionId());
			%>
		</td>
		<td></td>
	</tr>
	<tr>
		<td class="td_title">request.getScheme</td>
		<td>
			<%
				out.print(request.getScheme());
			%>
		</td>
		<td></td>
	</tr>
	<tr>
		<td class="td_title">request.getServerName</td>
		<td>
			<%
				out.print(request.getServerName());
			%>
		</td>
		<td></td>
	</tr>
	<tr>
		<td class="td_title">request.getServerPort</td>
		<td>
			<%
				out.print(request.getServerPort());
			%>
		</td>
		<td></td>
	</tr>
</table>

<div class="successWarn">request HeaderNames:</div>

<table class="dataTable">
	<%
		Enumeration enumeration = request.getHeaderNames();

		while (enumeration.hasMoreElements()){

			String name = enumeration.nextElement().toString();
	%>
	<tr>
		<td class="td_title">
			<%
				out.print(name);
			%>
		</td>
		<td>
			<%
				String value = request.getHeader(name);

					if (name.equalsIgnoreCase("cookie")){

						Cookie[] cookies = request.getCookies();
			%>
			<table>
				<tr>
					<td>Name</td>
					<td>Domain</td>
					<td>MaxAge</td>
					<td>Comment</td>
					<td>Path</td>
					<td>Secure</td>
					<td>Version</td>
					<td>Value</td>
				</tr>
				<%
					for (int i = 0; i < cookies.length; i++){
								Cookie cookie = cookies[i];
				%>

				<tr>
					<td><%=cookie.getName()%></td>
					<td><%=cookie.getDomain()%></td>
					<td><%=cookie.getMaxAge()%></td>
					<td><%=cookie.getComment()%></td>
					<td><%=cookie.getPath()%></td>
					<td><%=cookie.getSecure()%></td>
					<td><%=cookie.getVersion()%></td>
					<td><span style="text-overflow: clip; width: 100px;"><%=cookie.getValue()%></span></td>
				</tr>

				<%
					}
				%>
			</table> <%
 	}else{
 			out.print(value);
 		}
 %>
		</td>
		<td></td>

	</tr>
	<%
		}
	%>

</table>

