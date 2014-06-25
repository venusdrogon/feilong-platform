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
package com.feilong.tools.jsoup;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.Validate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FeiLongJsoupUtil.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-11 下午11:17:42
 */
public class JsoupUtil{

	/** The Constant log. */
	private final static Logger	log	= LoggerFactory.getLogger(JsoupUtil.class);

	// *******************************************************************************
	/**
	 * 通过url 获得文档.
	 * 
	 * @param urlString
	 *            the url string
	 * @return the document
	 */
	public static Document getDocument(String urlString){
		try{
			URL url = new URL(urlString);
			Document document = Jsoup.parse(url, 10 * 1000);
			return document;
		}catch (MalformedURLException e){
			e.printStackTrace();
			log.error("{} MalformedURLException", urlString);
		}catch (IOException e){
			e.printStackTrace();
			log.error("parse {},io exception", urlString);
		}
		return null;
	}

	/**
	 * 通过url 获得文档.
	 * 
	 * @param url
	 *            the url
	 * @param userAgent
	 *            the user agent
	 * @return the document
	 */
	public static Document getDocument(String url,String userAgent){
		try{
			Document document = Jsoup.connect(url).userAgent(userAgent).timeout(10 * 1000).get();
			return document;
		}catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets the elements by select.
	 * 
	 * @param url
	 *            the url
	 * @param selectQuery
	 *            the select query
	 * @return the elements by select
	 */
	public static Elements getElementsBySelect(String url,String selectQuery){
		Validate.notEmpty(url);
		Validate.notEmpty(selectQuery);
		Document document = JsoupUtil.getDocument(url);
		Elements elements = document.select(selectQuery);
		return elements;
	}

	/**
	 * getElementById.
	 * 
	 * @param url
	 *            the url
	 * @param id
	 *            the id
	 * @return getElementById
	 */
	public static Element getElementById(String url,String id){
		Validate.notEmpty(url);
		Validate.notEmpty(id);
		Document document = JsoupUtil.getDocument(url);
		Element element = document.getElementById(id);
		return element;
	}
}
