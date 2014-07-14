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

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.Validator;

/**
 * 反射工具类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0.0 2010-1-18 下午06:35:58
 * @version 1.0.1 Apr 11, 2014 10:45:26 PM
 * @since 1.0.0
 */
public final class ReflectUtil{

	/** The Constant log. */
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

	// [start] Field

	/**
	 * 获得这个对象 所有字段的值(不是属性).
	 * 
	 * @param obj
	 *            the obj
	 * @return the field value map
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	public static Map<String, Object> getFieldValueMap(Object obj) throws IllegalArgumentException,IllegalAccessException{

		// 获得一个对象所有的声明字段(包括私有的,继承的)
		Field[] fields = getDeclaredFields(obj);

		Map<String, Object> map = new TreeMap<String, Object>();
		if (Validator.isNotNullOrEmpty(fields)){
			for (Field field : fields){
				String fieldName = field.getName();
				int modifiers = field.getModifiers();
				// 私有并且静态 一般是log
				boolean isPrivateAndStatic = Modifier.isPrivate(modifiers) && Modifier.isStatic(modifiers);
				log.debug("fieldName:[{}],modifiers/isPrivateAndStatic:[{}/{}]", fieldName, modifiers, isPrivateAndStatic);

				if (!isPrivateAndStatic){
					field.setAccessible(true);
					map.put(fieldName, field.get(obj));
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
	 */
	private static Field[] getDeclaredFields(Object obj){
		Field[] fields = null;

		Class<?> clz = obj.getClass();
		Class<?> superclass = clz.getSuperclass();

		//  返回Class对象所代表的类或接口中所有成员变量(不限于public)
		fields = clz.getDeclaredFields();
		do{
			if (log.isDebugEnabled()){
				log.debug("superclass:{}", superclass.getName());
			}
			fields = ArrayUtils.addAll(fields, superclass.getDeclaredFields());
			superclass = superclass.getSuperclass();

		}while (null != superclass && superclass != Object.class);

		return fields;
	}

	/**
	 * 返回 Field 对象的一个数组，这些对象反映此 Class对象所表示的类或接口,声明的所有字段。.
	 * 
	 * <pre>
	 * 包括public,protected,默认,private字段，
	 * 但不包括继承的字段。
	 * 
	 * 返回数组中的元素没有排序，也没有任何特定的顺序。
	 * 如果该类或接口不声明任何字段，或者此 Class 对象表示一个基本类型、一个数组类或 void，则此方法返回一个长度为 0 的数组。
	 * </pre>
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
	 * @see Class#getFields()
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
	 * @throws SecurityException
	 *             the security exception
	 * @throws NoSuchFieldException
	 *             the no such field exception
	 */
	public static Field getDeclaredField(Class<?> clz,String name) throws SecurityException,NoSuchFieldException{
		Field field = clz.getDeclaredField(name);
		return field;
	}

	// [end]
	// *********************************************************************************

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
		Class<?> ownerClass = loadClass(className);
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

		Class<?>[] parameterTypes = toParameterTypes(paramValues);
		/**
		 * 它反映此 Class 对象所表示的类或接口的指定公共成员方法。<br>
		 * name 参数是一个 String，用于指定所需方法的简称。<br>
		 * parameterTypes 参数是按声明顺序标识该方法形参类型的 Class 对象的一个数组。如果 parameterTypes 为 null，则按空数组处理。
		 */
		Method method = ownerClass.getMethod(methodName, parameterTypes);
		return method;
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
	 * @throws SecurityException
	 *             the security exception
	 * @throws NoSuchFieldException
	 *             the no such field exception
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	public static void setProperty(Object owner,String fieldName,Object value) throws SecurityException,NoSuchFieldException,
			IllegalArgumentException,IllegalAccessException{
		Class<?> ownerClass = owner.getClass();
		Field field = ownerClass.getField(fieldName);
		field.set(ownerClass, value);
	}

