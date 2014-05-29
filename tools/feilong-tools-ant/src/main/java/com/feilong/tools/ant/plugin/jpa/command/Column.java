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
package com.feilong.tools.ant.plugin.jpa.command;

import java.io.Serializable;

/**
 * 表列.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年5月29日 下午4:03:43
 * @since 1.0.7
 */
public class Column implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 288232184048495608L;

	/** 表名称. */
	private String				tableName;

	/** 列名称. */
	private String				columnName;

	/** 列类型. */
	private String				type;

	/** 长度. */
	private String				length;

	/**
	 * 获得 表名称.
	 * 
	 * @return the tableName
	 */
	public String getTableName(){
		return tableName;
	}

	/**
	 * 设置 表名称.
	 * 
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName){
		this.tableName = tableName;
	}

	/**
	 * 获得 列名称.
	 * 
	 * @return the columnName
	 */
	public String getColumnName(){
		return columnName;
	}

	/**
	 * 设置 列名称.
	 * 
	 * @param columnName
	 *            the columnName to set
	 */
	public void setColumnName(String columnName){
		this.columnName = columnName;
	}

	/**
	 * 获得 长度.
	 * 
	 * @return the length
	 */
	public String getLength(){
		return length;
	}

	/**
	 * 设置 长度.
	 * 
	 * @param length
	 *            the length to set
	 */
	public void setLength(String length){
		this.length = length;
	}

	/**
	 * 获得 列类型.
	 * 
	 * @return the type
	 */
	public String getType(){
		return type;
	}

	/**
	 * 设置 列类型.
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(String type){
		this.type = type;
	}
}
