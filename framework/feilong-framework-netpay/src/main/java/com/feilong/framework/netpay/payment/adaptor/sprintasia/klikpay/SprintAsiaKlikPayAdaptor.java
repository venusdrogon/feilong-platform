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
package com.feilong.framework.netpay.payment.adaptor.sprintasia.klikpay;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.log.Slf4jUtil;
import com.feilong.commons.core.util.NumberUtil;
import com.feilong.commons.core.util.RegexUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.framework.netpay.command.PaymentResult;
import com.feilong.framework.netpay.payment.adaptor.AbstractPaymentAdaptor;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.klikpay.util.KlikPayUtil;
import com.feilong.framework.netpay.payment.command.PayRequest;
import com.feilong.framework.netpay.payment.command.PaymentFormEntity;

/**
 * The Class KlikPayAdaptor.
 */
public class SprintAsiaKlikPayAdaptor extends AbstractPaymentAdaptor{

	/** The Constant log. */
	private static final Logger	log						= LoggerFactory.getLogger(SprintAsiaKlikPayAdaptor.class);

	/** 表单提交地址. */
	private String				gateway;

	/** The method. */
	private String				method;

	/**
	 * What is the "klikPayCode"? <br>
	 * [AD] klikPayCode is unique ID for MetraPlasa.
	 */
	private String				klikPayCode;

	/** clearKey given by BCA. */
	private String				clearkey;

	// - payType (5) field may consists only one of these values:
	// ▪ 01 = Full Transaction
	// ▪ 02 = Installment Transaction
	// ▪ 03 = Combination of both transactions above
	/** 01 = Full Transaction. */
	private String				payType_FullTransaction	= "01";

	// /** 02 = Installment Transaction. */
	// private String payType_InstallmentTransaction = "02";
	//
	// /** 03 = Combination of both transactions above. */
	// private String payType_CombinationTransaction = "03";

	/** currency只能是 IDR - currency (4) field may consists only one of these values: ▪ IDR. */
	private String				currencyDefault;

	/** The price pattern. */
	private String				pricePattern;

