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
 * <p>
 * 通用验证类,feilong-core核心类之一,判断对象是否是null或者空
 * </p>
 * <ol>
 * <li>{@link #isNullOrEmpty(Object)} 判断对象是否是null或者空</li>
 * <li>{@link #isNotNullOrEmpty(Object)}判断对象是否不是null或者不是空</li>
 * </ol>
 * .
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 2, 2010 8:35:28 PM
 * @version 1.0.1 2012-9-23 21:34 api更新,rename method,isNullOrEmpty替换isNull
 * @since 1.0.0
 * @see String#trim()
 * @see Map#isEmpty()
 * @see Collection#isEmpty()
 * @see Enumeration#hasMoreElements()
 * @see Iterator#hasNext()
 */
public final class Validator{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private final static Logger	log	= LoggerFactory.getLogger(Validator.class);

	/** Don't let anyone instantiate this class. */
	private Validator(){}

	/**
	 * 判断对象是否为Null或者Empty
	 * <p>
	 * 对于empty的判断:
	 * </p>
	 * <ol>
	 * <li>{@link Collection},使用其 {@link Collection#isEmpty()}</li>
	 * <li>{@link Map},使用其 {@link Map#isEmpty()}</li>
	 * <li><code>Object[]</code>,判断length==0</li>
	 * <li>{@link String},使用 {@link String#trim()}.length()<=0效率高</li>
	 * <li>{@link Enumeration},使用 {@link Enumeration#hasMoreElements()}</li>
	 * <li>{@link Iterator},使用 {@link Iterator#hasNext()}</li>
	 * </ol>
	 * 
	 * @param value
	 *            可以是Collection,Map,Object[],String,Enumeration,Iterator类型
	 * @return 如果是null,返回true;如果是空也返回true,其他情况返回false
	 */
	@SuppressWarnings("rawtypes")
	public final static boolean isNullOrEmpty(Object value){
		if (null == value){
			return true;
		}
		// *****************************************************************************
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
	 * 判断对象是否不为Null或者Empty,调用 !{@link #isNullOrEmpty(Object)} 方法 <br>
	 * <p>
	 * 对于empty的判断:
	 * </p>
	 * <ol>
	 * <li>{@link Collection},使用其 {@link Collection#isEmpty()}</li>
	 * <li>{@link Map},使用其 {@link Map#isEmpty()}</li>
	 * <li><code>Object[]</code>,判断length==0</li>
	 * <li>{@link String},使用 {@link String#trim()}.length()<=0效率高</li>
	 * <li>{@link Enumeration},使用 {@link Enumeration#hasMoreElements()}</li>
	 * <li>{@link Iterator},使用 {@link Iterator#hasNext()}</li>
	 * </ol>
	 * 
	 * @param value
	 *            可以是Collection,Map,Object[],String,Enumeration,Iterator类型
	 * @return 如果是null,返回false;如果是空也返回false,其他情况返回true
	 */
	public final static boolean isNotNullOrEmpty(Object value){
		return !isNullOrEmpty(value);
	}

}