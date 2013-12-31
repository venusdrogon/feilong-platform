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
package com.feilong.commons.core.security;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 14, 2013 8:14:12 PM
 */
public class OnewayEncryptionTest{

	private static final Logger	log	= LoggerFactory.getLogger(OnewayEncryptionTest.class);

	/**
	 * Test method for {@link com.feilong.commons.core.security.OnewayEncryption#encode(com.feilong.commons.core.security.OnewayType, java.lang.String)}.
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

		//log.info(OnewayEncryption.encode(OnewayType.HmacSHA512, "你好"));

	}

	/**
	 * Test method for {@link com.feilong.commons.core.security.OnewayEncryption#encode(com.feilong.commons.core.security.OnewayType, byte[])}.
	 */
	@Test
	public final void testEncodeOnewayTypeByteArray(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.security.OnewayEncryption#encodeFile(com.feilong.commons.core.security.OnewayType, java.lang.String)}.
	 */
	@Test
	public final void testEncodeFile(){
		fail("Not yet implemented"); // TODO
	}
}
