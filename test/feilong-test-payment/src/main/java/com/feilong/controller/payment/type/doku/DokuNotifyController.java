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
package com.feilong.controller.payment.type.doku;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.controller.payment.type.BaseNotifyController;
import com.feilong.controller.payment.type.NotifyResultType;
import com.feilong.framework.netpay.payment.PaymentAdaptor;
import com.feilong.servlet.http.RequestUtil;

/**
 * The Class DOKUController.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-18 下午11:32:05
 */
@Controller
public class DokuNotifyController extends BaseNotifyController{

	/** The Constant log. */
	private static final Logger	log				= LoggerFactory.getLogger(DokuNotifyController.class);

	/** <code>{@value}</code>. */
	private static final String	NOTIFY_SUCCESS	= "CONTINUE";

	/** <code>{@value}</code>. */
	private static final String	NOTIFY_FAIL		= "STOP";

	/** doku notify address 地址. */
	@Value("#{p_dokuPayment['ipAddress.notify']}")
	private String				ipAddressNotify;

	/**
	 * No. 13 & No. 14 NOTIFY Process
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws IOException
	 *             //,method = { RequestMethod.POST }
	 */
	@RequestMapping(value = { "/payment/notify/doku" })
	public void DOKUnotify(HttpServletRequest request,HttpServletResponse response) throws IOException{

		String requestAllURL = RequestUtil.getRequestFullURL(request, CharsetType.UTF8);
		if (log.isDebugEnabled()){
			log.info("requestAllURL:{}", requestAllURL);
			log.info("requestStringForLog:{}", RequestUtil.getRequestStringForLog(request));
		}

		String clientIp = RequestUtil.getClientIp(request);
		// 是否是 doku的 ip地址
		boolean isCorrectIpAddress = validateIpAddress(ipAddressNotify, clientIp);

		// ********************① 验证ip地址****************************************************************
		if (!isCorrectIpAddress){
			// But to handle the response to the “hacker”,
			// we suggest you to give them misleading information that would make them believe that what they do was a successful one
			log.error("request ip:{} is not DOKU ip:{},full request url:{}", clientIp, ipAddressNotify, requestAllURL);
			printString(response, NOTIFY_SUCCESS);
			return;
		}

		PaymentAdaptor paymentAdaptor = getDoKuPaymentAdaptor(request);
		NotifyResultType notifyResultType = validateAndUpdateTrade(paymentAdaptor, request);

		String responseString = (notifyResultType == NotifyResultType.Success) ? NOTIFY_SUCCESS : NOTIFY_FAIL;
		printString(response, responseString);
	}
}
