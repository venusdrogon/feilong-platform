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
package com.feilong.tools.net.httpclient3;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.httpclient.UsernamePasswordCredentials;

import com.feilong.commons.core.enumeration.HttpMethodType;

/**
 * The Class HttpClientConfig.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.6 2014年5月8日 上午1:08:10
 * @since 1.0.6
 */
public class HttpClientConfig implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long			serialVersionUID	= 288232184048495608L;

	/** 访问的地址. */
	private String						uri;

	/** 协议. */
	private HttpMethodType				httpMethodType;

	/** 一组包含安全规则和明文密码的凭据。这个实现对由HTTP标准规范中定义的标准认证模式是足够的. */
	private UsernamePasswordCredentials	usernamePasswordCredentials;

	/** 请求参数. */
	private Map<String, String>			params;

	/** 代理地址. */
	private String						proxyAddress;

	/** 代理port. */
	private int							proxyPort;

	/**
	 * 获得 访问的地址.
	 *
	 * @return the uri
	 */
	public String getUri(){
		return uri;
	}

	/**
	 * 设置 访问的地址.
	 *
	 * @param uri
	 *            the uri to set
	 */
	public void setUri(String uri){
		this.uri = uri;
	}

	/**
	 * 获得 协议.
	 *
	 * @return the httpMethodType
	 */
	public HttpMethodType getHttpMethodType(){
		return httpMethodType;
	}

	/**
	 * 设置 协议.
	 *
	 * @param httpMethodType
	 *            the httpMethodType to set
	 */
	public void setHttpMethodType(HttpMethodType httpMethodType){
		this.httpMethodType = httpMethodType;
	}

	/**
	 * 获得 一组包含安全规则和明文密码的凭据。这个实现对由HTTP标准规范中定义的标准认证模式是足够的.
	 *
	 * @return the usernamePasswordCredentials
	 */
	public UsernamePasswordCredentials getUsernamePasswordCredentials(){
		return usernamePasswordCredentials;
	}

	/**
	 * 设置 一组包含安全规则和明文密码的凭据。这个实现对由HTTP标准规范中定义的标准认证模式是足够的.
	 *
	 * @param usernamePasswordCredentials
	 *            the usernamePasswordCredentials to set
	 */
	public void setUsernamePasswordCredentials(UsernamePasswordCredentials usernamePasswordCredentials){
		this.usernamePasswordCredentials = usernamePasswordCredentials;
	}

	/**
	 * 获得 请求参数.
	 *
	 * @return the params
	 */
	public Map<String, String> getParams(){
		return params;
	}

	/**
	 * 设置 请求参数.
	 *
	 * @param params
	 *            the params to set
	 */
	public void setParams(Map<String, String> params){
		this.params = params;
	}

	/**
	 * 获得 代理地址.
	 *
	 * @return the proxyAddress
	 */
	public String getProxyAddress(){
		return proxyAddress;
	}

	/**
	 * 设置 代理地址.
	 *
	 * @param proxyAddress
	 *            the proxyAddress to set
	 */
	public void setProxyAddress(String proxyAddress){
		this.proxyAddress = proxyAddress;
	}

	/**
	 * 获得 代理port.
	 *
	 * @return the proxyPort
	 */
	public int getProxyPort(){
		return proxyPort;
	}

	/**
	 * 设置 代理port.
	 *
	 * @param proxyPort
	 *            the proxyPort to set
	 */
	public void setProxyPort(int proxyPort){
		this.proxyPort = proxyPort;
	}
}
