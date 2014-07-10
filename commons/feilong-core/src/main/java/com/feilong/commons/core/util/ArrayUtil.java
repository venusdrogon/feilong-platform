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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.entity.JoinStringEntity;
import com.feilong.commons.core.lang.ObjectUtil;

/**
 * 数组工具类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-4-16 下午01:00:27
 * @since 1.0.0
 */
public final class ArrayUtil{

	private static final Logger	log	= LoggerFactory.getLogger(ArrayUtil.class);

	/** Don't let anyone instantiate this class. */
	private ArrayUtil(){}

	/**
	 * 将数组转成转成Iterator<br>
	 * 如果我们幸运的话，它是一个对象数组,我们可以遍历并with no copying<br>
	 * 否则,异常 ClassCastException 中 ,Rats -- 它是一个基本类型数组,循环放入arrayList 转成arrayList.iterator()
	 * 
	 * <p>
	 * <b>注:</b>{@link Arrays#asList(Object...)} 转的list是 {@link Array} 的内部类 ArrayList,这个类没有实现
	 * {@link java.util.AbstractList#add(int, Object)} 这个方法, 如果拿这个list进行add操作,会出现 {@link java.lang.UnsupportedOperationException}
	 * </p>
	 * 
	 * 
	 * @param <T>
	 *            the generic type
	 * @param arrays
	 *            数组,可以是 对象数组,或者是 基本类型数组
	 * @return if (null == arrays) return null;<br>
	 *         否则会先将arrays转成Object[]数组,调用 {@link Arrays#asList(Object...)}转成list,再调用 {@link List#iterator()
	 *         t}<br>
	 *         对于基本类型的数组,由于不是 Object[],会有类型转换异常,此时先通过 {@link Array#getLength(Object)}取到数组长度,循环调用 {@link Array#get(Object, int)}设置到 list中
	 * @see Arrays#asList(Object...)
	 * @see Array#getLength(Object)
	 * @see Array#get(Object, int)
	 * @see List#iterator()
	 */
	@SuppressWarnings({ "unchecked" })
	public static <T> Iterator<T> toIterator(Object arrays){
		if (null == arrays){
			return null;
		}
		try{
			// 如果我们幸运的话，它是一个对象数组,我们可以遍历并with no copying
			Object[] objArrays = (Object[]) arrays;
			List<T> list = (List<T>) Arrays.asList(objArrays);
			return list.iterator();
		}catch (ClassCastException e){

			if (log.isDebugEnabled()){
				log.debug("arrays can not cast to Object[],maybe primitive type,values is:{}", arrays);
			}

			// Rats -- 它是一个基本类型数组
			int length = Array.getLength(arrays);
			List<T> list = new ArrayList<T>(length);
			for (int i = 0; i < length; ++i){
				Object object = Array.get(arrays, i);
				list.add((T) object);
			}
			return list.iterator();
		}
	}

	/**
	 * 数组转成LinkedList.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param arrays
	 *            字符串数组
	 * @return 数组转成LinkedList<br>
	 *         如果 arrays isNotNullOrEmpty,返回null
	 */
	public static <T> LinkedList<T> toLinkedList(T[] arrays){
		if (Validator.isNotNullOrEmpty(arrays)){
			LinkedList<T> list = new LinkedList<T>();
			for (int i = 0, j = arrays.length; i < j; ++i){
				list.add(arrays[i]);
			}
			return list;
		}
		return null;
	}

	/**
	 * 数组转成 List(ArrayList)，此方法返回的list可以进行add操作
	 * <p>
	 * 注意 :{@link java.util.Arrays#asList(Object...)}返回的list,没有实现 {@link java.util.Collection#add(Object)}方法<br>
	 * 因此,会使用 {@link ArrayList#ArrayList(java.util.Collection)} 来进行重新封装返回
	 * </p>
	 * 
	 * @param <T>
	 *            the generic type
	 * @param arrays
	 *            T数组
	 * @return 数组转成 List(ArrayList)<br>
	 *         if Validator.isNullOrEmpty(arrays), return null,else return new ArrayList<T>(Arrays.asList(arrays));
	 * @see java.util.Arrays#asList(Object...)
	 */
	public static <T> List<T> toList(T[] arrays){
		if (Validator.isNullOrEmpty(arrays)){
			return null;
		}
		// 这个地方返回的是Arrays类的内部类的对象，而Arrays类里的内部类ArrayList没有实现AbstractList类的add方法，导致抛此异常! strList.add("c");
		// return Arrays.asList(arrays);

		List<T> list = new ArrayList<T>(Arrays.asList(arrays));
		return list;
	}

	/**
	 * 任意的数组转成Integer 数组.
	 * 
	 * @param objects
	 *            objects
	 * @return Validator.isNotNullOrEmpty(objects)则返回null<br>
	 *         一旦其中有值转换不了integer,则出现参数异常
	 */
	public static Integer[] toIntegers(Object[] objects){
		if (Validator.isNotNullOrEmpty(objects)){
			int length = objects.length;
			Integer[] integers = new Integer[length];
			for (int i = 0; i < length; i++){
				integers[i] = ObjectUtil.toInteger(objects[i]);
			}
			return integers;
		}
		return null;
	}

	/**
	 * 判断 一个数组中,是否包含某个特定的值<br>
	 * 使用equals 来比较,所以如果是 对象类型 需要自己实现equals方法.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param arrays
	 *            数组
	 * @param value
	 *            特定值
	 * @return 如果 Validator.isNotNullOrEmpty(array) 返回false <br>
	 *         如果包含,则返回true,否则返回false<br>
	 */
	public static <T> boolean isContain(T[] arrays,T value){
		if (Validator.isNotNullOrEmpty(arrays)){
			for (T arr : arrays){
				if (arr.equals(value)){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 将数组 通过 joinStringEntity 拼接成 字符串.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param arrays
	 *            请使用包装类型,比如 Integer []arrays,而不是 int []arrays
	 * @param joinStringEntity
	 *            the join string entity
	 * @return <ul>
	 *         <li>如果 arrays 是null 或者Empty ,返回null</li>
	 *         <li>否则循环,拼接joinStringEntity.getConnector()</li>
	 *         </ul>
	 */
	public static <T> String toString(T[] arrays,JoinStringEntity joinStringEntity){
		if (Validator.isNotNullOrEmpty(arrays)){

			StringBuilder sb = new StringBuilder();
			for (int i = 0, j = arrays.length; i < j; ++i){
				T t = arrays[i];

				sb.append(t);
				// 不是最后一个 拼接
				if (i != j - 1){
					sb.append(joinStringEntity.getConnector());
				}
			}
			return sb.toString();
		}
		return null;
	}
}
