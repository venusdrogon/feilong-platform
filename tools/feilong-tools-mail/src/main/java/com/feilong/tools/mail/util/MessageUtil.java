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
package com.feilong.tools.mail.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.FileUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.tools.mail.entity.MailInfo;

/**
 * The Class MessageUtil.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.9 2015年2月2日 上午2:25:18
 * @since 1.0.9
 */
public final class MessageUtil{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(MessageUtil.class);

    /** Don't let anyone instantiate this class. */
    private MessageUtil(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    /**
     * To mail info list.
     *
     * @param messages
     *            the messages
     * @return the list< mail info>
     * @throws MessagingException
     *             the messaging exception
     * @throws IOException
     *             the IO exception
     */
    public static final List<MailInfo> toMailInfoList(Message[] messages) throws MessagingException,IOException{
        if (log.isInfoEnabled()){
            log.info("messages length:[{}]", messages.length);
        }
        //******************************************************

        List<MailInfo> list = new ArrayList<MailInfo>();

        for (int i = 0, n = messages.length; i < n; i++){
            Message message = messages[i];
            MailInfo mailInfo = toMailInfoList(message);
            list.add(mailInfo);
            //log.debug(JsonUtil.format(MessageUtil.getMapForLog(message)));
        }
        return list;
    }

    /**
     * To mail info list.
     *
     * @param message
     *            the message
     * @return the mail info
     * @throws MessagingException
     *             the messaging exception
     * @throws IOException
     *             the IO exception
     */
    public static final MailInfo toMailInfoList(Message message) throws MessagingException,IOException{
        MailInfo mailInfo = new MailInfo();

        String from = getFromAddress(message);
        mailInfo.setFrom(from);

        mailInfo.setContentType(message.getContentType());
        mailInfo.setContent(getContent(message));
        mailInfo.setReceivedDate(message.getReceivedDate());

        String[] recipients = null;
        Address[] allRecipients = message.getAllRecipients();
        if (Validator.isNotNullOrEmpty(allRecipients)){
            int length = allRecipients.length;

            recipients = new String[length];
            for (int i = 0, j = length; i < j; ++i){
                Address address = allRecipients[i];
                recipients[i] = getAddress(address);
            }
        }
        mailInfo.setRecipients(recipients);
        mailInfo.setSentDate(message.getSentDate());
        mailInfo.setSize(FileUtil.formatSize(message.getSize()));
        mailInfo.setSubject(message.getSubject());
        return mailInfo;
    }

    /**
     * 获得 map for log.
     *
     * @param message
     *            the message
     * @return the map for log
     * @throws MessagingException
     *             the messaging exception
     * @throws IOException
     *             the IO exception
     */
    public static final Map<String, Object> getMapForLog(Message message) throws MessagingException,IOException{
        Map<String, Object> object = new LinkedHashMap<String, Object>();
        String address = getFromAddress(message);
        object.put("from", address);
        object.put("sentDate", message.getSentDate());
        object.put("size", FileUtil.formatSize(message.getSize()));
        object.put("subject", message.getSubject());
        //object.put("content", message.getContent());

        object.put("allHeaders", message.getAllHeaders());
        object.put("allRecipients", message.getAllRecipients());
        object.put("contentType", message.getContentType());
        //object.put("getDataHandler", message.getDataHandler());
        object.put("description", message.getDescription());
        object.put("getFileName", message.getFileName());
        object.put("getFlags", message.getFlags().toString());
        object.put("getFolder", message.getFolder().getFullName());
        object.put("getLineCount", message.getLineCount());
        object.put("getMessageNumber", message.getMessageNumber());
        object.put("getReceivedDate", message.getReceivedDate());

        Address[] replyTo = message.getReplyTo();
        object.put("getReplyTo", replyTo);
        return object;
    }

    /**
     * 获得 from address.
     *
     * @param message
     *            the message
     * @return the from address
     * @throws MessagingException
     *             the messaging exception
     * @since 1.0.9
     */
    private static String getFromAddress(Message message) throws MessagingException{
        Address[] from = message.getFrom();
        Address value = from[0];
        return getAddress(value);
    }

    /**
     * 获得 address.
     *
     * @param value
     *            the value
     * @return the address
     * @since 1.0.9
     */
    private static String getAddress(Address value){
        InternetAddress internetAddress = (InternetAddress) value;
        String address = internetAddress.getAddress();
        return address;
    }

    /**
     * 获得 content.
     *
     * @param part
     *            the p
     * @return the string
     * @throws IOException
     *             the IO exception
     * @throws MessagingException
     *             the messaging exception
     * @see javax.mail.search.BodyTerm#matchPart(Part)
     * @deprecated
     */
    //TODO
    @Deprecated
    public static String getContent1(Part part) throws IOException,MessagingException{
        Object partContent = part.getContent();

        //String
        if (partContent instanceof String){
            return (String) partContent;
        }

        //Multipart
        else if (partContent instanceof Multipart){
            Multipart multipart = (Multipart) partContent;
            int count = multipart.getCount();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count; i++){
                BodyPart bodyPart = multipart.getBodyPart(i);
                sb.append(getContent(bodyPart));
            }
            return sb.toString();
        }

        //InputStream
        else if (partContent instanceof InputStream){
            //return IOUtil.inputStream2String((InputStream) partContent);
            //TODO not support
            return null;
        }else{
            throw new UnsupportedOperationException("partContent not support!");
        }
    }

    /**
     * 获得 content.
     *
     * @param part
     *            the part
     * @return the content
     * @throws MessagingException
     *             the messaging exception
     * @throws IOException
     *             the IO exception
     */
    public static String getContent(Part part) throws MessagingException,IOException{
        // Using isMimeType to determine the content type 
        //avoids fetching the actual content data until we need it.
        if (part.isMimeType("text/*")){
            String s = (String) part.getContent();
            return s;
        }else if (part.isMimeType("multipart/*")){
            StringBuilder sb = new StringBuilder();

            Multipart mp = (Multipart) part.getContent();
            int count = mp.getCount();
            for (int i = 0; i < count; i++){
                sb.append(getContent(mp.getBodyPart(i)));
            }
            return sb.toString();
        }else if (part.isMimeType("message/rfc822")){
            return getContent((Part) part.getContent());
        }else{
            log.info("part getContentType:{}", part.getContentType());
            return null;
        }
    }
}
