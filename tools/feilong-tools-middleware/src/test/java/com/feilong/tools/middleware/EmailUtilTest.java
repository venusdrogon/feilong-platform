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

import com.feilong.tools.middleware.email.EmailType;
import com.feilong.tools.middleware.email.EmailUtil;

/**
 * The Class EmailUtilTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-18 下午11:51:03
 */
public class EmailUtilTest{

    /** The Constant log. */
    private static final Logger log   = LoggerFactory.getLogger(EmailUtilTest.class);

    /** The email. */
    String                      email = "venusdrogon@163.com";

    /**
     * {@link com.feilong.tools.middleware.email.EmailUtil#getEmailLoginHrefByEmail(java.lang.String)} 的测试方法。
     */
    // @Test
    // public void testGetEmailLoginHrefByEmail(){
    // log.info(EmailUtil.getEmailLoginHrefByEmail(email));
    // }

    /**
     * {@link com.feilong.tools.middleware.email.EmailUtil#getEmailChineseName(java.lang.String)} 的测试方法。
     */
    @Test
    public void testGetEmailChineseName(){
        log.info(EmailUtil.getEmailChineseName(email));
    }

    /**
     * {@link com.feilong.tools.middleware.email.EmailUtil#getEmailType(java.lang.String)} 的测试方法。
     */
    @Test
    public void testGetFeiLongEmailType(){
        EmailType emailType = EmailUtil.getEmailType(email);
        log.info(emailType.getChineseName());
        emailType.setChineseName("hahahaha");
        log.info(emailType.toString());
        log.info(emailType.getChineseName());
    }

    /**
     * {@link com.feilong.tools.middleware.email.EmailUtil#getEmailPostfix(java.lang.String)} 的测试方法。
     */
    @Test
    public void testGetEmailPostfix(){
        log.info(EmailUtil.getEmailPostfix(email));
    }
}
