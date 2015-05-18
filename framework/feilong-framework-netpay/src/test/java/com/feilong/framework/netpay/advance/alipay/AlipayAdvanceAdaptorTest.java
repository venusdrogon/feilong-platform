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
package com.feilong.framework.netpay.advance.alipay;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.feilong.framework.netpay.advance.BaseAdvanceAdaptorTest;
import com.feilong.framework.netpay.advance.PaymentAdvanceAdaptor;
import com.feilong.framework.netpay.advance.command.QueryRequest;
import com.feilong.framework.netpay.advance.command.QueryResult;
import com.feilong.framework.netpay.advance.command.TradeRole;
import com.feilong.framework.netpay.advance.exception.TradeCloseException;

/**
 * The Class AlipayAdvanceAdaptorTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.1.2 2015年5月8日 下午2:39:47
 * @since 1.1.2
 */
public class AlipayAdvanceAdaptorTest extends BaseAdvanceAdaptorTest{

    /** The Constant log. */
    private static final Logger   log = LoggerFactory.getLogger(AlipayAdvanceAdaptorTest.class);

    /** The payment adaptor. */
    @Autowired
    @Qualifier("alipayAdvanceAdaptor")
    private PaymentAdvanceAdaptor paymentAdvanceAdaptor;

    /**
     * Test method for
     * {@link com.feilong.framework.netpay.advance.adaptor.alipay.AlipayAdvanceAdaptor#closeTrade(java.lang.String, com.feilong.framework.netpay.advance.command.TradeRole)}
     *
     * @throws TradeCloseException
     *             the trade close exception
     */
    @Test
    public final void testCloseTrade() throws TradeCloseException{
        String orderNo = "5251548784";
        TradeRole tradeRole = TradeRole.SELLER;
        paymentAdvanceAdaptor.closeTrade(orderNo, tradeRole);
    }

    /**
     * Test get query result.
     */
    @Test
    public final void testGetQueryResult(){
        String tradeNo = "5251548784";
        QueryRequest queryRequest = new QueryRequest();
        queryRequest.setTradeNo(tradeNo);

        QueryResult queryResult = paymentAdvanceAdaptor.getQueryResult(queryRequest);

    }
}