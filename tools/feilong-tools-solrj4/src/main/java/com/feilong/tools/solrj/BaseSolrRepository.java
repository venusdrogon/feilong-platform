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

package com.feilong.tools.solrj;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import loxia.dao.Pagination;
import loxia.dao.Sort;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.request.UpdateRequest;

import com.feilong.tools.solrj.data.SolrData;
import com.feilong.tools.solrj.data.SolrGroupData;
import com.feilong.tools.solrj.exception.SolrException;
import com.feilong.tools.solrj.paramscommand.FacetParamsCommand;
import com.feilong.tools.solrj.paramscommand.GroupParamsCommand;

/**
 * Solrj核心操作封装.
 * 
 * <h3>和 官方商城组4.0相比,有以下改动</h3>
 * 
 * <blockquote>
 * <p>
 * <ul>
 * <li>1.所有操作执行的方法 将 SolrException 异常外抛, 业务来控制</li>
 * <li>2.更详细的 参数非空validator</li>
 * <li>3.参数顺序进行更友好调整</li>
 * <li>4.sort当作参数传递</li>
 * <li>5.增加适用场景</li>
 * <li>6.删除了以往不调用的方法</li>
 * </ul>
 * <p>
 * </blockquote>
 * 
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0.2 2012-3-11 下午5:01:26
 * @version 1.0.5 2013-12-19 23:39
 * @version 1.0.7 2014-5-26 23:23 丰富了javadoc
 * @param <T>
 *            T为 和solr schemal对应的对象
 * @param <PK>
 *            PK为T的主键,比如id字段
 * @see org.apache.solr.client.solrj.SolrServer
 * @see org.apache.solr.client.solrj.impl.LBHttpSolrServer
 * @see org.apache.solr.client.solrj.impl.CloudSolrServer
 * @see org.apache.solr.client.solrj.impl.HttpSolrServer
 * @since 1.0.2
 */
public interface BaseSolrRepository<T, PK extends Serializable> {

	// *****************************************保存/更新******************************************

	/**
	 * 保存/更新 某个bean对象
	 * 
	 * <p>
	 * 内部会调用 {@link #batchSave(Collection)} 方法,其实 {@link SolrServer#addBean(Object)} {@link SolrServer#addBeans(Collection)}
	 * {@link SolrServer#add(Collection)} 在底层是相通的,会将对象设置到 {@link UpdateRequest#getDocumentsMap()} 中
	 * </p>
	 * 
	 * @param model
	 *            solr 和schemal关联的对象
	 * @throws SolrException
	 *             if操作发生异常
	 * @throws NullPointerException
	 *             isNullOrEmpty(model)
	 * @see SolrServer#addBean(Object)
	 * @see SolrServer#addBeans(Collection)
	 * @see SolrServer#add(Collection)
	 * @see #batchSave(Collection)
	 * @since solr 3.5
	 */
	void save(T model) throws SolrException,NullPointerException;

	/**
	 * 批量保存/更新
	 * 
	 * <p>
	 * 其实 {@link SolrServer#addBean(Object)} {@link SolrServer#addBeans(Collection)} {@link SolrServer#add(Collection)} 在底层是相通的,会将对象设置到
	 * {@link UpdateRequest#getDocumentsMap()} 中
	 * </p>
	 * 
	 * @param modelList
	 *            solr 和schemal关联的对象 集合对象
	 * @throws NullPointerException
	 *             if isNullOrEmpty(modelList)
	 * @throws SolrException
	 *             if操作发生异常
	 * @see SolrServer#addBeans(Collection)
	 * @see SolrServer#add(Collection)
	 * @since solr 3.5
	 */
	void batchSave(Collection<T> modelList) throws NullPointerException,SolrException;

	// *****************************************删除***********************************
	/**
	 * Delete.
	 * 
	 * @param primaryKey
	 *            the primary key
	 * @throws SolrException
	 *             if操作发生异常
	 */
	void delete(PK primaryKey) throws SolrException;

	/**
	 * Delete by query.
	 * 
	 * @param query
	 *            the query
	 * @throws SolrException
	 *             if操作发生异常
	 */
	void deleteByQuery(String query) throws SolrException;

	/**
	 * 批量删除.
	 * 
	 * @param pkList
	 *            the pk list
	 * @throws SolrException
	 *             如果执行的过程中,出现异常,抛出这个异常
	 */
	void batchDelete(List<PK> pkList) throws SolrException;

	// ***************************************查询******************************************************************

