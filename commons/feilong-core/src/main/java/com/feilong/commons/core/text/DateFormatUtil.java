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
package com.feilong.commons.core.text;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.Validator;

/**
 * DateFormat 是日期/时间格式化子类的抽象类,<br>
 * 直接已知子类： SimpleDateFormat.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-27 上午1:39:38
 * @see Format
 * @see DateFormat
 * @see SimpleDateFormat
 */
public class DateFormatUtil{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(DateFormatUtil.class);

	/**
	 * format 日期类型 格式化成字符串类型.
	 * 
	 * @param date
	 *            the date
	 * @param pattern
	 *            the pattern
	 * @return the string
	 */
	public static String format(Date date,String pattern){
		return format(date, pattern, Locale.getDefault());
	}

	/**
	 * format 日期类型 格式化成字符串类型.
	 * 
	 * @param date
	 *            the date
	 * @param pattern
	 *            the pattern
	 * @param locale
	 *            the locale
	 * @return the string
	 */
	public static String format(Date date,String pattern,Locale locale){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale);
		String format = simpleDateFormat.format(date);
		return format;
	}

	/**
	 * parse 字符串类型转成日期类型.
	 * 
	 * @param dateString
	 *            the date string
	 * @param pattern
	 *            the pattern
	 * @return the date
	 */
	public static Date parse(String dateString,String pattern){
		return parse(dateString, pattern, Locale.getDefault());
	}

	/**
	 * 字符串类型转成日期类型.
	 * 
	 * @param dateString
	 *            the date string
	 * @param pattern
	 *            the pattern
	 * @param locale
	 *            the locale
	 * @return the date
	 */
	public static Date parse(String dateString,String pattern,Locale locale){
		if (Validator.isNotNullOrEmpty(dateString)){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale);
			ParsePosition parsePosition = new ParsePosition(0);
			Date date = simpleDateFormat.parse(dateString, parsePosition);
			// Object[] objects = { dateString, pattern, parsePosition };
			// log.debug("dateString:[{}], pattern:[{}], parsePosition:[{}]", objects);
			return date;
		}
		throw new IllegalArgumentException("param dateString can not be null");
	}
}
