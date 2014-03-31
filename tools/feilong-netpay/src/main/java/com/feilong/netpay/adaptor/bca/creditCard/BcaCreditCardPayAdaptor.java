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
package com.feilong.netpay.adaptor.bca.creditCard;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.NumberUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.netpay.adaptor.AbstractPaymentAdaptor;
import com.feilong.netpay.command.PayRequest;
import com.feilong.netpay.command.PaymentFormEntity;
import com.feilong.netpay.command.TradeRole;
import com.feilong.servlet.http.RequestUtil;
import com.feilong.tools.net.httpclient.HttpClientUtilException;

/**
 * alipay 纯网关接口<br>
 * <ul>
 * <li>此接口只支持 https 请求</li>
 * <li>参数 body（商品描述）、subject（商品名称）、extra_common_param（公用 回传参数）不能包含特殊字符（如：#、%、&、+）、敏感词汇，<br>
 * 也不能使用外 国文字（旺旺不支持的外文，如：韩文、泰语、藏文、蒙古文、阿拉伯语）；</li>
 * <li>此接口支持重复调用，前提是交易基本信息（买家、卖家、交易金额、超时时间等）在多次调用中保持一致，且交易尚未完成支付。</li>
 * </ul>
 * .
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 15, 2013 8:41:39 PM
 */
public class BcaCreditCardPayAdaptor extends AbstractPaymentAdaptor{

	/** The Constant log. */
	private static final Logger	log				= LoggerFactory.getLogger(BcaCreditCardPayAdaptor.class);

	/** 表单提交地址. */
	private String				gateway;

	/** The method. */
	private String				method;

	/**
	 * Required<br>
	 * Value: Merchant’s DOacquire ID (will be provided by your Account Manager)<br>
	 * Format: Up to 20 alphanumeric characters
	 */
	private String				siteID;

	private String				serviceVersion;

	/**
	 * Required<br>
	 * Value: Transaction currency<br>
	 * Format: 3 characters (ISO 4217 alpha-3 format. Example: USD, MYR, IDR)<br>
	 */
	private String				currencyDefault;

	/** The price pattern. */
	private String				pricePattern	= "############.00";

