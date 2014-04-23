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
package com.feilong.netpay.adaptor.bca.klikpay;

import java.util.Date;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.netpay.adaptor.BasePaymentTest;
import com.feilong.netpay.adaptor.PaymentAdaptor;
import com.feilong.netpay.adaptor.bca.klikpay.command.ApprovalCode;
import com.feilong.netpay.adaptor.bca.klikpay.command.OutputPaymentIPAY;
import com.feilong.netpay.adaptor.bca.klikpay.command.Reason;
import com.feilong.netpay.adaptor.bca.klikpay.command.ReasonEnum;
import com.feilong.netpay.adaptor.bca.klikpay.util.BCAKeyGenerator;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 22, 2014 4:15:13 PM
 */
public class KlikPayAdaptorTest extends BasePaymentTest{

	@Autowired
	@Qualifier("klikPayAdaptor")
	private PaymentAdaptor		paymentAdaptor;

	private static final Logger	log	= LoggerFactory.getLogger(KlikPayAdaptorTest.class);

	@Test
	public final void createPaymentForm(){
		Map<String, String> specialSignMap = null;
		createPaymentForm(paymentAdaptor, specialSignMap);
	}

	@Test
	public final void testDoAuthKey() throws Exception{
		// Input  F7468B69D12BB6CE76D6206419A6AC28
		// Encrypt by KeyId  12345678901234561234567890123456
		// authKey will become  BF81501C562D6FEA2FCB905D392D5851

		String input = "F7468B69D12BB6CE76D6206419A6AC28";
		String encryptByKeyId = "12345678901234561234567890123456";

		Assert.assertEquals("BF81501C562D6FEA2FCB905D392D5851", BCAKeyGenerator.doAuthKey(input, encryptByKeyId));
	}

	/**
	 * Test method for
	 * {@link com.feilong.netpay.adaptor.bca.klikpay.KlikPayAdaptor#getSign(java.lang.String, java.util.Date, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetSign(){
		// "miscFee": "0.00",
		// "descp": "",
		// "payType": "01",
		// "signature": "1995880609",

		KlikPayAdaptor bcaAdaptor = new KlikPayAdaptor();
		String klikPayCode = "03BELAV220";

		Date transactionDate = DateUtil.string2Date("23/04/2014 18:20:20", DatePattern.ddMMyyyyHHmmss);
		String transactionNo = "010003240001";
		String totalAmount = "9000009.00";
		String currency = "IDR";
		String keyId = bcaAdaptor.getKeyId("ClearKeyDev2Blj2");
		String sign = bcaAdaptor.getSignature(klikPayCode, transactionDate, transactionNo, totalAmount, currency, keyId);
		log.info(sign);
	}

	@Test
	public final void testGetAuthKey(){
		KlikPayAdaptor bcaAdaptor = new KlikPayAdaptor();
		String klikPayCode = "123";
		String transactionNo = "456";

		Date transactionDate = DateUtil.string2Date("20/01/2010 01:01:01", DatePattern.ddMMyyyyHHmmss);
		String currency = "IDR";
		String keyId = "12345678901234561234567890123456";
		String sign = bcaAdaptor.getAuthKey(klikPayCode, transactionDate, transactionNo, currency, keyId);
		log.info(sign);

		Assert.assertEquals("BF81501C562D6FEA2FCB905D392D5851", sign);
	}

	@Test
	public final void testObject(){

		OutputPaymentIPAY outputPaymentIPAY = getOutputPaymentIPAY();

		KlikPayAdaptor klikPayAdaptor = (KlikPayAdaptor) paymentAdaptor;
		log.info("\n" + klikPayAdaptor.getPaymentFlagInvocationOutputXML(outputPaymentIPAY));

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
