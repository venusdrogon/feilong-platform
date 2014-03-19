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
package com.feilong.netpay.adaptor.alipay;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.util.JsonFormatUtil;
import com.feilong.netpay.adaptor.PaymentAdaptor;
import com.feilong.netpay.command.PaySo;
import com.feilong.netpay.command.PaymentFormEntity;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 16, 2013 8:27:39 PM
 */
@ContextConfiguration(locations = { "classpath*:spring/payment/spring-payment.xml" })
public class AlipayCreditCardPayAdaptorTest extends AbstractJUnit4SpringContextTests{

	private static final Logger	log	= LoggerFactory.getLogger(AlipayCreditCardPayAdaptorTest.class);

	@Autowired
	@Qualifier("alipayCreditCardPayAdaptor")
	private PaymentAdaptor		paymentAdaptor;

	@Test
	public void testDoBeginPayment(){

		String code = DateUtil.date2String(new Date(), DatePattern.timestamp);
		BigDecimal total_fee = new BigDecimal(0.01f);

		PaySo paySo = new PaySo();
		paySo.setSoCode(code);
		paySo.setTotalActual(total_fee);

		String return_url = "/patment1url";
		String notify_url = "/patment2url";
		PaymentFormEntity paymentFormEntity = paymentAdaptor.doBeginPayment(paySo, return_url, notify_url, null);

		log.info(JsonFormatUtil.format(paymentFormEntity));
		System.out.println(paymentFormEntity.getFullEncodedUrl());
		System.out.println();
		System.out.println();
		System.out.println();
	}

	/**
	 * Test method for
	 * {@link com.feilong.netpay.adaptor.alipay.jumbo.brandstore.payment.adaptor.AlipayPayAdaptor#doNotifyVerify(javax.servlet.http.HttpServletRequest)}
	 * .
	 */
	@Test
	public void testDoNotifyVerify(){
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.feilong.netpay.adaptor.alipay.jumbo.brandstore.payment.adaptor.AlipayPayAdaptor#doGetFeedbackSoCode(javax.servlet.http.HttpServletRequest)}
	 * .
	 */
	@Test
	public void testDoGetFeedbackSoCode(){
		fail("Not yet implemented");
	}
}
