/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
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
 * 操作properties配置文件
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-3-22 上午10:05:19
 * @since 1.0
 */
public class PropertiesUtil extends BaseConfigure{

	private static final Logger	log	= LoggerFactory.getLogger(PropertiesUtil.class);

	/****************************************************************************************/
	/**
	 * 读取方式
	 * 
	 * @author 金鑫 2010-4-20 下午04:16:34
	 */
	public enum ReadType{
		/**
		 * ClassLoader.getSystemResourceAsStream(propertiesPath)
		 */
		byClassLoaderGetSystemResourceAsStream,
		/**
		 * clz.getClassLoader().getResourceAsStream(propertiesPath)
		 */
		byClassLoaderGetResourceAsStream
	}

	/****************************************************************************************/
	/**
	 * 获取Properties配置文件键值
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
	 * 转换成map
	 * 
	 * @param properties
	 * @return
	 */
	public static Map<String, String> toMap(Properties properties){
		// Create a new HashMap and pass an instance of Properties.
		// Properties is an implementation of a Map which keys and values stored as in a string.
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Map<String, String> map = new HashMap<String, String>((Map) properties);
		return map;
	}

	/**
	 * 获取Properties
	 * 
	 * @param clz
	 *            当前类
	 * @param propertiesPath
	 *            Properties文件路径 如"/WEB-INF/classes/feilong.user.properties"
	 * @return Properties
	 */
	public static Properties getPropertiesWithClassLoader(Class<?> clz,String propertiesPath){
		ClassLoader classLoader = ClassLoaderUtil.getClassLoaderByClass(clz);
		InputStream inputStream = classLoader.getResourceAsStream(propertiesPath);
		return getProperties(inputStream);
	}

	/****************************************************************************************/
	/**
	 * 获得Properties对象
	 * 
	 * @param readType
	 *            读取方式
	 * @param propertiesPath
	 *            Properties路径
	 * @return 获取Properties配置文件键值
	 */
	public static Properties getProperties(ReadType readType,String propertiesPath){
		InputStream inputStream = null;
		switch (readType) {
			case byClassLoaderGetSystemResourceAsStream:
				inputStream = ClassLoader.getSystemResourceAsStream(propertiesPath);
				break;
			default:
				break;
		}
		return getProperties(inputStream);
	}

	/**
	 * 通过ClassLoader获得properties值
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

	/**
	 * 获得飞龙配置文件 feilong.user.properties 值
	 * 
	 * @param clz
	 *            当前加载类
	 * @param key
	 *            用指定的键在此属性列表中搜索属性。如果在此属性列表中未找到该键，则接着递归检查默认属性列表及其默认值。如果未找到属性，则此方法返回 null。
	 * @return 获得飞龙配置文件 feilong.user.properties 值
	 */
	// public static String getPropertiesFeiLongValueWithClassLoader(Class clz,String key){
	// return getPropertiesValueWithClassLoader(clz, properties_feilong, key);
	// }
	/**
	 * 获得飞龙配置文件 feilong.user.properties 值
	 * 
	 * @param servletContext
	 *            servletContext
	 * @param key
	 *            用指定的键在此属性列表中搜索属性。如果在此属性列表中未找到该键，则接着递归检查默认属性列表及其默认值。如果未找到属性，则此方法返回 null。
	 * @return 获得飞龙配置文件 feilong.user.properties 值
	 */
	// public static String getPropertiesFeiLongValueWithServletContext(ServletContext servletContext,String key){
	// return getPropertiesValue(servletContext, properties_feilong, key);
	// }
	/**
	 * 获取Properties
	 * 
	 * @param clz
	 *            当前类
	 * @param propertiesPath
	 *            Properties文件路径 如"/WEB-INF/classes/feilong.user.properties"
	 * @return 获取Properties
	 */
	public static Properties getProperties(Class<?> clz,String propertiesPath){
		InputStream inputStream = clz.getResourceAsStream(propertiesPath);
		return getProperties(inputStream);
	}

	/**
	 * 获取Properties
	 * 
	 * @param inputStream
	 *            inputStream
	 * @return 获取Properties
	 */
	public static Properties getProperties(InputStream inputStream){
		Properties properties = null;
		if (null != inputStream){
			properties = new Properties();
			try{
				properties.load(inputStream);
			}catch (IOException e){
				e.printStackTrace();
			}
		}else{
			log.warn("the inputStream is null,can'nt load properties");
		}
		return properties;
	}
	// public static boolean write(String fileName){
	// // 建立Properties对象
	// Properties properties = new Properties();
	// // 将信息方入Properties对象
	// properties.put("a.b.c", "金鑫");
	// properties.put("aaa", "ppp");
	// // 将信息包存在a.ini文件中
	// try{
	// properties.store(new FileOutputStream(fileName), null);
	// }catch (FileNotFoundException e){
	// e.printStackTrace();
	// }catch (IOException e){
	// e.printStackTrace();
	// }
	// return true;
	// }
	//
	// public static void read(String fileName){
	// Properties properties = new Properties();
	// // 可以从a.ini中通过Properties.get方法读取配置信息
	// try{
	// properties.load(new FileInputStream(fileName));
	// }catch (FileNotFoundException e){
	// e.printStackTrace();
	// }catch (IOException e){
	// e.printStackTrace();
	// }
	// log.debug(properties.get("a.b.c"));
	// log.debug(properties.get("aaa"));
	// }
	// /**
	// * 更新（或插入）一对properties信息(主键及其键值) 如果该主键已经存在，更新该主键的值； 如果该主键不存在，则插件一对键值。
	// *
	// * @param keyname
	// * 键名
	// * @param keyvalue
	// * 键值
	// */
	// public static void writeProperties(String keyname,String keyvalue){
	// try{
	// // 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
	// // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
	// OutputStream fos = new FileOutputStream(profilepath);
	// props.setProperty(keyname, keyvalue);
	// // 以适合使用 load 方法加载到 Properties 表中的格式，
	// // 将此 Properties 表中的属性列表（键和元素对）写入输出流
	// props.store(fos, "Update '" + keyname + "' value");
	// }catch (IOException e){
	// System.err.println("属性文件更新错误");
	// }
	// }
	// /**
	// * 更新properties文件的键值对 如果该主键已经存在，更新该主键的值； 如果该主键不存在，则插件一对键值。
	// *
	// * @param keyname
	// * 键名
	// * @param keyvalue
	// * 键值
	// */
	// public void updateProperties(String keyname,String keyvalue){
	// try{
	// props.load(new FileInputStream(profilepath));
	// // 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
	// // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
	// OutputStream fos = new FileOutputStream(profilepath);
	// props.setProperty(keyname, keyvalue);
	// // 以适合使用 load 方法加载到 Properties 表中的格式，
	// // 将此 Properties 表中的属性列表（键和元素对）写入输出流
	// props.store(fos, "Update '" + keyname + "' value");
	// }catch (IOException e){
	// System.err.println("属性文件更新错误");
	// }
	// }
}
