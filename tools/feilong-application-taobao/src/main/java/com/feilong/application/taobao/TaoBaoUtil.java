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
package com.feilong.application.taobao;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.feilong.application.taobao.entity.TaoBaoOAuthLoginForCodeEntity;
import com.feilong.application.taobao.entity.TaoBaoOAuthLoginForTokenEntity;
import com.feilong.application.taobao.entity.TaoBaoStandardLoginEntity;
import com.feilong.application.taobao.entity.TaoBaoStandardLoginOutEntity;
import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.security.oneway.MD5Util;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.servlet.http.ParamUtil;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.User;
import com.taobao.api.internal.util.WebUtils;
import com.taobao.api.request.UserGetRequest;
import com.taobao.api.response.UserGetResponse;

/**
 * 飞龙淘宝包.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-10-17 下午01:31:02
 */
public class TaoBaoUtil{

	/**
	 * 获得淘宝退出登录,完整url.
	 * 
	 * @param taoBaoStandardLoginOutEntity
	 *            the tao bao standard login out entity
	 * @return 获得淘宝退出登录,完整url,如<br>
	 *         http://container.api.taobao.com/container/logoff?app_key=12129547&sign=06E307E5164260BF87B2E75689AC77B6&sign_method=md5&
	 *         timestamp=2010-12-06 17:19:08 {@link TaoBaoStandardLoginOutEntity}
	 */
	public static String getTaoBaoStandardLoginOutUrl(TaoBaoStandardLoginOutEntity taoBaoStandardLoginOutEntity){
		// validate taoBaoStandardLoginOutEntity
		if (Validator.isNullOrEmpty(taoBaoStandardLoginOutEntity)){
			throw new IllegalArgumentException("Param taoBaoStandardLoginOutEntity must be not null!");
		}
		// validate app_key
		else if (Validator.isNullOrEmpty(taoBaoStandardLoginOutEntity.getApp_key())){
			throw new IllegalArgumentException("Param taoBaoStandardLoginOutEntity.getApp_key() must be not null!");
		}
		// validate app_secret
		else if (Validator.isNullOrEmpty(taoBaoStandardLoginOutEntity.getApp_secret())){
			throw new IllegalArgumentException("Param taoBaoStandardLoginEntity.getApp_secret() must be not null!");
		}
		// validate sign_method
		else if (Validator.isNullOrEmpty(taoBaoStandardLoginOutEntity.getSign_method())){
			throw new IllegalArgumentException("Param taoBaoStandardLoginEntity.getSign_method() must be not null!");
		}
		// validate timestamp
		else if (Validator.isNullOrEmpty(taoBaoStandardLoginOutEntity.getTimestamp())){
			throw new IllegalArgumentException("Param taoBaoStandardLoginEntity.getTimestamp() must be not null!");
		}
		// validate RequestUrl
		else if (Validator.isNullOrEmpty(taoBaoStandardLoginOutEntity.getRequestUrl())){
			throw new IllegalArgumentException("Param taoBaoStandardLoginEntity.getRequestUrl() must be not null!");
		}else{
			TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
			apiparamsMap.put("sign_method", taoBaoStandardLoginOutEntity.getSign_method());
			apiparamsMap.put("app_key", taoBaoStandardLoginOutEntity.getApp_key());
			apiparamsMap.put("timestamp", taoBaoStandardLoginOutEntity.getTimestamp());
			// 组装协议参数sign
			apiparamsMap.put("sign", sign(apiparamsMap, taoBaoStandardLoginOutEntity.getApp_secret()));
			/******************************************************************************************/
			Map<String, String> nameAndValueMap1 = new HashMap<String, String>();
			nameAndValueMap1.putAll(apiparamsMap);
			return ParamUtil.addParameterValueMap(taoBaoStandardLoginOutEntity.getRequestUrl(), nameAndValueMap1, CharsetType.UTF8);
		}
	}

