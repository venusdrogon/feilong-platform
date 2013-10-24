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
 * 直接已知子类： SimpleDateFormat
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-27 上午1:39:38
 * @see Format
 * @see DateFormat
 * @see SimpleDateFormat
 */
public class DateFormatUtil{

	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(DateFormatUtil.class);

	/**
	 * format 日期类型 格式化成字符串类型
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date,String pattern){
		return format(date, pattern, Locale.getDefault());
	}

	/**
	 * format 日期类型 格式化成字符串类型
	 * 
	 * @param date
	 * @param pattern
	 * @param locale
	 * @return
	 */
	public static String format(Date date,String pattern,Locale locale){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale);
		String format = simpleDateFormat.format(date);
		return format;
	}

	/**
	 * parse 字符串类型转成日期类型
	 * 
	 * @param dateString
	 * @param pattern
	 * @return
	 */
	public static Date parse(String dateString,String pattern){
		return parse(dateString, pattern, Locale.getDefault());
	}

	/**
	 * 字符串类型转成日期类型
	 * 
	 * @param dateString
	 * @param pattern
	 * @param locale
	 * @return
	 */
	public static Date parse(String dateString,String pattern,Locale locale){
		if (Validator.isNotNullOrEmpty(dateString)){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale);
			ParsePosition parsePosition = new ParsePosition(0);
			Date date = simpleDateFormat.parse(dateString, parsePosition);
			//Object[] objects = { dateString, pattern, parsePosition };
			//log.debug("dateString:[{}], pattern:[{}], parsePosition:[{}]", objects);
			return date;
		}
		throw new IllegalArgumentException("param dateString can not be null");
	}
}
