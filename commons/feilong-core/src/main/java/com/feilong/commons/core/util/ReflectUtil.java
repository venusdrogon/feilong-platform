/**
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
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 反射类.
 * 
 * @author 金鑫 2010-1-18 下午06:35:58
 * @since 1.0
 */
public final class ReflectUtil{

	/** The Constant log. */
	private final static Logger	log	= LoggerFactory.getLogger(ReflectUtil.class);

	/** Don't let anyone instantiate this class. */
	private ReflectUtil(){}

	/**
	 * Gets the generic model class.<br>
	 * public class SkuItemRepositoryImpl extends BaseSolrRepositoryImpl<SkuItem, Long> implements SkuItemRepository<br>
	 * 这样的类,取到泛型里面第一个参数 SkuItem.class
	 * 
	 * @param <T>
	 *            the generic type
	 * @param clazz
	 *            the clazz
	 * @return the generic model class
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getGenericModelClass(Class<?> clazz){
		Type type = clazz.getGenericSuperclass();
		while (!(type instanceof ParameterizedType) && clazz != null && clazz != Object.class){
			clazz = clazz.getSuperclass();
			type = clazz.getGenericSuperclass();
		}
		if (!(type instanceof ParameterizedType)){
			Class<?>[] iclazzs = clazz.getInterfaces();
			if (iclazzs.length > 0){
				int index = -1;
				if (index >= 0){
					if (clazz.getGenericInterfaces()[index] instanceof ParameterizedType)
						type = clazz.getGenericInterfaces()[index];
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
	 * 返回 Field 对象的一个数组，这些对象反映此 Class对象所表示的类或接口,声明的所有字段。
	 * 
	 * <pre>
	 * 包括public,protected,默认,private字段，
	 * 但不包括继承的字段。
	 * 
	 * 返回数组中的元素没有排序，也没有任何特定的顺序。
	 * 如果该类或接口不声明任何字段，或者此 Class 对象表示一个基本类型、一个数组类或 void，则此方法返回一个长度为 0 的数组。
	 * </pre>
	 * 
	 * .
	 * 
	 * @param clz
	 *            the clz
	 * @return 包括public,protected,默认,private字段，但不包括继承的字段。
	 */
	public static String[] getDeclaredFieldNames(Class<?> clz){
		Field[] declaredFields = clz.getDeclaredFields();
		return getFieldsNames(declaredFields);
	}

	/**
	 * 反映此 Class 对象所表示的类或接口的所有可访问公共字段(public属性)<br>
	 * 元素没有排序，也没有任何特定的顺序<br>
	 * 如果类或接口没有可访问的公共字段，或者表示一个数组类、一个基本类型或 void，则此方法返回长度为 0 的数组。 <br>
	 * 特别地，如果该 Class 对象表示一个类，则此方法返回该类及其所有超类的公共字段。<br>
	 * 如果该 Class 对象表示一个接口，则此方法返回该接口及其所有超接口的公共字段。 <br>
	 * .
	 * 
	 * @param clz
	 *            the clz
	 * @return the field names
	 * @see {@link Class#getFields()}
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
	 * @return the fields names
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
	 */
	public static Field getDeclaredField(Class<?> clz,String name){
		Field field = null;
		try{
			field = clz.getDeclaredField(name);
		}catch (SecurityException e){
			e.printStackTrace();
		}catch (NoSuchFieldException e){
			e.printStackTrace();
		}
		return field;
	}

