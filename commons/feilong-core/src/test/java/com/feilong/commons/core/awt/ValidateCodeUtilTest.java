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
package com.feilong.commons.core.awt;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-14 下午01:46:59
 */
public class ValidateCodeUtilTest{

    private static final Logger log = LoggerFactory.getLogger(ValidateCodeUtilTest.class);

    /**
     * {@link com.feilong.commons.core.awt.ValidateCodeUtil#generateValidateCode(int)} 的测试方法。
     */
    @Test
    public final void testGenerateValidateCode(){
        log.info(ValidateCodeUtil.generateValidateCode(4));
    }
}
