/*
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.servlet.http;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.lang.ObjectUtil;
import com.feilong.commons.core.net.URIConstants;
import com.feilong.commons.core.net.URIUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;

/**
 * HttpServletRequest工具类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-11-3 下午02:24:55
 * @version 1.0.4 2014-3-27 14:38
 * @see RequestConstants
 * @see RequestLogSwitch
 */
public final class RequestUtil{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(RequestUtil.class);

	/**
	 * Instantiates a new request util.
	 */
	private RequestUtil(){};

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
			Map<String, ?> map = getParameterMap(request);
			return map.containsKey(param);
		}
		return false;
	}

	/**
	 * 请求路径中是否包含某个参数名称 (注意:这是判断是否包含参数,而不是判断参数值是否为空).
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
	 * 获得参数 map(结果转成了 TreeMap).
	 * 
	 * @param request
	 *            the request
	 * @return the parameter map
	 */
	public static Map<String, String[]> getParameterMap(HttpServletRequest request){
		@SuppressWarnings("unchecked")
		// http://localhost:8888/s.htm?keyword&a=
		// 这种链接
		// map key 会是 keyword,a 值都是空
		// servlet 3.0 此处返回类型的是 泛型数组 Map<String, String[]>
		Map<String, String[]> map = request.getParameterMap();

		// 转成TreeMap ,这样log出现的key 是有顺序的
		Map<String, String[]> returnMap = new TreeMap<String, String[]>(map);
		return returnMap;
	}

	/**
	 * 获取queryString （支持 post/get）.
	 * 
	 * @param request
	 *            the request
	 * @return the query string
	 */
	public static String getQueryStringLog(HttpServletRequest request){
		String returnValue = "";

		// Returns the name of the HTTP method with which this request was made,
		// for example, GET, POST, or PUT.
		// Same as the value of the CGI variable REQUEST_METHOD.
		String method = request.getMethod();

		if (method.toUpperCase().equals("POST")){
			Map<String, String[]> map = getParameterMap(request);
			if (Validator.isNotNullOrEmpty(map)){
				returnValue = URIUtil.combineQueryString(map, null);
			}
		}else{
			// Returns the query string that is contained in the request URL after the path.
			// This method returns null if the URL does not have a query string.
			// Same as the value of the CGI variable QUERY_STRING.
			// 它只对get方法得到的数据有效。
			returnValue = request.getQueryString();
		}
		return returnValue;
	}

	// ********************************************************************************************
	/**
	 * 获得request error 相关参数 map.
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return 如果request 有 {@link RequestConstants#ATTRIBUTE_ERROR_STATUS_CODE}属性,则返回error 相关属性 封装到map,<br>
	 *         如果 request没有 {@link RequestConstants#ATTRIBUTE_ERROR_STATUS_CODE}属性,返回null
	 */
	public static Map<String, String> getErrorMap(HttpServletRequest request){
		String errorCode = getAttributeToString(request, RequestConstants.ATTRIBUTE_ERROR_STATUS_CODE);
		if (Validator.isNotNullOrEmpty(errorCode)){
			Map<String, String> map = new LinkedHashMap<String, String>();
			map.put(RequestConstants.ATTRIBUTE_ERROR_STATUS_CODE, errorCode);
			map.put(
							RequestConstants.ATTRIBUTE_ERROR_REQUEST_URI,
							getAttributeToString(request, RequestConstants.ATTRIBUTE_ERROR_REQUEST_URI));
			map.put(RequestConstants.ATTRIBUTE_ERROR_EXCEPTION, getAttributeToString(request, RequestConstants.ATTRIBUTE_ERROR_EXCEPTION));
			map.put(
							RequestConstants.ATTRIBUTE_ERROR_EXCEPTION_TYPE,
							getAttributeToString(request, RequestConstants.ATTRIBUTE_ERROR_EXCEPTION_TYPE));
			map.put(RequestConstants.ATTRIBUTE_ERROR_MESSAGE, getAttributeToString(request, RequestConstants.ATTRIBUTE_ERROR_MESSAGE));
			map.put(
							RequestConstants.ATTRIBUTE_ERROR_SERVLET_NAME,
							getAttributeToString(request, RequestConstants.ATTRIBUTE_ERROR_SERVLET_NAME));
			return map;
		}
		return null;
	}

	/**
	 * 将request 相关属性，数据转成json格式 以便log显示(目前仅作log使用).<br>
	 * 使用默认的 {@link RequestLogSwitch}
	 * 
	 * @param request
	 *            the request
	 * @return the request string for log
	 * @see RequestLogSwitch
	 */
	public static String getRequestStringForLog(HttpServletRequest request){
		RequestLogSwitch requestLogSwitch = new RequestLogSwitch();
		return getRequestStringForLog(request, requestLogSwitch);
	}

	/**
	 * 将request 相关属性，数据转成json格式 以便log显示(目前仅作log使用).
	 * 
	 * @param request
	 *            the request
	 * @param requestLogSwitch
	 *            the request log switch
	 * @return the request string for log
	 */
	public static String getRequestStringForLog(HttpServletRequest request,RequestLogSwitch requestLogSwitch){

		if (null == requestLogSwitch){
			requestLogSwitch = new RequestLogSwitch();
		}

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		// 在3.0 是数组Map<String, String[]> getParameterMap
		// The keys in the parameter map are of type String.
		// The values in the parameter map are of type String array.
		Map<String, ?> parameterMap = getParameterMap(request);

		// requestFullURL
		if (requestLogSwitch.getShowFullURL()){
			// XXX 编码参数转成可传递
			map.put("requestFullURL:", getRequestFullURL(request, CharsetType.UTF8));
		}
		// Method
		if (requestLogSwitch.getShowMethod()){
			map.put("request.getMethod:", request.getMethod());
		}

		// _parameterMap
		if (requestLogSwitch.getShowParams()){
			map.put("_parameterMap", parameterMap);
		}

		// _cookieMap
		if (requestLogSwitch.getShowCookies()){
			map.put("_cookieMap", CookieUtil.getCookieMap(request));
		}

		// aboutURLMap
		if (requestLogSwitch.getShowURLs()){

			// 1.getServletContext().getRealPath("/") 后包含当前系统的文件夹分隔符（windows系统是"\"，linux系统是"/"），而getPathInfo()以"/"开头。
			// 2.getPathInfo()与getPathTranslated()在servlet的url-pattern被设置为/*或/aa/*之类的pattern时才有值，其他时候都返回null。
			// 3.在servlet的url-pattern被设置为*.xx之类的pattern时，getServletPath()返回的是getRequestURI()去掉前面ContextPath的剩余部分。

			Map<String, String> aboutURLMap = new LinkedHashMap<String, String>();

			aboutURLMap.put("request.getContextPath()", request.getContextPath());

			// Returns any extra path information associated with the URL the client sent when it made this request.
			// Servlet访问路径之后，QueryString之前的中间部分
			aboutURLMap.put("request.getPathInfo()", request.getPathInfo());

			// web.xml中定义的Servlet访问路径
			aboutURLMap.put("request.getServletPath()", request.getServletPath());
			// 等于getServletContext().getRealPath("/") + getPathInfo()
			aboutURLMap.put("request.getPathTranslated()", request.getPathTranslated());

			// ***********************************************************************
			aboutURLMap.put("getQueryStringLog", getQueryStringLog(request));
			// &之后GET方法的参数部分
			aboutURLMap.put("request.getQueryString()", request.getQueryString());

			// ***********************************************************************
			// 等于getContextPath() + getServletPath() + getPathInfo()
			aboutURLMap.put("request.getRequestURI()", request.getRequestURI());

			// 等于getScheme() + "://" + getServerName() + ":" + getServerPort() + getRequestURI()
			aboutURLMap.put("request.getRequestURL()", "" + request.getRequestURL());

			map.put("aboutURLMap", aboutURLMap);
		}

		// aboutIPMap
		if (requestLogSwitch.getShowIPs()){
			Map<String, String> aboutIPMap = new TreeMap<String, String>();
			aboutIPMap.put("request.getLocalAddr()", request.getLocalAddr());
			aboutIPMap.put("request.getRemoteAddr()", request.getRemoteAddr());
			aboutIPMap.put("request.getRemoteHost()", request.getRemoteHost());
			aboutIPMap.put("request.getServerName()", request.getServerName());
			aboutIPMap.put("getClientIp", getClientIp(request));
			map.put("aboutIPMap", aboutIPMap);
		}

		// aboutPortMap
		if (requestLogSwitch.getShowPorts()){
			Map<String, String> aboutPortMap = new TreeMap<String, String>();
			aboutPortMap.put("request.getLocalPort()", "" + request.getLocalPort());
			aboutPortMap.put("request.getRemotePort()", "" + request.getRemotePort());
			aboutPortMap.put("request.getServerPort()", "" + request.getServerPort());
			map.put("aboutPortMap", aboutPortMap);
		}

		// _headerMap
		if (requestLogSwitch.getShowHeaders()){
			map.put("_headerMap", getHeaderMap(request));
		}

		// aboutElseMap
		if (requestLogSwitch.getShowElses()){
			Map<String, Object> aboutElseMap = new TreeMap<String, Object>();
			aboutElseMap.put("request.getAuthType()", request.getAuthType());
			aboutElseMap.put("request.getCharacterEncoding()", request.getCharacterEncoding());
			aboutElseMap.put("request.getContentLength()", "" + request.getContentLength());
			aboutElseMap.put("request.getLocalName()", request.getLocalName());

			aboutElseMap.put("request.getProtocol()", request.getProtocol());
			aboutElseMap.put("request.getRemoteUser()", request.getRemoteUser());
			aboutElseMap.put("request.getRequestedSessionId()", request.getRequestedSessionId());
			aboutElseMap.put("request.getScheme()", request.getScheme());
			aboutElseMap.put("request.getLocale()", request.getLocale());
			map.put("aboutElseMap", aboutElseMap);
		}

		// _errorMap
		if (requestLogSwitch.getShowErrors()){
			map.put("_errorMap", getErrorMap(request));
		}

		// 避免json渲染出错，只放 key
		// attribute 不属于 log 范围之内, 如果有需要 自行调用 getAttributeMap(request)
		// map.put("_attributeKeys", getAttributeMap(request).keySet());

		String string = JsonUtil.format(map);
		return string;
	}

	/**
	 * 遍历显示request的header 用于debug<br>
	 * 将 request header name 和value 封装到map.
	 * 
	 * @param request
	 *            the request
	 * @return the header map
	 */
	public static Map<String, String> getHeaderMap(HttpServletRequest request){
		Map<String, String> map = new TreeMap<String, String>();
		@SuppressWarnings("unchecked")
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()){
			String name = headerNames.nextElement();
			String value = request.getHeader(name);
			map.put(name, value);
		}
		return map;
	}

	/**
	 * ******************************* url参数相关 getAttribute*****************************************************.
	 * 
	 * @param request
	 *            the request
	 * @param name
	 *            the name
	 * @return the attribute to int
	 */
	// [start] url参数相关

	/**
	 * 取到request里面的属性值.
	 * 
	 * @param request
	 *            请求
	 * @param name
	 *            属性名称
	 * @return 取到的object类型会调用 ObjectUtil.toString(value)
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
	 * request.getRequestURI() 返回值类似：/feilong/requestdemo.jsp
	 * request.getRequestURL() 返回值类似：http://localhost:8080/feilong/requestdemo.jsp
	 * </pre>
	 * 
	 * @param request
	 *            the request
	 * @return 获得请求的?部分前面的地址
	 */
	public final static String getRequestURL(HttpServletRequest request){
		String forward_request_uri = (String) request.getAttribute(RequestConstants.ATTRIBUTE_FORWARD_REQUEST_URI);
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
		String servletPath = (String) request.getAttribute(RequestConstants.ATTRIBUTE_FORWARD_SERVLET_PATH);
		if (servletPath == null){
			servletPath = request.getServletPath();
		}
		return servletPath;
	}

	/**
	 * 获得请求的全地址
	 * <ul>
	 * <li>如果request不含queryString,直接返回 requestURL(比如post请求)</li>
	 * <li>如果request含queryString,直接返回 requestURL+编码后的queryString</li>
	 * </ul>
	 * .
	 * 
	 * @param request
	 *            the request
	 * @param charsetType
	 *            编码集 {@link CharsetType}
	 * @return 如:http://localhost:8080/feilong/requestdemo.jsp?id=2
	 */
	public final static String getRequestFullURL(HttpServletRequest request,String charsetType){
		String requestURL = getRequestURL(request);

		String queryString = request.getQueryString();
		if (Validator.isNullOrEmpty(queryString)){
			return requestURL;
		}

		// XXX 处理乱码
		return requestURL + URIConstants.QUESTIONMARK + URIUtil.decodeLuanMa_ISO8859(queryString, charsetType);
	}

	/**
	 * scheme+port+getContextPath <br>
	 * 区分 http 和https.<br>
	 * 
	 * @param request
	 *            the request
	 * @return 如:http://localhost:8080/feilong/
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

		if ((scheme.equals(URIConstants.SCHEME_HTTP) && (port != 80)) || (scheme.equals(URIConstants.SCHEME_HTTPS) && (port != 443))){
			url.append(':');
			url.append(port);
		}

		url.append(request.getContextPath());
		return url.toString();
	}

	// [end]
	// ***************************************LocalAddr******************************************************************************

	/**
	 * 获得项目本地ip地址.
	 * 
	 * @param request
	 *            the request
	 * @return Returns the Internet Protocol (IP) address of the interface on which the request was received.
	 */
	public final static String getLocalAddr(HttpServletRequest request){
		return request.getLocalAddr();
	}

	// ***************************************Header******************************************************************************

	/**
	 * 获得客户端ip地址.
	 * 
	 * @param request
	 *            the request
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

		Map<String, String> map = new TreeMap<String, String>();

		//TODO X-real-ip 程哥说如果主站使用cdn的话，以前的方法获取到的不正确

		// 是否使用反向代理
		String ipAddress = request.getHeader(HttpHeaders.X_FORWARDED_FOR);
		map.put("1.header_xForwardedFor", ipAddress);
		if (Validator.isNullOrEmpty(ipAddress) || unknown.equalsIgnoreCase(ipAddress)){
			ipAddress = request.getHeader(HttpHeaders.PROXY_CLIENT_IP);
			map.put("2.header_proxyClientIP", ipAddress);
		}
		if (Validator.isNullOrEmpty(ipAddress) || unknown.equalsIgnoreCase(ipAddress)){
			ipAddress = request.getHeader(HttpHeaders.WL_PROXY_CLIENT_IP);
			map.put("3.header_wLProxyClientIP", ipAddress);
		}
		if (Validator.isNullOrEmpty(ipAddress) || unknown.equalsIgnoreCase(ipAddress)){
			ipAddress = request.getRemoteAddr();
			map.put("4.request.getRemoteAddr()", ipAddress);
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15){ // "***.***.***.***".length() = 15
			map.put("5.all", ipAddress);
			if (ipAddress.indexOf(",") > 0){
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
				map.put("6.ipAddress", ipAddress);
			}
		}
		if (log.isDebugEnabled()){
			log.debug(JsonUtil.format(map));
		}
		return ipAddress;
	}

	// *****************************Header区域******************************************************

	/**
	 * 　User Agent中文名为用户代理，简称 UA，<br>
	 * 它是一个特殊字符串头，使得服务器能够识别客户使用的操作系统及版本、CPU 类型、浏览器及版本、浏览器渲染引擎、浏览器语言、浏览器插件等。.
	 * 
	 * @param request
	 *            the request
	 * @return the user agent
	 */
	public final static String getHeaderUserAgent(HttpServletRequest request){
		return request.getHeader(HttpHeaders.USER_AGENT);
	}

	/**
	 * 获得上上个请求的URL
	 * 
	 * <pre>
	 * 请用于常规请求,必须走http协议才有值,javascript跳转无效
	 * 以下情况请慎用:
	 * 也就是说要通过&lt;a href=&quot;url&quot;&gt;sss&lt;/a&gt;标记才能获得那个值
	 * 而通过改变location或是&lt;a href=&quot;javascript:location='url'&quot;&gt;sss&lt;/a&gt;都是得不到那个值得
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
	 *            the request
	 * @return 上上个请求的URL
	 */
	public final static String getHeaderReferer(HttpServletRequest request){
		return request.getHeader(HttpHeaders.REFERER);
	}

	/**
	 * 1、Origin字段里只包含是谁发起的请求，并没有其他信息 (通常情况下是方案，主机和活动文档URL的端口)。 <br>
	 * 跟Referer不一样的是，Origin字段并没有包含涉及到用户隐私的URL路径和请求内容，这个尤其重要。 <br>
	 * 2、Origin字段只存在于POST请求，而Referer则存在于所有类型的请求。.
	 * 
	 * @param request
	 *            the request
	 * @return the header origin
	 */
	public final static String getHeaderOrigin(HttpServletRequest request){
		return request.getHeader(HttpHeaders.ORIGIN);
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
	 *            the request
	 * @return 如果是ajax 请求 返回true
	 * @see http://en.wikipedia.org/wiki/X-Requested-With#Requested-With
	 */
	@SuppressWarnings("javadoc")
	public final static boolean isAjaxRequest(HttpServletRequest request){
		String header = request.getHeader(HttpHeaders.X_REQUESTED_WITH);
		if (Validator.isNotNullOrEmpty(header) && header.equalsIgnoreCase(HttpHeaders.X_REQUESTED_WITH_VALUE_AJAX)){
			return true;
		}
		return false;
	}

	/**
	 * 判断一个请求 ,不是ajax 请求.
	 * 
	 * @param request
	 *            the request
	 * @return 如果不是ajax 返回true
	 */
	public final static boolean isNotAjaxRequest(HttpServletRequest request){
		return !isAjaxRequest(request);
	}

	// ****************************************************************************************************

	/**
	 * 遍历显示request的attribute,将 name /attributeValue 存入到map.
	 * 
	 * @param request
	 *            the request
	 * @return the attribute map
	 * @deprecated 目前如果直接 转json 如果属性有级联关系,会报错,待重构
	 */
	// XXX
	private static Map<String, Object> getAttributeMap(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		@SuppressWarnings("unchecked")
		Enumeration<String> attributeNames = request.getAttributeNames();
		while (attributeNames.hasMoreElements()){
			String name = attributeNames.nextElement();
			Object attributeValue = request.getAttribute(name);
			// String[] excludes = {
			// "autowireCapableBeanFactory",
			// "classLoader",
			// "servletConfig",
			// "servletContext",
			// "beanClassLoader",
			// "environment",
			// "beanFactory",
			// "parentBeanFactory",
			// "parent",
			// "defaultAssertionStatus",
			// "URLs" };
			// log.debug("\n\tbegin name:[{}]", name);
			//
			// String string = JsonUtil.format(attributeValue, excludes);
			// log.debug("\n\tname:[{}],\n\t:{}", name, string);
			map.put(name, attributeValue);
		}

		// log.debug("the param request attributeNames:{}", JsonUtil.toJSON(map.keySet()).toString(4, 4));
		return map;
	}

	// *********************************获取值**************************************************
	/**
	 * 将参数值转换成int类型.
	 * 
	 * @param request
	 *            请求
	 * @param paramName
	 *            参数名称
	 * @return 将参数值转换成int类型
	 * @exception IllegalArgumentException
	 *                如果param值 不能转成integer 会抛出 IllegalArgumentException异常
	 */
	public static Integer getParameterToInteger(HttpServletRequest request,String paramName){
		String value = getParameter(request, paramName);
		return ObjectUtil.toInteger(value);
	}

	/**
	 * 将参数值转换成BigDecimal类型.
	 * 
	 * @param request
	 *            请求
	 * @param paramName
	 *            参数名称
	 * @return 将参数值转换成BigDecimal类型
	 */
	public static BigDecimal getParameterToBigDecimal(HttpServletRequest request,String paramName){
		String value = getParameter(request, paramName);
		return ObjectUtil.toBigDecimal(value);
	}

	/**
	 * 获得request中的请求参数值.
	 * 
	 * @param request
	 *            当前请求
	 * @param paramName
	 *            参数名称
	 * @return 获得request中的请求参数值
	 */
	public static String getParameter(HttpServletRequest request,String paramName){
		return request.getParameter(paramName);
	}

	/**
	 * 参数值去除井号,一般用于sendDirect 跳转中带有#标签,参数值取不准确的问题.
	 * 
	 * @param request
	 *            the request
	 * @param paramName
	 *            the param name
	 * @return 参数值去除井号,一般用于sendDirect 跳转中带有#标签,参数值取不准确的问题
	 */
	public static String getParameterWithoutSharp(HttpServletRequest request,String paramName){
		String returnValue = getParameter(request, paramName);
		if (Validator.isNotNullOrEmpty(returnValue)){
			if (StringUtil.isContain(returnValue, URIConstants.FRAGMENT)){
				returnValue = StringUtil.substring(returnValue, null, URIConstants.FRAGMENT);
			}
		}
		return returnValue;
	}

	/**
	 * 原样获得参数值(request.getParameter()函数时，会自动进行一次URI的解码过程，调用时内置的解码过程会导致乱码出现)
	 * 
	 * <pre>
	 * url参数是什么,取到的就是什么,不经过处理
	 * </pre>
	 * 
	 * @param request
	 *            请求
	 * @param paramName
	 *            参数名称
	 * @return 原样获得参数值
	 * @deprecated
	 */
	@Deprecated
	public static String getParameterAsItIsDecode(HttpServletRequest request,String paramName){
		String returnValue = null;
		String queryString = request.getQueryString();
		if (Validator.isNotNullOrEmpty(queryString)){
			Map<String, String[]> map = URIUtil.parseQueryToArrayMap(queryString, null);
			return map.get(paramName)[0];
		}
		return returnValue;
	}

	/**
	 * 取到参数值,没有返回null,有去除空格返回.
	 * 
	 * @param request
	 *            当前请求
	 * @param paramName
	 *            the param name
	 * @return 取到参数值,没有返回null,有去除空格返回
	 */
	public static String getParameterWithTrim(HttpServletRequest request,String paramName){
		String returnValue = getParameter(request, paramName);
		if (Validator.isNotNullOrEmpty(returnValue)){
			returnValue = returnValue.trim();
		}
		return returnValue;
	}
}
