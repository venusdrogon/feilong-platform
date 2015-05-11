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
package com.feilong.tools.xstream;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.test.User;
import com.thoughtworks.xstream.XStream;

/**
 * The Class XStreamTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Oct 11, 2013 12:57:10 AM
 */
public class XStreamUtilTest{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(XStreamUtilTest.class);

    /**
     * Name.
     */
    @Test
    public void name(){
        User user = new User(1L);

        XStream xStream = new XStream();
        xStream.alias("user", User.class);
        log.info(xStream.toXML(user));
    }

    /**
     * Test to xml.
     */
    @Test
    public void testToXML(){
        User user = new User(1L);
        ToXmlConfig toXmlConfig = new ToXmlConfig();
        toXmlConfig.getAliasMap().put("user", User.class);
        log.info(XStreamUtil.toXML(user, toXmlConfig));
        log.info(XStreamUtil.toXML(user, null));
    }

    /**
     * Test to xm l2.
     */
    @Test
    public void testToXML2(){
        User user = new User(1L);
        ToXmlConfig toXmlConfig = new ToXmlConfig();
        toXmlConfig.getAliasMap().put("user", User.class);
        toXmlConfig.getImplicitCollectionMap().put("userAddresseList", User.class);
        log.info(XStreamUtil.toXML(user, toXmlConfig));
    }

    /**
     * Test to xm l3.
     */
    @Test
    public void testToXML3(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("out_trade_no", "112122212");
        map.put("total_fee", "125.00");

        //call_back_url=alipay 的return_url
        map.put("call_back_url", "");
        map.put("notify_url", "");
        log.info(XStreamUtil.toXML(map, null));
    }
}
