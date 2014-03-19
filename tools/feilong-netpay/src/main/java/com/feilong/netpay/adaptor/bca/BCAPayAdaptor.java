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
package com.feilong.netpay.adaptor.bca;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.security.MD5Util;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.netpay.adaptor.AbstractPaymentAdaptor;
import com.feilong.netpay.command.PaySo;
import com.feilong.netpay.command.PaymentFormEntity;
import com.feilong.netpay.command.TradeRole;
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
public class BCAPayAdaptor extends AbstractPaymentAdaptor{

	private static final Logger	log	= LoggerFactory.getLogger(BCAPayAdaptor.class);

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.PaymentAdaptor#doNotifyVerify(javax.servlet.http.HttpServletRequest)
	 */
	public boolean doNotifyVerify(HttpServletRequest request){
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.PaymentAdaptor#doGetFeedbackSoCode(javax.servlet.http.HttpServletRequest)
	 */
	public String doGetFeedbackSoCode(HttpServletRequest request){
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.PaymentAdaptor#doGetFeedbackTotalFee(javax.servlet.http.HttpServletRequest)
	 */
	public String doGetFeedbackTotalFee(HttpServletRequest request){
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.PaymentAdaptor#doCloseTrade(java.lang.String, com.feilong.netpay.command.TradeRole)
	 */
	public boolean doCloseTrade(String orderNo,TradeRole tradeRole) throws HttpClientUtilException{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.PaymentAdaptor#isSupportCloseTrade()
	 */
	public boolean isSupportCloseTrade(){
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.adaptor.AbstractPaymentAdaptor#doGetPaymentFormEntity(java.lang.String, java.math.BigDecimal,
	 * java.lang.String, java.lang.String, java.util.Map)
	 */
	protected PaymentFormEntity doGetPaymentFormEntity(PaySo paySo,String return_url,String notify_url,Map<String, String> specialSignMap){
		// TODO Auto-generated method stub
		return null;
	}

	// 详见 svn "API Doc. for Payment KlikPay BCA-Sprint v1.8.pdf"
	public String getSign(String klikPayCode,Date transactionDate,String transactionNo,String totalAmount,String currency,String keyId){

		String firstValue = klikPayCode + transactionNo + currency + keyId;
		log.debug("the firstValue:{}", firstValue);

		String formatTransactionDate = DateUtil.date2String(transactionDate, "ddMMyyyy");

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
	public String getAuthKey(String klikPayCode,Date transactionDate,String transactionNo,String currency,String keyId){

		// klikPay Code : 123 1230000000
		// transactionNo : 456 456AAAAAAAAAAAAAAA
		// currency : IDR IDR11
		// transactionDate : 20/01/2010 01:01:01  20/01/2010 01:01:01
		// KeyId : 12345678901234561234567890123456 
		// 12345678901234561234567890123456

		String padKlikPayCode = "";
		String padTransactionDate = "";
		String padTransactionNo = "";
		String padCurrency = "";
		String padKeyId = "";

		String secondValue = padKlikPayCode + padTransactionNo + padCurrency + padTransactionDate + padKeyId;

		String md5SecondValue = MD5Util.encode(secondValue).toUpperCase();

		String authKeyString;
		// Field# Parameter Pad Position Length Pad Data
		// 1. klikPayCode Right 10 0
		// 2. transactionNo Right 18 A
		// 3. currency Right 5 1
		// 4. transactionDate Left 19 C
		// 5. keyId Right 32 E

		String result = "";
		return result;
	}

	@SuppressWarnings("cast")
	// TODO 检查这个算法的漏洞
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
}
