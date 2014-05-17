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
package com.feilong.taglib.display.httpconcat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.configure.ResourceBundleUtil;
import com.feilong.commons.core.entity.JoinStringEntity;
import com.feilong.commons.core.log.Slf4jUtil;
import com.feilong.commons.core.security.oneway.MD5Util;
import com.feilong.commons.core.text.MessageFormatUtil;
import com.feilong.commons.core.util.CollectionUtil;
import com.feilong.commons.core.util.ListUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.taglib.display.httpconcat.command.HttpConcatParam;
import com.feilong.tools.json.JsonUtil;

/**
 * http concat的核心工具类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2014年5月5日 上午11:04:37
 */
public final class HttpConcatUtil{

	/** The Constant log. */
	private static final Logger			log			= LoggerFactory.getLogger(HttpConcatUtil.class);

	/** The Constant TEMPLATE_CSS. */
	private static final String			TEMPLATE_CSS;

	/** The Constant TEMPLATE_JS. */
	private static final String			TEMPLATE_JS;

	/** 是否支持 HTTP_CONCAT (全局参数). */
	private static final Boolean		GLOBAL_HTTP_CONCAT_SUPPORT;

	/** 设置缓存是否开启. */
	// XXX
	private boolean						cache		= true;

	/** 缓存视图的映射对象. */
	// XXX
	private final Map<String, String>	viewCache	= new HashMap<String, String>();

	// XXX 支持多变量
	static{
		GLOBAL_HTTP_CONCAT_SUPPORT = ResourceBundleUtil.getValue(
				HttpConcatConstants.CONFIG_FILE,
				HttpConcatConstants.KEY_HTTPCONCAT_SUPPORT,
				Boolean.class);

		if (Validator.isNullOrEmpty(GLOBAL_HTTP_CONCAT_SUPPORT)){
			log.warn(
					"can not find key:[{}],pls ensure you have put the correct configuration file path:[{}]",
					HttpConcatConstants.KEY_HTTPCONCAT_SUPPORT,
					HttpConcatConstants.CONFIG_FILE);
		}
	}

	// 加载模板
	static{
		TEMPLATE_CSS = ResourceBundleUtil.getValue(HttpConcatConstants.CONFIG_FILE, HttpConcatConstants.KEY_TEMPLATE_CSS);
		TEMPLATE_JS = ResourceBundleUtil.getValue(HttpConcatConstants.CONFIG_FILE, HttpConcatConstants.KEY_TEMPLATE_JS);
		if (Validator.isNullOrEmpty(TEMPLATE_CSS)){
			String messagePattern = "can not find key:[{}],pls ensure you have put the correct configuration file path:[{}]";
			throw new IllegalArgumentException(Slf4jUtil.formatMessage(
					messagePattern,
					HttpConcatConstants.KEY_HTTPCONCAT_SUPPORT,
					HttpConcatConstants.CONFIG_FILE));

		}
		if (Validator.isNullOrEmpty(TEMPLATE_JS)){
			String messagePattern = "can not find key:[{}],pls ensure you have put the correct configuration file path:[{}]";
			throw new IllegalArgumentException(Slf4jUtil.formatMessage(
					messagePattern,
					HttpConcatConstants.KEY_HTTPCONCAT_SUPPORT,
					HttpConcatConstants.CONFIG_FILE));
		}
	}

