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
package com.feilong.commons.core.util;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 飞龙通用验证类,该类最实用的方法,核心类之一<br>
 * isNullOrEmpty(Object value),判断元素是否为空.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 2, 2010 8:35:28 PM
 * @version 1.1 2012-9-23 21:34 api更新,isNullOrEmpty 替换isNull(deprecated)
 * @since 1.0
 */
public final class Validator{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private final static Logger	log	= LoggerFactory.getLogger(Validator.class);

	/** Don't let anyone instantiate this class. */
	private Validator(){}

	/**
	 * 判断元素是否为Null或者Empty
	 * <p>
	 * 目前可以判断一下元素
	 * <ul>
	 * <li>Collection,使用其isEmpty()</li>
	 * <li>Map,使用其isEmpty()</li>
	 * <li>Object[],判断length==0</li>
	 * <li>String,使用.trim().length()效率高</li>
	 * <li>Enumeration,使用hasMoreElements()</li>
	 * <li>Iterator,使用hasNext()</li>
	 * </ul>
	 * 
	 * @param value
	 *            可以是Collection,Map,Object[],String,Enumeration,Iterator类型
	 * @return 空返回true
	 * @since 1.0
	 */
	@SuppressWarnings("rawtypes")
	public final static boolean isNullOrEmpty(Object value){
		if (null == value){
			return true;
		}
		/*****************************************************************************/
		boolean flag = false;
		// 字符串
		if (value instanceof String){
			// 比较字符串长度, 效率高
			flag = value.toString().trim().length() <= 0;
		}
		// Object[]object数组
		else if (value instanceof Object[]){
			flag = ((Object[]) value).length == 0;
		}
		// ***********************************************************
		// 集合
		else if (value instanceof Collection){
			flag = ((Collection) value).isEmpty();
		}
		// map
		else if (value instanceof Map){
			flag = ((Map) value).isEmpty();
		}
		// 枚举
		else if (value instanceof Enumeration){
			flag = !((Enumeration) value).hasMoreElements();
		}
		// Iterator迭代器
		else if (value instanceof Iterator){
			flag = !((Iterator) value).hasNext();
		}
		return flag;
	}

	/**
	 * 判断元素是否不为Null或者Empty,调用<code>!isNullOrEmpty</code>方法
	 * <p>
	 * 目前可以判断一下元素
	 * <ul>
	 * <li>Collection,使用其isEmpty()</li>
	 * <li>Map,使用其isEmpty()</li>
	 * <li>Object[],判断length==0</li>
	 * <li>String,使用.trim().length()效率高</li>
	 * <li>Enumeration,使用hasMoreElements()</li>
	 * <li>Iterator,使用hasNext()</li>
	 * </ul>
	 * 
	 * @param value
	 *            可以是Collection,Map,Object[],String,Enumeration,Iterator类型
	 * @return 不为空返回true
	 * @since 1.0
	 */
	public final static boolean isNotNullOrEmpty(Object value){
		return !isNullOrEmpty(value);
	}

}