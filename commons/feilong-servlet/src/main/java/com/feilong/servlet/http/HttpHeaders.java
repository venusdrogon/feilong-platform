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
 * Constants enumerating the HTTP headers. All headers defined in RFC1945 (HTTP/1.0), RFC2616 (HTTP/1.1), and RFC2518
 * (WebDAV) are listed.
 *
 * @see "org.apache.http.HttpHeaders"
 * @since 1.0.8
 * @since httpcore 4.1
 */
public interface HttpHeaders{

	/** RFC 2616 (HTTP/1.1) Section 14.1 */
	String	ACCEPT						= "Accept";

	/** RFC 2616 (HTTP/1.1) Section 14.2 */
	String	ACCEPT_CHARSET				= "Accept-Charset";

	/** RFC 2616 (HTTP/1.1) Section 14.3 */
	String	ACCEPT_ENCODING				= "Accept-Encoding";

	/** RFC 2616 (HTTP/1.1) Section 14.4 */
	String	ACCEPT_LANGUAGE				= "Accept-Language";

	/** RFC 2616 (HTTP/1.1) Section 14.5 */
	String	ACCEPT_RANGES				= "Accept-Ranges";

	/** RFC 2616 (HTTP/1.1) Section 14.6 */
	String	AGE							= "Age";

	/** RFC 1945 (HTTP/1.0) Section 10.1, RFC 2616 (HTTP/1.1) Section 14.7 */
	String	ALLOW						= "Allow";

	/** RFC 1945 (HTTP/1.0) Section 10.2, RFC 2616 (HTTP/1.1) Section 14.8 */
	String	AUTHORIZATION				= "Authorization";

	/** RFC 2616 (HTTP/1.1) Section 14.9 */
	String	CACHE_CONTROL				= "Cache-Control";

	/** RFC 2616 (HTTP/1.1) Section 14.10 */
	String	CONNECTION					= "Connection";

	/** RFC 1945 (HTTP/1.0) Section 10.3, RFC 2616 (HTTP/1.1) Section 14.11 */
	String	CONTENT_ENCODING			= "Content-Encoding";

	/** RFC 2616 (HTTP/1.1) Section 14.12 */
	String	CONTENT_LANGUAGE			= "Content-Language";

	/** RFC 1945 (HTTP/1.0) Section 10.4, RFC 2616 (HTTP/1.1) Section 14.13 */
	String	CONTENT_LENGTH				= "Content-Length";

	/** RFC 2616 (HTTP/1.1) Section 14.14 */
	String	CONTENT_LOCATION			= "Content-Location";

	/** The content disposition. */
	String	CONTENT_DISPOSITION			= "Content-Disposition";

	/** RFC 2616 (HTTP/1.1) Section 14.15 */
	String	CONTENT_MD5					= "Content-MD5";

	/** RFC 2616 (HTTP/1.1) Section 14.16 */
	String	CONTENT_RANGE				= "Content-Range";

	/**
	 * RFC 1945 (HTTP/1.0) Section 10.5, RFC 2616 (HTTP/1.1) Section 14.17 <br>
	 * 
	 * <pre>
	 * http://tools.ietf.org/html/rfc2616#section-7.2.1
	 * 7.2.1 Type
	 * 
	 *    When an entity-body is included with a message, the data type of that body is determined via the header fields Content-Type and Content-Encoding. 
	 *    These define a two-layer, ordered encoding model:
	 * 
	 *        entity-body := Content-Encoding( Content-Type( data ) )
	 * 
	 *    Content-Type specifies the media type of the underlying data.
	 *    Content-Encoding may be used to indicate any additional content codings applied to the data, usually for the purpose of data compression, that are a property of the requested resource. There is no default encoding.
	 * 
	 *    Any HTTP/1.1 message containing an entity-body SHOULD include a Content-Type header field defining the media type of that body. 
	 *    If and only if the media type is not given by a Content-Type field, the recipient MAY attempt to guess the media type via inspection of its
	 *    content and/or the name extension(s) of the URI used to identify the resource. 
	 *    If the media type remains unknown, the recipient SHOULD treat it as type "application/octet-stream".
	 * </pre>
	 * */
	String	CONTENT_TYPE				= "Content-Type";

	/** RFC 1945 (HTTP/1.0) Section 10.6, RFC 2616 (HTTP/1.1) Section 14.18 */
	String	DATE						= "Date";

	/** RFC 2518 (WevDAV) Section 9.1 */
	String	DAV							= "Dav";

	/** RFC 2518 (WevDAV) Section 9.2 */
	String	DEPTH						= "Depth";

	/** RFC 2518 (WevDAV) Section 9.3 */
	String	DESTINATION					= "Destination";

	/** RFC 2616 (HTTP/1.1) Section 14.19 */
	String	ETAG						= "ETag";

	/** RFC 2616 (HTTP/1.1) Section 14.20 */
	String	EXPECT						= "Expect";

	/** RFC 1945 (HTTP/1.0) Section 10.7, RFC 2616 (HTTP/1.1) Section 14.21 */
	String	EXPIRES						= "Expires";

	/** RFC 1945 (HTTP/1.0) Section 10.8, RFC 2616 (HTTP/1.1) Section 14.22 */
	String	FROM						= "From";

	/** RFC 2616 (HTTP/1.1) Section 14.23 */
	String	HOST						= "Host";

	/** RFC 2518 (WevDAV) Section 9.4 */
	String	IF							= "If";

	/** RFC 2616 (HTTP/1.1) Section 14.24 */
	String	IF_MATCH					= "If-Match";

