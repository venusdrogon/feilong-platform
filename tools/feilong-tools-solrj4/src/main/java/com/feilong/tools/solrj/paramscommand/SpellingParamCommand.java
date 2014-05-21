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


package com.feilong.tools.solrj.paramscommand;

import java.io.Serializable;

import org.apache.solr.common.params.SpellingParams;

/**
 * 设置和SpellingParam 有关的参数.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Nov 29, 2012 3:26:36 PM
 * @see SpellingParams
 */
public class SpellingParamCommand implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID		= 288232184048495608L;

	/**
	 * The name of the dictionary to be used for giving the suggestion for a request. The value for this parameter is configured in
	 * solrconfig.xml
	 */
	private String				dictionary;

	/**
	 * The count of suggestions needed for a given query.
	 * <p/>
	 * If this parameter is absent in the request then only one suggestion is returned. <br>
	 * If it is more than one then a maximum of given suggestions are returned for each token in the query.
	 */
	private int					count					= 10;

	/**
	 * When this parameter is set to true and the misspelled word exists in the user field, only words that occur more frequently in the
	 * Solr field than the one given will be returned. The default value is false.
	 * <p/>
	 * <b>This is applicable only for dictionaries built from Solr fields.</b>
	 */
	private String				onlyMorePopular;

	/**
	 * Whether to use the extended response format, which is more complicated but richer. <br/>
	 * Returns the document frequency for each suggestion and returns one suggestion block for each term in the query string. <br/>
	 * Default is false.
	 * <p/>
	 * <b>This is applicable only for dictionaries built from Solr fields.</b>
	 */
	private boolean				extendedResults			= false;

	/**
	 * Use the value for this parameter as the query to spell check.
	 * <p/>
	 * This parameter is <b>optional</b>. If absent, then the q parameter is used.
	 */
	private String				q;

	/**
	 * Whether to build the index or not. Optional and false by default.
	 */
	private boolean				build					= false;

	/**
	 * Whether to reload the index. Optional and false by default.
	 */
	private String				reload;

	/** Take the top suggestion for each token and create a new query from it. */
	private String				collate;

	/**
	 * The maximum number of collations to return.<br>
	 * Default=1. <br>
	 * Ignored if "spellcheck.collate" is false.
	 */
	private String				maxCollations;

	/**
	 * The maximum number of collations to test by querying against the index.<br>
	 * When testing, the collation is substituted for the original query's "q" param.<br>
	 * Any "qf"s are retained. <br>
	 * If this is set to zero, does not test for hits before returning collations (returned collations may result in zero hits). <br>
	 * Default=0. Ignored of "spellcheck.collate" is false.
	 */
	private String				maxCollationTries;

	/**
	 * The maximum number of word correction combinations to rank and evaluate prior to deciding which collation candidates to test against
	 * the index.<br>
	 * This is a performance safety-net in cases a user enters a query with many misspelled words. <br>
	 * The default is 10,000 combinations.
	 */
	private String				maxCollationEvaluations;

	/**
	 * Whether to use the Extended Results Format for collations. <br>
	 * Includes "before>after" pairs to easily allow clients to generate messages like "no results for PORK.  did you mean POLK?" <br>
	 * Also indicates the # of hits each collation will return on re-query.<br>
	 * Default=false, which retains 1.4-compatible output.
	 */
	private boolean				collateExtendedResults	= false;

	/**
	 * Certain spelling implementations may allow for an accuracy setting.
	 */
	private float				accuracy				= 0.5f;

	/**
	 * Gets the name of the dictionary to be used for giving the suggestion for a request.
	 * 
	 * @return the dictionary
	 */
	public String getDictionary(){
		return dictionary;
	}

	/**
	 * Sets the name of the dictionary to be used for giving the suggestion for a request.
	 * 
	 * @param dictionary
	 *            the dictionary to set
	 */
	public void setDictionary(String dictionary){
		this.dictionary = dictionary;
	}

	/**
	 * Gets the when this parameter is set to true and the misspelled word exists in the user field, only words that occur more frequently
	 * in the Solr field than the one given will be returned.
	 * 
	 * @return the onlyMorePopular
	 */
	public String getOnlyMorePopular(){
		return onlyMorePopular;
	}

	/**
	 * Sets the when this parameter is set to true and the misspelled word exists in the user field, only words that occur more frequently
	 * in the Solr field than the one given will be returned.
	 * 
	 * @param onlyMorePopular
	 *            the onlyMorePopular to set
	 */
	public void setOnlyMorePopular(String onlyMorePopular){
		this.onlyMorePopular = onlyMorePopular;
	}

	/**
	 * Gets the use the value for this parameter as the query to spell check.
	 * 
	 * @return the q
	 */
	public String getQ(){
		return q;
	}

	/**
	 * Sets the use the value for this parameter as the query to spell check.
	 * 
	 * @param q
	 *            the q to set
	 */
	public void setQ(String q){
		this.q = q;
	}

	/**
	 * Gets the whether to reload the index.
	 * 
	 * @return the reload
	 */
	public String getReload(){
		return reload;
	}

	/**
	 * Sets the whether to reload the index.
	 * 
	 * @param reload
	 *            the reload to set
	 */
	public void setReload(String reload){
		this.reload = reload;
	}

	/**
	 * Gets the take the top suggestion for each token and create a new query from it.
	 * 
	 * @return the collate
	 */
	public String getCollate(){
		return collate;
	}

	/**
	 * Sets the take the top suggestion for each token and create a new query from it.
	 * 
	 * @param collate
	 *            the collate to set
	 */
	public void setCollate(String collate){
		this.collate = collate;
	}

	/**
	 * Gets the
	 * <p>
	 * The maximum number of collations to return.
	 * 
	 * @return the maxCollations
	 */
	public String getMaxCollations(){
		return maxCollations;
	}

	/**
	 * Sets the
	 * <p>
	 * The maximum number of collations to return.
	 * 
	 * @param maxCollations
	 *            the maxCollations to set
	 */
	public void setMaxCollations(String maxCollations){
		this.maxCollations = maxCollations;
	}

	/**
	 * Gets the
	 * <p>
	 * The maximum number of collations to test by querying against the index.
	 * 
	 * @return the maxCollationTries
	 */
	public String getMaxCollationTries(){
		return maxCollationTries;
	}

	/**
	 * Sets the
	 * <p>
	 * The maximum number of collations to test by querying against the index.
	 * 
	 * @param maxCollationTries
	 *            the maxCollationTries to set
	 */
	public void setMaxCollationTries(String maxCollationTries){
		this.maxCollationTries = maxCollationTries;
	}

	/**
	 * Gets the
	 * <p>
	 * The maximum number of word correction combinations to rank and evaluate prior to deciding which collation candidates to test against
	 * the index.
	 * 
	 * @return the maxCollationEvaluations
	 */
	public String getMaxCollationEvaluations(){
		return maxCollationEvaluations;
	}

	/**
	 * Sets the
	 * <p>
	 * The maximum number of word correction combinations to rank and evaluate prior to deciding which collation candidates to test against
	 * the index.
	 * 
	 * @param maxCollationEvaluations
	 *            the maxCollationEvaluations to set
	 */
	public void setMaxCollationEvaluations(String maxCollationEvaluations){
		this.maxCollationEvaluations = maxCollationEvaluations;
	}

	/**
	 * Checks if is whether to build the index or not.
	 * 
	 * @return the build
	 */
	public boolean isBuild(){
		return build;
	}

	/**
	 * Sets the whether to build the index or not.
	 * 
	 * @param build
	 *            the build to set
	 */
	public void setBuild(boolean build){
		this.build = build;
	}

	/**
	 * Gets the count of suggestions needed for a given query.
	 * 
	 * @return the count
	 */
	public int getCount(){
		return count;
	}

	/**
	 * Sets the count of suggestions needed for a given query.
	 * 
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count){
		this.count = count;
	}

	/**
	 * Checks if is whether to use the extended response format, which is more complicated but richer.
	 * 
	 * @return the extendedResults
	 */
	public boolean isExtendedResults(){
		return extendedResults;
	}

	/**
	 * Sets the whether to use the extended response format, which is more complicated but richer.
	 * 
	 * @param extendedResults
	 *            the extendedResults to set
	 */
	public void setExtendedResults(boolean extendedResults){
		this.extendedResults = extendedResults;
	}

	/**
	 * Checks if is
	 * <p>
	 * Whether to use the Extended Results Format for collations.
	 * 
	 * @return the collateExtendedResults
	 */
	public boolean isCollateExtendedResults(){
		return collateExtendedResults;
	}

	/**
	 * Sets the
	 * <p>
	 * Whether to use the Extended Results Format for collations.
	 * 
	 * @param collateExtendedResults
	 *            the collateExtendedResults to set
	 */
	public void setCollateExtendedResults(boolean collateExtendedResults){
		this.collateExtendedResults = collateExtendedResults;
	}

	/**
	 * Gets the certain spelling implementations may allow for an accuracy setting.
	 * 
	 * @return the accuracy
	 */
	public float getAccuracy(){
		return accuracy;
	}

	/**
	 * Sets the certain spelling implementations may allow for an accuracy setting.
	 * 
	 * @param accuracy
	 *            the accuracy to set
	 */
	public void setAccuracy(float accuracy){
		this.accuracy = accuracy;
	}

}
