/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package com.feilong.commons.core.date;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

/**
 * 扩展 {@link DateUtil}类,更多人性化的操作及转换<br>
 * 
 * @author <a href="venusdrogon@163.com">金鑫</a>
 * @see {@link DateUtil}
 * @version 1.1 Aug 4, 2010 9:06:54 PM
 * @since 1.0
 */
public final class CalendarUtil{

	/** Don't let anyone instantiate this class. */
	private CalendarUtil(){}

	/**
	 * 获得当天00:00:00<br>
	 * 例如: 2011-01-01 10:20:20---->2011-01-01 00:00:00
	 * 
	 * @return 获得当天00:00:00
	 */
	public static Calendar getResetTodayCalendar_byDay(){
		return getResetCalendar_byDay(new Date());
	}

	/**
	 * 获得任意日期的00:00:00<br>
	 * 例如: 2011-01-01 10:20:20---->2011-01-01 00:00:00
	 * 
	 * @return 获得任意日期的00:00:00
	 */
	public static Calendar getResetCalendar_byDay(Date date){
		Calendar calendar = DateUtil.toCalendar(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}

	/**
	 * 获得任意日期的00:00:00<br>
	 * 例如: 2011-01-01 10:20:20---->2011-01-01 00:00:00
	 * 
	 * @return 获得任意日期的00:00:00
	 */
	public static Date getResetDate_byDay(Date date){
		Calendar calendar = getResetCalendar_byDay(date);
		return calendar.getTime();
	}

	/***********************************************************************************************************/
	/**
	 * 获得日历字段值
	 * 
	 * @param date
	 *            date
	 * @param field
	 *            Calendar字段:<br>
	 *            月份:Calendar.MONTH(真实值需要加1处理),<br>
	 *            日:Calendar.DAY_OF_MONTH,<br>
	 *            年份:Calendar.YEAR<br>
	 *            ...
	 * @return 获得日历字段值
	 */
	public static int getCalendarFieldValue(Date date,int field){
		return DateUtil.toCalendar(date).get(field);
	}

	/***********************************************************************************************************/
	/**
	 * 将日期字符串转成Calendar
	 * 
	 * @param dateString
	 *            将日期字符串
	 * @param datePattern
	 *            datePattern
	 * @return Calendar
	 */
	public static Calendar string2Calendar(String dateString,String datePattern){
		Date date = DateUtil.string2Date(dateString, datePattern);
		Calendar calendar = DateUtil.toCalendar(date);
		return calendar;
	}

	/**
	 * 获得英文星期
	 * 
	 * @param week
	 *            星期 日从0开始 1 2 --6
	 * @return 如 Sunday { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" } 中一个
	 */
	public static String getEnglishWeek(int week){
		return DateDictionary.week_englishs[week];
	}

	/**
	 * 获得阳历中月份的最大天数The days in the month of solar calendar(阳历)
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return 最大的天数
	 * @see {@link Calendar#getActualMaximum(int)}
	 */
	public static int getMaxDayOfMonth(int year,int month){
		Calendar calendar = toCalendar(year, month, 1);
		return getMaxDayOfMonth(calendar);
	}

	/**
	 * 获得阳历中月份的最大天数The days in the month of solar calendar(阳历)
	 * 
	 * @param calendar
	 *            calendar
	 * @return
	 */
	public static int getMaxDayOfMonth(Calendar calendar){
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		// if ((iMonth == 1) || (iMonth == 3) || (iMonth == 5) || (iMonth == 7) || (iMonth == 8) || (iMonth == 10) || (iMonth == 12)){
		// return 31;
		// }else if ((iMonth == 4) || (iMonth == 6) || (iMonth == 9) || (iMonth == 11)){
		// return 30;
		// }else if (iMonth == 2){
		// if (DateUtil.isLeapYear(iYear)){
		// return 29;
		// }
		// return 28;
		// }else{
		// return 0;
		// }
	}

	/**
	 * 获得这一天在这一年中的偏移量 The offset days from New Year and the day when point out in solar calendar
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @return 获得这一天在这一年中的偏移量
	 */
	public static int getDayOfYear(int year,int month,int day){
		Calendar calendar = toCalendar(year, month, day);
		return calendar.get(Calendar.DAY_OF_YEAR);// - 1
	}

	/**
	 * 获得某个时间在年份中的周数
	 * 
	 * @param dateString
	 *            日期字符串
	 * @param datePattern
	 *            模式
	 * @return 获得某个时间在年份中的周数
	 */
	public static int getWeekofYear(String dateString,String datePattern){
		Date date = DateUtil.string2Date(dateString, datePattern);
		return getWeekofYear(date);
	}

	/**
	 * 获得某个时间在年份中的周数
	 * 
	 * @param date
	 *            date
	 * @return 获得某个时间在年份中的周数
	 */
	public static int getWeekofYear(Date date){
		Calendar calendar = DateUtil.toCalendar(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获得一个日期是周几
	 * 
	 * <pre>
	 * SUNDAY、MONDAY、TUESDAY、WEDNESDAY、THURSDAY、FRIDAY 和 SATURDAY
	 * 
	 * 分别对应1-7
	 * </pre>
	 * 
	 * @param dateString
	 *            dateString 2010-08-09
	 * @param datePattern
	 *            模式 yyyy-MM-dd
	 * @return 周几
	 */
	public static int getWeek(String dateString,String datePattern){
		return getWeek(DateUtil.string2Date(dateString, datePattern));
	}

	/**
	 * 获得一个日期是周几
	 * 
	 * <pre>
	 * SUNDAY、MONDAY、TUESDAY、WEDNESDAY、THURSDAY、FRIDAY 和 SATURDAY
	 * 
	 * 分别对应1-7
	 * </pre>
	 * 
	 * @param date
	 *            date
	 * @return 周几
	 */
	public static int getWeek(Date date){
		Calendar calendar = DateUtil.toCalendar(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 月份偏移量的日期,支持负数表示前
	 * 
	 * <pre>
	 * 比如:三个月之后的现在 getDateOffsetByMonth(new Date(),3);
	 * 
	 * </pre>
	 * 
	 * @param date
	 *            date
	 * @param monthOffset
	 *            月份
	 * @return 月份偏移量的日期
	 */
	public static Date getDateOffsetByMonth(Date date,int monthOffset){
		Calendar calendar = DateUtil.toCalendar(date);
		calendar.set(Calendar.MONTH, getCalendarFieldValue(date, Calendar.MONTH) + monthOffset);
		return toDate(calendar);
	}

	/**
	 * 星期偏移量的日期,支持负数表示前
	 * 
	 * <pre>
	 * 比如:三个星期之后的现在 getDateOffsetByWeek(new Date(),3);
	 * 
	 * </pre>
	 * 
	 * @param date
	 *            date
	 * @param weekOffset
	 *            星期偏移量
	 * @return 星期偏移量的日期,支持负数表示前
	 */
	public static Date getDateOffsetByWeek(Date date,int weekOffset){
		Calendar calendar = DateUtil.toCalendar(date);
		calendar.set(Calendar.WEEK_OF_YEAR, getCalendarFieldValue(date, Calendar.WEEK_OF_YEAR) + weekOffset);
		return toDate(calendar);
	}

	/**
	 * 获得一年中所有的周几集合 例如:getWeekDateStringList(6, "yyyy-MM-dd");
	 * 
	 * @param week
	 *            周几 星期天开始为0 依次1 2 3 4 5 6
	 * @param datePattern
	 *            获得集合里面时间字符串模式
	 * @return 获得一年中所有的周几集合
	 */
	public static List<String> getWeekDateStringList(int week,String datePattern){
		List<String> list = new LinkedList<String>();
		/**
		 * 当前日期
		 */
		Calendar calendar_today = Calendar.getInstance();
		/**
		 * 当前年份
		 */
		int year_current = calendar_today.get(Calendar.YEAR);
		/**
		 * 下一个年份
		 */
		int year_next = 1 + calendar_today.get(Calendar.YEAR);
		/**
		 * 开始的calendar
		 */
		Calendar calendar_begin = Calendar.getInstance();
		/**
		 * 结束的calendar
		 */
		Calendar calendar_end = Calendar.getInstance();
		calendar_begin.set(year_current, 0, 1);// 2010-1-1
		calendar_end.set(year_next, 0, 1);// 2011-1-1
		// ****************************************************************************************
		// 今天在这个星期中的第几天 星期天为1 星期六为7
		int today_DAY_OF_WEEK = calendar_today.get(Calendar.DAY_OF_WEEK);
		// 今天前一个周六
		calendar_today.add(Calendar.DAY_OF_MONTH, -today_DAY_OF_WEEK - 7 + (1 + week));// + week
		Calendar calendar_clone_today = (Calendar) calendar_today.clone();
		// 向前
		for (; calendar_today.before(calendar_end) && calendar_today.after(calendar_begin); calendar_today.add(Calendar.DAY_OF_YEAR, -7)){
			list.add(toString(calendar_today, datePattern));
		}
		// 向后
		for (int i = 0; calendar_clone_today.before(calendar_end) && calendar_clone_today.after(calendar_begin); ++i, calendar_clone_today
				.add(Calendar.DAY_OF_YEAR, 7)){
			/**
			 * 第一个值和上面循环重复了 去掉
			 */
			if (i != 0){
				list.add(toString(calendar_clone_today, datePattern));
			}
		}
		Collections.sort(list);
		return list;
	}

	/**
	 * 将calendar转成Date
	 * 
	 * @param calendar
	 *            calendar
	 * @return Date
	 * @since 1.0
	 */
	public final static Date toDate(Calendar calendar){
		return calendar.getTime();
	}

	/**
	 * 将Calendar转成string
	 * 
	 * @param calendar
	 *            calendar
	 * @param datePattern
	 *            模式
	 * @return string
	 */
	public static String toString(Calendar calendar,String datePattern){
		Date date = toDate(calendar);
		return DateUtil.date2String(date, datePattern);
	}

	/**
	 * 设置日历字段 YEAR、MONTH 和 DAY_OF_MONTH 的值。<br>
	 * 保留其他日历字段以前的值。如果不需要这样做，则先调用 clear()。
	 * 
	 * @param year
	 *            用来设置 YEAR 日历字段的值
	 * @param month
	 *            用来设置 MONTH 日历字段的值。此处传递是我们口头意义上的月份, 内部自动进行-1的操作<br>
	 *            比如 8月就传递 8 ; 9月就传9 <br>
	 *            注:Java 的date Month 值是基于 0 的。例如，0 表示 January。
	 * @param day
	 *            用来设置 DAY_OF_MONTH 日历字段的值。
	 * @return
	 */
	public static Calendar toCalendar(int year,int month,int day){
		Calendar calendar = new GregorianCalendar();

		// 在使用set方法之前，必须先clear一下，否则很多信息会继承自系统当前时间
		calendar.clear();

		calendar.set(year, month - 1, day);
		return calendar;
	}
}