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
package com.feilong.controller.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 支付集成.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 16, 2014 7:16:42 PM
 */
@Controller
public class PaymentIntegrationController{

	/** The Constant log. */
	@SuppressWarnings("unused")private static final Logger	log	= LoggerFactory.getLogger(PaymentIntegrationController.class);

	/**
	 * Goto payment integration.
	 * 
	 * @return the string
	 */
	@RequestMapping(value = { "/payment/paymentIntegration", "/", "" })
	public String gotoPaymentIntegration(){
		return "feilong.module.payment.paymentIntegration";
	}

}
