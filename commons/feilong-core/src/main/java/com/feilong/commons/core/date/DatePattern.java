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
package com.feilong.commons.core.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 常用的日期pattern
 * </p>
 * <blockquote>
 * <table border=1 cellspacing=0 cellpadding=4 summary="Chart shows pattern letters, date/time component, presentation, and examples.">
 * <tr bgcolor="#ccccff">
 * <th align=left>Letter</th>
 * <th align=left>Date or Time Component</th>
 * <th align=left>Presentation</th>
 * <th align=left>Examples</th>
 * </tr>
 * <tr>
 * <td><code>G</code></td>
 * <td>Era designator</td>
 * <td><a href="#text">Text</a></td>
 * <td><code>AD</code></td>
 * </tr>
 * <tr bgcolor="#eeeeff">
 * <td><code>y</code></td>
 * <td>Year</td>
 * <td><a href="#year">Year</a></td>
 * <td><code>1996</code>; <code>96</code></td>
 * </tr>
 * <tr>
 * <td><code>M</code></td>
 * <td>Month in year</td>
 * <td><a href="#month">Month</a></td>
 * <td><code>July</code>; <code>Jul</code>; <code>07</code></td>
 * </tr>
 * <tr bgcolor="#eeeeff">
 * <td><code>w</code></td>
 * <td>Week in year</td>
 * <td><a href="#number">Number</a></td>
 * <td><code>27</code></td>
 * </tr>
 * <tr>
 * <td><code>W</code></td>
 * <td>Week in month</td>
 * <td><a href="#number">Number</a></td>
 * <td><code>2</code></td>
 * </tr>
 * <tr bgcolor="#eeeeff">
 * <td><code>D</code></td>
 * <td>Day in year</td>
 * <td><a href="#number">Number</a></td>
 * <td><code>189</code></td>
 * </tr>
 * <tr>
 * <td><code>d</code></td>
 * <td>Day in month</td>
 * <td><a href="#number">Number</a></td>
 * <td><code>10</code></td>
 * </tr>
 * <tr bgcolor="#eeeeff">
 * <td><code>F</code></td>
 * <td>Day of week in month</td>
 * <td><a href="#number">Number</a></td>
 * <td><code>2</code></td>
 * </tr>
 * <tr>
 * <td><code>E</code></td>
 * <td>Day in week</td>
 * <td><a href="#text">Text</a></td>
 * <td><code>Tuesday</code>; <code>Tue</code></td>
 * </tr>
 * <tr bgcolor="#eeeeff">
 * <td><code>a</code></td>
 * <td>Am/pm marker</td>
 * <td><a href="#text">Text</a></td>
 * <td><code>PM</code></td>
 * </tr>
 * <tr>
 * <td><code>H</code></td>
 * <td>Hour in day (0-23)</td>
 * <td><a href="#number">Number</a></td>
 * <td><code>0</code></td>
 * </tr>
 * <tr bgcolor="#eeeeff">
 * <td><code>k</code></td>
 * <td>Hour in day (1-24)</td>
 * <td><a href="#number">Number</a></td>
 * <td><code>24</code></td>
 * </tr>
 * <tr>
 * <td><code>K</code></td>
 * <td>Hour in am/pm (0-11)</td>
 * <td><a href="#number">Number</a></td>
 * <td><code>0</code></td>
 * </tr>
 * <tr bgcolor="#eeeeff">
 * <td><code>h</code></td>
 * <td>Hour in am/pm (1-12)</td>
 * <td><a href="#number">Number</a></td>
 * <td><code>12</code></td>
 * </tr>
 * <tr>
 * <td><code>m</code></td>
 * <td>Minute in hour</td>
 * <td><a href="#number">Number</a></td>
 * <td><code>30</code></td>
 * </tr>
 * <tr bgcolor="#eeeeff">
 * <td><code>s</code></td>
 * <td>Second in minute</td>
 * <td><a href="#number">Number</a></td>
 * <td><code>55</code></td>
 * <tr>
 * <td><code>S</code></td>
 * <td>Millisecond</td>
 * <td><a href="#number">Number</a></td>
 * <td><code>978</code></td>
 * </tr>
 * <tr bgcolor="#eeeeff">
 * <td><code>z</code></td>
 * <td>Time zone</td>
 * <td><a href="#timezone">General time zone</a></td>
 * <td><code>Pacific Standard Time</code>; <code>PST</code>; <code>GMT-08:00</code></td>
 * </tr>
 * <tr>
 * <td><code>Z</code></td>
 * <td>Time zone</td>
 * <td><a href="#rfc822timezone">RFC 822 time zone</a></td>
 * <td><code>-0800</code></td>
 * </tr>
 * </table>
 * </blockquote>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 21 04:18:00
 * @version 1.0.5 2014-5-4 14:23 change to interface
 * @see {@link SimpleDateFormat}
 * @since 1.0.0
 */
