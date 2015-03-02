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
package com.feilong.framework.netpay.advance;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.Validator;
import com.feilong.framework.netpay.advance.exception.PaymentAdvanceAdaptorNotFoundException;

/**
 * PaymentAdvanceAdaptorManagerImpl.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.6 2014年5月9日 上午1:19:50
 * @since 1.0.6
 */
// 不走数据库
// @Transactional
// 不以自动扫描的形式存在
// @Service("paymentAdaptorManager")
public class DefaultAdvanceAdaptorManagerFactory implements PaymentAdvanceAdaptorFactory{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger					log	= LoggerFactory.getLogger(DefaultAdvanceAdaptorManagerFactory.class);

	// @Value("#{paymentAdvanceAdaptorManager}")
	/** 商城支持的支付,单独配置 ,避免其他项目引用可能带来的错误. */
	private Map<String, PaymentAdvanceAdaptor>	paymentAdvanceAdaptorMap;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.framework.netpay.advance.PaymentAdvanceAdaptorFactory#getPaymentAdvanceAdaptor(java.lang.String)
	 */
	@Override
	public PaymentAdvanceAdaptor getPaymentAdvanceAdaptor(String paymentType) throws PaymentAdvanceAdaptorNotFoundException{
		if (Validator.isNullOrEmpty(paymentAdvanceAdaptorMap)){
			throw new IllegalArgumentException("paymentAdvanceAdaptorMap can't be null/empty!");
		}
		if (Validator.isNullOrEmpty(paymentType)){
			throw new IllegalArgumentException("paymentType can't be null/empty!");
		}
		if (!paymentAdvanceAdaptorMap.containsKey(paymentType)){
			throw new PaymentAdvanceAdaptorNotFoundException(
							"paymentAdvanceAdaptorMap not containsKey paymentType:[{}],paymentAdvanceAdaptorMap info:{}",
							paymentType,
							paymentAdvanceAdaptorMap);
		}
		PaymentAdvanceAdaptor paymentAdvanceAdaptor = paymentAdvanceAdaptorMap.get(paymentType);
		return paymentAdvanceAdaptor;
	}

	/**
	 * 商城支持的支付,单独配置 ,避免其他项目引用可能带来的错误.
	 * 
	 * @param paymentAdvanceAdaptorMap
	 *            the paymentAdvanceAdaptorMap to set
	 */
	public void setPaymentAdvanceAdaptorMap(Map<String, PaymentAdvanceAdaptor> paymentAdvanceAdaptorMap){
		this.paymentAdvanceAdaptorMap = paymentAdvanceAdaptorMap;
	}
}