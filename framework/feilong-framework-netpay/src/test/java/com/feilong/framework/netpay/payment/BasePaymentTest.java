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

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.feilong.commons.core.awt.DesktopUtil;
import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.io.CharsetType;
import com.feilong.commons.core.io.IOWriteUtil;
import com.feilong.commons.core.lang.reflect.FieldUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.framework.netpay.PaymentTest;
import com.feilong.framework.netpay.payment.command.PayRequest;
import com.feilong.framework.netpay.payment.command.PaymentFormEntity;
import com.feilong.tools.velocity.VelocityUtil;

/**
 * The Class BasePaymentTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 22, 2014 4:31:38 PM
 */
@ContextConfiguration(locations = { "classpath*:spring/payment/payment/spring-payment-adaptor.xml" })
public class BasePaymentTest extends AbstractJUnit4SpringContextTests{

    /** The Constant log. */
    protected static final Logger log                 = LoggerFactory.getLogger(BasePaymentTest.class);

    /** The template in class path. */
    private String                templateInClassPath = "paymentChannel.vm";

    /** The encode. */
    private String                encode_file         = CharsetType.UTF8;

    /** The open file. */
    private boolean               openFile            = true;

    /**
     * 通用的测试方法(自动取到paymentAdaptor 的 Qualifier value).
     * 
     * @param paymentAdaptor
     *            the payment adaptor
     * @param specialSignMap
     *            the special sign map
     */
    protected void createPaymentForm(PaymentAdaptor paymentAdaptor,Map<String, String> specialSignMap){
        PayRequest payRequest = PaymentTest.construcTestPayRequest();

        PaymentFormEntity paymentFormEntity = paymentAdaptor.getPaymentFormEntity(payRequest, specialSignMap);

        if (null == paymentFormEntity){
            throw new IllegalArgumentException("paymentFormEntity can't be null/empty!,do you implements complete?");
        }

        if (openFile){
            log.info(JsonUtil.format(paymentFormEntity));

            String fullEncodedUrl = paymentFormEntity.getFullEncodedUrl();
            log.info(fullEncodedUrl);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("paymentFormEntity", paymentFormEntity);

            String method = paymentFormEntity.getMethod();

            // if (method.toLowerCase().equals("get")){
            // DesktopUtil.browse(fullEncodedUrl);
            // }else{

            String filePath = getFilePath();

            String html = VelocityUtil.parseTemplateWithClasspathResourceLoader(templateInClassPath, map);
            log.info(html);

            writeAndOpen(filePath, html);
            // }
        }
    }

    /**
     * Write and open.
     *
     * @param filePath
     *            the file path
     * @param html
     *            the html
     */
    private void writeAndOpen(String filePath,String html){
        IOWriteUtil.write(filePath, html, encode_file);
        DesktopUtil.open(filePath);

    }

    /**
     * 获得 file path.
     *
     * @return the file path
     */
    private String getFilePath(){
        Field declaredField = FieldUtil.getDeclaredField(this.getClass(), "paymentAdaptor");
        Qualifier qualifier = declaredField.getAnnotation(Qualifier.class);
        String paymentAdaptorName = qualifier.value();
        String fileName = paymentAdaptorName + DateUtil.date2String(new Date(), DatePattern.TIMESTAMP);

        String folderPath = SystemUtils.USER_HOME + "\\feilong\\payment\\" + paymentAdaptorName;
        String filePath = folderPath + "\\" + fileName + ".html";
        return filePath;
    }
}