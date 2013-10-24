/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package com.feilong.servlet.http;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.Constants;
import com.feilong.commons.core.net.URIUtil;
import com.feilong.commons.core.util.JsonFormatUtil;
import com.feilong.commons.core.util.ObjectUtil;
import com.feilong.commons.core.util.Validator;

/**
 * HttpServletRequest工具类
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-11-3 下午02:24:55
 */
public final class RequestUtil{

	private static final Logger	log	= LoggerFactory.getLogger(RequestUtil.class);

	private RequestUtil(){};

	// ************************************include**************************************************************************
	/**
	 * <code>{@value}</code> <br>
	 * Standard Servlet 2.3+ spec request attributes for include URI and paths.
	 * <p>
	 * If included via a RequestDispatcher, the current resource will see the originating request. <br>
	 * Its own URI and paths are exposed as request attributes.
	 */
	public static final String	INCLUDE_REQUEST_URI_ATTRIBUTE		= "javax.servlet.include.request_uri";

	/**
	 * <code>{@value}</code><br>
	 * The request attribute under which the context path of the included servlet is stored on an included dispatcher request.
	 */
	public static final String	INCLUDE_CONTEXT_PATH_ATTRIBUTE		= "javax.servlet.include.context_path";

	/**
	 * <code>{@value}</code><br>
	 * The request attribute under which the servlet path of the included servlet is stored on an included dispatcher request.
	 */
	public static final String	INCLUDE_SERVLET_PATH_ATTRIBUTE		= "javax.servlet.include.servlet_path";

	/**
	 * <code>{@value}</code><br>
	 * The request attribute under which the path info of the included servlet is stored on an included dispatcher request.
	 */
	public static final String	INCLUDE_PATH_INFO_ATTRIBUTE			= "javax.servlet.include.path_info";

	/**
	 * <code>{@value}</code><br>
	 * The request attribute under which the query string of the included servlet is stored on an included dispatcher request
	 */
	public static final String	INCLUDE_QUERY_STRING_ATTRIBUTE		= "javax.servlet.include.query_string";

	// *********************************forward******************************************************************************/

	/**
	 * <code>{@value}</code><br>
	 * Standard Servlet 2.4+ spec request attributes for forward URI and paths.
	 * <p>
	 * If forwarded to via a RequestDispatcher, the current resource will see its own URI and paths.<br>
	 * The originating URI and paths are exposed as request attributes.<br>
	 * The request attribute under which the original request URI is stored on an forwarded dispatcher request.
	 */
	public static final String	FORWARD_REQUEST_URI_ATTRIBUTE		= "javax.servlet.forward.request_uri";

	/**
	 * <code>{@value}</code><br>
	 * The request attribute under which the original context path is stored on an forwarded dispatcher request.
	 */
	public static final String	FORWARD_CONTEXT_PATH_ATTRIBUTE		= "javax.servlet.forward.context_path";

	/**
	 * <code>{@value}</code><br>
	 * The request attribute under which the original servlet path is stored on an forwarded dispatcher request.
	 */
	public static final String	FORWARD_SERVLET_PATH_ATTRIBUTE		= "javax.servlet.forward.servlet_path";

	/**
	 * <code>{@value}</code><br>
	 * The request attribute under which the original path info is stored on an forwarded dispatcher request.
	 */
	public static final String	FORWARD_PATH_INFO_ATTRIBUTE			= "javax.servlet.forward.path_info";

	/**
	 * <code>{@value}</code><br>
	 * The request attribute under which the original query string is stored on an forwarded dispatcher request.
	 */
	public static final String	FORWARD_QUERY_STRING_ATTRIBUTE		= "javax.servlet.forward.query_string";

	// *********************************error******************************************************************************/
	/**
	 * <code>{@value}</code><br>
	 * Standard Servlet 2.3+ spec request attributes for error pages.
	 * <p>
	 * To be exposed to JSPs that are marked as error pages,<br>
	 * when forwarding to them directly rather than through the servlet container's error page resolution mechanism. <br>
	 * The request attribute under which we forward an HTTP status code (as an object of type Integer) to an error page.
	 */
	public static final String	ERROR_STATUS_CODE_ATTRIBUTE			= "javax.servlet.error.status_code";

