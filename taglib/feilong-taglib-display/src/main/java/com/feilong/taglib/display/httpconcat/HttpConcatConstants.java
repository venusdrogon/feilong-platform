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
package com.feilong.taglib.display.httpconcat;

/**
 * 解析http concat用到的常量
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年5月15日 下午2:48:45
 * @since 1.0.7
 */
public interface HttpConcatConstants{

    /** 配置文件 <code>{@value}</code>. */
    String CONFIG_FILE            = "config/httpconcat";

    //**************************************************************
    /** css <code>{@value}</code>. */
    String TYPE_CSS               = "css";

    /** js <code>{@value}</code>. */
    String TYPE_JS                = "js";

    //**************************************************************

    /** <code>{@value}</code>. */
    String KEY_HTTPCONCAT_SUPPORT = "httpconcatSupport";

    //**************************************************************

    /** <code>{@value}</code>. */
    String KEY_TEMPLATE_CSS       = "template.css";

    /** <code>{@value}</code>. */
    String KEY_TEMPLATE_JS        = "template.js";
}
