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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.lang.ClassUtil;
import com.feilong.commons.core.util.Validator;

/**
 * Utilities for working with Fields by reflection. <br>
 * Adapted and refactored from the dormant [reflect] Commons sandbox component.
 * 
 * The ability is provided to break the scoping restrictions coded by the programmer.<br>
 * This can allow fields to be changed that shouldn't be.
 * This facility should be used with care.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年7月15日 下午1:08:15
 * @see org.apache.commons.lang3.reflect.FieldUtils
 * @see "org.springframework.util.ReflectionUtils"
 * @since 1.0.7
 */
public final class FieldUtil{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(FieldUtil.class);

	/**
	 * Instantiates a new field util.
	 */
	private FieldUtil(){};

	// [start] Field

	/**
	 * 获得这个对象 所有字段的值(不是属性).
	 * 
	 * @param obj
	 *            the obj
	 * @return the field value map
	 * @throws ReflectException
	 *             the reflect exception
	 * @see #getDeclaredFields(Object)
	 * @see java.lang.reflect.Modifier#isPrivate(int)
	 * @see java.lang.reflect.Modifier#isStatic(int)
	 */
	public static Map<String, Object> getFieldValueMap(Object obj) throws ReflectException{

		// 获得一个对象所有的声明字段(包括私有的,继承的)
		Field[] fields = getDeclaredFields(obj);

		Map<String, Object> map = new TreeMap<String, Object>();
		if (Validator.isNotNullOrEmpty(fields)){
			for (Field field : fields){
				String fieldName = field.getName();
				int modifiers = field.getModifiers();
				// 私有并且静态 一般是log
				boolean isPrivateAndStatic = Modifier.isPrivate(modifiers) && Modifier.isStatic(modifiers);
				log.debug("field name:[{}],modifiers:[{}],isPrivateAndStatic:[{}]", fieldName, modifiers, isPrivateAndStatic);

				if (!isPrivateAndStatic){
					field.setAccessible(true);
					try{
						map.put(fieldName, field.get(obj));
					}catch (Exception e){
						log.error(e.getClass().getName(), e);
						throw new ReflectException(e);
					}
				}
			}
		}
		return map;
	}

	/**
	 * 获得一个对象所有的声明字段(包括私有的 private,继承 inherited 的).
	 * 
	 * @param obj
	 *            the obj
	 * @return the declared fields
	 * @see java.lang.Class#getDeclaredFields()
	 * @see java.lang.Class#getSuperclass()
	 * @see org.apache.commons.lang3.ArrayUtils#addAll(boolean[], boolean...)
	 */
	private static Field[] getDeclaredFields(Object obj){
		Field[] fields = null;

		Class<?> clz = obj.getClass();
		Class<?> superclass = clz.getSuperclass();

		//  返回Class对象所代表的类或接口中所有成员变量(不限于public)
		fields = clz.getDeclaredFields();
		do{
			if (log.isDebugEnabled()){
				log.debug("current class:[{}],super class:[{}]", clz.getName(), superclass.getName());
			}
			fields = ArrayUtils.addAll(fields, superclass.getDeclaredFields());
			superclass = superclass.getSuperclass();

		}while (null != superclass && superclass != Object.class);

		return fields;
	}

	/**
	 * 返回 Field 对象的一个数组，这些对象反映此 Class对象所表示的类或接口,声明的所有字段..
	 * 
	 * <pre>
	 * 包括public,protected,默认,private字段，
	 * 但不包括继承的字段.
	 * 
	 * 返回数组中的元素没有排序，也没有任何特定的顺序.
	 * 如果该类或接口不声明任何字段，或者此 Class 对象表示一个基本类型、一个数组类或 void，则此方法返回一个长度为 0 的数组.
	 * </pre>
	 * 
	 * @param clz
	 *            the clz
	 * @return 包括public,protected,默认,private字段，但不包括继承的字段.
	 * @see java.lang.Class#getDeclaredFields()
	 * @see #getFieldsNames(Field[])
	 */
	public static String[] getDeclaredFieldNames(Class<?> clz){
		Field[] declaredFields = clz.getDeclaredFields();
		return getFieldsNames(declaredFields);
	}

