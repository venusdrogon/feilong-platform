/**
 * Copyright (c) 2008-2012 FeiLong, Inc. All Rights Reserved.
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
package com.feilong.tools.solrj.data;

import java.io.Serializable;
import java.util.Map;

/**
 * 基类solr 存储数据.
 * 
 * @param <T>
 *            the generic type
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-13 下午2:32:15
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
