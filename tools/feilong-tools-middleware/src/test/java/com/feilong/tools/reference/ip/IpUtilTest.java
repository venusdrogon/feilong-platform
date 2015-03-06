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
package com.feilong.tools.reference.ip;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.entity.JoinStringEntity;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.CollectionsUtil;

/**
 * The Class IpUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-5-22 16:35:43
 */
public class IpUtilTest{

    /** The Constant log. */
    private static final Logger log  = LoggerFactory.getLogger(IpUtilTest.class);

    /** <code>{@value}</code>. */
    private static String       name = "103.10.129.";

    /**
     * Name.
     */
    @Test
    public void name(){
        List<String> list = new ArrayList<String>();
        for (int i = 0; i <= 255; ++i){
            list.add(name + i);
        }

        JoinStringEntity joinStringEntity = new JoinStringEntity(";");
        String ipgWhiteListIPs = CollectionsUtil.toString(list, joinStringEntity);
        String[] array = ipgWhiteListIPs.split(";");

        if (log.isDebugEnabled()){
            log.debug(JsonUtil.format(array));
        }

    }

    /**
     * Test ip to address.
     */
    @Test
    public void testIpToAddress(){
        log.info(IpUtil.ipToAddress("58.25.165.250", "all"));
    }
}
