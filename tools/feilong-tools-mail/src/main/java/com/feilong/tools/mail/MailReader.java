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

import java.util.List;

import javax.mail.search.SearchTerm;

import com.feilong.tools.mail.entity.MailInfo;
import com.feilong.tools.mail.entity.MailSenderConfig;

/**
 * The Interface MailReader.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.9 2015年3月26日 下午3:32:33
 * @see com.feilong.tools.mail.entity.MailSenderConfig
 * @since 1.0.9
 */
public interface MailReader{

    /**
     * 获得 imap mail info list.
     *
     * @param mailSenderConfig
     *            the mail sender config
     * @return the IMAP mail info list
     * @throws MailReaderException
     *             the mail reader exception
     */
    List<MailInfo> getMailInfoList(MailSenderConfig mailSenderConfig) throws MailReaderException;

    /**
     * 获得 imap mail info list.
     *
     * @param mailSenderConfig
     *            the mail sender config
     * @param newstIndex
     *            the newst index
     * @param searchTerm
     *            the search term
     * @return the IMAP mail info list
     * @throws MailReaderException
     *             the mail reader exception
     */
    List<MailInfo> getMailInfoList(MailSenderConfig mailSenderConfig,Integer newstIndex,SearchTerm searchTerm) throws MailReaderException;
}
