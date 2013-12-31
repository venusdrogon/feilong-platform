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
package com.feilong.tools.net.httpclient;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.enumeration.HttpMethodType;
import com.feilong.commons.core.util.JsonFormatUtil;
import com.feilong.commons.core.util.Validator;

/**
 * HttpClient 相关工具类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-11-18 上午09:35:27
 * @version 1.1 2011-7-27 11:59
 */
public final class HttpClientUtil{

	/** The Constant log. */
	private final static Logger	log					= LoggerFactory.getLogger(HttpClientUtil.class);

	/** 伪造的 useragent. */
	private final static String	defaultUserAgent	= "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21";

	/**
	 * 获得HttpMethod请求结果.
	 * 
	 * @param uri
	 *            路径
	 * @param httpMethodType
	 *            请求方式
	 * @param charSet
	 *            the char set
	 * @return 获得HttpMethod请求结果
	 * @throws HttpClientUtilException
	 *             the http client util exception
	 * @author 铁彪
	 * @deprecated
	 */
	public static String getHttpMethodResponseBodyAsStringIgnoreCharSet(String uri,HttpMethodType httpMethodType,String charSet)
			throws HttpClientUtilException{
		HttpMethod httpMethod = getHttpMethodWithExecute(uri, httpMethodType);
		httpMethod.getParams().setContentCharset(charSet);
		return getHttpMethodResponseBodyAsString(httpMethod);
	}

	// ****************************************************************************************
	/**
	 * 获得HttpMethod请求结果.
	 * 
	 * @param uri
	 *            路径
	 * @param httpMethodType
	 *            请求方式
	 * @return the http method response body as string
	 * @throws HttpClientUtilException
	 *             如果代码执行有异常会以HttpClientUtilException的形式抛出
	 */
	public static String getHttpMethodResponseBodyAsString(String uri,HttpMethodType httpMethodType) throws HttpClientUtilException{
		String userName = null;
		String password = null;
		return getHttpMethodResponseBodyAsString(uri, httpMethodType, userName, password);
	}

	/**
	 * Gets the http method response body as string.
	 * 
	 * @param uri
	 *            the uri
	 * @param httpMethodType
	 *            the http method type
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return the http method response body as string
	 * @throws HttpClientUtilException
	 *             如果代码执行有异常会以HttpClientUtilException的形式抛出
	 */
	public static String getHttpMethodResponseBodyAsString(String uri,HttpMethodType httpMethodType,String userName,String password)
			throws HttpClientUtilException{
		HttpMethod httpMethod = getHttpMethodWithExecute(uri, httpMethodType, userName, password);
		return getHttpMethodResponseBodyAsString(httpMethod);
	}

	/**
	 * 获得HttpMethod请求结果.
	 * 
	 * @param uri
	 *            the uri
	 * @param httpMethodType
	 *            the http method type
	 * @param header
	 *            如果需要header
	 * @return the http method response body as string
	 * @throws HttpClientUtilException
	 *             如果代码执行有异常会以HttpClientUtilException的形式抛出
	 */
	public static String getHttpMethodResponseBodyAsString(String uri,HttpMethodType httpMethodType,Header header)
			throws HttpClientUtilException{
		HttpMethod httpMethod = getHttpMethodWithExecute(uri, httpMethodType);
		httpMethod.setRequestHeader(header);
		return getHttpMethodResponseBodyAsString(httpMethod);
	}

	// ****************************************************************************************

	/**
	 * 获得PostMethod请求结果.
	 * 
	 * @param uri
	 *            uri
	 * @param nameValuePairs
	 *            nameValuePairs
	 * @return if has error/exception,return null
	 * @throws HttpClientUtilException
	 *             如果代码执行有异常会以HttpClientUtilException的形式抛出
	 */
	public static String getPostMethodResponseBodyAsString(String uri,NameValuePair[] nameValuePairs) throws HttpClientUtilException{
		// 使用POST方法
		HttpMethod httpMethod = getPostMethodWithParams(uri, nameValuePairs);
		HttpMethodParams params = httpMethod.getParams();
		params.setContentCharset(CharsetType.UTF8);
		return getHttpMethodResponseBodyAsString(httpMethod);
	}

