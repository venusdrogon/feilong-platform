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
package com.feilong.controller.payment.type.doku;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.controller.payment.BasePaymentController;
import com.feilong.framework.netpay.command.PaymentResult;
import com.feilong.framework.netpay.payment.PaymentAdaptor;
import com.feilong.servlet.http.RequestUtil;

/**
 * The Class DOKUController.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-18 下午11:32:05
 */
@Controller
public class DokuRedirectController extends BasePaymentController{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(DokuRedirectController.class);

	/** doku redirect address 地址. */
	@Value("#{p_dokuPayment['ipAddress.redirect']}")
	private String				ipAddressRedirect;

	/**
	 * No. 16 REDIRECT Process<br>
	 * 出于更安全考虑, 你不应该拿redirect参数作为交易的状态<br>
	 * Redirect process只是交易或者session的标识,不是告诉你交易状态<br>
	 * 因为交易状态在之前的NOTIFY 流程已经给你了<br>
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 * @throws IOException
	 *             //,method = { RequestMethod.GET }
	 */
	@RequestMapping(value = { "/payment/redirect/doku" })
	public String DOKUREDIRECT(HttpServletRequest request) throws IOException{

		String requestAllURL = RequestUtil.getRequestFullURL(request, CharsetType.UTF8);
		if (log.isDebugEnabled()){
			log.info("requestAllURL:{}", requestAllURL);
			log.info("requestStringForLog:{}", RequestUtil.getRequestStringForLog(request));
		}

		String clientIp = RequestUtil.getClientIp(request);

		Object description = "payment fail";
		boolean isSuccess = false;

		// **************************①是否是 doku的 ip地址***********************
		boolean isCorrectIpAddress = validateIpAddress(ipAddressRedirect, clientIp);

		if (!isCorrectIpAddress){
			log.error("request ip:{} is not DOKU ip:{},full request url:{}", clientIp, ipAddressRedirect, requestAllURL);
			return redirect(request, "request ip:" + clientIp + " is not DOKU ip:" + ipAddressRedirect + "", false);
		}

		// **************************②验证参数***********************
		PaymentAdaptor paymentAdaptor = getDoKuPaymentAdaptor(request);
		PaymentResult passRedirectVerify = paymentAdaptor.verifyRedirect(request);
		if (PaymentResult.PAID != passRedirectVerify){
			log.error("not passRedirectVerify,full request url:{}", requestAllURL);
			return redirect(request, "doRedirectVerify false!", false);
		}

		// TODO 判断交易是否存在

		// TODO 判断 结果

		Map<String, String> object = request.getParameterMap();

		// N 12.2 Total amount. Eg: 10000.00
		String AMOUNT = request.getParameter("AMOUNT");

		// AN ...14 Transaction ID from Merchant
		String TRANSIDMERCHANT = request.getParameter("TRANSIDMERCHANT");

		// N 2 See payment channel code list
		String PAYMENTCHANNEL = request.getParameter("PAYMENTCHANNEL");

		// AN ...48
		String SESSIONID = request.getParameter("SESSIONID");

		// *****************************************************************************
		// 是否支付成功
		boolean isPaymentStatusSuccess = true;

		// 是正确的 且是支付成功的 redirect
		if (isPaymentStatusSuccess){

			// ATM支付的 流程和 其他支付方式不一样
			boolean isATMPayment = isATMPayment(request);
			// TODO ATM
			if (isATMPayment){

				// N …8 Virtual Account identifier for VA transaction
				String PAYMENTCODE = request.getParameter("PAYMENTCODE");
				description = "success,PAYMENTCODE:" + PAYMENTCODE;
			}else{
				description = "success";
			}
			return redirect(request, description, true);
		}
		return redirect(request, description, isSuccess);
	}
}