	/**
	 * 执行某对象方法.
	 * 
	 * @param owner
	 *            对象
	 * @param methodName
	 *            方法名
	 * @param params
	 *            参数
	 * @return 方法返回值
	 */
	public static Object invokeMethod(Object owner,String methodName,Object...params){
		Class<?> ownerClass = owner.getClass();
		Method method = getMethod(ownerClass, methodName, params);
		if (null == method){
			return null;
		}
		try{
			return method.invoke(owner, params);
		}catch (IllegalArgumentException e){
			log.debug(e.getMessage());
			e.printStackTrace();
		}catch (IllegalAccessException e){
			log.debug(e.getMessage());
			e.printStackTrace();
		}catch (InvocationTargetException e){
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	// public static Object invokeMethod(Object owner,String methodName,LinkedList<Map<Object, Class>> linkedList){
	// Class ownerClass = owner.getClass();
	// //**********************************************************
	// int size = linkedList.size();
	// //参数数组
	// Object[] params = new Object[size];
	// //参数对应的class 数组
	// Class[] classes = new Class[size];
	// for (int i = 0; i < size; ++i){
	// Map<Object, Class> map=linkedList.get(i);
	// params[i];
	//
	// classes[i]=map.get(key);
	//
	// }
	// //**********************************************************
	// Method method = getMethod(ownerClass, methodName, params);
	// try{
	// if (Validator.isNotNull(linkedList)){}
	// return method.invoke(owner, params);
	// }catch (IllegalArgumentException e){
	// log.debug(e.getMessage());
	// e.printStackTrace();
	// }catch (IllegalAccessException e){
	// log.debug(e.getMessage());
	// e.printStackTrace();
	// }catch (InvocationTargetException e){
	// log.debug(e.getMessage());
	// e.printStackTrace();
	// }
	// return null;
	// }
	/**
	 * 获得方法.
	 * 
	 * @param ownerClass
	 *            类
	 * @param methodName
	 *            方法名
	 * @param params
	 *            动态参数
	 * @return 该方法
	 */
	private static Method getMethod(Class<?> ownerClass,String methodName,Object...params){
		log.debug("input param ownerClass is :" + ownerClass);
		log.debug("input param methodName is :" + methodName);
		log.debug("input param params is :" + params);
		Class<?>[] paramsClass = null;
		if (Validator.isNullOrEmpty(params)){
			log.debug("params is empty,use default paramsClass");
		}else{
			int len = params.length;
			paramsClass = new Class[len];
			Object param = null;
			Class<?> clz = null;
			for (int i = 0; i < len; ++i){
				param = params[i];
				// 是否是boolean类型
				if (ObjectUtil.isBoolean(param)){
					paramsClass[i] = boolean.class;
					// 是不是Integer类型
				}else if (ObjectUtil.isInteger(param)){
					paramsClass[i] = int.class;
				}else{
					clz = param.getClass();
					if (clz.getName().equals("org.jfree.data.category.DefaultCategoryDataset")){
						// paramsClass[i] = CategoryDataset.class;
						try{
							paramsClass[i] = Class.forName("org.jfree.data.category.CategoryDataset");
						}catch (ClassNotFoundException e){
							e.printStackTrace();
						}
					}else{
						paramsClass[i] = clz;
					}
				}
			}
		}
		try{
			/**
			 * 它反映此 Class 对象所表示的类或接口的指定公共成员方法。<br>
			 * name 参数是一个 String，用于指定所需方法的简称。<br>
			 * parameterTypes 参数是按声明顺序标识该方法形参类型的 Class 对象的一个数组。如果 parameterTypes 为 null，则按空数组处理。
			 */
			Method method = ownerClass.getMethod(methodName, paramsClass);
			return method;
		}catch (SecurityException e){
			log.error("存在安全侵犯");
			e.printStackTrace();
		}catch (NoSuchMethodException e){
			log.error("in this class,can not found this method (" + methodName + "),please check the name of the method");
			log.error(e.getMessage());
		}
		return null;
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
	 */
	public static Object invokeStaticMethod(String className,String methodName,Object...params){
		try{
			Class<?> ownerClass = loadClass(className);
			Method method = getMethod(ownerClass, methodName, params);
			if (null != method){
				// 如果底层方法是静态的，那么可以忽略指定的 obj 参数。该参数可以为 null。 从中调用底层方法的对象
				// 如果底层方法所需的形参数为 0，则所提供的 args 数组长度可以为 0 或 null 用于方法调用的参数
				return method.invoke(null, params);
			}
		}catch (SecurityException e){
			e.printStackTrace();
		}catch (IllegalArgumentException e){
			e.printStackTrace();
		}catch (IllegalAccessException e){
			e.printStackTrace();
		}catch (InvocationTargetException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回一个类.
	 * 
	 * @param className
	 *            包名+类名 "org.jfree.chart.ChartFactory"
	 * @return the class
	 */
	private static Class<?> loadClass(String className){
		try{
			return Class.forName(className);// JVM查找并加载指定的类
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 得到某个对象的公共属性.
	 * 
	 * @param owner
	 *            the owner
	 * @param fieldName
	 *            the field name
	 * @return 该属性对象
	 */
	public static Object getProperty(Object owner,String fieldName){
		Class<?> ownerClass = owner.getClass();
		Object property = null;
		try{
			Field field = ownerClass.getField(fieldName);
			property = field.get(owner);
		}catch (SecurityException e){
			e.printStackTrace();
		}catch (IllegalArgumentException e){
			e.printStackTrace();
		}catch (NoSuchFieldException e){
			e.printStackTrace();
		}catch (IllegalAccessException e){
			e.printStackTrace();
		}
		return property;
	}

	/**
	 * 设置属性.
	 * 
	 * @param owner
	 *            the owner
	 * @param fieldName
	 *            字段
	 * @param value
	 *            值
	 */
	public static void setProperty(Object owner,String fieldName,Object value){
		Class<?> ownerClass = owner.getClass();
		try{
			Field field = ownerClass.getField(fieldName);
			field.set(ownerClass, value);
		}catch (SecurityException e){
			e.printStackTrace();
		}catch (IllegalArgumentException e){
			e.printStackTrace();
		}catch (NoSuchFieldException e){
			e.printStackTrace();
		}catch (IllegalAccessException e){
			e.printStackTrace();
		}
	}

	/**
	 * 得到某类的静态公共属性.
	 * 
	 * @param className
	 *            类名
	 * @param fieldName
	 *            属性名
	 * @return 该属性对象
	 * @throws Exception
	 *             the exception
	 */
	public static Object getStaticProperty(String className,String fieldName) throws Exception{
		Class<?> ownerClass = Class.forName(className);
		Field field = ownerClass.getField(fieldName);
		Object property = field.get(ownerClass);
		return property;
	}

	/**
	 * 新建实例.
	 * 
	 * @param className
	 *            类名
	 * @param args
	 *            构造函数的参数
	 * @return 新建的实例
	 */
	public static Object newInstance(String className,Object...args){
		try{
			Class<?> newoneClass = Class.forName(className);
			Class<?>[] argsClass = new Class[args.length];
			for (int i = 0, j = args.length; i < j; ++i){
				argsClass[i] = args[i].getClass();
			}
			Constructor<?> constructor = newoneClass.getConstructor(argsClass);
			return constructor.newInstance(args);
		}catch (SecurityException e){
			e.printStackTrace();
		}catch (IllegalArgumentException e){
			e.printStackTrace();
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch (NoSuchMethodException e){
			e.printStackTrace();
		}catch (InstantiationException e){
			e.printStackTrace();
		}catch (IllegalAccessException e){
			e.printStackTrace();
		}catch (InvocationTargetException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 是不是某个类的实例.
	 * 
	 * @param obj
	 *            实例
	 * @param cls
	 *            类
	 * @return 如果 obj 是此类的实例，则返回 true
	 */
	public static boolean isInstance(Object obj,Class<?> cls){
		return cls.isInstance(obj);
	}

	/**
	 * 判断对象是否是接口.
	 * 
	 * @param ownerClass
	 *            对象class
	 * @return 是返回true
	 */
	public static boolean isInterface(Class<?> ownerClass){
		// 返回此类或接口以整数编码的 Java 语言修饰符
		int flag = ownerClass.getModifiers();
		// 对类和成员访问修饰符进行解码
		return Modifier.isInterface(flag);
	}

	/**
	 * 得到数组中的某个元素.
	 * 
	 * @param array
	 *            数组
	 * @param index
	 *            索引
	 * @return 返回指定数组对象中索引组件的值
	 */
	public static Object getByArray(Object array,int index){
		return Array.get(array, index);
	}
}
