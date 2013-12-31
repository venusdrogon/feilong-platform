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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FieldStatsInfo;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Collation;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Correction;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Suggestion;
import org.apache.solr.client.solrj.response.TermsResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.util.NamedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.JsonFormatUtil;
import com.feilong.commons.core.util.Validator;

/**
 * The Class SolrUtil.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-6-1 下午12:39:38
 */
public final class SolrjUtil{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(SolrjUtil.class);

	private SolrjUtil(){}

	/**
	 * Show solr query.
	 * 
	 * @param solrQuery
	 *            the solr query
	 */
	public static void showSolrQuery(SolrQuery solrQuery){
		if (log.isDebugEnabled()){

			Map<String, Object> map = new HashMap<String, Object>();

			// solrQuery.setShowDebugInfo(true);
			map.put("solrQuery.getFields()", solrQuery.getFields());
			map.put("solrQuery.getHighlight()", solrQuery.getHighlight());
			map.put("solrQuery.getQuery()", solrQuery.getQuery());
			map.put("solrQuery.getFacetFields()", solrQuery.getFacetFields());
			map.put("solrQuery.getFacetLimit()", solrQuery.getFacetLimit());
			map.put("solrQuery.getFacetMinCount()", solrQuery.getFacetMinCount());
			map.put("solrQuery.getFacetQuery()", solrQuery.getFacetQuery());
			map.put("solrQuery.getFacetSort()", solrQuery.getFacetSort());
			map.put("solrQuery.getFacetSortString()", solrQuery.getFacetSortString());
			map.put("solrQuery.getFilterQueries()", solrQuery.getFilterQueries());
			map.put("solrQuery.getHighlight()", solrQuery.getHighlight());
			map.put("solrQuery.getHighlightFields()", solrQuery.getHighlightFields());
			map.put("solrQuery.getHighlightFragsize()", solrQuery.getHighlightFragsize());
			map.put("solrQuery.getHighlightRequireFieldMatch()", solrQuery.getHighlightRequireFieldMatch());
			map.put("solrQuery.getHighlightSimplePost()", solrQuery.getHighlightSimplePost());
			map.put("solrQuery.getHighlightSimplePre()", solrQuery.getHighlightSimplePre());
			map.put("solrQuery.getHighlightSnippets()", solrQuery.getHighlightSnippets());
			map.put("solrQuery.getParameterNames()", solrQuery.getParameterNames());
			
			Iterator<String> parameterNamesIterator = solrQuery.getParameterNamesIterator();
			Map<String, String> parameterNameAndValues = new HashMap<String, String>();
			while (parameterNamesIterator.hasNext()){
				String param = (String) parameterNamesIterator.next();
				parameterNameAndValues.put(param, solrQuery.get(param));
			}
			
			map.put("solrQuery parameterNameAndValues()", parameterNameAndValues);
			
			map.put("solrQuery.getQueryType()", solrQuery.getQueryType());
			map.put("solrQuery.getRows()", solrQuery.getRows());
			map.put("solrQuery.getSortField()", solrQuery.getSortField());
			map.put("solrQuery.getSortFields()", solrQuery.getSortFields());
			map.put("solrQuery.getStart()", solrQuery.getStart());
			map.put("solrQuery.getTerms()", solrQuery.getTerms());
			map.put("solrQuery.getTermsFields()", solrQuery.getTermsFields());
			map.put("solrQuery.getTermsLimit()", solrQuery.getTermsLimit());
			map.put("solrQuery.getTermsLower()", solrQuery.getTermsLower());
			map.put("solrQuery.getTermsLowerInclusive()", solrQuery.getTermsLowerInclusive());
			map.put("solrQuery.getTermsMaxCount()", solrQuery.getTermsMaxCount());
			map.put("solrQuery.getTermsMinCount()", solrQuery.getTermsMinCount());
			map.put("solrQuery.getTermsPrefix()", solrQuery.getTermsPrefix());
			map.put("solrQuery.getTermsRaw()", solrQuery.getTermsRaw());
			map.put("solrQuery.getTermsRegex()", solrQuery.getTermsRegex());
			map.put("solrQuery.getTermsRegexFlags()", solrQuery.getTermsRegexFlags());
			map.put("solrQuery.getTermsSortString()", solrQuery.getTermsSortString());
			map.put("solrQuery.getTermsUpper()", solrQuery.getTermsUpper());
			map.put("solrQuery.getTermsUpperInclusive()", solrQuery.getTermsUpperInclusive());
			map.put("solrQuery.getTimeAllowed()", solrQuery.getTimeAllowed());
			map.put("solrQuery.toString()", solrQuery.toString());


			log.debug("{}", JsonFormatUtil.format(map));
		}
	}

