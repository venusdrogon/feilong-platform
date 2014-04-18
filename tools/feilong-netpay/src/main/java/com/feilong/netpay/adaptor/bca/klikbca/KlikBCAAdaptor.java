/**
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
package com.feilong.netpay.adaptor.bca.klikbca;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.netpay.adaptor.AbstractPaymentAdaptor;
import com.feilong.netpay.adaptor.bca.klikbca.command.OutputDetailPayment;
import com.feilong.netpay.adaptor.bca.klikbca.command.OutputListTransactionPGW;
import com.feilong.netpay.adaptor.bca.klikbca.command.OutputPaymentPGW;
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
		// amount fields consist of 3 digits of currency and 11 digits of numeric format
		return StringUtil.substring(request.getParameter("amount"), 3);
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
