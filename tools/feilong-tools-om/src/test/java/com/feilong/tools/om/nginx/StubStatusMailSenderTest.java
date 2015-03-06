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
package com.feilong.tools.om.nginx;

import java.io.IOException;

import javax.mail.MessagingException;

import org.junit.Test;

/**
 * The Class StubStatusMailSenderTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 31, 2013 1:54:16 AM
 */
public class StubStatusMailSenderTest{

    /**
     * Test method for {@link com.feilong.tools.om.nginx.StubStatusMailSender#sendMonitorMail(java.lang.String)}.
     *
     * @throws MessagingException
     *             the messaging exception
     * @throws IOException
     *             the IO exception
     */
    @Test
    public final void testSendMonitorMail() throws MessagingException,IOException{
        String filePath = "F:\\stubstatus\\2014\\02-06\\02.txt";
        StubStatusMailSender.sendMonitorMail(filePath);
    }
}