	/**
	 * 得到某个对象的公共属性.
	 * 
	 * @param owner
	 *            the owner
	 * @param fieldName
	 *            the field name
	 * @return 该属性对象
	 * @throws SecurityException
	 *             the security exception
	 * @throws NoSuchFieldException
	 *             the no such field exception
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	public static Object getProperty(Object owner,String fieldName) throws SecurityException,NoSuchFieldException,IllegalArgumentException,
			IllegalAccessException{
		Class<?> ownerClass = owner.getClass();
		Field field = ownerClass.getField(fieldName);
		Object property = field.get(owner);
		return property;
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

	// [end]

	// [start] newInstance

	/**
	 * 新建实例<br>
	 * 示例:
	 * 
	 * <pre>
	 * {@code
	 * User user = ReflectUtil.newInstance("com.feilong.test.User") 将返回user 对象
	 * 
	 * 你还可以 
	 * User user1 = ReflectUtil.newInstance("com.feilong.test.User", 100L); 返回 id 是100的构造函数
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
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(String className,Object...parameterValues) throws ClassNotFoundException,NoSuchMethodException,
			InstantiationException,IllegalAccessException,InvocationTargetException{
		// 1. Class cl=对象引用o.getClass();返回引用o运行时真正所指的对象(因为:儿子对象的引用可能会赋给父对象的引用变量中)所属的类O的Class的对象。谈不上对类O做什么操作。
		// 2. Class cl=A.class; JVM将使用类A的类装载器,将类A装入内存(前提:类A还没有装入内存),不对类A做类的初始化工作.返回类A的Class的对象。
		// 3. Class cl=Class.forName("类全名");装载连接初始化类。
		// 4.Class cl=ClassLoader.loadClass("类全名");装载类，不连接不初始化。

		// 装载连接初始化类
		Class<?> klass = Class.forName(className);
		Object newInstance = newInstance(klass, parameterValues);
		return (T) newInstance;
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
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 */
	public static <T> T newInstance(Class<T> klass,Object...parameterValues) throws NoSuchMethodException,InstantiationException,
			IllegalAccessException,InvocationTargetException{
		if (null == klass){
			throw new IllegalArgumentException("klass can't be null");
		}

		Class<?>[] parameterTypes = toParameterTypes(parameterValues);
		Constructor<T> constructor = klass.getConstructor(parameterTypes);

		// 使用此 Constructor 对象表示的构造方法来创建该构造方法的声明类的新实例，并用指定的初始化参数初始化该实例。个别参数会自动解包，以匹配基本形参，必要时，基本参数和引用参数都要进行方法调用转换。
		return constructor.newInstance(parameterValues);
	}

	/**
	 * 是不是某个类的实例.
	 * 
	 * @param obj
	 *            实例
	 * @param klass
	 *            类
	 * @return 如果 obj 是此类的实例，则返回 true
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
	 */
	public static boolean isInterface(Class<?> ownerClass){
		// 返回此类或接口以整数编码的 Java 语言修饰符
		int flag = ownerClass.getModifiers();
		// 对类和成员访问修饰符进行解码
		return Modifier.isInterface(flag);
	}

	// [end]

	/**
	 * 返回一个类.
	 * 
	 * @param className
	 *            包名+类名 "org.jfree.chart.ChartFactory"
	 * @return the class
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 */
	private static Class<?> loadClass(String className) throws ClassNotFoundException{
		return Class.forName(className);// JVM查找并加载指定的类
	}

	/**
	 * 得到数组中的某个元素.
	 * 
	 * @param array
	 *            数组
	 * @param index
	 *            索引
	 * @return 返回指定数组对象中索引组件的值
	 * @see java.lang.reflect.Array#get(Object, int)
	 */
	public static Object getByArray(Object array,int index){
		return Array.get(array, index);
	}

	/**
	 * 解析参数,获得参数类型,如果参数 paramValues 是null 返回 null
	 * 
	 * @param paramValues
	 *            参数值
	 * @return 如果参数 paramValues 是null 返回 null
	 */
	private static Class<?>[] toParameterTypes(Object...paramValues){
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
