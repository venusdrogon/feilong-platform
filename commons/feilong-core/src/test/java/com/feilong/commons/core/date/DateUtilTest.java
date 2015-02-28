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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.DatePattern;
import com.feilong.commons.core.util.StringUtil;

/**
 * The Class DateUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-2-19 下午4:17:03
 */
@SuppressWarnings("all")
public class DateUtilTest extends BaseDateUtilTest{

	/** The Constant log. */
	private final static Logger	log	= LoggerFactory.getLogger(DateUtilTest.class);

	// ctrl +alt + ↑
	// ctrl +alt+↓
	// ctrl+D 删除光标所在行

	/**
	 * TestDateUtilTest.
	 */
	@Test
	public void testDateUtilTest(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(2014, Calendar.DECEMBER, 29);
		log.info(DateUtil.date2String(calendar.getTime(), "yyyy-MM-dd"));
		log.info(DateUtil.date2String(calendar.getTime(), "YYYY-MM-dd"));
	}

	/**
	 * Aaaa.
	 */
	@Test
	public void aaaa(){
		Date beginDate = DateUtil.string2Date("2013-12-21 00:00:00", DatePattern.commonWithTime);
		Date endDate = DateUtil.string2Date("2013-12-21 05:00:00", DatePattern.commonWithTime);

		// 起始小时
		int hour = DateUtil.getHourOfDay(beginDate);

		// 相差小时
		int ihour = DateUtil.getIntervalHour(beginDate, endDate);

		for (int i = 0; i < ihour; ++i){
			for (int j = 0; j < 60; ++j){
				log.info("0" + i + ":" + StringUtil.format("%02d", j));
			}
		}
	}

	/**
	 * 此时此刻 在今天的秒数.
	 */
	@Test
	public void test1(){
		Calendar calendar = DateUtil.toCalendar(TESTDATE_20141231013024);
		log.info(calendar.getActualMaximum(Calendar.SECOND) + "");
		log.info(calendar.getTimeInMillis() + "");
		log.info(calendar.hashCode() + "");
		log.info(DateUtil.getDayOfMonth(now) + "");
	}

	/**
	 * Gets the second of day.
	 * 
	 * @return the second of day
	 */
	@Test
	public void getSecondOfDay(){
		Date now = new Date();
		log.info(DateUtil.getSecondOfDay(now) + "");
	}

	/**
	 * Gets the second of hour.
	 * 
	 * @return the second of hour
	 */
	@Test
	public void getSecondOfHour(){
		Date now = new Date();
		log.info(DateUtil.getSecondOfHour(now) + "");
	}

	/**
	 * Gets the day of year.
	 * 
	 * @return the day of year
	 */
	@Test
	public void getDayOfYear(){
		Date now = new Date();
		Date date1 = DateUtil.string2Date("2013-01-05", DatePattern.onlyDate);
		log.info(DateUtil.getDayOfYear(date1) + "");
		log.info(DateUtil.getDayOfYear(now) + "");
	}

	/**
	 * Gets the hour of year.
	 * 
	 * @return the hour of year
	 */
	@Test
	public void getHourOfYear(){
		Date now = new Date();
		log.info(DateUtil.getHourOfYear(DateUtil.string2Date("2013-01-05 12:00:05", DatePattern.commonWithTime)) + "");
		log.info(DateUtil.getHourOfYear(DateUtil.string2Date("2013-01-01 00:00:05", DatePattern.commonWithTime)) + "");
		log.info(DateUtil.getHourOfYear(DateUtil.string2Date("2013-09-16 11:42:22", DatePattern.commonWithTime)) + "");
		log.info(DateUtil.getHourOfYear(now) + "");
	}

	/**
	 * Gets the first date of this month.
	 * 
	 * @return the first date of this month
	 */
	@Test
	public void getFirstDateOfThisMonth(){
		logDate(DateUtil.getFirstDateOfThisMonth(now));
		logDate(DateUtil.getFirstDateOfThisMonth(DateUtil.addMonth(now, +1)));
		logDate(DateUtil.getFirstDateOfThisMonth(DateUtil.addMonth(now, -1)));
	}