	/**
	 * 反映此 Class 对象所表示的类或接口的所有可访问公共字段(public属性)<br>
	 * 元素没有排序，也没有任何特定的顺序<br>
	 * 如果类或接口没有可访问的公共字段，或者表示一个数组类、一个基本类型或 void，则此方法返回长度为 0 的数组. <br>
	 * 特别地，如果该 Class 对象表示一个类，则此方法返回该类及其所有超类的公共字段.<br>
	 * 如果该 Class 对象表示一个接口，则此方法返回该接口及其所有超接口的公共字段. <br>
	 * .
	 * 
	 * @param clz
	 *            the clz
	 * @return the field names
	 * @see Class#getFields()
	 * @see #getFieldsNames(Field[])
	 */
	public static String[] getFieldNames(Class<?> clz){
		Field[] fields = clz.getFields();
		return getFieldsNames(fields);
	}

	/**
	 * 获得Field[] fields,每个field name 拼成数组.
	 * 
	 * @param fields
	 *            the fields
	 * @return 如果 fields isNullOrEmpty,返回 null;否则取field name,合为数组返回
	 * @see java.lang.reflect.Field#getName()
	 */
	private static String[] getFieldsNames(Field[] fields){
		if (Validator.isNullOrEmpty(fields)){
			return null;
		}
		String[] fieldNames = new String[fields.length];
		for (int j = 0; j < fields.length; ++j){
			fieldNames[j] = fields[j].getName();
		}
		return fieldNames;
	}

	/**
	 * 返回一个 Field 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明字段.
	 * 
	 * @param clz
	 *            clz
	 * @param name
	 *            属性名称
	 * @return 返回一个 Field 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明字段
	 * @throws ReflectException
	 *             the reflect exception
	 * @see java.lang.Class#getDeclaredField(String)
	 */
	public static Field getDeclaredField(Class<?> clz,String name) throws ReflectException{
		try{
			Field field = clz.getDeclaredField(name);
			return field;
		}catch (Exception e){
			log.error(e.getClass().getName(), e);
			throw new ReflectException(e);
		}
	}

	// [end]

	// [start] Property

	/**
	 * 设置属性.
	 * 
	 * @param owner
	 *            the owner
	 * @param fieldName
	 *            字段
	 * @param value
	 *            值
	 * @throws ReflectException
	 *             the reflect exception
	 * @see java.lang.Object#getClass()
	 * @see java.lang.Class#getField(String)
	 * @see java.lang.reflect.Field#set(Object, Object)
	 */
	public static void setProperty(Object owner,String fieldName,Object value) throws ReflectException{
		try{
			Class<?> ownerClass = owner.getClass();
			Field field = ownerClass.getField(fieldName);
			field.set(ownerClass, value);
		}catch (Exception e){
			log.error(e.getClass().getName(), e);
			throw new ReflectException(e);
		}
	}

	/**
	 * 得到某个对象的公共属性.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param owner
	 *            the owner
	 * @param fieldName
	 *            the field name
	 * @return 该属性对象
	 * @throws ReflectException
	 *             the reflect exception
	 * 
	 * @see java.lang.Object#getClass()
	 * @see java.lang.Class#getField(String)
	 * @see java.lang.reflect.Field#get(Object)
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getProperty(Object owner,String fieldName) throws ReflectException{
		try{
			Class<?> ownerClass = owner.getClass();
			Field field = ownerClass.getField(fieldName);
			Object property = field.get(owner);
			return (T) property;
		}catch (Exception e){
			log.error(e.getClass().getName(), e);
			throw new ReflectException(e);
		}
	}

	/**
	 * 得到某类的静态公共属性.
	 * 
	 * <pre>
	 * {@code
	 * example1 :
	 * 获得 IOConstants类的 GB静态属性
	 * FieldUtil.getStaticProperty("com.feilong.commons.core.io.IOConstants", "GB")
	 * 返回 :1073741824
	 * }
	 * </pre>
	 * 
	 * @param <T>
	 *            the generic type
	 * @param className
	 *            类名
	 * @param fieldName
	 *            属性名
	 * @return 该属性对象
	 * @throws ReflectException
	 *             the reflect exception
	 * @see com.feilong.commons.core.lang.ClassUtil#loadClass(String)
	 * @see java.lang.Class#getField(String)
	 * @see java.lang.reflect.Field#get(Object)
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getStaticProperty(String className,String fieldName) throws ReflectException{
		try{
			Class<?> ownerClass = ClassUtil.loadClass(className);
			Field field = ownerClass.getField(fieldName);
			Object property = field.get(ownerClass);
			return (T) property;
		}catch (Exception e){
			log.error(e.getClass().getName(), e);
			throw new ReflectException(e);
		}
	}

	// [end]
}