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
package com.feilong.commons.core.lang.reflect;

import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 反射工具类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0.0 2010-1-18 下午06:35:58
 * @version 1.0.1 Apr 11, 2014 10:45:26 PM
 * @see org.apache.commons.lang3.reflect.ConstructorUtils
 * @see org.apache.commons.lang3.reflect.FieldUtils
 * @see org.apache.commons.lang3.reflect.MethodUtils
 * @see org.apache.commons.lang3.reflect.TypeUtils
 * @see org.apache.commons.lang3.reflect.InheritanceUtils
 * @see java.lang.reflect.Modifier
 * @since 1.0.0
 */
public final class ReflectUtil{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private final static Logger	log	= LoggerFactory.getLogger(ReflectUtil.class);

	/** Don't let anyone instantiate this class. */
	private ReflectUtil(){}

	/**
	 * Gets the generic model class.<br>
	 * {@code  public class SkuItemRepositoryImpl extends BaseSolrRepositoryImpl<SkuItem, Long> implements SkuItemRepository}<br>
	 * 这样的类,取到泛型里面第一个参数 SkuItem.class
	 * 
	 * @param <T>
	 *            the generic type
	 * @param clazz
	 *            the clazz
	 * @return the generic model class
	 */
	@SuppressWarnings({ "unchecked" })
	public static <T> Class<T> getGenericModelClass(Class<?> clazz){
		Type type = clazz.getGenericSuperclass();
		while (!(type instanceof ParameterizedType) && null != clazz && Object.class != clazz){
			clazz = clazz.getSuperclass();
		}
		if (!(type instanceof ParameterizedType)){
			Class<?>[] iclazzs = clazz.getInterfaces();
			if (iclazzs.length > 0){
				int index = -1;
				if (index >= 0){
					Type[] genericInterfaces = clazz.getGenericInterfaces();
					Type type2 = genericInterfaces[index];
					if (type2 instanceof ParameterizedType){
						type = type2;
					}
				}
			}
		}
		if (!(type instanceof ParameterizedType)){
			throw new RuntimeException("Can not find the right Generic Class.");
		}
		ParameterizedType pType = (ParameterizedType) type;
		return (Class<T>) pType.getActualTypeArguments()[0];
	}

	/**
	 * 是不是某个类的实例.
	 * 
	 * @param obj
	 *            实例
	 * @param klass
	 *            类
	 * @return 如果 obj 是此类的实例，则返回 true
	 * @see java.lang.Class#isInstance(Object)
	 */
	public static boolean isInstance(Object obj,Class<?> klass){
		return klass.isInstance(obj);
	}

	/**
	 * 判断对象是否是接口.
	 * 
	 * @param ownerClass
	 *            对象class
	 * @return 是返回true
	 * @see java.lang.Class#getModifiers()
	 * @see java.lang.reflect.Modifier#isInterface(int)
	 */
	public static boolean isInterface(Class<?> ownerClass){
		// 返回此类或接口以整数编码的 Java 语言修饰符
		int flag = ownerClass.getModifiers();
		// 对类和成员访问修饰符进行解码
		return Modifier.isInterface(flag);
	}

	// [end]
}