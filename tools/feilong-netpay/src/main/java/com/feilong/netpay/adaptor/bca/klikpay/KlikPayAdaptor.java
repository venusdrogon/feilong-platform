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
package com.feilong.netpay.adaptor.bca.klikpay;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.security.oneway.MD5Util;
import com.feilong.commons.core.util.NumberUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.netpay.adaptor.AbstractPaymentAdaptor;
import com.feilong.netpay.adaptor.bca.klikpay.command.OutputPaymentIPAY;
import com.feilong.netpay.adaptor.bca.klikpay.util.BCAKeyGenerator;
import com.feilong.netpay.command.PayRequest;
import com.feilong.netpay.command.PaymentFormEntity;
import com.feilong.netpay.command.TradeRole;
import com.feilong.tools.net.httpclient.HttpClientUtilException;
import com.feilong.tools.xstream.ToXmlConfig;
import com.feilong.tools.xstream.XStreamUtil;

/**
 * The Class KlikPayAdaptor.
 */
public class KlikPayAdaptor extends AbstractPaymentAdaptor{

	/** The Constant log. */
	private static final Logger	log						= LoggerFactory.getLogger(KlikPayAdaptor.class);

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

		if (Validator.isNullOrEmpty(return_url)){
			throw new IllegalArgumentException("return_url can't be null/empty!");
		}

		// **************************************************************************

		Map<String, String> map = new HashMap<String, String>();

		// klikPayCode String 10 (AN) TRUE
		map.put("klikPayCode", klikPayCode);

		// transactionNo String 18 (AN) TRUE
		String transactionNo = tradeNo;
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
		map.put("callback", return_url);

		// transactionDate String 19 DD/MM/YYYY hh:mm:ss TRUE

		Date transactionDate = new Date();
		map.put("transactionDate", DateUtil.date2String(transactionDate, transactionDatePattern));

		// descp String 60 (AN) FALSE
		map.put("descp", "");

		// miscFee String 12 7500000.00 FALSE
		map.put("miscFee", "0.00");

		// signature String 10 (N) TRUE
		// - signature (10) is validation that will be parsed by BCA KlikPay login page to decide whether data sent is valid or not.
		String keyId = getKeyId(clearkey);
		String signature = getSignature(klikPayCode, transactionDate, transactionNo, totalAmount, currencyDefault, keyId);
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
	public boolean doNotifyVerify(HttpServletRequest request){
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
		}

		// 不必填
		// - approvalCode (7, 8) are given by BCA only when customers use Credit Card as their payment on BCA KlikPay.
		String approvalCode = request.getParameter("approvalCode");

		// 9 authKey string 32 (N) TRUE
		String authKey = request.getParameter("authKey");
		if (Validator.isNullOrEmpty(authKey)){
			throw new IllegalArgumentException("authKey can't be null/empty!");
		}

		// 不必填
		String additionalData = request.getParameter("additionalData");

		String keyId = getKeyId(clearkey);
		String ourAuthKey = getAuthKey(
				klikPayCode,
				DateUtil.string2Date(transactionDate, DatePattern.ddMMyyyyHHmmss),
				transactionNo,
				currency,
				keyId);

		// 如果 参数中的加密值 和我们算出来的不相等, 那么失败
		if (!ourAuthKey.equals(authKey)){
			log.error("authKey:{} is not eq our's :{}", authKey, ourAuthKey);
			return false;
		}else{
			log.debug("pass doNotifyVerify,transactionNo:{}", transactionNo);
		}