	/**
	 * 获得淘宝 快速登录实现,完整url.
	 * 
	 * @param taoBaoStandardLoginEntity
	 *            the tao bao standard login entity
	 * @return 获得淘宝 快速登录实现,完整url,如<br>
	 *         http://container.api.taobao.com/container/identify?app_key=12129547&sign=B78AC46FDD0470661BC2B9B0E11E10FE&sign_method=md5&
	 *         target =1&timestamp=2010-12-06%2017:23:54 {@link TaoBaoStandardLoginEntity}
	 */
	public static String getTaoBaoStandardLoginUrl(TaoBaoStandardLoginEntity taoBaoStandardLoginEntity){
		// validate taoBaoStandardLoginEntity
		if (Validator.isNullOrEmpty(taoBaoStandardLoginEntity)){
			throw new IllegalArgumentException("Param taoBaoStandardLoginEntity must be not null!");
		}
		// validate app_key
		else if (Validator.isNullOrEmpty(taoBaoStandardLoginEntity.getApp_key())){
			throw new IllegalArgumentException("Param taoBaoStandardLoginEntity.getApp_key() must be not null!");
		}
		// validate app_secret
		else if (Validator.isNullOrEmpty(taoBaoStandardLoginEntity.getApp_secret())){
			throw new IllegalArgumentException("Param taoBaoStandardLoginEntity.getApp_secret() must be not null!");
		}
		// validate sign_method
		else if (Validator.isNullOrEmpty(taoBaoStandardLoginEntity.getSign_method())){
			throw new IllegalArgumentException("Param taoBaoStandardLoginEntity.getSign_method() must be not null!");
		}
		// validate timestamp
		else if (Validator.isNullOrEmpty(taoBaoStandardLoginEntity.getTimestamp())){
			throw new IllegalArgumentException("Param taoBaoStandardLoginEntity.getTimestamp() must be not null!");
		}
		// validate RequestUrl
		else if (Validator.isNullOrEmpty(taoBaoStandardLoginEntity.getRequestUrl())){
			throw new IllegalArgumentException("Param taoBaoStandardLoginEntity.getRequestUrl() must be not null!");
		}else{
			TreeMap<String, String> nameAndValueMap = new TreeMap<String, String>();
			nameAndValueMap.put("sign_method", taoBaoStandardLoginEntity.getSign_method());
			nameAndValueMap.put("app_key", taoBaoStandardLoginEntity.getApp_key());
			nameAndValueMap.put("timestamp", taoBaoStandardLoginEntity.getTimestamp());
			if (Validator.isNotNullOrEmpty(taoBaoStandardLoginEntity.getApp_user_nick())){
				nameAndValueMap.put("app_user_nick", taoBaoStandardLoginEntity.getApp_user_nick());
			}
			if (Validator.isNotNullOrEmpty(taoBaoStandardLoginEntity.getTarget())){
				nameAndValueMap.put("target", taoBaoStandardLoginEntity.getTarget());
			}
			nameAndValueMap.put("sign", sign(nameAndValueMap, taoBaoStandardLoginEntity.getApp_secret()));
			/**************************************************************************/
			Map<String, String> nameAndValueMap1 = new HashMap<String, String>();
			nameAndValueMap1.putAll(nameAndValueMap);
			return ParamUtil.addParameterValueMap(taoBaoStandardLoginEntity.getRequestUrl(), nameAndValueMap1, CharsetType.UTF8);
		}
	}

