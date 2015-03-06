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

/**
 * The Class StubStatusMainTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 31, 2013 11:40:57 PM
 */
public class StubStatusMainTest{

    /**
     * Test method for {@link com.feilong.tools.om.nginx.StubStatusMain#crawStubStatusNike(String, String, String, String)}.
     *
     * @throws IOException
     *             the IO exception
     */
    @Test
    public final void testCrawStubStatusNike() throws IOException{

        final String uri = "http://www.nikestore.com.cn/nginx_status";

        final String userName = "nginx_status";
        final String password = "baozun_nikestore_status";

        // 此参数暂时不设置成 可配置式的
        final String patch = "F:\\stubstatus\\nike\\${year}\\${monthAndDay}\\${hour}.txt";

        StubStatusMain.crawStubStatusNike(uri, userName, password, patch);
    }

    /**
     * Test craw stub status feilong.
     *
     * @throws IOException
     *             the IO exception
     */
    @Test
    public final void testCrawStubStatusFeilong() throws IOException{
        final String uri = "http://127.0.0.1:8011/feilong-nginx-status";

        final String userName = "feilong";
        final String password = "521000";

        // 此参数暂时不设置成 可配置式的
        final String patch = "F:\\stubstatus\\feilong\\${year}\\${monthAndDay}\\${hour}.txt";

        StubStatusMain.crawStubStatusNike(uri, userName, password, patch);
    }
}
