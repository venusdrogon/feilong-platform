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
package com.feilong.framework.netpay.payment.adaptor.sprintasia.klikbca.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.Validator;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.klikbca.command.OutputDetailPayment;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.klikbca.command.OutputListTransactionPGW;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.klikbca.command.OutputPaymentPGW;
import com.feilong.tools.xstream.ToXmlConfig;
import com.feilong.tools.xstream.XStreamUtil;

/**
 * The Class KlikBCAUtil.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 25, 2014 4:36:03 PM
 */
public class KlikBCAUtil{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(KlikBCAUtil.class);

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
	public static String getPaymentInquiryXML(String userID,String additionalData,List<OutputDetailPayment> outputDetailPayments){

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
	public static String getPaymentConfirmationXML(OutputPaymentPGW outputPaymentPGW){

		if (Validator.isNullOrEmpty(outputPaymentPGW)){
			throw new IllegalArgumentException("outputPaymentPGW can't be null/empty!");
		}

		Map<String, Class<?>> aliasMap = new HashMap<String, Class<?>>();
		aliasMap.put("OutputPaymentPGW", OutputPaymentPGW.class);

		ToXmlConfig toXmlConfig = new ToXmlConfig();
		toXmlConfig.setAliasMap(aliasMap);

		return XStreamUtil.toXML(outputPaymentPGW, toXmlConfig);
	}
}
