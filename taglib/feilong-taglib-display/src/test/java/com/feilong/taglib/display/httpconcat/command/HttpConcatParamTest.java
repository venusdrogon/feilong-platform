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
package com.feilong.taglib.display.httpconcat.command;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.taglib.display.httpconcat.BaseHttpConcatTest;

/**
 * The Class HttpConcatParamTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年5月24日 下午6:22:48
 * @since 1.0.7
 */
public class HttpConcatParamTest extends BaseHttpConcatTest{

    /** The Constant log. */
    private static final Logger log    = LoggerFactory.getLogger(HttpConcatParamTest.class);

    /** The domain. */
    String                      domain = "http://www.feilong.com";

    /**
     * Test hash code.
     */
    @Test
    public final void testHashCode(){
        HttpConcatParam t = new HttpConcatParam();

        if (log.isInfoEnabled()){
            log.info("" + t.hashCode());
            t.setDomain(domain);
            log.info("" + t.hashCode());
        }
    }

    /**
     * Test equals object.
     */
    @Test
    public final void testEqualsObject(){
        HttpConcatParam pagerParams1 = new HttpConcatParam();

        HttpConcatParam pagerParams2 = new HttpConcatParam();

        if (log.isInfoEnabled()){
            Assert.assertEquals(true, pagerParams1.equals(pagerParams1));
            Assert.assertEquals(false, pagerParams1.equals(null));
            Assert.assertEquals(true, pagerParams1.equals(pagerParams2));

            pagerParams2.setDomain(domain);
            Assert.assertEquals(false, pagerParams1.equals(pagerParams2));

            pagerParams1.setDomain(domain);
            Assert.assertEquals(true, pagerParams1.equals(pagerParams2));

            ArrayList<String> itemSrcList = new ArrayList<String>();
            itemSrcList.add("1.js");
            pagerParams1.setItemSrcList(itemSrcList);
            Assert.assertEquals(false, pagerParams1.equals(pagerParams2));

            itemSrcList = new ArrayList<String>();
            itemSrcList.add("1.js");
            pagerParams2.setItemSrcList(itemSrcList);
            Assert.assertEquals(true, pagerParams1.equals(pagerParams2));

            pagerParams1.setDomain(null);
            Assert.assertEquals(false, pagerParams1.equals(pagerParams2));
        }
    }

    /**
     * Name.
     */
    @Test
    public void name(){
        HttpConcatParam httpConcatParam1 = getHttpConcatParam();
        HttpConcatParam httpConcatParam2 = getHttpConcatParam();
        Assert.assertEquals(true, httpConcatParam1.equals(httpConcatParam2));
    }
}
