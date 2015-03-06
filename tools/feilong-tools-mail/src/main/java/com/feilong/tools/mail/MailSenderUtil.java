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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.MailcapCommandMap;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.search.SearchTerm;
import javax.mail.util.ByteArrayDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.CharsetType;
import com.feilong.commons.core.io.MimeType;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.tools.mail.entity.MailInfo;
import com.feilong.tools.mail.entity.MailSenderConfig;
import com.feilong.tools.mail.util.FolderUtil;
import com.feilong.tools.mail.util.MessageUtil;

/**
 * 邮件发送器.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 金鑫 2010-1-23 下午04:22:24
 * @version 金鑫 2011-12-24 01:54
 * @version 1.0 Dec 7, 2013 9:04:22 PM
 * @version 1.0.8 2014-11-27 14:15
 * @see javax.mail.Message
 * @see javax.mail.Session
 * @see javax.mail.Transport#send(Message)
 * @since 1.0.0
 */
public final class MailSenderUtil{

    /** The Constant log. */
    private static final Logger log              = LoggerFactory.getLogger(MailSenderUtil.class);

    /** contentId前缀. */
    public static final String  PREFIX_CONTENTID = "image";

    /** The Constant CHARSET_PERSONAL. */
    private static final String CHARSET_PERSONAL = CharsetType.GB2312;

    /** The message. */
    private Message             message          = null;

    /**
     * 发送邮件.
     * 
     * @param mailSenderConfig
     *            the mail entity
     * @throws MessagingException
     *             the messaging exception
     * @throws UnsupportedEncodingException
     *             the unsupported encoding exception
     */
    public void sendMail(MailSenderConfig mailSenderConfig) throws MessagingException,UnsupportedEncodingException{
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
            log.debug("mailSenderConfig:{}", JsonUtil.format(mailSenderConfig));
        }

        // *****************************************************************************************
        // 根据session创建一个邮件消息

        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session session = createSession(mailSenderConfig);
        // 根据session创建一个邮件消息
        message = new MimeMessage(session);

        setMessageAttribute(mailSenderConfig);

        setBody(mailSenderConfig);

        setDefaultCommandMap();