	/**
	 * Gets the last date of this month.
	 * 
	 * @return the last date of this month
	 */
	@Test
	public void getLastDateOfThisMonth(){
		logDate(DateUtil.getLastDateOfThisMonth(now));
		logDate(DateUtil.getLastDateOfThisMonth(DateUtil.string2Date("2012-02-01", DatePattern.onlyDate)));
		logDate(DateUtil.getLastDateOfThisMonth(DateUtil.addMonth(now, +1)));
		logDate(DateUtil.getLastDateOfThisMonth(DateUtil.addMonth(now, -1)));
	}

	/**
	 * Gets the first date of this year.
	 * 
	 * @return the first date of this year
	 */
	@Test
	public void getFirstDateOfThisYear(){
		logDate(DateUtil.getFirstDateOfThisYear(now));
		logDate(DateUtil.getFirstDateOfThisYear(DateUtil.addYear(now, +1)));
		logDate(DateUtil.getFirstDateOfThisYear(DateUtil.addYear(now, -1)));
	}

	/**
	 * Test get last date of this year.
	 */
	@Test
	public void testGetLastDateOfThisYear(){
		logDate(DateUtil.getLastDateOfThisYear(now));
		logDate(DateUtil.getLastDateOfThisYear(DateUtil.addYear(now, +1)));
		logDate(DateUtil.getLastDateOfThisYear(DateUtil.addYear(now, -1)));
	}

	/**
	 * Gets the first date of this week.
	 * 
	 * @return the first date of this week
	 */
	@Test
	public void getFirstDateOfThisWeek(){
		Date date = DateUtil.addDay(now, -2);
		log.debug("the param date:{}", DateUtil.date2String(date, DatePattern.commonWithMillisecond));
		Date now_3 = DateUtil.getFirstDateOfThisWeek(date);
		log.debug(DateUtil.date2String(now_3, DatePattern.commonWithMillisecond));
		log.debug("今天所在week 第一天:{}", DateUtil.date2String(DateUtil.getFirstDateOfThisWeek(new Date()), DatePattern.commonWithMillisecond));

		log.debug("getFirstDateOfThisWeek:{}", DateUtil.date2String(
						DateUtil.getFirstDateOfThisWeek(DateUtil.string2Date("2014-01-01 05:00:00", DatePattern.commonWithTime)),
						DatePattern.commonWithMillisecond));

	}

	/**
	 * Gets the last date of this week.
	 * 
	 * @return the last date of this week
	 */
	@Test
	public void getLastDateOfThisWeek(){
		Date date = DateUtil.addDay(now, -2);

		log.debug("the param date:{}", DateUtil.date2String(date, DatePattern.commonWithMillisecond));
		Date now_3 = DateUtil.getLastDateOfThisWeek(date);
		log.debug(DateUtil.date2String(now_3, DatePattern.commonWithMillisecond));

		log.debug("getLastDateOfThisWeek:{}", DateUtil.date2String(
						DateUtil.getLastDateOfThisWeek(DateUtil.string2Date("2014-12-31 05:00:00", DatePattern.commonWithTime)),
						DatePattern.commonWithMillisecond));
	}

	/**
	 * Test add minute.
	 */
	@Test
	public void testAddMinute(){
		logDate(DateUtil.addMinute(now, 180));
		logDate(DateUtil.addMinute(now, -180));
	}

	/**
	 * Test add minute111.
	 */
	@Test
	public void testAddMinute111(){
		log.debug(DateUtil.date2String(new Date(), DatePattern.ddMMyyyyHHmmss));
	}

