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
package com.feilong.taglib.display.httpconcat.command;

import java.io.Serializable;
import java.util.List;

/**
 * The Class HttpConcatParam.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2014年5月5日 上午11:13:14
 */
public class HttpConcatParam implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 288232184048495608L;

	/** 类型css/js. */
	private String				type;

	/** 版本号. */
	private String				version;

	/**
	 * 根目录<br>
	 * 如果设置root为'/script' 会拼成http://staging.nikestore.com.cn/script/??jquery/jquery-1.4.2.min.js?2013022801
	 */
	private String				root;

	/** The domain. */
	private String				domain;

	/** 是否支持 http concat(如果设置这个参数,本次渲染,将会覆盖全局变量). */
	private Boolean				httpConcatSupport	= null;

	/** The item src list. */
	private List<String>		itemSrcList;

	/**
	 * 获得 类型css/js.
	 * 
	 * @return the type
	 */
	public String getType(){
		return type;
	}

	/**
	 * 设置 类型css/js.
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(String type){
		this.type = type;
	}

	/**
	 * 获得 版本号.
	 * 
	 * @return the version
	 */
	public String getVersion(){
		return version;
	}

	/**
	 * 设置 版本号.
	 * 
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version){
		this.version = version;
	}

	/**
	 * 获得 根目录<br>
	 * 如果设置root为'/script' 会拼成http://staging.nikestore.com.cn/script/??jquery/jquery-1.4.2.min.js?2013022801
	 * 
	 * @return the root
	 */
	public String getRoot(){
		return root;
	}

	/**
	 * 设置 根目录<br>
	 * 如果设置root为'/script' 会拼成http://staging.nikestore.com.cn/script/??jquery/jquery-1.4.2.min.js?2013022801
	 * 
	 * @param root
	 *            the root to set
	 */
	public void setRoot(String root){
		this.root = root;
	}

	/**
	 * 获得 the domain.
	 * 
	 * @return the domain
	 */
	public String getDomain(){
		return domain;
	}

	/**
	 * 设置 the domain.
	 * 
	 * @param domain
	 *            the domain to set
	 */
	public void setDomain(String domain){
		this.domain = domain;
	}

	/**
	 * 获得 the item src list.
	 * 
	 * @return the itemSrcList
	 */
	public List<String> getItemSrcList(){
		return itemSrcList;
	}

	/**
	 * 设置 the item src list.
	 * 
	 * @param itemSrcList
	 *            the itemSrcList to set
	 */
	public void setItemSrcList(List<String> itemSrcList){
		this.itemSrcList = itemSrcList;
	}

	/**
	 * 获得 是否支持 http concat(如果设置这个参数,本次渲染,将会覆盖全局变量).
	 * 
	 * @return the httpConcatSupport
	 */
	public Boolean getHttpConcatSupport(){
		return httpConcatSupport;
	}

	/**
	 * 设置 是否支持 http concat(如果设置这个参数,本次渲染,将会覆盖全局变量).
	 * 
	 * @param httpConcatSupport
	 *            the httpConcatSupport to set
	 */
	public void setHttpConcatSupport(Boolean httpConcatSupport){
		this.httpConcatSupport = httpConcatSupport;
	}

}