	/**
	 * <code>{@value}</code><br>
	 * The request attribute under which we forward a Java exception type (as an object of type Class) to an error page.
	 */
	public static final String	ERROR_EXCEPTION_TYPE_ATTRIBUTE		= "javax.servlet.error.exception_type";

	/**
	 * <code>{@value}</code><br>
	 * The request attribute under which we forward an HTTP status message (as an object of type STring) to an error page.
	 */
	public static final String	ERROR_MESSAGE_ATTRIBUTE				= "javax.servlet.error.message";

	/**
	 * <code>{@value}</code><br>
	 * The request attribute under which we forward a Java exception (as an object of type Throwable) to an error page.
	 */
	public static final String	ERROR_EXCEPTION_ATTRIBUTE			= "javax.servlet.error.exception";

	/**
	 * <code>{@value}</code><br>
	 * The request attribute under which we forward the request URI (as an object of type String) of the page on which an error occurred.
	 */
	public static final String	ERROR_REQUEST_URI_ATTRIBUTE			= "javax.servlet.error.request_uri";

	/**
	 * <code>{@value}</code><br>
	 * The request attribute under which we forward a servlet name to an error page.
	 */
	public static final String	ERROR_SERVLET_NAME_ATTRIBUTE		= "javax.servlet.error.servlet_name";

	// ****************************************header**************************************************************************
	public static final String	header_referer						= "referer";

	public static final String	header_userAgent					= "User-Agent";

	/**
	 * X-Forwarded-For:简称XFF头，它代表客户端，也就是HTTP的请求端真实的IP，只有在通过了HTTP 代理或者负载均衡服务器时才会添加该项。<br>
	 * 它不是RFC中定义的标准请求头信息，在squid缓存代理服务器开发文档中可以找到该项的详细介绍。 <br>
	 * 标准格式如下：<br>
	 * X-Forwarded-For: client1, proxy1, proxy2
	 */
	public static final String	header_xForwardedFor				= "x-forwarded-for";

	public static final String	header_proxyClientIP				= "Proxy-Client-IP";

	/**
	 * WL-Proxy-Client-IP 这个应该是WebLogic前置HttpClusterServlet提供的属性，一般不需要自己处理，在WebLogic控制台中已经可以指定使用这个属性来覆盖
	 */
	public static final String	header_wLProxyClientIP				= "WL-Proxy-Client-IP";

	public static final String	header_XRequestedWith				= "X-Requested-With";

	public static final String	header_XRequestedWith_value_ajax	= "XMLHttpRequest";

	// ******************************是否包含******************************************
	/**
	 * 请求路径中是否包含某个参数名称 (注意:这是判断是否包含参数,而不是判断参数值是否为空)
	 * 
	 * <pre>
	 * 如果传递来的param为null或者&quot;&quot;.则返回false
	 * 
	 * </pre>
	 * 
	 * @param request
	 *            请求
	 * @param param
	 *            参数名称
	 * @return 包含该参数返回true,不包含返回false
	 */
	public static boolean isContainsParam(HttpServletRequest request,String param){
		if (Validator.isNotNullOrEmpty(param)){
			Map<String, String> map = getParameterMap(request);
			return map.containsKey(param);
		}
		return false;
	}

	/**
	 * 请求路径中是否包含某个参数名称 (注意:这是判断是否包含参数,而不是判断参数值是否为空)
	 * 
	 * @param request
	 *            请求
	 * @param param
	 *            参数名称
	 * @return 不包含该参数返回true
	 */
	public static boolean isNotContainsParam(HttpServletRequest request,String param){
		return !isContainsParam(request, param);
	}

	/**
	 * 获得参数 map
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getParameterMap(HttpServletRequest request){
		@SuppressWarnings("unchecked")
		Map<String, String> map = request.getParameterMap();
		// http://localhost:8888/s.htm?keyword&a=
		// 这种链接
		// map key 会是 keyword,a 值都是空
		return map;
	}

	/************************************************************************************************************/

