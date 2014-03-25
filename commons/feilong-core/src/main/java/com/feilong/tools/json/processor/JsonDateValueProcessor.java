/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
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