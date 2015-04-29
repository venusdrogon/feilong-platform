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
import java.util.List;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SearchTerm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.tools.mail.entity.MailInfo;
import com.feilong.tools.mail.entity.MailSenderConfig;
import com.feilong.tools.mail.util.FolderUtil;
import com.feilong.tools.mail.util.MessageUtil;

/**
 * The Class MailReaderUtil.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.9 2015年3月26日 下午3:26:52
 * @since 1.0.9
 */
public class IMAPMailReader implements MailReader{

    /** The Constant log. */
    private static final Logger log        = LoggerFactory.getLogger(IMAPMailReader.class);

    /** imap or pop3. */
    private final String        protocol   = "imap";

    /** The folder name. */
    private final String        folderName = "INBOX";

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.mail.MailReader#getMailInfoList(com.feilong.tools.mail.entity.MailSenderConfig)
     */
    @Override
    public List<MailInfo> getMailInfoList(MailSenderConfig mailSenderConfig) throws MailReaderException{
        SearchTerm searchTerm = null;
        Integer newstIndex = null;
        return getMailInfoList(mailSenderConfig, newstIndex, searchTerm);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.mail.MailReader#getMailInfoList(com.feilong.tools.mail.entity.MailSenderConfig, java.lang.Integer,
     * javax.mail.search.SearchTerm)
     */
    @Override
    public List<MailInfo> getMailInfoList(MailSenderConfig mailSenderConfig,Integer newstIndex,SearchTerm searchTerm)
                    throws MailReaderException{

        if (log.isDebugEnabled()){
            log.debug("input mailSenderConfig:[{}]", JsonUtil.format(mailSenderConfig));
            log.debug("input searchTerm:[{}]", JsonUtil.format(searchTerm));
        }

        List<MailInfo> mailInfoList = null;

        String mailServerHost = mailSenderConfig.getMailServerHost();
        String userName = mailSenderConfig.getUserName();
        String password = mailSenderConfig.getPassword();

        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session session = SessionFactory.createSession(mailSenderConfig);

        // Get the store
        Store store = null;
        Folder folder = null;
        try{
            store = session.getStore(protocol);
            //store.connect();
            store.connect(mailServerHost, userName, password);

            folder = this.getFolder(store);
            //******************************************************************************
            Message[] messages = this.getMessages(folder, searchTerm, newstIndex);
            mailInfoList = MessageUtil.toMailInfoList(messages);
        }catch (MessagingException | IOException e){
            throw new MailReaderException(e);
        }finally{
            try{
                if (null != store && store.isConnected()){
                    store.close();
                }
                if (null != folder && folder.isOpen()){
                    folder.close(false); // Close connection 
                }
            }catch (MessagingException e){
                log.error("", e);
                throw new MailReaderException(e);
            }
        }
        return mailInfoList;
    }

    /**
     * 获得 folder.
     *
     * @param store
     *            the store
     * @return the folder
     * @throws MessagingException
     *             the messaging exception
     * @since 1.0.9
     */
    private Folder getFolder(Store store) throws MessagingException{
        Folder folder = store.getFolder(folderName);
        folder.open(Folder.READ_ONLY);

        if (log.isDebugEnabled()){
            log.debug(JsonUtil.format(FolderUtil.getMapForLog(folder)));
        }
        return folder;
    }

    /**
     * 获得 messages.
     *
     * @param folder
     *            the folder
     * @param searchTerm
     *            the search term
     * @param newstIndex
     *            the newst index
     * @return the messages
     * @throws MessagingException
     *             the messaging exception
     * @since 1.0.9
     */
    private Message[] getMessages(Folder folder,SearchTerm searchTerm,Integer newstIndex) throws MessagingException{
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
        return messages;
    }
}
