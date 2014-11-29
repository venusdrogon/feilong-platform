package com.baidu;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class DateUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-2-19 下午4:17:03
 */
public class IncomeDateTest{

	/** The Constant log. */
	private final static Logger	log	= LoggerFactory.getLogger(IncomeDateTest.class);

	/**
	 * 获得收益时间(获取当前天+1天，周末不算).
	 *
	 * @param date
	 *            任意日期
	 * @return the income date
	 * @throws NullPointerException
	 *             if null == date
	 */
	private Date getIncomeDate(Date date) throws NullPointerException{
		if (null == date){
			throw new NullPointerException("the date is null or empty!");
		}

		//对日期的操作,我们需要使用 Calendar 对象
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		//+1天
		calendar.add(Calendar.DAY_OF_MONTH, +1);

		Date incomeDate = calendar.getTime();

		if (isWeekend(calendar) || isHoliday(calendar)){
			//递归
			return getIncomeDate(incomeDate);
		}
		return incomeDate;
	}

	/**
	 * 判断一个日历是不是周末.
	 *
	 * @param calendar
	 *            the calendar
	 * @return true, if checks if is weekend
	 */
	private boolean isWeekend(Calendar calendar){
		//判断是星期几
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

		if (dayOfWeek == 1 || dayOfWeek == 7){
			return true;
		}
		return false;
	}

	/**
	 * 一个日历是不是节假日.
	 *
	 * @param calendar
	 *            the calendar
	 * @return true, if checks if is holiday
	 */
	private boolean isHoliday(Calendar calendar){
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String dateString = simpleDateFormat.format(calendar.getTime());

		//节假日 这个可能不同地区,不同年份 都有可能不一样,所以需要有个地方配置, 可以放数据库, 配置文件,环境变量 等等地方
		//这里以配置文件 为例子
		ResourceBundle resourceBundle = ResourceBundle.getBundle("holidayConfig");
		String holidays = resourceBundle.getString("holiday");

		String[] holidayArray = holidays.split(",");

		boolean isHoliday = org.apache.commons.lang.ArrayUtils.contains(holidayArray, dateString);
		return isHoliday;
	}

	/**
	 * Testenclosing_type.
	 *
	 * @throws ParseException
	 *             the parse exception
	 */
	@Test
	public void testGetIncomeDate() throws ParseException{
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		System.out.println(simpleDateFormat.format(getIncomeDate(new Date())));
		System.out.println(simpleDateFormat.format(getIncomeDate(simpleDateFormat.parse("2014-07-31 13:33:05"))));
		System.out.println(simpleDateFormat.format(getIncomeDate(simpleDateFormat.parse("2014-08-01 13:33:05"))));
		System.out.println(simpleDateFormat.format(getIncomeDate(simpleDateFormat.parse("2014-08-02 13:33:05"))));
		System.out.println(simpleDateFormat.format(getIncomeDate(simpleDateFormat.parse("2014-08-03 13:33:05"))));
		System.out.println(simpleDateFormat.format(getIncomeDate(simpleDateFormat.parse("2014-09-30 13:33:05"))));
		System.out.println(simpleDateFormat.format(getIncomeDate(simpleDateFormat.parse("2014-10-02 13:33:05"))));
	}
}
