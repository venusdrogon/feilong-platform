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
package com.feilong.commons.core.configure;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.lang.ClassLoaderUtil;

/**
 * 操作properties配置文件.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-3-22 上午10:05:19
 * @since 1.0.0
 */
public final class PropertiesUtil implements BaseConfigure{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(PropertiesUtil.class);

	/**
	 * 转换成map<br>
	 * Create a new HashMap and pass an instance of Properties.<br>
	 * Properties is an implementation of a Map which keys and values stored as in a string.
	 * 
	 * @param properties
	 *            the properties
	 * @return the map
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, String> toMap(Properties properties){
		Map<String, String> map = new HashMap<String, String>((Map) properties);
		return map;
	}

	// [start] getPropertiesValue

	/**
	 * 获取Properties配置文件键值.
	 * 
	 * @param clz
	 *            当前类
	 * @param propertiesPath
	 *            Properties文件路径 如"/WEB-INF/classes/feilong.user.properties"
	 * @param key
	 *            键
	 * @return 获取Properties配置文件键值
	 */
	public static String getPropertiesValue(Class<?> clz,String propertiesPath,String key){
		Properties properties = getProperties(clz, propertiesPath);
		return properties.getProperty(key);
	}

	/**
	 * 通过ClassLoader获得properties值.
	 * 
	 * @param clz
	 *            当前Class
	 * @param propertiesPath
	 *            Properties文件路径 如"/WEB-INF/classes/feilong.user.properties"
	 * @param key
	 *            用指定的键在此属性列表中搜索属性。如果在此属性列表中未找到该键，则接着递归检查默认属性列表及其默认值。如果未找到属性，则此方法返回 null。
	 * @return 通过ClassLoader获得properties值
	 */
	public static String getPropertiesValueWithClassLoader(Class<?> clz,String propertiesPath,String key){
		Properties properties = getPropertiesWithClassLoader(clz, propertiesPath);
		return properties.getProperty(key);
	}

	// [end]

	// [start] getProperties

	/**
	 * klass调用ClassLoader,通过ClassLoader 获取Properties.
	 * 
	 * @param klass
	 *            klass,通过 klass 获得 ClassLoader,然后获得 getResourceAsStream
	 * @param propertiesPath
	 *            如果 class 是 {@link PropertiesUtil},而配置文件在 src/main/resources下面,比如 messages/feilong-core-message_en_US.properties<br>
	 *            <ul>
	 *            <li>那么使用 {@link #getProperties(Class, String)} 需要这么写
	 *            {@code getProperties(PropertiesUtil.class,"/messages/feilong-core-message_en_US.properties")} <br>
	 *            注意此处的propertiesPath 要写成 "/messages/feilong-core-message_en_US.properties", 路径可以写成相对路径或者绝对路径</li>
	 *            <li>那么使用 {@link #getPropertiesWithClassLoader(Class, String)} 需要这么写
	 *            {@code getProperties(PropertiesUtil.class,"messages/feilong-core-message_en_US.properties")}<br>
	 *            注意此处的propertiesPath 要写成 "messages/feilong-core-message_en_US.properties",ClassLoader JVM会使用BootstrapLoader去加载资源文件。<br>
	 *            所以路径还是这种相对于工程的根目录即"messages/feilong-core-message_en_US.properties"(不需要“/”)</li>
	 *            </ul>
	 * @return Properties
	 */
	public static Properties getPropertiesWithClassLoader(Class<?> klass,String propertiesPath){
		ClassLoader classLoader = ClassLoaderUtil.getClassLoaderByClass(klass);
		InputStream inputStream = classLoader.getResourceAsStream(propertiesPath);
		return getProperties(inputStream);
	}

