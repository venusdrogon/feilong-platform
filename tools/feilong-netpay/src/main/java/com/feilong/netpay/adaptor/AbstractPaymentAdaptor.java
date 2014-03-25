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
package com.feilong.netpay.adaptor;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.JsonFormatUtil;
import com.feilong.netpay.command.PaymentFormEntity;

/**
 * 所有 Adaptor 的 基础类, 包括了 通用的验证等方法
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 20, 2013 12:29:49 PM
 */
public abstract class AbstractPaymentAdaptor implements PaymentAdaptor{

	private static final Logger	log	= LoggerFactory.getLogger(AbstractPaymentAdaptor.class);

	/**
	 * 获得PaymentFormEntity
	 * 
	 * @param actionGateway
	 *            提交网关地址
	 * @param method
	 *            method 是get 还是post
	 * @param hiddenParamMap
	 *            参数
	 * @return PaymentFormEntity
	 */
	protected PaymentFormEntity getPaymentFormEntity(String actionGateway,String method,Map<String, String> hiddenParamMap){
		PaymentFormEntity paymentFormEntity = new PaymentFormEntity();
		paymentFormEntity.setAction(actionGateway);
		paymentFormEntity.setMethod(method);
		paymentFormEntity.setHiddenParamMap(hiddenParamMap);

		if (log.isDebugEnabled()){
			log.debug(JsonFormatUtil.format(paymentFormEntity));
		}
		return paymentFormEntity;
	}

}
