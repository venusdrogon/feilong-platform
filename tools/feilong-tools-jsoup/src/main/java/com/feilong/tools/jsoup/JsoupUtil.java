/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
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
 * FeiLongJsoupUtil
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-11 下午11:17:42
 */
public class JsoupUtil{

	private final static Logger	log	= LoggerFactory.getLogger(JsoupUtil.class);

	// *******************************************************************************
	/**
	 * 通过url 获得文档
	 * 
	 * @param urlString
	 * @return
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
	 * 通过url 获得文档
	 * 
	 * @param urlString
	 * @return
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

	public static Elements getElementsBySelect(String url,String selectQuery){
		Validate.notEmpty(url);
		Validate.notEmpty(selectQuery);
		Document document = JsoupUtil.getDocument(url);
		Elements elements = document.select(selectQuery);
		return elements;
	}

	/**
	 * getElementById
	 * 
	 * @param url
	 * @param id
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
