package com.feilong.tools.net;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.enumeration.HttpMethodType;

/**
 * HttpClient 相关工具类
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-11-18 上午09:35:27
 * @version 1.1 2011-7-27 11:59
 */
public final class HttpClientUtil{

	private final static Logger	log	= LoggerFactory.getLogger(HttpClientUtil.class);

	/**
	 * 执行该方法后服务器返回的状态码<br>
	 * 该状态码能表示出该方法执行是否成功、需要认证或者页面发生了跳转<br>
	 * （默认状态下GetMethod的实例是自动处理跳转的）
	 */
	private static int			statusCode;

	/*************************************************************************************************/
	/**
	 * 获得HttpMethod请求结果
	 * 
	 * @param uri
	 *            路径
	 * @param httpMethodType
	 *            请求方式
	 * @return if has error/exception,return null
	 */
	public static String getHttpMethodResponseBodyAsString(String uri,HttpMethodType httpMethodType){
		HttpMethod httpMethod = getHttpMethodWithExecute(uri, httpMethodType);
		return getHttpMethodResponseBodyAsString(httpMethod);
	}

	/**
	 * 获得HttpMethod请求结果
	 * 
	 * @param uri
	 * @param httpMethodType
	 * @param header
	 *            如果需要header
	 * @return
	 */
	public static String getHttpMethodResponseBodyAsString(String uri,HttpMethodType httpMethodType,Header header){
		HttpMethod httpMethod = getHttpMethodWithExecute(uri, httpMethodType);
		httpMethod.setRequestHeader(header);
		return getHttpMethodResponseBodyAsString(httpMethod);
	}

	/**
	 * 获得PostMethod请求结果
	 * 
	 * @param uri
	 *            uri
	 * @param nameValuePairs
	 *            nameValuePairs
	 * @return if has error/exception,return null
	 */
	public static String getPostMethodResponseBodyAsString(String uri,NameValuePair[] nameValuePairs){
		// 使用POST方法
		HttpMethod httpMethod = getPostMethodWithParams(uri, nameValuePairs);
		HttpMethodParams params = httpMethod.getParams();
		params.setContentCharset(CharsetType.UTF8);
		return getHttpMethodResponseBodyAsString(httpMethod);
	}

	/**
	 * 获得HttpMethod
	 * 
	 * @param uri
	 *            uri
	 * @param httpMethodType
	 *            http请求方法
	 * @return if has error/exception,return null
	 */
	public static HttpMethod getHttpMethodWithExecute(String uri,HttpMethodType httpMethodType){
		log.debug("uri:{}", uri);
		HttpMethod httpMethod = null;
		switch (httpMethodType) {
			case GET:
				// 使用get方法
				httpMethod = new GetMethod(uri);
				httpMethod.getParams().setParameter(
						HttpMethodParams.USER_AGENT,
						"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21");
				break;
			case POST:
				// 使用post方法
				httpMethod = new PostMethod(uri);
				break;
		}
		log.debug("after GetMethod");
		return executeMethod(httpMethod);
	}

	/**
	 * 获得带参数的 HttpMethod
	 * 
	 * @param uri
	 * @param nameValuePairs
	 *            参数和值对
	 * @return if has error/exception,return null
	 */
	public static HttpMethod getPostMethodWithParams(String uri,NameValuePair[] nameValuePairs){
		// 使用post方法
		PostMethod postMethod = new PostMethod(uri);
		postMethod.setRequestBody(nameValuePairs);
		return executeMethod(postMethod);
	}

	/*************************************************************************************************/
	/**
	 * httpClient.executeMethod
	 * 
	 * @param httpMethod
	 *            httpMethod
	 * @return if has error/exception,return null
	 */
	private static HttpMethod executeMethod(HttpMethod httpMethod){
		HttpClient httpClient = new HttpClient();
		try{
			log.debug("before executeMethod");
			statusCode = httpClient.executeMethod(httpMethod);
			if (statusCode != HttpStatus.SC_OK){
				log.error("statusCode is :" + statusCode);
			}
			log.debug("after executeMethod");
			return httpMethod;
		}catch (HttpException e){
			log.error("{},visit error,{}", httpMethod.getPath() + httpMethod.getQueryString(), e.getMessage());
			e.printStackTrace();
		}catch (IOException e){
			log.error("{},visit error,{}", httpMethod.getPath() + httpMethod.getQueryString(), e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得HttpMethod请求结果
	 * 
	 * @param httpMethod
	 *            httpMethod
	 * @return if has error/exception,return null
	 */
	private static String getHttpMethodResponseBodyAsString(HttpMethod httpMethod){
		// httpMethod.getParams().setContentCharset(CharsetType.UTF8);
		String requestResult = null;
		try{
			// 得到返回的数据
			requestResult = httpMethod.getResponseBodyAsString();
		}catch (Exception e){
			log.error(httpMethod.getPath() + httpMethod.getQueryString() + ",visit error," + e.getMessage());
		}
		// 释放连接
		httpMethod.releaseConnection();
		return requestResult;
	}

	/**
	 * 获得HttpMethod请求结果
	 * 
	 * @param uri
	 *            路径
	 * @param httpMethodType
	 *            请求方式
	 * @return 获得HttpMethod请求结果
	 * @author 铁彪
	 */
	public static String getHttpMethodResponseBodyAsStringIgnoreCharSet(String uri,HttpMethodType httpMethodType,String charSet){
		HttpMethod httpMethod = getHttpMethodWithExecute(uri, httpMethodType);
		httpMethod.getParams().setContentCharset(charSet);
		return getHttpMethodResponseBodyAsString(httpMethod);
	}
}
