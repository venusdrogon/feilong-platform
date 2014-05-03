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
package com.feilong.tools.json.processor;

import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;

/**
 * 时间转换 日期值处理器实现
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 26, 2013 3:49:55 PM
 */
public class JsonDateValueProcessor implements JsonValueProcessor{

	private String	datePattern	= DatePattern.commonWithTime;

	public JsonDateValueProcessor(){}

	public JsonDateValueProcessor(String datePattern){
		this.datePattern = datePattern;
	}

	public Object processArrayValue(Object value,JsonConfig jsonConfig){
		return process(value, jsonConfig);
	}

	public Object processObjectValue(String key,Object value,JsonConfig jsonConfig){
		return process(value, jsonConfig);
	}

	private Object process(Object value,JsonConfig jsonConfig){
		if (null == value){
			return null;
		}
		if (value instanceof Date){
			return DateUtil.date2String((Date) value, datePattern);
		}
		return value.toString();
	}

	/**
	 * @return the datePattern
	 */
	public String getDatePattern(){
		return datePattern;
	}

	/**
	 * @param datePattern
	 *            the datePattern to set
	 */
	public void setDatePattern(String datePattern){
		this.datePattern = datePattern;
	}
}