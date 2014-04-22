/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
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

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.Validator;

/**
 * 飞龙cookies 类.
 * 
 * @author 金鑫 2010-6-24 上午08:05:32
 * @author 金鑫 2012-5-18 14:53
 */
public final class CookieUtil{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(CookieUtil.class);

	/**
	 * Instantiates a new cookie util.
	 */
	private CookieUtil(){}

	/**
	 * 取到Cookie值.
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param cookieName
	 *            属性名
	 * @return 取到Cookie值
	 */
	public static String getCookieValue(HttpServletRequest request,String cookieName){
		Cookie cookie = getCookie(request, cookieName);
		if (null != cookie){
			return cookie.getValue();
		}
		return null;
	}

	/**
	 * 获得cookie.
	 * 
	 * @param request
	 *            the request
	 * @param cookieName
	 *            the cookie name
	 * @return the cookie
	 */
	public static Cookie getCookie(HttpServletRequest request,String cookieName){
		Cookie[] cookies = request.getCookies();
		if (Validator.isNotNullOrEmpty(cookies)){
			for (Cookie cookie : cookies){
				if (cookie.getName().equals(cookieName)){
					return cookie;
				}
			}
		}
		log.warn("can'nt find the cookie:{}", cookieName);
		return null;
	}

	/**
	 * 将cookie的 key 和value转成 map(TreeMap)
	 * 
	 * @param request
	 *            the request
	 * @return the cookie map
	 */
	public static Map<String, String> getCookieMap(HttpServletRequest request){
		Map<String, String> map = new TreeMap<String, String>();
		Cookie[] cookies = request.getCookies();
		if (Validator.isNotNullOrEmpty(cookies)){
			for (Cookie cookie : cookies){
				String name = cookie.getName();
				String value = cookie.getValue();
				map.put(name, value);
			}
		}
		return map;
	}

	/**
	 * 删除cookie.
	 * 
	 * @param cookieName
	 *            the cookie name
	 * @param response
	 *            the response
	 */
	public static void deleteCookie(String cookieName,HttpServletResponse response){
		// 设置为0为立即删除该Cookie
		int expiry = 0;
		CookieEntity cookieEntity = new CookieEntity(cookieName, null, expiry);
		addCookie(cookieEntity, response);
	}

	/**
	 * 创建个cookie.
	 * 
	 * @param cookieEntity
	 *            cookieEntity
	 * @param response
	 *            response
	 */
	public static void addCookie(CookieEntity cookieEntity,HttpServletResponse response){
		String value = cookieEntity.getValue();
		Cookie cookie = new Cookie(cookieEntity.getName(), value);
		// 设置Cookie过期之前的时间,以秒计
		// cookie.setMaxAge(cookieEntity.getExpiry());//设置以秒计的cookie的最大存活时间。
		// cookie.setComment(purpose);//指定一个注释来描述cookie的目的。
		// cookie.setDomain(pattern);// 指明cookie应当被声明的域。
		// cookie.setPath(uri);//指定客户端将cookie返回的cookie的路径。
		// cookie.setSecure(flag);// 指定是否cookie应该只通过安全协议，例如HTTPS或SSL,传送给浏览器。

		// cookie.setVersion(v);//设置本cookie遵循的cookie的协议的版本
		// cookie.setValue(newValue);//在一个cookie创建之后，给其分配一个新的值。
		response.addCookie(cookie);
	}

