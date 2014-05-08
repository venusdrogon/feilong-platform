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
package com.feilong.netpay.adaptor.sprintasia.klikbca.util;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.netpay.adaptor.sprintasia.klikbca.command.OutputPaymentPGW;
import com.feilong.netpay.adaptor.sprintasia.klikbca.util.KlikBCAUtil;

/**
 * The Class KlikBCAUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 25, 2014 4:43:41 PM
 */
public class KlikBCAUtilTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(KlikBCAUtilTest.class);

	/**
	 * Test method for.
	 * {@link com.feilong.netpay.adaptor.sprintasia.klikbca.util.KlikBCAUtil#getPaymentInquiryXML(java.lang.String, java.lang.String, java.util.List)}
	 * .
	 */
	@Test
	public final void testGetPaymentInquiryXML(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for.
	 * {@link com.feilong.netpay.adaptor.sprintasia.klikbca.util.KlikBCAUtil#getPaymentConfirmationXML(com.feilong.netpay.adaptor.sprintasia.klikbca.command.OutputPaymentPGW)}
	 * .
	 */
	@Test
	public final void testGetPaymentConfirmationXML(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test object.
	 */
	@Test
	public final void testObject(){

		String additionalData = "additionalData";
		String reason = "";
		String transactionDate = DateUtil.date2String(new Date(), DatePattern.ddMMyyyyHHmmss);
		String status = "00";
		String transactionNo = "010000130002";
		String userID = "123456";

		OutputPaymentPGW outputPaymentPGW = new OutputPaymentPGW();
		outputPaymentPGW.setAdditionalData(additionalData);
		outputPaymentPGW.setReason(reason);
		outputPaymentPGW.setStatus(status);
		outputPaymentPGW.setTransactionDate(transactionDate);
		outputPaymentPGW.setTransactionNo(transactionNo);
		outputPaymentPGW.setUserID(userID);

		log.info(KlikBCAUtil.getPaymentConfirmationXML(outputPaymentPGW));

		// <OutputPaymentPGW>
		// <userID>user01</userID>
		// <transactionNo>1234ABC5678EFG4321</transactionNo>
		// <transactionDate>20/10/2010 10:08:09</transactionDate>
		// <status>00</status>
		// <reason>Success</reason>
		// <additionalData> </additionalData>
		// </OutputPaymentPGW>
	}
}
