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

import com.feilong.framework.netpay.advance.exception.PaymentAdvanceAdaptorNotFoundException;

/**
 * 高级支付adaptor适配.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.6 2014年5月9日 上午1:18:57
 * @since 1.0.6
 */
public interface PaymentAdvanceAdaptorFactory{

	/**
	 * 通过支付类型 获得PaymentAdvanceAdaptor.
	 *
	 * @param paymentType
	 *            支付类型
	 * @return the payment adaptor
	 * @throws PaymentAdvanceAdaptorNotFoundException
	 *             找不到 {@link PaymentAdvanceAdaptor},将会抛出这个异常
	 */
	PaymentAdvanceAdaptor getPaymentAdvanceAdaptor(String paymentType) throws PaymentAdvanceAdaptorNotFoundException;
}