	// TODO RFC2965 fields also need to be passed
	/**
	 * Append cookie value.
	 * 
	 * @param headerBuf
	 *            the header buf
	 * @param version
	 *            the version
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @param path
	 *            the path
	 * @param domain
	 *            the domain
	 * @param comment
	 *            the comment
	 * @param maxAge
	 *            the max age
	 * @param isSecure
	 *            the is secure
	 * @param isHttpOnly
	 *            the is http only
	 */
	public static void appendCookieValue(
			StringBuffer headerBuf,
			int version,
			String name,
			String value,
			String path,
			String domain,
			String comment,
			int maxAge,
			boolean isSecure,
			boolean isHttpOnly){
		StringBuffer buf = new StringBuffer();
		// Servlet implementation checks name
		buf.append(name);
		buf.append("=");
		// Servlet implementation does not check anything else

		version = maybeQuote2(version, buf, value, true);

		// Add version 1 specific information
		if (version == 1){
			// Version=1 ... required
			buf.append("; Version=1");

			// Comment=comment
			if (comment != null){
				buf.append("; Comment=");
				maybeQuote2(version, buf, comment);
			}
		}

		// Add domain information, if present
		if (domain != null){
			buf.append("; Domain=");
			maybeQuote2(version, buf, domain);
		}

		// Max-Age=secs ... or use old "Expires" format
		// TODO RFC2965 Discard
		if (maxAge >= 0){
			if (version > 0){
				buf.append("; Max-Age=");
				buf.append(maxAge);
			}
			// IE6, IE7 and possibly other browsers don't understand Max-Age.
			// They do understand Expires, even with V1 cookies!
			if (version == 0 || ALWAYS_ADD_EXPIRES){
				// Wdy, DD-Mon-YY HH:MM:SS GMT ( Expires Netscape format )
				buf.append("; Expires=");
				// To expire immediately we need to set the time in past
				if (maxAge == 0)
					buf.append(ancientDate);
				else
					OLD_COOKIE_FORMAT.get().format(new Date(System.currentTimeMillis() + maxAge * 1000L), buf, new FieldPosition(0));
			}
		}

		// Path=path
		if (path != null){
			buf.append("; Path=");
			if (version == 0){
				maybeQuote2(version, buf, path);
			}else{
				maybeQuote2(version, buf, path, tspecials2NoSlash, false);
			}
		}

		// Secure
		if (isSecure){
			buf.append("; Secure");
		}

		// HttpOnly
		if (isHttpOnly){
			buf.append("; HttpOnly");
		}
		headerBuf.append(buf);
	}

	/**
	 * Quotes values using rules that vary depending on Cookie version.
	 * 
	 * @param version
	 *            the version
	 * @param buf
	 *            the buf
	 * @param value
	 *            the value
	 * @return the int
	 */
	public static int maybeQuote2(int version,StringBuffer buf,String value){
		return maybeQuote2(version, buf, value, false);
	}

	/**
	 * Maybe quote2.
	 * 
	 * @param version
	 *            the version
	 * @param buf
	 *            the buf
	 * @param value
	 *            the value
	 * @param allowVersionSwitch
	 *            the allow version switch
	 * @return the int
	 */
	public static int maybeQuote2(int version,StringBuffer buf,String value,boolean allowVersionSwitch){
		return maybeQuote2(version, buf, value, null, allowVersionSwitch);
	}

	/**
	 * Maybe quote2.
	 * 
	 * @param version
	 *            the version
	 * @param buf
	 *            the buf
	 * @param value
	 *            the value
	 * @param literals
	 *            the literals
	 * @param allowVersionSwitch
	 *            the allow version switch
	 * @return the int
	 */
	public static int maybeQuote2(int version,StringBuffer buf,String value,String literals,boolean allowVersionSwitch){
		if (value == null || value.length() == 0){
			buf.append("\"\"");
		}else if (containsCTL(value, version))
			throw new IllegalArgumentException("Control character in cookie value, consider BASE64 encoding your value");
		else if (alreadyQuoted(value)){
			buf.append('"');
			buf.append(escapeDoubleQuotes(value, 1, value.length() - 1));
			buf.append('"');
		}else if (allowVersionSwitch && (!STRICT_SERVLET_COMPLIANCE) && version == 0 && !isToken2(value, literals)){
			buf.append('"');
			buf.append(escapeDoubleQuotes(value, 0, value.length()));
			buf.append('"');
			version = 1;
		}else if (version == 0 && !isToken(value, literals)){
			buf.append('"');
			buf.append(escapeDoubleQuotes(value, 0, value.length()));
			buf.append('"');
		}else if (version == 1 && !isToken2(value, literals)){
			buf.append('"');
			buf.append(escapeDoubleQuotes(value, 0, value.length()));
			buf.append('"');
		}else{
			buf.append(value);
		}
		return version;
	}

	/**
	 * Escapes any double quotes in the given string.
	 * 
	 * @param s
	 *            the input string
	 * @param beginIndex
	 *            start index inclusive
	 * @param endIndex
	 *            exclusive
	 * @return The (possibly) escaped string
	 */
	private static String escapeDoubleQuotes(String s,int beginIndex,int endIndex){

		if (s == null || s.length() == 0 || s.indexOf('"') == -1){
			return s;
		}

		StringBuffer b = new StringBuffer();
		for (int i = beginIndex; i < endIndex; i++){
			char c = s.charAt(i);
			if (c == '\\'){
				b.append(c);
				// ignore the character after an escape, just append it
				if (++i >= endIndex)
					throw new IllegalArgumentException("Invalid escape character in cookie value.");
				b.append(s.charAt(i));
			}else if (c == '"')
				b.append('\\').append('"');
			else
				b.append(c);
		}

		return b.toString();
	}

