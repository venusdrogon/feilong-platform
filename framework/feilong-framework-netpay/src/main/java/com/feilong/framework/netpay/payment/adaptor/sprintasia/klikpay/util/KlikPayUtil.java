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
package com.feilong.framework.netpay.payment.adaptor.sprintasia.klikpay.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.enumeration.DatePattern;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.klikpay.command.OutputPaymentIPAY;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.klikpay.sprintutil.AuthKey;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.klikpay.sprintutil.BCAKeyGenerator;
import com.feilong.tools.security.oneway.MD5Util;
import com.feilong.tools.xstream.ToXmlConfig;
import com.feilong.tools.xstream.XStreamUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 24, 2014 3:47:53 PM
 */
public class KlikPayUtil{

	private static final Logger	log	= LoggerFactory.getLogger(KlikPayUtil.class);

	/**
	 * Uppercase [to String [Hexa[clearKey]]].
	 * 
	 * @param clearKey
	 *            the clear key
	 * @return the key id
	 */
	public static String getKeyId(String clearKey){
		// char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		// byte[] bytes = clearKey.getBytes();
		// String keyId = "";
		// for (int cntr = 0; cntr < clearKey.length(); cntr++){
		// keyId = keyId + hexArray[(bytes[cntr] & 0xFF) / 16] + hexArray[(bytes[cntr] & 0xFF) % 16];
		// }

		String keyId = AuthKey.KeyId(clearKey);
		log.debug("clearKey:[{}], keyId:[{}]", clearKey, keyId);
		return keyId;
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
	public static String getSignature(
			String klikPayCode,
			Date transactionDate,
			String transactionNo,
			String totalAmount,
			String currency,
			String keyId){
		String firstValue = klikPayCode + transactionNo + currency + keyId;

		String datePattern = "ddMMyyyy";
		String formatTransactionDate = DateUtil.date2String(transactionDate, datePattern);

		// Total amount from redirect forward : 1500500.00
		// Total amount that will be used : 1500500
		// - totalAmount和miscFee 最后两个数字必须是00,注意舍入单位是每1货币。
		String formatTotalAmount = StringUtil.substringWithoutLast(totalAmount, 3);
		Integer secondValue = Integer.parseInt(formatTransactionDate) + Integer.parseInt(formatTotalAmount);

		// *******************************************************
		String firstValueHash = "" + AuthKey.getHash(firstValue);

		String secondValueHash = "" + AuthKey.getHash("" + secondValue);

		// firstValueHash secondValueHash 可能会超过 Integer.MAX_VALUE
		// 2147483647

		// 1538284578(1Hash)
		// 760802109(2Hash)

		// String signature = Math.abs((Integer.parseInt(firstValueHash) + Integer.parseInt(secondValueHash))) + "";
		String signature = "" + new BigDecimal(firstValueHash).add(new BigDecimal(secondValueHash)).abs();
		log.debug("signature value:{}", signature);

		return signature;
	}

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
	public static String getAuthKey(String klikPayCode,Date transactionDate,String transactionNo,String currency,String keyId){

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
			String authKeyString = AuthKey.doAuthKey(md5SecondValue, keyId).toUpperCase();
			return authKeyString;
		}catch (Exception e){
			log.error(e.getClass().getName(), e);
		}
		return null;
	}

	/**
	 * 获得支付确认 返回的xml.
	 * 
	 * @param outputPaymentIPAY
	 *            the output payment ipay
	 * @return the payment confirmation xml
	 */
	public static String getPaymentFlagInvocationOutputXML(OutputPaymentIPAY outputPaymentIPAY){
		if (Validator.isNullOrEmpty(outputPaymentIPAY)){
			throw new IllegalArgumentException("outputPaymentIPAY can't be null/empty!");
		}

		Map<String, Class<?>> aliasMap = new HashMap<String, Class<?>>();
		aliasMap.put("OutputPaymentIPAY", OutputPaymentIPAY.class);

		ToXmlConfig toXmlConfig = new ToXmlConfig();
		toXmlConfig.setAliasMap(aliasMap);

		return XStreamUtil.toXML(outputPaymentIPAY, toXmlConfig);
	}
}
