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
package com.feilong.commons.core.lang;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import com.feilong.commons.core.util.ArrayUtil;
import com.feilong.commons.core.util.Validator;

/**
 * object工具类.
 * 
 * @author 金鑫 2010-4-5 下午11:00:54
 * @since 1.0.0
 */
public final class ObjectUtil{

	/** Don't let anyone instantiate this class. */
	private ObjectUtil(){}

	/**
	 * 返回对象内存大小.
	 * 
	 * @param serializable
	 *            the object
	 * @return the int
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	//TODO 这个需要check下,可能有更好的方案
	public static int size(Object serializable) throws IOException{
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
		objectOutputStream.writeObject(serializable);
		objectOutputStream.close();
		return byteArrayOutputStream.size();
	}

	/**
	 * 支持将
	 * <ul>
	 * <li>逗号分隔的字符串</li>
	 * <li>数组</li>
	 * <li>map,将key 转成Iterator</li>
	 * <li>Collection</li>
	 * <li>Iterator</li>
	 * </ul>
	 * 转成Iterator.
	 * 
	 * @param object
	 *            <ul>
	 *            <li>逗号分隔的字符串</li>
	 *            <li>数组</li>
	 *            <li>map,将key 转成Iterator</li>
	 *            <li>Collection</li>
	 *            <li>Iterator</li>
	 *            </ul>
	 * @return <ul>
	 *         <li>如果 null == currentCollection 返回null,</li>
	 *         <li>否则转成Iterator</li>
	 *         </ul>
	 */
	@SuppressWarnings("rawtypes")
	public final static Iterator toIterator(Object object){
		Iterator iterator = null;
		// object 不是空
		if (null != object){
			// 数组
			if (object.getClass().isArray()){
				iterator = ArrayUtil.toIterator(object);
			}
			// Collection
			else if (object instanceof Collection){
				iterator = ((Collection) object).iterator();
			}
			// Iterator
			else if (object instanceof Iterator){
				iterator = (Iterator) object;
			}
			// map
			else if (object instanceof Map){
				iterator = ((Map) object).keySet().iterator();
			}
			// 逗号分隔的字符串
			else if (object instanceof String){
				String[] strings = object.toString().split(",");
				iterator = ArrayUtil.toIterator(strings);
			}else{
				throw new IllegalArgumentException("param object:" + object + " don't support convert to Iterator.");
			}
			// TODO Enumeration todo
		}
		return iterator;
	}

	/**
	 * 非空判断两个值是否相等 <br>
	 * 当两个值都不为空,且object.equals(object2)才返回true
	 * 
	 * @param object
	 *            object
	 * @param object2
	 *            object2
	 * @return 当两个值都不为空,且object.equals(object2)才返回true
	 */
	public final static boolean equalsNotNull(Object object,Object object2){
		if (Validator.isNotNullOrEmpty(object) && Validator.isNotNullOrEmpty(object2)){
			return object.equals(object2);
		}
		return false;
	}

	/**
	 * 判断两个值是否相等,允许两个值都为null.
	 * 
	 * @param object
	 *            object
	 * @param object2
	 *            object2
	 * @param flag_nullType
	 *            标识null和""相比的情况,默认值为false 标识不相等
	 * @return 判断两个值是否相等
	 */
	public final static Boolean equals(Object object,Object object2,boolean flag_nullType){
		if (object == object2){
			return true;
		}
		// object 是空
		if (null == object){
			// 标识null和""相比的情况
			if (flag_nullType){
				if ("".equals(object2.toString().trim())){
					return true;
				}
			}
		}else{
			// 标识null和""相比的情况
			if ("".equals(object.toString().trim())){
				if (null == object2){
					if (flag_nullType){
						return true;
					}
				}else{
					if ("".equals(object2.toString().trim())){
						return true;
					}
				}
			}else{
				return object.equals(object2);
			}
		}
		return false;
	}

	/**
	 * 判断两个值是否相等,允许两个值都为null.
	 * 
	 * @param object
	 *            object
	 * @param object2
	 *            object2
	 * @return 判断两个值是否相等 标识null和""相比的情况,默认值为false 标识不相等
	 */
	public final static Boolean equals(Object object,Object object2){
		return equals(object, object2, false);
	}

	/**
	 * 判断对象是不是boolean类型数据.
	 * 
	 * @param object
	 *            对象
	 * @return 是返回true
	 */
	public final static Boolean isBoolean(Object object){
		return object instanceof Boolean;
	}

	/**
	 * 判断对象是不是Integer类型.
	 * 
	 * @param object
	 *            对象
	 * @return 是返回true
	 */
	public final static Boolean isInteger(Object object){
		return object instanceof Integer;
	}

	/**
	 * object 类型转换成boolean类型.
	 * 
	 * @param object
	 *            object
	 * @return boolean
	 */
	public final static Boolean toBoolean(Object object){
		if (null == object){
			throw new IllegalArgumentException("object can't be null/empty!");
		}
		return Boolean.parseBoolean(object.toString());
	}

