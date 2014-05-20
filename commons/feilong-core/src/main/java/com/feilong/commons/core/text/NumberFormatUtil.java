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

import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * NumberFormat 是所有数值格式的抽象基类,此类提供格式化和解析数值的接口<br>
 * 直接已知子类： ChoiceFormat, DecimalFormat.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-27 上午1:39:53
 * @see Format
 * @see NumberFormat
 * @see DecimalFormat
 */
public class NumberFormatUtil{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(NumberFormatUtil.class);

	/**
	 * Format.
	 * 
	 * @param value
	 *            the value
	 * @param pattern
	 *            the pattern
	 * @return the string<br>
	 *         如果有异常 将返回null
	 */
	public static String format(Number value,String pattern){
		try{
			DecimalFormat decimalFormat = new DecimalFormat(pattern);
			// decimalFormat.applyPattern("##,###.000");
			return decimalFormat.format(value);
		}catch (Exception e){
			Object[] objects = { e.getMessage(), value, pattern };
			log.error("{},传入的参数为,[value:{}],[pattern:{}]", objects);
		}
		return null;
	}
}
