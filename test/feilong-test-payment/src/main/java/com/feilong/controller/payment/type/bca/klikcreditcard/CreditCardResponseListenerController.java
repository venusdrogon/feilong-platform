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
import com.feilong.controller.payment.type.BaseNotifyController;
import com.feilong.controller.payment.type.NotifyResultType;
import com.feilong.framework.netpay.payment.PaymentAdaptor;
import com.feilong.servlet.http.RequestUtil;

/**
 * The Class CreditCardResponseListenerController.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 21, 2014 1:32:05 PM
 */
@Controller
public class CreditCardResponseListenerController extends BaseNotifyController{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(CreditCardResponseListenerController.class);

	/** The payment adaptor. */
	@Autowired
	@Qualifier("bcaCreditCardPayAdaptor")
	private PaymentAdaptor		paymentAdaptor;

	/**
	 * doResponse.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@RequestMapping(value = { "/payment/bca/creditcard/responselistener" },method = { RequestMethod.POST })
	public void doResponseListener(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String requestAllURL = RequestUtil.getRequestFullURL(request, CharsetType.UTF8);
		if (log.isDebugEnabled()){
			log.info("requestAllURL:{}", requestAllURL);
			log.info("requestStringForLog:{}", RequestUtil.getRequestStringForLog(request));
		}

		// TODO 验证来源
		NotifyResultType notifyResultType = validateAndUpdateTrade(paymentAdaptor, request);
		printString(response, notifyResultType.toString());
	}

}
