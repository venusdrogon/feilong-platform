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
package com.feilong.commons.core.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.IOUtil;
import com.feilong.commons.core.util.Validator;

/**
 * URLConnectionUtil.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 26, 2013 11:10:59 AM
 * @since 1.0.2
 */
public final class URLConnectionUtil{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(URLConnectionUtil.class);

	/**
	 * Read line with proxy.
	 * 
	 * @param urlString
	 *            the url string
	 * @return 如果有异常返回 null,否则 读取一个文本行。通过下列字符之一即可认为某行已终止：换行 ('\n')、回车 ('\r') 或回车后直接跟着换行。
	 */
	public static String readLine(String urlString){
		Proxy proxy = null;
		HttpURLConnectionParam httpURLConnectionParam = null;
		return readLineWithProxy(urlString, proxy, httpURLConnectionParam);
	}

	/**
	 * Read line with proxy.
	 * 
	 * @param urlString
	 *            the url string
	 * @param proxyAddress
	 *            the proxy address
	 * @param proxyPort
	 *            代理端口 <br>
	 *            A valid port value is between 0 ~ 65535. <br>
	 *            A port number of zero will let the system pick up an ephemeral port in a bind operation.
	 * @return the string
	 */
	public static String readLineWithProxy(String urlString,String proxyAddress,Integer proxyPort){
		HttpURLConnectionParam httpURLConnectionParam = null;
		return readLineWithProxy(urlString, proxyAddress, proxyPort, httpURLConnectionParam);
	}

	/**
	 * responseBodyAsStringWithProxy.
	 * 
	 * @param urlString
	 *            the url string
	 * @param proxyAddress
	 *            the proxy address
	 * @param proxyPort
	 *            代理端口 <br>
	 *            A valid port value is between 0 ~ 65535. <br>
	 *            A port number of zero will let the system pick up an ephemeral port in a bind operation.
	 * @return the string
	 */
	public static String getResponseBodyAsStringWithProxy(String urlString,String proxyAddress,Integer proxyPort){
		HttpURLConnectionParam httpURLConnectionParam = null;
		return getResponseBodyAsStringWithProxy(urlString, proxyAddress, proxyPort, httpURLConnectionParam);
	}

	/**
	 * Read line with proxy.
	 * 
	 * @param urlString
	 *            the url string
	 * @param proxyAddress
	 *            the proxy address
	 * @param proxyPort
	 *            代理端口 <br>
	 *            A valid port value is between 0 ~ 65535. <br>
	 *            A port number of zero will let the system pick up an ephemeral port in a bind operation.
	 * @param httpURLConnectionParam
	 *            the http url connection param
	 * @return the string
	 */
	public static String getResponseBodyAsStringWithProxy(
			String urlString,
			String proxyAddress,
			Integer proxyPort,
			HttpURLConnectionParam httpURLConnectionParam){
		Proxy proxy = getProxy(proxyAddress, proxyPort);
		return getResponseBodyAsStringWithProxy(urlString, proxy, httpURLConnectionParam);
	}

	/**
	 * Read line with proxy.
	 * 
	 * @param urlString
	 *            the url string
	 * @param proxyAddress
	 *            the proxy address
	 * @param proxyPort
	 *            代理端口 <br>
	 *            A valid port value is between 0 ~ 65535. <br>
	 *            A port number of zero will let the system pick up an ephemeral port in a bind operation.
	 * @param httpURLConnectionParam
	 *            the http url connection param
	 * @return the string
	 */
	public static String readLineWithProxy(
			String urlString,
			String proxyAddress,
			Integer proxyPort,
			HttpURLConnectionParam httpURLConnectionParam){
		Proxy proxy = getProxy(proxyAddress, proxyPort);
		return readLineWithProxy(urlString, proxy, httpURLConnectionParam);
	}

	/**
	 * Read line with proxy.
	 * 
	 * @param urlString
	 *            the url string
	 * @param proxy
	 *            代理, 如果为null 则不设置代理
	 * @return 如果有异常返回 null,否则 读取一个文本行。通过下列字符之一即可认为某行已终止：换行 ('\n')、回车 ('\r') 或回车后直接跟着换行。
	 */
	public static String readLineWithProxy(String urlString,Proxy proxy){
		HttpURLConnectionParam httpURLConnectionParam = null;
		return readLineWithProxy(urlString, proxy, httpURLConnectionParam);
	}