        // ***********************************************************************
        // 发送邮件
        Transport.send(message);
    }

    /**
     * 设置 default command map.<br>
     * <p>
     * 解决 bug javax.activation.UnsupportedDataTypeException: no object DCH for MIME type multipart/related;
     * </p>
     */
    private void setDefaultCommandMap(){

        MailcapCommandMap mailcapCommandMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        mailcapCommandMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
        mailcapCommandMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
        mailcapCommandMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
        mailcapCommandMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
        mailcapCommandMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
        CommandMap.setDefaultCommandMap(mailcapCommandMap);
    }

    /**
     * setBody.
     *
     * @param mailSenderConfig
     *            the body
     * @throws MessagingException
     *             the messaging exception
     */
    private void setBody(MailSenderConfig mailSenderConfig) throws MessagingException{
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

        String[] attachFileNames = mailSenderConfig.getAttachFileNames();
        // html
        if (Validator.isNullOrEmpty(attachFileNames)){
            // nothing to do
        }
        // ***************以HTML格式发送邮件 带附件的邮件图片********************************************************
        else{
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            // ************************************************
            System.setProperty("mail.mime.encodefilename", "true");

            // 用于组合文本和图片，"related"型的MimeMultipart对象  
            mimeMultipart.setSubType("related");

            List<byte[]> attachList = mailSenderConfig.getAttachList();
            if (Validator.isNotNullOrEmpty(attachList)){

                String type = MimeType.BIN.getMime();

                int size = attachList.size();
                for (int i = 0; i < size; i++){
                    // 新建一个存放附件的BodyPart
                    MimeBodyPart mimeBodyPart = new MimeBodyPart();

                    byte[] data = attachList.get(i);
                    ByteArrayDataSource byteArrayDataSource = new ByteArrayDataSource(data, type);
                    DataHandler dataHandler = new DataHandler(byteArrayDataSource);
                    mimeBodyPart.setDataHandler(dataHandler);

                    // 加上这句将作为附件发送,否则将作为信件的文本内容
                    mimeBodyPart.setFileName(attachFileNames[i]);

                    mimeBodyPart.setContentID(PREFIX_CONTENTID + (i));
                    // mimeBodyPart.setHeader("Content-ID", );

                    // 将含有附件的BodyPart加入到MimeMultipart对象中
                    mimeMultipart.addBodyPart(mimeBodyPart);
                }
            }
        }
        // ***********************************************************************
        // 将MiniMultipart对象设置为邮件内容
        message.setContent(mimeMultipart);
    }

    /**
     * 设置message公共属性.
     * 
     * @param mailSenderConfig
     *            属性
     * @throws UnsupportedEncodingException
     *             the unsupported encoding exception
     * @throws MessagingException
     *             the messaging exception
     */
    private void setMessageAttribute(MailSenderConfig mailSenderConfig) throws UnsupportedEncodingException,MessagingException{
        String fromAddress = mailSenderConfig.getFromAddress();
        // 设置邮件消息的发送者

        String encodeText = MimeUtility.encodeText(mailSenderConfig.getPersonal(), CHARSET_PERSONAL, "b");

        Address addressFrom = new InternetAddress(fromAddress, encodeText);
        message.setFrom(addressFrom);

        // ***************************************************************************
        // 设置邮件接受人群
        // 支持 to cc bcc
        setRecipients(mailSenderConfig);

        // ***************************************************************************
        // 设置邮件消息的主题
        message.setSubject(mailSenderConfig.getSubject());

        setHeaders(mailSenderConfig);
    }

    /**
     * 设置 header 信息.
     *
     * @param mailSenderConfig
     *            the headers
     * @throws MessagingException
     *             the messaging exception
     * @see javax.mail.Part#addHeader(String, String)
     * @see javax.mail.Part#setHeader(String, String)
     * @since 1.0.9
     */
    private void setHeaders(MailSenderConfig mailSenderConfig) throws MessagingException{
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
     * @param mailSenderConfig
     *            the new recipients
     * @throws AddressException
     *             the address exception
     * @throws MessagingException
     *             the messaging exception
     */
    private void setRecipients(MailSenderConfig mailSenderConfig) throws AddressException,MessagingException{
        // *********************to******************************************************
        // 创建邮件的接收者地址，并设置到邮件消息中
        // Message.RecipientType.TO属性表示接收者的类型为TO
        setRecipients(Message.RecipientType.TO, mailSenderConfig.getTos());
        // ************************cc 抄送***************************************************
        setRecipients(Message.RecipientType.CC, mailSenderConfig.getCcs());
        // **********************bcc 密送*****************************************************
        setRecipients(Message.RecipientType.BCC, mailSenderConfig.getBccs());
    }

    /**
     * 设置 邮件接收人.
     *
     * @param recipientType
     *            the recipient type
     * @param addresseArray
     *            the addresse array
     * @throws AddressException
     *             the address exception
     * @throws MessagingException
     *             the messaging exception
     * @since 1.0.8
     */
    private void setRecipients(RecipientType recipientType,String[] addresseArray) throws AddressException,MessagingException{
        if (Validator.isNotNullOrEmpty(addresseArray)){
            final int length = addresseArray.length;
            Address[] addresses = new InternetAddress[length];
            for (int i = 0; i < length; ++i){
                addresses[i] = new InternetAddress(addresseArray[i]);
            }
            message.setRecipients(recipientType, addresses);
        }
    }

    /**
     * 根据邮件会话属性和密码验证器构造一个发送邮件的session.
     * 
     * @param mailSenderConfig
     *            mailSenderInfo
     * @return Session
     */
    private Session createSession(MailSenderConfig mailSenderConfig){
        Properties properties = new Properties();
        properties.put("mail.smtp.host", mailSenderConfig.getMailServerHost());
        properties.put("mail.smtp.port", mailSenderConfig.getMailServerPort());
        properties.put("mail.smtp.auth", mailSenderConfig.isValidate() ? "true" : "false");
        // 判断是否需要身份认证
        Authenticator authenticator = null;
        // 如果需要身份认证，则创建一个密码验证器
        if (mailSenderConfig.isValidate()){
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

    /**
     * 获得 po p3 messages.
     *
     * @param mailSenderConfig
     *            the mail sender config
     * @return the PO p3 messages
     * @throws MessagingException
     *             the messaging exception
     * @throws IOException
     *             the IO exception
     */
    public List<MailInfo> getPOP3MailInfoList(MailSenderConfig mailSenderConfig) throws MessagingException,IOException{
        SearchTerm searchTerm = null;
        Integer newstIndex = null;
        return getPOP3MailInfoList(mailSenderConfig, newstIndex, searchTerm);
    }

    /**
     * 获得 po p3 messages.
     *
     * @param mailSenderConfig
     *            the mail sender config
     * @param newstIndex
     *            the newst index
     * @param searchTerm
     *            the search term
     * @return the PO p3 messages
     * @throws MessagingException
     *             the messaging exception
     * @throws IOException
     *             the IO exception
     */
    public List<MailInfo> getPOP3MailInfoList(MailSenderConfig mailSenderConfig,Integer newstIndex,SearchTerm searchTerm)
                    throws MessagingException,IOException{
        String mailServerHost = mailSenderConfig.getMailServerHost();
        String userName = mailSenderConfig.getUserName();
        String password = mailSenderConfig.getPassword();

        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session session = createSession(mailSenderConfig);

        // Get the store
        //imap pop3
        Store store = session.getStore("imap");
        //store.connect();
        store.connect(mailServerHost, userName, password);

        // Get folder
        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);

        if (log.isDebugEnabled()){
            log.debug(JsonUtil.format(FolderUtil.getMapForLog(folder)));
        }

        //******************************************************************************
        // Get directory
        Message[] messages = null;

        //最近的多少条
        if (Validator.isNotNullOrEmpty(newstIndex)){
            int messageCount = folder.getMessageCount();
            int start = messageCount - newstIndex;
            start = start < 1 ? 1 : start;
            messages = folder.getMessages(start, messageCount);
        }else{
            //所有
            messages = folder.getMessages();
        }

        if (Validator.isNullOrEmpty(searchTerm)){
            //nothing to do 
        }else{
            messages = folder.search(searchTerm, messages);
        }

        List<MailInfo> mailInfoList = MessageUtil.toMailInfoList(messages);

        //******************************************************
        // Close connection 
        folder.close(false);
        store.close();

        return mailInfoList;
    }
}