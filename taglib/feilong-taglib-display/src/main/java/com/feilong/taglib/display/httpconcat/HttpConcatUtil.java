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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.configure.ResourceBundleUtil;
import com.feilong.commons.core.text.MessageFormatUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.taglib.display.httpconcat.command.HttpConcatParam;

/**
 * The Class HttpConcatUtil.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2014年5月5日 上午11:04:37
 */
public final class HttpConcatUtil{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger		log	= LoggerFactory.getLogger(HttpConcatUtil.class);

	/** The Constant TEMPLATE_CSS. */
	private static final String		TEMPLATE_CSS;

	/** The Constant TEMPLATE_JS. */
	private static final String		TEMPLATE_JS;

	/** 是否支持 HTTP_CONCAT. */
	private static final Boolean	HTTP_CONCAT_SUPPORT;

	// XXX 支持多变量
	static{
		HTTP_CONCAT_SUPPORT = ResourceBundleUtil.getValue("config/tengine", "tengineSupport", Boolean.class);
		TEMPLATE_CSS = ResourceBundleUtil.getValue("config/tengine", "template.css");
		TEMPLATE_JS = ResourceBundleUtil.getValue("config/tengine", "template.js");
	}

	/**
	 * Gets the write content.
	 * 
	 * @param httpConcatParam
	 *            the http concat param
	 * @return the write content
	 */
	public static String getWriteContent(HttpConcatParam httpConcatParam){
		String type = httpConcatParam.getType();
		String template = getTemplate(type);

		if (HTTP_CONCAT_SUPPORT){
			// concat
			return MessageFormatUtil.format(template, getConcatLink(httpConcatParam));
		}

		// 本地开发环境支持的.
		StringBuffer sb = new StringBuffer();
		List<String> itemSrcList = httpConcatParam.getItemSrcList();
		for (String itemSrc : itemSrcList){
			sb.append(MessageFormatUtil.format(template, getNoConcatLink(itemSrc, httpConcatParam)));
		}
		return sb.toString();
	}

	/**
	 * 获得合并的链接.
	 * 
	 * @param itemSrcList
	 *            the file list
	 * @return the link
	 */
	private static String getConcatLink(HttpConcatParam httpConcatParam){
		List<String> itemSrcList = httpConcatParam.getItemSrcList();
		String domain = httpConcatParam.getDomain();
		String root = httpConcatParam.getRoot();
		String version = httpConcatParam.getVersion();

		StringBuffer sb = new StringBuffer();
		sb.append(domain);
		if (Validator.isNotNullOrEmpty(root)){
			sb.append(root);
		}
		int size = itemSrcList.size();
		if (size == 1){
			sb.append("/");
		}else{
			sb.append("/??");
		}
		for (int i = 0; i < size; i++){
			String itemSrc = itemSrcList.get(i);
			sb.append(itemSrc);
			if (i != size - 1){
				sb.append(",");
			}
		}
		sb.append("?");
		sb.append(version);

		return sb.toString();
	}

	/**
	 * Aa.
	 * 
	 * @param itemSrc
	 *            the src
	 * @return the string
	 */
	private static String getNoConcatLink(String itemSrc,HttpConcatParam httpConcatParam){

		String domain = httpConcatParam.getDomain();
		String root = httpConcatParam.getRoot();
		String version = httpConcatParam.getVersion();

		StringBuffer sb = new StringBuffer();
		sb.append(domain);
		if (Validator.isNotNullOrEmpty(root)){
			sb.append(root);
		}
		sb.append("/");
		sb.append(itemSrc);
		sb.append("?");
		sb.append(version);
		return sb.toString();
	}

	/**
	 * 不同的type不同的模板.
	 * 
	 * @param type
	 *            the type
	 * @return the template
	 */
	private static String getTemplate(String type){
		if ("css".equalsIgnoreCase(type)){
			return TEMPLATE_CSS;
		}else if ("js".equalsIgnoreCase(type)){
			return TEMPLATE_JS;
		}
		throw new UnsupportedOperationException("type:" + type + " not support!");
	}
}
