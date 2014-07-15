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

import java.lang.reflect.Constructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.lang.ClassUtil;

/**
 * 使用反射的方式构造Bean的新实例.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年7月15日 下午1:08:15
 * @see org.apache.commons.lang3.reflect.ConstructorUtils
 * @since 1.0.7
 */
public final class ConstructorUtil{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(ConstructorUtil.class);

	/**
	 * Instantiates a new constructor util.
	 */
	private ConstructorUtil(){};

	// [start] newInstance

	/**
	 * 新建实例<br>
	 * 示例:
	 * 
	 * <pre>
	 * {@code
	 * User user = ConstructorUtil.newInstance("com.feilong.test.User") 将返回user 对象
	 * 
	 * 你还可以 
	 * User user1 = ConstructorUtil.newInstance("com.feilong.test.User", 100L); 返回 id 是100的构造函数
	 * }
	 * </pre>
	 * 
	 * @param <T>
	 *            t
	 * @param className
	 *            类得名称,比如 com.feilong.test.User
	 * @param parameterValues
	 *            构造函数的参数
	 * @return 新建的实例,如果结果不能转成T 会抛出异常
	 * @throws ReflectException
	 *             the reflect exception
	 * @see ClassUtil#loadClass(String)
	 * @see #newInstance(Class, Object...)
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(String className,Object...parameterValues) throws ReflectException{
		try{
			// 装载连接初始化类
			Class<?> klass = ClassUtil.loadClass(className);
			Object newInstance = newInstance(klass, parameterValues);
			return (T) newInstance;
		}catch (Exception e){
			e.printStackTrace();
			throw new ReflectException(e);
		}
	}

	/**
	 * 新建实例.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param klass
	 *            类
	 * @param parameterValues
	 *            构造函数的参数值, 比如100L
	 * @return <ol>
	 *         <li>if null==klass,抛出 {@link IllegalArgumentException}</li>
	 *         <li>如果 args isNotNullOrEmpty,则构建{@code Class<?>[] parameterTypes}</li>
	 *         <li>{@link Class#getConstructor(Class...)} 获得构造函数</li>
	 *         <li>{@link Constructor#newInstance(Object...)}实例化</li>
	 *         </ol>
	 * @throws ReflectException
	 *             the reflect exception
	 * @see com.feilong.commons.core.lang.ClassUtil#toParameterTypes(Object...)
	 * @see java.lang.Class#getConstructor(Class...)
	 * @see java.lang.reflect.Constructor#newInstance(Object...)
	 * @see org.apache.commons.lang3.reflect.ConstructorUtils#invokeConstructor(Class, Object...)
	 */
	public static <T> T newInstance(Class<T> klass,Object...parameterValues) throws ReflectException{
		if (null == klass){
			throw new IllegalArgumentException("class can't be null");
		}

		try{
			Class<?>[] parameterTypes = ClassUtil.toParameterTypes(parameterValues);
			Constructor<T> constructor = klass.getConstructor(parameterTypes);

			// 使用此 Constructor 对象表示的构造方法来创建该构造方法的声明类的新实例，并用指定的初始化参数初始化该实例。个别参数会自动解包，以匹配基本形参，必要时，基本参数和引用参数都要进行方法调用转换。
			return constructor.newInstance(parameterValues);
		}catch (Exception e){
			e.printStackTrace();
			throw new ReflectException(e);
		}
	}
}
