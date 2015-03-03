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
package com.feilong.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.feilong.commons.core.util.Validator;
import com.feilong.tools.chart.index.ChartIndex;

/**
 * The Class ChartIndexUtil.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年8月28日 下午11:17:45
 * @since 1.0.8
 */
public final class ChartIndexUtil{

	/**
	 * 将map数据装成 {@code List<ChartIndex>},此处将调用 {@link #toChartIndexList(Map, Map)}方法,.
	 *
	 * @param <K>
	 *            key将被设置到 ChartIndex 的code 属性
	 * @param <V>
	 *            value 集合的size大小将被设置到 ChartIndex的value属性
	 * @param group
	 *            将map数据装成 {@code List<ChartIndex>}
	 * @return the list< chart index>
	 * @see #toChartIndexList(Map, Map)
	 */
	public static <K, V extends Collection<?>> List<ChartIndex> toChartIndexList(Map<K, V> group){
		return toChartIndexList(group, null);
	}

	/**
	 * 将map数据装成 {@code List<ChartIndex>}.
	 *
	 * @param <K>
	 *            key将被设置到 ChartIndex 的code 属性
	 * @param <V>
	 *            value 集合的size大小将被设置到 ChartIndex的value属性
	 * @param group
	 *            将map数据装成 {@code List<ChartIndex>}
	 * @param indexCodeAndNameMap
	 *            用于转换code 对应的name,将被设置到 ChartIndex的name属性,如果没有传递转换map,name属性将和code 属性值相同
	 * @return the list< chart index>
	 */
	public static <K, V extends Collection<?>> List<ChartIndex> toChartIndexList(Map<K, V> group,Map<K, String> indexCodeAndNameMap){
		List<ChartIndex> chartIndexList = new ArrayList<ChartIndex>();

		for (Map.Entry<K, V> entry : group.entrySet()){
			Collection<?> collection = entry.getValue();
			final int value = collection.size();

			K key = entry.getKey();
			ChartIndex chartIndex = constructChartIndex(key, value, indexCodeAndNameMap);
			chartIndexList.add(chartIndex);
		}
		return chartIndexList;
	}

	/**
	 * 将map数据装成 {@code List<ChartIndex>}.
	 *
	 * @param <K>
	 *            key将被设置到 ChartIndex 的code 属性
	 * @param <V>
	 *            value 是简单 Number类型,将被设置到 ChartIndex的value属性
	 * @param group
	 *            将map数据装成 {@code List<ChartIndex>}
	 * @param indexCodeAndNameMap
	 *            用于转换code 对应的name,将被设置到 ChartIndex的name属性,如果没有传递转换map,name属性将和code 属性值相同
	 * @return the list< chart index>
	 */
	public static <K, V extends Number> List<ChartIndex> toChartIndexListByValue(Map<K, V> group,Map<K, String> indexCodeAndNameMap){
		List<ChartIndex> chartIndexList = new ArrayList<ChartIndex>();

		for (Map.Entry<K, V> entry : group.entrySet()){
			Number value = entry.getValue();

			K key = entry.getKey();
			ChartIndex chartIndex = constructChartIndex(key, value, indexCodeAndNameMap);
			chartIndexList.add(chartIndex);
		}
		return chartIndexList;
	}

	/**
	 * 构造ChartIndex.
	 *
	 * @param <K>
	 *            the key type
	 * @param code
	 *            the code
	 * @param value
	 *            the value
	 * @param indexCodeAndNameMap
	 *            the index code and name map
	 * @return the chart index
	 */
	private static <K> ChartIndex constructChartIndex(K code,Number value,Map<K, String> indexCodeAndNameMap){
		//是否需要转换code 和name之间的对应关系
		boolean needConvert = Validator.isNotNullOrEmpty(indexCodeAndNameMap);

		ChartIndex chartIndex = new ChartIndex();
		chartIndex.setCode("" + code);

		if (needConvert){
			chartIndex.setName(indexCodeAndNameMap.get(code));
		}else{
			chartIndex.setName(chartIndex.getCode());
		}

		//			chartIndex.setColor("");
		chartIndex.setValue(value);
		return chartIndex;
	}
}
