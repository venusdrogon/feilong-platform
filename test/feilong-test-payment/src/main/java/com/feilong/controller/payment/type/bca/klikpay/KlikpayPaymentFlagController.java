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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feilong.controller.payment.type.BaseNotifyController;
import com.feilong.controller.payment.type.NotifyResultType;
import com.feilong.framework.netpay.payment.PaymentAdaptor;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.klikpay.SprintAsiaKlikPayAdaptor;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.klikpay.command.ApprovalCode;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.klikpay.command.OutputPaymentIPAY;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.klikpay.command.Reason;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.klikpay.command.ReasonEnum;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.klikpay.util.KlikPayUtil;

/**
 * KlikpayPaymentFlagController.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-18 下午11:32:05
 */
@Controller
public class KlikpayPaymentFlagController extends BaseNotifyController{

	/** The Constant log. */
	@SuppressWarnings("unused")private static final Logger	log	= LoggerFactory.getLogger(KlikpayPaymentFlagController.class);

	/** The payment adaptor. */
	@Autowired
	@Qualifier("klikPayAdaptor")
	private SprintAsiaKlikPayAdaptor		sprintAsiaKlikPayAdaptor;

	/** The transaction date pattern. */
	@Value("#{p_bcaklikPayPayment['transactionDatePattern']}")
	private String				transactionDatePattern;

	/**
	 * Payment flag.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@RequestMapping(value = { "/payment/notify/bca/klikpay" })
	public void paymentFlag(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PaymentAdaptor paymentAdaptor = sprintAsiaKlikPayAdaptor;
		NotifyResultType notifyResultType = validateAndUpdateTrade(paymentAdaptor, request);

		// 将结果转成reasonEnum
		ReasonEnum reasonEnum = toReasonEnum(notifyResultType);
		// 获取 outputPaymentIPAY
		OutputPaymentIPAY outputPaymentIPAY = getOutputPaymentIPAY(request, reasonEnum);

		// 生成 responseString
		String responseString = KlikPayUtil.getPaymentFlagInvocationOutputXML(outputPaymentIPAY);
		printString(response, responseString);
	}

	/**
	 * To reason enum.
	 * 
	 * @param notifyResultType
	 *            the notify result type
	 * @return the reason enum
	 */
	private ReasonEnum toReasonEnum(NotifyResultType notifyResultType){
		boolean isSuccess = notifyResultType == NotifyResultType.Success;
		ReasonEnum reasonEnum = null;
		if (isSuccess){
			reasonEnum = ReasonEnum.SUCCESS;
		}else{
			// TODO 将不同的 错误 转成 ReasonEnum
			reasonEnum = ReasonEnum.CANNOTBEPROCESSED;
		}
		return reasonEnum;
	}

	/**
	 * Gets the output payment ipay.
	 * 
	 * @param request
	 *            the request
	 * @param reasonEnum
	 *            the reason enum
	 * @return the output payment ipay
	 */
	private OutputPaymentIPAY getOutputPaymentIPAY(HttpServletRequest request,ReasonEnum reasonEnum){

		// "_parameterMap": {
		// "klikPayCode": ["03BELAV220"],
		// "transactionDate": ["16/04/2014 14:22:42"],
		// "transactionNo": ["20140416142242"],
		// "currency": ["IDR"],
		// "totalAmount": ["60000.00"],
		// "payType": ["01"],
		// "approvalCode": [""],
		// "authKey": ["F49BCB8F286608D29302F3EF4EA9F3B7"],
		// "additionalData": [""]
		// },
		String transactionDate = request.getParameter("transactionDate");
		String transactionNo = request.getParameter("transactionNo");
		String currency = request.getParameter("currency");
		String klikPayCode = request.getParameter("klikPayCode");
		String payType = request.getParameter("payType");
		String approvalCodeParamValue = request.getParameter("approvalCode");
		String status = "00";
		if (reasonEnum == ReasonEnum.SUCCESS){
			status = "00";// 00 TRX_SUCCESS(*) ;
		}else{
			status = "01";// 01 TRX_REJECTED(*)
		}
		String totalAmount = request.getParameter("totalAmount");

		String additionalData = "";

		// ********************************************************************************

		OutputPaymentIPAY outputPaymentIPAY = new OutputPaymentIPAY();

		outputPaymentIPAY.setAdditionalData(additionalData);

		ApprovalCode approvalCode = new ApprovalCode();
		approvalCode.setFullTransaction(approvalCodeParamValue);
		// approvalCode.setInstallmentTransaction("00003");
		outputPaymentIPAY.setApprovalCode(approvalCode);

		outputPaymentIPAY.setCurrency(currency);
		outputPaymentIPAY.setKlikPayCode(klikPayCode);

		outputPaymentIPAY.setPayType(payType);

		Reason reason = new Reason();
		reason.setEnglish(reasonEnum.getEnglish());
		reason.setIndonesian(reasonEnum.getIndonesian());
		outputPaymentIPAY.setReason(reason);

		outputPaymentIPAY.setStatus(status);

		outputPaymentIPAY.setTotalAmount(totalAmount);

		outputPaymentIPAY.setTransactionDate(transactionDate);
		outputPaymentIPAY.setTransactionNo(transactionNo);
		return outputPaymentIPAY;
	}
}