	/**
	 * 显示 SpellCheckResponse信息
	 * 
	 * @param queryResponse
	 * @param token
	 */
	public static void showSpellCheckResponse(QueryResponse queryResponse,String token){
		if (log.isDebugEnabled()){
			SpellCheckResponse spellCheckResponse = queryResponse.getSpellCheckResponse();
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("spellCheckResponse.toString()", spellCheckResponse.toString());

			map.put("spellCheckResponse.getCollatedResult()", spellCheckResponse.getCollatedResult());

			List<Collation> collatedResults = spellCheckResponse.getCollatedResults();
			Map<String, Object> collationMap = new LinkedHashMap<String, Object>();

			if (Validator.isNotNullOrEmpty(collatedResults)){
				for (Collation collation : collatedResults){
					collationMap.put("collation.getCollationQueryString()", collation.getCollationQueryString());
					List<Correction> misspellingsAndCorrections = collation.getMisspellingsAndCorrections();
					for (Correction correction : misspellingsAndCorrections){
						collationMap.put("correction.getCorrection()", correction.getCorrection());
						collationMap.put("correction.getOriginal()", correction.getOriginal());
					}
					collationMap.put("collation.getNumberOfHits()", collation.getNumberOfHits());
				}
			}
			map.put("collationMap", collationMap);

			map.put("spellCheckResponse.getFirstSuggestion(token)", spellCheckResponse.getFirstSuggestion(token));
			// map.put("spellCheckResponse.getSuggestion(token)", spellCheckResponse.getSuggestion(token));

			map.put("queryResponse.toString()", queryResponse.toString());

			if (spellCheckResponse != null){
				// List<Suggestion> suggestionList = spellCheckResponse.getSuggestions();
				//
				// List<Map<String, Object>> suggestionMaps = new ArrayList<Map<String, Object>>();
				// for (Suggestion suggestion : suggestionList){
				//
				// Map<String, Object> suggestionMap = toMap(suggestion);
				// suggestionMaps.add(suggestionMap);
				// }
				// map.put("suggestionMaps", suggestionMaps);
				// ---------------------------------------------------------------
				Map<String, Suggestion> suggestionMap = spellCheckResponse.getSuggestionMap();
				Map<String, Object> object = new HashMap<String, Object>();
				for (Map.Entry<String, Suggestion> entry : suggestionMap.entrySet()){
					String key = entry.getKey();
					Suggestion suggestion = entry.getValue();
					Map<String, Object> _suggestionMap = toMap(suggestion);
					object.put(key, _suggestionMap);
				}
				map.put("suggestionMap", object);
			}

			log.debug("{}", JsonFormatUtil.format(map));
		}
	}

	/**
	 * 将suggestion 转成
	 * 
	 * @param suggestion
	 * @return
	 */
	private static Map<String, Object> toMap(Suggestion suggestion){
		Map<String, Object> suggestionMap = new LinkedHashMap<String, Object>();

		suggestionMap.put("suggestion.getNumFound()", suggestion.getNumFound());
		suggestionMap.put("suggestion.getToken()", suggestion.getToken());
		suggestionMap.put("suggestion.getAlternatives()", suggestion.getAlternatives());
		suggestionMap.put("suggestion.getAlternativeFrequencies()", suggestion.getAlternativeFrequencies());
		suggestionMap.put("suggestion.getStartOffset()", suggestion.getStartOffset());
		suggestionMap.put("suggestion.getEndOffset()", suggestion.getEndOffset());
		suggestionMap.put("suggestion.getOriginalFrequency()", suggestion.getOriginalFrequency());
		return suggestionMap;
	}

	/**
	 * Show query response.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param queryResponse
	 *            the query response
	 * @param modelClass
	 *            the model class
	 */
	@SuppressWarnings("rawtypes")
	public static <T> void showQueryResponse(QueryResponse queryResponse,Class<T> modelClass){

		if (log.isDebugEnabled()){

			Map<String, Object> map = new HashMap<String, Object>();
			TermsResponse termsResponse = queryResponse.getTermsResponse();
			map.put("queryResponse.getTermsResponse()", termsResponse);
			map.put("queryResponse.toString()", queryResponse.toString());

			// ********************************************************************
			int qTime = queryResponse.getQTime();
			map.put("queryResponse.getQTime()", qTime);

			String requestUrl = queryResponse.getRequestUrl();
			map.put("queryResponse.getRequestUrl()", requestUrl);

			int status = queryResponse.getStatus();
			map.put("queryResponse.getStatus()", status);

			// ********************************************************************
			NamedList<ArrayList> sortValues = queryResponse.getSortValues();
			map.put("queryResponse.getSortValues()", sortValues);

			// ********************************************************************
			Map<String, Object> debugMap = queryResponse.getDebugMap();
			map.put("queryResponse.getDebugMap()", debugMap);

			// ********************************************************************
			long elapsedTime = queryResponse.getElapsedTime();
			map.put("queryResponse.getElapsedTime()", elapsedTime);
			// ********************************************************************
			Map<String, String> explainMap = queryResponse.getExplainMap();
			map.put("queryResponse.getExplainMap()", explainMap);

			// ********************************************************************
			Map<String, FieldStatsInfo> fieldStatsInfo = queryResponse.getFieldStatsInfo();
			map.put("queryResponse.getFieldStatsInfo()", fieldStatsInfo);

			// ********************************************************************
			Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
			map.put("queryResponse.getHighlighting()", highlighting);

			// ********************************************************************
			SpellCheckResponse spellCheckResponse = queryResponse.getSpellCheckResponse();
			map.put("queryResponse.getSpellCheckResponse()", spellCheckResponse);

			// ********************************************************************
			SolrDocumentList solrDocumentList = queryResponse.getResults();

			long numFound = solrDocumentList.getNumFound();
			map.put("queryResponse.getResults().getNumFound()", numFound);

			// ********************************************************************
			map.put("modelClass.getName", modelClass.getName());

			Map<String, Integer> facetQuery = queryResponse.getFacetQuery();
			map.put("queryResponse.getFacetQuery()", facetQuery);

			List<FacetField> facetFields = queryResponse.getFacetFields();
			map.put("queryResponse.getFacetFields()", facetFields);

			List<T> beans = queryResponse.getBeans(modelClass);
			map.put("beans size", beans.size());

			log.debug("{}", JsonFormatUtil.format(map));
		}
	}
}
