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
package com.feilong.netpay.adaptor.bca;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.feilong.commons.core.date.DateUtil;
import com.feilong.netpay.adaptor.bca.BCAPayAdaptor;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 6, 2014 3:00:33 PM
 */
public class BCAAdaptorTest extends AbstractJUnit4SpringContextTests{

	private static final Logger	log	= LoggerFactory.getLogger(BCAAdaptorTest.class);

	/**
	 * Test method for
	 * {@link com.feilong.netpay.adaptor.bca.BCAPayAdaptor#getSign(java.lang.String, java.util.Date, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetSign(){
		BCAPayAdaptor bcaAdaptor = new BCAPayAdaptor();
		String klikPayCode = "123";

		Date transactionDate = DateUtil.string2Date("20/01/2010", "dd/MM/yyyy");
		String transactionNo = "456";
		String totalAmount = "1500500.00";
		String currency = "IDR";
		String keyId = "12345678901234561234567890123456";
		String sign = bcaAdaptor.getSign(klikPayCode, transactionDate, transactionNo, totalAmount, currency, keyId);
		log.info(sign);
	}

	@Test
	public final void testGetAuthKey(){
		BCAPayAdaptor bcaAdaptor = new BCAPayAdaptor();
		String klikPayCode = "123";

		Date transactionDate = DateUtil.string2Date("20/01/2010", "dd/MM/yyyy");
		String transactionNo = "456";
		String currency = "IDR";
		String keyId = "12345678901234561234567890123456";
		String sign = bcaAdaptor.getAuthKey(klikPayCode, transactionDate, transactionNo, currency, keyId);
		log.info(sign);
	}
}
