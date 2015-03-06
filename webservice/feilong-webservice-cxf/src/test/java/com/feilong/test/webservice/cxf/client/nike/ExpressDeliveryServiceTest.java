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
package com.feilong.test.webservice.cxf.client.nike;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ExpressDeliveryServiceTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-28 下午04:20:48
 * @since 1.0
 */
public class ExpressDeliveryServiceTest{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(ExpressDeliveryServiceTest.class);

    /**
     * Test transfer order sign.
     */
    @Test
    public void testTransferOrderSign(){
        StringBuilder orderSignXml = new StringBuilder();
        orderSignXml.append("<tporderSign>");
        //*************************************************************
        orderSignXml.append("<order>");
        orderSignXml.append("<orderid>00001421</orderid>");
        orderSignXml.append("<mailno>AB000001</mailno>");
        orderSignXml.append("<sign_time>2011-04-28 12:12:00</sign_time>");
        orderSignXml.append("<signer>金鑫</signer>");
        orderSignXml.append("<reason_content></reason_content>");
        orderSignXml.append("<info_content>SIGNED</info_content>");
        orderSignXml.append("</order>");
        //*************************************************************
        orderSignXml.append("<order>");
        orderSignXml.append("<orderid>00001388</orderid>");
        orderSignXml.append("<mailno>CG000009</mailno>");
        orderSignXml.append("<sign_time>2011-04-28 12:22:00</sign_time>");
        orderSignXml.append("<signer>金鑫</signer>");
        orderSignXml.append("<reason_content></reason_content>");
        orderSignXml.append("<info_content>SIGNED</info_content>");
        orderSignXml.append("</order>");
        //*************************************************************
        orderSignXml.append("<order>");
        orderSignXml.append("<orderid>00001385</orderid>");
        orderSignXml.append("<mailno>CG080009</mailno>");
        orderSignXml.append("<sign_time>2011-04-28 12:45:00</sign_time>");
        orderSignXml.append("<signer>小丽</signer>");
        orderSignXml.append("<reason_content></reason_content>");
        orderSignXml.append("<info_content>SIGNED</info_content>");
        orderSignXml.append("</order>");
        //*************************************************************
        orderSignXml.append("</tporderSign>");
        //******************************************************************************************
        //		Service service = Service.create(new QName("http://www.nikestore.com.cn/webService", "UserService"));
        //		service.addPort(new QName("http://server/", "UserServicePort"), SOAPBinding.SOAP11HTTP_BINDING, "http://localhost:9000/userService");
        //		
        ExpressDeliveryService expressDeliveryService = null;
        ExpressDeliveryServiceService expressDeliveryServiceService = new ExpressDeliveryServiceService();
        expressDeliveryService = expressDeliveryServiceService.getExpressDeliveryServicePort();
        //service.getPort(ExpressDeliveryService.class);
        String returnInfo = expressDeliveryService.transferOrderSign(orderSignXml.toString(), "5d4db27d92073e85bfb307cf42738308");
        log.info(returnInfo);
    }
}
