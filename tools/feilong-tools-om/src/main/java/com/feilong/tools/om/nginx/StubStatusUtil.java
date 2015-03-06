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
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Date;

import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.HttpMethodType;
import com.feilong.commons.core.util.Validator;
import com.feilong.tools.net.httpclient3.HttpClientConfig;
import com.feilong.tools.net.httpclient3.HttpClientException;
import com.feilong.tools.net.httpclient3.HttpClientUtil;
import com.feilong.tools.om.nginx.command.StubStatusCommand;

/**
 * NginxStubStatus 工具类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 23, 2013 3:42:00 AM
 */
public final class StubStatusUtil{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(StubStatusUtil.class);

    /**
     * 解析nginx stub status 成 NginxStubStatusCommand.
     * 
     * @param uri
     *            uri
     * @param userName
     *            用户名
     * @param password
     *            密码
     * @return the stub status command
     */
    public static StubStatusCommand getStubStatusCommand(String uri,String userName,String password){
        Date now = new Date();

        Integer activeConnections = 0;
        Long serverAccepts = 0L;
        Long serverHandled = 0L;
        Long serverRequests = 0L;
        Integer reading = 0;
        Integer writing = 0;
        Integer waiting = 0;

        // **************************************************************************

        UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials(userName, password);

        HttpClientConfig httpClientConfig = new HttpClientConfig();
        httpClientConfig.setUri(uri);
        httpClientConfig.setHttpMethodType(HttpMethodType.GET);
        httpClientConfig.setUsernamePasswordCredentials(usernamePasswordCredentials);

        try{
            String responseBodyAsString = HttpClientUtil.getResponseBodyAsString(httpClientConfig);

            if (Validator.isNotNullOrEmpty(responseBodyAsString)){

                log.debug("responseBodyAsString:\n{}", responseBodyAsString);

                Reader reader = new StringReader(responseBodyAsString);
                LineNumberReader lineNumberReader = new LineNumberReader(reader);

                String line = null;

                try{
                    while ((line = lineNumberReader.readLine()) != null){
                        int lineNumber = lineNumberReader.getLineNumber();
                        log.debug("the param lineNumber:{}", lineNumber);
                        if (1 == lineNumber){
                            String[] split = line.split(":");
                            activeConnections = Integer.parseInt(split[1].trim());
                        }else if (2 == lineNumber){
                            // nothing to do,only text "server accepts handled requests"
                        }else if (3 == lineNumber){
                            String[] split = line.trim().split(" ");

                            serverAccepts = Long.parseLong(split[0].trim());
                            serverHandled = Long.parseLong(split[1].trim());
                            serverRequests = Long.parseLong(split[2].trim());

                        }else if (4 == lineNumber){
                            String[] split = line.trim().split(" ");

                            reading = Integer.parseInt(split[1].trim());
                            writing = Integer.parseInt(split[3].trim());
                            waiting = Integer.parseInt(split[5].trim());
                        }else{
                            break;
                        }
                    }
                }catch (NumberFormatException e){
                    log.error(e.getClass().getName(), e);
                }catch (IOException e){
                    log.error(e.getClass().getName(), e);
                }
            }
        }catch (HttpClientException e1){
            e1.printStackTrace();
        }

        // **************有可能异常情况, 设置为默认值*****************************************
        StubStatusCommand stubStatusCommand = new StubStatusCommand();
        stubStatusCommand.setActiveConnections(activeConnections);
        stubStatusCommand.setReading(reading);
        stubStatusCommand.setServerAccepts(serverAccepts);
        stubStatusCommand.setServerHandled(serverHandled);
        stubStatusCommand.setServerRequests(serverRequests);
        stubStatusCommand.setWaiting(waiting);
        stubStatusCommand.setWriting(writing);
        stubStatusCommand.setCrawlDate(now);

        return stubStatusCommand;
    }
}
