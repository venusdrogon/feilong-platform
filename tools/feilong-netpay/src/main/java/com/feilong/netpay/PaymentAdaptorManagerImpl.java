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
	/** 商城支持的支付, 单独配置 ,避免其他项目引用 可能带来的错误. */
	private Map<String, PaymentAdaptor>	paymentAdaptorMap;

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.PaymentAdaptorManager#getPaymentAdaptor(java.lang.String)
	 */
	public PaymentAdaptor getPaymentAdaptor(String paymentType){
		if (null == paymentAdaptorMap || paymentAdaptorMap.isEmpty()){
			log.error("paymentAdaptorMap is null/empty");
			return null;
		}

		// 全部 配置,测试环境不可以去付款

		// if (Constants.pay_isCanGoToPay){
		if (!paymentAdaptorMap.containsKey(paymentType)){
			log.error("paymentAdaptorMap not containsKey paymentType:[{}]", paymentType);
			return null;
		}
		PaymentAdaptor paymentAdaptor = paymentAdaptorMap.get(paymentType);
		return paymentAdaptor;
		// }else{
		// log.warn("Constants.pay_isCanGoToPay:{}", Constants.pay_isCanGoToPay);
		// }

		// return null;
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