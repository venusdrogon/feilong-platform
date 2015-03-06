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
package com.feilong.application.sina;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.httpclient.NameValuePair;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weibo4j.model.WeiboException;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;

/**
 * The Class WeiboUtilTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 11, 2013 12:23:54 AM
 */
public class WeiboUtilTest{

    /** The Constant log. */
    private static final Logger log          = LoggerFactory.getLogger(WeiboUtilTest.class);

    /** The access_token. */
    private String              access_token = "2.00eFxqECiedPvB83b474816527NTJB";

    /**
     * Send weibo_with photo.
     *
     * @throws WeiboException
     *             the weibo exception
     * @throws IOException
     *             the IO exception
     */
    @Test
    public final void sendWeibo_withPhoto() throws WeiboException,IOException{
        String imagePath = "F:\\Picture 图片\\maitan.gif";
        String statuses = "#鑫哥的微博小蜜# 发个图片玩玩";
        WeiboUtil.sendWeibo(access_token, statuses, imagePath);
    }

    /**
     * Send weibo.
     *
     * @throws WeiboException
     *             the weibo exception
     * @throws IOException
     *             the IO exception
     */
    @Test
    public void sendWeibo() throws WeiboException,IOException{
        Date date = new Date();
        String statuses = "#鑫哥的微博小蜜# 吃饭去了,好饿" + DateUtil.date2String(date, DatePattern.COMMON_DATE_AND_TIME);
        WeiboUtil.sendWeibo(access_token, statuses);
    }

    /**
     * Weibo.
     */
    @Test
    public void weibo(){
        String uri = "https://api.weibo.com/2/statuses/update.json";

        String status = "先泽请我吃饭~~~~~@张先泽";
        // status = URIUtil.encode(status, CharsetType.UTF8);
        NameValuePair[] nameValuePairs = { //
        new NameValuePair("status", status),//
                new NameValuePair("access_token", access_token) };

        // %E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E
        // %E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E

        String responseBodyAsString = "";
        // = HttpClientUtil.getPostMethodResponseBodyAsString(uri, nameValuePairs);

        log.info(responseBodyAsString);
        // log.debug("responseBodyAsString:{}", responseBodyAsString);
        // Header[] headers = httpMethod.getResponseHeaders();
        // log.debug("print httpMethod.getResponseHeaders()=======================");
        // for (Header header : headers){
        // log.debug(header.getName() + ":" + header.getValue());
        // }
        // httpMethod.releaseConnection();
    }
}
