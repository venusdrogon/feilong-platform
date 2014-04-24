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
package com.feilong.netpay.adaptor.bca.klikpay.util;

import static org.junit.Assert.*;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.netpay.adaptor.bca.klikpay.KlikPayAdaptor;
import com.feilong.netpay.adaptor.bca.klikpay.command.ApprovalCode;
import com.feilong.netpay.adaptor.bca.klikpay.command.OutputPaymentIPAY;
import com.feilong.netpay.adaptor.bca.klikpay.command.Reason;
import com.feilong.netpay.adaptor.bca.klikpay.command.ReasonEnum;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 24, 2014 4:02:04 PM
 */
public class KlikPayUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(KlikPayUtilTest.class);

	/**
	 * Test method for {@link com.feilong.netpay.adaptor.bca.klikpay.util.KlikPayUtil#getKeyId(java.lang.String)}.
	 */
	@Test
	public final void testGetKeyId(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.feilong.netpay.adaptor.bca.klikpay.util.KlikPayUtil#getSignature(java.lang.String, java.util.Date, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetSignature(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.feilong.netpay.adaptor.bca.klikpay.util.KlikPayUtil#getAuthKey(java.lang.String, java.util.Date, java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetAuthKey(){
		String klikPayCode = "123";
		String transactionNo = "456";

		Date transactionDate = DateUtil.string2Date("20/01/2010 01:01:01", DatePattern.ddMMyyyyHHmmss);
		String currency = "IDR";
		String keyId = "12345678901234561234567890123456";
		String sign = KlikPayUtil.getAuthKey(klikPayCode, transactionDate, transactionNo, currency, keyId);
		log.info(sign);

		Assert.assertEquals("BF81501C562D6FEA2FCB905D392D5851", sign);
	}

	@Test
	public final void testObject(){

		OutputPaymentIPAY outputPaymentIPAY = getOutputPaymentIPAY();

		log.info("\n" + KlikPayUtil.getPaymentFlagInvocationOutputXML(outputPaymentIPAY));

		// <OutputPaymentIPAY>
		// <klikPayCode>0001</klikPayCode>
		// <transactionNo>317058F4D44B71252F</transactionNo>
		// <transactionDate>15/12/2008 09:07:05</transactionDate>
		// <currency>IDR</currency>
		// <totalAmount>1000.00</totalAmount>
		// <payType>02</payType>
		// <approvalCode>
		// <fullTransaction>00002</fullTransaction>
		// <installmentTransaction>00003</installmentTransaction>
		// </approvalCode>
		// <status>00</status>
		// <reason>
		// <indonesian>Sukses</indonesian>
		// <english>Success</english>
		// </reason>
		// <additionalData></additionalData>
		// </OutputPaymentIPAY>
	}

	/**
	 * @return
	 */
	protected OutputPaymentIPAY getOutputPaymentIPAY(){
		String additionalData = "";
		String transactionDate = DateUtil.date2String(new Date(), DatePattern.ddMMyyyyHHmmss);
		String transactionNo = "010000130002";
		String currency = "IDR";
		String klikPayCode = "0001";
		String payType = "02";
		String status = "00";// 00 TRX_SUCCESS(*) ;01 TRX_REJECTED(*)
		String totalAmount = "1000.00";

		ReasonEnum reasonEnum = ReasonEnum.SUCCESS;

		OutputPaymentIPAY outputPaymentIPAY = new OutputPaymentIPAY();

		outputPaymentIPAY.setAdditionalData(additionalData);

		ApprovalCode approvalCode = new ApprovalCode();
		approvalCode.setFullTransaction("00002");
		approvalCode.setInstallmentTransaction("00003");
		outputPaymentIPAY.setApprovalCode(approvalCode);

		outputPaymentIPAY.setCurrency(currency);
		outputPaymentIPAY.setKlikPayCode(klikPayCode);

		outputPaymentIPAY.setPayType(payType);

		Reason reason = new Reason();
		reason.setEnglish(reasonEnum.getEnglish());
		reason.setIndonesian(reasonEnum.getIndonesian());
		outputPaymentIPAY.setReason(reason);

		outputPaymentIPAY.setStatus(status);

		outputPaymentIPAY.setTotalAmount(totalAmount);

		outputPaymentIPAY.setTransactionDate(transactionDate);
		outputPaymentIPAY.setTransactionNo(transactionNo);
		return outputPaymentIPAY;
	}
}
