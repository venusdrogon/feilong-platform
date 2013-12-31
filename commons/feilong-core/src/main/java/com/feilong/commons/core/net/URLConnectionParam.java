/**
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
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package com.feilong.commons.core.net;

/**
 * URLConnectionParam.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 27, 2013 4:56:57 PM
 * @since 1.0.2
 */
public abstract class URLConnectionParam{

	/**
	 * 设置一个指定的超时值（以毫秒为单位），该值将在打开到此 URLConnection 引用的资源的通信链接时使用。<br>
	 * 超时时间为零表示无穷大超时。<br>
	 * 如果在建立连接之前超时期满，则会引发一个 java.net.SocketTimeoutException。
	 */
	private int	connectTimeout	= 60000;

	/**
	 * 将读超时设置为指定的超时值，以毫秒为单位。用一个非零值指定在建立到资源的连接后从 Input 流读入时的超时时间。<br>
	 * 超时时间为零表示无穷大超时。<br>
	 * 如果在数据可读取之前超时期满，则会引发一个 java.net.SocketTimeoutException。
	 */
	private int	readTimeout		= 60000;

	/**
	 * Gets the 设置一个指定的超时值（以毫秒为单位），该值将在打开到此 URLConnection 引用的资源的通信链接时使用。<br>
	 * 超时时间为零表示无穷大超时。<br>
	 * 如果在建立连接之前超时期满，则会引发一个 java.
	 * 
	 * @return the connectTimeout
	 */
	public int getConnectTimeout(){
		return connectTimeout;
	}

	/**
	 * Sets the 设置一个指定的超时值（以毫秒为单位），该值将在打开到此 URLConnection 引用的资源的通信链接时使用。<br>
	 * 超时时间为零表示无穷大超时。<br>
	 * 如果在建立连接之前超时期满，则会引发一个 java.
	 * 
	 * @param connectTimeout
	 *            the connectTimeout to set
	 */
	public void setConnectTimeout(int connectTimeout){
		this.connectTimeout = connectTimeout;
	}

	/**
	 * Gets the 将读超时设置为指定的超时值，以毫秒为单位。用一个非零值指定在建立到资源的连接后从 Input 流读入时的超时时间。<br>
	 * 超时时间为零表示无穷大超时。<br>
	 * 如果在数据可读取之前超时期满，则会引发一个 java.
	 * 
	 * @return the readTimeout
	 */
	public int getReadTimeout(){
		return readTimeout;
	}

	/**
	 * Sets the 将读超时设置为指定的超时值，以毫秒为单位。用一个非零值指定在建立到资源的连接后从 Input 流读入时的超时时间。<br>
	 * 超时时间为零表示无穷大超时。<br>
	 * 如果在数据可读取之前超时期满，则会引发一个 java.
	 * 
	 * @param readTimeout
	 *            the readTimeout to set
	 */
	public void setReadTimeout(int readTimeout){
		this.readTimeout = readTimeout;
	}
}
