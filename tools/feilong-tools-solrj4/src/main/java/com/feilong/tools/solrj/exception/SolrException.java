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
package com.feilong.tools.solrj.exception;

/**
 * solr exception,必须处理.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 18, 2013 11:39:19 PM
 */
public class SolrException extends Exception{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 6991993033857005758L;

	/**
	 * Instantiates a new solr exception.
	 */
	public SolrException(){
		super();
	}

	/**
	 * Instantiates a new solr exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public SolrException(String message, Throwable cause){
		super(message, cause);
	}

	/**
	 * Instantiates a new solr exception.
	 * 
	 * @param message
	 *            the message
	 */
	public SolrException(String message){
		super(message);
	}

	/**
	 * Instantiates a new solr exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public SolrException(Throwable cause){
		super(cause);
	}
}
