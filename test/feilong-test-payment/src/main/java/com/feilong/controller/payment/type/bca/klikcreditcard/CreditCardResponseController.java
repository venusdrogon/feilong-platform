package com.feilong.controller.payment.type.bca.klikcreditcard;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.controller.payment.BasePaymentController;
import com.feilong.framework.netpay.payment.PaymentAdaptor;
import com.feilong.servlet.http.RequestUtil;

/**
 * Creditcard Response
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 18, 2014 9:41:38 PM
 */
@Controller
public class CreditCardResponseController extends BasePaymentController{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(CreditCardResponseController.class);

	/** The payment adaptor. */
	@Autowired
	@Qualifier("bcaCreditCardPayAdaptor")
	private PaymentAdaptor		paymentAdaptor;

	/**
	 * doResponse
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/payment/bca/creditcard/response" },method = { RequestMethod.POST })
	public void doResponse(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String requestAllURL = RequestUtil.getRequestFullURL(request, CharsetType.UTF8);
		if (log.isDebugEnabled()){
			log.info("requestAllURL:{}", requestAllURL);
			log.info("requestStringForLog:{}", RequestUtil.getRequestStringForLog(request));
		}

		printString(response, "hehe");
	}

}
