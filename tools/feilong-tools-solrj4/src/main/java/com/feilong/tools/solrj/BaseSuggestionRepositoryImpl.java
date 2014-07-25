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
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Suggestion;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.params.SpellingParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.Validator;
import com.feilong.tools.solrj.paramscommand.SpellingParamCommand;

/**
 * 搜索联想
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Nov 29, 2012 3:53:55 PM
 */
public abstract class BaseSuggestionRepositoryImpl implements BaseSuggestionRepository{

	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(BaseSuggestionRepositoryImpl.class);

	// Don't Autowired
	protected SolrServer		solrServer;

	/**
	 * 默认为"/suggest" 可以通过spring DI change this value,You can see Converse project
	 */
	private String				qt	= "/suggest";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jumbo.brandstore.manager.solr.BaseSuggestionRepository#findSuggestionLexeme(com.jumbo.brandstore.manager.solr.command.
	 * SpellingParamCommand)
	 */
	public List<String> findSuggestionLexeme(SpellingParamCommand spellingParamCommand){
		SolrQuery solrQuery = new SolrQuery();

		solrQuery.setQueryType(qt);
		solrQuery.set(CommonParams.Q, "*:*");

		float accuracy = spellingParamCommand.getAccuracy();
		solrQuery.set(SpellingParams.SPELLCHECK_ACCURACY, accuracy + "");

		boolean isBuild = spellingParamCommand.isBuild();
		if (Validator.isNotNullOrEmpty(isBuild)){
			// spellcheck.build=true which is needed only once to build the spellcheck index from the main Solr index.
			// It takes time and should not be specified with each request.
			solrQuery.set(SpellingParams.SPELLCHECK_BUILD, isBuild);
		}

		//
		String collate = spellingParamCommand.getCollate();
		if (Validator.isNotNullOrEmpty(collate)){
			// solrQuery.set(SpellingParams.SPELLCHECK_COLLATE, true);
		}

		//
		boolean isCollateExtendedResults = spellingParamCommand.isCollateExtendedResults();
		solrQuery.set(SpellingParams.SPELLCHECK_COLLATE_EXTENDED_RESULTS, isCollateExtendedResults);

		//
		int count = spellingParamCommand.getCount();
		if (Validator.isNotNullOrEmpty(count)){
			solrQuery.set(SpellingParams.SPELLCHECK_COUNT, count);
		}

		boolean extendedResults = spellingParamCommand.isExtendedResults();
		solrQuery.set(SpellingParams.SPELLCHECK_EXTENDED_RESULTS, extendedResults);

		String q = spellingParamCommand.getQ();
		if (Validator.isNotNullOrEmpty(q)){
			solrQuery.set(SpellingParams.SPELLCHECK_Q, q);
		}

		String dictionary = spellingParamCommand.getDictionary();

		String maxCollationEvaluations = spellingParamCommand.getMaxCollationEvaluations();

		String maxCollations = spellingParamCommand.getMaxCollations();

		String maxCollationTries = spellingParamCommand.getMaxCollationTries();

		String onlyMorePopular = spellingParamCommand.getOnlyMorePopular();

		String reload = spellingParamCommand.getReload();

		// ****************************************************************************************************
		QueryResponse queryResponse = null;
		try{
			queryResponse = solrServer.query(solrQuery);
		}catch (SolrServerException e){
			e.printStackTrace();
		}

		SolrjUtil.showSpellCheckResponse(queryResponse, q);

		SpellCheckResponse spellCheckResponse = queryResponse.getSpellCheckResponse();
		List<Suggestion> suggestionList = spellCheckResponse.getSuggestions();
		if (Validator.isNotNullOrEmpty(suggestionList)){
			List<String> suggestionNameList = new ArrayList<String>();
			for (Suggestion suggestion : suggestionList){
				List<String> alternatives = suggestion.getAlternatives();
				suggestionNameList.addAll(alternatives);
			}
			return suggestionNameList;
		}
		return null;
	}

	/**
	 * @param qt
	 *            the qt to set
	 */
	public void setQt(String qt){
		this.qt = qt;
	}

	/**
	 * @param solrServer
	 *            the solrServer to set
	 */
	public void setSolrServer(SolrServer solrServer){
		this.solrServer = solrServer;
	}
}
