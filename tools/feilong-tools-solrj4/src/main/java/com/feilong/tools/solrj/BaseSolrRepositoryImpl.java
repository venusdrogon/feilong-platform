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
import java.util.ArrayList;
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
import org.apache.solr.common.params.FacetParams;
import org.apache.solr.common.params.GroupParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.ReflectUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.tools.json.JsonUtil;
import com.feilong.tools.solrj.command.SolrGroup;
import com.feilong.tools.solrj.command.SolrGroupCommand;
import com.feilong.tools.solrj.data.BaseSolrData;
import com.feilong.tools.solrj.data.SolrData;
import com.feilong.tools.solrj.data.SolrGroupData;
import com.feilong.tools.solrj.exception.SolrException;
import com.feilong.tools.solrj.paramscommand.FacetParamsCommand;
import com.feilong.tools.solrj.paramscommand.GroupParamsCommand;

/**
 * The Class BaseSolrRepositoryImpl.
 * 
 * @param <T>
 *            the generic type
 * @param <PK>
 *            the generic type
 * @author copy from pepsitmall
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-2-24 下午2:00:52
 * @version 1.1 2013-12-19 23:39
 */
public abstract class BaseSolrRepositoryImpl<T, PK extends Serializable> implements BaseSolrRepository<T, PK>{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(BaseSolrRepositoryImpl.class);

	/** The server. */
	// Don't Autowired
	protected SolrServer		solrServer;

	// *****************************************************************************************

	/** The model class. */
	protected Class<T>			modelClass;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init(){
		this.modelClass = ReflectUtil.getGenericModelClass(this.getClass());
	}

	// ***********************************************************************************
	/*
	 * (non-Javadoc)
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
	 * @see com.feilong.tools.solrj.BaseSolrRepository#findByQuery(org.apache.solr.client.solrj.SolrQuery, java.lang.Integer, int,
	 * loxia.dao.Sort[], java.lang.String[])
	 */
	public SolrData<T> findByQuery(SolrQuery solrQuery,Integer pageNumber,int rows,Sort[] sorts,String[] facetFields){
		FacetParamsCommand facetParamCommand = toFacetParamCommand(facetFields);
		return findByQuery(solrQuery, pageNumber, rows, sorts, facetParamCommand);
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.tools.solrj.BaseSolrRepository#findByQuery(org.apache.solr.client.solrj.SolrQuery, java.lang.Integer, int,
	 * loxia.dao.Sort[], com.feilong.tools.solrj.command.FacetParamCommand)
	 */
	public SolrData<T> findByQuery(SolrQuery solrQuery,Integer pageNumber,int rows,Sort[] sorts,FacetParamsCommand facetParamCommand){
		if (solrQuery == null){
			throw new IllegalArgumentException("solrQuery can't be null/empty!");
		}

		// ***************************************************************
		// 设置facet相关参数
		setFacetParam(solrQuery, facetParamCommand);

		// ***************************************************************

		solrQuery = setStartAndRows(solrQuery, pageNumber, rows);
		solrQuery = setSort(solrQuery, sorts);

		SolrjUtil.showSolrQuery(solrQuery);

		try{
			QueryResponse queryResponse = solrServer.query(solrQuery);
			SolrDocumentList solrDocumentList = queryResponse.getResults();
			long numFound = solrDocumentList.getNumFound();
			List<T> beans = queryResponse.getBeans(modelClass);
			SolrjUtil.showQueryResponse(queryResponse, modelClass);
			/******************************************************/
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
	 * @see com.feilong.tools.solrj.BaseSolrRepository#findByQueryWithGroup(org.apache.solr.client.solrj.SolrQuery, java.lang.Integer, int,
	 * loxia.dao.Sort[], java.lang.String[], com.feilong.tools.solrj.command.GroupParamCommand)
	 */
	public SolrGroupData<T> findByQueryWithGroup(
			SolrQuery solrQuery,
			Integer pageNumber,
			int rows,
			Sort[] sorts,
			String[] facetFields,
			GroupParamsCommand groupParamCommand) throws SolrException{

		if (solrQuery == null){
			throw new IllegalArgumentException("solrQuery can't be null/empty!");
		}

		if (null == groupParamCommand || groupParamCommand.getIsGroup()){
			log.error("this method is a groupQuery method,if you don't want group ,please use else method ");
			throw new IllegalArgumentException("null == groupParamCommand || groupParamCommand.getIsGroup()");
		}

		FacetParamsCommand facetParamCommand = toFacetParamCommand(facetFields);

		// 设置facet相关参数
		setFacetParam(solrQuery, facetParamCommand);

		// 设置 group 相关参数
		setGroupParam(solrQuery, groupParamCommand);

		// ********************************************************************************************
		solrQuery = setStartAndRows(solrQuery, pageNumber, rows);
		solrQuery = setSort(solrQuery, sorts);

		SolrjUtil.showSolrQuery(solrQuery);
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

							List<T> beans = convertSolrDocumentListToBeans(solrDocumentList);
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
	 * @see com.feilong.tools.solrj.BaseSolrRepository#findFacetQueryMap(org.apache.solr.client.solrj.SolrQuery, java.lang.String[],
	 * java.lang.Integer)
	 */
	public Map<String, Integer> findFacetQueryMap(SolrQuery solrQuery,String[] facetQuerys,Integer facetQueryMinCount){
		if (solrQuery == null){
			throw new IllegalArgumentException("solrQuery can't be null/empty!");
		}
		if (Validator.isNotNullOrEmpty(facetQuerys)){
			solrQuery.setFacet(true);

			log.debug("the param facetQuerys:{}", facetQuerys);
			for (String facetQuery : facetQuerys){
				solrQuery.addFacetQuery(facetQuery);
			}

			try{
				QueryResponse queryResponse = solrServer.query(solrQuery);
				Map<String, Integer> facetQueryMap = queryResponse.getFacetQuery();

				// 排除0
				if (Validator.isNotNullOrEmpty(facetQueryMap)){
					// 如果 传入的是null,默认为0
					if (null == facetQueryMinCount){
						facetQueryMinCount = 0;
					}

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
						log.debug(JsonUtil.format(facetQueryMap));
					}
					return facetQueryMap;
				}

			}catch (SolrServerException e){
				log.error(e.getMessage());
				e.printStackTrace();
			}
		}else{
			log.debug("the param facetQuerys is null/empty");
		}

		return null;
	}

