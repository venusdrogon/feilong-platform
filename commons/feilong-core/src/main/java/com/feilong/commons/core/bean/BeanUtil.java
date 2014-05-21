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
package com.feilong.commons.core.bean;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.Validator;

/**
 * 封装了org.apache.commons.beanutils包下面的类
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 2010-7-9 下午02:44:36
 * @version 2012-5-15 15:07
 * @version 1.0.7 2014年5月21日 下午12:24:53 move to om.feilong.commons.core.bean package
 * @since 1.0.0
 */
public final class BeanUtil{

	/** The Constant log. */
	private final static Logger	log	= LoggerFactory.getLogger(BeanUtil.class);

	/** Don't let anyone instantiate this class. */
	private BeanUtil(){}

	/**
	 * 调用org.apache.commons.beanutils.cloneBean
	 * 
	 * @param bean
	 *            the bean
	 * @return the object
	 * @since 1.0
	 */
	public static Object cloneBean(Object bean){
		try{
			Object cloneBean = BeanUtils.cloneBean(bean);
			return cloneBean;
		}catch (IllegalAccessException e){
			log.debug(e.getMessage());
		}catch (InstantiationException e){
			log.debug(e.getMessage());
		}catch (InvocationTargetException e){
			log.debug(e.getMessage());
		}catch (NoSuchMethodException e){
			log.debug(e.getMessage());
		}
		return null;
	}