	/**
	 * responseBodyAsStringWithProxy.
	 * 
	 * @param urlString
	 *            the url string
	 * @param proxy
	 *            代理, 如果为null 则不设置代理
	 * @return 如果有异常返回 null,否则 读取一个文本行。通过下列字符之一即可认为某行已终止：换行 ('\n')、回车 ('\r') 或回车后直接跟着换行。
	 */
	public static String getResponseBodyAsStringWithProxy(String urlString,Proxy proxy){
		HttpURLConnectionParam httpURLConnectionParam = null;
		return getResponseBodyAsStringWithProxy(urlString, proxy, httpURLConnectionParam);
	}

	/**
	 * Read line with proxy.
	 * 
	 * @param urlString
	 *            the url string
	 * @param proxy
	 *            代理, 如果为null 则不设置代理
	 * @param httpURLConnectionParam
	 *            httpURLConnectionParam
	 * @return 如果有异常返回 null,否则 读取一个文本行。通过下列字符之一即可认为某行已终止：换行 ('\n')、回车 ('\r') 或回车后直接跟着换行。
	 */
	public static String readLineWithProxy(String urlString,Proxy proxy,HttpURLConnectionParam httpURLConnectionParam){
		return _readLine(urlString, proxy, httpURLConnectionParam);
	}

	/**
	 * responseBodyAsStringWithProxy.
	 * 
	 * @param urlString
	 *            the url string
	 * @param proxy
	 *            代理, 如果为null 则不设置代理
	 * @param httpURLConnectionParam
	 *            httpURLConnectionParam
	 * @return 如果有异常返回 null,否则 读取一个文本行。通过下列字符之一即可认为某行已终止：换行 ('\n')、回车 ('\r') 或回车后直接跟着换行。
	 */
	public static String getResponseBodyAsStringWithProxy(String urlString,Proxy proxy,HttpURLConnectionParam httpURLConnectionParam){
		return _getResponseBodyAsStringWithProxy(urlString, proxy, httpURLConnectionParam);
	}

	/**
	 * Gets the response body as string.
	 * 
	 * @param urlString
	 *            the url string
	 * @return the response body as string
	 */
	public static String getResponseBodyAsString(String urlString){
		Proxy proxy = null;
		HttpURLConnectionParam httpURLConnectionParam = null;
		return _getResponseBodyAsStringWithProxy(urlString, proxy, httpURLConnectionParam);
	}

	// ****************************************************************************************
	/**
	 * 获得 HttpURLConnection.
	 * 
	 * @param urlString
	 *            the url string
	 * @param proxy
	 *            代理, 如果为null 则不设置代理
	 * @param httpURLConnectionParam
	 *            httpURLConnectionParam
	 * @return the http url connection
	 */
	private static HttpURLConnection getHttpURLConnection(String urlString,Proxy proxy,HttpURLConnectionParam httpURLConnectionParam){
		if (null == httpURLConnectionParam){
			httpURLConnectionParam = new HttpURLConnectionParam();
		}
		HttpURLConnection httpURLConnection = null;
		try{

			log.debug("urlString:{}", urlString);
			URL url = new URL(urlString);

			// 此处的urlConnection对象实际上是根据URL的请求协议(此处是http)生成的URLConnection类的子类HttpURLConnection,
			// 故此处最好将其转化 为HttpURLConnection类型的对象,以便用到 HttpURLConnection更多的API.
			if (Validator.isNotNullOrEmpty(proxy)){
				log.debug("use proxy:{}", proxy.toString());
				httpURLConnection = (HttpURLConnection) url.openConnection(proxy);
			}else{
				// 每次调用此 URL 的协议处理程序的 openConnection 方法都打开一个新的连接。
				httpURLConnection = (HttpURLConnection) url.openConnection();
			}

			// **************************************************************************
			int connectTimeout = httpURLConnectionParam.getConnectTimeout();
			int readTimeout = httpURLConnectionParam.getReadTimeout();

			// 一定要为HttpUrlConnection设置connectTimeout属性以防止连接被阻塞
			httpURLConnection.setConnectTimeout(connectTimeout);
			httpURLConnection.setReadTimeout(readTimeout);

			//  此处getOutputStream会隐含的进行connect(即：如同调用上面的connect()方法，  
			//  所以在开发中不调用上述的connect()也可以)。 

			// 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
			// 如果在已打开连接（此时 connected 字段的值为 true）的情况下调用 connect 方法，则忽略该调用。

			// 实际上只是建立了一个与服务器的tcp连接,并没有实际发送http请求。 
			// 无论是post还是get,http请求实际上直到HttpURLConnection的getInputStream()这个函数里面才正式发送出去。 
			// httpURLConnection.connect();
			return httpURLConnection;
		}catch (MalformedURLException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}

		return null;
	}

