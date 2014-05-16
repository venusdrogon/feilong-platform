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

/**
 * 解析 Request 使用的常量<br>
 * This class defines the common RequestConstans ,so that they can be referenced as a constant within Java code. <br>
 * 参考了 velocity RuntimeConstants.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0.4 2014-3-27 14:38
 * @see {@link org.apache.catalina.Globals}
 */
public interface RequestConstants{

	// ************************************include(Servlet 2.2)**************************************************************************
	/**
	 * <code>{@value}</code> <br>
	 * Standard Servlet 2.3+ spec request attributes for include URI and paths.
	 * <p>
	 * If included via a RequestDispatcher, the current resource will see the originating request. <br>
	 * Its own URI and paths are exposed as request attributes.
	 */
	String	ATTRIBUTE_INCLUDE_REQUEST_URI		= "javax.servlet.include.request_uri";

	/**
	 * <code>{@value}</code><br>
	 * The request attribute under which the context path of the included servlet is stored on an included dispatcher request.
	 */
	String	ATTRIBUTE_INCLUDE_CONTEXT_PATH		= "javax.servlet.include.context_path";

	/**
	 * <code>{@value}</code><br>
	 * The request attribute under which the servlet path of the included servlet is stored on an included dispatcher request.
	 */
	String	ATTRIBUTE_INCLUDE_SERVLET_PATH		= "javax.servlet.include.servlet_path";

	/**
	 * <code>{@value}</code><br>
	 * The request attribute under which the path info of the included servlet is stored on an included dispatcher request.
	 */
	String	ATTRIBUTE_INCLUDE_PATH_INFO			= "javax.servlet.include.path_info";

	/**
	 * <code>{@value}</code><br>
	 * The request attribute under which the query string of the included servlet is stored on an included dispatcher request.
	 */
	String	ATTRIBUTE_INCLUDE_QUERY_STRING		= "javax.servlet.include.query_string";

	// *********************************forward (Servlet 2.4)******************************************************************************/
	// 某些情况下一个forward()方法的目标servlet可能会需要知道真正原始的request URI

	/**
	 * <code>{@value}</code><br>
	 * Standard Servlet 2.4+ spec request attributes for forward URI and paths.
	 * <p>
	 * If forwarded to via a RequestDispatcher, the current resource will see its own URI and paths.<br>
	 * The originating URI and paths are exposed as request attributes.<br>
	 * The request attribute under which the original request URI is stored on an forwarded dispatcher request.
	 */
	String	ATTRIBUTE_FORWARD_REQUEST_URI		= "javax.servlet.forward.request_uri";

	/**
	 * <code>{@value}</code><br>
	 * The request attribute under which the original context path is stored on an forwarded dispatcher request.
	 */
	String	ATTRIBUTE_FORWARD_CONTEXT_PATH		= "javax.servlet.forward.context_path";

	/**
	 * <code>{@value}</code><br>
	 * The request attribute under which the original servlet path is stored on an forwarded dispatcher request.
	 */
	String	ATTRIBUTE_FORWARD_SERVLET_PATH		= "javax.servlet.forward.servlet_path";

	/**
	 * <code>{@value}</code><br>
	 * The request attribute under which the original path info is stored on an forwarded dispatcher request.
	 */
	String	ATTRIBUTE_FORWARD_PATH_INFO			= "javax.servlet.forward.path_info";

	/**
	 * <code>{@value}</code><br>
	 * The request attribute under which the original query string is stored on an forwarded dispatcher request.
	 */
	String	ATTRIBUTE_FORWARD_QUERY_STRING		= "javax.servlet.forward.query_string";

	// **************************************Servlet API 2.3 ******************************************************************/
	// Servlet API 2.3 also adds two new request attributes that can help a servlet make an informed decision about how to handle secure
	// HTTPS connections.
	// For requests made using HTTPS, the server will provide these new request attributes:

	/** A String representing the cipher suite used by HTTPS, if any. */
	String	ATTRIBUTE_REQUEST_CIPHER_SUITE		= "javax.servlet.request.cipher_suite";

	/** An Integer representing the bit size of the algorithm, if any. */
	String	ATTRIBUTE_REQUEST_KEY_SIZE			= "javax.servlet.request.key_size";

	// ********************************************************************************************************/
	/** ATTRIBUTE_REQUEST_X509CERTIFICATE. */
	String	ATTRIBUTE_REQUEST_X509CERTIFICATE	= "javax.servlet.request.X509Certificate";

