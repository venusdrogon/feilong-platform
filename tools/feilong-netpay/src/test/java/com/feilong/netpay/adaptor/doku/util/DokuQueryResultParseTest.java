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
package com.feilong.netpay.adaptor.doku.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.netpay.adaptor.doku.command.DokuQueryResult;
import com.feilong.netpay.parse.CustomCommand;
import com.feilong.netpay.parse.CommandXmlParse;
import com.feilong.tools.json.JsonUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.6 2014年5月8日 上午10:11:19
 * @since 1.0.6
 */
public class DokuQueryResultParseTest{

	private static final Logger	log	= LoggerFactory.getLogger(DokuQueryResultParseTest.class);

	/**
	 * Test method for {@link com.feilong.netpay.adaptor.doku.util.DokuQueryResultParse#parseXML(java.lang.String)}.
	 */
	@Test
	public final void testParseXML(){
		String xml = "<?xml version=\"1.0\"?><PAYMENT_STATUS><AMOUNT>7790000.00</AMOUNT><TRANSIDMERCHANT>010003660001</TRANSIDMERCHANT><WORDS>e9e6ed65c872f1646644001f1b67fc8bc5de8df6</WORDS><RESPONSECODE>0000</RESPONSECODE><APPROVALCODE>RB1234567890</APPROVALCODE><RESULTMSG>SUCCESS</RESULTMSG><PAYMENTCHANNEL>06</PAYMENTCHANNEL><PAYMENTCODE></PAYMENTCODE><SESSIONID>20140508105926</SESSIONID><BANK>BRI</BANK><MCN></MCN><PAYMENTDATETIME>20140508095526</PAYMENTDATETIME><VERIFYID></VERIFYID><VERIFYSCORE>-1</VERIFYSCORE><VERIFYSTATUS>NA</VERIFYSTATUS></PAYMENT_STATUS>";

		CommandXmlParse<DokuQueryResult> queryResultXmlParse = new DokuQueryResultParse();
		CustomCommand customCommand = queryResultXmlParse.parseXML(xml);
	}
}