	/** RFC 1945 (HTTP/1.0) Section 10.9, RFC 2616 (HTTP/1.1) Section 14.25 */
	String	IF_MODIFIED_SINCE			= "If-Modified-Since";

	/** RFC 2616 (HTTP/1.1) Section 14.26 */
	String	IF_NONE_MATCH				= "If-None-Match";

	/** RFC 2616 (HTTP/1.1) Section 14.27 */
	String	IF_RANGE					= "If-Range";

	/** RFC 2616 (HTTP/1.1) Section 14.28 */
	String	IF_UNMODIFIED_SINCE			= "If-Unmodified-Since";

	/** RFC 1945 (HTTP/1.0) Section 10.10, RFC 2616 (HTTP/1.1) Section 14.29 */
	String	LAST_MODIFIED				= "Last-Modified";

	/** RFC 1945 (HTTP/1.0) Section 10.11, RFC 2616 (HTTP/1.1) Section 14.30 */
	String	LOCATION					= "Location";

	/** RFC 2518 (WevDAV) Section 9.5 */
	String	LOCK_TOKEN					= "Lock-Token";

	/** RFC 2616 (HTTP/1.1) Section 14.31 */
	String	MAX_FORWARDS				= "Max-Forwards";

	/** RFC 2518 (WevDAV) Section 9.6 */
	String	OVERWRITE					= "Overwrite";

	/** RFC 1945 (HTTP/1.0) Section 10.12, RFC 2616 (HTTP/1.1) Section 14.32 */
	String	PRAGMA						= "Pragma";

	/** RFC 2616 (HTTP/1.1) Section 14.33 */
	String	PROXY_AUTHENTICATE			= "Proxy-Authenticate";

	/** RFC 2616 (HTTP/1.1) Section 14.34 */
	String	PROXY_AUTHORIZATION			= "Proxy-Authorization";

	/** RFC 2616 (HTTP/1.1) Section 14.35 */
	String	RANGE						= "Range";

	/** RFC 1945 (HTTP/1.0) Section 10.13, RFC 2616 (HTTP/1.1) Section 14.36 */
	String	REFERER						= "Referer";

	/** RFC 2616 (HTTP/1.1) Section 14.37 */
	String	RETRY_AFTER					= "Retry-After";

	/** RFC 1945 (HTTP/1.0) Section 10.14, RFC 2616 (HTTP/1.1) Section 14.38 */
	String	SERVER						= "Server";

	/** RFC 2518 (WevDAV) Section 9.7 */
	String	STATUS_URI					= "Status-URI";

	/** RFC 2616 (HTTP/1.1) Section 14.39 */
	String	TE							= "TE";

	/** RFC 2518 (WevDAV) Section 9.8 */
	String	TIMEOUT						= "Timeout";

	/** RFC 2616 (HTTP/1.1) Section 14.40 */
	String	TRAILER						= "Trailer";

	/** RFC 2616 (HTTP/1.1) Section 14.41 */
	String	TRANSFER_ENCODING			= "Transfer-Encoding";

	/** RFC 2616 (HTTP/1.1) Section 14.42 */
	String	UPGRADE						= "Upgrade";

	/** RFC 1945 (HTTP/1.0) Section 10.15, RFC 2616 (HTTP/1.1) Section 14.43 */
	String	USER_AGENT					= "User-Agent";

	/** RFC 2616 (HTTP/1.1) Section 14.44 */
	String	VARY						= "Vary";

	/** RFC 2616 (HTTP/1.1) Section 14.45 */
	String	VIA							= "Via";

	/** RFC 2616 (HTTP/1.1) Section 14.46 */
	String	WARNING						= "Warning";

	/** RFC 1945 (HTTP/1.0) Section 10.16, RFC 2616 (HTTP/1.1) Section 14.47 */
	String	WWW_AUTHENTICATE			= "WWW-Authenticate";

	// ****************************************header**************************************************************************

	/**
	 * 1、Origin字段里只包含是谁发起的请求，并没有其他信息 (通常情况下是方案，主机和活动文档URL的端口)。<br>
	 * 跟Referer不一样的是，Origin字段并没有包含涉及到用户隐私的URL路径和请求内容，这个尤其重要。 <br>
	 * 2、Origin字段只存在于POST请求，而Referer则存在于所有类型的请求。<br>
	 * .
	 */
	String	ORIGIN						= "origin";

	/**
	 * X-Forwarded-For:简称XFF头，它代表客户端，也就是HTTP的请求端真实的IP，只有在通过了HTTP 代理或者负载均衡服务器时才会添加该项。<br>
	 * 它不是RFC中定义的标准请求头信息，在squid缓存代理服务器开发文档中可以找到该项的详细介绍。 <br>
	 * 标准格式如下：<br>
	 * X-Forwarded-For: client1, proxy1, proxy2.
	 */
	String	X_FORWARDED_FOR				= "x-forwarded-for";

	/** <code>{@value}</code>. */
	String	PROXY_CLIENT_IP				= "Proxy-Client-IP";

	/** WL-Proxy-Client-IP 这个应该是WebLogic前置HttpClusterServlet提供的属性，一般不需要自己处理，在WebLogic控制台中已经可以指定使用这个属性来覆盖. */
	String	WL_PROXY_CLIENT_IP			= "WL-Proxy-Client-IP";

	/** <code>{@value}</code> . */
	String	X_REQUESTED_WITH			= "X-Requested-With";

	/** <code>{@value}</code>. */
	String	X_REQUESTED_WITH_VALUE_AJAX	= "XMLHttpRequest";

}
