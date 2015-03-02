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
package com.feilong.tools.mail.internet;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.InternetAddress;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.CharsetType;

/**
 * The Class InternetAddressUtilTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年8月26日 下午10:54:42
 * @since 1.0.8
 */
public class InternetAddressUtilTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(InternetAddressUtilTest.class);

	/**
	 * Test.
	 *
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 */
	@Test
	public final void test() throws UnsupportedEncodingException{
		InternetAddress internetAddress = new InternetAddress("longxia@baozun.cn", "夏龙", CharsetType.UTF8);
		InternetAddress internetAddress1 = new InternetAddress("zhen.yao@baozun.cn", "姚真", CharsetType.UTF8);

		InternetAddress[] internetAddresses = { internetAddress, internetAddress1 };

		log.debug("\n{}", internetAddress.toUnicodeString());
		log.debug("\n{}", InternetAddress.toString(internetAddresses, 0));
	}
}
