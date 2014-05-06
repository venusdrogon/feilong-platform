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
package com.feilong.netpay.adaptor.sprintasia.creditcard;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.netpay.adaptor.sprintasia.creditcard.CreditCardQueryResultPaser;

/**
 * The Class CreditCardQueryResultPaserTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2014年5月5日 下午10:04:24
 */
public class CreditCardQueryResultPaserTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(CreditCardQueryResultPaserTest.class);

	/**
	 * Parses the wddx packet.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@Test
	public void parseWddxPacket() throws IOException,IllegalArgumentException,IllegalAccessException{
		String wddxPacketXML = "<wddxPacket version='1.0'><header/><data><struct><var name='TRANSACTIONID'><string>868BBC35-A5D1-FCBF-0453F134C99B5553</string></var><var name='ACQUIRERRESPONSECODE'><string>000</string></var><var name='SCRUBMESSAGE'><string></string></var><var name='AMOUNT'><number>9011999.0</number></var><var name='SERVICEVERSION'><string>2.0</string></var><var name='TRANSACTIONSCRUBCODE'><string></string></var><var name='MERCHANTTRANSACTIONID'><string>010003170001</string></var><var name='CURRENCY'><string>IDR</string></var><var name='TRANSACTIONSTATUS'><string>APPROVED</string></var><var name='SITEID'><string>Blanja2</string></var><var name='TRANSACTIONDATE'><string>2014-04-23 15:19:27</string></var><var name='ACQUIRERCODE'><string>AUTH20140423152019PM</string></var><var name='SCRUBCODE'><string></string></var><var name='TRANSACTIONSCRUBMESSAGE'><string></string></var><var name='ACQUIRERAPPROVALCODE'><string>298883</string></var><var name='TRANSACTIONTYPE'><string>AUTHORIZATION</string></var></struct></data></wddxPacket>";
		CreditCardQueryResultPaser.parseWddxPacket(wddxPacketXML);
	}

}
