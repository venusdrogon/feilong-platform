 /**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
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

package com.feilong.tools.solrj;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import loxia.dao.Pagination;
import loxia.dao.Sort;

import org.apache.solr.client.solrj.SolrQuery;

import com.feilong.tools.solrj.data.SolrData;
import com.feilong.tools.solrj.data.SolrGroupData;
import com.feilong.tools.solrj.exception.SolrException;
import com.feilong.tools.solrj.paramscommand.FacetParamsCommand;
import com.feilong.tools.solrj.paramscommand.GroupParamsCommand;

/**
 * Solrj核心操作封装.<br>
 * 和 官方商城组4.0相比,有以下改动 <br>
 * <ul>
 * <li>1.所有操作执行的方法 将 SolrException 异常外抛, 业务来控制</li>
 * <li>2.更详细的 参数非空validator</li>
 * <li>3.参数顺序进行更友好调整</li>
 * <li>4.sort当作参数传递</li>
 * <li>5.增加适用场景</li>
 * <li>6.删除了以往不调用的方法</li>
 * </ul>
 * 
 * @param <T>
 *            the generic type
 * @param <PK>
 *            the generic type
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-11 下午5:01:26
 * @version 1.1 2013-12-19 23:39
 */
public interface BaseSolrRepository<T, PK extends Serializable> {

	// *****************************************保存/更新******************************************

	/**
	 * has save or update function.
	 * 
	 * @param model
	 *            the model
	 * @return the t
	 * @throws SolrException
	 *             the solr exception
	 */
	T save(T model) throws SolrException;

	/**
	 * 批量保存.
	 * 
	 * @param modelList
	 *            the model list
	 * @throws SolrException
	 *             the solr exception
	 */
	void batchSave(List<T> modelList) throws SolrException;

	// *****************************************删除***********************************
	/**
	 * Delete.
	 * 
	 * @param primaryKey
	 *            the primary key
	 * @throws SolrException
	 *             the solr exception
	 */
	void delete(PK primaryKey) throws SolrException;

	/**
	 * Delete by query.
	 * 
	 * @param query
	 *            the query
	 * @throws SolrException
	 *             the solr exception
	 */
	void deleteByQuery(String query) throws SolrException;

	/**
	 * 批量删除.
	 * 
	 * @param pkList
	 *            the pk list
	 * @throws SolrException
	 *             the solr exception
	 */
	void batchDelete(List<PK> pkList) throws SolrException;

	// ***************************************查询******************************************************************

	/**
	 * Find by query,这个方法适合于 (给你几个code ,查询下) 这种不带facet等参数的方法.
	 * 
	 * @param solrQuery
	 *            the solr query
	 * @param pageNumber
	 *            第几页 页面,为自然页,从1开始<br>
	 *            可以为null,系统自动为1<br>
	 *            如果<1,系统自动为1
	 * @param rows
	 *            每页读取多少数据
	 * @param sorts
	 *            排序,如果传入为null 不会增加排序字段
	 * @return the pagination
	 */
	Pagination<T> findByQuery(SolrQuery solrQuery,Integer pageNumber,int rows,Sort[] sorts);

	/**
	 * 这是一个 带facet功能的 solr query,<br>
	 * 可以实现 鞋(20) 带数字的这样的功能<br>
	 * 如果你的网站不需要显示 类似于 鞋(20) 带数字count 功能的 不需要直接调用这个方法.
	 * 
	 * @param solrQuery
	 *            the solr query
	 * @param pageNumber
	 *            the page number
	 * @param rows
	 *            the rows
	 * @param sorts
	 *            排序,如果传入为null 不会增加排序字段
	 * @param facetFields
	 *            the facet fields
	 * @return the solr data
	 */
	SolrData<T> findByQuery(SolrQuery solrQuery,Integer pageNumber,int rows,Sort[] sorts,String[] facetFields);

	/**
	 * 这是一个 带facet功能的 solr query,<br>
	 * 可以实现 鞋(20) 带数字的这样的功能<br>
	 * 如果你的网站不需要显示 类似于 鞋(20) 带数字count 功能的 不需要直接调用这个方法.
	 * 
	 * @param solrQuery
	 *            the solr query
	 * @param pageNumber
	 *            第几页,为自然页,从1开始<br>
	 *            <ul>
	 *            <li>可以为null,系统自动为1</li>
	 *            <li>如果<1,系统自动为1</li>
	 *            </ul>
	 * @param rows
	 *            the rows
	 * @param sorts
	 *            排序,如果传入为null 不会增加排序字段
	 * @param facetParamCommand
	 *            facetParamCommand 封装了一系列的facet参数
	 * @return the solr data
	 */
	SolrData<T> findByQuery(SolrQuery solrQuery,Integer pageNumber,int rows,Sort[] sorts,FacetParamsCommand facetParamCommand);

	// **************************************待重新封装(可调用)***********************************************************
	/**
	 * 仅仅获得 facetQuerys 信息<br>
	 * 某些场景下,facetQuery是基于 搜索去掉一些条件 出来的,比如 UA的 价格, .
	 * 
	 * @param solrQuery
	 *            the solr query
	 * @param facetQuerys
	 *            the facet querys
	 * @param facetQueryMinCount
	 *            如果 传入的是null,默认为0,当facetquery出来的count 小于这个阀值的时候将被从map中删除掉
	 * @return the map< string, integer>
	 * @deprecated 待重新封装(可调用)
	 */
	Map<String, Integer> findFacetQueryMap(SolrQuery solrQuery,String[] facetQuerys,Integer facetQueryMinCount);

	// **************************************待重新封装***********************************************************
	/**
	 * 使用 group by.
	 * 
	 * @param solrQuery
	 *            the solr query
	 * @param pageNumber
	 *            第几页 页面,为自然页,从1开始<br>
	 *            可以为null,系统自动为1<br>
	 *            如果<1,系统自动为1
	 * @param rows
	 *            每页读取多少数据
	 * @param facetFields
	 *            the facet fields
	 * @param sorts
	 *            排序,如果传入为null 不会增加排序字段
	 * @return the solr group data
	 * @throws SolrException
	 *             the solr exception
	 * @deprecated 待重新封装,暂不要调用
	 */
	SolrGroupData<T> findByQueryWithGroup(
			SolrQuery solrQuery,
			Integer pageNumber,
			int rows,
			Sort[] sorts,
			String[] facetFields,
			GroupParamsCommand groupParamCommand) throws SolrException;
}