	// *****************************************************************************
	/**
	 * 获得解析的内容.
	 * 
	 * @param httpConcatParam
	 *            the http concat param
	 * @return <ul>
	 *         <li>如果 httpConcatParam isNullOrEmpty, throw {@link NullPointerException}</li>
	 *         <li>如果 httpConcatParam.getItemSrcList() isNullOrEmpty,return null</li>
	 *         <li>如果 httpConcatParam.getType() 不是 js或者css, throw {@link UnsupportedOperationException}</li>
	 *         <li>如果支持 concat,那么生成concat字符串</li>
	 *         <li>如果不支持 concat,那么生成多行js/css 原生的字符串</li>
	 *         </ul>
	 */
	public static String getWriteContent(HttpConcatParam httpConcatParam){
		if (Validator.isNullOrEmpty(httpConcatParam)){
			throw new NullPointerException("the httpConcatParam is null or empty!");
		}

		if (log.isDebugEnabled()){
			log.debug(JsonUtil.format(httpConcatParam));
		}

		// ******************************************************************
		Boolean httpConcatSupport = httpConcatParam.getHttpConcatSupport();
		if (log.isDebugEnabled()){
			log.debug("" + httpConcatSupport);
		}

		if (null == httpConcatSupport){
			httpConcatSupport = GLOBAL_HTTP_CONCAT_SUPPORT;
			// httpConcatParam.setHttpConcatSupport(GLOBAL_HTTP_CONCAT_SUPPORT);
		}

		// *******************************************************************
		// 标准化 httpConcatParam,比如list去重,标准化domain等等
		httpConcatParam = standardHttpConcatParam(httpConcatParam);

		// *********************************************************************************

		String type = httpConcatParam.getType();
		String template = getTemplate(type);

		// *********************************************************************************
		String returnValue = "";
		if (httpConcatSupport){
			// concat
			returnValue = MessageFormatUtil.format(template, getConcatLink(httpConcatParam));
		}else{ // 本地开发环境支持的.
			List<String> itemSrcList = httpConcatParam.getItemSrcList();
			StringBuilder sb = new StringBuilder();
			for (String itemSrc : itemSrcList){
				sb.append(MessageFormatUtil.format(template, getNoConcatLink(itemSrc, httpConcatParam)));
			}
			returnValue = sb.toString();
		}

		// **************************log***************************************************
		if (log.isDebugEnabled()){
			log.debug("returnValue:[{}],length:[{}]", returnValue, returnValue.length());
		}
		return returnValue;
	}

	/**
	 * 标准化 httpConcatParam,比如list去重,标准化domain等等
	 * 
	 * @param httpConcatParam
	 * @return
	 */
	private static HttpConcatParam standardHttpConcatParam(HttpConcatParam httpConcatParam){
		String domain = httpConcatParam.getDomain();
		String root = httpConcatParam.getRoot();

		// 格式化 domain 成 http://www.feilong.com/ 形式
		if (Validator.isNotNullOrEmpty(domain)){
			if (!domain.endsWith("/")){
				domain = domain + "/";
			}
		}else{
			domain = "";
		}
		// 格式化 root 成 xxxx/xxx/ 形式,
		if (Validator.isNotNullOrEmpty(root)){
			if (!root.endsWith("/")){
				root = root + "/";
			}
			if (root.startsWith("/")){
				root = StringUtil.substring(root, 1);
			}
		}else{
			root = "";
		}

		httpConcatParam.setDomain(domain);
		httpConcatParam.setRoot(root);
		// ***************************************************************************

		// 判断item list
		List<String> itemSrcList = httpConcatParam.getItemSrcList();

		if (Validator.isNullOrEmpty(itemSrcList)){
			log.warn("the param itemSrcList isNullOrEmpty,need itemSrcList to create links");
			return null;
		}

		// 去重,元素不重复
		List<String> noRepeatitemList = ListUtil.removeDuplicate(itemSrcList);
		if (Validator.isNullOrEmpty(noRepeatitemList)){
			log.warn("the param noRepeatitemList isNullOrEmpty,need noRepeatitemList to create links");
			return null;
		}
		int noRepeatitemListSize = noRepeatitemList.size();
		int itemSrcListSize = itemSrcList.size();

		if (noRepeatitemListSize != itemSrcListSize){
			log.warn(
					"noRepeatitemList.size():[{}] != itemSrcList.size():[{}],httpConcatParam:{}",
					noRepeatitemListSize,
					itemSrcListSize,
					JsonUtil.format(httpConcatParam));
		}
		httpConcatParam.setItemSrcList(noRepeatitemList);
		// *******************************************************************
		if (log.isDebugEnabled()){
			log.debug("standardHttpConcatParam:{}", JsonUtil.format(httpConcatParam));
		}
		return httpConcatParam;

	}

