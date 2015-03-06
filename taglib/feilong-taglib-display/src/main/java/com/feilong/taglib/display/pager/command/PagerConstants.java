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
package com.feilong.taglib.display.pager.command;

/**
 * 分页常量.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-16 上午12:03:45
 * @version 1.0.5 2014-5-7 13:24
 */
public final class PagerConstants{

    /** 默认分页每页显示数量 <code>{@value}</code>. */
    public static final Integer DEFAULT_PAGESIZE                       = 20;

    /** 默认的皮肤 <code>{@value}</code>. */
    public static final String  DEFAULT_SKIN                           = "digg";

    /** 默认分页参数名称 <code>{@value}</code>. */
    public static final String  DEFAULT_PAGE_PARAM_NAME                = "pageNo";

    /** 默认分页使用的vm 脚本 <code>{@value}</code>. */
    public static final String  DEFAULT_TEMPLATE_IN_CLASSPATH          = "velocity/feilong-default-pager.vm";

    // **************************************************************************************
    /** 默认将解析出来的htm 的存放在 request 作用域里面的变量 <code>{@value}</code>. */
    public static final String  DEFAULT_PAGE_ATTRIBUTE_PAGER_HTML_NAME = "feilongPagerHtml";

    // ******************************************************************************

    /** url 中特殊变量,如果带有这个变量 将不解析vm,调试使用问题所在 <code>{@value}</code> . */
    public static final String  DEFAULT_PARAM_DEBUG_NOT_PARSEVM        = "debugNotParseVM";

    /** url 中特殊变量,如果带有这个变量 将不解析vm,调试使用问题所在 <code>{@value}</code> . */
    public static final String  DEFAULT_PARAM_DEBUG_NOT_PARSEVM_VALUE  = "true";

    /**
     * 模板链接页码,一般url正常的url不会出现这个分页码<code>{@value}</code>.
     *
     * @since 1.0.5
     */
    // XXX 可修改为 可传递参数
    public static final int     DEFAULT_TEMPLATE_PAGE_NO               = -88888888;
}
