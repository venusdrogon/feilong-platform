/*
 * Copyright (c) 2010 Jumbomart All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Jumbomart.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jumbo.
 *
 * JUMBOMART MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. JUMBOMART SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 */
package com.feilong.netpay.adaptor.bca.klikbca;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.Validator;
import com.feilong.netpay.adaptor.AbstractPaymentAdaptor;
import com.feilong.netpay.command.PayRequest;
import com.feilong.netpay.command.PaymentFormEntity;
import com.feilong.netpay.command.TradeRole;
import com.feilong.tools.net.httpclient.HttpClientUtilException;
import com.feilong.tools.xstream.ToXmlConfig;
import com.feilong.tools.xstream.XStreamUtil;

/**
 * The Class KlikBCAAdaptor.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 15, 2013 8:41:39 PM
 */
public class KlikBCAAdaptor extends AbstractPaymentAdaptor{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(KlikBCAAdaptor.class);

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.adaptor.PaymentAdaptor#getPaymentFormEntity(com.feilong.netpay.command.PayRequest, java.util.Map)
	 */
	public PaymentFormEntity getPaymentFormEntity(PayRequest payRequest,Map<String, String> specialSignMap){
		throw new UnsupportedOperationException("KlikBCAAdaptor not support getPaymentFormEntity");
	}

	/**
	 * 生成 Inquiry output数据.
	 * 
	 * @param userID
	 *            the user id
	 * @param additionalData
	 *            the additional data
	 * @param outputDetailPayments
	 *            the output detail payments
	 * @return the payment inquiry xml
	 */
	public String getPaymentInquiryXML(String userID,String additionalData,List<OutputDetailPayment> outputDetailPayments){

		if (Validator.isNullOrEmpty(userID)){
			throw new IllegalArgumentException("userID can't be null/empty!");
		}

		// ********************************************************************

		OutputListTransactionPGW outputListTransactionPGW = new OutputListTransactionPGW();
		outputListTransactionPGW.setUserID(userID);
		outputListTransactionPGW.setAdditionalData(additionalData);

		outputListTransactionPGW.setOutputDetailPayments(outputDetailPayments);

		Map<String, Class<?>> aliasMap = new HashMap<String, Class<?>>();
		aliasMap.put("OutputListTransactionPGW", OutputListTransactionPGW.class);
		aliasMap.put("OutputDetailPayment", OutputDetailPayment.class);

		Map<String, Class<?>> implicitCollectionMap = new HashMap<String, Class<?>>();
		implicitCollectionMap.put("outputDetailPayments", OutputListTransactionPGW.class);

		ToXmlConfig toXmlConfig = new ToXmlConfig();
		toXmlConfig.setAliasMap(aliasMap);
		toXmlConfig.setImplicitCollectionMap(implicitCollectionMap);

		return XStreamUtil.toXML(outputListTransactionPGW, toXmlConfig);
	}

	/**
	 * 获得支付确认 返回的xml.
	 * 
	 * @param outputPaymentPGW
	 *            the output payment pgw
	 * @return the payment confirmation xml
	 */
	public String getPaymentConfirmationXML(OutputPaymentPGW outputPaymentPGW){

		if (Validator.isNullOrEmpty(outputPaymentPGW)){
			throw new IllegalArgumentException("outputPaymentPGW can't be null/empty!");
		}

		Map<String, Class<?>> aliasMap = new HashMap<String, Class<?>>();
		aliasMap.put("OutputPaymentPGW", OutputPaymentPGW.class);

		ToXmlConfig toXmlConfig = new ToXmlConfig();
		toXmlConfig.setAliasMap(aliasMap);

		return XStreamUtil.toXML(outputPaymentPGW, toXmlConfig);

	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.PaymentAdaptor#doNotifyVerify(javax.servlet.http.HttpServletRequest)
	 */
	public boolean doNotifyVerify(HttpServletRequest request){

		String userid = request.getParameter("userid");

		String transno = request.getParameter("transno");

		// 19 DD/mm/YYYY HH:MM:SS
		String transdate = request.getParameter("transdate");

		// 999999999999.99
		String amount = request.getParameter("amount");
		String type = request.getParameter("type");

		String adddata = request.getParameter("adddata");

		if (Validator.isNullOrEmpty(userid)){
			return false;
		}
		if (Validator.isNullOrEmpty(transno)){
			return false;
		}
		if (Validator.isNullOrEmpty(transdate)){
			return false;
		}
		if (Validator.isNullOrEmpty(amount)){
			return false;
		}
		if (Validator.isNullOrEmpty(type)){
			return false;
		}

		// Value of type field always N
		if (!"N".equals(type)){
			return false;
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.PaymentAdaptor#doGetFeedbackSoCode(javax.servlet.http.HttpServletRequest)
	 */
	public String doGetFeedbackTradeNo(HttpServletRequest request){
		return request.getParameter("transno");
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.PaymentAdaptor#doGetFeedbackTotalFee(javax.servlet.http.HttpServletRequest)
	 */
	public String doGetFeedbackTotalFee(HttpServletRequest request){
		return request.getParameter("amount");
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.adaptor.PaymentAdaptor#doRedirectVerify(javax.servlet.http.HttpServletRequest)
	 */
	public boolean doRedirectVerify(HttpServletRequest request){
		throw new UnsupportedOperationException("KlikBCAAdaptor not support doRedirectVerify");
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.PaymentAdaptor#doCloseTrade(java.lang.String, com.feilong.netpay.command.TradeRole)
	 */
	public boolean doCloseTrade(String orderNo,TradeRole tradeRole) throws HttpClientUtilException{
		throw new UnsupportedOperationException("KlikBCAAdaptor not support doCloseTrade");
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.PaymentAdaptor#isSupportCloseTrade()
	 */
	public boolean isSupportCloseTrade(){
		return false;
	}
}
