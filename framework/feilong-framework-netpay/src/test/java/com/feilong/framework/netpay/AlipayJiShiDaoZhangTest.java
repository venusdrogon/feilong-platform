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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.awt.DesktopUtil;
import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.io.CharsetType;
import com.feilong.commons.core.io.IOWriteUtil;
import com.feilong.commons.core.net.HttpMethodType;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.framework.netpay.payment.command.PaymentFormEntity;
import com.feilong.tools.security.oneway.MD5Util;
import com.feilong.tools.velocity.VelocityUtil;

/**
 * The Class AlipayJiShiDaoZhangTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.1.2 2015年5月6日 下午2:52:09
 * @since 1.1.2
 */
public class AlipayJiShiDaoZhangTest{

    /** The Constant log. */
    private static final Logger log          = LoggerFactory.getLogger(AlipayJiShiDaoZhangTest.class);

    /** The key. */
    private final String        key          = "hyth4sb2c1wh27ksz27kgiq2e78oif4q";

    private final String        seller_email = "alipay-test17@alipay.com";

    private final String        partner      = "2088201564895543";

    /**
     * Test.
     */
    @Test
    public final void test(){

        String gateway = "https://mapi.alipay.com/gateway.do";

        Map<String, String> map = new HashMap<String, String>();

        //不同的商城, 基本上值一样
        map.put("_input_charset", CharsetType.UTF8);
        map.put("service", "create_direct_pay_by_user");
        map.put("payment_type", "1");
        map.put("paymethod", "directPay");

        //*************************************************
        //这里的参数 每个订单都一样
        map.put("seller_email", seller_email);
        map.put("partner", partner);

        //**************************************
        //每个商城值每笔订单都不一样
        map.put("out_trade_no", DateUtil.date2String(new Date(), DatePattern.TIMESTAMP));
        map.put("subject", "25484-021");
        map.put("body", "nikegood");
        map.put("total_fee", "300.00");
        map.put("show_url", "http://www.nikestore.com.hk/product/644715-812/detail.htm");

        //        map.put("anti_phishing_key", "");
        map.put("it_b_pay", "15m");
        //        map.put("notify_url", "http://168.25.21.25");
        //        map.put("return_url", "");

        String sign = getSign(map);
        map.put("sign", sign);
        map.put("sign_type", "MD5");

        //String url = URIUtil.getEncodedUrlByValueMap(gateway, map, CharsetType.UTF8);

        if (log.isDebugEnabled()){
            //log.debug(url);
            log.debug(sign);
        }

        Map<String, Object> contextKeyValues = new HashMap<>();

        PaymentFormEntity formEntity = new PaymentFormEntity();
        formEntity.setAction(gateway);
        formEntity.setMethod(HttpMethodType.GET.getMethod());
        formEntity.setHiddenParamMap(map);

        if (log.isDebugEnabled()){
            log.debug(JsonUtil.format(formEntity));
        }

        contextKeyValues.put("paymentFormEntity", formEntity);
        String parseTemplateWithClasspathResourceLoader = VelocityUtil.parseTemplateWithClasspathResourceLoader(
                        "paymentChannel.vm",
                        contextKeyValues);

        String filePath = "d:/123.html";
        IOWriteUtil.write(filePath, parseTemplateWithClasspathResourceLoader, CharsetType.UTF8);
        DesktopUtil.open(filePath);

    }

    /**
     * 获得 sign.
     *
     * @param map
     *            the map
     * @return the sign
     * @since 1.1.2
     */
    private String getSign(Map<String, String> map){

        //key

        //map --->>有序 
        TreeMap<String, String> tp = new TreeMap<>(map);

        //keyset  sort

        //a-z  

        //&

        //StringBuffer  StringBuilder

        if (log.isDebugEnabled()){
            log.debug(JsonUtil.format(tp));
        }

        StringBuilder stringBuilder = new StringBuilder(tp.size() * 5);

        for (Map.Entry<String, String> entry : tp.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            stringBuilder.append(key).append("=").append(value).append("&");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        String origin = stringBuilder.toString() + key;

        if (log.isDebugEnabled()){
            log.debug(origin);
        }

        return MD5Util.encode(origin);
    }
}
