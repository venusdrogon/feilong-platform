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
package com.feilong.tools.net.httpclient3;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.httpclient.NameValuePair;

import com.feilong.commons.core.util.Validator;

/**
 * The Class NameValuePairUtil.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年9月29日 下午1:18:47
 * @since 1.0.8
 */
final class NameValuePairUtil{

    /**
     * 从一个map转换成NameValuePair数组.
     *
     * @param params
     *            the params
     * @return if (Validator.isNotNullOrEmpty(params)), will return null
     */
    public static NameValuePair[] fromMap(Map<String, String> params){
        if (Validator.isNotNullOrEmpty(params)){
            NameValuePair[] nameValuePairs = new NameValuePair[params.size()];
            int i = 0;
            for (Map.Entry<String, String> entry : params.entrySet()){
                String key = entry.getKey();
                String value = entry.getValue();
                nameValuePairs[i] = new NameValuePair(key, value);
                i++;
            }
            return nameValuePairs;
        }
        return null;
    }

    /**
     * 将nameValuePairs转成Map.
     *
     * @param nameValuePairs
     *            the name value pairs
     * @return if (Validator.isNotNullOrEmpty(nameValuePairs)), will return Collections.emptyMap()
     * @since 1.0.9
     */
    public static Map<String, String> toMap(NameValuePair[] nameValuePairs){
        if (Validator.isNotNullOrEmpty(nameValuePairs)){
            Map<String, String> map = new TreeMap<String, String>();

            for (NameValuePair nameValuePair : nameValuePairs){
                map.put(nameValuePair.getName(), nameValuePair.getValue());
            }

            return map;
        }
        return Collections.emptyMap();
    }
}
