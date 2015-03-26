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
package com.feilong.tools.mail;

/**
 * The Class MailHeader.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.9 2015年1月5日 上午11:27:33
 * @since 1.0.9
 * @see javax.mail.Part#setHeader(String, String)
 */
public final class MailHeader{

    /** 是否需要回执. */
    public static final String DISPOSITION_NOTIFICATION_TO = "Disposition-Notification-To";

    /** 邮件的优先级. */
    public static final String X_PRIORITY                  = "X-Priority";

    /** 邮件客户端 版本. */
    public static final String X_MAILER                    = "X-mailer";

    /** 邮件客户端 版本. */
    public static final String X_MAILER_VALUE              = "FeiLong MailSender Api 1.0.9";

    /** Don't let anyone instantiate this class. */
    private MailHeader(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }
}