	/**
	 * 获得request 错误日志<br>
	 * 如果 errorCode有 返回json 格式日志,否则返回空
	 * 
	 * @param request
	 * @deprecated use getErrorMap
	 */
	public static String getErrorLog(HttpServletRequest request){
		Map<String, String> map = getErrorMap(request);
		return JsonFormatUtil.format(map);
	}

	/**
	 * 遍历显示request的attribute 用于debug
	 * 
	 * @param request
	 * @deprecated use getAttributeMap
	 */
	public static void showAttributes(HttpServletRequest request){
		if (log.isDebugEnabled()){
			Map<String, Object> map = getAttributeMap(request);
			log.debug("request attributeMap:{}", JsonFormatUtil.format(map));
		}
	}

	/**
	 * 遍历显示request的header 用于debug
	 * 
	 * @param request
	 * @deprecated use getHeaderMap
	 */
	public static void showHeaders(HttpServletRequest request){
		if (log.isDebugEnabled()){
			Map<String, String> map = getHeaderMap(request);
			log.debug("request HeaderMap:{}", JsonFormatUtil.format(map));
		}
	}

	/**
	 * 获得request error 相关参数 map
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return 如果request 有 {@link #ERROR_STATUS_CODE_ATTRIBUTE}属性,则返回error 相关属性 封装到map,<br>
	 *         如果 request没有 {@link #ERROR_STATUS_CODE_ATTRIBUTE}属性,返回null
	 */
	public static Map<String, String> getErrorMap(HttpServletRequest request){
		String errorCode = getAttributeToString(request, ERROR_STATUS_CODE_ATTRIBUTE);
		if (Validator.isNotNullOrEmpty(errorCode)){
			Map<String, String> map = new LinkedHashMap<String, String>();
			map.put(ERROR_STATUS_CODE_ATTRIBUTE, errorCode);
			map.put(ERROR_REQUEST_URI_ATTRIBUTE, getAttributeToString(request, ERROR_REQUEST_URI_ATTRIBUTE));
			map.put(ERROR_EXCEPTION_ATTRIBUTE, getAttributeToString(request, ERROR_EXCEPTION_ATTRIBUTE));
			map.put(ERROR_EXCEPTION_TYPE_ATTRIBUTE, getAttributeToString(request, ERROR_EXCEPTION_TYPE_ATTRIBUTE));
			map.put(ERROR_MESSAGE_ATTRIBUTE, getAttributeToString(request, ERROR_MESSAGE_ATTRIBUTE));
			map.put(ERROR_SERVLET_NAME_ATTRIBUTE, getAttributeToString(request, ERROR_SERVLET_NAME_ATTRIBUTE));
			return map;
		}
		return null;
	}

	/**
	 * 遍历显示request的attribute,将 name /attributeValue 存入到map
	 * 
	 * @param request
	 */
	public static Map<String, Object> getAttributeMap(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		@SuppressWarnings("unchecked")
		Enumeration<String> attributeNames = request.getAttributeNames();
		while (attributeNames.hasMoreElements()){
			String name = attributeNames.nextElement();
			Object attributeValue = request.getAttribute(name);
			map.put(name, attributeValue);
		}
		return map;
	}

