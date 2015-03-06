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
package com.feilong.taglib.base;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;

/**
 * The Class BaseTagTEL.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-13 上午1:35:28
 */
public abstract class BaseTagTEL extends TagExtraInfo{

    /**
     * 显示 tagData 里面的信息 一般用于 debug.
     *
     * @param tagData
     *            the tag data
     * @return the map< string, object>
     */
    protected Map<String, Object> toMap(TagData tagData){
        Map<String, Object> map = new HashMap<String, Object>();
        Enumeration<String> attributes = tagData.getAttributes();
        while (attributes.hasMoreElements()){
            String key = attributes.nextElement();
            Object value = tagData.getAttribute(key);
            map.put(key, value);
        }
        return map;
    }
}
