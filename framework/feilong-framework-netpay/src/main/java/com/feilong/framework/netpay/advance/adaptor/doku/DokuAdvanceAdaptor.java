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
package com.feilong.framework.netpay.advance.adaptor.doku;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.enumeration.HttpMethodType;
import com.feilong.commons.core.lang.EnumUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.framework.netpay.advance.AbstractPaymentAdvanceAdaptor;
import com.feilong.framework.netpay.advance.adaptor.doku.command.DokuQueryResult;
import com.feilong.framework.netpay.advance.adaptor.doku.util.DokuQueryResultParse;
import com.feilong.framework.netpay.advance.command.QueryRequest;
import com.feilong.framework.netpay.advance.command.QueryResult;
import com.feilong.framework.netpay.advance.exception.TradeQueryException;
import com.feilong.framework.netpay.command.PaymentResult;
import com.feilong.framework.netpay.payment.adaptor.doku.command.Resultmsg;
import com.feilong.framework.netpay.payment.adaptor.doku.util.DokuAdaptorUtil;
import com.feilong.tools.net.httpclient3.HttpClientConfig;
import com.feilong.tools.net.httpclient3.HttpClientUtil;
import com.feilong.tools.security.oneway.SHA1Util;

/**
 * Doku支付方式.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 11, 2014 5:05:30 PM
 */
public class DokuAdvanceAdaptor extends AbstractPaymentAdvanceAdaptor{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(DokuAdvanceAdaptor.class);

	/** The MALLID. */
	private String				MALLID;

	/** The CHAINMERCHANT. */
	private String				CHAINMERCHANT;

	/** The Shared_key. */
	private String				Shared_key;

	/** 查询网关提交地址. */
	private String				queryGateway;

	/** queryMethod. */
	private String				queryMethod;

	/**
	 * Payment Channel
	 * <ul>
	 * <li>01 Credit Card Visa/Master</li>
	 * <li>02 Mandiri ClickPay</li>
	 * <li>05 Permata VA LITE</li>
	 * <li>06 BRI e-Pay</li>
	 * </ul>
	 * .
	 */
	private String				PAYMENTCHANNEL;

	// Andi 拿出的 DOKU 邮件里面的script 是 "ISO-8859-1"
	/** The charset name for sh a1. */
	private String				charsetNameForSHA1;

