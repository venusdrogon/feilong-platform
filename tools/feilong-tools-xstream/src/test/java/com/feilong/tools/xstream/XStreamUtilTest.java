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
package com.feilong.tools.xstream;

import java.io.Serializable;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.test.User;
import com.thoughtworks.xstream.XStream;

/**
 * The Class XStreamTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Oct 11, 2013 12:57:10 AM
 */
public class XStreamUtilTest implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long   serialVersionUID = 288232184048495608L;

    /** The Constant log. */
    private static final Logger log              = LoggerFactory.getLogger(XStreamUtilTest.class);

    /**
     * Name.
     */
    @Test
    public void name(){
        User user = new User(1L);

        XStream xStream = new XStream();
        xStream.alias("user", User.class);
        log.info(xStream.toXML(user));
    }
}
