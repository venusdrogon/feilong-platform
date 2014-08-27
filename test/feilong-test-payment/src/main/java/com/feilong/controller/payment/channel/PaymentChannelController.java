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
package com.feilong.controller.payment.channel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.NumberUtil;
import com.feilong.controller.payment.type.bca.klikpay.KlikpayCallbackController;
import com.feilong.framework.netpay.payment.PaymentAdaptor;
import com.feilong.framework.netpay.payment.PaymentAdaptorFactory;
import com.feilong.framework.netpay.payment.command.PayRequest;
import com.feilong.framework.netpay.payment.command.PaySoLine;
import com.feilong.framework.netpay.payment.command.PaymentFormEntity;
import com.feilong.spring.util.UriTemplateUtil;

/**
 * 支付通道.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 16, 2014 7:16:42 PM
 */
@Controller
public class PaymentChannelController{

	/** The Constant log. */
	private static final Logger		log	= LoggerFactory.getLogger(PaymentChannelController.class);

	/** The payment adaptor manager. */
	@Autowired
	private PaymentAdaptorFactory	paymentAdaptorFactory;

	/** The klik pay callback domain. */
	@Value("#{p_bcaklikPayPayment['callbackDomain']}")
	private String					klikPayCallbackDomain;

	/**
	 * 去支付通道<br>
	 * 此处只是模拟,.
	 * 
	 * @param model
	 *            the model
	 * @param request
	 *            the request
	 * @param paymentType
	 *            the payment type
	 * @return the string
	 */
	@RequestMapping("/payment/paymentChannel")
	public String gotoPaymentChannel(Model model,HttpServletRequest request,@RequestParam("paymentType") String paymentType){
		PaymentFormEntity paymentFormEntity = getPaymentFormEntity(paymentType);
		request.setAttribute("paymentFormEntity", paymentFormEntity);
		return "feilong.module.payment.paymentChannel";
	}

	/**
	 * Gets the payment form entity.
	 * 
	 * @param paymentType
	 *            the payment type
	 * @return the payment form entity
	 */
	public PaymentFormEntity getPaymentFormEntity(String paymentType){
		log.info("paymentType:{}", paymentType);

		String tradeNo = DateUtil.date2String(new Date(), DatePattern.timestamp);
		BigDecimal total_fee = new BigDecimal(60000.00f);

		PayRequest payRequest = new PayRequest();
		payRequest.setTradeNo(tradeNo);
		payRequest.setTotalFee(total_fee);
		payRequest.setBuyerEmail("venusdrogon@163.com");

		// AN …50 Travel Arranger Name / Buyer name
		payRequest.setBuyerName("jinxin");

		payRequest.setTransferFee(new BigDecimal(10000.00f));

		List<PaySoLine> paySoLineList = payRequest.getPaySoLineList();

		PaySoLine paySoLine1 = new PaySoLine();
		paySoLine1.setItemName("nike ;s free 5.0");
		paySoLine1.setUnitPrice(new BigDecimal(20000));
		paySoLine1.setQuantity(1);
		paySoLine1.setSubTotalPrice(NumberUtil.getMultiplyValue(paySoLine1.getUnitPrice(), paySoLine1.getQuantity(), 2));
		paySoLineList.add(paySoLine1);

		PaySoLine paySoLine2 = new PaySoLine();
		paySoLine2.setItemName("nike free 4.0");
		paySoLine2.setUnitPrice(new BigDecimal(15000));
		paySoLine2.setQuantity(2);
		paySoLine2.setSubTotalPrice(NumberUtil.getMultiplyValue(paySoLine2.getUnitPrice(), paySoLine2.getQuantity(), 2));
		paySoLineList.add(paySoLine2);

		payRequest.setCreateDate(new Date());

		// ******************************************************************
		String return_url = "/patment1url";

		if (paymentType.equals("155")){
			return_url = klikPayCallbackDomain
					+ UriTemplateUtil.expandWithVariable(KlikpayCallbackController.callbackURL, "tradeNo", tradeNo)
					+ "?id=222222&no=asasdasdasds";
		}
		String notify_url = "/patment2url";
		Map<String, String> specialSignMap = null;

		payRequest.setNotifyUrl(notify_url);
		payRequest.setReturnUrl(return_url);

		PaymentAdaptor paymentAdaptor = paymentAdaptorFactory.getPaymentAdaptor(paymentType);
		PaymentFormEntity paymentFormEntity = paymentAdaptor.getPaymentFormEntity(payRequest, specialSignMap);

		log.info("paymentFormEntity:\n{}", JsonUtil.format(paymentFormEntity));
		return paymentFormEntity;
	}
}
