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
package com.feilong.tools.jsoup.job51;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.tools.jsoup.JsoupUtil;
import com.feilong.tools.jsoup.JsoupUtilException;
import com.feilong.tools.jsoup.jinbaowang.entity.Enterprise;

/**
 * 网页爬虫---招聘信息.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-12 上午12:40:50
 */
public class JobCrawler{

	/** The Constant log. */
	private final static Logger	log					= LoggerFactory.getLogger(JobCrawler.class);

	/** 上海所有工作. */
	public static String		enterprise_ShangHai	= "http://search.51job.com/jobsearch/search_result.php?fromJs=1&jobarea=0200&district=0000&funtype=0000&industrytype=00&issuedate=9&providesalary=99&keywordtype=2&curr_page=6&lang=c&stype=2&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=01&lonlat=0,0&radius=-1&ord_field=0&list_type=0&fromType=14";

	/**
	 * Test.
	 */
	@Test
	public void test(){
		try{
			Document document = Jsoup.connect(enterprise_ShangHai).timeout(3000).get();
			//.searchPageNav
			Elements elements = document.select(".td2 a");
			List<Enterprise> list = new ArrayList<Enterprise>();
			for (Element element : elements){
				String enterpriseUrl = element.attr("href");
				log.info(enterpriseUrl);
				Enterprise enterprise = getEnterpriseInfo(enterpriseUrl);
				list.add(enterprise);
			}
			//************************************************************
			for (Enterprise enterprise : list){
				log.info(enterprise.getName());
				log.info(enterprise.getEmail());
				log.info(enterprise.getLinkMan());
				log.info(enterprise.getTelephone());
			}
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	/** The url. */
	String	url	= "http://search.51job.com/list/co,c,214871,0000,10,1.html";

	/**
	 * Gets the enterprise info.
	 * 
	 * @param enterpriseUrl
	 *            the enterprise url
	 * @return the enterprise info
	 */
	public Enterprise getEnterpriseInfo(String enterpriseUrl){
		//www.51job.com
		//zhaopin.com
		//chinahr.com
		//01job.cn
		/*******************************************************************************/
		Enterprise enterprise;
		try{
			String selector_enterpriseName = ".sr_bt";
			String selector_email = "p:contains(电子邮箱) a";
			String selector_telephone = "p:contains(电):contains(话)";
			String selector_linkMan = "p:contains(联):contains(联):contains(人)";
			/********************************************************************************/
			Document document_enterprise = JsoupUtil.getDocument(enterpriseUrl);
			/********************************************************************************/
			//enterprise name
			Element element_enterpriseName = document_enterprise.select(selector_enterpriseName).first();
			//email
			Element element_email = document_enterprise.select(selector_email).first();
			//电话
			Element element_telephone = document_enterprise.select(selector_telephone).first();
			//联系人
			Element element_linkMan = document_enterprise.select(selector_linkMan).first();
			enterprise = new Enterprise();
			enterprise.setName(element_enterpriseName.ownText().replace("?", ""));
			enterprise.setEmail(element_email == null ? null : element_email.html());
			enterprise.setLinkMan(element_telephone == null ? null : element_telephone.html());
			enterprise.setTelephone(element_linkMan == null ? null : element_linkMan.html());
			return enterprise;
		}catch (JsoupUtilException e){
			e.printStackTrace();
		}
		return null;
		//**********************************************************
	}

	//	@Test
	//	@Ignore
	/**
	 * Gets the enterprise info.
	 * 
	 */
	public void testGetEnterpriseInfo(){
		getEnterpriseInfo(url);
	}
}
