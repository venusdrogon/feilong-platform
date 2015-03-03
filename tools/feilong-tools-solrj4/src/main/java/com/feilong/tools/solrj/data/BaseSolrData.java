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

package com.feilong.tools.solrj.data;

import java.io.Serializable;
import java.util.Map;

/**
 * 基类solr 存储数据.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-13 下午2:32:15
 * @param <T>
 *            the generic type
 */
public abstract class BaseSolrData<T> implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long				serialVersionUID	= -6466942586535105967L;

	/**
	 * 数据结构:<br>
	 * 
	 * <pre>
	 * facetMap.put("c1_id",
	 * 		<"女装",18>
	 * 		<"男装",28>
	 * 		<"童装",8>
	 * );
	 * </pre>
	 */
	private Map<String, Map<String, Long>>	facetMap;

	/**
	 * 数据结构:<br>
	 * 此时没有过滤0的分组,为了良好的扩展性需要自行代码过滤
	 * 
	 * <pre>
	 *         "facetQueryMap": {
	 *             "price_1:[0.0 TO 200.0]": 13,
	 *             "price_1:[200.0 TO 400.0]": 100,
	 *             "price_1:[400.0 TO 600.0]": 0,
	 *             "price_1:[600.0 TO 900.0]": 31
	 *         }
	 * </pre>
	 */
	private Map<String, Integer>			facetQueryMap;

	/**
	 * Gets the 数据结构:<br>
	 * 
	 * <pre>
	 * facetMap.
	 * 
	 * @return the facetMap
	 */
	public Map<String, Map<String, Long>> getFacetMap(){
		return facetMap;
	}

	/**
	 * Sets the 数据结构:<br>
	 * 
	 * <pre>
	 * facetMap.
	 * 
	 * @param facetMap the facetMap to set
	 */
	public void setFacetMap(Map<String, Map<String, Long>> facetMap){
		this.facetMap = facetMap;
	}

	/**
	 * Gets the 数据结构:<br>
	 * 此时没有过滤0的分组,为了良好的扩展性需要自行代码过滤
	 * 
	 * <pre>
	 * "facetQueryMap": { "price_1:[0.
	 * 
	 * @return the facetQueryMap
	 */
	public Map<String, Integer> getFacetQueryMap(){
		return facetQueryMap;
	}

	/**
	 * Sets the 数据结构:<br>
	 * 此时没有过滤0的分组,为了良好的扩展性需要自行代码过滤
	 * 
	 * <pre>
	 * "facetQueryMap": { "price_1:[0.
	 * 
	 * @param facetQueryMap the facetQueryMap to set
	 */
	public void setFacetQueryMap(Map<String, Integer> facetQueryMap){
		this.facetQueryMap = facetQueryMap;
	}

}
