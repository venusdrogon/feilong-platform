/*
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
package com.feilong.framework.netpay.payment.alipay.pconline;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.feilong.framework.netpay.payment.BasePaymentTest;
import com.feilong.framework.netpay.payment.PaymentAdaptor;

/**
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.1.2 2015年4月22日 下午5:37:51
 * @since 1.1.2
 */
public class AlipayOnlineForexPayAdaptorTest extends BasePaymentTest{

    private static final Logger log = LoggerFactory.getLogger(AlipayOnlineForexPayAdaptorTest.class);

    /** The payment adaptor. */
    @Autowired
    @Qualifier("alipayForexPayAdaptor")
    private PaymentAdaptor      paymentAdaptor;

    /**
     * Creates the payment form.
     */
    @Test
    public final void createPaymentForm(){
        Map<String, String> specialSignMap = new HashMap<String, String>();
        createPaymentForm(paymentAdaptor, specialSignMap);
    }
}
