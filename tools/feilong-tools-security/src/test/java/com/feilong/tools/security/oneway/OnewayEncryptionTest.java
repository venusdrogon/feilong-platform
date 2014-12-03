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
package com.feilong.tools.security.oneway;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.tools.security.BaseSecurityTest;
import com.feilong.tools.security.oneway.OnewayEncryption;
import com.feilong.tools.security.oneway.OnewayType;

/**
 * The Class OnewayEncryptionTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 14, 2013 8:14:12 PM
 */
@SuppressWarnings("all")
//无访问控制符修饰的内容可以被同一个包中的类访问，
class OnewayEncryptionTest extends BaseSecurityTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(OnewayEncryptionTest.class);

	/**
	 * Test method for
	 * {@link com.feilong.tools.security.oneway.OnewayEncryption#encode(com.feilong.tools.security.oneway.OnewayType, java.lang.String)}
	 * .
	 */
	@Test
	public final void testEncodeOnewayTypeString(){
		// 正确
		assertEquals("7eca689f0d3389d9dea66ae112e5cfd7", OnewayEncryption.encode(OnewayType.MD5, "你好"));
		// 正确
		assertEquals("440ee0853ad1e99f962b63e459ef992d7c211722", OnewayEncryption.encode(OnewayType.SHA, "你好"));
		// 正确
		assertEquals("440ee0853ad1e99f962b63e459ef992d7c211722", OnewayEncryption.encode(OnewayType.SHA1, "你好"));
		// 正确
		assertEquals("670d9743542cae3ea7ebe36af56bd53648b0a1126162e78d81a32934a711302e", OnewayEncryption.encode(OnewayType.SHA256, "你好"));
		// 正确
		assertEquals(
				"05f076c7d180e91d80a56d70b226fca01e2353554c315ac1e8caaaeca2ce0dc0d9d84e206a2bf1143a0ae1b9be9bcfa8",
				OnewayEncryption.encode(OnewayType.SHA384, "你好"));
		// 正确
		assertEquals(
				"5232181bc0d9888f5c9746e410b4740eb461706ba5dacfbc93587cecfc8d068bac7737e92870d6745b11a25e9cd78b55f4ffc706f73cfcae5345f1b53fb8f6b5",
				OnewayEncryption.encode(OnewayType.SHA512, "你好"));

		// log.info(OnewayEncryption.encode(OnewayType.HmacSHA512, "你好"));
	}

	/**
	 * Name.
	 */
	@Test
	public void name(){
		String origin = "你好";
		log.debug(debugSecurityValue(OnewayEncryption.encode(OnewayType.MD5, origin, CharsetType.UTF8)));
		log.debug(debugSecurityValue(OnewayEncryption.encode(OnewayType.SHA, origin, CharsetType.UTF8)));
		log.debug(debugSecurityValue(OnewayEncryption.encode(OnewayType.SHA1, origin, CharsetType.UTF8)));
		log.debug(debugSecurityValue(OnewayEncryption.encode(OnewayType.SHA256, origin, CharsetType.UTF8)));
		log.debug(debugSecurityValue(OnewayEncryption.encode(OnewayType.SHA384, origin, CharsetType.UTF8)));
		log.debug(debugSecurityValue(OnewayEncryption.encode(OnewayType.SHA512, origin, CharsetType.UTF8)));
	}
}