	/**
	 * 通过ClassLoader 获取Properties.getResourceAsStream 方法获得 InputStream<br>
	 * 
	 * @param klass
	 *            类,会通过 klass 调用
	 * @param propertiesPath
	 *            如果 class 是 {@link PropertiesUtil},而配置文件在 src/main/resources下面,比如 messages/feilong-core-message_en_US.properties<br>
	 *            <ul>
	 *            <li>那么使用 {@link #getProperties(Class, String)} 需要这么写
	 *            {@code getProperties(PropertiesUtil.class,"/messages/feilong-core-message_en_US.properties")} <br>
	 *            注意此处的propertiesPath 要写成 "/messages/feilong-core-message_en_US.properties", 路径可以写成相对路径或者绝对路径</li>
	 *            <li>那么使用 {@link #getPropertiesWithClassLoader(Class, String)} 需要这么写
	 *            {@code getProperties(PropertiesUtil.class,"messages/feilong-core-message_en_US.properties")}<br>
	 *            注意此处的propertiesPath 要写成 "messages/feilong-core-message_en_US.properties",ClassLoader JVM会使用BootstrapLoader去加载资源文件。<br>
	 *            所以路径还是这种相对于工程的根目录即"messages/feilong-core-message_en_US.properties"(不需要“/”)</li>
	 *            </ul>
	 * @return 获取Properties
	 */
	public static Properties getProperties(Class<?> klass,String propertiesPath){
		// klass.getResourceAsStream方法内部会调用classLoader.getResourceAsStream
		// 之所以这样做无疑还是方便客户端的调用，省的每次获取ClassLoader才能加载资源文件的麻烦。
		InputStream inputStream = klass.getResourceAsStream(propertiesPath);
		return getProperties(inputStream);
	}

	/**
	 * 获取Properties.
	 * 
	 * @param inputStream
	 *            inputStream
	 * @return <ul>
	 *         <li>如果null==inputStream,返回null</li>
	 *         <li>如果发生异常,返回null</li>
	 *         <li>正常情况,返回 properties.load(inputStream)</li>
	 *         </ul>
	 */
	public static Properties getProperties(InputStream inputStream){
		if (null != inputStream){
			try{
				Properties properties = new Properties();
				properties.load(inputStream);
				return properties;
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		log.warn("the inputStream is null,can't load properties!and will return null");
		return null;
	}

	// [end]

	// @formatter:off


//	public static boolean write(String fileName){
//		// 建立Properties对象
//		Properties properties = new Properties();
//		// 将信息方入Properties对象
//		properties.put("a.b.c", "金鑫");
//		properties.put("aaa", "ppp");
//		// 将信息包存在a.ini文件中
//		try{
//			properties.store(new FileOutputStream(fileName), null);
//		}catch (FileNotFoundException e){
//			e.printStackTrace();
//		}catch (IOException e){
//			e.printStackTrace();
//		}
//		return true;
//	}
//
//	public static void read(String fileName){
//		Properties properties = new Properties();
//		// 可以从a.ini中通过Properties.get方法读取配置信息
//		try{
//			properties.load(new FileInputStream(fileName));
//		}catch (FileNotFoundException e){
//			e.printStackTrace();
//		}catch (IOException e){
//			e.printStackTrace();
//		}
//		log.debug(properties.get("a.b.c"));
//		log.debug(properties.get("aaa"));
//	}
//
//	/**
//	 * 更新（或插入）一对properties信息(主键及其键值) 如果该主键已经存在，更新该主键的值； 如果该主键不存在，则插件一对键值。
//	 * 
//	 * @param keyname
//	 *            键名
//	 * @param keyvalue
//	 *            键值
//	 */
//	public static void writeProperties(String keyname,String keyvalue){
//		try{
//			// 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
//			// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
//			OutputStream fos = new FileOutputStream(profilepath);
//			props.setProperty(keyname, keyvalue);
//			// 以适合使用 load 方法加载到 Properties 表中的格式，
//			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
//			props.store(fos, "Update '" + keyname + "' value");
//		}catch (IOException e){
//			System.err.println("属性文件更新错误");
//		}
//	}
//
//	/**
//	 * 更新properties文件的键值对 如果该主键已经存在，更新该主键的值； 如果该主键不存在，则插件一对键值。
//	 * 
//	 * @param keyname
//	 *            键名
//	 * @param keyvalue
//	 *            键值
//	 */
//	public void updateProperties(String keyname,String keyvalue){
//		try{
//			props.load(new FileInputStream(profilepath));
//			// 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
//			// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
//			OutputStream fos = new FileOutputStream(profilepath);
//			props.setProperty(keyname, keyvalue);
//			// 以适合使用 load 方法加载到 Properties 表中的格式，
//			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
//			props.store(fos, "Update '" + keyname + "' value");
//		}catch (IOException e){
//			System.err.println("属性文件更新错误");
//		}
//	}

	// @formatter:on
}
