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
package com.feilong.netpay.adaptor.bca.keygenerator;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 24, 2014 10:11:59 AM
 */
public class BCAKeyGeneratorTest{

	private static final Logger	log	= LoggerFactory.getLogger(BCAKeyGeneratorTest.class);

	/**
	 * Test method for {@link com.feilong.netpay.adaptor.bca.keygenerator.AuthKey#doAuthKey(java.lang.String, java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testDoAuthKey() throws Exception{
		String input = "F7468B69D12BB6CE76D6206419A6AC28";
		String encryptByKeyId = "12345678901234561234567890123456";
		// authKey will become  BF81501C562D6FEA2FCB905D392D5851
		// bf81501c562d6fea2fcb905d392d58513a661ceaa9b844d7
		// bf81501c562d6fea2fcb905d392d5851
		log.info(BCAKeyGenerator.doAuthKey(input, encryptByKeyId));
	}
}
