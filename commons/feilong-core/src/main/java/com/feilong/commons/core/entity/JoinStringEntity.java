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
package com.feilong.commons.core.entity;

import java.io.Serializable;

/**
 * 用于 连接object 成为字符串.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 11, 2011 2:37:57 PM
 * @since 1.0
 */
public class JoinStringEntity implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 3182446945343865398L;

	/** 连接符. */
	private String				connector;

	/**
	 * Instantiates a new join string entity.
	 */
	public JoinStringEntity(){
		super();
	}

	/**
	 * Instantiates a new join string entity.
	 * 
	 * @param connector
	 *            the connector
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
