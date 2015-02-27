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

	/** Don't let anyone instantiate this class. */
	private PropertiesUtil(){
		//AssertionError不是必须的。但它可以避免不小心在类的内部调用构造器。保证该类在任何情况下都不会被实例化。
		//see 《Effective Java》 2nd
		throw new AssertionError("No " + getClass().getName() + " instances for you!");
	}

	/**
	 * 转换成map<br>
	 * Create a new HashMap and pass an instance of Properties.<br>
	 * Properties is an implementation of a Map which keys and values stored as in a string.
	 * 
	 * @param properties
	 *            the properties
	 * @return the map
	 * @see org.apache.commons.collections.MapUtils#toProperties(Map)
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
	 *            用指定的键在此属性列表中搜索属性.如果在此属性列表中未找到该键，则接着递归检查默认属性列表及其默认值.如果未找到属性，则此方法返回 null.
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
	 *            注意此处的propertiesPath 要写成 "messages/feilong-core-message_en_US.properties",ClassLoader JVM会使用BootstrapLoader去加载资源文件.<br>
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
	 *            注意此处的propertiesPath 要写成 "messages/feilong-core-message_en_US.properties",ClassLoader JVM会使用BootstrapLoader去加载资源文件.<br>
	 *            所以路径还是这种相对于工程的根目录即"messages/feilong-core-message_en_US.properties"(不需要“/”)</li>
	 *            </ul>
	 * @return 获取Properties
	 */
	public static Properties getProperties(Class<?> klass,String propertiesPath){
		// klass.getResourceAsStream方法内部会调用classLoader.getResourceAsStream
		// 之所以这样做无疑还是方便客户端的调用，省的每次获取ClassLoader才能加载资源文件的麻烦.
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
				log.error(e.getClass().getName(), e);
			}
		}
		log.warn("the inputStream is null,can't load properties!and will return null");
		return null;
	}

	// [end]
}