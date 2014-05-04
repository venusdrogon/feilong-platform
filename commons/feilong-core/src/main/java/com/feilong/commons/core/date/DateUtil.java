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

import static com.feilong.commons.core.PropertiesConstants.CONFIG_DATE_DAY;
import static com.feilong.commons.core.PropertiesConstants.CONFIG_DATE_HOUR;
import static com.feilong.commons.core.PropertiesConstants.CONFIG_DATE_MILLISECOND;
import static com.feilong.commons.core.PropertiesConstants.CONFIG_DATE_MINUTE;
import static com.feilong.commons.core.PropertiesConstants.CONFIG_DATE_SECOND;
import static com.feilong.commons.core.PropertiesConstants.CONFIG_DATE_THEDAY_BEFORE_YESTERDAY;
import static com.feilong.commons.core.PropertiesConstants.CONFIG_DATE_YESTERDAY;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import com.feilong.commons.core.text.DateFormatUtil;
import com.feilong.commons.core.util.Validator;

/**
 * 日期操作工具类(feilong-core 核心类之一)<br>
 * 包括通用的:字符串转日期,日期转字符串,日期加减,获得日期某部值,获得两个日期间隔.
 * 
 * @see CalendarUtil
 * @see DatePattern
 * @see DateFormatUtil
 * @author <a href="venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-1-27 下午01:53:21
 * @since 1.0
 */
public final class DateUtil{

	/** Don't let anyone instantiate this class. */
	private DateUtil(){}

	/**
	 * 按照同样格式,转成Date类型,判断 date_before 是否早于date_after<br>
	 * 如:
	 * 
	 * <pre>
	 * isBefore("2011-05-01","2011-04-01",DateUtil.pattern_onlyDate)
	 * return true
	 * </pre>
	 * 
	 * @param date_before
	 *            date_before
	 * @param date_after
	 *            date_after
	 * @param datePattern
	 *            pattern @see {@link DatePattern}
	 * @return 如果date_before 早于 date_after返回 true
	 */
	public final static boolean isBefore(String date_before,String date_after,String datePattern){
		Date before = DateUtil.string2Date(date_before, datePattern);
		return isBefore(before, date_after, datePattern);
	}

	/**
	 * 按照同样格式,转成Date类型,判断 date_before 是否早于date_after<br>
	 * 如:
	 * 
	 * <pre>
	 * isBefore("2011-05-01","2011-04-01",DateUtil.pattern_onlyDate)
	 * return true
	 * </pre>
	 * 
	 * @param before
	 *            before
	 * @param date_after
	 *            date_after
	 * @param datePattern
	 *            pattern @see {@link DatePattern}
	 * @return 如果before 早于 date_after返回 true
	 */
	public final static boolean isBefore(Date before,String date_after,String datePattern){
		Date after = DateUtil.string2Date(date_after, datePattern);
		return before.before(after);
	}

	/**
	 * 获得当天0:00:00及下一天0:00:00,一般用于统计当天数据,between ... and ...
	 * 
	 * <pre>
	 * 比如今天是 2012-10-16 22:18:34
	 * 
	 * return {2012-10-16 00:00:00.000,2012-10-17 00:00:00.000}
	 * </pre>
	 * 
	 * @return Date数组 第一个为today 第二个为tomorrow
	 * @since 1.0
	 */
	public final static Date[] getExtentToday(){
		Calendar calendar = CalendarUtil.getResetTodayCalendar_byDay();
		Date today = calendar.getTime();
		// ***************************
		calendar.add(Calendar.DATE, 1);
		Date tomorrow = calendar.getTime();
		Date[] dates = { today, tomorrow };
		return dates;
	}

