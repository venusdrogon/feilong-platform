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

package com.feilong.tools.solrj.paramscommand;

import java.io.Serializable;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.params.FacetParams;

/**
 * 和facet 相关的参数,注释掉的部分 表示暂没有封装.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Nov 29, 2012 4:05:35 PM
 * @see {@link FacetParams}
 */
public class FacetParamsCommand implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 288232184048495608L;

	/** Should facet counts be calculated?. */
	private boolean				facet				= true;

	/** 自定义的增加的属性 表示用于facet的字段数组. */
	private String[]			facetFields;

	/** 自定义的增加的属性,表示用于facet query的数组,最代表性的是 ua 的价格. */
	private String[]			facetQuerys;

	/**
	 * Numeric option indicating the maximum number of facet field counts be included in the response for each field - in descending order
	 * of count.<br>
	 * Can be overridden on a per field basis. <br>
	 * 默认 25 @see {@link SolrQuery#getFacetLimit()}
	 */
	private Integer				limit				= 25;

	/**
	 * Numeric option indicating the minimum number of hits before a facet should be included in the response.<br>
	 * Can be overridden on a per field basis. <br>
	 * 默认 1 @see {@link SolrQuery#getFacetMinCount()}
	 */
	private Integer				mincount			= 1;

	/**
	 * String option:
	 * <ul>
	 * <li>"count" causes facets to be sorted by the count, @see {@link FacetParams#FACET_SORT_COUNT}</li>
	 * <li>"index" results in index order @see {@link FacetParams#FACET_SORT_INDEX}.</li>
	 * </ul>
	 * <br>
	 * 默认 为count 排序
	 */
	private String				sort				= FacetParams.FACET_SORT_COUNT;

	// /** What method should be used to do the faceting */
	// private String method;
	//
	// /**
	// * Value for FACET_METHOD param to indicate that Solr should enumerate over terms in a field to calculate the facet counts.
	// */
	// private String FACET_METHOD_enum = "enum";
	//
	// /**
	// * Value for FACET_METHOD param to indicate that Solr should enumerate over documents and count up terms by consulting an uninverted
	// representation of the
	// * field values (such as the FieldCache used for sorting).
	// */
	// private String FACET_METHOD_fc = "fc";

	// /**
	// * Any lucene formated queries the user would like to use for Facet Constraint Counts (multi-value)
	// */
	// private String query;

	// /**
	// * Any field whose terms the user wants to enumerate over for Facet Constraint Counts (multi-value)
	// */
	// private String field;

	// /**
	// * The offset into the list of facets.<br>
	// * Can be overridden on a per field basis.
	// */
	// private String offset;

	// private String FACET_SORT_COUNT = "count";
	//
	// private String FACET_SORT_COUNT_LEGACY = "true";
	//
	// private String FACET_SORT_INDEX = "index";
	//
	// private String FACET_SORT_INDEX_LEGACY = "false";

	// /**
	// * Boolean option indicating whether facet field counts of "0" should be included in the response.<br>
	// * Can be overridden on a per field basis.
	// */
	// private String zeros;

	// /**
	// * Boolean option indicating whether the response should include a facet field count for all records which have no value for the facet
	// field. <br>
	// * Can be overridden on a per field basis.
	// */
	// private String missing;

	// /**
	// * Only return constraints of a facet field with the given prefix.
	// */
	// private String FACET_PREFIX = FACET + ".prefix";
	//
	// /**
	// * When faceting by enumerating the terms in a field, only use the filterCache for terms with a df >= to this parameter.
	// */
	// private String FACET_ENUM_CACHE_MINDF = FACET + ".enum.cache.minDf";
	//
	// /**
	// * Any field whose terms the user wants to enumerate over for Facet Contraint Counts (multi-value)
	// */
	// private String FACET_DATE = FACET + ".date";
	//
	// /**
	// * Date string indicating the starting point for a date facet range. Can be overriden on a per field basis.
	// */
	// private String FACET_DATE_START = FACET_DATE + ".start";
	//
	// /**
	// * Date string indicating the endinging point for a date facet range. Can be overriden on a per field basis.
	// */
	// private String FACET_DATE_END = FACET_DATE + ".end";
	//
	// /**
	// * Date Math string indicating the interval of sub-ranges for a date facet range. Can be overriden on a per field basis.
	// */
	// private String FACET_DATE_GAP = FACET_DATE + ".gap";
	//
	// /**
	// * Boolean indicating how counts should be computed if the range between 'start' and 'end' is not evenly divisible by 'gap'. If this
	// value is true, then
	// all
	// * counts of ranges involving the 'end' point will use the exact endpoint specified -- this includes the 'between' and 'after' counts
	// as well as the last
	// * range computed using the 'gap'. If the value is false, then 'gap' is used to compute the effective endpoint closest to the 'end'
	// param which results in
	// * the range between 'start' and 'end' being evenly divisible by 'gap'. The default is false. Can be overriden on a per field basis.
	// */
	// private String FACET_DATE_HARD_END = FACET_DATE + ".hardend";
	//
	// /**
	// * String indicating what "other" ranges should be computed for a date facet range (multi-value). Can be overriden on a per field
	// basis.
	// *
	// * @see FacetRangeOther
	// */
	// private String FACET_DATE_OTHER = FACET_DATE + ".other";
	//
	// /**
	// * <p>
	// * Multivalued string indicating what rules should be applied to determine when the the ranges generated for date faceting should be
	// inclusive or
	// exclusive
	// * of their end points.
	// * </p>
	// * <p>
	// * The default value if none are specified is: [lower,upper,edge] <i>(NOTE: This is different then FACET_RANGE_INCLUDE)</i>
	// * </p>
	// * <p>
	// * Can be overriden on a per field basis.
	// * </p>
	// *
	// * @see FacetRangeInclude
	// * @see #FACET_RANGE_INCLUDE
	// */
	// private String FACET_DATE_INCLUDE = FACET_DATE + ".include";

	// /**
	// * Any numerical field whose terms the user wants to enumerate over Facet Contraint Counts for selected ranges.
	// */
	// private String FACET_RANGE = FACET + ".range";
	//
	// /**
	// * Number indicating the starting point for a numerical range facet. Can be overriden on a per field basis.
	// */
	// private String FACET_RANGE_START = FACET_RANGE + ".start";
	//
	// /**
	// * Number indicating the ending point for a numerical range facet. Can be overriden on a per field basis.
	// */
	// private String FACET_RANGE_END = FACET_RANGE + ".end";
	//
	// /**
	// * Number indicating the interval of sub-ranges for a numerical facet range. Can be overriden on a per field basis.
	// */
	// private String FACET_RANGE_GAP = FACET_RANGE + ".gap";
	//
	// /**
	// * Boolean indicating how counts should be computed if the range between 'start' and 'end' is not evenly divisible by 'gap'. If this
	// value is true, then
	// all
	// * counts of ranges involving the 'end' point will use the exact endpoint specified -- this includes the 'between' and 'after' counts
	// as well as the last
	// * range computed using the 'gap'. If the value is false, then 'gap' is used to compute the effective endpoint closest to the 'end'
	// param which results in
	// * the range between 'start' and 'end' being evenly divisible by 'gap'. The default is false. Can be overriden on a per field basis.
	// */
	// private String FACET_RANGE_HARD_END = FACET_RANGE + ".hardend";
	//
	// /**
	// * String indicating what "other" ranges should be computed for a numerical range facet (multi-value). Can be overriden on a per field
	// basis.
	// */
	// private String FACET_RANGE_OTHER = FACET_RANGE + ".other";
	//
	// /**
	// * <p>
	// * Multivalued string indicating what rules should be applied to determine when the the ranges generated for numeric faceting should
	// be inclusive or
	// * exclusive of their end points.
	// * </p>
	// * <p>
	// * The default value if none are specified is: lower
	// * </p>
	// * <p>
	// * Can be overriden on a per field basis.
	// * </p>
	// *
	// * @see FacetRangeInclude
	// */
	// private String FACET_RANGE_INCLUDE = FACET_RANGE + ".include";

	// ********************************************************************************************

	/**
	 * Checks if is should facet counts be calculated?.
	 * 
	 * @return the facet
	 */
	public boolean isFacet(){
		return facet;
	}

	/**
	 * Sets the should facet counts be calculated?.
	 * 
	 * @param facet
	 *            the facet to set
	 */
	public void setFacet(boolean facet){
		this.facet = facet;
	}

	/**
	 * Gets the 自定义的增加的属性 表示用于facet的字段数组.
	 * 
	 * @return the facetFields
	 */
	public String[] getFacetFields(){
		return facetFields;
	}

	/**
	 * Sets the 自定义的增加的属性 表示用于facet的字段数组.
	 * 
	 * @param facetFields
	 *            the facetFields to set
	 */
	public void setFacetFields(String[] facetFields){
		this.facetFields = facetFields;
	}

	/**
	 * Gets the 自定义的增加的属性,表示用于facet query的数组,最代表性的是 ua 的价格.
	 * 
	 * @return the facetQuerys
	 */
	public String[] getFacetQuerys(){
		return facetQuerys;
	}

	/**
	 * Sets the 自定义的增加的属性,表示用于facet query的数组,最代表性的是 ua 的价格.
	 * 
	 * @param facetQuerys
	 *            the facetQuerys to set
	 */
	public void setFacetQuerys(String[] facetQuerys){
		this.facetQuerys = facetQuerys;
	}

	/**
	 * Gets the numeric option indicating the maximum number of facet field counts be included in the response for each field - in
	 * descending order of count.
	 * 
	 * @return the limit
	 */
	public Integer getLimit(){
		return limit;
	}

	/**
	 * Sets the numeric option indicating the maximum number of facet field counts be included in the response for each field - in
	 * descending order of count.
	 * 
	 * @param limit
	 *            the limit to set
	 */
	public void setLimit(Integer limit){
		this.limit = limit;
	}

	/**
	 * Gets the numeric option indicating the minimum number of hits before a facet should be included in the response.
	 * 
	 * @return the mincount
	 */
	public Integer getMincount(){
		return mincount;
	}

	/**
	 * Sets the numeric option indicating the minimum number of hits before a facet should be included in the response.
	 * 
	 * @param mincount
	 *            the mincount to set
	 */
	public void setMincount(Integer mincount){
		this.mincount = mincount;
	}

	/**
	 * Gets the string option:
	 * <ul>
	 * <li>"count" causes facets to be sorted by the count, @see {@link FacetParams#FACET_SORT_COUNT}</li>
	 * <li>"index" results in index order @see {@link FacetParams#FACET_SORT_INDEX}.
	 * 
	 * @return the sort
	 */
	public String getSort(){
		return sort;
	}

	/**
	 * Sets the string option:
	 * <ul>
	 * <li>"count" causes facets to be sorted by the count, @see {@link FacetParams#FACET_SORT_COUNT}</li>
	 * <li>"index" results in index order @see {@link FacetParams#FACET_SORT_INDEX}.
	 * 
	 * @param sort
	 *            the sort to set
	 */
	public void setSort(String sort){
		this.sort = sort;
	}

}