	/** The transaction date pattern. */
	private String				transactionDatePattern;

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.adaptor.PaymentAdaptor#getPaymentFormEntity(com.feilong.netpay.command.PayRequest, java.util.Map)
	 */
	public PaymentFormEntity getPaymentFormEntity(PayRequest payRequest,Map<String, String> specialSignMap){

		doCommonValidate(payRequest);

		String tradeNo = payRequest.getTradeNo();
		BigDecimal totalFee = payRequest.getTotalFee();
		String return_url = payRequest.getReturnUrl();

		// *******************************************************
		String callbackUrl = return_url;

		if (Validator.isNullOrEmpty(callbackUrl)){
			throw new IllegalArgumentException("return_url can't be null/empty!");
		}

		// callback url 的最大长度是100, 虽然可以传>100的字符串过去, 但是返回的时候只返回前100个字符 坑爹
		int callbackUrlMaxLength = 100;
		int callbackUrlLength = callbackUrl.length();
		if (callbackUrlLength > callbackUrlMaxLength){
			throw new IllegalArgumentException(Slf4jUtil.formatMessage(
					"callbackUrl:[{}] ,length:[{}] can't >{}",
					callbackUrl,
					callbackUrlLength,
					callbackUrlMaxLength));
		}

		// *******************************************************
		String transactionNo = tradeNo;
		String regexPattern_transactionNo = "^[0-9a-zA-Z]{1,18}+$";
		if (!RegexUtil.matches(regexPattern_transactionNo, transactionNo)){
			throw new IllegalArgumentException(Slf4jUtil.formatMessage(
					"transactionNo:[{}] ,don't match:[{}]",
					transactionNo,
					regexPattern_transactionNo));
		}

		// **************************验证时间************************************************
		Date createDate = payRequest.getCreateDate();
		if (Validator.isNullOrEmpty(createDate)){
			throw new IllegalArgumentException(Slf4jUtil.formatMessage(
					"transactionNo:[{}],payRequest's createDate can't be null/empty!",
					transactionNo));
		}

		Date transactionDate = createDate;

		// **************************************************************************
		Map<String, String> map = new HashMap<String, String>();

		// klikPayCode String 10 (AN) TRUE
		map.put("klikPayCode", klikPayCode);

		// transactionNo String 18 (AN) TRUE
		map.put("transactionNo", transactionNo);

		// totalAmount String 12 7500000.00 TRUE
		// - totalAmount和miscFee 最后两个数字必须是00,注意舍入单位是每1货币
		String totalAmount = NumberUtil.toString(totalFee, pricePattern);
		map.put("totalAmount", totalAmount);

		// currency String 5 (AN) TRUE
		map.put("currency", currencyDefault);

		// payType String 2 99 TRUE
		// - payType (5) field may consists only one of these values:
		// ▪ 01 = Full Transaction
		// ▪ 02 = Installment Transaction
		// ▪ 03 = Combination of both transactions above
		map.put("payType", payType_FullTransaction);

		// callback String 100 (AN) TRUE
		// callback (6) is the URL on your site that user will be directed when they have finished the transaction on BCA KlikPay.
		map.put("callback", callbackUrl);

		// transactionDate String 19 DD/MM/YYYY hh:mm:ss TRUE
		map.put("transactionDate", DateUtil.date2String(transactionDate, transactionDatePattern));

		// descp String 60 (AN) FALSE
		map.put("descp", "");

		// miscFee String 12 7500000.00 FALSE
		map.put("miscFee", "0.00");

		// signature String 10 (N) TRUE
		// - signature (10) is validation that will be parsed by BCA KlikPay login page to decide whether data sent is valid or not.
		String keyId = KlikPayUtil.getKeyId(clearkey);
		String signature = KlikPayUtil.getSignature(klikPayCode, transactionDate, transactionNo, totalAmount, currencyDefault, keyId);
		map.put("signature", signature);

		// *************************************************************************************************
		// 特殊 传入
		if (Validator.isNotNullOrEmpty(specialSignMap)){
			map.putAll(specialSignMap);
		}

		return getPaymentFormEntity(gateway, method, map);
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.PaymentAdaptor#doNotifyVerify(javax.servlet.http.HttpServletRequest)
	 */
	public PaymentResult verifyNotify(HttpServletRequest request){
		// Field# Parameter Type Length Format Mandatory
		// 1 klikPayCode string 10 (AN) TRUE
		// 2 transactionDate string 19 DD/MM/YYYY hh:mm:ss TRUE
		// 3 transactionNo string 18 (AN) TRUE
		// 4 currency string 5 (AN) TRUE
		// 5 totalAmount number 12 888888.00 TRUE
		// 6 payType string 2 99 TRUE
		// 7 approvalCode[fullTransaction] number 6 (N) FALSE
		// 8 approvalCode[installmentTransaction] number 6 (N) FALSE
		// 9 authKey string 32 (N) TRUE
		// 10 additionalData string 999 (AN) FALSE

		// 1 klikPayCode string 10 (AN) TRUE
		@SuppressWarnings("hiding")
		String klikPayCode = request.getParameter("klikPayCode");

		if (Validator.isNullOrEmpty(klikPayCode)){
			throw new IllegalArgumentException("klikPayCode can't be null/empty!");
		}
		// 2 transactionDate string 19 DD/MM/YYYY hh:mm:ss TRUE
		String transactionDate = request.getParameter("transactionDate");
		if (Validator.isNullOrEmpty(transactionDate)){
			throw new IllegalArgumentException("transactionDate can't be null/empty!");
		}
		// 3 transactionNo string 18 (AN) TRUE
		String transactionNo = request.getParameter("transactionNo");
		if (Validator.isNullOrEmpty(transactionNo)){
			throw new IllegalArgumentException("transactionNo can't be null/empty!");
		}
		// 4 currency string 5 (AN) TRUE
		String currency = request.getParameter("currency");
		if (Validator.isNullOrEmpty(currency)){
			throw new IllegalArgumentException("currency can't be null/empty!");
		}

		// 5 totalAmount number 12 888888.00 TRUE
		String totalAmount = request.getParameter("totalAmount");
		if (Validator.isNullOrEmpty(totalAmount)){
			throw new IllegalArgumentException("totalAmount can't be null/empty!");
		}

		// 6 payType string 2 99 TRUE
		// o 01 = full
		// o 02 = installment
		// o 03 = 01 and 02
		String payType = request.getParameter("payType");
		if (Validator.isNullOrEmpty(payType)){
			throw new IllegalArgumentException("payType can't be null/empty!");
		}else if (!"01".equals(payType)){// 我们只支持 全额支付,此处加上限制,防止用户人为修改传递的参数
			String messagePattern = "transactionNo:[{}],payType:[{}] not support!";
			throw new UnsupportedOperationException(Slf4jUtil.formatMessage(messagePattern, transactionNo, payType));
		}

		// 不必填
		// - approvalCode (7, 8) are given by BCA only when customers use Credit Card as their payment on BCA KlikPay.
		@SuppressWarnings("unused")
		String approvalCode = request.getParameter("approvalCode");

		// 9 authKey string 32 (N) TRUE
		String authKey = request.getParameter("authKey");
		if (Validator.isNullOrEmpty(authKey)){
			throw new IllegalArgumentException("authKey can't be null/empty!");
		}

		// 不必填
		@SuppressWarnings("unused")
		String additionalData = request.getParameter("additionalData");

		String keyId = KlikPayUtil.getKeyId(clearkey);
		String ourAuthKey = KlikPayUtil.getAuthKey(
				klikPayCode,
				DateUtil.string2Date(transactionDate, DatePattern.ddMMyyyyHHmmss),
				transactionNo,
				currency,
				keyId);

		// 如果 参数中的加密值 和我们算出来的不相等, 那么失败
		if (!ourAuthKey.equals(authKey)){
			log.error("authKey:{} is not eq our's :{}", authKey, ourAuthKey);
			return PaymentResult.FAIL;
		}

		// XXX 在API 里面没有发现 状态这样的参数

		log.info("pass doNotifyVerify,transactionNo:{}", transactionNo);
		return PaymentResult.PAID;
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.PaymentAdaptor#doGetFeedbackSoCode(javax.servlet.http.HttpServletRequest)
	 */
	public String getFeedbackTradeNo(HttpServletRequest request){
		return request.getParameter("transactionNo");
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.PaymentAdaptor#doGetFeedbackTotalFee(javax.servlet.http.HttpServletRequest)
	 */
	public String getFeedbackTotalFee(HttpServletRequest request){
		return request.getParameter("totalAmount");
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.adaptor.PaymentAdaptor#doRedirectVerify(javax.servlet.http.HttpServletRequest)
	 */
	public PaymentResult verifyRedirect(HttpServletRequest request){
		throw new UnsupportedOperationException("KlikPayAdaptor not support verifyRedirect,need use customer verifyRedirect");
	}

	/**
	 * Sets the 表单提交地址.
	 * 
	 * @param gateway
	 *            the gateway to set
	 */
	public void setGateway(String gateway){
		this.gateway = gateway;
	}

	/**
	 * Sets the method.
	 * 
	 * @param method
	 *            the method to set
	 */
	public void setMethod(String method){
		this.method = method;
	}

	/**
	 * Sets the what is the "klikPayCode"? <br>
	 * [AD] klikPayCode is unique ID for MetraPlasa.
	 * 
	 * @param klikPayCode
	 *            the klikPayCode to set
	 */
	public void setKlikPayCode(String klikPayCode){
		this.klikPayCode = klikPayCode;
	}

	/**
	 * Sets the currency只能是 IDR - currency (4) field may consists only one of these values: ▪ IDR.
	 * 
	 * @param currencyDefault
	 *            the currencyDefault to set
	 */
	public void setCurrencyDefault(String currencyDefault){
		this.currencyDefault = currencyDefault;
	}

	/**
	 * Sets the clearKey given by BCA.
	 * 
	 * @param clearkey
	 *            the clearkey to set
	 */
	public void setClearkey(String clearkey){
		this.clearkey = clearkey;
	}

	/**
	 * Sets the price pattern.
	 * 
	 * @param pricePattern
	 *            the pricePattern to set
	 */
	public void setPricePattern(String pricePattern){
		this.pricePattern = pricePattern;
	}

	/**
	 * Sets the transaction date pattern.
	 * 
	 * @param transactionDatePattern
	 *            the transactionDatePattern to set
	 */
	public void setTransactionDatePattern(String transactionDatePattern){
		this.transactionDatePattern = transactionDatePattern;
	}
}