	/**
	 * 把Bean的属性值放入到一个Map里面。<br>
	 * 这个方法返回一个Object中所有的可读属性，并将属性名/属性值放入一个Map中，<br>
	 * 另外还有一个名为class的属性，属性值是Object的类名，事实上class是java.lang.Object的一个属性
	 * 
	 * @param bean
	 *            the bean
	 * @return the map
	 */
	public static Map<String, String> describe(Object bean){
		try{
			@SuppressWarnings("unchecked")
			Map<String, String> map = BeanUtils.describe(bean);
			return map;
		}catch (IllegalAccessException e){
			e.printStackTrace();
		}catch (InvocationTargetException e){
			e.printStackTrace();
		}catch (NoSuchMethodException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 把properties里面的值放入bean中.
	 * 
	 * @param bean
	 *            the bean
	 * @param properties
	 *            the properties
	 */
	public static void populate(Object bean,Map<String, ?> properties){
		try{
			BeanUtils.populate(bean, properties);
		}catch (IllegalAccessException e){
			e.printStackTrace();
		}catch (InvocationTargetException e){
			e.printStackTrace();
		}
	}

	// ****************************copy**************************************************************************
	/**
	 * 对象Properties的复制<br>
	 * 注意:这种copy都是浅拷贝，复制后的2个Bean的同一个属性可能拥有同一个对象的ref，<br>
	 * 这个在使用时要小心，特别是对于属性为自定义类的情况 .
	 * 
	 * @param toObj
	 *            目标对象
	 * @param fromObj
	 *            原始对象
	 */
	public static void copyProperties(Object toObj,Object fromObj){
		try{
			BeanUtils.copyProperties(toObj, fromObj);
		}catch (IllegalAccessException e){
			e.printStackTrace();
			log.error(e.getMessage());
		}catch (InvocationTargetException e){
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

	/**
	 * 对象值的复制 fromObj-->toObj
	 * 
	 * <pre>
	 * 如果有java.util.Date 类型的 需要copy,那么 需要先这么着
	 * DateConverter converter = new DateConverter(DatePattern.forToString, Locale.US);
	 * ConvertUtils.register(converter, Date.class);
	 * 或者 使用 内置的 	
	 * ConvertUtils.register(new DateLocaleConverter(Locale.US, DatePattern.forToString), Date.class);	 * 
	 * 
	 * BeanUtil.copyProperty(b, a, &quot;date&quot;);
	 * </pre>
	 * 
	 * <pre>
	 * 例如两个pojo:enterpriseSales和enterpriseSales_form  都含有字段&quot;enterpriseName&quot;,&quot;linkMan&quot;,&quot;phone&quot;
	 * 通常写法
	 * enterpriseSales.setEnterpriseName(enterpriseSales_form.getEnterpriseName());
	 * enterpriseSales.setLinkMan(enterpriseSales_form.getLinkMan());
	 * enterpriseSales.setPhone(enterpriseSales_form.getPhone());
	 * 此时,可以使用  
	 * BeanUtil.copyProperties(enterpriseSales,enterpriseSales_form,new String[]{&quot;enterpriseName&quot;,&quot;linkMan&quot;,&quot;phone&quot;});
	 * </pre>
	 * 
	 * *
	 * 
	 * @param toObj
	 *            目标对象
	 * @param fromObj
	 *            原始对象
	 * @param filedNames
	 *            字段数组, can't be null/empty!
	 */
	public static void copyProperties(Object toObj,Object fromObj,String[] filedNames){
		if (Validator.isNotNullOrEmpty(filedNames)){
			int length = filedNames.length;
			for (int i = 0; i < length; ++i){
				String filedName = filedNames[i];
				copyProperty(toObj, fromObj, filedName);
			}
		}else{
			throw new IllegalArgumentException("filedNames can't be null/empty!");
		}
	}

	/**
	 * 对象值的复制 fromObj-->toObj
	 * 
	 * <pre>
	 * 如果有java.util.Date 类型的 需要copy,那么 需要先这么着
	 * DateConverter converter = new DateConverter(DatePattern.forToString, Locale.US);
	 * ConvertUtils.register(converter, Date.class);
	 * 或者 使用 内置的 	
	 * ConvertUtils.register(new DateLocaleConverter(Locale.US, DatePattern.forToString), Date.class);	 * 
	 * 
	 * BeanUtil.copyProperty(b, a, &quot;date&quot;);
	 * </pre>
	 * 
	 * <pre>
	 * 例如两个pojo:enterpriseSales和enterpriseSales_form  都含有字段&quot;enterpriseName&quot; 
	 * 通常写法
	 * enterpriseSales.setEnterpriseName(enterpriseSales_form.getEnterpriseName());
	 * 
	 * 此时,可以使用
	 * BeanUtil.copyProperty(enterpriseSales,enterpriseSales_form,&quot;enterpriseName&quot;);
	 * </pre>
	 * 
	 * *
	 * 
	 * @param toObj
	 *            目标对象
	 * @param fromObj
	 *            原始对象
	 * @param filedName
	 *            字段名称
	 */
	public static void copyProperty(Object toObj,Object fromObj,String filedName){
		Object value = getProperty(fromObj, filedName);
		copyProperty(toObj, filedName, value);
	}

	/**
	 * bean中的成员变量name赋值为value<br>
	 * 
	 * <pre>
	 * 如果有java.util.Date 类型的 需要copy,那么 需要先这么着
	 * DateConverter converter = new DateConverter(DatePattern.forToString, Locale.US);
	 * ConvertUtils.register(converter, Date.class);
	 * BeanUtil.copyProperty(b, a, &quot;date&quot;);
	 * </pre>
	 * 
	 * <pre>
	 * 嵌套赋值: BeanUtils.copyProperty(a, &quot;sample.display&quot;, &quot;second one&quot;);
	 * 
	 * 功能和setProperty一样
	 * 
	 * 如果我们只是为bean的属性赋值的话,使用copyProperty()就可以了;
	 * 而setProperty()方法是实现BeanUtils.populate()(后面会说到)机制的基础,也就是说如果我们需要自定义实现populate()方法,那么我们可以override setProperty()方法.
	 * 所以,做为一般的日常使用,setProperty()方法是不推荐使用的.
	 * 
	 * </pre>
	 * 
	 * @param bean
	 *            bean
	 * @param name
	 *            成员变量name
	 * @param value
	 *            赋值为value
	 */
	public static void copyProperty(Object bean,String name,Object value){
		try{
			BeanUtils.copyProperty(bean, name, value);
		}catch (IllegalAccessException e){
			e.printStackTrace();
		}catch (InvocationTargetException e){
			e.printStackTrace();
		}
	}

	// ******************************************************************************************************
	/**
	 * 使用 BeanUtils PropertyUtils来设置属性值.
	 * 
	 * <pre>
	 * 
	 * BeanUtils.setProperty(pt1, &quot;x&quot;, &quot;9&quot;); // 这里的9是String类型
	 * PropertyUtils.setProperty(pt1, &quot;x&quot;, 9); // 这里的是int类型
	 * // 这两个类BeanUtils和PropertyUtils,前者能自动将int类型转化，后者不能
	 * </pre>
	 * 
	 * @param bean
	 *            bean
	 * @param name
	 *            name
	 * @param value
	 *            value
	 * @param isNeedConvertType
	 *            是否需要类型转换 <br>
	 *            <ul>
	 *            <li>true调用 BeanUtils.setProperty(bean, name, value)</li>
	 *            <li>false调用PropertyUtils.setProperty(bean, name, value)</li>
	 *            </ul>
	 * <br>
	 */
	public static void setProperty(Object bean,String name,Object value,boolean isNeedConvertType){
		try{
			if (isNeedConvertType){
				// BeanUtils支持把所有类型的属性都作为字符串处理
				// 在后台自动进行类型转换(字符串和真实类型的转换)
				BeanUtils.setProperty(bean, name, value);
			}else{
				// PropertyUtils的功能类似于BeanUtils,但在底层不会对传递的数据做转换处理
				PropertyUtils.setProperty(bean, name, value);
			}
		}catch (IllegalAccessException e){
			e.printStackTrace();
		}catch (InvocationTargetException e){
			e.printStackTrace();
		}catch (NoSuchMethodException e){
			e.printStackTrace();
		}
	}

	/**
	 * 使用BeanUtils类从对象中取得属性值.
	 * 
	 * @param bean
	 *            bean
	 * @param name
	 *            属性名称
	 * @return 使用BeanUtils类从对象中取得属性值<br>
	 *         如果方法内部出现异常,return null
	 * @see BeanUtils
	 */
	public static String getProperty(Object bean,String name){
		try{
			// Return the value of the specified property of the specified bean,
			// no matter which property reference format is used, as a String.
			String propertyValue = BeanUtils.getProperty(bean, name);
			return propertyValue;
		}catch (IllegalAccessException e){
			e.printStackTrace();
		}catch (InvocationTargetException e){
			e.printStackTrace();
		}catch (NoSuchMethodException e){
			e.printStackTrace();
		}
		return null;
	}
}