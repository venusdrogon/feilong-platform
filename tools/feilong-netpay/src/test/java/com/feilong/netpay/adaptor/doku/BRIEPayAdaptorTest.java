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
package com.feilong.netpay.adaptor.doku;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.feilong.commons.core.awt.DesktopUtil;
import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.io.IOWriteUtil;
import com.feilong.commons.core.security.SHA1Util;
import com.feilong.commons.core.util.JsonFormatUtil;
import com.feilong.netpay.adaptor.PaymentAdaptor;
import com.feilong.netpay.command.PaySo;
import com.feilong.netpay.command.PaySoLine;
import com.feilong.netpay.command.PaymentFormEntity;
import com.feilong.tools.velocity.VelocityUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 12, 2014 8:59:42 PM
 */
@ContextConfiguration(locations = { "classpath*:spring/payment/spring-payment.xml" })
public class BRIEPayAdaptorTest extends AbstractJUnit4SpringContextTests{

	private static final Logger	log	= LoggerFactory.getLogger(BRIEPayAdaptorTest.class);

	@Autowired
	@Qualifier("BRIEPayAdaptor")
	private PaymentAdaptor		paymentAdaptor;

	@Test
	public final void testDoGetPaymentFormEntity(){

		String code = DateUtil.date2String(new Date(), DatePattern.timestamp);
		BigDecimal total_fee = new BigDecimal(50000.00f);

		PaySo paySo = new PaySo();
		paySo.setSoCode(code);
		paySo.setTotalActual(total_fee);
		paySo.setMemberEmail("venusdrogon@163.com");
		paySo.setMemberName("jin xin");

		paySo.setTransferFee(new BigDecimal(10000.00f));

		List<PaySoLine> paySoLineList = paySo.getPaySoLineList();

		PaySoLine paySoLine1 = new PaySoLine();
		paySoLine1.setItemName("nike ;s free 5.0");
		paySoLine1.setUnitPrice(new BigDecimal(20000));
		paySoLine1.setQuantity(1);
		paySoLine1.setSubTotalPrice(new BigDecimal(20000));
		paySoLineList.add(paySoLine1);

		PaySoLine paySoLine2 = new PaySoLine();
		paySoLine2.setItemName("nike free 4.0");
		paySoLine2.setUnitPrice(new BigDecimal(15000));
		paySoLine2.setQuantity(2);
		paySoLine2.setSubTotalPrice(new BigDecimal(30000));
		paySoLineList.add(paySoLine2);

		// ******************************************************************
		String return_url = "/patment1url";
		String notify_url = "/patment2url";
		PaymentFormEntity paymentFormEntity = paymentAdaptor.doBeginPayment(paySo, return_url, notify_url, null);

		log.info(JsonFormatUtil.format(paymentFormEntity));
		System.out.println(paymentFormEntity.getFullEncodedUrl());
		System.out.println();
		System.out.println();
		System.out.println();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paymentFormEntity", paymentFormEntity);

		String html = VelocityUtil.parseTemplateWithClasspathResourceLoader("test.vm", map);

		log.info(html);

		String filePath = "F:/1.html";
		IOWriteUtil.write(filePath, html);

		DesktopUtil.browse(filePath);
	}

	@Test
	public final void testDoGetPaymentFormEntity1(){
		Map<String, Object> map = new HashMap<String, Object>();

		PaymentFormEntity paymentFormEntity = new PaymentFormEntity();

		String method = "post";
		String action = "http://webtest.feilong.com/feilong-web-test/DOKUnotify";

		paymentFormEntity.setAction(action);
		paymentFormEntity.setMethod(method);

		String AMOUNT = "50000.00";
		String TRANSIDMERCHANT = DateUtil.date2String(new Date(), DatePattern.timestamp);

		Map<String, String> hiddenParamMap = new HashMap<String, String>();
		// N 12.2 Total amount. Eg: 10000.00

		hiddenParamMap.put("AMOUNT", AMOUNT);

		// AN ...14 Transaction ID from Merchant

		hiddenParamMap.put("TRANSIDMERCHANT", TRANSIDMERCHANT);

		// AN ...200 Hashed key combination encryption (use SHA1 method).
		// The hashed key generated from com-bining these parameters value in this order:AMOUNT+MALLID+<shared key>+TRANSIDMERCHANT+
		// RESULTMSG+VERIFYSTATUS
		hiddenParamMap.put("WORDS", getWORDS(TRANSIDMERCHANT, AMOUNT));

		// A 1 P: Notify Payment V: Notify Reversal
		hiddenParamMap.put("STATUSTYPE", "V");

		// N 4 0000: Success, others Failed
		hiddenParamMap.put("RESPONSECODE", "0000");

		// AN ...20 Transaction number from bank
		hiddenParamMap.put("APPROVALCODE", "123456688455");

		// A ...20 SUCCESS / FAILED
		hiddenParamMap.put("RESULTMSG", "SUCCESS");

		// N 2 See payment channel code list
		hiddenParamMap.put("PAYMENTCHANNEL", "05");

		// N …8 Virtual Account identifier for VA transaction
		hiddenParamMap.put("PAYMENTCODE", "12345678");

		// AN ...48
		hiddenParamMap.put("SESSIONID", TRANSIDMERCHANT);

		// AN …100 Bank Issuer
		hiddenParamMap.put("BANK", "china bank");

		// ANS 16 Masked card number
		hiddenParamMap.put("MCN", "15254555554");

		// N 14 YYYYMMDDHHMMSS
		hiddenParamMap.put("PAYMENTDATETIME", TRANSIDMERCHANT);

		// N 30 Generated by Fraud Screening (RequestID)
		hiddenParamMap.put("VERIFYID", "55458");

		// N …3 0 - 100
		hiddenParamMap.put("VERIFYSCORE", "455121215");

		// A …10 APPROVE / REJECT / REVIEW / HIGHRISK / NA
		hiddenParamMap.put("VERIFYSTATUS", "APPROVE");

		// *******************************************************************************
		paymentFormEntity.setHiddenParamMap(hiddenParamMap);
		map.put("paymentFormEntity", paymentFormEntity);

		String html = VelocityUtil.parseTemplateWithClasspathResourceLoader("test.vm", map);

		log.info(html);

		String filePath = "F:/2.html";
		IOWriteUtil.write(filePath, html);

		DesktopUtil.browse(filePath);
	}

	private String getWORDS(String TRANSIDMERCHANT,String AMOUNT){

		String MALLID = "416";
		String Shared_key = "Pqq0Bk7Ce26D";

		String WORDS = AMOUNT + MALLID + Shared_key + TRANSIDMERCHANT;

		// Andi 拿出的 DOKU 邮件里面的script 是 "ISO-8859-1"
		WORDS = SHA1Util.encode(WORDS, CharsetType.ISO_8859_1);
		return WORDS;
	}
}