	/**
	 * Validate param.
	 */
	@PostConstruct
	public void validateParam(){
		if (Validator.isNullOrEmpty(MALLID)){
			throw new IllegalArgumentException("MALLID can't be null/empty!");
		}
		if (Validator.isNullOrEmpty(CHAINMERCHANT)){
			throw new IllegalArgumentException("CHAINMERCHANT can't be null/empty!");
		}
		if (Validator.isNullOrEmpty(Shared_key)){
			throw new IllegalArgumentException("Shared_key can't be null/empty!");
		}
		if (Validator.isNullOrEmpty(queryMethod)){
			throw new IllegalArgumentException("queryMethod can't be null/empty!");
		}

		// TODO
		// if (Validator.isNullOrEmpty(PAYMENTCHANNEL)){
		// throw new IllegalArgumentException("PAYMENTCHANNEL can't be null/empty!");
		// }
		if (Validator.isNullOrEmpty(charsetNameForSHA1)){
			throw new IllegalArgumentException("charsetNameForSHA1 can't be null/empty!");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.feilong.netpay.advanceadaptor.AbstractPaymentAdvanceAdaptor#getQueryResult(com.feilong.netpay.advanceadaptor.command.QueryRequest
	 * )
	 */
	@Override
	public QueryResult getQueryResult(QueryRequest queryRequest) throws TradeQueryException{

		// queryRequest
		if (Validator.isNullOrEmpty(queryRequest)){
			throw new IllegalArgumentException("oh,queryRequest can't be null/empty!");
		}

		// tradeNo
		String TRANSIDMERCHANT = queryRequest.getTradeNo();
		if (Validator.isNullOrEmpty(TRANSIDMERCHANT)){
			throw new IllegalArgumentException("queryRequest.getTradeNo() can't be null/empty!What do you query for?");
		}

		// buyer
		Serializable buyer = queryRequest.getBuyer();
		if (Validator.isNullOrEmpty(buyer)){
			throw new IllegalArgumentException(
							"buyer can't be null/empty!,this time,queryRequest.getBuyer() use for generateSessionId,very important!");
		}
		HttpMethodType httpMethodType = null;
		try{
			httpMethodType = EnumUtil.getEnumByPropertyValueIgnoreCase(HttpMethodType.class, "method", queryMethod);
		}catch (NoSuchFieldException e){
			throw new TradeQueryException(e);
		}

		if (Validator.isNullOrEmpty(httpMethodType)){
			throw new IllegalArgumentException(
							"httpMethodType can't be null/empty!Do you Forget to configure correct queryMethod?and only support get/post now");
		}

		// ***********************************************************************************
		String SESSIONID = DokuAdaptorUtil.generateSessionId(buyer);
		Map<String, String> map = new HashMap<String, String>();
		map.put("MALLID", MALLID); // Given by DOKU
		map.put("CHAINMERCHANT", CHAINMERCHANT);// Given by DOKU
		map.put("TRANSIDMERCHANT", TRANSIDMERCHANT);// Transaction ID from Merchant
		map.put("SESSIONID", SESSIONID);
		map.put("PAYMENTCHANNEL", PAYMENTCHANNEL);// See payment channel code list
		// WORDS AN ...200 Hashed key combination encryption (use SHA1 meth- od).
		// The hashed key generated from combining these parameters value in this order : MALLID+<shared key>+TRANSIDMERCHANT
		map.put("WORDS", getWORDSForCheckStatus(TRANSIDMERCHANT));

		HttpClientConfig httpClientConfig = new HttpClientConfig();
		httpClientConfig.setUri(queryGateway);
		httpClientConfig.setHttpMethodType(httpMethodType);
		httpClientConfig.setParams(map);

		// // <?xml
		// version="1.0"?><PAYMENT_STATUS><AMOUNT>7790000.00</AMOUNT><TRANSIDMERCHANT>010003660001</TRANSIDMERCHANT><WORDS>e9e6ed65c872f1646644001f1b67fc8bc5de8df6</WORDS><RESPONSECODE>0000</RESPONSECODE><APPROVALCODE>RB1234567890</APPROVALCODE><RESULTMSG>SUCCESS</RESULTMSG><PAYMENTCHANNEL>06</PAYMENTCHANNEL><PAYMENTCODE></PAYMENTCODE><SESSIONID>20140508105926</SESSIONID><BANK>BRI</BANK><MCN></MCN><PAYMENTDATETIME>20140508095526</PAYMENTDATETIME><VERIFYID></VERIFYID><VERIFYSCORE>-1</VERIFYSCORE><VERIFYSTATUS>NA</VERIFYSTATUS></PAYMENT_STATUS>
		String responseBodyAsString = HttpClientUtil.getResponseBodyAsString(httpClientConfig);

		if (Resultmsg.FAILED.equals(responseBodyAsString)){
			log.error("responseBodyAsString:[{}],httpClientConfig:{}", responseBodyAsString, JsonUtil.format(httpClientConfig));
			return null;
		}

		DokuQueryResultParse dokuQueryResultParse = new DokuQueryResultParse();
		DokuQueryResult dokuQueryResult = dokuQueryResultParse.parseXML(responseBodyAsString);
		PaymentResult paymentResult = toPaymentResult(dokuQueryResult);

		// DOKU 取不到
		String paymentGatewayTradeNo = null;

		QueryResult queryResult = new QueryResult();

		queryResult.setGatewayAmount(new BigDecimal(dokuQueryResult.getAmount()));
		// 20140508095526
		queryResult.setGatewayPaymentTime(DateUtil.string2Date(dokuQueryResult.getPaymentDateTime(), DatePattern.TIMESTAMP));
		queryResult.setGatewayResult(responseBodyAsString);
		queryResult.setGatewayTradeNo(paymentGatewayTradeNo);
		queryResult.setPaymentResult(paymentResult);
		queryResult.setQueryResultCommand(dokuQueryResult);
		queryResult.setTradeNo(TRANSIDMERCHANT);

		if (log.isDebugEnabled()){
			log.debug("queryResult:{}", JsonUtil.format(queryResult));
		}
		return queryResult;
	}

	/**
	 * To payment result.
	 * 
	 * @param dokuQueryResult
	 *            the doku query result
	 * @return the payment result
	 */
	private PaymentResult toPaymentResult(DokuQueryResult dokuQueryResult){
		String resultmsg = dokuQueryResult.getResultmsg();

		// 成功
		if (Resultmsg.SUCCESS.equals(resultmsg)){
			return PaymentResult.PAID;
		}
		// 失败
		else if (Resultmsg.FAILED.equals(resultmsg)){
			return PaymentResult.NO_PAID;
		}
		// 其余抛出异常
		else{
			throw new UnsupportedOperationException("resultmsg:" + resultmsg + " not support!");
		}
	}

	/**
	 * Gets the wORDS for check status.
	 * 
	 * @param TRANSIDMERCHANT
	 *            the tRANSIDMERCHANT
	 * @return the wORDS for check status
	 */
	// Hashed key combination encryption (use SHA1 meth- od).
	// The hashed key generated from combining these parameters value in this order : MALLID+<shared key>+TRANSIDMERCHANT
	private String getWORDSForCheckStatus(String TRANSIDMERCHANT){
		String WORDS = MALLID + Shared_key + TRANSIDMERCHANT;
		return SHA1Util.encode(WORDS, charsetNameForSHA1);
	}

	// ****************************************************************************************************************************

	/**
	 * Gets the mALLID.
	 * 
	 * @return the mALLID
	 */
	public String getMALLID(){
		return MALLID;
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
	 * Gets the cHAINMERCHANT.
	 * 
	 * @return the cHAINMERCHANT
	 */
	public String getCHAINMERCHANT(){
		return CHAINMERCHANT;
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
	 * Gets the shared_key.
	 * 
	 * @return the shared_key
	 */
	public String getShared_key(){
		return Shared_key;
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
	 * 获得 查询网关提交地址.
	 * 
	 * @return the queryGateway
	 */
	public String getQueryGateway(){
		return queryGateway;
	}

	/**
	 * 设置 查询网关提交地址.
	 * 
	 * @param queryGateway
	 *            the queryGateway to set
	 */
	public void setQueryGateway(String queryGateway){
		this.queryGateway = queryGateway;
	}

	/**
	 * 获得 queryMethod.
	 * 
	 * @return the queryMethod
	 */
	public String getQueryMethod(){
		return queryMethod;
	}

	/**
	 * 设置 queryMethod.
	 * 
	 * @param queryMethod
	 *            the queryMethod to set
	 */
	public void setQueryMethod(String queryMethod){
		this.queryMethod = queryMethod;
	}

	/**
	 * Gets the pAYMENTCHANNEL.
	 * 
	 * @return the pAYMENTCHANNEL
	 */
	public String getPAYMENTCHANNEL(){
		return PAYMENTCHANNEL;
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
	 * 获得 the charset name for sh a1.
	 * 
	 * @return the charsetNameForSHA1
	 */
	public String getCharsetNameForSHA1(){
		return charsetNameForSHA1;
	}

	/**
	 * 设置 the charset name for sh a1.
	 * 
	 * @param charsetNameForSHA1
	 *            the charsetNameForSHA1 to set
	 */
	public void setCharsetNameForSHA1(String charsetNameForSHA1){
		this.charsetNameForSHA1 = charsetNameForSHA1;
	}

	// ****************************************************************************************************************************

}
