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
package com.feilong.controller.payment.type.bca.klikBCA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.controller.payment.type.BaseNotifyController;
import com.feilong.framework.netpay.payment.PaymentAdaptor;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.klikbca.command.OutputDetailPayment;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.klikbca.util.KlikBCAUtil;
import com.feilong.servlet.http.RequestUtil;

/**
 * The Class DOKUController.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-18 下午11:32:05
 */
@Controller
public class KlikBCAInquiryController extends BaseNotifyController{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(KlikBCAInquiryController.class);

	/** The payment adaptor. */
	@Autowired
	@Qualifier("klikBCAAdaptor")
	private PaymentAdaptor		paymentAdaptor;

	/**
	 * Do inquiry.
	 * 
	 * @param userID
	 *            the user id
	 * @param additionalData
	 *            the additional data
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@RequestMapping(value = { "/payment/inquiry/klikbca" },method = { RequestMethod.POST })
	public void doInquiry(
			@RequestParam(value = "userid",required = true) String userID,
			@RequestParam(value = "adddata",required = false) String additionalData,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException{

		String queryStringLog = RequestUtil.getQueryStringLog(request);
		log.info("queryStringLog:{}", queryStringLog);

		String transactionDate = DateUtil.date2String(new Date(), DatePattern.ddMMyyyyHHmmss);
		String transactionNo = "010000130002";
		List<OutputDetailPayment> outputDetailPayments = new ArrayList<OutputDetailPayment>();
		OutputDetailPayment outputDetailPayment1 = new OutputDetailPayment();
		outputDetailPayment1.setAmount("IDR3500000.00");
		outputDetailPayment1.setDescription("hahaha");
		outputDetailPayment1.setTransactionDate(transactionDate);
		outputDetailPayment1.setTransactionNo(transactionNo);
		outputDetailPayments.add(outputDetailPayment1);

		OutputDetailPayment outputDetailPayment2 = new OutputDetailPayment();
		outputDetailPayment2.setAmount("IDR3500000.00");
		outputDetailPayment2.setDescription("hahaha2");
		outputDetailPayment2.setTransactionDate(transactionDate);
		outputDetailPayment2.setTransactionNo(transactionNo);
		outputDetailPayments.add(outputDetailPayment2);

		String responseString = KlikBCAUtil.getPaymentInquiryXML(userID, additionalData, outputDetailPayments);

		printString(response, responseString);
	}
}
