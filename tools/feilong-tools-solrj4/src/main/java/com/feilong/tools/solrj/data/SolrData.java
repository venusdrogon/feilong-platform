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

import java.util.Map;

import loxia.dao.Pagination;

/**
 * solr 存储数据.
 * 
 * @param <T>
 *            the generic type
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-13 下午2:32:15
 */
public class SolrData<T> extends BaseSolrData<T>{

	/** The Constant serialVersionUID. */
	private static final long			serialVersionUID	= -6466942586535105967L;

	/** 聚合前的总数. */
	private int							matches;

	/** The pagination. */
	private Pagination<T>				pagination;

	/**
	 * 如果使用了 group by 功能,才会封装,否则没用<br>
	 * 数据结构:<br>
	 * key 是 传入的 GroupParams.GROUP_FIELD 每个值<br>
	 * value 是这个字段 查询结果
	 */
	private Map<String, Pagination<T>>	groupMap;

	/**
	 * Gets the pagination.
	 * 
	 * @return the pagination
	 */
	public Pagination<T> getPagination(){
		return pagination;
	}

	/**
	 * Sets the pagination.
	 * 
	 * @param pagination
	 *            the pagination to set
	 */
	public void setPagination(Pagination<T> pagination){
		this.pagination = pagination;
	}

	/**
	 * Gets the 如果使用了 group by 功能,才会封装,否则没用<br>
	 * 数据结构:<br>
	 * key 是 传入的 GroupParams.
	 * 
	 * @return the groupMap
	 */
	public Map<String, Pagination<T>> getGroupMap(){
		return groupMap;
	}

	/**
	 * Sets the 如果使用了 group by 功能,才会封装,否则没用<br>
	 * 数据结构:<br>
	 * key 是 传入的 GroupParams.
	 * 
	 * @param groupMap
	 *            the groupMap to set
	 */
	public void setGroupMap(Map<String, Pagination<T>> groupMap){
		this.groupMap = groupMap;
	}

	/**
	 * Gets the 聚合前的总数.
	 * 
	 * @return the 聚合前的总数
	 */
	public int getMatches(){
		return matches;
	}

	/**
	 * Sets the 聚合前的总数.
	 * 
	 * @param matches
	 *            the new 聚合前的总数
	 */
	public void setMatches(int matches){
		this.matches = matches;
	}

}