	// ****************************************************************************************************************

	/**
	 * Gets the facet param command.
	 * 
	 * @param facetFields
	 *            the facet fields
	 * @return the facet param command
	 */
	private FacetParamsCommand toFacetParamCommand(String[] facetFields){
		FacetParamsCommand facetParamCommand = new FacetParamsCommand();
		if (Validator.isNotNullOrEmpty(facetFields)){
			// 如果 facetFields facetQuerys 只要不都为null 则表示要facet
			facetParamCommand.setFacet(true);
			// 默认 mincount至少为1 返回 如果需要更精确的 请调用 com.jumbo.brandstore.manager.solr.BaseSolrRepositoryImpl.findByQuery(Integer, int, SolrQuery,
			// FacetParamCommand)
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
				log.debug(JsonUtil.format(map));
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
	 * @see com.feilong.tools.solrj.BaseSolrRepository#save(java.lang.Object)
	 */
	public T save(T model) throws SolrException{

		if (Validator.isNullOrEmpty(model)){
			throw new IllegalArgumentException("model can't be null/empty!");
		}

		log.debug("solrServer.addBean:{}", model);

		try{
			@SuppressWarnings("unused")
			UpdateResponse addBeanUpdateResponse = solrServer.addBean(model);
			@SuppressWarnings("unused")
			UpdateResponse commitUpdateResponse = solrServer.commit();
			return model;
		}catch (Exception e){
			log.error("error:{}", JsonUtil.format(model));
			e.printStackTrace();
			throw new SolrException("Save failed for model " + model + "," + e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.tools.solrj.BaseSolrRepository#batchSave(java.util.List)
	 */
	public void batchSave(List<T> modelList) throws SolrException{
		if (Validator.isNullOrEmpty(modelList)){
			throw new IllegalArgumentException("modelList can't be null/empty!");
		}

		log.debug("solrServer.batchSave:{}", modelList);

		try{
			@SuppressWarnings("unused")
			UpdateResponse addBeansUpdateResponse = solrServer.addBeans(modelList);
			@SuppressWarnings("unused")
			UpdateResponse commitUpdateResponse = solrServer.commit();
		}catch (Exception e){
			// log.error("error:{}", JsonUtil.format(modelList));
			e.printStackTrace();
			throw new SolrException("Batch save model failed for modelClass " + modelClass + "," + e.getMessage());
		}
	}

	// ********************************************删除**************************************************************
	/*
	 * (non-Javadoc)
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
			throw new SolrException("Delete failed for model with PK:" + primaryKey + "," + e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.tools.solrj.BaseSolrRepository#deleteByQuery(java.lang.String)
	 */
	public void deleteByQuery(String query) throws SolrException{
		if (Validator.isNullOrEmpty(query)){
			throw new IllegalArgumentException("query can't be null/empty!");
		}

		log.debug("solrServer.deleteByQuery:{}", query);

		try{
			@SuppressWarnings("unused")
			UpdateResponse deleteByQueryUpdateResponse = solrServer.deleteByQuery(query);
			@SuppressWarnings("unused")
			UpdateResponse commitUpdateResponse = solrServer.commit();
		}catch (Exception e){
			throw new SolrException("Delete failed for model with query:" + query + "," + e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
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
			throw new SolrException("Batch delete model failed for modelClass " + modelClass + "," + e.getMessage());
		}
	}

	// **********************************************************************************************************

	/**
	 * 设置facet相关参数.
	 * 
	 * @param solrQuery
	 *            the solr query
	 * @param facetParamCommand
	 *            the facet param command
	 * @return the solr query
	 */
	private SolrQuery setFacetParam(SolrQuery solrQuery,FacetParamsCommand facetParamCommand){
		if (Validator.isNotNullOrEmpty(facetParamCommand)){
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
					log.debug("the param facetQuerys:{}", facetQuerys);
					for (String facetQuery : facetQuerys){
						if (Validator.isNullOrEmpty(facetQuery)){
							throw new IllegalArgumentException("facetQuery can't be null/empty!");
						}
						solrQuery.addFacetQuery(facetQuery);
					}
				}
			}
		}
		return solrQuery;
	}

	/**
	 * Sets the sort.
	 * 
	 * @param solrQuery
	 *            the solr query
	 * @param sorts
	 *            the sorts
	 * @return the solr query
	 */
	private SolrQuery setSort(SolrQuery solrQuery,Sort[] sorts){
		if (Validator.isNullOrEmpty(solrQuery)){
			throw new IllegalArgumentException("solrQuery can't be null/empty!");
		}
		if (Validator.isNullOrEmpty(sorts)){
			log.debug("the param sorts is null~~");
		}else{
			for (Sort sort : sorts){
				if (Validator.isNullOrEmpty(sort)){
					throw new IllegalArgumentException("sort can't be null/empty!");
				}

				ORDER order = sort.getType().equals(Sort.ASC) ? ORDER.asc : ORDER.desc;
				solrQuery.addSortField(sort.getField(), order);
			}
		}
		return solrQuery;
	}

	/**
	 * 将 solrDocumentList 转成bean list.
	 * 
	 * @param solrDocumentList
	 *            the solr document list
	 * @return the list
	 * @see org.apache.solr.client.solrj.response.QueryResponse#getBeans(Class<T>)
	 */
	private List<T> convertSolrDocumentListToBeans(SolrDocumentList solrDocumentList){
		DocumentObjectBinder documentObjectBinder = null;
		if (null == solrServer){
			documentObjectBinder = new DocumentObjectBinder();
		}else{
			documentObjectBinder = solrServer.getBinder();
		}
		/******************************************************/
		List<T> beans = documentObjectBinder.getBeans(modelClass, solrDocumentList);
		return beans;
	}

	/**
	 * 设置start 和row.
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
	 * @return the integer
	 */
	private SolrQuery setStartAndRows(SolrQuery solrQuery,Integer pageNumber,int rows){
		Integer start = toStart(pageNumber, rows);
		solrQuery.setStart(start);
		solrQuery.setRows(rows);

		Object[] args = { pageNumber, start, rows };
		log.debug("pageNumber:{},start:{},rows:{}", args);
		return solrQuery;
	}

	/**
	 * 封装参数.
	 * 
	 * @param <G>
	 *            the generic type
	 * @param pageNumber
	 *            第几页,为自然页,从1开始<br>
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
	 */
	private <G> Pagination<G> generatePagination(int pageNumber,int rows,long count,List<G> items){
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
		if (null == pageNumber || pageNumber < 1){
			// 默认
			pageNumber = 1;
		}

		Integer start = (pageNumber - 1) * rows;
		return start;
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
