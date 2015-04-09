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

import java.util.Date;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.feilong.commons.core.util.Validator;
import com.feilong.tools.mail.entity.MailSenderConfig;

/**
 * 邮件发送器.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.1.1 2015年4月9日 上午12:23:03
 * @see javax.mail.Message
 * @see javax.mail.Session
 * @see javax.mail.Transport#send(javax.mail.Message)
 * @see "org.springframework.mail.MailSender"
 * @see "org.springframework.mail.javamail.JavaMailSenderImpl"
 * @since 1.1.1
 */
public abstract class AbstractMailSender implements MailSender{

    /**
     * 设置 default command map.<br>
     * <p>
     * 解决 bug javax.activation.UnsupportedDataTypeException: no object DCH for MIME type multipart/related;
     * </p>
     */
    protected void setDefaultCommandMap(){

        MailcapCommandMap mailcapCommandMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        mailcapCommandMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
        mailcapCommandMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
        mailcapCommandMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
        mailcapCommandMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
        mailcapCommandMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
        CommandMap.setDefaultCommandMap(mailcapCommandMap);
    }

    /**
     * 设置 header 信息.
     *
     * @param message
     *            the message
     * @param mailSenderConfig
     *            the headers
     * @throws MessagingException
     *             the messaging exception
     * @see javax.mail.Part#addHeader(String, String)
     * @see javax.mail.Part#setHeader(String, String)
     */
    protected void setHeaders(Message message,MailSenderConfig mailSenderConfig) throws MessagingException{
        // ***************************************************************************
        // 邮件的优先级
        Priority priority = mailSenderConfig.getPriority();
        if (null != priority){
            message.addHeader(MailHeader.X_PRIORITY, priority.getLevelValue());
        }
        // ***************************************************************************
        // 是否需要回执
        if (mailSenderConfig.getIsNeedReturnReceipt()){
            message.setHeader(MailHeader.DISPOSITION_NOTIFICATION_TO, "1");
        }
        // 邮件客户端
        message.setHeader(MailHeader.X_MAILER, MailHeader.X_MAILER_VALUE);

        // 设置邮件消息发送的时间
        message.setSentDate(new Date());
    }

    /**
     * 设置邮件接受人群<br>
     * 支持 to cc bcc.
     *
     * @param message
     *            the message
     * @param mailSenderConfig
     *            the new recipients
     * @throws AddressException
     *             the address exception
     * @throws MessagingException
     *             the messaging exception
     */
    protected void setRecipients(Message message,MailSenderConfig mailSenderConfig) throws AddressException,MessagingException{
        // *********************to******************************************************
        // 创建邮件的接收者地址，并设置到邮件消息中
        // Message.RecipientType.TO属性表示接收者的类型为TO
        setRecipients(message, Message.RecipientType.TO, mailSenderConfig.getTos());
        // ************************cc 抄送***************************************************
        setRecipients(message, Message.RecipientType.CC, mailSenderConfig.getCcs());
        // **********************bcc 密送*****************************************************
        setRecipients(message, Message.RecipientType.BCC, mailSenderConfig.getBccs());
    }

    /**
     * 设置 邮件接收人.
     *
     * @param message
     *            the message
     * @param recipientType
     *            the recipient type
     * @param addresseArray
     *            the addresse array
     * @throws AddressException
     *             the address exception
     * @throws MessagingException
     *             the messaging exception
     */
    protected void setRecipients(Message message,RecipientType recipientType,String[] addresseArray) throws AddressException,
                    MessagingException{
        if (Validator.isNotNullOrEmpty(addresseArray)){
            final int length = addresseArray.length;
            Address[] addresses = new InternetAddress[length];
            for (int i = 0; i < length; ++i){
                addresses[i] = new InternetAddress(addresseArray[i]);
            }
            message.setRecipients(recipientType, addresses);
        }
    }
}