	// ****************************************************************************************

	/**
	 * 获得HttpMethod.
	 * 
	 * @param uri
	 *            uri
	 * @param httpMethodType
	 *            http请求方法
	 * @return if has error/exception,return null
	 * @throws HttpClientUtilException
	 *             如果代码执行有异常会以HttpClientUtilException的形式抛出
	 */
	public static HttpMethod getHttpMethodWithExecute(String uri,HttpMethodType httpMethodType) throws HttpClientUtilException{
		String userName = null;
		String password = null;
		return getHttpMethodWithExecute(uri, httpMethodType, userName, password);
	}

	// ****************************************************************************************

	/**
	 * with UsernamePassword will create UsernamePasswordCredentials.
	 * 
	 * @param uri
	 *            the uri
	 * @param httpMethodType
	 *            the http method type
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @return the http method with execute
	 * @throws HttpClientUtilException
	 *             如果代码执行有异常会以HttpClientUtilException的形式抛出
	 */
	public static HttpMethod getHttpMethodWithExecute(String uri,HttpMethodType httpMethodType,String userName,String password)
			throws HttpClientUtilException{
		log.debug("uri:{}", uri);
		HttpMethod httpMethod = null;
		switch (httpMethodType) {
			case GET:
				// 使用get方法
				httpMethod = new GetMethod(uri);
				httpMethod.getParams().setParameter(HttpMethodParams.USER_AGENT, defaultUserAgent);
				break;
			case POST:
				// 使用post方法
				httpMethod = new PostMethod(uri);
				break;
		}

		log.debug("after httpMethod");
		return executeMethod(httpMethod, userName, password);
	}

	// ****************************************************************************************

	/**
	 * 获得带参数的 HttpMethod.
	 * 
	 * @param uri
	 *            the uri
	 * @param nameValuePairs
	 *            参数和值对
	 * @return if has error/exception,return null
	 * @throws HttpClientUtilException
	 *             如果代码执行有异常会以HttpClientUtilException的形式抛出
	 */
	public static HttpMethod getPostMethodWithParams(String uri,NameValuePair[] nameValuePairs) throws HttpClientUtilException{
		// 使用post方法
		PostMethod postMethod = new PostMethod(uri);
		postMethod.setRequestBody(nameValuePairs);
		return executeMethod(postMethod);
	}

	// ****************************************************************************************

	/**
	 * @param httpMethod
	 *            the http method
	 * @return the http method
	 * @throws HttpClientUtilException
	 *             the http client util exception
	 */
	/**
	 * httpClient.executeMethod
	 * 
	 * @param httpMethod
	 *            httpMethod
	 * @return
	 * @throws HttpClientUtilException
	 */
	private static HttpMethod executeMethod(HttpMethod httpMethod) throws HttpClientUtilException{
		String userName = null;
		String password = null;
		return executeMethod(httpMethod, userName, password);
	}

	// ********************************************************************************************

