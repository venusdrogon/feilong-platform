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

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang.enums.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.HttpMethodType;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;

/**
 * enum工具类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.6 2014年5月8日 上午3:30:51
 * @see EnumUtils
 * @see org.apache.commons.lang3.EnumUtils
 * @since 1.0.6
 */
public class EnumUtil{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(EnumUtil.class);

	/**
	 * 通过fieldName的 value(忽视大小写) 获得枚举<br>
	 * 方法内部通过反射的机制来读取 get+fieldName首字母大写方法 获得枚举value 循环和value 忽视大小写比较,如果相等即返回. <br>
	 * 
	 * <pre>
	 * 
	 * 适用于这种{@link HttpMethodType} 待自定义属性的枚举类型,调用方式:
	 * 
	 * {@code
	 * 	EnumUtil.getEnumByField(HttpMethodType.class, "method", "get")
	 * }
	 * </pre>
	 * 
	 * @param <E>
	 *            the element type
	 * @param enumClass
	 *            the enum class 比如 {@link HttpMethodType}
	 * @param fieldName
	 *            字段名称,比如 {@link HttpMethodType}的method,按照javabean 规范
	 * @param value
	 *            属性值 比如post,此处判断 忽视大小写
	 * @return 如果 value 是null,返回null<br>
	 *         如果查找不到,也返回null<br>
	 *         如果出现异常,也返回null<br>
	 *         其他情况 方法内部通过反射的机制来读取 get+fieldName首字母大写方法 获得枚举value 循环和value 忽视大小写比较,如果相等即返回
	 */
	public static <E extends Enum<E>> E getEnumByField(Class<E> enumClass,String fieldName,String value){
		if (Validator.isNullOrEmpty(fieldName)){
			throw new NullPointerException("the fieldName is null or empty!");
		}

		if (Validator.isNotNullOrEmpty(value)){

			// An enum is a kind of class
			// and an annotation is a kind of interface
			// 如果此 Class 对象不表示枚举类型，则返回枚举类的元素或 null。
			E[] enumConstants = enumClass.getEnumConstants();

			try{
				for (E e : enumConstants){
					String getterMethodName = StringUtil.getGetterMethodName(fieldName);
					Object invokeMethod = ReflectUtil.invokeMethod(e, getterMethodName);

					if (log.isInfoEnabled()){
						log.info("" + JsonUtil.format(e));
						log.info("invokeMethod value:{}", invokeMethod);
					}
					if (value.equalsIgnoreCase(invokeMethod.toString())){
						return e;
					}
				}
			}catch (IllegalArgumentException e){
				e.printStackTrace();
			}catch (SecurityException e){
				e.printStackTrace();
			}catch (IllegalAccessException e){
				e.printStackTrace();
			}catch (InvocationTargetException e){
				e.printStackTrace();
			}catch (NoSuchMethodException e){
				e.printStackTrace();
			}
		}
		return null;
	}
}
