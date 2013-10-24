/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
 * <p/>
 * This software is the confidential and proprietary information of FeiLong
 * Network Technology, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with FeiLong.
 * <p/>
 * FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 * <p/>
 */
package com.feilong.commons.core.entity;

/**
 * 用于 连接object 成为字符串
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 11, 2011 2:37:57 PM
 * @since 1.0
 */
public class JoinStringEntity{

	/**
	 * 连接符
	 */
	private String	connector;

	public JoinStringEntity(){
		super();
	}

	/**
	 * @param connector
	 */
	public JoinStringEntity(String connector){
		super();
		this.connector = connector;
	}

	/**
	 * 连接符
	 * 
	 * @return the connector
	 */
	public String getConnector(){
		return connector;
	}

	/**
	 * 连接符
	 * 
	 * @param connector
	 *            the connector to set
	 */
	public void setConnector(String connector){
		this.connector = connector;
	}
}