	/**
	 * Checks if is token2.
	 * 
	 * @param value
	 *            the value
	 * @return true, if is token2
	 */
	public static boolean isToken2(String value){
		return isToken2(value, null);
	}

	/**
	 * Checks if is token2.
	 * 
	 * @param value
	 *            the value
	 * @param literals
	 *            the literals
	 * @return true, if is token2
	 */
	public static boolean isToken2(String value,String literals){
		String tspecials2 = (literals == null ? CookieUtil.tspecials : literals);
		if (value == null)
			return true;
		int len = value.length();

		for (int i = 0; i < len; i++){
			char c = value.charAt(i);
			if (tspecials2.indexOf(c) != -1)
				return false;
		}
		return true;
	}

	/*
	 * Tests a string and returns true if the string counts as a reserved token in the Java language.
	 * @param value the <code>String</code> to be tested
	 * @return <code>true</code> if the <code>String</code> is a reserved token; <code>false</code> if it is not
	 */
	/**
	 * Checks if is token.
	 * 
	 * @param value
	 *            the value
	 * @return true, if is token
	 */
	public static boolean isToken(String value){
		return isToken(value, null);
	}

	/**
	 * Checks if is token.
	 * 
	 * @param value
	 *            the value
	 * @param literals
	 *            the literals
	 * @return true, if is token
	 */
	public static boolean isToken(String value,String literals){
		String tspecials = (literals == null ? CookieUtil.tspecials : literals);
		if (value == null)
			return true;
		int len = value.length();

		for (int i = 0; i < len; i++){
			char c = value.charAt(i);

			if (tspecials.indexOf(c) != -1)
				return false;
		}
		return true;
	}

	/** The Constant tspecials. */
	private static final String	tspecials			= ",; ";

	/** The Constant tspecials2. */
	private static final String	tspecials2			= "()<>@,;:\\\"/[]?={} \t";

	/** The Constant tspecials2NoSlash. */
	private static final String	tspecials2NoSlash	= "()<>@,;:\\\"[]?={} \t";

	/**
	 * Already quoted.
	 * 
	 * @param value
	 *            the value
	 * @return true, if successful
	 */
	public static boolean alreadyQuoted(String value){
		if (value == null || value.length() == 0)
			return false;
		return (value.charAt(0) == '\"' && value.charAt(value.length() - 1) == '\"');
	}

	/**
	 * Contains ctl.
	 * 
	 * @param value
	 *            the value
	 * @param version
	 *            the version
	 * @return true, if successful
	 */
	public static boolean containsCTL(String value,int version){
		if (value == null)
			return false;
		int len = value.length();
		for (int i = 0; i < len; i++){
			char c = value.charAt(i);
			if (c < 0x20 || c >= 0x7f){
				if (c == 0x09)
					continue; // allow horizontal tabs
				return true;
			}
		}
		return false;
	}

	/** If set to true, we parse cookies according to the servlet spec,. */
	public static final boolean						STRICT_SERVLET_COMPLIANCE	= Boolean
																						.valueOf(
																								System.getProperty(
																										"org.apache.catalina.STRICT_SERVLET_COMPLIANCE",
																										"false")).booleanValue();

	/** If set to false, we don't use the IE6/7 Max-Age/Expires work around. */
	public static final boolean						ALWAYS_ADD_EXPIRES			= Boolean
																						.valueOf(
																								System.getProperty(
																										"org.apache.tomcat.util.http.ServerCookie.ALWAYS_ADD_EXPIRES",
																										"true")).booleanValue();

	/** The Constant ancientDate. */
	private static final String						ancientDate;

	// Other fields
	/** The Constant OLD_COOKIE_PATTERN. */
	private static final String						OLD_COOKIE_PATTERN			= "EEE, dd-MMM-yyyy HH:mm:ss z";

	/** The Constant OLD_COOKIE_FORMAT. */
	private static final ThreadLocal<DateFormat>	OLD_COOKIE_FORMAT			= new ThreadLocal<DateFormat>(){

																					protected DateFormat initialValue(){
																						DateFormat df = new SimpleDateFormat(
																								OLD_COOKIE_PATTERN,
																								Locale.US);
																						df.setTimeZone(TimeZone.getTimeZone("GMT"));
																						return df;
																					}
																				};
	static{
		ancientDate = OLD_COOKIE_FORMAT.get().format(new Date(10000));
	}
}
