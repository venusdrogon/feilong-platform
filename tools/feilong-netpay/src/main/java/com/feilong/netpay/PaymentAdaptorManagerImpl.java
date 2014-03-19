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
package com.feilong.netpay;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.netpay.adaptor.PaymentAdaptor;

/**
 * 支付对外的接口.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Feb 2, 2013 8:25:27 PM
 */
// 不走数据库
// @Transactional
// 不以自动扫描的形式存在
// @Service("paymentAdaptorManager")
public class PaymentAdaptorManagerImpl implements PaymentAdaptorManager{

	/** The Constant log. */
	private static final Logger			log	= LoggerFactory.getLogger(PaymentAdaptorManagerImpl.class);

	// @Value("#{paymentAdaptorMap}")
	//
	/** 商城支持的支付, 单独配置 ,避免其他项目引用 可能带来的错误. */
	private Map<String, PaymentAdaptor>	paymentAdaptorMap;

	/*
	 * (non-Javadoc)
	 * @see com.jumbo.brandstore.payment.PaymentAdaptorManager#getPaymentAdaptor(java.lang.String)
	 */
	public PaymentAdaptor getPaymentAdaptor(String paymentType){
		if (null == paymentAdaptorMap || paymentAdaptorMap.isEmpty()){
			log.error("paymentAdaptorMap is null/empty");
			return null;
		}

		/**
		 * 全部 配置,测试环境不可以去付款
		 */
		//if (Constants.pay_isCanGoToPay){
			if (!paymentAdaptorMap.containsKey(paymentType)){
				log.debug("paymentType:{},don't use PaymentAdaptor", paymentType);
				return null;
			}else{
				PaymentAdaptor paymentAdaptor = paymentAdaptorMap.get(paymentType);
				return paymentAdaptor;
			}
//		}else{
//			log.warn("Constants.pay_isCanGoToPay:{}", Constants.pay_isCanGoToPay);
//		}

		//return null;
	}

	/**
	 * Sets the 商城支持的支付, 单独配置 ,避免其他项目引用 可能带来的错误.
	 * 
	 * @param paymentAdaptorMap
	 *            the paymentAdaptorMap to set
	 */
	public void setPaymentAdaptorMap(Map<String, PaymentAdaptor> paymentAdaptorMap){
		this.paymentAdaptorMap = paymentAdaptorMap;
	}
}
