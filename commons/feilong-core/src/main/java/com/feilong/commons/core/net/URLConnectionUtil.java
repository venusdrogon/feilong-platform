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
package com.feilong.commons.core.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.InputStreamUtil;
import com.feilong.commons.core.io.ReaderUtil;
import com.feilong.commons.core.io.UncheckedIOException;
import com.feilong.commons.core.util.Validator;

/**
 * URLConnectionUtil(支持代理).
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 26, 2013 11:10:59 AM
 * @see java.net.HttpURLConnection
 * @see java.net.URLConnection
 * @since 1.0.2
 */
public final class URLConnectionUtil{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(URLConnectionUtil.class);

    //********************************************************************************************
    /** Don't let anyone instantiate this class. */
    private URLConnectionUtil(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    //********************************************************************************************

    /**
     * Read line with proxy.
     *
     * @param urlString
     *            the url string
     * @return 如果有异常返回 null,否则 读取一个文本行.通过下列字符之一即可认为某行已终止：换行 ('\n')、回车 ('\r') 或回车后直接跟着换行.
     * @throws UncheckedIOException
     *             the unchecked io exception
     */
    public static String readLine(String urlString) throws UncheckedIOException{
        return readLine(urlString, null);
    }

    /**
     * Read line with proxy.
     *
     * @param urlString
     *            the url string
     * @param httpURLConnectionParam
     *            the http url connection param
     * @return the string
     * @throws UncheckedIOException
     *             the unchecked io exception
     */
    public static String readLine(String urlString,HttpURLConnectionParam httpURLConnectionParam) throws UncheckedIOException{
        InputStream inputStream = getInputStream(urlString, httpURLConnectionParam);
        BufferedReader bufferedReader = InputStreamUtil.toBufferedReader(inputStream, httpURLConnectionParam.getContentCharset());

        if (null != bufferedReader){
            return ReaderUtil.readLine(bufferedReader);
        }
        return null;
    }

    //********************************************************************************************
    /**
     * Gets the response body as string.
     *
     * @param urlString
     *            the url string
     * @return the response body as string
     * @throws UncheckedIOException
     *             the unchecked io exception
     * @see HttpURLConnectionParam
     */
    public static String getResponseBodyAsString(String urlString) throws UncheckedIOException{
        return getResponseBodyAsString(urlString, null);
    }

    /**
     * 获得 response body as string.
     *
     * @param urlString
     *            the url string
     * @param httpURLConnectionParam
     *            httpURLConnectionParam
     * @return 如果有异常返回 null,否则 读取一个文本行.通过下列字符之一即可认为某行已终止：换行 ('\n')、回车 ('\r') 或回车后直接跟着换行.
     * @throws UncheckedIOException
     *             the unchecked io exception
     */
    public static String getResponseBodyAsString(String urlString,HttpURLConnectionParam httpURLConnectionParam)
                    throws UncheckedIOException{
        InputStream inputStream = getInputStream(urlString, httpURLConnectionParam);
        String inputStream2String = InputStreamUtil.inputStream2String(inputStream, httpURLConnectionParam.getContentCharset());
        return inputStream2String;

    }

    /**
     * 获得 input stream.
     *
     * @param urlString
     *            the url string
     * @return the input stream
     * @throws UncheckedIOException
     *             the unchecked io exception
     */
    public static InputStream getInputStream(String urlString) throws UncheckedIOException{
        return getInputStream(urlString, null);
    }

    /**
     * 获得 input stream.
     *
     * @param urlString
     *            the url string
     * @param httpURLConnectionParam
     *            the http url connection param
     * @return the input stream
     * @throws UncheckedIOException
     *             the unchecked io exception
     */
    public static InputStream getInputStream(String urlString,HttpURLConnectionParam httpURLConnectionParam) throws UncheckedIOException{
        if (null == httpURLConnectionParam){
            httpURLConnectionParam = new HttpURLConnectionParam();
        }

        HttpURLConnection httpURLConnection = getHttpURLConnection(urlString, httpURLConnectionParam);

        if (null != httpURLConnection){

            try{
                InputStream inputStream = httpURLConnection.getInputStream();
                return inputStream;
            }catch (IOException e){
                throw new UncheckedIOException(e);
            }finally{
                //TODO
                // 指示近期服务器不太可能有其他请求.调用 disconnect() 并不意味着可以对其他请求重用此 HttpURLConnection 实例.
                // httpURLConnection.disconnect();
            }
        }
        return null;
    }

    // ****************************************************************************************
    /**
     * 获得 HttpURLConnection.
     *
     * @param urlString
     *            the url string
     * @param httpURLConnectionParam
     *            httpURLConnectionParam
     * @return the http url connection
     * @throws UncheckedIOException
     *             the unchecked io exception
     */
    private static HttpURLConnection getHttpURLConnection(String urlString,HttpURLConnectionParam httpURLConnectionParam)
                    throws UncheckedIOException{

        HttpURLConnection httpURLConnection = null;
        try{

            log.debug("url String:[{}]", urlString);
            URL url = new URL(urlString);

            Proxy proxy = getProxy(httpURLConnectionParam.getProxyAddress(), httpURLConnectionParam.getProxyPort());

            // 此处的urlConnection对象实际上是根据URL的请求协议(此处是http)生成的URLConnection类的子类HttpURLConnection,
            // 故此处最好将其转化 为HttpURLConnection类型的对象,以便用到 HttpURLConnection更多的API.
            if (Validator.isNotNullOrEmpty(proxy)){
                log.debug("use proxy:{}", proxy.toString());
                httpURLConnection = (HttpURLConnection) url.openConnection(proxy);
            }else{
                // 每次调用此 URL 的协议处理程序的 openConnection 方法都打开一个新的连接.
                httpURLConnection = (HttpURLConnection) url.openConnection();
            }

            // **************************************************************************
            int connectTimeout = httpURLConnectionParam.getConnectTimeout();
            int readTimeout = httpURLConnectionParam.getReadTimeout();

            // 一定要为HttpUrlConnection设置connectTimeout属性以防止连接被阻塞
            httpURLConnection.setConnectTimeout(connectTimeout);

            httpURLConnection.setReadTimeout(readTimeout);

            //  此处getOutputStream会隐含的进行connect(即：如同调用上面的connect()方法，  
            //  所以在开发中不调用上述的connect()也可以). 

            // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）.
            // 如果在已打开连接（此时 connected 字段的值为 true）的情况下调用 connect 方法，则忽略该调用.

            // 实际上只是建立了一个与服务器的tcp连接,并没有实际发送http请求. 
            // 无论是post还是get,http请求实际上直到HttpURLConnection的getInputStream()这个函数里面才正式发送出去. 
            // httpURLConnection.connect();
            return httpURLConnection;
        }catch (MalformedURLException e){
            throw new UncheckedIOException(e);
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }
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