	/* (non-Javadoc)
	 * @see com.feilong.netpay.adaptor.PaymentAdaptor#getPaymentFormEntity(com.feilong.netpay.command.PayRequest, java.util.Map)
	 */
	public PaymentFormEntity getPaymentFormEntity(PayRequest payRequest,Map<String, String> specialSignMap){
		String tradeNo = payRequest.getTradeNo();
		BigDecimal total_fee = payRequest.getTotalFee();
		String return_url = payRequest.getReturnUrl();
		String notify_url = payRequest.getNotifyUrl();
		if (doValidator(tradeNo, total_fee, return_url, notify_url)){
			PaymentFormEntity paymentFormEntity = doGetPaymentFormEntity(payRequest, return_url, notify_url, specialSignMap);
			return paymentFormEntity;
		}
		return null;
	}
	/**
	 * 验证参数
	 * 
	 * @param tradeNo
	 * @param total_fee
	 * @param return_url
	 * @param notify_url
	 */
	private boolean doValidator(String tradeNo,BigDecimal total_fee,String return_url,String notify_url){
		// ******************************************************************
		// validate
		if (Validator.isNullOrEmpty(tradeNo)){
			throw new IllegalArgumentException("code can't be null/empty!");
		}
		if (Validator.isNullOrEmpty(total_fee)){
			throw new IllegalArgumentException("total_fee can't be null/empty!");
		}

		// 交易总额 单位为 RMB-Yuan 取值范围为[0.01， 100000000.00]
		// 精确到小数点 后两位
		BigDecimal minPay = new BigDecimal(0.01f);
		BigDecimal maxPay = new BigDecimal(100000000);
		if (total_fee.compareTo(minPay) == -1 || total_fee.compareTo(maxPay) == 1){
			throw new IllegalArgumentException("total_fee:" + total_fee + " can't < " + minPay + " or > " + maxPay);
		}

		if (Validator.isNullOrEmpty(return_url)){
			throw new IllegalArgumentException("return_url can't be null/empty!");
		}

		if (Validator.isNullOrEmpty(notify_url)){
			throw new IllegalArgumentException("notify_url can't be null/empty!");
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.adaptor.AbstractPaymentAdaptor#doGetPaymentFormEntity(java.lang.String, java.math.BigDecimal,
	 * java.lang.String, java.lang.String, java.util.Map)
	 */
	protected PaymentFormEntity doGetPaymentFormEntity(PayRequest payRequest,String return_url,String notify_url,Map<String, String> specialSignMap){
		String transactionNo = payRequest.getTradeNo();
		String totalAmount = NumberUtil.toString(payRequest.getTotalFee(), pricePattern);

		// ******************************************************************************
		Map<String, String> map = new HashMap<String, String>();

		// Required
		// Value: Merchant’s DOacquire ID (will be provided by your Account Manager)
		// Format: Up to 20 alphanumeric characters
		map.put("siteID", siteID);

		// Required
		// Value: 1.0
		map.put("serviceVersion", serviceVersion);

		// Required
		// Value: Merchant’s unique Transaction ID
		// Format: Up to 50 alphanumeric characters
		map.put("merchantTransactionID", transactionNo);

		// Required
		// Value: Transaction currency
		// Format: 3 characters (ISO 4217 alpha-3 format. Example: USD, MYR, IDR)
		map.put("currency", currencyDefault);

		// Required
		// Value: Transaction amount
		// Format: Up to 10 numeric characters
		map.put("amount", totalAmount);
		// ******************************Optional*********************************************************************

		// Optional
		// Value: Merchant’s invoice data which will be display to Cardholder
		// Format: Up to 250 alphanumeric characters
		// map.put("merchantTransactionNote", merchantTransactionNote);

		// Optional
		// Value: Additional information which may be required by partner bank/acquirer
		// Format: Up to 250 alphanumeric characters
		// map.put("userDefineValue", userDefineValue);

		// Optional
		// Value: Cardholder billing name (as printed on card) Format: Up to 50 alphanumeric characters
		// map.put("billingName", billingName);

		// Optional
		// Value: Cardholder billing address
		// Format: Up to 250 alphanumeric characters
		// map.put("billingAddress", billingAddress);

		// Optional
		// Value: Cardholder billing city
		// Format: Up to 50 alphabetical characters
		// map.put("billingCity", billingCity);

		// Optional
		// Value: Cardholder billing state
		// Format: Up to 50 alphabetical characters
		// map.put("billingState", billingState);

		// Optional
		// Value: Cardholder billing postal code/zip Format: Up to 10 alphanumeric characters
		// map.put("billingPostalCode", billingPostalCode);

		// Optional
		// Value: Cardholder billing country
		// Format: 2 characters (ISO 3166-1-alpha-2 format. Example: US, MY,ID)
		// map.put("billingCountry", billingCountry);

		// Optional
		// Value: Cardholder contact phone number Format: Up to 20 alphanumeric characters
		// map.put("billingPhone", billingPhone);

		// Optional
		// Value: Cardholder contact email address Format: Up to 50 alphanumeric characters
		// map.put("billingEmail", billingEmail);

		// Optional
		// Value: Goods recipient name
		// Format: Up to 50 alphanumeric characters
		// map.put("deliveryName", deliveryName);

		// Optional
		// Value: Goods recipient address
		// Format: Up to 250 alphanumeric characters
		// map.put("deliveryAddress", deliveryAddress);

		// Optional
		// Value: Goods recipient city
		// Format: Up to 50 alphanumeric characters
		// map.put("deliveryCity", deliveryCity);

		// Optional
		// Value: Goods recipient state
		// Format: Up to 50 alphanumeric characters
		// map.put("deliveryState", deliveryState);

		// Optional
		// Value: Goods recipient postal code/zip
		// Format: Up to 10 alphanumeric characters
		// map.put("deliveryPostalCode", deliveryPostalCode);

		// Optional
		// Value: Goods recipient country
		// Format: 2 characters (ISO 3166-1-alpha-2 format. Example: US, MY,ID)
		// map.put("deliveryCountry", deliveryCountry);

		// *************************************************************************************************
		// 需要被签名的 参数map

		// 特殊 传入
		if (Validator.isNotNullOrEmpty(specialSignMap)){
			map.putAll(specialSignMap);
		}

		return getPaymentFormEntity(gateway, method, map);
		// throw new IllegalArgumentException("specialSignMap has IllegalArgument key");
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.adaptor.PaymentAdaptor#doRedirectVerify(javax.servlet.http.HttpServletRequest)
	 */
	public boolean doRedirectVerify(HttpServletRequest request){
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.PaymentAdaptor#doNotifyVerify(javax.servlet.http.HttpServletRequest)
	 */
	public boolean doNotifyVerify(HttpServletRequest request){

		// Value: Merchant’s DOacquire ID
		// Format: Up to 20 alphanumeric characters
		String siteID = request.getParameter("siteID");

		// Value: 1.0
		String serviceVersion = request.getParameter("serviceVersion");

		// Value: Merchant’s unique Transaction ID
		// Format: Up to 50 alphanumeric characters
		String merchantTransactionID = request.getParameter("merchantTransactionID");

		// Value: Transaction currency
		// Format: 3 characters (ISO 4217 alpha-3 format. Example: USD, MYR,
		// IDR)
		String currency = request.getParameter("currency");

		// Value: Transaction amount
		// Format: Up to 10 numeric characters
		String amount = request.getParameter("amount");

		// Value: DOacquire transaction ID
		// Format: Up to 50 alphanumeric characters
		String transactionID = request.getParameter("transactionID");

		// Value: AUTHORIZATION
		String transactionType = request.getParameter("transactionType");

		// Value: DOacquire transaction status
		// Format:
		//  PENDING (transaction still in process)
		//  APPROVE (transaction has been authorized by the partner
		// bank/acquirer)
		//  DECLINE (transaction has been rejected by the partner
		// bank/acquirer)
		//  SCRUB (transaction has been rejected based on account risk
		// policy)
		//  ERROR (network connectivity error with the partner
		// bank/acquirer)
		//  CANCEL (cardholder did not complete the transaction)
		//
		String transactionStatus = request.getParameter("transactionStatus");

		// Value: Card number
		// Format: First 6 and Last 4 of Cardholder Card Number (411111**1111)
		String cardNo = request.getParameter("cardNo");

		// Value: Partner bank/acquirer selected to authorize this transaction
		// Format: Up to 10 alphanumeric characters
		String acquirerCode = request.getParameter("acquirerCode");

		// Value: Merchant account provided by partner bank/acquirer
		// Format: Up to 50 alphanumeric characters
		String acquirerMerchantAccount = request.getParameter("acquirerMerchantAccount");

		// Value: Response code received from the partner bank/acquirer
		// Format: Up to 10 alphanumeric characters
		String acquirerResponseCode = request.getParameter("acquirerResponseCode");

		// Value: Approval code given by the partner bank/acquirer
		// Format: Up to 10 alphanumeric characters
		String acquirerApprovalCode = request.getParameter("acquirerApprovalCode");

		// Value: System generated code by DOacquire when transaction status is
		// SCRUB
		// Format: Up to 10 alphanumeric characters
		String scrubCode = request.getParameter("scrubCode");

		// Value: System generated message by DOacquire when transaction
		// status is SCRUB
		// Format: Up to 250 alphanumeric characters
		String scrubMessage = request.getParameter("scrubMessage");

		boolean isSignOk = true;

		if (isSignOk){
			return true;
		}else{
			// Object[] logArgs = { WORDS, RequestUtil.getRequestAllURL(request) };
			// log.error("from DoKu WORDS is:{},ourWORDS:{},full request url is :{}", logArgs);
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.PaymentAdaptor#doGetFeedbackSoCode(javax.servlet.http.HttpServletRequest)
	 */
	public String doGetFeedbackTradeNo(HttpServletRequest request){
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.PaymentAdaptor#doGetFeedbackTotalFee(javax.servlet.http.HttpServletRequest)
	 */
	public String doGetFeedbackTotalFee(HttpServletRequest request){
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.PaymentAdaptor#doCloseTrade(java.lang.String, com.feilong.netpay.command.TradeRole)
	 */
	public boolean doCloseTrade(String orderNo,TradeRole tradeRole) throws HttpClientUtilException{
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.PaymentAdaptor#isSupportCloseTrade()
	 */
	public boolean isSupportCloseTrade(){
		return false;
	}

	/**
	 * @param gateway
	 *            the gateway to set
	 */
	public void setGateway(String gateway){
		this.gateway = gateway;
	}

	/**
	 * @param method
	 *            the method to set
	 */
	public void setMethod(String method){
		this.method = method;
	}

	/**
	 * @param currencyDefault
	 *            the currencyDefault to set
	 */
	public void setCurrencyDefault(String currencyDefault){
		this.currencyDefault = currencyDefault;
	}

	/**
	 * @param siteID
	 *            the siteID to set
	 */
	public void setSiteID(String siteID){
		this.siteID = siteID;
	}

	/**
	 * @param serviceVersion
	 *            the serviceVersion to set
	 */
	public void setServiceVersion(String serviceVersion){
		this.serviceVersion = serviceVersion;
	}

}
