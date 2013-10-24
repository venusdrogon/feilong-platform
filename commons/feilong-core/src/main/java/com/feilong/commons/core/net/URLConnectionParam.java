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
