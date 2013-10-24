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

import java.net.Proxy;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 27, 2013 10:55:18 AM
 */
public class URLConnectionUtilTest{

	private static final Logger	log				= LoggerFactory.getLogger(URLConnectionUtilTest.class);

	private String				urlString		= "http://www.nikestore.com.cn/?t=" + System.currentTimeMillis();

	private String				proxyAddress	= "10.8.12.205";

	private int					proxyPort		= 3128;

	/**
	 * Test method for
	 * {@link com.feilong.commons.core.net.URLConnectionUtil#readLineWithProxy(java.lang.String, java.lang.String, java.lang.Integer)} .
	 */
	@Test
	public final void readLineWithProxy1(){
		Proxy proxy = null;

		HttpURLConnectionParam httpURLConnectionParam = new HttpURLConnectionParam();

		String readLineWithProxy = URLConnectionUtil.readLineWithProxy(urlString, proxy, httpURLConnectionParam);
		log.info(readLineWithProxy);
	}

	/**
	 * Test method for {@link com.feilong.commons.core.net.URLConnectionUtil#readLineWithProxy(java.lang.String, java.net.Proxy)}.
	 */
	@Test
	public final void readLineWithProxy(){
		String readLineWithProxy = URLConnectionUtil.readLineWithProxy(urlString, proxyAddress, proxyPort);
		log.info(readLineWithProxy);
	}

	/**
	 * Test method for {@link com.feilong.commons.core.net.URLConnectionUtil#readLineWithProxy(java.lang.String, java.net.Proxy)}.
	 */
	@Test
	public final void getResponseBodyAsStringWithProxy(){
		String responseBodyAsStringWithProxy = URLConnectionUtil.getResponseBodyAsStringWithProxy(urlString, proxyAddress, proxyPort);
		log.info(responseBodyAsStringWithProxy);
	}

	/**
	 * Test method for {@link com.feilong.commons.core.net.URLConnectionUtil#readLineWithProxy(java.lang.String, java.net.Proxy)}.
	 */
	@Test
	public final void getResponseBodyAsString(){
		String responseBodyAsString = URLConnectionUtil.getResponseBodyAsString(urlString);
		log.info(responseBodyAsString);
	}
}
