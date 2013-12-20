/**
 * Copyright (c) 2010 Jumbomart All Rights Reserved.

 *
 * This software is the confidential and proprietary information of Jumbomart.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jumbo.
 *
 * JUMBOMART MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. JUMBOMART SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 */
package com.feilong.tools.solrj.exception;

/**
 * solr exception,必须处理
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 18, 2013 11:39:19 PM
 */
public class SolrException extends Exception{

	private static final long	serialVersionUID	= 6991993033857005758L;

	public SolrException(){
		super();
	}

	public SolrException(String message, Throwable cause){
		super(message, cause);
	}

	public SolrException(String message){
		super(message);
	}

	public SolrException(Throwable cause){
		super(cause);
	}
}
