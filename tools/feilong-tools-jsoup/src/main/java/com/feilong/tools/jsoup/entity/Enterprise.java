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
package com.feilong.tools.jsoup.entity;

import java.io.Serializable;

/**
 * 企业信息.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-12 上午01:17:46
 */
public class Enterprise implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 3862419791800963668L;

	/** 企业名称. */
	private String				name;

	/** 电话. */
	private String				telephone;

	/** email. */
	private String				email;

	/** 联系人. */
	private String				linkMan;

	/** 地址(招聘网站). */
	private String				pageUrl;

	//******************************************************************
	/**
	 * 企业名称.
	 * 
	 * @return the 企业名称
	 */
	public String getName(){
		return name;
	}

	/**
	 * 企业名称.
	 * 
	 * @param name
	 *            the new 企业名称
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * 电话.
	 * 
	 * @return the 电话
	 */
	public String getTelephone(){
		return telephone;
	}

	/**
	 * 电话.
	 * 
	 * @param telephone
	 *            the new 电话
	 */
	public void setTelephone(String telephone){
		this.telephone = telephone;
	}

	/**
	 * email.
	 * 
	 * @return the email
	 */
	public String getEmail(){
		return email;
	}

	/**
	 * email.
	 * 
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email){
		this.email = email;
	}

	/**
	 * 联系人.
	 * 
	 * @return the 联系人
	 */
	public String getLinkMan(){
		return linkMan;
	}

	/**
	 * 联系人.
	 * 
	 * @param linkMan
	 *            the new 联系人
	 */
	public void setLinkMan(String linkMan){
		this.linkMan = linkMan;
	}

	/**
	 * 地址(招聘网站).
	 * 
	 * @return the 地址(招聘网站)
	 */
	public String getPageUrl(){
		return pageUrl;
	}

	/**
	 * 地址(招聘网站).
	 * 
	 * @param pageUrl
	 *            the new 地址(招聘网站)
	 */
	public void setPageUrl(String pageUrl){
		this.pageUrl = pageUrl;
	}
}