public interface DatePattern{

	/** <code>{@value}</code> 年月 带水平线,一般用于分类日志,将众多日志按月分类 example:2012-01. */
	String	yearAndMonth				= "yyyy-MM";

	/** <code>{@value}</code> 只有日期 年月日 example:2012-01-22. */
	String	onlyDate					= "yyyy-MM-dd";

	/** <code>{@value}</code> 月日 example:01-22. */
	String	monthAndDay					= "MM-dd";

	/** <code>{@value}</code> 月日带星期 example:01-22(星期四). */
	String	monthAndDayWithWeek			= "MM-dd(E)";

	/** <code>{@value}</code> 不带秒 example:2013-12-27 22:13. */
	String	commonWithoutSecond			= "yyyy-MM-dd HH:mm";

	/** <code>{@value}</code> example:2013-12-27 22:13:55. */
	String	commonWithTime				= "yyyy-MM-dd HH:mm:ss";

	/** <code>{@value}</code> example:31/03/2014 14:53:39. */
	String	ddMMyyyyHHmmss				= "dd/MM/yyyy HH:mm:ss";

	/** <code>{@value}</code> 带毫秒的时间格式 example:2013-12-27 22:13:55.453. */
	String	commonWithMillisecond		= "yyyy-MM-dd HH:mm:ss.SSS";

	/** <code>{@value}</code> 不带年 不带秒 example:12-27 22:13. */
	String	commonWithoutAndYearSecond	= "MM-dd HH:mm";

	// *******************************************************************

	/** <code>{@value}</code> example:13. */
	String	yy							= "yy";

	/** <code>{@value}</code> example:2013. */
	String	yyyy						= "yyyy";

	/** <code>{@value}</code> MM月份 example:12. */
	String	MM							= "MM";

	/** <code>{@value}</code> example:20131227. */
	String	yyyyMMdd					= "yyyyMMdd";

	/** <code>{@value}</code> example:2156. */
	String	mmss						= "mmss";

	/** <code>{@value}</code> example:21. */
	String	HH							= "HH";

	/** <code>{@value}</code> 只有时间且不带秒 example:21:57. */
	String	onlyTime_withoutSecond		= "HH:mm";

	/** <code>{@value}</code> 只有时间 example:21:57:36. */
	String	onlyTime					= "HH:mm:ss";

	/** 时间戳,<code>{@value}</code>,一般用于拼接文件名称 example:20131227215816. */
	String	timestamp					= "yyyyMMddHHmmss";

	/** 带毫秒的时间戳,<code>{@value}</code> example:20131227215758437. */
	String	timestampWithMillisecond	= "yyyyMMddHHmmssSSS";

	/**
	 * 系统Date toString 使用的格式,并且 Locale.US,<br>
	 * example: 星期五 十二月 27 22:13:55 CST 2013 <br>
	 * 详见{@link Date#toString()} <code>{@value}</code> .
	 */
	String	forToString					= "EEE MMM dd HH:mm:ss zzz yyyy";
}
