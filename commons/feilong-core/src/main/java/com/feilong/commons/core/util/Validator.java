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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * 判断对象是否为null或者Empty,feilong-core核心类之一.
 * </p>
 * <ol>
 * <li>{@link #isNullOrEmpty(Object)} 判断对象是否是null或者空</li>
 * <li>{@link #isNotNullOrEmpty(Object)}判断对象是否不是null或者不是空</li>
 * </ol>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 2, 2010 8:35:28 PM
 * @version 1.0.1 2012-9-23 21:34 rename method,isNullOrEmpty替代isNull
 * @version 1.0.7 2014-5-22 15:57 add {@link #arrayIsNullOrEmpty(Object)}
 * @see String#trim()
 * @see Map#isEmpty()
 * @see Collection#isEmpty()
 * @see Enumeration#hasMoreElements()
 * @see Iterator#hasNext()
 * @since 1.0.0
 */
public final class Validator{

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
	 * <li>{@link String},使用 {@link String#trim()}{@code .length()<=0}效率高</li>
	 * <li>{@link Enumeration},使用 {@link Enumeration#hasMoreElements()}</li>
	 * <li>{@link Iterator},使用 {@link Iterator#hasNext()}</li>
	 * <li><code>Object[]</code>,判断length==0,注二维数组不管是primitive 还是包装类型,都instanceof Object[]</li>
	 * <li><code>byte[]</code>,判断length==0</li>
	 * <li><code>boolean[]</code>,判断length==0</li>
	 * <li><code>char[]</code>,判断length==0</li>
	 * <li><code>int[]</code>,判断length==0</li>
	 * <li><code>short[]</code>,判断length==0</li>
	 * <li><code>float[]</code>,判断length==0</li>
	 * <li><code>double[]</code>,判断length==0</li>
	 * </ol>
	 * 
	 * @param value
	 *            可以是Collection,Map,String,Enumeration,Iterator,以及所有数组类型
	 * @return 如果是null,返回true<br>
	 *         如果是empty也返回true<br>
	 *         其他情况返回false<br>
	 *         如果不是上述类型,不判断empty,返回false
	 * @see CollectionUtils#isEmpty(Collection)
	 * @see CollectionUtils#isNotEmpty(Collection)
	 * @see StringUtils#isBlank(String)
	 * @see StringUtils#isEmpty(String)
	 * @see MapUtils#isEmpty(Map)
	 * @see MapUtils#isNotEmpty(Map)
	 * @see ArrayUtils#isEmpty(byte[])
	 * @see ArrayUtils#isEmpty(boolean[])
	 * @see ArrayUtils#isEmpty(char[])
	 * @see ArrayUtils#isEmpty(int[])
	 * @see ArrayUtils#isEmpty(long[])
	 * @see ArrayUtils#isEmpty(short[])
	 * @see ArrayUtils#isEmpty(float[])
	 * @see ArrayUtils#isEmpty(double[])
	 * @see ArrayUtils#isEmpty(Object[])
	 */
	public final static boolean isNullOrEmpty(Object value){
		if (null == value){
			return true;
		}
		// *****************************************************************************

		// 字符串
		if (value instanceof String){// 比较字符串长度, 效率高
			return value.toString().trim().length() <= 0;
		}

		// 集合
		if (value instanceof Collection){
			return ((Collection<?>) value).isEmpty();
		}

		// map
		if (value instanceof Map){
			return ((Map<?, ?>) value).isEmpty();
		}

		// 枚举
		if (value instanceof Enumeration){
			return !((Enumeration<?>) value).hasMoreElements();
		}

		// Iterator迭代器
		if (value instanceof Iterator){
			return !((Iterator<?>) value).hasNext();
		}

		boolean arrayFlag = arrayIsNullOrEmpty(value);
		if (arrayFlag){
			return true;
		}
		// 这里可以扩展
		return false;
	}

	/**
	 * 判断对象是否不为Null或者Empty,调用 !{@link #isNullOrEmpty(Object)} 方法 <br>
	 * <p>
	 * 对于empty的判断:
	 * </p>
	 * <ol>
	 * <li>{@link Collection},使用其 {@link Collection#isEmpty()}</li>
	 * <li>{@link Map},使用其 {@link Map#isEmpty()}</li>
	 * <li>{@link String},使用 {@link String#trim()}{@code .length()<=0}效率高</li>
	 * <li>{@link Enumeration},使用 {@link Enumeration#hasMoreElements()}</li>
	 * <li>{@link Iterator},使用 {@link Iterator#hasNext()}</li>
	 * <li><code>Object[]</code>,判断length==0</li>
	 * <li><code>byte[]</code>,判断length==0</li>
	 * <li><code>boolean[]</code>,判断length==0</li>
	 * <li><code>char[]</code>,判断length==0</li>
	 * <li><code>int[]</code>,判断length==0</li>
	 * <li><code>short[]</code>,判断length==0</li>
	 * <li><code>float[]</code>,判断length==0</li>
	 * <li><code>double[]</code>,判断length==0</li>
	 * </ol>
	 * 
	 * @param value
	 *            可以是Collection,Map,String,Enumeration,Iterator,以及所有数组类型
	 * @return 如果是null,返回false<br>
	 *         如果是空也返回false<br>
	 *         其他情况返回true<br>
	 *         如果不是上述类型,不判断empty,返回true
	 * @see CollectionUtils#isEmpty(Collection)
	 * @see CollectionUtils#isNotEmpty(Collection)
	 * @see StringUtils#isBlank(String)
	 * @see StringUtils#isEmpty(String)
	 * @see MapUtils#isEmpty(Map)
	 * @see MapUtils#isNotEmpty(Map)
	 * @see ArrayUtils#isEmpty(byte[])
	 * @see ArrayUtils#isEmpty(boolean[])
	 * @see ArrayUtils#isEmpty(char[])
	 * @see ArrayUtils#isEmpty(int[])
	 * @see ArrayUtils#isEmpty(long[])
	 * @see ArrayUtils#isEmpty(short[])
	 * @see ArrayUtils#isEmpty(float[])
	 * @see ArrayUtils#isEmpty(double[])
	 * @see ArrayUtils#isEmpty(Object[])
	 */
	public final static boolean isNotNullOrEmpty(Object value){
		return !isNullOrEmpty(value);
	}

	/**
	 * 数组 类型的验证,区分 primitive 和包装类型.
	 * 
	 * @param value
	 *            可以是
	 *            <ul>
	 *            <li>Object[] 二维数组属于这个类型</li>
	 *            <li>byte[]</li>
	 *            <li>boolean[]</li>
	 *            <li>char[]</li>
	 *            <li>int[]</li>
	 *            <li>long[]</li>
	 *            <li>short[]</li>
	 *            <li>float[]</li>
	 *            <li>double[]</li>
	 *            </ul>
	 * @return 如果是数组类型(区分 primitive和包装类型),判断其length==0;<br>
	 *         如果不是 直接返回false
	 * @see ArrayUtils#isEmpty(byte[])
	 * @see ArrayUtils#isEmpty(boolean[])
	 * @see ArrayUtils#isEmpty(char[])
	 * @see ArrayUtils#isEmpty(int[])
	 * @see ArrayUtils#isEmpty(long[])
	 * @see ArrayUtils#isEmpty(short[])
	 * @see ArrayUtils#isEmpty(float[])
	 * @see ArrayUtils#isEmpty(double[])
	 * @see ArrayUtils#isEmpty(Object[])
	 * @since 1.0.7
	 */
	private static boolean arrayIsNullOrEmpty(Object value){
		// ***********************************************************
		// 数组 Integer/String...自定义的对象User.等数组也 instanceof Object[]
		if (value instanceof Object[]){
			// if (log.isDebugEnabled()){
			// log.debug("value:{} instanceof Object[]", JsonUtil.format(value));
			// }
			return ((Object[]) value).length == 0;
		}

		// primitive ints
		if (value instanceof int[]){
			// if (log.isDebugEnabled()){
			// log.debug("value:{} instanceof int[]", value);
			// }
			return ((int[]) value).length == 0;
		}

		// primitive long
		if (value instanceof long[]){
			// if (log.isDebugEnabled()){
			// log.debug("value:{} instanceof long[]", value);
			// }
			return ((long[]) value).length == 0;
		}

		// primitive float
		if (value instanceof float[]){
			// if (log.isDebugEnabled()){
			// log.debug("value:{} instanceof float[]", value);
			// }
			return ((float[]) value).length == 0;
		}

		// primitive double
		if (value instanceof double[]){
			// if (log.isDebugEnabled()){
			// log.debug("value:{} instanceof double[]", value);
			// }
			return ((double[]) value).length == 0;
		}

		// primitive char
		if (value instanceof char[]){
			// if (log.isDebugEnabled()){
			// log.debug("value:{} instanceof char[]", value);
			// }
			return ((char[]) value).length == 0;
		}

		// primitive boolean
		if (value instanceof boolean[]){
			// if (log.isDebugEnabled()){
			// log.debug("value:{} instanceof boolean[]", value);
			// }
			return ((boolean[]) value).length == 0;
		}

		// primitive byte
		if (value instanceof byte[]){
			// if (log.isDebugEnabled()){
			// log.debug("value:{} instanceof byte[]", value);
			// }
			return ((byte[]) value).length == 0;
		}

		// primitive short
		if (value instanceof short[]){
			// if (log.isDebugEnabled()){
			// log.debug("value:{} instanceof short[]", value);
			// }
			return ((short[]) value).length == 0;
		}
		return false;
	}
}