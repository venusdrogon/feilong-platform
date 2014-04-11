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

import com.feilong.netpay.adaptor.PaymentAdaptor;

/**
 * 支付对外的接口.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Feb 2, 2013 8:25:30 PM
 */
public interface PaymentAdaptorManager{

	/**
	 * 通过支付类型 获得PaymentAdaptor.
	 * 
	 * @param paymentType
	 *            支付类型
	 * @return the payment adaptor
	 */
	PaymentAdaptor getPaymentAdaptor(String paymentType);
}