	/**
	 * Adds the month.
	 */
	@Test
	public void addMonth(){
		Date beginDate = DateUtil.string2Date("2013-10-28", DatePattern.onlyDate);
		logDate(DateUtil.addMonth(beginDate, 6));
		logDate(DateUtil.addMonth(new Date(), 3));
		logDate(DateUtil.addMonth(new Date(), -3));

		Date date = DateUtil.addMonth(new Date(), 5);
		logDate(date);

		date = DateUtil.addMonth(new Date(), -5);
		logDate(date);
	}

	/**
	 * Test add second.
	 */
	@Test
	public void testAddSecond(){
		logDate(DateUtil.addSecond(now, 180));
		logDate(DateUtil.addSecond(now, -180));
	}

	/**
	 * Test is before.
	 */
	@Test
	public void testIsBefore(){
		boolean isBefore = DateUtil.isBefore(fromString, toString, DatePattern.onlyDate);
		log.debug(String.valueOf(isBefore));
	}

	/**
	 * Test get interval day.
	 */
	@Test
	public void testGetIntervalDay(){
		// Date now = DateUtil.convertStringToDate(fromString, DateUtil.pattern_commonWithTime);
		// Date date = DateUtil.convertStringToDate(toString, DateUtil.pattern_commonWithTime);
		// log.debug(DateUtil.getIntervalDay(now, date));
		String fromString = "2008-12-1";
		String toString = "2008-9-29";
		int intervalDay = DateUtil.getIntervalDay(fromString, toString, DatePattern.onlyDate);
		log.debug(intervalDay + "");
	}

	/**
	 * Test convert birthday to age.
	 */
	@Test
	public void testConvertBirthdayToAge(){
		log.debug("convertBirthdayToAge:" + SelectHelper.convertBirthdayToAge("2000-05-41"));
	}

	/**
	 * Test date pattern.
	 */
	@Test
	public void testDatePattern(){
		log.debug("commonWithMillisecond:" + DateUtil.date2String(now, DatePattern.commonWithMillisecond));
		log.debug("commonWithoutAndYearSecond:" + DateUtil.date2String(now, DatePattern.commonWithoutAndYearSecond));
		log.debug("commonWithoutSecond:" + DateUtil.date2String(now, DatePattern.commonWithoutSecond));
		log.debug("commonWithTime:" + DateUtil.date2String(now, DatePattern.commonWithTime));
		log.debug("forToString:" + DateUtil.date2String(now, DatePattern.forToString));
		log.debug("HH:" + DateUtil.date2String(now, DatePattern.HH));
		log.debug("MM:" + DateUtil.date2String(now, DatePattern.MM));
		log.debug("mmss:" + DateUtil.date2String(now, DatePattern.mmss));
		log.debug("monthAndDay:" + DateUtil.date2String(now, DatePattern.monthAndDay));
		log.debug("monthAndDayWithWeek:" + DateUtil.date2String(now, DatePattern.monthAndDayWithWeek));
		log.debug("onlyDate:" + DateUtil.date2String(now, DatePattern.onlyDate));
		log.debug("onlyTime:" + DateUtil.date2String(now, DatePattern.onlyTime));
		log.debug("onlyTime_withoutSecond:" + DateUtil.date2String(now, DatePattern.onlyTime_withoutSecond));

		log.debug("timestamp:" + DateUtil.date2String(now, DatePattern.timestamp));
		log.debug("timestampWithMillisecond:" + DateUtil.date2String(now, DatePattern.timestampWithMillisecond));
		log.debug("yearAndMonth:" + DateUtil.date2String(now, DatePattern.yearAndMonth));
		log.debug("yy:" + DateUtil.date2String(now, DatePattern.yy));
		log.debug("yyyyMMdd:" + DateUtil.date2String(now, DatePattern.yyyyMMdd));
	}

	/**
	 * Test date2 string.
	 */
	@Test
	public void testDate2String(){
		String dateToString = DateUtil.date2String(new Date(), DatePattern.timestampWithMillisecond);
		log.info(dateToString);
	}

