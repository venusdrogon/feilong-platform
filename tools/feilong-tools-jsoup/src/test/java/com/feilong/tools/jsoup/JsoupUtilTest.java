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
package com.feilong.tools.jsoup;

import org.jsoup.nodes.Document;
import org.junit.Test;

/**
 * The Class JsoupUtilTest.
 */
@SuppressWarnings("all")
public class JsoupUtilTest{

    /**
     * 获得 document.
     *
     * @throws JsoupUtilException
     *             the jsoup util exception
     */
    @Test
    public void getDocument() throws JsoupUtilException{
        String urlString = "http://www.d9cn.org/d9cnbook/50/50537/10967924.html";
        Document document = JsoupUtil.getDocument(urlString);
    }
}