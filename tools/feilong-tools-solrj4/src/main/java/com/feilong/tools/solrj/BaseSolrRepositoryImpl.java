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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import loxia.dao.Pagination;
import loxia.dao.Sort;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.FacetParams;
import org.apache.solr.common.params.GroupParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.lang.reflect.TypeUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.tools.solrj.command.SolrGroup;
import com.feilong.tools.solrj.command.SolrGroupCommand;
import com.feilong.tools.solrj.data.BaseSolrData;
import com.feilong.tools.solrj.data.SolrData;
import com.feilong.tools.solrj.data.SolrGroupData;
import com.feilong.tools.solrj.exception.SolrException;
import com.feilong.tools.solrj.paramscommand.FacetParamsCommand;
import com.feilong.tools.solrj.paramscommand.GroupParamsCommand;

/**
 * 所有solr repo 操作实现的父类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0.2 2012-2-24 下午2:00:52 copy from pepsitmall
 * @version 1.0.5 2013-12-19 23:39
 * @version 1.0.7 2014-5-26 23:23 丰富了javadoc
 * @param <T>
 *            T为 和solr schemal对应的对象
 * @param <PK>
 *            PK为T的主键,比如id字段
 * @see org.apache.solr.client.solrj.SolrServer
 * @see org.apache.solr.client.solrj.impl.LBHttpSolrServer
 * @see org.apache.solr.client.solrj.impl.ConcurrentUpdateSolrServer
 * @see org.apache.solr.client.solrj.impl.CloudSolrServer
 * @see org.apache.solr.client.solrj.impl.HttpSolrServer
 * @since 1.0.2
 */
public abstract class BaseSolrRepositoryImpl<T, PK extends Serializable> implements BaseSolrRepository<T, PK>{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(BaseSolrRepositoryImpl.class);

	/**
	 * The solrServer.
	 * 
	 * @see org.apache.solr.client.solrj.impl.LBHttpSolrServer
	 * @see org.apache.solr.client.solrj.impl.ConcurrentUpdateSolrServer
	 * @see org.apache.solr.client.solrj.impl.CloudSolrServer
	 * @see org.apache.solr.client.solrj.impl.HttpSolrServer
	 */
	// Don't Autowired
	protected SolrServer		solrServer;

	// *****************************************************************************************

	/** 和solr schemal对应的对象类型. */
	protected Class<T>			modelClass;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init(){
		this.modelClass = TypeUtil.getGenericModelClass(this.getClass());

		if (null == solrServer){
			throw new NullPointerException(
					"solrServer is null or empty! must ioc solrServer property!,you can choose LBHttpSolrServer,ConcurrentUpdateSolrServer,CloudSolrServer,HttpSolrServer");
		}
	}

