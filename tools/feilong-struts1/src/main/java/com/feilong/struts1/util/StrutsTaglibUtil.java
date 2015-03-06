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
package com.feilong.struts1.util;

import java.util.Enumeration;
import java.util.Iterator;

import org.apache.struts.util.IteratorAdapter;

import com.feilong.commons.core.lang.ObjectUtil;

/**
 * FeiLongStrutsTaglibUtil.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-1-19 下午01:14:39
 */
public class StrutsTaglibUtil{

    /**
     * 数组/map/Collection/Iterator/Enumeration转成Iterator.
     *
     * @author 金鑫
     * @version 1.0 2010-5-12 下午05:07:41
     * @param currentCollection
     *            Object
     * @return Iterator
     */
    public static Iterator objectToIterator(Object currentCollection){
        Iterator iterator = ObjectUtil.toIterator(currentCollection);
        if (null == iterator){
            // currentCollection 不是空
            if (null != currentCollection){
                // 构建此集合的迭代器
                if (currentCollection instanceof Enumeration){
                    iterator = new IteratorAdapter((Enumeration<?>) currentCollection);
                }
            }
        }
        return iterator;
    }
}
