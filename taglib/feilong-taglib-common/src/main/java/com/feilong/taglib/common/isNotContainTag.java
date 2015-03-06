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
package com.feilong.taglib.common;

import com.feilong.commons.core.util.ListUtil;
import com.feilong.taglib.base.AbstractConditionalTag;

/**
 * 判断一个集合(或者可以被转成Iterator) 是否 没有 一个值 (或者说这个value 不在 collection当中).
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 4, 2013 1:33:02 PM
 */
public class isNotContainTag extends AbstractConditionalTag{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8239319419199818297L;

    /** 一个集合,将会被转成Iterator,可以为逗号隔开的字符串,会被分隔成Iterator. */
    private Object            collection       = null;

    /** 任意类型的值,最终toString 判断比较. */
    private Object            value            = null;

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.taglib.base.AbstractConditionalTag#condition()
     */
    @Override
    public boolean condition(){
        return !ListUtil.isContainTag(collection, value);
    }

    /**
     * Sets the 一个集合,将会被转成Iterator,可以为逗号隔开的字符串,会被分隔成Iterator.
     * 
     * @param collection
     *            the collection to set
     */
    public void setCollection(Object collection){
        this.collection = collection;
    }

    /**
     * Sets the 一个值.
     * 
     * @param value
     *            the value to set
     */
    public void setValue(Object value){
        this.value = value;
    }
}
