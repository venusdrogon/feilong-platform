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
package com.feilong.framework.netpay.payment.sprintasia.klikpay;

import java.util.Date;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.framework.netpay.payment.BasePaymentTest;
import com.feilong.framework.netpay.payment.PaymentAdaptor;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.klikpay.sprintutil.AuthKey;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.klikpay.sprintutil.BCAKeyGenerator;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.klikpay.util.KlikPayUtil;

/**
 * The Class KlikPayAdaptorTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 22, 2014 4:15:13 PM
 */
public class SprintAsiaKlikPayAdaptorTest extends BasePaymentTest{

    /** The payment adaptor. */
    @Autowired
    @Qualifier("sprintAsiaKlikPayAdaptor")
    private PaymentAdaptor paymentAdaptor;

    /**
     * Creates the payment form.
     */
    @Test
    public final void createPaymentForm(){
        Map<String, String> specialSignMap = null;
        createPaymentForm(paymentAdaptor, specialSignMap);
    }

    /**
     * Test do auth key.
     * 
     * @throws Exception
     *             the exception
     */
    @Test
    public final void testDoAuthKey() throws Exception{
        // Input  F7468B69D12BB6CE76D6206419A6AC28
        // Encrypt by KeyId  12345678901234561234567890123456
        // authKey will become  BF81501C562D6FEA2FCB905D392D5851

        String input = "F7468B69D12BB6CE76D6206419A6AC28";
        String encryptByKeyId = "12345678901234561234567890123456";

        Assert.assertEquals("BF81501C562D6FEA2FCB905D392D5851", AuthKey.doAuthKey(input, encryptByKeyId));
    }

    /**
     * Test get sign.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public final void testGetSign() throws Exception{
        // "miscFee": "0.00",
        // "descp": "",
        // "payType": "01",
        // "signature": "1995880609",

        String klikPayCode = "03BELAV220";

        String dateString = "23/04/2014 18:20:20";
        Date transactionDate = DateUtil.string2Date(dateString, DatePattern.ddMMyyyyHHmmss);
        String transactionNo = "010003240001";
        String totalAmount = "9000009.00";
        String currency = "IDR";

        String keyId = KlikPayUtil.getKeyId("ClearKeyDev2Blj2");
        String sign = KlikPayUtil.getSignature(klikPayCode, transactionDate, transactionNo, totalAmount, currency, keyId);
        String sign1 = BCAKeyGenerator.signature(klikPayCode, dateString, transactionNo, totalAmount, currency, keyId);
        log.info("{},{}", sign, sign1);

        Assert.assertEquals(sign, sign1);
    }

}