	/**
	 * The ATTRIBUT e_ reques t_ ss l_ session
	 * {@link <a href="http://stackoverflow.com/questions/1422977/how-to-prevent-tomcat-session-hijacking">http://stackoverflow.com/questions/1422977/how-to-prevent-tomcat-session-hijacking</a>}
	 * .
	 */
	String	ATTRIBUTE_REQUEST_SSL_SESSION		= "javax.servlet.request.ssl_session";

	// 暂时还用不到下面的属性
	// /** The WOR k_ di r_ attr. */
	// String WORK_DIR_ATTR = "javax.servlet.context.tempdir";
	//
	// /** The SUBJEC t_ attr. */
	// String SUBJECT_ATTR = "javax.security.auth.subject";
	//
	// *********************************error******************************************************************************/
	/**
	 * <code>{@value}</code><br>
	 * Standard Servlet 2.3+ spec request attributes for error pages.
	 * <p>
	 * To be exposed to JSPs that are marked as error pages,<br>
	 * when forwarding to them directly rather than through the servlet container's error page resolution mechanism. <br>
	 * The request attribute under which we forward an HTTP status code (as an object of type Integer) to an error page.
	 */
	String	ATTRIBUTE_ERROR_STATUS_CODE			= "javax.servlet.error.status_code";

	/**
	 * <code>{@value}</code><br>
	 * The request attribute under which we forward a Java exception type (as an object of type Class) to an error page.
	 */
	String	ATTRIBUTE_ERROR_EXCEPTION_TYPE		= "javax.servlet.error.exception_type";

	/**
	 * <code>{@value}</code><br>
	 * The request attribute under which we forward an HTTP status message (as an object of type STring) to an error page.
	 */
	String	ATTRIBUTE_ERROR_MESSAGE				= "javax.servlet.error.message";

	/**
	 * Servlet 2.3+ <code>{@value}</code><br>
	 * The request attribute under which we forward a Java exception (as an object of type Throwable) to an error page.
	 */
	String	ATTRIBUTE_ERROR_EXCEPTION			= "javax.servlet.error.exception";

	/**
	 * Servlet 2.3+<code>{@value}</code><br>
	 * The request attribute under which we forward the request URI (as an object of type String) of the page on which an error occurred.
	 */
	String	ATTRIBUTE_ERROR_REQUEST_URI			= "javax.servlet.error.request_uri";

	/**
	 * <code>{@value}</code><br>
	 * The request attribute under which we forward a servlet name to an error page.
	 */
	String	ATTRIBUTE_ERROR_SERVLET_NAME		= "javax.servlet.error.servlet_name";

	// ****************************************header**************************************************************************
	/** The Constant header_referer. */
	String	HEADER_REFERER						= "referer";

	/**
	 * 1、Origin字段里只包含是谁发起的请求，并没有其他信息 (通常情况下是方案，主机和活动文档URL的端口)。<br>
	 * 跟Referer不一样的是，Origin字段并没有包含涉及到用户隐私的URL路径和请求内容，这个尤其重要。 <br>
	 * 2、Origin字段只存在于POST请求，而Referer则存在于所有类型的请求。<br>
	 * .
	 */
	String	HEADER_ORIGIN						= "origin";

	/** The Constant header_userAgent. */
	String	HEADER_USER_AGENT					= "User-Agent";

	/**
	 * X-Forwarded-For:简称XFF头，它代表客户端，也就是HTTP的请求端真实的IP，只有在通过了HTTP 代理或者负载均衡服务器时才会添加该项。<br>
	 * 它不是RFC中定义的标准请求头信息，在squid缓存代理服务器开发文档中可以找到该项的详细介绍。 <br>
	 * 标准格式如下：<br>
	 * X-Forwarded-For: client1, proxy1, proxy2.
	 */
	String	HEADER_X_FORWARDED_FOR				= "x-forwarded-for";

	/** The Constant header_proxyClientIP. */
	String	HEADER_PROXY_CLIENT_IP				= "Proxy-Client-IP";

	/** WL-Proxy-Client-IP 这个应该是WebLogic前置HttpClusterServlet提供的属性，一般不需要自己处理，在WebLogic控制台中已经可以指定使用这个属性来覆盖. */
	String	HEADER_WL_PROXY_CLIENT_IP			= "WL-Proxy-Client-IP";

	/** The Constant header_XRequestedWith. */
	String	HEADER_X_REQUESTED_WITH				= "X-Requested-With";

	/** The Constant header_XRequestedWith_value_ajax. */
	String	HEADER_X_REQUESTED_WITH_VALUE_AJAX	= "XMLHttpRequest";
}
