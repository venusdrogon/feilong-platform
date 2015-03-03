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
package com.feilong.taglib.common;

import com.feilong.commons.core.util.Validator;
import com.feilong.taglib.base.AbstractCommonTag;

/**
 * 截取页面显示内容,超过长度用省略号表示.
 * 
 * @author 金鑫2009-9-29上午10:56:56
 */
public class SubStringTag extends AbstractCommonTag{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** 需要被截取的内容. */
	private String				content;

	/** 截取长度. */
	private int					length;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.taglib.base.AbstractCommonTag#writeContent()
	 */
	@Override
	public String writeContent(){
		String returnValue = "";
		if (Validator.isNotNullOrEmpty(content)){
			content = content.trim();
			if (content.length() > length){
				returnValue = content.substring(0, length) + "...";
			}else{
				returnValue = content;
			}
		}
		return returnValue;
	}

	/**
	 * 设置 需要被截取的内容.
	 * 
	 * @param content
	 *            the new 需要被截取的内容
	 */
	public void setContent(String content){
		this.content = content;
	}

	/**
	 * 设置 截取长度.
	 * 
	 * @param length
	 *            the new 截取长度
	 */
	public void setLength(int length){
		this.length = length;
	}
}
