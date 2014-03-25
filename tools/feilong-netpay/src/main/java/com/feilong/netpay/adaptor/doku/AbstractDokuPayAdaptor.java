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
package com.feilong.netpay.adaptor.doku;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.security.oneway.SHA1Util;
import com.feilong.commons.core.util.JsonFormatUtil;
import com.feilong.commons.core.util.NumberUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.netpay.adaptor.AbstractPaymentAdaptor;
import com.feilong.netpay.command.PayRequest;
import com.feilong.netpay.command.PaySoLine;
import com.feilong.netpay.command.PaymentFormEntity;
import com.feilong.netpay.command.TradeRole;
import com.feilong.servlet.http.RequestUtil;
import com.feilong.tools.net.httpclient.HttpClientUtilException;

/**
 * Doku支付方式.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 11, 2014 5:05:30 PM
 */
public abstract class AbstractDokuPayAdaptor extends AbstractPaymentAdaptor{

	/** The Constant log. */
	private static final Logger	log							= LoggerFactory.getLogger(AbstractDokuPayAdaptor.class);

	/** 表单提交地址. */
	private String				gateway;

	/** The MALLID. */
	private String				MALLID;

	/** The CHAINMERCHANT. */
	private String				CHAINMERCHANT;

	/** The Shared_key. */
	private String				Shared_key;

	/** The method. */
	private String				method;

	/** The shipping item name. */
	private String				shippingItemName;

	/** The CURRENCY. */
	private String				CURRENCY;

	/**
	 * Payment Channel<br>
	 * CODE DESCRIPTION
	 * <ul>
	 * <li>01 Credit Card Visa/Master</li>
	 * <li>02 Mandiri ClickPay</li>
	 * <li>05 Permata VA LITE</li>
	 * <li>06 BRI e-Pay</li>
	 * </ul>
	 * .
	 */
	private String				PAYMENTCHANNEL;

	/** The price pattern. */
	private String				pricePattern				= "############.00";

	/** 跳转回来带的 成功状态 code. */
	private String				redirectSuccessStatusCode	= "0000";

	// Andi 拿出的 DOKU 邮件里面的script 是 "ISO-8859-1"
	/** The charset name for sh a1. */
	private String				charsetNameForSHA1			= CharsetType.ISO_8859_1;

	// **********************************************************************************************

