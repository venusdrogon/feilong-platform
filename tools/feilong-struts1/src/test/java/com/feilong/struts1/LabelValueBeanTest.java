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
package com.feilong.struts1;

import org.apache.struts.util.LabelValueBean;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class LabelValueBeanTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-6-25 16:27:29
 */
public class LabelValueBeanTest{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(LabelValueBeanTest.class);

    /**
     * F.
     */
    @Test
    public void f(){
        LabelValueBean labelValueBean = new LabelValueBean();
        labelValueBean.setLabel("haha");
        labelValueBean.setValue("ssssss");
        log.info(labelValueBean.getLabel());
    }
}
