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
package com.feilong.taglib.display.pager;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class PagerTagTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-6-25 16:27:03
 */
public class PagerTagTest{

    /** The Constant log. */
    private static final Logger log      = LoggerFactory.getLogger(PagerTagTest.class);

    /** The pager tag. */
    private PagerTag            pagerTag = new PagerTag();

    // private TagTestHelper helper = new TagTestHelper(tag);

    /**
     * Test method for {@link com.feilong.taglib.display.pager.PagerTag#writeContent()}.
     */
    @Test
    public void testWriteContent(){
        log.info(pagerTag.writeContent());
    }
}
