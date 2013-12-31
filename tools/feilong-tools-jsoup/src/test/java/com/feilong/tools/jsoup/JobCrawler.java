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
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.tools.jsoup.entity.Enterprise;

/**
 *网页爬虫---招聘信息
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-12 上午12:40:50
 */
public class JobCrawler{

	private final static Logger	log					= LoggerFactory.getLogger(JobCrawler.class);

	/**
	 * 上海所有工作
	 */
	public static String		enterprise_ShangHai	= "http://search.51job.com/jobsearch/search_result.php?fromJs=1&jobarea=0200&district=0000&funtype=0000&industrytype=00&issuedate=9&providesalary=99&keywordtype=2&curr_page=6&lang=c&stype=2&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=01&lonlat=0,0&radius=-1&ord_field=0&list_type=0&fromType=14";

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
				System.out.println(enterprise.getName());
				System.out.println(enterprise.getEmail());
				System.out.println(enterprise.getLinkMan());
				System.out.println(enterprise.getTelephone());
			}
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	String	url	= "http://search.51job.com/list/co,c,214871,0000,10,1.html";

	public Enterprise getEnterpriseInfo(String enterpriseUrl){
		//www.51job.com
		//zhaopin.com
		//chinahr.com
		//01job.cn
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
		/*******************************************************************************/
		Enterprise enterprise = new Enterprise();
		enterprise.setName(element_enterpriseName.ownText().replace("?", ""));
		enterprise.setEmail(element_email == null ? null : element_email.html());
		enterprise.setLinkMan(element_telephone == null ? null : element_telephone.html());
		enterprise.setTelephone(element_linkMan == null ? null : element_linkMan.html());
		return enterprise;
		//**********************************************************
	}

	//	@Test
	//	@Ignore
	public void getEnterpriseInfo(){
		getEnterpriseInfo(url);
	}
}
