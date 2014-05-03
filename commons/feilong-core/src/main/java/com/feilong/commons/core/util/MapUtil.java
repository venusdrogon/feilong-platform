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
package com.feilong.commons.core.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class MapUtil.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 8, 2012 8:02:44 PM
 */
public class MapUtil{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(MapUtil.class);

	/**
	 * 指定一个map,指定特定的keys,取得其中的 value 最小值.
	 * 
	 * @param <K>
	 *            the key type
	 * @param <T>
	 *            the generic type
	 * @param map
	 *            指定一个map
	 * @param keys
	 *            指定特定的key
	 * @return 如果 keys 中的所有的key 都不在 map 中出现 ,那么返回null
	 */
	@SuppressWarnings("unchecked")
	public static <K, T extends Number> T getMinValue(Map<K, T> map,K[] keys){
		Map<K, T> subMap = getSubMap(map, keys);

		if (null != subMap){
			Collection<T> values = subMap.values();
			Object[] array = values.toArray();
			// 冒泡排序
			Algorithm.bubbleSort(array, false);
			return (T) array[0];
		}
		return null;
	}

	/**
	 * 获得 一个map 中的 按照指定的key 整理成新的map.
	 * 
	 * @param <K>
	 *            the key type
	 * @param <T>
	 *            the generic type
	 * @param map
	 *            the map
	 * @param keys
	 *            指定key,如果key 不在map key 里面 ,则返回的map 中忽略该key
	 * @return the sub map<br>
	 *         如果 Validator.isNull(keys) 返回null<br>
	 *         如果 Validator.isNull(map) 返回null
	 */
	public static <K, T> Map<K, T> getSubMap(Map<K, T> map,K[] keys){
		if (Validator.isNullOrEmpty(keys)){
			log.debug("the param keys is null/empty");
			return null;
		}
		if (Validator.isNullOrEmpty(map)){
			log.debug("the param map is null/empty");
			return null;
		}
		Map<K, T> returnMap = new HashMap<K, T>();

		for (K key : keys){
			if (map.containsKey(key)){
				returnMap.put(key, map.get(key));
			}else{
				log.debug("map don't contains key:{}", key);
			}
		}
		return returnMap;
	}
}
