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

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.IOReaderUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.tools.om.nginx.command.LogFormatCommand;

/**
 * The Class LogTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-27 上午11:55:46
 * @since 1.0
 */
public class LogTest{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(LogTest.class);

    /**
     * Test aj11visitlogall.
     * 
     * @throws IOException
     */
    @Test
    public void testAj11visitlogall() throws IOException{
        String fileName = "C:\\Users\\feilong\\Documents\\AJ11\\AJ11\\aj11visitlogall3000000.txt";
        String content = IOReaderUtil.getFileContent(fileName);
        // 将内容以换行符转成数组
        // String[] rowsContents = content.split("\r\n");
        log.info(content);
    }

    /**
     * Nginx log format test.
     */
    @Test
    public void nginxLogFormatTest(){
        String line = "112.64.235.89 - - [19/Dec/2013:16:21:47 +0800] 404 \"GET /product/fair/aj11blue1200.htm?cid=dis1221ondspaj01 HTTP/1.1\" 29520 \"-\" \"Mozilla/4.0\" 112.64.235.89 \"192.168.10.27:8407\" \"0.027\"";
        String pattern = "$remote_addr - $remote_user [$time_local] $status \"$request\" $body_bytes_sent \"$http_referer\" \"$http_user_agent\" $proxy_add_x_forwarded_for \"$upstream_addr\" \"$request_time\"";
        LogFormatCommand nginxLogFormatCommand = getNginxLogFormatCommand(line, pattern);
        log.info(JsonUtil.format(nginxLogFormatCommand));
    }

    /**
     * Gets the nginx log format command.
     * 
     * @param line
     *            the line
     * @param pattern
     *            the pattern
     * @return the nginx log format command
     */
    private LogFormatCommand getNginxLogFormatCommand(String line,String pattern){
        log.debug("the param pattern:{}", pattern);
        log.debug("the param line:{}", line);

        String[] arr = line.split(" ");

        String remote_addr = arr[0];
        String remote_user = arr[1];
        String time_local = arr[3].substring(1);
        String request = arr[6];
        String status = arr[8];
        String body_bytes_sent = arr[9];
        String http_referer = arr[10];
        String http_user_agent = arr[11] + " " + arr[12];
        String proxy_add_x_forwarded_for = "";
        String upstream_addr = "";
        String request_time = "";

        // SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd:HH:mm:ss", Locale.US);

        LogFormatCommand nginxLogFormatCommand = new LogFormatCommand();
        nginxLogFormatCommand.setRemote_addr(remote_addr);
        nginxLogFormatCommand.setRemote_user(remote_user);
        nginxLogFormatCommand.setTime_local(time_local);
        nginxLogFormatCommand.setRequest(request);
        nginxLogFormatCommand.setStatus(status);
        nginxLogFormatCommand.setBody_bytes_sent(body_bytes_sent);
        nginxLogFormatCommand.setHttp_referer(http_referer);
        nginxLogFormatCommand.setHttp_user_agent(http_user_agent);
        nginxLogFormatCommand.setProxy_add_x_forwarded_for(proxy_add_x_forwarded_for);
        nginxLogFormatCommand.setUpstream_addr(upstream_addr);
        nginxLogFormatCommand.setRequest_time(request_time);

        return nginxLogFormatCommand;
    }
}
