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

import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.StringUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-2-19 下午4:17:03
 */
public class DateUtilTest{

	private final static Logger	log			= LoggerFactory.getLogger(DateUtilTest.class);

	/**
	 * <code>{@value}</code> code
	 */
	public static String		fromString	= "2011-03-5 23:31:25.456";

	public static String		toString	= "2011-03-10 01:30:24.895";

	public static Date			now			= new Date();

	// ctrl +alt + ↑
	// ctrl +alt+↓
	// ctrl+D 删除光标所在行
	@BeforeClass
	public static void beforeClass(){
		log.debug("beforeClass,现在时间:" + DateUtil.date2String(now));
	}

	@Before
	public void before(){
		log.debug("before,现在时间:" + DateUtil.date2String(now));
	}

	@After
	public void after(){
		log.debug("after:--------------");
	}

	@AfterClass
	public static void afterClass(){
		log.debug("afterClass:--------------");
	}

	// **********************************************************************
	@Test
	public void testGetFirstDateOfThisYear(){
		Date now_3 = DateUtil.getFirstDateOfThisYear(now);
		log.debug(DateUtil.date2String(now_3));
	}

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

				System.out.println("0" + i + ":" + StringUtil.format("%02d", j));
			}

		}

	}

	/**
	 * 此时此刻 在今天的秒数
	 */
	@Test
	public void test1(){
		Date now = new Date();
		Calendar calendar = DateUtil.toCalendar(now);
		log.info(calendar.getActualMaximum(Calendar.SECOND) + "");
		log.info(calendar.getTimeInMillis() + "");
		log.info(calendar.hashCode() + "");
		log.info(DateUtil.getDayOfMonth(now) + "");
	}

	@Test
	public void getSecondOfDay(){
		Date now = new Date();
		log.info(DateUtil.getSecondOfDay(now) + "");
	}

	@Test
	public void getSecondOfHour(){
		Date now = new Date();
		log.info(DateUtil.getSecondOfHour(now) + "");
	}

	@Test
	public void getDayOfYear(){
		Date now = new Date();
		Date date1 = DateUtil.string2Date("2013-01-05", DatePattern.onlyDate);
		log.info(DateUtil.getDayOfYear(date1) + "");
		log.info(DateUtil.getDayOfYear(now) + "");
	}

	@Test
	public void getHourOfYear(){
		Date now = new Date();
		log.info(DateUtil.getHourOfYear(DateUtil.string2Date("2013-01-05 12:00:05", DatePattern.commonWithTime)) + "");
		log.info(DateUtil.getHourOfYear(DateUtil.string2Date("2013-01-01 00:00:05", DatePattern.commonWithTime)) + "");
		log.info(DateUtil.getHourOfYear(DateUtil.string2Date("2013-09-16 11:42:22", DatePattern.commonWithTime)) + "");
		log.info(DateUtil.getHourOfYear(now) + "");
	}

	@Test
	public void testGetFirstDateOfThisMonth(){
		Date now_3 = DateUtil.getFirstDateOfThisMonth(now);
		log.debug(DateUtil.date2String(now_3));
	}

	@Test
	public void getLastDateOfThisMonth(){
		log.debug(DateUtil.date2String(DateUtil.getLastDateOfThisMonth(now)));
		log.debug(DateUtil.date2String(DateUtil.getLastDateOfThisMonth(DateUtil.string2Date("2012-02-01", DatePattern.onlyDate))));
	}

	@Test
	public void testGetLastDateOfThisYear(){
		Date now_3 = DateUtil.getLastDateOfThisYear(now);
		log.debug(DateUtil.date2String(now_3));
	}

	@Test
	public void testGetFirstDateOfThisWeek(){
		Date date = DateUtil.addDay(now, -2);
		log.debug("the param date:{}", DateUtil.date2String(date, DatePattern.commonWithMillisecond));
		Date now_3 = DateUtil.getFirstDateOfThisWeek(date);
		log.debug(DateUtil.date2String(now_3, DatePattern.commonWithMillisecond));
	}

	@Test
	public void testGetLastDateOfThisWeek(){
		Date date = DateUtil.addDay(now, -2);

		log.debug("the param date:{}", DateUtil.date2String(date, DatePattern.commonWithMillisecond));
		Date now_3 = DateUtil.getLastDateOfThisWeek(date);
		log.debug(DateUtil.date2String(now_3, DatePattern.commonWithMillisecond));
	}

	@Test
	public void testGetIntervalForViewLong(){
		log.debug(DateUtil.getIntervalForView(25841));
		log.debug(DateUtil.getIntervalForView(0));
	}

	@Test
	public void testAddMinute(){
		print(DateUtil.addMinute(now, 180));
		print(DateUtil.addMinute(now, -180));
	}

	@Test
	public void testAddSecond(){
		print(DateUtil.addSecond(now, 180));
		print(DateUtil.addSecond(now, -180));
	}

	@Test
	public void testIsBefore(){
		boolean isBefore = DateUtil.isBefore(fromString, toString, DatePattern.onlyDate);
		log.debug(String.valueOf(isBefore));
	}

	@Test
	public void testGetIntervalDayList(){
		List<Date> dates = DateUtil.getIntervalDayList(fromString, toString, DatePattern.commonWithTime);
		for (int i = 0; i < dates.size(); ++i){
			log.debug(DateUtil.date2String(dates.get(i), DatePattern.commonWithTime));
		}
	}

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

	@Test
	public void testGetIntervalForView(){
		Date now = DateUtil.string2Date("2011-05-19 11:31:25.456", DatePattern.commonWithTime);
		now = new Date();
		Date date = DateUtil.string2Date("2012-12-03 00:00:00", DatePattern.commonWithTime);
		log.debug(DateUtil.getIntervalForView(now, date));
		long intervalTime = DateUtil.getIntervalTime(now, date);
		log.debug(intervalTime + "");
	}

	@Test
	public void testConvertBirthdayToAge(){
		log.debug("convertBirthdayToAge:" + DateUtil.convertBirthdayToAge("2000-05-41"));
	}

	@Test
	public void testDate2String(){
		String dateToString = DateUtil.date2String(new Date(), DatePattern.timestampWithMillisecond);
		log.info(dateToString);
	}

	@Test
	public void testString2Date(){
		Date date = DateUtil.string2Date(fromString, DatePattern.onlyDate);
		log.debug(date.toString());
		String dateToString = DateUtil.date2String(date, DatePattern.commonWithTime);
		log.info(dateToString);

		Date onlineTime = DateUtil.string2Date("20130102140806000", DatePattern.timestampWithMillisecond);// 商品上线时间

		log.info(onlineTime.toString());
	}

	@Test
	public void testGetIntervalSecond(){
		Date startDate = DateUtil.string2Date("2013-01-01 00:00:00", DatePattern.commonWithTime);
		Date now = new Date();
		log.info(DateUtil.getIntervalSecond(startDate, now) + "");
		log.info(DateUtil.getIntervalSecond(startDate, DateUtil.string2Date("2113-01-01 00:00:00", DatePattern.commonWithTime)) + "");

		log.debug(DateUtil.getIntervalSecond(161986) + "");
		log.debug(Integer.MAX_VALUE + "");
	}

	@Test
	public void testGetDayOfWeek(){
		log.debug(DateUtil.getDayOfWeek(new Date()) + "");
	}

	@Test
	public void testGetMonth(){
		log.debug(DateUtil.getMonth(new Date()) + "");
	}

	@Test
	public void testGetDayOfMonth(){
		log.debug(DateUtil.getDayOfMonth(new Date()) + "");
	}

	@Test
	public void testGetYear(){
		log.debug(DateUtil.getYear(new Date()) + "");
	}

	@Test
	public void testGetHourOfDay(){
		log.debug(DateUtil.getHourOfDay(new Date()) + "");
	}

	@Test
	public void testGetMinute(){
		log.debug(DateUtil.getMinute(new Date()) + "");
	}

	@Test
	public void testGetSecond(){
		log.debug(DateUtil.getSecond(new Date()) + "");
	}

	@Test
	public void testGetTime(){
		log.debug(DateUtil.getTime(new Date()) + "");
	}

	@Test
	public void testIsLeapYear(){
		int year = -3;
		log.debug(new GregorianCalendar(-3, 1, 1).isLeapYear(year) + "");
		log.debug(DateUtil.isLeapYear(year) + "");
	}

	@Test
	public void testAddYear(){
		Date date = DateUtil.addYear(now, 5);
		print(date);

		date = DateUtil.addYear(now, -5);
		print(date);
	}

	@Test
	public void testAddMonth(){
		Date date = DateUtil.addMonth(new Date(), 5);
		print(date);

		date = DateUtil.addMonth(new Date(), -5);
		print(date);
	}

	@Test
	public void testAddDay(){
		Date date = DateUtil.addDay(new Date(), 5);
		print(date);

		date = DateUtil.addDay(new Date(), -5);
		print(date);
	}

	@Test
	public void testAddWeek(){
		Date date = DateUtil.addWeek(new Date(), 5);
		print(date);

		date = DateUtil.addWeek(new Date(), -5);
		print(date);
	}

	@Test
	public void testAddHour(){
		log.debug("the param NewConstructorTypeMunger :{}", now);
		print(DateUtil.addHour(now, 5));
		print(DateUtil.addHour(now, -5));
	}

	/**
	 * 将传入的date转换为中国特色日期
	 * 
	 * <pre>
	 * 如果 现在是 2012-10-18 14:16:00
	 * 
	 * 给你个时间, 
	 * 2012-10-18 14:15:02,要你显示成   "58秒前"
	 * 2012-10-18 14:14:22,要你显示成   "1分钟前"
	 * 2012-10-18 13:55:00,要你显示成   "19分钟前"
	 * 2012-10-17 14:15:02,要你显示成   "昨天 14:15"
	 * 2012-10-16 14:15:02,要你显示成   "前天 14:15"
	 * 2012-10-15 14:15:02,要你显示成   "10-15 14:15"
	 * 2012-09-15 14:15:02,要你显示成   "09-15 14:15"
	 * 2011-09-15 14:15:02,要你显示成   "2011-09-15 14:15"
	 * 
	 * </pre>
	 */
	@Test
	public void testToHumanizationDateString(){
		log.debug("testConvertDateToChineseDateString:**********************************************");

		log.debug(DateUtil.toHumanizationDateString(DateUtil.string2Date("2012-10-18 13:55:00", DatePattern.commonWithTime)));
		log.debug(DateUtil.toHumanizationDateString(DateUtil.string2Date("2012-10-18 14:14:22", DatePattern.commonWithTime)));
		log.debug(DateUtil.toHumanizationDateString(DateUtil.string2Date("2012-10-18 14:15:22", DatePattern.commonWithTime)));
		log.debug(DateUtil.toHumanizationDateString(DateUtil.string2Date("2012-10-17 14:15:02", DatePattern.commonWithTime)));
		log.debug(DateUtil.toHumanizationDateString(DateUtil.string2Date("2012-10-16 14:15:02", DatePattern.commonWithTime)));
		log.debug(DateUtil.toHumanizationDateString(DateUtil.string2Date("2012-10-15 14:15:02", DatePattern.commonWithTime)));
		log.debug(DateUtil.toHumanizationDateString(DateUtil.string2Date("2012-09-15 14:15:02", DatePattern.commonWithTime)));
		log.debug(DateUtil.toHumanizationDateString(DateUtil.string2Date("2011-09-15 14:15:02", DatePattern.commonWithTime)));
		log.debug(DateUtil.toHumanizationDateString(DateUtil.string2Date("2012-12-03 00:00:00", DatePattern.commonWithTime)));
	}

	/**
	 * Test method for {@link com.feilong.commons.core.date.DateUtil#getExtentToday()}.
	 */
	@Test
	public final void testGetExtentToday(){
		Date[] dates = DateUtil.getExtentToday();
		for (Date date : dates){
			log.debug(DateUtil.date2String(date, DatePattern.commonWithMillisecond));
		}
	}

	/**
	 * Test method for {@link com.feilong.commons.core.date.DateUtil#getYesterday(java.util.Date)}.
	 */
	@Test
	public final void testGetYesterday(){
		print(DateUtil.getYesterday(now));
	}

	/**
	 * Test method for {@link com.feilong.commons.core.date.DateUtil#getExtentYesterday()}.
	 */
	@Test
	public final void testGetExtentYesterday(){
		Date[] dates = DateUtil.getExtentYesterday();
		for (Date date : dates){
			print(date);
		}
	}

	private void print(Date date){
		log.debug(DateUtil.date2String(date, DatePattern.commonWithMillisecond));
	}

	/**
	 * Test method for {@link com.feilong.commons.core.date.DateUtil#isInTime(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testIsInTime(){
		Date date = new Date();
		log.debug("{}", DateUtil.isInTime(date, "2012-10-10 22:59:00", "2012-10-16 22:59:00", DatePattern.commonWithTime));
	}

	/**
	 * Test method for {@link com.feilong.commons.core.date.DateUtil#operateDate(java.util.Date, int, int)}.
	 */
	@Test
	public final void testOperateDate(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.date.DateUtil#date2String(java.util.Date)}.
	 */
	@Test
	public final void testDate2StringDate(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.date.DateUtil#date2String(java.util.Date, java.lang.String)}.
	 */
	@Test
	public final void testDate2StringDateString(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.date.DateUtil#dateList2StringList(java.util.List, java.lang.String)}.
	 */
	@Test
	public final void testDateList2StringList(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.date.DateUtil#isEqual(java.util.Date, java.util.Date, java.lang.String)}.
	 */
	@Test
	public final void testIsEqual(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.date.DateUtil#getIntervalForView(java.util.Date, java.util.Date)}.
	 */
	@Test
	public final void testGetIntervalForViewDateDate(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.date.DateUtil#getIntervalHour(long)}.
	 */
	@Test
	public final void testGetIntervalHourLong(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.date.DateUtil#getIntervalMinute(long)}.
	 */
	@Test
	public final void testGetIntervalMinute(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.date.DateUtil#getIntervalHour(java.util.Date, java.util.Date)}.
	 */
	@Test
	public final void getIntervalHour(){
		Date startDate = DateUtil.string2Date("2013-01-01 00:00:00", DatePattern.commonWithTime);
		Date now = new Date();
		log.info(DateUtil.getIntervalHour(startDate, now) + "");
		log.info(DateUtil.getIntervalHour(startDate, DateUtil.string2Date("2113-01-01 00:00:00", DatePattern.commonWithTime)) + "");
		log.info(DateUtil.getIntervalHour(startDate, DateUtil.string2Date("3113-01-01 00:00:00", DatePattern.commonWithTime)) + "");
	}

	/**
	 * Test method for {@link com.feilong.commons.core.date.DateUtil#getIntervalDay(long)}.
	 */
	@Test
	public final void testGetIntervalDayLong(){}

	/**
	 * Test method for {@link com.feilong.commons.core.date.DateUtil#getIntervalDay(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testGetIntervalDayString(){}

	/**
	 * Test method for {@link com.feilong.commons.core.date.DateUtil#getIntervalDay(java.util.Date, java.util.Date)}.
	 */
	@Test
	public final void testGetIntervalDayDateDate(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.date.DateUtil#getIntervalTime(java.util.Date, java.util.Date)}.
	 */
	@Test
	public final void testGetIntervalTime(){
		Date startDate = DateUtil.string2Date("2013-01-01 00:00:00", DatePattern.commonWithTime);
		Date now = new Date();
		log.info(DateUtil.getIntervalTime(startDate, now) + "");
		log.info(DateUtil.getIntervalTime(startDate, DateUtil.string2Date("2113-01-01 00:00:00", DatePattern.commonWithTime)) + "");

	}

	/**
	 * Test method for {@link com.feilong.commons.core.date.DateUtil#toCalendar(java.util.Date)}.
	 */
	@Test
	public final void testToCalendar(){
		fail("Not yet implemented"); // TODO
	}
}