	/**
	 * Find by query,这个方法适合于 (给你几个code ,查询下) 这种不带facet等参数的方法.
	 * 
	 * @param solrQuery
	 *            the solr query
	 * @param pageNumber
	 *            第几页,为自然页,从1开始
	 *            <ul>
	 *            <li>可以为null,系统自动为1</li>
	 *            <li>如果<1,系统自动为1</li>
	 *            </ul>
	 * @param rows
	 *            每页读取多少数据
	 * @param sorts
	 *            排序,如果传入为null 不会增加排序功能
	 * @return the pagination
	 * @see #findByQuery(SolrQuery, Integer, int, Sort[], FacetParamsCommand)
	 * @see Pagination
	 */
	Pagination<T> findByQuery(SolrQuery solrQuery,Integer pageNumber,int rows,Sort[] sorts);

	/**
	 * 这是一个 带facet功能的 solr query
	 * <p>
	 * 可以实现 鞋(20) 带数字的这样的功能<br>
	 * 如果你的网站不需要显示 类似于 鞋(20) 带数字count 功能的 不需要直接调用这个方法.
	 * </p>
	 * 
	 * @param solrQuery
	 *            the solr query
	 * @param pageNumber
	 *            第几页,为自然页,从1开始
	 *            <ul>
	 *            <li>可以为null,系统自动为1</li>
	 *            <li>如果<1,系统自动为1</li>
	 *            </ul>
	 * @param rows
	 *            每页读取多少数据
	 * @param sorts
	 *            排序,如果传入为null 不会增加排序功能
	 * @param facetFields
	 *            需要facet的字段
	 * @return the solr data
	 * @see #findByQuery(SolrQuery, Integer, int, Sort[], FacetParamsCommand)
	 * @see SolrData
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
	 *            第几页,为自然页,从1开始
	 *            <ul>
	 *            <li>可以为null,系统自动为1</li>
	 *            <li>如果<1,系统自动为1</li>
	 *            </ul>
	 * @param rows
	 *            每页读取多少数据
	 * @param sorts
	 *            排序,如果传入为null 不会增加排序字段
	 * @param facetParamCommand
	 *            facetParamCommand 封装了一系列的facet参数
	 * @return the solr data,如果执行的过程中,出现了异常,返回
	 * @throws NullPointerException
	 *             if null==solrQuery
	 */
	SolrData<T> findByQuery(SolrQuery solrQuery,Integer pageNumber,int rows,Sort[] sorts,FacetParamsCommand facetParamCommand)
					throws NullPointerException;

	// **************************************待重新封装(可调用)***********************************************************
	/**
	 * 仅仅获得 facetQuerys 信息<br>
	 * 某些场景下,facetQuery是基于 搜索去掉一些条件 出来的,比如 UA的 价格.
	 * 
	 * @param solrQuery
	 *            the solr query
	 * @param facetQuerys
	 *            the facet querys
	 * @param facetQueryMinCount
	 *            if (null == facetQueryMinCount || facetQueryMinCount < 0 ) than 自动为0,<br>
	 *            当facetquery出来的count 小于这个阀值的时候将被从map中删除掉
	 * @return the map< string, integer><br>
	 *         出现查询没有facet结果,返回null
	 * @throws NullPointerException
	 *             if null == solrQuery or isNullOrEmpty(facetQuerys)
	 * @throws SolrException
	 *             如果操作过程中出现了异常
	 * @deprecated 在未来版本,会重新封装(可调用)
	 */
	@Deprecated
	//TODO
	Map<String, Integer> findFacetQueryMap(SolrQuery solrQuery,String[] facetQuerys,Integer facetQueryMinCount)
					throws NullPointerException,SolrException;

	// **************************************待重新封装***********************************************************
	/**
	 * 使用 group by.
	 * 
	 * @param solrQuery
	 *            the solr query
	 * @param pageNumber
	 *            第几页,为自然页,从1开始
	 *            <ul>
	 *            <li>可以为null,系统自动为1</li>
	 *            <li>如果<1,系统自动为1</li>
	 *            </ul>
	 * @param rows
	 *            每页读取多少数据
	 * @param sorts
	 *            排序,如果传入为null 不会增加排序字段
	 * @param facetFields
	 *            the facet fields
	 * @param groupParamCommand
	 *            the group param command
	 * @return the solr group data
	 * @throws SolrException
	 *             if (solrQuery == null) or if (null == groupParamCommand || groupParamCommand.getIsGroup())
	 * @throws NullPointerException
	 *             the null pointer exception
	 * @deprecated 在未来版本,会重新封装(可调用)
	 */
	@Deprecated
	//TODO
	SolrGroupData<T> findByQueryWithGroup(
					SolrQuery solrQuery,
					Integer pageNumber,
					int rows,
					Sort[] sorts,
					String[] facetFields,
					GroupParamsCommand groupParamCommand) throws SolrException,NullPointerException;
}
