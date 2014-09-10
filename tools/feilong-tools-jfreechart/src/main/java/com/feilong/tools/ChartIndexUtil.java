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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(ChartIndexUtil.class);

	/**
	 * To chart index list.
	 *
	 * @param <K>
	 *            the key type
	 * @param <V>
	 *            the value type
	 * @param group
	 *            the group
	 * @param indexCodeAndNameMap
	 *            the index code and name map
	 * @return the list< chart index>
	 */
	public static <K, V extends Collection<?>> List<ChartIndex> toChartIndexList(Map<K, V> group,Map<K, String> indexCodeAndNameMap){
		List<ChartIndex> chartIndexList = new ArrayList<ChartIndex>();

		//是否需要转换code 和name之间的对应关系
		boolean needConvert = Validator.isNotNullOrEmpty(indexCodeAndNameMap);

		for (Map.Entry<K, V> entry : group.entrySet()){
			K key = entry.getKey();
			Collection<?> value = entry.getValue();

			ChartIndex chartIndex = new ChartIndex();
			chartIndex.setCode("" + key);

			if (needConvert){
				chartIndex.setName(indexCodeAndNameMap.get(key));
			}else{
				chartIndex.setName(chartIndex.getCode());
			}

			//			chartIndex.setColor("");
			chartIndex.setValue(value.size());
			chartIndexList.add(chartIndex);
		}
		return chartIndexList;
	}

	public static <K, V extends Collection<?>> List<ChartIndex> toChartIndexList(Map<K, V> group){
		return toChartIndexList(group, null);
	}
}
