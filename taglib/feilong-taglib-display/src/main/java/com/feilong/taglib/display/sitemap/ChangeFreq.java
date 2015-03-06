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
package com.feilong.taglib.display.sitemap;

/**
 * 这个页面变更的频率情况 <br>
 * 这个值提供基本信息给搜索引擎 ,并且搜索引擎可能不准确的按照这个参数走.<br>
 * 有效值:
 * <ul>
 * <li>always(每次访问都不一样,使用这个参数 describe documents that change each time they are accessed)</li>
 * <li>hourly</li>
 * <li>daily</li>
 * <li>weekly</li>
 * <li>monthly</li>
 * <li>yearly</li>
 * <li>never (用来形容 archived URLs 归档url/历史url)</li>
 * </ul>
 * 
 * 注意, 这个tag的值,只是个提示,不是命令<br>
 * 
 * 搜索引擎爬取页面信息的时候,这个标签的值可能仅做参考:<br>
 * 他们可能爬取标识 为hourly 的页面,低于这个频率;<br>
 * 也可能爬取标识为 yearly的页面 高于这个频率 <br>
 * 爬虫可能会定期爬取标识为 never的页面,so that they can handle unexpected changes to those pages.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jun 26, 2014 4:43:10 PM
 */
public enum ChangeFreq{

    /** 每次访问都不一样,使用这个参数 describe documents that change each time they are accessed. */
    always,

    /** The hourly. */
    hourly,

    /** The daily. */
    daily,

    /** The weekly. */
    weekly,

    /** The monthly. */
    monthly,

    /** The yearly. */
    yearly,

    /** 用来形容 archived URLs 归档url/历史url. */
    never
}
