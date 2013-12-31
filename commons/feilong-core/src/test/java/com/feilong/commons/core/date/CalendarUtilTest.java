/**
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
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package com.feilong.commons.core.date;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-2-19 下午4:16:30
 */
public class CalendarUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(CalendarUtilTest.class);

	@Test
	public void getWeekNumberInYear(){
		log.debug(CalendarUtil.getWeekofYear("20120914", DatePattern.yyyyMMdd) + "");

	}

	@Test
	public void getDateOffsetByMonth(){
		log.debug(DateUtil.date2String(CalendarUtil.getDateOffsetByMonth(new Date(), 3)));
	}

	@Test
	public void getDateOffsetByWeek(){
		log.debug(DateUtil.date2String(CalendarUtil.getDateOffsetByWeek(new Date(), 1)));
		log.debug(DateUtil.date2String(CalendarUtil.getDateOffsetByWeek(new Date(), -1)));
	}

	@Test
	public void getMaxDayOfMonth(){

		int year = 2012;
		int month = 2;

		Object[] objects = { year, month, CalendarUtil.getMaxDayOfMonth(year, month) };
		log.debug("{} 年 {}月, 最大天:{}", objects);

	}

	@Test
	public void getActualMaximum(){
		Calendar calendar = CalendarUtil.string2Calendar("2007-02-20", DatePattern.onlyDate);
		log.debug("the param objects:{}", calendar.getActualMaximum(Calendar.DAY_OF_YEAR));

		// calendar.getActualMaximum(Calendar.DAY_OF_YEAR)
	}

	@Test
	public void getDayOfYear(){
		log.debug(CalendarUtil.getDayOfYear(2013, 9, 5) + "");
	}
}