	/**
	 * Test string2 date.
	 */
	@Test
	public void testString2Date(){
		Date date = DateUtil.string2Date(fromString, DatePattern.onlyDate);
		log.debug(date.toString());
		String dateToString = DateUtil.date2String(date, DatePattern.commonWithTime);
		log.info(dateToString);

		Date onlineTime = DateUtil.string2Date("20130102140806000", DatePattern.timestampWithMillisecond);// 商品上线时间

		log.info(onlineTime.toString());
	}

	/**
	 * Test get interval second.
	 */
	@Test
	public void testGetIntervalSecond(){
		Date startDate = DateUtil.string2Date("2013-01-01 00:00:00", DatePattern.commonWithTime);
		Date now = new Date();
		log.info(DateUtil.getIntervalSecond(startDate, now) + "");
		log.info(DateUtil.getIntervalSecond(startDate, DateUtil.string2Date("2113-01-01 00:00:00", DatePattern.commonWithTime)) + "");

		log.debug(DateUtil.getIntervalSecond(161986) + "");
		log.debug(Integer.MAX_VALUE + "");
	}

	/**
	 * Gets the day of week.
	 * 
	 * @return the day of week
	 */
	@Test
	public void getDayOfWeek(){
		log.debug(DateUtil.getDayOfWeek(new Date()) + "");
		log.debug(DateUtil.getDayOfWeek(currentYearBegin) + "");
		log.debug(DateUtil.getDayOfWeek(currentYearEnd) + "");
	}

	/**
	 * Gets the month.
	 * 
	 * @return the month
	 */
	@Test
	public void getMonth(){
		log.debug(DateUtil.getMonth(new Date()) + "");
	}

	/**
	 * Gets the week of year.
	 * 
	 * @return the week of year
	 */
	@Test
	public void getWeekOfYear(){
		log.debug(DateUtil.getWeekOfYear(new Date()) + "");
		log.debug(DateUtil.getWeekOfYear(DateUtil.string2Date("2013-12-31 01:30:24.895", DatePattern.commonWithMillisecond)) + "");
		log.debug(DateUtil.getWeekOfYear(currentYearBegin) + "");
		log.debug(DateUtil.getWeekOfYear(currentYearEnd) + "");
		log.debug(DateUtil.getWeekOfYear(DateUtil.string2Date("2014-12-31 01:30:24.895", DatePattern.commonWithMillisecond)) + "");
		log.debug(DateUtil.getWeekOfYear(DateUtil.string2Date("2014-12-30 01:30:24.895", DatePattern.commonWithMillisecond)) + "");
		log.debug(DateUtil.getWeekOfYear(DateUtil.string2Date("2014-12-20 01:30:24.895", DatePattern.commonWithMillisecond)) + "");
		log.debug(DateUtil.getWeekOfYear(DateUtil.string2Date("2014-12-26 01:30:24.895", DatePattern.commonWithMillisecond)) + "");
		log.debug(DateUtil.getWeekOfYear(DateUtil.string2Date("2011-03-10 01:30:24.895", DatePattern.commonWithMillisecond)) + "");
	}

	/**
	 * Test get day of month.
	 */
	@Test
	public void testGetDayOfMonth(){
		log.debug(DateUtil.getDayOfMonth(new Date()) + "");
	}

	/**
	 * Test get year.
	 */
	@Test
	public void testGetYear(){
		log.debug(DateUtil.getYear(new Date()) + "");
	}

	/**
	 * Gets the hour of day.
	 * 
	 * @return the hour of day
	 */
	@Test
	public void getHourOfDay(){
		log.debug(DateUtil.getHourOfDay(new Date()) + "");
	}

	/**
	 * Gets the minute.
	 * 
	 * @return the minute
	 */
	@Test
	public void getMinute(){
		log.debug(DateUtil.getMinute(new Date()) + "");
	}

	/**
	 * Gets the second.
	 * 
	 * @return the second
	 */
	@Test
	public void getSecond(){
		log.debug(DateUtil.getSecond(new Date()) + "");
	}

