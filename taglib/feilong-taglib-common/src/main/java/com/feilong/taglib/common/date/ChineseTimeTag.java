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
package com.feilong.taglib.common.date;

import java.util.Date;

import com.feilong.commons.core.date.DateExtensionUtil;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.taglib.base.AbstractCommonTag;

/**
 * 日期转换成中国特色日期.
 * 
 * @author 金鑫 2009-9-7上午11:49:48
 */
public class ChineseTimeTag extends AbstractCommonTag{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** 日期. */
	private Date				date				= null;

	/** 日期字符串 dateString和pattern结合使用,dateString优先于date. */
	private String				dateString			= null;

	/** 日期字符串模式pattern结合使用. */
	private String				pattern				= null;

	/** 仅仅显示日期. */
	private boolean				onlyChinese;

	/** 带不带颜色. */
	private boolean				hasColor;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.taglib.base.AbstractCommonTag#writeContent()
	 */
	@Override
	public String writeContent(){
		/**
		 * 日期字符串 dateString和pattern结合使用,dateString优先于date
		 */
		if (Validator.isNotNullOrEmpty(dateString)){
			date = DateUtil.string2Date(dateString, pattern);
		}
		if (Validator.isNotNullOrEmpty(date)){
			// 仅仅显示日期
			if (onlyChinese){
				return DateUtil.date2String(date, "yyyy年MM月dd日");
			}
			if (hasColor){
				//return DateUtil.convertDateToChineseDate2(date);
			}
			return DateExtensionUtil.toHumanizationDateString(date);
		}
		return "";
	}

	/**
	 * 设置 日期.
	 * 
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date){
		this.date = date;
	}

	/**
	 * 设置 仅仅显示日期.
	 * 
	 * @param onlyChinese
	 *            the onlyChinese to set
	 */
	public void setOnlyChinese(boolean onlyChinese){
		this.onlyChinese = onlyChinese;
	}

	/**
	 * 设置 带不带颜色.
	 * 
	 * @param hasColor
	 *            the hasColor to set
	 */
	public void setHasColor(boolean hasColor){
		this.hasColor = hasColor;
	}

	/**
	 * 设置 日期字符串 dateString和pattern结合使用,dateString优先于date.
	 * 
	 * @param dateString
	 *            the dateString to set
	 */
	public void setDateString(String dateString){
		this.dateString = dateString;
	}

	/**
	 * 设置 日期字符串模式pattern结合使用.
	 * 
	 * @param pattern
	 *            the pattern to set
	 */
	public void setPattern(String pattern){
		this.pattern = pattern;
	}
}
