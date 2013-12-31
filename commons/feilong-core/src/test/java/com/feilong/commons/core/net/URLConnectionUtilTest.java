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