	/**
	 * Gets the time.
	 * 
	 * @return the time
	 */
	@Test
	public void getTime(){
		log.debug(DateUtil.getTime(new Date()) + "");
	}

	/**
	 * Test is leap year.
	 */
	@Test
	public void testIsLeapYear(){
		int year = -3;
		log.debug(new GregorianCalendar(-3, 1, 1).isLeapYear(year) + "");
		log.debug(DateUtil.isLeapYear(year) + "");
	}

	/**
	 * Adds the year.
	 */
	@Test
	public void addYear(){
		Date date = DateUtil.addYear(now, 5);
		logDate(date);

		date = DateUtil.addYear(now, -5);
		logDate(date);
	}

	/**
	 * Adds the day.
	 */
	@Test
	public void addDay(){
		Date date = DateUtil.addDay(new Date(), 5);
		logDate(date);

		date = DateUtil.addDay(new Date(), -5);
		logDate(date);

		date = DateUtil.addDay(DateUtil.string2Date("2014-12-31 02:10:05", DatePattern.commonWithTime), 5);
		logDate(date);

		logDate(DateUtil.addDay(DateUtil.string2Date("2014-01-01 02:10:05", DatePattern.commonWithTime), -5));
	}

	/**
	 * Adds the week.
	 */
	@Test
	public void addWeek(){
		Date date = DateUtil.addWeek(new Date(), 1);
		logDate(date);

		date = DateUtil.addWeek(new Date(), -1);
		logDate(date);
	}

	/**
	 * Test add hour.
	 */
	@Test
	public void testAddHour(){
		log.debug("the param NewConstructorTypeMunger :{}", now);
		logDate(DateUtil.addHour(now, 5));
		logDate(DateUtil.addHour(now, -5));
	}

	/**
	 * Test method for {@link com.feilong.commons.core.date.DateUtil#getYesterday(java.util.Date)}.
	 */
	@Test
	public final void testGetYesterday(){
		logDate(DateUtil.getYesterday(now));
	}

	/**
	 * Test method for {@link com.feilong.commons.core.date.DateUtil#isInTime(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testIsInTime(){
		log.debug("{}", DateUtil.isInTime(now, "2012-10-10 22:59:00", "2012-10-16 22:59:00", DatePattern.commonWithTime));
	}

	/**
	 * Test method for {@link com.feilong.commons.core.date.DateUtil#getIntervalHour(java.util.Date, java.util.Date)}.
	 * 
	 * @return the interval hour
	 */
	@Test
	public final void getIntervalHour(){
		Date startDate = DateUtil.string2Date("2013-01-01 00:00:00", DatePattern.commonWithTime);
		log.info(DateUtil.getIntervalHour(startDate, now) + "");
		log.info(DateUtil.getIntervalHour(startDate, DateUtil.string2Date("2113-01-01 00:00:00", DatePattern.commonWithTime)) + "");
		log.info(DateUtil.getIntervalHour(startDate, DateUtil.string2Date("3113-01-01 00:00:00", DatePattern.commonWithTime)) + "");
	}

	/**
	 * Test method for {@link com.feilong.commons.core.date.DateUtil#getIntervalTime(java.util.Date, java.util.Date)}.
	 */
	@Test
	public final void testGetIntervalTime(){
		Date startDate = DateUtil.string2Date("2013-01-01 00:00:00", DatePattern.commonWithTime);
		log.info(DateUtil.getIntervalTime(startDate, now) + "");
		log.info(DateUtil.getIntervalTime(startDate, DateUtil.string2Date("2113-01-01 00:00:00", DatePattern.commonWithTime)) + "");

	}

	/**
	 * Test to calendar1.
	 */
	@Test
	public final void testToCalendar1(){
		log.info((new Date().getTime() + "").length() + "");
	}

	@Test
	public final void testGetIntervalDay2(){
		log.info("" + DateUtil.getIntervalDay("2008-08-24", "2008-08-27", "yyyy-MM-dd"));
	}
}