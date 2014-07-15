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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.lang.ClassUtil;
import com.feilong.commons.core.util.Validator;

/**
 * 使用反射的方式请求bean中的方法。
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年7月15日 下午1:08:15
 * @see org.apache.commons.lang3.reflect.MethodUtils
 * @since 1.0.7
 */
public final class MethodUtil{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(MethodUtil.class);

	private MethodUtil(){};

	// [start]

	/**
	 * 执行某对象方法.
	 * 
	 * @param owner
	 *            对象
	 * @param methodName
	 *            方法名
	 * @param params
	 *            参数
	 * @return 如果找不到method,返回null,其他调用method 的invoke方法
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws SecurityException
	 *             the security exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 */
	public static Object invokeMethod(Object owner,String methodName,Object...params) throws IllegalArgumentException,
			IllegalAccessException,InvocationTargetException,SecurityException,NoSuchMethodException{
		Class<?> ownerClass = owner.getClass();
		Method method = getMethod(ownerClass, methodName, params);
		if (null == method){
			return null;
		}
		return method.invoke(owner, params);
	}

	/**
	 * 执行静态方法.
	 * 
	 * @param className
	 *            类名
	 * @param methodName
	 *            方法名
	 * @param params
	 *            动态参数
	 * @return 该方法执行之后的结果
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws SecurityException
	 *             the security exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 */
	public static Object invokeStaticMethod(String className,String methodName,Object...params) throws IllegalArgumentException,
			IllegalAccessException,InvocationTargetException,ClassNotFoundException,SecurityException,NoSuchMethodException{
		Class<?> ownerClass = ClassUtil.loadClass(className);
		Method method = getMethod(ownerClass, methodName, params);
		if (null != method){
			// 如果底层方法是静态的，那么可以忽略指定的 obj 参数。该参数可以为 null。 从中调用底层方法的对象
			// 如果底层方法所需的形参数为 0，则所提供的 args 数组长度可以为 0 或 null 用于方法调用的参数
			return method.invoke(null, params);
		}
		return null;
	}

	/**
	 * 获得方法.
	 * 
	 * @param ownerClass
	 *            类
	 * @param methodName
	 *            方法名
	 * @param paramValues
	 *            动态参数值,程序会基于参数值 转成参数类型
	 * @return 该方法
	 * @throws SecurityException
	 *             the security exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 */
	private static Method getMethod(Class<?> ownerClass,String methodName,Object...paramValues) throws SecurityException,
			NoSuchMethodException{
		if (Validator.isNullOrEmpty(ownerClass)){
			throw new IllegalArgumentException("ownerClass can't be null/empty!");
		}
		if (Validator.isNullOrEmpty(methodName)){
			throw new IllegalArgumentException("methodName can't be null/empty!");
		}

		log.debug("input param ownerClass is :" + ownerClass);
		log.debug("input param methodName is :" + methodName);
		log.debug("input param params is :" + paramValues);

		Class<?>[] parameterTypes = ClassUtil.toParameterTypes(paramValues);
		/**
		 * 它反映此 Class 对象所表示的类或接口的指定公共成员方法。<br>
		 * name 参数是一个 String，用于指定所需方法的简称。<br>
		 * parameterTypes 参数是按声明顺序标识该方法形参类型的 Class 对象的一个数组。如果 parameterTypes 为 null，则按空数组处理。
		 */
		Method method = ownerClass.getMethod(methodName, parameterTypes);
		return method;
	}

	// [end]
}
