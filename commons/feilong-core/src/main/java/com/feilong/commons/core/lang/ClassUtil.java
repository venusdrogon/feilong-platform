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

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.Validator;

/**
 * The Class ClassUtil.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-6-1 下午7:19:47
 * @since 1.0.0
 */
public final class ClassUtil{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(ClassUtil.class);

	/**
	 * debug.
	 * 
	 * @param clz
	 *            the clz
	 * @return the map for log
	 */
	public static Map<String, Object> getMapForLog(Class<? extends Object> clz){

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("clz.getComponentType()", clz.getComponentType());
		// 用来判断指定的Class类是否为一个基本类型。
		map.put("clz.isPrimitive()", clz.isPrimitive());
		map.put("clz.isLocalClass()", clz.isLocalClass());
		map.put("clz.isMemberClass()", clz.isMemberClass());
		map.put("clz.isSynthetic()", clz.isSynthetic());
		map.put("clz.isArray()", clz.isArray());
		map.put("clz.isAnnotation()", clz.isAnnotation());
		map.put("clz.isAnonymousClass()", clz.isAnonymousClass());
		map.put("clz.isEnum()", clz.isEnum());

		return map;
	}

	/**
	 * 返回一个类.
	 * 
	 * <ol>
	 * <li>Class cl=对象引用o.getClass();<br>
	 * 返回引用o运行时真正所指的对象(因为:儿子对象的引用可能会赋给父对象的引用变量中)所属的类O的Class的对象。<br>
	 * 谈不上对类O做什么操作。</li>
	 * <li>Class cl=A.class;<br>
	 * JVM将使用类A的类装载器,将类A装入内存(前提:类A还没有装入内存),不对类A做类的初始化工作.<br>
	 * 返回类A的Class的对象。</li>
	 * <li>Class cl=Class.forName("类全名");<br>
	 * 装载连接初始化类。</li>
	 * <li>Class cl=ClassLoader.loadClass("类全名");<br>
	 * 装载类，不连接不初始化。</li>
	 * </ol>
	 * 
	 * @param className
	 *            包名+类名 "org.jfree.chart.ChartFactory"
	 * @return the class
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @since 1.0.7
	 */
	public static Class<?> loadClass(String className) throws ClassNotFoundException{
		return Class.forName(className);// JVM查找并加载指定的类
	}

	/**
	 * 解析参数,获得参数类型,如果参数 paramValues 是null 返回 null.
	 * 
	 * @param paramValues
	 *            参数值
	 * @return 如果参数 paramValues 是null 返回 null
	 * @see org.apache.commons.lang3.ClassUtils#toClass(Object...)
	 * @since 1.0.7
	 */
	public static Class<?>[] toParameterTypes(Object...paramValues){
		if (Validator.isNullOrEmpty(paramValues)){
			log.debug("params is empty,use default paramsClass");
			return null;
		}
		int len = paramValues.length;

		Class<?>[] parameterTypes = new Class[len];
		for (int i = 0; i < len; ++i){
			Object param = paramValues[i];
			if (ObjectUtil.isBoolean(param)){// 是否是boolean类型
				parameterTypes[i] = boolean.class;
			}else if (ObjectUtil.isInteger(param)){// 是不是Integer类型
				parameterTypes[i] = int.class;
			}else{
				Class<?> clz = param.getClass();
				if (clz.getName().equals("org.jfree.data.category.DefaultCategoryDataset")){
					try{
						parameterTypes[i] = loadClass("org.jfree.data.category.CategoryDataset");
					}catch (ClassNotFoundException e){
						e.printStackTrace();
					}
				}else{
					// XXX 待整理
					parameterTypes[i] = clz;
				}
			}
		}
		return parameterTypes;
	}
}
