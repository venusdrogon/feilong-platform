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

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.text.MessageFormatUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;

/**
 * ResourceBundle 工具类
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-11-11 上午10:24:25
 */
public class ResourceBundleUtil{

	private final static Logger	log	= LoggerFactory.getLogger(ResourceBundleUtil.class);

	/****************************************************************************************/
	/**
	 * 获取Properties配置文件键值 ,采用java.util.ResourceBundle类的getBundle()方法来读取
	 * 
	 * @param <T>
	 * @param baseName
	 *            配置文件的包+类全名(不要尾缀)
	 * @param key
	 *            Properties配置文件键名
	 * @param defaultValue
	 *            默认值,如果key 对应的value 不存在
	 * @return 该键的值,如果key 对应的value 不存在返回defaultValue
	 */
	@SuppressWarnings("cast")
	public static <T> T getValue(String baseName,String key,T defaultValue){
		ResourceBundle resourceBundle = getResourceBundle(baseName);
		return (T) getValue(resourceBundle, key, defaultValue);
	}

	/**
	 * 获取Properties配置文件键值 ,采用java.util.ResourceBundle类的getBundle()方法来读取
	 * 
	 * @param resourceBundle
	 * @param <T>
	 * @param baseName
	 *            配置文件的包+类全名(不要尾缀)
	 * @param key
	 *            Properties配置文件键名
	 * @param defaultValue
	 *            默认值,如果key 对应的value 不存在
	 * @return 该键的值,如果key 对应的value 不存在返回defaultValue
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getValue(ResourceBundle resourceBundle,String key,T defaultValue){
		String propertyValue = getValue(resourceBundle, key);
		if (Validator.isNullOrEmpty(propertyValue)){
			log.debug("propertyValue is null,return the defaultValue");
			return defaultValue;
		}
		return (T) StringUtil.convertStringToT(propertyValue, defaultValue.getClass());
	}

	/**
	 * 获取Properties配置文件键值,按照typeClass 返回对应的类型
	 * 
	 * @param <T>
	 * @param baseName
	 * @param key
	 * @param typeClass
	 *            指明返回类型,<br>
	 *            如果是String.class,则返回的是String <br>
	 *            如果是Integer.class,则返回的是Integer
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getValue(String baseName,String key,Class<?> typeClass){
		String value = getValue(baseName, key);
		return (T) StringUtil.convertStringToT(value, typeClass);
	}

	/**
	 * 获取Properties配置文件键值,按照typeClass 返回对应的类型
	 * 
	 * @param <T>
	 * @param resourceBundle
	 * @param key
	 * @param typeClass
	 *            指明返回类型,<br>
	 *            如果是String.class,则返回的是String <br>
	 *            如果是Integer.class,则返回的是Integer
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getValue(ResourceBundle resourceBundle,String key,Class<?> typeClass){
		String value = getValue(resourceBundle, key);
		return (T) StringUtil.convertStringToT(value, typeClass);
	}

	/**
	 * 获取Properties配置文件键值 ,采用java.util.ResourceBundle类的getBundle()方法来读取
	 * 
	 * @param baseName
	 *            配置文件的包+类全名(不要尾缀)
	 * @param key
	 *            Properties配置文件键名
	 * @return 该键的值
	 * @since 1.0
	 */
	public static String getValue(String baseName,String key){
		ResourceBundle resourceBundle = getResourceBundle(baseName);
		return getValue(resourceBundle, key);
	}

	/**
	 * 带参数的 配置文件<br>
	 * 格式如:name={0}
	 * 
	 * @param baseName
	 * @param key
	 * @param arguments
	 *            此处可以传递Object[]数组过来
	 * @return
	 */
	public static String getValueWithArguments(String baseName,String key,Object...arguments){
		ResourceBundle resourceBundle = getResourceBundle(baseName);
		return getValueWithArguments(resourceBundle, key, arguments);
	}

	/**
	 * 获取Properties配置文件键值 ,采用java.util.ResourceBundle类的getBundle()方法来读取
	 * 
	 * @param resourceBundle
	 *            配置文件的包+类全名(不要尾缀)
	 * @param key
	 *            Properties配置文件键名
	 * @return 该键的值<br>
	 *         如果配置文件中,
	 *         <ul>
	 *         <li>key不存在,log.warn 输出警告,然后返回null</li>
	 *         <li>key存在,但value是null 或者 empty,log.warn 输出警告,然后返回value</li>
	 *         </ul>
	 */
	public static String getValue(ResourceBundle resourceBundle,String key){
		if (!resourceBundle.containsKey(key)){
			log.warn("resourceBundle don't containsKey:[{}]", key);
		}else{
			try{
				String value = resourceBundle.getString(key);
				if (Validator.isNullOrEmpty(value)){
					log.warn("resourceBundle has key:[{}],but value is null/empty", key);
				}
				return value;
			}catch (Exception e){
				log.error(e.getMessage());
			}
		}
		return null;
	}