	/**
	 * 为了获取授权码Code,拼接url.
	 * 
	 * @param taoBaoOAuthLoginForCodeEntity
	 *            the tao bao o auth login for code entity
	 * @return the tao bao o auth login url for get code {@link TaoBaoOAuthLoginForCodeEntity}
	 */
	public static String getTaoBaoOAuthLoginUrlForGetCode(TaoBaoOAuthLoginForCodeEntity taoBaoOAuthLoginForCodeEntity){
		// validate taoBaoOAuthLoginForCodeEntity
		if (Validator.isNullOrEmpty(taoBaoOAuthLoginForCodeEntity)){
			throw new IllegalArgumentException("Param taoBaoOAuthLoginForCodeEntity must be not null!");
		}
		// validate client_id
		else if (Validator.isNullOrEmpty(taoBaoOAuthLoginForCodeEntity.getClient_id())){
			throw new IllegalArgumentException("Param taoBaoOAuthLoginForCodeEntity.getClient_id() must be not null!");
		}
		// validate response_type
		else if (Validator.isNullOrEmpty(taoBaoOAuthLoginForCodeEntity.getResponse_type())){
			throw new IllegalArgumentException("Param taoBaoOAuthLoginForCodeEntity.getResponse_type() must be not null!");
		}
		// validate RequestUrl
		else if (Validator.isNullOrEmpty(taoBaoOAuthLoginForCodeEntity.getRequestUrl())){
			throw new IllegalArgumentException("Param taoBaoOAuthLoginForCodeEntity.getRequestUrl() must be not null!");
		}else{
			TreeMap<String, String> nameAndValueMap = new TreeMap<String, String>();
			nameAndValueMap.put("response_type", taoBaoOAuthLoginForCodeEntity.getResponse_type());
			nameAndValueMap.put("client_id", taoBaoOAuthLoginForCodeEntity.getClient_id());
			nameAndValueMap.put("redirect_uri", taoBaoOAuthLoginForCodeEntity.getRedirect_uri());
			if (Validator.isNotNullOrEmpty(taoBaoOAuthLoginForCodeEntity.getState())){
				nameAndValueMap.put("state", taoBaoOAuthLoginForCodeEntity.getState());
			}
			/**********************************************************************/
			Map<String, String> nameAndValueMap1 = new HashMap<String, String>();
			nameAndValueMap1.putAll(nameAndValueMap);
			/**********************************************************************/
			return ParamUtil.addParameterValueMap(taoBaoOAuthLoginForCodeEntity.getRequestUrl(), nameAndValueMap1, CharsetType.UTF8);
		}
	}

	/**
	 * 获得 oauth 退出地址.
	 * 
	 * @param client_id
	 *            即创建应用时的Appkey
	 * @param redirect_uri
	 *            成功退出后返回到redirect_uri地址
	 * @return the tao bao o auth login out url
	 */
	public static String getTaoBaoOAuthLoginOutUrl(String client_id,String redirect_uri){
		String format = "https://oauth.taobao.com/logoff?client_id=%s&redirect_uri=%s";
		return StringUtil.format(format, client_id, redirect_uri);
	}