	// ****************************************************************************************

	/**
	 * Read line with proxy.
	 * 
	 * @param urlString
	 *            the url string
	 * @param proxy
	 *            代理, 如果为null 则不设置代理
	 * @param httpURLConnectionParam
	 *            httpURLConnectionParam
	 * @return 如果有异常返回 null,否则 读取一个文本行。通过下列字符之一即可认为某行已终止：换行 ('\n')、回车 ('\r') 或回车后直接跟着换行。
	 */
	private static String _readLine(String urlString,Proxy proxy,HttpURLConnectionParam httpURLConnectionParam){
		HttpURLConnection httpURLConnection = getHttpURLConnection(urlString, proxy, httpURLConnectionParam);
		try{
			BufferedReader bufferedReader = getBufferedReader(httpURLConnection);
			if (null != bufferedReader){
				// 读取一个文本行。通过下列字符之一即可认为某行已终止：换行 ('\n')、回车 ('\r') 或回车后直接跟着换行。
				String readLine = bufferedReader.readLine();
				return readLine;
			}
		}catch (IOException e){
			e.printStackTrace();
		}finally{
			if (null != httpURLConnection){
				// 指示近期服务器不太可能有其他请求。调用 disconnect() 并不意味着可以对其他请求重用此 HttpURLConnection 实例。
				httpURLConnection.disconnect();
			}
		}

		return null;
	}

	/**
	 * _get response body as string with proxy.
	 * 
	 * @param urlString
	 *            the url string
	 * @param proxy
	 *            the proxy
	 * @param httpURLConnectionParam
	 *            the http url connection param
	 * @return the string
	 */
	private static String _getResponseBodyAsStringWithProxy(String urlString,Proxy proxy,HttpURLConnectionParam httpURLConnectionParam){
		HttpURLConnection httpURLConnection = getHttpURLConnection(urlString, proxy, httpURLConnectionParam);
		if (null != httpURLConnection){

			try{
				InputStream inputStream = httpURLConnection.getInputStream();
				String inputStream2String = IOUtil.inputStream2String(inputStream);
				return inputStream2String;
			}catch (IOException e){
				e.printStackTrace();
			}finally{
				if (null != httpURLConnection){
					// 指示近期服务器不太可能有其他请求。调用 disconnect() 并不意味着可以对其他请求重用此 HttpURLConnection 实例。
					httpURLConnection.disconnect();
				}
			}
		}
		return null;
	}

	// ****************************************************************************************
	/**
	 * Gets the buffered reader.
	 * 
	 * @param httpURLConnection
	 *            the http url connection
	 * @return the buffered reader
	 */
	private static BufferedReader getBufferedReader(HttpURLConnection httpURLConnection){
		if (null != httpURLConnection){
			try{

				InputStream inputStream = httpURLConnection.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				return bufferedReader;
			}catch (IOException e){
				e.printStackTrace();
			}
		}

		return null;
	}

	// ******************************************************************************************

	/**
	 * 获得代理.
	 * 
	 * @param proxyAddress
	 *            the proxy address
	 * @param proxyPort
	 *            代理端口 <br>
	 *            A valid port value is between 0 ~ 65535. <br>
	 *            A port number of zero will let the system pick up an ephemeral port in a bind operation.
	 * @return the proxy
	 */
	private static Proxy getProxy(String proxyAddress,Integer proxyPort){
		Proxy proxy = null;
		if (Validator.isNotNullOrEmpty(proxyAddress) && Validator.isNotNullOrEmpty(proxyPort)){
			proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAddress, proxyPort));
		}
		return proxy;
	}

}
