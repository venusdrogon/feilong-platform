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
package com.feilong.framework.netpay.payment;

import com.feilong.framework.netpay.payment.exception.PaymentAdaptorNotFoundException;

/**
 * 支付工厂
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Feb 2, 2013 8:25:30 PM
 */
public interface PaymentAdaptorFactory{

    /**
     * 通过支付类型 paymentType, 获得{@link PaymentAdaptor},<br>
     * 具体的实现细节,视每个实现类决定, {@link DefaultPaymentAdaptorFactory} 的实现是基于 map隐射决定的,可以定制.
     * 
     * @param paymentType
     *            支付类型,目前暂定为 String类型,将来可能会升级
     * @return {@link PaymentAdaptor}
     * @throws PaymentAdaptorNotFoundException
     *             找不到 {@link PaymentAdaptor}, 将会抛出这个异常
     */
    PaymentAdaptor getPaymentAdaptor(String paymentType) throws PaymentAdaptorNotFoundException;
}