		return true;
	}

	/**
	 * 获得支付确认 返回的xml.
	 * 
	 * @param outputPaymentIPAY
	 *            the output payment ipay
	 * @return the payment confirmation xml
	 */
	public String getPaymentFlagInvocationOutputXML(OutputPaymentIPAY outputPaymentIPAY){
		if (Validator.isNullOrEmpty(outputPaymentIPAY)){
			throw new IllegalArgumentException("outputPaymentIPAY can't be null/empty!");
		}

		Map<String, Class<?>> aliasMap = new HashMap<String, Class<?>>();
		aliasMap.put("OutputPaymentIPAY", OutputPaymentIPAY.class);

		ToXmlConfig toXmlConfig = new ToXmlConfig();
		toXmlConfig.setAliasMap(aliasMap);

		return XStreamUtil.toXML(outputPaymentIPAY, toXmlConfig);
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.PaymentAdaptor#doGetFeedbackSoCode(javax.servlet.http.HttpServletRequest)
	 */
	public String doGetFeedbackTradeNo(HttpServletRequest request){
		return request.getParameter("transactionNo");
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.PaymentAdaptor#doGetFeedbackTotalFee(javax.servlet.http.HttpServletRequest)
	 */
	public String doGetFeedbackTotalFee(HttpServletRequest request){
		return request.getParameter("totalAmount");
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.PaymentAdaptor#doCloseTrade(java.lang.String, com.feilong.netpay.command.TradeRole)
	 */
	public boolean doCloseTrade(String orderNo,TradeRole tradeRole) throws HttpClientUtilException{
		throw new UnsupportedOperationException("KlikPayAdaptor not support doCloseTrade");
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.adaptor.PaymentAdaptor#doRedirectVerify(javax.servlet.http.HttpServletRequest)
	 */
	public boolean doRedirectVerify(HttpServletRequest request){
		throw new UnsupportedOperationException("KlikPayAdaptor not support doRedirectVerify");
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.PaymentAdaptor#isSupportCloseTrade()
	 */
	public boolean isSupportCloseTrade(){
		return false;
	}

	// 详见 svn "API Doc. for Payment KlikPay BCA-Sprint v1.8.pdf"
	/**
	 * Gets the sign.
	 * 
	 * @param klikPayCode
	 *            the klik pay code
	 * @param transactionDate
	 *            the transaction date
	 * @param transactionNo
	 *            the transaction no
	 * @param totalAmount
	 *            the total amount
	 * @param currency
	 *            the currency
	 * @param keyId
	 *            the key id
	 * @return the sign
	 */
	public String getSignature(String klikPayCode,Date transactionDate,String transactionNo,String totalAmount,String currency,String keyId){

		String firstValue = klikPayCode + transactionNo + currency + keyId;
		log.debug("the firstValue:{}", firstValue);

		String datePattern = "ddMMyyyy";
		String formatTransactionDate = DateUtil.date2String(transactionDate, datePattern);

		// Total amount from redirect forward : 1500500.00
		// Total amount that will be used : 1500500
		// - totalAmount和miscFee 最后两个数字必须是00,注意舍入单位是每1货币。
		String formatTotalAmount = StringUtil.substringWithoutLast(totalAmount, 3);

		Integer secondValue = Integer.parseInt(formatTransactionDate) + Integer.parseInt(formatTotalAmount);
		log.debug("the secondValue:{}", secondValue);

		String firstValueHash = hash(firstValue);
		String secondValueHash = hash(secondValue + "");

		String result = Math.abs((Integer.parseInt(firstValueHash) + Integer.parseInt(secondValueHash))) + "";
		return result;
	}

	// A = klikPayCode
	// B = transactionNo C = currency
	// D = transactionDate
	// K = keyId (current key used for transaction)
	// e
	// k = encrypt by keyId
	// h = hash function
	/**
	 * Gets the auth key.
	 * 
	 * @param klikPayCode
	 *            the klik pay code
	 * @param transactionDate
	 *            the transaction date
	 * @param transactionNo
	 *            the transaction no
	 * @param currency
	 *            the currency
	 * @param keyId
	 *            the key id
	 * @return the auth key
	 */
	public String getAuthKey(String klikPayCode,Date transactionDate,String transactionNo,String currency,String keyId){

		// klikPay Code : 123 1230000000
		// transactionNo : 456 456AAAAAAAAAAAAAAA
		// currency : IDR IDR11
		// transactionDate : 20/01/2010 01:01:01  20/01/2010 01:01:01
		// KeyId : 12345678901234561234567890123456 
		// 12345678901234561234567890123456

		// Field# Parameter Pad Position Length Pad Data
		// 1. klikPayCode Right 10 0
		// 2. transactionNo Right 18 A
		// 3. currency Right 5 1
		// 4. transactionDate Left 19 C
		// 5. keyId Right 32 E

		String padKlikPayCode = BCAKeyGenerator.strpad(klikPayCode, 10, '0', false);
		String padTransactionDate = DateUtil.date2String(transactionDate, DatePattern.ddMMyyyyHHmmss);
		String padTransactionNo = BCAKeyGenerator.strpad(transactionNo, 18, 'A', false);
		String padCurrency = BCAKeyGenerator.strpad(currency, 5, '1', false);
		String padKeyId = keyId;

		String secondValue = padKlikPayCode + padTransactionNo + padCurrency + padTransactionDate + padKeyId;

		String md5SecondValue = MD5Util.encode(secondValue).toUpperCase();

		try{
			String authKeyString = BCAKeyGenerator.doAuthKey(md5SecondValue, keyId).toUpperCase();
			return authKeyString;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Hash.
	 * 
	 * @param value
	 *            the value
	 * @return the string
	 */
	@SuppressWarnings("cast")
	public String hash(String value){
		Integer hash = 0;
		for (int i = 0; i < value.length(); i++){
			// (int)chars[i]); ascii( value[i] );
			hash = (hash * 31) + (int) (value.charAt(i));
			while (hash > Integer.MAX_VALUE){
				hash = hash + Integer.MIN_VALUE - Integer.MAX_VALUE - 1;
			}
			while (hash < Integer.MIN_VALUE){
				hash = hash + Integer.MAX_VALUE - Integer.MIN_VALUE + 1;
			}
		}
		log.debug("the value:{}---> hash:{}", value, hash);
		return hash + "";
	}

	/**
	 * Uppercase [to String [Hexa[clearKey]]].
	 * 
	 * @param clearKey
	 *            the clear key
	 * @return the key id
	 */
	public static String getKeyId(String clearKey){
		char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		byte[] bytes = clearKey.getBytes();
		String keyId = "";
		for (int cntr = 0; cntr < clearKey.length(); cntr++){
			keyId = keyId + hexArray[(bytes[cntr] & 0xFF) / 16] + hexArray[(bytes[cntr] & 0xFF) % 16];
		}

		log.debug("clearKey:{}, keyId:{}", clearKey, keyId);
		return keyId;
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
