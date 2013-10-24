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
