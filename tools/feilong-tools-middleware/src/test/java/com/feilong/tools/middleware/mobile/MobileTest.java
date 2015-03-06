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
package com.feilong.tools.middleware.mobile;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 手机相关测试.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-3-1 下午10:43:20
 */
public class MobileTest{

    /** The Constant log. */
    private static final Logger log          = LoggerFactory.getLogger(MobileTest.class);

    /** The mobile number. */
    String                      mobileNumber = "15001841318";

    /**
     * {@link com.feilong.tools.middleware.mobile.MobileUtil#getMobileNumberHided(java.lang.String)} 的测试方法。
     */
    @Test
    public void testGetMobileNumberHided(){
        assertEquals("150****1318", MobileUtil.getMobileNumberHided(mobileNumber));

        log.info(MobileUtil.getMobileNumberHided(mobileNumber, 15));
        // assertEquals(mobileNumber, FeiLongMobile.getMobileNumberHided(mobileNumber,4));
    }

    // @Test
    /**
     * Test get mobile number number segment.
     */
    public void testGetMobileNumberNumberSegment(){
        assertEquals("150", MobileUtil.getMobileNumberNumberSegment(mobileNumber));
    }
}
