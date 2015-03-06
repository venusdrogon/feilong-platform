/**
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

import java.util.Map;

/**
 * The Class ToXmlConfig.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 31, 2014 10:12:31 AM
 */
public final class ToXmlConfig{

    /** 别名. */
    private Map<String, Class<?>> aliasMap;

    /** 隐式集合 隐藏,隐藏,比如下面有list. */
    private Map<String, Class<?>> implicitCollectionMap;

    /**
     * Gets the 别名.
     * 
     * @return the aliasMap
     */
    public Map<String, Class<?>> getAliasMap(){
        return aliasMap;
    }

    /**
     * Sets the 别名.
     * 
     * @param aliasMap
     *            the aliasMap to set
     */
    public void setAliasMap(Map<String, Class<?>> aliasMap){
        this.aliasMap = aliasMap;
    }

    /**
     * Gets the 隐式集合 隐藏,隐藏,比如下面有list.
     * 
     * @return the implicitCollectionMap
     */
    public Map<String, Class<?>> getImplicitCollectionMap(){
        return implicitCollectionMap;
    }

    /**
     * Sets the 隐式集合 隐藏,隐藏,比如下面有list.
     * 
     * @param implicitCollectionMap
     *            the implicitCollectionMap to set
     */
    public void setImplicitCollectionMap(Map<String, Class<?>> implicitCollectionMap){
        this.implicitCollectionMap = implicitCollectionMap;
    }
}