	/**
	 * 带参数的 配置文件<br>
	 * 格式如:name={0}
	 * 
	 * @param resourceBundle
	 * @param key
	 *            如上面的 name
	 * @param arguments
	 *            此处可以传递Object[]数组过来
	 * @return 支持 arguments 为null,原样返回
	 */
	public static String getValueWithArguments(ResourceBundle resourceBundle,String key,Object...arguments){
		String value = getValue(resourceBundle, key);
		if (Validator.isNullOrEmpty(value)){
			return null;
		}
		// 支持 arguments 为null,原样返回
		return MessageFormatUtil.format(value, arguments);
	}

	/*************************************************************************************/
	/**
	 * 读取配置文件,将k/v 统统转成map
	 * 
	 * @param baseName
	 *            配置文件
	 * @return Map<String, String>,如果
	 */
	public static Map<String, String> readAllPropertiesToMap(String baseName){
		ResourceBundle resourceBundle = getResourceBundle(baseName);
		Enumeration<String> enumeration = resourceBundle.getKeys();
		if (Validator.isNotNullOrEmpty(enumeration)){
			Map<String, String> propertyMap = new HashMap<String, String>();
			while (enumeration.hasMoreElements()){
				String key = enumeration.nextElement();
				String value = resourceBundle.getString(key);
				propertyMap.put(key, value);
			}
			return propertyMap;
		}
		return null;
	}

	/********************************************************************************/
	/**
	 * 读取值,转成数组,<br>
	 * 默认调用 getArray(baseName, key, spliter, String.class) 形式
	 * 
	 * @param baseName
	 * @param key
	 * @param spliter
	 *            分隔符
	 * @return 以value.split(spliter),如果 资源值不存在,返回null
	 */
	public static String[] getArray(String baseName,String key,String spliter){
		return getArray(baseName, key, spliter, String.class);
	}

	/**
	 * 读取值,转成数组,<br>
	 * 默认调用 getArray(resourceBundle, key, spliter, String.class) 形式
	 * 
	 * @param resourceBundle
	 * @param key
	 * @param spliter
	 *            分隔符
	 * @return 以value.split(spliter),如果 资源值不存在,返回null
	 */
	public static String[] getArray(ResourceBundle resourceBundle,String key,String spliter){
		return getArray(resourceBundle, key, spliter, String.class);
	}

	/**
	 * 读取值,转成数组
	 * 
	 * @param baseName
	 * @param key
	 * @param spliter
	 *            分隔符
	 * @param typeClass
	 *            指明返回类型,<br>
	 *            如果是String.class,则返回的是String []数组<br>
	 *            如果是Integer.class,则返回的是Integer [] 数组
	 * @return 以value.split(spliter),如果 资源值不存在,返回null
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] getArray(String baseName,String key,String spliter,Class<?> typeClass){
		ResourceBundle resourceBundle = getResourceBundle(baseName);
		return (T[]) getArray(resourceBundle, key, spliter, typeClass);
	}

	/**
	 * 读取值,转成数组
	 * 
	 * @param resourceBundle
	 * @param key
	 * @param spliter
	 *            分隔符
	 * @param typeClass
	 *            指明返回类型,<br>
	 *            如果是String.class,则返回的是String []数组<br>
	 *            如果是Integer.class,则返回的是Integer [] 数组
	 * @return 以value.split(spliter),如果 资源值不存在,返回null
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] getArray(ResourceBundle resourceBundle,String key,String spliter,Class<?> typeClass){
		String value = getValue(resourceBundle, key);
		return (T[]) StringUtil.splitToT(value, spliter, typeClass);
	}

	/*********************************************************************************/
	/**
	 * @param baseName
	 * @param prefix
	 *            前缀
	 * @param spliter
	 * @return
	 */
	public static Map<String, String> readPrefixAsMap(String baseName,String prefix,String spliter){
		Map<String, String> propertyMap = readAllPropertiesToMap(baseName);
		if (Validator.isNotNullOrEmpty(propertyMap)){
			Map<String, String> result = new HashMap<String, String>();
			for (Map.Entry<String, String> entry : propertyMap.entrySet()){
				String key = entry.getKey();
				String value = entry.getValue();
				// 以 prefix 开头
				if (key.startsWith(prefix)){
					// 分隔
					String[] values = key.split(spliter);
					if (values.length >= 2){
						result.put(values[1], value);
					}
				}
			}
			return result;
		}
		return null;
	}

	/**
	 * 获得ResourceBundle
	 * 
	 * @param baseName
	 * @return
	 */
	public static ResourceBundle getResourceBundle(String baseName){
		// Locale enLoc = new Locale("en", "US"); // 表示美国地区
		ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName, Locale.getDefault());
		return resourceBundle;
	}
}