	/**
	 * 将request 相关 get 数据 转成 \n 分隔的 String,以便log 显示
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestStringForLog(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("request.getAuthType()", request.getAuthType());
		map.put("request.getCharacterEncoding()", request.getCharacterEncoding());
		map.put("request.getContentLength()", request.getContentLength());
		map.put("request.getContextPath()", request.getContextPath());
		map.put("request.getLocalAddr()", request.getLocalAddr());
		map.put("request.getLocale()", request.getLocale());
		map.put("request.getLocalName()", request.getLocalName());
		map.put("request.getLocalPort()", request.getLocalPort());
		map.put("request.getMethod()", request.getMethod());
		map.put("request.getPathInfo()", request.getPathInfo());
		map.put("request.getPathTranslated()", request.getPathTranslated());
		map.put("request.getProtocol()", request.getProtocol());
		map.put("request.getQueryString()", request.getQueryString());
		map.put("request.getRemoteAddr()", request.getRemoteAddr());
		map.put("request.getRemoteHost()", request.getRemoteHost());
		map.put("request.getRemoteUser()", request.getRemoteUser());
		map.put("request.getRemotePort()", request.getRemotePort());
		map.put("request.getRequestedSessionId()", request.getRequestedSessionId());
		map.put("request.getRequestURI()", request.getRequestURI());
		map.put("request.getRequestURL()", request.getRequestURL());
		map.put("request.getScheme()", request.getScheme());
		map.put("request.getServerName()", request.getServerName());
		map.put("request.getServerPort()", request.getServerPort());
		map.put("request.getServletPath()", request.getServletPath());
		return JsonFormatUtil.format(map);
	}

	/**
	 * 遍历显示request的header 用于debug<br>
	 * 将 request header name 和value 封装到map
	 * 
	 * @param request
	 */
	public static Map<String, String> getHeaderMap(HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>();
		@SuppressWarnings("unchecked")
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()){
			String name = headerNames.nextElement();
			String value = request.getHeader(name);
			map.put(name, value);
		}
		return map;
	}

	/** ******************************* url参数相关 getAttribute***************************************************** */
	// [start] url参数相关
	/**
	 * 获得requst属性值,并将其转换成int类型
	 * 
	 * @param request
	 *            请求
	 * @param name
	 *            参数名
	 * @return 获得requst属性值,并将其转换成int类型
	 */
	public final static Integer getAttributeToInt(HttpServletRequest request,String name){
		Object value = getAttribute(request, name);
		return ObjectUtil.toInteger(value);
	}

	/**
	 * 取到request里面的属性值
	 * 
	 * @param request
	 *            请求
	 * @param name
	 *            属性名称
	 * @return 取到request里面的属性值
	 */
	public final static Object getAttribute(HttpServletRequest request,String name){
		return request.getAttribute(name);
	}

	/**
	 * 取到request里面的属性值
	 * 
	 * @param request
	 *            请求
	 * @param name
	 *            属性名称
	 * @return 取到request里面的属性值
	 */
	public final static String getAttributeToString(HttpServletRequest request,String name){
		Object value = request.getAttribute(name);
		return ObjectUtil.toString(value);
	}

	/**
	 * 获得请求的?部分前面的地址<br>
	 * 识别 request 是否 forword
	 * 
	 * <pre>
	 * 如:http://localhost:8080/feilong/requestdemo.jsp?id=2
	 * 返回:http://localhost:8080/feilong/requestdemo.jsp
	 * 
	 * 注:
	 * 	 request.getRequestURI() 返回值类似：/feilong/requestdemo.jsp
	 * 	 request.getRequestURL() 返回值类似：http://localhost:8080/feilong/requestdemo.jsp
	 * </pre>
	 * 
	 * @param request
	 * @return 获得请求的?部分前面的地址
	 */
	public final static String getRequestURL(HttpServletRequest request){
		String forward_request_uri = (String) request.getAttribute(FORWARD_REQUEST_URI_ATTRIBUTE);
		if (Validator.isNotNullOrEmpty(forward_request_uri)){
			return forward_request_uri;
		}
		return request.getRequestURL().toString();
	}

	/**
	 * Return the servlet path for the given request, detecting an include request URL if called within a RequestDispatcher include.
	 * 
	 * @param request
	 *            current HTTP request
	 * @return the servlet path
	 */
	public static String getOriginatingServletPath(HttpServletRequest request){
		String servletPath = (String) request.getAttribute(FORWARD_SERVLET_PATH_ATTRIBUTE);
		if (servletPath == null){
			servletPath = request.getServletPath();
		}
		return servletPath;
	}

	/**
	 * 获得请求的全地址
	 * 
	 * @param request
	 * @return 如:http://localhost:8080/feilong/requestdemo.jsp?id=2
	 */
	public final static String getRequestAllURL(HttpServletRequest request){
		String queryString = request.getQueryString();
		String returnValue = "";
		if (Validator.isNullOrEmpty(queryString)){
			returnValue = getRequestURL(request);
		}else{
			returnValue = getRequestURL(request) + "?" + URIUtil.decodeLuanMa_ISO8859(queryString);
		}
		return returnValue;
	}

	/**
	 * scheme+port+getContextPath <br>
	 * 区分 http 和https
	 * 
	 * @param request
	 * @return
	 */
	public final static String getServerRootWithContextPath(HttpServletRequest request){

		StringBuffer url = new StringBuffer();
		String scheme = request.getScheme();

		url.append(scheme);
		url.append("://");
		url.append(request.getServerName());

		int port = request.getServerPort();
		if (port < 0){
			port = 80; // Work around java.net.URL bug
		}
		if ((scheme.equals("http") && (port != 80)) || (scheme.equals("https") && (port != 443))){
			url.append(':');
			url.append(port);
		}

		url.append(request.getContextPath());
		return url.toString();
	}

	// [end]
	// ***************************************LocalAddr******************************************************************************

	/**
	 * 是否是本地ip(测试)
	 * 
	 * @param request
	 * @return 是否是本地ip(测试)
	 */
	public final static boolean isLocalHost(HttpServletRequest request){
		return Constants.localhostIp.equals(getLocalAddr(request));
	}

	/**
	 * 获得项目本地ip地址
	 * 
	 * @param request
	 * @return Returns the Internet Protocol (IP) address of the interface on which the request was received.
	 */
	public final static String getLocalAddr(HttpServletRequest request){
		return request.getLocalAddr();
	}

	// ***************************************Header******************************************************************************

	/**
	 * 获得客户端ip地址
	 * 
	 * @param request
	 * @return 获得客户端ip地址
	 */
	public final static String getClientIp(HttpServletRequest request){
		// WL-Proxy-Client-IP=215.4.1.29
		// Proxy-Client-IP=215.4.1.29
		// X-Forwarded-For=215.4.1.29
		// WL-Proxy-Client-Keysize=
		// WL-Proxy-Client-Secretkeysize=
		// X-WebLogic-Request-ClusterInfo=true
		// X-WebLogic-KeepAliveSecs=30
		// X-WebLogic-Force-JVMID=-527489098
		// WL-Proxy-SSL=false
		String unknown = "unknown";

		String wrap = "\n\t\t";
		StringBuilder logBuilder = new StringBuilder();
		// 是否使用反向代理
		String ipAddress = request.getHeader(header_xForwardedFor);
		logBuilder.append(wrap + "header_xForwardedFor:" + ipAddress);
		if (Validator.isNullOrEmpty(ipAddress) || unknown.equalsIgnoreCase(ipAddress)){
			ipAddress = request.getHeader(header_proxyClientIP);
			logBuilder.append(wrap + "header_proxyClientIP:" + ipAddress);
		}
		if (Validator.isNullOrEmpty(ipAddress) || unknown.equalsIgnoreCase(ipAddress)){
			ipAddress = request.getHeader(header_wLProxyClientIP);
			logBuilder.append(wrap + "header_wLProxyClientIP:" + ipAddress);
		}
		if (Validator.isNullOrEmpty(ipAddress) || unknown.equalsIgnoreCase(ipAddress)){
			ipAddress = request.getRemoteAddr();
			logBuilder.append(wrap + "request.getRemoteAddr():" + ipAddress);
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15){ // "***.***.***.***".length() = 15
			logBuilder.append(wrap + "all:" + ipAddress);
			if (ipAddress.indexOf(",") > 0){
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		log.debug(logBuilder.toString());
		return ipAddress;
	}

	/**
	 * 　User Agent中文名为用户代理，简称 UA，<br>
	 * 它是一个特殊字符串头，使得服务器能够识别客户使用的操作系统及版本、CPU 类型、浏览器及版本、浏览器渲染引擎、浏览器语言、浏览器插件等。
	 * 
	 * @param request
	 * @return
	 */
	public final static String getUserAgent(HttpServletRequest request){
		return request.getHeader(header_userAgent);
	}

	/**
	 * 获得上上个请求的URL
	 * 
	 * <pre>
	 * 请用于常规请求,必须走http协议才有值,javascript跳转无效
	 * 以下情况请慎用:
	 * 	  也就是说要通过&lt;a href=&quot;url&quot;&gt;sss&lt;/a&gt;标记才能获得那个值   
	 * 	而通过改变location或是&lt;a href=&quot;javascript:location='url'&quot;&gt;sss&lt;/a&gt;都是得不到那个值得
	 * 
	 * referer是浏览器在用户提交请求当前页面中的一个链接时,将当前页面的URL放在头域中提交给服务端的,如当前页面为a.html,
	 * 它里面有一个b.html的链接,当用户要访问b.html时浏览器就会把a.html作为referer发给服务端.
	 * 
	 * <ul><li>
	 * 有嵌入iframe
	 * </li></ul>
	 * 
	 * </pre>
	 * 
	 * @param request
	 * @return 上上个请求的URL
	 */
	public final static String getReferer(HttpServletRequest request){
		return request.getHeader(header_referer);
	}

	/**
	 * 判断一个请求 是否是ajax 请求<br>
	 * 注:x-requested-with这个头是某些JS类库给加上去的，直接写AJAX是没有这个头的,<br>
	 * jquery/ext 确定添加,暂时可以使用这个来判断<br>
	 * <br>
	 * The X-Requested-With is a non-standard HTTP header which is mainly used to identify Ajax requests. <br>
	 * Most JavaScript frameworks send this header with value of XMLHttpRequest.
	 * 
	 * @param request
	 * @return 如果是ajax 请求 返回true
	 * @see http://en.wikipedia.org/wiki/X-Requested-With#Requested-With
	 */
	public final static boolean isAjaxRequest(HttpServletRequest request){
		String header = request.getHeader(header_XRequestedWith);
		if (Validator.isNotNullOrEmpty(header) && header.equalsIgnoreCase(header_XRequestedWith_value_ajax)){
			return true;
		}
		return false;
	}

	/**
	 * 判断一个请求 ,不是ajax 请求
	 * 
	 * @param request
	 * @return 如果不是ajax 返回true
	 */
	public final static boolean isNotAjaxRequest(HttpServletRequest request){
		return !isAjaxRequest(request);
	}

	// /**
	// * 将collection转成map
	// *
	// * <pre>
	// * 暂时仅支持 list及数组
	// *
	// * </pre>
	// *
	// * @param map
	// * @param scopeOptions
	// * 作用域集合名称
	// * @param scopeLabel
	// * 显示,如果为空,则默认为scopeOptions的名称去掉后缀List 自动加上Name
	// * @param scopeValue
	// * 值,如果为空,则默认为scopeOptions的名称去掉后缀List 自动加上Id
	// * @param request
	// * @return map
	// */
	// @SuppressWarnings("rawtypes")
	// public final static Map<String, String> collectionToMap(
	// Map<String, String> map,
	// String scopeOptions,
	// String scopeLabel,
	// String scopeValue,
	// HttpServletRequest request){
	// if (null == map){
	// map = new LinkedHashMap<String, String>();
	// }
	// String s_label = "";
	// String s_value = "";
	// Object optionCollection = request.getAttribute(scopeOptions);
	// // optionList不是null
	// if (Validator.isNotNullOrEmpty(optionCollection)){
	// // 集合
	// if (optionCollection instanceof List){
	// // 判断scopeOptions以List结尾
	// String specialCharacters = "List";
	// boolean isEndsWithListString = scopeOptions.endsWith(specialCharacters);
	// // 去掉末尾
	// String beanName = scopeOptions.substring(0, scopeOptions.length() - specialCharacters.length());
	// // scopeValue
	// if (Validator.isNullOrEmpty(scopeValue)){
	// if (isEndsWithListString){
	// scopeValue = beanName + "Id";
	// }
	// }
	// // scopeLabel
	// if (Validator.isNullOrEmpty(scopeLabel)){
	// if (isEndsWithListString){
	// scopeLabel = beanName + "Name";
	// }
	// }
	// try{
	// List list = (List) optionCollection;
	// for (Object objectOption : list){
	// s_label = PropertyUtils.getProperty(objectOption, scopeLabel).toString();
	// s_value = PropertyUtils.getProperty(objectOption, scopeValue).toString();
	// map.put(s_value, s_label);
	// }
	// }catch (Exception e){
	// e.printStackTrace();
	// }
	// }
	// // Result 用于jdbc
	// else if (optionCollection instanceof Result){
	// // 判断scopeOptions以List结尾
	// String specialCharacters = "Result";
	// boolean isEndsWithListString = scopeOptions.endsWith(specialCharacters);
	// // 去掉末尾
	// String beanName = scopeOptions.substring(0, scopeOptions.length() - specialCharacters.length());
	// // scopeValue
	// if (Validator.isNullOrEmpty(scopeValue)){
	// if (isEndsWithListString){
	// scopeValue = beanName + "Id";
	// }
	// }
	// // scopeLabel
	// if (Validator.isNullOrEmpty(scopeLabel)){
	// if (isEndsWithListString){
	// scopeLabel = beanName + "Name";
	// }
	// }
	// Result result = (Result) optionCollection;
	// SortedMap[] sortedMaps = result.getRows();
	// for (SortedMap sortedMap : sortedMaps){
	// s_label = sortedMap.get(scopeLabel).toString();
	// s_value = sortedMap.get(scopeValue).toString();
	// map.put(s_value, s_label);
	// }
	// }
	// // 数组
	// else if (optionCollection instanceof Object[]){}
	// }
	// return map;
	// }

	// /**
	// * 读取文件内容
	// *
	// * @param request
	// * HttpServletRequest
	// * @param directoryName
	// * 文件夹路径 前面没有/ 后面有/ 如:res/html/email/
	// * @param fileName
	// * 文件名称 如:register.html
	// * @return 读取文件内容
	// */
	// public final static String getFileContent(HttpServletRequest request,String directoryName,String fileName){
	// ServletContext servletContext = request.getSession().getServletContext();
	// return ServletContextUtil.getFileContent(servletContext, directoryName, fileName);
	// }

	// /**
	// * 判断是否是通过电脑web访问
	// *
	// * @param request
	// * @return web访问返回true
	// * @deprecated 不准确 待修改
	// */
	// public final static boolean isWebVisit(HttpServletRequest request){
	// String userAgent = getUserAgent(request);
	// if (Validator.isNotNullOrEmpty(userAgent) && StringUtil.isContain(userAgent.toLowerCase(), "mozilla")){
	// return true;
	// }
	// return false;
	// }
	// /**
	// * 判断一个请求是否包含参数
	// *
	// * @param request
	// * 请求
	// * @return 包含返回true
	// * @deprecated
	// */
	// // TODO post请求
	// public static boolean isContainsParam(HttpServletRequest request){
	// return StringUtil.isContain(RequestUtil.getRequestAllURL(request), "?");
	// }
	//
	// /**
	// * 判断一个请求是否不包含参数
	// *
	// * @param request
	// * @return 不包含参数
	// * @deprecated
	// */
	// public static boolean isNotContainsParam(HttpServletRequest request){
	// return !isContainsParam(request);
	// }

	// /**
	// * 获得参数经过8859之后的值
	// *
	// * @param request
	// * 当前请求
	// * @param param
	// * 参数名称
	// * @return 获得参数经过8859之后的值
	// * @deprecated 暂没有想好
	// */
	// public static String getParameterWith8859(HttpServletRequest request,String param){
	// String value = getParameter(request, param);
	// return URIUtil.decodeLuanMa_ISO8859(value);
	// }

	// /**
	// * 获取Properties配置文件键值
	// *
	// * @param servletContext
	// * servletContext
	// * @param propertiesPath
	// * Properties文件路径 如"/WEB-INF/classes/feilong.user.properties"
	// * @param key
	// * 键
	// * @return 获取Properties配置文件键值
	// * @deprecated 这个方法使用场景 几乎没有,可以使用spring 代替
	// */
	// public final static String getPropertiesValue(ServletContext servletContext,String propertiesPath,String key){
	// InputStream inputStream = servletContext.getResourceAsStream(propertiesPath);
	// Properties properties = PropertiesUtil.getProperties(inputStream);
	// return properties.getProperty(key);
	// }

}
