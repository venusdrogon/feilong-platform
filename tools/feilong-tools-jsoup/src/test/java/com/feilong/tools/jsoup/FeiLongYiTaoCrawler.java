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

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-2-17 上午9:35:07
 */
public class FeiLongYiTaoCrawler{

	private static final Logger	log	= LoggerFactory.getLogger(FeiLongYiTaoCrawler.class);

	String						url	= "http://s.etao.com/search?q=439182-452&tbpm=t";

	@Test
	public void test(){
		// 数据
		Elements elements = JsoupUtil.getElementsBySelect(url, ".section");
		Element element = elements.get(0);
		// 列表数据
		Elements elements1 = elements.select(".listview");
		Element element1 = elements1.get(0);
		// 列表li
		Elements lis = element1.select(".listitem");
		for (Element li : lis){
			log.info("title:{}", li.select(".title a").get(0).text());
			log.info("price:{}", li.select(".price").get(0).text());
			log.info("freight:{}", li.select(".freight").get(0).text());
			log.info("url:{}", li.select(".title a").get(0).attr("href"));
			log.info("--------------------------------------------------");
		}
		// log.info(element.html());
		// log.info(element1.html());
	}
}
