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

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import com.feilong.tools.mail.entity.MailSenderConfig;

/**
 * {@link javax.mail.Session}
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.9 2015年3月26日 下午3:29:38
 * @since 1.0.9
 */
class SessionFactory{

    /**
     * 根据邮件会话属性和密码验证器构造一个发送邮件的session.
     * 
     * @param mailSenderConfig
     *            mailSenderInfo
     * @return Session
     */
    public static Session createSession(MailSenderConfig mailSenderConfig){
        boolean isValidate = mailSenderConfig.getIsValidate();

        Properties properties = new Properties();
        properties.put("mail.smtp.host", mailSenderConfig.getMailServerHost());
        properties.put("mail.smtp.port", mailSenderConfig.getMailServerPort());
        properties.put("mail.smtp.auth", isValidate ? "true" : "false");
        // 判断是否需要身份认证
        Authenticator authenticator = null;
        // 如果需要身份认证，则创建一个密码验证器
        if (isValidate){
            final String userName = mailSenderConfig.getUserName();
            final String password = mailSenderConfig.getPassword();
            authenticator = new Authenticator(){

                @Override
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(userName, password);
                }
            };
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session session = Session.getDefaultInstance(properties, authenticator);
        session.setDebug(mailSenderConfig.getIsDebug());
        return session;
    }
}
