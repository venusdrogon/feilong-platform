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
package com.feilong.netpay.adaptor.bca.klikBCA;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.netpay.adaptor.BasePaymentTest;
import com.feilong.netpay.adaptor.bca.klikbca.KlikBCAAdaptor;
import com.feilong.netpay.adaptor.bca.klikbca.OutputPaymentPGW;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 1, 2014 9:40:39 PM
 */
public class KlikBCAAdaptorTest extends BasePaymentTest{

	private static final Logger	log	= LoggerFactory.getLogger(KlikBCAAdaptorTest.class);

	@Autowired
	@Qualifier("klikBCAAdaptor")
	private KlikBCAAdaptor		klikBCAAdaptor;

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

		log.info(klikBCAAdaptor.getPaymentConfirmationXML(outputPaymentPGW));

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
