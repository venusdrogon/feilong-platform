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
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
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
public class AlipayPayAdaptorTest extends AbstractJUnit4SpringContextTests{

	private static final Logger			log	= LoggerFactory.getLogger(AlipayPayAdaptorTest.class);

	// @Autowired
	// @Qualifier("alipayPayAdaptor")
	// private PaymentAdaptor paymentAdaptor;

	// @Autowired
	// @Qualifier("paymentAdaptorMap")
	@Value("#{paymentAdaptorMap}")
	private Map<String, PaymentAdaptor>	paymentAdaptorMap;

	@Autowired
	private ApplicationContext			applicationContext;

	@Test
	public void testDoBeginPayment(){
		// Map<String, PaymentAdaptor> paymentAdaptorMap1 = (Map<String, PaymentAdaptor>) applicationContext.getBean("paymentAdaptorMap");
		PaymentAdaptor paymentAdaptor = paymentAdaptorMap.get("6");
		String code = DateUtil.date2String(new Date(), DatePattern.timestamp);
		BigDecimal total_fee = new BigDecimal(0.01f);

		PaySo paySo = new PaySo();
		paySo.setSoCode(code);
		paySo.setTotalActual(total_fee);
		String return_url = "/patment1url";
		String notify_url = "/patment2url";

		Map<String, String> specialSignMap = new HashMap<String, String>();
		specialSignMap.put("token", "20130120e28ebb6933ba483fad4bc190d72b8689");

		// 设置未付款交易的超时时间，一旦时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟， h-小时， d-天， 1c-当天（无论交易何时创建，都在 0 点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		// 该功能需要联系技术支持来配置关闭时间。
		specialSignMap.put("it_b_pay", "1m");

		PaymentFormEntity paymentFormEntity = paymentAdaptor.doBeginPayment(paySo, return_url, notify_url, specialSignMap);

		log.info(JsonFormatUtil.format(paymentFormEntity));
		System.out.println(paymentFormEntity.getFullEncodedUrl());
		System.out.println();
		System.out.println();
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
