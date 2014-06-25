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
package com.feilong.entity.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-2-22 下午09:49:19
 */
@Deprecated
public class User implements Serializable{

	/**
	 * Instantiates a new user.
	 */
	public User(){
		super();
	}

	/**
	 * Instantiates a new user.
	 * 
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 * @param createDate
	 *            the create date
	 */
	public User(int id, String name, Date createDate){
		super();
		this.id = id;
		this.createDate = createDate;
		this.name = name;
	}

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= -2163104816950892085L;

	/** The id. */
	private int					id;

	/** The create date. */
	private Date				createDate;

	/** The name. */
	private String				name;

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public int getId(){
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(int id){
		this.id = id;
	}

	/**
	 * Gets the creates the date.
	 * 
	 * @return the creates the date
	 */
	public Date getCreateDate(){
		return createDate;
	}

	/**
	 * Sets the creates the date.
	 * 
	 * @param createDate
	 *            the new creates the date
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName(){
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name){
		this.name = name;
	}
}
