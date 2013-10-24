/**
 * Copyright (c) 2008-2013 FeiLong Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	feilong makes no representations or warranties about the suitability of the software, either express or implied, 
 * 	including but not limited to the implied warranties of merchantability, fitness for a particular
 * 	purpose, or non-infringement.  
 * 	feilong shall not be liable for any damages suffered by licensee as a result of using, modifying or distributing
 * 	this software or its derivatives.
 * </p>
 */
package com.feilong.commons.core.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 常用的日期pattern. <blockquote>
 * <table border=0 cellspacing=3 cellpadding=0 summary="Chart shows pattern letters, date/time component, presentation, and examples.">
 * <tr bgcolor="#ccccff">
 * <th align=left>Letter
 * <th align=left>Date or Time Component
 * <th align=left>Presentation
 * <th align=left>Examples
 * <tr>
 * <td><code>G</code>
 * <td>Era designator
 * <td><a href="#text">Text</a>
 * <td><code>AD</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>y</code>
 * <td>Year
 * <td><a href="#year">Year</a>
 * <td><code>1996</code>; <code>96</code>
 * <tr>
 * <td><code>M</code>
 * <td>Month in year
 * <td><a href="#month">Month</a>
 * <td><code>July</code>; <code>Jul</code>; <code>07</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>w</code>
 * <td>Week in year
 * <td><a href="#number">Number</a>
 * <td><code>27</code>
 * <tr>
 * <td><code>W</code>
 * <td>Week in month
 * <td><a href="#number">Number</a>
 * <td><code>2</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>D</code>
 * <td>Day in year
 * <td><a href="#number">Number</a>
 * <td><code>189</code>
 * <tr>
 * <td><code>d</code>
 * <td>Day in month
 * <td><a href="#number">Number</a>
 * <td><code>10</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>F</code>
 * <td>Day of week in month
 * <td><a href="#number">Number</a>
 * <td><code>2</code>
 * <tr>
 * <td><code>E</code>
 * <td>Day in week
 * <td><a href="#text">Text</a>
 * <td><code>Tuesday</code>; <code>Tue</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>a</code>
 * <td>Am/pm marker
 * <td><a href="#text">Text</a>
 * <td><code>PM</code>
 * <tr>
 * <td><code>H</code>
 * <td>Hour in day (0-23)
 * <td><a href="#number">Number</a>
 * <td><code>0</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>k</code>
 * <td>Hour in day (1-24)
 * <td><a href="#number">Number</a>
 * <td><code>24</code>
 * <tr>
 * <td><code>K</code>
 * <td>Hour in am/pm (0-11)
 * <td><a href="#number">Number</a>
 * <td><code>0</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>h</code>
 * <td>Hour in am/pm (1-12)
 * <td><a href="#number">Number</a>
 * <td><code>12</code>
 * <tr>
 * <td><code>m</code>
 * <td>Minute in hour
 * <td><a href="#number">Number</a>
 * <td><code>30</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>s</code>
 * <td>Second in minute
 * <td><a href="#number">Number</a>
 * <td><code>55</code>
 * <tr>
 * <td><code>S</code>
 * <td>Millisecond
 * <td><a href="#number">Number</a>
 * <td><code>978</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>z</code>
 * <td>Time zone
 * <td><a href="#timezone">General time zone</a>
 * <td><code>Pacific Standard Time</code>; <code>PST</code>; <code>GMT-08:00</code>
 * <tr>
 * <td><code>Z</code>
 * <td>Time zone
 * <td><a href="#rfc822timezone">RFC 822 time zone</a>
 * <td><code>-0800</code>
 * </table>
 * </blockquote>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 21 04:18:00
 * @see SimpleDateFormat
 */
public final class DatePattern{

	/** <code>{@value}</code> 年月 带水平线,一般用于分类日志,将众多日志按月分类 example:2012-01. */
	public static final String	yearAndMonth				= "yyyy-MM";

	/** <code>{@value}</code> 只有日期 年月日 example:2012-01-22. */
	public static final String	onlyDate					= "yyyy-MM-dd";

	/** <code>{@value}</code> 月日 example:01-22. */
	public static final String	monthAndDay					= "MM-dd";

	/** <code>{@value}</code> 月日带星期 example:01-22(星期四). */
	public static final String	monthAndDayWithWeek			= "MM-dd(E)";

	/** <code>{@value}</code> 不带秒. */
	public static final String	commonWithoutSecond			= "yyyy-MM-dd HH:mm";

	/** <code>{@value}</code>. */
	public static final String	commonWithTime				= "yyyy-MM-dd HH:mm:ss";

	/** <code>{@value}</code> 带毫秒的时间格式. */
	public static final String	commonWithMillisecond		= "yyyy-MM-dd HH:mm:ss.SSS";

	/** <code>{@value}</code> 不带年 不带秒. */
	public static final String	commonWithoutAndYearSecond	= "MM-dd HH:mm";

	/** <code>{@value}</code>. */
	public static final String	yy							= "yy";

	/** <code>{@value}</code>. */
	public static final String	yyyyMMdd					= "yyyyMMdd";

	/** <code>{@value}</code>. */
	public static final String	mmss						= "mmss";

	/** <code>{@value}</code> 只有时间且不带秒. */
	public static final String	onlyTime_withoutSecond		= "HH:mm";

	/** <code>{@value}</code> 只有时间. */
	public static final String	onlyTime					= "HH:mm:ss";

	/** 时间戳,<code>{@value}</code>,一般用于拼接文件名称. */
	public static final String	timestamp					= "yyyyMMddHHmmss";

	/** 带毫秒的时间戳,<code>{@value}</code> . */
	public static final String	timestampWithMillisecond	= "yyyyMMddHHmmssSSS";

	/**
	 * 系统Date toString 使用的格式,并且 Locale.US,<br>
	 * 详见{@link Date#toString()} <code>{@value}</code> .
	 */
	public static final String	forToString					= "EEE MMM dd HH:mm:ss zzz yyyy";
}