	/**
	 * 基于 code 获得 token 值<br>
	 * access_token就相当于sessionkey，后续调用其他接口可以直接使用.
	 * 
	 * @param taoBaoOAuthLoginForTokenEntity
	 *            the tao bao o auth login for token entity
	 * @return the tao bao o auth access token
	 */
	public static String getTaoBaoOAuthAccessToken(TaoBaoOAuthLoginForTokenEntity taoBaoOAuthLoginForTokenEntity){
		Map<String, String> props = new HashMap<String, String>();
		props.put("grant_type", taoBaoOAuthLoginForTokenEntity.getGrant_type());
		props.put("code", taoBaoOAuthLoginForTokenEntity.getCode());
		props.put("client_id", taoBaoOAuthLoginForTokenEntity.getClient_id());
		props.put("client_secret", taoBaoOAuthLoginForTokenEntity.getClient_secret());
		props.put("redirect_uri", taoBaoOAuthLoginForTokenEntity.getRedirect_uri());
		// grant_type Y 授权类型 authorization_code 或者 refresh_token
		// code Y 授权请求中的授权码,即第一步获取到的code
		// redirect_uri Y 登录后的回调地址，（注意：此地址必须要与注册应用时的回调地址相匹配，匹配规则是：注域名完全匹配
		// client_id Y 客户标识，即appkey
		// client_secret Y 客户密钥，即appsecret
		try{
			// WebUtils 平台SDK封装
			String s = WebUtils.doPost(taoBaoOAuthLoginForTokenEntity.getRequestUrl(), props, 30000, 30000);
			try{
				// 解析获取token
				JSONObject jsonObject = new JSONObject(s);
				// 判断是否获取到token，若获取失败，可以根据返回的错误描述排查
				if (!jsonObject.has("error")){
					return jsonObject.getString("access_token");
				}
			}catch (JSONException e){
				e.printStackTrace();
			}
		}catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取当前登录用户User数据.
	 * 
	 * @param access_token
	 *            access_token就相当于sessionkey，后续调用其他接口可以直接使用
	 * @param serverUrl
	 *            正式环境：http://gw.api.taobao.com/router/rest <br>
	 *            沙箱环境：http://gw.api.tbsandbox.com/router/rest
	 * @param appKey
	 *            appKey
	 * @param appSecret
	 *            appSecret
	 * @param fields
	 *            需返回的字段列表。<br>
	 *            可选值：User结构体中的所有字段；以半角逗号(,)分隔。<br>
	 *            不支持：地址，真实姓名，身份证，手机，电话<br>
	 * @return User {@link User}
	 */
	public static User getUserByAccessToken(String access_token,String serverUrl,String appKey,String appSecret,String fields){
		TaobaoClient taobaoClient = new DefaultTaobaoClient(serverUrl, appKey, appSecret);
		UserGetRequest userGetRequest = new UserGetRequest();
		userGetRequest.setFields(fields);
		try{
			UserGetResponse userGetResponse = taobaoClient.execute(userGetRequest, access_token);
			if (userGetResponse.isSuccess()){
				User user = userGetResponse.getUser();
				return user;
			}
		}catch (ApiException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取当前登录用户User数据.
	 * 
	 * @param access_token
	 *            access_token就相当于sessionkey，后续调用其他接口可以直接使用
	 * @param serverUrl
	 *            正式环境：http://gw.api.taobao.com/router/rest <br>
	 *            沙箱环境：http://gw.api.tbsandbox.com/router/rest
	 * @param appKey
	 *            appKey
	 * @param appSecret
	 *            appSecret
	 * @param fields
	 *            需返回的字段列表。<br>
	 *            可选值：User结构体中的所有字段； <br>
	 *            不支持：地址，真实姓名，身份证，手机，电话<br>
	 * @return User {@link User}
	 */
	public static User getUserByAccessToken(String access_token,String serverUrl,String appKey,String appSecret,String[] fields){
		String _fields = StringUtils.join(fields, ',');
		return getUserByAccessToken(access_token, serverUrl, appKey, appSecret, _fields);
	}

	/**
	 * sign生成规则：
	 * <ul>
	 * <li>1.拼装<br>
	 * 根据参数名称将你的所有请求参数按照字母先后顺序排序:key + value .... key + value <br>
	 * value对是除签名和图片外的所有请求参数按key做的升序排列, value无需编码。 <br>
	 * 例如将foo=1,bar=2,baz=3 排序为bar=2,baz=3,foo=1 <br>
	 * 参数名和参数值链接后，得到拼装字符串bar2baz3foo1</li>
	 * <li>2.签名(utf-8编码)<br>
	 * md5:将secretcode同时拼接到参数字符串头、尾部进行md5加密，再转化成大写，<br>
	 * 格式 是：uppercase(hex(md5(secretkey1value1key2value2...secret))。<br>
	 * 例 如:uppercase(hex(md5("secretbar2baz3foo1secret"))) <br>
	 * 注：hex为自定义方法，目的是将二进制转16进制</li>
	 * </ul>
	 * 
	 * @param params
	 *            the params
	 * @param secret
	 *            the secret
	 * @return the string
	 */
	public static String sign(final TreeMap<String, String> params,String secret){
		if (null == params || params.isEmpty()){
			return null;
		}
		if (Validator.isNullOrEmpty(secret)){
			return null;
		}
		// 1 拼接
		StringBuilder sb = new StringBuilder();
		sb.append(secret);
		for (Iterator<Entry<String, String>> it = params.entrySet().iterator(); it.hasNext();){
			Entry<String, String> entry = it.next();
			sb.append(entry.getKey());
			sb.append(null == entry.getValue() ? "" : entry.getValue());
		}
		sb.append(secret);
		// 签名(utf-8编码)
		byte[] bytes = StringUtil.toBytes(sb.toString(), CharsetType.UTF8);
		return MD5Util.encode(bytes).toUpperCase();
	}
}