	/**
	 * use httpState to create httpmethod.
	 * 
	 * @param httpMethod
	 *            the http method
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return the http method
	 * @throws HttpClientUtilException
	 *             如果代码执行有异常会以HttpClientUtilException的形式抛出
	 */
	private static HttpMethod executeMethod(HttpMethod httpMethod,String userName,String password) throws HttpClientUtilException{

		log.debug("****************begin executeMethod**************");

		HttpClient httpClient = new HttpClient();

		if (Validator.isNotNullOrEmpty(userName) && Validator.isNotNullOrEmpty(password)){

			log.debug("userName:[{}],password:[{}]", userName, password);

			httpMethod.setDoAuthentication(true);

			HttpState httpState = new HttpState();
			UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials(userName, password);
			httpState.setCredentials(AuthScope.ANY, usernamePasswordCredentials);

			httpClient.setState(httpState);

			// 1.1抢先认证(Preemptive Authentication)
			// 在这种模式时，HttpClient会主动将basic认证应答信息传给服务器，即使在某种情况下服务器可能返回认证失败的应答，
			// 这样做主要是为了减少连接的建立。

			HttpClientParams params = new HttpClientParams();
			params.setAuthenticationPreemptive(true);
			httpClient.setParams(params);
		}

		// **************************************************************************************

		try{

			/**
			 * 执行该方法后服务器返回的状态码<br>
			 * 该状态码能表示出该方法执行是否成功、需要认证或者页面发生了跳转<br>
			 * （默认状态下GetMethod的实例是自动处理跳转的）
			 */
			int statusCode = httpClient.executeMethod(httpMethod);
			if (statusCode != HttpStatus.SC_OK){
				log.error("!statusCode is:[{}]", statusCode);
			}

		}catch (Exception e){
			Map<String, Object> map = getHttpMethodAttributeMapForLog(httpMethod);
			log.error("{},visit error,{}", JsonFormatUtil.format(map), e.getMessage());
			e.printStackTrace();

			throw new HttpClientUtilException("", e.getCause());
		}

		log.debug("************end executeMethod*****************");
		return httpMethod;
	}

	// ******************************************************************************

	/**
	 * 获得HttpMethod请求结果.
	 * 
	 * @param httpMethod
	 *            httpMethod
	 * @return the http method response body as string
	 * @throws HttpClientUtilException
	 *             如果代码执行有异常会以HttpClientUtilException的形式抛出
	 */
	private static String getHttpMethodResponseBodyAsString(HttpMethod httpMethod) throws HttpClientUtilException{
		// httpMethod.getParams().setContentCharset(CharsetType.UTF8);
		String requestResult = null;
		try{
			// 得到返回的数据
			requestResult = httpMethod.getResponseBodyAsString();
		}catch (Exception e){
			Map<String, Object> map = getHttpMethodAttributeMapForLog(httpMethod);
			log.error("{},visit error,", JsonFormatUtil.format(map), e.getMessage());
			e.printStackTrace();

			throw new HttpClientUtilException("", e.getCause());
		}finally{
			// 释放连接
			httpMethod.releaseConnection();
		}
		return requestResult;
	}

	// ******************************************************************************
	/**
	 * Gets the http method attribute map for log.
	 * 
	 * @param httpMethod
	 *            the http method
	 * @return the http method attribute map for log
	 */
	private static Map<String, Object> getHttpMethodAttributeMapForLog(HttpMethod httpMethod){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("httpMethod.getName()", httpMethod.getName());
		map.put("httpMethod.getPath()", httpMethod.getPath());
		map.put("httpMethod.getQueryString()", httpMethod.getQueryString());
		// object.put("", httpMethod.getResponseBodyAsString());
		map.put("httpMethod.getStatusText()", httpMethod.getStatusText());
		map.put("httpMethod.getDoAuthentication()", httpMethod.getDoAuthentication());
		map.put("httpMethod.getFollowRedirects()", httpMethod.getFollowRedirects());
		map.put("httpMethod.getHostAuthState()", httpMethod.getHostAuthState());
		map.put("httpMethod.getHostConfiguration()", httpMethod.getHostConfiguration());
		map.put("httpMethod.getParams()", httpMethod.getParams());
		map.put("httpMethod.getProxyAuthState()", httpMethod.getProxyAuthState());
		map.put("httpMethod.getRequestHeaders()", httpMethod.getRequestHeaders());
		// object.put("", httpMethod.getResponseBody());
		map.put("httpMethod.getResponseFooters()", httpMethod.getResponseFooters());
		map.put("httpMethod.getResponseHeaders()", httpMethod.getResponseHeaders());
		map.put("httpMethod.getStatusCode()", httpMethod.getStatusCode());
		map.put("httpMethod.getStatusLine()", httpMethod.getStatusLine());
		try{
			map.put("httpMethod.getURI()", httpMethod.getURI());
		}catch (URIException e1){
			e1.printStackTrace();
		}
		return map;
	}

}
