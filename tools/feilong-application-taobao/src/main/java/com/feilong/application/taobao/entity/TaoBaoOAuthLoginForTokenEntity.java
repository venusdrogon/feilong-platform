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
package com.feilong.application.taobao.entity;

import java.io.Serializable;

/**
 * 基于OAuth2.0的登录验证授权,用授权码Code换取Token
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-10-17 15:55:56
 */
public class TaoBaoOAuthLoginForTokenEntity implements Serializable{

	/**
	 * Instantiates a new tao bao o auth login for token entity.
	 * 
	 * @param requestUrl
	 *            the request url
	 * @param clientId
	 *            the client id
	 * @param redirectUri
	 *            the redirect uri
	 * @param grantType
	 *            the grant type
	 * @param code
	 *            the code
	 * @param clientSecret
	 *            the client secret
	 */
	public TaoBaoOAuthLoginForTokenEntity(String requestUrl, String clientId, String redirectUri, String grantType, String code,
					String clientSecret){
		super();
		this.requestUrl = requestUrl;
		client_id = clientId;
		redirect_uri = redirectUri;
		grant_type = grantType;
		this.code = code;
		client_secret = clientSecret;
	}

	/**
	 * Instantiates a new tao bao o auth login for token entity.
	 */
	public TaoBaoOAuthLoginForTokenEntity(){
		super();
	}

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 4087772680781105962L;

	/**
	 * 访问请求, 请求url,https://oauth.taobao.com/token
	 */
	private String				requestUrl;

	/** Y 即创建应用时的Appkey. */
	private String				client_id;

	/** Y 登录后的回调地址，（注意：此地址必须要与注册应用时的回调地址相匹配，匹配规则是：注域名完全匹配. */
	private String				redirect_uri;

	/** Y 授权类型 authorization_code 或者 refresh_token. */
	private String				grant_type;

	/** Y 授权请求中的授权码,即第一步获取到的code. */
	private String				code;

	/** Y 客户密钥，即appsecret. */
	private String				client_secret;

	/**
	 * Y 即创建应用时的Appkey.
	 * 
	 * @return the client_id
	 */
	public String getClient_id(){
		return client_id;
	}

	/**
	 * Y 即创建应用时的Appkey.
	 * 
	 * @param clientId
	 *            the client_id to set
	 */
	public void setClient_id(String clientId){
		client_id = clientId;
	}

	/**
	 * Y 登录后的回调地址，（注意：此地址必须要与注册应用时的回调地址相匹配，匹配规则是：注域名完全匹配.
	 * 
	 * @return the redirect_uri
	 */
	public String getRedirect_uri(){
		return redirect_uri;
	}

	/**
	 * Y 登录后的回调地址，（注意：此地址必须要与注册应用时的回调地址相匹配，匹配规则是：注域名完全匹配.
	 * 
	 * @param redirectUri
	 *            the redirect_uri to set
	 */
	public void setRedirect_uri(String redirectUri){
		redirect_uri = redirectUri;
	}

	/**
	 * 访问请求, 请求url,https://oauth.taobao.com/authorize
	 * 
	 * @return the requestUrl
	 */
	public String getRequestUrl(){
		return requestUrl;
	}

	/**
	 * 访问请求, 请求url,https://oauth.taobao.com/authorize
	 * 
	 * @param requestUrl
	 *            the requestUrl to set
	 */
	public void setRequestUrl(String requestUrl){
		this.requestUrl = requestUrl;
	}

	/**
	 * Y 授权类型 authorization_code 或者 refresh_token.
	 * 
	 * @return the grant_type
	 */
	public String getGrant_type(){
		return grant_type;
	}

	/**
	 * Y 授权类型 authorization_code 或者 refresh_token.
	 * 
	 * @param grantType
	 *            the grant_type to set
	 */
	public void setGrant_type(String grantType){
		grant_type = grantType;
	}

	/**
	 * Y 授权请求中的授权码,即第一步获取到的code.
	 * 
	 * @return the code
	 */
	public String getCode(){
		return code;
	}

	/**
	 * Y 授权请求中的授权码,即第一步获取到的code.
	 * 
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code){
		this.code = code;
	}

	/**
	 * Y 客户密钥，即appsecret.
	 * 
	 * @return the client_secret
	 */
	public String getClient_secret(){
		return client_secret;
	}

	/**
	 * Y 客户密钥，即appsecret.
	 * 
	 * @param clientSecret
	 *            the client_secret to set
	 */
	public void setClient_secret(String clientSecret){
		client_secret = clientSecret;
	}
}
