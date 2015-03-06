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
package com.feilong.taglib.common.date;

import java.util.Date;

import com.feilong.commons.core.date.DateUtil;
import com.feilong.taglib.base.AbstractCommonTag;

/**
 * 显示当前时间.
 *
 * @author 金鑫 2010-4-13 下午09:17:10
 */
public class CurrentTimeTag extends AbstractCommonTag{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** 显示模式. */
    private String            pattern          = "yyyy-MM-dd";

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.taglib.base.AbstractCommonTag#writeContent()
     */
    @Override
    public String writeContent(){
        return DateUtil.date2String(new Date(), pattern);
    }

    /**
     * 设置 显示模式.
     *
     * @param pattern
     *            the pattern to set
     */
    public void setPattern(String pattern){
        this.pattern = pattern;
    }
}
