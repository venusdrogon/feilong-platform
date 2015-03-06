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
package com.feilong.tools.middleware;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试 地球.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-2-6 下午11:21:21
 */
public class EarthUtilTest{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(EarthUtilTest.class);

    /**
     * {@link com.feilong.tools.middleware.EarthUtil#getDistance(double, double, double, double)} 的测试方法。
     */
    @Test
    public void testGetDistance(){
        log.info("" + EarthUtil.getDistance(120, 36, 121, 36));
    }
}
