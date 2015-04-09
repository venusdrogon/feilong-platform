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

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.CharsetType;
import com.feilong.commons.core.io.FileUtil;
import com.feilong.commons.core.io.MimeType;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.tools.mail.entity.MailSenderConfig;

/**
 * 邮件发送器.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 金鑫 2010-1-23 下午04:22:24
 * @version 金鑫 2011-12-24 01:54
 * @version 1.0 Dec 7, 2013 9:04:22 PM
 * @version 1.0.8 2014-11-27 14:15
 * @version 1.0.9 2015年3月26日 下午4:23:12 将message对象修改为参数传递
 * @see javax.mail.Message
 * @see javax.mail.Session
 * @see javax.mail.Transport#send(Message)
 * @see "org.springframework.mail.MailSender"
 * @see "org.springframework.mail.javamail.JavaMailSenderImpl"
 * @since 1.0.0
 */
public final class DefaultMailSender extends AbstractMailSender{

    /** The Constant log. */
    private static final Logger log              = LoggerFactory.getLogger(DefaultMailSender.class);

    /** contentId前缀. */
    public static final String  PREFIX_CONTENTID = "image";

    /** The Constant CHARSET_PERSONAL. */
    private static final String CHARSET_PERSONAL = CharsetType.GB2312;

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.mail.MailSender#sendMail(com.feilong.tools.mail.entity.MailSenderConfig)
     */
    @Override
    public void sendMail(MailSenderConfig mailSenderConfig) throws MailSenderException{
        if (Validator.isNullOrEmpty(mailSenderConfig)){
            throw new IllegalArgumentException("mailSenderConfig can't be null/empty!");
        }
        if (Validator.isNullOrEmpty(mailSenderConfig.getTos())){
            throw new IllegalArgumentException("mailSenderConfig tos can't be null/empty!");
        }
        if (Validator.isNullOrEmpty(mailSenderConfig.getFromAddress())){
            throw new IllegalArgumentException("mailSenderConfig fromAddress can't be null/empty!");
        }

        if (log.isDebugEnabled()){
            log.debug("mailSenderConfig:{}", JsonUtil.format(mailSenderConfig, new String[] { "attachList" }));
        }

        // *****************************************************************************************
        // 根据session创建一个邮件消息

        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session session = SessionFactory.createSession(mailSenderConfig);
        // 根据session创建一个邮件消息
        Message message = new MimeMessage(session);

        try{
            setMessageAttribute(message, mailSenderConfig);

            setBody(message, mailSenderConfig);

            super.setDefaultCommandMap();

            // 发送邮件
            Transport.send(message);
        }catch (UnsupportedEncodingException | MessagingException e){
            log.error("", e);
            throw new MailSenderException(e);
        }
    }

    /**
     * setBody.
     *
     * @param message
     *            the message
     * @param mailSenderConfig
     *            the body
     * @throws MessagingException
     *             the messaging exception
     */
    private void setBody(Message message,MailSenderConfig mailSenderConfig) throws MessagingException{
        // if txt
        // message.setText(mailContent);

        // ***********************************************************************
        // 以HTML格式发送邮件 （不带附件的邮件）

        // ******************************************************
        // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
        MimeMultipart mimeMultipart = new MimeMultipart();

        // 创建一个包含HTML内容的MimeBodyPart
        BodyPart bodyPart = new MimeBodyPart();
        // 设置HTML内容
        // 设置邮件消息的主要内容
        bodyPart.setContent(mailSenderConfig.getContent(), mailSenderConfig.getContentMimeType());

        mimeMultipart.addBodyPart(bodyPart);

        //*********设置附件***************************************************************
        this.setAttachment(mimeMultipart, mailSenderConfig);

        // 将MiniMultipart对象设置为邮件内容
        message.setContent(mimeMultipart);
    }

    /**
     * 设置附件.
     *
     * @param mimeMultipart
     *            the mime multipart
     * @param mailSenderConfig
     *            the mail sender config
     * @throws MessagingException
     *             the messaging exception
     * @since 1.1.1
     */
    private void setAttachment(MimeMultipart mimeMultipart,MailSenderConfig mailSenderConfig) throws MessagingException{
        String[] attachFilePaths = mailSenderConfig.getAttachFilePaths();

        // html
        if (Validator.isNullOrEmpty(attachFilePaths)){
            // nothing to do
        }
        // ***************以HTML格式发送邮件 带附件的邮件图片********************************************************
        else{

            List<byte[]> attachList = new ArrayList<byte[]>();
            List<String> attachFileNames = new ArrayList<String>();

            for (String attachFilePath : attachFilePaths){
                attachFileNames.add(FileUtil.getFileName(attachFilePath));
                attachList.add(FileUtil.convertFileToByteArray(new File(attachFilePath)));
            }
            if (Validator.isNotNullOrEmpty(attachList)){

                // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
                // ************************************************
                System.setProperty("mail.mime.encodefilename", "true");

                // 用于组合文本和图片，"related"型的MimeMultipart对象  
                mimeMultipart.setSubType("related");

                String mimeType = MimeType.BIN.getMime();

                for (int i = 0, j = attachList.size(); i < j; i++){
                    // 新建一个存放附件的BodyPart
                    MimeBodyPart mimeBodyPart = new MimeBodyPart();

                    byte[] data = attachList.get(i);
                    ByteArrayDataSource byteArrayDataSource = new ByteArrayDataSource(data, mimeType);
                    DataHandler dataHandler = new DataHandler(byteArrayDataSource);
                    mimeBodyPart.setDataHandler(dataHandler);

                    // 加上这句将作为附件发送,否则将作为信件的文本内容
                    mimeBodyPart.setFileName(attachFileNames.get(i));

                    mimeBodyPart.setContentID(PREFIX_CONTENTID + (i));

                    // 将含有附件的BodyPart加入到MimeMultipart对象中
                    mimeMultipart.addBodyPart(mimeBodyPart);
                }
            }
        }
    }

    /**
     * 设置message公共属性.
     *
     * @param message
     *            the message
     * @param mailSenderConfig
     *            属性
     * @throws UnsupportedEncodingException
     *             the unsupported encoding exception
     * @throws MessagingException
     *             the messaging exception
     * @see #setRecipients(Message, MailSenderConfig)
     * @see #setHeaders(Message, MailSenderConfig)
     */
    private void setMessageAttribute(Message message,MailSenderConfig mailSenderConfig) throws UnsupportedEncodingException,
                    MessagingException{
        String fromAddress = mailSenderConfig.getFromAddress();

        //the encoding to be used. Currently supported values are "B" and "Q". 
        //If this parameter is null, then the "Q" encoding is used if most of characters to be encoded are in the ASCII charset, 
        //otherwise "B" encoding is used.
        //B为base64方式
        String encoding = "b";

        // 设置邮件消息的发送者
        String encodeText = MimeUtility.encodeText(mailSenderConfig.getPersonal(), CHARSET_PERSONAL, encoding);

        Address addressFrom = new InternetAddress(fromAddress, encodeText);
        message.setFrom(addressFrom);

        // ***************************************************************************
        // 设置邮件接受人群
        // 支持 to cc bcc
        super.setRecipients(message, mailSenderConfig);

        // ***************************************************************************
        // 设置邮件消息的主题
        message.setSubject(mailSenderConfig.getSubject());

        super.setHeaders(message, mailSenderConfig);
    }
}