	// *****************************************************************************
	/**
	 * 生成version号<br>
	 * 目前采用md5加密.
	 * 
	 * @param origin
	 *            the origin
	 * @return the string
	 */
	public static String createVersion(String origin){
		return MD5Util.encode(origin);
	}

	// *****************************************************************************
	/**
	 * 获得合并的链接.
	 * 
	 * @param httpConcatParam
	 *            the http concat param
	 * @return the link
	 */
	private static String getConcatLink(HttpConcatParam httpConcatParam){
		List<String> itemSrcList = httpConcatParam.getItemSrcList();
		String domain = httpConcatParam.getDomain();
		String root = httpConcatParam.getRoot();
		String version = httpConcatParam.getVersion();

		// **********************************************************************************

		StringBuilder sb = new StringBuilder();
		sb.append(domain);
		sb.append(root);

		int size = itemSrcList.size();
		// 只有一条 输出原生字符串
		if (size == 1){
			sb.append(itemSrcList.get(0));
			if (log.isDebugEnabled()){
				log.debug("itemSrcList size==1,will generate primary {}.", httpConcatParam.getType());
			}
		}else{
			sb.append("??");

			JoinStringEntity joinStringEntity = new JoinStringEntity(JoinStringEntity.DEFAULT_CONNECTOR);
			sb.append(CollectionUtil.toString(itemSrcList, joinStringEntity));
		}
		appendVersion(version, sb);

		return sb.toString();
	}

	/**
	 * 获得不需要 Concat 的连接.
	 * 
	 * @param itemSrc
	 *            the src
	 * @param httpConcatParam
	 *            the http concat param
	 * @return the string
	 */
	private static String getNoConcatLink(String itemSrc,HttpConcatParam httpConcatParam){
		String domain = httpConcatParam.getDomain();
		String root = httpConcatParam.getRoot();
		String version = httpConcatParam.getVersion();
		StringBuilder sb = new StringBuilder();
		sb.append(domain);
		sb.append(root);
		sb.append(itemSrc);
		appendVersion(version, sb);
		return sb.toString();
	}

	// /**
	// * 加工结果
	// *
	// * @param sb
	// * @return
	// */
	// private static String handleResult(StringBuilder sb){
	// String string = sb.toString();
	// if (log.isDebugEnabled()){
	// log.debug("the param sb:{}", sb);
	// }
	// // return string.replace("//", "/");
	// return string;
	// }

	/**
	 * Append version.
	 * 
	 * @param version
	 *            the version
	 * @param sb
	 *            the sb
	 */
	private static void appendVersion(String version,StringBuilder sb){
		if (Validator.isNotNullOrEmpty(version)){
			sb.append("?");
			sb.append(version);
		}else{
			if (log.isDebugEnabled()){
				log.debug("the param version isNullOrEmpty,we suggest you to set version value");
			}
		}
	}

	// *****************************************************************************

	/**
	 * 不同的type不同的模板.
	 * 
	 * @param type
	 *            类型 {@link HttpConcatConstants#TYPE_CSS} 以及{@link HttpConcatConstants#TYPE_JS}
	 * @return 目前仅支持 {@link HttpConcatConstants#TYPE_CSS} 以及{@link HttpConcatConstants#TYPE_JS},其余不支持,会抛出
	 *         {@link UnsupportedOperationException}
	 */
	private static String getTemplate(String type){
		if (HttpConcatConstants.TYPE_CSS.equalsIgnoreCase(type)){
			return TEMPLATE_CSS;
		}else if (HttpConcatConstants.TYPE_JS.equalsIgnoreCase(type)){
			return TEMPLATE_JS;
		}
		throw new UnsupportedOperationException("type:[" + type + "] not support!,current time,only support js or css");
	}

}
