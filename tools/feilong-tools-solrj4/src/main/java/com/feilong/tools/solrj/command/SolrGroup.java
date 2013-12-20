/**
 * Copyright (c) 2012 Baozun All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Baozun.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Baozun.
 *
 * BAOZUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. BAOZUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 */
package com.feilong.tools.solrj.command;

import java.io.Serializable;
import java.util.List;

import org.apache.solr.common.params.FacetParams;

/**
 * 和group by 相关的参数
 * 
 * @param <T>
 *            the generic type
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Nov 29, 2012 4:05:35 PM
 * @see {@link FacetParams}
 */
public class SolrGroup<T> implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 288232184048495608L;

	/** The group value. */
	private String				groupValue;

	/** 这个聚合值的 总数. */
	// = solrDocumentList.getNumFound();
	private Long				numFound;

	/** 将聚合的 每个 document 转成 bean. */
	private List<T>				beans				= null;

	/**
	 * Gets the group value.
	 * 
	 * @return the groupValue
	 */
	public String getGroupValue(){
		return groupValue;
	}

	/**
	 * Sets the group value.
	 * 
	 * @param groupValue
	 *            the groupValue to set
	 */
	public void setGroupValue(String groupValue){
		this.groupValue = groupValue;
	}

	/**
	 * Gets the 这个聚合值的 总数.
	 * 
	 * @return the numFound
	 */
	public Long getNumFound(){
		return numFound;
	}

	/**
	 * Sets the 这个聚合值的 总数.
	 * 
	 * @param numFound
	 *            the numFound to set
	 */
	public void setNumFound(Long numFound){
		this.numFound = numFound;
	}

	/**
	 * Gets the 将聚合的 每个 document 转成 bean.
	 * 
	 * @return the beans
	 */
	public List<T> getBeans(){
		return beans;
	}

	/**
	 * Sets the 将聚合的 每个 document 转成 bean.
	 * 
	 * @param beans
	 *            the beans to set
	 */
	public void setBeans(List<T> beans){
		this.beans = beans;
	}

}
