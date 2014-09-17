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

package com.feilong.hibernate.search.filter;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.TermQuery;
import org.hibernate.search.annotations.Factory;
import org.hibernate.search.annotations.Key;
import org.hibernate.search.filter.CachingWrapperFilter;
import org.hibernate.search.filter.FilterKey;
import org.hibernate.search.filter.StandardFilterKey;

/**
 * The Class SkuStatusFilterFactory.
 */
public class SkuStatusFilterFactory{

	/** The status. */
	private Integer	status;

	/**
	 * 获得 status.
	 *
	 * @return the status
	 */
	public Integer getStatus(){
		return status;
	}

	/**
	 * 设置 status.
	 *
	 * @param status
	 *            the status
	 */
	public void setStatus(Integer status){
		this.status = status;
	}

	/**
	 * 获得 key.
	 *
	 * @return the key
	 */
	@Key
	public FilterKey getKey(){
		StandardFilterKey key = new StandardFilterKey();
		key.addParameter(status);
		return key;
	}

	/**
	 * 获得 filter.
	 *
	 * @return the filter
	 */
	@Factory
	public Filter getFilter(){
		Query query = new TermQuery(new Term("status", status.toString()));
		return new CachingWrapperFilter(new QueryWrapperFilter(query));
	}
}
