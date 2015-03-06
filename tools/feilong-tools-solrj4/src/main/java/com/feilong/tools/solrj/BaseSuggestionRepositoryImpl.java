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
 * 搜索联想.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Nov 29, 2012 3:53:55 PM
 */
public abstract class BaseSuggestionRepositoryImpl implements BaseSuggestionRepository{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(BaseSuggestionRepositoryImpl.class);

    // Don't Autowired
    /** The solr server. */
    protected SolrServer        solrServer;

    /** 默认为"/suggest" 可以通过spring DI change this value,You can see Converse project. */
    private String              qt  = "/suggest";

    /*
     * (non-Javadoc)
     * 
     * @see com.jumbo.brandstore.manager.solr.BaseSuggestionRepository#findSuggestionLexeme(com.jumbo.brandstore.manager.solr.command.
     * SpellingParamCommand)
     */
    @Override
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
            log.error(e.getClass().getName(), e);
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
     * 设置 默认为"/suggest" 可以通过spring DI change this value,You can see Converse project.
     *
     * @param qt
     *            the qt to set
     */
    public void setQt(String qt){
        this.qt = qt;
    }

    /**
     * 设置 solr server.
     *
     * @param solrServer
     *            the solrServer to set
     */
    public void setSolrServer(SolrServer solrServer){
        this.solrServer = solrServer;
    }
}
