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
package com.feilong.project.train.entity;

import java.io.Serializable;

/**
 * 人员名单.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年9月4日 下午3:51:46
 * @since 1.0.8
 */
public class TrainSignUpEmployeeEntity implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 288232184048495608L;

	/** 姓名. */
	private String				name;

	/** 邮箱. */
	private String				email;

	/** 手机. */
	private String				mobile;

	/** 商城分类. */
	private String				storeCategoryName;

	/** 商城名称 . */
	private String				storeName;

	/**
	 * 获得 姓名.
	 *
	 * @return the name
	 */
	public String getName(){
		return name;
	}

	/**
	 * 设置 姓名.
	 *
	 * @param name
	 *            the name to set
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * 获得 邮箱.
	 *
	 * @return the email
	 */
	public String getEmail(){
		return email;
	}

	/**
	 * 设置 邮箱.
	 *
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email){
		this.email = email;
	}

	/**
	 * 获得 手机.
	 *
	 * @return the mobile
	 */
	public String getMobile(){
		return mobile;
	}

	/**
	 * 设置 手机.
	 *
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	/**
	 * 获得 商城分类.
	 *
	 * @return the storeCategoryName
	 */
	public String getStoreCategoryName(){
		return storeCategoryName;
	}

	/**
	 * 设置 商城分类.
	 *
	 * @param storeCategoryName
	 *            the storeCategoryName to set
	 */
	public void setStoreCategoryName(String storeCategoryName){
		this.storeCategoryName = storeCategoryName;
	}

	/**
	 * 获得 商城名称 .
	 *
	 * @return the storeName
	 */
	public String getStoreName(){
		return storeName;
	}

	/**
	 * 设置 商城名称 .
	 *
	 * @param storeName
	 *            the storeName to set
	 */
	public void setStoreName(String storeName){
		this.storeName = storeName;
	}

}