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
package com.feilong.framework.netpay;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.util.NumberUtil;
import com.feilong.framework.netpay.payment.command.PayRequest;
import com.feilong.framework.netpay.payment.command.PaySoLine;

/**
 * The Class PaymentTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年9月28日 下午5:27:51
 * @since 1.0.8
 */
public class PaymentTest{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(PaymentTest.class);

    /**
     * Construct pay request.
     *
     * @return the pay request
     */
    public static PayRequest construcTestPayRequest(){

        /** The code. */
        String code = DateUtil.date2String(new Date(), DatePattern.TIMESTAMP);
        //code="feilong1111";

        // ******************************************************************
        String return_url = "http://www.esprit.cn/payment/redirect/klikPay";
        return_url = "http://203.128.73.211/p/klikpayback/010002770003?s=cca0ca41b07759089b8a0c35a2b98a361d3016d8";
        String notify_url = "http://203.128.73.211/p/klikpayback/010002770003?s=cca0ca41b07759089b8a0c35a2b98a361d3016d8";

        int per = 1;

        BigDecimal total_fee = BigDecimal.valueOf(60.00f * per);

        PayRequest payRequest = new PayRequest();

        payRequest.setTradeNo(code);
        payRequest.setTotalFee(total_fee);
        payRequest.setBuyerEmail("venusdrogon@163.com");
        payRequest.setBuyerName("jinxin");
        payRequest.setBuyer(888);

        payRequest.setTransferFee(new BigDecimal(10.00f * per));

        List<PaySoLine> paySoLineList = payRequest.getPaySoLineList();

        PaySoLine paySoLine1 = new PaySoLine();
        paySoLine1.setItemName("nike ;s free 5.0");
        paySoLine1.setUnitPrice(new BigDecimal(20 * per));
        paySoLine1.setQuantity(1);
        paySoLine1.setSubTotalPrice(NumberUtil.getMultiplyValue(paySoLine1.getUnitPrice(), paySoLine1.getQuantity()));
        paySoLineList.add(paySoLine1);

        PaySoLine paySoLine2 = new PaySoLine();
        paySoLine2.setItemName("nike free 4.0");
        paySoLine2.setUnitPrice(new BigDecimal(15 * per));
        paySoLine2.setQuantity(2);
        paySoLine2.setSubTotalPrice(NumberUtil.getMultiplyValue(paySoLine2.getUnitPrice(), paySoLine2.getQuantity()));
        paySoLineList.add(paySoLine2);

        payRequest.setCreateDate(new Date());

        payRequest.setReturnUrl(return_url);
        payRequest.setNotifyUrl(notify_url);
        return payRequest;
    }
}
