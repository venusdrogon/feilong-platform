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
package com.feilong.web;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.web.browser.BrowserUtil;

/**
 * The Class BrowserUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 29, 2013 8:11:13 PM
 */
public class BrowserUtilTest{

    /** The Constant log. */
    private static final Logger log    = LoggerFactory.getLogger(BrowserUtilTest.class);

    /** The iphone. */
    private String              iphone = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_0 like Mac OS X; en-us) AppleWebKit/532.9 (KHTML, like Gecko) Version/4.0.5 Mobile/8A293 Safari/6531.22.7";

    /** The ipad. */
    private String              ipad   = "Mozilla/5.0 (iPad; U; CPU  OS 4_1 like Mac OS X; en-us)AppleWebKit/532.9(KHTML, like Gecko) Version/4.0.5 Mobile/8B117 Safari/6531.22.7";

    /** The chrome. */
    private String              chrome = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1312.57 Safari/537.17";

    /**
     * Test.
     */
    @Test
    public final void test(){
        log.info(BrowserUtil.getIsMobileDevice(iphone) + "");
        log.info(BrowserUtil.getIsMobileDevice(ipad) + "");
        log.info(BrowserUtil.getIsMobileDevice(chrome) + "");
    }
}
