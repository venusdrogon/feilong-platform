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
package com.feilong.controller.payment.type.bca.klikpay;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feilong.controller.payment.BasePaymentController;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.klikpay.SprintAsiaKlikPayAdaptor;

/**
 * KlikpayCallbackController.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-18 下午11:32:05
 */
@Controller
public class KlikpayCallbackController extends BasePaymentController{

	/** The Constant log. */
	@SuppressWarnings("unused")private static final Logger	log							= LoggerFactory.getLogger(KlikpayCallbackController.class);

	/** <code>{@value}</code>. */
	public static final String	callbackURL					= "/payment/callback/bca/klikpay/{tradeNo}";

	/** 支付失败的地址. */
	private static String		paymentFailRedirectPage		= "/payment/paymentFail";

	/** 支付成功的地址. */
	private static String		paymentSuccessRedirectPage	= "/payment/successPayment";

	/** The payment adaptor. */
	@Autowired
	@Qualifier("klikPayAdaptor")
	private SprintAsiaKlikPayAdaptor		sprintAsiaKlikPayAdaptor;

	/**
	 * Callback.
	 * 
	 * @param tradeNo
	 *            the trade no
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@RequestMapping(value = { callbackURL })
	public String callback(@PathVariable("tradeNo") String tradeNo,HttpServletRequest request,HttpServletResponse response)
			throws IOException{
		// request.setAttribute("total", AMOUNT);
		// return paymentSuccessRedirectPage;

		Object description = "payment fail";
		boolean isSuccess = true;

		return redirect(request, description, isSuccess);
	}
}
