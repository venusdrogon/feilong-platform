package com.feilong.controller.payment.type.bca.klikBCA;

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
import com.feilong.framework.netpay.payment.adaptor.sprintasia.klikbca.command.OutputPaymentPGW;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.klikbca.util.KlikBCAUtil;
import com.feilong.servlet.http.RequestUtil;

/**
 * DOKU的支付网关.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-18 下午11:32:05
 */
@Controller
public class KlikBCAPaymentFlagInvocationsController extends BaseNotifyController{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(KlikBCAPaymentFlagInvocationsController.class);

	/** The payment adaptor. */
	@Autowired
	@Qualifier("klikBCAAdaptor")
	private PaymentAdaptor		paymentAdaptor;

	/**
	 * Do payment confirmation.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/payment/notify/bca/klikbca" },method = { RequestMethod.POST })
	public void doPaymentConfirmation(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String requestAllURL = RequestUtil.getRequestFullURL(request, CharsetType.UTF8);
		if (log.isDebugEnabled()){
			log.info("requestAllURL:{}", requestAllURL);
			log.info("requestStringForLog:{}", RequestUtil.getRequestStringForLog(request));
		}

		// TODO 验证来源
		NotifyResultType notifyResultType = validateAndUpdateTrade(paymentAdaptor, request);
		OutputPaymentPGW outputPaymentPGW = getOutputPaymentPGW(request, notifyResultType);

		String returnString = KlikBCAUtil.getPaymentConfirmationXML(outputPaymentPGW);

		log.info(returnString);
		printString(response, returnString);
	}

	/**
	 * @param request
	 * @param notifyResultType
	 * @return
	 */
	protected OutputPaymentPGW getOutputPaymentPGW(HttpServletRequest request,NotifyResultType notifyResultType){
		String userID = request.getParameter("userid");

		String transactionNo = request.getParameter("transno");

		// 19 DD/mm/YYYY HH:MM:SS
		String transactionDate = request.getParameter("transdate");

		String additionalData = request.getParameter("adddata");

		String reason = "";

		// Value of the status field will be 00 if the transaction is successful,
		// and 01 if the transaction is failed.
		// The reason field can be used to indicate reason of failure, and will be shown to the user
		String status = "";

		boolean isSuccess = notifyResultType == NotifyResultType.Success;
		if (isSuccess){
			status = "00";// 00 if the transaction is successful,
		}else{
			status = "01";// and 01 if the transaction is failed.
			reason = "payment fail";
		}

		OutputPaymentPGW outputPaymentPGW = new OutputPaymentPGW();
		outputPaymentPGW.setAdditionalData(additionalData);
		outputPaymentPGW.setReason(reason);
		outputPaymentPGW.setStatus(status);
		outputPaymentPGW.setTransactionDate(transactionDate);
		outputPaymentPGW.setTransactionNo(transactionNo);
		outputPaymentPGW.setUserID(userID);
		return outputPaymentPGW;
	}

}