	// ***********************************************************************************
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.solrj.BaseSolrRepository#findByQuery(org.apache.solr.client.solrj.SolrQuery, java.lang.Integer, int,
	 * loxia.dao.Sort[])
	 */
	public Pagination<T> findByQuery(SolrQuery solrQuery,Integer pageNumber,int rows,Sort[] sorts){
		FacetParamsCommand facetParamCommand = null;
		SolrData<T> solrData = findByQuery(solrQuery, pageNumber, rows, sorts, facetParamCommand);

		Pagination<T> page = solrData.getPagination();
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.solrj.BaseSolrRepository#findByQuery(org.apache.solr.client.solrj.SolrQuery, java.lang.Integer, int,
	 * loxia.dao.Sort[], java.lang.String[])
	 */
	public SolrData<T> findByQuery(SolrQuery solrQuery,Integer pageNumber,int rows,Sort[] sorts,String[] facetFields){
		FacetParamsCommand facetParamCommand = toFacetParamCommand(facetFields);
		return findByQuery(solrQuery, pageNumber, rows, sorts, facetParamCommand);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.solrj.BaseSolrRepository#findByQuery(org.apache.solr.client.solrj.SolrQuery, java.lang.Integer, int,
	 * loxia.dao.Sort[], com.feilong.tools.solrj.command.FacetParamCommand)
	 */
	public SolrData<T> findByQuery(SolrQuery solrQuery,Integer pageNumber,int rows,Sort[] sorts,FacetParamsCommand facetParamCommand)
			throws NullPointerException{
		if (solrQuery == null){
			throw new NullPointerException("solrQuery can't be null/empty!");
		}

		// ***************************************************************
		// 设置facet相关参数
		setFacetParam(solrQuery, facetParamCommand);

		// ***************************************************************

		solrQuery = setStartAndRows(solrQuery, pageNumber, rows);
		solrQuery = setSort(solrQuery, sorts);

		if (log.isDebugEnabled()){
			Map<String, Object> solrQueryMapForLog = SolrjUtil.getSolrQueryMapForLog(solrQuery);
			log.debug("solrQueryMapForLog:{}", JsonUtil.format(solrQueryMapForLog));
		}

		try{
			QueryResponse queryResponse = solrServer.query(solrQuery);
			SolrDocumentList solrDocumentList = queryResponse.getResults();
			long numFound = solrDocumentList.getNumFound();
			List<T> beans = queryResponse.getBeans(modelClass);

			if (log.isDebugEnabled()){
				Map<String, Object> queryResponseMapForLog = SolrjUtil.getQueryResponseMapForLog(queryResponse, modelClass);
				log.debug("queryResponseMapForLog:{}", JsonUtil.format(queryResponseMapForLog));
			}

			//******************************************************
			Pagination<T> page = generatePagination(pageNumber, rows, numFound, beans);
			SolrData<T> solrData = new SolrData<T>();
			setFacetData(solrData, queryResponse);

			solrData.setPagination(page);
			return solrData;
		}catch (SolrServerException e){
			log.error(new SolrException(e).getMessage());
			e.printStackTrace();
			// throw new SolrException(e);
			return new SolrData<T>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.solrj.BaseSolrRepository#findByQueryWithGroup(org.apache.solr.client.solrj.SolrQuery, java.lang.Integer, int,
	 * loxia.dao.Sort[], java.lang.String[], com.feilong.tools.solrj.command.GroupParamCommand)
	 */
	public SolrGroupData<T> findByQueryWithGroup(
			SolrQuery solrQuery,
			Integer pageNumber,
			int rows,
			Sort[] sorts,
			String[] facetFields,
			GroupParamsCommand groupParamCommand) throws SolrException,NullPointerException{

		if (solrQuery == null){
			throw new NullPointerException("solrQuery can't be null/empty!");
		}

		if (null == groupParamCommand || groupParamCommand.getIsGroup()){
			log.error("this method is a groupQuery method,if you don't want group ,please use else method ");
			throw new NullPointerException("null == groupParamCommand || groupParamCommand.getIsGroup()");
		}

		FacetParamsCommand facetParamCommand = toFacetParamCommand(facetFields);

		// 设置facet相关参数
		setFacetParam(solrQuery, facetParamCommand);

		// 设置 group 相关参数
		setGroupParam(solrQuery, groupParamCommand);

		// ********************************************************************************************
		solrQuery = setStartAndRows(solrQuery, pageNumber, rows);
		solrQuery = setSort(solrQuery, sorts);

		if (log.isDebugEnabled()){
			Map<String, Object> solrQueryMapForLog = SolrjUtil.getSolrQueryMapForLog(solrQuery);
			log.debug("solrQueryMapForLog:{}", JsonUtil.format(solrQueryMapForLog));
		}
		try{
			QueryResponse queryResponse = solrServer.query(solrQuery);
			// log.debug("queryResponse:{}", queryResponse.toString());
			GroupResponse groupResponse = queryResponse.getGroupResponse();

			if (null != groupResponse){
				SolrGroupData<T> solrGroupData = new SolrGroupData<T>();

				// 自定义对象
				Map<String, SolrGroupCommand<T>> solrGroupCommandMap = new HashMap<String, SolrGroupCommand<T>>();

				List<GroupCommand> groupCommandList = groupResponse.getValues();
				for (GroupCommand groupCommand : groupCommandList){
					// 自定义对象
					SolrGroupCommand<T> solrGroupCommand = new SolrGroupCommand<T>();

					Integer ngroups = groupCommand.getNGroups();

					if (null != ngroups){

						int matches = groupCommand.getMatches();
						String name = groupCommand.getName();

						List<Group> groupList = groupCommand.getValues();

						List<SolrGroup<T>> values = new ArrayList<SolrGroup<T>>();
						for (Group group : groupList){
							String groupValue = group.getGroupValue();
							SolrDocumentList solrDocumentList = group.getResult();

							List<T> beans = toBeans(solrDocumentList);
							Long numFound = solrDocumentList.getNumFound();

							SolrGroup<T> solrGroup = new SolrGroup<T>();
							solrGroup.setNumFound(numFound);
							solrGroup.setGroupValue(groupValue);
							solrGroup.setBeans(beans);

							values.add(solrGroup);
						}

						Long count = Long.parseLong(ngroups + "");
						Pagination<SolrGroup<T>> pagination = generatePagination(pageNumber, rows, count, values);

						// pagination.se

						solrGroupCommand.setMatches(matches);
						solrGroupCommand.setName(name);
						solrGroupCommand.setNgroups(ngroups);

						solrGroupCommand.setPagination(pagination);

						solrGroupCommandMap.put(name, solrGroupCommand);
					}
				}

				setFacetData(solrGroupData, queryResponse);

				solrGroupData.setSolrGroupCommandMap(solrGroupCommandMap);
				return solrGroupData;
			}
		}catch (SolrServerException e){
			throw new SolrException(e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.solrj.BaseSolrRepository#findFacetQueryMap(org.apache.solr.client.solrj.SolrQuery, java.lang.String[],
	 * java.lang.Integer)
	 */
	@Deprecated
	public Map<String, Integer> findFacetQueryMap(SolrQuery solrQuery,String[] facetQuerys,Integer facetQueryMinCount)
			throws NullPointerException,SolrException{
		if (null == solrQuery){
			throw new NullPointerException("solrQuery can't be null/empty!");
		}

		if (Validator.isNullOrEmpty(facetQuerys)){
			throw new NullPointerException("facetQuerys can't be null/empty!");
		}

		// 如果 传入的是null,自动为0
		if (null == facetQueryMinCount || facetQueryMinCount < 0){
			facetQueryMinCount = 0;
		}
		//**************************************************************************************

		if (log.isDebugEnabled()){
			log.debug("facetQuerys:{},facetQueryMinCount:{}", JsonUtil.format(facetQuerys), facetQueryMinCount);
		}
		//**************************************************************************************
		solrQuery.setFacet(true);

		for (String facetQuery : facetQuerys){
			solrQuery.addFacetQuery(facetQuery);
		}

		try{
			QueryResponse queryResponse = solrServer.query(solrQuery);
			Map<String, Integer> facetQueryMap = queryResponse.getFacetQuery();

			// 排除0
			if (Validator.isNotNullOrEmpty(facetQueryMap)){

				// 此种删除 不会抛出 ConcurrentModificationException
				Set<String> keySet = facetQueryMap.keySet();
				for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();){
					String key = iterator.next();
					Integer value = facetQueryMap.get(key);
					if (value < facetQueryMinCount){
						iterator.remove();
					}
				}

				if (log.isDebugEnabled()){
					log.debug("facetQueryMap:{}", JsonUtil.format(facetQueryMap));
				}
				return facetQueryMap;
			}
		}catch (SolrServerException e){
			e.printStackTrace();
			throw new SolrException(e);
		}

		return null;
	}

	// ****************************************************************************************************************

	/**
	 * Gets the facet param command.
	 * 
	 * @param facetFields
	 *            the facet fields
	 * @return if null==facetFields,return new FacetParamsCommand()<br>
	 *         否则,内部自动设置 {@link FacetParamsCommand#setMincount(Integer)}为1,如果需要更加精确的,请调用
	 *         {@link #findByQuery(SolrQuery, Integer, int, Sort[], FacetParamsCommand)}
	 */
	private FacetParamsCommand toFacetParamCommand(String[] facetFields){
		FacetParamsCommand facetParamCommand = new FacetParamsCommand();
		if (Validator.isNotNullOrEmpty(facetFields)){
			// 如果 facetFields facetQuerys 只要不都为null 则表示要facet
			facetParamCommand.setFacet(true);
			facetParamCommand.setMincount(1);
			facetParamCommand.setSort(FacetParams.FACET_SORT_COUNT);
			facetParamCommand.setFacetFields(facetFields);
		}
		return facetParamCommand;
	}

	/**
	 * 设置通用的 数据.
	 * 
	 * @param baseSolrData
	 *            the base solr data
	 * @param queryResponse
	 *            the query response
	 */
	private void setFacetData(BaseSolrData<T> baseSolrData,QueryResponse queryResponse){
		Map<String, Map<String, Long>> facetMap = getFacetMap(queryResponse);
		Map<String, Integer> facetQuery = queryResponse.getFacetQuery();

		baseSolrData.setFacetQueryMap(facetQuery);
		baseSolrData.setFacetMap(facetMap);
	}

	/**
	 * debug FacetField.
	 * 
	 * @param queryResponse
	 *            the query response
	 * @return the facet map
	 */
	private Map<String, Map<String, Long>> getFacetMap(QueryResponse queryResponse){
		Map<String, Map<String, Long>> map = null;
		List<FacetField> facetFields = queryResponse.getFacetFields();
		if (Validator.isNotNullOrEmpty(facetFields)){
			map = new HashMap<String, Map<String, Long>>();
			// ************************************************
			for (FacetField facetField : facetFields){
				String facetFieldName = facetField.getName();
				// int valueCount = facetField.getValueCount();
				List<Count> values = facetField.getValues();
				if (Validator.isNotNullOrEmpty(values)){
					Map<String, Long> facetMap = new LinkedHashMap<String, Long>();
					for (Count count : values){
						String countName = count.getName();
						Long countCount = count.getCount();
						facetMap.put(countName, countCount);
					}
					map.put(facetFieldName, facetMap);
				}
			}
			if (log.isDebugEnabled()){
				log.debug("map:{}", JsonUtil.format(map));
			}
		}
		return map;
	}

	/**
	 * 设置 group 参数.
	 * 
	 * @param solrQuery
	 *            the solr query
	 * @param groupParamCommand
	 *            the group param command
	 */
	private void setGroupParam(SolrQuery solrQuery,GroupParamsCommand groupParamCommand){
		// if true, turn on result grouping
		solrQuery.set(GroupParams.GROUP, true);

		String[] groupFields = groupParamCommand.getGroupFields();

		// 可以设置为数组 params.set("group.field", "id", "age");  "style"
		solrQuery.set(GroupParams.GROUP_FIELD, groupFields);

		// 这两个参数 只影响 聚合的结果
		// 比如 每款鞋子 我只想显示 两双 诸如此类;
		// 如果不配置 只显示第一个 坑爹
		solrQuery.set(GroupParams.GROUP_OFFSET, groupParamCommand.getOffset());// the offset for the doclist of each group
		solrQuery.set(GroupParams.GROUP_LIMIT, groupParamCommand.getLimit());// the limit for the number of documents in each group

		// 这个参数 用来 显示 聚合后 一共有多少款 诸如此类
		// group.ngroups:Whether the group count should be included in the response
		solrQuery.set(GroupParams.GROUP_TOTAL_COUNT, groupParamCommand.getNgroups());

		// grouped/simple
		// 区别 simple 不会聚合显示, grouped 会, grouped 是我们想要的
		String format = groupParamCommand.getFormat();
		if (Validator.isNotNullOrEmpty(format)){
			solrQuery.set(GroupParams.GROUP_FORMAT, format);
		}

		// How to sort documents within a single group.
		// Defaults to the same value as the sort parameter.
		String sort = groupParamCommand.getSort();
		if (Validator.isNotNullOrEmpty(sort)){
			solrQuery.set(GroupParams.GROUP_SORT, sort);
		}

		// treat the first group result as the main result. true/false
		//  when /*group.format=simple and */ group.main=true, just return the documentList only!!!   
		String main = groupParamCommand.getMain();
		if (Validator.isNotNullOrEmpty(main)){
			solrQuery.set(GroupParams.GROUP_MAIN, main);
		}

		// ***********************************************************************************************************

		// default is 0; 
		// Whether to cache the first pass search (doc ids and score) for the second pass search.
		// Also defines the maximum size of the group cache relative to maxdoc in a percentage.

		// Values can be a positive integer, from 0 till 100.
		// A value of 0 will disable the group cache.
		// The default is 0.

		// Cache limit of 50 percent relative to maxdoc has exceeded.
		// Please increase cache size or disable caching
		String cachePercent = groupParamCommand.getCachePercent();
		if (Validator.isNotNullOrEmpty(cachePercent)){
			solrQuery.set(GroupParams.GROUP_CACHE_PERCENTAGE, cachePercent);
		}

	}

	// ********************************保存********************************************************************

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.solrj.BaseSolrRepository#save(java.lang.Object)
	 */
	public void save(T model) throws SolrException,NullPointerException{
		if (Validator.isNullOrEmpty(model)){
			throw new NullPointerException("model can't be null/empty!");
		}

		if (log.isDebugEnabled()){
			log.debug("save model:{}", model);
		}

		List<T> list = new ArrayList<T>();
		list.add(model);

		batchSave(list);

		//UpdateResponse addBeanUpdateResponse = solrServer.addBean(model);
		//UpdateResponse commitUpdateResponse = solrServer.commit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.solrj.BaseSolrRepository#batchSave(java.util.Collection)
	 */
	public void batchSave(Collection<T> modelList) throws NullPointerException,SolrException{
		if (Validator.isNullOrEmpty(modelList)){
			throw new NullPointerException("modelList can't be null/empty!");
		}

		if (log.isDebugEnabled()){
			log.debug("batchSave:{}", modelList);
		}

		Collection<SolrInputDocument> solrInputDocuments = toSolrInputDocumentCollection(modelList);
		batchUpdate(solrInputDocuments);

		//solrServer.addBeans(modelList) 实现的核心是 将beans转成  Collection<SolrInputDocument> solrInputDocuments
		//再调用 add(Collection<SolrInputDocument> docs, int commitWithinMs) 方法

		//UpdateResponse addBeansUpdateResponse = solrServer.addBeans(modelList);
		//UpdateResponse commitUpdateResponse = solrServer.commit();
	}

	/**
	 * Batch update.
	 * 
	 * @param solrInputDocuments
	 *            the solr input documents
	 * @throws NullPointerException
	 *             isNullOrEmpty(solrInputDocuments)
	 * @throws SolrException
	 *             操作异常
	 */
	//TODO 
	private void batchUpdate(Collection<SolrInputDocument> solrInputDocuments) throws NullPointerException,SolrException{

		if (Validator.isNullOrEmpty(solrInputDocuments)){
			throw new NullPointerException("solrInputDocuments can't be null/empty!");
		}

		if (log.isDebugEnabled()){
			log.debug("solrInputDocuments:{}", solrInputDocuments);
		}

		try{
			@SuppressWarnings("unused")
			UpdateResponse addBeanUpdateResponse = solrServer.add(solrInputDocuments);
			@SuppressWarnings("unused")
			UpdateResponse commitUpdateResponse = solrServer.commit();
		}catch (Exception e){
			e.printStackTrace();
			throw new SolrException("Save failed for model list " + solrInputDocuments.size() + "," + e.getMessage());
		}
	}

	/**
	 * Batch update.
	 * 
	 * @param map
	 *            key为model的主键值,map是需要更新的字段 K/V
	 * @throws NullPointerException
	 *             isNullOrEmpty(map)
	 * @throws SolrException
	 *             the solr exception
	 */
	//TODO
	private void batchUpdate(Map<PK, Map<String, Object>> map) throws NullPointerException,SolrException{
		if (Validator.isNullOrEmpty(map)){
			throw new NullPointerException("map can't be null/empty!");
		}

		if (log.isDebugEnabled()){
			log.debug("solrInputDocuments:{}", map);
		}
		List<SolrInputDocument> solrInputDocuments = new ArrayList<SolrInputDocument>();

		for (Map.Entry<PK, Map<String, Object>> entry : map.entrySet()){
			PK pk = entry.getKey();
			Map<String, Object> value = entry.getValue();

			SolrInputDocument doc = new SolrInputDocument();
			//TODO
			doc.setField("id", pk);

			for (Map.Entry<String, Object> entry1 : value.entrySet()){
				String key = entry1.getKey();
				Object value1 = entry.getValue();
				doc.setField(key, value1);
			}
			solrInputDocuments.add(doc);
		}
		batchUpdate(solrInputDocuments);
	}

	// ********************************************删除**************************************************************
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.solrj.BaseSolrRepository#delete(java.io.Serializable)
	 */
	public void delete(PK primaryKey) throws SolrException{
		if (Validator.isNullOrEmpty(primaryKey)){
			throw new IllegalArgumentException("primaryKey can't be null/empty!");
		}

		log.debug("solrServer.deleteById:{}", primaryKey);
		try{
			@SuppressWarnings("unused")
			UpdateResponse deleteByIdUpdateResponse = solrServer.deleteById(primaryKey.toString());
			@SuppressWarnings("unused")
			UpdateResponse commitUpdateResponse = solrServer.commit();
		}catch (Exception e){
			throw new SolrException("Delete failed for model with PK:" + primaryKey + "," + e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.solrj.BaseSolrRepository#deleteByQuery(java.lang.String)
	 */
	public void deleteByQuery(String query) throws SolrException{
		if (Validator.isNullOrEmpty(query)){
			throw new IllegalArgumentException("query can't be null/empty!");
		}

		if (log.isDebugEnabled()){
			log.debug("solrServer.deleteByQuery:{}", query);
		}

		try{
			@SuppressWarnings("unused")
			UpdateResponse deleteByQueryUpdateResponse = solrServer.deleteByQuery(query);
			@SuppressWarnings("unused")
			UpdateResponse commitUpdateResponse = solrServer.commit();
		}catch (Exception e){
			throw new SolrException("Delete failed for model with query:" + query + "," + e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.solrj.BaseSolrRepository#batchDelete(java.util.List)
	 */
	public void batchDelete(List<PK> pkList) throws SolrException{
		if (Validator.isNullOrEmpty(pkList)){
			log.warn("pkList is null/empty");
			throw new IllegalArgumentException("pkList can't be null/empty!");
		}
		List<String> ids = new ArrayList<String>();
		for (PK pk : pkList){
			if (Validator.isNullOrEmpty(pk)){
				throw new IllegalArgumentException("pk can't be null/empty!");
			}
			ids.add(pk.toString());
		}

		log.debug("solrServer.deleteById,pkList:{}", pkList);

		try{
			@SuppressWarnings("unused")
			UpdateResponse deleteByIdUpdateResponse = solrServer.deleteById(ids);
			@SuppressWarnings("unused")
			UpdateResponse commitUpdateResponse = solrServer.commit();
		}catch (Exception e){
			throw new SolrException("Batch delete model failed for modelClass " + modelClass + "," + e.getMessage(), e);
		}
	}

	// **********************************************************************************************************

	/**
	 * 设置facet相关参数
	 * <p>
	 * 仅当 {@link FacetParamsCommand#isFacet()}为true的时候,才会设置facet信息
	 * </p>
	 * .
	 * 
	 * @param solrQuery
	 *            the solr query
	 * @param facetParamCommand
	 *            the facet param command
	 * @return if isNullOrEmpty(facetParamCommand),直接返回SolrQuery;否则封装solrquery facet参数
	 * @throws IllegalArgumentException
	 *             如果 facetParamCommand.getFacetQuerys()中有一个是 isNullOrEmpty(facetQuery)
	 * @see FacetParamsCommand
	 */
	private SolrQuery setFacetParam(SolrQuery solrQuery,FacetParamsCommand facetParamCommand) throws IllegalArgumentException{

		if (Validator.isNullOrEmpty(facetParamCommand)){
			return solrQuery;
		}
		if (log.isDebugEnabled()){
			log.debug("facetParamCommand:{}", JsonUtil.format(facetParamCommand));
		}
		// 转换facet 参数
		boolean facet = facetParamCommand.isFacet();
		solrQuery.setFacet(facet);
		if (facet){
			String[] facetFields = facetParamCommand.getFacetFields();
			String[] facetQuerys = facetParamCommand.getFacetQuerys();
			boolean isFacetFieldsNotNullOrEmpty = Validator.isNotNullOrEmpty(facetFields);
			boolean isFacetQuerysNotNullOrEmpty = Validator.isNotNullOrEmpty(facetQuerys);

			if (isFacetFieldsNotNullOrEmpty || isFacetQuerysNotNullOrEmpty){
				solrQuery.setFacetMinCount(facetParamCommand.getMincount());
				solrQuery.setFacetLimit(facetParamCommand.getLimit());
				solrQuery.setFacetSort(facetParamCommand.getSort());
			}

			// FacetFields不是 null or empty
			if (isFacetFieldsNotNullOrEmpty){
				solrQuery.addFacetField(facetFields);
			}

			// facetQuery不是 null or empty
			if (isFacetQuerysNotNullOrEmpty){
				for (String facetQuery : facetQuerys){
					if (Validator.isNullOrEmpty(facetQuery)){
						throw new IllegalArgumentException("facetQuery can't be null/empty!");
					}
					solrQuery.addFacetQuery(facetQuery);
				}
			}
		}
		return solrQuery;
	}

	/**
	 * 设置排序信息.
	 * 
	 * @param solrQuery
	 *            the solr query
	 * @param sorts
	 *            如果sorts是isNullOrEmpty,直接返回 solrQuery
	 * @return 带有排序因子的SolrQuery
	 * @throws NullPointerException
	 *             if isNullOrEmpty(solrQuery)
	 * @see org.apache.solr.client.solrj.SolrQuery.ORDER
	 */
	private SolrQuery setSort(SolrQuery solrQuery,Sort[] sorts) throws NullPointerException{
		if (Validator.isNullOrEmpty(solrQuery)){
			throw new NullPointerException("solrQuery can't be null/empty!");
		}
		if (Validator.isNullOrEmpty(sorts)){
			log.debug("the param sorts is null~~");
			return solrQuery;
		}

		for (Sort sort : sorts){
			if (Validator.isNullOrEmpty(sort)){
				throw new IllegalArgumentException("sort can't be null/empty!");
			}
			ORDER order = sort.getType().equals(Sort.ASC) ? ORDER.asc : ORDER.desc;
			//TODO 使用新的API
			solrQuery.addSortField(sort.getField(), order);
		}
		return solrQuery;
	}

	/**
	 * 将 solrDocumentList 转成bean list.
	 * 
	 * @param solrDocumentList
	 *            the solr document list
	 * @return the list
	 * @see org.apache.solr.client.solrj.response.QueryResponse#getBeans(Class)
	 * @see org.apache.solr.client.solrj.beans.DocumentObjectBinder
	 */
	private List<T> toBeans(SolrDocumentList solrDocumentList){
		//		DocumentObjectBinder documentObjectBinder = null;
		//		if (null == solrServer){
		//			documentObjectBinder = new DocumentObjectBinder();
		//		}else{
		//			documentObjectBinder = solrServer.getBinder();
		//		}
		DocumentObjectBinder documentObjectBinder = solrServer.getBinder();
		List<T> beans = documentObjectBinder.getBeans(modelClass, solrDocumentList);
		return beans;
	}

	/**
	 * 将 Collection<T> beans转成 Collection<SolrInputDocument>.
	 * 
	 * @param beans
	 *            the beans
	 * @return the collection
	 * @see DocumentObjectBinder
	 * @see DocumentObjectBinder#toSolrInputDocument(Object)
	 * @since 1.0.7
	 */
	private Collection<SolrInputDocument> toSolrInputDocumentCollection(Collection<T> beans){
		DocumentObjectBinder documentObjectBinder = solrServer.getBinder();
		Collection<SolrInputDocument> solrInputDocuments = new ArrayList<SolrInputDocument>(beans.size());
		for (Object bean : beans){
			SolrInputDocument solrInputDocument = documentObjectBinder.toSolrInputDocument(bean);
			solrInputDocuments.add(solrInputDocument);
		}
		return solrInputDocuments;
	}

	/**
	 * 设置start和row.
	 * 
	 * @param solrQuery
	 *            solrQuery对象 内部调用,必须非空
	 * @param pageNumber
	 *            第几页,为自然页,从1开始<br>
	 *            <ul>
	 *            <li>可以为null,系统自动为1</li>
	 *            <li>如果<1,系统自动为1</li>
	 *            </ul>
	 * @param rows
	 *            the rows
	 * @return the integer
	 */
	private SolrQuery setStartAndRows(SolrQuery solrQuery,Integer pageNumber,int rows){
		Integer start = toStart(pageNumber, rows);
		solrQuery.setStart(start);
		solrQuery.setRows(rows);

		if (log.isDebugEnabled()){
			log.debug("pageNumber:{},start:{},rows:{}", pageNumber, start, rows);
		}
		return solrQuery;
	}

	/**
	 * 封装参数.
	 * 
	 * @param <G>
	 *            the generic type
	 * @param pageNumber
	 *            第几页,为自然页,从1开始
	 *            <ul>
	 *            <li>可以为null,系统自动为1</li>
	 *            <li>如果<1,系统自动为1</li>
	 *            </ul>
	 * @param rows
	 *            the rows
	 * @param count
	 *            the count
	 * @param items
	 *            the items
	 * @return the pagination
	 * @see loxia.dao.Pagination
	 */
	private <G> Pagination<G> generatePagination(Integer pageNumber,int rows,long count,List<G> items){
		Integer start = toStart(pageNumber, rows);

		Pagination<G> pagination = new Pagination<G>();
		pagination.setCount(count);
		pagination.setStart(start);
		pagination.setSize(rows);

		pagination.setCurrentPage(rows == 0 ? 1 : (start / rows + 1));
		pagination.setTotalPages(rows == 0 ? 0 : (int) count / rows + (count % rows == 0 ? 0 : 1));
		pagination.setItems(items);
		return pagination;
	}

	/**
	 * 将 PageNumber转成start.
	 * 
	 * @param pageNumber
	 *            第几页,为自然页,从1开始<br>
	 *            <ul>
	 *            <li>可以为null,系统自动为1</li>
	 *            <li>如果<1,系统自动为1</li>
	 *            </ul>
	 * @param rows
	 *            the rows
	 * @return the integer
	 */
	private Integer toStart(Integer pageNumber,int rows){
		Integer standardPageNumber = standardPageNumber(pageNumber);
		Integer start = (standardPageNumber - 1) * rows;
		return start;
	}

	/**
	 * 标准化pageNumber参数 ,肯定不会为{@code null},必然{@code >=1}.
	 * 
	 * @param pageNumber
	 *            第几页,为自然页,从1开始
	 *            <ul>
	 *            <li>可以为null,系统自动为1</li>
	 *            <li>如果<1,系统自动为1</li>
	 *            </ul>
	 * @return 标准化的PageNumber
	 */
	private Integer standardPageNumber(Integer pageNumber){
		if (null == pageNumber || pageNumber < 1){
			// 默认
			return 1;
		}
		return pageNumber;
	}

	// **********************************************************************************
	/**
	 * Sets the server.
	 * 
	 * @param solrServer
	 *            the solrServer to set
	 */
	public void setSolrServer(SolrServer solrServer){
		this.solrServer = solrServer;
	}
}
