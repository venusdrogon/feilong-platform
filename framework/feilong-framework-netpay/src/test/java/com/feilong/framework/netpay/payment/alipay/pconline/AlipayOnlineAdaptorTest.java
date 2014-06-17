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
package com.feilong.framework.netpay.payment.alipay.pconline;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.feilong.framework.netpay.payment.BasePaymentTest;
import com.feilong.framework.netpay.payment.PaymentAdaptor;

/**
 * The Class AlipayOnlineAdaptorTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 16, 2013 8:27:39 PM
 */
public class AlipayOnlineAdaptorTest extends BasePaymentTest{

	/** The payment adaptor. */
	@Autowired
	@Qualifier("alipayOnlineAdaptor")
	private PaymentAdaptor	paymentAdaptor;

	// @Autowired
	// @Qualifier("paymentAdaptorMap")
	// @Value("#{paymentAdaptorMap}")
	// private Map<String, PaymentAdaptor> paymentAdaptorMap;

	/**
	 * Creates the payment form.
	 * @throws NoSuchFieldException 
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	@Test
	public void createPaymentForm() throws SecurityException, IOException, NoSuchFieldException{
		// Map<String, PaymentAdaptor> paymentAdaptorMap1 = (Map<String, PaymentAdaptor>) applicationContext.getBean("paymentAdaptorMap");
		// PaymentAdaptor paymentAdaptor = paymentAdaptorMap.get("6");

		Map<String, String> specialSignMap = new HashMap<String, String>();
		specialSignMap.put("token", "20130120e28ebb6933ba483fad4bc190d72b8689");

		// 设置未付款交易的超时时间，一旦时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟， h-小时， d-天， 1c-当天（无论交易何时创建，都在 0 点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		// 该功能需要联系技术支持来配置关闭时间。
		specialSignMap.put("it_b_pay", "2m");

		createPaymentForm(paymentAdaptor, specialSignMap);
	}
}
