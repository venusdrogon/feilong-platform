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

import java.util.Map;

import com.feilong.commons.core.util.Validator;
import com.feilong.framework.netpay.payment.exception.PaymentAdaptorNotFoundException;

/**
 * 支付对外的接口 {@link DefaultPaymentAdaptorFactory} 的实现是基于 map隐射决定的,可以定制.<br>
 * 当然如有有需要, 也可以做 DatasourcePaymentAdaptorFactory诸如此类配置在数据中的PaymentAdaptor
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Feb 2, 2013 8:25:27 PM
 * 
 * @see com.feilong.framework.netpay.payment.PaymentAdaptorFactory
 */
// 不走数据库
// 不需要 @Transactional
// 不以自动扫描的形式存在
// @Service("paymentAdaptorManager")
public class DefaultPaymentAdaptorFactory implements PaymentAdaptorFactory{

    // @Value("#{paymentAdaptorMap}")
    /** 商城支持的支付,单独配置 ,避免其他项目引用可能带来的错误.. */
    private Map<String, PaymentAdaptor> paymentAdaptorMap;

    /** */
    //TODO 是否可以去支付 做成配置的
    //private Boolean                     eswitch;

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.framework.netpay.payment.PaymentAdaptorFactory#getPaymentAdaptor(java.lang.String)
     */
    @Override
    public PaymentAdaptor getPaymentAdaptor(String paymentType) throws PaymentAdaptorNotFoundException{
        if (Validator.isNullOrEmpty(paymentAdaptorMap)){
            throw new IllegalArgumentException("paymentAdaptorMap can't be null/empty!");
        }

        if (!paymentAdaptorMap.containsKey(paymentType)){
            throw new PaymentAdaptorNotFoundException(
                            "paymentAdaptorMap not containsKey paymentType:[{}],paymentAdaptorMap info:{}",
                            paymentType,
                            paymentAdaptorMap);
        }
        PaymentAdaptor paymentAdaptor = paymentAdaptorMap.get(paymentType);
        return paymentAdaptor;
    }

    /**
     * Sets the 商城支持的支付,单独配置 ,避免其他项目引用可能带来的错误.
     * 
     * @param paymentAdaptorMap
     *            the paymentAdaptorMap to set
     */
    public void setPaymentAdaptorMap(Map<String, PaymentAdaptor> paymentAdaptorMap){
        this.paymentAdaptorMap = paymentAdaptorMap;
    }
}