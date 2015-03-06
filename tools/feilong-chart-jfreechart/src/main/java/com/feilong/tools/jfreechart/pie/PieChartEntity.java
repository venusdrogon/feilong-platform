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
package com.feilong.tools.jfreechart.pie;

import java.util.Map;

import com.feilong.tools.jfreechart.BaseChartEntity;

/**
 * The Class PieChartEntity.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 14 05:35:47
 */
public class PieChartEntity extends BaseChartEntity{

    /** The Constant serialVersionUID. */
    private static final long     serialVersionUID = -7723192818168964170L;

    /**
     * 文字和 数据
     * 
     * <pre>
     * Map&lt;String, Number&gt; keyAndDataMap = new LinkedHashMap&lt;String, Number&gt;();
     * keyAndDataMap.put(&quot;失败率&quot;, 50);
     * keyAndDataMap.put(&quot;成功率&quot;, 250);
     * </pre>
     */
    protected Map<String, Number> keyAndDataMap;

    /**
     * 获得 文字和 数据
     * 
     * <pre>
     * Map&lt;String, Number&gt; keyAndDataMap = new LinkedHashMap&lt;String, Number&gt;(); keyAndDataMap.
     * 
     * @return the keyAndDataMap
     */
    public Map<String, Number> getKeyAndDataMap(){
        return keyAndDataMap;
    }

    /**
     * 设置 文字和 数据
     * 
     * <pre>
     * Map&lt;String, Number&gt; keyAndDataMap = new LinkedHashMap&lt;String, Number&gt;(); keyAndDataMap.
     * 
     * @param keyAndDataMap            the keyAndDataMap to set
     */
    public void setKeyAndDataMap(Map<String, Number> keyAndDataMap){
        this.keyAndDataMap = keyAndDataMap;
    }
}