	/**
	 * 获得昨天(日期的前一天的此时此刻)
	 * 
	 * <pre>
	 * 仅对天数-1,其余时间部分不做任何处理 
	 * 
	 * 比如 现在 2012-10-16 22:43:06 
	 * return 2012-10-15 22:43:06.169
	 * </pre>
	 * 
	 * @param date
	 *            date
	 * @return 获得昨天/ 日期的前一天
	 * @since 1.0
	 */
	public final static Date getYesterday(Date date){
		Calendar calendar = toCalendar(date);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * 获得昨天的区间 [yestoday,today]<br>
	 * 第一个为昨天00:00 <br>
	 * 第二个为今天00:00 <br>
	 * 一般用于sql/hql统计昨天数据,between ... and ...
	 * 
	 * <pre>
	 * 比如现在 :2012-10-16 22:46:42
	 * 
	 * return  {2012-10-15 00:00:00.000,2012-10-16 00:00:00.000}
	 * </pre>
	 * 
	 * @return Date数组 <br>
	 *         第一个为昨天00:00 <br>
	 *         第二个为今天00:00
	 * @since 1.0
	 */
	public final static Date[] getExtentYesterday(){
		Calendar calendar = CalendarUtil.getResetTodayCalendar_byDay();
		Date today = calendar.getTime();
		calendar.add(Calendar.DATE, -1);
		Date yesterday = calendar.getTime();
		Date[] dates = { yesterday, today };
		return dates;
	}

	/**
	 * 获得传入date 所在星期的第一天(周日) 0:0:0:0 到毫秒<br>
	 * 注意:按照外国制,周日为一个星期第一天,周六为最后一天
	 * 
	 * <pre>
	 * 如果 现在是 2012-10-11 17:10:30.701 (周四),
	 * 
	 * return  2012-10-07 00:00:00.000
	 * </pre>
	 * 
	 * @param date
	 *            the date
	 * @return Date
	 * @since 1.0
	 */
	public final static Date getFirstDateOfThisWeek(Date date){
		Calendar calendar = toCalendar(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		dayBegin(calendar);
		return calendar.getTime();
	}

	/**
	 * 获得传入date 所在星期的最后一天(周六) 23:59:59.999 到毫秒<br>
	 * 注意:按照外国制,周日为一个星期第一天,周六为最后一天
	 * 
	 * <pre>
	 * 如果 现在是 2012-10-11 17:10:30.701 (周四),
	 * 
	 * return 2012-10-13 23:59:59.999
	 * </pre>
	 * 
	 * @param date
	 *            任意date
	 * @return 传入date 所在星期的最后一天 23:59:59.999 到毫秒
	 * @since 1.0.1
	 */
	public final static Date getLastDateOfThisWeek(Date date){
		Calendar calendar = toCalendar(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		dayEnd(calendar);
		return calendar.getTime();
	}

	/**
	 * 获得当天所在月的第一天,0:0:0:0 到毫秒<br>
	 * 
	 * <pre>
	 * 如果 现在是 2012-10-11 17:10:30.701 (周四),
	 * 
	 * return 2012-10-01 00:00:00
	 * </pre>
	 * 
	 * @param date
	 *            the date
	 * @return Date
	 * @since 1.0
	 */
	public final static Date getFirstDateOfThisMonth(Date date){
		Calendar calendar = toCalendar(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		dayBegin(calendar);
		return calendar.getTime();
	}

	/**
	 * 获得当天所在月的最后一天 23:59:59.999 到毫秒<br>
	 * 
	 * <pre>
	 * 如果 现在是 2012-10-11 17:10:30.701,
	 * 
	 * return 2012-10-31 23:59:59.999
	 * </pre>
	 * 
	 * @param date
	 *            the date
	 * @return Date
	 * @since 1.0
	 */
	public final static Date getLastDateOfThisMonth(Date date){
		Calendar calendar = toCalendar(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		dayEnd(calendar);
		return calendar.getTime();
	}

	/**
	 * 获得指定日期所在年的第一天,0:0:0:0 到毫秒
	 * 
	 * <pre>
	 * 如果 现在是 2012-10-11 17:10:30.701,
	 * 
	 * return 2012-01-01 00:00:00
	 * </pre>
	 * 
	 * @param date
	 *            指定日期
	 * @return date
	 * @since 1.0
	 */
	public final static Date getFirstDateOfThisYear(Date date){
		Calendar calendar = toCalendar(date);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		dayBegin(calendar);
		return calendar.getTime();
	}

	/**
	 * 获得当天所在年的最后一天 23:59:59.999 到毫秒<br>
	 * 
	 * <pre>
	 * 如果 现在是 2012-10-11 17:10:30.701,
	 * 
	 * return 2012-12-31 23:59:59.999
	 * </pre>
	 * 
	 * @param date
	 *            任意date
	 * @return Date
	 * @since 1.0
	 */
	public final static Date getLastDateOfThisYear(Date date){
		Calendar calendar = toCalendar(date);
		calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		dayEnd(calendar);
		return calendar.getTime();
	}

	/**
	 * 一天开始,0:0:0.0
	 * 
	 * @param calendar
	 *            the calendar
	 */
	private static void dayBegin(Calendar calendar){
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}

	/**
	 * 一天结束,最后的时间 23:59:59.999
	 * 
	 * @param calendar
	 *            the calendar
	 */
	private static void dayEnd(Calendar calendar){
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
	}

	/**
	 * 判断当前时间是否在两个时间之间
	 * 
	 * <pre>
	 * 比如现在是 :2012-10-16 23:00:02
	 * 
	 * isInTime(date, "2012-10-10 22:59:00", "2012-10-18 22:59:00", DatePattern.commonWithTime)
	 * 
	 * return true
	 * </pre>
	 * 
	 * @param date
	 *            需要判断的日期
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param datePattern
	 *            开始时间和结束时间的格式 @see {@link DatePattern}
	 * @return 如果 date after beginTimeDate&&当前时间before endTimeDate,返回true
	 */
	public final static boolean isInTime(Date date,String beginTime,String endTime,String datePattern){
		Date beginTimeDate = string2Date(beginTime, datePattern);
		Date endTimeDate = string2Date(endTime, datePattern);
		return isInTime(date, beginTimeDate, endTimeDate);
	}

	/**
	 * 判断当前时间是否在两个时间之间
	 * 
	 * <pre>
	 * 比如现在是 :2012-10-16 23:00:02
	 * 
	 * isInTime(date, "2012-10-10 22:59:00", "2012-10-18 22:59:00")
	 * 
	 * return true
	 * </pre>
	 * 
	 * .
	 * 
	 * @param date
	 *            需要判断的日期
	 * @param beginTimeDate
	 *            the begin time date
	 * @param endTimeDate
	 *            the end time date
	 * @return 如果 date after beginTimeDate&&当前时间before endTimeDate,返回true
	 */
	public final static boolean isInTime(Date date,Date beginTimeDate,Date endTimeDate){
		boolean flag = date.after(beginTimeDate) && date.before(endTimeDate);
		return flag;
	}

	// [start] 时间操作(加减)
	/**
	 * *********************** 时间操作(加减) ****************************************.
	 * 
	 * @param date
	 *            the date
	 * @param year
	 *            the year
	 * @return the date
	 */
	/**
	 * 指定时间,加减年份(仅仅年进行加减)
	 * 
	 * <pre>
	 * addYear(2012-06-29 00:33:05,5)
	 * return 20<span style="color:red">17</span>-06-29 00:33:05
	 * 
	 * addYear(2012-06-29 00:33:05,-5)
	 * return 20<span style="color:red">07</span>-06-29 00:33:05
	 * </pre>
	 * 
	 * @param date
	 *            指定时间
	 * @param year
	 *            增加年份 可以是负数 表示前面多少
	 * @return 加减年份后的时间
	 * @since 1.0
	 * @see #operateDate(Date, int, int)
	 */
	public final static Date addYear(Date date,int year){
		return operateDate(date, Calendar.YEAR, year);
	}

	/**
	 * 指定时间加减月份,(仅仅月进行加减)
	 * 
	 * <pre>
	 * addMonth(2012-10-16 23:12:43,5)
	 * return 2013-03-16 23:12:43.932
	 * 
	 * addMonth(2012-10-16 23:12:43,-5)
	 * return 2012-05-16 23:12:43.943
	 * </pre>
	 * 
	 * @param date
	 *            指定时间
	 * @param month
	 *            加减月份 可以是负数 表示前面多少<br>
	 *            比如-3 表示 3个月之前
	 * @return 加减月份后的时间
	 * @see #operateDate(Date, int, int)
	 */
	public final static Date addMonth(Date date,int month){
		return operateDate(date, Calendar.MONTH, month);
	}

	/**
	 * 指定时间加减天数,(仅仅天进行加减)
	 * 
	 * <pre>
	 * addDay(2012-06-29 00:42:26,5)
	 * return 2012-07-04 00:42:26
	 * 
	 * addDay(2012-06-29 00:42:26,-5)
	 * return 2012-06-24 00:42:26
	 * </pre>
	 * 
	 * .
	 * 
	 * @param date
	 *            指定时间
	 * @param day
	 *            需要加减的天数 可以为负数
	 * @return 日期加减天数
	 * @since 1.0
	 * @see #operateDate(Date, int, int)
	 */
	public final static Date addDay(Date date,int day){
		return operateDate(date, Calendar.DAY_OF_MONTH, day);
	}

	/**
	 * 日期加减星期 WEEK_OF_YEAR
	 * 
	 * <pre>
	 * addWeek(2012-06-29 00:45:18,5)
	 * return 2012-08-03 00:45:18
	 * 
	 * addWeek(2012-06-29 00:45:18,-5)
	 * return 2012-05-25 00:45:18
	 * </pre>
	 * 
	 * .
	 * 
	 * @param date
	 *            指定时间
	 * @param week
	 *            需要加减的星期数 可以为负数
	 * @return 指定时间加减星期
	 * @since 1.0
	 * @see #operateDate(Date, int, int)
	 */
	public final static Date addWeek(Date date,int week){
		return operateDate(date, Calendar.WEEK_OF_YEAR, week);
	}

	/**
	 * 日期加减小时 Calendar.HOUR_OF_DAY 24小时制
	 * 
	 * <pre>
	 * addHour(2012-06-29 00:46:24,5)
	 * return 2012-06-29 05:46:24
	 * 
	 * addHour(2012-06-29 00:46:24,-5)
	 * return 2012-06-28 19:46:24
	 * </pre>
	 * 
	 * @param date
	 *            the date
	 * @param hour
	 *            the hour
	 * @return the date
	 * @see #operateDate(Date, int, int)
	 */
	public final static Date addHour(Date date,int hour){
		return operateDate(date, Calendar.HOUR_OF_DAY, hour);
	}

	/**
	 * 日期加减分钟 Calendar.MINUTE
	 * 
	 * <pre>
	 * addMinute(2012-10-16 23:20:33,180)
	 * return 2012-10-17 02:20:33.669
	 * 
	 * addMinute(2012-10-16 23:20:33,-180)
	 * return 2012-10-16 20:20:33.669
	 * </pre>
	 * 
	 * @param date
	 *            the date
	 * @param minute
	 *            the minute
	 * @return the date
	 * @see #operateDate(Date, int, int)
	 */
	public final static Date addMinute(Date date,int minute){
		return operateDate(date, Calendar.MINUTE, minute);
	}

	/**
	 * 日期加减秒 Calendar.SECOND
	 * 
	 * <pre>
	 * addSecond(2012-10-16 23:22:02,180)
	 * return 2012-10-16 23:25:02.206
	 * 
	 * addSecond(2012-10-16 23:22:02,-180)
	 * return 2012-10-16 23:19:02.206
	 * </pre>
	 * 
	 * @param date
	 *            任意时间
	 * @param second
	 *            加减秒
	 * @return the date
	 * @see #operateDate(Date, int, int)
	 */
	public final static Date addSecond(Date date,int second){
		return operateDate(date, Calendar.SECOND, second);
	}

	/**
	 * 底层操作时间的方法 <br>
	 * 根据日历的规则，为给定的日历字段添加或减去指定的时间量.
	 * 
	 * @param currentDate
	 *            当前date
	 * @param field
	 *            日历字段
	 * @param amount
	 *            为字段添加的日期或时间量,可以为负数
	 * @return 底层操作时间的方法 根据日历的规则，为给定的日历字段添加或减去指定的时间量
	 * @see #addYear(Date, int)
	 * @see #addMonth(Date, int)
	 * @see #addWeek(Date, int)
	 * @see #addDay(Date, int)
	 * @see #addHour(Date, int)
	 * @see #addMinute(Date, int)
	 * @see #addSecond(Date, int)
	 * @since 1.0
	 */
	public final static Date operateDate(Date currentDate,int field,int amount){
		Calendar calendar = toCalendar(currentDate);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	// [end]
	// [start] 底层取到数据
	/**
	 * 获得任意日期中的年份部分
	 * 
	 * <pre>
	 * 2012-06-29
	 * return 2012
	 * </pre>
	 * 
	 * .
	 * 
	 * @param date
	 *            the date
	 * @return 获得任意日期中的年份部分
	 * @since 1.0
	 * @see CalendarUtil#getCalendarFieldValue(Date, int)
	 */
	public final static int getYear(Date date){
		return CalendarUtil.getCalendarFieldValue(date, Calendar.YEAR);
	}

	/**
	 * 获得任意日期中的月份(已经+1处理)<br>
	 * 
	 * <pre>
	 * 2012-06-29
	 * return 6
	 * </pre>
	 * 
	 * .
	 * 
	 * @param date
	 *            the date
	 * @return 获得任意日期中的月份
	 * @since 1.0
	 * @see CalendarUtil#getCalendarFieldValue(Date, int)
	 */
	public final static int getMonth(Date date){
		return CalendarUtil.getCalendarFieldValue(date, Calendar.MONTH) + 1;
	}

	/**
	 * 获得任意时间中的天(在当年中)<br>
	 * 
	 * <pre>
	 * 2013-01-01
	 * return 1
	 * 
	 * 2013-01-05
	 * return 5
	 * </pre>
	 * 
	 * .
	 * 
	 * @param date
	 *            the date
	 * @return 获得任意时间中的天(在当年中)
	 * @since 1.0.2
	 */
	public final static int getDayOfYear(Date date){
		Date firstDateOfThisYear = getFirstDateOfThisYear(date);
		return getIntervalDay(date, firstDateOfThisYear) + 1;
	}

	/**
	 * 获得任意时间中的天<br>
	 * 
	 * <pre>
	 * 2012-06-29
	 * return 29
	 * </pre>
	 * 
	 * .
	 * 
	 * @param date
	 *            the date
	 * @return 获得任意时间中的天
	 * @since 1.0
	 * @see CalendarUtil#getCalendarFieldValue(Date, int)
	 */
	public final static int getDayOfMonth(Date date){
		return CalendarUtil.getCalendarFieldValue(date, Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获得当前日期星期几<br>
	 * 从星期天开始,并且星期天是1<br>
	 * 
	 * <pre>
	 * 2012-6-29    是星期5
	 * return 6
	 * </pre>
	 * 
	 * .
	 * 
	 * @param date
	 *            the date
	 * @return 当前日期星期几
	 * @see Calendar#SUNDAY
	 * @see Calendar#MONDAY
	 * @see Calendar#TUESDAY
	 * @see Calendar#WEDNESDAY
	 * @see Calendar#THURSDAY
	 * @see Calendar#FRIDAY
	 * @see Calendar#SATURDAY
	 * @see CalendarUtil#getCalendarFieldValue(Date, int)
	 */
	public final static int getDayOfWeek(Date date){
		return CalendarUtil.getCalendarFieldValue(date, Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获得时间中的小时(24小时制)<br>
	 * 
	 * <pre>
	 * 2012-6-29 00:26:53
	 * return 0
	 * </pre>
	 * 
	 * .
	 * 
	 * @param date
	 *            date
	 * @return 获得时间中的小时
	 * @since 1.0
	 * @see CalendarUtil#getCalendarFieldValue(Date, int)
	 */
	public final static int getHourOfDay(Date date){
		return CalendarUtil.getCalendarFieldValue(date, Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获得date 在它一年中的 小时数<br>
	 * max value: 8784
	 * 
	 * <pre>
	 * 
	 * 2013-01-01 00:00:05
	 * return 0
	 * 
	 * 2013-01-01 01:00:05
	 * return 1
	 * 
	 * 2013-01-05 12:00:05
	 * return 108
	 * 
	 * 2013-09-09 17:28
	 * return 6041
	 * </pre>
	 * 
	 * .
	 * 
	 * @param date
	 *            date
	 * @return 获得date 在它一年中的 小时数
	 * @since 1.0.2
	 */
	public final static int getHourOfYear(Date date){
		Date firstDateOfThisYear = getFirstDateOfThisYear(date);
		return getIntervalHour(firstDateOfThisYear, date);
	}

	/**
	 * 获得时间中的分钟
	 * 
	 * <pre>
	 * 2012-6-29 00:26:53
	 * return 26
	 * </pre>
	 * 
	 * .
	 * 
	 * @param date
	 *            date
	 * @return 获得时间中的分钟
	 * @since 1.0
	 * @see CalendarUtil#getCalendarFieldValue(Date, int)
	 */
	public final static int getMinute(Date date){
		return CalendarUtil.getCalendarFieldValue(date, Calendar.MINUTE);
	}

	/**
	 * 获得时间中的秒
	 * 
	 * <pre>
	 * 2012-6-29 00:26:53
	 * return 53
	 * </pre>
	 * 
	 * .
	 * 
	 * @param date
	 *            date
	 * @return 获得时间中的秒
	 * @since 1.0
	 * @see CalendarUtil#getCalendarFieldValue(Date, int)
	 */
	public final static int getSecond(Date date){
		return CalendarUtil.getCalendarFieldValue(date, Calendar.SECOND);
	}

	/**
	 * 获得时间在当天中的秒数,最大值86400 {@link TimeInterval#SECONDS_PER_DAY}
	 * 
	 * <pre>
	 * 2013-09-09 16:42:41
	 * return 60161
	 * </pre>
	 * 
	 * .
	 * 
	 * @param date
	 *            date
	 * @return 获得当前时间在当天中的秒数
	 * @since 1.0.2
	 */
	public final static int getSecondOfDay(Date date){
		int hour = getHourOfDay(date);
		return hour * TimeInterval.SECONDS_PER_HOUR + getSecondOfHour(date);
	}

	/**
	 * 获得时间在当前小时中的秒数,最大值3600 {@link TimeInterval#SECONDS_PER_HOUR}
	 * 
	 * <pre>
	 * 2013-09-15 01:15:23
	 * return 923
	 * </pre>
	 * 
	 * .
	 * 
	 * @param date
	 *            date
	 * @return 获得时间在当前小时中的秒数
	 * @since 1.0.2
	 */
	public final static int getSecondOfHour(Date date){
		int minute = getMinute(date);
		int second = getSecond(date);
		return second + minute * 60;
	}

	/**
	 * 返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数
	 * 
	 * <pre>
	 * 2012-6-29 00:28
	 * return 1340900883288
	 * </pre>
	 * 
	 * .
	 * 
	 * @param date
	 *            date
	 * @return date.getTime()
	 * @since 1.0
	 */
	public final static long getTime(Date date){
		return date.getTime();
	}

	// [end]
	/**
	 * 判断某年是否为闰年
	 * 
	 * <pre>
	 * 最根本的原因是：地球绕太阳运行周期为365天5小时48分46秒（合365.24219天）即一回归年（tropical year）。
	 *  
	 * 公历的平年只有365日，比回归年短约0.2422日，所余下的时间约为四年累计一天，故四年于2月加1天，使当年的历年长度为366日，这一年就为闰年。
	 *  		
	 * 现行公历中每400年有97个闰年。按照每四年一个闰年计算，平均每年就要多算出0.0078天，这样经过四百年就会多算出大约3天来，
	 * 因此，每四百年中要减少三个闰年。
	 * 
	 * 所以规定，公历年份是整百数的，必须是400的倍数的才是闰年，不是400的倍数的,虽然是100的倍数，也是平年,
	 * 这就是通常所说的：四年一闰，百年不闰，四百年再闰。
	 * 
	 * 例如，2000年是闰年，1900年则是平年。
	 * </pre>
	 * 
	 * @param year
	 *            年份
	 * @return 四年一闰，百年不闰，四百年再闰
	 * @since 1.0
	 * @see GregorianCalendar#isLeapYear(int)
	 */
	public final static boolean isLeapYear(int year){
		// GregorianCalendar calendar = new GregorianCalendar();
		// calendar.isLeapYear(year);

		return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
	}

	// [start] 类型转换

	/**
	 * 不带参数pattern的 的method,采用默认 pattern--->{@link DatePattern#commonWithTime}.
	 * 
	 * @param date
	 *            任意时间
	 * @return use {@link DatePattern#commonWithTime} format
	 */
	public final static String date2String(Date date){
		String defaultPattern = DatePattern.commonWithTime;
		return date2String(date, defaultPattern);
	}

	/**
	 * 将时间转换成特殊格式的字符串
	 * 
	 * <pre>
	 * date2String(Tue Oct 16 23:49:21 CST 2012,DatePattern.commonWithMillisecond)
	 * return 2012-10-16 23:49:21.525
	 * </pre>
	 * 
	 * @param date
	 *            任意时间
	 * @param datePattern
	 *            模式 @see {@link DatePattern}
	 * @return string
	 * @since 1.0
	 * @see DateFormatUtil#format(Date, String)
	 */
	public final static String date2String(Date date,String datePattern){
		return DateFormatUtil.format(date, datePattern);
	}

	/**
	 * 将日期集合装成特定pattern的字符串集合.
	 * 
	 * @param dateList
	 *            日期集合
	 * @param datePattern
	 *            模式 @see {@link DatePattern}
	 * @return 如果 Validator.isNotNullOrEmpty(dateList) return null;<br>
	 *         否则循环date转成string,返回List<String>
	 * @since 1.0
	 */
	public final static List<String> dateList2StringList(List<Date> dateList,String datePattern){
		if (Validator.isNotNullOrEmpty(dateList)){
			List<String> stringList = new LinkedList<String>();
			for (Date date : dateList){
				stringList.add(date2String(date, datePattern));
			}
			return stringList;
		}
		return null;
	}

	/**
	 * 将时间string字符串转换成date类型.
	 * 
	 * @param dateString
	 *            时间字符串
	 * @param datePattern
	 *            模式,时间字符串的模式 @see {@link DatePattern}
	 * @return 将string字符串转换成date类型
	 * @since 1.0
	 * @see DateFormatUtil#parse(String, String)
	 */
	public final static Date string2Date(String dateString,String datePattern){
		return DateFormatUtil.parse(dateString, datePattern);
	}

	/**
	 * 人性化显示date时间,依据是和现在的时间间隔
	 * <p>
	 * 转换规则,将传入的inDate和 new Date()当前时间比较<br>
	 * 当两者的时间差,(一般inDate小于当前时间 ,暂时不支持大于当前时间)
	 * <ul>
	 * <li>如果时间差为0天,<br>
	 * 如果小时间隔等于0,如果分钟间隔为0,则显示间隔秒 + "秒钟前"<br>
	 * 如果小时间隔等于0,如果分钟间隔不为0,则显示间隔分钟 + "分钟前"<br>
	 * </li>
	 * <li>如果时间差为0天,<br>
	 * 如果小时间隔不等于0,如果inDate的day 和current的day 相等,则显示space_hour + "小时前"<br>
	 * 如果小时间隔不等于0,如果inDate的day 和current的day不相等,则显示"昨天 " + date2String(inDate, "HH:mm")<br>
	 * </li>
	 * <li>如果时间差为1天,且inDate的day+1和currentDate的day 相等,则显示"昨天 HH:mm"</li>
	 * <li>如果时间差为1天,且inDate的day+1和currentDate的day 不相等,则显示"前天 HH:mm"</li>
	 * <li>如果时间差为2天,且inDate的day+2和currentDate的day 相等,则显示"前天 HH:mm"</li>
	 * <li>如果时间差为2天,且inDate的day+2和currentDate的day 不相等,<br>
	 * 1).如果inDate的year和currentDate的year相等,则显示"MM-dd HH:mm"<br>
	 * 2).如果inDate的year和currentDate的year不相等,则显示"yyyy-MM-dd HH:mm"</li>
	 * <li>如果时间差大于2天<br>
	 * 1).如果inDate的year和currentDate的year相等,则显示"MM-dd HH:mm"<br>
	 * 2).如果inDate的year和currentDate的year不相等,则显示"yyyy-MM-dd HH:mm"</li> </li>
	 * </ul>
	 * 
	 * @param inDate
	 *            任意日期<br>
	 *            <i>warn:一般inDate<=当前时间 ,暂时不支持大于当前时间</i>
	 * @return 人性化显示date时间
	 * @see #date2String(Date, String)
	 * @see #getYear(Date)
	 * @see #getDayOfMonth(Date)
	 * @see #getYear(Date)
	 * @see #getIntervalTime(Date, Date)
	 * @see #getIntervalDay(long)
	 * @see #getIntervalHour(long)
	 * @see #getIntervalMinute(long)
	 * @see #getIntervalSecond(long)
	 * @since 1.0
	 */
	public final static String toHumanizationDateString(Date inDate){
		Date nowDate = new Date();
		/**************************************************************************************/
		String returnValue = null;
		// 传过来的日期的年份
		int inYear = getYear(inDate);
		// 传过来的日期的月份
		// int inMonth = getMonth(inDate);
		// 传过来的日期的日
		int inDay = getDayOfMonth(inDate);
		/**************************************************************************************/
		// 当前时间的年
		int year = getYear(nowDate);
		// 当前时间的余额
		// int month = getMonth();
		// 当前时间的日
		int day = getDayOfMonth(nowDate);
		/**************************************************************************************/
		// 任意日期和现在相差的毫秒数
		long space_time = getIntervalTime(inDate, nowDate);
		// 相差天数
		int space_day = getIntervalDay(space_time);
		// 相差小时数
		int space_hour = getIntervalHour(space_time);
		// 相差分数
		int space_minute = getIntervalMinute(space_time);
		// 相差秒数
		int space_second = getIntervalSecond(space_time);
		/**************************************************************************************/
		// 间隔一天
		if (space_day == 1){
			if (isEqual(addDay(inDate, 1), nowDate, DatePattern.onlyDate)){
				returnValue = CONFIG_DATE_YESTERDAY + " ";
			}else{
				returnValue = CONFIG_DATE_THEDAY_BEFORE_YESTERDAY + " ";
			}
			returnValue += date2String(inDate, DatePattern.onlyTime_withoutSecond);
		}
		// 间隔2天
		else if (space_day == 2){
			if (isEqual(addDay(inDate, 2), nowDate, DatePattern.onlyDate)){
				returnValue = CONFIG_DATE_THEDAY_BEFORE_YESTERDAY + " " + date2String(inDate, DatePattern.onlyTime_withoutSecond);
			}else{
				// 今年
				if (year == inYear){
					returnValue = date2String(inDate, DatePattern.commonWithoutAndYearSecond);
				}else{
					returnValue = date2String(inDate, DatePattern.commonWithoutSecond);
				}
			}
		}
		// 间隔大于2天
		else if (space_day > 2){
			// 今年
			if (year == inYear){
				returnValue = date2String(inDate, DatePattern.commonWithoutAndYearSecond);
			}else{
				returnValue = date2String(inDate, DatePattern.commonWithoutSecond);
			}
		}
		// 间隔0天
		else if (space_day == 0){
			// 小时间隔
			if (space_hour != 0){
				if (inDay == day){
					returnValue = space_hour + CONFIG_DATE_HOUR + "前";
				}else{
					returnValue = CONFIG_DATE_YESTERDAY + " " + date2String(inDate, DatePattern.onlyTime_withoutSecond);
				}
			}else{
				// 分钟间隔
				if (space_minute == 0){
					returnValue = space_second + CONFIG_DATE_SECOND + "前";
				}else{
					returnValue = space_minute + CONFIG_DATE_MINUTE + "前";
				}
			}
		}
		return returnValue;
	}

	// [end]
	// [start] 转换成特色时间
	/**
	 * 在相同格式下,判断两个日期是否相等.
	 * 
	 * @param date1
	 *            日期1
	 * @param date2
	 *            日期2
	 * @param datePattern
	 *            格式 @see {@link DatePattern}
	 * @return 相等返回true,不相等则为false
	 * @since 1.0
	 */
	public final static boolean isEqual(Date date1,Date date2,String datePattern){
		return date2String(date1, datePattern).equals(date2String(date2, datePattern));
	}

	/**
	 * 获得两日期之间的间隔,并且转换成直观的表示方式
	 * 
	 * <pre>
	 * getIntervalForView(2011-05-19 8:30:40,2011-05-19 11:30:24) 
	 * return 转换成2小时59分44秒
	 * 
	 * getIntervalForView(2011-05-19 11:31:25.456,2011-05-19 11:30:24.895)
	 * return 1分钟1秒 
	 * 
	 * 自动增加 天,小时,分钟,秒,毫秒中文文字
	 * </pre>
	 * 
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间2
	 * @return 获得两日期之间的间隔,并且转换成直观的表示方式
	 * @since 1.0
	 * @see #getIntervalForView(long)
	 */
	public final static String getIntervalForView(Date date1,Date date2){
		long space_time = DateUtil.getIntervalTime(date1, date2);
		return getIntervalForView(space_time);
	}

	/**
	 * 将时间(单位毫秒),并且转换成直观的表示方式
	 * 
	 * <pre>
	 * getIntervalForView(13516)
	 * return 13秒516毫秒
	 * 
	 * getIntervalForView(0)
	 * return 0
	 * 
	 * 自动增加 天,小时,分钟,秒,毫秒中文文字
	 * </pre>
	 * 
	 * .
	 * 
	 * @param space_time
	 *            单位毫秒
	 * @return 将时间(单位毫秒),并且转换成直观的表示方式<br>
	 *         如果 space_time 是0 直接返回0
	 * @since 1.0
	 */
	public final static String getIntervalForView(long space_time){
		if (0 == space_time){
			return "0";
		}
		// **************************************************************************************
		// 间隔天数
		long space_day = getIntervalDay(space_time);
		// 间隔小时 减去间隔天数后,
		long space_hour = getIntervalHour(space_time) - space_day * 24;
		// 间隔分钟 减去间隔天数及间隔小时后,
		long space_minute = getIntervalMinute(space_time) - (space_day * 24 + space_hour) * 60;
		// 间隔秒 减去间隔天数及间隔小时,间隔分钟后,
		long space_second = getIntervalSecond(space_time) - ((space_day * 24 + space_hour) * 60 + space_minute) * 60;
		// 间隔秒 减去间隔天数及间隔小时,间隔分钟后,
		long space_millisecond = space_time - (((space_day * 24 + space_hour) * 60 + space_minute) * 60 + space_second) * 1000;
		// **************************************************************************************
		StringBuilder stringBuilder = new StringBuilder();
		if (0 != space_day){
			stringBuilder.append(space_day + CONFIG_DATE_DAY);
		}
		if (0 != space_hour){
			stringBuilder.append(space_hour + CONFIG_DATE_HOUR);
		}
		if (0 != space_minute){
			stringBuilder.append(space_minute + CONFIG_DATE_MINUTE);
		}
		if (0 != space_second){
			stringBuilder.append(space_second + CONFIG_DATE_SECOND);
		}
		if (0 != space_millisecond){
			stringBuilder.append(space_millisecond + CONFIG_DATE_MILLISECOND);
		}
		return stringBuilder.toString();
	}

	/**
	 * 两个时间相差的小时数.
	 * 
	 * @param space_time
	 *            间隔毫秒
	 * @return 相差的小时数
	 * @since 1.0
	 */
	public final static int getIntervalHour(long space_time){
		// 相差小时数
		return (int) (space_time / (60 * 60 * 1000));
	}

	/**
	 * 两个时间相差的分数.
	 * 
	 * @param space_time
	 *            间隔毫秒
	 * @return 相差的分数
	 * @since 1.0
	 */
	public final static int getIntervalMinute(long space_time){
		// 相差分数
		return (int) (space_time / (60 * 1000));
	}

	/**
	 * 两个时间相差的秒数.
	 * 
	 * @param space_time
	 *            间隔毫秒
	 * @return 相差的秒数
	 * @since 1.0
	 */
	public final static int getIntervalSecond(long space_time){
		// 相差秒数
		return (int) (space_time / 1000);
	}

	/**
	 * 两个时间相差的秒数.
	 * 
	 * @param date1
	 *            the date1
	 * @param date2
	 *            the date2
	 * @return 相差的秒数
	 * @since 1.0.2
	 */
	public final static int getIntervalSecond(Date date1,Date date2){
		// 相差秒数
		return getIntervalSecond(getIntervalTime(date1, date2));
	}

	/**
	 * 两个时间相差的的小时数.
	 * 
	 * @param date1
	 *            date1
	 * @param date2
	 *            date2
	 * @return 相差的小时数
	 * @since 1.0
	 */
	public final static int getIntervalHour(Date date1,Date date2){
		return getIntervalHour(getIntervalTime(date1, date2));
	}

	/**
	 * 两个时间相差的天数.
	 * 
	 * @param space_time
	 *            间隔毫秒
	 * @return 相差的天数
	 * @since 1.0
	 */
	public final static int getIntervalDay(long space_time){
		// 相差天数
		return (int) (space_time / (24 * 60 * 60 * 1000));
	}

	/**
	 * 两个时间相差的的天数.
	 * 
	 * @param date1
	 *            date1
	 * @param date2
	 *            date2
	 * @param datePattern
	 *            时间模式 @see {@link DatePattern}
	 * @return 相差的天数
	 * @since 1.0
	 */
	public final static int getIntervalDay(String date1,String date2,String datePattern){
		Date date_1 = string2Date(date1, datePattern);
		Date date_2 = string2Date(date2, datePattern);
		return getIntervalDay(getIntervalTime(date_1, date_2));
	}

	/**
	 * 两个时间相差的的天数.
	 * 
	 * @param date1
	 *            date1
	 * @param date2
	 *            date2
	 * @return 相差的天数
	 * @since 1.0
	 */
	public final static int getIntervalDay(Date date1,Date date2){
		return getIntervalDay(getIntervalTime(date1, date2));
	}

	/**
	 * 获得两个日期时间的日期间隔时间集合,用于统计日报表<br>
	 * 返回的List 为 LinkedList,日期按照顺序排列<br>
	 * 每天的日期会被重置清零 0:0:0
	 * 
	 * <pre>
	 * getIntervalDayList("2011-03-5 23:31:25.456","2011-03-10 01:30:24.895", DatePattern.commonWithTime)
	 * 
	 * return
	 * 2011-03-05 00:00:00
	 * 2011-03-06 00:00:00
	 * 2011-03-07 00:00:00
	 * 2011-03-08 00:00:00
	 * 2011-03-09 00:00:00
	 * 2011-03-10 00:00:00
	 * 
	 * </pre>
	 * 
	 * @param fromDate
	 *            开始时间
	 * @param toDate
	 *            结束时间
	 * @param datePattern
	 *            时间模式 @see {@link DatePattern}
	 * @return the interval day list
	 * @since 1.0
	 * @see #getIntervalDay(Date, Date)
	 */
	public final static List<Date> getIntervalDayList(String fromDate,String toDate,String datePattern){
		List<Date> dateList = new LinkedList<Date>();
		/***************************************************************/
		Date begin_Date = string2Date(fromDate, datePattern);
		Date end_Date = string2Date(toDate, datePattern);
		// ******重置时间********
		Date beginDateReset = CalendarUtil.getResetDate_byDay(begin_Date);
		Date endDateReset = CalendarUtil.getResetDate_byDay(end_Date);
		/***************************************************************/
		// 相隔的天数
		int intervalDay = getIntervalDay(beginDateReset, endDateReset);
		/***************************************************************/
		Date minDate = beginDateReset;
		if (beginDateReset.equals(endDateReset)){
			minDate = beginDateReset;
		}else if (beginDateReset.before(endDateReset)){
			minDate = beginDateReset;
		}else{
			minDate = endDateReset;
		}
		/***************************************************************/
		dateList.add(minDate);
		/***************************************************************/
		if (intervalDay > 0){
			for (int i = 0; i < intervalDay; ++i){
				dateList.add(addDay(minDate, i + 1));
			}
		}
		/***************************************************************/
		return dateList;
	}

	/**
	 * 两个时间相差的毫秒数,不管date1是否早于还是晚于date2,均返回绝对值.
	 * 
	 * @param date1
	 *            date1
	 * @param date2
	 *            date2
	 * @return 两个时间相差的毫秒数,不管date1是否早于还是晚于date2,均返回绝对值
	 * @since 1.0
	 */
	public final static long getIntervalTime(Date date1,Date date2){
		return Math.abs(getTime(date2) - getTime(date1));
	}

	// [end]

	/**
	 * 将date转成Calendar.
	 * 
	 * @param date
	 *            date
	 * @return Calendar
	 */
	public static Calendar toCalendar(Date date){

		// Calendar的getInstance( )方法返回用默认的地区和时区的当前日期和当前时间所初始化的GregorianCalendar（标准日历）
		// 除了日本和泰国,效果等同于 Calendar calendar = Calendar.getInstance();

		// GregorianCalendar 标准阳历 格列高利历/公历
		// 现在的公历是根据罗马人的"儒略历"改编而
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar;
	}
}