	/**
	 * Construct 后.
	 */
	@PostConstruct
	public void postConstruct(){
		if (log.isDebugEnabled()){

		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.adaptor.PaymentAdaptor#getPaymentFormEntity(com.feilong.netpay.command.PayRequest, java.util.Map)
	 */
	public PaymentFormEntity getPaymentFormEntity(PayRequest payRequest,Map<String, String> specialSignMap){
		// TODO validate param
		if (Validator.isNullOrEmpty(payRequest)){
			throw new IllegalArgumentException("paySo can't be null/empty!");
		}

		String NAME = payRequest.getBuyerName();
		String EMAIL = payRequest.getBuyerEmail();

		// ******************* 验证 NAME********************
		// NAME AN …50 Travel Arranger Name / Buyer name
		if (Validator.isNullOrEmpty(NAME)){
			throw new IllegalArgumentException("NAME can't be null/empty!");
		}else if (NAME.length() > 50){
			throw new IllegalArgumentException("the length of NAME not more than 50");
		}

		// ******************* 验证 EMAIL********************
		// EMAIL ANS …100 Customer email
		if (Validator.isNullOrEmpty(EMAIL)){
			throw new IllegalArgumentException("EMAIL can't be null/empty!");
		}else if (NAME.length() > 100){
			throw new IllegalArgumentException("the length of EMAIL not more than 100");
		}

		// ******************* 验证 code********************
		String tradeNo = payRequest.getTradeNo();
		if (Validator.isNullOrEmpty(tradeNo)){
			throw new IllegalArgumentException("code can't be null/empty!");
		}else if (tradeNo.length() > 14){ // TRANSIDMERCHANT AN …14 Transaction ID from Merchant
			throw new IllegalArgumentException("the length of code not more than 14");
		}

		String TRANSIDMERCHANT = tradeNo;

		// ******************* 验证 totalActual********************
		BigDecimal totalFee = payRequest.getTotalFee();
		BigDecimal transferFee = payRequest.getTransferFee();

		if (Validator.isNullOrEmpty(totalFee)){
			throw new IllegalArgumentException("totalActual can't be null/empty!");
		}
		if (Validator.isNullOrEmpty(transferFee)){
			throw new IllegalArgumentException("transferFee can't be null/empty!");
		}

		// AMOUNT N 12.2 Total amount. Eg 10000.00
		BigDecimal maxValue = new BigDecimal("999999999999.00");

		if (totalFee.compareTo(maxValue) > 0){
			throw new IllegalArgumentException("totalFee:" + totalFee + " can't be more than maxValue:" + maxValue + "");
		}

		// ******************* 验证 totalActual********************

		String AMOUNT = NumberUtil.toString(totalFee, pricePattern);
		// these are the total amount that the buyer must paid, both of them must have the same value for checking purpose in Doku side
		String PURCHASEAMOUNT = AMOUNT;
		String REQUESTDATETIME = DateUtil.date2String(new Date(), DatePattern.timestamp);

		// see Doku_OneCheckout_Metraplasa page 61
		String PURCHASECURRENCY = CURRENCY;

		// TODO
		String SESSIONID = DateUtil.date2String(new Date(), DatePattern.timestamp);

		// **************************************************************************************

		Map<String, String> map = new HashMap<String, String>();

		// Given by DOKU
		map.put("MALLID", MALLID);

		// Given by DOKU, if not using Chain, default value is NA
		map.put("CHAINMERCHANT", CHAINMERCHANT);

		// Total amount. Eg 10000.00
		map.put("AMOUNT", AMOUNT);

		// Total amount. Eg: 10000.00
		map.put("PURCHASEAMOUNT", PURCHASEAMOUNT);

		// Transaction ID from Merchant

		// TRANSIDMERCHANT AN …14 Transaction ID from Merchant
		map.put("TRANSIDMERCHANT", TRANSIDMERCHANT);

		// Hashed key combi- nation encryption (use SHA1 meth- od).
		// The hashed key generated from combining these parameters value in this order : AMOUNT+MALLID + <shared key> +
		// TRANSIDMER-CHANT
		map.put("WORDS", getWORDSForPaymentRequest(TRANSIDMERCHANT, AMOUNT));

		// YYYYMMDDHHMMSS
		map.put("REQUESTDATETIME", REQUESTDATETIME);

		// ISO3166 , numeric code
		map.put("CURRENCY", CURRENCY);

		// ISO3166 , numeric code
		map.put("PURCHASECURRENCY", PURCHASECURRENCY);

		//
		map.put("SESSIONID", SESSIONID);

		// Travel Arranger Name / Buyer name
		map.put("NAME", NAME);

		// Customer email
		map.put("EMAIL", EMAIL);

		// Custom additional data for specific Merchant use
		// String ADDITIONALDATA = "mp2";
		// map.put("ADDITIONALDATA", ADDITIONALDATA);
		//
		// See payment chan-nel code list
		map.put("PAYMENTCHANNEL", PAYMENTCHANNEL);
		//
		// // Credit Card Number or Mandiri Debit Card
		// map.put("CARDNUMBER", "");
		//
		// // Credit Card Expiry Date (YYMM)
		// map.put("EXPIRYDATE", "");
		//
		// // Credit Card Security Number f“r "not pres”nt" transac- tions
		// map.put("CVV2", "");
		//
		// // Acquirer code for installment
		// map.put("INSTALLMENT_ACQUIRER", "");
		//
		// // Number of month to pay the installment
		// map.put("TENOR", "");
		//
		// // Promotion ID from the bank for curren merchant
		// map.put("PROMOID", "");
		//
		// // 10 last digits of card number
		// map.put("CHALLENGE_CODE_1", "");
		//
		// // amount to be paid (without fragment)
		// map.put("CHALLENGE_CODE_2", "");
		//
		// // 9 digit of random number generated by merchant
		// map.put("CHALLENGE_CODE_3", "");
		//
		// // Virtual Account identifier for VA transaction
		// map.put("PAYMENTCODE", "");

		// **********************CARDHOLDER INFOR- MATION *******************************************************
		// // Cardholder Name
		// map.put("CC_NAME", "");
		//
		// // Home address con- tains street and number
		// map.put("ADDRESS", "");
		//
		// // City name
		// map.put("CITY", "");
		//
		// // State / province name
		// map.put("STATE", "");
		//
		// // ISO3166 , alpha-2
		// map.put("COUNTRY", "");
		//
		// // Zip Code
		// map.put("ZIPCODE", "");
		//
		// // Home Phone
		// map.put("HOMEPHONE", "");
		//
		// // Mobile Phone
		// map.put("MOBILEPHONE", "");
		//
		// // Work Phone / Office Phone
		// map.put("WORKPHONE", "");
		//
		// // YYYYMMDD
		// map.put("BIRTHDATE", "");

		// *************************NON AIRLINES CATEGORY ***************************************************
		// Show transaction description.
		// Use comma to separate each field and semicolon for each item.
		// Item1,1000.00,2,20000.00;item2,15000.00,2,30000.00
		String BASKET = getBASKET(payRequest);
		map.put("BASKET", BASKET);

		// // Shipping address contains street and number
		// map.put("SHIPPING_ADDRESS", "shanghai jiangsu nan 111");
		//
		// // City name
		// map.put("SHIPPING_CITY", "shanghai");
		//
		// // State / province name
		// map.put("SHIPPING_STATE", "shanghai");
		//
		// // ISO3166 , alpha-2
		// map.put("SHIPPING_COUNTRY", "china");
		//
		// // Zip Code
		// map.put("SHIPPING_ZIPCODE", "216000");

		log.debug("{}", JsonFormatUtil.format(map));

		boolean isPassValidatorSpecialSignMap = validatorSpecialSignMap(specialSignMap);

		if (isPassValidatorSpecialSignMap){

			// *************************************************************************************************
			// 需要被签名的 参数map

			// 特殊 传入
			if (Validator.isNotNullOrEmpty(specialSignMap)){
				map.putAll(specialSignMap);
			}

			return getPaymentFormEntity(gateway, method, map);
		}
		throw new IllegalArgumentException("specialSignMap has IllegalArgument key");
	}

	/**
	 * 生成 BASKET,显示交易说明（分号分隔每个item，逗号分隔每个字段）.
	 * 
	 * @param payRequest
	 *            the pay so
	 * @return the bASKET
	 */
	private String getBASKET(PayRequest payRequest){
		StringBuilder sb = new StringBuilder();

		List<PaySoLine> paySoLineList = payRequest.getPaySoLineList();

		for (int i = 0, j = paySoLineList.size(); i < j; ++i){
			PaySoLine paySoLine = paySoLineList.get(i);

			String itemName = paySoLine.getItemName();

			itemName = formatItemName(itemName);
			sb.append(itemName);
			sb.append(",");

			sb.append(NumberUtil.toString(paySoLine.getUnitPrice(), pricePattern));
			sb.append(",");

			sb.append(paySoLine.getQuantity());
			sb.append(",");

			sb.append(NumberUtil.toString(paySoLine.getSubTotalPrice(), pricePattern));
			if (i != j - 1){
				sb.append(";");
			}
		}

		// **************如果包含运费,*****************************
		BigDecimal transferFee = payRequest.getTransferFee();
		boolean isHasTransferFee = (transferFee.compareTo(new BigDecimal(0)) == 1);

		if (isHasTransferFee){
			sb.append(";");

			sb.append(shippingItemName);
			sb.append(",");

			sb.append(NumberUtil.toString(transferFee, pricePattern));
			sb.append(",");

			sb.append(1);
			sb.append(",");

			sb.append(NumberUtil.toString(transferFee, pricePattern));
		}

		return sb.toString();
	}

	/**
	 * 将item 名称中的 分号(;) 以及逗号 (,) 过滤掉.
	 * 
	 * @param itemName
	 *            the item name
	 * @return the string
	 */
	private String formatItemName(String itemName){
		// TODO 验证特殊字符
		return itemName.replace(";", " ").replace(",", " ");
	}

	/**
	 * 验证输入的参数(子类可以按照需要 重写).
	 * 
	 * @param specialSignMap
	 *            指定的签名map
	 * @return true, if successful
	 */
	protected boolean validatorSpecialSignMap(Map<String, String> specialSignMap){
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.jumbo.brandstore.payment.PaymentAdaptor#notifyVerify(java.lang.String, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public boolean doNotifyVerify(HttpServletRequest request){
		// N 12.2 Total amount. Eg: 10000.00
		String AMOUNT = request.getParameter("AMOUNT");

		// AN ...14 Transaction ID from Merchant
		String TRANSIDMERCHANT = request.getParameter("TRANSIDMERCHANT");

		// AN ...200 Hashed key combination encryption (use SHA1 method).
		// The hashed key generated from com-bining these parameters value in this order:AMOUNT+MALLID+<shared key>+TRANSIDMERCHANT+
		// RESULTMSG+VERIFYSTATUS
		String WORDS = request.getParameter("WORDS");

		// A 1 P: Notify Payment V: Notify Reversal
		String STATUSTYPE = request.getParameter("STATUSTYPE");

		// N 4 0000: Success, others Failed
		String RESPONSECODE = request.getParameter("RESPONSECODE");

		// AN ...20 Transaction number from bank
		String APPROVALCODE = request.getParameter("APPROVALCODE");

		// A ...20 SUCCESS / FAILED
		String RESULTMSG = request.getParameter("RESULTMSG");

		// N 2 See payment channel code list
		String PAYMENTCHANNEL = request.getParameter("PAYMENTCHANNEL");

		// N …8 Virtual Account identifier for VA transaction
		String PAYMENTCODE = request.getParameter("PAYMENTCODE");

		// AN ...48
		String SESSIONID = request.getParameter("SESSIONID");

		// AN …100 Bank Issuer
		String BANK = request.getParameter("BANK");

		// N 14 YYYYMMDDHHMMSS
		String PAYMENTDATETIME = request.getParameter("PAYMENTDATETIME");

		// A …10 APPROVE / REJECT / REVIEW / HIGHRISK / NA
		String VERIFYSTATUS = request.getParameter("VERIFYSTATUS");

		// ANS 16 Masked card number
		// for Visa/Master
		// String MCN = request.getParameter("MCN");

		// N 30 Generated by Fraud Screening (RequestID)
		// for Visa/Master
		// String VERIFYID = request.getParameter("VERIFYID");

		// N …3 0 - 100
		// for Visa/Master
		// String VERIFYSCORE = request.getParameter("VERIFYSCORE");

		String ourWORDS = getWORDSForNotify(TRANSIDMERCHANT, AMOUNT, RESULTMSG, VERIFYSTATUS);
		boolean isSignOk = ourWORDS.equals(WORDS);

		if (isSignOk){
			return true;
		}else{
			Object[] logArgs = { WORDS, ourWORDS, RequestUtil.getRequestAllURL(request) };
			log.error("from DoKu WORDS is:{},ourWORDS:{},full request url is :{}", logArgs);
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.adaptor.AbstractPaymentAdaptor#doRedirectVerify(javax.servlet.http.HttpServletRequest)
	 */
	public boolean doRedirectVerify(HttpServletRequest request){

		// 是正确的 redirect 地址
		boolean isCorrectRedirectAddress = false;

		// 是否支付成功
		boolean isPaymentStatusSuccess = false;

		// ************************************************

		// N 12.2 Total amount. Eg: 10000.00
		String AMOUNT = request.getParameter("AMOUNT");

		// AN ...14 Transaction ID from Merchant
		String TRANSIDMERCHANT = request.getParameter("TRANSIDMERCHANT");

		// AN ...200 Hashed key combination encryption (use SHA1 method).
		// The hashed key generated from com-bining these parameters value in this order:AMOUNT+MALLID+<shared key>+TRANSIDMERCHANT+
		// RESULTMSG+VERIFYSTATUS
		String WORDS = request.getParameter("WORDS");

		// N 4 0000: Success, others Failed
		String STATUSCODE = request.getParameter("STATUSCODE");

		// N 2 See payment channel code list
		String PAYMENTCHANNEL = request.getParameter("PAYMENTCHANNEL");

		// AN ...48
		String SESSIONID = request.getParameter("SESSIONID");

		// N …8 Virtual Account identifier for VA transaction
		String PAYMENTCODE = request.getParameter("PAYMENTCODE");

		String ourWORDS = getWORDSForRedirect(TRANSIDMERCHANT, AMOUNT, STATUSCODE);
		boolean isSignOk = ourWORDS.equals(WORDS);

		if (isSignOk){
			if (redirectSuccessStatusCode.equals(STATUSCODE)){
				return true;
			}else{
				log.error("redirectSuccessStatusCode:{}", redirectSuccessStatusCode);
				return false;
			}
		}else{
			Object[] logArgs = { WORDS, ourWORDS, RequestUtil.getRequestAllURL(request) };
			log.error("from DoKu WORDS is:{},ourWORDS:{},full request url is :{}", logArgs);
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.adaptor.PaymentAdaptor#doGetFeedbackSoCode(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String doGetFeedbackTradeNo(HttpServletRequest request){
		return request.getParameter("SESSIONID");
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.adaptor.PaymentAdaptor#doGetFeedbackTotalFee(javax.servlet.http.HttpServletRequest)
	 */
	public String doGetFeedbackTotalFee(HttpServletRequest request){
		return request.getParameter("AMOUNT");
	}

	/*
	 * (non-Javadoc)
	 * @see com.jumbo.brandstore.payment.PaymentAdaptor#closeTrade(java.lang.String, com.jumbo.brandstore.payment.TradeRole)
	 */
	public boolean doCloseTrade(String orderNo,TradeRole tradeRole) throws HttpClientUtilException{
		throw new UnsupportedOperationException("DOKU don't support close trade");
	}

	/*
	 * (non-Javadoc)
	 * @see com.jumbo.brandstore.payment.PaymentAdaptor#isSupportCloseTrade()
	 */
	public boolean isSupportCloseTrade(){
		return false;
	}

	/**
	 * Hashed key combination encryption (use SHA1 method). <br>
	 * The hashed key generated from com- bining these parameters value in this order: <br>
	 * AMOUNT+MALLID+<shared key> +TRANSIDMERCHANT+ RESULTMSG+VERIFYSTATUS
	 * 
	 * @param TRANSIDMERCHANT
	 *            the tRANSIDMERCHANT
	 * @param AMOUNT
	 *            the aMOUNT
	 * @return the wORDS
	 */
	private String getWORDSForPaymentRequest(String TRANSIDMERCHANT,String AMOUNT){
		String WORDS = AMOUNT + MALLID + Shared_key + TRANSIDMERCHANT;
		return SHA1Util.encode(WORDS, charsetNameForSHA1);
	}

	/**
	 * Hashed key combination encryption (use SHA1 method).<br>
	 * The hashed key generated from com- bining these parameters value in this order:<br>
	 * AMOUNT+MALLID+<shared key> +TRANSIDMERCHANT+ RESULTMSG+VERIFYSTATUS
	 * 
	 * @param TRANSIDMERCHANT
	 *            the tRANSIDMERCHANT
	 * @param AMOUNT
	 *            the aMOUNT
	 * @param RESULTMSG
	 *            the rESULTMSG
	 * @param VERIFYSTATUS
	 *            the vERIFYSTATUS
	 * @return the wORDS for notify
	 */
	private String getWORDSForNotify(String TRANSIDMERCHANT,String AMOUNT,String RESULTMSG,String VERIFYSTATUS){
		String WORDS = AMOUNT + MALLID + Shared_key + TRANSIDMERCHANT + RESULTMSG + VERIFYSTATUS;
		return SHA1Util.encode(WORDS, charsetNameForSHA1);
	}

	/**
	 * Hashed key combination encryption (use SHA1 method). <br>
	 * The hashed key generated from combining these parameters value in this order:<br>
	 * AMOUNT+<shared key> +TRANSIDMERCHANT+STATUSCODE
	 * 
	 * @param TRANSIDMERCHANT
	 *            the tRANSIDMERCHANT
	 * @param AMOUNT
	 *            the aMOUNT
	 * @param STATUSCODE
	 *            the sTATUSCODE
	 * @return the wORDS for redirect
	 */
	private String getWORDSForRedirect(String TRANSIDMERCHANT,String AMOUNT,String STATUSCODE){
		String WORDS = AMOUNT + Shared_key + TRANSIDMERCHANT + STATUSCODE;
		return SHA1Util.encode(WORDS, charsetNameForSHA1);
	}

	// ****************************************************************************************************************************

	/**
	 * Sets the 表单提交地址 <br>
	 * 支付宝网关.
	 * 
	 * @param gateway
	 *            the gateway to set
	 */
	public void setGateway(String gateway){
		this.gateway = gateway;
	}

	/**
	 * Sets the mALLID.
	 * 
	 * @param mALLID
	 *            the mALLID to set
	 */
	public void setMALLID(String mALLID){
		MALLID = mALLID;
	}

	/**
	 * Sets the cHAINMERCHANT.
	 * 
	 * @param cHAINMERCHANT
	 *            the cHAINMERCHANT to set
	 */
	public void setCHAINMERCHANT(String cHAINMERCHANT){
		CHAINMERCHANT = cHAINMERCHANT;
	}

	/**
	 * Sets the shared_key.
	 * 
	 * @param shared_key
	 *            the shared_key to set
	 */
	public void setShared_key(String shared_key){
		Shared_key = shared_key;
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
	 * Sets the shipping item name.
	 * 
	 * @param shippingItemName
	 *            the shippingItemName to set
	 */
	public void setShippingItemName(String shippingItemName){
		this.shippingItemName = shippingItemName;
	}

	/**
	 * Sets the pAYMENTCHANNEL.
	 * 
	 * @param pAYMENTCHANNEL
	 *            the pAYMENTCHANNEL to set
	 */
	public void setPAYMENTCHANNEL(String pAYMENTCHANNEL){
		PAYMENTCHANNEL = pAYMENTCHANNEL;
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
	 * Sets the cURRENCY.
	 * 
	 * @param cURRENCY
	 *            the cURRENCY to set
	 */
	public void setCURRENCY(String cURRENCY){
		CURRENCY = cURRENCY;
	}

}