	/**
	 * object转成integer类型.
	 * 
	 * @param value
	 *            值
	 * @return <ul>
	 *         <li>如果value是null,则返回null</li>
	 *         <li>如果value本身是Integer类型,则强制转换成 (Integer) value</li>
	 *         <li>否则 new Integer(value.toString().trim())</li>
	 *         <li>如果value不能转成integer 会抛出 IllegalArgumentException异常</li>
	 *         </ul>
	 */
	public final static Integer toInteger(Object value){
		Integer returnValue = null;
		if (Validator.isNotNullOrEmpty(value)){
			// Integer
			if (value instanceof Integer){
				returnValue = (Integer) value;
			}else{
				try{
					returnValue = new Integer(value.toString().trim());
				}catch (Exception e){
					throw new IllegalArgumentException("Input param:\"" + value + "\", convert to integer exception");
				}
			}
		}
		return returnValue;
	}

	/**
	 * object类型转换成BigDecimal.
	 * 
	 * @param value
	 *            值
	 * @return BigDecimal
	 */
	public final static BigDecimal toBigDecimal(Object value){
		BigDecimal returnValue = null;
		if (Validator.isNotNullOrEmpty(value)){
			if (value instanceof BigDecimal){
				returnValue = (BigDecimal) value;
			}else{
				returnValue = new BigDecimal(value.toString().trim());
			}
		}
		return returnValue;
	}

	/**
	 * 把对象转换为long类型.
	 * 
	 * @param value
	 *            包含数字的对象.
	 * @return long 转换后的数值,对不能转换的对象返回null。
	 */
	public final static Long toLong(Object value){
		if (Validator.isNotNullOrEmpty(value)){
			if (value instanceof Long){
				return (Long) value;
			}
			return Long.parseLong(value.toString());
		}
		return null;
	}

	/**
	 * Object to double.
	 * 
	 * @param value
	 *            the value
	 * @return the double
	 */
	public final static Double toDouble(Object value){
		if (Validator.isNotNullOrEmpty(value)){
			if (value instanceof Double){
				return (Double) value;
			}
			return new Double(value.toString());
		}
		return null;
	}

	/**
	 * object to float.
	 * 
	 * @param value
	 *            the value
	 * @return the float
	 */
	public final static Float toFloat(Object value){
		if (Validator.isNotNullOrEmpty(value)){
			if (value instanceof Float){
				return (Float) value;
			}
			return new Float(value.toString());
		}
		return null;
	}

	/**
	 * object to short.
	 * 
	 * @param value
	 *            the value
	 * @return the short
	 */
	public final static Short toShort(Object value){
		if (Validator.isNotNullOrEmpty(value)){
			if (value instanceof Short){
				return (Short) value;
			}
			return new Short(value.toString());
		}
		return null;
	}

	/**
	 * 把对象转换成字符串.
	 * 
	 * @param value
	 *            参数值
	 * @return <ul>
	 *         <li>{@code (null == value) =====>return null}</li>
	 *         <li>{@code (value instanceof String) =====>return (String) value}</li>
	 *         <li>{@code return value.toString()}</li>
	 *         </ul>
	 * @since 1.0
	 */
	public final static String toString(Object value){
		if (null == value){
			return null;
		}
		if (value instanceof String){
			return (String) value;
		}
		return value.toString();
	}

	/**
	 * object to T.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param value
	 *            the value
	 * @param klass
	 *            the class1
	 * @return if null==value return null,else to class convert<br>
	 *         如果不是内置的class,将使用强制转换 (T) value
	 */
	@SuppressWarnings("unchecked")
	// XXX
	public final static <T> T toT(Object value,Class<?> klass){
		if (null == value){
			return null;
		}
		if (klass == String.class){
			return (T) toString(value);
		}else if (klass == Boolean.class){
			return (T) toBoolean(value);
		}else if (klass == Integer.class){
			return (T) toInteger(value);
		}else if (klass == BigDecimal.class){
			return (T) toBigDecimal(value);
		}else if (klass == Long.class){
			return (T) toLong(value);
		}else if (klass == Double.class){
			return (T) toDouble(value);
		}else if (klass == Float.class){
			return (T) toFloat(value);
		}else if (klass == Short.class){
			return (T) toShort(value);
		}
		return (T) value;
	}

	/**
	 * 去除空格
	 * 
	 * <pre>
	 * trim(null) --------&gt; &quot;&quot;
	 * trim(&quot;null&quot;) --------&gt; &quot;null&quot;
	 * 
	 * </pre>
	 * 
	 * .
	 * 
	 * @param obj
	 *            obj
	 * @return 去除空格
	 */
	public final static String trim(Object obj){
		return obj == null ? "" : obj.toString().trim();
	}
}
